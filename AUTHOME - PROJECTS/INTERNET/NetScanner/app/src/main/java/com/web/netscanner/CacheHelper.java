package com.web.netscanner;

import android.content.Context;

public class CacheHelper {
    private Cacher hostNameCache;
    private Cacher ipAddressCache;
    private final Cacher macAddressCache;

    public CacheHelper(Context context) {
        this.ipAddressCache = new Cacher(context, 300, "IP_ADDRESSES.txt");
        this.macAddressCache = new Cacher(context, 300, "MAC_ADDRESSES.txt");
        this.hostNameCache = new Cacher(context, 300, "HOSTNAMES.txt");
    }

    public Cacher getIpAddressCache() {
        return this.ipAddressCache;
    }

    public Cacher getMacAddressCache() {
        return this.macAddressCache;
    }

    public Cacher getHostNameCache() {
        return this.hostNameCache;
    }
}