package com.appx.converter;

import com.appx.factory.ExpressionFactory;
import com.appx.model.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ExpressionConverter {
    private static final Pattern EXPRESSION_PATTERN =
            Pattern.compile("(mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\))");

    public static List<Expression> toMuls(String line) {
        Matcher matcher = EXPRESSION_PATTERN.matcher(line);
        return Stream.generate(matcher::find)
                .takeWhile(b -> b).map(__ -> {
                    return ExpressionFactory.createExpression(matcher.group());
                })
                .collect(ArrayList::new, List::add, List::addAll);
    }


}
