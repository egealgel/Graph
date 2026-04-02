import java.util.Arrays;

public class DijkstraWeighted {
    public static void dijkstra(int[][] graph, int startNode){
        int n = graph.length; //number of vertices
        int[] dist = new int[n];    //min distance from startNode to each vertex
        boolean[] visited = new boolean[n]; //vertex visit status

        Arrays.fill(dist, Integer.MAX_VALUE);   //initially all distances are infinity
        dist[startNode] = 0; //distance to itself is 0

        
        for(int i=0; i<n-1; i++){
            //pick the minimum distance vertex from unvisited vertices
            int u = findMinDistance(dist, visited);
            //mark the picked vertex as visited
            visited[u] = true;
            //control all adjacent vertices of the picked vertex and update their distance values
            for (int v = 0; v<n; v++){
                //v is unvisited, there is an edge from u to v, and u is not infinity
                if(!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE){
                    //if u to v dist is less than current dist to v, update it
                    if(dist[u] + graph[u][v] < dist[v]){
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }
        printSolution(dist); 
    }
    //find the vertex with minimum distance value from unvisited vertices
    private static int findMinDistance(int[] dist, boolean[] visited){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int v=0; v<dist.length; v++){
            //find the minimum distance vertex from unvisited vertices
            if(!visited[v] && dist[v] <= min){
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    //print the distance array
    private static void printSolution(int[] dist){
        System.out.println("Vertex \t Distance to Start Point");
        for(int i=0; i<dist.length; i++){
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0,10,5,0},
            {0,0,2,1},
            {0,3,0,9},
            {0,0,0,0},
        };
        dijkstra(graph, 0);
    }
}
