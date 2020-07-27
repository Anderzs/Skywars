package dk.legendebente.skywars.ranks;

import dk.legendebente.skywars.files.PlayerFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum Rank {


    //Staff
    DEVELOPER("Udvikler", ChatColor.GOLD),
    OWNER("Ejer", ChatColor.DARK_RED),
    HOST("Host", ChatColor.GOLD),
    ADMIN("Admin", ChatColor.RED),
    MODERATOR("Mod", ChatColor.DARK_BLUE),
    HELPER("Hjælper", ChatColor.DARK_AQUA),


    //Normale spillere
    YOUTUBE("YouTube", ChatColor.RED),
    TWITCH("Twitch", ChatColor.DARK_PURPLE),
    VIP("VIP", ChatColor.GOLD),
    DEFAULT("", ChatColor.GRAY);

    private ChatColor colorCode;
    private PlayerFile playerFile;
    private String name;

    Rank(String name, ChatColor colorCode){
        this.colorCode = colorCode;
        this.name = name;
    }

    public boolean hasRank(Player player, Rank rank){
        return false;
    }

    public ChatColor GetColor(){
        return this.colorCode;
    }

    public String GetName(){
        return this.name;
    }

}
