# Présentation

Il y a 6 fichiers de tests unitaires présents dans *test/java/re/forestier/edu*, ces fichiers sont **UnitTestsAffichage**, **UnitTestsPlayer** et **UnitTestsUpdatePlayer** puis **UnitPitestsAffichages**, **UnitPitestsPlayer** et **UnitPitestsUpdatePlayer**, chacun de ces fichiers contient les tests correspondants aux classes du même nom, certains sont en lien avec le systeme **JaCoCotestReport**, et les autres avec l'outil **PIT**.

# Tests Univtaires

## JaCoCoTestReport

JaCoCo est un outil servant à voir le taux de couverture des tests (autrement dit voir les lignes du code qui ont été testées.)

### UpdatePlayer

#### majFinDeTour

- "Dead Player" : Test de la condition si le joueur est mort *fonction testDeadPlayer*
- "Alive Player" : Tests des différents scénarios en lien avec les classes et les points de vie *fonction testAlivePlayer*
- "Conditional Inventory" : Tests des conditions en lien avec la présence d'objet dans l'inventaire de certaines classes *fonction testConditionalInventoryPlayer*

#### addXP 

- "Player level" : Test ajout négatif de niveau 

- "Player level 2" : Ajouter beaucoup d'XP pour passer de niveau et vérifier ensuite le **put**

**Ces tests sont effectués dans les fonctions de la partie "player / retrieveLevel"**

#### UpdatePlayer 

- "UpdatePlayer" : Test de la fonction générale "UpdatePlayer" *fonction testUpdatePlayer*

### Player

#### removeMoney 

- "Remove money" : Test de retirer plus d'argent que le joueur n'en possède *fonction testNegativeMoney*

- "Remove money 2" : Retirer moins d'argent que ce que le joueur possède *fonction testPositiveMoney*


#### addMoney 

- "Player money" : Test d'ajout d'argent de valeur négative *fonction testAddNegativeMoney*

- "Player money 2" : Test d'ajout d'argent = 0 *fonction testAddNoMoney*

- "Player money 3" : Test d'ajout normal *fonction testAddMoney*
#### retrieveLevel 

- "Player level 3" : Tester d'arriver au level 2 *fonction testGoToLvl2*
- "Player level 4" : Tester d'arriver au level 3
- "Player level 5" : Tester d'arriver au level 4 et 5 *fonction testGoToLvl4and5*


#### getXP

- "Player XP" : Test de la fonction getXP (affichage aussi) *fonction testXP*

#### player 

- "Player name" : Test basique avec nom du joueur correctement mit *fonction testPlayerName*

- "Player class" : Test de l'affichage des classes et de si la classe est **null** *fonction testClass*

### Affichage

#### afficherJoueur

- "Inventaire" : Remplir l'inventaire pour tester l'affichage *fonction testAddItem*

#### Affichage 

- "Affichage" : Test de la fonction générale "Affichage" *fonction testAffichage*


## PIT

PIT (ou Pitest) est un outil qui va faire *muter* notre code, par exemple `modifier un < en <=` afin de voir si le code réagit correctement.

### UpdatePlayer

#### addXp 

- "Sorties booléennes" : Test des différentes sorties booléennes de la fonction. *fonction testBoole*

#### majFinDeTour

- "Print KO" : Test si le joueur est KO, le message affiché est bien le bon. *fonction testKO*

- "Conditions de vie" : Test la condition principale du currenthealthpoints pour tuer les différentes mutations. *fonction testConditionHealthBelowHalf*


### Player

#### removeMoney

- "Retrait d'argent" : Tests de différentes situations de retrait d'argent pour tuer les mutations *fonction testRemoveMoney*

#### getXp

- "Affichage d'XP" : Test de la fonction getXp pour vérifier si elle renvoie bien la bonne valeur *fonction testGetXp*


### Affichage

#### afficherJoueur

- "Inventaire bien rempli" : Test de la fonction "afficherJoueur" pour voir de si l'item à l'intérieur de l'inventaire est bien écrit *fonction testAddItemPitest*

-----------------------------





