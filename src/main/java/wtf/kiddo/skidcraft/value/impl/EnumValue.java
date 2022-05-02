package wtf.kiddo.skidcraft.value.impl;

import wtf.kiddo.skidcraft.value.Dependency;
import wtf.kiddo.skidcraft.value.Value;

/**
 * Author: zcy
 * Created: 2022/5/2
 */
public final class EnumValue<T extends Enum<T>> extends Value<T> {
    private final T[] constants;

    public EnumValue(String label, T value) {
        super(label, value);
        this.constants = extractConstantsFromEnumValue(value);
    }

    public EnumValue(String label, T value, Dependency visibility) {
        super(label, value, visibility);
        this.constants = extractConstantsFromEnumValue(value);
    }

    public T[] extractConstantsFromEnumValue(T value) {
        return value.getDeclaringClass().getEnumConstants();
    }

    public T[] getConstants() {
        return constants;
    }

    public void increment() {
        T currentValue = getValue();

        for (T constant : constants) {
            if (constant != currentValue) {
                continue;
            }

            T newValue;

            int ordinal = constant.ordinal();
            if (ordinal == constants.length - 1) {
                newValue = constants[0];
            } else {
                newValue = constants[ordinal + 1];
            }

            setValue(newValue);
            return;
        }
    }

    public void decrement() {
        T currentValue = getValue();

        for (T constant : constants) {
            if (constant != currentValue) {
                continue;
            }

            T newValue;

            int ordinal = constant.ordinal();
            if (ordinal == 0) {
                newValue = constants[constants.length - 1];
            } else {
                newValue = constants[ordinal - 1];
            }

            setValue(newValue);
            return;
        }
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
