package fr.cpe;

// ╔══════════════════════════════════════════════════════════════════════════════╗
// ║                                                                            ║
// ║   ✏️  FICHIER MODIFIABLE — C'est ici que vous configurez Guice             ║
// ║                                                                            ║
// ║   Quand vous créez une interface + une implémentation, déclarez            ║
// ║   le binding ici pour que Guice sache quoi injecter.                       ║
// ║                                                                            ║
// ╚══════════════════════════════════════════════════════════════════════════════╝

import com.google.inject.AbstractModule;

import fr.cpe.model.IAttackStrategy;
import fr.cpe.model.SlowingAttackStrategy;

/**
 * Module Guice — c'est ici que vous déclarez vos bindings (interface → implémentation).
 *
 * <h2>Quand ajouter un binding ?</h2>
 * <p>Dès que vous utilisez une <strong>interface</strong> comme type de dépendance.
 * Guice ne peut pas deviner quelle implémentation choisir tout seul.</p>
 *
 * <h2>Exemple concret :</h2>
 * <pre>
 *   // Vous avez créé :
 *   //   - interface CollisionStrategy { ... }
 *   //   - class SimpleCollision implements CollisionStrategy { ... }
 *   //
 *   // Dans configure(), ajoutez :
 *   bind(CollisionStrategy.class).to(SimpleCollision.class);
 *
 *   // Maintenant, partout où Guice voit @Inject CollisionStrategy,
 *   // il fournira une instance de SimpleCollision.
 * </pre>
 *
 * <h2>Classes concrètes :</h2>
 * <p>Si votre dépendance est une classe concrète (pas une interface),
 * Guice sait l'instancier tout seul — pas besoin de binding.</p>
 */
public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        // Pas de binding pour l'instant : Guice sait instancier les classes concrètes
        // tout seul (GameEngine, GameService) grâce à @Inject.
        //
        // Quand vous introduirez des interfaces, ajoutez vos bindings ici.
        bind(IAttackStrategy.class).to(SlowingAttackStrategy.class);
    }
}
