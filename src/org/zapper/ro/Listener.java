package org.zapper.ro;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;


public class Listener implements org.bukkit.event.Listener {
    public static int var = 1;
    Runtime rt = Runtime.getRuntime();
    float uwu = (float) 0.5;
   /* @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event ) throws IOException, InterruptedException {
     Player p = event.getEntity().getPlayer();
     p.sendMessage("test");
     rt.exec("curl -basic http://192.168.0.138/2/on");
        TimeUnit.SECONDS.sleep(1);
     rt.exec("curl -basic http://192.168.0.138/2/off");

    }
    @EventHandler
    public void PlayerDamageEvent(EntityDamageEvent event) throws IOException, InterruptedException {
        Player p = (Player) event.getEntity();
        if(p.isOp() == true && var == 1){
            rt.exec("curl -basic http://192.168.0.138/2/on");
            TimeUnit.SECONDS.sleep((long) uwu);
            rt.exec("curl -basic http://192.168.0.138/2/off");
        }

    }*/
    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event){
      double wb =  Bukkit.getServer().getWorld("world").getWorldBorder().getSize();
        Player p = event.getPlayer();
        World world = Bukkit.getServer().getWorld("world");
        Location x = new Location(world,wb/2-2,p.getLocation().getY(),p.getLocation().getZ(),90,0);
        Location xmin = new Location(world,(wb/2-2)*-1,p.getLocation().getY(),p.getLocation().getZ(),-90,0);
        Location z = new Location(world,p.getLocation().getX(),p.getLocation().getY(),wb/2-2,180,0);
        Location zmin = new Location(world,p.getLocation().getX(),p.getLocation().getY(),(wb/2-2)*-1,0,0);

        if(p.getLocation().getBlockX() == wb/2-1 ) p.teleport(xmin);
        else if(p.getLocation().getBlockX() == (wb/2-1)*-1 ) p.teleport(x);
        else if(p.getLocation().getBlockZ() == wb/2-1 ) p.teleport(zmin);
        else if(p.getLocation().getBlockZ() == (wb/2-1)*-1 ) p.teleport(z);
    }
}
