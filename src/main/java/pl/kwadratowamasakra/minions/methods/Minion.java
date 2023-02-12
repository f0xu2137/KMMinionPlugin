package pl.kwadratowamasakra.minions.methods;

import org.bukkit.Color;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public class Minion {

    private final ArmorStand armorStand;
    private final Color[] colors = new Color[4];
    private int animationFrame;
    private boolean toStartAnimation;
    private int animationCount;

    public Minion(final ArmorStand armorStand) {
        this.armorStand = armorStand;
    }

    public final ArmorStand getArmorStand() {
        return armorStand;
    }

    public final ItemStack getItemStack() {
        return armorStand.getItemInHand();
    }

    public final int getAnimationFrame() {
        return animationFrame;
    }

    public final void setAnimationFrame(final int animationFrame) {
        this.animationFrame = animationFrame;
    }

    public final boolean isToStartAnimation() {
        return toStartAnimation;
    }

    public final void setToStartAnimation(final boolean toStartAnimation) {
        this.toStartAnimation = toStartAnimation;
    }

    public final boolean isRunningAnimation() {
        return animationFrame > 0;
    }

    public final int getAnimationCount() {
        return animationCount;
    }

    public final void setAnimationCount(final int animationCount) {
        this.animationCount = animationCount;
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
}
