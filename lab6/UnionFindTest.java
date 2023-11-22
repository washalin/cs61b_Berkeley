import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnionFindTest {
    public UnionFind getUnionFind() {
        UnionFind uf = new UnionFind(16);
        uf.union(1, 3);
        uf.union(4, 5);
        uf.union(2, 6);
        uf.union(7, 9);
        uf.union(8, 10);
        uf.union(11, 12);
        uf.union(13, 14);
        uf.union(15, 0);
        uf.union(0, 1);
        uf.union(3, 2);
        uf.union(12, 15);
        return uf;
        //parents 3,3,6,-8,5,-2,3,9,10,-2,-2,12,3,14,-2,3
    }

    @Test
    public void testSizeOf() {
        UnionFind uf = getUnionFind();
        assertEquals(8, uf.sizeOf(2));
        assertEquals(2, uf.sizeOf(9));
    }

    @Test
    public void testParent() {
        UnionFind uf = getUnionFind();
        assertEquals(3, uf.parent(0));
        assertEquals(-2, uf.parent(14));
        assertEquals(-8, uf.parent(3));
    }
    @Test
    public void testConected() {
      UnionFind uf = getUnionFind();
      assertEquals(true,uf.connected(3,1));
      assertEquals(true,uf.connected(15,6));
      assertEquals(false,uf.connected(11,7));
      assertEquals(false,uf.connected(8,9));

    }
    @Test
    public void testUnion() {
        UnionFind uf = getUnionFind();
        assertEquals(false,uf.connected(13,11));
        uf.union(15,14);
        assertEquals(true,uf.connected(13,11));
        assertEquals(false,uf.connected(7,8));
        uf.union(9,10);
        assertEquals(true,uf.connected(7,8));
        assertEquals(false,uf.connected(4,10));
        uf.union(5,8);
        assertEquals(true,uf.connected(4,10));
    }
    @Test
    public void testFind() {
        UnionFind uf = getUnionFind();
        assertEquals(3,uf.find(2));
        assertEquals(3,uf.parent(2));
        assertEquals(3,uf.find(11));
        assertEquals(3,uf.parent(11));
        assertEquals(10,uf.find(8));
    }

}
