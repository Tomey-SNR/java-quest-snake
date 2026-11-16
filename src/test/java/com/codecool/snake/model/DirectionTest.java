package com.codecool.snake.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    void shouldReturnCorrectMovementOffsets() {
        assertEquals(0, Direction.UP.getDeltaX());
        assertEquals(-1, Direction.UP.getDeltaY());

        assertEquals(0, Direction.DOWN.getDeltaX());
        assertEquals(1, Direction.DOWN.getDeltaY());

        assertEquals(-1, Direction.LEFT.getDeltaX());
        assertEquals(0, Direction.LEFT.getDeltaY());

        assertEquals(1, Direction.RIGHT.getDeltaX());
        assertEquals(0, Direction.RIGHT.getDeltaY());
    }
}
