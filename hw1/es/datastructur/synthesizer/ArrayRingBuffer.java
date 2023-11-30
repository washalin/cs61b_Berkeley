package es.datastructur.synthesizer;

import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> extends AbstractBoundQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */

    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;

    }

    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last % capacity] = x;
        last += 1;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T tmp = rb[first % capacity];
        first += 1;
        fillCount -= 1;
        return tmp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        //  Return the first item. None of your instance variables should
        //       change.
        System.out.println(fillCount + " " + capacity);

        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first % capacity];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    public Iterator<T> iterator() {
        return new myIterator();
    }

    private class myIterator<T> implements Iterator<T> {

        private int cur;

        public myIterator() {
            cur = first;
        }

        @Override
        public boolean hasNext() {
            if (cur % capacity == last % capacity) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            T tmp = (T) rb[cur % capacity];
            cur += 1;
            return tmp;
        }
    }
}

