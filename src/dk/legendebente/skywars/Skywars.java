package dk.legendebente.skywars;

import dk.legendebente.skywars.commands.SkywarsCommand;
import dk.legendebente.skywars.events.*;
import dk.legendebente.skywars.objects.SkywarsBoard;
import dk.legendebente.skywars.objects.SkywarsGame;
import dk.legendebente.skywars.schedulers.ScoreboardScheduler;
import dk.legendebente.skywars.schedulers.StartGameScheduler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;

public class Skywars extends JavaPlugin {

    private static Skywars instance;
    private SkywarsGame runningGame;
    private static String prefix = "&8[&e&lSKYWARS&8] ";
    private StartGameScheduler startGameScheduler;
    private ScoreboardScheduler scoreboardScheduler;
    private SkywarsBoard skywarsBoard;



    @Override
    public void onEnable(){
        instance = this;
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        new SkywarsGame();

        //TODO: Tilføj alle event listeners
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

        getCommand("skywars").setExecutor(new SkywarsCommand());

        this.skywarsBoard = new SkywarsBoard();
        this.startGameScheduler = new StartGameScheduler();
        this.scoreboardScheduler = new ScoreboardScheduler();
    }

    public SkywarsGame getGame(){
        return runningGame;
    }

    public void registerGame(SkywarsGame game){
        this.runningGame = game;
    }

    public static Skywars getInstance(){
        return instance;
    }

    public static String getPrefix(){
        return prefix;
    }

    public StartGameScheduler getStartGameScheduler(){
        return this.startGameScheduler;
    }

    public ScoreboardScheduler getScoreboardScheduler(){
        return this.scoreboardScheduler;
    }

    public SkywarsBoard getSkywarsBoard(){
        return this.skywarsBoard;
    }

}
