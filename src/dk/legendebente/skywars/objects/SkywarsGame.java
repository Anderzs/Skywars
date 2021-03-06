package dk.legendebente.skywars.objects;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.files.ConfigFile;
import dk.legendebente.skywars.packets.Title;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SkywarsGame {

    //Skywars variabler
    private int minPlayers;
    private int maxPlayers;
    private String host;
    private Location lobbyPoint;

    //Temp variabler
    private HashMap<GamePlayer, Location> _playerSpawn;
    private List<Location> _spawnPoints;
    private List<GamePlayer> _playersJoined;
    private List<GamePlayer> _playersLeftIngame;
    private List<GamePlayer> _spectators;
    private Set<Chest> _chestsOpened;
    private GameState _gameState = GameState.LOBBY;


    //Fil konfiguration
    private File configFile;
    private FileConfiguration configuration;

    //Lavet til single-server-mode, hvilket vil sige at det kun er 1 game der kører
    public SkywarsGame(){
        if(!getConfigFile().exists()){
            createConfigFile();
        }

        //Loader spawnpoints til skywars arenaen
        loadSpawnPoints();

        //Sæt variabler
        this.configFile = getConfigFile();
        this.configuration = YamlConfiguration.loadConfiguration(configFile);
        this.minPlayers = configuration.getInt("Game.minPlayers");
        this.maxPlayers = configuration.getInt("Game.maxPlayers");

        this._playersLeftIngame = new ArrayList<>();
        this._playersJoined = new ArrayList<>();
        this._spectators = new ArrayList<>();
        this._chestsOpened = new HashSet<>();
        this._playerSpawn = new HashMap<>();

        Skywars.getInstance().registerGame(this);
        Bukkit.getConsoleSender().sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Registrerede spillet!"));
    }

    private void loadSpawnPoints(){
        Skywars.getInstance().getLogger().info("Loader spawnpoints...");
        this._spawnPoints = new ArrayList<>();
        ConfigFile config = Skywars.getInstance().getConfiguration();

        World world = Bukkit.getWorld(config.getString("Game.Location.WORLD"));
        for(String spawnPoint : Skywars.getInstance().getConfiguration().getConfigurationSection("Game.spawnPoints").getKeys(false)){
            try{
                double x = config.getDouble("Game.spawnPoints." + spawnPoint + ".X");
                double y = config.getDouble("Game.spawnPoints." + spawnPoint + ".Y");
                double z = config.getDouble("Game.spawnPoints." + spawnPoint + ".Z");
                _spawnPoints.add(new Location(world, x, y, z));
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void joinGame(Player player){
        player.setGameMode(GameMode.ADVENTURE);
        player.setFlying(false);
        player.getInventory().clear();
        player.teleport(getLobbyPoint());

        GamePlayer _player = new GamePlayer(player);
        _playersJoined.add(_player);
        sendToAll("&a" + player.getName() + " &7tilsluttede spillet! &8(&e" + getPlayersJoined().size() + "&7/&e" + getMaxPlayers() + "&8)");
    }

    public void startGame() {
        for(GamePlayer gp : getPlayersJoined()){
            getPlayersLeft().add(gp);
            Skywars.getInstance().getSkywarsBoard().setScoreboard(gp, Skywars.getInstance().getSkywarsBoard().getLobbyScoreboard());
        }
        sendToAll("&7Teleportering starter om &630 sekunder");
        Skywars.getInstance().getStartGameScheduler().runTaskTimerAsynchronously(Skywars.getInstance(), 0, 20);
        setGameState(GameState.PREPARATION);
    }

    public void activateSpectator(GamePlayer gamePlayer){
        Player player = gamePlayer.getPlayer();
        Title specTitle = new Title(ChatHandler.format("&6&lSPECTATOR"), ChatHandler.format("&7Du er blevet &6&lSPECTATOR"), 10, 20, 10);
        player.closeInventory();
        player.getInventory().clear();
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ChatHandler.format("&8[&c&l!&8] &7Opdateret gamemode til &6spectator"));
        specTitle.sendTitle(player);

        //Fjern spilleren fra spillet
        getPlayersLeft().remove(gamePlayer);
    }

    public void teleportToSpawnPoints(){
        int id = 0;
        for(GamePlayer gp : getPlayersLeft()) {
            try{
                //TODO: Lav så den opdaterer gamemodes
                gp.getPlayer().teleport(_spawnPoints.get(id));
                _playerSpawn.put(gp, _spawnPoints.get(id));
                id += 1;
                gp.getPlayer().setHealth(gp.getPlayer().getMaxHealth());
            } catch(IndexOutOfBoundsException e){
                Skywars.getInstance().getLogger().severe("Kunne ikke få alle ind på mappet!");
            }
        }
    }

    private void createConfigFile(){
        try{
            getConfigFile().createNewFile();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendToAll(String msg){
        for(GamePlayer gp : getPlayersJoined()){
            gp.sendMessage(msg);
        }
    }

    public void sendToAlive(String msg){
        for(GamePlayer gp : getPlayersLeft()){
            gp.sendMessage(msg);
        }
    }

    public Location getLobbyPoint(){
        try{
            configuration.load(configFile);
        } catch(Exception e){
            e.printStackTrace();
        }
        return new Location(Bukkit.getWorld(configuration.getString("Game.Location.WORLD")),
                configuration.getDouble("Game.Location.X"),
                configuration.getDouble("Game.Location.Y"),
                configuration.getDouble("Game.Location.Z"));
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

    public GamePlayer getGamePlayer(Player player){
        for(GamePlayer gp : getPlayersJoined()){
            if(gp.getPlayer() == player){
                return gp;
            }
        }
        return null;
    }

    public void setGameState(GameState gameState){
        this._gameState = gameState;
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
