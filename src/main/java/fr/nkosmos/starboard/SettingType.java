package fr.nkosmos.starboard;

import fr.nkosmos.starboard.special.Range;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.awt.*;

@RequiredArgsConstructor
@Getter
@ToString
public enum SettingType {
    CHECKBOX(Boolean.class),
    TEXTAREA(String.class),
    DECIMAL_SLIDER(Double.class),
    RANGE_SLIDER(Range.class),
    PERCENTAGE_SLIDER(Float.class),
    SLIDER(Integer.class),
    DROPDOWN(Object[].class),
    COLOR(Color.class),
    BUTTON(null);

    private final Class<?> clazz;
}
