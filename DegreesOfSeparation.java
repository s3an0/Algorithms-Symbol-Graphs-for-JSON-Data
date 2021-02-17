public class DegreesOfSeparation {

    private static SymbolGraph sg;
    private static Graph G;
    private Long source;
    private Long end;
    public Double time;

    public void DegreesOfSeparationBFS(SymbolGraph sg, Graph G, Long source, Long end) {
        this.sg = sg;
        this.G = G;
        this.source = source;
        this.end = end;

        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        int s = sg.get(source);

        Stopwatch watch = new Stopwatch();

        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
        bfs.bfsRandom(G, s);

        if (sg.contains(end)) {
            int t = sg.get(end);
            if (bfs.hasPathTo(t)) {
                for (int v : bfs.pathTo(t)) {
                    Long currId = sg.id(v);
                    System.out.println(sg.getNode(currId).getShortName() + " | " + currId + " | " + sg.getNode(currId).getType());
                }
            }
            else {
                System.out.println("Not connected");
            }
        }
        else {
            System.out.println("Not in database.");
        }

        time = watch.elapsedTime();

    }

    public boolean DegreesOfSeparationBFSInst(SymbolGraph sg, Graph G, Long source, Long end) {
        this.sg = sg;
        this.G = G;
        this.source = source;
        this.end = end;

        boolean hasInst = false;

        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return false;
        }

        int s = sg.get(source);

        Stopwatch watch = new Stopwatch();

        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
        bfs.bfsRandom(G, s);

        if (sg.contains(end)) {
            int t = sg.get(end);
            if (bfs.hasPathTo(t)) {
                for (int v : bfs.pathTo(t)) {
                    Long currId = sg.id(v);
                    if(sg.getNode(currId).getType().equals("institution")) {
                        hasInst = true;
                    }
                }
            }
            else {
                System.out.println("Not connected");
            }
        }
        else {
            System.out.println("Not in database.");
        }

        time = watch.elapsedTime();

        if(hasInst) {
            for (int v : bfs.pathTo(sg.get(end))) {
                Long currId = sg.id(v);
                System.out.println(sg.getNode(currId).getShortName() + " | " + currId + " | " + sg.getNode(currId).getType());
            }
        }

        return hasInst;
    }

    public void DegreesOfSeparationDFS(SymbolGraph sg, Graph G, Long source, Long end) {
        this.sg = sg;
        this.G = G;
        this.source = source;
        this.end = end;

        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        int s = sg.get(source);

        Stopwatch watch = new Stopwatch();

        DepthFirstPaths dfs = new DepthFirstPaths(G, s);
        dfs.dfsRandom(G, s);

        if (sg.contains(end)) {
            int t = sg.get(end);
            if (dfs.hasPathTo(t)) {
                for (int v : dfs.pathTo(t)) {
                    Long currId = sg.id(v);
                    System.out.println(sg.getNode(currId).getShortName() + " | " + currId + " | " + sg.getNode(currId).getType());
                }
            }
            else {
                System.out.println("Not connected");
            }
        }
        else {
            System.out.println("Not in database.");
        }

        time = watch.elapsedTime();

    }
}