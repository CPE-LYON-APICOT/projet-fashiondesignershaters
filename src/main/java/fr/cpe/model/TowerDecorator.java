package fr.cpe.model;

public abstract class TowerDecorator extends Tower{

    public final Tower baseTower;

    public TowerDecorator(Tower tower){
        this.baseTower = tower;
    }
}
