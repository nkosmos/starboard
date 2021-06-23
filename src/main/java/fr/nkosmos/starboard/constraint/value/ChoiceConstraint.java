package fr.nkosmos.starboard.constraint.value;

import fr.nkosmos.starboard.ISetting;
import fr.nkosmos.starboard.constraint.ValueConstraint;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

public @Data class ChoiceConstraint<T> implements ValueConstraint<T> {

    private final List<T> choices;

    public ChoiceConstraint(T... choices) {
        this.choices = Arrays.asList(choices);
    }

    @Override
    public T constraint(ISetting<T> setting, T value) {
        if (choices.contains(value)) return value;
        return setting.get();
    }

}
