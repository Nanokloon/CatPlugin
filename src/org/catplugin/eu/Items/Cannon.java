package org.catplugin.eu.Items;

import com.mojang.datafixers.types.Func;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.catplugin.eu.Functions;
import org.catplugin.eu.Main;

import java.util.Arrays;
import java.util.Objects;

public class Cannon {
    public static void init(){
        ItemMeta ballswordmeta = Main.ballSword.getItemMeta();
        NamespacedKey ballKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "ballz");
        ballswordmeta.setDisplayName(ChatColor.DARK_RED + "Cannon");
        ballswordmeta.getPersistentDataContainer().set(ballKey, PersistentDataType.INTEGER, 1);
        ballswordmeta.setLore(Arrays.asList(ChatColor.RED + "Bestows great power", ChatColor.RED + "to its wielder", ChatColor.BOLD + "RIGHT CLICK FOR FIREBALL (LIMITED USE)"));
        Damageable damage = (Damageable) ballswordmeta;
        damage.setDamage(1831);
        Main.ballSword.setItemMeta((ItemMeta) damage);
    }

    public static ShapedRecipe makeRecipe(){
        NamespacedKey z = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "boomhoe");
        ShapedRecipe ballrecipe = new ShapedRecipe(z, Main.ballSword);
        ballrecipe.shape("MNM", "PNP", " R ");
        ballrecipe.setIngredient('M', Material.FIRE_CHARGE);
        ballrecipe.setIngredient('N', Material.NETHERITE_BLOCK);
        ballrecipe.setIngredient('R', Material.BLAZE_ROD);
        ballrecipe.setIngredient('P', Material.BLAZE_POWDER);
        return ballrecipe;
    }
    public static void makeGuide(){
        Main.cannonGuide = Bukkit.createInventory(null, 45, "CraftGuide");

        for (int j = 0; j < 45; j++) {
            Main.cannonGuide.setItem(j, Main.menuGlass);
        }
        Main.cannonGuide.setItem(10, new ItemStack(Material.FIRE_CHARGE, 1));
        Main.cannonGuide.setItem(11, new ItemStack(Material.NETHERITE_BLOCK, 1));
        Main.cannonGuide.setItem(12, new ItemStack(Material.FIRE_CHARGE, 1));
        Main.cannonGuide.setItem(19, new ItemStack(Material.BLAZE_POWDER, 1));
        Main.cannonGuide.setItem(20, new ItemStack(Material.NETHERITE_BLOCK, 1));
        Main.cannonGuide.setItem(21, new ItemStack(Material.BLAZE_POWDER, 1));
        Main.cannonGuide.setItem(25, Main.ballSword);
        Main.cannonGuide.setItem(28, new ItemStack(Material.NETHER_BRICK, 0));
        Main.cannonGuide.setItem(29, new ItemStack(Material.BLAZE_ROD, 1));
        Main.cannonGuide.setItem(30, new ItemStack(Material.NETHERITE_AXE, 0));

        Main.cannonGuide.setItem(36, Functions.makeArrow("Back (Chicken Slayer)"));
        Main.cannonGuide.setItem(44, Functions.makeArrow("Next (Hearty Boots)"));


    }
}
