package dk.legendebente.skywars.commands;

import dk.legendebente.skywars.Skywars;
import dk.legendebente.skywars.chathandler.ChatHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SkywarsCommand implements CommandExecutor {

    private StartCommand startCommand;
    private ListCommand listCommand;
    private LobbyPointCommand lobbyPointCommand;

    public SkywarsCommand(){
        this.startCommand = new StartCommand();
        this.listCommand = new ListCommand();
        this.lobbyPointCommand = new LobbyPointCommand();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("skywars")){
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

            //TODO: argList.toArray(new String[0]

            if(arg.equalsIgnoreCase("start")){
                this.startCommand.execute(sender, null);
            } else if(arg.equalsIgnoreCase("setlobby")){
                this.lobbyPointCommand.execute(sender, null);
            } else if(arg.equalsIgnoreCase("list")){
                this.listCommand.execute(sender, null);
            } else if(arg.equalsIgnoreCase("help") || arg.equalsIgnoreCase("hjælp")){
                helpArguments(player);
            } else {
                player.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&cInvalid Argument"));
                player.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&7Brug &e/skywars help &7for hjælp"));
            }
        }
        return true;
    }

    private void helpArguments(Player player){
        player.sendMessage(ChatHandler.format(Skywars.getPrefix() + "&bTilgængelige argumenter:"));
        player.sendMessage(ChatHandler.format(" &8&l* &7/skywars start &8| &eStart et game som normalt"));
        player.sendMessage(ChatHandler.format(" &8&l* &7/skywars forcestart &8| &eTeleporterer alle med det samme"));
        player.sendMessage(ChatHandler.format(" &8&l* &7/skywars setlobby &8| &eLav lokation for lobby spawn"));
    }

}
