/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.methods.Minion;
import pl.kwadratowamasakra.minions.methods.MinionBlock;

import java.util.List;

public class BlockListeners implements Listener {

    private final KMMinionPlugin plugin;

    public BlockListeners(final KMMinionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public final void onPlace(final BlockPlaceEvent e) {
        final Block block = e.getBlock();
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
