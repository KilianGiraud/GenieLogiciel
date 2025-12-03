package re.forestier.edu.rpg;

import java.util.ArrayList;

public class Adventurer extends Player {
    public Adventurer(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, "ADVENTURER", money, inventory);
    }
}

