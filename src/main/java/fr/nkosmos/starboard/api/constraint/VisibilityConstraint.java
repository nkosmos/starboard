package fr.nkosmos.starboard.api.constraint;

import fr.nkosmos.starboard.api.ISetting;

@FunctionalInterface
public interface VisibilityConstraint {

    boolean allowVisibility(ISetting<?> setting);

}
