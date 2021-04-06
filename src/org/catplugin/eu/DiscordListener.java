package org.catplugin.eu;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;
import java.util.List;



public class DiscordListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {


        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (msg.getContentRaw().equals("n!ping")) {
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!").queue(response -> {
                response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
            });
        }
        if (msg.getContentRaw().toLowerCase().startsWith("n!sendmsg")) {
            String[] args = msg.getContentRaw().split(" ");
            String message = "";
            for (int i = 1; i < args.length; i++) {
                message += args[i];
                message += " ";
            }
            Bukkit.broadcastMessage(ChatColor.GOLD + "[Msg from " + msg.getAuthor().getName() + "] " + ChatColor.WHITE + message);
            MessageChannel channelA = null;
            try {
                channelA = Main.jda.awaitReady().getGuildById("712092358711181325").getTextChannelById("812370653223190568");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channelA.sendMessage("[Msg from " + msg.getAuthor().getName() + "] " + message).queue();
            message = "";
        }
        if (msg.getContentRaw().toLowerCase().startsWith("n!whitelist")) {
            String[] args = msg.getContentRaw().split(" ");
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = "whitelist add ";
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("CatPlugin"), new Runnable() {public void run() { Bukkit.dispatchCommand(console, command+args[1]); }}, 1L);
            channel.sendMessage("You're now whitelisted!").queue();
            Bukkit.reloadWhitelist();

        }
        if (msg.getContentRaw().toLowerCase().startsWith("n!help")) {
            EmbedBuilder embedBuilder  = new EmbedBuilder().setTitle("Help");
            embedBuilder.setColor(Color.CYAN);
            embedBuilder.addField("n!sendmsg", "Send a message to the SMP",false );
            embedBuilder.addField("n!whitelist <IGN>", "Adds you to the SMP whitelist if you're a member of LPK or HPK. Don't misspell your ign",false );
            channel.sendMessage(embedBuilder.build()).queue();
        }
        if (msg.getContentRaw().toLowerCase().startsWith("n!online")) {
            EmbedBuilder embedBuilder  = new EmbedBuilder().setTitle("Online");
            embedBuilder.setColor(Color.MAGENTA);
            List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();
            for(int i = 0 ; i <players.size();i++){
                embedBuilder.addField(players.get(i).getName(),"Is online on the SMP!",false);
            }
            channel.sendMessage(embedBuilder.build()).queue();
        }

    }
}


