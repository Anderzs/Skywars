package dk.legendebente.skywars.schedulers;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class StartGameScheduler extends BukkitRunnable {

    private SkywarsGame game = Skywars.getInstance().getGame();
    private int _time = 30;


    @Override
    public void run() {
        _time -= 1;
        if(_time == 0){
            cancel();
            game.teleportToSpawnPoints();
        } else {
            if(_time % 5 == 0 || _time <= 5){
                game.sendToAll("&7Teleportering starter om &6" + _time + " &6sekund" + (_time == 1 ? "" : "er"));
            }
        }
    }

    public int getTime(){
        return this._time;
    }
}
