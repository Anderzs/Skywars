package dk.legendebente.skywars.commands.skywarscommand;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.commands.SubCommand;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.command.CommandSender;

public class ForceStartCommand extends SubCommand {

    SkywarsGame game = Skywars.getInstance().getGame();

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(game.getGameState() != SkywarsGame.GameState.LOBBY){
            sender.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Spillet er allerede igang!"));
            return;
        }
    }
}
