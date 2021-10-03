package org.catplugin.eu.Mobs;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.EntitySkeletonWither;
import net.minecraft.world.level.World;
import org.bukkit.Location;

public class WitherSkele {//extends EntitySkeletonWither {
    public WitherSkele(Location loc) {
//        super(EntityTypes.ba, ((CraftWorld) loc.getWorld()).getHandle());
//        super(EntityTypes.ba, ((World) loc.getWorld()).);
      /*  this.setCustomName(new ChatComponentText(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wither Skeleton BOSS"));
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
        this.setSlot(EnumItemSlot., CraftItemStack.asNMSCopy(netheriteSword));
        this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(helm));
        this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(chestplate));
        this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(leggings));
        this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));
       /* for (Player player1 : Bukkit.getOnlinePlayers()) {
            this.setGoalTarget((EntityLiving) ((CraftPlayer) player1).getHandle());
        }
        this.
        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));*/
       // this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D));
        //this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
       // this.setGoalTarget(((CraftPlayer) player).getHandle());

    }
}
