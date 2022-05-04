package wtf.kiddo.skidcraft.event;

import me.bush.eventbus.event.Event;
import net.minecraft.client.Minecraft;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class LBMoveEvent extends Event {
    public boolean safeWalk;

    public LBMoveEvent(final boolean safeWalk) {
        this.safeWalk = safeWalk;
    }

    public LBMoveEvent() {

    }
    public boolean safeWalking() {
        return this.safeWalk;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
