package com.codecool.snake.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void shouldCreatePositionWithCorrectCoordinates() {

        Position position = new Position(5, 10);

        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }
}
