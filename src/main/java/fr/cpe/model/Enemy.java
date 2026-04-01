package fr.cpe.model;

public class Enemy {
    private int pv;
    private float speed;
    private int position;

    public void move(){

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Enemy(int pv, float speed) {
        this.pv = pv;
        this.speed = speed;
    }
}
