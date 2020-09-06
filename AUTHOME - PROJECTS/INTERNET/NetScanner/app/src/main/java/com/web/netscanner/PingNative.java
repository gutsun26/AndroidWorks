package com.web.netscanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class PingNative {
    private PingNative() {
    }

    public static PingResult ping(InetAddress inetAddress, PingOptions pingOptions) throws IOException, InterruptedException {
        String str;
        PingResult pingResult = new PingResult(inetAddress);
        if (inetAddress == null) {
            pingResult.isReachable = false;
            return pingResult;
        }
        StringBuilder sb = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        int max = Math.max(pingOptions.getTimeoutMillis() / 1000, 1);
        int max2 = Math.max(pingOptions.getTimeToLive(), 1);
        String hostAddress = inetAddress.getHostAddress();
        String str2 = "ping";
        if (hostAddress == null) {
            hostAddress = inetAddress.getHostName();
        } else if (IPTools.isIPv6Address(hostAddress)) {
            str2 = "ping6";
        } else {
            IPTools.isIPv4Address(hostAddress);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(" -c 1 -W ");
        sb2.append(max);
        sb2.append(" -t ");
        sb2.append(max2);
        sb2.append(" ");
        sb2.append(hostAddress);
        Process exec = runtime.exec(sb2.toString());
        exec.waitFor();
        switch (exec.exitValue()) {
            case 0:
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return getPingStats(pingResult, sb.toString());
                    }
                    sb.append(readLine);
                    sb.append("\n");
                }
            case 1:
                str = "failed, exit = 1";
                break;
            default:
                str = "error, exit = 2";
                break;
        }
        pingResult.error = str;
        return pingResult;
    }

    public static PingResult getPingStats(PingResult pingResult, String str) {
        String str2;
        if (str.contains("0% packet loss")) {
            int indexOf = str.indexOf("/mdev = ");
            int indexOf2 = str.indexOf(" ms\n", indexOf);
            pingResult.fullString = str;
            if (indexOf == -1 || indexOf2 == -1) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error: ");
                sb.append(str);
                str2 = sb.toString();
            } else {
                String substring = str.substring(indexOf + 8, indexOf2);
                String[] split = substring.split("/");
                pingResult.isReachable = true;
                pingResult.result = substring;
                pingResult.timeTaken = Float.parseFloat(split[1]);
                return pingResult;
            }
        } else {
            str2 = str.contains("100% packet loss") ? "100% packet loss" : str.contains("% packet loss") ? "partial packet loss" : str.contains("unknown host") ? "unknown host" : "unknown error in getPingStats";
        }
        pingResult.error = str2;
        return pingResult;
    }
}
