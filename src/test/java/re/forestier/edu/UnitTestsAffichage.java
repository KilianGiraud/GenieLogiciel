package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class UnitTestsAffichage {    

    /* Test d'affichage (inventaire) */


    // Ajout d'un Item, affichage du joueur puis vérification de l'item dans l'inventaire
    @Test
    @DisplayName("Item added")
    void testAddItem() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        p.addItem(new Item("Maximilien", "Test item", 1, 0));

        Affichage.afficherJoueur(p);
        assertTrue(
                p.getInventory().stream().anyMatch(item -> item.getItemName().equals("Maximilien"))
        );

    }

    // Fonction qui va executer la fonction "Affichage"
    @Test
    @DisplayName("Test Affichage")
    void testAffichage() {
        new Affichage();
    }
}
