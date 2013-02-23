import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int size;

    /**
     * Construct an empty randomized queue
     */
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        size = 0;
    }

    /**
     * Is the queue empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items on the queue
     */
    public int size() {
        return size;
    }

    /**
     * Add the item
     */
    public void enqueue(Item item) {
        if (size == a.length)
            resize(2 * size);
        a[size++] = item;
    }

    /**
     * Delete and return a random item
     */
    public Item dequeue() {
        int i = StdRandom.uniform(size);
        Item item = a[i];
        a[i] = a[size - 1];
        a[size - 1] = null;
        if (size < a.length / 4) {
            resize(a.length / 2);
        }
        size--;
        return item;
    }

    /**
     * Return (but do not delete) a random item
     */
    public Item sample() {
        return a[StdRandom.uniform(size)];
    }

    /**
     * Return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int order[] = generateRandomOrder();
            int current = 0;

            @Override
            public boolean hasNext() {
                return current != size;
            }

            @Override
            public Item next() {
                return a[order[current++]];
            }

            @Override
            public void remove() {
            }
        };
    }

    protected int[] generateRandomOrder() {
        int[] order = new int[size];
        for (int i = 0; i < size; i++) {
            order[i] = i;
        }
        StdRandom.shuffle(order);
        return order;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Item[] b = (Item[]) new Object[capacity];
        System.arraycopy(a, 0, b, 0, size);
        a = b;
    }
}