package dk.legendebente.skywars.files;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class PlayerFile extends SkywarsFile {

    private Player player;

    public PlayerFile(Player player) {
        super(new File(Skywars.getInstance().getDataFolder(), "/stats/" + player.getUniqueId().toString() + ".yml"));
        this.player = player;
    }

    public PlayerFile(UUID uuid){
        super(new File(Skywars.getInstance().getDataFolder(), "/stats/" + uuid.toString() + ".yml"));
    }

    public int getWins(){
        return super.getInt("Spiller.Wins");
    }

    public Player getPlayer(){
        return this.player;
    }


    public Rank getRank(){
        Object rank = super.getObject("Spiller.Rank");
        return rank instanceof Rank ? (Rank) rank : null;
    }

    public void setDefaultData(Player player){
        super.setString("Spiller.Navn", player.getName());
        super.setInt("Spiller.Wins", 0);
        super.setObject("Spiller.Rank", Rank.DEFAULT);
        super.saveFile();
    }


}
