package com.copemonster.events.player;

import com.copemonster.events.Event;
import lombok.Getter;

@Getter
public class SendMessageEvent extends Event {
    private final String message;

    public SendMessageEvent(String message) {
        this.message = message;
    }
}
