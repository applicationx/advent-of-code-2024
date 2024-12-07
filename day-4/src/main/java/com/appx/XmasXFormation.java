package com.appx;

import java.util.List;

public class XmasXFormation {
    public int countXFormations(List<String> grid) {
        int count = 0;
        int rows = grid.size();
        int cols = grid.get(0).length();

        for (int y = 1; y < rows - 1; y++) {
            for (int x = 1; x < cols - 1; x++) {
                if (grid.get(y).charAt(x) == 'A') {
                    // For each 'A', we need exactly two valid MAS sequences in an X formation
                    if (isValidXFormation(grid, x, y, rows, cols)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean isValidXFormation(List<String> grid, int x, int y, int rows, int cols) {
        // For a valid X formation, we need exactly two diagonal MAS sequences
        // that share the center 'A'
        int validDiagonals = 0;

        // Check upper-left to lower-right diagonal
        if (checkDiagonalMAS(grid, x - 1, y - 1, x + 1, y + 1, rows, cols)) {
            validDiagonals++;
        }

        // Check upper-right to lower-left diagonal
        if (checkDiagonalMAS(grid, x + 1, y - 1, x - 1, y + 1, rows, cols)) {
            validDiagonals++;
        }

        // We need exactly two valid diagonals for a proper X formation
        return validDiagonals == 2;
    }

    private boolean checkDiagonalMAS(List<String> grid, int x1, int y1, int x2, int y2, int rows, int cols) {
        if (!isInBounds(x1, y1, rows, cols) || !isInBounds(x2, y2, rows, cols)) {
            return false;
        }

        // Check for MAS (forward)
        boolean forwardMAS = grid.get(y1).charAt(x1) == 'M' && grid.get(y2).charAt(x2) == 'S';
        // Check for SAM (backward)
        boolean backwardMAS = grid.get(y1).charAt(x1) == 'S' && grid.get(y2).charAt(x2) == 'M';

        return forwardMAS || backwardMAS;
    }

    private boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < cols && y >= 0 && y < rows;
    }
} 