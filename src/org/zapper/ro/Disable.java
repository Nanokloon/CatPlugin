package org.zapper.ro;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static java.lang.Runtime.*;

public class Disable implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Runtime rt = getRuntime();
        if (Listener.var == 1) {
            Listener.var = 0;
            try {
                rt.exec("curl -basic http://192.168.0.138/2/off");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (Listener.var == 0) {
            Listener.var = 1;
        }
        Player p = (Player) commandSender;
        Bukkit.getServer().broadcastMessage("Zapper toggled");
        return false;
    }
}
