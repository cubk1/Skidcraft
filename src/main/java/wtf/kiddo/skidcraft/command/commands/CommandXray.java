package wtf.kiddo.skidcraft.command.commands;

import wtf.kiddo.skidcraft.command.Command;
import wtf.kiddo.skidcraft.mod.impl.render.Xray;
import wtf.kiddo.skidcraft.utils.MathUtils;

import java.util.Arrays;

public class CommandXray extends Command {
    public CommandXray() {
        super("xray", new String[]{"orechams"});
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 2) {
            if (MathUtils.parsable(args[1], (byte)4)) {
                int id = Integer.parseInt(args[1]);
                if (args[0].equalsIgnoreCase("add")) {
                    Xray.KEY_IDS.add(id);
                    return "Added Block ID " + id;
                } else if (args[0].equalsIgnoreCase("remove")) {
                    Xray.KEY_IDS.remove(id);
                    return "Removed Block ID " + id;
                } else {
                    return "Invalid syntax";
                }
            } else {
                return "Invalid block ID";
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
            return Arrays.toString(Xray.KEY_IDS.toArray(new Integer[0]));
        } else {
            return "Use .xray <add|remove> <BlockID> | .xray list";
        }
    }
}

