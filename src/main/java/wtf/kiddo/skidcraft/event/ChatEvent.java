/*
 * Decompiled with CFR 0_132.
 */
package wtf.kiddo.skidcraft.event;

import me.bush.eventbus.event.Event;

public class ChatEvent extends Event {
    private String message;

    public ChatEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected boolean isCancellable() {
        return true;
    }
}

