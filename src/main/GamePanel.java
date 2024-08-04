package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import objects.Polygon;

import javax.swing.JPanel;
import java.awt.Graphics;

public class GamePanel extends JPanel{

    private static final int HEIGHT = 50;
    private static final int WIDTH = 200;
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
                int heightToRemove = this.polygon.getHeight() - 1;

                this.polygon.setHeight(1);
                this.polygon.setTopPosition(this.polygon.getTopPosition() + (int) Math.ceil((float) heightToRemove/2));
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() - (int) Math.floor((float) heightToRemove/2));
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
                int widthToRemove = this.polygon.getWidth() - 1;
            
                this.polygon.setWidth(1);
                this.polygon.setLeftPosition(this.polygon.getLeftPosition() + (int) Math.ceil((float) widthToRemove/2));
                this.polygon.setRightPosition(this.polygon.getRightPosition() - (int) Math.floor((float) widthToRemove/2));
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

    public void changeYDelta(boolean sign) {
        int spaceUntilLimit;

        if (sign && petitionToMove(DOWN)) {
            spaceUntilLimit = spaceUntilLimit(DOWN);
            if (spaceUntilLimit >= 5) {
                this.polygon.setTopPosition(this.polygon.getTopPosition() + 5);
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() + 5);
            }
            else if (spaceUntilLimit > 0) {
                this.polygon.setTopPosition(this.polygon.getTopPosition() + spaceUntilLimit);
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() + spaceUntilLimit);
            }
        }
        else if(!sign && petitionToMove(UP)){
            spaceUntilLimit = spaceUntilLimit(UP);
            if (spaceUntilLimit >= 5) {
                this.polygon.setTopPosition(this.polygon.getTopPosition() - 5);
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() - 5);
            }
            else if (spaceUntilLimit > 0) {
                this.polygon.setTopPosition(this.polygon.getTopPosition() - spaceUntilLimit);
                this.polygon.setBottomPosition(this.polygon.getBottomPosition() - spaceUntilLimit);
            }
        }

        repaint();
    }

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
