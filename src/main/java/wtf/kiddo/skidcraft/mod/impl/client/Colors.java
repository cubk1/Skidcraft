package wtf.kiddo.skidcraft.mod.impl.client;

import net.minecraft.src.MathHelper;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.value.impl.NumberValue;

import java.awt.*;

/**
 * Author: zcy
 * Created: 2022/5/3
 */
public final class Colors extends Mod {
    private static final NumberValue<Float> hue = new NumberValue<>("Hue", 0.0f, 0.0f, 360.0f, 1.0f);
    private static final NumberValue<Float> saturation = new NumberValue<>("Saturation", 90.0f, 0.0f, 100.0f, 1.0f);
    private static final NumberValue<Float> lightness = new NumberValue<>("Lightness", 45.0f, 0.0f, 100.0f, 1.0f);
    public Colors() {
        super("Colors", Category.Client);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    public static int getClientColorCustomAlpha(int alpha){
        Color color = setAlpha(new Color(Color.HSBtoRGB(hue.getValue(), saturation.getValue() / 100f, lightness.getValue() / 100f)), alpha);
        return color.getRGB();
    }

    public static Color setAlpha(Color color, int alpha) {
        alpha = MathHelper.clamp_int(alpha, 0, 255);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static Color getRainbow(int speed, int offset, float s, float brightness) {
        float hue = (System.currentTimeMillis() + offset) % speed;
        hue /= speed;
        return Color.getHSBColor(hue, s, brightness);
    }

    public static int getClientColor(){
        return Color.getHSBColor(hue.getValue(), saturation.getValue() / 100f, lightness.getValue() / 100f).getRGB();
    }
}
