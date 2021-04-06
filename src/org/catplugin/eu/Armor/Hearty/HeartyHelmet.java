package org.catplugin.eu.Armor.Hearty;

import com.google.common.collect.Multimap;
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

public class HeartyHelmet {
    public static void init(){

        ItemMeta heartyMeta = Main.heartyHelmet.getItemMeta();
        heartyMeta.setDisplayName(ChatColor.RED + "♥ Hearty Helmet ♥");
        NamespacedKey heartyKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hearty");
        AttributeModifier hpModifier = new AttributeModifier(UUID.randomUUID(),"generic.maxHealth",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        AttributeModifier armorModifier = new AttributeModifier(UUID.randomUUID(),"generic.armor",3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        AttributeModifier toughModifier = new AttributeModifier(UUID.randomUUID(),"generic.armorToughness",2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);

        heartyMeta.getPersistentDataContainer().set(heartyKey, PersistentDataType.INTEGER, 1);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH,hpModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR,armorModifier);
        heartyMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,toughModifier);
        Main.heartyHelmet.setItemMeta(heartyMeta);


    }
    public static ShapedRecipe makeRecipe(){
        NamespacedKey z = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hHelm");
        ShapedRecipe helmRecipe = new ShapedRecipe(z, Main.heartyHelmet);
        helmRecipe.shape("XXX", "Y Y", "   ");
        helmRecipe.setIngredient('X', Material.DIAMOND);
        helmRecipe.setIngredient('Y',Material.DIAMOND_BLOCK);
        return helmRecipe;

    }
    public static void makeGuide(){
        Main.heartyHelmetGuide = Bukkit.createInventory(null, 45, "CraftGuide");


        for (int i = 0; i < 45; i++) {
            Main.heartyHelmetGuide.setItem(i, Main.menuGlass);
        }
        Main.heartyHelmetGuide.setItem(10, new ItemStack(Material.DIAMOND, 1));
        Main.heartyHelmetGuide.setItem(11, new ItemStack(Material.DIAMOND, 1));
        Main.heartyHelmetGuide.setItem(12, new ItemStack(Material.DIAMOND, 1));
        Main.heartyHelmetGuide.setItem(19, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyHelmetGuide.setItem(20, new ItemStack(Material.DIAMOND_BLOCK, 0));
        Main.heartyHelmetGuide.setItem(21, new ItemStack(Material.DIAMOND_BLOCK, 1));
        Main.heartyHelmetGuide.setItem(25, Main.heartyHelmet);
        Main.heartyHelmetGuide.setItem(28, new ItemStack(Material.CHICKEN, 0));
        Main.heartyHelmetGuide.setItem(29, new ItemStack(Material.STICK, 0));
        Main.heartyHelmetGuide.setItem(30, new ItemStack(Material.CHICKEN, 0));

        Main.heartyHelmetGuide.setItem(36, makeArrow("Back (Hearty Chestplate)"));
        Main.heartyHelmetGuide.setItem(44, makeArrow("Taking suggestions for features / items, post in #suggestions and @nanokloon"));

    }
}
