package com.example.oppari2.frame.store;

public interface StoreRetrieveListener {
    void onRetrieveSuccess(String id, String value);
    void onRetrieveFailed(String id, String error);
}
