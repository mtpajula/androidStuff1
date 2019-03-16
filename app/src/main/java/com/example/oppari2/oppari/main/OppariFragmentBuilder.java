package com.example.oppari2.oppari.main;

import com.example.oppari2.R;
import com.example.oppari2.frame.canvas.BaseFragment;
import com.example.oppari2.frame.canvas.SimpleFragment;
import com.example.oppari2.frame.event.Event;
import com.example.oppari2.frame.interfaces.FragmentBuilder;
import com.example.oppari2.oppari.splash.SplashFragment;

import static com.example.oppari2.frame.event.EventConstants.EVENT_ACTIVITY;
import static com.example.oppari2.frame.event.EventConstants.EVENT_FRAGMENT;

public class OppariFragmentBuilder implements FragmentBuilder {

    public static final String FRAGMENT_SPLASH   = "splash fragment";
    public static final String FRAGMENT_LOGIN    = "login fragment";
    public static final String FRAGMENT_REGISTER = "register fragment";

    @Override
    public BaseFragment getFragment(String fragmentId) {
        BaseFragment fragment = null;
        switch (fragmentId) {
            case FRAGMENT_SPLASH:
                fragment = new SplashFragment();
                break;
            case FRAGMENT_LOGIN:
                fragment = getLoginFragment();
                break;
            case FRAGMENT_REGISTER:
                fragment = getRegisterFragment();
                break;
        }
        return fragment;
    }

    private SimpleFragment getLoginFragment() {
        SimpleFragment fragment = new SimpleFragment();
        fragment.setLayoutId(R.layout.fragment_login);

        Event<String> loginButtonEvent = new Event<>();
        loginButtonEvent.sender = R.id.login_button;
        loginButtonEvent.type = EVENT_ACTIVITY;

        fragment.registerOnClickEvent(loginButtonEvent);

        Event<String> registerButtonEvent = new Event<>();
        registerButtonEvent.sender = R.id.new_registration_button;
        registerButtonEvent.type = EVENT_FRAGMENT;
        registerButtonEvent.payload = FRAGMENT_REGISTER;

        fragment.registerOnClickEvent(registerButtonEvent);

        return fragment;
    }

    private SimpleFragment getRegisterFragment() {
        SimpleFragment fragment = new SimpleFragment();
        fragment.setLayoutId(R.layout.fragment_register);

        Event<String> createButtonEvent = new Event<>();
        createButtonEvent.sender = R.id.register_button;
        createButtonEvent.type = EVENT_ACTIVITY;

        fragment.registerOnClickEvent(createButtonEvent);

        return fragment;
    }
}
