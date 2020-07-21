package dk.legendebente.skywars.schedulers;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.objects.GamePlayer;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ScoreboardScheduler extends BukkitRunnable {

    SkywarsGame game = Skywars.getInstance().getGame();
    HashMap<String, Integer> scores = new HashMap<>();

    @Override
    public void run() {
        if(Skywars.getInstance().getStartGameScheduler().getTime() == 0) {
            cancel();
        } else {
            for(GamePlayer gp : game.getPlayersJoined()){
                Skywars.getInstance().getSkywarsBoard().removeLinesFromScoreboard(Skywars.getInstance().getSkywarsBoard().getLobbyScoreboard(), new String[]{"&7Starter om: &6" + (Skywars.getInstance().getStartGameScheduler().getTime() + 1) + " sekunder"});
                Skywars.getInstance().getSkywarsBoard().removeLinesFromScoreboard(Skywars.getInstance().getSkywarsBoard().getLobbyScoreboard(), new String[]{"&7Starter om: &6" + (Skywars.getInstance().getStartGameScheduler().getTime() + 1) + " sekund"});
                Skywars.getInstance().getSkywarsBoard().removeLinesFromScoreboard(Skywars.getInstance().getSkywarsBoard().getLobbyScoreboard(), new String[]{"&7Starter om: &6" + (Skywars.getInstance().getStartGameScheduler().getTime()) + " sekunder"});
                Skywars.getInstance().getSkywarsBoard().removeLinesFromScoreboard(Skywars.getInstance().getSkywarsBoard().getLobbyScoreboard(), new String[]{"&7Starter om: &6" + (Skywars.getInstance().getStartGameScheduler().getTime()) + " sekund"});
                scores.clear();
                scores.put("&7Starter om: &6" + (Skywars.getInstance().getStartGameScheduler().getTime() - 1) + " sekund" + ((Skywars.getInstance().getStartGameScheduler().getTime() - 1) == 1 ? "" : "er"), 2);
                Skywars.getInstance().getSkywarsBoard().setScores(Skywars.getInstance().getSkywarsBoard().getLobbyScoreboard(), scores);
            }
        }

    }

}
