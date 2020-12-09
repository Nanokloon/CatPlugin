package org.zapper.ro;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftGuide implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        p.openInventory(Main.craftGuide);
        ((Player) commandSender).openInventory(Main.craftGuide);
        return true;
    }
}
