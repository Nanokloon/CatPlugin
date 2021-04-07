package Boss;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.catplugin.eu.Mobs.WitherSkele;

public class SpawnBoss implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("spawnboss") && commandSender instanceof Player) {
            Player player = (Player) commandSender;
            org.catplugin.eu.Mobs.WitherSkele skele = new WitherSkele(player.getLocation(), player);
            WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
            world.addEntity(skele);
        }
        return false;
    }
}
