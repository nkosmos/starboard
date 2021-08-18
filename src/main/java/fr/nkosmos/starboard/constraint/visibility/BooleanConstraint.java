package fr.nkosmos.starboard.constraint.visibility;

import fr.nkosmos.starboard.api.ISetting;
import fr.nkosmos.starboard.api.constraint.VisibilityConstraint;
import lombok.Data;

import java.util.Arrays;
import java.util.function.Supplier;

public @Data class BooleanConstraint implements VisibilityConstraint {

    private final Supplier<Boolean>[] booleanSuppliers;

    @Override
    public boolean allowVisibility(ISetting<?> setting) {
        return Arrays.stream(booleanSuppliers).anyMatch(Supplier::get);
    }
}
