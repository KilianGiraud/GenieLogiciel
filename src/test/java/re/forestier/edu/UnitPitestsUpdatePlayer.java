package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;


public class UnitPitestsUpdatePlayer {

    /* addXp */

    //Test permettant de vérifier les états de sortie booléens.
    @Test
    @DisplayName("Tests des sorties booléennes")
    void testBoole() {
        Player p = new Player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        Player p2 = new Player("Maximilien", "John le Magicien", "DWARF", 100, new ArrayList<>());

        Boolean testP1 = UpdatePlayer.addXp(p, 58); // Test pour que newLevel soit différent de currentLevel
        Boolean testP2 = UpdatePlayer.addXp(p2, 0); // Test pour que newLevel soit égal à currentLevel

        assertThat(testP1, is(true));
        assertThat(testP2, is(false));
    }

    /* majFinDeTour */

    //Test qui va vérifier si l'output du println est bien le bon texte
    @Test
    @DisplayName("Test du print KO")
    void testKO(){
        Player p = new Player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        p.currenthealthpoints = 0;

        java.io.ByteArrayOutputStream contenu = new java.io.ByteArrayOutputStream();
        java.io.PrintStream original = System.out;
        System.setOut(new java.io.PrintStream(contenu));

        try {
            UpdatePlayer.majFinDeTour(p);

            // Vérification que le message de KO est bien affiché

            String output = contenu.toString().trim();
            assertThat(output, is("Le joueur est KO !"));
        } finally {

            // Remettre la sortie systeme originale
            System.setOut(original);
        }
    }

    /* //Test qui va vérifier le changement de currenthealthpoints si le player est un ADVENTURER
    @Test
    @DisplayName("Test heal pour ADVENTURER selon le niveau")
    void testHealAdventurerLevel() {
        player lowLevel = new player("Noob", "Débutant", "ADVENTURER", 100, new ArrayList<>());
        player highLevel = new player("Pro", "Vétéran", "ADVENTURER", 100, new ArrayList<>());

        lowLevel.currenthealthpoints = 40;
        highLevel.currenthealthpoints = 40;

        // Simule retrieveLevel() = 2 pour lowLevel et 5 pour highLevel
        UpdatePlayer.addXp(lowLevel, 10);  

        UpdatePlayer.addXp(highLevel, 10);
        UpdatePlayer.addXp(highLevel, 27);
        UpdatePlayer.addXp(highLevel, 57);   
        UpdatePlayer.addXp(highLevel, 111); // Passage du joueur highLevel au niveau 5

        UpdatePlayer.majFinDeTour(lowLevel);
        UpdatePlayer.majFinDeTour(highLevel);

        // lowLevel (<3) : +2 puis -1 = +1
        assertEquals(41, lowLevel.currenthealthpoints, "Low-level adventurer devrait gagner +1 PV");

        // highLevel (>=3) : +2 sans malus
        assertEquals(42, highLevel.currenthealthpoints, "High-level adventurer devrait gagner +2 PV");
    } */

    // Tests qui va vérifier les différents cas de la condition, soit elle est respectée, soit la valeur currenthealthpoints est égale à healthpoints/2 pour tuer la mutation.
    @Test
    @DisplayName("Test condition currenthealthpoints < healthpoints/2")
    void testConditionHealthBelowHalf() {
        Player p = new Player("Leo", "Ranger", "DWARF", 100, new ArrayList<>());

        // Cas où currenthealthpoints est juste en dessous de la moitié
        p.healthpoints = 100;
        p.currenthealthpoints = 49;
        UpdatePlayer.majFinDeTour(p);
        assertTrue(p.currenthealthpoints > 49, "Le joueur devrait regagner de la vie quand il est sous la moitié");

        // Cas où currenthealthpoints est exactement à la moitié
        Player p2 = new Player("Max", "Hero", "DWARF", 100, new ArrayList<>());
        p2.healthpoints = 100;
        p2.currenthealthpoints = 50;
        UpdatePlayer.majFinDeTour(p2);
        // Il ne devrait pas y avoir de heal ici
        assertEquals(50, p2.currenthealthpoints, "Pas de soin quand on est à la moitié ou plus");
    }

}
