package dk.legendebente.skywars.objects;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Map;

public class SkywarsBoard {

    private Scoreboard lobbyScoreboard;
    private HashMap<String, Integer> lobbyScores;
    private SkywarsGame game = Skywars.getInstance().getGame();

    public SkywarsBoard(){
        registerLobbyScoreboard();
    }


    private void registerLobbyScoreboard(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();

        //Registrer scoreboardet
        this.lobbyScoreboard = manager.getNewScoreboard();
        Objective obj = lobbyScoreboard.registerNewObjective("Board", "");
        obj.setDisplayName(ChatHandler.format("   &6&lSKYWARS   "));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);


        //Scores
        Score spc1 = obj.getScore("§a");
        Score name = obj.getScore(ChatHandler.format("&7Spiller: &6{name}"));
        Score wins = obj.getScore(ChatHandler.format("&7Wins: &6{wins}"));
        Score spc2 = obj.getScore("§b");
        Score online = obj.getScore(ChatHandler.format("&7Spillere: &6{joined}&7/&6{max}"));
        Score starting = obj.getScore(ChatHandler.format("&7Starter om: &6{timeLeft}"));
        Score spc3 = obj.getScore("§c");
        Score legenden = obj.getScore(ChatHandler.format("  &7&oLavet af LegendeBente  "));

        //Set scores
        spc1.setScore(7);
        name.setScore(6);
        wins.setScore(5);
        spc2.setScore(4);
        online.setScore(3);
        starting.setScore(2);
        spc3.setScore(1);
        legenden.setScore(0);
    }

    public void setScoreboard(GamePlayer gp, Scoreboard _scoreboard){
        removeLinesFromScoreboard(_scoreboard, new String[] {
                "&7Spiller: &6{name}",
                "&7Wins: &6{wins}",
                "&7Spillere: &6{joined}&7/&6{max}",
                "&7Starter om: &6{timeLeft}",
                "  &7&oLavet af LegendeBente  "});

        setScores(_scoreboard, initializeScores(gp));
        gp.getPlayer().setScoreboard(_scoreboard);
        Skywars.getInstance().getScoreboardScheduler().runTaskTimer(Skywars.getInstance(), 0, 20);
    }

    public HashMap<String, Integer> initializeScores(GamePlayer gp){
        this.lobbyScores = new HashMap<>();
        lobbyScores.clear();
        lobbyScores.put("&7Spiller: &6" + gp.getName(), 6);
        lobbyScores.put("&7Wins: &60", 5);
        lobbyScores.put("&7Spillere: &6" + game.getPlayersJoined().size() + "&7/&6" + game.getMaxPlayers(), 3);
        lobbyScores.put("&7Starter om: &6" + Skywars.getInstance().getStartGameScheduler().getTime() + " sekunder", 2);
        lobbyScores.put("  &7&oLavet af LegendeBente  ", 0);
        return lobbyScores;
    }

    public Scoreboard getLobbyScoreboard(){
        return this.lobbyScoreboard;
    }

    public void removeLinesFromScoreboard(Scoreboard _scoreboard, String[] lines){
        for(int i = 0; i < lines.length; i++){
            _scoreboard.resetScores(ChatHandler.format(lines[i]));
        }
    }

    public void setScores(Scoreboard _scoreboard, HashMap<String, Integer> scores){
        for(Map.Entry<String, Integer> entry : scores.entrySet()){
            _scoreboard.getObjective(DisplaySlot.SIDEBAR).getScore(ChatHandler.format(entry.getKey())).setScore(entry.getValue());
        }

    }



}
