package fr.nkosmos.starboard.api.constraint.special;

import fr.nkosmos.starboard.api.ISetting;
import fr.nkosmos.starboard.api.constraint.ValueConstraint;

@FunctionalInterface
public interface ValueCallback<T> extends ValueConstraint<T> {

    @Override
    default T constraint(ISetting<T> setting, T value){
        onValueChanged(setting, value);
        return value;
    }

    void onValueChanged(ISetting<T> setting, T newValue);
}
