package com.example.oppari2.frame.event;

import com.example.oppari2.frame.development.Dev;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class EventRouter {

    ArrayList<WeakReference<EventListener>> listeners;
    private static final String sender = "EventRouter";

    public EventRouter() {
        listeners = new ArrayList<>();
    }

    public void registerListener(EventListener eventListener) {
        WeakReference<EventListener> wr = new WeakReference<>(eventListener);
        listeners.add(wr);
    }

    public void triggerEvent(Event event) {

        Dev.m(sender, "Event triggered. " + event.type);
        Dev.m(sender, "Listeners: " + String.valueOf(listeners.size()));

        for (WeakReference<EventListener> weakReference : listeners) {
            EventListener listener = weakReference.get();
            if (listener != null) {
                ArrayList<String> types = listener.getTypes();
                Dev.m(sender, "Listener types: " + String.valueOf(types.toString()));
                for (String type : types) {
                    if (type.equals(event.type)) {
                        listener.onEvent(event);
                    }
                }
            }
        }
    }
}
