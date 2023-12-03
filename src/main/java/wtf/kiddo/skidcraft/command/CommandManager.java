/*
 * Decompiled with CFR 0_132.
 */
package wtf.kiddo.skidcraft.command;

import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.command.commands.CommandBind;
import wtf.kiddo.skidcraft.command.commands.CommandToggle;
import wtf.kiddo.skidcraft.command.commands.CommandXray;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class CommandManager {
    private static List<Command> commands;

    public void initializeCommands() {
        commands = new ArrayList<>();
        Client.INSTANCE.getEventBus().subscribe(this);
        commands.add(new CommandBind());
        commands.add(new CommandToggle());
        commands.add(new CommandXray());
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Optional<Command> getCommandByName(String name) {
        return commands.stream().filter(c2 -> {
            boolean isAlias = false;
            String[] arrstring = c2.getAlias();
            int n = arrstring.length;
            int n2 = 0;
            while (n2 < n) {
                String str = arrstring[n2];
                if (str.equalsIgnoreCase(name)) {
                    isAlias = true;
                    break;
                }
                ++n2;
            }
            if (!c2.getName().equalsIgnoreCase(name) && !isAlias) {
                return false;
            }
            return true;
        }).findFirst();
    }
    public void add(Command command) {
        this.commands.add(command);
    }
}

