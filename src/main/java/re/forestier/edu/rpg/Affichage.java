package re.forestier.edu.rpg;

public class Affichage {

    public static String afficherJoueur(Player player) {

        StringBuilder sb = new StringBuilder();

        sb.append("Joueur ").append(player.getAvatarName())
                .append(" joué par ").append(player.getPlayerName());

        sb.append("\nClasse : ").append(player.getClass().getSimpleName());

        sb.append("\nNiveau : ").append(player.retrieveLevel())
                .append(" (XP totale : ").append(player.getXp()).append(")");

        sb.append("\n\nCapacités :");
        player.getAbilities().forEach((name, level) ->
                sb.append("\n   ").append(name).append(" : ").append(level)
        );

        sb.append("\n\nInventaire :");
        if (player.getInventory().isEmpty()) {
            sb.append("\n   (vide)");
        } else {
            player.getInventory().forEach(item ->
                    sb.append("\n   ").append(item)
            );
        }

        return sb.toString();
    }
}
