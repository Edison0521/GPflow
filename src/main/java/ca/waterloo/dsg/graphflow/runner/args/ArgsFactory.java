package ca.waterloo.dsg.graphflow.runner.args;

import lombok.var;
import org.apache.commons.cli.Option;

/**
 * The class containing all Command Line Options options needed by the runners.
 */
public class ArgsFactory {

    public static Option getHelpOption() {
        return new Option("h" /* HELP */, "help", false, "Print this message.");
    }

    /*
     * Serialize DataSet Runner:
     * ~~~~~~~~~~~~~~~~~~~~~~~~~
     *      INPUT_FILE_EDGES        -e
     *      EDGES_FILE_SEPARATOR    -m
     *      INPUT_FILE_VERTICES     -v
     *      VERTICES_FILE_SEPARATOR -n
     *      SERIALIZE_OUTPUT        -o
     *      UNDIRECTED              -u
     */
    public static String INPUT_FILE_VERTICES = "v";
    public static String INPUT_FILE_EDGES = "e";
    public static String EDGES_FILE_SEPARATOR = "m";
    public static String VERTICES_FILE_SEPARATOR = "n";
    public static String SERIALIZE_OUTPUT = "o";
    public static String UNDIRECTED = "u";

    public static Option getInputFileEdges() {
        var option = new Option(INPUT_FILE_EDGES, "input_file_edges", true /* hasArg */,
            "The separator between columns in the input CSV file.");
        option.setRequired(true);
        return option;
    }

    public static Option getOutputDirOption() {
        var option = new Option(SERIALIZE_OUTPUT, "output", true,
            "Absolute path to serialize the input graph.");
        option.setRequired(true);
        return option;
    }

    public static Option getEdgesFileSeparator() {
        return new Option(EDGES_FILE_SEPARATOR, "edge_separator", true /* hasArg */,
            "The separator between columns in the input CSV file. The default is set to ','.");
    }

    public static Option getInputFileVertices() {
        return new Option(INPUT_FILE_VERTICES, "input_file_vertices", true /* hasArg */,
            "The absolute path to the vertices csv file.");
    }

    public static Option getVerticesFileSeparator() {
        return new Option(VERTICES_FILE_SEPARATOR, "vertices_separator", true /* hasArg */,
            "The separator between columns in the input CSV file. The default is set to ','.");
    }

    public static String QUERY = "q";

    public static Option getQueryOption() {
        var option = new Option(QUERY, "query", true,
            "Query graph to evaluate e.g. '(a)->(b)' and '(a)->(b), (b)->(c)'");
        option.setRequired(true);
        return option;
    }

    /*
     * Query Plan Executor:
     * ~~~~~~~~~~~~~~~~~~~~
     *      INPUT_GRAPH_DIR    -i
     *      INPUT_SER_PLAN     -p
     *      OUTPUT_FILE        -o
     *      NUM_THREADS        -t
     *      PARTITION_SIZE     -s
     *      DISABLE_FLATTENING -f
     *      DISABLE_CACHE      -c
     *      ENABLE_ADAPTIVITY  -a
     *
     * Query Plan JSON Executor:
     * ~~~~~~~~~~~~~~~~~~~~~~~~~
     *      INPUT_GRAPH_DIR    -i
     *      INPUT_JSON_PLAN    -j
     *      NUM_THREADS        -t
     *      PARTITION_SIZE     -s
     *      ENABLE_ADAPTIVITY  -a (same as 'Query Plan Executor')
     */
    public static String INPUT_GRAPH_DIR = "i";
    public static String OUTPUT_FILE = "o";
    public static String NUM_THREADS = "t";

    public static Option getInputGraphDirectoryOption() {
        var option = new Option(INPUT_GRAPH_DIR, "input_graph_dir", true,
            "Absolute path to the directory of the serialized input graph.");
        option.setRequired(true);
        return option;
    }

    public static Option getNotRequiredOutputFileOption() {
        return new Option(OUTPUT_FILE, "output_file", true,
            "Absolute path to the output log file.");
    }

    public static Option getNumberThreadsOption() {
        return new Option(NUM_THREADS, "number_threads", true,
            "Number of threads used to parallelize the computation.");
    }

    /*
     * Serialize Catalog Runner:
     * ~~~~~~~~~~~~~~~~~~~~~~~~~
     *      INPUT_GRAPH            -i (same as 'Query Plan Executor')
     *      NUM_SAMPLED_EDGES      -n
     *      NUM_MAX_INPUT_VERTICES -v
     *      NUM_THREADS            -t (same as 'Query Plan Executor')
     */
    public static String NUM_SAMPLED_EDGES = "n";
    public static String NUM_MAX_INPUT_VERTICES = "v";

    public static Option getNumberEdgesToSampleOption() {
        return new Option(NUM_SAMPLED_EDGES, "number_edges_to_sample", true,
            "The number of edges to sample when scanning for the catalog query transform.");
    }

    public static Option getMaxInputNumVerticesOption() {
        return new Option(NUM_MAX_INPUT_VERTICES, "vertices", true,
            "The max number of vertices for input subgraphs when collecting catalog stats.");
    }

    public static Option getIsGraphUndirected() {
        return new Option(UNDIRECTED, "undirected", false, "hint: the input graph is undirected.");
    }

    /*
     * Query Executor:
     * ~~~~~~~~~~~~~~~~~~~
     *      INPUT_GRAPH_DIR          -i (same as 'Query Plan Executor')
     *      QUERY                    -q (same as 'Query Plans Generator')
     *      EXECUTE_PLAN             -e
     *      DISABLE_FLATTENING       -f (same as 'Query Plan Executor')
     *      ENABLE_ADAPTIVITY        -a (same as 'Query Plan Executor')
     */
    public static String EXECUTE_PLAN = "e";

    public static Option getExecuteOption() {
        return new Option(EXECUTE_PLAN, "execute", false, "Execute the optimizer's picked plan.");
    }
}
