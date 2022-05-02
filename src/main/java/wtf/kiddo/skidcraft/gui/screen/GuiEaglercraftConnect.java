package wtf.kiddo.skidcraft.gui.screen;

import net.minecraft.src.*;
import wtf.kiddo.skidcraft.util.WebSocket2Socket;

import java.util.Random;

/**
 * Author: zcy
 * Created: 2022/5/2
 */
public final class GuiEaglercraftConnect extends GuiScreen {
    private GuiTextField ipField;
    @Override
    public void initGui() {
        int fieldWidth = 200;
        int fieldHeight = 20;
        this.ipField = new GuiTextField(this.fontRenderer, this.width / 2 - 100, this.height / 5, fieldWidth, fieldHeight);
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 95, fieldWidth, fieldHeight, "Connect"));
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        if(par1GuiButton.id == 1) {
            //在你的jvm产生十万个没用的thread,并且不会得到任何处理
            final int port = new Random().nextInt(65535);
            new WebSocket2Socket(this.ipField.getText(),port).start();
            ServerData data = new ServerData("1337","127.0.0.1:" + port);
            this.mc.loadWorld((WorldClient)null);
            this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, data));
        }
        super.actionPerformed(par1GuiButton);
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        ipField.textboxKeyTyped(par1, par2);
        super.keyTyped(par1, par2);
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
        this.ipField.mouseClicked(par1, par2, par3);
        super.mouseClicked(par1, par2, par3);
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        drawDefaultBackground();
        drawCenteredString(this.fontRenderer, "Eaglercraft Address (Websocket)", this.width / 2, this.ipField.yPos - 15, 16777215);

        this.ipField.drawTextBox();
        super.drawScreen(par1, par2, par3);
    }
}
