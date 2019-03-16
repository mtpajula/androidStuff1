package com.example.oppari2.frame.store.types.backstack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BackStep implements Serializable {

    @SerializedName("container")
    @Expose
    private int container;

    @SerializedName("fragment")
    @Expose
    private String fragment;

    @SerializedName("activity")
    @Expose
    private String activity;

    public int getContainer() {
        return container;
    }

    public void setContainer(int container) {
        this.container = container;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
