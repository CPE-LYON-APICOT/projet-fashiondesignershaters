package fr.cpe.model;

public class IceDecorator extends TowerDecorator{
    public IceDecorator(Tower tower) {
        super(tower);
    }

    @Override
    public int getPower() {
        return super.getPower()*2;
    }
}
