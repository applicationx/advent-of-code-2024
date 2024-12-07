package com.appx.factory;

import com.appx.model.Data;
import com.appx.model.InputLine;
import com.appx.model.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public final class InputLineFactory {

    private InputLineFactory() {
    }

    public static Optional<InputLine> create(String line) {
        if (line.isEmpty()) {
            return Optional.empty();
        } else if (line.contains("|")) {
            String[] numbers = line.trim().split("\\|");
            return Optional.of(new Rule(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
        } else {
            String[] data = line.split(",");
            return Optional.of(new Data(new ArrayList<>(Arrays.stream(data).map(Integer::parseInt).collect(Collectors.toList()))));
        }

    }
}
