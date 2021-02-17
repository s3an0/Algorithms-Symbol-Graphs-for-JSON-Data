import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    private Node first; // top of the stack
    private int N; // # of items

    // when operating on a stack, remember that first will be on the top, while last will be on the bottom. LIFO
    // when pushing, you are adding to the top, which changes first. When popping, you are removing the top and
    // changing first as a result to whatever was below first

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public int size()
    {
        return N;
    }

    public void push(Item item) // add an item to the top of the stack
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public Item pop() // remove an item from the top of the stack
    {
        Item item = first.item;
        first = first.next;
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
