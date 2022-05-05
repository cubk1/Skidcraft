package wtf.kiddo.skidcraft.command.commands;

import net.minecraft.src.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import wtf.kiddo.skidcraft.command.Command;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.mod.ModManager;

public class CommandBind extends Command {
    public CommandBind() {
        super("b", new String[]{"bind"});
    }

    @Override
    public String execute(String[] args) {
        if (args.length >= 2) {
            Mod m = ModManager.getMod(args[0]);
            if (m != null) {
                int k = Keyboard.getKeyIndex((String)args[1].toUpperCase());
                m.setKey(k);
                Object[] arrobject = new Object[2];
                arrobject[0] = m.getLabel();
                arrobject[1] = k == 0 ? "none" : args[1].toUpperCase();
                return String.format("> Bound %s to %s", arrobject);
            } else {
                return "> Invalid module name, double check spelling.";
            }
        } else {
            return "§bCorrect usage:§7 .bind <module> <key>";
        }
    }
}

