package com.copemonster.utils.exceptions;

public class ModuleNotFound extends RuntimeException {
    public ModuleNotFound(String name) {
        super("No module with name: " + name);
    }
}
