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
import fr.cpe.factory.WaveFactory;
import fr.cpe.model.Enemy;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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

    private final BallService ballService;
    private final WaveFactory waveFactory;
    private final TowerService towerService;
    private final WaveManager waveManager;
    private final CurrencyService currencyService;

    @Inject
    public GameService(BallService ballService, WaveFactory waveFactory, TowerService towerService, WaveManager waveManager, CurrencyService currencyService) {
        this.ballService = ballService;
        this.waveFactory = waveFactory;
        this.towerService = towerService;
        this.waveManager = waveManager;
        this.currencyService = currencyService;
    }

    /**
     * Initialise les éléments visuels du jeu (appelé une fois au démarrage).
     */
    public void init(Pane gamePane) {
        ballService.init(gamePane);


        List<Enemy> wave = waveFactory.createWave(5);


        Text text = new Text(20, 30, "Projet POO — À vous de jouer !");
        text.setFill(Color.web("#cdd6f4"));
        gamePane.getChildren().add(text);
    }

    /**
     * Met à jour l'état du jeu (appelé à chaque frame).
     */
    public void update(double width, double height) {
        ballService.update(width, height);
    }
}
