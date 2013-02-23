import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;


public class RandomizedQueueTest {
    private static final String FIRST = "first";
    private static final String SECOND = "second";
    private static final String THIRD = "third";
    private static final String FOURTH = "fourth";

    private static final Set<String> items = new HashSet<String>() {{
        add(FIRST);
        add(SECOND);
        add(THIRD);
        add(FOURTH);
    }};

    @Test
    public void testAddRemove() {
        RandomizedQueue<String> que = new RandomizedQueue<String>();

        assertTrue(que.isEmpty());
        assertEquals(0,  que.size());

        que.enqueue(FIRST);
        que.enqueue(SECOND);
        que.enqueue(THIRD);
        que.enqueue(FOURTH);

        assertFalse(que.isEmpty());
        assertEquals(4,  que.size());

        assertTrue(items.contains(que.sample()));
        
        assertTrue(items.contains(que.dequeue()));
        assertTrue(items.contains(que.dequeue()));
        assertTrue(items.contains(que.dequeue()));
        assertTrue(items.contains(que.dequeue()));
        
        assertTrue(que.isEmpty());
        assertEquals(0,  que.size());
    }

    @Test
    public void testIterator() {
        RandomizedQueue<String> que = new RandomizedQueue<String>();
        
        que.enqueue(FIRST);
        que.enqueue(SECOND);
        que.enqueue(THIRD);
        que.enqueue(FOURTH);

        Iterator<String> iter = que.iterator();
        
        assertTrue(iter.hasNext());
        assertTrue(items.contains(iter.next()));
        assertTrue(items.contains(iter.next()));
        assertTrue(items.contains(iter.next()));
        assertTrue(items.contains(iter.next()));
        assertFalse(iter.hasNext());
    }
}
