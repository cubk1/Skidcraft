package wtf.kiddo.skidcraft.mod.impl.render;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;


public final class FullBright extends Mod {
    //     Credit   https://github.com/cubk/VapuLite-1
    private float old;
    public FullBright() {
        super("FullBright", Category.Render);
        this.setKey(Keyboard.KEY_RSHIFT);
    }

    @Override
    protected void onEnable() {
        this.old = mc.gameSettings.gammaSetting;
        Minecraft.getMinecraft().gameSettings.gammaSetting = 300;
    }

    @Override
    protected void onDisable() {
Minecraft.getMinecraft().gameSettings.gammaSetting = this.old;
    }
}
