package pl.kwadratowamasakra.minions.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.methods.Minion;
import pl.kwadratowamasakra.minions.utils.DiscoArmorUtil;

public class DiscoArmorTask extends BukkitRunnable {

    private final KMMinionPlugin plugin;

    public DiscoArmorTask(final KMMinionPlugin plugin) {
        this.plugin = plugin;
        runTaskTimerAsynchronously(plugin, 3L, 3L);
    }

    private static Color nextRGB(final Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        if (red == 255 && green < 255 && blue == 0) {
            green += 15;
        }
        if (green == 255 && red > 0 && blue == 0) {
            red -= 15;
        }
        if (green == 255 && blue < 255 && red == 0) {
            blue += 15;
        }
        if (blue == 255 && green > 0 && red == 0) {
            green -= 15;
        }
        if (blue == 255 && red < 255 && green == 0) {
            red += 15;
        }
        if (red == 255 && blue > 0 && green == 0) {
            blue -= 15;
        }
        return Color.fromRGB(red, green, blue);
    }

    @SuppressWarnings("deprecation")
    public final void run() {
        for (final Minion minion : plugin.getServerHelper().getMinions()) {
            final Color color;
            final Color[] colors;
            colors = minion.getColors();
            color = nextRGB(colors[0]);
            colors[0] = color;
            minion.setColors(colors);
            for (int i = 0; i < 3; ++i) {
                final ItemStack item = new ItemStack(Material.getMaterial(298 + i), 1);
                final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
                meta.setColor(color);
                item.setItemMeta(meta);
                for (final Player online : Bukkit.getOnlinePlayers()) {
                    DiscoArmorUtil.sendArmorChange(online, minion.getArmorStand().getEntityId(), 1 + i, item);
                }
            }
        }
    }
}
