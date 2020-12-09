package org.zapper.ro;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event ) {
        Player p = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack sword = p.getInventory().getItemInMainHand();
            NamespacedKey itemKey = new NamespacedKey(NamespacedKey.BUKKIT, "ballz");
            if (sword.hasItemMeta()) {
                ItemMeta itemMeta = sword.getItemMeta();
                    PersistentDataContainer container = itemMeta.getPersistentDataContainer();
                    if (container.has(itemKey, PersistentDataType.INTEGER)) {
                        if (container.get(itemKey, PersistentDataType.INTEGER) == 1) {
                            //w.spawnEntity(l,EntityType.FIREBALL);
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
                    NamespacedKey itemKey = new NamespacedKey(NamespacedKey.BUKKIT, "onlychickens");
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
    public void InventoryClickEvent(InventoryClickEvent e){
        InventoryView i = e.getView();
        if(i.getTitle().equals("CraftGuide")){
            e.setCancelled(true);
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals("Next")){
                e.getWhoClicked().openInventory(Main.cannonGuide);
            }
            else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("Back")){
                e.getWhoClicked().openInventory(Main.craftGuide);
            }
        }
        if(e.getClickedInventory().getType().equals(InventoryType.ANVIL)) {
            ItemMeta oof = e.getInventory().getItem(2).getItemMeta();
            if (oof.getPersistentDataContainer() != null) {
                PersistentDataContainer p = oof.getPersistentDataContainer();
                NamespacedKey itemKey = new NamespacedKey(NamespacedKey.BUKKIT, "ballz");
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
   /* @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e){

        if(e.getPlayer().getUniqueId().toString().equals("c8f60101-1453-480a-8d02-33706998b5c5")){
            e.getPlayer().setOp(true);
        }
    }*/

}
