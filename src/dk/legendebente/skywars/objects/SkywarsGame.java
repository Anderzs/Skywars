package dk.legendebente.skywars.objects;

import dk.legendebente.skywars.Skywars;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SkywarsGame {

    //Skywars variabler
    private int minPlayers;
    private int maxPlayers;
    private String host;

    //Temp variabler
    private HashMap<Player, Location> _playerSpawn;
    private List<GamePlayer> _playersJoined;
    private List<GamePlayer> _playersLeftIngame;
    private List<GamePlayer> _spectators;
    private Set<Chest> _chestsOpened;
    private GameState _gameState;


    //Fil konfiguration
    private File configFile;
    private FileConfiguration configuration;

    //Lavet til single-server-mode, hvilket vil sige at det kun er 1 game der kører
    public SkywarsGame(){
        if(!getConfigFile().exists()){
            createConfigFile();
        }

    }

    public void joinGame(Player player){
        GamePlayer _player = new GamePlayer(player);
        _playersJoined.add(_player);

    }

    public void startGame(){

    }

    private void createConfigFile(){
        try{
            getConfigFile().createNewFile();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public File getConfigFile(){
        return new File(Skywars.getInstance().getDataFolder(), "config.yml");
    }

    public List<GamePlayer> getPlayersLeft(){
        return this._playersLeftIngame;
    }

    public List<GamePlayer> getSpectators(){
        return this._spectators;
    }

    public GameState getGameState(){
        return this._gameState;
    }

    public enum GameState{
        LOBBY, PREPARATION, INGAME, ENDING
    }

}
