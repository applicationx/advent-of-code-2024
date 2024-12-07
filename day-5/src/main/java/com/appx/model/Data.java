package com.appx.model;

import java.util.List;

public record Data(List<Integer> dataPoints) implements InputLine {

    public Integer middle() {
        return dataPoints.get(dataPoints.size() / 2);
    }
}
