package ca.waterloo.dsg.graphflow.plan.operator.hashjoin;

import ca.waterloo.dsg.graphflow.plan.QueryPlan;
import ca.waterloo.dsg.graphflow.plan.operator.Operator;
import ca.waterloo.dsg.graphflow.query.QueryGraph;
import ca.waterloo.dsg.graphflow.util.collection.SetUtils;
import lombok.var;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashJoin {

    public static QueryPlan make(QueryGraph outSubgraph, QueryPlan buildPlan, QueryPlan probePlan) {
        return new QueryPlan(make(outSubgraph, buildPlan.shallowCopy().getSubplans(),
            probePlan.shallowCopy().getSubplans()));
    }

    public static List<Operator> make(QueryGraph outSubgraph, List<Operator> buildSubplans,
        List<Operator> probeSubplans) {
        var preBuild = buildSubplans.get(buildSubplans.size() - 1);
        var preProbe = probeSubplans.get(probeSubplans.size() - 1);
        var joinQVertices = SetUtils.intersect(preBuild.getOutQVertices(),
            preProbe.getOutQVertices());

        var buildQVertexToIdxMap = preBuild.getOutQVertexToIdxMap();
        var queryVertexToHash = joinQVertices.get(0);
        var buildHashIdx = buildQVertexToIdxMap.get(queryVertexToHash);
        var build = new Build(preBuild.getOutSubgraph(), queryVertexToHash, buildHashIdx);
        build.setPrev(preBuild);
        preBuild.setNext(build);
        buildSubplans.set(buildSubplans.size() - 1, build);

        var mapping = preBuild.getOutSubgraph().getIsomorphicMappingIfAny(
            preProbe.getOutSubgraph());
        Map<String, Integer> probeQVertexToIdxMap;
        if (null != mapping) {
            probeQVertexToIdxMap = new HashMap<>();
            for (var queryVertex : buildQVertexToIdxMap.keySet()) {
                var idx = buildQVertexToIdxMap.get(queryVertex);
                if (idx < buildHashIdx) {
                    probeQVertexToIdxMap.put(mapping.get(queryVertex), idx);
                } else if (idx > buildHashIdx) {
                    probeQVertexToIdxMap.put(mapping.get(queryVertex), idx - 1);
                }
            }
            probeQVertexToIdxMap.put(mapping.get(joinQVertices.get(0)),
                buildQVertexToIdxMap.size() - 1);
        } else {
            probeQVertexToIdxMap = preProbe.getOutQVertexToIdxMap();
        }
        var probeHashIdx = probeQVertexToIdxMap.get(queryVertexToHash);
        var outQVertexToIdxMap = computeOutQVertexToIdxMap(joinQVertices, buildQVertexToIdxMap,
            probeQVertexToIdxMap);
        var hashedTupleLen = buildQVertexToIdxMap.size() - 1;
        var probeIndices = new int[joinQVertices.size() - 1];
        var buildIndices = new int[joinQVertices.size() - 1];
        for (var i = 1; i < joinQVertices.size(); i++) {
            probeIndices[i - 1] = probeQVertexToIdxMap.get(joinQVertices.get(i));
            var otherBuildIdx = buildQVertexToIdxMap.get(joinQVertices.get(i));
            if (buildHashIdx < otherBuildIdx) {
                otherBuildIdx -= 1;
            }
            buildIndices[i - 1] = otherBuildIdx;
        }

        Probe probe;
        var inSubgraph = preProbe.getOutSubgraph();
        if (null != mapping) {
            if (probeIndices.length == 0) {
                probe = new ProbeCartesian(outSubgraph, inSubgraph, joinQVertices, probeHashIdx,
                    hashedTupleLen, preProbe.getOutTupleLen(), outQVertexToIdxMap);
            } else {
                probe = new ProbeMultiVerticesCartesian(outSubgraph, inSubgraph, joinQVertices,
                    probeHashIdx, probeIndices, buildIndices, hashedTupleLen, preProbe.
                    getOutTupleLen(), outQVertexToIdxMap);
            }
        } else {
            if (probeIndices.length == 0) {
                probe = new Probe(outSubgraph, inSubgraph, joinQVertices, probeHashIdx,
                    hashedTupleLen, preProbe.getOutTupleLen(), outQVertexToIdxMap);
            } else {
                probe = new ProbeMultiVertices(outSubgraph, inSubgraph, joinQVertices, probeHashIdx,
                    probeIndices, buildIndices, hashedTupleLen, preProbe.getOutTupleLen(),
                    outQVertexToIdxMap);
            }
            probe.setPrev(preProbe);
            preProbe.setNext(probe);
            probeSubplans.set(probeSubplans.size() - 1, probe);
        }
        build.setProbingSubgraph(probe.getInSubgraph());

        var subplans = new ArrayList<Operator>(buildSubplans);
        if (null != mapping) {
            subplans.add(probe);
        } else {
            subplans.addAll(probeSubplans);
        }
        return subplans;
    }

    private static Map<String, Integer> computeOutQVertexToIdxMap(List<String> joinQVertices,
        Map<String, Integer> buildQVertexToIdxMap, Map<String, Integer> probeQVertexToIdxMap) {
        var outQVerticesToIdxMap = new HashMap<String, Integer>(probeQVertexToIdxMap);
        var buildQVertices = new String[buildQVertexToIdxMap.size()];
        for (var buildQVertex : buildQVertexToIdxMap.keySet()) {
            buildQVertices[buildQVertexToIdxMap.get(buildQVertex)] = buildQVertex;
        }
        for (var buildQVertex : buildQVertices) {
            if (joinQVertices.contains(buildQVertex)) {
                continue;
            }
            outQVerticesToIdxMap.put(buildQVertex, outQVerticesToIdxMap.size());
        }
        return outQVerticesToIdxMap;
    }
}
