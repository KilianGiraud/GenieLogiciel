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

    /* Tests Player / retrieveLevel */



    // Ajout d'XP afin d'atteindre le Level 2 et tests des capacités ajoutées
    @Test
    @DisplayName("Access to level 2")
    void testGoToLvl2() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        final Item[] objectList = {
                new Item("Lookout Ring", "Prevents surprise attacks", 1, 50),
                new Item("Scroll of Stupidity", "INT -2 when applied to an enemy", 1, 10),
                new Item("Draupnir", "Increases XP gained by 100%", 2, 80),
                new Item("Magic Charm", "Magic +10 for 5 rounds", 1, 40),
                new Item("Rune Staff of Curse", "May burn enemies or yourself", 5, 200),
                new Item("Combat Edge", "Sharp weapon", 3, 100),
                new Item("Holy Elixir", "Recover HP", 1, 30)
        };

        int oldSize = p.getInventory().size();
        Map<String, Integer> oldAbilities = new HashMap<>(p.getAbilities());

        try {
            p.addXp(58);
            p.retrieveLevel();
            
            // Vérifie que la taille a bien augmenté de 1
            assertThat(p.getInventory().size(), is(oldSize + 1));
            
            // Vérifie que le dernier objet ajouté est bien dans la liste des objets possibles
            Item lastAdded = p.getInventory().get(p.getInventory().size() - 1);
            assertTrue(
                    Arrays.stream(objectList)
                            .anyMatch(item -> item.getItemName().equals(lastAdded.getItemName()))
            );


            // Vérifie qu'au moins une ability a changé (ajoutée ou upgradée)
            boolean abilityChanged = p.getAbilities().entrySet().stream().anyMatch(entry ->
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
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Adventurer p2 = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Adventurer p3 = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Adventurer p4 = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Adventurer p5 = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

        p.addXp(5);
        assertThat(p.retrieveLevel(), is(1));

        p2.addXp(10);
        assertThat(p2.retrieveLevel(), is(2));

        p3.addXp(27);
        assertThat(p3.retrieveLevel(), is(3));

        p4.addXp(57);
        assertThat(p4.retrieveLevel(), is(4));

        p5.addXp(111);
        assertThat(p5.retrieveLevel(), is(5));


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

    /* Test Player Player */


    // Test de toutes les classes et du cas où la classe est nulle
    @Test
    @DisplayName("Show Player's class")
    void testClass() {
        Adventurer p1 = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Dwarf p2 = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Archer p3 = new Archer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        assertThat(p1.getAvatarClass(), is("ADVENTURER"));
        assertThat(p2.getAvatarClass(), is("DWARF"));
        assertThat(p3.getAvatarClass(), is("ARCHER"));
    }
}
