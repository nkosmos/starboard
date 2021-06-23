package fr.nkosmos.starboard.constraint.value;

import fr.nkosmos.starboard.ISetting;
import fr.nkosmos.starboard.constraint.ValueConstraint;
import lombok.*;

import java.util.Arrays;

public @Data class ChoiceConstraint<T> implements ValueConstraint<T> {

    private final T[] choices;

    @Override
    public T constraint(ISetting<T> setting, T value) {
        if (Arrays.asList(choices).contains(value)) return value;
        return setting.get();
    }

}
