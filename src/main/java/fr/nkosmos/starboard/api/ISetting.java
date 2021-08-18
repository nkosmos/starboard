package fr.nkosmos.starboard.api;

import fr.nkosmos.starboard.Group;
import fr.nkosmos.starboard.constraint.special.ValueCallback;
import fr.nkosmos.starboard.constraint.visibility.BooleanConstraint;
import fr.nkosmos.starboard.constraint.visibility.SettingValueConstraint;
import fr.nkosmos.starboard.constraint.ValueConstraint;
import fr.nkosmos.starboard.constraint.VisibilityConstraint;
import fr.nkosmos.starboard.constraint.value.CoerceConstraint;
import fr.nkosmos.starboard.constraint.value.ChoiceConstraint;

import java.util.Set;
import java.util.function.Supplier;

public interface ISetting<T> {

    String getName();

    T get();

    T getDefaultValue();

    void set(T value);

    boolean isVisible();

    Group getParentGroup();

    Set<ValueConstraint<T>> getValueConstraints();

    Set<VisibilityConstraint> getVisibilityConstraints();

    default void resetDefault() {
        this.set(this.getDefaultValue());
    }

    default <K extends ISetting<T>> K coerce(Number min, Number max) {
        this.getValueConstraints().add((ValueConstraint<T>) new CoerceConstraint(min, max));
        return (K) this;
    }

    default <K extends ISetting<T>> K values(T... choices) {
        this.getValueConstraints().add(new ChoiceConstraint<>(choices));
        return (K) this;
    }

    default <K extends ISetting<T>> K callback(ValueCallback<T> callback) {
        this.getValueConstraints().add(callback);
        return (K) this;
    }

    default <K extends ISetting<T>> K onlyIf(ISetting<Boolean>... booleanSettings) {
        Supplier<Boolean>[] arr = new Supplier[booleanSettings.length];

        // wierd hack to get around ClassCastException when using the Stream API
        for (int i = 0; i < booleanSettings.length; i++) {
            final int j = i;
            arr[i] = () -> booleanSettings[j].get();
        }

        return onlyIf(arr);
    }

    default <K extends ISetting<T>> K onlyIf(Supplier<Boolean>... conditions) {
        this.getVisibilityConstraints().add(new BooleanConstraint(conditions));
        return (K) this;
    }

    default <K extends ISetting<T>> K onlyWhen(ISetting<?> setting, Object value) {
        this.getVisibilityConstraints().add(new SettingValueConstraint(setting, value));
        return (K) this;
    }

}
