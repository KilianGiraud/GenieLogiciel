package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class UnitTestsPlayer {
    /* Tests de player / removeMoney */

    //Tentative de retrait d'un montant supérieur à la quantité d'argent du joueur
    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }


    // Tentative classique de retrait d'argent
    @Test
    @DisplayName("Money removed")
    void testPositiveMoney() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(50);
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    /* Tests de player / addMoney */


    // Tentative de d'ajouter une valeur négative d'argent
    @Test
    @DisplayName("Impossible to add negative money")
    void testAddNegativeMoney() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.addMoney(-40);
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }


    // Tentative d'ajout d'argent à 0
    @Test
    @DisplayName("Nothing happened")
    void testAddNoMoney() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.addMoney(0);
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }


    // Tentative classique d'ajout d'argent
    @Test
    @DisplayName("Money added")
    void testAddMoney() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.addMoney(100);
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    /* Tests player / retrieveLevel */


    // Ajout d'XP afin d'atteindre le Level 2
    @Test
    @DisplayName("Access to level 2")
    void testGoToLvl2() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());

        try {
            UpdatePlayer.addXp(p, 58);
            p.retrieveLevel();
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }

    }

    // Ajout d'XP afin d'atteindre le Level 4 et 5
    @Test
    @DisplayName("Access to level 4 and 5")
    void testGoToLvl4and5() {
        player p2 = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());

        try {
            UpdatePlayer.addXp(p2, 120);
            p2.retrieveLevel();
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    /* Test XP */

    // Initiation d'une variable qui réprésente l'XP du joueur puis vérifier sa valeur
    @Test
    @DisplayName("Show User's XP")
    void testXP() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        int xp = p.getXp();
        assertThat(xp, is(0));
    }

    /* Test player player */


    // Test de toutes les classes et du cas où la classe est nulle
    @Test
    @DisplayName("Show Player's class")
    void testClass() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "", 100, new ArrayList<>());
        player p1 = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        player p2 = new player("Kilian", "Denver le dernier Dinosaure", "DWARF", 100, new ArrayList<>());
        player p3 = new player("Kilian", "Denver le dernier Dinosaure", "ARCHER", 100, new ArrayList<>());
        assertThat(p.getAvatarClass(), is(nullValue()));
        assertThat(p1.getAvatarClass(), is("ADVENTURER"));
        assertThat(p2.getAvatarClass(), is("DWARF"));
        assertThat(p3.getAvatarClass(), is("ARCHER"));
    }
}
