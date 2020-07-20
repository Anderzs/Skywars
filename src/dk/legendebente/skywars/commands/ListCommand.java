package dk.legendebente.skywars.commands;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import dk.legendebente.skywars.objects.GamePlayer;
import dk.legendebente.skywars.objects.SkywarsGame;
import org.bukkit.command.CommandSender;

public class ListCommand extends SubCommand {

    SkywarsGame game = Skywars.getInstance().getGame();

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!game.getPlayersJoined().isEmpty() && game.getPlayersJoined() != null){
            sender.sendMessage("");
            sender.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&aAktive spiller info: "));
            sender.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Spillere i live: &a" + game.getPlayersLeft().size()));
            sender.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Spiltilstand: &a" + game.getGameState().toString().toLowerCase()));
            for(GamePlayer gamePlayer : game.getPlayersJoined()){
                if(gamePlayer.isDead()){
                    sender.sendMessage(ChatHandler.format("&8&l* &8[&cDØD&8] &c" + gamePlayer.getName()));
                } else {
                    sender.sendMessage(ChatHandler.format("&8&l* &8[&aLIVE&8] &a" + gamePlayer.getName()));
                }
            }
            sender.sendMessage("");

        } else {
            sender.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&cKunne ikke finde nogen spillere."));
        }
    }
}
