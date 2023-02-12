package pl.kwadratowamasakra.minions.methods;

import org.bukkit.Location;

public class MinionBlock {

    private int animationFrame;
    private int animationFrameTotal;
    private int animationCount;
    private int animationCountStart;
    private int maxAnimationFrame;
    private Location blockLocation;
    private boolean toStartAnimation;

    public final boolean isRunningAnimation() {
        return animationFrame > 0;
    }

    public final int getAnimationCount() {
        return animationCount;
    }

    public final void setAnimationCount(final int animationCount) {
        this.animationCount = animationCount;
    }

    public final int getAnimationCountStart() {
        return animationCountStart;
    }

    public final void setAnimationCountStart(final int animationCountStart) {
        this.animationCountStart = animationCountStart;
    }

    public final int getMaxAnimationFrame() {
        return maxAnimationFrame;
    }

    public final void setMaxAnimationFrame(final int maxAnimationFrame) {
        int frame = maxAnimationFrame;
        if (frame % 2 != 0) {
            frame += 1;
        }
        this.maxAnimationFrame = frame;
    }

    public final Location getBlockLocation() {
        return blockLocation;
    }

    public final void setBlockLocation(final Location blockLocation) {
        this.blockLocation = blockLocation;
    }

    public final int getAnimationFrame() {
        return animationFrame;
    }

    public final void setAnimationFrame(final int animationFrame) {
        this.animationFrame = animationFrame;
    }

    public final int getAnimationFrameTotal() {
        return animationFrameTotal;
    }

    public final void setAnimationFrameTotal(final int animationFrameTotal) {
        this.animationFrameTotal = animationFrameTotal;
    }

    public final boolean isToStartAnimation() {
        return toStartAnimation;
    }

    public final void setToStartAnimation(final boolean toStartAnimation) {
        this.toStartAnimation = toStartAnimation;
    }

}
