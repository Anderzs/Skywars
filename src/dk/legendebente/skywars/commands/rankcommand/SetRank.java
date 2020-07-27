package dk.legendebente.skywars.commands.rankcommand;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.commands.SubCommand;
import dk.legendebente.skywars.files.PlayerFile;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRank extends SubCommand {

    private PlayerFile playerFile;

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        playerFile = Skywars.getInstance().getPlayerFile(player);

        sender.sendMessage(playerFile.getRank().toString());
    }
}
