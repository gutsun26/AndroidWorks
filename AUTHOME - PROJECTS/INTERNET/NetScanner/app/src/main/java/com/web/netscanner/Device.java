package com.web.netscanner;

import java.net.InetAddress;

public class Device {
    public String hostname;

    /* renamed from: ip */
    public String f44ip;
    public String mac;
    public float time = 0.0f;

    public Device(InetAddress inetAddress) {
        this.f44ip = inetAddress.getHostAddress();
        this.hostname = inetAddress.getCanonicalHostName();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Device{ip='");
        sb.append(this.f44ip);
        sb.append('\'');
        sb.append(", hostname='");
        sb.append(this.hostname);
        sb.append('\'');
        sb.append(", mac='");
        sb.append(this.mac);
        sb.append('\'');
        sb.append(", time=");
        sb.append(this.time);
        sb.append('}');
        return sb.toString();
    }
}
