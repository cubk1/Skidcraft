package wtf.kiddo.skidcraft.value.impl;

import wtf.kiddo.skidcraft.value.Dependency;
import wtf.kiddo.skidcraft.value.Value;

/**
 * Author: zcy
 * Created: 2022/5/2
 */
public final class NumberValue<T extends Number> extends Value<T> {
    private final T minimum, maximum, inc;
    public NumberValue(String label, T value, T minimum, T maximum, T inc) {
        super(label, value);
        this.minimum = minimum;
        this.maximum = maximum;
        this.inc = inc;
    }

    public NumberValue(String label, T value, T minimum, T maximum, T inc, Dependency visibility) {
        super(label, value, visibility);
        this.minimum = minimum;
        this.maximum = maximum;
        this.inc = inc;
    }

    public T getMinimum() {
        return this.minimum;
    }
    public T getMaximum() {
        return this.maximum;
    }
    public T getInc() {
        return this.inc;
    }
    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = clamp(value, minimum, maximum);
    }

    public static <T extends Number> T clamp(final T value, final T min, final T max) {
        return (((Comparable) value).compareTo(min) < 0) ? min
                : ((((Comparable) value).compareTo(max) > 0) ? max : value);
    }
}
