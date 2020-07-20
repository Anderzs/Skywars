package dk.legendebente.skywars.objects;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GamePlayer {

    private Player player;
    private SkywarsGame game = Skywars.getInstance().getGame();

    public GamePlayer(Player player){
        this.player = player;
    }

    public String getName(){
        return player.getName();
    }

    public boolean isIngame(){
        if(game.getGameState() == SkywarsGame.GameState.INGAME && game.getPlayersLeft().contains(this)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isDead(){
        if(game.getSpectators().contains(this) || Bukkit.getServer().getPlayer(player.getName()) == null || !game.getPlayersLeft().contains(this)){
            return true;
        } else {
            return false;
        }
    }

    public void sendMessage(String message){
        this.player.sendMessage(ChatHandler.format(message));
    }

    public Player getPlayer(){
        return this.player;
    }

}
