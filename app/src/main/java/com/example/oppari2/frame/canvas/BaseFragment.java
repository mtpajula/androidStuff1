package com.example.oppari2.frame.canvas;

import android.support.v4.app.Fragment;

import com.example.oppari2.frame.event.EventRouter;
import com.example.oppari2.frame.store.Store;

public abstract class BaseFragment extends Fragment {

    private Store store;
    private EventRouter eventRouter;
    protected Integer layoutId;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(Integer layoutId) {
        this.layoutId = layoutId;
    }

    public EventRouter getEventRouter() {
        return eventRouter;
    }

    public void setEventRouter(EventRouter eventRouter) {
        this.eventRouter = eventRouter;
    }
}
