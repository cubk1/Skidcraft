package wtf.kiddo.skidcraft.gui.ingame.clickgui;

import wtf.kiddo.skidcraft.gui.ingame.clickgui.properties.BooleanButton;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.properties.EnumButton;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.properties.NumberSlider;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.value.Value;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;
import wtf.kiddo.skidcraft.value.impl.EnumValue;
import wtf.kiddo.skidcraft.value.impl.NumberValue;

import java.util.ArrayList;

/**
 * Author: zcy
 * Created: 2022/5/3
 */
public class FuckingGlobalModuleButton extends Item {
    private final java.util.List<Item> items = new ArrayList<Item>();

    public FuckingGlobalModuleButton(Mod module) {
        super(module.getLabel());
        if (!module.getValues().isEmpty()) {
            for (Value<?> property : module.getValues()) {
                if (property.getValue() instanceof Boolean) {
                    this.items.add(new BooleanButton((BooleanValue) property));
                }
                if (property instanceof EnumValue) {
                    this.items.add(new EnumButton((EnumValue)property));
                }
                if (property instanceof NumberValue) {
                    this.items.add(new NumberSlider((NumberValue)property));
                }
                if (!(property.getValue() instanceof NumberValue)) continue;
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (!this.items.isEmpty()) {
            float height = 1.0f;
            for (Item item : items) {
                item.setLocation(this.x + 1.0f, this.y + height);
                item.setHeight(15);
                item.setWidth(this.width - 9);
                item.drawScreen(mouseX, mouseY, partialTicks);
                height += 15.0f;
            }
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (!this.items.isEmpty()) {
            for (Item item : items) {
                item.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }

    @Override
    public int getHeight() {
        int height = 1;
        for (Item item : items) {
            height += item.getHeight() + 1;
        }
        return height + 2;
    }
}
