package com.example.oppari2.oppari.main;

import com.example.oppari2.R;
import com.example.oppari2.frame.interfaces.AppStatePopulator;
import com.example.oppari2.frame.store.types.app.AppContainer;
import com.example.oppari2.frame.store.types.app.AppState;

import static com.example.oppari2.oppari.main.OppariFragmentBuilder.FRAGMENT_LOGIN;
import static com.example.oppari2.oppari.main.OppariFragmentBuilder.FRAGMENT_REGISTER;
import static com.example.oppari2.oppari.main.OppariFragmentBuilder.FRAGMENT_SPLASH;

public class OppariAppStatePopulator implements AppStatePopulator {
    @Override
    public AppState getAppState() {
        AppState appState = new AppState();

        AppContainer container1 = new AppContainer();
        container1.setId(R.id.fragment_container_1);
        container1.addAppFragment(FRAGMENT_SPLASH, true);
        container1.addAppFragment(FRAGMENT_LOGIN);
        container1.addAppFragment(FRAGMENT_REGISTER);
        container1.setName("container 1");

        appState.addAppContainer(container1);

        return appState;
    }
}
