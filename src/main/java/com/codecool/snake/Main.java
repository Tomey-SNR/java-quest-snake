package com.codecool.snake;

import com.codecool.snake.controller.GameController;
import com.codecool.snake.model.Game;
import com.codecool.snake.view.GamePanel;
import com.codecool.snake.view.GameWindow;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            Game game = new Game(20, 20);

            GamePanel gamePanel = new GamePanel(game);

            new GameController(game, gamePanel);

            new GameWindow(gamePanel);
        });
    }
}
