# Genie Logiciel : Refactoring

## Etape 1 : Avoir un code conventionnel

Pour avoir un code **conventionnel**, il faut avant tout supprimer les commentaires inutiles *(comme la recette de cuisine en russe)* ou encore renommer les variables / classes / methodes selon la bonne methode *(CamelCase (Lower ou Upper selon le cas))*

## Etape 2 : Abstract class and Subclasses

Dans un premier temps, il était important de transformer la classe **Player** en classe abstraite et rendre les différents types *(ADVENTURER, ARCHER, DWARF)* en sous-classe pour une meilleure gestion du code

## Etape 3 : Gestion des XP et du level up

La HashMap **abilitiesPerTypeAndLevel()** est horrible, illisible et pas pratique, pour ca on va pouvoir utiliser les sous-classes avec l'utilisation d'un **case switch** pour adapter les abilités en fonction des niveaux.

On adapte ensuite la fonction **addXP()** pour qu'elle concorde avec cette modification.

La fonction **retrieveLevel()** était également inadaptée, il était donc judicieux de séparer le tout en deux : 

- Un HashMap **LEVEL_THRESHOLDS** : Pour ne pas avoir a les recréer à chaque appel
- Une fonction **retrieveLevel()** : Pour établir le niveau du joueur

Cela permettra par la suite d'implémenter bien plus facilement de **nouveaux niveaux**

