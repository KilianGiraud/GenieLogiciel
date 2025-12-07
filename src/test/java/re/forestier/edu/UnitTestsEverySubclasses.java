package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class UnitTestsEverySubclasses {

    /* Adventurer */

    @Test
    @DisplayName("Go through all level for Adventurer")
    void testLevelAdventurer() {
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

    @Test
    @DisplayName("Testing Adventurer's heal logic")
    void testAdventurerHealLogic() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Adventurer p2 = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

        p.setHealthPoints(200);
        p.setCurrentHealthPoints(99);

        p.endOfTurn();

        assertThat(p.getCurrentHealthPoints(), is(99 + 2 - 1)); // p is under level 3 so he gain +2 and -1 (level under 3)

        p2.setHealthPoints(200);
        p2.setCurrentHealthPoints(99);

        p2.addXp(27);

        p2.endOfTurn();

        assertThat(p2.getCurrentHealthPoints(), is(99 + 2)); // p2 is level 3 so doesn't get -1

    }

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

    /* Archer */

    @Test
    @DisplayName("Go through all level for Archer")
    void testLevelArcher() {
        Archer p = new Archer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Archer p2 = new Archer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Archer p3 = new Archer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Archer p4 = new Archer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Archer p5 = new Archer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

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

    /* Dwarf */

    @Test
    @DisplayName("Go through all level for Dwarf")
    void testLevelDwarf() {
        Dwarf p = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Dwarf p2 = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Dwarf p3 = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Dwarf p4 = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Dwarf p5 = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

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

    /* ARCHER & DWARF */

    @Test
    @DisplayName("Conditional Inventory for DWARF and ARCHER with low life")
    void testConditionalinventoryPlayer() {
        Dwarf p = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Archer p1 = new Archer("Hugolin", "Samsam", 100, new ArrayList<>());

        p.setCurrentHealthPoints(100);
        p.setHealthPoints(1000);
        p1.setCurrentHealthPoints(100);
        p1.setHealthPoints(1000);

        p.addItem(new Item("Holy Elixir", "Recover HP", 1, 30));
        p1.addItem(new Item("Magic Bow", "Magic boost", 2, 80));

        p.endOfTurn();
        p1.endOfTurn();

        assertThat(p.getCurrentHealthPoints(), is(102));
        assertThat(p1.getCurrentHealthPoints(), is(112));

    }

    /* Goblin */

    @Test
    @DisplayName("Go through all level for Goblin")
    void testLevelGoblin() {
        Goblin p = new Goblin("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Goblin p2 = new Goblin("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Goblin p3 = new Goblin("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Goblin p4 = new Goblin("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Goblin p5 = new Goblin("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

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

    @Test
    @DisplayName("testing Goblin's heal logic (none)")
    void testGoblinHealLogic() {
        Goblin p = new Goblin("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());

        p.setHealthPoints(100);
        p.setCurrentHealthPoints(49);
        p.endOfTurn();

        assertThat(p.getCurrentHealthPoints(), is(49)); // Goblin doesn't have heal logic so he's not healing
    }

    /* ALL SUBCLASSES */

    @Test
    @DisplayName("Show Player's class")
    void testClass() {
        Adventurer p1 = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Dwarf p2 = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Archer p3 = new Archer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Goblin p4 = new Goblin("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        assertThat(p1.getAvatarClass(), is("ADVENTURER"));
        assertThat(p2.getAvatarClass(), is("DWARF"));
        assertThat(p3.getAvatarClass(), is("ARCHER"));
        assertThat(p4.getAvatarClass(), is("GOBLIN"));
    }

}
