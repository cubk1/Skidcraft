/*
 * Decompiled with CFR 0_132.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package wtf.kiddo.skidcraft.gui.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import org.lwjgl.input.Keyboard;

import java.util.Random;

public class GuiAltLogin
extends GuiScreen {
    private final GuiScreen previousScreen;
    private GuiTextField username;

    public GuiAltLogin(GuiScreen previousScreen) {
        this.previousScreen = previousScreen;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 1: {
                this.mc.displayGuiScreen(this.previousScreen);
                break;
            }
            case 0: {
                mc.session.username = username.getText();
                this.mc.displayGuiScreen(this.previousScreen);
                break;
            }
            case 2: {
                username.setText(randomName(4,8) + new Random().nextInt(6666));
            }
        }
    }

    public static String randomName(int min,int max) {
        String name;
        char[] nameChar;
        int nameLength=(int)(Math.random()*(max-min+1))+min;
        nameChar=new char[nameLength];
        // 首字母大写，装的更像正常玩家避免被第一时间踢出
        nameChar[0]=(char) (Math.random()*26+65);
        for(int i=1;i<nameLength;i++) {
            nameChar[i]=(char)(Math.random()*26+97);
        }
        name=new String(nameChar);
        return name;
    }

    @Override
    public void drawScreen(int x, int y, float z) {
        this.drawDefaultBackground();
        this.username.drawTextBox();
        this.drawCenteredString(mc.fontRenderer,"Alt Login", this.width / 2, 20, -1);
        if (this.username.getText().isEmpty()) {
            mc.fontRenderer.drawStringWithShadow("New username", this.width / 2 - 96, 66, -7829368);
        }
        super.drawScreen(x, y, z);
    }

    @Override
    public void initGui() {
        int var3 = this.height / 4 + 24;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, var3 + 60 + 12, "Login"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, var3 + 60 + 12 + 24, "Random Username"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, var3 + 60 + 12 + 24 + 24, "Back"));
        this.username = new GuiTextField( this.mc.fontRenderer, this.width / 2 - 100, 60, 200, 20);
        this.username.setFocused(true);
        this.username.setMaxStringLength(200);
        Keyboard.enableRepeatEvents((boolean)true);
    }

    @Override
    protected void keyTyped(char character, int key) {
        super.keyTyped(character, key);
        if (character == '\r') {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
        this.username.textboxKeyTyped(character, key);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        this.username.mouseClicked(x, y, button);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    @Override
    public void updateScreen() {
        this.username.updateCursorCounter();
    }
}

