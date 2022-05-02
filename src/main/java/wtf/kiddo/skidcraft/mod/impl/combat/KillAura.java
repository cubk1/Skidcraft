package wtf.kiddo.skidcraft.mod.impl.combat;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet10Flying;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.RotationUtils;


public final class KillAura extends Mod {
    float[] rotation = new float[2];

    public KillAura() {
        super("KillAura", Category.COMBAT);
//        this.setEnabled(true);
        this.setKey(19);
    }

    @EventListener
    public void onUpdate(final UpdateEvent event) {
        for (Object en : mc.theWorld.loadedEntityList) {
            if (mc.thePlayer.getDistanceToEntity((Entity) en) < 4f && en != mc.thePlayer && mc.thePlayer.ticksExisted % 2 == 0 && en instanceof EntityLiving) {
                rotation = RotationUtils.getRotations4Attack(((Entity) en));
                mc.thePlayer.swingItem();
                mc.playerController.attackEntity(mc.thePlayer, (Entity) en);
                return;
            }
        }
        ;
        rotation[0] = 69f;
        rotation[1] = -1f;
    }

    @EventListener
    public void onPacket(final PacketEvent event) {
        Packet packet = event.getPacket();
        if (packet instanceof Packet10Flying && rotation[0] != 69f && rotation[1] != -1f) {
            ((Packet10Flying) packet).yaw = rotation[0];
            ((Packet10Flying) packet).pitch = rotation[1];
            mc.thePlayer.rotationYaw = rotation[0];
            mc.thePlayer.rotationPitch = rotation[1];
        }
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
