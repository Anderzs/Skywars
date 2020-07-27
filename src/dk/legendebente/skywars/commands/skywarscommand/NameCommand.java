package dk.legendebente.skywars.commands.skywarscommand;

import dk.legendebente.skywars.commands.SubCommand;
import dk.legendebente.skywars.disguise.ChangeName;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameCommand extends SubCommand {


    @Override
    public void execute(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        for(Player target : Bukkit.getOnlinePlayers()){
            ChangeName.changeName("&6&k:::::::", target);
        }

    }
}
