package dk.legendebente.skywars.objects;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.packets.Title;
import org.bukkit.GameMode;
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

    public void activateSpectator(GamePlayer gamePlayer){
        Player player = gamePlayer.getPlayer();
        Title specTitle = new Title(ChatHandler.format("&6&lSPECTATOR"), ChatHandler.format("&7Du er blevet &6&lSPECTATOR"), 10, 20, 10);
        player.closeInventory();
        player.getInventory().clear();
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ChatHandler.format("&8[&c&l!&8] &7Du er blevet sat til &6SPECTATOR&7!"));
        specTitle.sendTitle(player);

        //Fjern spilleren fra spillet
        getPlayersLeft().remove(gamePlayer);
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

    public List<GamePlayer> getPlayersJoined(){
        return this._playersJoined;
    }

    public List<GamePlayer> getSpectators(){
        return this._spectators;
    }

    public GameState getGameState(){
        return this._gameState;
    }

    public int getMaxPlayers(){
        return this.maxPlayers;
    }

    public enum GameState{
        LOBBY, PREPARATION, INGAME, ENDING
    }

}
