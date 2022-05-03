package wtf.kiddo.skidcraft.gui.ingame.clickgui.properties;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.GuiInstance;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.Item;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.Panel;
import wtf.kiddo.skidcraft.mod.impl.client.Colors;
import wtf.kiddo.skidcraft.util.RenderUtil;
import wtf.kiddo.skidcraft.value.impl.NumberValue;

/**
 * Author: zcy
 * Created: 2022/5/3
 */
public final class NumberSlider extends Item {
    private NumberValue numberProperty;
    private Number min;
    private Number max;
    private int difference;

    public NumberSlider(NumberValue numberProperty) {
        super(numberProperty.getLabel());
        this.numberProperty = numberProperty;
        this.min = (Number)numberProperty.getMinimum();
        this.max = (Number)numberProperty.getMaximum();
        this.difference = max.intValue() - min.intValue();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        dragSetting(mouseX, mouseY);
//        RenderMethods.drawRect(x, y, ((Number)numberProperty.getValue()).floatValue() <= min.floatValue() ? x : x + (width + 7.4F) * partialMultiplier(), y + height - 0.5f, !isHovering(mouseX, mouseY) ? 2012955202 : -1711586750);
        RenderUtil.drawRect(x, y, ((Number)numberProperty.getValue()).floatValue() <= min.floatValue() ? x : x + (width + 7.4F) * partialMultiplier(), y + height - 0.5f, !isHovering(mouseX, mouseY) ? Colors.getClientColorCustomAlpha(77) : Colors.getClientColorCustomAlpha(55));
//        RenderMethods.drawRect(x, y, x + getValueWidth(), y + height, !isHovering(mouseX, mouseY) ? 0x775CE843 : 0x66A317BD);//2002577475 : -1721964477);
//        FontUtil.drawString(String.format("%s\u00a77 %s", this.getLabel(), this.numberProperty.getValue()), this.x + 2.3f, this.y - 1.0f, -1);
        Minecraft.getMinecraft().fontRenderer.drawString(String.format("%s\u00a77 %s", this.getLabel(), this.numberProperty.getValue()), (int) this.x + 2, (int) this.y + 4, -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isHovering(mouseX, mouseY) && mouseButton == 0) {
            setSettingFromX(mouseX);
        }
    }

    private void setSettingFromX(int mouseX) {
        float percent = (mouseX - x) / (width + 7.4F);
        if(numberProperty.getValue() instanceof Double) {
            double result = (Double)numberProperty.getMinimum() + (difference * percent);
            numberProperty.setValue(Math.round(10.0 * result) / 10.0);
        } else if (numberProperty.getValue() instanceof Float) {
            float result = (Float)numberProperty.getMinimum() + (difference * percent);
            numberProperty.setValue(Math.round(10.0f * result) / 10.0f);
        } else if (numberProperty.getValue() instanceof Integer) {
            numberProperty.setValue(((Integer)numberProperty.getMinimum() + (int)(difference * percent)));
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    private void dragSetting(int mouseX, int mouseY) {
        if(isHovering(mouseX, mouseY) && Mouse.isButtonDown(0)) {
            setSettingFromX(mouseX);
        }
    }

    private boolean isHovering(int mouseX, int mouseY) {
        for (Panel panel : GuiInstance.getInstance().getPanels()) {
            if (!panel.drag) continue;
            return false;
        }
        return (float)mouseX >= this.getX() && (float)mouseX <= this.getX() + (float)this.getWidth() && (float)mouseY >= this.getY() && (float)mouseY <= this.getY() + (float)this.height;
    }

    private float getValueWidth() {
        return ((Number)this.numberProperty.getMaximum()).floatValue() - ((Number)this.numberProperty.getMinimum()).floatValue() + ((Number)this.numberProperty.getValue()).floatValue();
    }

    private float middle() {
        return max.floatValue() - min.floatValue();
    }

    private float part() {
        return ((Number)numberProperty.getValue()).floatValue() - min.floatValue();
    }

    private float partialMultiplier() {
        return part() / middle();
    }
}
