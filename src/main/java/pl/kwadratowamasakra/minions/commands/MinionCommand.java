package pl.kwadratowamasakra.minions.commands;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.methods.Minion;
import pl.kwadratowamasakra.minions.utils.MainUtil;
import pl.kwadratowamasakra.minions.utils.SkullItemBuilder;

public class MinionCommand implements CommandExecutor {

    private final KMMinionPlugin plugin;

    public MinionCommand(final KMMinionPlugin plugin) {
        this.plugin = plugin;
    }

    public final boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;

            final Location location = p.getLocation();
            final ArmorStand stand = location.getWorld().spawn(location.getBlock().getLocation().clone().add(0.5, 0, 0.5), ArmorStand.class);
            stand.setSmall(true);
            stand.setArms(true);
            stand.setGravity(false);
            stand.setCustomName(MainUtil.fixColor("&e>> MINION <<"));
            stand.setCustomNameVisible(true);
            stand.setHelmet(new SkullItemBuilder(Material.SKULL_ITEM, 1, 3, "radek203").build());

            final ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
            itemStack.addEnchantment(Enchantment.DIG_SPEED, 5);
            stand.setItemInHand(itemStack);

            final Minion minion = new Minion(stand);

            final Color[] colors = new Color[1];
            colors[0] = Color.fromRGB(255, 0, 0);
            minion.setColors(colors);
            plugin.getServerHelper().addMinion(minion);
        }
        return false;
    }

}
