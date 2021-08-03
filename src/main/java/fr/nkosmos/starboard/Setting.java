package fr.nkosmos.starboard;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import fr.nkosmos.starboard.constraint.ValueConstraint;
import fr.nkosmos.starboard.constraint.VisibilityConstraint;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
public class Setting<T> implements ISetting<T> {

    private final String name;
    private final SettingType type;
    private final T defaultValue;

    private final Set<ValueConstraint<T>> valueConstraints = new HashSet<>();
    private final Set<VisibilityConstraint> visibilityConstraints = new HashSet<>();

    @Getter(AccessLevel.PRIVATE) private T value;

    public Setting(Group category, String name, T defaultValue) {
        this(category, name, guessType(defaultValue), defaultValue);
    }

    public Setting(Group group, String name, SettingType type, T defaultValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;

        group.settings.add(this);
    }

    private static SettingType guessType(Object value) {
        Class<?> clazz = value.getClass();
        if (Arrays.asList(clazz.getInterfaces()).contains(ISettingEnum.class)) {
            return SettingType.DROPDOWN;
        }
        return Arrays.stream(SettingType.values())
                .filter(t -> t.getClazz().equals(clazz))
                .findFirst()
                .orElse(null);
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
