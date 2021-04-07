package org.catplugin.eu.Mobs;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class SuicidalVillager extends EntityVillager {
    public SuicidalVillager(Location l) {
        super(EntityTypes.VILLAGER,((CraftWorld)l.getWorld()).getHandle());

        org.bukkit.inventory.ItemStack netheriteSword = new org.bukkit.inventory.ItemStack(org.bukkit.Material.NETHERITE_SWORD, 1);
        org.bukkit.inventory.ItemStack netheriteBoots = new org.bukkit.inventory.ItemStack(Material.NETHERITE_BOOTS, 1);
        netheriteBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL,69);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(netheriteSword));
        this.setSlot(EnumItemSlot.FEET,CraftItemStack.asNMSCopy(netheriteBoots));
        this.setAggressive(true);
        this.setPosition(l.getX(), l.getY(), l.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Sewerslide Villager"));
        this.setCustomNameVisible(true);

        this.setHealth(30.0F);

        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityLiving>(this, EntityLiving.class, true));
        this.goalSelector.a(1, new PathfinderGoalLeapAtTarget(this,3.0F));
        //this.goalSelector.a(1, new PathfinderGoalRandomStrollLand(this, 1.0D));
        //this.goalSelector.a(2, new PathfinderGoalRandomLookaround(this));
    }
}
