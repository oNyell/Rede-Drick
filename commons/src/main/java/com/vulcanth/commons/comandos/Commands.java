package com.vulcanth.commons.comandos;

import com.vulcanth.commons.annotations.CommandInfo;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Set;

public class Commands {

    public static void registerCommands(JavaPlugin plugin, Class<?> rootClass) {
        String packageName = rootClass.getPackage().getName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> commandClasses = reflections.getTypesAnnotatedWith(CommandInfo.class);

        for (Class<?> commandClass : commandClasses) {
            try {
                CommandInfo commandInfo = commandClass.getAnnotation(CommandInfo.class);
                PluginCommand pluginCommand = plugin.getCommand(commandInfo.name());
                if (pluginCommand != null) {
                    CommandExecutor executor = (CommandExecutor) commandClass.getConstructor(JavaPlugin.class).newInstance(plugin);
                    pluginCommand.setExecutor(executor);
                    pluginCommand.setDescription(commandInfo.description());
                    pluginCommand.setAliases(commandInfo.aliases().length > 0 ? Arrays.asList(commandInfo.aliases()) : Arrays.asList());
                }
            } catch (Exception e) {
                plugin.getLogger().severe("§cErro ao registrar comando: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
