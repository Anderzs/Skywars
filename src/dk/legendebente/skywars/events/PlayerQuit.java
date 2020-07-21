package dk.legendebente.skywars.events;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    SkywarsGame game = Skywars.getInstance().getGame();

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

}
