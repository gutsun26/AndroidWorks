package com.web.udni_sms;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.web.udni_sms.admin_db.AdminControllerDB;
import com.web.udni_sms.ui.clients.ClientsFragment;

import java.io.File;

public class EditSiteInformationActivity extends AppCompatActivity
{

    EditText editSiteName, editSiteNumber,editSiteAddress_1,editSiteAddress_2;
    Button updateButton;
    int TAKE_PHOTO_CODE = 0;
    public static int count = 0;
    AdminControllerDB adminControllerDB;
    SQLiteDatabase sqLiteDatabase;
    String query = null,stringSplit;
    String sqlStatement=null;
    int rowCount;
    String theKey=null;
    String getEditSiteName, getEditSiteNumber,getEditSiteAddress_1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siteinfo);
        editSiteName        =   (EditText)findViewById(R.id.editSiteName);
        editSiteNumber      =   (EditText)findViewById(R.id.editSiteNumber);
        editSiteAddress_1   =   (EditText)findViewById(R.id.editSiteAddress_1);
        //editSiteAddress_2   =   (EditText)findViewById(R.id.editSiteAddress_2);
        updateButton            =   (Button)findViewById(R.id.updateButton);
        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theKey                      = theChoice.getString("ID");
        //toast(theKey);

        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        query = "SELECT * FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+theKey+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    editSiteName.setText(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NAME")));
                    editSiteNumber.setText(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NUMBER")));
                    editSiteAddress_1.setText(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_ADDRESS")));
                }while (cursor.moveToNext());
            }
        }

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
        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ADMIN_CLIENT_NAME",getEditSiteName);
        cv.put("ADMIN_CLIENT_NUMBER",getEditSiteNumber);
        cv.put("ADMIN_CLIENT_ADDRESS",getEditSiteAddress_1);
        sqLiteDatabase.update("ADMIN_CLIENT_DATA",cv,"ADMIN_CLIENT_NAME='"+theKey+"'",null);
        toast("UPDATED RECORD");
        this.finish();
    }
}

/**** FOR TAKING PHOTO AND SAVING IT *****/
/*
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newDir = new File(dir);
        newDir.mkdirs();

        imageSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /*OPTION 1*/
                /*
                count++;
                String file = dir + count + ".jpg";
                File newFile = new File(file);
                try {
                    newFile.createNewFile();
                } catch (Exception e) {
                    toast(e.toString());
                }
                Uri outPut = Uri.fromFile(newFile);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outPut);
                startActivityForResult(intent, TAKE_PHOTO_CODE);

            }
        });*/