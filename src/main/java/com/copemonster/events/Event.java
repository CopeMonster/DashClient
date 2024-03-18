package com.copemonster.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private boolean cancelled = false;

    public void cancel() { cancelled = true; }
}
