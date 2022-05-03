package wtf.kiddo.skidcraft.gui.ingame.clickgui.properties;

import net.minecraft.client.Minecraft;
import wtf.kiddo.skidcraft.gui.ingame.clickgui.Button;
import wtf.kiddo.skidcraft.mod.impl.global.Colors;
import wtf.kiddo.skidcraft.util.RenderUtil;
import wtf.kiddo.skidcraft.value.impl.EnumValue;

/**
 * Author: zcy
 * Created: 2022/5/3
 */
public final class EnumButton extends Button {
    private EnumValue property;

    public EnumButton(EnumValue property) {
        super(property.getLabel());
        this.property = property;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        RenderMethods.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height, this.getState() ? (!this.isHovering(mouseX, mouseY) ? 2012955202 : -1711586750) : (!this.isHovering(mouseX, mouseY) ? 0x11333333 : -2009910477));
        RenderUtil.drawRect(this.x, this.y, this.x + (float)this.width + 7.4f, this.y + (float)this.height, this.getState() ? (!this.isHovering(mouseX, mouseY) ? Colors.getClientColorCustomAlpha(77) : Colors.getClientColorCustomAlpha(55)) : (!this.isHovering(mouseX, mouseY) ? 0x11333333 : -2009910477));
//        FontUtil.drawString(String.format("%s\u00a77 %s", this.getLabel(), this.property.getFixedValue()), this.x + 2.3f, this.y - 1.0f, this.getState() ? -1 : -5592406);
        Minecraft.getMinecraft().fontRenderer.drawString(String.format("%s\u00a77 %s", this.getLabel(), this.property.getValueAsString()), (int) this.x + 2, (int)this.y + 4, this.getState() ? -1 : -5592406);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.isHovering(mouseX, mouseY)) {
//            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("random.click"), 1.0f));
            if (mouseButton == 0) {
                this.property.increment();
            } else if (mouseButton == 1) {
                this.property.decrement();
            }
        }
    }

    @Override
    public int getHeight() {
        return 14;
    }

    @Override
    public void toggle() {
    }

    @Override
    public boolean getState() {
        return true;
    }
}

