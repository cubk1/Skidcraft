package wtf.kiddo.skidcraft.mod.impl.combat;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Packet28EntityVelocity;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: cubk
 * Created: 2022/5/1
 */
public final class Velocity extends Mod {
    public Velocity() {
        super("Velocity", Category.Combat);
    }

    @EventListener
    public void onTick(PacketEvent event) {
        if (event.getPacket() instanceof Packet28EntityVelocity && ((Packet28EntityVelocity) event.getPacket()).entityId == mc.thePlayer.entityId){
            ((Packet28EntityVelocity) event.getPacket()).motionX = 0;
            ((Packet28EntityVelocity) event.getPacket()).motionY = 0;
            ((Packet28EntityVelocity) event.getPacket()).motionZ = 0;
        }

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
