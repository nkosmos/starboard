package fr.nkosmos.starboard.constraint;

import fr.nkosmos.starboard.api.ISetting;

@FunctionalInterface
public interface VisibilityConstraint {

    boolean allowVisibility(ISetting<?> setting);

}
