package com.example.oppari2.frame.store.types.app;

import com.example.oppari2.frame.development.Dev;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AppContainer {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("active")
    @Expose
    private boolean active = false;

    @SerializedName("currentFragment")
    @Expose
    private AppFragment currentFragment;

    @SerializedName("defaultFragment")
    @Expose
    private AppFragment defaultFragment;

    @SerializedName("appFragments")
    @Expose
    private ArrayList<AppFragment> appFragments;

    public AppContainer() {
        appFragments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppFragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(AppFragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public ArrayList<AppFragment> getAppFragments() {
        return appFragments;
    }

    public void setAppFragments(ArrayList<AppFragment> appFragments) {
        this.appFragments = appFragments;
    }

    public void addAppFragment(AppFragment appFragment) {
        appFragments.add(appFragment);
    }

    public AppFragment addAppFragment(String fragmentId) {
        AppFragment appFragment = new AppFragment();
        appFragment.setId(fragmentId);
        appFragments.add(appFragment);
        return appFragment;
    }

    public void addAppFragment(String fragmentId, boolean setDefault) {
        if (setDefault) {
            defaultFragment = addAppFragment(fragmentId);
        } else {
            addAppFragment(fragmentId);
        }

    }

    public AppFragment getDefaultFragment() {
        return defaultFragment;
    }

    public void setDefaultFragment(AppFragment defaultFragment) {
        this.defaultFragment = defaultFragment;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AppFragment getFragment() {
        if (getCurrentFragment() == null) {
            return getDefaultFragment();
        } else {
            return getCurrentFragment();
        }
    }

    public AppFragment getFragment(String fragmentId) {
        AppFragment result = null;

        for (AppFragment appFragment : appFragments) {
            if (appFragment.getId().equals(fragmentId)) {
                result = appFragment;
                break;
            }
        }
        Dev.m("AppContainer", "get fragment " + String.valueOf(result));
        return result;
    }
}
