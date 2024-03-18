package com.copemonster.impl.commands.client;

import com.copemonster.impl.commands.Command;
import com.copemonster.impl.commands.CommandObject;
import com.copemonster.impl.modules.Module;
import com.copemonster.impl.modules.Modules;
import com.copemonster.utils.exceptions.InvalidCommandSyntaxException;
import com.copemonster.utils.exceptions.ModuleNotFound;

@CommandObject(
        name = "ToggleCommand",
        description = "Toggle any module. Syntax: toggle/t <module_name>",
        aliases = {"toggle", "t"}
)
public class ToggleCommand extends Command {
    @Override
    public void execute(String... args) {
        if (args.length != 1) {
            throw new InvalidCommandSyntaxException(args);
        }

        Module module = Modules.getModule(args[0]);
        if (module == null) {
            throw new ModuleNotFound(args[0]);
        }

        module.toggle();
    }
}
