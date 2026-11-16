package com.codecool.snake.controller;

import com.codecool.snake.model.Direction;
import com.codecool.snake.model.Game;
import com.codecool.snake.view.GamePanel;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameController {

    private final Game game;
    private final GamePanel gamePanel;
    private final Timer gameTimer;

    private boolean paused;

    public GameController(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;

        this.gameTimer = new Timer(150, event -> updateGame());

        configureKeyBindings();
        gameTimer.start();
    }

    private void updateGame() {
        if (paused || game.isGameOver()) {
            gameTimer.stop();
            gamePanel.repaint();
            return;
        }

        game.tick();
        gamePanel.repaint();

        if (game.isGameOver()) {
            gameTimer.stop();
        }
    }

    private void configureKeyBindings() {
        InputMap inputMap = gamePanel.getInputMap(
                GamePanel.WHEN_IN_FOCUSED_WINDOW
        );

        ActionMap actionMap = gamePanel.getActionMap();

        bindKey(inputMap, actionMap, KeyEvent.VK_UP,
                "moveUp", Direction.UP);

        bindKey(inputMap, actionMap, KeyEvent.VK_DOWN,
                "moveDown", Direction.DOWN);

        bindKey(inputMap, actionMap, KeyEvent.VK_LEFT,
                "moveLeft", Direction.LEFT);

        bindKey(inputMap, actionMap, KeyEvent.VK_RIGHT,
                "moveRight", Direction.RIGHT);

        inputMap.put(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, 0),
                "pause"
        );

        actionMap.put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                togglePause();
            }
        });
    }

    private void bindKey(
            InputMap inputMap,
            ActionMap actionMap,
            int keyCode,
            String actionName,
            Direction direction
    ) {
        inputMap.put(
                KeyStroke.getKeyStroke(keyCode, 0),
                actionName
        );

        actionMap.put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (!paused && !game.isGameOver()) {
                    game.getSnake().changeDirection(direction);
                }
            }
        });
    }

    private void togglePause() {
        if (game.isGameOver()) {
            return;
        }

        paused = !paused;

        gamePanel.setPaused(paused);

        if (paused) {
            gameTimer.stop();
        } else {
            gameTimer.start();
        }

        gamePanel.repaint();
    }
}
