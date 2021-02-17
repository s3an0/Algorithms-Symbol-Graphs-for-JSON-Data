import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class JSONScan {

    String path;

    public JSONScan(String path) {
        this.path = path;
    }

    public LinkedList<Node> createNodes() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object jObj = jsonParser.parse(new FileReader(path));

        JSONObject jsonObj = (JSONObject) jObj;
        JSONArray jArray = (JSONArray) jsonObj.get("nodes");

        LinkedList<Node> nodes = new LinkedList<>();

        for (Object current : jArray) {
            JSONObject currNew = (JSONObject) current;

            Double followers_count = ((Number) currNew.get("followers_count")).doubleValue();
            String screen_name = (String) currNew.get("screen_name");
            Long id = (Long) currNew.get("id");
            String name = (String) currNew.get("name");
            String continent = (String) currNew.get("continent");
            String shortName = (String) currNew.get("shortName");
            String type = (String) currNew.get("type");

            // Retrieve all edges
            LinkedList<String> edges = new LinkedList<>();
            JSONArray edgesArray = (JSONArray) currNew.get("edges");
            for(Object edge : edgesArray) {
                edges.add((String) edge);
            }

            // Retrieve all neighbors
            LinkedList<Long> neighbors = new LinkedList<>();
            JSONArray neighborsArray = (JSONArray) currNew.get("neighbors");
            for(Object edge : neighborsArray) {
                neighbors.add((Long) edge);
            }

            Node currNode = new Node(followers_count, screen_name, id, name, continent, shortName, type, edges, neighbors);
            nodes.add(currNode);
        }

        return nodes;
    }

    public LinkedList<Link> createLinks() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        Object jObj = jsonParser.parse(new FileReader(path));

        JSONObject jsonObj = (JSONObject) jObj;
        JSONArray jArray = (JSONArray) jsonObj.get("links");

        LinkedList<Link> links = new LinkedList<>();

        for (Object current : jArray) {
            JSONObject currNew = (JSONObject) current;

            Long source = (Long) currNew.get("source");
            Long target = (Long) currNew.get("target");

            Link currLink = new Link(source, target);
            links.add(currLink);
        }

        return links;
    }

}
