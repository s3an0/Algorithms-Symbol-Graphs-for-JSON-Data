import java.util.LinkedList;

public class SymbolGraph {

    private LinearProbingHashST<Long, Integer> st; // String -> index
    private LinearProbingHashST<Long, Node> nodeSt;
    private Long[] keys; // index -> String
    private Graph G; // the graph

    public SymbolGraph(LinkedList<Node> nodes, LinkedList<Link> links) {
        st = new LinearProbingHashST<Long, Integer>();
        nodeSt = new LinearProbingHashST<Long, Node>();

        LinkedList<Long> ids = new LinkedList<>();

        for (int i = 0; i < nodes.size(); i++) {
            if (!st.contains(nodes.get(i).getId())) {
                st.put(nodes.get(i).getId(), st.size());
                nodeSt.put(nodes.get(i).getId(), nodes.get(i));
                ids.add(nodes.get(i).getId());
            }
        }

        keys = new Long[ids.size()];
        int index = 0;
        for (int i = 0; i < ids.size(); i++){
            Long currl = ids.get(i);
            keys[index] = currl;
            index = index + 1;
        }


        G = new Graph(st.size());

        for (int i = 0; i < keys.length; i++) {

            Long currKey = keys[i];

            for (int j = 0; j < links.size(); j++) {
                Long linkSource = links.get(j).getSource();
                if (linkSource.equals(currKey)) {
                    int currSource = st.get(links.get(j).getSource());
                    int currTarget = st.get(links.get(j).getTarget());

                    G.addEdge(currSource, currTarget);
                }
            }
        }
    }

    public boolean contains(Long s) {
        return st.contains(s);
    }

    public int get(Long s) {
        return st.get(s);
    }

    public Node getNode(Long s) {
        return nodeSt.get(s);
    }

    public Long id(int v) {
        return keys[v];
    }


    public Graph G() {
        return G;
    }

    public void bfs(int source) {
        BreadthFirstPaths bfs = new BreadthFirstPaths(G(), source);
    }

}
