package me.xxben.anticaps;

import me.xxben.anticaps.commands.AntiCapsCMD;
import me.xxben.anticaps.listeners.ChatListener;
import me.xxben.anticaps.storage.FileLoader;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AntiCaps extends JavaPlugin {

    private static Plugin plugin;
    private static Logger logger;
    private static Manager manager;

    @Override
    public void onEnable() {

        plugin = this;
        logger = getLogger();

        registerListeners();
        registerCommands();

        FileLoader.startUp();

        manager = new Manager();

        getLogger().info("AntiCaps Enabled");
    }

    @Override
    public void onDisable() {

        manager = null;

        getLogger().info("AntiCaps Disabled");

        plugin = null;
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

    public static Manager getManager() { return manager; }
}