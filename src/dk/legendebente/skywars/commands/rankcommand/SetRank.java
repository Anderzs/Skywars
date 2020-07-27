package dk.legendebente.skywars.commands.rankcommand;

import dk.legendebente.skywars.commands.SubCommand;
import org.bukkit.command.CommandSender;

public class SetRank extends SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("Sejr");
    }
}
