package com.codecool.snake.view;

import com.codecool.snake.model.Game;
import com.codecool.snake.model.Position;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    private static final int CELL_SIZE = 25;

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color SNAKE_COLOR = new Color(0, 200, 80);
    private static final Color FOOD_COLOR = Color.RED;

    private final Game game;

    public GamePanel(Game game) {
        this.game = game;

        setPreferredSize(new Dimension(
                game.getBoardWidth() * CELL_SIZE,
                game.getBoardHeight() * CELL_SIZE
        ));

        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawFood(graphics);
        drawSnake(graphics);
        drawScore(graphics);

        if (game.isGameOver()) {
            drawGameOver(graphics);
        }
    }

    private void drawSnake(Graphics graphics) {
        graphics.setColor(SNAKE_COLOR);

        for (Position position : game.getSnake().getBody()) {
            graphics.fillRect(
                    position.getX() * CELL_SIZE,
                    position.getY() * CELL_SIZE,
                    CELL_SIZE - 2,
                    CELL_SIZE - 2
            );
        }
    }

    private void drawFood(Graphics graphics) {
        if (game.getFood() == null) {
            return;
        }

        Position position = game.getFood().getPosition();

        graphics.setColor(FOOD_COLOR);

        graphics.fillOval(
                position.getX() * CELL_SIZE,
                position.getY() * CELL_SIZE,
                CELL_SIZE - 2,
                CELL_SIZE - 2
        );
    }

    private void drawScore(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));

        graphics.drawString("Score: " + game.getScore(), 10, 20);
    }

    private void drawGameOver(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));

        String message = "GAME OVER";

        int messageWidth = graphics.getFontMetrics()
                .stringWidth(message);

        graphics.drawString(
                message,
                (getWidth() - messageWidth) / 2,
                getHeight() / 2
        );
    }
}
