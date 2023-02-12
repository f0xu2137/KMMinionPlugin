package pl.kwadratowamasakra.minions.listeners;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.methods.Minion;

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
                if (minion.getAnimationFrame() <= 3) {
                    minion.getArmorStand().setRightArmPose(new EulerAngle((-0.15) * minion.getAnimationFrame(), 0, (-0.05) * minion.getAnimationFrame()));
                } else if (minion.getAnimationFrame() <= 6) {
                    minion.getArmorStand().setRightArmPose(new EulerAngle((-0.45) + 0.15 * (minion.getAnimationFrame() - 3), 0, (-0.15) + 0.05 * (minion.getAnimationFrame() - 3)));
                } else {
                    minion.setAnimationFrame(0);
                    minion.setAnimationCount(minion.getAnimationCount() - 1);
                    if (minion.getAnimationCount() > 0) {
                        minion.setToStartAnimation(true);
                    }
                }
            }
        }
    }

}
