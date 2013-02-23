import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    /**
     * Construct an empty deque
     */
    public Deque() {
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.previos = head;
        size = 0;
    }

    /**
     * Is the deque empty?
     */
    public boolean isEmpty() {
        return head.next == tail;
    }

    /**
     * Return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Insert the item at the front
     */
    public void addFirst(Item item) {
        Node node = new Node(item);
        head.next.previos = node;
        node.next = head.next;
        node.previos = head;
        head.next = node;
        size++;
    }

    /**
     * Insert the item at the end
     */
    public void addLast(Item item) {
        Node node = new Node(item);
        tail.previos.next = node;
        node.previos = tail.previos;
        node.next = tail;
        tail.previos = node;
        size++;
    }

    /**
     * Delete and return the item at the front
     */
    public Item removeFirst() {
        Node node = head.next;
        head.next = node.next;
        node.next.previos = head;
        size--;
        return node.data;
    }

    /**
     * Delete and return the item at the end
     */
    public Item removeLast() {
        Node node = tail.previos;
        tail.previos = node.previos;
        node.previos.next = tail;
        size--;
        return node.data;
    }

    /**
     * Return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = head.next;

            @Override
            public boolean hasNext() {
                return current != tail;
            }

            @Override
            public Item next() {
                Node node = current;
                current = current.next;
                return node.data;
            }

            @Override
            public void remove() {
                current.previos.next = current.next;
                current.next.previos = current.previos;
                current = current.next;
                size--;
            }
        };
    }

    private class Node {
        private Item data;
        private Node previos;
        private Node next;

        public Node(Item data) {
            this.data = data;
        }
    }
}