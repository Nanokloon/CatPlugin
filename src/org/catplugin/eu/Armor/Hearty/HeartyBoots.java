package org.catplugin.eu.Armor.Hearty;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.catplugin.eu.Main;

import java.util.Objects;
import java.util.UUID;

import static org.catplugin.eu.Functions.makeArrow;

public class HeartyBoots {
    public static void init(){

        ItemMeta heartyMeta = Main.heartyBoots.getItemMeta();
        heartyMeta.setDisplayName(ChatColor.RED + "♥ Hearty Boots ♥");
        NamespacedKey heartyKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hearty");
        AttributeModifier hpModifier = new AttributeModifier(UUID.randomUUID(),"generic.maxHealth",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier armorModifier = new AttributeModifier(UUID.randomUUID(),"generic.armor",3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        AttributeModifier toughModifier = new AttributeModifier(UUID.randomUUID(),"generic.armorToughness",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);

        heartyMeta.getPersistentDataContainer().set(heartyKey, PersistentDataType.INTEGER, 1);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,hpModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR,armorModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,toughModifier);
        Main.heartyBoots.setItemMeta(heartyMeta);


    }
    public static ShapedRecipe makeRecipe(){
        NamespacedKey z = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hBoots");
        ShapedRecipe heartyRecipe = new ShapedRecipe(z, Main.heartyBoots);
        heartyRecipe.shape("X X", "Y Y", "   ");
        heartyRecipe.setIngredient('X', Material.DIAMOND);
        heartyRecipe.setIngredient('Y',Material.DIAMOND_BLOCK);
        return heartyRecipe;
    }
    public static void makeGuide(){
        Main.heartyBootsGuide = Bukkit.createInventory(null, 45, "CraftGuide");


        for (int i = 0; i < 45; i++) {
            Main.heartyBootsGuide.setItem(i, Main.menuGlass);
        }
        Main.heartyBootsGuide.setItem(10, new ItemStack(Material.DIAMOND, 1));
        Main.heartyBootsGuide.setItem(11, new ItemStack(Material.DIAMOND_BLOCK, 0));
        Main.heartyBootsGuide.setItem(12, new ItemStack(Material.DIAMOND, 1));
        Main.heartyBootsGuide.setItem(19, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyBootsGuide.setItem(20, new ItemStack(Material.DIAMOND_BLOCK, 0));
        Main.heartyBootsGuide.setItem(21, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyBootsGuide.setItem(25, Main.heartyBoots);
        Main.heartyBootsGuide.setItem(28, new ItemStack(Material.CHICKEN, 0));
        Main.heartyBootsGuide.setItem(29, new ItemStack(Material.STICK, 0));
        Main.heartyBootsGuide.setItem(30, new ItemStack(Material.CHICKEN, 0));

        Main.heartyBootsGuide.setItem(36, makeArrow("Back (Cannon)"));
        Main.heartyBootsGuide.setItem(44, makeArrow("Next (Hearty Leggings)"));

    }
}
