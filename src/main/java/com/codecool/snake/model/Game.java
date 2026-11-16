package com.codecool.snake.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Game {

    private final int boardWidth;
    private final int boardHeight;
    private final Position startingPosition;
    private final Direction startingDirection;
    private final Random random;

    private Snake snake;
    private Food food;
    private int score;
    private GameState state = GameState.START;

    public Game(int boardWidth, int boardHeight) {
        this(
                boardWidth,
                boardHeight,
                new Position(boardWidth / 2, boardHeight / 2),
                Direction.RIGHT,
                null,
                new Random()
        );
    }

    Game(
            int boardWidth,
            int boardHeight,
            Position startingPosition,
            Direction direction,
            Position foodPosition,
            Random random
    ) {
        if (boardWidth <= 0 || boardHeight <= 0) {
            throw new IllegalArgumentException(
                    "Board dimensions must be positive"
            );
        }

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.random = Objects.requireNonNull(random);

        this.startingPosition = Objects.requireNonNull(startingPosition);
        this.startingDirection = Objects.requireNonNull(direction);

        this.snake = new Snake(
                this.startingPosition,
                this.startingDirection
        );

        if (!isInsideBoard(startingPosition)) {
            throw new IllegalArgumentException(
                    "Snake must start inside the board"
            );
        }

        this.food = foodPosition == null
                ? generateFood()
                : new Food(foodPosition);

        if (food != null && !isInsideBoard(food.getPosition())) {
            throw new IllegalArgumentException(
                    "Food must be inside the board"
            );
        }

        if (food != null && isOnSnake(food.getPosition())) {
            throw new IllegalArgumentException(
                    "Food cannot spawn on the snake"
            );
        }
    }

    public void start() {
        if (state == GameState.START) {
            state = GameState.PLAYING;
        }
    }

    public void tick() {
        if (state != GameState.PLAYING) {
            return;
        }

        Position nextHead = new Position(
                snake.getHead().getX()
                        + snake.getDirection().getDeltaX(),
                snake.getHead().getY()
                        + snake.getDirection().getDeltaY()
        );

        boolean eating = food != null
                && nextHead.getX() == food.getPosition().getX()
                && nextHead.getY() == food.getPosition().getY();

        snake.move(eating);

        if (eating) {
            score++;
            food = generateFood();

            if (food == null) {
                state = GameState.GAME_OVER;
                return;
            }
        }

        if (!isInsideBoard(snake.getHead()) || hasSelfCollision()) {
            state = GameState.GAME_OVER;
        }
    }

    public void reset() {
        snake = new Snake(startingPosition, startingDirection);
        score = 0;
        state = GameState.START;
        food = generateFood();
    }

    private Food generateFood() {
        List<Position> availablePositions = new ArrayList<>();

        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                Position position = new Position(x, y);

                if (!isOnSnake(position)) {
                    availablePositions.add(position);
                }
            }
        }

        if (availablePositions.isEmpty()) {
            return null;
        }

        Position randomPosition = availablePositions.get(
                random.nextInt(availablePositions.size())
        );

        return new Food(randomPosition);
    }

    private boolean isInsideBoard(Position position) {
        return position.getX() >= 0
                && position.getX() < boardWidth
                && position.getY() >= 0
                && position.getY() < boardHeight;
    }

    private boolean isOnSnake(Position position) {
        return snake.getBody().stream().anyMatch(segment ->
                segment.getX() == position.getX()
                        && segment.getY() == position.getY()
        );
    }

    private boolean hasSelfCollision() {
        Position head = snake.getHead();
        List<Position> body = snake.getBody();

        return body.stream()
                .skip(1)
                .anyMatch(segment ->
                        segment.getX() == head.getX()
                                && segment.getY() == head.getY()
                );
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }

    public GameState getState() {
        return state;
    }

    public boolean isGameOver() {
        return state == GameState.GAME_OVER;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
}
