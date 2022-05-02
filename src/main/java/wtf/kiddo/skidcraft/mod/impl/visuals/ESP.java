package wtf.kiddo.skidcraft.mod.impl.visuals;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.EntityLiving;
import org.lwjgl.opengl.GL11;
import wtf.kiddo.skidcraft.event.Render3DEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

import static wtf.kiddo.skidcraft.utils.RenderUtils.drawTracerLine;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class ESP extends Mod {
    private int displayListid;

    public ESP() {
        super("ESP", Category.VISUALS);
        this.setEnabled(true);
    }

    @EventListener
    public void onRender3DEvent(final Render3DEvent event) {

        mc.theWorld.loadedEntityList.forEach(en -> {
            if(en != mc.thePlayer && en instanceof EntityLiving)
                drawTracerLine(((EntityLiving) en).posX,((EntityLiving) en).posY,((EntityLiving) en).posZ,255,255,255,200,3);
//            RenderUtils.renderBlock((int) ((EntityLiving) en).posX, (int) ((EntityLiving) en).posY, (int) ((EntityLiving) en).posZ);
        });

    }

    @Override
    protected void onEnable() {
        this.displayListid = GL11.glGenLists(1);
    }

    @Override
    protected void onDisable() {
        GL11.glDeleteLists(this.displayListid, 1);
    }
}
