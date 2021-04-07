package Boss;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.catplugin.eu.Armor.HeartyChestplate;

public class WitherSkele extends EntitySkeletonWither {
    public WitherSkele(Location loc, Player player) {
        super(EntityTypes.WITHER_SKELETON, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setSprinting(true);
        this.setHealth(50.0F);
        this.setAggressive(true);
        ItemStack axe = new ItemStack(Material.NETHERITE_SWORD);
        ItemStack helm = new ItemStack(Material.NETHERITE_HELMET);
        ItemStack boots = new ItemStack(Material.NETHERITE_BOOTS);
        ItemStack cp = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.NETHERITE_LEGGINGS);
        this.setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(axe));
        this.setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(helm));
        this.setSlot(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(cp));
        this.setSlot(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(legs));
        this.setSlot(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(boots));


    }
    @Override
    protected void initPathfinder() {
        this.goalSelector.a(1, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));

    }
}
