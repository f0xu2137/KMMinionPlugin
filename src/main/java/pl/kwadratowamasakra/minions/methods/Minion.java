package pl.kwadratowamasakra.minions.methods;

import org.bukkit.Color;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class Minion {

    private final ArmorStand armorStand;
    private final Color[] colors = new Color[4];

    private final List<MinionBlock> blocksQueue = new LinkedList<>();

    public Minion(final ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public final ArmorStand getArmorStand() {
        return armorStand;
    }

    public final ItemStack getItemStack() {
        return armorStand.getItemInHand();
    }

    private void setColor(final Color color) {
        colors[0] = color;
        for (int i = 1; i < 4; i++) {
            colors[i] = null;
        }
    }

    public final Color[] getColors() {
        return colors;
    }

    public final void setColors(final Color[] colors) {
        if (colors == null) {
            for (int i = 0; i < 4; i++) {
                this.colors[i] = null;
            }
            return;
        }
        if (colors.length == 1) {
            setColor(colors[0]);
            return;
        }
        System.arraycopy(colors, 0, this.colors, 0, 4);
    }

    public final void destroyMinion() {
        if (getItemStack() != null) {
            armorStand.getWorld().dropItem(armorStand.getLocation(), getItemStack());
        }
        armorStand.remove();
    }

    final List<MinionBlock> getBlocksQueue() {
        return blocksQueue;
    }

    public final void addBlock(final MinionBlock minionBlock) {
        blocksQueue.add(minionBlock);
    }

    public final void removeBlock(final MinionBlock minionBlock) {
        blocksQueue.remove(minionBlock);
    }

    public final MinionBlock getCurrentBlock() {
        return blocksQueue.isEmpty() ? null : blocksQueue.get(0);
    }
}
