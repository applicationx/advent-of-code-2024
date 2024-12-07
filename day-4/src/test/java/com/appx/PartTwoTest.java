package com.appx;

import com.appx.file.ResourcesReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PartTwoTest {

    private final ResourcesReader resourcesReader = new ResourcesReader();
    private final XmasXFormation xmasXFormation = new XmasXFormation();

    @Test
    void numberOfMAS_in_x_formation_test_input() throws IOException {
        List<String> grid = resourcesReader.stream("test_input.txt").collect(Collectors.toList());
        assertThat(xmasXFormation.countXFormations(grid)).isEqualTo(9);
    }

    @Test
    void numberOfMAS_in_x_formation_input() throws IOException {
        List<String> grid = resourcesReader.stream("input.txt").collect(Collectors.toList());
        assertThat(xmasXFormation.countXFormations(grid)).isEqualTo(1925);
    }
} 