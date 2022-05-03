package wtf.kiddo.skidcraft;

import me.bush.eventbus.bus.EventBus;
import me.bush.eventbus.event.Event;
import org.lwjgl.opengl.Display;
import wtf.kiddo.skidcraft.mod.ModManager;
import wtf.kiddo.skidcraft.utils.MathUtils;
import wtf.kiddo.skidcraft.value.ValueManager;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public enum Client {
    INSTANCE;

    public static String CLIENT_NAME = "Skid Craft";

    private EventBus eventBus;
    private ValueManager valueManager;
    private ModManager modManager;

    public void startClient() {
        Display.setTitle(CLIENT_NAME);
        eventBus = new EventBus();
        valueManager = new ValueManager();
        modManager = new ModManager();
        modManager.initializeMods();
    }

    public final EventBus getEventBus() {
        return eventBus;
    }

    public ValueManager getValueManager() {
        return valueManager;
    }

    public ModManager getModManager() {
        return modManager;
    }
}
