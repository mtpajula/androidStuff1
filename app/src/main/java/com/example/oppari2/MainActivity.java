package com.example.oppari2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.oppari2.frame.ActivityStateController;
import com.example.oppari2.frame.event.Event;
import com.example.oppari2.frame.interfaces.ActivityChangeListener;
import com.example.oppari2.oppari.main.OppariAppStatePopulator;
import com.example.oppari2.oppari.main.OppariFragmentBuilder;

public class MainActivity extends AppCompatActivity {

    private ActivityStateController stateController;
    private ActivityChangeListener activityChangeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityChangeListener = new ActivityChangeListener(this) {
            @Override
            public Intent getActivityChangeIntent(Event<String> event) {
                return new Intent(getActivity(), MapsActivity.class);
            }
        };

        stateController = new ActivityStateController(this,
                new OppariAppStatePopulator(),
                new OppariFragmentBuilder());
        stateController.onActivityCreate(savedInstanceState);
        stateController.activateContainer(R.id.fragment_container_1);
        stateController.registerActivityChangeListener(activityChangeListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        stateController.onActivityStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stateController.onActivityDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        stateController.onActivitySaveInstanceState(bundle);
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onBackPressed() {
        if (stateController.onBackPressedToSuper()) {
            super.onBackPressed();
        }
    }

    public ActivityStateController getStateController() {
        return stateController;
    }
}
