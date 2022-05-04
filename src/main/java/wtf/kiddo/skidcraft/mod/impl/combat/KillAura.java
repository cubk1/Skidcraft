package wtf.kiddo.skidcraft.mod.impl.combat;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.src.*;
import wtf.kiddo.skidcraft.event.LBUpdateEvent;
import wtf.kiddo.skidcraft.event.PacketEvent;
import wtf.kiddo.skidcraft.mod.Category;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.utils.RotationUtils;
import wtf.kiddo.skidcraft.value.impl.BooleanValue;
import wtf.kiddo.skidcraft.value.impl.NumberValue;


public final class KillAura extends Mod {
    private static final BooleanValue silentRotationValue = new BooleanValue("SilentRotation",true);
    private static final BooleanValue autoblockValue = new BooleanValue("AutoBlock",true);
    private static final NumberValue<Float> rangeValue = new NumberValue<>("Range", 4.0f, 1.0f, 6.0f, 0.1f);
    float[] rotation = new float[2];

    public KillAura() {
        super("KillAura", Category.Combat);
    }

    @EventListener
    public void onUpdate(final LBUpdateEvent event) {

        for (Object en : mc.theWorld.loadedEntityList) {
            if (mc.thePlayer.getDistanceToEntity((Entity) en) < rangeValue.getValue() && en != mc.thePlayer && en instanceof EntityLiving) {
                rotation = RotationUtils.getRotations4Attack(((Entity) en));
                if (!silentRotationValue.getValue()) {
                    mc.thePlayer.rotationYaw = rotation[0];
                    mc.thePlayer.rotationPitch = rotation[1];
                }
                if(autoblockValue.getValue() && !((EntityLiving) en).isDead && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword ){
                    // https://github.com/BapeDeveloperTeam/Bape-Opensource/blob/main/src/main/java/mc/bape/module/blatant/Killaura.java
                    mc.thePlayer.getCurrentEquippedItem().useItemRightClick(mc.theWorld, mc.thePlayer);
                }
                mc.thePlayer.swingItem();
//                mc.playerController.attackEntity(mc.thePlayer, (Entity) en);
//                mc.gameSettings.keyBindUseItem.pressed = false;
                if (mc.thePlayer.ticksExisted % 2 == 0)
                    mc.thePlayer.sendQueue.addToSendQueue(new Packet7UseEntity(mc.thePlayer.entityId, ((EntityLiving) en).entityId, 1));
//                mc.gameSettings.keyBindUseItem.pressed = true;
                return;
            }
        }
        ;
        rotation[0] = 69f;
        rotation[1] = -1f;
    }

    @EventListener
    public void onPacket(final PacketEvent event) {
        Packet packet = event.getPacket();
        if (packet instanceof Packet10Flying && rotation[0] != 69f && rotation[1] != -1f) {
            ((Packet10Flying) packet).yaw = rotation[0];
            ((Packet10Flying) packet).pitch = rotation[1];

        }
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
