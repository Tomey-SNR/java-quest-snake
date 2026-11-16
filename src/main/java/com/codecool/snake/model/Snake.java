package com.codecool.snake.model;

import java.util.LinkedList;
import java.util.List;

public class Snake {

    private final LinkedList<Position> body;
    private Direction direction;

    public Snake(Position startingPosition, Direction direction) {
        this.body = new LinkedList<>();
        this.body.add(startingPosition);
        this.direction = direction;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public List<Position> getBody() {
        return List.copyOf(body);
    }

    public Direction getDirection() {
        return direction;
    }

    public void move(boolean grow) {
        int newX = getHead().getX() + direction.getDeltaX();
        int newY = getHead().getY() + direction.getDeltaY();

        Position newHead = new Position(newX, newY);

        body.addFirst(newHead);

        if (!grow) {
            body.removeLast();
        }
    }

    public void changeDirection(Direction newDirection) {
        if (newDirection == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }

        boolean isOppositeDirection =
                direction.getDeltaX() + newDirection.getDeltaX() == 0
                        && direction.getDeltaY() + newDirection.getDeltaY() == 0;

        if (!isOppositeDirection) {
            direction = newDirection;
        }
    }
}
