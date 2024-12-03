package com.appx;

import com.appx.converter.ExpressionConverter;
import com.appx.file.ResourcesReader;
import com.appx.processor.StateAwareCalculator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {

    private static final long EXPECTED = 89798695L;

    @Test
    void addTogetherMulValues_DuringCorrectState() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        assertThat(reader.stream("input.txt")
                .map(ExpressionConverter::toExpressions)
                .flatMap(Collection::stream)
                .collect(collectingAndThen(toList(), StateAwareCalculator::getResultValue)))
                .as("The sum of the results of the muls during the do() state")
                .isEqualTo(EXPECTED);
    }

}
