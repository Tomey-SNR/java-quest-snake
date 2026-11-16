package com.codecool.snake.model;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldInitializeGameCorrectly() {
        Game game = new Game(
                10,
                10,
                new Position(5, 5),
                Direction.RIGHT,
                new Position(8, 8),
                new Random(42)
        );

        assertEquals(0, game.getScore());
        assertFalse(game.isGameOver());
        assertEquals(5, game.getSnake().getHead().getX());
        assertEquals(5, game.getSnake().getHead().getY());
        assertEquals(GameState.START, game.getState());
    }

    @Test
    void shouldMoveSnakeWhenGameUpdates() {
        Game game = new Game(
                10,
                10,
                new Position(5, 5),
                Direction.RIGHT,
                new Position(8, 8),
                new Random(42)
        );

        game.start();
        game.tick();

        assertEquals(6, game.getSnake().getHead().getX());
        assertEquals(5, game.getSnake().getHead().getY());
    }

    @Test
    void shouldIncreaseScoreWhenSnakeEatsFood() {
        Game game = new Game(
                10,
                10,
                new Position(5, 5),
                Direction.RIGHT,
                new Position(6, 5),
                new Random(42)
        );

        game.start();
        game.tick();

        assertEquals(1, game.getScore());
        assertEquals(2, game.getSnake().getBody().size());
    }

    @Test
    void shouldEndGameWhenSnakeHitsRightWall() {
        Game game = new Game(
                10,
                10,
                new Position(9, 5),
                Direction.RIGHT,
                new Position(0, 0),
                new Random(42)
        );

        game.start();
        game.tick();

        assertTrue(game.isGameOver());
        assertEquals(GameState.GAME_OVER, game.getState());
    }

    @Test
    void shouldNotUpdateGameAfterGameOver() {
        Game game = new Game(
                10,
                10,
                new Position(9, 5),
                Direction.RIGHT,
                new Position(0, 0),
                new Random(42)
        );

        game.start();
        game.tick();

        int headXAfterCollision = game.getSnake().getHead().getX();

        game.tick();

        assertEquals(
                headXAfterCollision,
                game.getSnake().getHead().getX()
        );
        assertTrue(game.isGameOver());
    }

    @Test
    void generatedFoodShouldNotAppearOnSnake() {
        Game game = new Game(10, 10);

        Position foodPosition = game.getFood().getPosition();

        for (Position segment : game.getSnake().getBody()) {
            assertFalse(
                    foodPosition.getX() == segment.getX()
                            && foodPosition.getY() == segment.getY()
            );
        }
    }

    @Test
    void resetShouldRestoreInitialGameState() {
        Game game = new Game(
                10,
                10,
                new Position(5, 5),
                Direction.RIGHT,
                new Position(6, 5),
                new Random(42)
        );

        game.start();
        game.tick();

        assertEquals(1, game.getScore());
        assertEquals(2, game.getSnake().getBody().size());

        game.reset();

        assertEquals(0, game.getScore());
        assertFalse(game.isGameOver());
        assertEquals(GameState.START, game.getState());
        assertEquals(1, game.getSnake().getBody().size());
        assertEquals(5, game.getSnake().getHead().getX());
        assertEquals(5, game.getSnake().getHead().getY());
        assertEquals(Direction.RIGHT, game.getSnake().getDirection());
        assertNotNull(game.getFood());
    }

    @Test
    void gameShouldStartInStartState() {
        Game game = new Game(10, 10);

        assertEquals(GameState.START, game.getState());
    }

    @Test
    void startShouldChangeStateToPlaying() {
        Game game = new Game(10, 10);

        game.start();

        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void snakeShouldNotMoveBeforeGameStarts() {
        Game game = new Game(
                10,
                10,
                new Position(5, 5),
                Direction.RIGHT,
                new Position(8, 8),
                new Random(42)
        );

        int initialX = game.getSnake().getHead().getX();

        game.tick();

        assertEquals(initialX, game.getSnake().getHead().getX());
        assertEquals(GameState.START, game.getState());
    }
}
