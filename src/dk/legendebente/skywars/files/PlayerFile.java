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
        if(!fileExists()){
            super.createFile();
            setDefaultData(player);
        }
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
        loadConfiguration();
        String val = super.getString("Spiller.Rank");
        //Bukkit.broadcastMessage(val + " " + Rank.valueOf(val));
        return (val == null ? Rank.DEFAULT : Rank.valueOf(val));
    }



    public void setDefaultData(Player player){
        loadConfiguration();
        super.setString("Spiller.Navn", player.getName());
        super.setInt("Spiller.Wins", 0);
        super.setObject("Spiller.Rank", Rank.DEFAULT.toString());
        super.saveFile();
    }

    public boolean fileExists(){
        File playerFile = new File(Skywars.getInstance().getDataFolder(), "/stats/" + player.getUniqueId().toString() + ".yml");
        if(playerFile.exists()){
            return true;
        } else {
            return false;
        }
    }


}
