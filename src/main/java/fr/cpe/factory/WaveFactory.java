package fr.cpe.factory;

import fr.cpe.model.Boss;
import fr.cpe.model.Enemy;
import fr.cpe.model.Mob;
import java.util.ArrayList;
import java.util.List;

public class WaveFactory {
    public List<Enemy> createWave(int level) {
        List<Enemy> wave = new ArrayList<>();

        int mobCount = 5 + level;
        int mobHp = (int) (20 * Math.pow(1.15, level - 1));

        // --- LA CORRECTION EST ICI ---
        // On passe de 2.0f à 0.03f (environ 2 cases par seconde)
        float mobSpeed = 0.02f + (level * 0.005f); 

        for (int i = 0; i < mobCount; i++) {
            // On espace un peu les ennemis en changeant leur X de départ
            Mob m = new Mob(mobHp, mobSpeed);
            m.setX(-i * 1.5); // Ils arrivent les uns après les autres
            wave.add(m);
        }

        System.out.println("Vague " + level + " générée : " + mobCount + " ennemis.");
        return wave;
    }
}