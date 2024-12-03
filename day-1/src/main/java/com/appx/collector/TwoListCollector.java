package com.appx.collector;

import com.appx.model.Row;
import com.appx.model.TwoLists;

import java.util.stream.Collector;

public class TwoListCollector {

    public static Collector<Row, ?, TwoLists> toTwoLists() {
        return Collector.of(
                TwoLists::new,
                (twoLists, row) -> {
                    twoLists.getFirstList().add(row.first());
                    twoLists.getSecondList().add(row.second());
                },
                (left, right) -> {
                    left.getFirstList().addAll(right.getFirstList());
                    left.getSecondList().addAll(right.getSecondList());
                    return left;
                }
        );
    }

}
