package com.vulcanth.commons.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.Messenger;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommunicationAPI {

    private final Plugin plugin;

    public CommunicationAPI(Plugin plugin) {
        this.plugin = plugin;
        Messenger messenger = Bukkit.getMessenger();
        messenger.registerOutgoingPluginChannel(plugin, "BungeeCord");
        messenger.registerIncomingPluginChannel(plugin, "BungeeCord", this::onMessageReceived);
    }

    public void sendMessage(ChannelMessage message) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Forward");
            out.writeUTF(message.getChannel());
            out.writeUTF(message.getSubChannel());
            out.writeUTF(message.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Player player = Bukkit.getOnlinePlayers().iterator().next();
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    private void onMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;
        // Implementação para lidar com mensagens recebidas
    }
}
