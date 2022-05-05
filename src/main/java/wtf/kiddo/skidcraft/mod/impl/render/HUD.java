package wtf.kiddo.skidcraft.mod.impl.render;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.ScaledResolution;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.gui.clickgui.GuiBindScreen;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.GuiInstance;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.mod.ModManager;
import wtf.kiddo.skidcraft.util.RenderUtil;
import wtf.kiddo.skidcraft.utils.ColorUtils;

import java.awt.*;
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
        mc.fontRenderer.drawStringWithShadow(Client.CLIENT_NAME,4,4,-1);
        int y = 1;
        int rainbowTick = 0;
        String name;
        ArrayList<Mod> sorted = new ArrayList<Mod>();
        for (Mod m : ModManager.getModules()) {
            if (!m.isEnabled()) continue;
            sorted.add(m);
        }
        sorted.sort((o1, o2) -> mc.fontRenderer.getStringWidth(o2.getLabel()) - mc.fontRenderer.getStringWidth(o1.getLabel()));
        for (Mod m : sorted) {
            name = m.getLabel();
            float x = RenderUtil.width() - mc.fontRenderer.getStringWidth(name);
            Color rainbow = new Color(Color.HSBtoRGB((float)((double)mc.thePlayer.ticksExisted / 50.0 + Math.sin((double)rainbowTick / 50.0 * 1.6)) % 1.0f, 0.5f, 1.0f));
            mc.fontRenderer.drawStringWithShadow(name, (int) (x - 2.0f), y, rainbow.getRGB());
            if (++rainbowTick > 50) {
                rainbowTick = 0;
            }
            y += 9;
        }
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
