package wtf.kiddo.skidcraft.event;

import me.bush.eventbus.event.Event;
import net.minecraft.client.Minecraft;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class LBUpdateEvent extends Event {
    private boolean isPre;

    public LBUpdateEvent(final boolean pre) {
        this.isPre = pre;
    }

    public LBUpdateEvent() {

    }
    public boolean isPre() {
        return isPre;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}
