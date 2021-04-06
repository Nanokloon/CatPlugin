package org.catplugin.eu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Functions {
    public static ItemStack makeArrow(String s) {
        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        ItemMeta arrowMeta = arrow.getItemMeta();
        arrowMeta.setDisplayName(ChatColor.WHITE+ s);
        arrow.setItemMeta(arrowMeta);
        return arrow;
    }
}
