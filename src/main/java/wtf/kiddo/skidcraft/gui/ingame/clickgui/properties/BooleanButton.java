package wtf.kiddo.skidcraft.gui.ingame.clickgui.properties;

import net.minecraft.client.Minecraft;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.Button;
import wtf.kiddo.skidcraft.mod.impl.global.Colors;
import wtf.kiddo.skidcraft.util.RenderUtil;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;

/**
 * Author: zcy
 * Created: 2022/5/3
 */
public final class BooleanButton extends Button {
    private BooleanValue property;

    public BooleanButton(BooleanValue property) {
        super(property.getLabel());
        this.property = property;
        this.width = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        RenderMethods.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height, this.getState() ? (!this.isHovering(mouseX, mouseY) ? 2012955202 : -1711586750) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Colors.getClientColorCustomAlpha(77) : Colors.getClientColorCustomAlpha(77)) : (!this.isHovering(mouseX, mouseY) ? 0x11555555 : -2007673515));
        Minecraft.getMinecraft().fontRenderer.drawString(this.getLabel(), (int)this.x + 2, (int)this.y + 4, this.getState() ? -1 : -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
//            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("random.click"), 1.0f));
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void toggle() {
        this.property.setValue((Boolean)this.property.getValue() == false);
    }

    @Override
    public boolean getState() {
        return (Boolean)this.property.getValue();
    }
}

