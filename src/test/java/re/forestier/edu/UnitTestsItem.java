package re.forestier.edu;

import org.junit.jupiter.api.*;
import re.forestier.edu.rpg.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class UnitTestsItem {

    /* buyItem*/

    @Test
    @DisplayName("Normal buy")
    void testBuyItem() {
        Adventurer p = new Adventurer("Kilian", "Denver", 200, new ArrayList<>());

        Item ring = new Item("Lookout Ring", "Prevents surprise attacks", 1, 50);

        boolean success = p.buyItem(ring);

        assertThat(success, is(true)); // Purchase need to succeed

        assertThat(p.getInventory(), hasItem(ring)); // Item ring need to be in the inventory

        assertThat(p.getMoney(), is(200 - 50)); // Money need to be removed
    }

    @Test
    @DisplayName("Not enough money to buy")
    void testBuyItemWithoutMoney() {
        Adventurer p = new Adventurer("Kilian", "Denver", 40, new ArrayList<>());

        Item ring = new Item("Lookout Ring", "Prevents surprise attacks", 1, 50);

        boolean success = p.buyItem(ring);

        assertThat(success, is(false)); // Purchase need to fail
    }

    @Test
    @DisplayName("Not enough weight to buy")
    void testBuyItemWithoutWeight() {
        Adventurer p = new Adventurer("Kilian", "Denver", 200, new ArrayList<>());

        p.setCurrentWeight(60);

        Item ring = new Item("Lookout Ring", "Prevents surprise attacks", 1, 50);

        boolean success = p.buyItem(ring);

        assertThat(success, is(false)); // Purchase need to fail
    }

    /* removeItem */

    @Test
    @DisplayName("Removing item")
    void testRemoveItem() {
        Adventurer p = new Adventurer("Kilian", "Denver", 200, new ArrayList<>());

        Item ring = new Item("Lookout Ring", "Prevents surprise attacks", 1, 50);

        boolean success = p.buyItem(ring);

        assertThat(success, is(true)); // Purchase need to succeed

        assertThat(p.getInventory(), hasItem(ring)); // Item ring need to be in the inventory

        p.removeItem(ring);

        assertThat(p.getInventory(), not(hasItem(ring))); // Inventory should not have the item

        assertThat(p.getMoney(), is(200 - 50)); // By using removeItem, Player should not recover his money
    }

    @Test
    @DisplayName("Removing item without having it")
    void testRemoveItemWithoutHavingIt() {
        Adventurer p = new Adventurer("Kilian", "Denver", 200, new ArrayList<>());

        Item ring = new Item("Lookout Ring", "Prevents surprise attacks", 1, 50);

        boolean success = p.removeItem(ring);

        assertThat(success, is(false)); // Can't remove an inexistant item
    }

    /* sellItem */

    @Test
    @DisplayName("Selling item")
    void testSellItem() {
        Adventurer p = new Adventurer("Kilian", "Denver", 200, new ArrayList<>());

        Item ring = new Item("Lookout Ring", "Prevents surprise attacks", 1, 50);

        p.buyItem(ring);

        boolean success = p.sellItem(ring);

        assertThat(success, is(true)); // Sell need to succeed

        assertThat(p.getInventory(), not(hasItem(ring))); // Item ring must not be in the inventory

        assertThat(p.getMoney(), is(200)); // By using removeItem, Player should not recover his money
    }

    @Test
    @DisplayName("Selling item without having it")
    void testSellItemWithoutHavingIt() {
        Adventurer p = new Adventurer("Kilian", "Denver", 200, new ArrayList<>());

        Item ring = new Item("Lookout Ring", "Prevents surprise attacks", 1, 50);

        boolean success = p.sellItem(ring);

        assertThat(success, is(false)); // Sell need to fail

    }

    /* addItem */

    @Test
    @DisplayName("Adding item without required weight")
    void testFailAddingItem() {
        Adventurer p = new Adventurer("Kilian", "Denver", 200, new ArrayList<>());

        Item ring = new Item("Lookout Ring", "Prevents surprise attacks", 1, 50);

        p.setCurrentWeight(60);

        boolean success = p.addItem(ring);

        assertThat(success, is(false)); // Can't add the item because of the weight

    }

}
