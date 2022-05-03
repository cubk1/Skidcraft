package wtf.kiddo.skidcraft.mod.impl.render;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.RenderManager;
import org.lwjgl.opengl.GL11;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

import java.awt.*;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class ESP extends Mod {
    public ESP() {
        super("ESP", Category.Render);
        this.setEnabled(true);
    }

    public static void dtl(final Entity e, final int color, final float lw) {
        if (e == null) {
            return;
        }
        final double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.viewerPosX;
        final double y = e.getEyeHeight() + e.lastTickPosY + (e.posY - e.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.viewerPosY;
        final double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.viewerPosZ;
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(lw);
        GL11.glColor4f(r, g, b, a);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0, 0.0 + mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    @EventListener
    public void onRender2DEvent(final Render2DEvent event) {
        mc.theWorld.loadedEntityList.forEach(en -> {
            if (en instanceof EntityLiving)
                dtl((Entity) en, new Color(255, 255, 255).getRGB(), 10);
        });
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}