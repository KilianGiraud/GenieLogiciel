package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Goblin extends Player{

    public Goblin(String playerName, String avatarName, int money, ArrayList<String> inventory) {
        super(playerName, avatarName, "GOBLIN", money, inventory);
    }

    @Override
    public HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> stats = new HashMap<>();

        switch(level) {
            case 1:
                stats.put("INT", 2);
                stats.put("ATK", 2);
                stats.put("ALC", 1);
                break;
            case 2:
                stats.put("ATK", 3);
                stats.put("ALC", 4);
                break;
            case 3:
                stats.put("VIS", 1);
                break;
            case 4:
                stats.put("DEF", 1);
                break;
            case 5:
                stats.put("DEF", 2);
                stats.put("ATK", 4);
                break;
        }

        return stats;
    }

    // Overrided method specific to each player
    // Heal Logic not indicated in subject so here is an idea I had.

    @Override
    protected void healLogic() {
        // No heal
    }


}
