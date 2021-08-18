package fr.nkosmos.starboard.constraint.value;

import fr.nkosmos.starboard.api.ISetting;
import fr.nkosmos.starboard.constraint.ValueConstraint;
import lombok.Data;

public @Data class CoerceConstraint implements ValueConstraint<Number> {

    private final Number min, max;

    @Override
    public Number constraint(ISetting<Number> setting, Number value) {
        if (value.doubleValue() < min.doubleValue()) return min;
        if (value.doubleValue() > max.doubleValue()) return max;
        return value;
    }
}
