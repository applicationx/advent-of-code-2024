package com.appx.factory;

import com.appx.converter.MulConverter;
import com.appx.model.Expression;

public final class ExpressionFactory {

    public static Expression createExpression(String value) {
        if (value.startsWith("mul")) {
            String[] values = value.split(" ");
            return MulConverter.toMuls(value).getFirst();
        } else if (value.startsWith("don't")) {
            return new com.appx.model.Dont();
        } else if (value.startsWith("do")) {
            return new com.appx.model.Do();
        } else {
            throw new IllegalArgumentException("Invalid expression");
        }
    }
}
