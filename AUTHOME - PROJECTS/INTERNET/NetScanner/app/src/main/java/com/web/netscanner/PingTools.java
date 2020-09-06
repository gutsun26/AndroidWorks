package com.web.netscanner;

import java.io.IOException;
import java.net.InetAddress;

public class PingTools {
    private PingTools() {
    }

    public static PingResult doPing(InetAddress inetAddress, PingOptions pingOptions) {
        try {
            return doNativePing(inetAddress, pingOptions);
        } catch (InterruptedException unused) {
            PingResult pingResult = new PingResult(inetAddress);
            pingResult.isReachable = false;
            pingResult.error = "Interrupted";
            return pingResult;
        } catch (Exception unused2) {
            return doJavaPing(inetAddress, pingOptions);
        }
    }

    public static PingResult doNativePing(InetAddress inetAddress, PingOptions pingOptions) throws IOException, InterruptedException {
        return PingNative.ping(inetAddress, pingOptions);
    }

    public static PingResult doJavaPing(InetAddress inetAddress, PingOptions pingOptions) {
        PingResult pingResult = new PingResult(inetAddress);
        if (inetAddress == null) {
            pingResult.isReachable = false;
            return pingResult;
        }
        try {
            long nanoTime = System.nanoTime();
            boolean isReachable = inetAddress.isReachable(null, pingOptions.getTimeToLive(), pingOptions.getTimeoutMillis());
            pingResult.timeTaken = ((float) (System.nanoTime() - nanoTime)) / 1000000.0f;
            pingResult.isReachable = isReachable;
            if (!isReachable) {
                pingResult.error = "Timed Out";
            }
        } catch (IOException e) {
            pingResult.isReachable = false;
            StringBuilder sb = new StringBuilder();
            sb.append("IOException: ");
            sb.append(e.getMessage());
            pingResult.error = sb.toString();
        }
        return pingResult;
    }
}
