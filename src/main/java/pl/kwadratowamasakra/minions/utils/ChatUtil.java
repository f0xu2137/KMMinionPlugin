package pl.kwadratowamasakra.minions.utils;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String fixColor(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
