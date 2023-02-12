package pl.kwadratowamasakra.minions.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.kwadratowamasakra.minions.KMMinionPlugin;
import pl.kwadratowamasakra.minions.methods.Minion;

public class PlaceListener implements Listener {

    private final KMMinionPlugin plugin;

    public PlaceListener(final KMMinionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public final void onPlace(final BlockPlaceEvent e) {
        final Block block = e.getBlock();
        if (block.getType() == Material.STONE || block.getType() == Material.OBSIDIAN) {
            final Minion minion = plugin.getServerHelper().getMinionByLocation(block.getLocation().clone().add(0.5, 0, 0.5));
            if (minion != null) {
                minion.setAnimationFrameTotal(0);
                if (block.getType() == Material.STONE) {
                    minion.setAnimationCount(8);
                    minion.setAnimationCountStart(8);
                } else {
                    minion.setAnimationCount(16);
                    minion.setAnimationCountStart(16);
                }
                minion.setBlockLocation(block.getLocation());
                minion.setMaxAnimationFrame(8);
                minion.setToStartAnimation(true);
            }
        }
    }

}
