/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.methods.Minion;
import pl.kwadratowamasakra.minions.utils.MainUtil;

public class InventoryListener implements Listener {

    private final KMMinionPlugin plugin;

    public InventoryListener(final KMMinionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public final void onClose(final InventoryCloseEvent e) {
        plugin.getServerHelper().removeOpenedInv(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public final void onClick(final InventoryClickEvent e) {
        if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) {
            e.setCancelled(true);
            return;
        }
        final int slot = e.getRawSlot();
        if (slot + 1 > e.getInventory().getSize()) {
            return;
        }
        final Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(MainUtil.fixColor("&8>> &2&lMINION &8<<"))) {
            e.setCancelled(true);
            final int id = plugin.getServerHelper().getOpenedInv(p.getUniqueId());
            final Minion minion = plugin.getServerHelper().getMinionById(id);
            if (minion != null) {
                if (slot == 13) {
                    final ItemStack toPlace = e.getCursor().clone();
                    if (toPlace == null || toPlace.getType() == Material.AIR || toPlace.getType() == Material.WOOD_PICKAXE || toPlace.getType() == Material.STONE_PICKAXE || toPlace.getType() == Material.GOLD_PICKAXE || toPlace.getType() == Material.IRON_PICKAXE || toPlace.getType() == Material.DIAMOND_PICKAXE) {
                        final ItemStack itemStack = minion.getItemStack().clone();
                        p.setItemOnCursor(itemStack);
                        e.getView().setItem(13, toPlace);
                        minion.getArmorStand().setItemInHand(toPlace);
                    } else {
                        p.sendMessage(MainUtil.fixColor("&8>> &cTego przedmiotu nie mozesz tu umiescic!"));
                    }
                } else if (slot == 26) {
                    minion.destroyMinion();
                    plugin.getServerHelper().removeMinion(minion);
                    p.closeInventory();
                }
            } else {
                p.closeInventory();
            }
        }
    }

}
