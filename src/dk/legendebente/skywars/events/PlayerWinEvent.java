package dk.legendebente.skywars.events;

import dk.legendebente.skywars.objects.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWinEvent extends Event {

    private GamePlayer _winner;
    private static final HandlerList handlers = new HandlerList();

    public PlayerWinEvent(GamePlayer winner){
        this._winner = winner;
    }

    public GamePlayer getWinner(){
        return this._winner;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }




}
