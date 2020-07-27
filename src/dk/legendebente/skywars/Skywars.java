package dk.legendebente.skywars;

import dk.legendebente.skywars.commands.rankcommand.RankCommand;
import dk.legendebente.skywars.commands.skywarscommand.SkywarsCommand;
import dk.legendebente.skywars.events.*;
import dk.legendebente.skywars.files.ConfigFile;
import dk.legendebente.skywars.files.PlayerFile;
import dk.legendebente.skywars.objects.SkywarsBoard;
import dk.legendebente.skywars.objects.SkywarsGame;
import dk.legendebente.skywars.schedulers.ScoreboardScheduler;
import dk.legendebente.skywars.schedulers.StartGameScheduler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public class Skywars extends JavaPlugin {

    private static Skywars instance;
    private SkywarsGame runningGame;
    private static String prefix = "&8[&e&lSKYWARS&8] ";
    private StartGameScheduler startGameScheduler;
    private ScoreboardScheduler scoreboardScheduler;
    private SkywarsBoard skywarsBoard;
    private ConfigFile configuration;
    private HashMap<Player, PlayerFile> playerFiles;
    private File statsFolder = new File(getDataFolder(), "/stats/");

    @Override
    public void onEnable(){
        instance = this;
        playerFiles = new HashMap<>();
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        if(!statsFolder.exists()){
            statsFolder.mkdir();
        }

        this.configuration = new ConfigFile(new File(getDataFolder(), "config.yml"));
        new SkywarsGame();

        this.skywarsBoard = new SkywarsBoard();
        this.startGameScheduler = new StartGameScheduler();
        this.scoreboardScheduler = new ScoreboardScheduler();

        //TODO: Tilføj alle event listeners
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

        getCommand("skywars").setExecutor(new SkywarsCommand());
        getCommand("rank").setExecutor(new RankCommand());
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

    public ConfigFile getConfiguration(){
        return this.configuration;
    }

    public ScoreboardScheduler getScoreboardScheduler(){
        return this.scoreboardScheduler;
    }

    public SkywarsBoard getSkywarsBoard(){
        return this.skywarsBoard;
    }

    public PlayerFile getPlayerFile(Player player){
        return playerFiles.get(player);
    }

    public void addPlayerFile(PlayerFile playerFile){
        playerFiles.put(playerFile.getPlayer(), playerFile);
    }

}
