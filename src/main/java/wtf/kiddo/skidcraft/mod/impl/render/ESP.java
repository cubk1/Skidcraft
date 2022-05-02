package wtf.kiddo.skidcraft.mod.impl.render;

import me.bush.eventbus.annotation.EventListener;
import org.lwjgl.opengl.GL11;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class ESP extends Mod {
    public ESP() {
        super("ESP", Category.Render);
        this.setEnabled(true);
    }

    @EventListener
    public void onRender2DEvent(final Render2DEvent event) {
        mc.theWorld.loadedEntityList.forEach(en -> {
            boolean bl = GL11.glIsEnabled(2848);
            boolean bl2 = GL11.glIsEnabled(2929);
            boolean bl3 = GL11.glIsEnabled(3553);
            boolean bl4 = GL11.glIsEnabled(3042);
            GL11.glPushMatrix();
            if (!bl) {
                GL11.glEnable(2848);
            }
            if (bl2) {
                GL11.glDisable(2929);
            }
            if (bl3) {
                GL11.glDisable(3553);
            }
            GL11.glBlendFunc(770, 771);
            if (!bl4) {
                GL11.glEnable(3042);
            }
            GL11.glColor4f(0.1f, 0.3f, 0.6f, 0.3f);
            if (!bl) {
                GL11.glDisable(2848);
            }
            if (bl2) {
                GL11.glEnable(2929);
            }
            if (bl3) {
                GL11.glEnable(3553);
            }
            if (!bl4) {
                GL11.glDisable(3042);
            }
            GL11.glPopMatrix();
        });
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}