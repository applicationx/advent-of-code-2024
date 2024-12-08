package com.appx;

import com.appx.file.ResourcesReader;
import com.appx.model.Equation;
import com.appx.model.Operation;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Solver {
    private final Set<Operation> allowedOperations;

    public Solver(Set<Operation> allowedOperations) {
        this.allowedOperations = allowedOperations;
    }

    public long solve(String inputFile) throws IOException {
        ResourcesReader reader = new ResourcesReader();
        List<String> lines = reader.stream(inputFile).toList();
        return lines.stream()
                .map(Equation::parse)
                .filter(this::canBeSolved)
                .mapToLong(Equation::getTestValue)
                .sum();
    }

    private boolean canBeSolved(Equation equation) {
        return tryAllOperatorCombinations(equation.getNumbers(), 0, equation.getNumbers().get(0), equation.getTestValue());
    }

    private boolean tryAllOperatorCombinations(List<Integer> numbers, int currentIndex, long currentResult, long target) {
        if (currentIndex == numbers.size() - 1) {
            if (currentResult == target) {
                return true;
            }
            return allowedOperations.contains(Operation.CONCATENATE) && 
                   Operation.CONCATENATE.apply(currentResult, numbers.get(currentIndex)) == target;
        }

        int nextNum = numbers.get(currentIndex + 1);
        
        for (Operation op : allowedOperations) {
            long result = op.apply(currentResult, nextNum);
            if (result >= 0 && tryAllOperatorCombinations(numbers, currentIndex + 1, result, target)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        // For part one, we only use ADD and MULTIPLY
        Solver solution = new Solver(Set.of(Operation.ADD, Operation.MULTIPLY));
        System.out.println(solution.solve("input.txt"));
    }
} 