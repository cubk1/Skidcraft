package wtf.kiddo.skidcraft.gui.clickgui;

import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.GuiScreen;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.mod.ModManager;

public class GuiBindScreen extends GuiScreen {
    private final Mod targetMod;
    public GuiBindScreen(Mod module) {
        this.targetMod = module;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Press any key for bind " + EnumChatFormatting.YELLOW + targetMod.getLabel(), this.width / 2, 150, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);

        if (keyCode == 1) {
            this.mc.displayGuiScreen(new ClickGUI());
        }

        for(Mod m : ModManager.getModules()) {
            if(keyCode == m.getKey()) {
                this.drawCenteredString(this.fontRenderer, EnumChatFormatting.RED+"This key is already bind to"  + m.getLabel(), this.width / 2 - 20, 150, 0xFFFFFF);
            } else if(keyCode != 1) {
                this.targetMod.setKey(keyCode);
                this.mc.displayGuiScreen(new ClickGUI());
            }
        }
    }
}