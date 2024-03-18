package com.copemonster.impl.modules;

import lombok.Getter;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Modules {
    @Getter
    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.clear();

        Set<Class<? extends Module>> reflections =
                new Reflections("com.copemonster.impl.modules.categories")
                        .getSubTypesOf(Module.class);

        reflections.forEach(klass -> {
            try {
                modules.add(klass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static List<Module> getModules(Category category) {
        return modules.stream()
                .filter(module -> module.getCategory() == category)
                .toList();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Module> T getModule(Class<T> klass) {
        return (T) modules.stream()
                .filter(module -> module.getClass() == klass)
                .findFirst()
                .orElse(null);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Module> T getModule(String name) {
        return (T) modules.stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
