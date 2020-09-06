package com.web.netscanner;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;

import java.util.ArrayList;
import java.util.List;

public class Settings
{
    private static final String DEFAULT_PORT_STRING = "My New Scan:20-30,45,99-102";
    private static final long DELAY_ASK_REVIEW_MILLIS = 432000000;
    private static String PREF_FIRST_RUN = "FIRST_RUN";
    private static String PREF_RUN_COUNT = "RUN_COUNT";
    private static String PREF_SHOWN_RATING_DIALOG = "SHOWN_RATING_DIALOG";
    private static String PREF_WOL_DEVICES = "WOL_DEVICES";
    private static final int maxCacheNo = 5000;

    private static SharedPreferences getPrefs()
    {
        return PreferenceManager.getDefaultSharedPreferences(App.get());
    }
    public static boolean getHideDevice() {
        return getPrefs().getBoolean("SUBNET_DEVICES_HIDE_DEVICE", false);
    }
    public static boolean getHideGateway() {
        return getPrefs().getBoolean("SUBNET_DEVICES_HIDE_GATEWAY", false);
    }
    /*
    public static String getWhoIsUrl() {
        return getPrefs().getString("WHOIS_URL", "https://who.is/whois/");
    }

    public static String getExternalIPUrl() {
        return getPrefs().getString("URL_EXTERNAL_IP", "http://ipv4.icanhazip.com");
    }

    public static String getExternalIPv6Url() {
        return getPrefs().getString("URL_EXTERNAL_IPv6", "http://ipv6.icanhazip.com");
    }

    public static boolean getDoReachabilityCheck() {
        return getPrefs().getBoolean("REACHABILITY_CHECK", true);
    }

    public static boolean getMapGeodesic() {
        return getPrefs().getBoolean("MAP_GEODESIC", true);
    }

    public static boolean getDoTracePing() {
        return getPrefs().getBoolean("TRACE_PING", true);
    }

    public static boolean getDoHTTPScan() {
        return getPrefs().getBoolean("HTTP_SCAN", true);
    }




    public static boolean getFindHTTPIcon() {
        return getPrefs().getBoolean("HTTP_ICON", true);
    }

    public static boolean getShownNativePingWarning() {
        return getPrefs().getBoolean("NATIVE_PING_WARNING", false);
    }

    public static void setShownNativePingWarning() {
        getPrefs().edit().putBoolean("NATIVE_PING_WARNING", true).apply();
    }

    public static boolean getShownNavDraw() {
        return getPrefs().getBoolean("SHOWN_NAV_DRAW", false);
    }

    public static void setShownNavDraw() {
        getPrefs().edit().putBoolean("SHOWN_NAV_DRAW", true).apply();
    }

    public static int getReachabilityTimeout() {
        return safeParseInt(getPrefs().getString("REACHABILITY_TIMEOUT", "3000"), PathInterpolatorCompat.MAX_NUM_POINTS);
    }

    public static int getTraceTimeoutSeconds() {
        return safeParseInt(getPrefs().getString("TRACE_TIMEOUT", "5"), 5, 0, 15);
    }

    public static int getDelayBetweenPings() {
        return safeParseInt(getPrefs().getString("PING_DELAY", "0"), 0);
    }

    public static int getGoodPing() {
        return safeParseInt(getPrefs().getString("PING_GOOD_MILLIS", "150"), FTPReply.FILE_STATUS_OK);
    }

    public static int getBadPing() {
        return safeParseInt(getPrefs().getString("PING_BAD_MILLIS", "300"), 300);
    }

    public static int getNoPings() {
        return safeParseInt(getPrefs().getString("NO_PINGS", "10"), 10, 1, -1);
    }

    public static int getNoWakeOnLanPacketsToSend() {
        return safeParseInt(getPrefs().getString("NO_WOL_PACKETS", "5"), 5, 1, -1);
    }

    public static void setNoWakeOnLanPacketsToSend(int i) {
        getPrefs().edit().putString("NO_WOL_PACKETS", String.valueOf(i)).apply();
    }

    public static int getHTTPScanTimeout() {
        return safeParseInt(getPrefs().getString("HTTP_SCAN_TIMEOUT", "2500"), 2500);
    }

    public static boolean getProVersion() {
        return getPrefs().getBoolean("PRO_VERSION", false);
    }

    public static void setProVersion(boolean z) {
        getPrefs().edit().putBoolean("PRO_VERSION", z).apply();
    }

    public static boolean getDoMACAddressLookUp() {
        return getPrefs().getBoolean("MAC_ADDRESS_LOOKUP", true);
    }

    public static int safeParseInt(String str, int i) {
        return safeParseInt(str, i, 0, -1);
    }

    public static int safeParseInt(String str, int i, int i2, int i3) {
        int i4;
        try {
            i4 = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            i4 = i;
        }
        if (i4 < i2) {
            i4 = i2;
        }
        return (i3 == -1 || i4 <= i3) ? i4 : i3;
    }

    public static void setPingTextColor(TextView textView, float f) {
        textView.setTextColor(getPingTextColor(f));
        if (f == 0.0f) {
            textView.setText("N/A");
            return;
        }
        textView.setText(String.format("%.1fms", new Object[]{Float.valueOf(f)}));
    }

    public static int getPingMethod() {
        int parseInt = Integer.parseInt(getPrefs().getString("PING_METHOD", "1"));
        if (!Info.isNativePingAvaliable()) {
            return 0;
        }
        return parseInt;
    }

    public static float getFontSize() {
        String string = getPrefs().getString("FONT_SIZE", "Normal");
        float dimension = App.get().getResources().getDimension(C0708R.dimen.fontsize_medium);
        if (string.equals("Large")) {
            return App.get().getResources().getDimension(C0708R.dimen.fontsize_large);
        }
        return string.equals("Small") ? App.get().getResources().getDimension(C0708R.dimen.fontsize_small) : dimension;
    }

    public static int getNextFreePortStringSlot() {
        for (int i = 0; i < 10000; i++) {
            if (getPortString(i) == null) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<String> getPortStrings() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            String portString = getPortString(i);
            if (portString == null) {
                break;
            }
            arrayList.add(portString);
        }
        if (arrayList.size() != 0) {
            return arrayList;
        }
        addPortString("Quick Scan:21-23,25,45,53,80,110,111,113,135,139,143,199,256,443,445,554,587,912,993,995,1720,1723,3306,3389,5900,8080,8888,10257");
        addPortString("Common HTTP:80,443,3124,3128,5800,7000,8008,8080,9080,9443,11371,12443,16080");
        addPortString("Microsoft:123,135,137,138,139,143,445,500,593,1025,1433,1434,1900,3372,3398,5000");
        addPortString("SQL:1433,1434,3306,4333,5432,6432,7306,7307,9001,25565");
        addPortString("Remote Desktop:3283,3389,5500,5800,5900");
        addPortString("Privileged Ports:1-1024");
        addPortString("Full Scan:1-65535");
        return getPortStrings();
    }

    public static String getPortString(int i) {
        SharedPreferences prefs = getPrefs();
        StringBuilder sb = new StringBuilder();
        sb.append("PORT_STRING:");
        sb.append(i);
        return prefs.getString(sb.toString(), null);
    }

    public static int addPortString(String str) {
        int nextFreePortStringSlot = getNextFreePortStringSlot();
        if (nextFreePortStringSlot == -1) {
            throw new IllegalArgumentException("Position is -1, all slots filled");
        }
        setPortString(nextFreePortStringSlot, str);
        return nextFreePortStringSlot;
    }

    public static void setPortString(int i, String str) {
        SharedPreferences.Editor edit = getPrefs().edit();
        StringBuilder sb = new StringBuilder();
        sb.append("PORT_STRING:");
        sb.append(i);
        edit.putString(sb.toString(), str).apply();
    }

    @Deprecated
    private static String getHostnameString(int i) {
        SharedPreferences prefs = getPrefs();
        StringBuilder sb = new StringBuilder();
        sb.append("AUTO_HOSTNAME:");
        sb.append(i);
        return prefs.getString(sb.toString(), null);
    }

    @Deprecated
    private static int getNextFreeHostnameStringSlot() {
        for (int i = 0; i < 5000; i++) {
            if (getHostnameString(i) == null) {
                return i;
            }
        }
        return -1;
    }

    public static void removeAllHostnames() {
        int i = 0;
        while (i <= getNextFreeHostnameStringSlot() && getHostnameString(i) != null) {
            SharedPreferences.Editor edit = getPrefs().edit();
            StringBuilder sb = new StringBuilder();
            sb.append("AUTO_HOSTNAME:");
            sb.append(i);
            edit.remove(sb.toString()).apply();
            i++;
        }
    }

    @Deprecated
    public static String getMacaddressString(int i) {
        SharedPreferences prefs = getPrefs();
        StringBuilder sb = new StringBuilder();
        sb.append("AUTO_MACADDRESS:");
        sb.append(i);
        return prefs.getString(sb.toString(), null);
    }

    @Deprecated
    public static int getNextFreeMacaddressStringSlot() {
        for (int i = 0; i < 5000; i++) {
            if (getMacaddressString(i) == null) {
                return i;
            }
        }
        return -1;
    }

    public static void removeAllMacaddresss() {
        int i = 0;
        while (i <= getNextFreeMacaddressStringSlot() && getMacaddressString(i) != null) {
            SharedPreferences.Editor edit = getPrefs().edit();
            StringBuilder sb = new StringBuilder();
            sb.append("AUTO_MACADDRESS:");
            sb.append(i);
            edit.remove(sb.toString()).apply();
            i++;
        }
    }

    public static void removeAll() {
        getPrefs().edit().clear().apply();
    }

    public static void showAddPortDialog(Context context, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String str = DEFAULT_PORT_STRING;
        if (i == -1) {
            builder.setTitle("Add Scan");
        } else {
            builder.setTitle("Edit Scan");
            str = getPortString(i);
        }
        int indexOf = str.indexOf(":");
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1, str.length());
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C0708R.layout.dialog_custom_ports, null);
        builder.setView(inflate);
        final EditText editText = (EditText) inflate.findViewById(C0708R.C0710id.scanTitle);
        final EditText editText2 = (EditText) inflate.findViewById(C0708R.C0710id.scanPorts);
        final Button button = (Button) inflate.findViewById(C0708R.C0710id.buttonAddScan);
        button.setText(i == -1 ? "Add Scan" : "Update");
        editText.setText(substring);
        editText2.setText(substring2);
        editText2.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                try {
                    Utils.parsePorts(editable.toString());
                    button.setEnabled(true);
                } catch (Exception unused) {
                    button.setEnabled(false);
                }
            }
        });
        final AlertDialog show = builder.show();
        final int i2 = i;
        final Context context2 = context;
        C07132 r6 = new View.OnClickListener() {
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                sb.append(editText.getText());
                sb.append(":");
                sb.append(editText2.getText());
                String sb2 = sb.toString();
                int i = i2;
                if (i2 == -1) {
                    i = Settings.addPortString(sb2);
                } else {
                    Settings.setPortString(i, sb2);
                }
                if (context2 instanceof SettingsPortScanningActivity) {
                    ((SettingsPortScanningActivity) context2).updatePortList(i, sb2);
                } else if (context2 instanceof PortScannerActivity) {
                    ((PortScannerActivity) context2).updateSpinnerView(i);
                } else {
                    ((BaseActivity) context2).toastMessage("Where am I? I shouldn't be here!");
                }
                show.dismiss();
            }
        };
        button.setOnClickListener(r6);
    }

    public static void increaseRunCount() {
        getPrefs().edit().putInt(PREF_RUN_COUNT, getRunCount() + 1).apply();
    }

    private static int getRunCount() {
        return getPrefs().getInt(PREF_RUN_COUNT, 0);
    }

    private static long getFirstRunMillis() {
        long j = getPrefs().getLong(PREF_FIRST_RUN, 0);
        if (j == 0) {
            getPrefs().edit().putLong(PREF_FIRST_RUN, System.currentTimeMillis()).apply();
        }
        return j == 0 ? System.currentTimeMillis() : j;
    }

    public static boolean shouldShowRatingDialog() {
        return !shownRatingDialog() && System.currentTimeMillis() - getFirstRunMillis() > DELAY_ASK_REVIEW_MILLIS && getRunCount() > 10;
    }

    private static boolean shownRatingDialog() {
        return getPrefs().getBoolean(PREF_SHOWN_RATING_DIALOG, false);
    }

    public static void setShownRatingDialog() {
        getPrefs().edit().putBoolean(PREF_SHOWN_RATING_DIALOG, true).apply();
    }

    public static ArrayList<WakeOnLanDevice> getSavedWakeOnLanDevices() {
        ArrayList<WakeOnLanDevice> arrayList = new ArrayList<>();
        String string = getPrefs().getString(PREF_WOL_DEVICES, null);
        if (TextUtils.isEmpty(string)) {
            return arrayList;
        }
        arrayList.addAll((List) new Gson().fromJson(string, new TypeToken<List<WakeOnLanDevice>>() {
        }.getType()));
        return arrayList;
    }

    public static boolean addWakeOnLanDevice(WakeOnLanDevice wakeOnLanDevice) {
        ArrayList savedWakeOnLanDevices = getSavedWakeOnLanDevices();
        if (savedWakeOnLanDevices.contains(wakeOnLanDevice)) {
            Timber.m77d("We already have this in the list", new Object[0]);
            return false;
        }
        savedWakeOnLanDevices.add(wakeOnLanDevice);
        Gson gson = new Gson();
        Timber.m77d("Json String: %s", gson.toJson((Object) savedWakeOnLanDevices));
        getPrefs().edit().putString(PREF_WOL_DEVICES, gson.toJson((Object) savedWakeOnLanDevices)).apply();
        return true;
    }

    public static void removeWakeOnLanDevice(WakeOnLanDevice wakeOnLanDevice) {
        ArrayList savedWakeOnLanDevices = getSavedWakeOnLanDevices();
        savedWakeOnLanDevices.remove(wakeOnLanDevice);
        getPrefs().edit().putString(PREF_WOL_DEVICES, new Gson().toJson((Object) savedWakeOnLanDevices)).apply();
    }

     */
}
