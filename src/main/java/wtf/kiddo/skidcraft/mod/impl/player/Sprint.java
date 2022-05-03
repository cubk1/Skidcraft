package wtf.kiddo.skidcraft.mod.impl.player;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;

/**
 * Author: zcy
 * Created: 2022/5/4
 */
public final class Sprint extends Mod {

    public Sprint() {
        super("Sprint", Category.PLAYER);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @EventListener
    public void onUpdateEvent(final UpdateEvent event) {
        if (event.isPre() && getMc().thePlayer.movementInput.moveForward > 0.0 && !getMc().thePlayer.isSneaking() && !getMc().thePlayer.isCollidedHorizontally && !getMc().thePlayer.isUsingItem() && getMc().thePlayer.getFoodStats().getFoodLevel() > 6) {
            getMc().thePlayer.setSprinting(true);
        }
    }
}
