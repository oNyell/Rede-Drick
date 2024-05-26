package com.vulcanth.commons;

import com.vulcanth.commons.bungee.comandos.*;
import org.bukkit.plugin.java.JavaPlugin;
import com.vulcanth.commons.comandos.Commands;
import com.vulcanth.commons.api.ChannelMessage;
import com.vulcanth.commons.api.CommunicationAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.Messenger;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Main extends JavaPlugin {

    private CommunicationAPI communicationAPI;

    @Override
    public void onEnable() {
        Commands.registerCommands(this, BTPCommand.class);
        communicationAPI = new CommunicationAPI(this);
        Messenger messenger = getServer().getMessenger();
        messenger.registerOutgoingPluginChannel(this, "BungeeCord");
        messenger.registerIncomingPluginChannel(this, "BungeeCord", this::onMessageReceived);
        getLogger().info("§aPlugin ativo com sucesso");
    }

    public void sendMessage(ChannelMessage message) {
        communicationAPI.sendMessage(message);
    }

    public void onMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) return;
        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(message))) {
            String subChannel = in.readUTF();
            if (subChannel.equals("Teleport")) {
                String[] data = in.readUTF().split(",");
                String playerName = data[0];
                String targetPlayerName = data[1];
                Player targetPlayer = Bukkit.getPlayer(targetPlayerName);
                if (targetPlayer != null) {
                    Player playerToTeleport = Bukkit.getPlayer(playerName);
                    if (playerToTeleport != null) {
                        playerToTeleport.teleport(targetPlayer);
                        playerToTeleport.sendMessage("§aVocê foi teleportado para " + targetPlayerName);
                    }
                } else {
                    player.sendMessage("§cO jogador alvo não está online.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
