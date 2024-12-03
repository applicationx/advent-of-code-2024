package com.appx.model;

public record Mul(int a, int b) implements Expression {
    public int result() {
        return a * b;
    }
}
