package dk.legendebente.skywars.events;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.files.PlayerFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    private PlayerFile playerFile;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        playerFile = Skywars.getInstance().getPlayerFile(event.getPlayer());

        Bukkit.broadcastMessage(playerFile.getRank().GetColor() + "" + ChatColor.BOLD + playerFile.getRank().GetName().toUpperCase() + " " + playerFile.getRank().GetColor() + event.getPlayer().getName() + "§8: §7" + event.getMessage());
    }

}
