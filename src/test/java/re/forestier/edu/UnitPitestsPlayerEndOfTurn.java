package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Dwarf;
// import re.forestier.edu.rpg.Affichage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class UnitPitestsPlayerEndOfTurn {

    /* addXp */

    //Test permettant de vérifier les états de sortie booléens.
    @Test
    @DisplayName("Tests des sorties booléennes")
    void testBoole() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Dwarf p2 = new Dwarf("Maximilien", "John le Magicien", 100, new ArrayList<>());

        Boolean testP1 = p.addXp(58); // Test pour que newLevel soit différent de currentLevel
        Boolean testP2 = p2.addXp(0); // Test pour que newLevel soit égal à currentLevel

        assertThat(testP1, is(true));
        assertThat(testP2, is(false));
    }

    /* majFinDeTour */

    //Test qui va vérifier si l'output du println est bien le bon texte
    @Test
    @DisplayName("Test du print KO")
    void testKO(){
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        p.currentHealthPoints = 0;

        java.io.ByteArrayOutputStream contenu = new java.io.ByteArrayOutputStream();
        java.io.PrintStream original = System.out;
        System.setOut(new java.io.PrintStream(contenu));

        try {
            p.endOfTurn();

            // Vérification que le message de KO est bien affiché

            String output = contenu.toString().trim();
            assertThat(output, is("Le joueur est KO !"));
        } finally {

            // Remettre la sortie systeme originale
            System.setOut(original);
        }
    }

    /* //Test qui va vérifier le changement de currentHealthPoints si le player est un ADVENTURER
    @Test
    @DisplayName("Test heal pour ADVENTURER selon le niveau")
    void testHealAdventurerLevel() {
        player lowLevel = new player("Noob", "Débutant", "ADVENTURER", 100, new ArrayList<>());
        player highLevel = new player("Pro", "Vétéran", "ADVENTURER", 100, new ArrayList<>());

        lowLevel.currentHealthPoints = 40;
        highLevel.currentHealthPoints = 40;

        // Simule retrieveLevel() = 2 pour lowLevel et 5 pour highLevel
        UpdatePlayer.addXp(lowLevel, 10);  

        UpdatePlayer.addXp(highLevel, 10);
        UpdatePlayer.addXp(highLevel, 27);
        UpdatePlayer.addXp(highLevel, 57);   
        UpdatePlayer.addXp(highLevel, 111); // Passage du joueur highLevel au niveau 5

        UpdatePlayer.majFinDeTour(lowLevel);
        UpdatePlayer.majFinDeTour(highLevel);

        // lowLevel (<3) : +2 puis -1 = +1
        assertEquals(41, lowLevel.currentHealthPoints, "Low-level adventurer devrait gagner +1 PV");

        // highLevel (>=3) : +2 sans malus
        assertEquals(42, highLevel.currentHealthPoints, "High-level adventurer devrait gagner +2 PV");
    } */

    // Tests qui va vérifier les différents cas de la condition, soit elle est respectée, soit la valeur currentHealthPoints est égale à healthPoints/2 pour tuer la mutation.
    @Test
    @DisplayName("Test condition currentHealthPoints < healthPoints/2")
    void testConditionHealthBelowHalf() {
        Dwarf p = new Dwarf("Leo", "Ranger", 100, new ArrayList<>());

        // Cas où currentHealthPoints est juste en dessous de la moitié
        p.healthPoints = 100;
        p.currentHealthPoints = 49;
        p.endOfTurn();
        assertTrue(p.currentHealthPoints > 49, "Le joueur devrait regagner de la vie quand il est sous la moitié");

        // Cas où currentHealthPoints est exactement à la moitié
        Dwarf p2 = new Dwarf("Max", "Hero", 100, new ArrayList<>());
        p2.healthPoints = 100;
        p2.currentHealthPoints = 50;
        p2.endOfTurn();
        // Il ne devrait pas y avoir de heal ici
        assertEquals(50, p2.currentHealthPoints, "Pas de soin quand on est à la moitié ou plus");
    }

}
