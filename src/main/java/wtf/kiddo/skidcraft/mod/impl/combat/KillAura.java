package wtf.kiddo.skidcraft.mod.impl.combat;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet39AttachEntity;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.RotationUtils;


public final class KillAura extends Mod {
    public KillAura() {
        super("KillAura", Category.COMBAT);
//        this.setEnabled(true);
    }

    @EventListener
    public void onUpdate(final UpdateEvent event) {
        for(Object en : mc.theWorld.loadedEntityList){
            if(mc.thePlayer.getDistanceToEntity((Entity) en) < 4f){
/*                float[] roatation = RotationUtils.getRotationFromPosition(((Entity) en).posX,((Entity) en).posY+1.5,((Entity) en).posZ);
                event.setYaw(roatation[0]);
                event.setPitch(roatation[1]);*/
                mc.playerController.attackEntity(mc.thePlayer, (Entity) en);
            }
        };
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
