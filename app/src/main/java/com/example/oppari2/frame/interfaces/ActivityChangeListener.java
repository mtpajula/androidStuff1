package com.example.oppari2.frame.interfaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.oppari2.frame.development.Dev;
import com.example.oppari2.frame.event.Event;
import com.example.oppari2.frame.event.EventListener;

import java.util.ArrayList;

import static com.example.oppari2.frame.event.EventConstants.EVENT_ACTIVITY;

public abstract class ActivityChangeListener implements EventListener {
    private AppCompatActivity activity;
    private static final String sender = "ActivityChangeListener";

    public ActivityChangeListener(AppCompatActivity activity) {
        this.activity = activity;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    @Override
    public ArrayList<String> getTypes() {
        ArrayList<String> types = new ArrayList<>();
        types.add(EVENT_ACTIVITY);
        return types;
    }

    @Override
    public void onEvent(Event event) {

        Event<String> activityEvent = (Event<String>) event;

        Dev.m(sender, "EVENT "
                + activityEvent.type
                + ", payload: "
                + activityEvent.payload);

        Intent i = getActivityChangeIntent(activityEvent);

        activity.finish();
        activity.startActivity(i);
    }

    public abstract Intent getActivityChangeIntent(Event<String> event);
}
