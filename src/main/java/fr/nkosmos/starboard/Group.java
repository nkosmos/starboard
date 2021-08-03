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

    protected final List<ISetting<?>> settings = new ArrayList<>();
    protected final List<Group> subgroups = new ArrayList<>();

    public Group(String name, Object parent) {
        this(name, parent, false);

        if (parent instanceof Group) {
            ((Group) parent).subgroups.add(this);
        }
    }

}
