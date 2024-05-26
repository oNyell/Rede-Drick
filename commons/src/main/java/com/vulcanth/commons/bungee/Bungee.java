package com.vulcanth.commons.bungee;

import com.vulcanth.commons.bungee.comandos.*;
import net.md_5.bungee.api.plugin.Plugin;
import com.vulcanth.commons.bungee.comandos.Commands;
import com.vulcanth.commons.api.ChannelMessage;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Bungee extends Plugin implements Listener {

    @Override
    public void onEnable() {
        Commands.registerCommands(this, BTPCommand.class);
        getProxy().getPluginManager().registerListener(this, this);
        getLogger().info("Plugin ativo com sucesso.");
    }

    public void sendMessage(ChannelMessage message) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            out.writeUTF("Forward");
            out.writeUTF("ALL");
            out.writeUTF(message.getSubChannel());
            out.writeUTF(message.getMessage());

            byte[] byteArray = b.toByteArray();

            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                player.getServer().sendData("BungeeCord", byteArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
