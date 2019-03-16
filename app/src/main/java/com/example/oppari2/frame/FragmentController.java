package com.example.oppari2.frame;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.oppari2.frame.canvas.BaseFragment;
import com.example.oppari2.frame.development.Dev;
import com.example.oppari2.frame.event.Event;
import com.example.oppari2.frame.event.EventListener;
import com.example.oppari2.frame.event.EventRouter;
import com.example.oppari2.frame.interfaces.FragmentBuilder;
import com.example.oppari2.frame.store.Store;
import com.example.oppari2.frame.store.types.app.AppContainer;
import com.example.oppari2.frame.store.types.app.AppFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.example.oppari2.frame.event.EventConstants.EVENT_FRAGMENT;

public class FragmentController implements EventListener {

    private static final String sender = "FragmentController";
    public String currentFragment;
    public CustomBackStack fragmentBackStack;
    public Store store;
    private EventRouter eventRouter;
    private FragmentBuilder fragmentBuilder;

    private WeakReference<AppCompatActivity> activityReference;

    public FragmentController(AppCompatActivity activity, FragmentBuilder fragmentBuilder) {
        activityReference = new WeakReference<>(activity);
        fragmentBackStack = new CustomBackStack("fragmentBackStack");
        this.fragmentBuilder = fragmentBuilder;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setEventRouter(EventRouter eventRouter) {
        this.eventRouter = eventRouter;
        this.eventRouter.registerListener(this);
    }

    public void compose() {
        Dev.m(sender, "compose");
        for (AppContainer appContainer : store.appState.getActiveContainers()) {
            startFragment(appContainer);
        }
    }

    public void startFragment(String fragmentId) {
        for (AppContainer appContainer : store.appState.getActiveContainers()) {
            if (appContainer.getFragment(fragmentId) != null) {
                startFragmentAndUpdateState(
                        appContainer,
                        appContainer.getFragment(fragmentId)
                );
                break;
            }
        }
    }

    public void startFragment(AppContainer appContainer) {
        //String fragmentId = appContainer.getFragment().getId();
        //int container = appContainer.getId();
        //startFragment(container, fragmentId);
        startFragmentAndUpdateState(appContainer, appContainer.getFragment());
    }

    private void startFragmentAndUpdateState(AppContainer appContainer, AppFragment appFragment) {
        if (appContainer.getFragment(appFragment.getId()) != null) {

            start(
                    appContainer.getId(),
                    appFragment.getId()
            );

            appContainer.setCurrentFragment(appFragment);

        } else {
            Dev.em(sender, "Fragment does not belong to this container");
        }

        //store.appState.log();
    }

    private void start(int container, String fragmentId) {

        String cm = "null";
        // For debugging
        for (AppContainer appContainer : store.appState.getActiveContainers()) {
            if (appContainer.getId() == container) {
                cm = appContainer.name;
            }
        }

        Dev.m(sender, "START Fragment "
                + fragmentId
                + " to container "
                + cm
        );

        if (activityReference == null) {
            return;
        }

        AppCompatActivity activity = activityReference.get();
        if (activity == null) {
            return;
        }

        if (activity.findViewById(container) == null) {
            Dev.em(sender, "No container in layout");
            return;
        }

        fragmentBackStack.addToBackStack(container, fragmentId);

        // Get fragment in FrameLayout (fragment_container)
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(container);

        currentFragment = fragmentId;

        // If no fragment id to follow
        if (fragmentId == null) {
            Dev.em(sender, "newFragment is null");
            return;
        }

        //Dev.m(sender, "Start Fragment: " + newFragment);

        // Begin the transaction
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();



        Dev.m(sender, fragmentBackStack.toString());

        /**
         * BUILD Fragment
         */
        BaseFragment builtFragment = fragmentBuilder.getFragment(fragmentId);
        injectFragment(builtFragment);
        /*        new BaseFragment.Builder(fragmentId)
                        .withStore(store)
                        .withEventRouter(eventRouter)
                        .build();*/

        ft.replace(container, builtFragment);

        try {
            ft.commit();
        } catch (Exception e) {
            Dev.em(sender, "ft.commit " + e.toString());
            fragmentBackStack.removeLastFromBackStack();
        }


        // remove current fragment
        if(fragment != null) {
            //Log.d("FRAGMENT","fragment container not null. Remove");
            activity.getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public void injectFragment(BaseFragment fragment) {
        Dev.m(sender, "inject router and store to fragment");
        fragment.setEventRouter(eventRouter);
        fragment.setStore(store);
    }

    public boolean backPressAction() {
        String goTo = fragmentBackStack.onBackPressed();

        if (goTo != null) {
            startFragment(goTo);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<String> getTypes() {
        ArrayList<String> types = new ArrayList<>();
        types.add(EVENT_FRAGMENT);
        return types;
    }

    @Override
    public void onEvent(Event event) {
        Event<String> fragmentEvent = (Event<String>) event;
        Dev.m(sender, "EVENT "
                + event.type
                + ", payload: "
                + event.payload);
        startFragment(fragmentEvent.getPayload());
    }
}
