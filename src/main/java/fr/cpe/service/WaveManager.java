package fr.cpe.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.cpe.factory.WaveFactory;
import fr.cpe.model.Enemy;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class WaveManager {
    private List<Enemy> enemies = new ArrayList<>();
    private final WaveFactory waveFactory;
    private int currentLevel = 1;

    @Inject
    public WaveManager(WaveFactory waveFactory) {
        this.waveFactory = waveFactory;
    }

    public void spawnNextWave() {
        this.enemies.addAll(waveFactory.createWave(currentLevel++));
    }

    public void updateWaves() {
        enemies.removeIf(Enemy::isDead);

        for (Enemy e : enemies) {
            e.move();
        }
    }

    public void drawEnemies(GraphicsContext gc, int tileSize) {
        for (Enemy e : enemies) {
            e.draw(gc, tileSize);
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}