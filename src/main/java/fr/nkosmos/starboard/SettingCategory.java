package fr.nkosmos.starboard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public @Data
class SettingCategory {

    private final String name;
    private final Object parent;
    private final boolean root;

    private final List<ISetting<?>> settings = new ArrayList<>();

    public SettingCategory(String name, Object parent) {
        this(name, parent, false);
    }

    public SettingCategory(String name, Object parent, boolean root) {
        this.name = name;
        this.parent = parent;
        this.root = root;

        ListenerProvider.INSTANCE.dispatchNewCategory(this);
    }

    @FunctionalInterface
    public interface RegistrationListener {
        void onNewCategory(SettingCategory settingCategory);
    }

    private static final class ListenerProvider {
        private static final ListenerProvider INSTANCE = new ListenerProvider();

        private final ServiceLoader<RegistrationListener> serviceLoader;

        private ListenerProvider() {
            this.serviceLoader = ServiceLoader.load(RegistrationListener.class);
        }

        void dispatchNewCategory(SettingCategory settingCategory) {
            this.serviceLoader.reload();

            this.serviceLoader.forEach(listener -> listener.onNewCategory(settingCategory));
        }
    }

}
