package com.web.netscanner;

import android.app.Application;
public class App extends Application
{
    private static App instance = null;
    private static boolean proVersion = false;
    private CacheHelper cacheHelper;

    public void onCreate() {
        instance = this;
        super.onCreate();
        getCacheHelper();
    }
    public CacheHelper getCacheHelper() {
        if (this.cacheHelper == null) {
            this.cacheHelper = new CacheHelper(this);
        }
        return this.cacheHelper;
    }

    public static App get() {
        return instance;
    }

    public void setProVersion(boolean z) {
        proVersion = z;
    }
}
