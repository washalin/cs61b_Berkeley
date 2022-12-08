public class LinkedListDeque<T> {
    public static class Node<T> {

        private T item;
        private Node next;
        private Node prev;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {

        sentinel = new Node(sentinel, null, sentinel);
        size = 0;
    }

//    public LinkedListDeque(LinkedListDeque other) {
//        sentinel = new Node(other.sentinel.prev, null, other.sentinel.next);
//        size = other.size();
//    }

    public void addFirst(T item) {

        if (size != 0) {
            Node newList = new Node(sentinel, item, sentinel.next);
            //sentinel.next = new Node(sentinel, item, sentinel.next);
            sentinel.next.prev = newList;
            sentinel.next = newList;

        } else {
            sentinel.next = new Node(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size != 0) {
            sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
            sentinel.prev = sentinel.prev.next;
        } else {
            sentinel.next = new Node(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }

    public boolean isEmpty() {
//        if (size == 0) {
//            return true;
//        }
//        return false;
        return size == 0;
    }

    public int size() {
        return size;
    }

    //    public T getLast() {
//        if (size != 0){
//            return (T) sentinel.prev.item;
//        }
//        else {
//            return null;
//        }
//    }
    public void printDeque() {
        if (size != 0) {
            Node p = sentinel;
            for (int i = 0; i < size; i++) {
                System.out.print(p.next.item + " ");
                p = p.next;
            }
            System.out.print("\n");
        } else {
            System.out.println("this deque is empty!");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        //if (size > 0) {
        size -= 1;
        T temp = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return temp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T temp = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return temp;
    }

    public T get(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        //      if (size > index) {
        Node p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return (T) p.item;
//        } else {
//            System.out.println("your index is out of the size of deque!");
//            return null;
//        }

    }

    private T get(Node p, int index) {
        if (index == 0) {
            return (T) p.item;
        }
        return get(p.next, index - 1);
    }

    public T getRecursive(int index) {
        if (isEmpty() || index >= size) {
            return null;
        }
        //if (size > index) {
        return get(sentinel.next, index);
//        } else {
//            System.out.println("your index is out of the size of deque!");
//            return null;
//        }
    }
}