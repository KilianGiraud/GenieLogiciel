package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.*;
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
        p.setHealthPoints(0);
        p.endOfTurn();
        assertThat(p.getCurrentHealthPoints(), is(0));
    }


    // Fonction qui va tester les différentes conditions du la fonction "endOfTurn" pour les différentes classes et les différents types de vie
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
}
