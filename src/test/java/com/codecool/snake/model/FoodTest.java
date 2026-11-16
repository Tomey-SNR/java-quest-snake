package com.codecool.snake.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void shouldCreateFoodWithCorrectPosition() {
        Position position = new Position(8, 6);

        Food food = new Food(position);

        assertEquals(8, food.getPosition().getX());
        assertEquals(6, food.getPosition().getY());
    }

    @Test
    void shouldNotAllowNullPosition() {
        assertThrows(
                NullPointerException.class,
                () -> new Food(null)
        );
    }
}
