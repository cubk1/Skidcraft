package wtf.kiddo.skidcraft.mod.impl.visuals;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.GuiInstance;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/3
 */
public final class ClickGUI extends Mod {
    public ClickGUI() {
        super("ClickGUI", Category.VISUALS);
        setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    protected void onEnable() {
        Minecraft.getMinecraft().displayGuiScreen(GuiInstance.getInstance());
        setEnabled(false);
    }

    @Override
    protected void onDisable() {

    }
}
