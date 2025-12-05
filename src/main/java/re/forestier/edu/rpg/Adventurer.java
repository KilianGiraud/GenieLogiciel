package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Adventurer extends Player {
    public Adventurer(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, "ADVENTURER", money, inventory);
    }

    @Override
    public HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> stats = new HashMap<>();

        switch(level) {
            case 1:
                stats.put("INT", 1);
                stats.put("DEF", 1);
                stats.put("ATK", 3);
                stats.put("CHA", 2);
                break;
            case 2:
                stats.put("INT", 2);
                stats.put("CHA", 3);
                break;
            case 3:
                stats.put("ATK", 5);
                stats.put("ALC", 1);
                break;
            case 4:
                stats.put("DEF", 3);
                break;
            case 5:
                stats.put("VIS", 1);
                stats.put("DEF", 4);
                break;
        }

        return stats;
    }

    @Override
    protected void healLogic() {
        setCurrentHealthPoints(getCurrentHealthPoints() + 2);

        if (retrieveLevel() < 3) {
            setCurrentHealthPoints(getCurrentHealthPoints() - 1);
        }
    }

}

