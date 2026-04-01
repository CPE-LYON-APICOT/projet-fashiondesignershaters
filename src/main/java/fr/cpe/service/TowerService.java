package fr.cpe.service;

import fr.cpe.factory.EnumTower;
import fr.cpe.factory.TowerFactory;
import fr.cpe.model.*;

import java.util.List;

public class TowerService {
    private List<Tower> towers;
    private TowerFactory towerFactory;

    public void addTower(EnumTower.Tower towerType, Coord c){
        Tower tower = towerFactory.createTower(towerType,c);
        towers.add(tower);
    }
    public void updateTower(Tower tower, TowerDecorator towerDecorator){
        if (towerDecorator instanceof RangeDecorator){
            tower = new RangeDecorator(tower);
        }

        else if (towerDecorator instanceof IceDecorator){
            tower = new IceDecorator(tower);
        }
    }
}
