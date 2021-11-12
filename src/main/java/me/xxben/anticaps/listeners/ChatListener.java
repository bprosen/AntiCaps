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
        int maxUppercase = AntiCaps.getManager().getMaxLetters();

        // checks if the message is already lowercase or less than the amount of max uppercase letters it will not continue
        if (msg.toLowerCase().equals(msg) || msg.length() <= maxUppercase)
            return;

        // this is what accounts for player names, therefore temporarily setting them to lowercase so it is ignored
        Set<String> userList = new HashSet<>();

        for (Player player : Bukkit.getOnlinePlayers())
            if (msg.contains(player.getName())) {
                msg = msg.replace(player.getName(), player.getName().toLowerCase());
                userList.add(player.getName());
            }

        int amountUppercase = 0;
        String finalMsg = "";

        // turns the msg into char to check the count of uppercase
        for (char chars : msg.toCharArray()) {
            if (Character.isUpperCase(chars)) {
                // if amount is more than max, lowercase it, otherwise increment
                if (amountUppercase > maxUppercase)
                    chars = Character.toLowerCase(chars);
                else
                    amountUppercase++;
            }
            finalMsg += chars;
        }

        // after message is remade, any player names stored will now be adjusted before the message is sent
        if (!userList.isEmpty())
            for (String userName : userList)
                finalMsg = finalMsg.replace(userName.toLowerCase(), userName);

        event.setMessage(finalMsg);
    }
}