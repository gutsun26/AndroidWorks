package com.web.netscanner;
import java.net.InetAddress;

public class PingResult {
    public String error = null;
    public String fullString;

    /* renamed from: ia */
    public final InetAddress f42ia;
    public boolean isReachable;
    public String result;
    public float timeTaken;

    public PingResult(InetAddress inetAddress) {
        this.f42ia = inetAddress;
    }

    public boolean isReachable() {
        return this.isReachable;
    }

    public boolean hasError() {
        return this.error != null;
    }

    public float getTimeTaken() {
        return this.timeTaken;
    }

    public String getError() {
        return this.error;
    }

    public InetAddress getAddress() {
        return this.f42ia;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PingResult{ia=");
        sb.append(this.f42ia);
        sb.append(", isReachable=");
        sb.append(this.isReachable);
        sb.append(", error='");
        sb.append(this.error);
        sb.append('\'');
        sb.append(", timeTaken=");
        sb.append(this.timeTaken);
        sb.append(", fullString='");
        sb.append(this.fullString);
        sb.append('\'');
        sb.append(", result='");
        sb.append(this.result);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }
}
