package org.catplugin.eu;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.catplugin.eu.Armor.Hearty.HeartyBoots;
import org.catplugin.eu.Armor.Hearty.HeartyChestplate;
import org.catplugin.eu.Armor.Hearty.HeartyHelmet;
import org.catplugin.eu.Armor.Hearty.HeartyLeggings;
import org.catplugin.eu.Armor.NetheriteHearty.NetheriteHeartyBoots;
import org.catplugin.eu.Armor.NetheriteHearty.NetheriteHeartyChestplate;
import org.catplugin.eu.Armor.NetheriteHearty.NetheriteHeartyHelmet;
import org.catplugin.eu.Armor.NetheriteHearty.NetheriteHeartyLeggings;
import org.catplugin.eu.Items.Cannon;
import org.catplugin.eu.Items.ChickenSword;
import org.catplugin.eu.Items.NetheriteApple;

import javax.security.auth.login.LoginException;

public class Main extends JavaPlugin {

    public static ItemStack chickenSword = new ItemStack(Material.NETHERITE_SWORD, 1);
    public static ItemStack ballSword = new ItemStack(Material.NETHERITE_HOE, 1);
    public static ItemStack netheriteApple = new ItemStack(Material.APPLE, 1);
    public static Inventory craftGuide;
    public static Inventory cannonGuide;
    public static ItemStack menuGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
    public static ItemStack backArrow = new ItemStack(Material.ARROW, 1);
    public static ItemStack nextArrow = new ItemStack(Material.ARROW, 1);
    public static boolean on = true;

    public static ItemStack heartyHelmet = new ItemStack(Material.DIAMOND_HELMET,1);
    public static ItemStack heartyChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE,1);
    public static ItemStack heartyLeggings = new ItemStack(Material.DIAMOND_LEGGINGS,1);
    public static ItemStack heartyBoots = new ItemStack(Material.DIAMOND_BOOTS,1);

    public static ItemStack netheriteHeartyHelmet = new ItemStack(Material.NETHERITE_HELMET,1);
    public static ItemStack netheriteHeartyChestplate = new ItemStack(Material.NETHERITE_CHESTPLATE,1);
    public static ItemStack netheriteHeartyLeggings = new ItemStack(Material.NETHERITE_LEGGINGS,1);
    public static ItemStack netheriteHeartyBoots = new ItemStack(Material.NETHERITE_BOOTS,1);

    public static final String TOKEN = GitignoredFile.botToken();
    public static JDA jda = null;
    public static JDABuilder builder = null;
    public static boolean run = false;
    public static boolean owo = false;
    public static boolean uwu = false;

    @Override
    public void onEnable() {
        getLogger().info("uw Moeder");
        getServer().getPluginManager().registerEvents(new Listener(), this);
        try {
            jda = JDABuilder.createDefault(TOKEN).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        builder = JDABuilder.createDefault(TOKEN);

        if(jda == null){
            getLogger().info("jda null");
        }
        else{
            getLogger().info(jda.getToken());
        }
        try {
            getLogger().info(String.valueOf(jda.awaitReady().getGuilds().size()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       // getLogger().info(String.valueOf(jda.getGuilds().size()));
        jda.addEventListener(new DiscordListener());
        builder.setActivity(Activity.playing("the SMP"));

        ChickenSword.init();
        Cannon.init();
        NetheriteApple.init();

        HeartyHelmet.init();
        HeartyChestplate.init();
        HeartyLeggings.init();
        HeartyBoots.init();

        NetheriteHeartyHelmet.init();
        NetheriteHeartyChestplate.init();
        NetheriteHeartyLeggings.init();
        NetheriteHeartyBoots.init();

        Bukkit.addRecipe(HeartyBoots.makeRecipe());
        Bukkit.addRecipe(HeartyLeggings.makeRecipe());
        Bukkit.addRecipe(HeartyHelmet.makeRecipe());
        Bukkit.addRecipe(HeartyChestplate.makeRecipe());

        Bukkit.addRecipe(Cannon.makeRecipe());
        Bukkit.addRecipe(ChickenSword.makeRecipe());


        ItemMeta menumeta = menuGlass.getItemMeta();
        menumeta.setDisplayName(" ");
        menuGlass.setItemMeta(menumeta);

        ItemMeta backMeta = backArrow.getItemMeta();
        backMeta.setDisplayName("Back");
        backArrow.setItemMeta(backMeta);

        ItemMeta nextMeta = nextArrow.getItemMeta();
        nextMeta.setDisplayName("Next");
        nextArrow.setItemMeta(nextMeta);

        ChickenSword.makeGuide();
        Cannon.makeGuide();


        Bukkit.getServer().getPluginManager().registerEvents(new Listener(), this);

        this.getCommand("givecustomitem").setExecutor(new GiveCustomItem());
        this.getCommand("craftguide").setExecutor(new CraftGuide());
        this.getCommand("givecustomitem").setTabCompleter(new TabComplete());
        this.getCommand("togglediscord").setExecutor(new ToggleDiscordChat());

    }

    @Override
    public void onDisable() {
        try {
            jda.awaitReady().shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
