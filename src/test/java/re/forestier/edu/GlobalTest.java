package re.forestier.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.*;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Adventurer player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<>());
        player.addXp(20);
        player.setInventory(new ArrayList<>());

        verify(Affichage.afficherJoueur(player));
    }

    @Test
    void testAffichageMarkdown() {
        Dwarf p = new Dwarf("Kilian", "Denver le dinosaure", 200, new ArrayList<>());

        p.addItem(new Item("Lookout Ring", "Prevents surprise attacks", 1, 50));

        verify(Affichage.afficherJoueurMarkdown(p));
    }

    @Test
    void testAffichageMarkdownEmpty() {
        Dwarf p = new Dwarf("Kilian", "Denver le dinosaure", 200, new ArrayList<>());

        verify(Affichage.afficherJoueurMarkdown(p));
    }
}
