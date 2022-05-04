package wtf.kiddo.skidcraft.mod.impl.render;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Packet10Flying;
import net.minecraft.src.Vec3;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class FreeCam extends Mod {
    private Vec3 pos = new Vec3(0D, 0D, 0D);

    public FreeCam() {
        super("FreeCam", Category.Render);
//        this.setEnabled(false);
    }

    @EventListener
    public void onPacket(final PacketEvent event) {
        if (event.getPacket() instanceof Packet10Flying) {
            Packet10Flying packet = (Packet10Flying) event.getPacket();
            packet.xPosition = pos.xCoord;
            packet.yPosition = pos.yCoord;
            packet.zPosition = pos.zCoord;
        }
    }
    public static float flightDown = 0.0f;

    @EventListener
    public void onUpdate(final UpdateEvent event) {
        mc.thePlayer.onGround = false;
        mc.thePlayer.motionX = 0.0D;
        mc.thePlayer.motionY = 0.0D;
        mc.thePlayer.motionZ = 0.0D;
        if (Keyboard.isKeyDown(mc.gameSettings.keyBindJump.keyCode) && mc.inGameHasFocus) {
            mc.thePlayer.motionY++;
        } else if (Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.keyCode) && mc.inGameHasFocus) {
            mc.thePlayer.motionY--;
        } else {
            mc.thePlayer.motionY = -flightDown;
        }
        double d5 = mc.thePlayer.rotationPitch + 90F;
        double d7 = mc.thePlayer.rotationYaw + 90F;
        boolean flag4 = Keyboard.isKeyDown(mc.gameSettings.keyBindForward.keyCode) && mc.inGameHasFocus;
        boolean flag6 = Keyboard.isKeyDown(mc.gameSettings.keyBindBack.keyCode) && mc.inGameHasFocus;
        boolean flag8 = Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.keyCode) && mc.inGameHasFocus;
        boolean flag10 = Keyboard.isKeyDown(mc.gameSettings.keyBindRight.keyCode) && mc.inGameHasFocus;
        if (flag4) {
            if (flag8) {
                d7 -= 45D;
            } else if (flag10) {
                d7 += 45D;
            }
        } else if (flag6) {
            d7 += 180D;
            if (flag8) {
                d7 += 45D;
            } else if (flag10) {
                d7 -= 45D;
            }
        } else if (flag8) {
            d7 -= 90D;
        } else if (flag10) {
            d7 += 90D;
        }
        if (flag4 || flag8 || flag6 || flag10) {
            mc.thePlayer.motionX = Math.cos(Math.toRadians(d7));
            mc.thePlayer.motionZ = Math.sin(Math.toRadians(d7));
        }
    }
    @Override
    protected void onEnable() {
        mc.thePlayer.noClip = true;
        pos.xCoord = mc.thePlayer.posX;
        pos.yCoord = mc.thePlayer.posY;
        pos.zCoord = mc.thePlayer.posZ;
    }

    @Override
    protected void onDisable() {
        mc.thePlayer.noClip = false;
        mc.thePlayer.setPosition(pos.xCoord,pos.yCoord,pos.zCoord);
    }
}
