package fr.nkosmos.starboard.api.constraint;

import fr.nkosmos.starboard.api.ISetting;

@FunctionalInterface
public interface ValueConstraint<T> {

    T constraint(ISetting<T> setting, T value);

}
