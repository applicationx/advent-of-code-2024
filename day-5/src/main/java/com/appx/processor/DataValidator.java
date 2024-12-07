package com.appx.processor;

import com.appx.model.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DataValidator {
    private final Map<Integer, Set<Integer>> beforeRules;
    private final Map<Integer, Set<Integer>> afterRules;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataValidator.class);

    public DataValidator(Map<Integer, Set<Integer>> beforeRules, Map<Integer, Set<Integer>> afterRules) {
        this.beforeRules = Objects.requireNonNull(beforeRules, "beforeRules must not be null");
        this.afterRules = Objects.requireNonNull(afterRules, "afterRules must not be null");
    }

    public boolean isValidData(Data data) {
        var first = data.dataPoints().getFirst();
        var tail = data.dataPoints().subList(1, data.dataPoints().size());
        return isValidData(first, tail);
    }

    private boolean isValidData(Integer value, List<Integer> data) {
        if (data.isEmpty()) {
            return true;
        }
        if (afterRules.getOrDefault(value, Set.of()).stream()
               .anyMatch(data::contains)) {
            LOGGER.debug("Invalid data: {} -> {}, afterRules({}) = {}", value, data, value, afterRules.get(value));
            return false;
        }
        return isValidData(data.getFirst(), data.subList(1, data.size()));
    }
}

