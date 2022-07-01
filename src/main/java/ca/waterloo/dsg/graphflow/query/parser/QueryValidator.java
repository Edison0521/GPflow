package ca.waterloo.dsg.graphflow.query.parser;

import ca.waterloo.dsg.graphflow.query.QueryGraph;

/**
 * The validator is called against input query graphs to ensure: (1) the query graph is connected;
 * and (2) to remove any duplicate relations in the query.
 */
public class QueryValidator {

    public static void validate(QueryGraph queryGraph) {
        // - Any two vertices have only one edge between them.
        if (queryGraph.isSimple()) {
            throw new IllegalArgumentException();
        }
        // - Either structural OR fully typed and labeled. // TODO: after submission.
        // - It is a connected query. TODO: after submission.
    }
}
