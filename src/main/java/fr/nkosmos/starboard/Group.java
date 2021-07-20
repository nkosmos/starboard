package fr.nkosmos.starboard;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public @Data class Group {

    private final String name;
    private final Object parent;
    private final boolean root;

    private final List<ISetting<?>> settings = new ArrayList<>();

    public Group(String name, Object parent) {
        this(name, parent, false);
    }

}
