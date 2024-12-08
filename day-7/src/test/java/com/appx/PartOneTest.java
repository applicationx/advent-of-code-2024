package com.appx;

import com.appx.model.Operation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.Set;

class PartOneTest {
    @Test
    void testSolveWithExampleInput() throws IOException {
        Solver solution = new Solver(Set.of(Operation.ADD, Operation.MULTIPLY));
        assertEquals(3749, solution.solve("test_input.txt"));
    }

    @Test
    void testSolveWithInput() throws IOException {
        Solver solution = new Solver(Set.of(Operation.ADD, Operation.MULTIPLY));
        assertEquals(1582598718861l, solution.solve("input.txt"));
    }

}