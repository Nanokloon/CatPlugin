
package org.catplugin.eu.Commands;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.catplugin.eu.Mobs.NetheriteDefender;
import org.jetbrains.annotations.NotNull;

public class TestCmd implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ItemStack netheriteAxe = new ItemStack(Material.NETHERITE_AXE, 1);
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(netheriteAxe);
        Player p = (Player)commandSender;
        World world = p.getWorld();
        p.getLocation().getWorld();
        NetheriteDefender netheriteDefender = new NetheriteDefender(p.getLocation(), p);
        WorldServer worldServer = ((CraftWorld)p.getWorld()).getHandle();
        worldServer.addEntity(netheriteDefender);
        return false;
    }
}
