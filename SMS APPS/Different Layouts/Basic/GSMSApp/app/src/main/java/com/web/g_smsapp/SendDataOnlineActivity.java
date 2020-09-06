package com.web.g_smsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SendDataOnlineActivity extends AppCompatActivity
{
    EditText theName, theCompany, theEmail, theWhatsAppNum, theMobile1, theMobile2, theAddress;
    String theNameString, theCompanyString, theEmailString, theWhatsAppNumString, theMobile1String, theMobile2String, theAddressString;
    Button sendButton;
    String serverURL = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_online);
        theName = (EditText)findViewById(R.id.theName);
        theCompany = (EditText)findViewById(R.id.theCompany);
        theEmail=(EditText)findViewById(R.id.theEmail);
        theWhatsAppNum=(EditText)findViewById(R.id.theWhatsAppNum);
        theMobile1=(EditText)findViewById(R.id.theMobile1);
        theMobile2=(EditText)findViewById(R.id.theMobile2);
        theAddress=(EditText)findViewById(R.id.theAddress);
        sendButton=(Button)findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToOnline();
            }
        });
    }//end of onCreate()

    public void sendToOnline()
    {
        theNameString 			=  	theName.getText().toString();
        theCompanyString 		=  	theCompany.getText().toString();
        theEmailString 			= 	theEmail.getText().toString();
        theWhatsAppNumString	=	theWhatsAppNum.getText().toString();
        theMobile1String 		=	theMobile1.getText().toString();
        theMobile2String 		=	theMobile2.getText().toString();
        theAddressString 		=	theAddress.getText().toString();


    }//end of sendToOnline()

    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
