package wtf.kiddo.skidcraft.mod.impl.move;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Potion;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.MoveUtils;

/**
 * Author: LiquidBounce
 */
public final class SpeedNCP extends Mod {

    public SpeedNCP() {

        super("Speed", Category.Movement);
//        this.setEnabled(false);
        this.setKey(47);
    }

    public static double getNormalSpeedEffect() {
        if (mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            return mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1;
        }

        return 0;
    }

    public static void setSpeed(double speed) {
        mc.thePlayer.motionX = -Math.sin(getDirection()) * speed;
        mc.thePlayer.motionZ = Math.cos(getDirection()) * speed;
    }

    public static float getDirection() {
        float yaw = mc.thePlayer.rotationYaw;
        if (mc.thePlayer.movementInput.moveForward < 0.0f) {
            yaw += 180.0f;
        }
        float forward = 1.0f;
        if (mc.thePlayer.movementInput.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (mc.thePlayer.movementInput.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (mc.thePlayer.movementInput.moveStrafe > 0.0f) {
            yaw -= 90.0f * forward;
        }
        if (mc.thePlayer.movementInput.moveStrafe < 0.0f) {
            yaw += 90.0f * forward;
        }
        return yaw * ((float) Math.PI / 180);
    }

    public static double getMotionX(double speed) {
        return -Math.sin(getDirection()) * speed;
    }

    public static double getMotionZ(double speed) {
        return Math.cos(getDirection()) * speed;
    }

    @EventListener
    public void onUpdateEvent(final LBUpdateEvent event) {
//     Credit   LiquidBounce by CCBlueX
        if (!event.isPre()) return;
        if (MoveUtils.isMoving()) {
            if (mc.thePlayer.onGround) {
                MoveUtils.strafe(0.56f);
                mc.thePlayer.motionY = 0.41999998688697815;
            } else {
                MoveUtils.strafe(MoveUtils.getSpeed() * mc.thePlayer.fallDistance > 0.4f ?
                        1.0f : 1.01f);
            }
        } else {
            mc.thePlayer.motionX = 0.0;
            mc.thePlayer.motionZ = 0.0;
        }

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    public boolean isToJump() {
        if (mc.thePlayer != null && !mc.thePlayer.isInWater() && !mc.thePlayer.isOnLadder()) return true;
        return false;
    }
}

