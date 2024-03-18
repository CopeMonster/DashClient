package com.copemonster.impl.commands;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Commands {
    @Getter
    @Setter
    private static String commandPrefix = "!";

    @Getter
    private static final List<Command> commands = new ArrayList<>();

    public static void init() {
        commands.clear();

        Set<Class<? extends Command>> reflections =
                new Reflections("com.copemonster.impl.commands.client")
                        .getSubTypesOf(Command.class);

        reflections.forEach(klass -> {
            try {
                commands.add(klass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static Command getCommand(String name) {
        return commands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static void executeCommand(String input){
        String[] split = input.split(" ");

        for (Command command : commands){
            if (command.hasAlias(split[0])) {
                command.execute(ArrayUtils.subarray(split, 1, split.length));
            }
        }
    }
}
