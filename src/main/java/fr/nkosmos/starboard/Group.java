package fr.nkosmos.starboard;

import fr.nkosmos.starboard.api.IGroup;
import fr.nkosmos.starboard.api.ISetting;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Group implements IGroup {

    private final String name;
    private final Group parent;
    private final boolean root;

    protected final List<ISetting<?>> settings = new ArrayList<>();
    protected final List<IGroup> subgroups = new ArrayList<>();

    public Group(String name) {
        this(name, null, false);
    }

    public Group(String name, Group parent) {
        this(name, parent, false);
    }

    public Group(String name, Group parent, boolean root) {
        this.name = name;
        this.parent = parent;
        this.root = root;

        if (parent != null) {
            parent.getSubgroups().add(this);
        }
    }
}
