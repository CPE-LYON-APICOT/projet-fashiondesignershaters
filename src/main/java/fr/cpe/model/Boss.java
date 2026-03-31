package fr.cpe.model;

public class Boss extends Enemy {
    private Enemy enemy;

    public Boss(Enemy enemy){
        this.enemy = enemy;
    }
}
