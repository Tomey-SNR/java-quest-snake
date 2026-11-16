package com.codecool.snake.view;

import com.codecool.snake.model.Food;
import com.codecool.snake.model.Game;
import com.codecool.snake.model.GameState;
import com.codecool.snake.model.Position;
import com.codecool.snake.model.Snake;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class GamePanel extends JPanel {

    private static final int CELL_SIZE = 25;

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color SNAKE_COLOR = new Color(50, 205, 50);
    private static final Color FOOD_COLOR = Color.RED;
    private static final Color TEXT_COLOR = Color.WHITE;

    private final Game game;

    private boolean paused;

    public GamePanel(Game game) {
        this.game = game;

        setPreferredSize(new java.awt.Dimension(
                game.getBoardWidth() * CELL_SIZE,
                game.getBoardHeight() * CELL_SIZE
        ));

        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g2d = (Graphics2D) graphics.create();

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        if (game.getState() == GameState.START) {
            drawStartScreen(g2d);
        } else {
            drawFood(g2d);
            drawSnake(g2d);
            drawScore(g2d);

            if (game.getState() == GameState.GAME_OVER) {
                drawGameOverScreen(g2d);
            } else if (paused) {
                drawPauseScreen(g2d);
            }
        }

        g2d.dispose();
    }

    private void drawStartScreen(Graphics2D g2d) {
        drawCenteredText(
                g2d,
                "JAVA QUEST",
                90,
                new Font("Arial", Font.BOLD, 34),
                Color.GREEN
        );

        drawCenteredText(
                g2d,
                "SNAKE",
                140,
                new Font("Arial", Font.BOLD, 28),
                TEXT_COLOR
        );

        drawCenteredText(
                g2d,
                "Press SPACE to start",
                230,
                new Font("Arial", Font.PLAIN, 20),
                TEXT_COLOR
        );

        drawCenteredText(
                g2d,
                "Use arrow keys to move",
                270,
                new Font("Arial", Font.PLAIN, 16),
                Color.LIGHT_GRAY
        );
    }

    private void drawFood(Graphics2D g2d) {
        Food food = game.getFood();

        if (food == null) {
            return;
        }

        Position position = food.getPosition();

        g2d.setColor(FOOD_COLOR);

        g2d.fillOval(
                position.getX() * CELL_SIZE,
                position.getY() * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE
        );
    }

    private void drawSnake(Graphics2D g2d) {
        Snake snake = game.getSnake();

        for (Position position : snake.getBody()) {
            g2d.setColor(SNAKE_COLOR);

            g2d.fillRect(
                    position.getX() * CELL_SIZE,
                    position.getY() * CELL_SIZE,
                    CELL_SIZE,
                    CELL_SIZE
            );
        }
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setColor(TEXT_COLOR);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        g2d.drawString(
                "Score: " + game.getScore(),
                10,
                20
        );
    }

    private void drawPauseScreen(Graphics2D g2d) {
        drawOverlay(g2d);

        drawCenteredText(
                g2d,
                "PAUSED",
                getHeight() / 2,
                new Font("Arial", Font.BOLD, 36),
                Color.YELLOW
        );

        drawCenteredText(
                g2d,
                "Press P to resume",
                getHeight() / 2 + 40,
                new Font("Arial", Font.PLAIN, 18),
                TEXT_COLOR
        );
    }

    private void drawGameOverScreen(Graphics2D g2d) {
        drawOverlay(g2d);

        drawCenteredText(
                g2d,
                "GAME OVER",
                getHeight() / 2 - 35,
                new Font("Arial", Font.BOLD, 36),
                Color.RED
        );

        drawCenteredText(
                g2d,
                "Final score: " + game.getScore(),
                getHeight() / 2 + 10,
                new Font("Arial", Font.PLAIN, 20),
                TEXT_COLOR
        );

        drawCenteredText(
                g2d,
                "Press R to restart",
                getHeight() / 2 + 55,
                new Font("Arial", Font.PLAIN, 18),
                Color.YELLOW
        );
    }

    private void drawOverlay(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawCenteredText(
            Graphics2D g2d,
            String text,
            int y,
            Font font,
            Color color
    ) {
        g2d.setFont(font);
        g2d.setColor(color);

        FontMetrics metrics = g2d.getFontMetrics();

        int x = (getWidth() - metrics.stringWidth(text)) / 2;

        g2d.drawString(text, x, y);
    }
}
