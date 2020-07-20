package dk.legendebente.skywars;

import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class Skywars extends JavaPlugin {

    private static Skywars instance;
    private SkywarsGame runningGame;
    private static String prefix = "&8[&e&lSKYWARS&8] ";

    @Override
    public void onEnable(){
        instance = this;

        new SkywarsGame();
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

}
