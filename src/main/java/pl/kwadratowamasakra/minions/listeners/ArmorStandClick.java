/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions.listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.inventories.MinionInventory;
import pl.kwadratowamasakra.minions.methods.Minion;

public class ArmorStandClick implements Listener {

    private final KMMinionPlugin plugin;

    public ArmorStandClick(final KMMinionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public final void onClick(final PlayerInteractAtEntityEvent e) {
        final Entity entity = e.getRightClicked();
        if (entity instanceof ArmorStand) {
            final ArmorStand armorStand = (ArmorStand) entity;
            final Minion minion = plugin.getServerHelper().getMinionById(armorStand.getEntityId());
            if (minion != null) {
                final Player p = e.getPlayer();
                e.setCancelled(true);
                plugin.getServerHelper().putOpenedInv(p.getUniqueId(), armorStand.getEntityId());
                p.openInventory(MinionInventory.getMinionGui(plugin.getServerHelper(), minion));
            }
        }
    }

}
