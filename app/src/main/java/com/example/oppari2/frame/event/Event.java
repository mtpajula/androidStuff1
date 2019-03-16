package com.example.oppari2.frame.event;

public class Event<T> {
    public String type;
    public boolean success = true;
    public int sender;
    public T payload;

    public T getPayload() {
        return payload;
    }
}
