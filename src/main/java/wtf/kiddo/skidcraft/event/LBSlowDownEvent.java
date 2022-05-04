package wtf.kiddo.skidcraft.event;

import me.bush.eventbus.event.Event;
import net.minecraft.client.Minecraft;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class LBSlowDownEvent extends Event {
    public float slowdown = 1;

    public LBSlowDownEvent(float slowdown) {
        this.slowdown = slowdown;
    }

    public LBSlowDownEvent() {

    }


    @Override
    protected boolean isCancellable() {
        return true;
    }
}
