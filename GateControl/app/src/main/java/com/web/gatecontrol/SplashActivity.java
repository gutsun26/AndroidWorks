package com.web.gatecontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.web.gatecontrol.db.UserDB;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
    int rowCount;
    UserDB userDB;
    SQLiteDatabase database;
    String gateTable = "CREATE TABLE IF NOT EXISTS GATES(Id INTEGER PRIMARY KEY AUTOINCREMENT, APP_ID TEXT, GATE_NAME TEXT, " +
            "SERVER TEXT,USERNAME TEXT, PASSWORD TEXT);";
    String collectData="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();
        userDB = new UserDB(this);
        database = userDB.getReadableDatabase();
        database.execSQL(gateTable);
        Cursor cursor = database.rawQuery("SELECT * FROM GATES",null);
        rowCount = cursor.getCount();
        if(rowCount == 0)
        {
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    startActivity(new Intent(SplashActivity.this, ScanQR.class));
                }
            }, 5000);
        }
        else
        {
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    Bundle allData = new Bundle();
                    allData.putString("allData", collectData);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtras(allData);
                    startActivity(intent);
                }
                //startActivity(new Intent(SplashActivity.this, ScanQR.class));
            }, 5000);
        }
    }
    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }//end of toast
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
