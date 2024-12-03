package com.appx;

import com.appx.converter.ExpressionConverter;
import com.appx.file.ResourcesReader;
import com.appx.processor.StateAwareCalculator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class PartTwoTest {

    @Test
    void addTogetherMulValues() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        var result = reader.stream("input.txt")
                .map(ExpressionConverter::toExpressions)
                .flatMap(Collection::stream)
                .collect(collectingAndThen(toList(), StateAwareCalculator::getResultValue));

        System.out.println("Result: " + result);
    }

}
