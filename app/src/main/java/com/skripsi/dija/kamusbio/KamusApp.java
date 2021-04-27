package com.skripsi.dija.kamusbio;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


public class KamusApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
