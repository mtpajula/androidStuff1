package com.example.oppari2.oppari.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.oppari2.MainActivity;
import com.example.oppari2.R;
import com.example.oppari2.frame.canvas.BaseFragment;
import com.example.oppari2.frame.development.Dev;
import com.example.oppari2.frame.event.Event;

import static com.example.oppari2.frame.event.EventConstants.EVENT_FRAGMENT;
import static com.example.oppari2.oppari.main.OppariFragmentBuilder.FRAGMENT_LOGIN;

public class SplashFragment extends BaseFragment {

    private static final String sender = "ItemDetailFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            ((MainActivity) getActivity()).getStateController().getFragmentController().injectFragment(this);
        }

        ViewGroup view = (ViewGroup) inflater.inflate(
                R.layout.fragment_splash, container, false);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                onTimer();
            }
        }, 2000);

        return view;
    }

    public void onTimer() {
        Dev.m(sender, "Timer ready");

        Event<String> event = new Event<>();
        event.type = EVENT_FRAGMENT;
        event.payload = FRAGMENT_LOGIN;

        getEventRouter().triggerEvent(event);
    }
}
