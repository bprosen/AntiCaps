package me.xxben.anticaps;

import me.xxben.anticaps.commands.AntiCapsCMD;
import me.xxben.anticaps.listeners.ChatListener;
import me.xxben.anticaps.storage.FileLoader;
import me.xxben.anticaps.utils.ChatUtils;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AntiCaps extends JavaPlugin implements Listener {

    private static Plugin plugin;
    private static Logger logger;

    public void onEnable() {

        plugin = this;
        logger = getLogger();

        registerListeners();
        registerCommands();
        FileLoader.startUp();
        ChatUtils.setMaxLetters();
        getLogger().info("AntiCaps Enabled");
    }

    public void onDisable() {

        plugin = null;
        getLogger().info("AntiCaps Disabled");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    private void registerCommands() {
        getCommand("anticaps").setExecutor(new AntiCapsCMD());
    }

    public static Plugin getInstance() {
        return plugin;
    }

    public static Logger getLog() {
        return logger;
    }
}