package wtf.kiddo.skidcraft.command;

import net.minecraft.client.Minecraft;

public abstract class Command {
    private final String name;
    private final String[] alias;
    public Minecraft mc = Minecraft.getMinecraft();

    public Command(String name, String[] alias) {
        this.name = name.toLowerCase();
        this.alias = alias;
    }

    public abstract String execute(String[] var1);

    public String getName() {
        return this.name;
    }

    public String[] getAlias() {
        return this.alias;
    }

    public void syntaxError(String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage("Error command syntax");
    }

    public void syntaxError(byte errorType) {
        switch (errorType) {
            case 0: {
                this.syntaxError("bad argument");
                break;
            }
            case 1: {
                this.syntaxError("argument gay");
            }
        }
    }
}

