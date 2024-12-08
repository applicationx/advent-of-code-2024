package com.appx;

import com.appx.model.Operation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartTwoTest {
    @Test
    void testSolveWithExampleInput() throws IOException {
        Solver solution = new Solver(Set.of(Operation.ADD, Operation.MULTIPLY, Operation.CONCATENATE));
        assertEquals(11387, solution.solve("test_input.txt"));
    }

    @Test
    void testSolveWithInput() throws IOException {
        Solver solution = new Solver(Set.of(Operation.ADD, Operation.MULTIPLY, Operation.CONCATENATE));
        assertEquals(165278151522644l, solution.solve("input.txt"));
    }

}