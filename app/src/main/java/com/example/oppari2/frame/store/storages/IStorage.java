package com.example.oppari2.frame.store.storages;

import com.example.oppari2.frame.store.StoreRetrieveListener;
import com.example.oppari2.frame.store.StoreSaveListener;

public interface IStorage {
    void save(String key, String value, StoreSaveListener listener);
    void retrieve(String key, StoreRetrieveListener listener);
}
