package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.MathHelper;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.event.KeyInputEvent;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
import wtf.kiddo.skidcraft.gui.clickgui.ClickGUI;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.MoveUtils;

public final class InvMove extends Mod {
    public InvMove() {
        super("InvMove", Category.Movement);
//        this.setEnabled(false);
    }
    //     Credit   https://github.com/cubk/VapuLite-1
    @EventListener
    public void onKeyInputEvent(final LBUpdateEvent event) {
        if (!(mc.currentScreen instanceof GuiContainer) && !(mc.currentScreen instanceof ClickGUI)) {
            return;
        }
        double speed = 0.05;
        if (!mc.thePlayer.onGround) {
            speed /= 4.0;
        }
        this.handleJump();
        this.handleForward(speed);
        if (!mc.thePlayer.onGround) {
            speed /= 2.0;
        }
        this.handleBack(speed);
        this.handleLeft(speed);
        this.handleRight(speed);
    }

    void moveForward(double speed) {
        float direction = (float) MoveUtils.getDirection();
        mc.thePlayer.motionX -= (double) MathHelper.sin(direction) * speed;
        mc.thePlayer.motionZ += (double) MathHelper.cos(direction) * speed;
    }

    void moveBack(double speed) {
        float direction = (float) MoveUtils.getDirection();
        mc.thePlayer.motionX += (double) MathHelper.sin(direction) * speed;
        mc.thePlayer.motionZ -= (double) MathHelper.cos(direction) * speed;
    }

    void moveLeft(double speed) {
        float direction = (float) MoveUtils.getDirection();
        mc.thePlayer.motionZ += (double) MathHelper.sin(direction) * speed;
        mc.thePlayer.motionX += (double) MathHelper.cos(direction) * speed;
    }

    void moveRight(double speed) {
        float direction = (float) MoveUtils.getDirection();
        mc.thePlayer.motionZ -= (double) MathHelper.sin(direction) * speed;
        mc.thePlayer.motionX -= (double) MathHelper.cos(direction) * speed;
    }

    void handleForward(double speed) {
        if (!Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode())) {
            return;
        }
        this.moveForward(speed);
    }

    void handleBack(double speed) {
        if (!Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode())) {
            return;
        }
        this.moveBack(speed);
    }

    void handleLeft(double speed) {
        if (!Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())) {
            return;
        }
        this.moveLeft(speed);
    }

    void handleRight(double speed) {
        if (!Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
            return;
        }
        this.moveRight(speed);
    }

    void handleJump() {
        if (mc.thePlayer.onGround && Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())) {
            mc.thePlayer.jump();
        }
    }
    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
