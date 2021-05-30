package me.xxben.anticaps.listeners;

import me.xxben.anticaps.AntiCaps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        String msg = event.getMessage();

        // checks if the message is already lowercase (most are) and therefore will not continue
        if (msg.toLowerCase().equals(msg))
            return;

        // this is what accounts for player names, therefore temporarily setting them to lowercase so it is ignored
        Set<String> userList = new HashSet<>();

        for (Player player : Bukkit.getOnlinePlayers())
            if (msg.contains(player.getName())) {
                msg = msg.replace(player.getName(), player.getName().toLowerCase());
                userList.add(player.getName());
            }

        int amountUppercase = 0;
        List<String> newMessageList = new ArrayList<>();

        // turns the msg into char to check the count of uppercase
        for (char chars : msg.toCharArray()) {
            if (Character.isUpperCase(chars)) {
                amountUppercase++;

                if (amountUppercase > AntiCaps.getManager().getMaxLetters())
                    chars = Character.toLowerCase(chars);
            }
            newMessageList.add(String.valueOf(chars));
        }

        String finalMsg = "";
        for (String messageChar : newMessageList)
            finalMsg += messageChar;

        // after message is remade, any player names stored will now be adjusted before the message is sent
        if (!userList.isEmpty())
            for (String userName : userList)
                finalMsg = finalMsg.replace(userName.toLowerCase(), userName);

        event.setMessage(finalMsg);
    }
}