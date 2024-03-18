package com.copemonster.impl.commands.client;

import com.copemonster.impl.commands.Command;
import com.copemonster.impl.commands.CommandObject;
import com.copemonster.impl.modules.Module;
import com.copemonster.impl.modules.Modules;
import com.copemonster.utils.exceptions.InvalidCommandSyntaxException;
import com.copemonster.utils.exceptions.ModuleNotFound;

@CommandObject(
        name = "BindCommand",
        description = "Bind module to specific key. Syntax: bind/b get/set/reset <module_name>",
        aliases = {"bind", "b"}
)
public class BindCommand extends Command {
    public static boolean waiting;
    public static Module waitingModule;

    @Override
    public void execute(String... args) {
        if (args.length != 2) {
            throw new InvalidCommandSyntaxException(args);
        }

        Option option = Option.valueOf(args[0]);

        Module module = Modules.getModule(args[1]);
        if (module == null) {
            throw new ModuleNotFound(args[1]);
        }

        switch (option) {
            case GET -> {
                // TODO info notification
            }
            case SET -> {
                // TODO info notification

                waiting = true;
                waitingModule = module;
            }
            case RESET -> {
                // TODO info notification

                module.getKeyBind().reset();
            }

        }
    }

    private enum Option {
        GET,
        SET,
        RESET;
    }
}
