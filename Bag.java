import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
    private Node first; // first node in list
    private int n = 0;

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public boolean contains(Item item){
        Node current = first;
        while(current != null) {
            if (item == current.item) {
                return true;
            } else {
                current = current.next;
            }
        }
        return false;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public int size() {
        return n;
    }


}
