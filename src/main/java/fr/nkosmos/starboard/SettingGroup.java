package fr.nkosmos.starboard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public @Data
class SettingGroup {

    private final String name;
    private final Object parent;
    private final boolean root;

    private final List<ISetting<?>> settings = new ArrayList<>();

    public SettingGroup(String name, Object parent) {
        this(name, parent, false);
    }

    public SettingGroup(String name, Object parent, boolean root) {
        this.name = name;
        this.parent = parent;
        this.root = root;

        ListenerProvider.INSTANCE.dispatchGroup(this);
    }

    @FunctionalInterface
    public interface RegistrationListener {
        void onNewGroup(SettingGroup settingGroup);
    }

    private static final class ListenerProvider {
        private static final ListenerProvider INSTANCE = new ListenerProvider();

        private final ServiceLoader<RegistrationListener> serviceLoader;

        private ListenerProvider() {
            this.serviceLoader = ServiceLoader.load(RegistrationListener.class);
        }

        void dispatchGroup(SettingGroup settingGroup) {
            this.serviceLoader.reload();

            this.serviceLoader.forEach(listener -> listener.onNewGroup(settingGroup));
        }
    }

}
