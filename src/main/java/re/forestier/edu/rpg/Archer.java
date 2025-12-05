package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Archer extends Player {

    public Archer(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, "ARCHER", money, inventory);
    }

    @Override
    public HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> stats = new HashMap<>();

        switch(level) {
            case 1:
                stats.put("INT", 1);
                stats.put("ATK", 3);
                stats.put("CHA", 1);
                stats.put("VIS", 3);
                break;
            case 2:
                stats.put("DEF", 1);
                stats.put("CHA", 2);
                break;
            case 3:
                stats.put("ATK", 3);
                break;
            case 4:
                stats.put("DEF", 2);
                break;
            case 5:
                stats.put("ATK", 4);
                break;
        }

        return stats;
    }

    @Override
    protected void healLogic() {
        setCurrentHealthPoints(getCurrentHealthPoints() + 1);
        if (getInventory().contains("Magic Bow")) {
            setCurrentHealthPoints(
                    getCurrentHealthPoints() + (getCurrentHealthPoints() / 8 - 1)
            );
        }
    }


}


