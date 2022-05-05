package wtf.kiddo.skidcraft.mod.impl.render;

import net.minecraft.src.Block;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zcy
 * Created: 2022/5/4
 */
public final class Xray extends Mod {
    public static List<Integer> KEY_IDS = new ArrayList<>();
    public Xray() {
        super("Xray", Category.Render);
        KEY_IDS.add(Block.oreGold.blockID);
        KEY_IDS.add(Block.oreDiamond.blockID);
        KEY_IDS.add(Block.oreIron.blockID);
        KEY_IDS.add(Block.oreCoal.blockID);
        KEY_IDS.add(Block.oreEmerald.blockID);
    }

    @Override
    protected void onEnable() {
        getMc().renderGlobal.loadRenderers();
    }

    @Override
    protected void onDisable() {
        getMc().renderGlobal.loadRenderers();
    }
}
