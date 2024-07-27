package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel{

    private MouseInputs mouse;
    private GameWindow gameWindow;

    private int height;
    private int width;
    private int xDelta;
    private int yDelta;

    public GamePanel() {
        this.height = 0;
        this.width = 0;
        this.xDelta = 0;
        this.yDelta = 0;

        this.mouse = new MouseInputs();
        this.gameWindow = null;

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(100+xDelta, 100+yDelta, 200+width, 50+height);
    }

    public void changeHeight(boolean sign) {
        if (height == -50 && !sign)
            return;

        // Increasses or decreasses hight by 4 pixels, and moves up or down 2 pixels respectively to compensate and stay on spot
        if (sign) { 
            height += 4; 
            yDelta -= 2;
        }
        else {
            height -= 4;
            yDelta +=2;
        }
        
        repaint();
    }

    public void changeWidth(boolean sign) {
        if (height == -200 && !sign)
            return;

        // Increasses or decreasses width by 4 pixels, and moves left or right 2 pixels respectively to compensate and stay on spot
        if (sign) {
            width += 4;
            xDelta -= 2;
        }
        else {
            width -= 4;
            xDelta += 2;
        }

        repaint();
    }

    public void changeXDelta(boolean sign) {
        int horizDrawableSize = gameWindow.getScreenWidth()-gameWindow.getHorizontalInsets();

        System.out.println("Screen width: " + gameWindow.getScreenWidth());
        System.out.println("Rectangle width is " + (200+width));
        System.out.println("Horizontal insets are " + gameWindow.getHorizontalInsets());
        System.out.println("Calculations are " + (horizDrawableSize-100-(200+width)));

        if ((xDelta == -100 && !sign) || (xDelta == horizDrawableSize-100-(200+width) && sign))
            return;

        if (xDelta+5 > horizDrawableSize-100-(200+width) && sign) //Rectangle moves 5 by 5 pixels. If the remaining space between the rectangle and the right
            xDelta += horizDrawableSize-100-(200+width) - xDelta; //side of the window is less than 5, the rectangle moves to the right only the amount left
        else if (xDelta-5 < -100 && !sign)  //Same as previous reasoning but for the left of the window
            xDelta -= xDelta + 100;
        else if (sign)
            xDelta += 5;
        else
            xDelta -= 5;

        System.out.println("xDelta is " + xDelta + "\n");

        repaint();
    }

    public void changeYDelta(boolean sign) {
        int vertDrawableSize = gameWindow.getScreenHeight() - gameWindow.getVerticalInsets();

        System.out.println("GameWindow height: " + gameWindow.getScreenHeight());
        System.out.println("Rectangle height is " + (50+height));
        System.out.println("Vertical insets are " + gameWindow.getVerticalInsets());
        System.out.println("Caluclations are " + (vertDrawableSize-100-(50+height)));

        if ((yDelta == -100 && !sign) || (yDelta == vertDrawableSize-100-(50+height) && sign))
            return;

        if (yDelta+5 > vertDrawableSize-100-(50+height) && sign) //Rectangle moves 5 by 5 pixels. If the remaining space between the rectangle and the bottom
            yDelta += vertDrawableSize-100-(50+height) - yDelta; //of the window is less than 5, the rectangle moves down only the amount left
        else if (yDelta-5 < -100 && !sign)  //Same as previous reasoning but for the top of the window
            yDelta -= yDelta + 100;
        else if (sign)
            yDelta += 5;
        else
            yDelta -= 5;

        System.out.println("yDelta is " + yDelta + "\n");

        repaint();
    }
}
