package wtf.kiddo.skidcraft.mod.impl.combat;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class Velocity extends Mod {
    public Velocity() {
        super("Velocity", Category.Combat);
    }

    @EventListener
    public void onTick(UpdateEvent event) {
        if (mc.thePlayer.hurtResistantTime > 0 && mc.thePlayer.hurtTime > 0) {
            mc.thePlayer.hurtResistantTime = 0;
            mc.thePlayer.hurtTime = 0;
            mc.thePlayer.motionX = 0.0;
            mc.thePlayer.motionY /= 10.0;
            mc.thePlayer.motionZ = 0.0;
        }
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
