import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of double-ended queue.
 * 
 * @author Timur Nasibullin
 * 
 * @param <Item>
 *            the type of queue element.
 */
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
        checkBeforeAdd(item);
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
        checkBeforeAdd(item);
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
        checkBeforeRemove();
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
        checkBeforeRemove();
        Node node = tail.previos;
        tail.previos = node.previos;
        node.previos.next = tail;
        size--;
        return node.data;
    }

    /**
     * Is new element not null?
     */
    private void checkBeforeAdd(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Is the queue not empty?
     */
    private void checkBeforeRemove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

    }

    /**
     * Return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = head.next;

            public boolean hasNext() {
                return current != tail;
            }

            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node node = current;
                current = current.next;
                return node.data;
            }

            public void remove() {
                throw new UnsupportedOperationException();
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