package com.appx;

import com.appx.file.ResourcesReader;
import com.appx.model.Direction;
import com.appx.model.World;
import com.appx.model.objects.Obstacle;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class PartTwoTest {

    @Test
    void infinite_variations() throws IOException {
        ResourcesReader reader = new ResourcesReader();
        List<String> lines = reader.stream("input.txt").toList();

        // 1. Run the baseline simulation without an additional obstacle
        SimulationResult baseline = simulateWithPotentialObstacle(lines, null, null);

        int startX = baseline.startX;
        int startY = baseline.startY;

        // 2. For each visited position (except start), try placing an obstacle and check if a loop occurs
        int loopCount = 0;

        for (var pos : baseline.visited) {
            int px = pos.x;
            int py = pos.y;
            if (px == startX && py == startY) {
                // Cannot place obstacle at starting position
                continue;
            }

            SimulationResult testResult = simulateWithPotentialObstacle(lines, px, py);
            if (testResult.loopDetected) {
                loopCount++;
            }
        }

        System.out.println("Number of positions that cause guard to get stuck in a loop: " + loopCount);
    }

    private static SimulationResult simulateWithPotentialObstacle(List<String> lines, Integer obstacleX, Integer obstacleY) {
        World world = new World(lines);

        // If requested, place an additional obstacle
        if (obstacleX != null && obstacleY != null) {
            if (!world.isOutOfBounds(obstacleX, obstacleY) && world.getTile(obstacleX, obstacleY).getObject() == null) {
                world.getTile(obstacleX, obstacleY).setObject(new Obstacle());
            }
        }

        // Track visited positions
        Set<Position> visited = new HashSet<>();
        visited.add(new Position(world.getGuardX(), world.getGuardY()));

        // Track states to detect loops: state = (x, y, direction)
        Set<State> statesSeen = new HashSet<>();
        statesSeen.add(new State(world.getGuardX(), world.getGuardY(), world.getGuard().getDirection()));

        boolean running = true;
        boolean loopDetected = false;

        while (running) {
            running = world.tick();
            if (running) {
                int gx = world.getGuardX();
                int gy = world.getGuardY();
                visited.add(new Position(gx, gy));

                Direction d = world.getGuard().getDirection();
                State current = new State(gx, gy, d);
                if (!statesSeen.add(current)) {
                    // We've seen this (x,y,direction) state before -> loop detected
                    loopDetected = true;
                    break;
                }
            }
        }

        return new SimulationResult(visited, loopDetected, world.getStartX(), world.getStartY());
    }


    private static class SimulationResult {
        Set<Position> visited;
        boolean loopDetected;
        int startX, startY;
        SimulationResult(Set<Position> visited, boolean loopDetected, int startX, int startY) {
            this.visited = visited;
            this.loopDetected = loopDetected;
            this.startX = startX;
            this.startY = startY;
        }
    }

    private static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x; this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position p = (Position)o;
            return x == p.x && y == p.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class State {
        int x, y;
        Direction d;
        State(int x, int y, Direction d) {
            this.x = x; this.y = y; this.d = d;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;
            State s = (State)o;
            return x == s.x && y == s.y && d == s.d;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y, d);
        }
    }
}