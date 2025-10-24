package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class UnitPitestPlayer {
    /* removeMoney */

    // Test des différents cas lorsque l'on retire de l'argent
    @Test
    @DisplayName("Test removeMoney : comportement aux limites et exceptions")
    void testRemoveMoney() {
        player p = new player("Kilian", "Rogue Trader", "ADVENTURER", 100, new ArrayList<>());
        
        // Simule un joueur avec 100 pièces
        p.money = 100;

        // Cas normal : il retire moins que ce qu’il a
        assertDoesNotThrow(() -> p.removeMoney(50),
            "Ne doit pas lever d'exception si le joueur a assez d'argent");
        assertEquals(50, p.money, "L'argent doit être décrémenté correctement");

        // Cas limite : il retire exactement tout son argent
        p.money = 100;
        assertDoesNotThrow(() -> p.removeMoney(100),
            "Ne doit pas lever d'exception si le joueur tombe à zéro");
        assertEquals(0, p.money, "L'argent doit tomber à zéro exactement");

        // Cas d’erreur : il essaie de retirer plus que ce qu’il a
        p.money = 100;
        Exception ex = assertThrows(IllegalArgumentException.class, () -> p.removeMoney(101),
            "Doit lever une exception si le joueur devient négatif");
        assertEquals("Player can't have a negative money!", ex.getMessage(),
            "Message d'erreur incorrect");
    }

    /* getXp */


    //Test qui vérifie si la fonction getXp renvoie la valeur correcte
    @Test
    @DisplayName("Test getXp : doit retourner la valeur correcte")
    void testGetXp() {
        player p = new player("Kilian", "Mage de Test", "ADVENTURER", 100, new ArrayList<>());

        // On fixe l'XP manuellement
        UpdatePlayer.addXp(p, 42);

        // On vérifie que getXp() retourne bien cette valeur
        assertEquals(42, p.getXp(), "getXp() doit retourner la valeur réelle du champ xp");

        // Bonus : on modifie l'XP et on revérifie pour s’assurer que ce n’est pas toujours 0
        UpdatePlayer.addXp(p, 958);;
        assertEquals(1000, p.getXp(), "getXp() doit refléter la nouvelle valeur de xp");
    }


}
