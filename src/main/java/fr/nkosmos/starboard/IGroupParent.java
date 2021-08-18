package fr.nkosmos.starboard;

import java.util.List;

public interface IGroupParent {
    IGroupParent getParent();

    List<Group> getSubgroups();

    String getName();
}
