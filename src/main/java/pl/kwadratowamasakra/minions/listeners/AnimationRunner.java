/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions.listeners;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.methods.Minion;
import pl.kwadratowamasakra.minions.methods.MinionBlock;
import pl.kwadratowamasakra.minions.utils.MainUtil;

import java.util.ArrayList;
import java.util.List;

public class AnimationRunner extends BukkitRunnable {

    private final KMMinionPlugin plugin;

    public AnimationRunner(final KMMinionPlugin plugin) {
        this.plugin = plugin;
        runTaskTimer(plugin, 4L, 4L);
    }

    public final void run() {
        final List<Minion> minions = new ArrayList<>(plugin.getServerHelper().getMinions());
        for (final Minion minion : minions) {
            final MinionBlock minionBlock = minion.getCurrentBlock();
            if (minionBlock != null && (minionBlock.isToStartAnimation() || minionBlock.isRunningAnimation())) {
                if (minionBlock.getAnimationFrameTotal() == 0) {
                    final Location loc = minion.getArmorStand().getLocation();
                    final Location location = minionBlock.getBlockLocation().clone().add(0.5, 0, 0.5);
                    minion.getArmorStand().teleport(MainUtil.setLookAt(loc, location));
                }
                minionBlock.setToStartAnimation(false);
                minionBlock.setAnimationFrame(minionBlock.getAnimationFrame() + 1);
                minionBlock.setAnimationFrameTotal(minionBlock.getAnimationFrameTotal() + 1);
                final Location blockLocation = minionBlock.getBlockLocation();

                final BlockPosition blockPosition = new BlockPosition(blockLocation.getBlockX(), blockLocation.getBlockY(), blockLocation.getBlockZ());
                final PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(0, blockPosition, Math.min((int) ((minionBlock.getAnimationFrameTotal() * (10.0 / (minionBlock.getMaxAnimationFrame() * minionBlock.getAnimationCountStart()))) - 1), 9));

                for (final Player p : Bukkit.getOnlinePlayers()) {
                    final PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                    connection.sendPacket(packet);
                }
                if (minionBlock.getAnimationFrame() <= (minionBlock.getMaxAnimationFrame() / 2)) {
                    minion.getArmorStand().setRightArmPose(
                            new EulerAngle(
                                    ((-0.15 / (minionBlock.getMaxAnimationFrame() / 2.0)) * minionBlock.getAnimationFrame()) * 3,
                                    0,
                                    ((-0.05 / (minionBlock.getMaxAnimationFrame() / 2.0)) * minionBlock.getAnimationFrame()) * 3
                            ));
                } else if (minionBlock.getAnimationFrame() <= minionBlock.getMaxAnimationFrame()) {
                    minion.getArmorStand().setRightArmPose(
                            new EulerAngle(
                                    (-0.45) + (0.15 / (minionBlock.getMaxAnimationFrame() / 2.0)) * 3 * (minionBlock.getAnimationFrame() - (minionBlock.getMaxAnimationFrame() / 2.0)),
                                    0,
                                    (-0.15) + (0.05 / (minionBlock.getMaxAnimationFrame() / 2.0)) * 3 * (minionBlock.getAnimationFrame() - (minionBlock.getMaxAnimationFrame() / 2.0))
                            ));
                } else {
                    minionBlock.setAnimationFrame(0);
                    minionBlock.setAnimationCount(minionBlock.getAnimationCount() - 1);
                    if (minionBlock.getAnimationCount() > 0) {
                        minionBlock.setToStartAnimation(true);
                    } else {
                        minionBlock.setAnimationFrameTotal(0);
                        minion.removeBlock(minionBlock);
                        final List<ItemStack> items = new ArrayList<>(blockLocation.getBlock().getDrops(minion.getItemStack()));
                        for (final ItemStack itemStack : items) {
                            blockLocation.getWorld().dropItem(blockLocation, itemStack);
                        }
                        MainUtil.recalculateDurability(minion.getArmorStand(), minion.getItemStack());
                        blockLocation.getBlock().setType(Material.AIR);
                    }
                }
            }
        }
    }

}
