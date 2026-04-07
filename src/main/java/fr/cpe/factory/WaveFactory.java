package fr.cpe.factory;

import fr.cpe.model.Boss;
import fr.cpe.model.Enemy;
import fr.cpe.model.Mob;

import java.util.ArrayList;
import java.util.List;

public class WaveFactory {
    public List<Enemy> createWave(int level) {
        List<Enemy> wave = new ArrayList<>();

        // --- CONFIGURATION DES STATS ---

        // Nombre d'ennemis : Croissance linéaire
        int mobCount = (int) (5 + (level * 1.2));
        int bossCount = level / 5;

        // PV : Croissance exponentielle (ex: +15% par niveau)
        int mobHp = (int) (20 * Math.pow(1.15, level - 1));
        int bossHp = (int) (150 * Math.pow(1.2, level - 1));

        // Vitesse : Croissance très lente ou logarithmique
        // On veut éviter que les ennemis deviennent téléporteurs à haut niveau
        float mobSpeed = 2.0f + (level * 0.1f);
        float bossSpeed = 1.0f + (level * 0.05f);

        // --- CRÉATION DES INSTANCES ---

        // Ajout des Mobs
        for (int i = 0; i < mobCount; i++) {
            // Constructeur : new Mob(pointsDeVie, vitesse)
            wave.add(new Mob(mobHp, mobSpeed));
        }

        // Ajout des Boss
        for (int i = 0; i < bossCount; i++) {
            // Constructeur : new Boss(pointsDeVie, vitesse)
            wave.add(new Boss(bossHp, bossSpeed));
        }
        System.out.println("Wave " + level + " created with " + mobCount + " mobs and " + bossCount + " bosses.");
        return wave;
    }
}
