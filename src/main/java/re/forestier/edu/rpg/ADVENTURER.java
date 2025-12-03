package re.forestier.edu.rpg;

import java.util.ArrayList;

public class ADVENTURER extends Player {
    public ADVENTURER(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, "ADVENTURER", money, inventory);
    }
}

