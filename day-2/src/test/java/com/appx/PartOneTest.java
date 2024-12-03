package com.appx;


import com.appx.converter.RowConverter;
import com.appx.file.ResourcesReader;
import com.appx.model.Row;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PartOneTest {

    @Test
    void summarizeSafeLevels() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        long count = reader.stream("input.txt")
                .map(RowConverter::toRow)
                .filter(Row::isSafe)
                .count();

        System.out.println("Safe levels: " + count);
    }
}
