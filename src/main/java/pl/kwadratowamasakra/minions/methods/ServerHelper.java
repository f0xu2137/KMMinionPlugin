package pl.kwadratowamasakra.minions.methods;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import pl.kwadratowamasakra.minions.utils.ItemBuilder;

import java.util.*;

public class ServerHelper {

    private final ItemStack defaultGlass;
    private final List<Minion> minionList = new ArrayList<>();
    private final Map<UUID, Integer> clickedMinions = new HashMap<>();

    public ServerHelper() {
        defaultGlass = setDefaultGlass();
    }

    private static Location lookAt(final Location entity, final Location target) {
        final Vector dirBetweenLocations = target.toVector().subtract(entity.toVector());
        return entity.setDirection(dirBetweenLocations);
    }

    private static ItemStack setDefaultGlass() {
        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 7).setTitle(" ").addLore(" ").build();
    }

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

    public final Minion getMinionById(final int id) {
        for (final Minion minion : minionList) {
            if (minion.getArmorStand().getEntityId() == id) {
                return minion;
            }
        }
        return null;
    }

    public final ItemStack getDefaultGlass() {
        return defaultGlass;
    }

    public final int getOpenedInv(final UUID uuid) {
        return clickedMinions.get(uuid);
    }

    public final void putOpenedInv(final UUID uuid, final int id) {
        clickedMinions.put(uuid, id);
    }

    public final void removeOpenedInv(final UUID uuid) {
        clickedMinions.remove(uuid);
    }
}
