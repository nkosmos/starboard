package fr.nkosmos.starboard.constraint.value;

import fr.nkosmos.starboard.api.ISetting;
import fr.nkosmos.starboard.api.constraint.ValueConstraint;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public @Data class PredicateConstraint<T> implements ValueConstraint<T> {

    private final List<Predicate<T>> choices;

    public PredicateConstraint(Predicate<T>... choices) {
        this.choices = Arrays.asList(choices);
    }

    @Override
    public T constraint(ISetting<T> setting, T value) {
        for (Predicate<T> predicate : choices) {
            if (!predicate.test(value)) {
                return setting.get();
            }
        }
        return value;
    }
}
