package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class UnitPitestsAffichage {
    // Ajout d'un Item, affichage du joueur puis vérification de l'item dans l'inventaire
    @Test
    @DisplayName("Item added")
    void testAddItem() {
        player p = new player("Kilian", "Denver le dernier Dinosaure", "ADVENTURER", 100, new ArrayList<>());
        p.inventory.add("Maximilien");
        String affichage = Affichage.afficherJoueur(p);
        assertThat(affichage, containsString("Maximilien"));
    }
}
