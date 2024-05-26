package com.vulcanth.commons.bungee.comandos;

import com.vulcanth.commons.annotations.CommandInfo;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import org.reflections.Reflections;

import java.util.Set;

public class Commands {

    public static void registerCommands(Plugin plugin, Class<?> rootClass) {
        String packageName = rootClass.getPackage().getName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> commandClasses = reflections.getTypesAnnotatedWith(CommandInfo.class);

        for (Class<?> commandClass : commandClasses) {
            try {
                CommandInfo commandInfo = commandClass.getAnnotation(CommandInfo.class);
                Command command = (Command) commandClass.getConstructor(Plugin.class).newInstance(plugin);
                plugin.getProxy().getPluginManager().registerCommand(plugin, command);
            } catch (Exception e) {
                plugin.getLogger().severe("§cErro ao registrar comando: " + e.getMessage());
            }
        }
    }
}
