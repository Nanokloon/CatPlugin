package org.catplugin.eu;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCustomItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        String what = strings[0];
        if(what.equals("chickensword")){
            p.getInventory().addItem(Main.chickenSword);
            p.sendMessage(ChatColor.RED + "Gave ChickenSword");
        }
        else if(what.equals("cannon")){
            p.getInventory().addItem(Main.ballSword);
            p.sendMessage(ChatColor.RED + "Gave Cannon");
        }

        return true;
    }
}
