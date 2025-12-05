package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {

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