package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.event.LBMoveEvent;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;


public final class SafeWalk extends Mod {
    public SafeWalk() {
        super("SafeWalk", Category.Movement);
//        this.setEnabled(false);
    }

    @EventListener
    public void onUpdate(final LBMoveEvent event) {
        event.safeWalk = true;
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
