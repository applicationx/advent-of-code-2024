package com.appx;

import com.appx.file.ResourcesReader;
import com.appx.model.Direction;
import com.appx.model.World;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PartOneTest {

    @Test
    public void testGuard_test_input() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        List<String> lines = reader.stream("test_input.txt").toList();
        World world = new World(lines);

        // Track visited positions
        Set<String> visited = new HashSet<>();
        visited.add(world.getGuardX() + "," + world.getGuardY());

        // Run the simulation until guard leaves map
        boolean running = true;
        while(running) {
            running = world.tick();
            if (running) {
                visited.add(world.getGuardX() + "," + world.getGuardY());
                // Optionally debug print:
                // world.printDebug();
            }
        }

        System.out.println("Distinct visited positions: " + visited.size());
        assertThat(visited.size()).isEqualTo(41);
    }

    @Test
    public void testGuard_input() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        List<String> lines = reader.stream("input.txt").toList();
        World world = new World(lines);

        // Track visited positions
        Set<String> visited = new HashSet<>();
        visited.add(world.getGuardX() + "," + world.getGuardY());

        // Run the simulation until guard leaves map
        boolean running = true;
        while(running) {
            running = world.tick();
            if (running) {
                visited.add(world.getGuardX() + "," + world.getGuardY());
                // Optionally debug print:
                // world.printDebug();
            }
        }

        System.out.println("Distinct visited positions: " + visited.size());
        assertThat(visited.size()).isEqualTo(5312);
    }
}
