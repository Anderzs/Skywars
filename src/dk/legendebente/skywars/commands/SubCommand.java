package dk.legendebente.skywars.commands;

import org.bukkit.command.CommandSender;

import java.io.IOException;

public abstract class SubCommand {

    public abstract void execute(CommandSender sender, String[] args) throws IOException;


}
