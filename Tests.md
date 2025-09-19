# Différents tests

## Tests unitaires

### Main / main(String[])

### Main / Main()

- "Main" : Executer la fonction Main

### UpdatePlayer / majFinDeTour

- "maj" : Executer la fonction
- "Mort" : Vérifier si cela fonctionne quand le joueur est mort (0 PV)
- "Le cimetière de if" : Vérifier toutes les clauses "if / else / elif" (y'en a trop pour tout énumérer)

### UpdatePlayer / addXP *assez de tests*

- "Player level" : Test ajout négatif de niveau 

- "Player level 2" : Ajouter beaucoup d'XP pour passer de niveau et vérifier ensuite le **put**

### player / removeMoney *assez de tests*

- "Remove money" : Test de retirer plus d'argent que le joueur n'en possède *fonction testNegativeMoney*

- "Remove money 2" : Retirer moins d'argent que ce que le joueur possède *fonction testPositiveMoney*


### player / addMoney *assez de tests*

- "Player money" : Test d'ajout d'argent de valeur négative *fonction testAddNegativeMoney*

- "Player money 2" : Test d'ajout d'argent = 0 *fonction testAddNoMoney*

- "Player money 3" : Test d'ajout normal
### player / retrieveLevel *assez de tests*

- "Player level 3" : Tester d'arriver au level 2 *fonction testGoToLvl2*
- "Player level 4" : Tester d'arriver au level 3
- "Player level 5" : Tester d'arriver au level 4 et 5 *fonction testGoToLvl4and5*


### player / getXP *assez de tests*

- "Player XP" : Test de la fonction getXP (affichage aussi) *fonction testXP*

### player / player *assez de tests*

- "Player name" : Test basique avec nom du joueur correctement mit *fonction testPlayerName*

- "Player class" : Test de l'affichage des classes et de si la classe est **null** *fonction testClass*


### Affichage / afficherJoueur *assez de tests*

- "Inventaire" : Remplir l'inventaire pour tester l'affichage *fonction testAddItem*

- "Affichage" : Test de la fonction générale "Affichage" *fonction testAffichage*

-----------------------------





