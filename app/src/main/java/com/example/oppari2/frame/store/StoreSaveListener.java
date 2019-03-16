package com.example.oppari2.frame.store;

public interface StoreSaveListener {
    void onSaveSuccess(String id);
    void onSaveFailed(String id, String error);
}
