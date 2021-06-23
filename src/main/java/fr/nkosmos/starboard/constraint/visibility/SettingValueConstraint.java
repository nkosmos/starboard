package fr.nkosmos.starboard.constraint.visibility;

import lombok.*;
import fr.nkosmos.starboard.Setting;
import fr.nkosmos.starboard.constraint.VisibilityConstraint;

public @Data class SettingValueConstraint implements VisibilityConstraint {

    private final Setting<?> setting;
    private final Object value;

    @Override
    public boolean allowVisibility(Setting<?> setting) {
        return this.setting.get().equals(value);
    }

}
