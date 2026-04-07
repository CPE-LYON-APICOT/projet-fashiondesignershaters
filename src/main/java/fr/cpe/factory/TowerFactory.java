package fr.cpe.factory;

import fr.cpe.model.*;
import fr.cpe.service.GameState;

public class TowerFactory {

    public Tower createTower(EnumTower.Tower towerType, Coord c){

        Tower tower = new Tower(c);
        GameState.getInstance().incrementerNbTourConstruite();
        if (towerType == EnumTower.Tower.ARCHER){
            tower.setStrategy(new HeavyAttackStrategy());
            System.out.println("Archer Tower created");
        }
        else if (towerType == EnumTower.Tower.FIRE_WIZARD){
            tower.setStrategy(new ZoneAttackStrategy());
            System.out.println("Fire Wizard Tower created");
        }
        else if (towerType == EnumTower.Tower.ICE_WIZARD){
            tower.setStrategy(new SlowingAttackStrategy());
            System.out.println("Ice Wizard Tower created");
        }

        return tower;
    }
}
