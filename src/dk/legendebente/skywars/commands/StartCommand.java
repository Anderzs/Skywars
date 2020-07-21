package dk.legendebente.skywars.commands;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand extends SubCommand {

    SkywarsGame game = Skywars.getInstance().getGame();

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(game.getGameState() != SkywarsGame.GameState.LOBBY){
                player.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&cSpillet er allerede igang!"));
                return;
            }

            player.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Du har startet spillet!"));
            game.startGame();
        }
    }
}
