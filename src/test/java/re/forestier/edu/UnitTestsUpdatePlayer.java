package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class UnitTestsUpdatePlayer {
     /* Tests UpdatePlayer */

    // Fonction qui va executer la fonction "UpdatePlayer"
    @Test
    @DisplayName("Test UpdatePlayer")
    void testUpdatePlayer() {
        new UpdatePlayer();
    }

    /* Tests UpdatePlayer / majFinDeTour */


    // Fonction qui va tester la fin de tour avec un joueur sans vie pour vérifier s'il est mort
    @Test
    @DisplayName("Dead Player")
    void testDeadPlayer() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        UpdatePlayer.majFinDeTour(p);
        assertThat(p.currenthealthpoints, is(0));
    }


    // Fonction qui va tester les différentes conditions du la fonction "majFinDeTour" pour les différentes classes et les différents types de vie
    @Test
    @DisplayName("Alive player for all classes with all type of life")
    void testAlivePlayer() {
        List<player> players = List.of(
            new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>()),
            new player("Maximilien", "Olive et Tom", "ARCHER", 100, new ArrayList<>()),
            new player("Hugolin", "Samsam", "DWARF", 100, new ArrayList<>())
        );
    
        // Différents cas de vie : {currenthealthpoints, heanlthpoints}
        int[][] lifeScenarios = {
            {100, 1000},  // Low life
            {1000, 1000}, // Normal life
            {500, 1000},   // Mid life
            {1500, 1000} // Too High Life
        };
    
        for (int[] scenario : lifeScenarios) {
            for (player p : players) {
                p.currenthealthpoints = scenario[0];
                p.healthpoints = scenario[1];
                UpdatePlayer.majFinDeTour(p);
            }
        }
    }
    


    // Fonction qui va tester les conditions d'inventaires pour les classes "DWARF" et "ARCHER" 
    @Test
    @DisplayName("Conditional Inventory for DWARF and ARCHER with low life")
    void testConditionalinventoryPlayer() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "DWARF", 100, new ArrayList<>());
        player p1 = new player("Hugolin", "Samsam", "ARCHER", 100, new ArrayList<>());

        p.currenthealthpoints = 100;
        p.healthpoints = 1000;
        p1.currenthealthpoints = 100;
        p1.healthpoints = 1000;

        p.inventory.add("Holy Elixir");
        p1.inventory.add("Magic Bow");

        UpdatePlayer.majFinDeTour(p);
        UpdatePlayer.majFinDeTour(p1);

        assertThat(p.currenthealthpoints, is(102));
        assertThat(p1.currenthealthpoints, is(112));

    }
}
