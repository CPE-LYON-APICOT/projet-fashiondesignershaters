package fr.cpe.service;

import com.google.inject.Inject;
import fr.cpe.factory.EnumTower;
import fr.cpe.factory.WaveFactory;
import fr.cpe.model.Coord;
import fr.cpe.model.Enemy;
import fr.cpe.model.Tower;
import javafx.scene.canvas.Canvas; // Manquant
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.HUD;
import ui.MenuUI;
import java.util.List;

public class GameService {
    private final TowerService towerService;
    private final WaveManager waveManager;
    private final CurrencyService currencyService;
    private final GameConfig config;
    private final WaveFactory waveFactory;

    private boolean isGameStarted = false;
    private MenuUI mainMenu;
    private GraphicsContext gc; // Stocké pour le render

    private final int UI_WIDTH = 200;
    private final int GAME_WIDTH = 600;
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
        // 1. Création du Canvas et récupération du contexte
        Canvas canvas = new Canvas(800, 600);
        this.gc = canvas.getGraphicsContext2D();
        gamePane.getChildren().add(canvas);

        // 2. Gestion des clics
        canvas.setOnMouseClicked(e -> {
            if (!isGameStarted) return; // Bloque les clics si menu ouvert

            double x = e.getX();
            double y = e.getY();

            if (x > GAME_WIDTH) {
                if (x >= CORE_X && x <= CORE_X + CORE_SIZE && y >= CORE_Y && y <= CORE_Y + CORE_SIZE) {
                    currencyService.addGold(config.GOLD_PER_CLICK);
                } else {
                    handleUiClick(y);
                }
            } else {
                handleGameClick(x, y);
            }
        });

        // 3. Écran d'accueil
        this.mainMenu = new MenuUI(800, 600, () -> {
            this.isGameStarted = true;
            this.mainMenu.getContainer().setVisible(false);
            this.waveManager.spawnNextWave(); // Lance la partie
        });
        gamePane.getChildren().add(mainMenu.getContainer());

        currencyService.addGold(100);
    }

    private void handleUiClick(double y) {
        if (y >= 100 && y <= 180) selectedType = EnumTower.Tower.ARCHER;
        else if (y >= 200 && y <= 280) selectedType = EnumTower.Tower.FIRE_WIZARD;
        else if (y >= 300 && y <= 380) selectedType = EnumTower.Tower.ICE_WIZARD;
    }

    private void handleGameClick(double x, double y) {
        int col = (int) (x / config.TILE_SIZE);
        int row = (int) (y / config.TILE_SIZE);

        if (row == PATH_ROW) return;

        Coord targetCoord = new Coord(col, row);
        Tower existingTower = findTowerAt(targetCoord);

        if (existingTower != null) {
            if (currencyService.spendGold(100)) { // Utilise spendGold directement
                towerService.upgradeTowers(existingTower, "RANGE");
            }
        } else {
            if (currencyService.spendGold(config.TOWER_BASIC_COST)) {
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
        if (!isGameStarted) return; // Arrête la logique si pas démarré

        waveManager.updateWaves();
        towerService.updateTowers(waveManager.getEnemies());

        // APPEL DU RENDU
        render(gc);
    }

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600);

        // --- Dessin de la Map ---
        for (int col = 0; col < GAME_WIDTH / config.TILE_SIZE; col++) {
            for (int row = 0; row < 600 / config.TILE_SIZE; row++) {
                gc.setFill(row == PATH_ROW ? Color.web("#d3a36a") : Color.web("#2ecc71"));
                gc.fillRect(col * config.TILE_SIZE, row * config.TILE_SIZE, config.TILE_SIZE, config.TILE_SIZE);
            }
        }

        // Grille visuelle
        gc.setStroke(Color.web("#000000", 0.1));
        for (int i = 0; i <= GAME_WIDTH; i += config.TILE_SIZE) gc.strokeLine(i, 0, i, 600);
        for (int i = 0; i <= 600; i += config.TILE_SIZE) gc.strokeLine(0, i, GAME_WIDTH, i);

        // Entités
        towerService.drawTowers(gc, config.TILE_SIZE);
        waveManager.drawEnemies(gc, config.TILE_SIZE);

        // --- Interface SHOP ---
        gc.setFill(Color.web("#282a36"));
        gc.fillRect(GAME_WIDTH, 0, UI_WIDTH, 600);

        gc.setFill(Color.WHITE);
        gc.fillText("SHOP", GAME_WIDTH + 20, 40);
        gc.fillText("Gold: " + currencyService.getGold(), GAME_WIDTH + 20, 70);

        drawUiButton(gc, 100, "ARCHER (" + config.TOWER_BASIC_COST + "G)", selectedType == EnumTower.Tower.ARCHER, Color.VIOLET);
        drawUiButton(gc, 200, "FIRE (" + config.TOWER_BASIC_COST + "G)", selectedType == EnumTower.Tower.FIRE_WIZARD, Color.RED);
        drawUiButton(gc, 300, "ICE (" + config.TOWER_BASIC_COST + "G)", selectedType == EnumTower.Tower.ICE_WIZARD, Color.LIGHTBLUE);

        // Noyau (Clicker)
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