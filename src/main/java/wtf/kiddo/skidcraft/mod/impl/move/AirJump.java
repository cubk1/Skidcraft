package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.client.Minecraft;
import wtf.kiddo.skidcraft.event.KeyInputEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;


public final class AirJump extends Mod {
    public AirJump() {
        super("AirJump", Category.Movement);
//        this.setEnabled(false);
    }

    @EventListener
    public void onKeyInputEvent(final KeyInputEvent event) {
        if (/*getMc().gameSettings.keyBindJump.pressed*/event.getKey() == 57) {
            Minecraft mc = getMc();
            mc.thePlayer.onGround = true;
            mc.thePlayer.isAirBorne = false;
        }
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
