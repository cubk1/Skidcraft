package wtf.kiddo.skidcraft.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Vec3;

public class RotationUtils {
    private static Minecraft mc = Minecraft.getMinecraft();
    public static float[] getRotationFromPosition(double x, double z, double y) {
        float[] rotation = new float[2];
        double xDiff = x - mc.thePlayer.posX;
        double zDiff = z - mc.thePlayer.posZ;
        double yDiff = y - mc.thePlayer.posY - 1.2;
        double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        rotation[0] = (float)(Math.atan2(zDiff, xDiff) * 180.0 / Math.PI) - 90.0f;
        rotation[1] = (float)(- Math.atan2(yDiff, dist) * 180.0 / Math.PI);
        return rotation;
    }
    public static float[] getRotations4Attack(Entity en) {
        float[] rotation = new float[2];
        double xAim = (en.posX - 0.5) + (en.posX - en.lastTickPosX) * 2.5;
        double yAim = en.posY + (en.getEyeHeight() - en.height / 1.5) - (en.posY - en.lastTickPosY) * 1.5;
        double zAim = (en.posZ - 0.5) + (en.posZ - en.lastTickPosZ) * 2.5;
        Vec3 vec = new Vec3(xAim, yAim, zAim);
        double diffX = vec.xCoord + 0.5 - mc.thePlayer.posX;
        double diffY = vec.yCoord + 0.5
                - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double diffZ = vec.zCoord + 0.5 - mc.thePlayer.posZ;
        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / Math.PI);
        rotation[0] = mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw);
        rotation[1] = mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch);
        return rotation;
    }
}
