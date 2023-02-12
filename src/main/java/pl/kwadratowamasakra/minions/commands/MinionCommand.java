/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.utils.MainUtil;

public class MinionCommand implements CommandExecutor {

    private final KMMinionPlugin plugin;

    public MinionCommand(final KMMinionPlugin plugin) {
        this.plugin = plugin;
    }

    public final boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player) sender;
            if (!p.hasPermission("km.minion.command")) {
                p.sendMessage(MainUtil.fixColor("&cNie masz dostepu do tej komendy!"));
                return false;
            }

            MainUtil.givePlayerItems(p, plugin.getServerHelper().getMinionItem().clone());

            final ItemStack itemStack = new ItemStack(Material.DIAMOND_PICKAXE);
            itemStack.addEnchantment(Enchantment.DIG_SPEED, 5);
            MainUtil.givePlayerItems(p, itemStack);

            p.sendMessage(MainUtil.fixColor("&8>> &7Dodano przedmioty do ekwipunku!"));
        }
        return false;
    }

}
