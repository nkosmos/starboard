package fr.nkosmos.starboard;

import fr.nkosmos.starboard.api.IGroup;
import fr.nkosmos.starboard.api.ISetting;
import fr.nkosmos.starboard.api.constraint.ValueConstraint;
import fr.nkosmos.starboard.api.constraint.VisibilityConstraint;
import fr.nkosmos.starboard.api.constraint.special.ValueCallback;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Setting<T> implements ISetting<T> {

    private final String name;
    private final T defaultValue;
    private final IGroup parentGroup;

    private final Set<ValueConstraint<T>> valueConstraints = new HashSet<>();
    private final Set<VisibilityConstraint> visibilityConstraints = new HashSet<>();

    @Getter(AccessLevel.PRIVATE)
    private T value;

    public Setting(IGroup parentGroup, String name, T defaultValue) {
        this.parentGroup = parentGroup;
        this.name = name;
        this.defaultValue = defaultValue;

        this.parentGroup.getSettings().add(this);
    }

    @Override
    public T get() {
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    @Override
    public void set(final T value, final boolean triggerCallbacks) {
        if (valueConstraints.isEmpty()) {
            this.value = value;
            return;
        }

        T val = value;
        for (ValueConstraint<T> constraint : valueConstraints) {
            if (!triggerCallbacks) {
                if (constraint instanceof ValueCallback) {
                    continue;
                }
            }
            val = constraint.constraint(this, val);
        }
        this.value = val;
    }

    @Override
    public boolean isVisible() {
        if (visibilityConstraints.isEmpty()) {
            return true;
        }

        boolean visibility = true;
        for (VisibilityConstraint constraint : visibilityConstraints) {
            if (!(visibility = constraint.allowVisibility(this))) {
                break;
            }
        }
        return visibility;
    }
}
