package com.codecool.snake.model;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldInitializeGameCorrectly() {
        Game game = createGame(
                new Position(5, 5),
                new Position(8, 8)
        );

        assertEquals(0, game.getScore());
        assertFalse(game.isGameOver());
        assertNotNull(game.getSnake());
        assertNotNull(game.getFood());
    }

    @Test
    void shouldMoveSnakeWhenGameUpdates() {
        Game game = createGame(
                new Position(5, 5),
                new Position(8, 8)
        );

        game.tick();

        assertEquals(6, game.getSnake().getHead().getX());
        assertEquals(5, game.getSnake().getHead().getY());
    }

    @Test
    void shouldIncreaseScoreWhenSnakeEatsFood() {
        Game game = createGame(
                new Position(5, 5),
                new Position(6, 5)
        );

        game.tick();

        assertEquals(1, game.getScore());
        assertEquals(2, game.getSnake().getBody().size());
    }

    @Test
    void shouldEndGameWhenSnakeHitsRightWall() {
        Game game = createGame(
                new Position(9, 5),
                new Position(0, 0)
        );

        game.tick();

        assertTrue(game.isGameOver());
    }

    @Test
    void shouldNotUpdateGameAfterGameOver() {
        Game game = createGame(
                new Position(9, 5),
                new Position(0, 0)
        );

        game.tick();

        int scoreAfterGameOver = game.getScore();
        int headXAfterGameOver = game.getSnake().getHead().getX();

        game.tick();

        assertEquals(scoreAfterGameOver, game.getScore());
        assertEquals(headXAfterGameOver, game.getSnake().getHead().getX());
    }

    @Test
    void shouldGenerateFoodAwayFromSnake() {
        Game game = new Game(10, 10);

        Position foodPosition = game.getFood().getPosition();
        Position snakePosition = game.getSnake().getHead();

        assertFalse(
                foodPosition.getX() == snakePosition.getX()
                        && foodPosition.getY() == snakePosition.getY()
        );
    }

    private Game createGame(Position startingPosition, Position foodPosition) {
        return new Game(
                10,
                10,
                startingPosition,
                Direction.RIGHT,
                foodPosition,
                new Random(42)
        );
    }
}
