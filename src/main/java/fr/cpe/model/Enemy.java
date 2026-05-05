package fr.cpe.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy {
    private int pv;
    private float speed;
    private double x;
    private double y;

    public Enemy(int pv, float speed) {
        this.pv = pv;
        this.speed = speed;
        this.x = -1.0;
        this.y = 7.0; 
    }

    public void move() {
        this.x += speed;
    }

    public void draw(GraphicsContext gc, int tileSize) {
        gc.setFill(Color.RED);
        gc.fillRect(x * tileSize + (tileSize * 0.2), y * tileSize + (tileSize * 0.2), tileSize * 0.6, tileSize * 0.6);
    }

    public boolean isDead() {
        return pv <= 0;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}