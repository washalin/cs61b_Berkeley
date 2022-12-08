public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        //System.arraycopy(array, 0, a, 0, size);
//        System.arraycopy(array, 0, a, 0, nextFirst);
//        int remain = array.length - nextFirst - 1;
//        System.arraycopy(array, nextLast, a, a.length - remain - 1, remain);
        int iter = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            int pos = (iter + array.length) % array.length;
            a[i] = array[pos];
            iter += 1;
        }
        nextFirst = -1;
        nextLast = size;
        array = a;
    }

    //    public ArrayDeque(ArrayDeque other) {
//        array = (T[]) new Object[other.array.length];
//        size = other.size;
//        nextFirst = other.nextFirst;
//        nextLast = other.nextLast;
//        int iter = nextFirst + 1;
//        for (int i = 0; i < size; i++) {
//            int pos = (iter + array.length) % array.length;
//            array[pos] = (T) other.array[pos];
//            iter += 1;
//        }
//    }
    public void addFirst(T item) {
        if (array[(nextFirst + array.length) % array.length] != null) {
            resize(2 * array.length);
        }
        int pos = (nextFirst + array.length) % array.length;
        array[pos] = item;
        nextFirst = pos - 1;
        size += 1;
    }

    public void addLast(T item) {
        if (array[(nextLast + array.length) % array.length] != null) {
            resize(2 * array.length);
        }
        int pos = (nextLast + array.length) % array.length;
        array[pos] = item;
        nextLast = pos + 1;
        size += 1;
    }

    public boolean isEmpty() {
//        if (size == 0) {
//            return false;
//        }
//        return true;a
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("your deque is null");
            return;
        }
        int iter = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            int pos = (iter + array.length) % array.length;
            System.out.print(array[pos] + " ");
            iter += 1;
        }
        System.out.print("\n");
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        float usage = (float) size / (float) array.length;
        if (usage < 0.25) {
            resize(array.length / 2);
        }
        int pos = (nextFirst + 1 + array.length) % array.length;
        T tmp = array[pos];
        array[pos] = null;
        nextFirst =pos;
        size -= 1;
        return tmp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        float usage = (float) size / (float) array.length;
        if (usage < 0.25) {
            resize(array.length / 2);
        }
        int pos = (nextLast - 1 + array.length) % array.length;
        T tmp = array[pos];
        array[pos] = null;
        nextLast  = pos;
        size -= 1;
        return tmp;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return array[(nextFirst + 1 + index + array.length) % array.length];
    }

}
