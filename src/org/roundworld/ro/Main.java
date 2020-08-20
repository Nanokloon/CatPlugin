package org.roundworld.ro;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
    getLogger().info("uw Moeder");
    getServer().getPluginManager().registerEvents(new Listener(), this);
    //this.getCommand("ww").setExecutor(new Disable());
    }
}
