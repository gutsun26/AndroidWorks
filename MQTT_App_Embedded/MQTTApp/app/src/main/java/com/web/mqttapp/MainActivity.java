package com.web.mqttapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;

import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    /*GUI ELEMENTS*/
    Button helloButton,redLEDOn, redLEDOff;
    MqttHelper mqttHelper;
    TextView dataReceived,msgReceived;


    /*CLOUD MQTT SERVER INSTANCE DETAILS & TO PUBLISH ON CLOUDMQTT*/
    final String publishTopic1 = "esp8266/control_led";
    final String publishTopic2 = "esp8266/led_control";
    final String theMqttMessage="HI! FROM GOPRAN APP";
    final String theRedLEDOn = "1";
    final String theRedLEDOff = "0";
    int qos             = 0;
    final String serverUri = "tcp://tailor.cloudmqtt.com:12590";
    final String clientId = "Gopran_App";
    final String username = "hnrlbvhf";
    final String password = "eNikXBpi-W7c";
    MemoryPersistence persistence = new MemoryPersistence();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataReceived    = (TextView) findViewById(R.id.dataReceived);
        msgReceived     = (TextView) findViewById(R.id.msgReceived);
        redLEDOn        = (Button) findViewById(R.id.redLEDOn);
        redLEDOff       = (Button) findViewById(R.id.redLEDOff);
        helloButton     = (Button)findViewById(R.id.helloButton);

        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishTheTopic();
            }
        });
        startMqtt();
    }//end of onCreate()

    public void sendControl(View view)
    {
        switch (view.getId())
        {
            case R.id.redLEDOn:
            {
                try
                {
                    MqttClient sampleClient = new MqttClient(serverUri, clientId, persistence);
                    MqttConnectOptions connOpts = new MqttConnectOptions();
                    connOpts.setCleanSession(false);
                    connOpts.setUserName(username);
                    connOpts.setPassword(password.toCharArray());
                    toastMessage("Connecting to broker: "+serverUri);
                    sampleClient.connect(connOpts);
                    MqttMessage message = new MqttMessage(theRedLEDOn.getBytes());
                    message.setQos(qos);
                    sampleClient.publish(publishTopic2, message);
                    toastMessage(" Publishing message: "+theRedLEDOn+" And Message published");

                }
                catch (MqttException ex)
                {
                    ex.printStackTrace();
                }
            }
            break;
            case R.id.redLEDOff:
            {
                try
                {
                    MqttClient sampleClient = new MqttClient(serverUri, clientId, persistence);
                    MqttConnectOptions connOpts = new MqttConnectOptions();
                    connOpts.setCleanSession(false);
                    connOpts.setUserName(username);
                    connOpts.setPassword(password.toCharArray());
                    toastMessage("Connecting to broker: "+serverUri);
                    sampleClient.connect(connOpts);
                    MqttMessage message = new MqttMessage(theRedLEDOff.getBytes());
                    message.setQos(qos);
                    sampleClient.publish(publishTopic2, message);
                    toastMessage(" Publishing message: "+theRedLEDOff+" And Message published");

                }
                catch (MqttException ex)
                {
                    ex.printStackTrace();
                }
            }
            break;
        }
    }
    private void startMqtt()
    {
        mqttHelper = new MqttHelper(getApplicationContext());
        try
        {
            mqttHelper.setCallback(new MqttCallbackExtended()
            {

                public void connectComplete(boolean b, String s)
                {
                    toastMessage(s);
                }


                public void connectionLost(Throwable throwable)
                {
                    toastMessage("CONNECTION LOST DUE TO "+throwable);
                }


                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception
                {
                    msgReceived.setText("MESSAGE ARRIVED: TOPIC-> "+ topic + " MQTT MESSAGE-> "+mqttMessage);
                }


                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken)
                {
                    toastMessage("DELIVER COMPLETE");
                }
            });

        }
        catch(Exception e)
        {
            toastMessage(e.toString());

        }
    }//end of startMqtt()

    public void publishTheTopic()
    {
        try
        {
            toastMessage("IN");
            MqttClient sampleClient = new MqttClient(serverUri, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            toastMessage("Connecting to broker: "+serverUri);
            sampleClient.connect(connOpts);
            MqttMessage message = new MqttMessage(theMqttMessage.getBytes());
            message.setQos(qos);
            sampleClient.publish(publishTopic1, message);
            toastMessage(" Publishing message: "+theMqttMessage+" And Message published");

        }
        catch (MqttException ex)
        {
            ex.printStackTrace();
        }
    }

    class MqttHelper
    {
        public MqttAndroidClient mqttAndroidClient;
        final String serverUri = "tcp://tailor.cloudmqtt.com:12590";
        final String clientId = "esp8266";
        final String subscriptionTopic = "esp8266/led_control";
        final String username = "hnrlbvhf";
        final String password = "eNikXBpi-W7c";

        public MqttHelper(Context context)
        {
            mqttAndroidClient = new MqttAndroidClient(context, serverUri, clientId);
            mqttAndroidClient.setCallback(new MqttCallbackExtended()
            {
                @Override
                public void connectComplete(boolean b, String s){}

                @Override
                public void connectionLost(Throwable throwable){}

                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception{}

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken){}
            });
            connect();
        }

        public void setCallback(MqttCallbackExtended callback)
        {
            mqttAndroidClient.setCallback(callback);
        }

        private void connect()
        {
            MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
            mqttConnectOptions.setAutomaticReconnect(true);
            mqttConnectOptions.setCleanSession(false);
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());

            try
            {

                mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener()
                {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken)
                    {

                        DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                        disconnectedBufferOptions.setBufferEnabled(true);
                        disconnectedBufferOptions.setBufferSize(100);
                        disconnectedBufferOptions.setPersistBuffer(false);
                        disconnectedBufferOptions.setDeleteOldestMessages(false);
                        mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                        subscribeToTopic();
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception)
                    {

                    }
                });


            } catch (MqttException ex)
            {
                toastMessage(ex.toString());
            }
        }

        private void subscribeToTopic()
        {
            try
            {
                mqttAndroidClient.subscribe(subscriptionTopic, 0, null, new IMqttActionListener()
                {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken){toastMessage("SUBSCRIBED TO THE TOPIC "+subscriptionTopic);}

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception){}
                });

            }
            catch (MqttException ex)
            {
                ex.printStackTrace();
            }
        }
    }
    public void toastMessage(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
