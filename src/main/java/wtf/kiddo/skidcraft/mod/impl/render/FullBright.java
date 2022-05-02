package wtf.kiddo.skidcraft.mod.impl.render;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.ColorUtils;

public final class FullBright extends Mod {
    public FullBright() {
        super("FullBright", Category.Render);
        this.setEnabled(true);
    }

    @EventListener
    public void onRender2DEvent(final UpdateEvent event) {
        mc.gameSettings.gammaSetting = 10;
    }

    @Override
    protected void onEnable() {

    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = 1;
    }
}
