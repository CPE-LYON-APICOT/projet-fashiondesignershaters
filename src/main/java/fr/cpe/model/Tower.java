package fr.cpe.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tower {

    protected IAttackStrategy strategy;
    protected int range;
    protected int power;
    protected Color color = Color.CORNFLOWERBLUE;
    public Coord position = new Coord(0,0);

    public Tower(Coord position){
        this.position = position;
        this.range = 2;
        this.power = 100;
    }

    public Tower(){
    }

    public void update(){
    }
    // montrer la range visuellement
    public void draw(GraphicsContext gc, int tileSize){
        gc.setFill(color);
        gc.fillRect(position.x() * tileSize, position.y() * tileSize, tileSize, tileSize);
        
        gc.setStroke(Color.web("#ffffff", 0.1));
        gc.strokeOval((position.x() + 0.5 - range) * tileSize, 
                     (position.y() + 0.5 - range) * tileSize, 
                     range * 2 * tileSize, 
                     range * 2 * tileSize);
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

    public void setColor(Color color) { 
        this.color = color; 
    }

    public Color getColor() { 
        return color; 
    }

    public boolean isEnemyInRange(Enemy target) {
        double dx = this.position.x() - target.getX();
        double dy = this.position.y() - target.getY();
        double distanceSquared = (dx * dx) + (dy * dy);
        
        return distanceSquared <= (range * range);
    }
}