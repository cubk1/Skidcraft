package wtf.kiddo.skidcraft.value.impl;

import wtf.kiddo.skidcraft.value.Dependency;
import wtf.kiddo.skidcraft.value.Value;

/**
 * Author: zcy
 * Created: 2022/5/2
 */
public final class BooleanValue extends Value<Boolean> {

    public BooleanValue(String label, Boolean value) {
        super(label, value);
    }

    public BooleanValue(String label, Boolean value, Dependency visibility) {
        super(label, value, visibility);
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    public void toggle() {
        setValue(!value);
    }
}