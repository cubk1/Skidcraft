package wtf.kiddo.skidcraft.mod.impl.client;

import com.sun.org.apache.xpath.internal.operations.Bool;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;
import wtf.kiddo.skidcraft.value.impl.NumberValue;


public final class KillAuraSettings extends Mod {
    private static final BooleanValue silentRotationValue = new BooleanValue("SilentRotation",true);
    public KillAuraSettings() {
        super("KillAuraSettings", Category.Client);
    }
    
    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    public static boolean getSilentRotation(){
        return silentRotationValue.getValue();
    }

}
