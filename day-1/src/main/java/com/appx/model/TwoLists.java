package com.appx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TwoLists {
    private final List<Integer> firstList = new ArrayList<>();
    private final List<Integer> secondList = new ArrayList<>();

    public List<Integer> getFirstList() {
        return firstList;
    }

    public List<Integer> getSecondList() {
        return secondList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TwoLists.class.getSimpleName() + "[", "]")
                .add("firstList=" + firstList)
                .add("secondList=" + secondList)
                .toString();
    }
}