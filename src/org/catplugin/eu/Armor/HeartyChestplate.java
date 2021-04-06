package org.catplugin.eu.Armor;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import net.minecraft.server.v1_16_R3.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.catplugin.eu.Main;

import java.util.UUID;

public class HeartyChestplate {
    public static void init(){

        ItemMeta chestplateMeta = Main.heartyChestplate.getItemMeta();
        chestplateMeta.setDisplayName(ChatColor.RED + "Hearty Chestplate");
        chestplateMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier(UUID.randomUUID(),"generic.maxHealth",1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));

        Main.heartyChestplate.setItemMeta(chestplateMeta);


    }
}
