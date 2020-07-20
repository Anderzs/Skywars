package dk.legendebente.skywars.events;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private SkywarsGame game = Skywars.getInstance().getGame();

    @EventHandler
    public void onServerJoin(PlayerJoinEvent event){

    }

    @EventHandler
    public void beforeServerJoin(AsyncPlayerPreLoginEvent event){
        if(game.getGameState() != SkywarsGame.GameState.LOBBY && !Bukkit.getPlayer(event.getUniqueId()).isOp()){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatHandler.format(Skywars.getPrefix() + "&7Spillet er igang! \n &7Tjek vores discord for mere information"));
        }
    }

}
