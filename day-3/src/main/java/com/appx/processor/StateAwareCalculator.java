package com.appx.processor;

import com.appx.model.Do;
import com.appx.model.Dont;
import com.appx.model.Expression;
import com.appx.model.Mul;

import java.util.List;

public class StateAwareCalculator {
    public static long getResultValue(List<Expression> result) {
        boolean isDo = true;
        long resultValue = 0;
        for (var expression : result) {
            switch (expression) {
                case Do() -> isDo = true;
                case Dont() -> isDo = false;
                case Mul(int x, int y) -> {
                    if (isDo) {
                        resultValue += x * y;
                    }
                }
            }
        }
        return resultValue;
    }

}
