import java.util.LinkedList;

public class Node implements Comparable<Node> {

    private Double followers_count;
    private String screen_name;
    private Long id;
    private String name;
    private String continent;
    private String shortName;
    private String type;
    private LinkedList<String> edges;
    private LinkedList<Long> neighbors;

    //String[]
    //LinkedList<String>
    public Node(Double followers_count, String screen_name, Long id, String name, String continent, String shortName, String type, LinkedList<String> edges, LinkedList<Long> neighbors) {
        this.followers_count = followers_count;
        this.screen_name = screen_name;
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.shortName = shortName;
        this.type = type;
        this.edges = edges;
        this.neighbors = neighbors;
    }

    public int compareTo(Node o) {
        return 0;
    }

    public String toString() {
        return "followers_count: " + followers_count + " | screen_name: " + screen_name + " | id: " + id + " | name: " + name + " | continent: " + continent + " | shortName: " + shortName + " | type: " + type + " | edges: " + edges + " | neighbors: " + neighbors;
    }

    public Long getId() {
        return id;
    }

    public LinkedList<Long> getNeighbors() {
        return neighbors;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getContinent() {
        return continent;
    }

    public String getShortName() {
        return shortName;
    }

    public String getType() {
        return type;
    }
}
