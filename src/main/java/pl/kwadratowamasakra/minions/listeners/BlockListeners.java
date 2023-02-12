/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions.listeners;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.methods.Minion;
import pl.kwadratowamasakra.minions.methods.MinionBlock;
import pl.kwadratowamasakra.minions.utils.MainUtil;
import pl.kwadratowamasakra.minions.utils.SkullItemBuilder;

import java.util.List;

public class BlockListeners implements Listener {

    private final KMMinionPlugin plugin;

    public BlockListeners(final KMMinionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public final void onPlace(final BlockPlaceEvent e) {
        final Block block = e.getBlock();
        if (e.getItemInHand().isSimilar(plugin.getServerHelper().getMinionItem())) {
            block.setType(Material.AIR);
            final Location location = block.getLocation();
            final ArmorStand stand = location.getWorld().spawn(location.clone().add(0.5, 0, 0.5), ArmorStand.class);
            stand.setSmall(true);
            stand.setArms(true);
            stand.setGravity(false);
            stand.setCustomName(MainUtil.fixColor("&e>> MINION <<"));
            stand.setCustomNameVisible(true);
            stand.setHelmet(new SkullItemBuilder(Material.SKULL_ITEM, 1, 3, "radek203").build());

            final Minion minion = new Minion(stand);
            minion.getArmorStand().setItemInHand(new ItemStack(Material.STONE_PICKAXE));

            final Color color = Color.fromRGB(255, 0, 0);
            minion.setColor(color);
            plugin.getServerHelper().addMinion(minion);

            return;
        }
        if (block.getType() == Material.STONE || block.getType() == Material.OBSIDIAN) {
            final Minion minion = plugin.getServerHelper().getMinionByLocation(block.getLocation().clone().add(0.5, 0, 0.5));
            if (minion != null) {
                final MinionBlock minionBlock = new MinionBlock();
                minionBlock.setAnimationFrameTotal(0);
                if (block.getType() == Material.STONE) {
                    minionBlock.setAnimationCount(8);
                    minionBlock.setAnimationCountStart(8);
                } else {
                    minionBlock.setAnimationCount(16);
                    minionBlock.setAnimationCountStart(16);
                }
                minionBlock.setBlockLocation(block.getLocation());
                minionBlock.setMaxAnimationFrame(8);
                minion.addBlock(minionBlock);
                minionBlock.setToStartAnimation(true);
            }
        }
    }

    @EventHandler
    public final void onBreak(final BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.STONE || e.getBlock().getType() == Material.OBSIDIAN) {
            plugin.getServerHelper().removeBlockByLocation(e.getBlock().getLocation());
        }
    }

    @EventHandler
    public final void onPistonExtend(final BlockPistonExtendEvent e) {
        final List<Block> blocks = e.getBlocks();
        if (!blocks.isEmpty()) {
            for (final Block block : blocks) {
                if (block.getType() == Material.STONE || block.getType() == Material.OBSIDIAN) {
                    plugin.getServerHelper().removeBlockByLocation(block.getLocation());
                }
            }
        }
    }

    @EventHandler
    public final void onPistonRetract(final BlockPistonRetractEvent e) {
        final List<Block> blocks = e.getBlocks();
        if (!blocks.isEmpty()) {
            for (final Block block : blocks) {
                if (block.getType() == Material.STONE || block.getType() == Material.OBSIDIAN) {
                    plugin.getServerHelper().removeBlockByLocation(block.getLocation());
                }
            }
        }
    }

    @EventHandler
    public final void onBlockExplode(final EntityExplodeEvent e) {
        for (final Block block : e.blockList()) {
            if (block.getType() == Material.STONE || block.getType() == Material.OBSIDIAN) {
                plugin.getServerHelper().removeBlockByLocation(block.getLocation());
            }
        }
    }

}
