package wtf.kiddo.skidcraft.util;

import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Author: zcy
 * Created: 2022/5/3
 */
public final class RenderUtil {

    public static int getColorFromPercentage(float current, float max) {
        float percentage = (current / max) / 3;
        return Color.HSBtoRGB(percentage, 1.0F, 1.0F);
    }

    public static void glRenderStart() {
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public static void glRenderStop() {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void drawRect(float x, float y, float d, float e, int color) {
        float f = (float)(color >> 24 & 255) / 255.0F;
        float f1 = (float)(color >> 16 & 255) / 255.0F;
        float f2 = (float)(color >> 8 & 255) / 255.0F;
        float f3 = (float)(color & 255) / 255.0F;
        glRenderStart();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(d, y);
        GL11.glVertex2d(d, e);
        GL11.glVertex2d(x, e);
        GL11.glEnd();
        glRenderStop();
    }

    public static void drawBorderedRect(float xPos, float yPos, float width, float height, float lineWidth, int lineColor, int bgColor) {
        drawRect(xPos, yPos, width, height, bgColor);
        float f = (float)(lineColor >> 24 & 255) / 255.0F;
        float f1 = (float)(lineColor >> 16 & 255) / 255.0F;
        float f2 = (float)(lineColor >> 8 & 255) / 255.0F;
        float f3 = (float)(lineColor & 255) / 255.0F;
        glRenderStart();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glLineWidth(lineWidth);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(xPos, yPos);
        GL11.glVertex2d(width, yPos);
        GL11.glVertex2d(width, yPos);
        GL11.glVertex2d(width, height);
        GL11.glVertex2d(width, height);
        GL11.glVertex2d(xPos, height);
        GL11.glVertex2d(xPos, height);
        GL11.glVertex2d(xPos, yPos);
        GL11.glEnd();
        glRenderStop();
    }

    public static void drawGradient(double x, double y, double x2, double y2, int col1, int col2) {
        float f = (float)(col1 >> 24 & 255) / 255.0F;
        float f1 = (float)(col1 >> 16 & 255) / 255.0F;
        float f2 = (float)(col1 >> 8 & 255) / 255.0F;
        float f3 = (float)(col1 & 255) / 255.0F;
        float f4 = (float)(col2 >> 24 & 255) / 255.0F;
        float f5 = (float)(col2 >> 16 & 255) / 255.0F;
        float f6 = (float)(col2 >> 8 & 255) / 255.0F;
        float f7 = (float)(col2 & 255) / 255.0F;
        glRenderStart();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
        glRenderStop();
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 255) / 255.0F;
        float f1 = (float)(col1 >> 16 & 255) / 255.0F;
        float f2 = (float)(col1 >> 8 & 255) / 255.0F;
        float f3 = (float)(col1 & 255) / 255.0F;
        float f4 = (float)(col2 >> 24 & 255) / 255.0F;
        float f5 = (float)(col2 >> 16 & 255) / 255.0F;
        float f6 = (float)(col2 >> 8 & 255) / 255.0F;
        float f7 = (float)(col2 & 255) / 255.0F;
        glRenderStart();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glShadeModel(GL11.GL_FLAT);
        glRenderStop();
    }
}
