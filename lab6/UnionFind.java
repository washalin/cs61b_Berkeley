
import java.util.LinkedList;

public class UnionFind {

    // TODO - Add instance variables?
    private int[] parents;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        parents = new int[n];
        for (int i = 0; i < n; i++) {

            parents[i] = -1;

        }

    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex >= parents.length) {
            throw new RuntimeException("vertex is not valid");
        }

    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        validate(v1);
        return -parents[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if (sizeOf(v1) > sizeOf(v2)) {
            parents[root1] += parents[root2];
            parents[root2] = root1;
        }
        else {
            parents[root2] += parents[root1];
            parents[root1] = root2;

        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
//        while (parents[vertex] >= 0) {
//            int temp = parents[vertex];
//            parents[vertex] = parents[temp];
//            vertex = temp;
//        }
        // vertex is root
        if (parents[vertex] < 0) {
            return vertex;
        }
        // parents[vertex] is root , no need to Path - compression
        if (parents[parents[vertex]] < 0) {
            return parents[vertex];
        }
        // Path-compression
        LinkedList<Integer> passedVertex = new LinkedList<>();
        while (parents[vertex] >= 0) {
            passedVertex.add(vertex);
            vertex = parents[vertex];
        }
        for (int i = 0; i<passedVertex.size();i++) {
            parents[passedVertex.get(i)] = vertex;
        }
        return vertex;
    }

}
