package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import objects.Polygon;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel{

    private static final int HEIGHT = 50;
    private static final int WIDTH = 24;
    private static final int INIT_X_POS = 100;
    private static final int INIT_Y_POS = 100;

    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private MouseInputs mouse;
    private GameWindow gameWindow;

    private Polygon polygon;

    public GamePanel() {
        this.mouse = new MouseInputs();
        this.gameWindow = null;

        this.polygon = new Polygon();
        this.polygon.setHeight(HEIGHT);
        this.polygon.setWidth(WIDTH);
        this.polygon.setLeftUpVertex(INIT_X_POS, INIT_Y_POS);
        this.polygon.setRightDownVertex(INIT_X_POS+WIDTH, INIT_Y_POS+HEIGHT);

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("Poistion of the cube: R - " + (this.polygon.getRightPosition())
                                           + " // L - " + this.polygon.getLeftPosition()
                                           + " // U - " + this.polygon.getTopPosition()
                                           + " // D - " + (this.polygon.getBottomPosition()));
        g.fillRect(this.polygon.getLeftPosition(), this.polygon.getTopPosition(), this.polygon.getWidth(), this.polygon.getHeight());
    }

    public void changeHeight(boolean sign) {
        if (sign && (petitionToMove(UP) && petitionToMove(DOWN))) {
            if (spaceUntilLimit(UP) >= 2 && spaceUntilLimit(DOWN) >= 2) {
                this.polygon.setHeight(this.polygon.getHeight() + 4);
                this.polygon.setTopPosition(this.polygon.getTopPosition() - 2);
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() + 2);
            }
            else{
                this.polygon.setHeight(this.polygon.getHeight() + 2);
                this.polygon.setTopPosition(this.polygon.getTopPosition() - 1);
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() + 1);
            }
        }
        else if (!sign) {
            if (this.polygon.getHeight() >= 5) {
                this.polygon.setHeight(this.polygon.getHeight() - 4);
                this.polygon.setTopPosition(this.polygon.getTopPosition() + 2);
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() - 2);
            }
            else if (this.polygon.getHeight() > 1){
                this.polygon.setHeight(1);
                this.polygon.setTopPosition(this.polygon.getTopPosition() + 1);
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() - 1);
            }
        }

        repaint();
    }

    public void changeWidth(boolean sign) {
        if (sign && (petitionToMove(LEFT) && petitionToMove(RIGHT))) {
            if (spaceUntilLimit(LEFT) >= 2 && spaceUntilLimit(RIGHT) >= 2) {
                this.polygon.setWidth(this.polygon.getWidth() + 4);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() - 2);
                this.polygon.setRightPosition(this.polygon.getRightPosition() + 2);
            }
            else{
                this.polygon.setWidth(this.polygon.getWidth() + 2);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() - 1);
                this.polygon.setRightPosition(this.polygon.getRightPosition() + 1);
            }
        }
        else if (!sign) {
            if (this.polygon.getWidth() >= 5) {
                this.polygon.setWidth(this.polygon.getWidth() - 4);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() + 2);
                this.polygon.setRightPosition(this.polygon.getRightPosition() - 2);
            }
            else if (this.polygon.getWidth() > 1){
                this.polygon.setWidth(1);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() - 1);
                this.polygon.setRightPosition(this.polygon.getRightPosition() - 1);
            }
        }

        repaint();
    }

    public void changeXDelta(boolean sign) {
        int spaceUntilLimit;

        if (sign && petitionToMove(RIGHT)) {
            spaceUntilLimit = spaceUntilLimit(RIGHT);
            if (spaceUntilLimit >= 5) {
                this.polygon.setRightPosition(this.polygon.getRightPosition() + 5);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() + 5);
            }
            else if (spaceUntilLimit > 0) {
                this.polygon.setRightPosition(this.polygon.getRightPosition() + spaceUntilLimit);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() + spaceUntilLimit);
            }
        }
        else if(!sign && petitionToMove(LEFT)){
            spaceUntilLimit = spaceUntilLimit(LEFT);
            if (spaceUntilLimit >= 5) {
                this.polygon.setRightPosition(this.polygon.getRightPosition() - 5);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() - 5);
            }
            else if (spaceUntilLimit > 0) {
                this.polygon.setRightPosition(this.polygon.getRightPosition() - spaceUntilLimit);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() - spaceUntilLimit);
            }
        }

        repaint();
    }

    // public void changeYDelta(boolean sign) {
    //     int vertDrawableSize = gameWindow.getScreenHeight() - gameWindow.getVerticalInsets();

    //     System.out.println("GameWindow height: " + gameWindow.getScreenHeight());
    //     System.out.println("Rectangle height is " + (50+height));
    //     System.out.println("Vertical insets are " + gameWindow.getVerticalInsets());
    //     System.out.println("Caluclations are " + (vertDrawableSize-100-(50+height)));

    //     if ((yDelta == -100 && !sign) || (yDelta == vertDrawableSize-100-(50+height) && sign))
    //         return;

    //     if (yDelta+5 > vertDrawableSize-100-(50+height) && sign) //Rectangle moves 5 by 5 pixels. If the remaining space between the rectangle and the bottom
    //         yDelta += vertDrawableSize-100-(50+height) - yDelta; //of the window is less than 5, the rectangle moves down only the amount left
    //     else if (yDelta-5 < -100 && !sign)  //Same as previous reasoning but for the top of the window
    //         yDelta -= yDelta + 100;
    //     else if (sign)
    //         yDelta += 5;
    //     else
    //         yDelta -= 5;

    //     System.out.println("yDelta is " + yDelta + "\n");

    //     repaint();
    // }

    public boolean petitionToMove(int direction) {
        switch (direction) {
            case LEFT:
                if (this.polygon.getLeftPosition() != 0)
                    return true;
                else
                    return false;
            case RIGHT: 
                if (this.polygon.getRightPosition() < (gameWindow.getScreenWidth() - gameWindow.getHorizontalInsets()))
                    return true;
                else
                    return false;
            case UP: 
                if (this.polygon.getTopPosition() != 0)
                    return true;
                else
                    return false;
            case DOWN: 
                if (this.polygon.getBottomPosition() < (gameWindow.getScreenHeight() - gameWindow.getVerticalInsets()))
                    return true;
                else
                    return false;
            default:
                return false;
        }
    }

    public int spaceUntilLimit(int direction) {
        switch (direction) {
            case LEFT:
                return this.polygon.getLeftPosition();
            case RIGHT: 
                return gameWindow.getScreenWidth() - gameWindow.getHorizontalInsets() - this.polygon.getRightPosition();
            case UP: 
                return this.polygon.getTopPosition();
            case DOWN: 
                return gameWindow.getScreenHeight() - gameWindow.getVerticalInsets() - this.polygon.getBottomPosition();
            default:
                return 0;
        }
    }
}
