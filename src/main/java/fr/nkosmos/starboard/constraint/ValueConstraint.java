package fr.nkosmos.starboard.constraint;

import fr.nkosmos.starboard.ISetting;

@FunctionalInterface
public interface ValueConstraint<T> {

    T constraint(ISetting<T> setting, T value);

}