package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.MoveUtils;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class Strafe extends Mod {
    public Strafe() {
        super("Strafe", Category.MOVE);
//        this.setEnabled(false);
    }

    @EventListener
    public void onUpdateEvent(final UpdateEvent event) {
        MoveUtils.strafe();
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
