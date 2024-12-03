package com.appx;

import com.appx.collector.TwoListCollector;
import com.appx.converter.RowConverter;
import com.appx.file.ResourcesReader;
import com.appx.model.TwoLists;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

class PartTwoTest {

    @Test
    void testSimilarity() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        TwoLists twoLists = reader.stream("input.txt")
                .map(RowConverter::toRow)
                .flatMap(Optional::stream)
                .collect(TwoListCollector.toTwoLists());

        List<Integer> firstList = twoLists.getFirstList();
        List<Integer> secondList = twoLists.getSecondList();

        int similarityValue = firstList.stream()
                .map(first -> first * secondList.stream().filter(second -> second.equals(first)).count())
                .mapToInt(Long::intValue)
                .sum();

        System.out.println("Similarity: " + similarityValue);

    }
}
