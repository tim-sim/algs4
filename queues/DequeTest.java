import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;


public class DequeTest {
    private static final String FIRST = "first";
    private static final String SECOND = "second";
    private static final String THIRD = "third";
    private static final String FOURTH = "fourth";

    @Test
    public void testAddRemove() {
        Deque<String> deq = new Deque<String>();

        assertTrue(deq.isEmpty());
        assertEquals(0, deq.size());

        deq.addFirst(SECOND);
        deq.addFirst(FIRST);
        deq.addLast(THIRD);
        deq.addLast(FOURTH);

        assertFalse(deq.isEmpty());
        assertEquals(4, deq.size());

        assertEquals(FIRST, deq.removeFirst());
        assertEquals(FOURTH, deq.removeLast());
        assertEquals(SECOND, deq.removeFirst());
        assertEquals(THIRD, deq.removeLast());

        assertTrue(deq.isEmpty());
        assertEquals(0, deq.size());
    }

    @Test
    public void testIterator() {
        Deque<String> deq = new Deque<String>();
        
        deq.addFirst(SECOND);
        deq.addFirst(FIRST);
        deq.addLast(THIRD);
        deq.addLast(FOURTH);

        Iterator<String> iter = deq.iterator();

        assertTrue(iter.hasNext());
        assertEquals(FIRST, iter.next());
        assertEquals(SECOND, iter.next());
        assertEquals(THIRD, iter.next());
        assertEquals(FOURTH, iter.next());
        assertFalse(iter.hasNext());
    }
}
