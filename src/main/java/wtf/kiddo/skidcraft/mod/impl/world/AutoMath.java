package wtf.kiddo.skidcraft.mod.impl.world;

import me.bush.eventbus.annotation.EventListener;
import wtf.kiddo.skidcraft.event.ChatEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.MathUtils;

/**
 * Author: cubk
 * Created: 2022/5/3
 */
public final class AutoMath extends Mod {
    public AutoMath() {
        super("AutoSolve", Category.World);
        this.setEnabled(true);
    }

    @EventListener
    public void onChat(ChatEvent event) {
        if(event.getMessage().contains("=?")){
            String math = event.getMessage();
            math = math.replace("=?","");
            mc.thePlayer.sendChatMessage(MathUtils.solve(math));
        }

        if(event.getMessage().contains("/register password ConfirmPassword"))
            mc.thePlayer.sendChatMessage("/reg Sb1145141919810! Sb1145141919810!");

        if(event.getMessage().contains("/login password"))
            mc.thePlayer.sendChatMessage("/login Sb1145141919810!");
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
