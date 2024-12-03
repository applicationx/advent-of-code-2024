package com.appx.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Row(List<Integer> levels) {

    public boolean isSafe() {
        if (levels.size() < 2) {
            return true;
        }

        boolean shouldIncrease = levels.get(0) < levels.get(1);
        for (int i = 1; i < levels.size(); i++) {
            boolean increasing = levels.get(i - 1) < levels.get(i);
            if (increasing != shouldIncrease) {
                return false;
            }
            int diff = Math.abs(levels.get(i) - levels.get(i - 1));
            if (diff == 0 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSafeWithDampener(Row row) {
        if (row.isSafe()) {
            return true;
        }
        for (int i = 0; i < row.levels().size(); i++) {
            List<Integer> removedValueList = new ArrayList<Integer>(row.levels());
            Collections.copy(removedValueList, row.levels());
            removedValueList.remove(i);
            if (new Row(removedValueList).isSafe()) {
                return true;
            }
        }
        return false;
    }
}