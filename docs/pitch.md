# Pitch du projet — NKOK Defense

## Présentation générale

**NKOK Defense** est un jeu hybride fusionnant l'intensité stratégique d'un *Tower Defense* et la satisfaction immédiate d'un *Incremental Clicker*. 

Le joueur doit jongler entre deux rôles : **le banquier et le général**. Chaque clic génère la richesse nécessaire pour bâtir des défenses face à des vagues d'ennemis. L'équilibre est fragile : trop d'économie vous laisse vulnérable, trop de défense vous ralentit financièrement.

---

## Fonctionnalités et Architecture

### 1. Le Noyau de Production (Système Clicker)
Le joueur clique sur le "Noyau" pour générer de l'argent. 
* **Valeur ajoutée** : Rend le joueur actif et indispensable.
* **Design Pattern : Observer**. Le "Noyau" prévient l'interface et la boutique dès que le solde change pour mettre à jour l'affichage en temps réel.

### 2. L'Arsenal de Défense (Types de Tours)
Achat de différentes tours (Archer, Feu, Glace) avec des statistiques uniques.
* **Valeur ajoutée** : Permet de varier les stratégies selon les ennemis.
* **Design Pattern : Factory**. Une fabrique de tours centralise la création des différentes unités sans que le moteur de jeu n'ait à connaître les détails de chaque classe.

### 3. Arbre d'Optimisation (Améliorations Économiques)
Investir pour que chaque clic rapporte plus d'argent ou pour automatiser une partie des revenus.
* **Valeur ajoutée** : Offre une progression exponentielle typique des jeux "Clicker".
* **Design Pattern : Strategy**. Le calcul du gain par clic peut changer dynamiquement selon les bonus actifs (multiplicateurs, bonus temporaires).

### 4. Évolutions de Spécialisation (Système d'Upgrade)
Une fois un certain palier atteint (temps ou or), une tour peut évoluer. Par exemple, une **Tour de Glace** peut être améliorée pour :
- **Option A (Super Slow)** : Ralentit beaucoup plus fort un seul ennemi.
- **Option B (Zone de Froid)** : Ralentit un groupe d'ennemis plus large.
* **Valeur ajoutée** : Donne de la profondeur tactique et un sentiment de puissance.
* **Design Pattern : Decorator**. On ajoute dynamiquement des "couches" de capacités à une tour existante sans modifier son code d'origine.

### 5. Feedback Visuel et "Dopamine Juice"
Chaque action (clic, tir, mort d'un ennemi) déclenche des effets visuels : chiffres qui flottent, secousses d'écran, particules.
* **Valeur ajoutée** : Rend le jeu extrêmement satisfaisant et addictif.
* **Design Pattern : Observer**. Les systèmes de particules "écoutent" les événements du jeu pour se déclencher au bon moment.

### 6. Bestiaire d'Envahisseurs (IA Ennemis)
Des vagues d'ennemis avec des comportements variés (certains foncent, d'autres esquivent).
* **Valeur ajoutée** : Force le joueur à adapter son placement de tours.
* **Design Pattern : Strategy**. Chaque type d'ennemi possède sa propre stratégie de déplacement et de résistance.

### 7. Interface de Commandement (UI & HUD)
Affichage des PV de la base, de l'argent et du score.
* **Valeur ajoutée** : Permet une lecture claire de la situation de crise.
* **Design Pattern : Singleton**. Un gestionnaire de configuration (GameConfig) unique pour s'assurer que toutes les tours et l'interface lisent les mêmes règles de jeu.

---

## Pourquoi NKOK Defense ?

NKOK Defense transforme la gestion de budget en une bataille épique. C'est un projet qui valorise la réflexion à long terme tout en récompensant l'action immédiate. Son architecture modulaire permet d'ajouter facilement de nouveaux types de tours ou d'ennemis, garantissant une grande évolutivité.