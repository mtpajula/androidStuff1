package com.example.oppari2.frame.store.storages;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.oppari2.frame.store.StoreRetrieveListener;
import com.example.oppari2.frame.store.StoreSaveListener;

public class SharedPerfsStorage {

    private SharedPreferences prefs;

    public SharedPerfsStorage(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void save(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String retrieve(String key) {
        return prefs.getString(key, null);
    }

    public void save(String key, String value, StoreSaveListener listener) {
        try {
            save(key, value);
            listener.onSaveSuccess(key);
        } catch (Exception e) {
            listener.onSaveFailed(key, e.toString());
        }
    }

    public void retrieve(String key, StoreRetrieveListener listener) {
        try {
            String value = retrieve(key);
            listener.onRetrieveSuccess(key, value);
        } catch (Exception e) {
            listener.onRetrieveFailed(key, e.toString());
        }
    }
}
