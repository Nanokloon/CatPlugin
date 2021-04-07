package Boss;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.catplugin.eu.Armor.HeartyChestplate;

public class WitherSkele extends EntitySkeletonWither {
    public WitherSkele(Location loc, Player player) {
        super(EntityTypes.WITHER_SKELETON, ((CraftWorld) loc.getWorld()).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.setSprinting(true);
        this.setHealth(50.0F);
        this.setAggressive(true);


    }
    @Override
    protected void initPathfinder() {
        this.goalSelector.a(1, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));

    }
}
