package ca.waterloo.dsg.graphflow.plan.operator.sink;

import ca.waterloo.dsg.graphflow.plan.operator.Operator;
import ca.waterloo.dsg.graphflow.query.QueryGraph;
import lombok.var;

import java.io.*;
import java.util.Arrays;

/**
 * A sink operator printing each output tuple.
 */
public class SinkPrint extends Sink {

    /**
     * Constructs a {@link SinkPrint} object.
     *
     * @param queryGraph is the {@link QueryGraph}, the tuples in the sink match.
     */
    public SinkPrint(QueryGraph queryGraph) {
        super(queryGraph);
    }

    /**
     * @see Operator#processNewTuple()
     */
    @Override
    public void processNewTuple() {
        //TODO print,select 1 million matches
        //System.out.println(Arrays.toString(probeTuple));
        if(matches.size()<1000000) matches.add(Arrays.toString(probeTuple));
    }

    /**
     * @see Operator#copy(boolean)
     */
    @Override
    public SinkPrint copy(boolean isThreadSafe) {
        var sink = new SinkPrint(outSubgraph);
        sink.prev = this.prev.copy(isThreadSafe);
        return sink;
    }
}
