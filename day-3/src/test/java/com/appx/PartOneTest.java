package com.appx;

import com.appx.converter.MulConverter;
import com.appx.file.ResourcesReader;
import com.appx.model.Mul;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;

class PartOneTest {

    @Test
    void addTogetherMulValues() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        var result = reader.stream("input.txt")
                .map(MulConverter::toMuls)
                .flatMap(Collection::stream)
                .map(Mul::result)
                .mapToInt(Integer::intValue)
                .sum();


        System.out.println("Result: " + result);
    }
}
