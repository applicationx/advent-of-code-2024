package com.appx.processor;

import com.appx.model.Data;
import com.appx.model.InputLine;
import com.appx.model.Rule;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

public class InputLineProcessor {

    public static Stream<Rule> streamRules(List<InputLine> inputLines) {
        return inputLines.stream()
                .filter(inputLine -> inputLine instanceof Rule)
                .map(inputLine -> (Rule) inputLine);
    }

    public static List<Rule> getRules(List<InputLine> inputLines) {
        return streamRules(inputLines).toList();
    }

    public static Stream<Data> streamData(List<InputLine> inputLines) {
        return inputLines.stream()
                .filter(inputLine -> inputLine instanceof Data)
                .map(inputLine -> (Data) inputLine);
    }

    public static List<Data> getData(List<InputLine> inputLines) {
        return streamData(inputLines).toList();
    }

    public static Map<Integer, Set<Integer>> getBeforeRules(Stream<Rule> rules) {
        return rules.collect(groupingBy(Rule::first, mapping(Rule::second, toSet())));
    }

    public static Map<Integer, Set<Integer>> getAfterRules(Stream<Rule> rules) {
        return rules.collect(groupingBy(Rule::second, mapping(Rule::first, toSet())));
    }
}
