package org.catplugin.eu.Items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.catplugin.eu.Main;

import java.util.Objects;

public class NetheriteApple {
    public static void init(){
        ItemMeta chickenMeta = Main.netheriteApple.getItemMeta();
        NamespacedKey chickenKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "netheriteapple");
        chickenMeta.setDisplayName(ChatColor.DARK_RED + "Netherite Apple");
        chickenMeta.getPersistentDataContainer().set(chickenKey, PersistentDataType.INTEGER, 1);
        Main.netheriteApple.setItemMeta(chickenMeta);
    }
}
