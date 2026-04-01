package fr.cpe.service;

import fr.cpe.model.*;

public class TowerService {
    public void toto(){
        Tower tower = new Tower();
        tower = new IceDecorator(tower);
        tower = new RangeDecorator(tower);
        if(tower instanceof TowerDecorator td)
            tower = td.baseTower;


        tower.setStrategy(new HeavyAttackStrategy());
        tower.setStrategy(new SlowingAttackStrategy());
    }
}
