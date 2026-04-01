package fr.cpe.model;

import java.util.Comparator;
import java.util.List;

public class HeavyAttackStrategy implements IAttackStrategy{

    @Override
    public void attack(List<Enemy> enemyList, int power) {
        var enemy = enemyList.stream()
                //.sorted(e->Comparator.comparing(e.getPv()))
                .findFirst().get();

        enemy.setPv(-(power * 5 ));
    }
}
