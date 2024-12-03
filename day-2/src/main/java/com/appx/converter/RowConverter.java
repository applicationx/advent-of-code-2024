package com.appx.converter;

import com.appx.model.Row;

import java.util.Arrays;

public final class RowConverter {

    public static Row toRow(String line) {
        String[] split = line.trim().split(" ");
        return new Row(Arrays.asList(split).stream().map(Integer::parseInt).toList());
    }
}
