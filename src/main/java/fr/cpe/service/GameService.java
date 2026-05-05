package fr.cpe.service;

// ╔══════════════════════════════════════════════════════════════════════════════╗
// ║                                                                            ║
// ║   ✏️  FICHIER MODIFIABLE — C'est le cœur de votre projet                   ║
// ║                                                                            ║
// ║   Le code actuel est un EXEMPLE (une balle qui rebondit).                  ║
// ║   Remplacez-le entièrement par votre propre logique de jeu.                ║
// ║                                                                            ║
// ║   Gardez juste la structure init() / update() car GameEngine              ║
// ║   les appelle automatiquement.                                             ║
// ║                                                                            ║
// ╚══════════════════════════════════════════════════════════════════════════════╝

import com.google.inject.Inject;
import fr.cpe.factory.EnumTower;
import fr.cpe.factory.WaveFactory;
import fr.cpe.model.Coord;
import fr.cpe.model.Enemy;
import fr.cpe.model.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ui.HUD;


import java.util.List;

/**
 * Service de jeu — gère l'état du jeu et ses éléments visuels.
 *
 * <h2>C'est ici que vous codez votre jeu !</h2>
 *
 * <p>Ce fichier est un <strong>exemple</strong> : une balle qui rebondit.
 * Remplacez tout par votre propre logique.</p>
 *
 * <h2>Méthodes importantes :</h2>
 * <ul>
 *   <li>{@code init(gamePane)} — appelé une fois au démarrage, créez vos Nodes ici</li>
 *   <li>{@code update(width, height)} — appelé ~60x/sec, mettez à jour la logique et les positions ici</li>
 * </ul>
 *
 * <h2>Rendu (Scene Graph) :</h2>
 * <p>Pas besoin de méthode render() ! Vous créez des Nodes JavaFX (Circle, Rectangle,
 * Text, ImageView…) dans {@code init()}, vous les ajoutez au {@code gamePane},
 * et JavaFX les affiche automatiquement. Dans {@code update()}, vous mettez à jour
 * leurs positions.</p>
 *
 * <h2>Clics souris :</h2>
 * <p>Chaque Node gère ses propres clics :</p>
 * <pre>
 *   monCercle.setOnMouseClicked(e -&gt; {
 *       // ce cercle a été cliqué !
 *   });
 * </pre>
 *
 * <h2>Comment ajouter des dépendances :</h2>
 * <p>Ajoutez-les en paramètre du constructeur avec {@code @Inject} :</p>
 * <pre>
 *   @Inject
 *   public GameService(BallService ball, MonAutreService autre) {
 *       this.ball = ball;
 *       this.autre = autre;
 *   }
 * </pre>
 * <p>Guice les injectera automatiquement.</p>
 */
public class GameService {
    private final TowerService towerService;
    private final WaveManager waveManager;
    private final CurrencyService currencyService;
    private final GameConfig config;
    private final WaveFactory waveFactory;

    private final int UI_WIDTH = 200; 
    private final int GAME_WIDTH = 600;
    
    // Le chemin sera sur cette ligne (index de 0 à 14)
    private final int PATH_ROW = 7; 

    private final double CORE_X = 680; 
    private final double CORE_Y = 480;
    private final double CORE_SIZE = 50;

    private EnumTower.Tower selectedType = EnumTower.Tower.ARCHER;

    @Inject
    public GameService(TowerService towerService, WaveManager waveManager, CurrencyService currencyService, GameConfig config, WaveFactory waveFactory) {
        this.towerService = towerService;
        this.waveManager = waveManager;
        this.currencyService = currencyService;
        this.config = config;
        this.waveFactory = waveFactory;
    }

    public void init(Pane gamePane) {
        HUD hud = new HUD(currencyService.getGold());
        currencyService.addObserver(hud);

        gamePane.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();

            if (x > GAME_WIDTH) {
                if (x >= CORE_X && x <= CORE_X + CORE_SIZE && y >= CORE_Y && y <= CORE_Y + CORE_SIZE) {
                    currencyService.addGold(config.GOLD_PER_CLICK);
                } else {
                    handleUiClick(y);
                }
            } 
            else {
                handleGameClick(x, y);
            }
        });
        
        waveManager.spawnNextWave();
    }

    private void handleUiClick(double y) {
        if (y >= 100 && y <= 180) selectedType = EnumTower.Tower.ARCHER;
        else if (y >= 200 && y <= 280) selectedType = EnumTower.Tower.FIRE_WIZARD;
        else if (y >= 300 && y <= 380) selectedType = EnumTower.Tower.ICE_WIZARD;
    }

    private void handleGameClick(double x, double y) {
        int col = (int) (x / config.TILE_SIZE);
        int row = (int) (y / config.TILE_SIZE);

        if (row == PATH_ROW) {
            System.out.println("Impossible de construire sur le chemin !");
            return;
        }

        Coord targetCoord = new Coord(col, row);
        Tower existingTower = findTowerAt(targetCoord);

        if (existingTower != null) {
            if (currencyService.getGold() >= 100) { 
                currencyService.spendGold(100);
                towerService.upgradeTowers(existingTower, "RANGE");
            }
        } 
        else {
            if (currencyService.getGold() >= config.TOWER_BASIC_COST) {
                currencyService.spendGold(config.TOWER_BASIC_COST);
                towerService.addTower(selectedType, targetCoord);
            }
        }
    }

    private Tower findTowerAt(Coord c) {
        for (Tower t : towerService.getTowers()) {
            if (t.position.x() == c.x() && t.position.y() == c.y()) return t;
        }
        return null;
    }

    public void update(double width, double height) {
        waveManager.updateWaves();
        towerService.updateTowers(waveManager.getEnemies());
    }

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600);

        // --- grille map ---
        for (int col = 0; col < GAME_WIDTH / config.TILE_SIZE; col++) {
            for (int row = 0; row < 600 / config.TILE_SIZE; row++) {
                if (row == PATH_ROW) {
                    gc.setFill(Color.web("#d3a36a")); // Beige/Sable pour le chemin
                } else {
                    gc.setFill(Color.web("#2ecc71")); // Vert pour l'herbe
                }
                gc.fillRect(col * config.TILE_SIZE, row * config.TILE_SIZE, config.TILE_SIZE, config.TILE_SIZE);
            }
        }

        // Grille de debug (plus discrète)
        gc.setStroke(Color.web("#000000", 0.1));
        for (int i = 0; i <= GAME_WIDTH; i += config.TILE_SIZE) gc.strokeLine(i, 0, i, 600);
        for (int i = 0; i <= 600; i += config.TILE_SIZE) gc.strokeLine(0, i, GAME_WIDTH, i);

        // Entités
        towerService.drawTowers(gc, config.TILE_SIZE);
        waveManager.drawEnemies(gc, config.TILE_SIZE);

        // --- INTERFACE ---
        gc.setFill(Color.web("#282a36"));
        gc.fillRect(GAME_WIDTH, 0, UI_WIDTH, 600);
        
        gc.setFill(Color.WHITE);
        gc.fillText("SHOP", GAME_WIDTH + 20, 40);
        gc.fillText("Gold: " + currencyService.getGold(), GAME_WIDTH + 20, 70);

        drawUiButton(gc, 100, "ARCHER (" + config.TOWER_BASIC_COST + "G)", selectedType == EnumTower.Tower.ARCHER, Color.VIOLET);
        drawUiButton(gc, 200, "FIRE (" + config.TOWER_BASIC_COST + "G)", selectedType == EnumTower.Tower.FIRE_WIZARD, Color.RED);
        drawUiButton(gc, 300, "ICE (" + config.TOWER_BASIC_COST + "G)", selectedType == EnumTower.Tower.ICE_WIZARD, Color.LIGHTBLUE);

        gc.setFill(Color.GOLD);
        gc.fillOval(CORE_X, CORE_Y, CORE_SIZE, CORE_SIZE);
        gc.setFill(Color.BLACK);
        gc.fillText("CLICK", CORE_X + 8, CORE_Y + 30);
    }

    private void drawUiButton(GraphicsContext gc, int y, String label, boolean selected, Color towerColor) {
        gc.setFill(selected ? Color.web("#6272a4") : Color.web("#44475a"));
        gc.fillRect(GAME_WIDTH + 10, y, UI_WIDTH - 20, 80);
        gc.setFill(towerColor);
        gc.fillRect(GAME_WIDTH + 20, y + 30, 15, 15);
        gc.setFill(Color.WHITE);
        gc.fillText(label, GAME_WIDTH + 45, y + 45);
    }
}