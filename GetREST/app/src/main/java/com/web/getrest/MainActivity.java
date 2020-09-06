package com.web.getrest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity
{
    TextView mess;
    //EditText devID;
    RadioButton devID;
    String theString, theMessage;
    Button onBtn, offBtn;
    //String postURL = "http://192.168.43.75:8080/resteasy/insert.jsp?data=";//Posting data
    String postURL = "http://gopraniot.com/insert.jsp?";//Posting data
    String strURL = "http://192.168.43.75:8080/resteasy/restapi/jhallo";//Requesting the REST API
    static final String restURL="http://192.168.43.75:8080/resteasy/gates/asia-schneider";
    static final String gopranRestURL="http://gopraniot.com/devices/devres/";
    final String onString="ON";
    final String offString="OFF";
    String controlString;
    final String responseOn="\"1\"";
    final String responseOff="\"0\"";
    final String CHANNEL_ID = "Gopran";
    final String CHANNEL_NAME = "Electronics";
    final String CHANNEL_DESC = "Notifications";
    NotificationManager mNotificationManager;
    String getDeviceID;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onBtn   = (Button)findViewById(R.id.onBtn);
        offBtn  = (Button)findViewById(R.id.offBtn);
        mess    = (TextView)findViewById(R.id.mess);
        devID    = (RadioButton) findViewById(R.id.devID);
        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlOn();
            }
        });

        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlOff();
            }
        });

        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                //Initiate your API here
                callREST();
                handler.postDelayed(this, 2000);
            }
        };

        handler.postDelayed(r, 2000);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            mNotificationManager = getSystemService(NotificationManager.class);
            mNotificationManager.createNotificationChannel(channel);
        }
    }//end of onCreate()

    public void createNotification()
    {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.item_count)
                .setContentTitle("It's working...")
                .setContentText("Device is ON!")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());
    }

    public void callREST()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                gopranRestURL,//CALLING REST
                null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        theString = response.toString();
                        mess.setText(theString);
                        /*
                        String theSplit[] = theString.split(":",0);
                        theMessage = theSplit[1];
                        theMessage = theMessage.replace("}","");
                        //mess.setText(theMessage);
                        if(theMessage.equalsIgnoreCase(responseOn))
                        {
                            mess.setText("DEVICE IS ON");
                            //createNotification();
                        }
                        if(theMessage.equalsIgnoreCase(responseOff))
                        {
                            mess.setText("DEVICE IS OFF");
                        }
                        */
                        //mess.setText(theMessage.replace("}",""));
                        //toast("REST RESPONSE: "+response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //toast("REST RESPONSE: "+error.toString());
                        mess.setText(error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    public void controlOn()
    {
        getDeviceID = "1";
        MyTask mt = new MyTask();
        controlString = getDeviceID+"="+onString;
        mt.execute(controlString);

    }

    public void controlOff()
    {
        getDeviceID = "1";
        MyTask mt = new MyTask();
        controlString = getDeviceID+"="+offString;
        mt.execute(controlString);

    }
    class MyTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            //toast("PreExecute");
        }
        @Override
        protected String doInBackground(String... params)
        {
            String tehResult = sendFromAndroid(params.toString());
            return tehResult;
        }
        @Override
        protected void onProgressUpdate(Void... values) {}

        @Override
        protected void onPostExecute(String result)
        {
            try
            {
                //toast("PostExecute"+site+","+data);
                mess.setText(result);
            }
            catch(Exception e)
            {
                toast(e.toString());
            }
        }
    }//end of AsyncTask MyTask

    public String sendFromAndroid(String parameter)
    {
        String param = parameter;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet getMethod = new HttpGet(postURL+controlString);
            BufferedReader in = null;
            BasicHttpResponse httpResponse = (BasicHttpResponse) httpclient
                    .execute(getMethod);
            in = new BufferedReader(new InputStreamReader(httpResponse
                    .getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }//end of sendFromAndroid
    public void toast(String x)
    {
        Toast.makeText(this, x, Toast.LENGTH_LONG).show();
    }
}
