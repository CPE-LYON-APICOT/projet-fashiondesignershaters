package fr.cpe.model;

public class Enemy {

    public double pv;
    public double speed;
    public double position;
    public double damage;

    public Enemy(double pv, double speed, double position, double damage) {
        this.pv = pv;
        this.speed = speed;
        this.position = position;
        this.damage = damage;
    }

    public void move(){

    }
}
