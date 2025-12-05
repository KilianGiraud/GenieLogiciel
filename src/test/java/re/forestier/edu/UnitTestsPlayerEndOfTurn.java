package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class UnitTestsPlayerEndOfTurn {
     /* Tests players */

    /* Tests players / endOfTurn */


    // Fonction qui va tester la fin de tour avec un joueur sans vie pour vérifier s'il est mort
    @Test
    @DisplayName("Dead Player")
    void testDeadPlayer() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        p.endOfTurn();
        assertThat(p.getCurrentHealthPoints(), is(0));
    }


    // Fonction qui va tester les différentes conditions du la fonction "majFinDeTour" pour les différentes classes et les différents types de vie
    @Test
    @DisplayName("Alive player for all classes with all type of life")
    void testAlivePlayer() {
        List<Player> players = List.of(
            new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>()),
            new Archer("Maximilien", "Olive et Tom", 100, new ArrayList<>()),
            new Dwarf("Hugolin", "Samsam", 100, new ArrayList<>())
        );
    
        // Différents cas de vie : {currentHealthPoints, heanlthpoints}
        int[][] lifeScenarios = {
            {100, 1000},  // Low life
            {1000, 1000}, // Normal life
            {500, 1000},   // Mid life
            {1500, 1000} // Too High Life
        };
    
        for (int[] scenario : lifeScenarios) {
            for (Player p : players) {
                p.setCurrentHealthPoints(scenario[0]);
                p.setHealthPoints(scenario[1]);
                p.endOfTurn();
            }
        }
    }
    


    // Fonction qui va tester les conditions d'inventaires pour les classes "DWARF" et "ARCHER" 
    @Test
    @DisplayName("Conditional Inventory for DWARF and ARCHER with low life")
    void testConditionalinventoryPlayer() {
        Dwarf p = new Dwarf("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        Archer p1 = new Archer("Hugolin", "Samsam", 100, new ArrayList<>());

        p.setCurrentHealthPoints(100);
        p.setHealthPoints(1000);
        p1.setCurrentHealthPoints(100);
        p1.setHealthPoints(1000);

        p.getInventory().add("Holy Elixir");
        p1.getInventory().add("Magic Bow");

        p.endOfTurn();
        p1.endOfTurn();

        assertThat(p.getCurrentHealthPoints(), is(102));
        assertThat(p1.getCurrentHealthPoints(), is(112));

    }
}
