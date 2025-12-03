package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Player;
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
        Player p = new Player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        p.inventory.add("Maximilien");
        Affichage.afficherJoueur(p);
        assertThat(p.inventory, hasItem("Maximilien"));
    }

    // Fonction qui va executer la fonction "Affichage"
    @Test
    @DisplayName("Test Affichage")
    void testAffichage() {
        new Affichage();
    }
}
