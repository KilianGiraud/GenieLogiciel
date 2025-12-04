package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Player {

    public abstract HashMap<String, Integer> getAbilitiesForLevel(int level);

    public String playerName;
    public String avatarName;
    protected String avatarClass;

    public Integer money;

    public int level;
    public int healthPoints;
    public int currentHealthPoints;
    protected int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    public Player(String playerName, String avatarName, String avatarClass, int money, ArrayList<String> inventory) {

        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = inventory;

        this.level = 1;
        this.healthPoints = 10;
        this.currentHealthPoints = 10;

        this.abilities = getAbilitiesForLevel(1);
    }


    public String getAvatarClass () {
        return avatarClass;
    }

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

    public int getXp() {
        return this.xp;
    }

}