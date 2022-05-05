package wtf.kiddo.skidcraft.mod.impl.world;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.*;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.RotationUtils;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;
import wtf.kiddo.skidcraft.value.impl.NumberValue;


public final class Timer extends Mod {
    private static final NumberValue<Float> speedValue = new NumberValue<>("Speed", 2.0f, 1.0f, 5.0f, 0.1f);
    public Timer() {
        super("Timer", Category.World);
    }

    @EventListener
    public void onUpdate(UpdateEvent event) {
        mc.timer.timerSpeed = speedValue.getValue();
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {
        mc.timer.timerSpeed = 1.0f;
    }
}
