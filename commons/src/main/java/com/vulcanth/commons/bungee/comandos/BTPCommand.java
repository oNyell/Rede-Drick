package com.vulcanth.commons.bungee.comandos;

import com.vulcanth.commons.annotations.CommandInfo;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import com.vulcanth.commons.bungee.Bungee;
import com.vulcanth.commons.api.ChannelMessage;

@CommandInfo(name = "btp", description = "Teleporta para um jogador ou servidor")
public class BTPCommand extends Command {
    private final Bungee plugin;

    public BTPCommand(Bungee plugin) {
        super("btp");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args.length == 1) {
                String targetPlayerName = args[0];
                ProxiedPlayer targetPlayer = plugin.getProxy().getPlayer(targetPlayerName);
                if (targetPlayer != null) {
                    if (player.getServer().getInfo().equals(targetPlayer.getServer().getInfo())) {
                        player.chat("/tp " + targetPlayerName);
                    } else {
                        player.connect(targetPlayer.getServer().getInfo(), (result, error) -> {
                            if (result) {
                                ChannelMessage message = new ChannelMessage("BungeeCord", "Teleport", player.getName() + "," + targetPlayerName);
                                plugin.sendMessage(message);
                                player.sendMessage(TextComponent.fromLegacyText("§aConectando..."));
                            } else {
                                player.sendMessage(TextComponent.fromLegacyText("§cErro ao conectar ao servidor de " + targetPlayerName));
                            }
                        });
                    }
                }
            }
        }
    }
}
