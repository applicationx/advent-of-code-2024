package com.appx;

import com.appx.factory.InputLineFactory;
import com.appx.file.ResourcesReader;
import com.appx.model.InputLine;
import com.appx.processor.DataRearranger;
import com.appx.processor.DataValidator;
import com.appx.processor.InputLineProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

public class PartTwoTest {

    @Test
    void rules_test_input() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        List<InputLine> inputLines = reader.stream("test_input.txt")
                .map(InputLineFactory::create)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        Map<Integer, Set<Integer>> beforeRules = InputLineProcessor.getBeforeRules(InputLineProcessor.streamRules(inputLines));
        Map<Integer, Set<Integer>> afterRules = InputLineProcessor.getAfterRules(InputLineProcessor.streamRules(inputLines));
        DataValidator validator = new DataValidator(beforeRules, afterRules);
        DataRearranger rearranger = new DataRearranger(beforeRules, afterRules);
        int sum = InputLineProcessor.streamData(inputLines)
                .filter(not(validator::isValidData))
                .peek(System.out::println)
                .map(data -> rearranger.rearrangeData(data))
                .mapToInt(data -> data.middle())
                .sum();

        assertThat(sum).isEqualTo(123);

    }

    @Test
    void rules_input() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        List<InputLine> inputLines = reader.stream("input.txt")
                .map(InputLineFactory::create)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

        Map<Integer, Set<Integer>> beforeRules = InputLineProcessor.getBeforeRules(InputLineProcessor.streamRules(inputLines));
        Map<Integer, Set<Integer>> afterRules = InputLineProcessor.getAfterRules(InputLineProcessor.streamRules(inputLines));
        DataValidator validator = new DataValidator(beforeRules, afterRules);
        DataRearranger rearranger = new DataRearranger(beforeRules, afterRules);
        int sum = InputLineProcessor.streamData(inputLines)
                .filter(not(validator::isValidData))
                .peek(System.out::println)
                .map(data -> rearranger.rearrangeData(data))
                .mapToInt(data -> data.middle())
                .sum();

        assertThat(sum).isEqualTo(123);

    }
}
