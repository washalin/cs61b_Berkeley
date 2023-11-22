import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    @Override
    public void clear() {
        root.size = 0;
        root = null;

    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument to put with a null key");
        }
        if (value == null) {
            remove(key);
            return;
        }
        root = put(root, key, value);

    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public Set<K> keySet() {
        if (isEmpty()) {
            return new HashSet<K>();
        }
        return keySet(min(), max());
    }

    public Set<K> keySet(K lo, K hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keySet is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keySet is null");
        }
        Set<K> keys = new HashSet<K>();
        keySet(root, keys, lo, hi);
        return keys;
    }

    private void keySet(Node x, Set<K> keys, K lo, K hi) {
        if (x == null) return;
        int cmpL = lo.compareTo(x.key);
        int cmpH = hi.compareTo(x.key);
        if (cmpL < 0) {
            keySet(x.left, keys, lo, hi);
        }
        if (cmpL <= 0 && cmpH >= 0) {
            keys.add(x.key);
        }
        if (cmpH > 0) {
            keySet(x.right, keys, lo, hi);
        }
    }

    private Node deleteMin(Node x) {
        // delete minimum node with one child or none child
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with a null key");
        }
        V v=get(key);
        root = remove(root, key);
        return v;
    }

    private Node remove(Node x, K key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else {
            // delete node with one child or none child
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            // delete node with two child
            Node tmp = x;
            x = min(tmp.right);
            x.right = deleteMin(tmp.right);
            x.left = tmp.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove(K,V) with a null key");
        }
        if (value.equals(get(key))) {
            remove(key);
            return value;
        }
        else {
            throw new InputMismatchException("calls remove() with wrong value");
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public K min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public K max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterotor<>();
    }
    private class BSTMapIterotor<K> implements Iterator<K> {

        Stack<Node> stack = new Stack<Node>();
        private BSTMapIterotor() {
            stackIn(root);
        }
        private void stackIn(Node x) {
            if (x == null) return;
            stack.push(x);
            stackIn(x.left);
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node tmp = stack.pop();
            stackIn(tmp.right);
            return (K) tmp.key;
        }
    }
    public void printInOrder() {
        if (isEmpty()) {
            System.out.print("your BSTMap is empty");
        }
        else {
            printInOrder(root);
        }
    }
    private void printInOrder(Node x) {
        if (x == null) {
            return;
        }
        printInOrder(x.left);
        System.out.println("key:" + x.key + ",value:" + x.value);
        printInOrder(x.right);
    }
}
