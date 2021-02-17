
public class Graph {

    private int V; // number of vertices
    private int E; // number of edges
    private Bag<Integer>[] adj; // adjacency lists

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
        for (int v = 0; v < V; v++) // Initialize all lists
            adj[v] = new Bag<Integer>(); // to empty.
    }

    // returns number of vertices
    public int V() {
        return V;
    }

    // returns number of edges
    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        if(!adj[v].contains(w)) {
            adj[v].add(w); // Add w to v’s list.
        }
        if(!adj[w].contains(v)) {
            adj[w].add(v); // Add v to w’s list.
        }
        E++;
    }

    public int getAdjSize(int v) {
        return adj[v].size();
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

}

