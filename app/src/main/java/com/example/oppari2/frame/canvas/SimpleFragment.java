package com.example.oppari2.frame.canvas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oppari2.MainActivity;
import com.example.oppari2.frame.development.Dev;
import com.example.oppari2.frame.event.Event;

import java.util.ArrayList;

public class SimpleFragment extends BaseFragment implements View.OnClickListener {

    private static final String sender = "SimpleFragment";
    private ArrayList<Event> buttonEvents = new ArrayList<>();
    private ViewGroup view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            //not the first time so we will check SavedInstanceState bundle
            layoutId = savedInstanceState.getInt("layoutId");
            ((MainActivity) getActivity()).getStateController().getFragmentController().injectFragment(this);
        }

        view = (ViewGroup) inflater.inflate(
                getLayoutId(), container, false);

        for (Event event : buttonEvents) {
            Button nextButton = view.findViewById(event.sender);
            nextButton.setOnClickListener(this);
        }

        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("layoutId",layoutId);
    }

    public void registerOnClickEvent(Event event) {
        buttonEvents.add(event);
    }

    @Override
    public void onClick(View v) {
        Dev.m(sender, "On click");

        for (Event event : buttonEvents) {
            if (event.sender == v.getId()) {
                getEventRouter().triggerEvent(event);
            }
        }
    }
}