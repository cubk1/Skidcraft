package wtf.kiddo.skidcraft.mod.impl.world;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
	private Timer timer = new Timer();
    public AutoMath() {
        super("AutoSolve", Category.World);
        this.setEnabled(true);
    }
    @EventListener
    public void onChat(ChatEvent event) {
        if(event.getMessage().contains("等于几?(用数字输入)")){
            String math = event.getMessage();
            timer.schedule(new TimerTask() {
				@Override
				public void run() {
					mc.thePlayer.sendChatMessage(MathUtils.solveFunpixel(math));
				}
            }, new Random().nextInt(80) + 20);
        }

        if(event.getMessage().contains("/register password ConfirmPassword"))
            mc.thePlayer.sendChatMessage("/reg 1111 1111");

        if(event.getMessage().contains("/login password"))
            mc.thePlayer.sendChatMessage("/login 1111");
    }
    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
