package me.xxben.anticaps.storage;

import me.xxben.anticaps.utils.ChatUtils;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigReader {

    public static String getString(String file, String message, boolean translated) {
        FileConfiguration itemsConfig = FileManager.getFileConfig(file);
        String itemValue = itemsConfig.getString(message);
        if (translated)
            return ChatUtils.translate(itemValue);
        return itemValue;
    }

    public static int getInt(String file, String number) {
        FileConfiguration itemsConfig = FileManager.getFileConfig(file);
        return itemsConfig.getInt(number);
    }

    public static List<String> getStringList(String file, String message, boolean translated) {
        FileConfiguration itemsConfig = FileManager.getFileConfig(file);
        List<String> stringList = new ArrayList<>();
        for (String msg : itemsConfig.getStringList(message)) {
            if (translated)
                stringList.add(ChatUtils.translate(msg));
            else
                stringList.add(msg);
        }
        return stringList;
    }
}
