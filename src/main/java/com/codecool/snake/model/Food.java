package com.codecool.snake.model;

import java.util.Objects;

public class Food {

    private final Position position;

    public Food(Position position) {
        this.position = Objects.requireNonNull(
                position, "Food position cannot be null"
        );
    }

    public Position getPosition() {
        return position;
    }
}
