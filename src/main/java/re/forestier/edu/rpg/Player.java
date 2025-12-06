package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Player {

    public abstract HashMap<String, Integer> getAbilitiesForLevel(int level);

    private String playerName;
    private String avatarName;
    protected String avatarClass;

    private Integer money;

    private int level;
    private int healthPoints;
    private int currentHealthPoints;
    private final int maxWeight;
    private int currentWeight;
    protected int xp;


    private HashMap<String, Integer> abilities;
    private ArrayList<Item> inventory;

    private static final Item[] objectList = {
            new Item("Lookout Ring", "Prevents surprise attacks", 1, 50),
            new Item("Scroll of Stupidity", "INT -2 when applied to an enemy", 1, 10),
            new Item("Draupnir", "Increases XP gained by 100%", 2, 80),
            new Item("Magic Charm", "Magic +10 for 5 rounds", 1, 40),
            new Item("Rune Staff of Curse", "May burn enemies or yourself", 5, 200),
            new Item("Combat Edge", "Sharp weapon", 3, 100),
            new Item("Holy Elixir", "Recover HP", 1, 30)
    };



    public Player(String playerName, String avatarName, String avatarClass, int money, ArrayList<Item> inventory, int maxWeight) {

        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;
        this.maxWeight = maxWeight;


        // A new player always starts at level 1 with base stats and full health.
        // Subclasses define the actual abilities through getAbilitiesForLevel().

        this.level = 1;
        this.healthPoints = 10;
        this.currentHealthPoints = 10;
        this.currentWeight = 0; // starts empty

        this.abilities = getAbilitiesForLevel(1);
    }

    /* MONEY */

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money = money - amount;
    }
    public void addMoney(int amount) {
        var value = Integer.valueOf(amount);
        money = money + (value != null ? value : 0);
    }

    /* END MONEY */

    /* LEVELS */

    // XP thresholds for each level based on the original game progression formula.
    // retrieveLevel() uses these values to determine the correct level at any time.
    private static final HashMap<Integer, Integer> LEVEL_THRESHOLDS = new HashMap<>() {{
        put(2, 10); // 1*10 + ((2*0)/4)
        put(3, 27); // 2*10 + ((3*10)/4)
        put(4, 57); // 3*10 + ((4*27)/4)
        put(5, 111); // 4*10 + ((5*57)/4)
    }};

    public int retrieveLevel() {

        for (int level = 2; level <= 5; level++) {
            if (xp < LEVEL_THRESHOLDS.get(level)) {
                return level - 1;
            }
        }

        return 5;
    }

    /* END LEVELS */

    /* XP */

    // XP addition triggers a level check.
    // If the player levels up:
    //  - a random bonus item is granted
    //  - the abilities for the new level are applied
    // This mirrors the original RPG mechanics.

    public boolean addXp(int amount) {
        int beforeLevel = retrieveLevel();
        this.xp += amount;
        int afterLevel = retrieveLevel();

        if (afterLevel > beforeLevel) {

            addRandomItem();

            onLevelUp(afterLevel);

            return true;
        }

        return false;
    }

    protected void onLevelUp(int newLevel) {
        this.abilities.putAll(getAbilitiesForLevel(newLevel));
    }

    /* END XP */

    /* END OF TURN */

    // End-of-turn healing logic:
    // 1. If the player is KO (0 HP), print message and stop.
    // 2. If below 50% HP, each subclass applies its own healing logic.
    // 3. HP is capped to avoid overhealing (e.g., effects from items).

    public void endOfTurn() {

        if (isKO()) {
            onKO();
            return;
        }

        if (isLowHealth()) {
            healLogic();  // specific to each type of player
        }

        capHealth(); // final common logic
    }

    protected boolean isLowHealth() {
        return currentHealthPoints < healthPoints / 2;
    }

    protected void capHealth() {
        if (currentHealthPoints > healthPoints) {
            currentHealthPoints = healthPoints;
        }
    }

    // Default heal behavior: no healing.
    // Subclasses override this to implement their own regeneration rules.

    protected void healLogic() {
        // default state : nothing
    }

    public boolean isKO() {
        return currentHealthPoints <= 0;
    }

    protected void onKO() {
        System.out.println("Le joueur est KO !");
    }

    /* END END OF TURN */

    /* ITEMS */

    protected void addRandomItem() {
        Random random = new Random();
        Item item = objectList[random.nextInt(objectList.length)];
        addItem(item);
    }


    public boolean addItem(Item item) {
        if (currentWeight + item.getItemWeight() > maxWeight) {
            return false;
        }
        inventory.add(item);
        currentWeight += item.getItemWeight();
        return true;
    }

    public boolean removeItem(Item item) { // If you just want to remove it without getting money
        if (inventory.remove(item)) {
            currentWeight -= item.getItemWeight();
            return true;
        }
        return false;
    }

    public boolean sellItem(Item item) { // If you want to sell it for money
        if (removeItem(item)) {
            this.money += item.getItemValue();
            return true;
        }
        return false;
    }

    public boolean buyItem(Item item) {

        if (this.money < item.getItemValue()) {
            System.out.println("Purchase impossible : insufficient funds.");
            return false;
        }

        if (currentWeight + item.getItemWeight() > maxWeight) {
            System.out.println("Purchase impossible : maximum weight exceeded.");
            return false;
        }

        this.money -= item.getItemValue();
        inventory.add(item);
        currentWeight += item.getItemWeight();

        System.out.println("Purchase successful : " + item.getItemName());
        return true;
    }

    /* END ITEMS */

    /* GETTERS */

    public int getXp() {
        return this.xp;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public String getAvatarName(){
        return this.avatarName;
    }

    public String getAvatarClass () {
        return avatarClass;
    }

    public int getMoney(){
        return this.money;
    }

    public int getHealthPoints(){
        return this.healthPoints;
    }

    public int getCurrentHealthPoints(){
        return this.currentHealthPoints;
    }

    public HashMap<String, Integer> getAbilities(){
        return this.abilities;
    }

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    public int getMaxWeight() { return this.maxWeight; }

    public int getCurrentWeight() { return this.currentWeight; }

    /* END GETTERS */

    /* SETTERS */

    public void setMoney(int value){
        this.money = value;
    }

    public void setCurrentHealthPoints(int value){
        this.currentHealthPoints = value;
    }

    public void setHealthPoints(int value){
        this.healthPoints = value;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void setCurrentWeight(int value) { this.currentWeight = value; }

    /* END SETTERS */


}