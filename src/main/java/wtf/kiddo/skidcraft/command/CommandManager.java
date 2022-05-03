/*
 * Decompiled with CFR 0_132.
 */
package wtf.kiddo.skidcraft.command;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.client.Minecraft;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.command.commands.Toggle;
import wtf.kiddo.skidcraft.event.ChatEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public class CommandManager {
    private static List<Command> commands;

    public void initializeCommands() {
        commands = new ArrayList<>();
        Client.INSTANCE.getEventBus().subscribe(this);

        commands.add(new Toggle());
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

    @EventListener
    private void onChat(ChatEvent e) {
        if (e.getMessage().length() > 1 && e.getMessage().startsWith(".")) {
            e.setCancelled(true);
            String[] args = e.getMessage().trim().substring(1).split(" ");
            Optional<Command> possibleCmd = this.getCommandByName(args[0]);
            if (possibleCmd.isPresent()) {
                String result = possibleCmd.get().execute(Arrays.copyOfRange(args, 1, args.length));
                if (result != null && !result.isEmpty()) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(result);
                }
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage("Bad command syntax!");
            }
        }
    }

}

