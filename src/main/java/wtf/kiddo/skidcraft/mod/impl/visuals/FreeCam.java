package wtf.kiddo.skidcraft.mod.impl.visuals;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Packet10Flying;
import net.minecraft.src.Vec3;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class FreeCam extends Mod {
    private Vec3 pos = new Vec3(0D, 0D, 0D);

    public FreeCam() {
        super("FreeCam", Category.VISUALS);
        this.setEnabled(false);
    }

    @EventListener
    public void onPacket(final PacketEvent event) {
        if (event.getPacket() instanceof Packet10Flying) event.setCancelled(true);
    }

    @Override
    protected void onEnable() {
        mc.thePlayer.noClip = true;
        pos.xCoord = mc.thePlayer.posX;
        pos.yCoord = mc.thePlayer.posY;
        pos.zCoord = mc.thePlayer.posZ;
    }

    @Override
    protected void onDisable() {
        mc.thePlayer.noClip = false;
        mc.thePlayer.setPosition(pos.xCoord,pos.yCoord,pos.zCoord);
    }
}
