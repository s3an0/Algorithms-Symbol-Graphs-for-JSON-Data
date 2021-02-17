import java.util.Collections;
import java.util.LinkedList;

public class DepthFirstPaths
{
    private boolean[] marked; // Has dfs() been called for this vertex?
    private int[] edgeTo; // last vertex on known path to this vertex
    private final int s; // source
    public DepthFirstPaths(Graph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
    }

    public void dfs(Graph G, int v)
    {

        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
            {
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

    public void dfsRandom(Graph G, int v)
    {
        marked[v] = true;

        LinkedList<Integer> listAdj = new LinkedList<>();
        for (int w : G.adj(v)) {
            listAdj.add(w);
        }
        Collections.shuffle(listAdj);

        for (int w : listAdj)
            if (!marked[w])
            {
                edgeTo[w] = v;
                dfsRandom(G, w);
            }
    }

    public boolean hasPathTo(int v)
    {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}