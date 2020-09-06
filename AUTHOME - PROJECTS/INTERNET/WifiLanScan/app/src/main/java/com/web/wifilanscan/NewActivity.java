package com.web.wifilanscan;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.database.Observable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NewActivity extends AppCompatActivity
{
    WifiManager wifiManager;
    String SSID;
    BroadcastReceiver broadcastreceiver;
    boolean connected;
    public Context context;
    int count = 0;
    String password;
    ListAdapter listAdapter;
    ListView listView;
    ArrayList arrayList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.new_activity);
        listView = findViewById(R.id.wifiList);
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        arrayList.clear();
        listView.setAdapter(listAdapter);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        {
            WifiConnectionHandler wifiConnectionHandler = new WifiConnectionHandler(getApplicationContext());
            if (wifiManager.isWifiEnabled())
            {
                wifiManager.startScan();
                List<ScanResult> results = wifiManager.getScanResults();

                for (ScanResult scanResult : results)
                {
                        arrayList.add(scanResult.SSID);
                        listAdapter.notifyAll();
                }
            }
            /****************************************METHOD 1****************************************************/
            /*
            WifiNetworkSpecifier.Builder builder = new WifiNetworkSpecifier.Builder();
            //builder.setSsid("wifi-ap-ssid");
            //builder.setWpa2Passphrase("wifi-ap-password");

            WifiNetworkSpecifier wifiNetworkSpecifier = builder.build();

            NetworkRequest.Builder networkRequestBuilder1 = new NetworkRequest.Builder();
            networkRequestBuilder1.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
            networkRequestBuilder1.setNetworkSpecifier(wifiNetworkSpecifier);

            NetworkRequest nr = networkRequestBuilder1.build();
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            /******************************************METHOD 2****************************************************/
            /*
            WifiNetworkSpecifier.Builder builder = new WifiNetworkSpecifier.Builder();
            builder.setSsid("wifi-ap-ssid");
            builder.setWpa2Passphrase("wifi-ap-password");

            WifiNetworkSpecifier wifiNetworkSpecifier = builder.build();

            NetworkRequest.Builder networkRequestBuilder = new NetworkRequest.Builder();
            networkRequestBuilder.addTransportType(NetworkCapabilities.TRANSPORT_WIFI);
            networkRequestBuilder.setNetworkSpecifier(wifiNetworkSpecifier);

            NetworkRequest networkRequest = networkRequestBuilder.build();
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            networkCallback = new ConnectivityManager.NetworkCallback()
            {
                @Override
                public void onAvailable(@NonNull Network network) {
                    //Use this network object to Send request.
                    //eg - Using OkHttp library to create a service request
                    //Service is an OkHttp interface where we define docs. Please read OkHttp docs
                    Service service = null;

                    OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
                    okHttpBuilder.socketFactory(network.getSocketFactory());

                    service = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(okHttpBuilder.build())
                            .build()
                            .create(Service.class);


                    Observable<Object> observable = null;
                    try {
                        if (service != null) {
                            observable = service.yourRestCall();
                        }
                        Subscriber<Object> sub = new Subscriber< Object >() {
                            @Override
                            public void onError(Throwable e) {
                                //Do on error
                            }

                            @Override
                            public void onNext(Object logs) {
                                //Do on next
                            }
                        };
                        if(observable != null) {
                            observable.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread()).subscribe(sub);
                        }

                        super.onAvailable(network);
                    }
                };
            cm.requestNetwork(networkRequest, networkCallback);*/
        }
    }//end of onCreate()

    class WifiConnectionHandler
    {

        public WifiConnectionHandler(Context context2)
        {
            wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        }
        public void startScan()
        {
            wifiManager.startScan();
        }
        public int addNetwork(WifiConfiguration wifiConfiguration)
        {
            return wifiManager.addNetwork(wifiConfiguration);
        }
        public boolean enableNetwork(int i, boolean z)
        {
            return wifiManager.enableNetwork(i, z);
        }

        public List getScanResults()
        {
            return wifiManager.getScanResults();
        }

        public boolean isWifiEnabled()
        {
            return wifiManager.isWifiEnabled();
        }

        public String wifiConnected()
        {
            return wifiManager.getConnectionInfo().getSSID();
        }
        public void disableWifi()
        {
            wifiManager.disconnect();
        }
        public boolean isWifiAvailable(String str)
        {
            List scanResults = getScanResults();
            for (int i = 0; i < scanResults.size(); i++)
            {
                if (str.equals(((ScanResult) scanResults.get(i)).SSID))
                {
                    return true;
                }
            }
            return false;
        }
    }
}
