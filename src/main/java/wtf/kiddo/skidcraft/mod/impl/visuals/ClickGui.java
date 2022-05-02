package wtf.kiddo.skidcraft.mod.impl.visuals;

import me.bush.eventbus.annotation.EventListener;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.gui.clickgui.ClickGUI;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: Ceeyourbac
 * Created: 2022/5/3
 */
public final class ClickGui extends Mod {
    public ClickGui() {
        super("ClickGui", Category.VISUALS);
        this.setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
        this.setEnabled(false);
    }

    @Override
    protected void onDisable() {

    }
}
