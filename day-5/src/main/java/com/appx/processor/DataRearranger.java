package com.appx.processor;

import com.appx.model.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DataRearranger {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataRearranger.class);
    private final Map<Integer, Set<Integer>> beforeRules;
    private final Map<Integer, Set<Integer>> afterRules;
    private final DataValidator validator;


    public DataRearranger(Map<Integer, Set<Integer>> beforeRules, Map<Integer, Set<Integer>> afterRules) {
        this.beforeRules = Objects.requireNonNull(beforeRules, "beforeRules must not be null");
        this.afterRules = Objects.requireNonNull(afterRules, "afterRules must not be null");
        validator = new DataValidator(beforeRules, afterRules);
    }

    //TODO: Need to implement this method
    public Data rearrangeData(Data input) {
        List<Integer> dataPoints = input.dataPoints();
        outer:
        do {
            for (int i = 0; i < dataPoints.size(); i++) {
                int value = dataPoints.get(i);
                Set<Integer> before = beforeRules.getOrDefault(value, Set.of());
                Set<Integer> after = afterRules.getOrDefault(value, Set.of());
                List<Integer> beforeData = dataPoints.subList(0, i);
                List<Integer> afterData = dataPoints.subList(i + 1, dataPoints.size());
                for (int j = 0; j < afterData.size(); j++) {
                    if (after.contains(afterData.get(j))) {
                        Integer removed = dataPoints.remove(i + j + 1);
                        dataPoints.add(i, removed);
                        continue outer;
                    }
                }
            }
        } while (!validator.isValidData(new Data(dataPoints)));
        return new Data(dataPoints);
    }
}
