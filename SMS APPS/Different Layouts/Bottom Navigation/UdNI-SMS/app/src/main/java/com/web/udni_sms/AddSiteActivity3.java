package com.web.udni_sms;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.web.udni_sms.customer_db.CustomerControllerDB;

public class AddSiteActivity3 extends AppCompatActivity
{
    EditText siteName, siteNumber, siteAddress_1, siteAddress_2;
    private String getClientName, getClientNumber, getClientAddress;
    Button saveButton;
    CustomerControllerDB customerControllerDB;
    SQLiteDatabase sqLiteDatabase;
    int numberCount, numberLength=10;
    int nameLength=2, cellNumberLength=10;
    int numberCount1, numberCount2, numberCount3;
    final String five="5", four="4", three="3", two="2", one="1", zero="0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        siteName        = (EditText)findViewById(R.id.siteName);
        siteNumber      = (EditText)findViewById(R.id.siteNumber);
        siteAddress_1   = (EditText)findViewById(R.id.siteAddress_1);
        saveButton      = (Button)findViewById(R.id.saveButton);
        //siteAddress_2   = (EditText)findViewById(R.id.siteAddress_2);
        customerControllerDB = new CustomerControllerDB(this);
        sqLiteDatabase = customerControllerDB.getReadableDatabase();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFunction();
            }
        });

        siteNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                numberCount = siteNumber.length();
                String getMobile_1;
                getMobile_1 = siteNumber.getText().toString();
                if (
                        getMobile_1.startsWith(five)    ||
                                getMobile_1.startsWith(four)    ||
                                getMobile_1.startsWith(three)   ||
                                getMobile_1.startsWith(two)     ||
                                getMobile_1.startsWith(one)     ||
                                getMobile_1.startsWith(zero)
                )
                {
                    toast("Incorrect starting digit");
                    siteNumber.setFocusable(true);
                    siteName.setEnabled(false);
                    siteAddress_1.setEnabled(false);

                }
                else
                {
                    siteName.setEnabled(true);
                    siteAddress_1.setEnabled(true);
                }
                numberCount = siteNumber.length();
                if( !(numberCount <= numberLength ) )
                {
                    siteNumber.setEnabled(false);
                    toast("ONLY 10 DIGITS");
                    siteNumber.setFocusable(true);
                    siteNumber.setEnabled(true);
                }
                else
                {
                    saveButton.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }//end of onCreate()

    public void saveFunction()
    {
        getClientName = siteName.getText().toString();
        getClientNumber = siteNumber.getText().toString();
        getClientAddress = siteAddress_1.getText().toString() ;
        if( getClientName.isEmpty() || getClientNumber.isEmpty() || getClientAddress.isEmpty())
        {
            toast("PLEASE FILL ALL THE FIELDS!");
        }
        String getMobile_1;
        getMobile_1 = siteNumber.getText().toString();
        if (
                getMobile_1.startsWith(five)    ||
                        getMobile_1.startsWith(four)    ||
                        getMobile_1.startsWith(three)   ||
                        getMobile_1.startsWith(two)     ||
                        getMobile_1.startsWith(one)     ||
                        getMobile_1.startsWith(zero)
        )
        {
            toast("Incorrect starting digit");
            siteNumber.setFocusable(true);
            siteName.setEnabled(false);
            siteAddress_1.setEnabled(false);

        }
        else if(getClientNumber.length() != cellNumberLength)
        {
            toast("Cell Phone Number digits are less than 10!");
            siteNumber.setFocusable(true);
            siteName.setEnabled(false);
            siteAddress_1.setEnabled(false);

        }
        else
        {
            sqLiteDatabase.execSQL("INSERT INTO CUSTOMER_CLIENT_DATA(CUSTOMER_CLIENT_NAME,CUSTOMER_CLIENT_NUMBER,CUSTOMER_CLIENT_ADDRESS) VALUES('" + getClientName + "','" + getClientNumber + "','" + getClientAddress +"')" );
            System.exit(0);
            //toast("UPDATED THE GIVEN DATA INTO DATABASE");
        }
    }//end of saveFunction()

    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
