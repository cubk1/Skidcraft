package wtf.kiddo.skidcraft.value.impl;

import wtf.kiddo.skidcraft.value.Dependency;
import wtf.kiddo.skidcraft.value.Value;

/**
 * Author: zcy
 * Created: 2022/5/2
 */
public class StringValue extends Value<String> {
    public StringValue(String label, String value) {
        super(label, value);
    }

    public StringValue(String label, String value, Dependency visibility) {
        super(label, value, visibility);
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}