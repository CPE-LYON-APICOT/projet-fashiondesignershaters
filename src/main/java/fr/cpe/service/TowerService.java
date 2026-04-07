package fr.cpe.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.cpe.factory.EnumTower;
import fr.cpe.factory.TowerFactory;
import fr.cpe.model.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class TowerService {
    private final List<Tower> towers = new ArrayList<>();
    private final TowerFactory towerFactory;

    @Inject
    public TowerService(TowerFactory towerFactory) {
        this.towerFactory = towerFactory;
    }

    public void addTower(EnumTower.Tower towerType, Coord c) {
        Tower tower = towerFactory.createTower(towerType, c);
        towers.add(tower);
    }

    public void upgradeTower(Tower tower, String upgradeType) {
        int index = towers.indexOf(tower);
        if (index != -1) {
            Tower upgradedTower = tower;

            if (upgradeType.equals("RANGE")) {
                upgradedTower = new RangeDecorator(tower);
            } else if (upgradeType.equals("ICE")) {
                upgradedTower = new IceDecorator(tower);
            }

            towers.set(index, upgradedTower);
        }
    }

    public void updateTowers() {
        for (Tower tower : towers) {
            tower.update();
        }
    }

    public void drawTowers(GraphicsContext gc) {
        for (Tower tower : towers) {
            tower.draw(gc);
        }
    }

    public List<Tower> getTowers() {
        return towers;
    }
}