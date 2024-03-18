package com.copemonster.events.IO;

import com.copemonster.events.Event;
import com.copemonster.utils.IO.KeyAction;

public class KeyEvent extends Event {
    public int key;
    public KeyAction action;

    public KeyEvent(int key, KeyAction action) {
        this.key = key;
        this.action = action;
    }
}
