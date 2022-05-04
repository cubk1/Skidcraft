package wtf.kiddo.skidcraft.command.commands;

import net.minecraft.src.EnumChatFormatting;
import wtf.kiddo.skidcraft.command.Command;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.mod.ModManager;

public class Bind extends Command {
    public Bind() {
        super("t", new String[]{"Bind", "togl", "turnon", "enable"});
    }

    @Override
    public String execute(String[] args) {
        String modName = "";
        if (args.length > 1) {
            modName = args[1];
        } else if (args.length < 1) {

        }
        boolean found = false;
        Mod m = ModManager.getMod(modName);
        if (m != null) {
            if (!m.isEnabled()) {
                m.setEnabled(true);
            } else {
                m.setEnabled(false);
            }
            found = true;
            if (m.isEnabled()) {
                return "> " + m.getLabel() + EnumChatFormatting.GRAY + " was" + EnumChatFormatting.GREEN + " enabled";
            } else {
                return "> " + m.getLabel() +  EnumChatFormatting.GRAY + " was" + EnumChatFormatting.RED + " disabled";
            }
        }
        if (!found) {
            return  "> Module name " + EnumChatFormatting.RED + args[0] + EnumChatFormatting.GRAY + " is invalid";
        }
        return null;
    }
}

