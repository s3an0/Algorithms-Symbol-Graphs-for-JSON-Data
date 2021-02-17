import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Q1 {

    private static LinkedList<Node> allNodes;
    private static LinkedList<Link> allLinks;
    private static SymbolGraph graph;

    public static void main(String[] args) throws IOException, ParseException {
        JSONScan jScan = new JSONScan("src\\network_large_directed_multiEdge.json");
        allNodes = jScan.createNodes();
        allLinks = jScan.createLinks();

        graph = new SymbolGraph(allNodes, allLinks);


        // Task Set 2: 1)
        System.out.println("Task Set 2: 1)");
        neighborCountryNA("giCentre");
        System.out.println("");

        // Task Set 2: 2)
        System.out.println("Task Set 2: 2)");
        findInstitute("Lane", "Rob");
        System.out.println("");

        //Task Set 3
        System.out.println("Time Difference for Finding Paths for a Random Sample of 100 IDs with BFS and DFS: ");
        timeDiffFull();

    }



    public static void neighborCountryNA(String screenName) {
        int index = 0;
        int totalNeighbors;

        for(int i = 0; i < allNodes.size(); i++) {
            if(screenName.equals(allNodes.get(i).getScreen_name())) {
                index = i;
                break;
            }
        }

        totalNeighbors = allNodes.get(index).getNeighbors().size();

        System.out.println(allNodes.get(index).getScreen_name() + "'s Neighbors in North America: ");

        for(int i = 0; i < totalNeighbors; i++) {
            Long currNeighborId = allNodes.get(index).getNeighbors().get(i);
            Node currNeighborNode = graph.getNode(currNeighborId);

            String neighborName = currNeighborNode.getScreen_name();
            Long neighborId = currNeighborNode.getId();
            String neighborContinent = currNeighborNode.getContinent();

            if(neighborContinent.equals("North America")) {
                System.out.println(neighborName + " (" +neighborId + ")");
            }
        }

    }

    public static void findInstitute(String shortNameStart, String shortNameEnd) {
        int index = 0;

        // find index of source short name
        for (int i = 0; i < allNodes.size(); i++) {
            if (shortNameStart.equals(allNodes.get(i).getShortName())) {
                index = i;

                break;
            }
        }
        Long startingId = allNodes.get(index).getId();

        // find index of end short name
        for (int i = 0; i < allNodes.size(); i++) {
            if (shortNameEnd.equals(allNodes.get(i).getShortName())) {
                index = i;
                break;
            }
        }
        Long endingId = allNodes.get(index).getId();

        System.out.println("Finding Shortest Path for " + graph.getNode(startingId).getShortName() + " with an Institution:");
        DegreesOfSeparation degSep = new DegreesOfSeparation();
        boolean result = degSep.DegreesOfSeparationBFSInst(graph, graph.G(), startingId, endingId);
        while(!result) {
            degSep = new DegreesOfSeparation();
            result = degSep.DegreesOfSeparationBFSInst(graph, graph.G(), startingId, endingId);
        }
    }

    public static void timeDiffFull() {
        Long[] startingIds = new Long[100];
        Long[] endingIds = new Long[100];

        Random randomNum = new Random();

        for (int i = 0; i < startingIds.length; i++) {
            startingIds[i] = allNodes.get(randomNum.nextInt(allNodes.size()-1)).getId();
        }
        for (int i = 0; i < endingIds.length; i++) {
            endingIds[i] = allNodes.get(randomNum.nextInt(allNodes.size()-1)).getId();
        }

        Stopwatch bfsWatch = new Stopwatch();
        for (int i = 0; i < startingIds.length; i++) {
            timeBFS(startingIds[i], endingIds[i]);
        }
        Double bfsT = bfsWatch.elapsedTime();

        System.out.println();

        Stopwatch dfsWatch = new Stopwatch();
        for (int i = 0; i < startingIds.length; i++) {
            timeDFS(startingIds[i], endingIds[i]);
        }
        Double dfsT = dfsWatch.elapsedTime();
        System.out.println("The time it took for the BFS to find the paths was " + bfsT + " seconds");
        System.out.println("The time it took for the DFS to find the paths was " + dfsT + " seconds");
    }

    public static void timeBFS(Long startingId, Long endingId) {

        System.out.println("Finding Path from " + graph.getNode(startingId).getShortName() + " to " + graph.getNode(endingId).getShortName() + ":");

        DegreesOfSeparation degSep1 = new DegreesOfSeparation();
        degSep1.DegreesOfSeparationBFS(graph, graph.G(), startingId, endingId);
    }

    public static void timeDFS(Long startingId, Long endingId) {

        System.out.println("Finding Path from " + graph.getNode(startingId).getShortName() + " to " + graph.getNode(endingId).getShortName() + ":");

        DegreesOfSeparation degSep1 = new DegreesOfSeparation();
        degSep1.DegreesOfSeparationDFS(graph, graph.G(), startingId, endingId);
    }
}
