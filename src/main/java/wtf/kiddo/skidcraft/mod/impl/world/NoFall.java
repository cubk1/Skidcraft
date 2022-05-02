package wtf.kiddo.skidcraft.mod.impl.world;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Packet10Flying;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class NoFall extends Mod {
    public NoFall() {
        super("NoFall", Category.World);
//        this.setEnabled(true);
    }

    @EventListener
    public void onUpdate(final PacketEvent event) {
        if (event.getPacket() instanceof Packet10Flying) {
            ((Packet10Flying) event.getPacket()).onGround = mc.thePlayer.ticksExisted % 2 == 0;
        }
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
