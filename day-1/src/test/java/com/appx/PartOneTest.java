package com.appx;

import com.appx.collector.TwoListCollector;
import com.appx.converter.RowConverter;
import com.appx.file.ResourcesReader;
import com.appx.model.TwoLists;
import com.appx.stream.StreamUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PartOneTest {

    public static final int EXPECTED = 1830467;

    @Test
    public void totalDistance() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        TwoLists twoLists = reader.stream("input.txt")
                .map(RowConverter::toRow)
                .flatMap(Optional::stream)
                .collect(TwoListCollector.toTwoLists());

        List<Integer> firstList = twoLists.getFirstList();
        List<Integer> secondList = twoLists.getSecondList();
        firstList.sort(Integer::compareTo);
        secondList.sort(Integer::compareTo);

        assertThat(StreamUtils.zip(firstList.stream(), secondList.stream(), (a, b) -> Math.abs(a - b))
                .mapToInt(Integer::intValue)
                .sum())
                .as("The sum of the differences between the two lists")
                .isEqualTo(EXPECTED);
    }
}
