package ca.waterloo.dsg.graphflow.runner;

import ca.waterloo.dsg.graphflow.plan.operator.Operator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Show {
    public static String readFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        Reader reader = null;
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            sb.append(temp + "\n");
        }
        br.close();
        String js = sb.toString();
        js = js.replaceAll("\n","");
        js = js.replaceAll("\r","");
        return js;
    }

    public static String[] toOptimizer(String InputGraph, String QueryGraph,String yago4_pre_Table) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("-q");
        list.add(readFromFile(QueryGraph));
        list.add("-i");
        list.add(InputGraph);
        list.add("-e");
        //TODO test
        //yago4_pre_Table 输出文件的文件id
        list.add(yago4_pre_Table);
        String[] strings = list.toArray(new String[list.size()]);
        //-q D:\test\test8\Output\1.txt -i D:\test\test8\bin2 -t 8 -e
        return strings;
    }

    public static String[] toDataset(String GraphVertices,String GraphEdges,String InputGraph) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("-v");
        list.add(GraphVertices);
        list.add("-e");
        list.add(GraphEdges);
        list.add("-o");
        list.add(InputGraph);
        String[] strings = list.toArray(new String[list.size()]);
        //-v D:\test\test8\test8Vertices.csv -e D:\test\test8\test8Edges.csv -o D:\test\test8\bin2
        return strings;
    }

    public static String[] toCatalog(String InputGraph) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("-i");
        list.add(InputGraph);
        String[] strings = list.toArray(new String[list.size()]);
        //-i D:\test\test8\bin2
        return strings;
    }

    public static void main(String[] args) throws IOException, InterruptedException, Operator.LimitExceededException {
        long startTime=System.currentTimeMillis();   //获取开始时间
        /**
         * 对每个图模式都进行图模式匹配
         */

        //先进行图模式的简化图创建
        String[] a = {""};
        // ReducedGraph.main(a);
        //图模式在简化图上进行同态匹配
        int RGraph_count = 0;
        File dir_path = new File("test/process_1_produce/yago4_twice_reduced");
        File[] File_list = dir_path.listFiles();
        for (int i=0;i<File_list.length;i++){
            if(File_list[i].isFile()) RGraph_count++;
        }
        //RGraph_count = 11;
        for(int i=0;i<RGraph_count;i++) {
            System.out.println("*******************");
            System.out.println("execute graphPattern "+i);
            System.out.println("*******************");
            String GraphVertices = "test/process_1_produce/yago4ReducingGraph/reducingGraph"+i+"/RVertices.csv";
            String GraphEdges = "test/process_1_produce/yago4ReducingGraph/reducingGraph"+i+"/REdges.csv";
            String InputGraph = "test/process_2_produce/Handle";
            String QueryGraph = "test/process_1_produce/yago4_twice_reduced/purePattern"+i+".txt";
            DatasetSerializer.main(toDataset(GraphVertices, GraphEdges, InputGraph));
            System.out.println("process1 finished!");
            CatalogSerializer.main(toCatalog(InputGraph));
            System.out.println("process2 finished!");
            OptimizerExecutor.main(toOptimizer(InputGraph, QueryGraph,i+""));
        }
        System.out.println("phase 2 build success!");

        long endTime=System.currentTimeMillis(); //获取结束时间
        Double costTime = (double)((endTime-startTime))/1000;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("result/yago4/runningTime.txt")));
        double preCostTime = Double.valueOf(bufferedReader.readLine());
        bufferedReader.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("result/yago4/runningTime.txt")));
        bufferedWriter.write((costTime+preCostTime)+"\n");
        bufferedWriter.close();
    }
}
