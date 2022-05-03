package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;


public final class Eagle extends Mod {
    public Eagle() {
        super("Eagle", Category.Movement);
//        this.setEnabled(false);
    }

    @EventListener
    public void onUpdate(final LBUpdateEvent event) {
        mc.gameSettings.keyBindSneak.pressed =
                mc.theWorld.isAirBlock((int) mc.thePlayer.posX,
                        (int) mc.thePlayer.posY - 1,
                        (int) mc.thePlayer.posZ);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
