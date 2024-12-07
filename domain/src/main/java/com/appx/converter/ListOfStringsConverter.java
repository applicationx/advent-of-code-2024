package com.appx.converter;

import java.util.List;

public final class ListOfStringsConverter {

    public static char[][] toMatrix(List<String> strings) {
        char[][] matrix = new char[strings.size()][strings.get(0).length()];
        for (int i = 0; i < strings.size(); i++) {
            matrix[i] = strings.get(i).toCharArray();
        }
        return matrix;
    }
}
