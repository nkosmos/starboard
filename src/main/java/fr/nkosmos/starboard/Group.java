package fr.nkosmos.starboard;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public @Data class Group implements IGroupParent {

    private final String name;
    private final IGroupParent parent;
    private final boolean root;

    protected final List<ISetting<?>> settings = new ArrayList<>();
    protected final List<Group> subgroups = new ArrayList<>();

    public Group(String name, IGroupParent parent) {
        this(name, parent, false);

        if (parent instanceof Group) {
            ((Group) parent).subgroups.add(this);
        }
    }
}
