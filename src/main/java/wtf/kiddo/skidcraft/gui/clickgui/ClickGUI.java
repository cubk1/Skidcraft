package wtf.kiddo.skidcraft.gui.clickgui;

import net.minecraft.src.Gui;
import net.minecraft.src.GuiScreen;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.ModManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends GuiScreen {

    private List<Panel> panels = new ArrayList<>();

    public ClickGUI() {
        int x = 4;

        for(final Category moduleCategory : Category.values()) {
            final Panel panel = new Panel(moduleCategory.name(), x, 50, 100);
            ModManager.getModules().stream().filter(module -> module.getCategory() == moduleCategory).forEach(module -> panel.addButton(new Button(panel, module)));
            panels.add(panel);
            x = x + 105;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for(final Panel panel : panels) {
            // Draw panel
            Gui.drawRect(panel.getX(), panel.getY(), panel.getX() + panel.getWidth(), panel.getY() + 20, new Color(72, 66, 244).hashCode());
            mc.fontRenderer.drawString(panel.getPanelName(), panel.getX() + 5, panel.getY() + 5, 0xffffff);

            for(int i = 0; i < panel.getButtons().size(); i++) {
                final Button button = panel.getButtons().get(i);

                Gui.drawRect(panel.getX(), panel.getY() + 20 + (20 * i), panel.getX() + panel.getWidth(), panel.getY() + (20 * i) + 40, Integer.MIN_VALUE);
                mc.fontRenderer.drawString((button.getModule().isEnabled() ? "§a" : "§c") + button.getModule().getLabel(), panel.getX() + 2, panel.getY() + 20 + (20 * i) + 7, 0xffffff);
            }

            // Drag panel
            if(panel.isDrag()) {
                panel.setX(mouseX + panel.getDragX());
                panel.setY(mouseY + panel.getDragY());
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(mouseButton == 0) {
            for(int index = panels.size() - 1; index >= 0; index--) {
                final Panel panel = panels.get(index);


                if(panel.isHoverHead(mouseX, mouseY)) {
                    panel.setDrag(true);
                    panel.setDragX(panel.getX() - mouseX);
                    panel.setDragY(panel.getY() - mouseY);
                    panels.remove(panel);
                    panels.add(panel);
                    break;
                }


                for(int buttonIndex = 0; buttonIndex < panel.getButtons().size(); buttonIndex++) {
                    final Button button = panel.getButtons().get(buttonIndex);

                    if(button.isHover(panel.getX(),panel.getY() + 20 + (20 * buttonIndex), panel.getWidth(), 20, mouseX, mouseY)) {
                        button.getModule().setEnabled(!button.getModule().isEnabled());
                    }
                }
            }
        } else if(mouseButton == 1){
            for(int index = panels.size() - 1; index >= 0; index--) {
                final Panel panel = panels.get(index);


                if(panel.isHoverHead(mouseX, mouseY)) {
                    panel.setDrag(true);
                    panel.setDragX(panel.getX() - mouseX);
                    panel.setDragY(panel.getY() - mouseY);
                    panels.remove(panel);
                    panels.add(panel);
                    break;
                }


                for(int buttonIndex = 0; buttonIndex < panel.getButtons().size(); buttonIndex++) {
                    final Button button = panel.getButtons().get(buttonIndex);

                    if(button.isHover(panel.getX(),panel.getY() + 20 + (20 * buttonIndex), panel.getWidth(), 20, mouseX, mouseY)) {
                        mc.displayGuiScreen(new GuiBindScreen(button.getModule()));
                    }
                }
            }
        }


        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for(final Panel panel : panels)
            panel.setDrag(false);
        super.mouseReleased(mouseX, mouseY, state);
    }
}