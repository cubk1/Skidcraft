package wtf.kiddo.skidcraft;

import me.bush.eventbus.bus.EventBus;
import org.lwjgl.opengl.Display;
import wtf.kiddo.skidcraft.command.CommandManager;
import wtf.kiddo.skidcraft.mod.ModManager;
import wtf.kiddo.skidcraft.value.ValueManager;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public enum Client {
    INSTANCE;

    public static String CLIENT_NAME = "FDPClient for 1.5.2";

    private EventBus eventBus;
    private ValueManager valueManager;
    private ModManager modManager;
    private CommandManager cmdManager;

    public void startClient() {
        Display.setTitle(CLIENT_NAME);
        eventBus = new EventBus();
        valueManager = new ValueManager();
        modManager = new ModManager();
        cmdManager = new CommandManager();
        modManager.initializeMods();
        cmdManager.initializeCommands();
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

    public CommandManager getCmdManager() {
        return cmdManager;
    }
}
