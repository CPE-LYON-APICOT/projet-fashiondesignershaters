package fr.cpe.model;

public class RangeDecorator extends TowerDecorator{
    public RangeDecorator(Tower tower) {
        super(tower);
    }

    @Override
    public int getRange() {
        return super.getRange()*2;
    }
}
