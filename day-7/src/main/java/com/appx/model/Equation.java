package com.appx.model;

import java.util.List;

public class Equation {
    private final long testValue;
    private final List<Integer> numbers;

    public Equation(long testValue, List<Integer> numbers) {
        this.testValue = testValue;
        this.numbers = numbers;
    }

    public long getTestValue() {
        return testValue;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public static Equation parse(String line) {
        String[] parts = line.split(":");
        long testValue = Long.parseLong(parts[0].trim());
        String[] numberStrings = parts[1].trim().split("\\s+");
        List<Integer> numbers = java.util.Arrays.stream(numberStrings)
                .map(Integer::parseInt)
                .collect(java.util.stream.Collectors.toList());
        return new Equation(testValue, numbers);
    }
} 