package ca.waterloo.dsg.graphflow.runner;

import java.io.*;
import java.util.*;

public class ReducedGraph {
    /**
     * reducing the graph only surplus the useful vertices and their edges
     */
    public static void main(String[] args) throws IOException {
        int RGraph_count = 0;
        File dir_path = new File("test/process_1_produce/yago4_twice_reduced");
        File[] File_list = dir_path.listFiles();
        for (int i=0;i<File_list.length;i++){
            if(File_list[i].isFile()) RGraph_count++;
        }
        for(int i=0;i<RGraph_count;i++){
            File file1 = new File("test/process_1_produce/yago4ReducingGraph/reducingGraph"+i+"/");
            file1.mkdir();
        }
        for(int i=0;i<RGraph_count;i++) {
            String graphPattern = "test/process_1_produce/yago4_twice_reduced/purePattern"+i+".txt";
            String RVertices = "test/process_1_produce/yago4ReducingGraph/reducingGraph"+i+"/RVertices.csv";
            String REdges = "test/process_1_produce/yago4ReducingGraph/reducingGraph"+i+"/REdges.csv";
            String RidMap = "test/process_1_produce/yago4ReducingGraph/reducingGraph"+i+"/RidMap.csv";
            System.out.println("execute graphPattern"+i);
            reducing(graphPattern, RVertices, REdges, RidMap);
        }
    }

    public static void reducing(String graphPattern,String RVertices,String REdges,String RidMap) throws IOException {
        //File designed
        String graphPatternFile = graphPattern;
        String RVerticeFile = RVertices;
        String REdgeFile = REdges;
        String RidMapFile = RidMap;

        //store the labels of useful vertices
        TreeSet<String> treeSet_label = new TreeSet<>();
        //store the id of useful vertices
        TreeSet<String> treeSet_id = new TreeSet<>();
        //store the id of reduced vertices
        TreeSet<String> treeSet_Rid = new TreeSet<>();
        //store the labels of useful edges
        TreeSet<String> treeSet_edges = new TreeSet<>();
        //store the map of verticeId and vertice message
        HashMap<String,String[]> verticesHap = new LinkedHashMap<>();
        //store the reduced edges
        List<String[]> edgeList = new ArrayList<>();

        //read the query graph
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(graphPatternFile)));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            String[] part = line.split("\\)-\\[");
            String[] part1 = part[0].split(":");
            treeSet_label.add(part1[1]);
            String[] part2 = part[1].split("]->\\(");
            treeSet_edges.add(part2[0]);
            System.out.println("EdgeType "+part2[0]);
            String[] part22 = part2[1].split(":");
            String[] part221 = part22[1].split("\\)");
            treeSet_label.add((part221[0]));
        }
        //read the vertices of bigGraph
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream("test/process_1_produce/GraphVertices-yago4.csv")));
        while ((line=bufferedReader1.readLine())!=null){
            String[] temp = line.split(",");
            if(treeSet_label.contains(temp[1])) {
                verticesHap.put(temp[0],temp);
                treeSet_id.add(temp[0]);
            }
        }
        //read the edges of bigGraph
        //get the reduced edges
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("test/process_1_produce/GraphEdges-yago4.csv")));
        while ((line=bufferedReader2.readLine())!=null){
            String[] temp = line.split(",");
            if(treeSet_id.contains(temp[0])&&treeSet_id.contains(temp[1])&&treeSet_edges.contains(temp[2])) {
                edgeList.add(temp);
            }
        }
        //get the reduced vertices
        for(String[] strs: edgeList){
            treeSet_Rid.add(strs[0]);
            treeSet_Rid.add(strs[1]);
        }

        //store the map of preId and newId
        int count=0;
        HashMap<String,String> idMap = new LinkedHashMap<>();
        for(String preId: treeSet_Rid){
            idMap.put(preId,count+"");
            count++;
        }
        String sourceId;
        String targetId;
        for(int i=0;i<edgeList.size();i++){
            sourceId = edgeList.get(i)[0];
            targetId = edgeList.get(i)[1];
            edgeList.get(i)[0] = idMap.get(sourceId);
            edgeList.get(i)[1] = idMap.get(targetId);
        }

        //output the reducedVertices
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(RVerticeFile)));
        for(String preId: treeSet_Rid){
            bufferedWriter.write(idMap.get(preId)+","+verticesHap.get(preId)[1]+"\n");
        }
        bufferedWriter.close();
        //output the reducedEdges
        BufferedWriter bufferedWriter1 = new BufferedWriter((new OutputStreamWriter(new FileOutputStream(REdgeFile))));
        for(String[] strs: edgeList){
            bufferedWriter1.write(strs[0]+","+strs[1]+","+strs[2]+"\n");
        }
        bufferedWriter1.close();
        //output the map of newId and preId
        BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(RidMapFile)));
        for(String str: idMap.keySet()){
            bufferedWriter2.write(idMap.get(str)+","+str+"\n");
        }
        bufferedWriter2.close();
    }
}
