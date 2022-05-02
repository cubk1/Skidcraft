package wtf.kiddo.skidcraft.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.src.MathHelper;

public class RotationUtils {
    private static Minecraft mc = Minecraft.getMinecraft();
    public static float[] getRotationFromPosition(double x, double z, double y) {
        float[] rotation = new float[0];
        double xDiff = x - mc.thePlayer.posX;
        double zDiff = z - mc.thePlayer.posZ;
        double yDiff = y - mc.thePlayer.posY - 1.2;
        double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        rotation[0] = (float)(Math.atan2(zDiff, xDiff) * 180.0 / Math.PI) - 90.0f;
        rotation[1] = (float)(- Math.atan2(yDiff, dist) * 180.0 / Math.PI);
        return rotation;
    }
}
