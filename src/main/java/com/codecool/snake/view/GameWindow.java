package com.codecool.snake.view;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel) {
        setTitle("Java Quest - Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(gamePanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
