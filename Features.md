# Génie Logiciel : Ajout de fonctionnalités

## Adventurer au level 2

**Ce problème a été reglé lors du refactoring, le passage au level 2 se fait naturellement désormais**

## Classe "Goblin"

Après un bon **refactoring**, ajouter la classe "Goblin" n'était pas compliqué, il suffisait de copier le patterne des autres classes et adapter les **capacités / logique de soin**

## Gestion des objets

Les objets étant de base de simple **châines de caractères**, il est utile de les transformer en **classe** afin d'en améliorer la gestion.

Une logique de poids est également ajoutée faisant en sorte qu'un joueur ne puisse pas avoir trop d'objet dans son inventaire *(la capacité maximale dépend de la classe)*

Un item a désormais plusieurs informations le concernant :
* Son nom
* Sa description
* Son poids
* Son coût

Une classe **Item** a donc été créée avec des méthodes associées :
* Les **getters**
* Un formatage en **chaîne de caractère** pour l'affichage

Mais également des méthodes dans **Player** :
* **addItem** : Pour ajouter un item dans son inventaire.
* **buyItem** : Pour acheter un item en utilisant la monnaie du jeu.
* **sellItem** : Pour vendre un item en echange d'argent.
* **removeItem** : Pour simplement supprimer l'item de son inventaire sans gagner d'argent (similaire aux drops classiques dans les jeux).

## Affichage en Markdown

Afin de réaliser cet affichage en **Markdown**, j'ai d'abord codé le test avec le fichier **approved.txt** pour respecter le principe de **TDD**

Il y a 2 tests (et donc 2 fichiers approved.txt) qui ont été néccessaires :

* **Affichage avec inventaire** : Un affichage classique avec un joueur possédant un ou plusieurs objets dans son inventaire
* **Affichage sans inventaire** : Un affichage similaire mais cette fois ci en ne mettant aucun objet dans l'inventaire du joueur.

## Tests

Des tests ont été rajoutés notamment en de l'implémentation du systeme d'**Item** et de la classe **"Goblin"** :

* Goblin :
  * Tests classiques de passage de niveau
  * Test de la logique de soin
* Item :
  * Tests des différentes fonctions associées :
    * **addItem**
    * **removeItem**
    * **buyItem**
    * **sellItem**

---
