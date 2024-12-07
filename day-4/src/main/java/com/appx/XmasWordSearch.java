package com.appx;

import java.util.List;

public class XmasWordSearch {
    public int countXMAS(List<String> grid) {
        int count = 0;
        int rows = grid.size();
        int cols = grid.get(0).length();

        // All possible directions: right, down-right, down, down-left, left, up-left, up, up-right
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                if (grid.get(y).charAt(x) == 'X') {
                    // Try all directions
                    for (int dir = 0; dir < 8; dir++) {
                        if (checkXMAS(grid, x, y, dx[dir], dy[dir], rows, cols)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean checkXMAS(List<String> grid, int x, int y, int dx, int dy, int rows, int cols) {
        String target = "XMAS";
        for (int i = 0; i < target.length(); i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;

            if (newX < 0 || newX >= cols || newY < 0 || newY >= rows ||
                    grid.get(newY).charAt(newX) != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
} 