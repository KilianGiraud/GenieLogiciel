package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Dwarf extends Player {
    public Dwarf(String playerName, String avatarName, int money, ArrayList<Item> inventory) {
        super(playerName, avatarName, "DWARF", money, inventory, 80); //Dwarf is clearly the strongest class, so he can hold a lot more of items.
    }

    @Override
    public HashMap<String, Integer> getAbilitiesForLevel(int level) {
        HashMap<String, Integer> stats = new HashMap<>();

        switch(level) {
            case 1:
                stats.put("ALC", 4);
                stats.put("INT", 1);
                stats.put("ATK", 3);
                break;
            case 2:
                stats.put("DEF", 1);
                stats.put("ALC", 5);
                break;
            case 3:
                stats.put("ATK", 4);
                break;
            case 4:
                stats.put("DEF", 2);
                break;
            case 5:
                stats.put("CHA", 1);
                break;
        }

        return stats;
    }

    // Overrided method specific to each player

    @Override
    protected void healLogic() {
        boolean hasElixir = getInventory().stream()
                .anyMatch(item -> item.getItemName().equals("Holy Elixir"));

        if (hasElixir) {
            setCurrentHealthPoints(getCurrentHealthPoints() + 1);
        }

        setCurrentHealthPoints(getCurrentHealthPoints() + 1);
    }


}

