
class Vertex{
    public char label; // Vertex label
    public boolean wasVisited; //Vertex visit status
    
    //Constructor
    public Vertex(char lab){
        label = lab;
        wasVisited = false; //initially not visited
    }
}

class Graph{
    private final int MAX_VERTS = 20; //Max vertex number
    private final int INFINITY = 1000000;  //infinity value for vertex which is not connected
    private Vertex vertexList[];    //vertex list
    private int adjMat[][]; //adjacency matrix
    private int nVerts;   // vertex number

    //Constructor
    public Graph(){
        vertexList = new Vertex[MAX_VERTS];

        //adj matrix size
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        //initial weight values are infinity
        for(int j=0; j<MAX_VERTS; j++)
            for(int k=0; k<MAX_VERTS; k++)
                adjMat[j][k] = INFINITY;
    }

    //addVertex to graph method
    public void addVertex(char lab){
        vertexList[nVerts++] = new Vertex(lab);
    }

    //addEdge to graph method
    public void addEdge(int start, int end, int weight){
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    //display vertex method
    public void displayVertex(int v){
        System.out.println(vertexList[v].label);
    }

    //MST algorithm
    public void mst(){
        //first vertex has been visited
        vertexList[0].wasVisited = true;
        int nVisited = 1;

        //loop until all vertices are visited
        while (nVisited< nVerts){
            int minWeight = INFINITY; 
            int sourceV = -1; 
            int destV = -1; 
            
            //tour all visited vertex
            for(int i=0; i<nVerts; i++){
                if(vertexList[i].wasVisited){
                    
                    //tour all unvisited vertex to find minimum edge
                    for(int j=0; j<nVerts; j++){
                        if(!vertexList[j].wasVisited && adjMat[i][j]< minWeight){
                            
                            //if found edge weight is less than minWeight, update minWeight and source-dest vertex
                            minWeight = adjMat[i][j];
                            sourceV = i;
                            destV = j;
                        }
                    }
                }
            }

            //if edge is compatible, display it and mark dest vertex as visited
            if(sourceV != -1){
                System.out.print(vertexList[sourceV].label + "-" + vertexList[destV].label + " (" + minWeight + ") ");

                //dest vertex is now visited
                vertexList[destV].wasVisited = true;
                nVisited++;
            }
            else{
                break;
            }
        }

        //lastly reset the visited flags
        for(int j=0; j<nVerts; j++)
            vertexList[j].wasVisited = false;
        System.out.println();
    }
}

class MSTApp{
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');

        theGraph.addEdge(0, 1,6);
        theGraph.addEdge(0, 3, 4);
        theGraph.addEdge(1, 2, 10);
        theGraph.addEdge(1, 3, 7);
        theGraph.addEdge(1, 4, 7);
        theGraph.addEdge(2, 3, 8);
        theGraph.addEdge(2, 4, 5);
        theGraph.addEdge(3, 4, 12);

        System.out.println("MST: ");
        theGraph.mst();
        System.out.print("");
    }
}