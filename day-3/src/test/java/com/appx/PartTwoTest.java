package com.appx;

import com.appx.converter.ExpressionConverter;
import com.appx.file.ResourcesReader;
import com.appx.model.Do;
import com.appx.model.Dont;
import com.appx.model.Mul;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

class PartTwoTest {

    @Test
    void addTogetherMulValues() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        var result = reader.stream("input.txt")
                .map(ExpressionConverter::toMuls)
                .flatMap(Collection::stream)
                .collect(toList());

        boolean isDo = true;
        long resultValue = 0;
        for (var expression : result) {
            switch (expression) {
                case Do() -> isDo = true;
                case Dont() -> isDo = false;
                case Mul(int x, int y) -> {
                    if (isDo) {
                        resultValue += x * y;
                    }
                }
            }
        }
        System.out.println("Result: " + resultValue);
    }
}
