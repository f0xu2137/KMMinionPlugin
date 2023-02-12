package pl.kwadratowamasakra.minions.methods;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ServerHelper {

    private final List<Minion> minionList = new ArrayList<>();

    public final List<Minion> getMinions() {
        return minionList;
    }

    public final void addMinion(final Minion minion) {
        minionList.add(minion);
    }

    public final void removeMinion(final Minion minion) {
        minionList.remove(minion);
    }

    public final Minion getMinionByLocation(final Location location) {
        for (final Minion minion : minionList) {
            final Location minionLoc = minion.getArmorStand().getLocation().getBlock().getLocation().clone().add(0.5, 0, 0.5);
            if (minionLoc.getBlockY() == location.getBlockY() && location.distance(minionLoc) < 2) {
                final Location loc = minion.getArmorStand().getLocation();
                minion.getArmorStand().teleport(lookAt(loc, location));
                return minion;
            }
        }
        return null;
    }

    private static Location lookAt(final Location entity, final Location target) {
        final Vector dirBetweenLocations = target.toVector().subtract(entity.toVector());
        return entity.setDirection(dirBetweenLocations);
    }
}
