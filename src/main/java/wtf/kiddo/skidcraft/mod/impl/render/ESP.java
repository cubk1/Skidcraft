package wtf.kiddo.skidcraft.mod.impl.render;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.event.Render3DEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.util.RenderUtil;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zcy
 * Created: 2022/5/3
 * Pasted from Summer
 */
public final class ESP extends Mod {
    private static final Map<EntityLiving, float[][]> entities = new HashMap<>();
    private final FloatBuffer windowPosition = BufferUtils.createFloatBuffer(4);
    private final IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
    private final FloatBuffer modelMatrix = GLAllocation.createDirectFloatBuffer(16);
    private final FloatBuffer projectionMatrix = GLAllocation.createDirectFloatBuffer(16);
    private final Map<EntityLiving, float[]> entityPosMap = new HashMap<>();

    private final static BooleanValue players = new BooleanValue("Players",true);
    private final static BooleanValue mobs = new BooleanValue("Mobs",true);
    private final static BooleanValue passives = new BooleanValue("Passives",true);
    private final static BooleanValue animals = new BooleanValue("Animals",true);
    private final static BooleanValue invisibles = new BooleanValue("Invisibles",true);

    private final static BooleanValue box = new BooleanValue("Box",true);
    private final static BooleanValue healthBar = new BooleanValue("HealthBar",true);
    private final static BooleanValue nametags = new BooleanValue("NameTags",true);
    public ESP() {
        super("2DESP", Category.Render);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    private boolean isValid(EntityLiving entity) {
        return getMc().thePlayer != entity && entity.entityId != -1488 && isValidType(entity) && entity.isEntityAlive() && (!entity.isInvisible() || invisibles.getValue());
    }

    private boolean isValidType(EntityLiving entity) {
        return (players.getValue() && entity instanceof EntityPlayer) || ((mobs.getValue() && (entity instanceof EntityMob || entity instanceof EntitySlime)) || (passives.getValue() && (entity instanceof EntityVillager || entity instanceof EntityGolem)) || (animals.getValue() && entity instanceof EntityAnimal));
    }

    @EventListener
    public void onRender2DEvent(final Render2DEvent event) {
        for (EntityLiving player : entityPosMap.keySet()) {
            if(!isValid(player)) continue;
            GL11.glPushMatrix();
            float[] positions = entityPosMap.get(player);
            float x = positions[0];
            float x2 = positions[1];
            float y = positions[2];
            float y2 = positions[3];
            if (healthBar.getValue()) {
                RenderUtil.drawRect(x - 2.5f, y - 0.5F, x - 0.5F, y2 + 0.5F, 0x96000000);
                float health = player.getHealth();
                float maxHealth = player.getMaxHealth();
                float healthPercentage = health / maxHealth;
                boolean needScissor = health < maxHealth;
                float heightDif = y - y2;
                float healthBarHeight = heightDif * healthPercentage;
                if (needScissor)
                    startScissorBox(event.getScaledResolution(), (int) x - 2, (int) (y2 + healthBarHeight), 2, (int) -healthBarHeight + 1);
                int col = RenderUtil.getColorFromPercentage(health, maxHealth);
                RenderUtil.drawRect(x - 2, y, x - 1, y2, col);
                if (needScissor)
                    endScissorBox();
            }
            if (nametags.getValue()) {
                String text = player.getEntityName();
                float xDif = x2 - x;
                float minScale = 0.65F;
                float scale = Math.max(minScale,
                        Math.min(1.0F, 1.0F - (getMc().thePlayer.getDistanceToEntity(player) / 100.0F)));
                float yOff = Math.max(0.0F,
                        Math.min(1.0F, getMc().thePlayer.getDistanceToEntity(player) / 12.0F));
                float upscale = 1.0F / scale;
                GL11.glPushMatrix();
                GL11.glScalef(scale, scale, scale);
                getMc().fontRenderer.drawStringWithShadow(text, (int)((x + xDif / 2.0F) * upscale - getMc().fontRenderer.getStringWidth(text) / 2.0F), (int)((y - 9 + yOff) * upscale), -1);
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
            }
            if (box.getValue()) {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                enableAlpha();
                GL11.glLineWidth(1.3F);
                GL11.glColor4f(1.0f,0.0f,0.0f, 1.0F);
                GL11.glBegin(GL11.GL_LINE_LOOP);
                GL11.glVertex2f(x, y);
                GL11.glVertex2f(x, y2);
                GL11.glVertex2f(x2, y2);
                GL11.glVertex2f(x2, y);
                GL11.glEnd();
                disableAlpha();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }
            GL11.glPopMatrix();
        }
    }

    @EventListener
    public void onRender3DEvent(final Render3DEvent event) {
        entities.keySet().removeIf(player -> !getMc().theWorld.loadedEntityList.contains(player));
        if (!entityPosMap.isEmpty())
            entityPosMap.clear();
        if (box.getValue() || healthBar.getValue() || nametags.getValue()) {
            int scaleFactor = event.getScaledResolution().getScaleFactor();
            for (Object o : getMc().theWorld.loadedEntityList) {
                if(o instanceof EntityLiving) {
                    EntityLiving player = (EntityLiving) o;
                    if (player.getDistanceToEntity(getMc().thePlayer) < 1.0F)
                        continue;
                    GL11.glPushMatrix();
                    Vec3 vec3 = getVec3(player);
                    float posX = (float) (vec3.xCoord - RenderManager.instance.viewerPosX);
                    float posY = (float) (vec3.yCoord - RenderManager.instance.viewerPosY);
                    float posZ = (float) (vec3.zCoord - RenderManager.instance.viewerPosZ);
                    double halfWidth = player.width / 2.0D + 0.18F;
                    AxisAlignedBB bb = new AxisAlignedBB(posX - halfWidth, posY, posZ - halfWidth, posX + halfWidth,
                            posY + player.height + 0.18D, posZ + halfWidth);
                    double[][] vectors = {{bb.minX, bb.minY, bb.minZ}, {bb.minX, bb.maxY, bb.minZ},
                            {bb.minX, bb.maxY, bb.maxZ}, {bb.minX, bb.minY, bb.maxZ}, {bb.maxX, bb.minY, bb.minZ},
                            {bb.maxX, bb.maxY, bb.minZ}, {bb.maxX, bb.maxY, bb.maxZ}, {bb.maxX, bb.minY, bb.maxZ}};
                    Vector3f projection;
                    Vector4f position = new Vector4f(Float.MAX_VALUE, Float.MAX_VALUE, -1.0F, -1.0F);
                    for (double[] vec : vectors) {
                        projection = project2D((float) vec[0], (float) vec[1], (float) vec[2], scaleFactor);
                        if (projection != null && projection.z >= 0.0F && projection.z < 1.0F) {
                            position.x = Math.min(position.x, projection.x);
                            position.y = Math.min(position.y, projection.y);
                            position.z = Math.max(position.z, projection.x);
                            position.w = Math.max(position.w, projection.y);
                        }
                    }
                    entityPosMap.put(player, new float[]{position.x, position.z, position.y, position.w});
                    GL11.glPopMatrix();
                }
            }
        }
    }

    public static void startScissorBox(ScaledResolution sr, int x, int y, int width, int height) {
        int sf = sr.getScaleFactor();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(x * sf, (sr.getScaledHeight() - (y + height)) * sf, width * sf, height * sf);
    }

    public static void endScissorBox() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void enableAlpha() {
        GL11.glEnable(GL11.GL_BLEND);
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
    }

    public static void disableAlpha() {
        GL11.glDisable(GL11.GL_BLEND);
    }

    private Vector3f project2D(float x, float y, float z, int scaleFactor) {
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelMatrix);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projectionMatrix);
        GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
        if (GLU.gluProject(x, y, z, modelMatrix, projectionMatrix, viewport, windowPosition)) {
            return new Vector3f(windowPosition.get(0) / scaleFactor,
                    (getMc().displayHeight - windowPosition.get(1)) / scaleFactor, windowPosition.get(2));
        }

        return null;
    }

    private Vec3 getVec3(final EntityLiving var0) {
        final float timer = getMc().timer.renderPartialTicks;
        final double x = var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * timer;
        final double y = var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * timer;
        final double z = var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * timer;
        return new Vec3(null,x, y, z);
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB var0) {
        Tessellator var1 = Tessellator.instance;
        var1.startDrawing(3);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.draw();
        var1.startDrawing(3);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.draw();
        var1.startDrawing(1);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.draw();
    }
}
