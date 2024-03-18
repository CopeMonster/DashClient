package com.copemonster.impl.modules;

import com.copemonster.DashClient;
import com.copemonster.utils.IO.KeyBind;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Module {
    private final String name;
    private final String description;
    private final Category category;

    @Setter
    private boolean isEnabled;

    @Setter
    private KeyBind keyBind;

    public Module() {
        ModuleObject moduleObject = getClass().getAnnotation(ModuleObject.class);

        this.name = moduleObject.name();
        this.description = moduleObject.description();
        this.category = moduleObject.category();
        this.isEnabled = moduleObject.isEnabled();
        this.keyBind = new KeyBind(moduleObject.key());
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void toggle() {
        if (isEnabled) {
            setEnabled(false);
            onDisable();
            DashClient.EVENT_BUS.unregister(this);
        } else {
            setEnabled(true);
            onEnable();
            DashClient.EVENT_BUS.register(this);
        }

        // TODO implement notification of enabling module
    }
}
