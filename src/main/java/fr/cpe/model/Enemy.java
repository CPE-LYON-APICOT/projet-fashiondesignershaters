package fr.cpe.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy {
    private int pv;
    private float speed;
    private double x;
    private double y;

    public Enemy(int pv, float speed) {
        this.pv = pv;
        this.speed = speed;
        this.x = -1.0;
        this.y = 7.0; 
    }

    public void move() {
        this.x += speed;
    }

    public void draw(GraphicsContext gc, int tileSize) {
        // 1. Dessin du corps de l'ennemi (ton code actuel)
        gc.setFill(Color.RED);
        // On centre un peu le carré dans la case
        double enemySize = tileSize * 0.6;
        double offsetX = (tileSize - enemySize) / 2;
        double offsetY = (tileSize - enemySize) / 2;
        
        gc.fillRect(this.getX() * tileSize + offsetX, this.getY() * tileSize + offsetY, enemySize, enemySize);

        // 2. Configuration de la barre de vie
        double barWidth = enemySize;
        double barHeight = 4;
        double xBar = this.getX() * tileSize + offsetX;
        double yBar = this.getY() * tileSize + offsetY - 8; // Placée juste au-dessus

        // Fond de la barre (Noir)
        gc.setFill(Color.BLACK);
        gc.fillRect(xBar, yBar, barWidth, barHeight);

        // Calcul de la proportion de vie
        // Attention : on cast en (double) pour éviter que 15/20 donne 0 en division entière
        double maxHp = 20.0; 
        double healthRatio = (double) this.getPv() / maxHp;

        // On s'assure que la barre ne dépasse pas (entre 0 et 1)
        healthRatio = Math.max(0, Math.min(1, healthRatio));

        // Couleur de la barre : Vert si tout va bien, Rouge si presque mort[cite: 1]
        if (healthRatio > 0.5) {
            gc.setFill(Color.GREEN);
        } else {
            gc.setFill(Color.ORANGE);
        }

        // Dessin de la partie remplie[cite: 1]
        gc.fillRect(xBar, yBar, barWidth * healthRatio, barHeight);
    }

    public boolean isDead() {
        return pv <= 0;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    
}