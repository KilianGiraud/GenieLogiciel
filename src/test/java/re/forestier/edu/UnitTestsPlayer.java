package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UnitTestsPlayer {
    /* Tests de player / removeMoney */

    //Tentative de retrait d'un montant supérieur à la quantité d'argent du joueur
    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
            assertThat(p.money, is(-100));
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
            assertThat(p.money, is(50));
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
            assertThat(p.money, is(60));
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
            assertThat(p.money, is(100));
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
            assertThat(p.money, is(200));
        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }
    }

    /* Tests player / retrieveLevel */



    // Ajout d'XP afin d'atteindre le Level 2 et tests des capacités ajoutées
    @Test
    @DisplayName("Access to level 2")
    void testGoToLvl2() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        final String[] objectList = {
        "Lookout Ring : Prevents surprise attacks",
        "Scroll of Stupidity : INT-2 when applied to an enemy",
        "Draupnir : Increases XP gained by 100%",
        "Magic Charm : Magic +10 for 5 rounds",
        "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?",
        "Combat Edge : Well, that's an edge",
        "Holy Elixir : Recover your HP"
        };

        int oldSize = p.inventory.size();
        Map<String, Integer> oldAbilities = new HashMap<>(p.abilities);

        try {
            UpdatePlayer.addXp(p, 58);
            p.retrieveLevel();
            
            // Vérifie que la taille a bien augmenté de 1
            assertThat(p.inventory.size(), is(oldSize + 1));
            
            // Vérifie que le dernier objet ajouté est bien dans la liste des objets possibles
            String lastAdded = p.inventory.get(p.inventory.size() - 1);
            assertThat(Arrays.asList(objectList), hasItem(lastAdded));

            // Vérifie qu'au moins une ability a changé (ajoutée ou upgradée)
            boolean abilityChanged = p.abilities.entrySet().stream().anyMatch(entry ->
                !oldAbilities.containsKey(entry.getKey()) || // nouvelle clé
                !oldAbilities.get(entry.getKey()).equals(entry.getValue()) // valeur upgradée
            );

            assertThat("At least one ability should be added or upgraded", abilityChanged, is(true));

        } catch (Exception e) {
            assertThat(e, is(instanceOf(IllegalArgumentException.class)));
        }

    }

    // Ajout d'XP afin d'atteindre les différents niveaux
    @Test
    @DisplayName("Access to differents levels")
    void testGoToDifferentLvl() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        player p2 = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        player p3 = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        player p4 = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        player p5 = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());

        UpdatePlayer.addXp(p, 5);
        assertThat(p.retrieveLevel(), is(1));

        UpdatePlayer.addXp(p2, 10);
        assertThat(p2.retrieveLevel(), is(2));

        UpdatePlayer.addXp(p3, 27);
        assertThat(p3.retrieveLevel(), is(3));

        UpdatePlayer.addXp(p4, 57);
        assertThat(p4.retrieveLevel(), is(4));

        UpdatePlayer.addXp(p5, 111);
        assertThat(p5.retrieveLevel(), is(5));


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
