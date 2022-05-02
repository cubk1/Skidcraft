package wtf.kiddo.skidcraft.mod.impl.render;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.ScaledResolution;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.mod.ModManager;
import wtf.kiddo.skidcraft.utils.ColorUtils;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class HUD extends Mod {
    public HUD() {
        super("HUD", Category.Render);
        this.setEnabled(true);
    }

    @EventListener
    public void onRender2DEvent(final Render2DEvent event) {
        float posy = 10;
        for (Mod mod : Client.INSTANCE.getModManager().getModMap().values()) {
            if(mod.isEnabled()) {
                getMc().fontRenderer.drawStringWithShadow(mod.getLabel(),4, (int) posy,ColorUtils.rainbow(1));
                posy += (getMc().fontRenderer.FONT_HEIGHT + 2);
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
