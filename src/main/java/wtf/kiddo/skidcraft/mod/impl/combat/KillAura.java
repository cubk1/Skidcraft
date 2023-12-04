package wtf.kiddo.skidcraft.mod.impl.combat;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.RenderUtils;
import wtf.kiddo.skidcraft.utils.RotationUtils;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;
import wtf.kiddo.skidcraft.value.impl.NumberValue;

import java.awt.*;

import static wtf.kiddo.skidcraft.utils.RenderUtils.getNextPostion;


public final class KillAura extends Mod {
    private final static BooleanValue players = new BooleanValue("Players",true);
    private final static BooleanValue mobs = new BooleanValue("Mobs",true);
    private final static BooleanValue animals = new BooleanValue("Animals",true);
    private final static BooleanValue invisibles = new BooleanValue("Invisibles",true);

    private static final BooleanValue silentRotationValue = new BooleanValue("SilentRotation",true);
    private static final BooleanValue autoblockValue = new BooleanValue("AutoBlock",true);

    private final static BooleanValue targethud = new BooleanValue("TargetHUD",true);
    private static final NumberValue<Float> rangeValue = new NumberValue<>("Range", 4.0f, 1.0f, 6.0f, 0.1f);
    float[] rotation = new float[2];
    public KillAura() {
        super("KillAura", Category.Combat);
    }

    @EventListener
    public void onUpdate(final LBUpdateEvent event) {

        for (Object en : mc.theWorld.loadedEntityList) {
            if (mc.thePlayer.getDistanceToEntity((Entity) en) < rangeValue.getValue() && en != mc.thePlayer && en instanceof EntityLiving) {
                rotation = RotationUtils.getRotations4Attack(((Entity) en));
                if (!silentRotationValue.getValue()) {
                    mc.thePlayer.rotationYaw = rotation[0];
                    mc.thePlayer.rotationPitch = rotation[1];
                }
                if(en instanceof EntityPlayer && !players.getValue())
                    return;
                if(en instanceof EntityMob && !mobs.getValue())
                    return;
                if(en instanceof EntityAnimal && !animals.getValue())
                    return;
                if(((EntityLiving) en).isInvisible() && !invisibles.getValue())
                    return;



                if(autoblockValue.getValue() && !((EntityLiving) en).isDead && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword ){
                    // https://github.com/BapeDeveloperTeam/Bape-Opensource/blob/main/src/main/java/mc/bape/module/blatant/Killaura.java
                    mc.thePlayer.getCurrentEquippedItem().useItemRightClick(mc.theWorld, mc.thePlayer);
                }
                mc.thePlayer.swingItem();
//                mc.playerController.attackEntity(mc.thePlayer, (Entity) en);
//                mc.gameSettings.keyBindUseItem.pressed = false;
                if (mc.thePlayer.ticksExisted % 2 == 0)
                    mc.thePlayer.sendQueue.addToSendQueue(new Packet7UseEntity(mc.thePlayer.entityId, ((EntityLiving) en).entityId, 1));
//                mc.gameSettings.keyBindUseItem.pressed = true;
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

        }
    }
    int animWidth;
    float f6;

    @EventListener
    public void onR2D(final Render2DEvent event) {
        for (Object en : mc.theWorld.loadedEntityList) {
            if (mc.thePlayer.getDistanceToEntity((Entity) en) < rangeValue.getValue() && en != mc.thePlayer && en instanceof EntityLiving && targethud.getValue()) {
                ScaledResolution sr = new ScaledResolution(mc);
                final int n4 = sr.getScaledWidth() / 2 + 150;
                final int n5 = sr.getScaledHeight() / 2 + 100;
                int n15 = -1;

                RenderUtils.drawRect1(n4 - 1.0f, n5 + 4.0f, 105.0f, 45.0f, new Color(0, 0, 0, 100));
                
                GL11.glEnable(GL11.GL_BLEND);

                mc.fontRenderer.drawString(((EntityLiving) en).getEntityName(), n4 + 22, n5 + 6,-1);
                GL11.glPushMatrix();
                GL11.glTranslatef(n4, n5, 1.0f);
                GL11.glScalef(2.0f, 2.0f, 2.0f);
                GL11.glTranslatef((float)(-n4), (float)(-n5), (float)1.0f);
                mc.fontRenderer.drawString(String.valueOf(new StringBuilder().append((double)Math.round((double)(((EntityLiving) en).getHealth() / 2.0f) * 10.0) / 10.0).append(" \u2764")), n4 + 10, n5 + 9, n15);
                GL11.glPopMatrix();
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                f6 = 105.0f * ((EntityLiving) en).getHealth() / ((EntityLiving) en).getMaxHealth();
                if ((float)animWidth > f6) {
                    animWidth = getNextPostion(animWidth, (int)f6, 100.0);
                }
                if ((float)animWidth < f6) {
                    animWidth = getNextPostion(animWidth, (int)f6, 100.0);
                }

                RenderUtils.drawRect1(n4 + -1, n5 + 46, animWidth, 3.0f,Color.GREEN);

            }
        }
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
