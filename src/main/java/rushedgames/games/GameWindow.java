package rushedgames.games;

import javax.swing.*;

public class GameWindow {
    public static void main(String[] args) {

        JFrame window = new JFrame("Snake");
        window.setResizable(false);
        GameScene scene = new GameScene();
        window.add(scene);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    
    }
}