package objects;

import java.awt.Point;

public class Polygon {

    private int height;
    private int width;
    private Point leftUpVertex;
    private Point rightDownVertex;

    public Polygon() {
        this.height = 1;
        this.width = 1;
        this.leftUpVertex = new Point(0,0);
        this.rightDownVertex = new Point(1,1);
    }

    // Polygon attributes setters & getters

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLeftUpVertex(int x, int y) {
        this.leftUpVertex.setLocation(x, y);
    }

    public void setRightDownVertex(int x, int y) {
        this.rightDownVertex.setLocation(x, y);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public Point getLeftUpVertex() {
        return this.leftUpVertex;
    }

    public Point getRightDownVertex() {
        return this.rightDownVertex;
    }

    //Additional setters & getters

    public void setRightPosition(int x) {
        this.rightDownVertex.setLocation(x, this.rightDownVertex.getY());
    }

    public void setLeftPosition(int x) {
        this.leftUpVertex.setLocation(x, this.leftUpVertex.getY());
    }

    public void setTopPosition(int y) {
        this.leftUpVertex.setLocation(this.leftUpVertex.getX(), y);
    }

    public void setBottomPosition(int y) {
        this.rightDownVertex.setLocation(this.rightDownVertex.getX(), y);
    }

    public int getRightPosition() {
        return (int) this.rightDownVertex.getX();
    }

    public int getLeftPosition() {
        return (int) this.leftUpVertex.getX();
    }

    public int getTopPosition() {
        return (int) this.leftUpVertex.getY();
    }

    public int getBottomPosition() {
        return (int) this.rightDownVertex.getY();
    }
}