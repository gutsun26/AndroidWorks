package com.web.udni_sms;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

import com.web.udni_sms.builder_db.BuilderControllerDB;

public class EditSiteInformationActivity2 extends AppCompatActivity
{

    EditText editSiteName, editSiteNumber,editSiteAddress_1,editSiteAddress_2;
    Button updateButton;
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    BuilderControllerDB builderControllerDB;
    SQLiteDatabase sqLiteDatabase;
    String query = null,stringSplit;
    String sqlStatement=null;
    int rowCount, numberCount, numberLength=10;
    String theKey=null;
    String getEditSiteName, getEditSiteNumber,getEditSiteAddress_1;
    int nameLength=2, cellNumberLength=10;
    int numberCount1, numberCount2, numberCount3;
    final String five="5", four="4", three="3", two="2", one="1", zero="0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_site);
        editSiteName        =   (EditText)findViewById(R.id.editSiteName);
        editSiteNumber      =   (EditText)findViewById(R.id.editSiteNumber);
        editSiteAddress_1   =   (EditText)findViewById(R.id.editSiteAddress_1);
        //editSiteAddress_2   =   (EditText)findViewById(R.id.editSiteAddress_2);
        updateButton            =   (Button)findViewById(R.id.updateButton);
        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theKey                      = theChoice.getString("ID");
        //toast(theKey);

        builderControllerDB = new BuilderControllerDB(this);
        sqLiteDatabase = builderControllerDB.getReadableDatabase();
        query = "SELECT * FROM BUILDER_CLIENT_DATA WHERE BUILDER_CLIENT_NAME='"+theKey+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    editSiteName.setText(cursor.getString(cursor.getColumnIndex("BUILDER_CLIENT_NAME")));
                    editSiteNumber.setText(cursor.getString(cursor.getColumnIndex("BUILDER_CLIENT_NUMBER")));
                    editSiteAddress_1.setText(cursor.getString(cursor.getColumnIndex("BUILDER_CLIENT_ADDRESS")));
                }while (cursor.moveToNext());
            }
        }

        editSiteNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String getMobile_1;
                getMobile_1 = editSiteNumber.getText().toString();
                numberCount = editSiteNumber.length();
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
                    editSiteNumber.setFocusable(true);
                    editSiteName.setEnabled(false);
                    editSiteAddress_1.setEnabled(false);
                }
                else
                {
                    editSiteName.setEnabled(true);
                    editSiteAddress_1.setEnabled(true);
                }
                numberCount = editSiteNumber.length();
                if( !(numberCount <= numberLength ) )
                {
                    editSiteNumber.setEnabled(false);
                    toast("ONLY 10 DIGITS");
                    editSiteNumber.setFocusable(true);
                    editSiteNumber.setEnabled(true);
                    updateButton.setEnabled(false);
                }
                else
                {
                    updateButton.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

    }//end of onCreate()

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }

    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void updateData()
    {
        getEditSiteName = editSiteName.getText().toString();
        getEditSiteNumber = editSiteNumber.getText().toString();
        getEditSiteAddress_1=editSiteAddress_1.getText().toString();
        toast(getEditSiteName + " " + getEditSiteNumber + " " +getEditSiteAddress_1);
        String getMobile_1;
        getMobile_1 = editSiteNumber.getText().toString();
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
            editSiteNumber.setFocusable(true);
            editSiteName.setEnabled(false);
            editSiteAddress_1.setEnabled(false);
        }
        else if(getEditSiteNumber.length() != cellNumberLength)
        {
            toast("Cell Phone Number digits are less than 10!");
            editSiteNumber.setFocusable(true);
            editSiteName.setEnabled(false);
            editSiteAddress_1.setEnabled(false);
            toast("Data not update! Please correct!");
        }
        else
        {
            builderControllerDB = new BuilderControllerDB(this);
            sqLiteDatabase = builderControllerDB.getReadableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("BUILDER_CLIENT_NAME",getEditSiteName);
            cv.put("BUILDER_CLIENT_NUMBER",getEditSiteNumber);
            cv.put("BUILDER_CLIENT_ADDRESS",getEditSiteAddress_1);
            sqLiteDatabase.update("BUILDER_CLIENT_DATA",cv,"BUILDER_CLIENT_NAME='"+theKey+"'",null);
            toast("UPDATED RECORD");
            System.exit(0);
        }
    }

    @Override
    public void onBackPressed()
    {
        System.exit(0);
    }
}