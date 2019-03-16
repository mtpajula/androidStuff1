package com.example.oppari2.frame.store.types.app;

import com.example.oppari2.frame.development.Dev;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AppState {

    @SerializedName("appContainers")
    @Expose
    public ArrayList<AppContainer> appContainers;

    private static final String sender = "AppState";

    public AppState() {
        appContainers = new ArrayList<>();
    }

    public void activateContainer(int container) {
        for (AppContainer appContainer : appContainers) {
            if (appContainer.getId() == container) {
                appContainer.setActive(true);
            }
        }
    }

    public ArrayList<AppContainer> getActiveContainers() {
        ArrayList<AppContainer> activeContainers = new ArrayList<>();

        for (AppContainer appContainer : appContainers) {
            if (appContainer.isActive()) {
                activeContainers.add(appContainer);
            }
        }
        return activeContainers;
    }

    public void addAppContainer(AppContainer appContainer) {
        appContainers.add(appContainer);
    }

    public void log() {
        for (AppContainer appContainer : appContainers) {

            Dev.m(sender, "CONTAINER "
                    + appContainer.name
                    + ". active: "
                    + String.valueOf(appContainer.isActive()
            ));

            String m = "null";

            if (appContainer.getCurrentFragment() != null) {
                m = appContainer.getCurrentFragment().getId();
            }

            Dev.m(sender, "    "
                    + appContainer.name
                    + ". current fragment: "
                    + String.valueOf(m)
            );

            if (appContainer.getDefaultFragment() != null) {
                m = appContainer.getDefaultFragment().getId();
            }

            Dev.m(sender, "    "
                    + appContainer.name
                    + ". default fragment: "
                    + String.valueOf(m)
            );

            for (AppFragment appFragment : appContainer.getAppFragments()) {
                Dev.m(sender, "    "
                        + appContainer.name
                        + ". fragment: "
                        + appFragment.getId()
                );
            }
        }
    }
}
