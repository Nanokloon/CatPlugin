package org.catplugin.eu.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.catplugin.eu.Main;

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
        else if(what.equals("netherapple")){
            p.getInventory().addItem(Main.netheriteApple);
            p.sendMessage(ChatColor.RED+"Gave Netherite Apple");
        }
        else if(what.equals("heartychest")){
            p.getInventory().addItem(Main.heartyChestplate);
            p.sendMessage(ChatColor.RED+"Gave Hearty Chestplate");
        }
        else if(what.equals("heartyhelmet")){
            p.getInventory().addItem(Main.heartyHelmet);
            p.sendMessage(ChatColor.RED+"Gave Hearty Helmet");
        }else if(what.equals("heartylegs")){
            p.getInventory().addItem(Main.heartyLeggings);
            p.sendMessage(ChatColor.RED+"Gave Hearty Leggings");
        }else if(what.equals("heartyboots")){
            p.getInventory().addItem(Main.heartyBoots);
            p.sendMessage(ChatColor.RED+"Gave Hearty Boots");
        }


        return true;
    }
}
