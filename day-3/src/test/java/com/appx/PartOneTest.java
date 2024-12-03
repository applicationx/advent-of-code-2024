package com.appx;

import com.appx.converter.MulConverter;
import com.appx.file.ResourcesReader;
import com.appx.model.Mul;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {

    public static final long EXPECTED = 185797128L;

    @Test
    void addTogetherMulValues() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        assertThat(reader.stream("input.txt")
                .map(MulConverter::toMuls)
                .flatMap(Collection::stream)
                .map(Mul::result)
                .mapToInt(Integer::intValue)
                .sum())
                .as("The sum of the results of the muls")
                .isEqualTo(EXPECTED);
    }
}
