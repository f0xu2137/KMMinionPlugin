package pl.kwadratowamasakra.minions.commands;

import org.bukkit.Color;
import org.bukkit.GameMode;
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
import pl.kwadratowamasakra.minions.utils.ChatUtil;
import pl.kwadratowamasakra.minions.utils.DiscoArmorUtil;
import pl.kwadratowamasakra.minions.utils.SkullItemBuilder;

public class MinionCommand implements CommandExecutor {

    private final KMMinionPlugin plugin;

    public MinionCommand(final KMMinionPlugin plugin) {
        this.plugin = plugin;
    }

    public final boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            p.setGameMode(GameMode.CREATIVE);

            final Location location = p.getLocation();
            final ArmorStand stand = location.getWorld().spawn(location.getBlock().getLocation().clone().add(0.5, 0, 0.5), ArmorStand.class);
            stand.setSmall(true);
            stand.setArms(true);
            stand.setGravity(false);
            stand.setCustomName(ChatUtil.fixColor("&e>> MINION <<"));
            stand.setCustomNameVisible(true);
            stand.setHelmet(new SkullItemBuilder(Material.SKULL_ITEM, 1, 3, "radek203").build());
            stand.setChestplate(DiscoArmorUtil.setArmorColor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.AQUA));
            stand.setLeggings(DiscoArmorUtil.setArmorColor(new ItemStack(Material.LEATHER_LEGGINGS), Color.AQUA));
            stand.setBoots(DiscoArmorUtil.setArmorColor(new ItemStack(Material.LEATHER_BOOTS), Color.AQUA));

            final ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
            itemStack.addEnchantment(Enchantment.DIG_SPEED, 5);
            stand.setItemInHand(itemStack);

            final Minion minion = new Minion(stand);
            minion.setAnimationCount(8);
            minion.setToStartAnimation(true);

            final Color[] colors = new Color[1];
            colors[0] = Color.fromRGB(255, 0, 0);
            minion.setColors(colors);
            plugin.getServerHelper().addMinion(minion);
        }
        return false;
    }

}
