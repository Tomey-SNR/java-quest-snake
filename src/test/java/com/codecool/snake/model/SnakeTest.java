package com.codecool.snake.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    @Test
    void shouldInitializeSnakeWithStartingPosition() {
        Position startingPosition = new Position(5, 5);
        Snake snake = new Snake(startingPosition, Direction.RIGHT);

        assertEquals(5, snake.getHead().getX());
        assertEquals(5, snake.getHead().getY());
        assertEquals(1, snake.getBody().size());
        assertEquals(Direction.RIGHT, snake.getDirection());
    }

    @Test
    void shouldMoveSnakeToRight() {
        Snake snake = new Snake(new Position(5, 5), Direction.RIGHT);

        snake.move(false);

        assertEquals(6, snake.getHead().getX());
        assertEquals(5, snake.getHead().getY());
    }

    @Test
    void shouldMoveSnakeToLeft() {
        Snake snake = new Snake(new Position(5, 5), Direction.LEFT);

        snake.move(false);

        assertEquals(4, snake.getHead().getX());
        assertEquals(5, snake.getHead().getY());
    }

    @Test
    void shouldMoveSnakeUp() {
        Snake snake = new Snake(new Position(5, 5), Direction.UP);

        snake.move(false);

        assertEquals(5, snake.getHead().getX());
        assertEquals(4, snake.getHead().getY());
    }

    @Test
    void shouldMoveSnakeDown() {
        Snake snake = new Snake(new Position(5, 5), Direction.DOWN);

        snake.move(false);

        assertEquals(5, snake.getHead().getX());
        assertEquals(6, snake.getHead().getY());
    }

    @Test
    void shouldMaintainBodyLengthWhenMoving() {
        Snake snake = new Snake(new Position(5, 5), Direction.RIGHT);

        snake.move(false);

        assertEquals(1, snake.getBody().size());
    }

    @Test
    void shouldGrowWhenMovingWithGrowthEnabled() {
        Snake snake = new Snake(new Position(5, 5), Direction.RIGHT);

        snake.move(true);

        assertEquals(2, snake.getBody().size());
    }
}
