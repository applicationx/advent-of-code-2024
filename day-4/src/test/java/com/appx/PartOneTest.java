package com.appx;

import com.appx.file.ResourcesReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {
    private final ResourcesReader resourcesReader = new ResourcesReader();
    private final XmasWordSearch xmasWordSearch = new XmasWordSearch();

    @Test
    void numberOfXMAS_test_input() throws IOException {
        List<String> grid = resourcesReader.stream("test_input.txt").collect(Collectors.toList());
        assertThat(xmasWordSearch.countXMAS(grid)).isEqualTo(18);
    }

    @Test
    void numberOfXMAS_input() throws IOException {
        List<String> grid = resourcesReader.stream("input.txt").collect(Collectors.toList());
        assertThat(xmasWordSearch.countXMAS(grid)).isEqualTo(2483);
    }
}
