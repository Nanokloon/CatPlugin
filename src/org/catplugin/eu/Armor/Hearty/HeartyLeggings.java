package org.catplugin.eu.Armor.Hearty;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.catplugin.eu.Main;

import java.util.Objects;
import java.util.UUID;

import static org.catplugin.eu.Functions.makeArrow;

public class HeartyLeggings {
    public static void init(){

        ItemMeta heartyMeta = Main.heartyLeggings.getItemMeta();
        NamespacedKey heartyKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hearty");

        heartyMeta.setDisplayName(ChatColor.RED + "♥ Hearty Leggings ♥");
        AttributeModifier hpModifier = new AttributeModifier(UUID.randomUUID(),"generic.maxHealth",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        AttributeModifier armorModifier = new AttributeModifier(UUID.randomUUID(),"generic.armor",6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        AttributeModifier toughModifier = new AttributeModifier(UUID.randomUUID(),"generic.armorToughness",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);

        heartyMeta.getPersistentDataContainer().set(heartyKey, PersistentDataType.INTEGER, 1);

        heartyMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,hpModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR,armorModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,toughModifier);
        Main.heartyLeggings.setItemMeta(heartyMeta);


    }
    public static ShapedRecipe makeRecipe(){
        NamespacedKey z = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hLegs");
        ShapedRecipe heartyRecipe = new ShapedRecipe(z, Main.heartyLeggings);
        heartyRecipe.shape("YYY", "X X", "X X");
        heartyRecipe.setIngredient('X', Material.DIAMOND);
        heartyRecipe.setIngredient('Y',Material.DIAMOND_BLOCK);
        return heartyRecipe;

    }
    public static void makeGuide(){
        Main.heartyLegsGuide = Bukkit.createInventory(null, 45, "CraftGuide");


        for (int i = 0; i < 45; i++) {
            Main.heartyLegsGuide.setItem(i, Main.menuGlass);
        }
        Main.heartyLegsGuide.setItem(10, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyLegsGuide.setItem(11, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyLegsGuide.setItem(12, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyLegsGuide.setItem(19, new ItemStack(Material.DIAMOND, 1));
        Main.heartyLegsGuide.setItem(20, new ItemStack(Material.DIAMOND_BLOCK, 0));
        Main.heartyLegsGuide.setItem(21, new ItemStack(Material.DIAMOND, 1));
        Main.heartyLegsGuide.setItem(25, Main.heartyLeggings);
        Main.heartyLegsGuide.setItem(28, new ItemStack(Material.DIAMOND, 1));
        Main.heartyLegsGuide.setItem(29, new ItemStack(Material.STICK, 0));
        Main.heartyLegsGuide.setItem(30, new ItemStack(Material.DIAMOND, 1));

        Main.heartyLegsGuide.setItem(36, makeArrow("Back (Hearty Boots)"));
        Main.heartyLegsGuide.setItem(44, makeArrow("Next (Hearty Chestplate)"));

    }
}
