/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions.methods;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class Minion {

    private final ArmorStand armorStand;
    private final List<MinionBlock> blocksQueue = new LinkedList<>();
    private Color color = null;

    public Minion(final ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public final ArmorStand getArmorStand() {
        return armorStand;
    }

    public final ItemStack getItemStack() {
        return armorStand.getItemInHand();
    }

    public final Color getColor() {
        return color;
    }

    public final void setColor(final Color color) {
        this.color = color;
    }

    public final void destroyMinion() {
        if (getItemStack() != null && getItemStack().getType() != Material.AIR) {
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
