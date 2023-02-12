package pl.kwadratowamasakra.minions.utils;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

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

}