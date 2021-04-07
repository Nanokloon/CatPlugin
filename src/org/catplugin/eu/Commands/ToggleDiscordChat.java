package org.catplugin.eu.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.catplugin.eu.Main.on;

public class ToggleDiscordChat implements CommandExecutor {
    @Override
    public boolean onCommand( CommandSender commandSender,  Command command,  String s,  String[] strings) {
        if(on==true){
            on=false;
        }
        else{
            on=true;
        }
        return true;
    }
}
