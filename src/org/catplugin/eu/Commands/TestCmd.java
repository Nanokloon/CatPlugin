
package org.catplugin.eu.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCmd implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player p = (Player)commandSender;

        p.getLocation().getWorld();

      //  NetheriteDefender netheriteDefender = new NetheriteDefender(p.getLocation());

      //  SuicidalVillager suicidalVillager = new SuicidalVillager(p.getLocation());

       // WorldServer worldServer = ((CraftWorld)p.getWorld()).getHandle();

       // worldServer.addEntity(suicidalVillager);

      //  worldServer.addEntity(netheriteDefender);
        return false;
    }
}
