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
        final List<Minion> minions = plugin.getServerHelper().getMinions();
        for (final Minion minion : minions) {
            if (minion.isToStartAnimation() || minion.isRunningAnimation()) {
                minion.setToStartAnimation(false);
                minion.setAnimationFrame(minion.getAnimationFrame() + 1);
                minion.setAnimationFrameTotal(minion.getAnimationFrameTotal() + 1);
                final Location blockLocation = minion.getBlockLocation();

                final BlockPosition blockPosition = new BlockPosition(blockLocation.getBlockX(), blockLocation.getBlockY(), blockLocation.getBlockZ());
                final PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(0, blockPosition, Math.min((int) ((minion.getAnimationFrameTotal() * (10.0 / (minion.getMaxAnimationFrame() * minion.getAnimationCountStart()))) - 1), 9));

                for (final Player p : Bukkit.getOnlinePlayers()) {
                    final PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
                    connection.sendPacket(packet);
                }
                if (minion.getAnimationFrame() <= (minion.getMaxAnimationFrame() / 2)) {
                    minion.getArmorStand().setRightArmPose(
                            new EulerAngle(
                                    ((-0.15 / (minion.getMaxAnimationFrame() / 2.0)) * minion.getAnimationFrame()) * 3,
                                    0,
                                    ((-0.05 / (minion.getMaxAnimationFrame() / 2.0)) * minion.getAnimationFrame()) * 3
                            ));
                } else if (minion.getAnimationFrame() <= minion.getMaxAnimationFrame()) {
                    minion.getArmorStand().setRightArmPose(
                            new EulerAngle(
                                    (-0.45) + (0.15 / (minion.getMaxAnimationFrame() / 2.0)) * 3 * (minion.getAnimationFrame() - (minion.getMaxAnimationFrame() / 2.0)),
                                    0,
                                    (-0.15) + (0.05 / (minion.getMaxAnimationFrame() / 2.0)) * 3 * (minion.getAnimationFrame() - (minion.getMaxAnimationFrame() / 2.0))
                            ));
                } else {
                    minion.setAnimationFrame(0);
                    minion.setAnimationCount(minion.getAnimationCount() - 1);
                    if (minion.getAnimationCount() > 0) {
                        minion.setToStartAnimation(true);
                    } else {
                        minion.setAnimationFrameTotal(0);
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
