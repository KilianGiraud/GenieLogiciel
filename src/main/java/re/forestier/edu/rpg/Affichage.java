package re.forestier.edu.rpg;


public class Affichage {

    public static String afficherJoueur(Player player) {
        final String[] finalString = {"Joueur " + player.getAvatarName() + " joué par " + player.getPlayerName()};
        finalString[0] += "\nNiveau : " + player.retrieveLevel() + " (XP totale : " + player.xp + ")";
        finalString[0] += "\n\nCapacités :";
        player.getAbilities().forEach((name, level) -> {
            finalString[0] += "\n   " + name + " : " + level;
        });
        finalString[0] += "\n\nInventaire :";
        player.getInventory().forEach(item -> {
            finalString[0] += "\n   " + item;
        });

        return finalString[0];
    }

    public static String afficherJoueurMarkdown(Player player) {
        StringBuilder sb = new StringBuilder();

        // HEADER ---------------------------------------------------
        sb.append("# Joueur : ").append(player.getAvatarName()).append("\n");
        sb.append("## Classe : ").append(player.getClass().getSimpleName()).append("\n");
        sb.append("**Joué par** : ").append(player.getPlayerName()).append("\n\n");

        sb.append("---\n\n");

        // LEVEL & XP ---------------------------------------------
        sb.append("## Niveau\n");
        sb.append("*").append(player.retrieveLevel()).append("*\n");
        sb.append("XP totale : **").append(player.getXp()).append("**\n\n");

        sb.append("---\n\n");

        // MONEY ---------------------------------------------------
        sb.append("## Argent\n");
        sb.append("**").append(player.getMoney()).append(" pièces**\n\n");

        sb.append("---\n\n");

        // SKILLS ------------------------------------------------
        sb.append("## Capacités\n");
        player.getAbilities().forEach((name, level) ->
                sb.append("* ").append(name).append(" : ").append(level).append("\n")
        );
        sb.append("\n");

        sb.append("---\n\n");

        // INVENTORY -----------------------------------------------
        sb.append("## Inventaire (")
                .append(player.getCurrentWeight())
                .append(" / ")
                .append(player.getMaxWeight())
                .append(" kg)\n");

        if (player.getInventory().isEmpty()) {
            sb.append("*(vide)*");
        } else {
            for (Item item : player.getInventory()) {
                sb.append("* ")
                        .append(item.getItemName())
                        .append(" — ").append(item.getItemDescription())
                        .append(" *(")
                        .append(item.getItemWeight()).append(" kg, ")
                        .append(item.getItemValue()).append(" g)*");
            }
        }

        return sb.toString();
    }


}
