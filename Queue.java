import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    private Node first; // link to least recently added node
    private Node last; // link to most recently added node
    private int N; // # of items on the queue

    // when operating on a queue, remember that first will be on the bottom, while last will be on the top. FIFO
    // when enqueueing, you are adding to the top, which changes last. When dequeueing, you are removing the bottom and 
    // changing first as a result to whatever was above first

    private class Node // nested class to define nodes
    {
        Item item;
        Node next;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) // add an item to the end of the list
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
        {
            first = last;
        }
        else {
            oldlast.next = last;
        }
        N++;
    }

    public Item dequeue() // remove an item from the beginning of the list
    {
        Item item = first.item;
        first = first.next;
        if (isEmpty())
        {
            last = null;
        }
        N--;
        return item;
    }

    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()
        {
            return current != null;
        }

        public Item next()
        {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}