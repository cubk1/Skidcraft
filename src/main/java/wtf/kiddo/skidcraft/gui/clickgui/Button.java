package wtf.kiddo.skidcraft.gui.clickgui;

import wtf.kiddo.skidcraft.mod.Mod;

public class Button {

    private Panel panel;
    private Mod module;

    public Button(Panel panel, Mod module) {
        this.panel = panel;
        this.module = module;
    }

    public void click() {
        module.setEnabled(!module.isEnabled());
    }

    public Mod getModule() {
        return module;
    }

    public boolean isHover(final int x, final int y, final int widht, final int height, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x + widht && mouseY >= y && mouseY <= y + height;
    }
}