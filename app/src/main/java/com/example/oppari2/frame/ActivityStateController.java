package com.example.oppari2.frame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.oppari2.frame.canvas.BaseFragment;
import com.example.oppari2.frame.event.Event;
import com.example.oppari2.frame.event.EventListener;
import com.example.oppari2.frame.event.EventRouter;
import com.example.oppari2.frame.interfaces.ActivityChangeListener;
import com.example.oppari2.frame.interfaces.AppStatePopulator;
import com.example.oppari2.frame.interfaces.FragmentBuilder;
import com.example.oppari2.frame.store.Store;

import java.util.ArrayList;

public class ActivityStateController {

    private static final String sender = "ActivityStateController";
    private AppCompatActivity activity;
    private FragmentController fragmentController;
    private Store store;
    private EventRouter eventRouter;
    private AppStatePopulator populator;
    public CustomBackStack activityBackStack;
    private ActivityChangeListener activityChangeListener;

    public ActivityStateController(AppCompatActivity activity,
                                   AppStatePopulator populator,
                                   FragmentBuilder fragmentBuilder) {
        this.activity = activity;
        this.populator = populator;

        store = new Store(activity.getApplicationContext());

        eventRouter = new EventRouter();


        fragmentController = new FragmentController(activity, fragmentBuilder);
        fragmentController.setStore(store);
        fragmentController.setEventRouter(eventRouter);

        activityBackStack = new CustomBackStack("activityBackStack");
    }

    public void setActivityChangeListener(ActivityChangeListener activityChangeListener) {
        this.activityChangeListener = activityChangeListener;
        eventRouter.registerListener(this.activityChangeListener);
    }

    public void onActivityCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            onCreateAfterNewInstance();
        } else {
            onCreateAfterConfigurationChange(savedInstanceState);
        }
    }

    private void onCreateAfterConfigurationChange(Bundle savedInstanceState) {
        store.appState = store.getBundleStore().retrieve(savedInstanceState);
        store.getBundleStore().retrieve(fragmentController.fragmentBackStack, savedInstanceState);
    }

    private void onCreateAfterNewInstance() {
        store.initAppState(populator);
    }

    public void onActivitySaveInstanceState(Bundle savedInstanceState) {
        store.getBundleStore().save(store.appState, savedInstanceState);
        store.getBundleStore().save(fragmentController.fragmentBackStack, savedInstanceState);
    }

    public void onActivityDestroy() {
        if (activity.isFinishing()) {
            onDestroyWhenFinishing();
        } else {
            onDestroyWhenConfigurationChange();
        }
    }

    private void onDestroyWhenConfigurationChange() {

    }

    private void onDestroyWhenFinishing() {

    }

    public void onActivityStart() {
        fragmentController.compose();
    }

    public void activateContainer(int container) {
        store.appState.activateContainer(container);
    }

    public boolean onBackPressedToSuper() {
        if (fragmentController.backPressAction()) {
            return false;
        } else {
            return true;
        }
    }

    public FragmentController getFragmentController() {
        return fragmentController;
    }

    public void registerActivityChangeListener(ActivityChangeListener activityChangeListener) {
        eventRouter.registerListener(activityChangeListener);
    }
}
