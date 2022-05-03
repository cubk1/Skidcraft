package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.util.Timer;

public final class LiquidWalk extends Mod {
    public LiquidWalk() {
        super("LiquidWalk", Category.Movement);
    }

    @EventListener
    public void onUpdate(final UpdateEvent event) {
        if(mc.thePlayer.isInWater()){
            mc.thePlayer.motionY = 0.2D;
        }
    }


    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
