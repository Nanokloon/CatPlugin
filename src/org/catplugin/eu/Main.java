package org.catplugin.eu;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Main extends JavaPlugin {

    public static ItemStack chickenSword=new ItemStack(Material.NETHERITE_SWORD,1);
    public static ItemStack ballSword=new ItemStack(Material.NETHERITE_HOE,1);
    public static ItemStack netheriteApple = new ItemStack(Material.APPLE,1);
    public static Inventory craftGuide;
    public static Inventory cannonGuide;

    @Override
    public void onEnable(){
        getLogger().info("uw Moeder");
        getServer().getPluginManager().registerEvents(new Listener(), this);

        ItemMeta chickenSwordmeta= chickenSword.getItemMeta();
        NamespacedKey itemKey= new NamespacedKey(NamespacedKey.BUKKIT,"onlychickens");
        chickenSwordmeta.setDisplayName(ChatColor.YELLOW+"CHICKEN SLAYER");
        chickenSwordmeta.getPersistentDataContainer().set(itemKey, PersistentDataType.INTEGER,1);
        chickenSwordmeta.setLore(Arrays.asList(ChatColor.RED+"WHY DO I EXIST",ChatColor.GREEN+"WHY DO I EXIST",ChatColor.BLUE+"WHY DO I EXIST"));
        chickenSword.setItemMeta(chickenSwordmeta);
        chickenSword.addEnchantment(Enchantment.DAMAGE_ALL, 5);

        NamespacedKey key = new NamespacedKey(this, "chickensword");
        ShapedRecipe recipe = new ShapedRecipe(key, chickenSword);
        recipe.shape("BBB","BBB","CSC");
        recipe.setIngredient('B', Material.DIAMOND_BLOCK);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('C', Material.CHICKEN);

        ItemMeta ballswordmeta= ballSword.getItemMeta();
        NamespacedKey ballKey= new NamespacedKey(NamespacedKey.BUKKIT,"ballz");
        ballswordmeta.setDisplayName(ChatColor.DARK_RED+"Cannon");
        ballswordmeta.getPersistentDataContainer().set(ballKey, PersistentDataType.INTEGER,1);
        ballswordmeta.setLore(Arrays.asList(ChatColor.RED+"Bestows great power",ChatColor.RED+"to its wielder",ChatColor.BOLD+"RIGHT CLICK FOR FIREBALL (LIMITED USE)"));
        Damageable damage = (Damageable) ballswordmeta;
        damage.setDamage(1831);
        ballSword.setItemMeta((ItemMeta) damage);
      //  ballSword.addEnchantment(Enchantment.KNOCKBACK, 1);


        NamespacedKey z = new NamespacedKey(this, "boomhoe");
        ShapedRecipe ballrecipe = new ShapedRecipe(z, ballSword);
        ballrecipe.shape("MNM","PNP"," R ");
        ballrecipe.setIngredient('M', Material.FIRE_CHARGE);
        ballrecipe.setIngredient('N', Material.NETHERITE_BLOCK);
        ballrecipe.setIngredient('R', Material.BLAZE_ROD);
        ballrecipe.setIngredient('P',Material.BLAZE_POWDER);

        Bukkit.addRecipe(ballrecipe);
        Bukkit.addRecipe(recipe);

        ItemMeta chickenMeta = netheriteApple.getItemMeta();
        NamespacedKey chickenKey = new NamespacedKey(NamespacedKey.BUKKIT,"netheriteapple");
        chickenMeta.setDisplayName(ChatColor.DARK_RED+"Netherite Apple");
        chickenMeta.getPersistentDataContainer().set(chickenKey,PersistentDataType.INTEGER,1);
        netheriteApple.setItemMeta(chickenMeta);



        ItemStack menuGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
        ItemMeta menumeta = menuGlass.getItemMeta();
        menumeta.setDisplayName(" ");
        menuGlass.setItemMeta(menumeta);

        ItemStack backArrow = new ItemStack(Material.ARROW,1);
        ItemMeta backMeta = backArrow.getItemMeta();
        backMeta.setDisplayName("Back");
        backArrow.setItemMeta(backMeta);

        ItemStack nextArrow = new ItemStack(Material.ARROW,1);
        ItemMeta nextMeta = nextArrow.getItemMeta();
        nextMeta.setDisplayName("Next");
        nextArrow.setItemMeta(nextMeta);

        craftGuide =  Bukkit.createInventory(null , 45 , "CraftGuide");


        for(int i = 0;i <45 ;i++){
            craftGuide.setItem(i,menuGlass);
        }
        craftGuide.setItem(10,new ItemStack(Material.DIAMOND_BLOCK,1));
        craftGuide.setItem(11,new ItemStack(Material.DIAMOND_BLOCK,1));
        craftGuide.setItem(12,new ItemStack(Material.DIAMOND_BLOCK,1));
        craftGuide.setItem(19,new ItemStack(Material.DIAMOND_BLOCK,1));
        craftGuide.setItem(20,new ItemStack(Material.DIAMOND_BLOCK,1));
        craftGuide.setItem(21,new ItemStack(Material.DIAMOND_BLOCK,1));
        craftGuide.setItem(25,chickenSword);
        craftGuide.setItem(28,new ItemStack(Material.CHICKEN,1));
        craftGuide.setItem(29,new ItemStack(Material.STICK,1));
        craftGuide.setItem(30,new ItemStack(Material.CHICKEN,1));

        craftGuide.setItem(44,nextArrow);

        cannonGuide =  Bukkit.createInventory(null , 45 , "CraftGuide");

        for(int j = 0;j <45 ;j++){
            cannonGuide.setItem(j, menuGlass);
        }
        cannonGuide.setItem(10,new ItemStack(Material.FIRE_CHARGE,1));
        cannonGuide.setItem(11,new ItemStack(Material.NETHERITE_BLOCK,1));
        cannonGuide.setItem(12,new ItemStack(Material.FIRE_CHARGE,1));
        cannonGuide.setItem(19,new ItemStack(Material.BLAZE_POWDER,1));
        cannonGuide.setItem(20,new ItemStack(Material.NETHERITE_BLOCK,1));
        cannonGuide.setItem(21,new ItemStack(Material.BLAZE_POWDER,1));
        cannonGuide.setItem(25,ballSword);
        cannonGuide.setItem(28,new ItemStack(Material.NETHER_BRICK,0));
        cannonGuide.setItem(29,new ItemStack(Material.BLAZE_ROD,1));
        cannonGuide.setItem(30,new ItemStack(Material.NETHERITE_AXE,0));

        cannonGuide.setItem(36,backArrow);

        Bukkit.getServer().getPluginManager().registerEvents(new Listener(), this);
        this.getCommand("givecustomitem").setExecutor(new GiveCustomItem());
        this.getCommand("craftguide").setExecutor(new CraftGuide());
        this.getCommand("givecustomitem").setTabCompleter(new TabComplete());
    }


}
