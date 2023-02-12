/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.Random;

public class MainUtil {

    private static final Random RANDOM = new Random();

    public static String fixColor(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private static int getRandomInt(final int min, final int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static void recalculateDurability(final ArmorStand armorStand, final ItemStack item) {
        if (item == null) {
            return;
        }
        if (item.getType().getMaxDurability() == 0) {
            return;
        }
        final short durability = item.getDurability();
        if (durability == item.getType().getMaxDurability()) {
            armorStand.setItemInHand(null);
            return;
        }
        final int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        if (enchantLevel > 0) {
            if (100 / (enchantLevel + 1) <= getRandomInt(0, 100)) {
                return;
            }
        }
        item.setDurability((short) (durability + 1));
        armorStand.setItemInHand(item);
    }

    public static Location setLookAt(final Location entity, final Location target) {
        final Vector dirBetweenLocations = target.toVector().subtract(entity.toVector());
        return entity.setDirection(dirBetweenLocations);
    }

    public static void givePlayerItems(final Player p, final ItemStack itemStack) {
        final Map<Integer, ItemStack> notStored = p.getInventory().addItem(itemStack);
        if (!notStored.isEmpty()) {
            final Location pLoc = p.getLocation();
            for (final ItemStack en : notStored.values()) {
                if (en.getType() != Material.AIR) {
                    pLoc.getWorld().dropItem(pLoc, en);
                }
            }
        }
    }

}
