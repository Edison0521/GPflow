package ca.waterloo.dsg.graphflow.plan.operator.sink;

import ca.waterloo.dsg.graphflow.plan.operator.Operator;
import ca.waterloo.dsg.graphflow.query.QueryGraph;
import lombok.var;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * A sink operator simulating copying the output tuples to a pre-allocated buffer.
 */
public class SinkCopy extends Sink {

    public int[] outputTuple;

    /**
     * Constructs a {@link SinkCopy} object.
     *
     * @param queryGraph is the {@link QueryGraph}, the tuples in the sink match.
     * @param outTupleLength is the output tuple length.
     */
    public SinkCopy(QueryGraph queryGraph, int outTupleLength) {
        super(queryGraph);
        this.outTupleLen = outTupleLength;
        this.outputTuple = new int[outTupleLength];
    }

    /**
     * @see Operator#processNewTuple()
     */
    @Override
    public void processNewTuple() {
        // simulates a copy to an output buffer assuming allocations happen before execution.
        System.arraycopy(probeTuple, 0, outputTuple, 0, outTupleLen);
    }

    /**
     * @see Operator#copy(boolean)
     */
    @Override
    public SinkCopy copy(boolean isThreadSafe) {
        var sink = new SinkCopy(outSubgraph, outTupleLen);
        sink.prev = this.prev.copy(isThreadSafe);
        return sink;
    }
}
