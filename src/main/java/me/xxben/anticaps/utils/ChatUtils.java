package me.xxben.anticaps.utils;

import me.xxben.anticaps.storage.ConfigReader;
import org.bukkit.ChatColor;

public class ChatUtils {

    private static int maxLetters;

    public static void setMaxLetters() {
        maxLetters = ConfigReader.getInt("config", "max-capital-letters");
    }

    public static int getMaxLetters() {
        return maxLetters;
    }

    public static String translate(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
