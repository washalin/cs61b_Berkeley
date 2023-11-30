package es.datastructur.synthesizer;
import java.util.Iterator;
public interface BoundedQueue<T> extends Iterable<T>{
    int capacity();;
    int fillCount();
    void enqueue(T x);
    T dequeue();
    T peek();

    @Override
    Iterator<T> iterator();

    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }
    default boolean isFull() {
        if (capacity() == fillCount()) {
            return true;
        }
        return false;
    }
}
