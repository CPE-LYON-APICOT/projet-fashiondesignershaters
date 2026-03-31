package fr.cpe.model;

import java.util.Comparator;
import java.util.List;

public class HeavyAttackStrategy implements IAttackStrategy{

    @Override
    public void attack(List<Enemy> enemyList, int power) {
        var ennemy = enemyList.stream()
                //.sorted(e->Comparator.comparing(e.getPv()))
                .findFirst().get();

        ennemy.setPv(-(power ));
    }
}
