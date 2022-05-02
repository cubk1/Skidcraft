package wtf.kiddo.skidcraft.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.RenderManager;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static void drawBlock(Entity en, float r, float g, float b, float a, float scale) {
        double x = en.posX - 0.5;
        double y = en.posY - 0.3;
        double z = en.posZ - 0.5;
        double pX = RenderManager.renderPosX;
        double pY = RenderManager.renderPosY;
        double pZ = RenderManager.renderPosZ;
        float tr = (1 - scale) / 2;
        GL11.glPushMatrix();
        GL11.glTranslated(-pX, -pY, -pZ);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(r, g, b, a);
        GL11.glTranslated(x, y, z);
        GL11.glDepthMask(false);
        GL11.glPushMatrix();
        GL11.glTranslatef(tr, tr, tr);
        GL11.glScalef(scale, scale, scale);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex3f(1, 1, 0);
        GL11.glVertex3f(0, 1, 0);
        GL11.glVertex3f(0, 1, 1);
        GL11.glVertex3f(1, 1, 1);
        GL11.glVertex3f(1, 0, 1);
        GL11.glVertex3f(0, 0, 1);
        GL11.glVertex3f(0, 0, 0);
        GL11.glVertex3f(1, 0, 0);
        GL11.glVertex3f(1, 1, 1);
        GL11.glVertex3f(0, 1, 1);
        GL11.glVertex3f(0, 0, 1);
        GL11.glVertex3f(1, 0, 1);
        GL11.glVertex3f(1, 0, 0);
        GL11.glVertex3f(0, 0, 0);
        GL11.glVertex3f(0, 1, 0);
        GL11.glVertex3f(1, 1, 0);
        GL11.glVertex3f(0, 1, 1);
        GL11.glVertex3f(0, 1, 0);
        GL11.glVertex3f(0, 0, 0);
        GL11.glVertex3f(0, 0, 1);
        GL11.glVertex3f(1, 1, 0);
        GL11.glVertex3f(1, 1, 1);
        GL11.glVertex3f(1, 0, 1);
        GL11.glVertex3f(1, 0, 0);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();

    }

}
