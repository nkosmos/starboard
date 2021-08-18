package fr.nkosmos.starboard;

import fr.nkosmos.starboard.api.ISetting;
import fr.nkosmos.starboard.constraint.ValueConstraint;
import fr.nkosmos.starboard.constraint.VisibilityConstraint;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
public class Setting<T> implements ISetting<T> {

    private final String name;
    private final T defaultValue;
    private final Group parentGroup;

    private final Set<ValueConstraint<T>> valueConstraints = new HashSet<>();
    private final Set<VisibilityConstraint> visibilityConstraints = new HashSet<>();

    @Getter(AccessLevel.PRIVATE) private T value;

    public Setting(Group parentGroup, String name, T defaultValue) {
        this.parentGroup = parentGroup;
        this.name = name;
        this.defaultValue = defaultValue;

        this.parentGroup.settings.add(this);
    }

    @Override
    public T get() {
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    @Override
    public void set(T value) {
        if (valueConstraints.isEmpty()) {
            this.value = value;
            return;
        }

        T val = value;
        for (ValueConstraint<T> constraint : valueConstraints) {
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
