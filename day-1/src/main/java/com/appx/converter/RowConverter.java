package com.appx.converter;

import com.appx.model.Row;

import java.util.Optional;

public final class RowConverter {

    public static Optional<Row> toRow(String row) {
        String[] parts = row.split("\\s+");
        if (parts.length == 2) {
            try {
                Integer first = Integer.valueOf(parts[0]);
                Integer second = Integer.valueOf(parts[1]);
                return Optional.of(new Row(first, second));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
