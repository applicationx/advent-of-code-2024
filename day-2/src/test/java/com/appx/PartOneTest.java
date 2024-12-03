package com.appx;


import com.appx.converter.RowConverter;
import com.appx.file.ResourcesReader;
import com.appx.model.Row;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {

    public static final int EXPECTED = 479;

    @Test
    void summarizeSafeLevels() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        assertThat(reader.stream("input.txt")
                .map(RowConverter::toRow)
                .filter(Row::isSafe)
                .count())
                .as("The number of safe levels")
                .isEqualTo(EXPECTED);
    }
}
