package dk.legendebente.skywars.commands.skywarscommand;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.commands.SubCommand;
import dk.legendebente.skywars.files.ConfigFile;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SpawnPointCommand extends SubCommand {

    private SkywarsGame game = Skywars.getInstance().getGame();
    private ConfigFile config = Skywars.getInstance().getConfiguration();

    @Override
    public void execute(CommandSender sender, String[] args)  {
        if(args.length == 0){
            sender.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Du skal vælge et tal til dit spawnpoint"));
            return;
        }
        Player player = (Player) sender;
        Location point = player.getLocation();
        config.setString("Game.spawnPoints." + args[0] + ".WORLD", player.getLocation().getWorld().getName().toString());
        config.setDouble("Game.spawnPoints." + args[0] + ".X", player.getLocation().getX());
        config.setDouble("Game.spawnPoints." + args[0] + ".Y", player.getLocation().getY());
        config.setDouble("Game.spawnPoints." + args[0] + ".Z", player.getLocation().getZ());
        config.saveFile();
        player.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Du har sat spawnpoint nummer &a" + args[0]));
    }
}
