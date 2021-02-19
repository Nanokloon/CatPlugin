package org.catplugin.eu;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import static org.catplugin.eu.Main.run;

public class DiscordListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (msg.getContentRaw().equals("n!ping")) {
            long time = System.currentTimeMillis();
            channel.sendMessage("Pong!").queue(response -> {response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue(); });
        }
        if(msg.getContentRaw().toLowerCase().startsWith("n!sendmsg")){
            String[] args = msg.getContentRaw().split(" ");
            String message = "";
            for(int i = 1 ; i< args.length;i++){
                message+=args[i];
                message+=" ";
            }
            Bukkit.broadcastMessage(ChatColor.GOLD + "[Msg from "+ msg.getAuthor().getName() +"] "+ ChatColor.WHITE + message);
            MessageChannel channelA = null;
            try {
                channelA = Main.jda.awaitReady().getGuildById("712092358711181325").getTextChannelById("812370653223190568");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channelA.sendMessage("[Msg from "+ msg.getAuthor().getName() +"] "+message).queue();

            message="";
        }
    }
}
