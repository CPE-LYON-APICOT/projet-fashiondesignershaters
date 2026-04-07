package fr.cpe.model;

import java.util.List;

public interface IAttackStrategy {

    public void attack(List<Enemy> enemyList, int power);

}
