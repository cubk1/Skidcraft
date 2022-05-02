package wtf.kiddo.skidcraft.mod.impl.visuals;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Entity;
import net.minecraft.src.Packet39AttachEntity;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.RenderUtils;
import wtf.kiddo.skidcraft.utils.RotationUtils;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class ESP extends Mod {
    public ESP() {
        super("ESP", Category.VISUALS);
        this.setEnabled(true);
    }

    @EventListener
    public void onRender2DEvent(final Render2DEvent event) {
        mc.theWorld.loadedEntityList.forEach(en -> {
            RenderUtils.drawBlock((Entity) en,255,255,255,255,0.5F);
        });
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
