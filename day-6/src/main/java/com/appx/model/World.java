package com.appx.model;


import com.appx.model.objects.Guard;
import com.appx.model.objects.Obstacle;

import java.util.List;

public class World {
    private final int width;
    private final int height;
    private final Tile[][] tiles;
    private int guardX;
    private int guardY;
    private Guard guard;

    // Track the starting position of the guard
    private int startX;
    private int startY;

    public World(List<String> lines) {
        this.height = lines.size();
        this.width = lines.get(0).length();
        this.tiles = new Tile[height][width];

        for (int y = 0; y < height; y++) {
            String line = lines.get(y);
            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);
                Tile tile = switch(c) {
                    case '#' -> new Tile(new Obstacle());
                    case '^' -> {
                        guard = new Guard(Direction.UP);
                        guardX = x; guardY = y;
                        yield new Tile(null);
                    }
                    case 'v' -> {
                        guard = new Guard(Direction.DOWN);
                        guardX = x; guardY = y;
                        yield new Tile(null);
                    }
                    case '<' -> {
                        guard = new Guard(Direction.LEFT);
                        guardX = x; guardY = y;
                        yield new Tile(null);
                    }
                    case '>' -> {
                        guard = new Guard(Direction.RIGHT);
                        guardX = x; guardY = y;
                        yield new Tile(null);
                    }
                    default -> new Tile(null);
                };
                tiles[y][x] = tile;
            }
        }

        this.startX = guardX;
        this.startY = guardY;

        // Place the guard on the tile
        tiles[guardY][guardX].setObject(guard);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Guard getGuard() { return guard; }
    public int getGuardX() { return guardX; }
    public int getGuardY() { return guardY; }
    public int getStartX() { return startX; }
    public int getStartY() { return startY; }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    /**
     * Tick the simulation one step:
     * - If next step is blocked, guard turns right
     * - If free, guard moves forward
     * - Returns false if next step would leave the map (guard leaves map), true otherwise.
     */
    public boolean tick() {
        Direction d = guard.getDirection();
        int nx = guardX + d.dx();
        int ny = guardY + d.dy();

        if (isOutOfBounds(nx, ny)) {
            // Next step leaves the map
            return false;
        }

        Tile ahead = tiles[ny][nx];
        if (ahead.isBlocked()) {
            // Turn right
            guard.setDirection(d.turnRight());
            return true;
        } else {
            // Move forward
            tiles[guardY][guardX].setObject(null);
            guardX = nx;
            guardY = ny;
            tiles[guardY][guardX].setObject(guard);
            return true;
        }
    }

    // Optional debug printing
    public void printDebug() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == guardX && y == guardY) {
                    char g = switch(guard.getDirection()) {
                        case UP -> '^';
                        case DOWN -> 'v';
                        case LEFT -> '<';
                        case RIGHT -> '>';
                    };
                    System.out.print(g);
                } else {
                    Tile t = tiles[y][x];
                    if (t.isBlocked()) System.out.print('#');
                    else System.out.print('.');
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}