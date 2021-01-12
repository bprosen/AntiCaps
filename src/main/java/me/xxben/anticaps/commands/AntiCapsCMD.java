package me.xxben.anticaps.commands;

import me.xxben.anticaps.storage.ConfigReader;
import me.xxben.anticaps.storage.FileLoader;
import me.xxben.anticaps.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AntiCapsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {

        // Console section
        if (!(sender instanceof Player)) {
            ConsoleCommandSender console = (ConsoleCommandSender) sender;
            runCmds(console, a);
        } else {
            // Player section
            Player player = (Player) sender;
            String permNode = ConfigReader.getString("config", "command.permission-node", false);

            if (player.hasPermission(permNode))
                runCmds(player, a);
            else
                player.sendMessage(ConfigReader.getString("lang", "no-permission", true));
        }
        return false;
    }

    private void runCmds(CommandSender sender, String[] a) {
        if (a.length == 1 && a[0].equalsIgnoreCase("reload")) {
            FileLoader.load();
            ChatUtils.setMaxLetters();
            sender.sendMessage(ConfigReader.getString("lang", "reloaded-config", true));
        } else if (a.length == 1 && a[0].equalsIgnoreCase("help")) {
            sendHelp(sender);
        } else {
            sendHelp(sender);
        }
    }

    private void sendHelp(CommandSender sender) {
        for (String string : ConfigReader.getStringList("lang", "anticaps-help", true))
            sender.sendMessage(string);
    }
}
