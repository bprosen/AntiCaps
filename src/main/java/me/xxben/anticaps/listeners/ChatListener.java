package me.xxben.anticaps.listeners;

import me.xxben.anticaps.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.ArrayList;
import java.util.List;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        String msg = event.getMessage();

        // Checks if the message is already lowercase (most are) and therefore will not continue.
        if (msg.toLowerCase().equals(msg))
            return;

        // This is what accounts for player names, therefore temporarily setting them to lowercase so it is ignored.
        List<String> userList = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers())
            if (msg.contains(player.getName())) {
                msg = msg.replace(player.getName(), player.getName().toLowerCase());
                userList.add(player.getName());
            }

        int amountUppercase = 0;
        List<String> newMessageList = new ArrayList<>();

        // Turns the msg into char to check the count of uppercase.
        for (char chars : msg.toCharArray()) {
            if (Character.isUpperCase(chars)) {
                amountUppercase++;
                if (amountUppercase > ChatUtils.getMaxLetters())
                    chars = Character.toLowerCase(chars);
            }
            String replaceWord = String.valueOf(chars);
            newMessageList.add(replaceWord);
        }

        String finalMsg = "";
        for (int i = 0; i < newMessageList.size(); i++)
            finalMsg = finalMsg + newMessageList.get(i);

        // After message is remade, any player names stored will now be adjusted before the message is sent.
        if (!userList.isEmpty())
            for (String userName : userList)
                finalMsg = finalMsg.replace(userName.toLowerCase(), userName);

        event.setMessage(finalMsg);
    }
}