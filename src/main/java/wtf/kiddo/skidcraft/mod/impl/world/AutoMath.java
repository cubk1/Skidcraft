package wtf.kiddo.skidcraft.mod.impl.world;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.Packet10Flying;
import wtf.kiddo.skidcraft.event.ChatEvent;
import wtf.kiddo.skidcraft.event.PacketEvent;
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
    String math ="";
    boolean waiting = true;
    @EventListener
    public void onChat(ChatEvent event) {
        if(event.getMessage().contains("等于几?(用数字输入)")){
            math = event.getMessage();
            System.out.println("Debug:  " + math);
            int parenthesesIndex = math.indexOf("之");
            if(parenthesesIndex != -1){
                math = math.replaceAll("之.", ")");
                math = "("+math;
                System.out.println("Debug:  " + math);
            }

            math = math.replace("等于几?(用数字输入)","")
                    .replace("一","1")
                    .replace("二","2")
                    .replace("三","3")
                    .replace("四","4")
                    .replace("五","5")
                    .replace("六","6")
                    .replace("七","7")
                    .replace("八","8")
                    .replace("九","9")
                    .replace("十","10")
                    .replace("乘","*")
                    .replace("加","+")
                    .replace("减","-")
                    .replace("以","")
//                    .replace("<"+mc.thePlayer.getEntityName()+"> ","")

                                                        ;
            System.out.println("Debug:  " + math);
            waiting = true;
        }

        if(event.getMessage().contains("/register password ConfirmPassword"))
            mc.thePlayer.sendChatMessage("/reg 1111 1111");

        if(event.getMessage().contains("/login password"))
            mc.thePlayer.sendChatMessage("/login 1111");
    }
    @EventListener
    public void onUpdate(final PacketEvent event) {
        if(mc.thePlayer!=null && mc.thePlayer.ticksExisted > 3 && waiting){
            waiting = false;
            mc.thePlayer.sendChatMessage(MathUtils.solve(math));
            math = "";
        }
    }
    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
