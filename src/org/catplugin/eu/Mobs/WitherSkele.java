package org.catplugin.eu.Mobs;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import java.util.List;

public class WitherSkele extends EntitySkeletonWither {
    public WitherSkele(Location loc, Player player) {
        super(EntityTypes.WITHER_SKELETON, ((CraftWorld) loc.getWorld()).getHandle());
        this.setCustomName(new ChatComponentText(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wither Skeleton BOSS"));
        this.setCustomNameVisible(true);
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setSprinting(true);
        this.setHealth(50.0F);
        this.setAggressive(true);
        org.bukkit.inventory.ItemStack netheriteSword = new org.bukkit.inventory.ItemStack(org.bukkit.Material.NETHERITE_AXE, 1);
        org.bukkit.inventory.ItemStack helm = new org.bukkit.inventory.ItemStack(Material.NETHERITE_HELMET, 1);
        org.bukkit.inventory.ItemStack chestplate = new org.bukkit.inventory.ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        org.bukkit.inventory.ItemStack leggings = new org.bukkit.inventory.ItemStack(org.bukkit.Material.NETHERITE_LEGGINGS, 1);
        org.bukkit.inventory.ItemStack boots = new org.bukkit.inventory.ItemStack(Material.NETHERITE_BOOTS, 1);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(netheriteSword));
        this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(helm));
        this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chestplate));
        this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(leggings));
        this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            this.setGoalTarget((EntityLiving) ((CraftPlayer) player1).getHandle());
        }


    }
    @Override
    protected void initPathfinder() {
        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.goalSelector.a(1, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(2, new PathfinderGoalRandomLookaround(this));

    }
}
