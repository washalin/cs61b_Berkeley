import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testAddSizePrintEmpty() {
        ArrayDeque<String> t1 = new ArrayDeque<>();
        assertEquals(true, t1.isEmpty());

        t1.addFirst("first");
        assertEquals(1, t1.size());

        t1.addLast("second");
        assertEquals(2, t1.size());

        t1.addLast("last");
        assertEquals(3, t1.size());

        t1.printDeque();
    }

    @Test
    public void testRemove() {
        ArrayDeque<Integer> t1 = new ArrayDeque<>();
        for (int i = 0; i < 8; i++) {
            t1.addLast(i);
        }
        t1.printDeque();

        t1.removeFirst();
        t1.removeLast();
        for (int i = 0; i < 16; i++) {
            t1.addFirst(i);
        }
        t1.printDeque();

        ArrayDeque<Integer> t2 = new ArrayDeque<>();
        t2.addFirst(0);
        t2.addFirst(1);
        t2.removeLast();
        t2.isEmpty();
        t2.removeLast();
        t2.addFirst(5);
        t2.addFirst(6);
        t2.addFirst(7);
        t2.addFirst(8);
        t2.removeLast();
        t2.addFirst(10);
        t2.printDeque();
    }
}