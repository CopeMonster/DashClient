package com.copemonster.utils.exceptions;

import java.util.Arrays;

public class InvalidCommandSyntaxException extends RuntimeException {
    public InvalidCommandSyntaxException(String... args) {
        super("Invalid command syntax: " + Arrays.toString(args));
    }
}
