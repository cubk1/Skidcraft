package wtf.kiddo.skidcraft.event;

import me.bush.eventbus.event.Event;
import net.minecraft.src.ScaledResolution;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class Render3DEvent extends Event {
    private final float partialTicks;
    private final ScaledResolution scaledResolution;

    public Render3DEvent(float partialTicks, ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public ScaledResolution getScaledResolution() {
        return scaledResolution;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
