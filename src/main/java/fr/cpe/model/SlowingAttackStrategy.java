package fr.cpe.model;

import java.util.List;

public class SlowingAttackStrategy implements IAttackStrategy {
    @Override
    public void attack(List<Enemy> enemyList, int power) {
        for (Enemy enemy : enemyList){
            enemy.setPv(enemy.getPv()-power);
            enemy.setSpeed(enemy.getSpeed() * 0.5f);
        }

    }
}
