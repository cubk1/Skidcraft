package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.event.KeyInputEvent;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.MoveUtils;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class Sprint extends Mod {
    public Sprint() {
        super("Sprint", Category.Movement);
        this.setEnabled(true);
    }

    @EventListener
    public void onUpdateEvent(final UpdateEvent event) {
        if (event.isPre() && getMc().thePlayer.movementInput.moveForward > 0.0 && !getMc().thePlayer.isSneaking() && !getMc().thePlayer.isCollidedHorizontally && !getMc().thePlayer.isUsingItem() && getMc().thePlayer.getFoodStats().getFoodLevel() > 6) {
            getMc().thePlayer.setSprinting(true);
        }
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
