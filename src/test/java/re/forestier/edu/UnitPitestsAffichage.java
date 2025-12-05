package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class UnitPitestsAffichage {
    // Ajout d'un Item, affichage du joueur puis vérification de l'item dans l'inventaire
    @Test
    @DisplayName("Item added")
    void testAddItemPitest() {
        Adventurer p = new Adventurer("Kilian", "Denver le dernier Dinosaure", 100, new ArrayList<>());
        p.getInventory().add("Maximilien");
        String affichage = Affichage.afficherJoueur(p);
        assertThat(affichage, containsString("Maximilien"));
    }
}
