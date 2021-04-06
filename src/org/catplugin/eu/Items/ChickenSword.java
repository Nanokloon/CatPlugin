package org.catplugin.eu.Items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.catplugin.eu.Main;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class ChickenSword {
    public static void init(){
        ItemMeta chickenSwordmeta = Main.chickenSword.getItemMeta();
        NamespacedKey itemKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "onlychickens");
        chickenSwordmeta.setDisplayName(ChatColor.YELLOW + "CHICKEN SLAYER");
        chickenSwordmeta.getPersistentDataContainer().set(itemKey, PersistentDataType.INTEGER, 1);
        chickenSwordmeta.setLore(Arrays.asList(ChatColor.RED + "WHY DO I EXIST", ChatColor.GREEN + "WHY DO I EXIST", ChatColor.BLUE + "WHY DO I EXIST"));
        Main.chickenSword.setItemMeta(chickenSwordmeta);
        Main.chickenSword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
    }
    public static ShapedRecipe makeRecipe(){
        NamespacedKey key = new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("CatPlugin"), "chickensword");
        ShapedRecipe recipe = new ShapedRecipe(key, Main.chickenSword);
        recipe.shape("BBB", "BBB", "CSC");
        recipe.setIngredient('B', Material.DIAMOND_BLOCK);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('C', Material.CHICKEN);
        return recipe;
    }
    public static void makeGuide(){
        Main.craftGuide = Bukkit.createInventory(null, 45, "CraftGuide");


        for (int i = 0; i < 45; i++) {
            Main.craftGuide.setItem(i, Main.menuGlass);
        }
        Main.craftGuide.setItem(10, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.craftGuide.setItem(11, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.craftGuide.setItem(12, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.craftGuide.setItem(19, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.craftGuide.setItem(20, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.craftGuide.setItem(21, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.craftGuide.setItem(25, Main.chickenSword);
        Main.craftGuide.setItem(28, new ItemStack(Material.CHICKEN, 1));
        Main.craftGuide.setItem(29, new ItemStack(Material.STICK, 1));
        Main.craftGuide.setItem(30, new ItemStack(Material.CHICKEN, 1));

        Main.craftGuide.setItem(44, Main.nextArrow);
    }
}
