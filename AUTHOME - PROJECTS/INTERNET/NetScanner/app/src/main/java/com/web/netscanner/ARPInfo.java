package com.web.netscanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ARPInfo
{
    private ARPInfo()
    {
    }

    public static String getMACFromIPAddress(String str)
    {
        if (str == null)
        {
            return null;
        }
        Iterator it = getLinesInARPCache().iterator();
        while (it.hasNext())
        {
            String[] split = ((String) it.next()).split(" +");
            if (split.length >= 4 && str.equals(split[0]))
            {
                String str2 = split[3];
                if (str2.matches("..:..:..:..:..:.."))
                {
                    return str2;
                }
                return null;
            }
        }
        return null;
    }

    public static String getIPAddressFromMAC(String str)
    {
        if (str == null)
        {
            return null;
        }
        if (!str.matches("..:..:..:..:..:.."))
        {
            throw new IllegalArgumentException("Invalid MAC Address");
        }
        Iterator it = getLinesInARPCache().iterator();
        while (it.hasNext())
        {
            String[] split = ((String) it.next()).split(" +");
            if (split.length >= 4 && str.equals(split[3]))
            {
                return split[0];
            }
        }
        return null;
    }

    public static ArrayList<String> getAllIPAddressesInARPCache()
    {
        return new ArrayList<>(getAllIPAndMACAddressesInARPCache().keySet());
    }

    public static ArrayList<String> getAllMACAddressesInARPCache()
    {
        return new ArrayList<>(getAllIPAndMACAddressesInARPCache().values());
    }

    public static HashMap<String, String> getAllIPAndMACAddressesInARPCache()
    {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator it = getLinesInARPCache().iterator();
        while (it.hasNext())
        {
            String[] split = ((String) it.next()).split(" +");
            if (split.length >= 4 && split[3].matches("..:..:..:..:..:..") && !split[3].equals("00:00:00:00:00:00"))
            {
                hashMap.put(split[0], split[3]);
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032 A[SYNTHETIC, Splitter:B:19:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003d A[SYNTHETIC, Splitter:B:25:0x003d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static ArrayList<String> getLinesInARPCache()
    {
        ArrayList arrayList = new ArrayList();
        String system = "/proc/net/arp";
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x002c }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Exception -> 0x002c }
            java.lang.String r4 = "/proc/net/arp"
            r3.<init>(r4)     // Catch:{ Exception -> 0x002c }
            r2.<init>(r3)     // Catch:{ Exception -> 0x002c }
        L_0x0012:
            java.lang.String r1 = r2.readLine()     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            if (r1 == 0) goto L_0x001c
            r0.add(r1)     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            goto L_0x0012
        L_0x001c:
            if (r2 == 0) goto L_0x003a
            r2.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x003a
        L_0x0022:
            r0 = move-exception
            r1 = r2
            goto L_0x003b
        L_0x0025:
            r1 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x002d
        L_0x002a:
            r0 = move-exception
            goto L_0x003b
        L_0x002c:
            r2 = move-exception
        L_0x002d:
            r2.printStackTrace()     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003a:
            return r0
        L_0x003b:
            if (r1 == 0) goto L_0x0045
            r1.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0045
        L_0x0041:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0045:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stealthcopter.networktools.ARPInfo.getLinesInARPCache():java.util.ArrayList");
    }
}
