# Fiche rendu projet — NKOK Defense

> Ce document est un bilan destiné au client. Présentez ce qui a été livré, ce qui fonctionne, et tournez habilement ce qui manque. Pas de jargon technique — on parle de fonctionnalités et de valeur perçue.

## Rappel du projet

<!-- Reprenez brièvement le pitch initial. Qu'aviez-vous promis ? -->
NKOK Defense est un jeu hybride fusionnant l'intensité stratégique d'un Tower Defense et la satisfaction d'un "Clicker". L'objectif était de proposer un cycle de jeu complet où le joueur gère à la fois sa production d'or manuelle et son arsenal de défense pour protéger un chemin contre des vagues d'ennemis.

## Ce qui a été livré

<!-- Présentez les fonctionnalités livrées. Captures d'écran / GIFs animés bienvenus. -->
<!-- Placez vos images dans docs/images/ et référencez-les avec : ![description](images/nom-du-fichier.png) -->

### Fonctionnalité 1 — Le Noyau de Production (Clicker)
Intégré directement dans l'interface de commande à droite, un noyau doré permet au joueur de générer manuellement sa richesse. Chaque clic produit une pièce d'or, rendant le joueur acteur de son propre développement économique.

### Fonctionnalité 2 — Arsenal de Défense Varié
Le joueur peut déployer trois types d'unités spécialisées pour couvrir le terrain :
*   **Archer (Violet)** : Unité de précision infligeant des dégâts importants sur une cible unique.
*   **Magicien de Feu (Rouge)** : Maître des flammes capable d'infliger des dégâts de zone pour gérer les groupes d'ennemis.
*   **Magicien de Glace (Bleu clair)** : Unité de soutien stratégique qui ralentit la vitesse de progression des envahisseurs.

### Fonctionnalité 3 — Environnement et Level Design
Le terrain de jeu est divisé en deux zones distinctes :
*   **Le Chemin (Beige)** : La route empruntée par les ennemis. Pour des raisons évidentes, aucune tour ne peut être construite directement sur cette voie.
*   **Les Plaines (Vert)** : Les zones de construction libres où le joueur doit optimiser le placement de son arsenal.

### Fonctionnalité 4 — Gestion de Vagues 
Le jeu gère automatiquement l'apparition d'ennemis en file indienne. 
## Ce qui n'a pas été livré (et pourquoi)

<!-- Expliquez ce qui manque. Soyez malin : présentez les manques comme des opportunités futures, pas comme des échecs. -->

### Représentation graphique des projectiles
Bien que les dégâts soient calculés et appliqués en temps réel dès qu'un ennemi entre à portée de tir, les trajectoires visuelles (flèches ou boules de feu) ne sont pas dessinées. Cela permet actuellement de garantir une bonne fluidité de jeu, mais une amélioration visuelle optimisée pourrait rendre le jeu plus attrayant.

### Système de points de vie de la base
Pour cette version de lancement, nous avons opté pour un mode "Sandbox" où les ennemis traversent la carte sans infliger de défaite au joueur. Cela permet une découverte sans frustration de toutes les mécaniques de jeu avant l'introduction d'un mode "Survie" plus punitif.

## Perspectives

<!-- Quelles évolutions proposez-vous pour la suite ? -->

### Court terme
*   **Plusieurs vagues d'ennemis + niveaux** : Rajout de plusieurs vagues d'ennemis, de niveaux différents (ce qui implique un système de niveaux et de défaite).
*   **Effets visuels de combat** : Ajout de lignes de tir colorées et d'effets de particules lors des impacts pour un rendu plus dynamique.
*   **Barres de vie des ennemis** : Affichage d'indicateurs de santé au-dessus des monstres pour que le joueur puisse mieux évaluer l'efficacité de son placement.

### Moyen terme
*   **Automatisation économique** : Introduction d'un système de revenus passifs (génération d'or automatique) pour compléter le système de clics manuels.
*   **Multiplicateurs de score** : Mise en place d'un système de récompenses selon le nombre de tours construites et d'ennemis vaincus.

### Plus loin
*   **Diversité des ennemis** : Création de nouveaux ennemis, qui pourraient par exemple attaquer les tours, sauter plusieurs cases etc.
*   **Biomes et Terrains variés** : Création de nouvelles cartes avec des chemins sinueux ou des obstacles naturels pour plus de diversité.
*   **Arbre de technologies** : Un menu global pour améliorer la puissance du Noyau ou débloquer des types de magiciens/tours plus puissants/es.