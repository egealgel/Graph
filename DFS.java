import java.util.*;

class Edge {
    int target; 
    int weight;

    Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

class WeightedGraph {
    private int V; // number of vertices
    private List<List<Edge>> adj; // adjacency list
    
    WeightedGraph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        //For every vertex, create a new list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }
    // Add edge to the graph  
    void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Edge(v, weight)); // Add edge from u to v
        adj.get(v).add(new Edge(u, weight)); // Add edge from v to u 
    }

    // Perform DFS traversal for the entire graph
    void fullTraversal() {
        //visited array to keep track of visited vertices
        boolean[] visited = new boolean[V];
        System.out.println("DFT:");
        //traverse all vertices
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                //if vertex not visited this vertex is new component
                System.out.println("--- New Component: " + i + " ---");
                DFSUtil(i, visited);
            }
        }
    }
    // DFS utility method
    private void DFSUtil(int v, boolean[] visited) {
        //current vertex is visited
        visited[v] = true;
        System.out.println("Visited Vertex: " + v);
        //current vertex's all adjacent vertices
        for (Edge e : adj.get(v)) {
            //if adjacent vertex not visited, visit it
            if (!visited[e.target]) {
                System.out.println("  Edge is following: (" + v + " -> " + e.target + ") Weight: " + e.weight);
                //go deeper
                DFSUtil(e.target, visited);
            }
        }
    }
}

public class DFS {
    public static void main(String[] args) {
        
        WeightedGraph graph = new WeightedGraph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 3);
        

        graph.fullTraversal();
    }
}