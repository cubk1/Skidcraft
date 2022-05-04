package wtf.kiddo.skidcraft.gui.ingame.clickgui;

import net.minecraft.src.GuiScreen;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.properties.BooleanButton;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.util.RenderUtil;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Author: zcy
 * Created: 2022/5/3
 */
public final class GuiInstance extends GuiScreen {
    private static GuiInstance instance;
    private final ArrayList<Panel> panels = new ArrayList<>();

    public GuiInstance() {
        this.load();
    }

    public static GuiInstance getInstance() {
        return instance == null ? (instance = new GuiInstance()) : instance;
    }

    private void load() {
        int x = -84;
        for (final Category moduleType : Category.values()) {
            if(moduleType != Category.Client) {
                this.panels.add(new Panel(moduleType.name(), x += 90, 4, true){
                    @Override
                    public void setupItems() {
                        Client.INSTANCE.getModManager().getModsInCategory(moduleType).forEach(mod -> {
                            this.addItem(new ModuleButton(mod));

                        });
                    }
                });
             } else {
                for (final Mod mod : Client.INSTANCE.getModManager().getModsInCategory(Category.Client)) {
                    this.panels.add(new Panel(mod.getLabel(), x += 90, 4, true) {
                        @Override
                        public void setupItems() {
                            this.addItem(new FuckingGlobalModuleButton(mod));
                        }
                    });
                }
            }
        }
        this.panels.forEach(panel -> panel.getItems().sort(Comparator.comparing(Item::getLabel)));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtil.drawGradient(0.0F, 0.0F, mc.displayWidth, mc.displayHeight, 536870912, -1879048192);
        this.panels.forEach(panel -> panel.drawScreen(mouseX, mouseY, partialTicks));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
        this.panels.forEach(panel -> panel.mouseClicked(mouseX, mouseY, clickedButton));
    }

    @Override
    protected void mouseMovedOrUp(int par1, int par2, int par3) {
        if(par3 != -1) {
            this.panels.forEach(panel -> panel.mouseReleased(par1, par2, par3));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public ArrayList<Panel> getPanels() {
        return panels;
    }
}
