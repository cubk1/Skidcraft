package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Packet15Place;
import wtf.kiddo.skidcraft.event.LBSlowDownEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

public final class NoSlowNCP extends Mod {

    public NoSlowNCP() {

        super("NoSlowdown", Category.Movement);
        this.setEnabled(true);
//        this.setKey(47);
    }

    @EventListener
    public void onMotion(final LBSlowDownEvent event) {
        if (mc.thePlayer.isBlocking()) {
            event.slowdown = 1f;
            mc.thePlayer.sendQueue.addToSendQueue(new Packet15Place(-1, -1, -1, 255, mc.thePlayer.inventory.getCurrentItem(), 0F, 0F, 0F));
        }
    }


    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
