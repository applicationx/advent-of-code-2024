package com.appx.converter;

import com.appx.model.Mul;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class MulConverter {

    private static final Pattern MULTIPLY_PATTERN = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

    public static List<Mul> toMuls(String line) {
        Matcher matcher = MULTIPLY_PATTERN.matcher(line);
        return Stream.generate(matcher::find)
                .takeWhile(b -> b).map(__ -> new Mul(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))))
                .collect(ArrayList::new, List::add, List::addAll);
    }
}
