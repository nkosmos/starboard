package fr.nkosmos.starboard.constraint.visibility;

import fr.nkosmos.starboard.api.ISetting;
import fr.nkosmos.starboard.constraint.VisibilityConstraint;
import lombok.Data;

public @Data class SettingValueConstraint implements VisibilityConstraint {

    private final ISetting<?> setting;
    private final Object value;

    @Override
    public boolean allowVisibility(ISetting<?> setting) {
        return this.setting.get().equals(value);
    }

}
