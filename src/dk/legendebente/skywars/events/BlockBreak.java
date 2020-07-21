package dk.legendebente.skywars.events;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    private SkywarsGame game = Skywars.getInstance().getGame();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(!event.getPlayer().isOp() || game.getGameState() == SkywarsGame.GameState.LOBBY || game.getGameState() == SkywarsGame.GameState.PREPARATION){
            event.setCancelled(true);
        }
    }

}
