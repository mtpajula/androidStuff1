package com.example.oppari2.frame.store;

import android.content.Context;

import com.example.oppari2.frame.interfaces.AppStatePopulator;
import com.example.oppari2.frame.store.storages.BundleStore;
import com.example.oppari2.frame.store.storages.SharedPerfsStorage;
import com.example.oppari2.frame.store.types.app.AppState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Store {

    public AppState appState;
    private BundleStore bundleStore;
    private Gson gson;

    private SharedPerfsStorage sharedPerfsStorage;

    public Store(Context context) {
        gson = new GsonBuilder().create();
        bundleStore = new BundleStore(gson);
        sharedPerfsStorage = new SharedPerfsStorage(context);
    }

    // Populate fragment containers and their fragments
    public void initAppState(AppStatePopulator populator) {
        appState = populator.getAppState();
    }

    public BundleStore getBundleStore() {
        return bundleStore;
    }

    public Gson getGson() {
        return gson;
    }

    public void save(String key, String value, StoreSaveListener listener) {
        sharedPerfsStorage.save(key, value, listener);
    }

    public void retrieve(String key, StoreRetrieveListener listener) {
        sharedPerfsStorage.retrieve(key, listener);
    }
}
