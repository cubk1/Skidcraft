package wtf.kiddo.skidcraft.mod;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.KeyInputEvent;
import wtf.kiddo.skidcraft.mod.impl.client.*;
import wtf.kiddo.skidcraft.mod.impl.combat.*;
import wtf.kiddo.skidcraft.mod.impl.exploits.*;
import wtf.kiddo.skidcraft.mod.impl.move.*;
import wtf.kiddo.skidcraft.mod.impl.render.*;
import wtf.kiddo.skidcraft.mod.impl.world.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class ModManager {
    private static final Map<String, Mod> map = new HashMap<>();
    private static ArrayList<Mod> modules = new ArrayList<Mod>();
    public ModManager() {
        Client.INSTANCE.getEventBus().subscribe(this);
    }

    public static ArrayList<Mod> getModules() {
        return modules;
    }

    public void initializeMods() {
        registerMod(HUD.class);
        registerMod(AirJump.class);
        registerMod(Sprint.class);
        registerMod(ServerCrasher.class);
        registerMod(NoFall.class);
        registerMod(KillAura.class);
        registerMod(SpeedNCP.class);
        registerMod(Strafe.class);
        registerMod(ESP.class);
        registerMod(FreeCam.class);
        registerMod(InvMove.class);
        registerMod(NoSlowNCP.class);
        registerMod(Velocity.class);
        registerMod(ClickGUI.class);
        registerMod(FullBright.class);
        registerMod(Fly.class);
        registerMod(AutoMath.class);
        registerMod(LiquidWalk.class);
        registerMod(SafeWalk.class);
        registerMod(Colors.class);
        registerMod(ESP.class);
        registerMod(Disabler.class);
        registerMod(Xray.class);
        registerMod(Timer.class);
    }

    private void registerMod(Class<? extends Mod> moduleClass) {
        try {

            final Mod createdMod = moduleClass.newInstance();

            Client.INSTANCE.getValueManager().registerObject(createdMod.getLabel(),createdMod);
            map.put(createdMod.getLabel().toLowerCase(), createdMod);
            modules.add(createdMod);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Map<String, Mod> getModMap() {
        return map;
    }

    public boolean isMod(final String module) {
        for (Mod mod : getModMap().values()) {
            if (mod.getLabel().equalsIgnoreCase(module)) {
                return true;
            }
        }
        return false;
    }

    public Mod getModClass(final Class<?> clazz) {
        for (Mod mod : getModMap().values()) {
            if (mod.getClass().equals(clazz)) {
                return mod;
            }
        }
        return null;
    }

    public ArrayList<Mod> getModsInCategory(Category category) {
        final ArrayList<Mod> mods = new ArrayList<>();
        for (Mod module : map.values()) {
            if (module.getCategory() == category) {
                mods.add(module);
            }
        }
        return mods;
    }

    public static Mod getMod(String name) {
        return getModMap().get(name.toLowerCase());
    }

    @EventListener
    public void onKeyInputEvent(final KeyInputEvent event) {
        getModMap().values().forEach(module -> {
            // 排除key为0的事件
            // 因为在中文输入法下向游戏输入任何按键都是0
            // 这会导致toggle所有没绑定按键的功能(没绑定按键默认为0,其实改成-1更好)
            if(event.getKey() != 0 && module.getKey() == event.getKey()) {
                module.toggle();
            }
        });
    }
}
