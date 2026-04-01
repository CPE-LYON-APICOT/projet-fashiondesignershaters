package fr.cpe.model;

import javafx.scene.canvas.GraphicsContext;

public class Tower {

    private IAttackStrategy strategy;
    private int range;
    private int power;
    public Coord position = new Coord(0,0);

    public Tower(Coord position){
        this.position = position;
        this.range = 1;
        this.power = 100;
    }
    public Tower(){

    }
    public void update(){

    }

    public void draw(GraphicsContext gc){

    }

    public IAttackStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(IAttackStrategy strategy) {
        this.strategy = strategy;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }


}
