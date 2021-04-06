package org.catplugin.eu.Armor.Hearty;

import com.google.common.collect.Multimap;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import net.minecraft.server.v1_16_R3.NBTTagString;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.catplugin.eu.Main;

import java.util.Objects;
import java.util.UUID;

import static org.catplugin.eu.Functions.makeArrow;

public class HeartyChestplate {
    public static void init(){

        ItemMeta heartyMeta = Main.heartyChestplate.getItemMeta();
        NamespacedKey heartyKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hearty");

        heartyMeta.setDisplayName(ChatColor.RED + "♥ Hearty Chestplate ♥");
        AttributeModifier hpModifier = new AttributeModifier(UUID.randomUUID(),"generic.maxHealth",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        AttributeModifier armorModifier = new AttributeModifier(UUID.randomUUID(),"generic.armor",8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        AttributeModifier toughModifier = new AttributeModifier(UUID.randomUUID(),"generic.armorToughness",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);

        heartyMeta.getPersistentDataContainer().set(heartyKey, PersistentDataType.INTEGER, 1);

        heartyMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,hpModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR,armorModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,toughModifier);
        Main.heartyChestplate.setItemMeta(heartyMeta);


    }
    public static ShapedRecipe makeRecipe(){
        NamespacedKey z = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hChest");
        ShapedRecipe heartyRecipe = new ShapedRecipe(z, Main.heartyChestplate);
        heartyRecipe.shape("X X", "YXY", "YXY");
        heartyRecipe.setIngredient('X', Material.DIAMOND);
        heartyRecipe.setIngredient('Y',Material.DIAMOND_BLOCK);
        return heartyRecipe;

    }
    public static void makeGuide(){
        Main.heartyChestplateGuide = Bukkit.createInventory(null, 45, "CraftGuide");


        for (int i = 0; i < 45; i++) {
            Main.heartyChestplateGuide.setItem(i, Main.menuGlass);
        }
        Main.heartyChestplateGuide.setItem(10, new ItemStack(Material.DIAMOND, 1));
        Main.heartyChestplateGuide.setItem(11, new ItemStack(Material.DIAMOND_BLOCK, 0));
        Main.heartyChestplateGuide.setItem(12, new ItemStack(Material.DIAMOND, 1));
        Main.heartyChestplateGuide.setItem(19, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyChestplateGuide.setItem(20, new ItemStack(Material.DIAMOND, 1));
        Main.heartyChestplateGuide.setItem(21, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyChestplateGuide.setItem(25, Main.heartyChestplate);
        Main.heartyChestplateGuide.setItem(28, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyChestplateGuide.setItem(29, new ItemStack(Material.DIAMOND, 1));
        Main.heartyChestplateGuide.setItem(30, new ItemStack(Material.DIAMOND_BLOCK, 1));

        Main.heartyChestplateGuide.setItem(36, makeArrow("Back (Hearty Leggings)"));
        Main.heartyChestplateGuide.setItem(44, makeArrow("Next (Hearty Helmet)"));

    }

}
