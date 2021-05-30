package me.xxben.anticaps;
import org.bukkit.ChatColor;

public class Utils {

    public static String translate(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
