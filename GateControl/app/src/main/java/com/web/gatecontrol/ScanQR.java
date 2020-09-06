package com.web.gatecontrol;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.web.gatecontrol.db.UserDB;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.util.Random;

public class ScanQR extends AppCompatActivity implements View.OnClickListener
{
    UserDB userDB;
    SQLiteDatabase database;
    //String appTable = "CREATE TABLE IF NOT EXISTS APP(Id INTEGER PRIMARY KEY AUTOINCREMENT, APP_ID TEXT);";
    String gateTable = "CREATE TABLE IF NOT EXISTS GATES(Id INTEGER PRIMARY KEY AUTOINCREMENT, APP_ID TEXT, GATE_NAME TEXT, " +
            "SERVER TEXT,USERNAME TEXT, PASSWORD TEXT);";
    //String gateTable = "CREATE TABLE IF NOT EXISTS GATES(Id INTEGER PRIMARY KEY AUTOINCREMENT, GATE_NAME TEXT, APP_ID TEXT);";
    private EditText textID;
    private String gateName,getNumber,getRandomString,makeAppID;
    private IntentIntegrator qrScan;
    FloatingActionButton btnScanBarcode;
    TextView server, un, pwd,cellNum, randString, appID;
    EditText cnum;
    String serverURI, userName, passWord, theAppID, collectData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        /*
        userDB = new UserDB(this);
        database = userDB.getReadableDatabase();
        database.execSQL(gateTable);
        toast("Created the Table GATES");
         */
        textID = (EditText) findViewById(R.id.textID);
        btnScanBarcode = (FloatingActionButton) findViewById(R.id.btnScanBarcode);
        server = (TextView) findViewById(R.id.server);
        un = (TextView)findViewById(R.id.un);
        pwd = (TextView)findViewById(R.id.pwd);
        cnum = (EditText)findViewById(R.id.cnum);
        cellNum = (TextView)findViewById(R.id.cellNum);
        randString = (TextView)findViewById(R.id.randString);
        appID = (TextView)findViewById(R.id.appID);

        qrScan = new IntentIntegrator(this);
        btnScanBarcode.setOnClickListener(this);
        /*
        btnScanBarcode.setEnabled(false);
        textID.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                gateName = textID.getText().toString();
                getNumber = cnum.getText().toString();
                if(gateName.isEmpty() || getNumber.isEmpty())
                {
                    toast("Enter both the fields");
                }
                else
                {
                    btnScanBarcode.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        */
    }//end of onCreate()

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        gateName = textID.getText().toString();
        getNumber = cnum.getText().toString();
        getRandomString = getAlphaNumericString(6);
        makeAppID = getRandomString+getNumber;
        if(gateName.isEmpty() || getNumber.isEmpty())
        {
            toast("Enter both the fields");
        }
        else
        {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null)
            {
                //if qrcode has nothing in it
                if (result.getContents() == null)
                {
                    Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //if qr contains data
                    try
                    {
                        //converting the data to json
                        JSONObject obj = new JSONObject(result.getContents());
                        //setting values to textviews
                        //textID.setText(obj.getString("id"));
                        //server.setText(obj.getString("serverUri"));
                        serverURI = obj.getString("serverUri");
                        //un.setText(obj.getString("username"));
                        userName = obj.getString("username");
                        //pwd.setText(obj.getString("password"));
                        passWord = obj.getString("password");
                        cellNum.setText(getNumber);
                        randString.setText(getRandomString);
                        appID.setText(makeAppID);
                        userDB = new UserDB(this);
                        database = userDB.getReadableDatabase();
                        //database.execSQL("INSERT INTO GATES (APP_ID, GATE_NAME, SERVER, USERNAME, PASSWORD) VALUES('"+makeAppID+"','"+ gateName+"','"+serverURI+"','"+userName+"','"+passWord+"')");
                        Bundle allData = new Bundle();
                        collectData = makeAppID+"-"+gateName+"-"+serverURI+"-"+userName+"-"+passWord;
                        allData.putString("allData",collectData);
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtras(allData);
                        startActivity(intent);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        //if control comes here
                        //that means the encoded format not matches
                        //in this case you can display whatever data is available on the qrcode
                        //to a toast
                        Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    }
                }
            }
            else
            {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }
    @Override
    public void onClick(View view)
    {
        //initiating the qr code scan
        qrScan.initiateScan();
    }

    public static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++)
        {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public void toast(String s)
    {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

}
