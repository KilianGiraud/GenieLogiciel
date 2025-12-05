# Génie Logiciel : Refactoring

Le refactoring a entièrement restructuré la logique du jeu pour la rendre **plus claire, maintenable et extensible**.

## Architecture du joueur

* `Player` devient une **classe abstraite**.
* `Adventurer`, `Archer` et `Dwarf` deviennent des **sous-classes**.
* Chaque classe gère désormais **ses propres capacités** et **sa logique de soin**.
* Toute la logique commune (XP, niveaux, inventaire, KO…) est centralisée dans `Player`.

## Système XP / Niveaux

* Création d’une **map de seuils d’XP** pour éviter les calculs répétés.
* `addXp()` est simplifié et gère :

    * la montée en niveau
    * l’ajout d’un objet aléatoire
    * la mise à jour des capacités du joueur

## Fin de tour (End-of-Turn)

* La logique compliquée de `majFinDeTour()` est remplacée par :

    * `isKO()`
    * `healLogic()` (surchargée dans chaque sous-classe)
    * `capHealth()`

L’ensemble est beaucoup plus lisible et cohérent.

## Inventaire & Objets

* Attribution des objets aléatoires déplacée dans `Player`.
* Suppression de la classe inutile `UpdatePlayer`.

## Tests

* Tous les tests ont été adaptés à l’architecture finale.
* Le projet atteint un niveau élevé de couverture (≈90%).
* Les branches non couvertes concernent uniquement les tableaux de stats des sous-classes (pas critiques).

---
