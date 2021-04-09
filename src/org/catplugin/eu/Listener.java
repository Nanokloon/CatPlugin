package org.catplugin.eu;


import net.dv8tion.jda.api.entities.MessageChannel;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.catplugin.eu.Mobs.NetheriteDefender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.catplugin.eu.Main.*;


public class Listener implements org.bukkit.event.Listener {
    public boolean ranOnce = false;
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event ) {
        Player p = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack sword = p.getInventory().getItemInMainHand();
            NamespacedKey itemKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "ballz");
            if (sword.hasItemMeta()) {
                ItemMeta itemMeta = sword.getItemMeta();
                    PersistentDataContainer container = itemMeta.getPersistentDataContainer();
                    if (container.has(itemKey, PersistentDataType.INTEGER)) {
                        if (container.get(itemKey, PersistentDataType.INTEGER) == 1) {
                            p.launchProjectile(Fireball.class);
                            
                            Damageable damage = (Damageable) sword.getItemMeta();
                            damage.setDamage(damage.getDamage() + 1);
                            sword.setItemMeta((ItemMeta) damage);

                            if (damage.getDamage() == 2031) {
                                p.getInventory().getItemInMainHand().setAmount(0);
                            } else {
                                p.getInventory().setItemInMainHand(sword);
                            }
                        }
                    }
            }
        }
    }
    @EventHandler
    public void PlayerAttackEvent(EntityDamageByEntityEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK) || e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)) {
            if (e.getDamager().getType().equals(EntityType.PLAYER)) {
                Player p = (Player) e.getDamager();
                if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)) {
                    ItemStack sword = p.getInventory().getItemInMainHand();
                    NamespacedKey itemKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "onlychickens");
                    NamespacedKey banKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "banhammer");
                    if (sword.hasItemMeta()) {
                        ItemMeta itemMeta = sword.getItemMeta();
                        if (itemMeta.getPersistentDataContainer() != null) {
                            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
                            if (container.has(itemKey, PersistentDataType.INTEGER)) {
                                int foundvalue = container.get(itemKey, PersistentDataType.INTEGER);
                                if (foundvalue == 1) {
                                    if (e.getEntity().getType().equals(EntityType.CHICKEN)) {

                                    } else {
                                        //p.getPlayer().sendMessage("U RLY TRIED LOL");
                                        e.setCancelled(true);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }
    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) {
        InventoryView i = e.getView();
        if (i.getTitle().equals("CraftGuide")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().hasItemMeta()) {
                    String string =ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
                    switch(string) {
                        case "Next (Cannon)":
                        case "Back (Cannon)":
                            e.getWhoClicked().openInventory(cannonGuide);
                            break;
                        case "Back (Chicken Slayer)":
                            e.getWhoClicked().openInventory(craftGuide);
                            break;
                        case "Next (Hearty Boots)":
                        case "Back (Hearty Boots)":
                            e.getWhoClicked().openInventory(heartyBootsGuide);
                            break;
                        case "Next (Hearty Leggings)":
                        case "Back (Hearty Leggings)":
                            e.getWhoClicked().openInventory(heartyLegsGuide);
                            break;
                        case "Next (Hearty Chestplate)":
                        case "Back (Hearty Chestplate)":
                            e.getWhoClicked().openInventory(heartyChestplateGuide);
                            break;
                        case "Next (Hearty Helmet)":
                        case "Back (Hearty Helmet)":
                            e.getWhoClicked().openInventory(heartyHelmetGuide);
                            break;
                    }
                }
            }
        }
        if(e.getClickedInventory()!=null){
        if(e.getClickedInventory().getType().equals(InventoryType.SMITHING)) {
            SmithingInventory smithingInventory = (SmithingInventory) e.getClickedInventory();
            NamespacedKey heartyKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "hearty");
            
            if (e.getClickedInventory().getItem(0)!=null) {
                if (e.getClickedInventory().getItem(0).getItemMeta().getPersistentDataContainer().has(heartyKey, PersistentDataType.INTEGER)) {
                    if (e.getClickedInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(heartyKey, PersistentDataType.INTEGER).equals(1)) {
                        if (e.getClickedInventory().getItem(0).getType().equals(Material.DIAMOND_HELMET)) {
                                if(e.getClickedInventory().getItem(2)!=null) {
                                upgradeNetherite(e,netheriteHeartyHelmet);
                            }
                        }
                        else if (e.getClickedInventory().getItem(0).getType().equals(Material.DIAMOND_CHESTPLATE)){
                            if(e.getClickedInventory().getItem(2)!=null) {
                                upgradeNetherite(e,netheriteHeartyChestplate);
                            }
                        }
                        else if (e.getClickedInventory().getItem(0).getType().equals(Material.DIAMOND_LEGGINGS)){
                            if(e.getClickedInventory().getItem(2)!=null) {
                                upgradeNetherite(e,netheriteHeartyLeggings);
                            }
                        }
                        else if (e.getClickedInventory().getItem(0).getType().equals(Material.DIAMOND_BOOTS)){
                            if(e.getClickedInventory().getItem(2)!=null) {
                                upgradeNetherite(e,netheriteHeartyBoots);
                            }
                        }
                    }
                }
            }
            }
        }

        //e.getWhoClicked().sendMessage(e.getInventory().getType().toString());
        if (e.getCurrentItem() == null) {
            return;

        } else {
            if (e.getClickedInventory().getType().equals(InventoryType.ANVIL)) {
                if (e.getInventory().getItem(2) != null) {
                    if (e.getInventory().getItem(2).hasItemMeta()) {
                        ItemMeta oof = e.getInventory().getItem(2).getItemMeta();
                        if (oof.getPersistentDataContainer() != null) {
                            PersistentDataContainer p = oof.getPersistentDataContainer();
                            NamespacedKey itemKey = new NamespacedKey(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("CatPlugin")), "ballz");
                            if (p.has(itemKey, PersistentDataType.INTEGER)) {
                                if (p.get(itemKey, PersistentDataType.INTEGER).equals(1)) {
                                    e.setCancelled(true);
                                    e.getWhoClicked().closeInventory();
                                    e.getWhoClicked().sendMessage(ChatColor.RED + "You cant fix the cannon ...");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void PlayerEatEvent(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        ItemStack eatenItem =  e.getItem();
        eatenItem.setAmount(1);
        if ( eatenItem.equals(Main.netheriteApple)){
            PotionEffect pot1 = new PotionEffect(PotionEffectType.ABSORPTION,10000,10);
            PotionEffect pot2 = new PotionEffect(PotionEffectType.REGENERATION,10000,5);
            PotionEffect pot3 = new PotionEffect(PotionEffectType.SATURATION,1000,3);
            PotionEffect pot4 = new PotionEffect(PotionEffectType.FIRE_RESISTANCE,10000,1);
            PotionEffect pot5 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,10000,3);
            PotionEffect pot6 = new PotionEffect(PotionEffectType.SLOW,10000,2);

            p.addPotionEffect(pot1);
            p.addPotionEffect(pot2);
            p.addPotionEffect(pot3);
            p.addPotionEffect(pot4);
            p.addPotionEffect(pot5);

            for (PotionEffect effect : p.getActivePotionEffects()) {
                if(effect.getType().equals(PotionEffectType.POISON) || effect.getType().equals(PotionEffectType.WITHER) || effect.getType().equals(PotionEffectType.SLOW)){
                    p.removePotionEffect(effect.getType());
                }
            }
            p.addPotionEffect(pot6);
        }
    }


    @EventHandler
    public void MessageRecivedEvent(AsyncPlayerChatEvent event) throws InterruptedException, IOException {
        String playerName = event.getPlayer().getName();
        String text = event.getMessage();
        MessageChannel channel = Main.jda.awaitReady().getGuildById("712092358711181325").getTextChannelById("812370653223190568");
        if (on == true) {
            if (run == false) {
                if (text.contains("@everyone") || text.contains("@here")) {
                    text = "Dont @ everyone or i snap ur neck";
                }
                channel.sendMessage(playerName + ": " + text).queue();
                run = true;
            } else {
                run = false;
            }
        }
    }


    @EventHandler
    public void EntityDeathEvent(EntityDeathEvent event){
        if(event.getEntityType().equals(EntityType.VILLAGER) && ChatColor.stripColor(event.getEntity().getCustomName()).equals( "Sewerslide Villager")){
            Location l = event.getEntity().getLocation();
            WorldServer worldServer = ((CraftWorld)event.getEntity().getWorld()).getHandle();
            TNTPrimed tnt = (TNTPrimed) event.getEntity().getWorld().spawnEntity(l,EntityType.PRIMED_TNT);
            tnt.setFuseTicks(20);
        }
    }

    @EventHandler
    public void PlayerBlockBreakEvent(BlockBreakEvent e){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 11);

        Player p = e.getPlayer();
        Location l = e.getBlock().getLocation();
        p.sendMessage(String.valueOf(randomNum));
        if(randomNum == 5 && e.getBlock().getType().equals(Material.ANCIENT_DEBRIS)){
            NetheriteDefender netheriteDefender = new NetheriteDefender(l);
            netheriteDefender.setHealth(60.0F);

            WorldServer worldServer = ((CraftWorld)p.getWorld()).getHandle();
            worldServer.addEntity(netheriteDefender);
            p.playSound(l,Sound.ITEM_TRIDENT_THUNDER,SoundCategory.HOSTILE,10,10);
        }
       /* l.add(-5,4,5);
        l.getBlock().setType(Material.DIAMOND_BLOCK);
        l.add(0,-4,0);
        Location l0 = l;

        l0.add(0,-1,0);
        Location l1 = l;

        Location l2 = l;
        l2.add(0,1,0);
        Block y0[][] = new Block[11][11];
        Block y1[][] = new Block[11][11];
        Block y2[][] = new Block[11][11];

        for(int i = 0 ; i<11 ; i++){
            for(int j = 0 ; j <11 ; j++){
                y0[i][j]=l0.getBlock();
                y1[i][j]=l1.getBlock();
                y2[i][j]=l2.getBlock();
                Bukkit.broadcastMessage(y0[i][j].getType() +"  " + y1[i][j].getType() + " "+ y2[i][j].getType() +" " + i + " " + j + " "  + l0.getBlockX()+ " "+ l0.getBlockZ());

                l0.getBlock().setType(Material.BEDROCK);
                l1.getBlock().setType(Material.BEDROCK);
                l2.getBlock().setType(Material.BEDROCK);

                l0.add(0.3,0,0);
                l1.add(0.3,0,0);
                l2.add(0.3,0,0);

            }
            l0.add(3.6,0,0.3);
            l1.add(3.6,0,0.3);
            l2.add(3.6,0,0.3);
        }

        for(int i = 0; i <11; i ++){
            for(int j = 0 ; j < 11 ; j++){
                if(!y0[i][j].getType().isAir() ){
                    if(y1[i][j].getType().isAir() && y2[i][j].getType().isAir()) {
                        NetheriteDefender netheriteDefender = new NetheriteDefender(y1[i][j].getLocation());
                        netheriteDefender.setNoAI(true);
                    }
                }
            }
        }*/



    }

    public void upgradeNetherite(InventoryClickEvent e , ItemStack upgradeStack){
        ItemMeta meta = e.getClickedInventory().getItem(2).getItemMeta();
        Damageable damageable = (Damageable) meta;
        ItemStack nethHelm = upgradeStack;
        Map<Enchantment, Integer> enchantmentMap = meta.getEnchants();
        Set<Enchantment> enchantments = enchantmentMap.keySet();
        Object ench[] = enchantments.toArray();
        ItemMeta nethmeta = nethHelm.getItemMeta();
        for(int j = 0 ; j<ench.length;j++) {
            nethmeta.addEnchant((Enchantment) ench[j],enchantmentMap.get(ench[j]),true);
        }
        Damageable nethDmg = (Damageable) nethmeta;
        nethDmg.setDamage(damageable.getDamage());

        e.getClickedInventory().getItem(3).setItemMeta((ItemMeta) nethDmg);
    }



}
