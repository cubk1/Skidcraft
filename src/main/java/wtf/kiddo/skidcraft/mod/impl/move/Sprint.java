package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.client.Minecraft;
import wtf.kiddo.skidcraft.event.KeyInputEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class Sprint extends Mod {
    public Sprint() {
        super("Sprint", Category.MOVE);
//        this.setEnabled(false);
    }

    @EventListener
    public void onKeyInputEvent(final KeyInputEvent event) {
        mc.thePlayer.setSprinting(true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
