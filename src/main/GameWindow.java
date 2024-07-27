package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

    private JFrame jframe;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();

        jframe.setSize(400,400);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public int getScreenWidth() {
        return (int) jframe.getSize().getWidth();
    }

    public int getScreenHeight() {
        return (int) jframe.getSize().getHeight();
    }

    public int getHorizontalInsets() {
        return jframe.getInsets().left + jframe.getInsets().right;
    }

    public int getVerticalInsets() {
        return jframe.getInsets().top + jframe.getInsets().bottom;
    }
}
