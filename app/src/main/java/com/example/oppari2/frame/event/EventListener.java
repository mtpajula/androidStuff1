package com.example.oppari2.frame.event;

import java.util.ArrayList;

public interface EventListener {
    ArrayList<String> getTypes();
    void onEvent(Event event);
}
