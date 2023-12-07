package byog.lab5;

import com.sun.xml.internal.ws.dump.LoggingDumpTube;


import javafx.geometry.Pos;
import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int SEED = 1314520;
    private static final Random RANDOM = new Random(SEED);

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    public static int hexRowWidth(int size, int i) {
        int trueI = i;
        if (i >= size) {
            trueI = 2 * size - i - 1;
        }
        return size + 2 * trueI;
    }

    public static int hexRowOffset(int size, int i) {
        int tureOffset = i;
        if (i >= size) {
            tureOffset = 2 * size - i - 1;
        }
        return -tureOffset;
    }


    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int x = 0; x < width; x++) {
            int xCoord = p.x + x;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int size, TETile t) {
        if (size < 2) {
            throw new IllegalArgumentException("hexagon must be at least size 2.");
        }
        for (int y = 0; y < 2 * size; y++) {
            int width = hexRowWidth(size, y);
            int xStart = p.x + hexRowOffset(size, y);
            int rowY = p.y + y;
            Position newP = new Position(xStart, rowY);
            addRow(world, newP, width, t);
        }
    }

    private static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static Position axisToScreen(Position center, Position p, int size) {
        int x = (size + 1) * 3 / 2 * p.x + center.x;
        int y = size * (p.x + 2 * p.y) + center.y;
        return new Position(x, y);
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0:
                return Tileset.GRASS;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.MOUNTAIN;
            case 4:
                return Tileset.SAND;
            default:
                return Tileset.TREE;
        }
    }

    public static void hexMap(TETile world[][], int size) {
        if (size < 2) {
            throw new IllegalArgumentException("hexagon must be at least size 2.");
        }
        Position center = new Position(WIDTH / 2 - size / 2, HEIGHT / 2 - size);
        int N = 3;
        for (int q = -N; q <= N; q++) {
            int r1 = Math.max(-N, -q - N);
            int r2 = Math.min(N, -q + N);
            for (int r = r1; r <= r2; r++) {
                addHexagon(world, axisToScreen(center, new Position(q, r), size), size, randomTile());
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        hexMap(world, 4);

        ter.renderFrame(world);
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }
}
