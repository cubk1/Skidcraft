package net.minecraft.src;

import net.minecraft.client.Minecraft;
import wtf.kiddo.skidcraft.Client;
import wtf.kiddo.skidcraft.event.MotionEvent;
import wtf.kiddo.skidcraft.event.UpdateEvent;
import wtf.kiddo.skidcraft.mod.Mod;
import wtf.kiddo.skidcraft.mod.ModManager;

public class EntityClientPlayerMP extends EntityPlayerSP {
    public NetClientHandler sendQueue;
    private double oldPosX;

    /**
     * Old Minimum Y of the bounding box
     */
    private double oldMinY;
    private double oldPosY;
    private double oldPosZ;
    private float oldRotationYaw;
    private float oldRotationPitch;

    /**
     * Check if was on ground last update
     */
    private boolean wasOnGround = false;

    /**
     * should the player stop sneaking?
     */
    private boolean shouldStopSneaking = false;
    private boolean wasSneaking = false;
    private int field_71168_co = 0;

    /**
     * has the client player's health been set?
     */
    private boolean hasSetHealth = false;

    public EntityClientPlayerMP(Minecraft par1Minecraft, World par2World, Session par3Session, NetClientHandler par4NetClientHandler) {
        super(par1Minecraft, par2World, par3Session, 0);
        this.sendQueue = par4NetClientHandler;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
        return false;
    }

    /**
     * Heal living entity (param: amount of half-hearts)
     */
    public void heal(int par1) {
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        if (this.worldObj.blockExists(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ))) {
            super.onUpdate();
            this.sendMotionUpdates();
        }
    }

    /**
     * Send updated motion and position information to the server
     */
    public void sendMotionUpdates() {
        final UpdateEvent event = new UpdateEvent(this.rotationYaw, this.rotationPitch, this.boundingBox.minY, this.onGround, true);
        Client.INSTANCE.getEventBus().post(event);
        if (event.isCancelled()) {
            Client.INSTANCE.getEventBus().post(new UpdateEvent(this.rotationYaw, this.rotationPitch, this.boundingBox.minY, this.onGround));
            return;
        }
        boolean var1 = this.isSprinting();

        if (var1 != this.wasSneaking) {
            if (var1) {
                this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 4));
            } else {
                this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 5));
            }

            this.wasSneaking = var1;
        }

        boolean var2 = this.isSneaking();

        if (var2 != this.shouldStopSneaking) {
            if (var2) {
                this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 1));
            } else {
                this.sendQueue.addToSendQueue(new Packet19EntityAction(this, 2));
            }

            this.shouldStopSneaking = var2;
        }

        double var3 = this.posX - this.oldPosX;
        double var5 = event.getY() - this.oldMinY;
        double var7 = this.posZ - this.oldPosZ;
        double var9 = (double) (event.getYaw() - this.oldRotationYaw);
        double var11 = (double) (event.getPitch() - this.oldRotationPitch);
        boolean var13 = var3 * var3 + var5 * var5 + var7 * var7 > 9.0E-4D || this.field_71168_co >= 20;
        boolean var14 = var9 != 0.0D || var11 != 0.0D;

        if (this.ridingEntity != null) {
            this.sendQueue.addToSendQueue(new Packet13PlayerLookMove(this.motionX, -999.0D, -999.0D, this.motionZ, event.getYaw(), event.getPitch(), event.isOnGround()));
            var13 = false;
        } else if (var13 && var14) {
            this.sendQueue.addToSendQueue(new Packet13PlayerLookMove(this.posX, event.getY(), this.posY, this.posZ, event.getYaw(), event.getPitch(), event.isOnGround()));
        } else if (var13) {
            this.sendQueue.addToSendQueue(new Packet11PlayerPosition(this.posX, event.getY(), this.posY, this.posZ, event.isOnGround()));
        } else if (var14) {
            this.sendQueue.addToSendQueue(new Packet12PlayerLook(event.getYaw(), event.getPitch(), event.isOnGround()));
        } else {
            this.sendQueue.addToSendQueue(new Packet10Flying(event.isOnGround()));
        }

        ++this.field_71168_co;
        this.wasOnGround = this.onGround;

        if (var13) {
            this.oldPosX = this.posX;
            this.oldMinY = event.getY();
            this.oldPosY = this.posY;
            this.oldPosZ = this.posZ;
            this.field_71168_co = 0;
        }

        if (var14) {
            this.oldRotationYaw = event.getYaw();
            this.oldRotationPitch = event.getPitch();
        }
        Client.INSTANCE.getEventBus().post(new UpdateEvent(this.rotationYaw, this.rotationPitch, this.boundingBox.minY, this.onGround));
    }

    @Override
    public void moveEntity(double par1, double par3, double par5) {
        final MotionEvent event = new MotionEvent(par1, par3, par5);

        Client.INSTANCE.getEventBus().post(event);
        super.moveEntity(event.getX(), event.getY(), event.getZ());
    }

    /**
     * Called when player presses the drop item key
     */
    public EntityItem dropOneItem(boolean par1) {
        int var2 = par1 ? 3 : 4;
        this.sendQueue.addToSendQueue(new Packet14BlockDig(var2, 0, 0, 0, 0));
        return null;
    }

    /**
     * Joins the passed in entity item with the world. Args: entityItem
     */
    protected void joinEntityItemWithWorld(EntityItem par1EntityItem) {
    }

    /**
     * Sends a chat message from the player. Args: chatMessage
     */
    public void sendChatMessage(String par1Str) {
        Mod mod = ModManager.getMod(par1Str.toLowerCase().replace(".t ", ""));
//        this.addChatMessage(par1Str.startsWith(".t ")?"1":"0");
        if (par1Str.toLowerCase().startsWith(".t ")) {
            if (mod != null) {
                mod.toggle();
                this.addChatMessage(("§8[§c§lFDP§6§lClient§8] §f" + par1Str.toLowerCase().replace(".t ", "") + " toggled"));
            }else{
                this.addChatMessage(("§8[§c§lFDP§6§lClient§8] §f" + par1Str.toLowerCase().replace(".t ", "") + " not found"));
            }
        } else {
            this.sendQueue.addToSendQueue(new Packet3Chat(par1Str));

        }

    }

    /**
     * Swings the item the player is holding.
     */
    public void swingItem() {
        super.swingItem();
        this.sendQueue.addToSendQueue(new Packet18Animation(this, 1));
    }

    public void respawnPlayer() {
        this.sendQueue.addToSendQueue(new Packet205ClientCommand(1));
    }

    /**
     * Deals damage to the entity. If its a EntityPlayer then will take damage from the armor first and then health
     * second with the reduced value. Args: damageAmount
     */
    protected void damageEntity(DamageSource par1DamageSource, int par2) {
        if (!this.isEntityInvulnerable()) {
            this.setEntityHealth(this.getHealth() - par2);
        }
    }

    /**
     * sets current screen to null (used on escape buttons of GUIs)
     */
    public void closeScreen() {
        this.sendQueue.addToSendQueue(new Packet101CloseWindow(this.openContainer.windowId));
        this.func_92015_f();
    }

    public void func_92015_f() {
        this.inventory.setItemStack((ItemStack) null);
        super.closeScreen();
    }

    /**
     * Updates health locally.
     */
    public void setHealth(int par1) {
        if (this.hasSetHealth) {
            super.setHealth(par1);
        } else {
            this.setEntityHealth(par1);
            this.hasSetHealth = true;
        }
    }

    /**
     * Adds a value to a statistic field.
     */
    public void addStat(StatBase par1StatBase, int par2) {
        if (par1StatBase != null) {
            if (par1StatBase.isIndependent) {
                super.addStat(par1StatBase, par2);
            }
        }
    }

    /**
     * Used by NetClientHandler.handleStatistic
     */
    public void incrementStat(StatBase par1StatBase, int par2) {
        if (par1StatBase != null) {
            if (!par1StatBase.isIndependent) {
                super.addStat(par1StatBase, par2);
            }
        }
    }

    /**
     * Sends the player's abilities to the server (if there is one).
     */
    public void sendPlayerAbilities() {
        this.sendQueue.addToSendQueue(new Packet202PlayerAbilities(this.capabilities));
    }

    public boolean func_71066_bF() {
        return true;
    }
}
