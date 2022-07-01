package ca.waterloo.dsg.graphflow.query;

import ca.waterloo.dsg.graphflow.storage.KeyStore;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;
import java.util.StringJoiner;

/**
 * A join query graph.
 */
public class QueryGraph implements Serializable {

    // Represents a map from a from to a to query vertex & the query edge between them.
    private Map<String, Map<String, List<QueryEdge>>> qVertexToQEdgesMap = new HashMap<>();
    @Getter private Map<String, Short> qVertexToTypeMap = new HashMap<>();
    private Map<String, int[]> qVertexToDegMap = new HashMap<>();
    @Getter private List<QueryEdge> qEdges = new ArrayList<>();

    // Mapping iterator used to decide if two query graphs are homeomorphism.
    private SubgraphMappingIterator it = null;
    private String encoding;
    @Getter @Setter private int limit;

    public short getQVertexType(String queryVertex) {
        return qVertexToTypeMap.get(queryVertex) == null ? 0 : qVertexToTypeMap.get(queryVertex);
    }

    public void initQVertexType() {
        for (var qVertex : qVertexToTypeMap.keySet()) {
            if (KeyStore.ANY == qVertexToTypeMap.get(qVertex)) {
                qVertexToTypeMap.put(qVertex, (short) 0);
            }
        }
    }

    public void setQVertexType(String queryVertex, Short toType) {
        qVertexToTypeMap.put(queryVertex, toType);
        for (var qEdge : qEdges) {
            if (qEdge.getFromQueryVertex().equals(queryVertex)) {
                qEdge.setFromType(toType);
            } else if (qEdge.getToQueryVertex().equals(queryVertex)) {
                qEdge.setToType(toType);
            }
        }
    }

    public void addQEdges(Collection<QueryEdge> queryEdges) {
        queryEdges.forEach(this::addQEdge);
    }

    /**
     * Adds a relation to the {@link QueryGraph}. The relation is stored both in forward and
     * backward direction. There can be multiple relations with different directions and relation
     * types between two qVertices. A backward relation between fromQueryVertex and toQueryVertex is
     * represented by a {@link QueryEdge} from toQueryVertex to fromQueryVertex.
     *
     * @param qEdge The relation to be added.
     */
    public void addQEdge(QueryEdge qEdge) {
        if (qEdge == null) {
            return;
        }
        // Get the vertex IDs.
        var fromQVertex = qEdge.getFromQueryVertex();
        var toQVertex = qEdge.getToQueryVertex();
        var fromType = qEdge.getFromType();
        var toType = qEdge.getToType();
        qVertexToTypeMap.putIfAbsent(fromQVertex, KeyStore.ANY);
        qVertexToTypeMap.putIfAbsent(toQVertex, KeyStore.ANY);
        if (KeyStore.ANY != fromType) {
            qVertexToTypeMap.put(fromQVertex, fromType);
        }
        if (KeyStore.ANY != toType) {
            qVertexToTypeMap.put(toQVertex, toType);
        }
        // Set the in and out degrees for each variable.
        if (!qVertexToDegMap.containsKey(fromQVertex)) {
            int[] degrees = new int[2];
            qVertexToDegMap.put(fromQVertex, degrees);
        }
        qVertexToDegMap.get(fromQVertex)[0]++;
        if (!qVertexToDegMap.containsKey(toQVertex)) {
            int[] degrees = new int[2];
            qVertexToDegMap.put(toQVertex, degrees);
        }
        qVertexToDegMap.get(toQVertex)[1]++;
        // Add fwd edge fromQVertex -> toQVertex to the qVertexToQEdgesMap.
        addQEdgeToQGraph(fromQVertex, toQVertex, qEdge);
        // Add bwd edge toQVertex <- fromQVertex to the qVertexToQEdgesMap.
        addQEdgeToQGraph(toQVertex, fromQVertex, qEdge);
        qEdges.add(qEdge);
    }

    /**
     * Adds the new {@link QueryEdge} to the query vertex to query edges map.
     *
     * @param fromQVertex is the from query vertex.
     * @param toQVertex is the to query vertex.
     * @param qEdge is the {@link QueryEdge} containing the query vertices and their types.
     */
    private void addQEdgeToQGraph(String fromQVertex, String toQVertex, QueryEdge qEdge) {
        qVertexToQEdgesMap.putIfAbsent(fromQVertex, new HashMap<>());
        qVertexToQEdgesMap.get(fromQVertex).putIfAbsent(toQVertex, new ArrayList<>());
        qVertexToQEdgesMap.get(fromQVertex).get(toQVertex).add(qEdge);
    }

    /**
     * Adds the new query vertex to the query vertex to query edges map.
     *
     * @param qVertex is the query vertex.
     */
    public void addQVertex(String qVertex) {
        qVertexToQEdgesMap.putIfAbsent(qVertex, new HashMap<>());
    }

    /**
     * @param queryGraph is the {@link QueryGraph} to get isomorphic mappings for.
     * @return The mapping iterator.
     */
    public SubgraphMappingIterator getSubgraphMappingIterator(QueryGraph queryGraph) {
        if (null == it) {
            it = new SubgraphMappingIterator(new ArrayList<>(qVertexToQEdgesMap.keySet()));
        }
        it.init(queryGraph);
        return it;
    }

    /**
     * @return A copy of all the query vertices present in the query.
     */
    public Set<String> getQVertices() {
        return new HashSet<>(qVertexToQEdgesMap.keySet());
    }

    /**
     * @return The number of query vertices present in the query.
     */
    public int getNumQVertices() {
        return qVertexToQEdgesMap.size();
    }

    /**
     * @return The number of query edges
     */
    public int getNumQEdges() {
        return qEdges.size();
    }

    /**
     * @param variable The variable.
     * @param neighborVariables The collection of to qVertices.
     * @return A list of {@link QueryEdge}s representing all the relations present between the
     * {@code variable} and each of the {@code neighborVariable}s in the setAdjListSortOrder.
     * @throws NoSuchElementException if the {@code variable} is not present in the
     * {@link QueryGraph}.
     */
    public List<QueryEdge> getQEdges(String variable, Collection<String> neighborVariables) {
        var adjacentRelations = new ArrayList<QueryEdge>();
        for (var neighborVariable : neighborVariables) {
            adjacentRelations.addAll(getQEdges(variable, neighborVariable));
        }
        return adjacentRelations;
    }

    /**
     * @param variable The from variable.
     * @param neighborVariable The to variable.
     * @return A list of {@link QueryEdge}s representing all the relations present between
     * {@code variable} and {@code neighborVariable}.
     * @throws NoSuchElementException if the {@code variable} is not present in the
     * {@link QueryGraph}.
     */
    public List<QueryEdge> getQEdges(String variable, String neighborVariable) {
        if (!qVertexToQEdgesMap.containsKey(variable)) {
            throw new NoSuchElementException("The variable '" + variable + "' is not present.");
        }
        if (!qVertexToQEdgesMap.get(variable).containsKey(neighborVariable)) {
            return new ArrayList<>();
        }
        return qVertexToQEdgesMap.get(variable).get(neighborVariable);
    }

    /**
     * @param fromVariables The set of {@code String} qVertices to get their to qVertices.
     * @return The set of {@code String} to qVertices.
     */
    public Set<String> getNeighbors(Collection<String> fromVariables) {
        var toVariables = new HashSet<String>();
        for (var fromVariable : fromVariables) {
            if (!qVertexToQEdgesMap.containsKey(fromVariable)) {
                throw new NoSuchElementException("The variable '" + fromVariable + "' is not " +
                    "present.");
            }
            toVariables.addAll(getNeighbors(fromVariable));
        }
        toVariables.removeAll(fromVariables);
        return toVariables;
    }

    /**
     * @param fromVariable The {@code String} variable to get its to qVertices.
     * @return The setAdjListSortOrder of {@code String} to qVertices.
     */
    public List<String> getNeighbors(String fromVariable) {
        if (!qVertexToQEdgesMap.containsKey(fromVariable)) {
            throw new NoSuchElementException("The variable '" + fromVariable + "' is not present.");
        }
        return new ArrayList<>(qVertexToQEdgesMap.get(fromVariable).keySet());
    }

    /**
     * @param vertex1 is one of the qVertices.
     * @param vertex2 is another query vertex of the qVertices.
     * @return {@code true} if there is a query edge between {@code vertex1} and {@code vertex2} in
     * any direction, {@code false} otherwise.
     */
    public boolean containsQueryEdge(String vertex1, String vertex2) {
        return qVertexToQEdgesMap.containsKey(vertex1) &&
            qVertexToQEdgesMap.get(vertex1).containsKey(vertex2);
    }

    /**
     * @return True if the query graph is a clique, false otherwise.
     */
    public boolean isClique() {
        var numVertices = qVertexToQEdgesMap.keySet().size();
        for (var qVertex : qVertexToQEdgesMap.keySet()) {
            if (qVertexToQEdgesMap.get(qVertex).keySet().size() < numVertices - 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return True if the query graph is acyclic, false otherwise.
     */
    public boolean isAcyclic() {
        Set<String> queryVertexNames = new HashSet<>();
        Stack<String> queryVariableStack = new Stack<>();
        queryVariableStack.push(qEdges.get(0).getFromQueryVertex());
        queryVertexNames.add(qEdges.get(0).getFromQueryVertex());
        while (!queryVariableStack.isEmpty()) {
            String currentName = queryVariableStack.pop();
            List<String> neighbourVariables = getNeighbors(currentName);
            boolean foundParent = false;
            for (String neighbour : neighbourVariables) {
                if (queryVertexNames.contains(neighbour) && foundParent) {
                    return false;
                } else if (queryVertexNames.contains(neighbour)) {
                    foundParent = true;
                } else {
                    queryVertexNames.add(neighbour);
                    queryVariableStack.add(neighbour);
                }
            }
        }
        return true;
    }

    /**
     * @return True if the query graph is a connected component. False, otherwise.
     */
    public boolean isConnected() {
        var traversedQVertices = new HashSet<String>();
        var frontierQVertices = new LinkedList<String>();
        traversedQVertices.add(qEdges.get(0).getFromQueryVertex());
        frontierQVertices.add(qEdges.get(0).getFromQueryVertex());
        while (!frontierQVertices.isEmpty()) {
            var variable = frontierQVertices.remove();
            var neighbourVariables = getNeighbors(variable);
            for (var neighbourVariable : neighbourVariables) {
                if (!traversedQVertices.contains(neighbourVariable)) {
                    frontierQVertices.add(neighbourVariable);
                    traversedQVertices.add(neighbourVariable);
                }
            }
        }
        return traversedQVertices.size() == getQVertices().size();
    }

    /**
     * Check if the {@link QueryGraph} is isomorphic to another given {@link QueryGraph}.
     *
     * @param otherQueryGraph The other {@link QueryGraph} to check for isomorphism.
     * @return True, if the query graph and oQGraph are isomorphic. False, otherwise.
     */
    public boolean isIsomorphicTo(QueryGraph otherQueryGraph) {
        return null != otherQueryGraph && getEncoding().equals(otherQueryGraph.getEncoding()) &&
            ((0 == qEdges.size() && otherQueryGraph.getQEdges().size() == 0) ||
                null != getSubgraphMappingIfAny(otherQueryGraph));
    }

    public Map<String, String> getIsomorphicMappingIfAny(QueryGraph otherQueryGraph) {
        if (!isIsomorphicTo(otherQueryGraph)) {
            return null;
        }
        return getSubgraphMappingIfAny(otherQueryGraph);
    }

    private Map<String, String> getSubgraphMappingIfAny(QueryGraph otherQueryGraph) {
        var it = getSubgraphMappingIterator(otherQueryGraph);
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    private Set<String> getQueryVertices() {
        return new HashSet<>(qVertexToQEdgesMap.keySet());
    }

    /**
     * @return A copy of the {@link QueryGraph} object.
     */
    public QueryGraph copy() {
        var queryGraphCopy = new QueryGraph();
        queryGraphCopy.addQEdges(qEdges);
        return queryGraphCopy;
    }

    /**
     * @param queryEdgeToExclude The relation to exclude from the queryGraph being copied.
     * @return A copy of the {@link QueryGraph} object excluding the given relation.
     */
    public QueryGraph copyExcluding(QueryEdge queryEdgeToExclude) {
        var queryGraphCopy = new QueryGraph();
        for (QueryEdge queryEdge : qEdges) {
            if (!queryEdge.getFromQueryVertex().equals(queryEdgeToExclude.getFromQueryVertex()) ||
                    !queryEdge.getToQueryVertex().equals(queryEdgeToExclude.getToQueryVertex())) {
                queryGraphCopy.addQEdge(queryEdge);
            }
        }
        return queryGraphCopy;
    }

    public boolean isSimple() {
        for (var fromVertex : qVertexToQEdgesMap.keySet()) {
            for (var toVertex : qVertexToQEdgesMap.get(fromVertex).keySet()) {
                if (qVertexToQEdgesMap.get(fromVertex).get(toVertex).size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCycleQuery() {
        if (getNumQVertices() != getNumQEdges()) {
            return false;
        }
        for (var fromVertex : qVertexToQEdgesMap.keySet()) {
            var degrees = 0;
            for (var toVertex : qVertexToQEdgesMap.keySet()) {
                if (fromVertex.equals(toVertex)) {
                    continue;
                }
                if (null != qVertexToQEdgesMap.get(fromVertex).get(toVertex)) {
                    degrees += qVertexToQEdgesMap.get(fromVertex).get(toVertex).size();
                }
            }
            if (degrees != 2) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return a {@link String} encoding based on the degree of vertices and direction of edges that
     * can be used as a hash.
     */
    public String getEncoding() {
        if (encoding == null) {
            var queryVerticesEncoded = new String[qVertexToQEdgesMap.size()];
            int vertexIdx = 0;
            for (var fromVertex : qVertexToQEdgesMap.keySet()) {
                var encodingStrBuilder = new StringBuilder();
                for (var toVertex : qVertexToQEdgesMap.get(fromVertex).keySet()) {
                    var queryEdges = qVertexToQEdgesMap.get(fromVertex).get(toVertex);
                    for (var queryEdge : queryEdges) {
                        if (fromVertex.equals(queryEdge.getFromQueryVertex())) {
                            encodingStrBuilder.append("F");
                        } else {
                            encodingStrBuilder.append("B");
                        }
                    }
                }
                var encodingToSort = encodingStrBuilder.toString().toCharArray();
                Arrays.sort(encodingToSort);
                queryVerticesEncoded[vertexIdx++] = new String(encodingToSort);
            }
            Arrays.sort(queryVerticesEncoded);
            encoding = String.join(".", queryVerticesEncoded);
        }
        return encoding;
    }

    public String toStringWithTypesAndLabels() {
        var stringJoiner = new StringJoiner("");
        var isFirstQueryEdge = true;
        for (var fromVertex : qVertexToQEdgesMap.keySet()) {
            for (var toVertex : qVertexToQEdgesMap.get(fromVertex).keySet()) {
                var fromType = qVertexToTypeMap.get(fromVertex);
                var toType = qVertexToTypeMap.get(toVertex);
                var queryEdges = qVertexToQEdgesMap.get(fromVertex).get(toVertex);
                for (var queryEdge : queryEdges) {
                    var label = queryEdge.getLabel();
                    if (queryEdge.getFromQueryVertex().equals(fromVertex)) {
                        if (isFirstQueryEdge) {
                            stringJoiner.add(String.format("(%s:%s)-[%s]->(%s:%s)", fromVertex,
                                fromType, label, toVertex, toType));
                            isFirstQueryEdge = false;
                        } else {
                            stringJoiner.add(String.format(", (%s:%s)-[%s]->(%s:%s)", fromVertex,
                                fromType, label, toVertex, toType));
                        }
                    }
                }
            }
        }
        return stringJoiner.toString();
    }

    @Override
    public String toString() {
        var stringJoiner = new StringJoiner("");
        var isFirstQueryEdge = true;
        for (var fromVertex : qVertexToQEdgesMap.keySet()) {
            for (var toVertex : qVertexToQEdgesMap.get(fromVertex).keySet()) {
                var queryEdges = qVertexToQEdgesMap.get(fromVertex).get(toVertex);
                for (var queryEdge : queryEdges) {
                    if (queryEdge.getFromQueryVertex().equals(fromVertex)) {
                        if (isFirstQueryEdge) {
                            stringJoiner.add(String.format("(%s)->(%s)", fromVertex, toVertex));
                            isFirstQueryEdge = false;
                        } else {
                            stringJoiner.add(String.format(", (%s)->(%s)", fromVertex, toVertex));
                        }
                    }
                }
            }
        }
        return stringJoiner.toString();
    }

    /**
     * An iterator over a set of possible mappings between two query graphs.
     */
    public class SubgraphMappingIterator implements Iterator<Map<String, String>>, Serializable {

        List<String> qVertices;
        List<String> oQVertices;
        QueryGraph   oQGraph;

        Map<String, String> next;
        boolean isNextComputed;

        Stack<String> currMapping = new Stack<>();
        int currentIdx;
        int[] vertexIndices;
        List<List<String>> verticesForIdx = new ArrayList<>();

        /**
         * Constructs an iterator for variable mappings between two query graphs.
         *
         * @param queryVertices are the query vertices of 'this' query graph.
         */
        SubgraphMappingIterator(List<String> queryVertices) {
            this.qVertices = queryVertices;
            this.next = new HashMap<>();
            for (var variable : this.qVertices) {
                next.put(variable, "");
            }
        }

        /**
         * Initialized the iterator based on the other query graph to map qVertices to.
         *
         * @param oQueryGraph The {@link QueryGraph} to map qVertices to.
         */
        void init(QueryGraph oQueryGraph) {
            this.oQVertices = new ArrayList<>(oQueryGraph.getQueryVertices());
            this.oQGraph = oQueryGraph;
            currentIdx = 0;
            vertexIndices = new int[oQVertices.size()];
            currMapping.clear();
            for (int i = 0; i < oQVertices.size(); i++) {
                if (verticesForIdx.size() <= i) {
                    verticesForIdx.add(new ArrayList<>());
                } else {
                    verticesForIdx.get(i).clear();
                }
                var oQVertex = oQVertices.get(i);
                var oQVertexDeg = oQueryGraph.qVertexToDegMap.get(oQVertex);
                for (var j = 0; j < qVertices.size(); j++) {
                    var qVertex = qVertices.get(j);
                    var type = qVertexToTypeMap.get(qVertex);
                    var qVertexDeg = qVertexToDegMap.get(qVertex);
                    if (oQueryGraph.qVertexToTypeMap.get(oQVertex).equals(type) &&
                        (Arrays.equals(oQVertexDeg, qVertexDeg) ||
                        (oQVertices.size() < qVertices.size() &&
                            qVertexDeg[0] >= oQVertexDeg[0] && qVertexDeg[1] >= oQVertexDeg[1]))) {
                        verticesForIdx.get(i).add(qVertex);
                    }
                }
                if (0 == verticesForIdx.get(i).size()) {
                    isNextComputed = true;
                    return;
                }
            }
            isNextComputed = false;
            hasNext();
        }

        /**
         * @see Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            if (!isNextComputed) {
                if (currMapping.size() == oQVertices.size()) {
                    currMapping.pop();
                }
                Outer: do {
                    var nextIdx = currMapping.size();
                    if (nextIdx == 0 && vertexIndices[0] < verticesForIdx.get(0).size()) {
                        currMapping.add(verticesForIdx.get(0).get(vertexIndices[0]++));
                    } else if (vertexIndices[nextIdx] < verticesForIdx.get(nextIdx).size()) {
                        var newVar = verticesForIdx.get(nextIdx).get(vertexIndices[nextIdx]++);
                        var otherForNew = oQVertices.get(nextIdx);
                        for (var i = 0; i < currMapping.size(); i++) {
                            var prevVar = currMapping.get(i);
                            if (prevVar.equals(newVar)) {
                                continue Outer;
                            }
                            var otherForPrev = oQVertices.get(i);
                            var qEdges = qVertexToQEdgesMap.get(newVar).get(prevVar);
                            var oQEdges = oQGraph.qVertexToQEdgesMap.get(otherForNew).get(
                                otherForPrev);
                            if (qEdges == null && oQEdges == null) {
                                continue;
                            }
                            if (qEdges == null || oQEdges == null ||
                                qEdges.size() != oQEdges.size()) {
                                continue Outer;
                            }
                            if (qEdges.size() == 0) {
                                continue;
                            }
                            var qEdge = qEdges.get(0);
                            var oQEdge = oQEdges.get(0);
                            if (qEdge.getLabel() != oQEdge.getLabel()) {
                                continue;
                            }
                            if (!((qEdge.getFromQueryVertex().equals(prevVar) &&
                                    oQEdge.getFromQueryVertex().equals(otherForPrev)) ||
                                (qEdge.getFromQueryVertex().equals(newVar) &&
                                    oQEdge.getFromQueryVertex().equals(otherForNew)))) {
                                continue Outer;
                            }
                        }
                        currMapping.add(newVar);
                    } else if (vertexIndices[nextIdx] >= verticesForIdx.get(nextIdx).size()) {
                        currMapping.pop();
                        vertexIndices[nextIdx] = 0;
                    }
                    if (currMapping.size() == oQVertices.size()) {
                        break;
                    }
                } while (!(currMapping.size() == 0 &&
                    vertexIndices[0] >= verticesForIdx.get(0).size()));
                isNextComputed = true;
            }
            if (!currMapping.isEmpty()) {
                var sameEdgeLabels = true;
                for (var i = 0; i < currMapping.size(); i++) {
                    for (var j = i + 1; j < currMapping.size(); j++) {
                        var qVertex = currMapping.get(i);
                        var oQVertex = currMapping.get(j);
                        if (containsQueryEdge(qVertex, oQVertex)) {
                            var qEdge = getQEdges(qVertex, oQVertex).get(0);
                            var oQEdge = oQGraph.getQEdges(oQVertices.get(i), oQVertices.get(j)).
                                get(0);
                            if (qEdge.getLabel() != oQEdge.getLabel()) {
                                sameEdgeLabels = false;
                                break;
                            }
                        }
                    }
                }
                if (!sameEdgeLabels) {
                    isNextComputed = false;
                    return hasNext();
                }
            }
            return !currMapping.isEmpty();
        }

        /**
         * @see Iterator#next()
         */
        @Override
        public Map<String, String> next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("Has no next mappings.");
            }
            isNextComputed = false;
            next.clear();
            for (int i = 0; i < oQVertices.size(); i++) {
                next.put(currMapping.get(i), oQVertices.get(i));
            }
            return next;
        }
    }
}
