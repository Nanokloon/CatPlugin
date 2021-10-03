package org.catplugin.eu.Mobs;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.piglin.EntityPiglin;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

public class NetheriteDefender {//extends EntityPiglin {

    public NetheriteDefender(Location l) {
       // super(EntityTypes.ao, ((CraftWorld)l.getWorld()).getHandle());
        /*ItemStack netheriteAxe = new ItemStack(Material.NETHERITE_AXE, 1);
        ItemStack goldHelm = new ItemStack(Material.GOLDEN_HELMET, 1);
        ItemStack goldChestplate = new ItemStack(Material.GOLDEN_CHESTPLATE, 1);
        ItemStack netheriteLeggings = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
        ItemStack netheriteBoots = new ItemStack(Material.NETHERITE_BOOTS, 1);

        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(netheriteAxe);
        this.setPosition(l.getX(), l.getY(), l.getZ());
        this.setSlot(EnumItemSlot, nmsItem);
        //this.setSlot(EnumItemSlot.OFFHAND, nmsItem);
        this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(goldHelm));
        this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(goldChestplate));
        this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(netheriteLeggings));
        this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(netheriteBoots));
        this.setCustomName(new ChatComponentText(ChatColor.RED + "" + ChatColor.BOLD + "Netherite Defender"));
        this.setCustomNameVisible(true);
        this.setHealth(30.0F);
        this.goalSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
        this.goalSelector.a(1, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.getG.a(2, new PathfinderGoalRandomLookaround(this));*/
    }
}
