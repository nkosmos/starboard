package fr.nkosmos.starboard.constraint;

import fr.nkosmos.starboard.Setting;

@FunctionalInterface
public interface VisibilityConstraint {

    boolean allowVisibility(Setting<?> setting);

}
