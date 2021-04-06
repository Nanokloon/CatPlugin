package org.catplugin.eu;


import com.codingforcookies.armorequip.ArmorEquipEvent;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.Objects;

import static org.catplugin.eu.Functions.getGuild;
import static org.catplugin.eu.Main.*;


public class Listener implements org.bukkit.event.Listener {
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
            if (e.getCurrentItem() != null){
                if (e.getCurrentItem().hasItemMeta())
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("Next")) {
                        e.getWhoClicked().openInventory(Main.cannonGuide);
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("Back")) {
                        e.getWhoClicked().openInventory(Main.craftGuide);
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
    public void PlayerJoinEvent(PlayerJoinEvent event) throws InterruptedException {
        String playerName = event.getPlayer().getName();
        MessageChannel channel = Main.jda.awaitReady().getGuildById("712092358711181325").getTextChannelById("812370653223190568");
        if(owo==false) {
            channel.sendMessage(playerName + " has joined the SMP.").queue();
            owo=true;
        }
        else{
            owo=false;
        }
    }

    @EventHandler
    public void PlayerLeaveEvent(PlayerQuitEvent event) throws InterruptedException {
        String playerName = event.getPlayer().getName();
        MessageChannel channel = Main.jda.awaitReady().getGuildById("712092358711181325").getTextChannelById("812370653223190568");
        if (uwu == false) {
            channel.sendMessage(playerName + " has left the SMP.").queue();
            uwu=true;
        }
        else{
            uwu=false;
        }
    }


}
