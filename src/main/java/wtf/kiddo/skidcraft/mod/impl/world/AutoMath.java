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
        if(event.getMessage().contains("=?") || event.getMessage().contains("等于几？")){
            String math = event.getMessage();
            math = math.replace("=?","");
            math = math.replace("等于几？","");
            math = math.replace("一","1");
            math = math.replace("二","2");
            math = math.replace("三","3");
            math = math.replace("四","4");
            math = math.replace("五","5");
            math = math.replace("六","6");
            math = math.replace("七","7");
            math = math.replace("八","8");
            math = math.replace("九","9");
            math = math.replace("零","0");
            math = math.replace("加","+");
            math = math.replace("减","-");
            math = math.replace("乘","*");
            math = math.replace("除以","/");
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
