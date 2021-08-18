package fr.nkosmos.starboard.api;

import java.util.List;

public interface IGroup {
    String getName();

    IGroup getParent();

    boolean isRoot();

    List<ISetting<?>> getSettings();

    List<IGroup> getSubgroups();
}
