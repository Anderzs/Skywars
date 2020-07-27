package dk.legendebente.skywars.events;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.files.PlayerFile;
import dk.legendebente.skywars.objects.GamePlayer;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private SkywarsGame game = Skywars.getInstance().getGame();
    private PlayerFile playerFile;

    @EventHandler
    public void onServerJoin(PlayerJoinEvent event){
        playerFile = new PlayerFile(event.getPlayer());
        Skywars.getInstance().addPlayerFile(playerFile);
        event.setJoinMessage(null);
        if(game.getPlayersJoined().size() >= game.getMaxPlayers() || game.getGameState() != SkywarsGame.GameState.LOBBY){
            game.activateSpectator(new GamePlayer(event.getPlayer()));
        } else {
            game.joinGame(event.getPlayer());
        }
    }

    @EventHandler
    public void beforeServerJoin(AsyncPlayerPreLoginEvent event){
        if(game.getGameState() != SkywarsGame.GameState.LOBBY){
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatHandler.format(Skywars.getPrefix() + "\n&cSpillet er igang! \n\n &eTjek vores discord for mere information"));
        }
    }

}
