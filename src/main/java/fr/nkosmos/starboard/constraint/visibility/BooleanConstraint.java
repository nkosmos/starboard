package fr.nkosmos.starboard.constraint.visibility;

import lombok.*;
import fr.nkosmos.starboard.Setting;
import fr.nkosmos.starboard.constraint.VisibilityConstraint;

import java.util.Arrays;
import java.util.function.Supplier;

public @Data class BooleanConstraint implements VisibilityConstraint {

    private final Supplier<Boolean>[] booleanSuppliers;

    @Override
    public boolean allowVisibility(Setting<?> setting) {
        return Arrays.stream(booleanSuppliers).anyMatch(Supplier::get);
    }
}
