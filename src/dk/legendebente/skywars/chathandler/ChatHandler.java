package dk.legendebente.skywars.chathandler;

import org.bukkit.ChatColor;

public class ChatHandler {

    public static String format(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
