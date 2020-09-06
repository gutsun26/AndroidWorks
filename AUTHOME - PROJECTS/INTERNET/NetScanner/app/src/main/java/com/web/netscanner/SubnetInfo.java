package com.web.netscanner;

import java.net.InetAddress;

public class SubnetInfo
{
    public String hostname;
    public int hostnameImage;

    /* renamed from: ip */
    public String f51ip;
    public String mac = "";
    public String macAddressInfo = null;
    public float time = 0.0f;

    public SubnetInfo(InetAddress inetAddress)
    {
        this.f51ip = inetAddress.getHostAddress();
        this.hostname = inetAddress.getCanonicalHostName();
        //this.hostnameImage = Info.getDeviceImageByHostname(this.hostname);
    }

    public SubnetInfo(Device device)
    {
        this.f51ip = device.f44ip;
        this.hostname = device.hostname;
        //this.hostnameImage = Info.getDeviceImageByHostname(this.hostname);
        this.time = device.time;
        this.mac = device.mac;
    }
}