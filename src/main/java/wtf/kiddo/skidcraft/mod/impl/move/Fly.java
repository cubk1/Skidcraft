package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.event.KeyInputEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

public final class Fly extends Mod {
    public Fly() {
        super("Fly", Category.Movement);
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

    }

    @Override
    protected void onDisable() {

    }
}
