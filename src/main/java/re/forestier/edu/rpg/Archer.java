package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

public class Archer extends Player {

    public Archer(String playerName, String avatarName, int money, ArrayList<Item> inventory) {
        super(playerName, avatarName, "ARCHER", money, inventory, 50); //Archer is light and agile and he wear a quiver, arrows and bow but not heavy weapon
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

    // Overrided method specific to each player

    @Override
    protected void healLogic() {
        setCurrentHealthPoints(getCurrentHealthPoints() + 1);

        boolean hasMagicBow = getInventory().stream()
                .anyMatch(item -> item.getItemName().equals("Magic Bow"));

        if (hasMagicBow) {
            setCurrentHealthPoints(getCurrentHealthPoints() + (getCurrentHealthPoints() / 8 - 1));
        }
    }



}


