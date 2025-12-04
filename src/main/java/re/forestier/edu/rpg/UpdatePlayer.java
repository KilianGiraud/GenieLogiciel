package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    public static boolean addXp(Player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();

        if (newLevel != currentLevel) {

            // Give a random object
            Random random = new Random();
            player.inventory.add(objectList[random.nextInt(objectList.length)]);

            // Apply new ability upgrades
            player.abilities.putAll(player.getAbilitiesForLevel(newLevel));

            return true;
        }

        return false;
    }


    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(Player player) {
        if(player.currentHealthPoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if(player.currentHealthPoints < player.healthPoints/2) {
            if(!player.getAvatarClass().equals("ADVENTURER")) {
                if(player.getAvatarClass().equals("DWARF")) {
                    if(player.inventory.contains("Holy Elixir")) {
                        player.currentHealthPoints+=1;
                    }
                    player.currentHealthPoints+=1;
                } else if(player.getAvatarClass().equals("ADVENTURER")) {
                    player.currentHealthPoints+=2;
                }


                if(player.getAvatarClass().equals("ARCHER")) {
                    player.currentHealthPoints+=1;
                    if(player.inventory.contains("Magic Bow")) {
                        player.currentHealthPoints+=player.currentHealthPoints/8-1;
                    }
                }
            } else {
                player.currentHealthPoints+=2;
                if(player.retrieveLevel() < 3) {
                    player.currentHealthPoints-=1;
                }
            }
        } else if(player.currentHealthPoints >= player.healthPoints/2){
            if(player.currentHealthPoints >= player.healthPoints) {
                player.currentHealthPoints = player.healthPoints;
                return;
            }
        }


        if(player.currentHealthPoints >= player.healthPoints) {
            player.currentHealthPoints = player.healthPoints;
        }
    }
}