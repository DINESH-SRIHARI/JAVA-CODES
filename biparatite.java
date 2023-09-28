package dsa.daa;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
//import java.util.*;
public class biparatite {
    static class Edge{
        int source;
        int destination;
        Edge(int source,int destination){
            this.source=source;
            this.destination=destination;
        }
    }
    public static void create(ArrayList<Edge> graph[]){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,4));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));

        graph[3].add(new Edge(3,2));
        //graph[3].add(new Edge(3,4));

        graph[4].add(new Edge(4,2));
        //graph[4].add(new Edge(4,3));
    }
    public static boolean bipartite(ArrayList<Edge> graph[]){
        int colour[]=new int[graph.length];
        for(int i=0;i<colour.length;i++){
            colour[i]=-1;
        }
        //colour[0]=0;
        Queue<Integer> qe=new LinkedList<>();
        //boolean visited[]=new boolean visited[graph.length];
        for(int i=0;i<graph.length;i++){
            if(colour[i]==-1){
                colour[i]=0;
                qe.add(i);
                while(!qe.isEmpty()){
                    int cur=qe.remove();
                    for(int j=0;j<graph[cur].size();j++){
                        Edge n=graph[cur].get(j);
                        if(colour[n.destination]==colour[cur]){
                            return false;
                        }
                        else if(colour[n.destination]==-1){
                            int val=cur==0?1:0;
                            colour[n.destination]=val;
                            qe.add(n.destination);
                        }
                    }
                }
            }
        }
        return true;

    }
    public static void main(String args[]){
        ArrayList<Edge> graph[]=new ArrayList[5];
        create(graph);
        System.out.println(bipartite(graph));
    }
}
