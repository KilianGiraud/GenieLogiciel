package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.*;
import re.forestier.edu.rpg.Affichage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UnitTestsPlayer {
    /* Tests de Player / removeMoney */

    //Tentative de retrait d'un montant supérieur à la quantité d'argent du joueur
    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
            assertThat(p.getMoney(), is(-100));
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }


    // Tentative classique de retrait d'argent
    @Test
    @DisplayName("Money removed")
    void testPositiveMoney() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

        try {
            p.removeMoney(50);
            assertThat(p.getMoney(), is(50));
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    /* Tests de Player / addMoney */


    // Tentative de d'ajouter une valeur négative d'argent
    @Test
    @DisplayName("Impossible to add negative money")
    void testAddNegativeMoney() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

        try {
            p.addMoney(-40);
            assertThat(p.getMoney(), is(60));
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }


    // Tentative d'ajout d'argent à 0
    @Test
    @DisplayName("Nothing happened")
    void testAddNoMoney() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

        try {
            p.addMoney(0);
            assertThat(p.getMoney(), is(100));
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }


    // Tentative classique d'ajout d'argent
    @Test
    @DisplayName("Money added")
    void testAddMoney() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

        try {
            p.addMoney(100);
            assertThat(p.getMoney(), is(200));
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    /* Test XP */

    // Initiation d'une variable qui réprésente l'XP du joueur puis vérifier sa valeur
    @Test
    @DisplayName("Show User's XP")
    void testXP() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        int xp = p.getXp();
        assertThat(xp, is(0));
    }

}
