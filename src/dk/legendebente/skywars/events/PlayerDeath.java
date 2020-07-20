package dk.legendebente.skywars.events;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.objects.GamePlayer;
import dk.legendebente.skywars.objects.SkywarsGame;
import dk.legendebente.skywars.packets.Title;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    private SkywarsGame game = Skywars.getInstance().getGame();
    private Title winnerTitle;
    private Title otherTitle;

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        PlayerWinEvent win = new PlayerWinEvent(game.getPlayersLeft().get(0));
        Bukkit.getServer().getPluginManager().callEvent(win);
    }

    @EventHandler
    public void playerWin(PlayerWinEvent event){
        winnerTitle = new Title();
        otherTitle = new Title();
        otherTitle.setAllTime(new int[]{0, 20, 0});
        otherTitle.setTitle(ChatHandler.format("&6&lSKYWARS"));
        for(GamePlayer player : game.getPlayersJoined()){
            if(player.getPlayer() != null){
                player.sendMessage("&7Spilleren &6" + event.getWinner().getName() + " &7har vundet skywars!");
            }
            if(player.getPlayer() != event.getWinner().getPlayer()){
                otherTitle.setSubtitle(ChatHandler.format("&7Du placerede som nummer &6#" + player.getPlacement()));
                otherTitle.sendTitle(player.getPlayer());
            }
        }
        winnerTitle.setTitle(ChatHandler.format("&6&lVICTORY"));
        winnerTitle.setSubtitle(ChatHandler.format("&7Du har vundet &6&lSKYWARS&7!"));
        winnerTitle.setAllTime(new int[]{0, 20, 0});
        winnerTitle.sendTitle(event.getWinner().getPlayer());
    }

}
