package com.appx;


import com.appx.converter.RowConverter;
import com.appx.file.ResourcesReader;
import com.appx.model.Row;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {

    public static final int EXPECTED = 531;

    @Test
    void summarizeSafeLevels_WithDampeners() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        assertThat(reader.stream("input.txt")
                .map(RowConverter::toRow)
                .filter(Row::isSafeWithDampener)
                .count()).as("The number of safe levels with dampener").isEqualTo(EXPECTED);
    }
}
