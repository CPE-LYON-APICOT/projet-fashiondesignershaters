package fr.cpe.model;

import java.util.Comparator;
import java.util.List;

public class HeavyAttackStrategy implements IAttackStrategy{

    @Override
    public void attack(List<Enemy> enemyList, int power) {
        if (!enemyList.isEmpty()) {
            Enemy target = enemyList.get(0); 
            target.setPv(target.getPv() - (power * 5)); 
        }
    }
}
