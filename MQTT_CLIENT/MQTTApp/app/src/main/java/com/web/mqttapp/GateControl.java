package com.web.mqttapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class GateControl extends AppCompatActivity
{
    /*Control - MQTT*/
    int qos             = 2;
    final String serverUri = "tcp://tailor.cloudmqtt.com:12590";
    final String clientId = "device_app";
    final String username = "hnrlbvhf";
    final String password = "eNikXBpi-W7c";

    //Topics
    final String theMqttMessage="Hi from Asia-Schneider App";
    final String g_open_topic = "asia-schneider/g_open";
    final String g_close_topic = "asia-schneider/g_close";
    final String subscriptionTopic = "asia-schneider/ack";

    //Messages
    final String openGate = "1";
    final String closeGate = "0";
    final String msgOpenAck="go";
    final String msgCloseAck="gc";
    final String gateOpenMsg="Gate opened";
    final String gateCloseMsg="Gate closed";
    MqttHelper mqttHelper;

    /*Model - MQTT API*/
    MemoryPersistence persistence = new MemoryPersistence();

    /*View  - UI*/
    Button openBtn, closeBtn;
    TextView msgReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asia_schneider);

        openBtn         =       (Button)findViewById(R.id.openBtn);
        closeBtn        =       (Button)findViewById(R.id.closeBtn);
        msgReceived     =       (TextView)findViewById(R.id.gateMsg);

        openBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openGate();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                closeGate();
            }
        });

        startMqtt();
    }//end of onCreate()

    public void openGate()
    {
        try
        {
            MqttClient sampleClient = new MqttClient(serverUri, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            //toastMessage("Connecting to broker: "+serverUri);
            sampleClient.connect(connOpts);
            MqttMessage message = new MqttMessage(openGate.getBytes());
            message.setQos(qos);
            sampleClient.publish(g_open_topic, message);
            toastMessage(" Publishing message: "+openGate+" And Message published");

        }
        catch (MqttException ex)
        {
            ex.printStackTrace();
        }
    }//end of openGate()

    public void closeGate()
    {
        try
        {
            MqttClient sampleClient = new MqttClient(serverUri, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            //toastMessage("Connecting to broker: "+serverUri);
            sampleClient.connect(connOpts);
            MqttMessage message = new MqttMessage(closeGate.getBytes());
            message.setQos(qos);
            sampleClient.publish(g_close_topic, message);
            toastMessage(" Publishing message: "+closeGate+" And Message published");

        }
        catch (MqttException ex)
        {
            ex.printStackTrace();
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
                    //toastMessage(s);
                }


                public void connectionLost(Throwable throwable)
                {
                    //toastMessage("CONNECTION LOST DUE TO "+throwable);
                }


                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception
                {
                    //msgReceived.setText("MESSAGE ARRIVED: TOPIC-> "+ topic + " MQTT MESSAGE-> "+mqttMessage);
                    if(mqttMessage.toString().equalsIgnoreCase(msgOpenAck))
                    {
                        msgReceived.setText(gateOpenMsg);
                    }
                    if(mqttMessage.toString().equalsIgnoreCase(msgCloseAck))
                    {
                        msgReceived.setText(gateCloseMsg);
                    }
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

    class MqttHelper
    {
        public MqttAndroidClient mqttAndroidClient;
        public MqttHelper(Context context)
        {
            mqttAndroidClient = new MqttAndroidClient(context, serverUri, clientId);
            mqttAndroidClient.setCallback(new MqttCallbackExtended()
            {
                @Override
                public void connectComplete(boolean b, String s){toastMessage("Connection Complete");}

                @Override
                public void connectionLost(Throwable throwable){toastMessage("Connection Lost");}

                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception
                {
                    toastMessage("Topic:"+topic+"\nMessage Arrived:" + mqttMessage);

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken){toastMessage("Delivered");}
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
                mqttAndroidClient.subscribe(subscriptionTopic, qos, null, new IMqttActionListener()
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
