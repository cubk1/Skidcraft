package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.event.KeyInputEvent;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
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
    public void nmsl(final LBUpdateEvent event) {
        if (!MoveUtils.isMoving() || mc.thePlayer.isSneaking()
                || !(mc.thePlayer.getFoodStats().getFoodLevel() > 6.0f || mc.thePlayer.capabilities.allowFlying) || mc.thePlayer.movementInput.moveForward < 0.8F) {
            mc.thePlayer.setSprinting(false);
            return;
        }
        mc.thePlayer.setSprinting(true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
