package com.copemonster.impl.commands;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Command {
    private final String name;
    private final String description;
    private final List<String> aliases;

    public Command() {
        CommandObject commandObject = getClass().getAnnotation(CommandObject.class);

        this.name = commandObject.name();
        this.description = commandObject.description();
        this.aliases = Arrays.stream(commandObject.aliases()).toList();
    }

    public boolean hasAlias(String alias) {
        return aliases.stream().anyMatch(alias::equalsIgnoreCase);
    }

    public abstract void execute(String... args);
}
