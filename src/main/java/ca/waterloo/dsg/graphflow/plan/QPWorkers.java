package ca.waterloo.dsg.graphflow.plan;

import ca.waterloo.dsg.graphflow.plan.operator.Operator;
import ca.waterloo.dsg.graphflow.plan.operator.scan.ScanBlocking;
import ca.waterloo.dsg.graphflow.plan.operator.scan.ScanBlocking.VertexIdxLimits;
import ca.waterloo.dsg.graphflow.storage.Graph;
import ca.waterloo.dsg.graphflow.storage.KeyStore;
import ca.waterloo.dsg.graphflow.util.IOUtils;
import ca.waterloo.dsg.graphflow.util.container.Triple;
import lombok.Getter;
import lombok.var;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Query plan workers execute a query plan in parallel given a number of threads.
 */
public class QPWorkers {

    protected static final Logger logger = LogManager.getLogger(QPWorkers.class);

    private QueryPlan[] queryPlans;
    private Thread[] workers;

    @Getter private double elapsedTime = 0;
    private long intersectionCost = 0;
    private long numIntermediateTuples = 0;
    private long numOutTuples = 0;
    transient private List<Triple<String /* name */,
        Long /* i-cost */, Long /* prefixes size */>> operatorMetrics;

    /**
     * Constructs a {@link QPWorkers} object.
     *
     * @param queryPlan is the query plan to execute.
     * @param numThreads is the number of threads to use executing the query.
     */
    public QPWorkers(QueryPlan queryPlan, int numThreads) {
        queryPlans = new QueryPlan[numThreads];
        if (numThreads == 1) {
            queryPlans[0] = queryPlan;
        } else { // numThreads > 1
            for (int i = 0; i < numThreads; i++) {
                queryPlans[i] = queryPlan.copy(true /* isThreadSafe */);
            }
            workers = new Thread[numThreads];
            var globalVertexIdxLimits = new VertexIdxLimits();
            for (var plan : queryPlans) {
                for (var lastOperator : plan.subplans) {
                    var operator = lastOperator;
                    while (null != operator.getPrev()) {
                        operator = operator.getPrev();
                    }
                    if (operator instanceof ScanBlocking) {
                        ((ScanBlocking) operator).setGlobalVerticesIdxLimits(globalVertexIdxLimits);
                    }
                }
            }
        }
    }

    public void init(Graph graph, KeyStore store) {
        for (var queryPlan : queryPlans) {
            queryPlan.init(graph, store);
        }
    }

    public void execute() throws InterruptedException {
        if (queryPlans.length == 1) {
            queryPlans[0].execute();
            elapsedTime = queryPlans[0].getElapsedTime();
        } else {
            // TODO: update LIMIT for the workers class.
            var beginTime = System.nanoTime();
            for (int i = 0; i < queryPlans.length; i++) {
                workers[i] = new Thread(queryPlans[i]::execute);
                workers[i].start();
            }
            for (var worker : workers) {
                worker.join();
            }
            elapsedTime = IOUtils.getElapsedTimeInMillis(beginTime);
        }
    }

    //TODO outMatches
    public void outMatch(String outfileId) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test/process_2_produce/yago4_pre_Table/pre_Table"+outfileId+".txt")));
        //one thread                         
        for(String str: queryPlans[0].sink.matches){
            str = str.replace("[","");
            str = str.replace("]","");
            str = str.replace(" ","");
            bufferedWriter.write(str+"\n");
        }
        bufferedWriter.close();
    }

    /**
     * @return The stats as a one line comma separated CSV  one line row for logging.
     */
    public String getOutputLog() throws Operator.LimitExceededException {
        if (queryPlans.length == 1) {
            return queryPlans[0].getOutputLog();
        }
        if (null == operatorMetrics) {
            operatorMetrics = new ArrayList<>();
            for (var queryPlan : queryPlans) {
                queryPlan.setStats();
            }
            aggregateOutput();
        }
        var strJoiner = new StringJoiner(",");
        strJoiner.add(String.format("%.4f", elapsedTime));
        strJoiner.add(String.format("%d", numOutTuples));
        strJoiner.add(String.format("%d", numIntermediateTuples));
        strJoiner.add(String.format("%d", intersectionCost));
        for (var operatorMetric : operatorMetrics) {
            strJoiner.add(String.format("%s", operatorMetric.a));     /* operator name */
            if (!operatorMetric.a.contains("PROBE") && !operatorMetric.a.contains("HASH") &&
                !operatorMetric.a.contains("SCAN")) {
                strJoiner.add(String.format("%d", operatorMetric.b)); /* i-cost */
            }
            if (!operatorMetric.a.contains("HASH")) {
                strJoiner.add(String.format("%d", operatorMetric.c)); /* output tuples size */
            }
        }
        return strJoiner.toString() + "\n";
    }

    private void aggregateOutput() {
        operatorMetrics = new ArrayList<>();
        for (var queryPlan : queryPlans) {
            intersectionCost += queryPlan.getIcost();
            numIntermediateTuples += queryPlan.getNumIntermediateTuples();
            numOutTuples += queryPlan.getNumOutTuples();
        }
        var queryPlan = queryPlans[0];
        for (var metric : queryPlan.getOperatorMetrics()) {
            operatorMetrics.add(new Triple<>(metric.a, metric.b, metric.c));
        }
        for (int i = 1; i < queryPlans.length; i++) {
            for (int j = 0; j < operatorMetrics.size(); j++) {
                operatorMetrics.get(j).b += queryPlans[i].getOperatorMetrics().get(j).b;
                operatorMetrics.get(j).c += queryPlans[i].getOperatorMetrics().get(j).c;
            }
        }
    }
}
