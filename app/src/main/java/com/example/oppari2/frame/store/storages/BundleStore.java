package com.example.oppari2.frame.store.storages;

import android.os.Bundle;

import com.example.oppari2.frame.CustomBackStack;
import com.example.oppari2.frame.store.types.app.AppState;
import com.example.oppari2.frame.store.types.backstack.BackStep;
import com.google.gson.Gson;

import java.util.ArrayList;

public class BundleStore {

    private Gson gson;

    public BundleStore(Gson gson) {
        this.gson = gson;
    }

    public void save(AppState appState, Bundle savedInstanceState) {
        savedInstanceState.putString("appState", gson.toJson(appState));
    }

    public AppState retrieve(Bundle savedInstanceState) {
        return gson.fromJson(savedInstanceState.getString("appState"),
                AppState.class);
    }

    public void save(CustomBackStack backStack, Bundle savedInstanceState) {
        savedInstanceState.putSerializable(backStack.key, backStack.getBackStack());
    }

    public void retrieve(CustomBackStack backStack, Bundle savedInstanceState) {
        backStack.setBackStack(
                (ArrayList<BackStep>) savedInstanceState.getSerializable(backStack.key)
        );
    }
}
