import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class BreadthFirstPaths {
    private boolean[] marked; // Is a shortest path to this vertex known?
    private int[] edgeTo; // last vertex on known path to this vertex
    private final int s; // source

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
    }

    public void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true; // Mark the source
        queue.enqueue(s); // and put it on the queue.
        while (!queue.isEmpty()) {
            int v = queue.dequeue(); // Remove next vertex from the queue.
            for (int w : G.adj(v)) {
                if (!marked[w]) // For every unmarked adjacent vertex,
                {
                    edgeTo[w] = v; // save last edge on a shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.enqueue(w); // and add it to the queue.
                }
            }
        }
    }

    public void bfsRandom(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true; // Mark the source
        queue.enqueue(s); // and put it on the queue.
        while (!queue.isEmpty()) {
            int v = queue.dequeue(); // Remove next vertex from the queue.

            // create list of all the neighbors to shuffle
            LinkedList<Integer> listAdj = new LinkedList<>();
            for (int w : G.adj(v)) {
                listAdj.add(w);
            }
            Collections.shuffle(listAdj);

            for (int w : listAdj) {
                if (!marked[w]) // For every unmarked adjacent vertex,
                {
                    edgeTo[w] = v; // save last edge on a shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.enqueue(w); // and add it to the queue.
                }
            }
        }
    }




    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public int pathToSize(int v) {
        int val = 0;
        if (!hasPathTo(v)) {
            return 0;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
            val++;
        }
        path.push(s);
        val++;
        return val;
    }

}