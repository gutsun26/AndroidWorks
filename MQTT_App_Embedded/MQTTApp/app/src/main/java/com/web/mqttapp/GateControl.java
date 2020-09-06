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
    final String clientId = "device_eu";
    final String username = "hnrlbvhf";
    final String password = "eNikXBpi-W7c";

    //Topics
    final String theMqttMessage="Hi from Asia-Schneider App";
    final String g_open_topic = "asia-schneider/g_open";
    final String g_close_topic = "asia-schneider/g_close";
    final String subscriptionTopic = "asia-schneider/ack";
    final String ackTopic = "asia-schneider/ack";


    //Messages
    final String openGate = "1";
    final String closeGate = "0";
    final String msgOpenAck="go";
    final String msgCloseAck="gc";
    final String gateOpenInst = "Open the Gate";
    final String gateCloseInst = "Close the Gate";
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
            MqttMessage message = new MqttMessage(msgOpenAck.getBytes());
            message.setQos(qos);
            sampleClient.publish(ackTopic, message);
            toastMessage(" Publishing message: "+msgOpenAck+" And Message published");

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
            MqttMessage message = new MqttMessage(msgCloseAck.getBytes());
            message.setQos(qos);
            sampleClient.publish(ackTopic, message);
            toastMessage(" Publishing message: "+msgCloseAck+" And Message published");

        }
        catch (MqttException ex)
        {
            ex.printStackTrace();
        }
    }

    private void startMqtt()
    {
        //Here the connection stage is initiated in the object of MqttHelper where in the constructor an object of MqttAndroidClient is
        //created and the arguments to that constructor are serverURI & ID of the Phone where app is installed.
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
                    if(mqttMessage.toString().equalsIgnoreCase(openGate))
                    {
                        msgReceived.setText(gateOpenInst);
                    }
                    if(mqttMessage.toString().equalsIgnoreCase(closeGate))
                    {
                        msgReceived.setText(gateCloseInst);
                    }
                }


                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken)
                {
                    toastMessage("DELIVERY COMPLETE");
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
            connect();//Method to connect by authentication (username and password)
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
                mqttAndroidClient.subscribe(g_open_topic, qos, null, new IMqttActionListener()
                {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken){toastMessage("SUBSCRIBED TO THE TOPIC "+g_open_topic);}

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception){}
                });
                mqttAndroidClient.subscribe(g_close_topic, qos, null, new IMqttActionListener()
                {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken){toastMessage("SUBSCRIBED TO THE TOPIC "+g_close_topic);}

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
