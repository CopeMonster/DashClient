package com.copemonster.utils.IO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyBind {
    private int key;
    private boolean isPressed;

    public KeyBind(int key) {
        this.key = key;
        this.isPressed = false;
    }

    public void reset() {
        key = -1;
    }

    public static KeyBind fromKey(int key) {
        return new KeyBind(key);
    }

    public static KeyBind emptyKey() {
        return new KeyBind(-1);
    }
}
