package com.web.g_smsapp.admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.web.g_smsapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdminChangeNumbersActivity extends AppCompatActivity
{
    /*UI*/
    Button changeButton, exitButton;
    String getReceivingNumber, getnumber;
    String item;
    EditText theReceivingNumber, textNumber, textNumber2;
    Spinner spinner1;
    TextView receivingNumberLengthWatch, changedNumberLengthWatch,choiceOfNumber,title;
    List<String> numbers;

    /*MODEL*/
    int length1 = 0, length2 = 0, numberLength = 10;
    String number1Command = "#01%+91";
    String number2Command = "#02%+91";
    String number3Command = "#03%+91";
    String number4Command = "#04%+91";
    String number5Command = "#05%+91";
    String number6Command = "#06%+91";
    String number1 = "NUMBER # 1";
    String number2 = "NUMBER # 2";

    static final String companyChoice   = "1";
    static final String builderChoice   = "2";
    static final String selfChoice      = "3";

    String theChosen=null;

    /*SMS API*/
    SmsManager sms;
    protected void onCreate(Bundle savedInstances)
    {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_change_ele_number);

        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theChosen                   = theChoice.getString("KEY");
        //toast(theChosen);
        title                       =   (TextView) findViewById(R.id.title);
        theReceivingNumber          =   (EditText) findViewById(R.id.theReceivingNumber);
        textNumber                  =   (EditText) findViewById(R.id.textNumber);
        changeButton                =   (Button)   findViewById(R.id.changeButton);
        exitButton                  =   (Button)   findViewById(R.id.exitButton);
        spinner1                    =   (Spinner)  findViewById(R.id.spinner1);

        receivingNumberLengthWatch  =   (TextView)findViewById(R.id.receivingNumberLengthWatch);
        changedNumberLengthWatch    =   (TextView)findViewById(R.id.changedNumberLengthWatch);
        choiceOfNumber              =   (TextView)findViewById(R.id.choiceOfNumber);

        if(theChosen.equalsIgnoreCase(companyChoice))
        {
            title.setText("CHANGE ELEVATOR COMPANY NUMBERS");
        }
        if(theChosen.equalsIgnoreCase(builderChoice))
        {
            title.setText("CHANGE PANEL BUILDER COMPANY NUMBERS");
        }
        if(theChosen.equalsIgnoreCase(selfChoice))
        {
            title.setText("CHANGE SELF NUMBERS");
        }



        // Spinner Drop down elements
        numbers = new ArrayList<String>();
        numbers.add("          ");
        numbers.add(number1);
        numbers.add(number2);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);

        changeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getNumberDetails();
                //showDialogBox();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitFromHere();
            }
        });


        theReceivingNumber.setFocusable(true);
        theReceivingNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length1 = theReceivingNumber.length();
                String convert = String.valueOf((length1));
                receivingNumberLengthWatch.setText(convert+"/"+numberLength);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                length2 = textNumber.length();
                String convert = String.valueOf((length2));
                changedNumberLengthWatch.setText(convert+"/"+numberLength);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        sms = SmsManager.getDefault();
    }//end of onCreate()

    public void getNumberDetails()
    {
        if((length1 != numberLength) || (length2 != numberLength))
        {
            //showDialogBox();
            toast("Cell Phone Number has to be "+numberLength+" digits only!");
        }
        else
        {
            getReceivingNumber  = theReceivingNumber.getText().toString();
            getnumber          = textNumber.getText().toString();
            item                = String.valueOf(spinner1.getSelectedItem());
            choiceOfNumber.setText("CHOSEN:"+item);
            switch(theChosen)
            {
                case companyChoice:
                {
                    if(item.equalsIgnoreCase(number1))
                    {
                        sms.sendTextMessage(getReceivingNumber, null, number1Command+getnumber, null, null);
                        toast("SENT TO " +getReceivingNumber + " COMMAND:" +number1Command+getnumber);
                    }
                    if(item.equalsIgnoreCase(number2))
                    {
                        sms.sendTextMessage(getReceivingNumber, null, number2Command+getnumber, null, null);
                        toast("SENT TO " +getReceivingNumber + " COMMAND:" +number2Command+getnumber);
                    }
                }
                break;
                case builderChoice:
                {
                    if(item.equalsIgnoreCase(number1))
                    {
                        sms.sendTextMessage(getReceivingNumber, null, number3Command+getnumber, null, null);
                        toast("SENT TO " +getReceivingNumber + " COMMAND:" +number3Command+getnumber);
                    }
                    if(item.equalsIgnoreCase(number2))
                    {
                        sms.sendTextMessage(getReceivingNumber, null, number4Command+getnumber, null, null);
                        toast("SENT TO " +getReceivingNumber + " COMMAND:" +number4Command+getnumber);
                    }
                }
                break;
                case selfChoice:
                {
                    if(item.equalsIgnoreCase(number1))
                    {
                        sms.sendTextMessage(getReceivingNumber, null, number5Command+getnumber, null, null);
                        toast("SENT TO " +getReceivingNumber + " COMMAND:" +number5Command+getnumber);
                    }
                    if(item.equalsIgnoreCase(number2))
                    {
                        sms.sendTextMessage(getReceivingNumber, null, number6Command+getnumber, null, null);
                        toast("SENT TO " +getReceivingNumber + " COMMAND:" +number6Command+getnumber);
                    }
                }
                break;
            }//end of switch()
        }//end of else

    }//end of getNumberDetails()

    public void exitFromHere()
    {
        System.exit(0);
    }//end of exitFromHere()

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }//end of toast()
}
