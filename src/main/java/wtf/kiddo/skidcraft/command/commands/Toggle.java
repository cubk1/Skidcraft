package wtf.kiddo.skidcraft.command.commands;

import net.minecraft.src.EnumChatFormatting;
import wtf.kiddo.skidcraft.command.Command;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.mod.ModManager;

public class Toggle extends Command {
    public Toggle() {
        super("t", new String[]{"toggle", "togl", "turnon", "enable"});
    }

    @Override
    public String execute(String[] args) {
        if(args.length <1){
            return "Use .toggle <Module>";
        }
        Mod m = ModManager.getMod(args[0]);
        m.toggle();
        if (m.isEnabled()) {
            return "> " + m.getLabel() + EnumChatFormatting.GRAY + " was" + EnumChatFormatting.GREEN + " enabled";
        } else {
            return "> " + m.getLabel() +  EnumChatFormatting.GRAY + " was" + EnumChatFormatting.RED + " disabled";
        }
    }
}

