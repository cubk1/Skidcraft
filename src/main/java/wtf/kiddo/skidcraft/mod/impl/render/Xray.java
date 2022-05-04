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
    public List<Integer> KEY_IDS = new ArrayList<>();
    public Xray() {
        super("Xray", Category.Render);
    }

    @Override
    protected void onEnable() {
        KEY_IDS.add(Block.oreGold.blockID);
        KEY_IDS.add(Block.oreDiamond.blockID);
        KEY_IDS.add(Block.oreIron.blockID);
        KEY_IDS.add(Block.oreCoal.blockID);
        KEY_IDS.add(Block.oreEmerald.blockID);
        getMc().renderGlobal.loadRenderers();
    }

    @Override
    protected void onDisable() {
        KEY_IDS.clear();
        getMc().renderGlobal.loadRenderers();
    }
}
