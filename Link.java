import java.util.LinkedList;

public class Link implements Comparable<Link> {

    private Long source;
    private Long target;

    //String[]
    //LinkedList<String>
    public Link(Long source, Long target) {
        this.source = source;
        this.target = target;
    }

    public int compareTo(Link o) {
        return 0;
    }

    public String toString() {
        return "source: " + source + " | target: " + target;
    }

    public Long getSource() {
        return source;
    }

    public Long getTarget() {
        return target;
    }
}
