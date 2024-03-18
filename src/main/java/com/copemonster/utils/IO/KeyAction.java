package com.copemonster.utils.IO;

import org.lwjgl.glfw.GLFW;

public enum KeyAction {
    PRESS,
    REPEAT,
    RELEASE;

    public static KeyAction get(int action) {
        if (action == GLFW.GLFW_RELEASE) return RELEASE;
        else if (action == GLFW.GLFW_PRESS) return PRESS;
        else return REPEAT;
    }
}