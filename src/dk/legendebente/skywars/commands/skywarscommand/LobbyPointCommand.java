package dk.legendebente.skywars.commands.skywarscommand;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.commands.SubCommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LobbyPointCommand extends SubCommand {

    @Override
    public void execute(CommandSender sender, String[] args)  {
        Player player = (Player) sender;
        File configFile = new File(Skywars.getInstance().getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        config.set("Game.Location.WORLD", player.getWorld().getName());
        config.set("Game.Location.X", player.getLocation().getX());
        config.set("Game.Location.Y", player.getLocation().getY());
        config.set("Game.Location.Z", player.getLocation().getZ());
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Du har sat lobby point lokation!"));
    }
}
