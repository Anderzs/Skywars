package dk.legendebente.skywars.commands.rankcommand;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RankCommand implements CommandExecutor {

    private SetRank setRank;

    public RankCommand(){
        this.setRank = new SetRank();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("rank")){
            if(!(sender instanceof Player)){
                sender.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Du kan ikke gøre dette fra konsollen!"));
                return true;
            }
            Player player = (Player) sender;
            if(!player.isOp()){
                player.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&cDu har ikke adgang til dette!"));
                return true;
            }

            if(args.length == 0){
                helpArguments(player);
                return true;
            }

            String arg = args[0];
            List<String> argList = new ArrayList<>();
            for(int i = 0; i < args.length; i++){
                if(i == 0){
                    continue;
                }
                argList.add(args[i]);
            }

            if(arg.equalsIgnoreCase("set")){
                setRank.execute(sender, argList.toArray(new String[0]));
            }
        }
        return true;
    }

    private void helpArguments(Player player){
        player.sendMessage("");
        player.sendMessage(ChatHandler.format("&bTilgængelige argumenter:"));
        player.sendMessage(ChatHandler.format(" &8&l* &7/skywars start &8| &eStart et game som normalt"));
        player.sendMessage(ChatHandler.format(" &8&l* &7/skywars forcestart &8| &eTeleporterer alle med det samme"));
        player.sendMessage(ChatHandler.format(" &8&l* &7/skywars setlobby &8| &eLav lokation for lobby spawn"));
        player.sendMessage(ChatHandler.format(" &8&l* &7/skywars setpoint &8| &eSæt et spawnpoint for spillere"));
        player.sendMessage("");
    }
}
