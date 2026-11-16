package com.codecool.snake.controller;

import com.codecool.snake.model.Direction;
import com.codecool.snake.model.Game;
import com.codecool.snake.model.GameState;
import com.codecool.snake.view.GamePanel;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class GameController {

    private final Game game;
    private final GamePanel gamePanel;
    private final Timer timer;

    private boolean paused;

    public GameController(Game game, GamePanel gamePanel) {
        this.game = game;
        this.gamePanel = gamePanel;

        this.timer = new Timer(150, event -> updateGame());

        setupKeyBindings();
    }

    private void setupKeyBindings() {
        InputMap inputMap = gamePanel.getInputMap(
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );

        ActionMap actionMap = gamePanel.getActionMap();

        bindKey(inputMap, actionMap, KeyEvent.VK_SPACE,
                "start", this::startGame);

        bindKey(inputMap, actionMap, KeyEvent.VK_UP,
                "up", () -> changeDirection(Direction.UP));

        bindKey(inputMap, actionMap, KeyEvent.VK_DOWN,
                "down", () -> changeDirection(Direction.DOWN));

        bindKey(inputMap, actionMap, KeyEvent.VK_LEFT,
                "left", () -> changeDirection(Direction.LEFT));

        bindKey(inputMap, actionMap, KeyEvent.VK_RIGHT,
                "right", () -> changeDirection(Direction.RIGHT));

        bindKey(inputMap, actionMap, KeyEvent.VK_P,
                "pause", this::togglePause);

        bindKey(inputMap, actionMap, KeyEvent.VK_R,
                "restart", this::restartGame);
    }

    private void bindKey(
            InputMap inputMap,
            ActionMap actionMap,
            int keyCode,
            String actionName,
            Runnable action
    ) {
        inputMap.put(
                KeyStroke.getKeyStroke(keyCode, 0),
                actionName
        );

        actionMap.put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                action.run();
            }
        });
    }

    private void startGame() {
        if (game.getState() != GameState.START) {
            return;
        }

        game.start();
        paused = false;
        gamePanel.setPaused(false);

        timer.start();
        gamePanel.repaint();
    }

    private void changeDirection(Direction direction) {
        if (game.getState() != GameState.PLAYING || paused) {
            return;
        }

        game.getSnake().changeDirection(direction);
    }

    private void updateGame() {
        if (paused || game.getState() != GameState.PLAYING) {
            return;
        }

        game.tick();
        gamePanel.repaint();

        if (game.getState() == GameState.GAME_OVER) {
            timer.stop();
        }
    }

    private void togglePause() {
        if (game.getState() != GameState.PLAYING) {
            return;
        }

        paused = !paused;

        gamePanel.setPaused(paused);

        if (paused) {
            timer.stop();
        } else {
            timer.start();
        }

        gamePanel.repaint();
    }

    private void restartGame() {
        if (game.getState() != GameState.GAME_OVER) {
            return;
        }

        timer.stop();
        paused = false;

        game.reset();

        gamePanel.setPaused(false);
        gamePanel.repaint();
    }
}
