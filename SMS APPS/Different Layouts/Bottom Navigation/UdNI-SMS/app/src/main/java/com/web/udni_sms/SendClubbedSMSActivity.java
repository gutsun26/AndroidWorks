package com.web.udni_sms;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.ads.*;
import com.web.udni_sms.admin_db.AdminControllerDB;
import com.web.udni_sms.admin_db.AdminSMSReceiverControllerDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SendClubbedSMSActivity extends AppCompatActivity
{
    /******************************MODEL****************************************/
    /*THE ACTUAL COMMANDS*/
    /*New set of commands as per new use case of clubbing commands*/
    /*
    String  stringCMD7      = "#07%100000AA%1000073C%0012FBA4";//INPUTS
    String  stringCMD8      = "#08%10001DCA%A123067D";//OUTPUTS
    String  stringCMD9      = "#09%AAAAAAAA%AAAAAAAA%AAAAAACD";//CALLS
    String  stringCMD10     = "#10%ABCDABCD%ABCDABCD%ABCDABCD%";//INDICATIONS
    String  stringCMD11     = "#11%10001030%01424824%10021014%10%10%10";//STATUS
    String  stringCMD12     = "#12%1000%1000%1000%1000%1000";//DRIVE PARAMETERS
    String  stringCMD13     = "#13%10001002";//EMR STOP STATUS
    String  stringCMD14     = "#14%00000001";//MODULE STATUS
    */

    String  stringCMD7      = "#07%";//INPUTS
    String  stringCMD8      = "#08%";//OUTPUTS
    String  stringCMD9      = "#09%";//CALLS
    String  stringCMD10     = "#10%";//INDICATIONS
    String  stringCMD11     = "#11%";//STATUS
    String  stringCMD12     = "#12%";//DRIVE PARAMETERS
    String  stringCMD13     = "#13%";//EMR STOP STATUS
    String  stringCMD14     = "#14%";//MODULE STATUS

    final String modbusError = "#99%00000001";
    final String replyError = "Reply error! Try again!";
    final String liftMessage = "LIFT IS STOPPED";
    String number=null,name=null;
    String getReceivingNumber=null;
    String getClientString=null;
    int length = 0, numberLength = 10;
    /*SMS RECEIVING RELATED*/
    public static final String SMS_BUNDLE = "pdus";
    String receivedMessage=null;
    String cellNumber=null;
    String extractedMessage=null;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_RECEIVE_SMS = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;
    int rowCount, rowCount2, rowCount3, emptyTable = 0;
    String choice = null;

    /******************************VIEW*****************************************/
    int progressStatus = 0;
    ImageView arrowImages;
    ProgressDialog progressBar, progressDialog;
    ImageView ImageView1, ImageView2, ImageView3;
    ImageView CMD7,CMD8,CMD9,CMD10,CMD11,CMD12,CMD13,CMD14,CMD15,CMD16,CMD17,CMD18,CMD19,CMD20,CMD21;
    TextView CMD7_text,CMD8_text,CMD9_text,CMD10_text,CMD11_text,CMD12_text,CMD13_text,CMD14_text,CMD15_text,CMD16_text,CMD17_text,CMD18_text,CMD19_text,CMD20_text,CMD21_text;
    Button insertTheDestNumberBtn,phoneBookButton;
    EditText theReceivingNumber,theClient;
    Spinner destNumber;
    TextView today, dateTimeDisplay,timerText,adminText,numberText,dirTxt;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    int timerValue = 6000;
    int displayTime = 21000;
    String adminString="ADMIN";
    final String runDirectionUP = "Lift Running Direction: UP";
    final String runDirectionDN = "Lift Running Direction: DOWN";
    final String message = "Scroll down & Click Text or the Message ICON";
    String theKey=null;
    TextView countDown;
    List<String> theData, theContent;
    ArrayAdapter<String> clientDataAdapter;
    final String	hexZero	    =	"0"	;
    final String	hexOne		=	"1"	;
    final String	hexTwo		=	"2"	;
    final String	hexThree	=	"3"	;
    final String	hexFour	    =	"4"	;
    final String	hexFive	    =	"5"	;
    final String	hexSix		=	"6"	;
    final String	hexSeven	=	"7"	;
    final String	hexEight	=	"8"	;
    final String	hexNine	    =	"9"	;
    final String	hexA	    =	"A"	;
    final String	hexB	    =	"B"	;
    final String	hexC	    =	"C"	;
    final String	hexD	    =	"D"	;
    final String	hexE	    =	"E"	;
    final String	hexF	    =	"F"	;

    /*Seven Segment Display*/
    View ss_a, ss_b, ss_c, ss_d, ss_e, ss_f, ss_g;
    View ss_h_a, ss_h_b, ss_h_c, ss_h_d, ss_h_e, ss_h_f, ss_h_g;
    View root;
    TextView sms_cmd7_bit0_txt, sms_cmd7_bit1_txt, sms_cmd7_bit2_txt, sms_cmd7_bit3_txt, sms_cmd7_bit4_txt, sms_cmd7_bit5_txt, sms_cmd7_bit6_txt, sms_cmd7_bit7_txt;
    RadioButton sms_cmd7_bit0, sms_cmd7_bit1, sms_cmd7_bit2, sms_cmd7_bit3, sms_cmd7_bit4, sms_cmd7_bit5, sms_cmd7_bit6, sms_cmd7_bit7;
    String sms_cmd7_txts[] =    {
            "SLWR",
            "STPR",
            "ARD",
            "OPC",
            "BRK",
            "MT",
            "MUP",
            "MDN"
    };

    /*COMMAND 8 - MAIN CARD TERMINAL INPUT - LOWER BYTE*/
    TextView sms_cmd8_bit0_txt, sms_cmd8_bit1_txt, sms_cmd8_bit2_txt, sms_cmd8_bit3_txt, sms_cmd8_bit4_txt, sms_cmd8_bit5_txt, sms_cmd8_bit6_txt, sms_cmd8_bit7_txt;
    RadioButton sms_cmd8_bit0, sms_cmd8_bit1, sms_cmd8_bit2, sms_cmd8_bit3, sms_cmd8_bit4, sms_cmd8_bit5, sms_cmd8_bit6, sms_cmd8_bit7;
    RadioButton sms_cmd8_bit_fr,sms_cmd8_bit_lft_lck; /*Fire & Lift Lock*/
    RadioButton sms_cmd7_cbc_bit0, sms_cmd7_cbc_bit1, sms_cmd7_cbc_bit2,sms_cmd7_cbc_bit3,
                sms_cmd7_cbc_bit4,sms_cmd7_cbc_bit5,sms_cmd7_cbc_bit6,sms_cmd7_cbc_bit7,
                sms_cmd7_cbc_bit8,sms_cmd7_cbc_bit9,sms_cmd7_cbc_bit10,sms_cmd7_cbc_bit11,sms_cmd7_cbc_bit12;
    /*COMMAND 8 - MAIN CARD TERMINAL INPUT - HIGHER BYTE*/
    TextView sms_cmd8_H_bit0_txt, sms_cmd8_H_bit1_txt, sms_cmd8_H_bit2_txt, sms_cmd8_H_bit3_txt, sms_cmd8_H_bit4_txt, sms_cmd8_H_bit5_txt, sms_cmd8_H_bit6_txt, sms_cmd8_H_bit7_txt;
    RadioButton sms_cmd8_H_bit0, sms_cmd8_H_bit1, sms_cmd8_H_bit2, sms_cmd8_H_bit3, sms_cmd8_H_bit4, sms_cmd8_H_bit5, sms_cmd8_H_bit6, sms_cmd8_H_bit7;
    String sms_cmd8_txts_L[] =    {
            "---",
            "---",
            "UTL",
            "DTL",
            "FUP",
            "FDN",
            "FUP1",
            "FDN1"
    };

    String sms_cmd8_txts_H[] =    {
            "---",
            "---",
            "---",
            "---",
            "---",
            "LGS",
            "CGS",
            "PS"
    };
    RadioButton cmd8_bit0, cmd8_bit1, cmd8_bit2, cmd8_bit3, cmd8_bit4, cmd8_bit5, cmd8_bit6, cmd8_bit7;
    RadioButton cmd8_bit9,cmd8_bit10,cmd8_bit11,cmd8_bit12,cmd8_bit13;
    /*CBC Outputs*/
    RadioButton cmd8_cbc_bit0, cmd8_cbc_bit1, cmd8_cbc_bit2, cmd8_cbc_bit3, cmd8_cbc_bit4, cmd8_cbc_bit5, cmd8_cbc_bit8, cmd8_cbc_bit9, cmd8_cbc_bit10,
                cmd8_cbc_bit11,cmd8_cbc_bit12, cmd8_cbc_bit13;
    /*Floor Calls (UP & DOWN)*/
    RadioButton cmd9_u_bit0,cmd9_u_bit1,cmd9_u_bit2,cmd9_u_bit3,cmd9_u_bit4,cmd9_u_bit5,cmd9_u_bit6,cmd9_u_bit7,
                cmd9_u_bit8,cmd9_u_bit9,cmd9_u_bit10,cmd9_u_bit11,cmd9_u_bit12,cmd9_u_bit13,cmd9_u_bit14,cmd9_u_bit15,
                cmd9_u_bit16,cmd9_u_bit17,cmd9_u_bit18,cmd9_u_bit19,cmd9_u_bit20,cmd9_u_bit21,cmd9_u_bit22,cmd9_u_bit23,
                cmd9_u_bit24,cmd9_u_bit25,cmd9_u_bit26,cmd9_u_bit27,cmd9_u_bit28,cmd9_u_bit29,cmd9_u_bit30,cmd9_u_bit31;
    RadioButton cmd9_d_bit0,cmd9_d_bit1,cmd9_d_bit2,cmd9_d_bit3,cmd9_d_bit4,cmd9_d_bit5,cmd9_d_bit6,cmd9_d_bit7,
                cmd9_d_bit8,cmd9_d_bit9,cmd9_d_bit10,cmd9_d_bit11,cmd9_d_bit12,cmd9_d_bit13,cmd9_d_bit14,cmd9_d_bit15,
                cmd9_d_bit16,cmd9_d_bit17,cmd9_d_bit18,cmd9_d_bit19,cmd9_d_bit20,cmd9_d_bit21,cmd9_d_bit22,cmd9_d_bit23,
                cmd9_d_bit24,cmd9_d_bit25,cmd9_d_bit26,cmd9_d_bit27,cmd9_d_bit28,cmd9_d_bit29,cmd9_d_bit30,cmd9_d_bit31;
    /*Car Calls*/
    RadioButton cmd9_c_bit0,cmd9_c_bit1,cmd9_c_bit2,cmd9_c_bit3,cmd9_c_bit4,cmd9_c_bit5,cmd9_c_bit6,cmd9_c_bit7,
                cmd9_c_bit8,cmd9_c_bit9,cmd9_c_bit10,cmd9_c_bit11,cmd9_c_bit12,cmd9_c_bit13,cmd9_c_bit14,cmd9_c_bit15,
                cmd9_c_bit16,cmd9_c_bit17,cmd9_c_bit18,cmd9_c_bit19,cmd9_c_bit20,cmd9_c_bit21,cmd9_c_bit22,cmd9_c_bit23,
                cmd9_c_bit24,cmd9_c_bit25,cmd9_c_bit26,cmd9_c_bit27,cmd9_c_bit28,cmd9_c_bit29,cmd9_c_bit30,cmd9_c_bit31;

    /*Floor Indicators*/
    RadioButton cmd9_ui_bit0,cmd9_ui_bit1,cmd9_ui_bit2,cmd9_ui_bit3,cmd9_ui_bit4,cmd9_ui_bit5,cmd9_ui_bit6,cmd9_ui_bit7,
            cmd9_ui_bit8,cmd9_ui_bit9,cmd9_ui_bit10,cmd9_ui_bit11,cmd9_ui_bit12,cmd9_ui_bit13,cmd9_ui_bit14,cmd9_ui_bit15,
            cmd9_ui_bit16,cmd9_ui_bit17,cmd9_ui_bit18,cmd9_ui_bit19,cmd9_ui_bit20,cmd9_ui_bit21,cmd9_ui_bit22,cmd9_ui_bit23,
            cmd9_ui_bit24,cmd9_ui_bit25,cmd9_ui_bit26,cmd9_ui_bit27,cmd9_ui_bit28,cmd9_ui_bit29,cmd9_ui_bit30,cmd9_ui_bit31;
    RadioButton cmd9_di_bit0,cmd9_di_bit1,cmd9_di_bit2,cmd9_di_bit3,cmd9_di_bit4,cmd9_di_bit5,cmd9_di_bit6,cmd9_di_bit7,
            cmd9_di_bit8,cmd9_di_bit9,cmd9_di_bit10,cmd9_di_bit11,cmd9_di_bit12,cmd9_di_bit13,cmd9_di_bit14,cmd9_di_bit15,
            cmd9_di_bit16,cmd9_di_bit17,cmd9_di_bit18,cmd9_di_bit19,cmd9_di_bit20,cmd9_di_bit21,cmd9_di_bit22,cmd9_di_bit23,
            cmd9_di_bit24,cmd9_di_bit25,cmd9_di_bit26,cmd9_di_bit27,cmd9_di_bit28,cmd9_di_bit29,cmd9_di_bit30,cmd9_di_bit31;
    /*Car Indicators*/
    RadioButton cmd9_ci_bit0,cmd9_ci_bit1,cmd9_ci_bit2,cmd9_ci_bit3,cmd9_ci_bit4,cmd9_ci_bit5,cmd9_ci_bit6,cmd9_ci_bit7,
            cmd9_ci_bit8,cmd9_ci_bit9,cmd9_ci_bit10,cmd9_ci_bit11,cmd9_ci_bit12,cmd9_ci_bit13,cmd9_ci_bit14,cmd9_ci_bit15,
            cmd9_ci_bit16,cmd9_ci_bit17,cmd9_ci_bit18,cmd9_ci_bit19,cmd9_ci_bit20,cmd9_ci_bit21,cmd9_ci_bit22,cmd9_ci_bit23,
            cmd9_ci_bit24,cmd9_ci_bit25,cmd9_ci_bit26,cmd9_ci_bit27,cmd9_ci_bit28,cmd9_ci_bit29,cmd9_ci_bit30,cmd9_ci_bit31;

    String errorStats[] =   {
                                "Dongle Not Connected",
                                "Dongle ID Mismatch",
                                "Lift Company ID Mismatch",
                                "Panel Builder ID Mismatch",
                                "Gopran ID Mismatch",
                                "Inverter Capacity Mismatch",
                                "Emergency Stop Command Executed",
                                "Relay Output is ON",
                                "Motor 1 Output is ON",
                                "Relay Output is OFF",
                                "Motor 1  Output OFF",
                                "Stop Reed Stuck",
                                "Slow Reed Stuck",
                                "Door Open Limit - Short",
                                "Door Close Limit - Short",
                                "Door Close Limit - Open",
                                "Door Open Limit - Open",
                                "Safety Short",
                                "Brake Control Feedback is Unavailable while Stopping the Lift",
                                "Output Contactor Feedback is Unavailable while Stopping the Lift",
                                "Reed Timeout",
                                "RCAM Timeout",
                                "Brake Control Feedback is Unavailable while Starting the Lift",
                                "BTS",
                                "Output Contactor Feedback is Unavailable while Starting the Lift",
                                "Inverter Enable",
                                "CBC Communication Broken",
                                "LOP Communication Broken",
                                "Inverter Communication Broken",
                                "ARD Mode ON",
                                "ARD Mode OFF",
                                "System on Fire",
                                "Attendant Mode",
                                "Maintenance Mode",
                                "Normal Mode"
                            };
    String moduleStatus = "GSM Module is Enabled";
    String emrStatus0 = "Lift Stop Request Served";
    String emrStatus1 = "Lift Stop Request Received";
    String errorMessage = new String();
    TextView msgTxt;

    HorizontalScrollView horiScroll;
    AdView adView;
    AdRequest adRequest;
    TableLayout mciView, mctiView, mcoView, cbciView, cbcoView,
            flrupView, flrdnView, carkeysView, lifterrView,threeView;//threeView for CMD15, 19, 20
    View mciTargetView, mctiTargetView, mcoTargetView, cbciTargetView, cbcoTargetView,
            flrupTargetView, flrdnTargetView, carkeysTargetView, lifterrTargetView, threeTargetView,
            indicaTargetView;
    /*******************************CONTROLLER**********************************/
    CountDownTimer cTimer = null;
    Boolean flag=false;
    /*SMS API*/
    SmsManager sms;
    /******************************************************************************/
    AdminControllerDB adminControllerDB;
    AdminSMSReceiverControllerDB AdminSMSReceiverControllerDB;
    SQLiteDatabase sqLiteDatabase, theSqLiteDatabase;
    String query = null, contactNumberQuery = null, theQuery;
    Cursor clientCursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clb_sms);
        /*MAPPING UI ELEMENTS*/
        adminText               =   (TextView)        findViewById(R.id.adminText);
        adView = (AdView) findViewById(R.id.ad_View);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        countDown       =   (TextView)          findViewById(R.id.countDown);
        mciView         =   (TableLayout)       findViewById(R.id.mciView);
        mctiView        =   (TableLayout)       findViewById(R.id.mctiView);
        mcoView         =   (TableLayout)       findViewById(R.id.mcoView);
        cbciView        =   (TableLayout)       findViewById(R.id.cbciView);
        cbcoView        =   (TableLayout)       findViewById(R.id.cbcoView);
        flrupView       =   (TableLayout)       findViewById(R.id.flrupView);
        flrdnView       =   (TableLayout)       findViewById(R.id.flrdnView);
        carkeysView     =   (TableLayout)       findViewById(R.id.carkeysView);
        lifterrView     =   (TableLayout)       findViewById(R.id.lifterrView);
        threeView       =   (TableLayout)       findViewById(R.id.threeView);
        msgTxt          =   (TextView)          findViewById(R.id.txt);

        mciTargetView       =findViewById(R.id.mciView);
        mctiTargetView      =findViewById(R.id.mctiView);
        mcoTargetView       =findViewById(R.id.mcoView);
        cbciTargetView      =findViewById(R.id.cbciView);
        cbcoTargetView      =findViewById(R.id.cbcoView);
        flrupTargetView     =findViewById(R.id.flrupView);
        flrdnTargetView     =findViewById(R.id.flrdnView);
        carkeysTargetView   =findViewById(R.id.carkeysView);
        lifterrTargetView   =findViewById(R.id.lifterrView);
        threeTargetView     =findViewById(R.id.threeView);
        indicaTargetView    =findViewById(R.id.indicas);

        CMD7                    =   (ImageView)        findViewById(R.id.CMD7);
        CMD8                    =   (ImageView)        findViewById(R.id.CMD8);
        CMD9                    =   (ImageView)        findViewById(R.id.CMD9);
        CMD10                   =   (ImageView)        findViewById(R.id.CMD10);
        CMD11                   =   (ImageView)        findViewById(R.id.CMD11);
        CMD12                   =   (ImageView)        findViewById(R.id.CMD12);
        CMD13                   =   (ImageView)        findViewById(R.id.CMD13);
        CMD14                   =   (ImageView)        findViewById(R.id.CMD14);
        today                   =   (TextView)           findViewById(R.id.today);

        CMD7_text 				=   (TextView)        findViewById(R.id.CMD7_text);
        CMD8_text               =   (TextView)        findViewById(R.id.CMD8_text);
        CMD9_text               =   (TextView)        findViewById(R.id.CMD9_text);
        CMD10_text              =   (TextView)        findViewById(R.id.CMD10_text);
        CMD11_text              =   (TextView)        findViewById(R.id.CMD11_text);
        CMD12_text              =   (TextView)        findViewById(R.id.CMD12_text);
        CMD13_text              =   (TextView)        findViewById(R.id.CMD13_text);
        CMD14_text              =   (TextView)        findViewById(R.id.CMD14_text);
        arrowImages             =   (ImageView)        findViewById(R.id.dirImg);
        dirTxt                  =   (TextView)        findViewById(R.id.dirTxt);
        /*BEGINNING OF INPUTS*/
        sms_cmd7_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd7_bit0_txt);
        sms_cmd7_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd7_bit1_txt);
        sms_cmd7_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd7_bit2_txt);
        sms_cmd7_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd7_bit3_txt);
        sms_cmd7_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd7_bit4_txt);
        sms_cmd7_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd7_bit5_txt);
        sms_cmd7_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd7_bit6_txt);
        sms_cmd7_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd7_bit7_txt);

        sms_cmd7_bit0_txt.setText(sms_cmd7_txts[7]);
        sms_cmd7_bit1_txt.setText(sms_cmd7_txts[6]);
        sms_cmd7_bit2_txt.setText(sms_cmd7_txts[5]);
        sms_cmd7_bit3_txt.setText(sms_cmd7_txts[4]);
        sms_cmd7_bit4_txt.setText(sms_cmd7_txts[3]);
        sms_cmd7_bit5_txt.setText(sms_cmd7_txts[2]);
        sms_cmd7_bit6_txt.setText(sms_cmd7_txts[1]);
        sms_cmd7_bit7_txt.setText(sms_cmd7_txts[0]);

        sms_cmd8_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd8_bit0_txt);
        sms_cmd8_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd8_bit1_txt);
        sms_cmd8_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd8_bit2_txt);
        sms_cmd8_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd8_bit3_txt);
        sms_cmd8_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd8_bit4_txt);
        sms_cmd8_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd8_bit5_txt);
        sms_cmd8_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd8_bit6_txt);
        sms_cmd8_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd8_bit7_txt);

        sms_cmd8_bit0_txt.setText(sms_cmd8_txts_L[7]);
        sms_cmd8_bit1_txt.setText(sms_cmd8_txts_L[6]);
        sms_cmd8_bit2_txt.setText(sms_cmd8_txts_L[5]);
        sms_cmd8_bit3_txt.setText(sms_cmd8_txts_L[4]);
        sms_cmd8_bit4_txt.setText(sms_cmd8_txts_L[3]);
        sms_cmd8_bit5_txt.setText(sms_cmd8_txts_L[2]);
        sms_cmd8_bit6_txt.setText(sms_cmd8_txts_L[1]);
        sms_cmd8_bit7_txt.setText(sms_cmd8_txts_L[0]);

        sms_cmd8_H_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd8_H_bit0_txt);
        sms_cmd8_H_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd8_H_bit1_txt);
        sms_cmd8_H_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd8_H_bit2_txt);
        sms_cmd8_H_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd8_H_bit3_txt);
        sms_cmd8_H_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd8_H_bit4_txt);
        sms_cmd8_H_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd8_H_bit5_txt);
        sms_cmd8_H_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd8_H_bit6_txt);
        sms_cmd8_H_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd8_H_bit7_txt);

        sms_cmd8_H_bit0_txt.setText(sms_cmd8_txts_H[7]);
        sms_cmd8_H_bit1_txt.setText(sms_cmd8_txts_H[6]);
        sms_cmd8_H_bit2_txt.setText(sms_cmd8_txts_H[5]);
        sms_cmd8_H_bit3_txt.setText(sms_cmd8_txts_H[4]);
        sms_cmd8_H_bit4_txt.setText(sms_cmd8_txts_H[3]);
        sms_cmd8_H_bit5_txt.setText(sms_cmd8_txts_H[2]);
        sms_cmd8_H_bit6_txt.setText(sms_cmd8_txts_H[1]);
        sms_cmd8_H_bit7_txt.setText(sms_cmd8_txts_H[0]);

        sms_cmd7_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd7_bit0);
        sms_cmd7_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd7_bit1);
        sms_cmd7_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd7_bit2);
        sms_cmd7_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd7_bit3);
        sms_cmd7_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd7_bit4);
        sms_cmd7_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd7_bit5);
        sms_cmd7_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd7_bit6);
        sms_cmd7_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd7_bit7);

        sms_cmd8_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd8_bit0);
        sms_cmd8_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd8_bit1);
        sms_cmd8_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd8_bit2);
        sms_cmd8_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd8_bit3);
        sms_cmd8_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd8_bit4);
        sms_cmd8_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd8_bit5);
        sms_cmd8_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd8_bit6);
        sms_cmd8_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd8_bit7);
        sms_cmd8_bit_fr         =   (RadioButton)       findViewById(R.id.sms_cmd8_bit_fr);
        sms_cmd8_bit_lft_lck    =   (RadioButton)       findViewById(R.id.sms_cmd8_bit_lft_lck);
        sms_cmd7_cbc_bit0      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit0);
        sms_cmd7_cbc_bit1      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit1);
        sms_cmd7_cbc_bit2      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit2);
        sms_cmd7_cbc_bit3      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit3);
        sms_cmd7_cbc_bit4      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit4);
        sms_cmd7_cbc_bit5      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit5);
        sms_cmd7_cbc_bit6      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit6);
        sms_cmd7_cbc_bit7      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit7);
        sms_cmd7_cbc_bit8      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit8);
        sms_cmd7_cbc_bit9      =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit9);
        sms_cmd7_cbc_bit10     =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit10);
        sms_cmd7_cbc_bit11     =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit11);
        sms_cmd7_cbc_bit12     =   (RadioButton)       findViewById(R.id.sms_cmd7_cbc_bit12);

        sms_cmd8_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit0);
        sms_cmd8_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit1);
        sms_cmd8_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit2);
        sms_cmd8_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit3);
        sms_cmd8_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit4);
        sms_cmd8_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit5);
        sms_cmd8_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit6);
        sms_cmd8_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit7);
        countDown.setVisibility(View.INVISIBLE);
        sms_cmd7_bit0.setClickable(false);
        sms_cmd7_bit1.setClickable(false);
        sms_cmd7_bit2.setClickable(false);
        sms_cmd7_bit3.setClickable(false);
        sms_cmd7_bit4.setClickable(false);
        sms_cmd7_bit5.setClickable(false);
        sms_cmd7_bit6.setClickable(false);
        sms_cmd7_bit7.setClickable(false);
        sms_cmd8_bit_fr.setClickable(false);
        sms_cmd8_bit_lft_lck.setClickable(false);
        sms_cmd7_cbc_bit0.setClickable(false);
        sms_cmd7_cbc_bit1.setClickable(false);
        sms_cmd7_cbc_bit2.setClickable(false);
        sms_cmd7_cbc_bit3.setClickable(false);
        sms_cmd7_cbc_bit0.setClickable(false);
        sms_cmd7_cbc_bit1.setClickable(false);
        sms_cmd7_cbc_bit2.setClickable(false);
        sms_cmd7_cbc_bit3.setClickable(false);
        sms_cmd7_cbc_bit4.setClickable(false);
        sms_cmd7_cbc_bit5.setClickable(false);
        sms_cmd7_cbc_bit6.setClickable(false);
        sms_cmd7_cbc_bit7.setClickable(false);
        sms_cmd7_cbc_bit8.setClickable(false);
        sms_cmd7_cbc_bit9.setClickable(false);
        sms_cmd7_cbc_bit10.setClickable(false);
        sms_cmd7_cbc_bit11.setClickable(false);
        sms_cmd7_cbc_bit12.setClickable(false);

        sms_cmd8_bit0.setClickable(false);
        sms_cmd8_bit1.setClickable(false);
        sms_cmd8_bit2.setClickable(false);
        sms_cmd8_bit3.setClickable(false);
        sms_cmd8_bit4.setClickable(false);
        sms_cmd8_bit5.setClickable(false);
        sms_cmd8_bit6.setClickable(false);
        sms_cmd8_bit7.setClickable(false);


        sms_cmd8_H_bit0.setClickable(false);
        sms_cmd8_H_bit1.setClickable(false);
        sms_cmd8_H_bit2.setClickable(false);
        sms_cmd8_H_bit3.setClickable(false);
        sms_cmd8_H_bit4.setClickable(false);
        sms_cmd8_H_bit5.setClickable(false);
        sms_cmd8_H_bit6.setClickable(false);
        sms_cmd8_H_bit7.setClickable(false);
        /*END OF INPUTS*/

        /*BEGINNING OF OUTPUTS*/
        cmd8_bit1 = (RadioButton)findViewById(R.id.cmd8_bit1);
        cmd8_bit2 = (RadioButton)findViewById(R.id.cmd8_bit2);
        cmd8_bit0 = (RadioButton)findViewById(R.id.cmd8_bit0);
        cmd8_bit3 = (RadioButton)findViewById(R.id.cmd8_bit3);
        cmd8_bit4 = (RadioButton)findViewById(R.id.cmd8_bit4);
        cmd8_bit5 = (RadioButton)findViewById(R.id.cmd8_bit5);
        cmd8_bit6 = (RadioButton)findViewById(R.id.cmd8_bit6);
        cmd8_bit7 = (RadioButton)findViewById(R.id.cmd8_bit7);

        cmd8_bit1.setClickable(false);
        cmd8_bit2.setClickable(false);
        cmd8_bit0.setClickable(false);
        cmd8_bit3.setClickable(false);
        cmd8_bit4.setClickable(false);
        cmd8_bit5.setClickable(false);
        cmd8_bit6.setClickable(false);
        cmd8_bit7.setClickable(false);

        cmd8_bit9 = (RadioButton)findViewById(R.id.cmd8_bit9);
        cmd8_bit10 = (RadioButton)findViewById(R.id.cmd8_bit10);
        cmd8_bit11 = (RadioButton)findViewById(R.id.cmd8_bit11);
        cmd8_bit12 = (RadioButton)findViewById(R.id.cmd8_bit12);
        cmd8_bit13 = (RadioButton)findViewById(R.id.cmd8_bit13);


        cmd8_bit9.setClickable(false);
        cmd8_bit10.setClickable(false);
        cmd8_bit11.setClickable(false);
        cmd8_bit12.setClickable(false);
        cmd8_bit13.setClickable(false);

        cmd8_cbc_bit0    =   (RadioButton)findViewById(R.id.cmd8_cbc_bit0);
        cmd8_cbc_bit1    =   (RadioButton)findViewById(R.id.cmd8_cbc_bit1);
        cmd8_cbc_bit2    =   (RadioButton)findViewById(R.id.cmd8_cbc_bit2);
        cmd8_cbc_bit3    =   (RadioButton)findViewById(R.id.cmd8_cbc_bit3);
        cmd8_cbc_bit4    =   (RadioButton)findViewById(R.id.cmd8_cbc_bit4);
        cmd8_cbc_bit5    =   (RadioButton)findViewById(R.id.cmd8_cbc_bit5);
        cmd8_cbc_bit8    =   (RadioButton)findViewById(R.id.cmd8_cbc_bit8);
        cmd8_cbc_bit9    =   (RadioButton)findViewById(R.id.cmd8_cbc_bit9);
        cmd8_cbc_bit10   =   (RadioButton)findViewById(R.id.cmd8_cbc_bit10);
        cmd8_cbc_bit11   =   (RadioButton)findViewById(R.id.cmd8_cbc_bit11);
        cmd8_cbc_bit12   =   (RadioButton)findViewById(R.id.cmd8_cbc_bit12);
        cmd8_cbc_bit13   =   (RadioButton)findViewById(R.id.cmd8_cbc_bit13);

        cmd8_cbc_bit0.setClickable(false);
        cmd8_cbc_bit1.setClickable(false);
        cmd8_cbc_bit2.setClickable(false);
        cmd8_cbc_bit3.setClickable(false);
        cmd8_cbc_bit4.setClickable(false);
        cmd8_cbc_bit5.setClickable(false);
        cmd8_cbc_bit8.setClickable(false);
        cmd8_cbc_bit9.setClickable(false);
        cmd8_cbc_bit10.setClickable(false);
        cmd8_cbc_bit11.setClickable(false);
        cmd8_cbc_bit12.setClickable(false);
        cmd8_cbc_bit13.setClickable(false);
        /*END OF OUTPUTS*/
        /*SEVEN SEGMENT*/
        ss_a                =   (View)  findViewById(R.id.ss_a);
        ss_b                =   (View)  findViewById(R.id.ss_b);
        ss_c                =   (View)  findViewById(R.id.ss_c);
        ss_d                =   (View)  findViewById(R.id.ss_d);
        ss_e                =   (View)  findViewById(R.id.ss_e);
        ss_f                =   (View)  findViewById(R.id.ss_f);
        ss_g                =   (View)  findViewById(R.id.ss_g);

        ss_h_a                =   (View)  findViewById(R.id.ss_h_a);
        ss_h_b                =   (View)  findViewById(R.id.ss_h_b);
        ss_h_c                =   (View)  findViewById(R.id.ss_h_c);
        ss_h_d                =   (View)  findViewById(R.id.ss_h_d);
        ss_h_e                =   (View)  findViewById(R.id.ss_h_e);
        ss_h_f                =   (View)  findViewById(R.id.ss_h_f);
        ss_h_g                =   (View)  findViewById(R.id.ss_h_g);

        displayNothing();
        /*END OF SEVEN SEGMENT*/

        /*FLOOR CALLS*/
        cmd9_u_bit0       = (RadioButton)findViewById(R.id.cmd9_u_bit0);
        cmd9_u_bit1       = (RadioButton)findViewById(R.id.cmd9_u_bit1);
        cmd9_u_bit2       = (RadioButton)findViewById(R.id.cmd9_u_bit2);
        cmd9_u_bit3       = (RadioButton)findViewById(R.id.cmd9_u_bit3);
        cmd9_u_bit4       = (RadioButton)findViewById(R.id.cmd9_u_bit4);
        cmd9_u_bit5       = (RadioButton)findViewById(R.id.cmd9_u_bit5);
        cmd9_u_bit6       = (RadioButton)findViewById(R.id.cmd9_u_bit6);
        cmd9_u_bit7       = (RadioButton)findViewById(R.id.cmd9_u_bit7);
        cmd9_u_bit8       = (RadioButton)findViewById(R.id.cmd9_u_bit8);
        cmd9_u_bit9       = (RadioButton)findViewById(R.id.cmd9_u_bit9);
        cmd9_u_bit10       = (RadioButton)findViewById(R.id.cmd9_u_bit10);
        cmd9_u_bit11       = (RadioButton)findViewById(R.id.cmd9_u_bit11);
        cmd9_u_bit12       = (RadioButton)findViewById(R.id.cmd9_u_bit12);
        cmd9_u_bit13       = (RadioButton)findViewById(R.id.cmd9_u_bit13);
        cmd9_u_bit14       = (RadioButton)findViewById(R.id.cmd9_u_bit14);
        cmd9_u_bit15       = (RadioButton)findViewById(R.id.cmd9_u_bit15);
        cmd9_u_bit16       = (RadioButton)findViewById(R.id.cmd9_u_bit16);
        cmd9_u_bit17       = (RadioButton)findViewById(R.id.cmd9_u_bit17);
        cmd9_u_bit18       = (RadioButton)findViewById(R.id.cmd9_u_bit18);
        cmd9_u_bit19       = (RadioButton)findViewById(R.id.cmd9_u_bit19);
        cmd9_u_bit20       = (RadioButton)findViewById(R.id.cmd9_u_bit20);
        cmd9_u_bit21       = (RadioButton)findViewById(R.id.cmd9_u_bit21);
        cmd9_u_bit22       = (RadioButton)findViewById(R.id.cmd9_u_bit22);
        cmd9_u_bit23       = (RadioButton)findViewById(R.id.cmd9_u_bit23);
        cmd9_u_bit24       = (RadioButton)findViewById(R.id.cmd9_u_bit24);
        cmd9_u_bit25       = (RadioButton)findViewById(R.id.cmd9_u_bit25);
        cmd9_u_bit26       = (RadioButton)findViewById(R.id.cmd9_u_bit26);
        cmd9_u_bit27       = (RadioButton)findViewById(R.id.cmd9_u_bit27);
        cmd9_u_bit28       = (RadioButton)findViewById(R.id.cmd9_u_bit28);
        cmd9_u_bit29       = (RadioButton)findViewById(R.id.cmd9_u_bit29);
        cmd9_u_bit30       = (RadioButton)findViewById(R.id.cmd9_u_bit30);
        cmd9_u_bit31       = (RadioButton)findViewById(R.id.cmd9_u_bit31);

        cmd9_u_bit0.setClickable(false);
        cmd9_u_bit1.setClickable(false);
        cmd9_u_bit2.setClickable(false);
        cmd9_u_bit3.setClickable(false);
        cmd9_u_bit4.setClickable(false);
        cmd9_u_bit5.setClickable(false);
        cmd9_u_bit6.setClickable(false);
        cmd9_u_bit7.setClickable(false);
        cmd9_u_bit8.setClickable(false);
        cmd9_u_bit9.setClickable(false);
        cmd9_u_bit10.setClickable(false);
        cmd9_u_bit11.setClickable(false);
        cmd9_u_bit12.setClickable(false);
        cmd9_u_bit13.setClickable(false);
        cmd9_u_bit14.setClickable(false);
        cmd9_u_bit15.setClickable(false);
        cmd9_u_bit16.setClickable(false);
        cmd9_u_bit17.setClickable(false);
        cmd9_u_bit18.setClickable(false);
        cmd9_u_bit19.setClickable(false);
        cmd9_u_bit20.setClickable(false);
        cmd9_u_bit21.setClickable(false);
        cmd9_u_bit22.setClickable(false);
        cmd9_u_bit23.setClickable(false);
        cmd9_u_bit24.setClickable(false);
        cmd9_u_bit25.setClickable(false);
        cmd9_u_bit26.setClickable(false);
        cmd9_u_bit27.setClickable(false);
        cmd9_u_bit28.setClickable(false);
        cmd9_u_bit29.setClickable(false);
        cmd9_u_bit30.setClickable(false);
        cmd9_u_bit31.setClickable(false);

        cmd9_d_bit0       = (RadioButton)findViewById(R.id.cmd9_d_bit0);
        cmd9_d_bit1       = (RadioButton)findViewById(R.id.cmd9_d_bit1);
        cmd9_d_bit2       = (RadioButton)findViewById(R.id.cmd9_d_bit2);
        cmd9_d_bit3       = (RadioButton)findViewById(R.id.cmd9_d_bit3);
        cmd9_d_bit4       = (RadioButton)findViewById(R.id.cmd9_d_bit4);
        cmd9_d_bit5       = (RadioButton)findViewById(R.id.cmd9_d_bit5);
        cmd9_d_bit6       = (RadioButton)findViewById(R.id.cmd9_d_bit6);
        cmd9_d_bit7       = (RadioButton)findViewById(R.id.cmd9_d_bit7);
        cmd9_d_bit8       = (RadioButton)findViewById(R.id.cmd9_d_bit8);
        cmd9_d_bit9       = (RadioButton)findViewById(R.id.cmd9_d_bit9);
        cmd9_d_bit10       = (RadioButton)findViewById(R.id.cmd9_d_bit10);
        cmd9_d_bit11       = (RadioButton)findViewById(R.id.cmd9_d_bit11);
        cmd9_d_bit12       = (RadioButton)findViewById(R.id.cmd9_d_bit12);
        cmd9_d_bit13       = (RadioButton)findViewById(R.id.cmd9_d_bit13);
        cmd9_d_bit14       = (RadioButton)findViewById(R.id.cmd9_d_bit14);
        cmd9_d_bit15       = (RadioButton)findViewById(R.id.cmd9_d_bit15);
        cmd9_d_bit16       = (RadioButton)findViewById(R.id.cmd9_d_bit16);
        cmd9_d_bit17       = (RadioButton)findViewById(R.id.cmd9_d_bit17);
        cmd9_d_bit18       = (RadioButton)findViewById(R.id.cmd9_d_bit18);
        cmd9_d_bit19       = (RadioButton)findViewById(R.id.cmd9_d_bit19);
        cmd9_d_bit20       = (RadioButton)findViewById(R.id.cmd9_d_bit20);
        cmd9_d_bit21       = (RadioButton)findViewById(R.id.cmd9_d_bit21);
        cmd9_d_bit22       = (RadioButton)findViewById(R.id.cmd9_d_bit22);
        cmd9_d_bit23       = (RadioButton)findViewById(R.id.cmd9_d_bit23);
        cmd9_d_bit24       = (RadioButton)findViewById(R.id.cmd9_d_bit24);
        cmd9_d_bit25       = (RadioButton)findViewById(R.id.cmd9_d_bit25);
        cmd9_d_bit26       = (RadioButton)findViewById(R.id.cmd9_d_bit26);
        cmd9_d_bit27       = (RadioButton)findViewById(R.id.cmd9_d_bit27);
        cmd9_d_bit28       = (RadioButton)findViewById(R.id.cmd9_d_bit28);
        cmd9_d_bit29       = (RadioButton)findViewById(R.id.cmd9_d_bit29);
        cmd9_d_bit30       = (RadioButton)findViewById(R.id.cmd9_d_bit30);
        cmd9_d_bit31       = (RadioButton)findViewById(R.id.cmd9_d_bit31);

        cmd9_d_bit0.setClickable(false);
        cmd9_d_bit1.setClickable(false);
        cmd9_d_bit2.setClickable(false);
        cmd9_d_bit3.setClickable(false);
        cmd9_d_bit4.setClickable(false);
        cmd9_d_bit5.setClickable(false);
        cmd9_d_bit6.setClickable(false);
        cmd9_d_bit7.setClickable(false);
        cmd9_d_bit8.setClickable(false);
        cmd9_d_bit9.setClickable(false);
        cmd9_d_bit10.setClickable(false);
        cmd9_d_bit11.setClickable(false);
        cmd9_d_bit12.setClickable(false);
        cmd9_d_bit13.setClickable(false);
        cmd9_d_bit14.setClickable(false);
        cmd9_d_bit15.setClickable(false);
        cmd9_d_bit16.setClickable(false);
        cmd9_d_bit17.setClickable(false);
        cmd9_d_bit18.setClickable(false);
        cmd9_d_bit19.setClickable(false);
        cmd9_d_bit20.setClickable(false);
        cmd9_d_bit21.setClickable(false);
        cmd9_d_bit22.setClickable(false);
        cmd9_d_bit23.setClickable(false);
        cmd9_d_bit24.setClickable(false);
        cmd9_d_bit25.setClickable(false);
        cmd9_d_bit26.setClickable(false);
        cmd9_d_bit27.setClickable(false);
        cmd9_d_bit28.setClickable(false);
        cmd9_d_bit29.setClickable(false);
        cmd9_d_bit30.setClickable(false);
        cmd9_d_bit31.setClickable(false);

        /*END OF FLOOR CALLS*/

        /*CAR CALLS*/
        cmd9_c_bit0       = (RadioButton)findViewById(R.id.cmd9_c_bit0);
        cmd9_c_bit1       = (RadioButton)findViewById(R.id.cmd9_c_bit1);
        cmd9_c_bit2       = (RadioButton)findViewById(R.id.cmd9_c_bit2);
        cmd9_c_bit3       = (RadioButton)findViewById(R.id.cmd9_c_bit3);
        cmd9_c_bit4       = (RadioButton)findViewById(R.id.cmd9_c_bit4);
        cmd9_c_bit5       = (RadioButton)findViewById(R.id.cmd9_c_bit5);
        cmd9_c_bit6       = (RadioButton)findViewById(R.id.cmd9_c_bit6);
        cmd9_c_bit7       = (RadioButton)findViewById(R.id.cmd9_c_bit7);
        cmd9_c_bit8       = (RadioButton)findViewById(R.id.cmd9_c_bit8);
        cmd9_c_bit9       = (RadioButton)findViewById(R.id.cmd9_c_bit9);
        cmd9_c_bit10       = (RadioButton)findViewById(R.id.cmd9_c_bit10);
        cmd9_c_bit11       = (RadioButton)findViewById(R.id.cmd9_c_bit11);
        cmd9_c_bit12       = (RadioButton)findViewById(R.id.cmd9_c_bit12);
        cmd9_c_bit13       = (RadioButton)findViewById(R.id.cmd9_c_bit13);
        cmd9_c_bit14       = (RadioButton)findViewById(R.id.cmd9_c_bit14);
        cmd9_c_bit15       = (RadioButton)findViewById(R.id.cmd9_c_bit15);
        cmd9_c_bit16       = (RadioButton)findViewById(R.id.cmd9_c_bit16);
        cmd9_c_bit17       = (RadioButton)findViewById(R.id.cmd9_c_bit17);
        cmd9_c_bit18       = (RadioButton)findViewById(R.id.cmd9_c_bit18);
        cmd9_c_bit19       = (RadioButton)findViewById(R.id.cmd9_c_bit19);
        cmd9_c_bit20       = (RadioButton)findViewById(R.id.cmd9_c_bit20);
        cmd9_c_bit21       = (RadioButton)findViewById(R.id.cmd9_c_bit21);
        cmd9_c_bit22       = (RadioButton)findViewById(R.id.cmd9_c_bit22);
        cmd9_c_bit23       = (RadioButton)findViewById(R.id.cmd9_c_bit23);
        cmd9_c_bit24       = (RadioButton)findViewById(R.id.cmd9_c_bit24);
        cmd9_c_bit25       = (RadioButton)findViewById(R.id.cmd9_c_bit25);
        cmd9_c_bit26       = (RadioButton)findViewById(R.id.cmd9_c_bit26);
        cmd9_c_bit27       = (RadioButton)findViewById(R.id.cmd9_c_bit27);
        cmd9_c_bit28       = (RadioButton)findViewById(R.id.cmd9_c_bit28);
        cmd9_c_bit29       = (RadioButton)findViewById(R.id.cmd9_c_bit29);
        cmd9_c_bit30       = (RadioButton)findViewById(R.id.cmd9_c_bit30);
        cmd9_c_bit31       = (RadioButton)findViewById(R.id.cmd9_c_bit31);

        cmd9_c_bit0.setClickable(false);
        cmd9_c_bit1.setClickable(false);
        cmd9_c_bit2.setClickable(false);
        cmd9_c_bit3.setClickable(false);
        cmd9_c_bit4.setClickable(false);
        cmd9_c_bit5.setClickable(false);
        cmd9_c_bit6.setClickable(false);
        cmd9_c_bit7.setClickable(false);
        cmd9_c_bit8.setClickable(false);
        cmd9_c_bit9.setClickable(false);
        cmd9_c_bit10.setClickable(false);
        cmd9_c_bit11.setClickable(false);
        cmd9_c_bit12.setClickable(false);
        cmd9_c_bit13.setClickable(false);
        cmd9_c_bit14.setClickable(false);
        cmd9_c_bit15.setClickable(false);
        cmd9_c_bit16.setClickable(false);
        cmd9_c_bit17.setClickable(false);
        cmd9_c_bit18.setClickable(false);
        cmd9_c_bit19.setClickable(false);
        cmd9_c_bit20.setClickable(false);
        cmd9_c_bit21.setClickable(false);
        cmd9_c_bit22.setClickable(false);
        cmd9_c_bit23.setClickable(false);
        cmd9_c_bit24.setClickable(false);
        cmd9_c_bit25.setClickable(false);
        cmd9_c_bit26.setClickable(false);
        cmd9_c_bit27.setClickable(false);
        cmd9_c_bit28.setClickable(false);
        cmd9_c_bit29.setClickable(false);
        cmd9_c_bit30.setClickable(false);
        cmd9_c_bit31.setClickable(false);
        /*END OF CAR CALLS*/

        /*FLOOR INDICATIONS*/
        cmd9_ui_bit0       = (RadioButton)findViewById(R.id.cmd9_ui_bit0);
        cmd9_ui_bit1       = (RadioButton)findViewById(R.id.cmd9_ui_bit1);
        cmd9_ui_bit2       = (RadioButton)findViewById(R.id.cmd9_ui_bit2);
        cmd9_ui_bit3       = (RadioButton)findViewById(R.id.cmd9_ui_bit3);
        cmd9_ui_bit4       = (RadioButton)findViewById(R.id.cmd9_ui_bit4);
        cmd9_ui_bit5       = (RadioButton)findViewById(R.id.cmd9_ui_bit5);
        cmd9_ui_bit6       = (RadioButton)findViewById(R.id.cmd9_ui_bit6);
        cmd9_ui_bit7       = (RadioButton)findViewById(R.id.cmd9_ui_bit7);
        cmd9_ui_bit8       = (RadioButton)findViewById(R.id.cmd9_ui_bit8);
        cmd9_ui_bit9       = (RadioButton)findViewById(R.id.cmd9_ui_bit9);
        cmd9_ui_bit10       = (RadioButton)findViewById(R.id.cmd9_ui_bit10);
        cmd9_ui_bit11       = (RadioButton)findViewById(R.id.cmd9_ui_bit11);
        cmd9_ui_bit12       = (RadioButton)findViewById(R.id.cmd9_ui_bit12);
        cmd9_ui_bit13       = (RadioButton)findViewById(R.id.cmd9_ui_bit13);
        cmd9_ui_bit14       = (RadioButton)findViewById(R.id.cmd9_ui_bit14);
        cmd9_ui_bit15       = (RadioButton)findViewById(R.id.cmd9_ui_bit15);
        cmd9_ui_bit16       = (RadioButton)findViewById(R.id.cmd9_ui_bit16);
        cmd9_ui_bit17       = (RadioButton)findViewById(R.id.cmd9_ui_bit17);
        cmd9_ui_bit18       = (RadioButton)findViewById(R.id.cmd9_ui_bit18);
        cmd9_ui_bit19       = (RadioButton)findViewById(R.id.cmd9_ui_bit19);
        cmd9_ui_bit20       = (RadioButton)findViewById(R.id.cmd9_ui_bit20);
        cmd9_ui_bit21       = (RadioButton)findViewById(R.id.cmd9_ui_bit21);
        cmd9_ui_bit22       = (RadioButton)findViewById(R.id.cmd9_ui_bit22);
        cmd9_ui_bit23       = (RadioButton)findViewById(R.id.cmd9_ui_bit23);
        cmd9_ui_bit24       = (RadioButton)findViewById(R.id.cmd9_ui_bit24);
        cmd9_ui_bit25       = (RadioButton)findViewById(R.id.cmd9_ui_bit25);
        cmd9_ui_bit26       = (RadioButton)findViewById(R.id.cmd9_ui_bit26);
        cmd9_ui_bit27       = (RadioButton)findViewById(R.id.cmd9_ui_bit27);
        cmd9_ui_bit28       = (RadioButton)findViewById(R.id.cmd9_ui_bit28);
        cmd9_ui_bit29       = (RadioButton)findViewById(R.id.cmd9_ui_bit29);
        cmd9_ui_bit30       = (RadioButton)findViewById(R.id.cmd9_ui_bit30);
        cmd9_ui_bit31       = (RadioButton)findViewById(R.id.cmd9_ui_bit31);

        cmd9_ui_bit0.setClickable(false);
        cmd9_ui_bit1.setClickable(false);
        cmd9_ui_bit2.setClickable(false);
        cmd9_ui_bit3.setClickable(false);
        cmd9_ui_bit4.setClickable(false);
        cmd9_ui_bit5.setClickable(false);
        cmd9_ui_bit6.setClickable(false);
        cmd9_ui_bit7.setClickable(false);
        cmd9_ui_bit8.setClickable(false);
        cmd9_ui_bit9.setClickable(false);
        cmd9_ui_bit10.setClickable(false);
        cmd9_ui_bit11.setClickable(false);
        cmd9_ui_bit12.setClickable(false);
        cmd9_ui_bit13.setClickable(false);
        cmd9_ui_bit14.setClickable(false);
        cmd9_ui_bit15.setClickable(false);
        cmd9_ui_bit16.setClickable(false);
        cmd9_ui_bit17.setClickable(false);
        cmd9_ui_bit18.setClickable(false);
        cmd9_ui_bit19.setClickable(false);
        cmd9_ui_bit20.setClickable(false);
        cmd9_ui_bit21.setClickable(false);
        cmd9_ui_bit22.setClickable(false);
        cmd9_ui_bit23.setClickable(false);
        cmd9_ui_bit24.setClickable(false);
        cmd9_ui_bit25.setClickable(false);
        cmd9_ui_bit26.setClickable(false);
        cmd9_ui_bit27.setClickable(false);
        cmd9_ui_bit28.setClickable(false);
        cmd9_ui_bit29.setClickable(false);
        cmd9_ui_bit30.setClickable(false);
        cmd9_ui_bit31.setClickable(false);

        cmd9_di_bit0       = (RadioButton)findViewById(R.id.cmd9_di_bit0);
        cmd9_di_bit1       = (RadioButton)findViewById(R.id.cmd9_di_bit1);
        cmd9_di_bit2       = (RadioButton)findViewById(R.id.cmd9_di_bit2);
        cmd9_di_bit3       = (RadioButton)findViewById(R.id.cmd9_di_bit3);
        cmd9_di_bit4       = (RadioButton)findViewById(R.id.cmd9_di_bit4);
        cmd9_di_bit5       = (RadioButton)findViewById(R.id.cmd9_di_bit5);
        cmd9_di_bit6       = (RadioButton)findViewById(R.id.cmd9_di_bit6);
        cmd9_di_bit7       = (RadioButton)findViewById(R.id.cmd9_di_bit7);
        cmd9_di_bit8       = (RadioButton)findViewById(R.id.cmd9_di_bit8);
        cmd9_di_bit9       = (RadioButton)findViewById(R.id.cmd9_di_bit9);
        cmd9_di_bit10       = (RadioButton)findViewById(R.id.cmd9_di_bit10);
        cmd9_di_bit11       = (RadioButton)findViewById(R.id.cmd9_di_bit11);
        cmd9_di_bit12       = (RadioButton)findViewById(R.id.cmd9_di_bit12);
        cmd9_di_bit13       = (RadioButton)findViewById(R.id.cmd9_di_bit13);
        cmd9_di_bit14       = (RadioButton)findViewById(R.id.cmd9_di_bit14);
        cmd9_di_bit15       = (RadioButton)findViewById(R.id.cmd9_di_bit15);
        cmd9_di_bit16       = (RadioButton)findViewById(R.id.cmd9_di_bit16);
        cmd9_di_bit17       = (RadioButton)findViewById(R.id.cmd9_di_bit17);
        cmd9_di_bit18       = (RadioButton)findViewById(R.id.cmd9_di_bit18);
        cmd9_di_bit19       = (RadioButton)findViewById(R.id.cmd9_di_bit19);
        cmd9_di_bit20       = (RadioButton)findViewById(R.id.cmd9_di_bit20);
        cmd9_di_bit21       = (RadioButton)findViewById(R.id.cmd9_di_bit21);
        cmd9_di_bit22       = (RadioButton)findViewById(R.id.cmd9_di_bit22);
        cmd9_di_bit23       = (RadioButton)findViewById(R.id.cmd9_di_bit23);
        cmd9_di_bit24       = (RadioButton)findViewById(R.id.cmd9_di_bit24);
        cmd9_di_bit25       = (RadioButton)findViewById(R.id.cmd9_di_bit25);
        cmd9_di_bit26       = (RadioButton)findViewById(R.id.cmd9_di_bit26);
        cmd9_di_bit27       = (RadioButton)findViewById(R.id.cmd9_di_bit27);
        cmd9_di_bit28       = (RadioButton)findViewById(R.id.cmd9_di_bit28);
        cmd9_di_bit29       = (RadioButton)findViewById(R.id.cmd9_di_bit29);
        cmd9_di_bit30       = (RadioButton)findViewById(R.id.cmd9_di_bit30);
        cmd9_di_bit31       = (RadioButton)findViewById(R.id.cmd9_di_bit31);

        cmd9_di_bit0.setClickable(false);
        cmd9_di_bit1.setClickable(false);
        cmd9_di_bit2.setClickable(false);
        cmd9_di_bit3.setClickable(false);
        cmd9_di_bit4.setClickable(false);
        cmd9_di_bit5.setClickable(false);
        cmd9_di_bit6.setClickable(false);
        cmd9_di_bit7.setClickable(false);
        cmd9_di_bit8.setClickable(false);
        cmd9_di_bit9.setClickable(false);
        cmd9_di_bit10.setClickable(false);
        cmd9_di_bit11.setClickable(false);
        cmd9_di_bit12.setClickable(false);
        cmd9_di_bit13.setClickable(false);
        cmd9_di_bit14.setClickable(false);
        cmd9_di_bit15.setClickable(false);
        cmd9_di_bit16.setClickable(false);
        cmd9_di_bit17.setClickable(false);
        cmd9_di_bit18.setClickable(false);
        cmd9_di_bit19.setClickable(false);
        cmd9_di_bit20.setClickable(false);
        cmd9_di_bit21.setClickable(false);
        cmd9_di_bit22.setClickable(false);
        cmd9_di_bit23.setClickable(false);
        cmd9_di_bit24.setClickable(false);
        cmd9_di_bit25.setClickable(false);
        cmd9_di_bit26.setClickable(false);
        cmd9_di_bit27.setClickable(false);
        cmd9_di_bit28.setClickable(false);
        cmd9_di_bit29.setClickable(false);
        cmd9_di_bit30.setClickable(false);
        cmd9_di_bit31.setClickable(false);

        /*END OF FLOOR INDICATIONS*/

        /*CAR INDICATIONS*/
        cmd9_ci_bit0       = (RadioButton)findViewById(R.id.cmd9_ci_bit0);
        cmd9_ci_bit1       = (RadioButton)findViewById(R.id.cmd9_ci_bit1);
        cmd9_ci_bit2       = (RadioButton)findViewById(R.id.cmd9_ci_bit2);
        cmd9_ci_bit3       = (RadioButton)findViewById(R.id.cmd9_ci_bit3);
        cmd9_ci_bit4       = (RadioButton)findViewById(R.id.cmd9_ci_bit4);
        cmd9_ci_bit5       = (RadioButton)findViewById(R.id.cmd9_ci_bit5);
        cmd9_ci_bit6       = (RadioButton)findViewById(R.id.cmd9_ci_bit6);
        cmd9_ci_bit7       = (RadioButton)findViewById(R.id.cmd9_ci_bit7);
        cmd9_ci_bit8       = (RadioButton)findViewById(R.id.cmd9_ci_bit8);
        cmd9_ci_bit9       = (RadioButton)findViewById(R.id.cmd9_ci_bit9);
        cmd9_ci_bit10       = (RadioButton)findViewById(R.id.cmd9_ci_bit10);
        cmd9_ci_bit11       = (RadioButton)findViewById(R.id.cmd9_ci_bit11);
        cmd9_ci_bit12       = (RadioButton)findViewById(R.id.cmd9_ci_bit12);
        cmd9_ci_bit13       = (RadioButton)findViewById(R.id.cmd9_ci_bit13);
        cmd9_ci_bit14       = (RadioButton)findViewById(R.id.cmd9_ci_bit14);
        cmd9_ci_bit15       = (RadioButton)findViewById(R.id.cmd9_ci_bit15);
        cmd9_ci_bit16       = (RadioButton)findViewById(R.id.cmd9_ci_bit16);
        cmd9_ci_bit17       = (RadioButton)findViewById(R.id.cmd9_ci_bit17);
        cmd9_ci_bit18       = (RadioButton)findViewById(R.id.cmd9_ci_bit18);
        cmd9_ci_bit19       = (RadioButton)findViewById(R.id.cmd9_ci_bit19);
        cmd9_ci_bit20       = (RadioButton)findViewById(R.id.cmd9_ci_bit20);
        cmd9_ci_bit21       = (RadioButton)findViewById(R.id.cmd9_ci_bit21);
        cmd9_ci_bit22       = (RadioButton)findViewById(R.id.cmd9_ci_bit22);
        cmd9_ci_bit23       = (RadioButton)findViewById(R.id.cmd9_ci_bit23);
        cmd9_ci_bit24       = (RadioButton)findViewById(R.id.cmd9_ci_bit24);
        cmd9_ci_bit25       = (RadioButton)findViewById(R.id.cmd9_ci_bit25);
        cmd9_ci_bit26       = (RadioButton)findViewById(R.id.cmd9_ci_bit26);
        cmd9_ci_bit27       = (RadioButton)findViewById(R.id.cmd9_ci_bit27);
        cmd9_ci_bit28       = (RadioButton)findViewById(R.id.cmd9_ci_bit28);
        cmd9_ci_bit29       = (RadioButton)findViewById(R.id.cmd9_ci_bit29);
        cmd9_ci_bit30       = (RadioButton)findViewById(R.id.cmd9_ci_bit30);
        cmd9_ci_bit31       = (RadioButton)findViewById(R.id.cmd9_ci_bit31);

        cmd9_ci_bit0.setClickable(false);
        cmd9_ci_bit1.setClickable(false);
        cmd9_ci_bit2.setClickable(false);
        cmd9_ci_bit3.setClickable(false);
        cmd9_ci_bit4.setClickable(false);
        cmd9_ci_bit5.setClickable(false);
        cmd9_ci_bit6.setClickable(false);
        cmd9_ci_bit7.setClickable(false);
        cmd9_ci_bit8.setClickable(false);
        cmd9_ci_bit9.setClickable(false);
        cmd9_ci_bit10.setClickable(false);
        cmd9_ci_bit11.setClickable(false);
        cmd9_ci_bit12.setClickable(false);
        cmd9_ci_bit13.setClickable(false);
        cmd9_ci_bit14.setClickable(false);
        cmd9_ci_bit15.setClickable(false);
        cmd9_ci_bit16.setClickable(false);
        cmd9_ci_bit17.setClickable(false);
        cmd9_ci_bit18.setClickable(false);
        cmd9_ci_bit19.setClickable(false);
        cmd9_ci_bit20.setClickable(false);
        cmd9_ci_bit21.setClickable(false);
        cmd9_ci_bit22.setClickable(false);
        cmd9_ci_bit23.setClickable(false);
        cmd9_ci_bit24.setClickable(false);
        cmd9_ci_bit25.setClickable(false);
        cmd9_ci_bit26.setClickable(false);
        cmd9_ci_bit27.setClickable(false);
        cmd9_ci_bit28.setClickable(false);
        cmd9_ci_bit29.setClickable(false);
        cmd9_ci_bit30.setClickable(false);
        cmd9_ci_bit31.setClickable(false);
        /*END OF INDICATIONS*/

        msgTxt.setText(message);

        arrowImages.setVisibility(View.INVISIBLE);
        dirTxt.setVisibility(View.INVISIBLE);
        sms = SmsManager.getDefault();
        checkForSmsPermission();
        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theKey                      = theChoice.getString("ID");
        //toast(theKey);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd, MMM, yyyy");
        //dateFormat = new SimpleDateFormat("dd, MMM, yyyy", Locale.ENGLISH);
        date = dateFormat.format(calendar.getTime());
        today.setText("Today is: "+date);

        AdminSMSReceiverControllerDB = new AdminSMSReceiverControllerDB(this);
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        theQuery = "CREATE TABLE IF NOT EXISTS ADMIN_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
        theSqLiteDatabase.execSQL(theQuery);
        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        query = "SELECT ADMIN_CLIENT_NAME FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+theKey+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    adminText.setText(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NAME")));
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        contactNumberQuery = "SELECT ADMIN_CLIENT_NUMBER FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+theKey+"'";
        Cursor cursor2 = sqLiteDatabase.rawQuery(contactNumberQuery,null);
        rowCount2 = cursor2.getCount();
        if(rowCount2!=0)
        {
            if (cursor2.moveToFirst())
            {
                do
                {
                    choice = cursor2.getString(cursor2.getColumnIndex("ADMIN_CLIENT_NUMBER"));
                    //toast(choice);
                }while (cursor2.moveToNext());
            }
        }
        cursor2.close();
        // Register a broadcast receiver for receiving SMS
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
        //toast("REGISTERED THE SMS RECEIVER");
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE DATE='"+date+"'",null);
        clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE ADDRESS='"+choice+"'",null);
        rowCount3 = clientCursor.getCount();
        //toast(String.valueOf(rowCount3));
        theContent = new ArrayList<String>();
        theContent.clear();
    }//end of onCreate()

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    BroadcastReceiver smsReceiver = new BroadcastReceiver()
    {
        SmsMessage message;
        String from;
        String content;
        public void onReceive(Context context, Intent intent)
        {
            Bundle intentExtras = intent.getExtras();
            if (intentExtras != null)
            {
                Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
                String smsMessageStr = "";
                String theString = "";
                for (int i = 0; i < sms.length; ++i)
                {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                    String smsBody = smsMessage.getMessageBody();
                    String address = smsMessage.getOriginatingAddress();

                    smsMessageStr += "SMS From: " + address + "\n";
                    smsMessageStr += smsBody + "\n";
                }

                for (int i = 0; i < sms.length; ++i)
                {
                    message  = SmsMessage.createFromPdu((byte[]) sms[i]);

                    content = message.getMessageBody();
                    from = message.getOriginatingAddress();

                    theString += from + ":";
                    theString += content + "\n";
                }
                if(content.equalsIgnoreCase(modbusError))
                {
                    toast(replyError);
                }
                else
                {
                    SendClubbedSMSActivity.this.getReceivedSMS(theString);
                }
            }
        }//end of onReceiver()
    };//end of BroadcastReceiver class

    public void getReceivedSMS(final String smsMessage)
    {
        receivedMessage = smsMessage;
        String[] array = receivedMessage.split(":",0);
        String theNumber=array[0];
        cellNumber          = theNumber.replace("+91","");
        extractedMessage    = array[1];
        //toast("NUMBER: "+cellNumber+" \nMESSAGE:"+extractedMessage);
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        List<String> THENUMBER = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ADMIN_CLIENT_DATA",null);
        THENUMBER.clear();
        if (cursor.moveToFirst())
        {
            do
            {
                THENUMBER.add(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NUMBER")));
                //toast(THENUMBER.toString());
            }while (cursor.moveToNext());
        }
        try
        {
            if(!THENUMBER.isEmpty())
            {
                for(int i=0; i<THENUMBER.size(); i++)
                {
                    if(THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        AdminSMSReceiverControllerDB = new AdminSMSReceiverControllerDB(this);
                        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
                        theQuery = "CREATE TABLE IF NOT EXISTS ADMIN_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
                        theSqLiteDatabase.execSQL(theQuery);
                        theSqLiteDatabase.execSQL("INSERT INTO ADMIN_SMS(DATE, ADDRESS, CONTENT) VALUES('"+date+"','"+cellNumber+"','"+extractedMessage+"')");
                        //Toast.makeText(getApplicationContext(), "DATA IS STORED",Toast.LENGTH_LONG).show();
                    }//end of if{}
                }//end of for()

                //INSERTING INTO DB AND THEN EXTRACTING IT TO DISPLAY ON UI
                for(int i=0; i<THENUMBER.size(); i++)
                {
                    if(THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE DATE='"+date+"'",null);
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE ADDRESS='"+cellNumber+"'",null);
                        clientCursor = theSqLiteDatabase.rawQuery("SELECT * FROM ADMIN_SMS ORDER BY Id DESC LIMIT 1",null);
                        theContent = new ArrayList<String>();
                        theContent.clear();
                        if (clientCursor.moveToFirst())
                        {
                            do
                            {
                                theContent.add(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                                //toast(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                            }while (clientCursor.moveToNext());
                            int count = clientCursor.getCount();
                            String contentArray[]=new String[count];
                            for(int j=0; j<contentArray.length; j++)
                            {
                                contentArray[j] = theContent.get(j);
                            }
                            StringBuffer sb0 = new StringBuffer();
                            for(int k = 0; k < contentArray.length; k++)
                            {
                                sb0.append(contentArray[k]);
                            }
                            countDown.setVisibility(View.VISIBLE);
                            String str1 = sb0.toString();
                            //adminText.setText(str1);
                            String decodeChoice = str1;
                            /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
                            String theData[] = new String[decodeChoice.length()];
                            theData = decodeChoice.split("#");
                            StringBuffer showData = new StringBuffer();
                            for(int s=1; s<theData.length; s++)//length of array
                            {
                                /*
                                showData.append(s);
                                showData.append(":");
                                showData.append(theData[s]);
                                showData.append("\n");
                                countDown.setText(showData.toString());

                                 */
                                /*Main Card Inputs, Main Card Terminal Inputs, LOP Inputs, CBC Inputs Display*/
                                if(String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("7"))
                                {
                                    displayTimer(displayTime);
                                    mciTargetView.getParent().requestChildFocus(mciTargetView,mciTargetView);
                                    //adminText.setText(theData[s].charAt(length-20));
                                    /*MAIN CARD INPUT*/
                                    /*Beginning of lower nibble of Main Card Input*/
                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd7_bit0.setChecked(true);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(true);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd7_bit0.setChecked(true);
                                        sms_cmd7_bit1.setChecked(true);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(true);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd7_bit0.setChecked(true);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(true);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(true);
                                        sms_cmd7_bit2.setChecked(true);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd7_bit0.setChecked(true);
                                        sms_cmd7_bit1.setChecked(true);
                                        sms_cmd7_bit2.setChecked(true);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd7_bit0.setChecked(true);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(true);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd7_bit0.setChecked(false);
                                        sms_cmd7_bit1.setChecked(false);
                                        sms_cmd7_bit2.setChecked(false);
                                        sms_cmd7_bit3.setChecked(false);
                                    }
                                    /*End of Higher Nibble of Main Card Input*/
                                    /*Beginning of Lower Nibble of Main Card Input*/
                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd7_bit4.setChecked(true);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(true);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd7_bit4.setChecked(true);
                                        sms_cmd7_bit5.setChecked(true);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(true);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd7_bit4.setChecked(true);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(true);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(true);
                                        sms_cmd7_bit6.setChecked(true);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd7_bit4.setChecked(true);
                                        sms_cmd7_bit5.setChecked(true);
                                        sms_cmd7_bit6.setChecked(true);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(true);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd7_bit4.setChecked(true);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(true);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(true);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(true);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }

                                    if(String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd7_bit4.setChecked(false);
                                        sms_cmd7_bit5.setChecked(false);
                                        sms_cmd7_bit6.setChecked(false);
                                        sms_cmd7_bit7.setChecked(false);
                                    }
                                    /*End of Higher nibble of Main Card Input*/
                                    /*END OF MAIN CARD INPUT*/
                                    /*MAIN CARD TERMINAL INPUT*/
                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd8_H_bit0.setChecked(false);
                                        sms_cmd8_H_bit1.setChecked(false);
                                        sms_cmd8_H_bit2.setChecked(false);
                                        sms_cmd8_H_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd8_H_bit0.setChecked(true);
                                        sms_cmd8_H_bit1.setChecked(false);
                                        sms_cmd8_H_bit2.setChecked(false);
                                        sms_cmd8_H_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd8_H_bit0.setChecked(false);
                                        sms_cmd8_H_bit1.setChecked(true);
                                        sms_cmd8_H_bit2.setChecked(false);
                                        sms_cmd8_H_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd8_H_bit0.setChecked(true);
                                        sms_cmd8_H_bit1.setChecked(true);
                                        sms_cmd8_H_bit2.setChecked(false);
                                        sms_cmd8_H_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd8_H_bit0.setChecked(false);
                                        sms_cmd8_H_bit1.setChecked(false);
                                        sms_cmd8_H_bit2.setChecked(true);
                                        sms_cmd8_H_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd8_H_bit0.setChecked(true);
                                        sms_cmd8_H_bit1.setChecked(false);
                                        sms_cmd8_H_bit2.setChecked(true);
                                        sms_cmd8_H_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd8_H_bit0.setChecked(false);
                                        sms_cmd8_H_bit1.setChecked(true);
                                        sms_cmd8_H_bit2.setChecked(true);
                                        sms_cmd8_H_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd8_H_bit0.setChecked(true);
                                        sms_cmd8_H_bit1.setChecked(true);
                                        sms_cmd8_H_bit2.setChecked(true);
                                        sms_cmd8_H_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd8_H_bit0.setChecked(false);
                                        sms_cmd8_H_bit1.setChecked(false);
                                        sms_cmd8_H_bit2.setChecked(false);
                                        sms_cmd8_H_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd8_H_bit0.setChecked(true);
                                        sms_cmd8_H_bit1.setChecked(false);
                                        sms_cmd8_H_bit2.setChecked(false);
                                        sms_cmd8_H_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd8_H_bit0.setChecked(false);
                                        sms_cmd8_H_bit1.setChecked(true);
                                        sms_cmd8_H_bit2.setChecked(false);
                                        sms_cmd8_H_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd8_H_bit0.setChecked(true);
                                        sms_cmd8_H_bit1.setChecked(true);
                                        sms_cmd8_H_bit2.setChecked(false);
                                        sms_cmd8_H_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd8_H_bit0.setChecked(false);
                                        sms_cmd8_H_bit1.setChecked(false);
                                        sms_cmd8_H_bit2.setChecked(true);
                                        sms_cmd8_H_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd8_H_bit0.setChecked(true);
                                        sms_cmd8_H_bit1.setChecked(false);
                                        sms_cmd8_H_bit2.setChecked(true);
                                        sms_cmd8_H_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd8_H_bit0.setChecked(false);
                                        sms_cmd8_H_bit1.setChecked(true);
                                        sms_cmd8_H_bit2.setChecked(true);
                                        sms_cmd8_H_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd8_H_bit0.setChecked(true);
                                        sms_cmd8_H_bit1.setChecked(true);
                                        sms_cmd8_H_bit2.setChecked(true);
                                        sms_cmd8_H_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd8_bit4.setChecked(false);
                                        sms_cmd8_bit5.setChecked(false);
                                        sms_cmd8_bit6.setChecked(false);
                                        sms_cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd8_bit4.setChecked(true);
                                        sms_cmd8_bit5.setChecked(false);
                                        sms_cmd8_bit6.setChecked(false);
                                        sms_cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd8_bit4.setChecked(false);
                                        sms_cmd8_bit5.setChecked(true);
                                        sms_cmd8_bit6.setChecked(false);
                                        sms_cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd8_bit4.setChecked(true);
                                        sms_cmd8_bit5.setChecked(true);
                                        sms_cmd8_bit6.setChecked(false);
                                        sms_cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd8_bit4.setChecked(false);
                                        sms_cmd8_bit5.setChecked(false);
                                        sms_cmd8_bit6.setChecked(true);
                                        sms_cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd8_bit4.setChecked(true);
                                        sms_cmd8_bit5.setChecked(false);
                                        sms_cmd8_bit6.setChecked(true);
                                        sms_cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd8_bit4.setChecked(false);
                                        sms_cmd8_bit5.setChecked(true);
                                        sms_cmd8_bit6.setChecked(true);
                                        sms_cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd8_bit4.setChecked(true);
                                        sms_cmd8_bit5.setChecked(true);
                                        sms_cmd8_bit6.setChecked(true);
                                        sms_cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd8_bit4.setChecked(false);
                                        sms_cmd8_bit5.setChecked(false);
                                        sms_cmd8_bit6.setChecked(false);
                                        sms_cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd8_bit4.setChecked(true);
                                        sms_cmd8_bit5.setChecked(false);
                                        sms_cmd8_bit6.setChecked(false);
                                        sms_cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd8_bit4.setChecked(false);
                                        sms_cmd8_bit5.setChecked(true);
                                        sms_cmd8_bit6.setChecked(false);
                                        sms_cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd8_bit4.setChecked(true);
                                        sms_cmd8_bit5.setChecked(true);
                                        sms_cmd8_bit6.setChecked(false);
                                        sms_cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd8_bit4.setChecked(false);
                                        sms_cmd8_bit5.setChecked(false);
                                        sms_cmd8_bit6.setChecked(true);
                                        sms_cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd8_bit4.setChecked(true);
                                        sms_cmd8_bit5.setChecked(false);
                                        sms_cmd8_bit6.setChecked(true);
                                        sms_cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd8_bit4.setChecked(false);
                                        sms_cmd8_bit5.setChecked(true);
                                        sms_cmd8_bit6.setChecked(true);
                                        sms_cmd8_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd8_bit4.setChecked(true);
                                        sms_cmd8_bit5.setChecked(true);
                                        sms_cmd8_bit6.setChecked(true);
                                        sms_cmd8_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd8_bit0.setChecked(false);
                                        sms_cmd8_bit1.setChecked(false);
                                        sms_cmd8_bit2.setChecked(false);
                                        sms_cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd8_bit0.setChecked(true);
                                        sms_cmd8_bit1.setChecked(false);
                                        sms_cmd8_bit2.setChecked(false);
                                        sms_cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd8_bit0.setChecked(false);
                                        sms_cmd8_bit1.setChecked(true);
                                        sms_cmd8_bit2.setChecked(false);
                                        sms_cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd8_bit0.setChecked(true);
                                        sms_cmd8_bit1.setChecked(true);
                                        sms_cmd8_bit2.setChecked(false);
                                        sms_cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd8_bit0.setChecked(false);
                                        sms_cmd8_bit1.setChecked(false);
                                        sms_cmd8_bit2.setChecked(true);
                                        sms_cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd8_bit0.setChecked(true);
                                        sms_cmd8_bit1.setChecked(false);
                                        sms_cmd8_bit2.setChecked(true);
                                        sms_cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd8_bit0.setChecked(false);
                                        sms_cmd8_bit1.setChecked(true);
                                        sms_cmd8_bit2.setChecked(true);
                                        sms_cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd8_bit0.setChecked(true);
                                        sms_cmd8_bit1.setChecked(true);
                                        sms_cmd8_bit2.setChecked(true);
                                        sms_cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd8_bit0.setChecked(false);
                                        sms_cmd8_bit1.setChecked(false);
                                        sms_cmd8_bit2.setChecked(false);
                                        sms_cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd8_bit0.setChecked(true);
                                        sms_cmd8_bit1.setChecked(false);
                                        sms_cmd8_bit2.setChecked(false);
                                        sms_cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd8_bit0.setChecked(false);
                                        sms_cmd8_bit1.setChecked(true);
                                        sms_cmd8_bit2.setChecked(false);
                                        sms_cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd8_bit0.setChecked(true);
                                        sms_cmd8_bit1.setChecked(true);
                                        sms_cmd8_bit2.setChecked(false);
                                        sms_cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd8_bit0.setChecked(false);
                                        sms_cmd8_bit1.setChecked(false);
                                        sms_cmd8_bit2.setChecked(true);
                                        sms_cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd8_bit0.setChecked(true);
                                        sms_cmd8_bit1.setChecked(false);
                                        sms_cmd8_bit2.setChecked(true);
                                        sms_cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd8_bit0.setChecked(false);
                                        sms_cmd8_bit1.setChecked(true);
                                        sms_cmd8_bit2.setChecked(true);
                                        sms_cmd8_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd8_bit0.setChecked(true);
                                        sms_cmd8_bit1.setChecked(true);
                                        sms_cmd8_bit2.setChecked(true);
                                        sms_cmd8_bit3.setChecked(true);
                                    }
                                    /*END OF MAIN CARD TERMINAL INPUT*/
                                    /*LOP INPUTS*/
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd8_bit_fr.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd8_bit_fr.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd8_bit_fr.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd8_bit_fr.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd8_bit_fr.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd8_bit_fr.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd8_bit_fr.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd8_bit_fr.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd8_bit_lft_lck.setChecked(true);
                                    }
                                    /*END OF LOP INPUTS*/
                                    /*CBC INPUTS*/
                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(false);
                                        sms_cmd7_cbc_bit1.setChecked(false);
                                        sms_cmd7_cbc_bit2.setChecked(false);
                                        sms_cmd7_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(true);
                                        sms_cmd7_cbc_bit1.setChecked(false);
                                        sms_cmd7_cbc_bit2.setChecked(false);
                                        sms_cmd7_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(false);
                                        sms_cmd7_cbc_bit1.setChecked(true);
                                        sms_cmd7_cbc_bit2.setChecked(false);
                                        sms_cmd7_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(true);
                                        sms_cmd7_cbc_bit1.setChecked(true);
                                        sms_cmd7_cbc_bit2.setChecked(false);
                                        sms_cmd7_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(false);
                                        sms_cmd7_cbc_bit1.setChecked(false);
                                        sms_cmd7_cbc_bit2.setChecked(true);
                                        sms_cmd7_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(true);
                                        sms_cmd7_cbc_bit1.setChecked(false);
                                        sms_cmd7_cbc_bit2.setChecked(true);
                                        sms_cmd7_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(false);
                                        sms_cmd7_cbc_bit1.setChecked(true);
                                        sms_cmd7_cbc_bit2.setChecked(true);
                                        sms_cmd7_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(true);
                                        sms_cmd7_cbc_bit1.setChecked(true);
                                        sms_cmd7_cbc_bit2.setChecked(true);
                                        sms_cmd7_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(false);
                                        sms_cmd7_cbc_bit1.setChecked(false);
                                        sms_cmd7_cbc_bit2.setChecked(false);
                                        sms_cmd7_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(true);
                                        sms_cmd7_cbc_bit1.setChecked(false);
                                        sms_cmd7_cbc_bit2.setChecked(false);
                                        sms_cmd7_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(false);
                                        sms_cmd7_cbc_bit1.setChecked(true);
                                        sms_cmd7_cbc_bit2.setChecked(false);
                                        sms_cmd7_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(true);
                                        sms_cmd7_cbc_bit1.setChecked(true);
                                        sms_cmd7_cbc_bit2.setChecked(false);
                                        sms_cmd7_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(false);
                                        sms_cmd7_cbc_bit1.setChecked(false);
                                        sms_cmd7_cbc_bit2.setChecked(true);
                                        sms_cmd7_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(true);
                                        sms_cmd7_cbc_bit1.setChecked(false);
                                        sms_cmd7_cbc_bit2.setChecked(true);
                                        sms_cmd7_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(false);
                                        sms_cmd7_cbc_bit1.setChecked(true);
                                        sms_cmd7_cbc_bit2.setChecked(true);
                                        sms_cmd7_cbc_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd7_cbc_bit0.setChecked(true);
                                        sms_cmd7_cbc_bit1.setChecked(true);
                                        sms_cmd7_cbc_bit2.setChecked(true);
                                        sms_cmd7_cbc_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(false);
                                        sms_cmd7_cbc_bit5.setChecked(false);
                                        sms_cmd7_cbc_bit6.setChecked(false);
                                        sms_cmd7_cbc_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(true);
                                        sms_cmd7_cbc_bit5.setChecked(false);
                                        sms_cmd7_cbc_bit6.setChecked(false);
                                        sms_cmd7_cbc_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(false);
                                        sms_cmd7_cbc_bit5.setChecked(true);
                                        sms_cmd7_cbc_bit6.setChecked(false);
                                        sms_cmd7_cbc_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(true);
                                        sms_cmd7_cbc_bit5.setChecked(true);
                                        sms_cmd7_cbc_bit6.setChecked(false);
                                        sms_cmd7_cbc_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(false);
                                        sms_cmd7_cbc_bit5.setChecked(false);
                                        sms_cmd7_cbc_bit6.setChecked(true);
                                        sms_cmd7_cbc_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(true);
                                        sms_cmd7_cbc_bit5.setChecked(false);
                                        sms_cmd7_cbc_bit6.setChecked(true);
                                        sms_cmd7_cbc_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(false);
                                        sms_cmd7_cbc_bit5.setChecked(true);
                                        sms_cmd7_cbc_bit6.setChecked(true);
                                        sms_cmd7_cbc_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(true);
                                        sms_cmd7_cbc_bit5.setChecked(true);
                                        sms_cmd7_cbc_bit6.setChecked(true);
                                        sms_cmd7_cbc_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(false);
                                        sms_cmd7_cbc_bit5.setChecked(false);
                                        sms_cmd7_cbc_bit6.setChecked(false);
                                        sms_cmd7_cbc_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(true);
                                        sms_cmd7_cbc_bit5.setChecked(false);
                                        sms_cmd7_cbc_bit6.setChecked(false);
                                        sms_cmd7_cbc_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(false);
                                        sms_cmd7_cbc_bit5.setChecked(true);
                                        sms_cmd7_cbc_bit6.setChecked(false);
                                        sms_cmd7_cbc_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(true);
                                        sms_cmd7_cbc_bit5.setChecked(true);
                                        sms_cmd7_cbc_bit6.setChecked(false);
                                        sms_cmd7_cbc_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(false);
                                        sms_cmd7_cbc_bit5.setChecked(false);
                                        sms_cmd7_cbc_bit6.setChecked(true);
                                        sms_cmd7_cbc_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(true);
                                        sms_cmd7_cbc_bit5.setChecked(false);
                                        sms_cmd7_cbc_bit6.setChecked(true);
                                        sms_cmd7_cbc_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(false);
                                        sms_cmd7_cbc_bit5.setChecked(true);
                                        sms_cmd7_cbc_bit6.setChecked(true);
                                        sms_cmd7_cbc_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd7_cbc_bit4.setChecked(true);
                                        sms_cmd7_cbc_bit5.setChecked(true);
                                        sms_cmd7_cbc_bit6.setChecked(true);
                                        sms_cmd7_cbc_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(false);
                                        sms_cmd7_cbc_bit9.setChecked(false);
                                        sms_cmd7_cbc_bit10.setChecked(false);
                                        sms_cmd7_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(true);
                                        sms_cmd7_cbc_bit9.setChecked(false);
                                        sms_cmd7_cbc_bit10.setChecked(false);
                                        sms_cmd7_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(false);
                                        sms_cmd7_cbc_bit9.setChecked(true);
                                        sms_cmd7_cbc_bit10.setChecked(false);
                                        sms_cmd7_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(true);
                                        sms_cmd7_cbc_bit9.setChecked(true);
                                        sms_cmd7_cbc_bit10.setChecked(false);
                                        sms_cmd7_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(false);
                                        sms_cmd7_cbc_bit9.setChecked(false);
                                        sms_cmd7_cbc_bit10.setChecked(true);
                                        sms_cmd7_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(true);
                                        sms_cmd7_cbc_bit9.setChecked(false);
                                        sms_cmd7_cbc_bit10.setChecked(true);
                                        sms_cmd7_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(false);
                                        sms_cmd7_cbc_bit9.setChecked(true);
                                        sms_cmd7_cbc_bit10.setChecked(true);
                                        sms_cmd7_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(true);
                                        sms_cmd7_cbc_bit9.setChecked(true);
                                        sms_cmd7_cbc_bit10.setChecked(true);
                                        sms_cmd7_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(false);
                                        sms_cmd7_cbc_bit9.setChecked(false);
                                        sms_cmd7_cbc_bit10.setChecked(false);
                                        sms_cmd7_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(true);
                                        sms_cmd7_cbc_bit9.setChecked(false);
                                        sms_cmd7_cbc_bit10.setChecked(false);
                                        sms_cmd7_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(false);
                                        sms_cmd7_cbc_bit9.setChecked(true);
                                        sms_cmd7_cbc_bit10.setChecked(false);
                                        sms_cmd7_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(true);
                                        sms_cmd7_cbc_bit9.setChecked(true);
                                        sms_cmd7_cbc_bit10.setChecked(false);
                                        sms_cmd7_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(false);
                                        sms_cmd7_cbc_bit9.setChecked(false);
                                        sms_cmd7_cbc_bit10.setChecked(true);
                                        sms_cmd7_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(true);
                                        sms_cmd7_cbc_bit9.setChecked(false);
                                        sms_cmd7_cbc_bit10.setChecked(true);
                                        sms_cmd7_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(false);
                                        sms_cmd7_cbc_bit9.setChecked(true);
                                        sms_cmd7_cbc_bit10.setChecked(true);
                                        sms_cmd7_cbc_bit11.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd7_cbc_bit8.setChecked(true);
                                        sms_cmd7_cbc_bit9.setChecked(true);
                                        sms_cmd7_cbc_bit10.setChecked(true);
                                        sms_cmd7_cbc_bit11.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexZero))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexOne))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexTwo))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexThree))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexFour))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexFive))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexSix))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexSeven))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexEight))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexNine))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexA))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexB))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexC))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexD))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexE))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(false);
                                    }
                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexF))
                                    {
                                        sms_cmd7_cbc_bit12.setChecked(true);
                                    }
                                    /*END OF CBC INPUTS*/
                                }
                                /*End of Main Card Inputs, Main Card Terminal Inputs, LOP Inputs, CBC Inputs Display*/

                                /*Main Card Outputs, Seven Segment Outputs & CBC Outputs Display*/
                                if(String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("8"))
                                {
                                    //toast("Yes");
                                    int length = theData[s].length();
                                    displayTimer(displayTime);
                                    mciTargetView.getParent().requestChildFocus(mcoTargetView,mcoTargetView);
                                    /*MAIN CARD OUTPUTS*/
                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd8_bit0.setChecked(false);
                                        cmd8_bit1.setChecked(false);
                                        cmd8_bit2.setChecked(false);
                                        cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd8_bit0.setChecked(true);
                                        cmd8_bit1.setChecked(false);
                                        cmd8_bit2.setChecked(false);
                                        cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd8_bit0.setChecked(false);
                                        cmd8_bit1.setChecked(true);
                                        cmd8_bit2.setChecked(false);
                                        cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd8_bit0.setChecked(true);
                                        cmd8_bit1.setChecked(true);
                                        cmd8_bit2.setChecked(false);
                                        cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd8_bit0.setChecked(false);
                                        cmd8_bit1.setChecked(false);
                                        cmd8_bit2.setChecked(true);
                                        cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd8_bit0.setChecked(true);
                                        cmd8_bit1.setChecked(false);
                                        cmd8_bit2.setChecked(true);
                                        cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd8_bit0.setChecked(false);
                                        cmd8_bit1.setChecked(true);
                                        cmd8_bit2.setChecked(true);
                                        cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd8_bit0.setChecked(true);
                                        cmd8_bit1.setChecked(true);
                                        cmd8_bit2.setChecked(true);
                                        cmd8_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd8_bit0.setChecked(false);
                                        cmd8_bit1.setChecked(false);
                                        cmd8_bit2.setChecked(false);
                                        cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd8_bit0.setChecked(true);
                                        cmd8_bit1.setChecked(false);
                                        cmd8_bit2.setChecked(false);
                                        cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexA))
                                    {
                                        cmd8_bit0.setChecked(false);
                                        cmd8_bit1.setChecked(true);
                                        cmd8_bit2.setChecked(false);
                                        cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexB))
                                    {
                                        cmd8_bit0.setChecked(true);
                                        cmd8_bit1.setChecked(true);
                                        cmd8_bit2.setChecked(false);
                                        cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexC))
                                    {
                                        cmd8_bit0.setChecked(false);
                                        cmd8_bit1.setChecked(false);
                                        cmd8_bit2.setChecked(true);
                                        cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexD))
                                    {
                                        cmd8_bit0.setChecked(true);
                                        cmd8_bit1.setChecked(false);
                                        cmd8_bit2.setChecked(true);
                                        cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexE))
                                    {
                                        cmd8_bit0.setChecked(false);
                                        cmd8_bit1.setChecked(true);
                                        cmd8_bit2.setChecked(true);
                                        cmd8_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexF))
                                    {
                                        cmd8_bit0.setChecked(true);
                                        cmd8_bit1.setChecked(true);
                                        cmd8_bit2.setChecked(true);
                                        cmd8_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd8_bit4.setChecked(false);
                                        cmd8_bit5.setChecked(false);
                                        cmd8_bit6.setChecked(false);
                                        cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd8_bit4.setChecked(true);
                                        cmd8_bit5.setChecked(false);
                                        cmd8_bit6.setChecked(false);
                                        cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd8_bit4.setChecked(false);
                                        cmd8_bit5.setChecked(true);
                                        cmd8_bit6.setChecked(false);
                                        cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd8_bit4.setChecked(true);
                                        cmd8_bit5.setChecked(true);
                                        cmd8_bit6.setChecked(false);
                                        cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd8_bit4.setChecked(false);
                                        cmd8_bit5.setChecked(false);
                                        cmd8_bit6.setChecked(true);
                                        cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd8_bit4.setChecked(true);
                                        cmd8_bit5.setChecked(false);
                                        cmd8_bit6.setChecked(true);
                                        cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd8_bit4.setChecked(false);
                                        cmd8_bit5.setChecked(true);
                                        cmd8_bit6.setChecked(true);
                                        cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd8_bit4.setChecked(true);
                                        cmd8_bit5.setChecked(true);
                                        cmd8_bit6.setChecked(true);
                                        cmd8_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd8_bit4.setChecked(false);
                                        cmd8_bit5.setChecked(false);
                                        cmd8_bit6.setChecked(false);
                                        cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd8_bit4.setChecked(true);
                                        cmd8_bit5.setChecked(false);
                                        cmd8_bit6.setChecked(false);
                                        cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexA))
                                    {
                                        cmd8_bit4.setChecked(false);
                                        cmd8_bit5.setChecked(true);
                                        cmd8_bit6.setChecked(false);
                                        cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexB))
                                    {
                                        cmd8_bit4.setChecked(true);
                                        cmd8_bit5.setChecked(true);
                                        cmd8_bit6.setChecked(false);
                                        cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexC))
                                    {
                                        cmd8_bit4.setChecked(false);
                                        cmd8_bit5.setChecked(false);
                                        cmd8_bit6.setChecked(true);
                                        cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexD))
                                    {
                                        cmd8_bit4.setChecked(true);
                                        cmd8_bit5.setChecked(false);
                                        cmd8_bit6.setChecked(true);
                                        cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexE))
                                    {
                                        cmd8_bit4.setChecked(false);
                                        cmd8_bit5.setChecked(true);
                                        cmd8_bit6.setChecked(true);
                                        cmd8_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexF))
                                    {
                                        cmd8_bit4.setChecked(true);
                                        cmd8_bit5.setChecked(true);
                                        cmd8_bit6.setChecked(true);
                                        cmd8_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd8_bit9.setChecked(false);
                                        cmd8_bit10.setChecked(false);
                                        cmd8_bit11.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd8_bit9.setChecked(false);
                                        cmd8_bit10.setChecked(false);
                                        cmd8_bit11.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd8_bit9.setChecked(true);
                                        cmd8_bit10.setChecked(false);
                                        cmd8_bit11.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd8_bit9.setChecked(true);
                                        cmd8_bit10.setChecked(false);
                                        cmd8_bit11.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd8_bit9.setChecked(false);
                                        cmd8_bit10.setChecked(true);
                                        cmd8_bit11.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd8_bit9.setChecked(false);
                                        cmd8_bit10.setChecked(true);
                                        cmd8_bit11.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd8_bit9.setChecked(true);
                                        cmd8_bit10.setChecked(true);
                                        cmd8_bit11.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd8_bit9.setChecked(true);
                                        cmd8_bit10.setChecked(true);
                                        cmd8_bit11.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd8_bit9.setChecked(false);
                                        cmd8_bit10.setChecked(false);
                                        cmd8_bit11.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd8_bit9.setChecked(false);
                                        cmd8_bit10.setChecked(false);
                                        cmd8_bit11.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexA))
                                    {
                                        cmd8_bit9.setChecked(true);
                                        cmd8_bit10.setChecked(false);
                                        cmd8_bit11.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexB))
                                    {
                                        cmd8_bit9.setChecked(true);
                                        cmd8_bit10.setChecked(false);
                                        cmd8_bit11.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexC))
                                    {
                                        cmd8_bit9.setChecked(false);
                                        cmd8_bit10.setChecked(true);
                                        cmd8_bit11.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexD))
                                    {
                                        cmd8_bit9.setChecked(false);
                                        cmd8_bit10.setChecked(true);
                                        cmd8_bit11.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexE))
                                    {
                                        cmd8_bit9.setChecked(true);
                                        cmd8_bit10.setChecked(true);
                                        cmd8_bit11.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexF))
                                    {
                                        cmd8_bit9.setChecked(true);
                                        cmd8_bit10.setChecked(true);
                                        cmd8_bit11.setChecked(true);

                                    }
                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexZero))
                                    {

                                        cmd8_bit12.setChecked(false);
                                        cmd8_bit11.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexOne))
                                    {

                                        cmd8_bit12.setChecked(true);
                                        cmd8_bit13.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexTwo))
                                    {

                                        cmd8_bit12.setChecked(false);
                                        cmd8_bit13.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexThree))
                                    {

                                        cmd8_bit12.setChecked(true);
                                        cmd8_bit13.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexFour))
                                    {

                                        cmd8_bit12.setChecked(false);
                                        cmd8_bit13.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexFive))
                                    {

                                        cmd8_bit12.setChecked(true);
                                        cmd8_bit13.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexSix))
                                    {

                                        cmd8_bit12.setChecked(false);
                                        cmd8_bit13.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexSeven))
                                    {

                                        cmd8_bit12.setChecked(true);
                                        cmd8_bit13.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexEight))
                                    {

                                        cmd8_bit12.setChecked(false);
                                        cmd8_bit13.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexNine))
                                    {

                                        cmd8_bit12.setChecked(true);
                                        cmd8_bit13.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexA))
                                    {

                                        cmd8_bit12.setChecked(false);
                                        cmd8_bit13.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexB))
                                    {

                                        cmd8_bit12.setChecked(true);
                                        cmd8_bit13.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexC))
                                    {

                                        cmd8_bit12.setChecked(false);
                                        cmd8_bit13.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexD))
                                    {

                                        cmd8_bit12.setChecked(true);
                                        cmd8_bit13.setChecked(false);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexE))
                                    {

                                        cmd8_bit12.setChecked(false);
                                        cmd8_bit13.setChecked(true);

                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexF))
                                    {

                                        cmd8_bit12.setChecked(true);
                                        cmd8_bit13.setChecked(true);

                                    }
                                    /*END OF MAIN CARD OUTPUTS*/

                                    /*CBC OUTPUTS*/
                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd8_cbc_bit0.setChecked(false);
                                        cmd8_cbc_bit1.setChecked(false);
                                        cmd8_cbc_bit2.setChecked(false);
                                        cmd8_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd8_cbc_bit0.setChecked(true);
                                        cmd8_cbc_bit1.setChecked(false);
                                        cmd8_cbc_bit2.setChecked(false);
                                        cmd8_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd8_cbc_bit0.setChecked(false);
                                        cmd8_cbc_bit1.setChecked(true);
                                        cmd8_cbc_bit2.setChecked(false);
                                        cmd8_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd8_cbc_bit0.setChecked(true);
                                        cmd8_cbc_bit1.setChecked(true);
                                        cmd8_cbc_bit2.setChecked(false);
                                        cmd8_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd8_cbc_bit0.setChecked(false);
                                        cmd8_cbc_bit1.setChecked(false);
                                        cmd8_cbc_bit2.setChecked(true);
                                        cmd8_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd8_cbc_bit0.setChecked(true);
                                        cmd8_cbc_bit1.setChecked(false);
                                        cmd8_cbc_bit2.setChecked(true);
                                        cmd8_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd8_cbc_bit0.setChecked(false);
                                        cmd8_cbc_bit1.setChecked(true);
                                        cmd8_cbc_bit2.setChecked(true);
                                        cmd8_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd8_cbc_bit0.setChecked(true);
                                        cmd8_cbc_bit1.setChecked(true);
                                        cmd8_cbc_bit2.setChecked(true);
                                        cmd8_cbc_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd8_cbc_bit0.setChecked(false);
                                        cmd8_cbc_bit1.setChecked(false);
                                        cmd8_cbc_bit2.setChecked(false);
                                        cmd8_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd8_cbc_bit0.setChecked(true);
                                        cmd8_cbc_bit1.setChecked(false);
                                        cmd8_cbc_bit2.setChecked(false);
                                        cmd8_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexA))
                                    {
                                        cmd8_cbc_bit0.setChecked(false);
                                        cmd8_cbc_bit1.setChecked(true);
                                        cmd8_cbc_bit2.setChecked(false);
                                        cmd8_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexB))
                                    {
                                        cmd8_cbc_bit0.setChecked(true);
                                        cmd8_cbc_bit1.setChecked(true);
                                        cmd8_cbc_bit2.setChecked(false);
                                        cmd8_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexC))
                                    {
                                        cmd8_cbc_bit0.setChecked(false);
                                        cmd8_cbc_bit1.setChecked(false);
                                        cmd8_cbc_bit2.setChecked(true);
                                        cmd8_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexD))
                                    {
                                        cmd8_cbc_bit0.setChecked(true);
                                        cmd8_cbc_bit1.setChecked(false);
                                        cmd8_cbc_bit2.setChecked(true);
                                        cmd8_cbc_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexE))
                                    {
                                        cmd8_cbc_bit0.setChecked(false);
                                        cmd8_cbc_bit1.setChecked(true);
                                        cmd8_cbc_bit2.setChecked(true);
                                        cmd8_cbc_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexF))
                                    {
                                        cmd8_cbc_bit0.setChecked(true);
                                        cmd8_cbc_bit1.setChecked(true);
                                        cmd8_cbc_bit2.setChecked(true);
                                        cmd8_cbc_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd8_cbc_bit4.setChecked(false);
                                        cmd8_cbc_bit5.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd8_cbc_bit4.setChecked(true);
                                        cmd8_cbc_bit5.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd8_cbc_bit4.setChecked(false);
                                        cmd8_cbc_bit5.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd8_cbc_bit4.setChecked(true);
                                        cmd8_cbc_bit5.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd8_cbc_bit4.setChecked(false);
                                        cmd8_cbc_bit5.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd8_cbc_bit4.setChecked(true);
                                        cmd8_cbc_bit5.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd8_cbc_bit4.setChecked(false);
                                        cmd8_cbc_bit5.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd8_cbc_bit4.setChecked(true);
                                        cmd8_cbc_bit5.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd8_cbc_bit4.setChecked(false);
                                        cmd8_cbc_bit5.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd8_cbc_bit4.setChecked(true);
                                        cmd8_cbc_bit5.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexA))
                                    {
                                        cmd8_cbc_bit4.setChecked(false);
                                        cmd8_cbc_bit5.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexB))
                                    {
                                        cmd8_cbc_bit4.setChecked(true);
                                        cmd8_cbc_bit5.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexC))
                                    {
                                        cmd8_cbc_bit4.setChecked(false);
                                        cmd8_cbc_bit5.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexD))
                                    {
                                        cmd8_cbc_bit4.setChecked(true);
                                        cmd8_cbc_bit5.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexE))
                                    {
                                        cmd8_cbc_bit4.setChecked(false);
                                        cmd8_cbc_bit5.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexF))
                                    {
                                        cmd8_cbc_bit4.setChecked(true);
                                        cmd8_cbc_bit5.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd8_cbc_bit8.setChecked(false);
                                        cmd8_cbc_bit9.setChecked(false);
                                        cmd8_cbc_bit10.setChecked(false);
                                        cmd8_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd8_cbc_bit8.setChecked(true);
                                        cmd8_cbc_bit9.setChecked(false);
                                        cmd8_cbc_bit10.setChecked(false);
                                        cmd8_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd8_cbc_bit8.setChecked(false);
                                        cmd8_cbc_bit9.setChecked(true);
                                        cmd8_cbc_bit10.setChecked(false);
                                        cmd8_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd8_cbc_bit8.setChecked(true);
                                        cmd8_cbc_bit9.setChecked(true);
                                        cmd8_cbc_bit10.setChecked(false);
                                        cmd8_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd8_cbc_bit8.setChecked(false);
                                        cmd8_cbc_bit9.setChecked(false);
                                        cmd8_cbc_bit10.setChecked(true);
                                        cmd8_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd8_cbc_bit8.setChecked(true);
                                        cmd8_cbc_bit9.setChecked(false);
                                        cmd8_cbc_bit10.setChecked(true);
                                        cmd8_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd8_cbc_bit8.setChecked(false);
                                        cmd8_cbc_bit9.setChecked(true);
                                        cmd8_cbc_bit10.setChecked(true);
                                        cmd8_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd8_cbc_bit8.setChecked(true);
                                        cmd8_cbc_bit9.setChecked(true);
                                        cmd8_cbc_bit10.setChecked(true);
                                        cmd8_cbc_bit11.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd8_cbc_bit8.setChecked(false);
                                        cmd8_cbc_bit9.setChecked(false);
                                        cmd8_cbc_bit10.setChecked(false);
                                        cmd8_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd8_cbc_bit8.setChecked(true);
                                        cmd8_cbc_bit9.setChecked(false);
                                        cmd8_cbc_bit10.setChecked(false);
                                        cmd8_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexA))
                                    {
                                        cmd8_cbc_bit8.setChecked(false);
                                        cmd8_cbc_bit9.setChecked(true);
                                        cmd8_cbc_bit10.setChecked(false);
                                        cmd8_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexB))
                                    {
                                        cmd8_cbc_bit8.setChecked(true);
                                        cmd8_cbc_bit9.setChecked(true);
                                        cmd8_cbc_bit10.setChecked(false);
                                        cmd8_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexC))
                                    {
                                        cmd8_cbc_bit8.setChecked(false);
                                        cmd8_cbc_bit9.setChecked(false);
                                        cmd8_cbc_bit10.setChecked(true);
                                        cmd8_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexD))
                                    {
                                        cmd8_cbc_bit8.setChecked(true);
                                        cmd8_cbc_bit9.setChecked(false);
                                        cmd8_cbc_bit10.setChecked(true);
                                        cmd8_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexE))
                                    {
                                        cmd8_cbc_bit8.setChecked(false);
                                        cmd8_cbc_bit9.setChecked(true);
                                        cmd8_cbc_bit10.setChecked(true);
                                        cmd8_cbc_bit11.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexF))
                                    {
                                        cmd8_cbc_bit8.setChecked(true);
                                        cmd8_cbc_bit9.setChecked(true);
                                        cmd8_cbc_bit10.setChecked(true);
                                        cmd8_cbc_bit11.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd8_cbc_bit12.setChecked(false);
                                        cmd8_cbc_bit13.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd8_cbc_bit12.setChecked(true);
                                        cmd8_cbc_bit13.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd8_cbc_bit12.setChecked(false);
                                        cmd8_cbc_bit13.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd8_cbc_bit12.setChecked(true);
                                        cmd8_cbc_bit13.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd8_cbc_bit12.setChecked(false);
                                        cmd8_cbc_bit13.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd8_cbc_bit12.setChecked(true);
                                        cmd8_cbc_bit13.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd8_cbc_bit12.setChecked(false);
                                        cmd8_cbc_bit13.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd8_cbc_bit12.setChecked(true);
                                        cmd8_cbc_bit13.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd8_cbc_bit12.setChecked(false);
                                        cmd8_cbc_bit13.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd8_cbc_bit12.setChecked(true);
                                        cmd8_cbc_bit13.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexA))
                                    {
                                        cmd8_cbc_bit12.setChecked(false);
                                        cmd8_cbc_bit13.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexB))
                                    {
                                        cmd8_cbc_bit12.setChecked(true);
                                        cmd8_cbc_bit13.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexC))
                                    {
                                        cmd8_cbc_bit12.setChecked(false);
                                        cmd8_cbc_bit13.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexD))
                                    {
                                        cmd8_cbc_bit12.setChecked(true);
                                        cmd8_cbc_bit13.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexE))
                                    {
                                        cmd8_cbc_bit12.setChecked(false);
                                        cmd8_cbc_bit13.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexF))
                                    {
                                        cmd8_cbc_bit12.setChecked(true);
                                        cmd8_cbc_bit13.setChecked(true);
                                    }
                                    /*END OF CBC OUTPUTS*/

                                    /*SEVEN SEGMENT DISPLAY*/
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexZero)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexZero)) )
                                    {
                                        displayNothing();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexThree)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexF)) )
                                    {
                                        displayZero_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexZero)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSix)) )
                                    {
                                        displayOne_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexFive)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexB)) )
                                    {
                                        displayTwo_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexFour)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexF)) )
                                    {
                                        displayThree_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSix)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSix)) )
                                    {
                                        displayFour_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSix)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexD)) )
                                    {
                                        displayFive_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSeven)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexD)) )
                                    {
                                        displaySix_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexZero)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSeven)) )
                                    {
                                        displaySeven_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSeven)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexF)) )
                                    {
                                        displayEight_H();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSix)) && (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSeven)) )
                                    {
                                        displayNine_H();
                                    }

                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexZero)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexZero)) )
                                    {
                                        displayNothing();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexThree)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexF)) )
                                    {
                                        displayZero();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexZero)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSix)) )
                                    {
                                        displayOne();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFive)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexB)) )
                                    {
                                        displayTwo();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFour)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexF)) )
                                    {
                                        displayThree();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSix)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSix)) )
                                    {
                                        displayFour();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSix)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexD)) )
                                    {
                                        displayFive();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSeven)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexD)) )
                                    {
                                        displaySix();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexZero)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSeven)) )
                                    {
                                        displaySeven();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSeven)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexF)) )
                                    {
                                        displayEight();
                                    }
                                    if ( (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSix)) && (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSeven)) )
                                    {
                                        displayNine();
                                    }
                                    /*END OF SEVEN SEGMENT DISPLAY*/
                                }
                                /*End of Main Card Outputs, Seven Segment Outputs & CBC Outputs Display*/
                                /*Floor Calls*/
                                if(String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("9"))
                                {
                                    int length = theData[s].length();
                                    displayTimer(displayTime);
                                    flrupView.getParent().requestChildFocus(flrupView,flrupView);
                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_u_bit31.setChecked(false);
                                        cmd9_u_bit30.setChecked(false);
                                        cmd9_u_bit29.setChecked(false);
                                        cmd9_u_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_u_bit31.setChecked(true);
                                        cmd9_u_bit30.setChecked(false);
                                        cmd9_u_bit29.setChecked(false);
                                        cmd9_u_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_u_bit31.setChecked(false);
                                        cmd9_u_bit30.setChecked(true);
                                        cmd9_u_bit29.setChecked(false);
                                        cmd9_u_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_u_bit31.setChecked(true);
                                        cmd9_u_bit30.setChecked(true);
                                        cmd9_u_bit29.setChecked(false);
                                        cmd9_u_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_u_bit31.setChecked(false);
                                        cmd9_u_bit30.setChecked(false);
                                        cmd9_u_bit29.setChecked(true);
                                        cmd9_u_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_u_bit31.setChecked(true);
                                        cmd9_u_bit30.setChecked(false);
                                        cmd9_u_bit29.setChecked(true);
                                        cmd9_u_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_u_bit31.setChecked(false);
                                        cmd9_u_bit30.setChecked(true);
                                        cmd9_u_bit29.setChecked(true);
                                        cmd9_u_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_u_bit31.setChecked(true);
                                        cmd9_u_bit30.setChecked(true);
                                        cmd9_u_bit29.setChecked(true);
                                        cmd9_u_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_u_bit31.setChecked(false);
                                        cmd9_u_bit30.setChecked(false);
                                        cmd9_u_bit29.setChecked(false);
                                        cmd9_u_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_u_bit31.setChecked(true);
                                        cmd9_u_bit30.setChecked(false);
                                        cmd9_u_bit29.setChecked(false);
                                        cmd9_u_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_u_bit31.setChecked(false);
                                        cmd9_u_bit30.setChecked(true);
                                        cmd9_u_bit29.setChecked(false);
                                        cmd9_u_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_u_bit31.setChecked(true);
                                        cmd9_u_bit30.setChecked(true);
                                        cmd9_u_bit29.setChecked(false);
                                        cmd9_u_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_u_bit31.setChecked(false);
                                        cmd9_u_bit30.setChecked(false);
                                        cmd9_u_bit29.setChecked(true);
                                        cmd9_u_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_u_bit31.setChecked(true);
                                        cmd9_u_bit30.setChecked(false);
                                        cmd9_u_bit29.setChecked(true);
                                        cmd9_u_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_u_bit31.setChecked(false);
                                        cmd9_u_bit30.setChecked(true);
                                        cmd9_u_bit29.setChecked(true);
                                        cmd9_u_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_u_bit31.setChecked(true);
                                        cmd9_u_bit30.setChecked(true);
                                        cmd9_u_bit29.setChecked(true);
                                        cmd9_u_bit28.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_u_bit27.setChecked(false);
                                        cmd9_u_bit26.setChecked(false);
                                        cmd9_u_bit25.setChecked(false);
                                        cmd9_u_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_u_bit27.setChecked(true);
                                        cmd9_u_bit26.setChecked(false);
                                        cmd9_u_bit25.setChecked(false);
                                        cmd9_u_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_u_bit27.setChecked(false);
                                        cmd9_u_bit26.setChecked(true);
                                        cmd9_u_bit25.setChecked(false);
                                        cmd9_u_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_u_bit27.setChecked(true);
                                        cmd9_u_bit26.setChecked(true);
                                        cmd9_u_bit25.setChecked(false);
                                        cmd9_u_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_u_bit27.setChecked(false);
                                        cmd9_u_bit26.setChecked(false);
                                        cmd9_u_bit25.setChecked(true);
                                        cmd9_u_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_u_bit27.setChecked(true);
                                        cmd9_u_bit26.setChecked(false);
                                        cmd9_u_bit25.setChecked(true);
                                        cmd9_u_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_u_bit27.setChecked(false);
                                        cmd9_u_bit26.setChecked(true);
                                        cmd9_u_bit25.setChecked(true);
                                        cmd9_u_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_u_bit27.setChecked(true);
                                        cmd9_u_bit26.setChecked(true);
                                        cmd9_u_bit25.setChecked(true);
                                        cmd9_u_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_u_bit27.setChecked(false);
                                        cmd9_u_bit26.setChecked(false);
                                        cmd9_u_bit25.setChecked(false);
                                        cmd9_u_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_u_bit27.setChecked(true);
                                        cmd9_u_bit26.setChecked(false);
                                        cmd9_u_bit25.setChecked(false);
                                        cmd9_u_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_u_bit27.setChecked(false);
                                        cmd9_u_bit26.setChecked(true);
                                        cmd9_u_bit25.setChecked(false);
                                        cmd9_u_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_u_bit27.setChecked(true);
                                        cmd9_u_bit26.setChecked(true);
                                        cmd9_u_bit25.setChecked(false);
                                        cmd9_u_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_u_bit27.setChecked(false);
                                        cmd9_u_bit26.setChecked(false);
                                        cmd9_u_bit25.setChecked(true);
                                        cmd9_u_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_u_bit27.setChecked(true);
                                        cmd9_u_bit26.setChecked(false);
                                        cmd9_u_bit25.setChecked(true);
                                        cmd9_u_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_u_bit27.setChecked(false);
                                        cmd9_u_bit26.setChecked(true);
                                        cmd9_u_bit25.setChecked(true);
                                        cmd9_u_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_u_bit27.setChecked(true);
                                        cmd9_u_bit26.setChecked(true);
                                        cmd9_u_bit25.setChecked(true);
                                        cmd9_u_bit24.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_u_bit23.setChecked(false);
                                        cmd9_u_bit22.setChecked(false);
                                        cmd9_u_bit21.setChecked(false);
                                        cmd9_u_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_u_bit23.setChecked(true);
                                        cmd9_u_bit22.setChecked(false);
                                        cmd9_u_bit21.setChecked(false);
                                        cmd9_u_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_u_bit23.setChecked(false);
                                        cmd9_u_bit22.setChecked(true);
                                        cmd9_u_bit21.setChecked(false);
                                        cmd9_u_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_u_bit23.setChecked(true);
                                        cmd9_u_bit22.setChecked(true);
                                        cmd9_u_bit21.setChecked(false);
                                        cmd9_u_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_u_bit23.setChecked(false);
                                        cmd9_u_bit22.setChecked(false);
                                        cmd9_u_bit21.setChecked(true);
                                        cmd9_u_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_u_bit23.setChecked(true);
                                        cmd9_u_bit22.setChecked(false);
                                        cmd9_u_bit21.setChecked(true);
                                        cmd9_u_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_u_bit23.setChecked(false);
                                        cmd9_u_bit22.setChecked(true);
                                        cmd9_u_bit21.setChecked(true);
                                        cmd9_u_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_u_bit23.setChecked(true);
                                        cmd9_u_bit22.setChecked(true);
                                        cmd9_u_bit21.setChecked(true);
                                        cmd9_u_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_u_bit23.setChecked(false);
                                        cmd9_u_bit22.setChecked(false);
                                        cmd9_u_bit21.setChecked(false);
                                        cmd9_u_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_u_bit23.setChecked(true);
                                        cmd9_u_bit22.setChecked(false);
                                        cmd9_u_bit21.setChecked(false);
                                        cmd9_u_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_u_bit23.setChecked(false);
                                        cmd9_u_bit22.setChecked(true);
                                        cmd9_u_bit21.setChecked(false);
                                        cmd9_u_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_u_bit23.setChecked(true);
                                        cmd9_u_bit22.setChecked(true);
                                        cmd9_u_bit21.setChecked(false);
                                        cmd9_u_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_u_bit23.setChecked(false);
                                        cmd9_u_bit22.setChecked(false);
                                        cmd9_u_bit21.setChecked(true);
                                        cmd9_u_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_u_bit23.setChecked(true);
                                        cmd9_u_bit22.setChecked(false);
                                        cmd9_u_bit21.setChecked(true);
                                        cmd9_u_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_u_bit23.setChecked(false);
                                        cmd9_u_bit22.setChecked(true);
                                        cmd9_u_bit21.setChecked(true);
                                        cmd9_u_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_u_bit23.setChecked(true);
                                        cmd9_u_bit22.setChecked(true);
                                        cmd9_u_bit21.setChecked(true);
                                        cmd9_u_bit20.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_u_bit19.setChecked(false);
                                        cmd9_u_bit18.setChecked(false);
                                        cmd9_u_bit17.setChecked(false);
                                        cmd9_u_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_u_bit19.setChecked(true);
                                        cmd9_u_bit18.setChecked(false);
                                        cmd9_u_bit17.setChecked(false);
                                        cmd9_u_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_u_bit19.setChecked(false);
                                        cmd9_u_bit18.setChecked(true);
                                        cmd9_u_bit17.setChecked(false);
                                        cmd9_u_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_u_bit19.setChecked(true);
                                        cmd9_u_bit18.setChecked(true);
                                        cmd9_u_bit17.setChecked(false);
                                        cmd9_u_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_u_bit19.setChecked(false);
                                        cmd9_u_bit18.setChecked(false);
                                        cmd9_u_bit17.setChecked(true);
                                        cmd9_u_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_u_bit19.setChecked(true);
                                        cmd9_u_bit18.setChecked(false);
                                        cmd9_u_bit17.setChecked(true);
                                        cmd9_u_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_u_bit19.setChecked(false);
                                        cmd9_u_bit18.setChecked(true);
                                        cmd9_u_bit17.setChecked(true);
                                        cmd9_u_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_u_bit19.setChecked(true);
                                        cmd9_u_bit18.setChecked(true);
                                        cmd9_u_bit17.setChecked(true);
                                        cmd9_u_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_u_bit19.setChecked(false);
                                        cmd9_u_bit18.setChecked(false);
                                        cmd9_u_bit17.setChecked(false);
                                        cmd9_u_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_u_bit19.setChecked(true);
                                        cmd9_u_bit18.setChecked(false);
                                        cmd9_u_bit17.setChecked(false);
                                        cmd9_u_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_u_bit19.setChecked(false);
                                        cmd9_u_bit18.setChecked(true);
                                        cmd9_u_bit17.setChecked(false);
                                        cmd9_u_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_u_bit19.setChecked(true);
                                        cmd9_u_bit18.setChecked(true);
                                        cmd9_u_bit17.setChecked(false);
                                        cmd9_u_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_u_bit19.setChecked(false);
                                        cmd9_u_bit18.setChecked(false);
                                        cmd9_u_bit17.setChecked(true);
                                        cmd9_u_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_u_bit19.setChecked(true);
                                        cmd9_u_bit18.setChecked(false);
                                        cmd9_u_bit17.setChecked(true);
                                        cmd9_u_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_u_bit19.setChecked(false);
                                        cmd9_u_bit18.setChecked(true);
                                        cmd9_u_bit17.setChecked(true);
                                        cmd9_u_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_u_bit19.setChecked(true);
                                        cmd9_u_bit18.setChecked(true);
                                        cmd9_u_bit17.setChecked(true);
                                        cmd9_u_bit16.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_u_bit15.setChecked(false);
                                        cmd9_u_bit14.setChecked(false);
                                        cmd9_u_bit13.setChecked(false);
                                        cmd9_u_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_u_bit15.setChecked(true);
                                        cmd9_u_bit14.setChecked(false);
                                        cmd9_u_bit13.setChecked(false);
                                        cmd9_u_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_u_bit15.setChecked(false);
                                        cmd9_u_bit14.setChecked(true);
                                        cmd9_u_bit13.setChecked(false);
                                        cmd9_u_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_u_bit15.setChecked(true);
                                        cmd9_u_bit14.setChecked(true);
                                        cmd9_u_bit13.setChecked(false);
                                        cmd9_u_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_u_bit15.setChecked(false);
                                        cmd9_u_bit14.setChecked(false);
                                        cmd9_u_bit13.setChecked(true);
                                        cmd9_u_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_u_bit15.setChecked(true);
                                        cmd9_u_bit14.setChecked(false);
                                        cmd9_u_bit13.setChecked(true);
                                        cmd9_u_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_u_bit15.setChecked(false);
                                        cmd9_u_bit14.setChecked(true);
                                        cmd9_u_bit13.setChecked(true);
                                        cmd9_u_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_u_bit15.setChecked(true);
                                        cmd9_u_bit14.setChecked(true);
                                        cmd9_u_bit13.setChecked(true);
                                        cmd9_u_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_u_bit15.setChecked(false);
                                        cmd9_u_bit14.setChecked(false);
                                        cmd9_u_bit13.setChecked(false);
                                        cmd9_u_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_u_bit15.setChecked(true);
                                        cmd9_u_bit14.setChecked(false);
                                        cmd9_u_bit13.setChecked(false);
                                        cmd9_u_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_u_bit15.setChecked(false);
                                        cmd9_u_bit14.setChecked(true);
                                        cmd9_u_bit13.setChecked(false);
                                        cmd9_u_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_u_bit15.setChecked(true);
                                        cmd9_u_bit14.setChecked(true);
                                        cmd9_u_bit13.setChecked(false);
                                        cmd9_u_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_u_bit15.setChecked(false);
                                        cmd9_u_bit14.setChecked(false);
                                        cmd9_u_bit13.setChecked(true);
                                        cmd9_u_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_u_bit15.setChecked(true);
                                        cmd9_u_bit14.setChecked(false);
                                        cmd9_u_bit13.setChecked(true);
                                        cmd9_u_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_u_bit15.setChecked(false);
                                        cmd9_u_bit14.setChecked(true);
                                        cmd9_u_bit13.setChecked(true);
                                        cmd9_u_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_u_bit15.setChecked(true);
                                        cmd9_u_bit14.setChecked(true);
                                        cmd9_u_bit13.setChecked(true);
                                        cmd9_u_bit12.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_u_bit11.setChecked(false);
                                        cmd9_u_bit10.setChecked(false);
                                        cmd9_u_bit9.setChecked(false);
                                        cmd9_u_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_u_bit11.setChecked(true);
                                        cmd9_u_bit10.setChecked(false);
                                        cmd9_u_bit9.setChecked(false);
                                        cmd9_u_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_u_bit11.setChecked(false);
                                        cmd9_u_bit10.setChecked(true);
                                        cmd9_u_bit9.setChecked(false);
                                        cmd9_u_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_u_bit11.setChecked(true);
                                        cmd9_u_bit10.setChecked(true);
                                        cmd9_u_bit9.setChecked(false);
                                        cmd9_u_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_u_bit11.setChecked(false);
                                        cmd9_u_bit10.setChecked(false);
                                        cmd9_u_bit9.setChecked(true);
                                        cmd9_u_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_u_bit11.setChecked(true);
                                        cmd9_u_bit10.setChecked(false);
                                        cmd9_u_bit9.setChecked(true);
                                        cmd9_u_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_u_bit11.setChecked(false);
                                        cmd9_u_bit10.setChecked(true);
                                        cmd9_u_bit9.setChecked(true);
                                        cmd9_u_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_u_bit11.setChecked(true);
                                        cmd9_u_bit10.setChecked(true);
                                        cmd9_u_bit9.setChecked(true);
                                        cmd9_u_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_u_bit11.setChecked(false);
                                        cmd9_u_bit10.setChecked(false);
                                        cmd9_u_bit9.setChecked(false);
                                        cmd9_u_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_u_bit11.setChecked(true);
                                        cmd9_u_bit10.setChecked(false);
                                        cmd9_u_bit9.setChecked(false);
                                        cmd9_u_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_u_bit11.setChecked(false);
                                        cmd9_u_bit10.setChecked(true);
                                        cmd9_u_bit9.setChecked(false);
                                        cmd9_u_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_u_bit11.setChecked(true);
                                        cmd9_u_bit10.setChecked(true);
                                        cmd9_u_bit9.setChecked(false);
                                        cmd9_u_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_u_bit11.setChecked(false);
                                        cmd9_u_bit10.setChecked(false);
                                        cmd9_u_bit9.setChecked(true);
                                        cmd9_u_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_u_bit11.setChecked(true);
                                        cmd9_u_bit10.setChecked(false);
                                        cmd9_u_bit9.setChecked(true);
                                        cmd9_u_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_u_bit11.setChecked(false);
                                        cmd9_u_bit10.setChecked(true);
                                        cmd9_u_bit9.setChecked(true);
                                        cmd9_u_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_u_bit11.setChecked(true);
                                        cmd9_u_bit10.setChecked(true);
                                        cmd9_u_bit9.setChecked(true);
                                        cmd9_u_bit8.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_u_bit4.setChecked(true);
                                        cmd9_u_bit5.setChecked(false);
                                        cmd9_u_bit6.setChecked(false);
                                        cmd9_u_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_u_bit4.setChecked(false);
                                        cmd9_u_bit5.setChecked(true);
                                        cmd9_u_bit6.setChecked(false);
                                        cmd9_u_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_u_bit4.setChecked(true);
                                        cmd9_u_bit5.setChecked(true);
                                        cmd9_u_bit6.setChecked(false);
                                        cmd9_u_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_u_bit4.setChecked(false);
                                        cmd9_u_bit5.setChecked(false);
                                        cmd9_u_bit6.setChecked(true);
                                        cmd9_u_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_u_bit4.setChecked(true);
                                        cmd9_u_bit5.setChecked(false);
                                        cmd9_u_bit6.setChecked(true);
                                        cmd9_u_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_u_bit4.setChecked(false);
                                        cmd9_u_bit5.setChecked(true);
                                        cmd9_u_bit6.setChecked(true);
                                        cmd9_u_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_u_bit4.setChecked(true);
                                        cmd9_u_bit5.setChecked(true);
                                        cmd9_u_bit6.setChecked(true);
                                        cmd9_u_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_u_bit4.setChecked(false);
                                        cmd9_u_bit5.setChecked(false);
                                        cmd9_u_bit6.setChecked(false);
                                        cmd9_u_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_u_bit4.setChecked(true);
                                        cmd9_u_bit5.setChecked(false);
                                        cmd9_u_bit6.setChecked(false);
                                        cmd9_u_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_u_bit4.setChecked(false);
                                        cmd9_u_bit5.setChecked(true);
                                        cmd9_u_bit6.setChecked(false);
                                        cmd9_u_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_u_bit4.setChecked(true);
                                        cmd9_u_bit5.setChecked(true);
                                        cmd9_u_bit6.setChecked(false);
                                        cmd9_u_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_u_bit4.setChecked(false);
                                        cmd9_u_bit5.setChecked(false);
                                        cmd9_u_bit6.setChecked(true);
                                        cmd9_u_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_u_bit4.setChecked(true);
                                        cmd9_u_bit5.setChecked(false);
                                        cmd9_u_bit6.setChecked(true);
                                        cmd9_u_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_u_bit4.setChecked(false);
                                        cmd9_u_bit5.setChecked(true);
                                        cmd9_u_bit6.setChecked(true);
                                        cmd9_u_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_u_bit4.setChecked(true);
                                        cmd9_u_bit5.setChecked(true);
                                        cmd9_u_bit6.setChecked(true);
                                        cmd9_u_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_u_bit0.setChecked(false);
                                        cmd9_u_bit1.setChecked(false);
                                        cmd9_u_bit2.setChecked(false);
                                        cmd9_u_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_u_bit0.setChecked(true);
                                        cmd9_u_bit1.setChecked(false);
                                        cmd9_u_bit2.setChecked(false);
                                        cmd9_u_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_u_bit0.setChecked(false);
                                        cmd9_u_bit1.setChecked(true);
                                        cmd9_u_bit2.setChecked(false);
                                        cmd9_u_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_u_bit0.setChecked(true);
                                        cmd9_u_bit1.setChecked(true);
                                        cmd9_u_bit2.setChecked(false);
                                        cmd9_u_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_u_bit0.setChecked(false);
                                        cmd9_u_bit1.setChecked(false);
                                        cmd9_u_bit2.setChecked(true);
                                        cmd9_u_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_u_bit0.setChecked(true);
                                        cmd9_u_bit1.setChecked(false);
                                        cmd9_u_bit2.setChecked(true);
                                        cmd9_u_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_u_bit0.setChecked(false);
                                        cmd9_u_bit1.setChecked(true);
                                        cmd9_u_bit2.setChecked(true);
                                        cmd9_u_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_u_bit0.setChecked(true);
                                        cmd9_u_bit1.setChecked(true);
                                        cmd9_u_bit2.setChecked(true);
                                        cmd9_u_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_u_bit0.setChecked(false);
                                        cmd9_u_bit1.setChecked(false);
                                        cmd9_u_bit2.setChecked(false);
                                        cmd9_u_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_u_bit0.setChecked(true);
                                        cmd9_u_bit1.setChecked(false);
                                        cmd9_u_bit2.setChecked(false);
                                        cmd9_u_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_u_bit0.setChecked(false);
                                        cmd9_u_bit1.setChecked(true);
                                        cmd9_u_bit2.setChecked(false);
                                        cmd9_u_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_u_bit0.setChecked(true);
                                        cmd9_u_bit1.setChecked(true);
                                        cmd9_u_bit2.setChecked(false);
                                        cmd9_u_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_u_bit0.setChecked(false);
                                        cmd9_u_bit1.setChecked(false);
                                        cmd9_u_bit2.setChecked(true);
                                        cmd9_u_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_u_bit0.setChecked(true);
                                        cmd9_u_bit1.setChecked(false);
                                        cmd9_u_bit2.setChecked(true);
                                        cmd9_u_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_u_bit0.setChecked(false);
                                        cmd9_u_bit1.setChecked(true);
                                        cmd9_u_bit2.setChecked(true);
                                        cmd9_u_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_u_bit0.setChecked(true);
                                        cmd9_u_bit1.setChecked(true);
                                        cmd9_u_bit2.setChecked(true);
                                        cmd9_u_bit3.setChecked(true);
                                    }
                                }
                                /*End of Floor Calls*/
                                floorDownAndCarCalls(receivedMessage);

                                if( (String.valueOf(theData[s].charAt(0)).equalsIgnoreCase("1")) && (String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("0")) )
                                {
                                    indications(receivedMessage);
                                }
                                if( (String.valueOf(theData[s].charAt(0)).equalsIgnoreCase("1")) && (String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("1")) )
                                {
                                    liftStatus(receivedMessage);
                                }
                                /*EMR Stop*/
                                if( (String.valueOf(theData[s].charAt(0)).equalsIgnoreCase("1")) && (String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("3")) )
                                {
                                    displayTimer(displayTime);
                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexOne))
                                    {
                                        AlertDialog dialog;
                                        ArrayList<String> ErrorStat = new ArrayList<String>();
                                        ErrorStat.clear();
                                        ErrorStat.add(emrStatus0);
                                        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setTitle("Emergency Stop Status");
                                        ListView listErrors     = new ListView(this);
                                        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ErrorStat);
                                        listErrors.setAdapter(modeAdapter);
                                        builder.setView(listErrors);
                                        builder.setPositiveButton("OK",new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1)
                                            {

                                            }
                                        });
                                        dialog = builder.create();
                                        dialog.show();
                                    }
                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexTwo))
                                    {
                                        AlertDialog dialog;
                                        ArrayList<String> ErrorStat = new ArrayList<String>();
                                        ErrorStat.clear();
                                        ErrorStat.add(emrStatus1);
                                        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setTitle("Emergency Stop Status");
                                        ListView listErrors     = new ListView(this);
                                        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ErrorStat);
                                        listErrors.setAdapter(modeAdapter);
                                        builder.setView(listErrors);
                                        builder.setPositiveButton("OK",new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1)
                                            {

                                            }
                                        });
                                        dialog = builder.create();
                                        dialog.show();
                                    }
                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexThree))
                                    {
                                        AlertDialog dialog;
                                        ArrayList<String> ErrorStat = new ArrayList<String>();
                                        ErrorStat.clear();
                                        ErrorStat.add(emrStatus0 + " & " + emrStatus1);
                                        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setTitle("Emergency Stop Status");
                                        ListView listErrors     = new ListView(this);
                                        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ErrorStat);
                                        listErrors.setAdapter(modeAdapter);
                                        builder.setView(listErrors);
                                        builder.setPositiveButton("OK",new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1)
                                            {

                                            }
                                        });
                                        dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                                /*End of EMR Stop*/
                                /*Module Status*/
                                if( (String.valueOf(theData[s].charAt(0)).equalsIgnoreCase("1")) && (String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("4")) )
                                {
                                    displayTimer(displayTime);
                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexOne))
                                    {
                                        AlertDialog dialog;
                                        ArrayList<String> ErrorStat = new ArrayList<String>();
                                        ErrorStat.clear();
                                        ErrorStat.add(moduleStatus);
                                        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        builder.setTitle("Module Status");
                                        ListView listErrors     = new ListView(this);
                                        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ErrorStat);
                                        listErrors.setAdapter(modeAdapter);
                                        builder.setView(listErrors);
                                        builder.setPositiveButton("OK",new DialogInterface.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(DialogInterface arg0, int arg1)
                                            {

                                            }
                                        });
                                        dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                                /*End of Module Status*/


                            }
                        }//end of if()
                    }//end of if{}
                }//end of for()

            }//end of if{}
        }
        catch (Exception e)
        {
            //toast(e.toString());
            adminText.setText(e.toString());
        }
    }//end of getReceivedSMS()

    public void floorDownAndCarCalls(String receivedMessage)
    {
        String[] array = receivedMessage.split(":",0);
        String theNumber=array[0];
        cellNumber          = theNumber.replace("+91","");
        extractedMessage    = array[1];
        //toast("NUMBER: "+cellNumber+" \nMESSAGE:"+extractedMessage);
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        List<String> THENUMBER = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ADMIN_CLIENT_DATA",null);
        THENUMBER.clear();
        if (cursor.moveToFirst())
        {
            do
            {
                THENUMBER.add(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NUMBER")));
                //toast(THENUMBER.toString());
            }while (cursor.moveToNext());
        }
        try
        {
            if (!THENUMBER.isEmpty())
            {
                for (int i = 0; i < THENUMBER.size(); i++) {

                    if (THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        AdminSMSReceiverControllerDB = new AdminSMSReceiverControllerDB(this);
                        theSqLiteDatabase = AdminSMSReceiverControllerDB.getReadableDatabase();
                        theQuery = "CREATE TABLE IF NOT EXISTS ADMIN_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
                        theSqLiteDatabase.execSQL(theQuery);
                        theSqLiteDatabase.execSQL("INSERT INTO ADMIN_SMS(DATE, ADDRESS, CONTENT) VALUES('" + date + "','" + cellNumber + "','" + extractedMessage + "')");
                        //Toast.makeText(getApplicationContext(), "DATA IS STORED",Toast.LENGTH_LONG).show();
                    }//end of if{}
                }//end of for()

                //INSERTING INTO DB AND THEN EXTRACTING IT TO DISPLAY ON UI
                for (int i = 0; i < THENUMBER.size(); i++)
                {
                    if (THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE DATE='"+date+"'",null);
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE ADDRESS='"+cellNumber+"'",null);
                        clientCursor = theSqLiteDatabase.rawQuery("SELECT * FROM ADMIN_SMS ORDER BY Id DESC LIMIT 1", null);
                        theContent = new ArrayList<String>();
                        theContent.clear();
                        if (clientCursor.moveToFirst())
                        {
                            do
                            {
                                theContent.add(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                                //toast(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                            } while (clientCursor.moveToNext());
                            int count = clientCursor.getCount();
                            String contentArray[] = new String[count];
                            for (int j = 0; j < contentArray.length; j++)
                            {
                                contentArray[j] = theContent.get(j);
                            }
                            StringBuffer sb0 = new StringBuffer();
                            for (int k = 0; k < contentArray.length; k++)
                            {
                                sb0.append(contentArray[k]);
                            }
                            countDown.setVisibility(View.VISIBLE);
                            String str1 = sb0.toString();
                            //adminText.setText(str1);
                            String decodeChoice = str1;
                            /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
                            String theData[] = new String[decodeChoice.length()];
                            theData = decodeChoice.split("#");
                            StringBuffer showData = new StringBuffer();
                            for (int s = 1; s < theData.length; s++)//length of array
                            {
                                /*FLOOR DOWN CALLS AND CAR CALLS*/
                                if(String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("9"))
                                {
                                    int length = theData[s].length();
                                    displayTimer(displayTime);
                                    //flrdnView.getParent().requestChildFocus(flrdnView,flrdnView);
                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_d_bit31.setChecked(false);
                                        cmd9_d_bit30.setChecked(false);
                                        cmd9_d_bit29.setChecked(false);
                                        cmd9_d_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_d_bit31.setChecked(true);
                                        cmd9_d_bit30.setChecked(false);
                                        cmd9_d_bit29.setChecked(false);
                                        cmd9_d_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_d_bit31.setChecked(false);
                                        cmd9_d_bit30.setChecked(true);
                                        cmd9_d_bit29.setChecked(false);
                                        cmd9_d_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_d_bit31.setChecked(true);
                                        cmd9_d_bit30.setChecked(true);
                                        cmd9_d_bit29.setChecked(false);
                                        cmd9_d_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_d_bit31.setChecked(false);
                                        cmd9_d_bit30.setChecked(false);
                                        cmd9_d_bit29.setChecked(true);
                                        cmd9_d_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_d_bit31.setChecked(true);
                                        cmd9_d_bit30.setChecked(false);
                                        cmd9_d_bit29.setChecked(true);
                                        cmd9_d_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_d_bit31.setChecked(false);
                                        cmd9_d_bit30.setChecked(true);
                                        cmd9_d_bit29.setChecked(true);
                                        cmd9_d_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_d_bit31.setChecked(true);
                                        cmd9_d_bit30.setChecked(true);
                                        cmd9_d_bit29.setChecked(true);
                                        cmd9_d_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_d_bit31.setChecked(false);
                                        cmd9_d_bit30.setChecked(false);
                                        cmd9_d_bit29.setChecked(false);
                                        cmd9_d_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_d_bit31.setChecked(true);
                                        cmd9_d_bit30.setChecked(false);
                                        cmd9_d_bit29.setChecked(false);
                                        cmd9_d_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_d_bit31.setChecked(false);
                                        cmd9_d_bit30.setChecked(true);
                                        cmd9_d_bit29.setChecked(false);
                                        cmd9_d_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_d_bit31.setChecked(true);
                                        cmd9_d_bit30.setChecked(true);
                                        cmd9_d_bit29.setChecked(false);
                                        cmd9_d_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_d_bit31.setChecked(false);
                                        cmd9_d_bit30.setChecked(false);
                                        cmd9_d_bit29.setChecked(true);
                                        cmd9_d_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_d_bit31.setChecked(true);
                                        cmd9_d_bit30.setChecked(false);
                                        cmd9_d_bit29.setChecked(true);
                                        cmd9_d_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_d_bit31.setChecked(false);
                                        cmd9_d_bit30.setChecked(true);
                                        cmd9_d_bit29.setChecked(true);
                                        cmd9_d_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_d_bit31.setChecked(true);
                                        cmd9_d_bit30.setChecked(true);
                                        cmd9_d_bit29.setChecked(true);
                                        cmd9_d_bit28.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_d_bit27.setChecked(false);
                                        cmd9_d_bit26.setChecked(false);
                                        cmd9_d_bit25.setChecked(false);
                                        cmd9_d_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_d_bit27.setChecked(true);
                                        cmd9_d_bit26.setChecked(false);
                                        cmd9_d_bit25.setChecked(false);
                                        cmd9_d_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_d_bit27.setChecked(false);
                                        cmd9_d_bit26.setChecked(true);
                                        cmd9_d_bit25.setChecked(false);
                                        cmd9_d_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_d_bit27.setChecked(true);
                                        cmd9_d_bit26.setChecked(true);
                                        cmd9_d_bit25.setChecked(false);
                                        cmd9_d_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_d_bit27.setChecked(false);
                                        cmd9_d_bit26.setChecked(false);
                                        cmd9_d_bit25.setChecked(true);
                                        cmd9_d_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_d_bit27.setChecked(true);
                                        cmd9_d_bit26.setChecked(false);
                                        cmd9_d_bit25.setChecked(true);
                                        cmd9_d_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_d_bit27.setChecked(false);
                                        cmd9_d_bit26.setChecked(true);
                                        cmd9_d_bit25.setChecked(true);
                                        cmd9_d_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_d_bit27.setChecked(true);
                                        cmd9_d_bit26.setChecked(true);
                                        cmd9_d_bit25.setChecked(true);
                                        cmd9_d_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_d_bit27.setChecked(false);
                                        cmd9_d_bit26.setChecked(false);
                                        cmd9_d_bit25.setChecked(false);
                                        cmd9_d_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_d_bit27.setChecked(true);
                                        cmd9_d_bit26.setChecked(false);
                                        cmd9_d_bit25.setChecked(false);
                                        cmd9_d_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_d_bit27.setChecked(false);
                                        cmd9_d_bit26.setChecked(true);
                                        cmd9_d_bit25.setChecked(false);
                                        cmd9_d_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_d_bit27.setChecked(true);
                                        cmd9_d_bit26.setChecked(true);
                                        cmd9_d_bit25.setChecked(false);
                                        cmd9_d_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_d_bit27.setChecked(false);
                                        cmd9_d_bit26.setChecked(false);
                                        cmd9_d_bit25.setChecked(true);
                                        cmd9_d_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_d_bit27.setChecked(true);
                                        cmd9_d_bit26.setChecked(false);
                                        cmd9_d_bit25.setChecked(true);
                                        cmd9_d_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_d_bit27.setChecked(false);
                                        cmd9_d_bit26.setChecked(true);
                                        cmd9_d_bit25.setChecked(true);
                                        cmd9_d_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_d_bit27.setChecked(true);
                                        cmd9_d_bit26.setChecked(true);
                                        cmd9_d_bit25.setChecked(true);
                                        cmd9_d_bit24.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_d_bit23.setChecked(false);
                                        cmd9_d_bit22.setChecked(false);
                                        cmd9_d_bit21.setChecked(false);
                                        cmd9_d_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_d_bit23.setChecked(true);
                                        cmd9_d_bit22.setChecked(false);
                                        cmd9_d_bit21.setChecked(false);
                                        cmd9_d_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_d_bit23.setChecked(false);
                                        cmd9_d_bit22.setChecked(true);
                                        cmd9_d_bit21.setChecked(false);
                                        cmd9_d_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_d_bit23.setChecked(true);
                                        cmd9_d_bit22.setChecked(true);
                                        cmd9_d_bit21.setChecked(false);
                                        cmd9_d_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_d_bit23.setChecked(false);
                                        cmd9_d_bit22.setChecked(false);
                                        cmd9_d_bit21.setChecked(true);
                                        cmd9_d_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_d_bit23.setChecked(true);
                                        cmd9_d_bit22.setChecked(false);
                                        cmd9_d_bit21.setChecked(true);
                                        cmd9_d_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_d_bit23.setChecked(false);
                                        cmd9_d_bit22.setChecked(true);
                                        cmd9_d_bit21.setChecked(true);
                                        cmd9_d_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_d_bit23.setChecked(true);
                                        cmd9_d_bit22.setChecked(true);
                                        cmd9_d_bit21.setChecked(true);
                                        cmd9_d_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_d_bit23.setChecked(false);
                                        cmd9_d_bit22.setChecked(false);
                                        cmd9_d_bit21.setChecked(false);
                                        cmd9_d_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_d_bit23.setChecked(true);
                                        cmd9_d_bit22.setChecked(false);
                                        cmd9_d_bit21.setChecked(false);
                                        cmd9_d_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_d_bit23.setChecked(false);
                                        cmd9_d_bit22.setChecked(true);
                                        cmd9_d_bit21.setChecked(false);
                                        cmd9_d_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_d_bit23.setChecked(true);
                                        cmd9_d_bit22.setChecked(true);
                                        cmd9_d_bit21.setChecked(false);
                                        cmd9_d_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_d_bit23.setChecked(false);
                                        cmd9_d_bit22.setChecked(false);
                                        cmd9_d_bit21.setChecked(true);
                                        cmd9_d_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_d_bit23.setChecked(true);
                                        cmd9_d_bit22.setChecked(false);
                                        cmd9_d_bit21.setChecked(true);
                                        cmd9_d_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_d_bit23.setChecked(false);
                                        cmd9_d_bit22.setChecked(true);
                                        cmd9_d_bit21.setChecked(true);
                                        cmd9_d_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_d_bit23.setChecked(true);
                                        cmd9_d_bit22.setChecked(true);
                                        cmd9_d_bit21.setChecked(true);
                                        cmd9_d_bit20.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_d_bit19.setChecked(false);
                                        cmd9_d_bit18.setChecked(false);
                                        cmd9_d_bit17.setChecked(false);
                                        cmd9_d_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_d_bit19.setChecked(true);
                                        cmd9_d_bit18.setChecked(false);
                                        cmd9_d_bit17.setChecked(false);
                                        cmd9_d_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_d_bit19.setChecked(false);
                                        cmd9_d_bit18.setChecked(true);
                                        cmd9_d_bit17.setChecked(false);
                                        cmd9_d_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_d_bit19.setChecked(true);
                                        cmd9_d_bit18.setChecked(true);
                                        cmd9_d_bit17.setChecked(false);
                                        cmd9_d_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_d_bit19.setChecked(false);
                                        cmd9_d_bit18.setChecked(false);
                                        cmd9_d_bit17.setChecked(true);
                                        cmd9_d_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_d_bit19.setChecked(true);
                                        cmd9_d_bit18.setChecked(false);
                                        cmd9_d_bit17.setChecked(true);
                                        cmd9_d_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_d_bit19.setChecked(false);
                                        cmd9_d_bit18.setChecked(true);
                                        cmd9_d_bit17.setChecked(true);
                                        cmd9_d_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_d_bit19.setChecked(true);
                                        cmd9_d_bit18.setChecked(true);
                                        cmd9_d_bit17.setChecked(true);
                                        cmd9_d_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_d_bit19.setChecked(false);
                                        cmd9_d_bit18.setChecked(false);
                                        cmd9_d_bit17.setChecked(false);
                                        cmd9_d_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_d_bit19.setChecked(true);
                                        cmd9_d_bit18.setChecked(false);
                                        cmd9_d_bit17.setChecked(false);
                                        cmd9_d_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_d_bit19.setChecked(false);
                                        cmd9_d_bit18.setChecked(true);
                                        cmd9_d_bit17.setChecked(false);
                                        cmd9_d_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_d_bit19.setChecked(true);
                                        cmd9_d_bit18.setChecked(true);
                                        cmd9_d_bit17.setChecked(false);
                                        cmd9_d_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_d_bit19.setChecked(false);
                                        cmd9_d_bit18.setChecked(false);
                                        cmd9_d_bit17.setChecked(true);
                                        cmd9_d_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_d_bit19.setChecked(true);
                                        cmd9_d_bit18.setChecked(false);
                                        cmd9_d_bit17.setChecked(true);
                                        cmd9_d_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_d_bit19.setChecked(false);
                                        cmd9_d_bit18.setChecked(true);
                                        cmd9_d_bit17.setChecked(true);
                                        cmd9_d_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_d_bit19.setChecked(true);
                                        cmd9_d_bit18.setChecked(true);
                                        cmd9_d_bit17.setChecked(true);
                                        cmd9_d_bit16.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_d_bit15.setChecked(false);
                                        cmd9_d_bit14.setChecked(false);
                                        cmd9_d_bit13.setChecked(false);
                                        cmd9_d_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_d_bit15.setChecked(true);
                                        cmd9_d_bit14.setChecked(false);
                                        cmd9_d_bit13.setChecked(false);
                                        cmd9_d_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_d_bit15.setChecked(false);
                                        cmd9_d_bit14.setChecked(true);
                                        cmd9_d_bit13.setChecked(false);
                                        cmd9_d_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_d_bit15.setChecked(true);
                                        cmd9_d_bit14.setChecked(true);
                                        cmd9_d_bit13.setChecked(false);
                                        cmd9_d_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_d_bit15.setChecked(false);
                                        cmd9_d_bit14.setChecked(false);
                                        cmd9_d_bit13.setChecked(true);
                                        cmd9_d_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_d_bit15.setChecked(true);
                                        cmd9_d_bit14.setChecked(false);
                                        cmd9_d_bit13.setChecked(true);
                                        cmd9_d_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_d_bit15.setChecked(false);
                                        cmd9_d_bit14.setChecked(true);
                                        cmd9_d_bit13.setChecked(true);
                                        cmd9_d_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_d_bit15.setChecked(true);
                                        cmd9_d_bit14.setChecked(true);
                                        cmd9_d_bit13.setChecked(true);
                                        cmd9_d_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_d_bit15.setChecked(false);
                                        cmd9_d_bit14.setChecked(false);
                                        cmd9_d_bit13.setChecked(false);
                                        cmd9_d_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_d_bit15.setChecked(true);
                                        cmd9_d_bit14.setChecked(false);
                                        cmd9_d_bit13.setChecked(false);
                                        cmd9_d_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_d_bit15.setChecked(false);
                                        cmd9_d_bit14.setChecked(true);
                                        cmd9_d_bit13.setChecked(false);
                                        cmd9_d_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_d_bit15.setChecked(true);
                                        cmd9_d_bit14.setChecked(true);
                                        cmd9_d_bit13.setChecked(false);
                                        cmd9_d_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_d_bit15.setChecked(false);
                                        cmd9_d_bit14.setChecked(false);
                                        cmd9_d_bit13.setChecked(true);
                                        cmd9_d_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_d_bit15.setChecked(true);
                                        cmd9_d_bit14.setChecked(false);
                                        cmd9_d_bit13.setChecked(true);
                                        cmd9_d_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_d_bit15.setChecked(false);
                                        cmd9_d_bit14.setChecked(true);
                                        cmd9_d_bit13.setChecked(true);
                                        cmd9_d_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_d_bit15.setChecked(true);
                                        cmd9_d_bit14.setChecked(true);
                                        cmd9_d_bit13.setChecked(true);
                                        cmd9_d_bit12.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_d_bit11.setChecked(false);
                                        cmd9_d_bit10.setChecked(false);
                                        cmd9_d_bit9.setChecked(false);
                                        cmd9_d_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_d_bit11.setChecked(true);
                                        cmd9_d_bit10.setChecked(false);
                                        cmd9_d_bit9.setChecked(false);
                                        cmd9_d_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_d_bit11.setChecked(false);
                                        cmd9_d_bit10.setChecked(true);
                                        cmd9_d_bit9.setChecked(false);
                                        cmd9_d_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_d_bit11.setChecked(true);
                                        cmd9_d_bit10.setChecked(true);
                                        cmd9_d_bit9.setChecked(false);
                                        cmd9_d_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_d_bit11.setChecked(false);
                                        cmd9_d_bit10.setChecked(false);
                                        cmd9_d_bit9.setChecked(true);
                                        cmd9_d_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_d_bit11.setChecked(true);
                                        cmd9_d_bit10.setChecked(false);
                                        cmd9_d_bit9.setChecked(true);
                                        cmd9_d_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_d_bit11.setChecked(false);
                                        cmd9_d_bit10.setChecked(true);
                                        cmd9_d_bit9.setChecked(true);
                                        cmd9_d_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_d_bit11.setChecked(true);
                                        cmd9_d_bit10.setChecked(true);
                                        cmd9_d_bit9.setChecked(true);
                                        cmd9_d_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_d_bit11.setChecked(false);
                                        cmd9_d_bit10.setChecked(false);
                                        cmd9_d_bit9.setChecked(false);
                                        cmd9_d_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_d_bit11.setChecked(true);
                                        cmd9_d_bit10.setChecked(false);
                                        cmd9_d_bit9.setChecked(false);
                                        cmd9_d_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_d_bit11.setChecked(false);
                                        cmd9_d_bit10.setChecked(true);
                                        cmd9_d_bit9.setChecked(false);
                                        cmd9_d_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_d_bit11.setChecked(true);
                                        cmd9_d_bit10.setChecked(true);
                                        cmd9_d_bit9.setChecked(false);
                                        cmd9_d_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_d_bit11.setChecked(false);
                                        cmd9_d_bit10.setChecked(false);
                                        cmd9_d_bit9.setChecked(true);
                                        cmd9_d_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_d_bit11.setChecked(true);
                                        cmd9_d_bit10.setChecked(false);
                                        cmd9_d_bit9.setChecked(true);
                                        cmd9_d_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_d_bit11.setChecked(false);
                                        cmd9_d_bit10.setChecked(true);
                                        cmd9_d_bit9.setChecked(true);
                                        cmd9_d_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_d_bit11.setChecked(true);
                                        cmd9_d_bit10.setChecked(true);
                                        cmd9_d_bit9.setChecked(true);
                                        cmd9_d_bit8.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_d_bit4.setChecked(true);
                                        cmd9_d_bit5.setChecked(false);
                                        cmd9_d_bit6.setChecked(false);
                                        cmd9_d_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_d_bit4.setChecked(false);
                                        cmd9_d_bit5.setChecked(true);
                                        cmd9_d_bit6.setChecked(false);
                                        cmd9_d_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_d_bit4.setChecked(true);
                                        cmd9_d_bit5.setChecked(true);
                                        cmd9_d_bit6.setChecked(false);
                                        cmd9_d_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_d_bit4.setChecked(false);
                                        cmd9_d_bit5.setChecked(false);
                                        cmd9_d_bit6.setChecked(true);
                                        cmd9_d_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_d_bit4.setChecked(true);
                                        cmd9_d_bit5.setChecked(false);
                                        cmd9_d_bit6.setChecked(true);
                                        cmd9_d_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_d_bit4.setChecked(false);
                                        cmd9_d_bit5.setChecked(true);
                                        cmd9_d_bit6.setChecked(true);
                                        cmd9_d_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_d_bit4.setChecked(true);
                                        cmd9_d_bit5.setChecked(true);
                                        cmd9_d_bit6.setChecked(true);
                                        cmd9_d_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_d_bit4.setChecked(false);
                                        cmd9_d_bit5.setChecked(false);
                                        cmd9_d_bit6.setChecked(false);
                                        cmd9_d_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_d_bit4.setChecked(true);
                                        cmd9_d_bit5.setChecked(false);
                                        cmd9_d_bit6.setChecked(false);
                                        cmd9_d_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_d_bit4.setChecked(false);
                                        cmd9_d_bit5.setChecked(true);
                                        cmd9_d_bit6.setChecked(false);
                                        cmd9_d_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_d_bit4.setChecked(true);
                                        cmd9_d_bit5.setChecked(true);
                                        cmd9_d_bit6.setChecked(false);
                                        cmd9_d_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_d_bit4.setChecked(false);
                                        cmd9_d_bit5.setChecked(false);
                                        cmd9_d_bit6.setChecked(true);
                                        cmd9_d_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_d_bit4.setChecked(true);
                                        cmd9_d_bit5.setChecked(false);
                                        cmd9_d_bit6.setChecked(true);
                                        cmd9_d_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_d_bit4.setChecked(false);
                                        cmd9_d_bit5.setChecked(true);
                                        cmd9_d_bit6.setChecked(true);
                                        cmd9_d_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_d_bit4.setChecked(true);
                                        cmd9_d_bit5.setChecked(true);
                                        cmd9_d_bit6.setChecked(true);
                                        cmd9_d_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_d_bit0.setChecked(false);
                                        cmd9_d_bit1.setChecked(false);
                                        cmd9_d_bit2.setChecked(false);
                                        cmd9_d_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_d_bit0.setChecked(true);
                                        cmd9_d_bit1.setChecked(false);
                                        cmd9_d_bit2.setChecked(false);
                                        cmd9_d_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_d_bit0.setChecked(false);
                                        cmd9_d_bit1.setChecked(true);
                                        cmd9_d_bit2.setChecked(false);
                                        cmd9_d_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_d_bit0.setChecked(true);
                                        cmd9_d_bit1.setChecked(true);
                                        cmd9_d_bit2.setChecked(false);
                                        cmd9_d_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_d_bit0.setChecked(false);
                                        cmd9_d_bit1.setChecked(false);
                                        cmd9_d_bit2.setChecked(true);
                                        cmd9_d_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_d_bit0.setChecked(true);
                                        cmd9_d_bit1.setChecked(false);
                                        cmd9_d_bit2.setChecked(true);
                                        cmd9_d_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_d_bit0.setChecked(false);
                                        cmd9_d_bit1.setChecked(true);
                                        cmd9_d_bit2.setChecked(true);
                                        cmd9_d_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_d_bit0.setChecked(true);
                                        cmd9_d_bit1.setChecked(true);
                                        cmd9_d_bit2.setChecked(true);
                                        cmd9_d_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_d_bit0.setChecked(false);
                                        cmd9_d_bit1.setChecked(false);
                                        cmd9_d_bit2.setChecked(false);
                                        cmd9_d_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_d_bit0.setChecked(true);
                                        cmd9_d_bit1.setChecked(false);
                                        cmd9_d_bit2.setChecked(false);
                                        cmd9_d_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_d_bit0.setChecked(false);
                                        cmd9_d_bit1.setChecked(true);
                                        cmd9_d_bit2.setChecked(false);
                                        cmd9_d_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_d_bit0.setChecked(true);
                                        cmd9_d_bit1.setChecked(true);
                                        cmd9_d_bit2.setChecked(false);
                                        cmd9_d_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_d_bit0.setChecked(false);
                                        cmd9_d_bit1.setChecked(false);
                                        cmd9_d_bit2.setChecked(true);
                                        cmd9_d_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_d_bit0.setChecked(true);
                                        cmd9_d_bit1.setChecked(false);
                                        cmd9_d_bit2.setChecked(true);
                                        cmd9_d_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_d_bit0.setChecked(false);
                                        cmd9_d_bit1.setChecked(true);
                                        cmd9_d_bit2.setChecked(true);
                                        cmd9_d_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_d_bit0.setChecked(true);
                                        cmd9_d_bit1.setChecked(true);
                                        cmd9_d_bit2.setChecked(true);
                                        cmd9_d_bit3.setChecked(true);
                                    }
                                    /*END OF FLOOR CALLS*/
                                    /*CAR CALLS*/
                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_c_bit31.setChecked(false);
                                        cmd9_c_bit30.setChecked(false);
                                        cmd9_c_bit29.setChecked(false);
                                        cmd9_c_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_c_bit31.setChecked(true);
                                        cmd9_c_bit30.setChecked(false);
                                        cmd9_c_bit29.setChecked(false);
                                        cmd9_c_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_c_bit31.setChecked(false);
                                        cmd9_c_bit30.setChecked(true);
                                        cmd9_c_bit29.setChecked(false);
                                        cmd9_c_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_c_bit31.setChecked(true);
                                        cmd9_c_bit30.setChecked(true);
                                        cmd9_c_bit29.setChecked(false);
                                        cmd9_c_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_c_bit31.setChecked(false);
                                        cmd9_c_bit30.setChecked(false);
                                        cmd9_c_bit29.setChecked(true);
                                        cmd9_c_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_c_bit31.setChecked(true);
                                        cmd9_c_bit30.setChecked(false);
                                        cmd9_c_bit29.setChecked(true);
                                        cmd9_c_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_c_bit31.setChecked(false);
                                        cmd9_c_bit30.setChecked(true);
                                        cmd9_c_bit29.setChecked(true);
                                        cmd9_c_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_c_bit31.setChecked(true);
                                        cmd9_c_bit30.setChecked(true);
                                        cmd9_c_bit29.setChecked(true);
                                        cmd9_c_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_c_bit31.setChecked(false);
                                        cmd9_c_bit30.setChecked(false);
                                        cmd9_c_bit29.setChecked(false);
                                        cmd9_c_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_c_bit31.setChecked(true);
                                        cmd9_c_bit30.setChecked(false);
                                        cmd9_c_bit29.setChecked(false);
                                        cmd9_c_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_c_bit31.setChecked(false);
                                        cmd9_c_bit30.setChecked(true);
                                        cmd9_c_bit29.setChecked(false);
                                        cmd9_c_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_c_bit31.setChecked(true);
                                        cmd9_c_bit30.setChecked(true);
                                        cmd9_c_bit29.setChecked(false);
                                        cmd9_c_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_c_bit31.setChecked(false);
                                        cmd9_c_bit30.setChecked(false);
                                        cmd9_c_bit29.setChecked(true);
                                        cmd9_c_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_c_bit31.setChecked(true);
                                        cmd9_c_bit30.setChecked(false);
                                        cmd9_c_bit29.setChecked(true);
                                        cmd9_c_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_c_bit31.setChecked(false);
                                        cmd9_c_bit30.setChecked(true);
                                        cmd9_c_bit29.setChecked(true);
                                        cmd9_c_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_c_bit31.setChecked(true);
                                        cmd9_c_bit30.setChecked(true);
                                        cmd9_c_bit29.setChecked(true);
                                        cmd9_c_bit28.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_c_bit27.setChecked(false);
                                        cmd9_c_bit26.setChecked(false);
                                        cmd9_c_bit25.setChecked(false);
                                        cmd9_c_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_c_bit27.setChecked(true);
                                        cmd9_c_bit26.setChecked(false);
                                        cmd9_c_bit25.setChecked(false);
                                        cmd9_c_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_c_bit27.setChecked(false);
                                        cmd9_c_bit26.setChecked(true);
                                        cmd9_c_bit25.setChecked(false);
                                        cmd9_c_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_c_bit27.setChecked(true);
                                        cmd9_c_bit26.setChecked(true);
                                        cmd9_c_bit25.setChecked(false);
                                        cmd9_c_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_c_bit27.setChecked(false);
                                        cmd9_c_bit26.setChecked(false);
                                        cmd9_c_bit25.setChecked(true);
                                        cmd9_c_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_c_bit27.setChecked(true);
                                        cmd9_c_bit26.setChecked(false);
                                        cmd9_c_bit25.setChecked(true);
                                        cmd9_c_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_c_bit27.setChecked(false);
                                        cmd9_c_bit26.setChecked(true);
                                        cmd9_c_bit25.setChecked(true);
                                        cmd9_c_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_c_bit27.setChecked(true);
                                        cmd9_c_bit26.setChecked(true);
                                        cmd9_c_bit25.setChecked(true);
                                        cmd9_c_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_c_bit27.setChecked(false);
                                        cmd9_c_bit26.setChecked(false);
                                        cmd9_c_bit25.setChecked(false);
                                        cmd9_c_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_c_bit27.setChecked(true);
                                        cmd9_c_bit26.setChecked(false);
                                        cmd9_c_bit25.setChecked(false);
                                        cmd9_c_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_c_bit27.setChecked(false);
                                        cmd9_c_bit26.setChecked(true);
                                        cmd9_c_bit25.setChecked(false);
                                        cmd9_c_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_c_bit27.setChecked(true);
                                        cmd9_c_bit26.setChecked(true);
                                        cmd9_c_bit25.setChecked(false);
                                        cmd9_c_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_c_bit27.setChecked(false);
                                        cmd9_c_bit26.setChecked(false);
                                        cmd9_c_bit25.setChecked(true);
                                        cmd9_c_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_c_bit27.setChecked(true);
                                        cmd9_c_bit26.setChecked(false);
                                        cmd9_c_bit25.setChecked(true);
                                        cmd9_c_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_c_bit27.setChecked(false);
                                        cmd9_c_bit26.setChecked(true);
                                        cmd9_c_bit25.setChecked(true);
                                        cmd9_c_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_c_bit27.setChecked(true);
                                        cmd9_c_bit26.setChecked(true);
                                        cmd9_c_bit25.setChecked(true);
                                        cmd9_c_bit24.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_c_bit23.setChecked(false);
                                        cmd9_c_bit22.setChecked(false);
                                        cmd9_c_bit21.setChecked(false);
                                        cmd9_c_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_c_bit23.setChecked(true);
                                        cmd9_c_bit22.setChecked(false);
                                        cmd9_c_bit21.setChecked(false);
                                        cmd9_c_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_c_bit23.setChecked(false);
                                        cmd9_c_bit22.setChecked(true);
                                        cmd9_c_bit21.setChecked(false);
                                        cmd9_c_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_c_bit23.setChecked(true);
                                        cmd9_c_bit22.setChecked(true);
                                        cmd9_c_bit21.setChecked(false);
                                        cmd9_c_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_c_bit23.setChecked(false);
                                        cmd9_c_bit22.setChecked(false);
                                        cmd9_c_bit21.setChecked(true);
                                        cmd9_c_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_c_bit23.setChecked(true);
                                        cmd9_c_bit22.setChecked(false);
                                        cmd9_c_bit21.setChecked(true);
                                        cmd9_c_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_c_bit23.setChecked(false);
                                        cmd9_c_bit22.setChecked(true);
                                        cmd9_c_bit21.setChecked(true);
                                        cmd9_c_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_c_bit23.setChecked(true);
                                        cmd9_c_bit22.setChecked(true);
                                        cmd9_c_bit21.setChecked(true);
                                        cmd9_c_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_c_bit23.setChecked(false);
                                        cmd9_c_bit22.setChecked(false);
                                        cmd9_c_bit21.setChecked(false);
                                        cmd9_c_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_c_bit23.setChecked(true);
                                        cmd9_c_bit22.setChecked(false);
                                        cmd9_c_bit21.setChecked(false);
                                        cmd9_c_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_c_bit23.setChecked(false);
                                        cmd9_c_bit22.setChecked(true);
                                        cmd9_c_bit21.setChecked(false);
                                        cmd9_c_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_c_bit23.setChecked(true);
                                        cmd9_c_bit22.setChecked(true);
                                        cmd9_c_bit21.setChecked(false);
                                        cmd9_c_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_c_bit23.setChecked(false);
                                        cmd9_c_bit22.setChecked(false);
                                        cmd9_c_bit21.setChecked(true);
                                        cmd9_c_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_c_bit23.setChecked(true);
                                        cmd9_c_bit22.setChecked(false);
                                        cmd9_c_bit21.setChecked(true);
                                        cmd9_c_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_c_bit23.setChecked(false);
                                        cmd9_c_bit22.setChecked(true);
                                        cmd9_c_bit21.setChecked(true);
                                        cmd9_c_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_c_bit23.setChecked(true);
                                        cmd9_c_bit22.setChecked(true);
                                        cmd9_c_bit21.setChecked(true);
                                        cmd9_c_bit20.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_c_bit19.setChecked(false);
                                        cmd9_c_bit18.setChecked(false);
                                        cmd9_c_bit17.setChecked(false);
                                        cmd9_c_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_c_bit19.setChecked(true);
                                        cmd9_c_bit18.setChecked(false);
                                        cmd9_c_bit17.setChecked(false);
                                        cmd9_c_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_c_bit19.setChecked(false);
                                        cmd9_c_bit18.setChecked(true);
                                        cmd9_c_bit17.setChecked(false);
                                        cmd9_c_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_c_bit19.setChecked(true);
                                        cmd9_c_bit18.setChecked(true);
                                        cmd9_c_bit17.setChecked(false);
                                        cmd9_c_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_c_bit19.setChecked(false);
                                        cmd9_c_bit18.setChecked(false);
                                        cmd9_c_bit17.setChecked(true);
                                        cmd9_c_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_c_bit19.setChecked(true);
                                        cmd9_c_bit18.setChecked(false);
                                        cmd9_c_bit17.setChecked(true);
                                        cmd9_c_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_c_bit19.setChecked(false);
                                        cmd9_c_bit18.setChecked(true);
                                        cmd9_c_bit17.setChecked(true);
                                        cmd9_c_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_c_bit19.setChecked(true);
                                        cmd9_c_bit18.setChecked(true);
                                        cmd9_c_bit17.setChecked(true);
                                        cmd9_c_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_c_bit19.setChecked(false);
                                        cmd9_c_bit18.setChecked(false);
                                        cmd9_c_bit17.setChecked(false);
                                        cmd9_c_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_c_bit19.setChecked(true);
                                        cmd9_c_bit18.setChecked(false);
                                        cmd9_c_bit17.setChecked(false);
                                        cmd9_c_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_c_bit19.setChecked(false);
                                        cmd9_c_bit18.setChecked(true);
                                        cmd9_c_bit17.setChecked(false);
                                        cmd9_c_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_c_bit19.setChecked(true);
                                        cmd9_c_bit18.setChecked(true);
                                        cmd9_c_bit17.setChecked(false);
                                        cmd9_c_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_c_bit19.setChecked(false);
                                        cmd9_c_bit18.setChecked(false);
                                        cmd9_c_bit17.setChecked(true);
                                        cmd9_c_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_c_bit19.setChecked(true);
                                        cmd9_c_bit18.setChecked(false);
                                        cmd9_c_bit17.setChecked(true);
                                        cmd9_c_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_c_bit19.setChecked(false);
                                        cmd9_c_bit18.setChecked(true);
                                        cmd9_c_bit17.setChecked(true);
                                        cmd9_c_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_c_bit19.setChecked(true);
                                        cmd9_c_bit18.setChecked(true);
                                        cmd9_c_bit17.setChecked(true);
                                        cmd9_c_bit16.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_c_bit15.setChecked(false);
                                        cmd9_c_bit14.setChecked(false);
                                        cmd9_c_bit13.setChecked(false);
                                        cmd9_c_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_c_bit15.setChecked(true);
                                        cmd9_c_bit14.setChecked(false);
                                        cmd9_c_bit13.setChecked(false);
                                        cmd9_c_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_c_bit15.setChecked(false);
                                        cmd9_c_bit14.setChecked(true);
                                        cmd9_c_bit13.setChecked(false);
                                        cmd9_c_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_c_bit15.setChecked(true);
                                        cmd9_c_bit14.setChecked(true);
                                        cmd9_c_bit13.setChecked(false);
                                        cmd9_c_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_c_bit15.setChecked(false);
                                        cmd9_c_bit14.setChecked(false);
                                        cmd9_c_bit13.setChecked(true);
                                        cmd9_c_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_c_bit15.setChecked(true);
                                        cmd9_c_bit14.setChecked(false);
                                        cmd9_c_bit13.setChecked(true);
                                        cmd9_c_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_c_bit15.setChecked(false);
                                        cmd9_c_bit14.setChecked(true);
                                        cmd9_c_bit13.setChecked(true);
                                        cmd9_c_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_c_bit15.setChecked(true);
                                        cmd9_c_bit14.setChecked(true);
                                        cmd9_c_bit13.setChecked(true);
                                        cmd9_c_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_c_bit15.setChecked(false);
                                        cmd9_c_bit14.setChecked(false);
                                        cmd9_c_bit13.setChecked(false);
                                        cmd9_c_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_c_bit15.setChecked(true);
                                        cmd9_c_bit14.setChecked(false);
                                        cmd9_c_bit13.setChecked(false);
                                        cmd9_c_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_c_bit15.setChecked(false);
                                        cmd9_c_bit14.setChecked(true);
                                        cmd9_c_bit13.setChecked(false);
                                        cmd9_c_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_c_bit15.setChecked(true);
                                        cmd9_c_bit14.setChecked(true);
                                        cmd9_c_bit13.setChecked(false);
                                        cmd9_c_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_c_bit15.setChecked(false);
                                        cmd9_c_bit14.setChecked(false);
                                        cmd9_c_bit13.setChecked(true);
                                        cmd9_c_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_c_bit15.setChecked(true);
                                        cmd9_c_bit14.setChecked(false);
                                        cmd9_c_bit13.setChecked(true);
                                        cmd9_c_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_c_bit15.setChecked(false);
                                        cmd9_c_bit14.setChecked(true);
                                        cmd9_c_bit13.setChecked(true);
                                        cmd9_c_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_c_bit15.setChecked(true);
                                        cmd9_c_bit14.setChecked(true);
                                        cmd9_c_bit13.setChecked(true);
                                        cmd9_c_bit12.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_c_bit11.setChecked(false);
                                        cmd9_c_bit10.setChecked(false);
                                        cmd9_c_bit9.setChecked(false);
                                        cmd9_c_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_c_bit11.setChecked(true);
                                        cmd9_c_bit10.setChecked(false);
                                        cmd9_c_bit9.setChecked(false);
                                        cmd9_c_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_c_bit11.setChecked(false);
                                        cmd9_c_bit10.setChecked(true);
                                        cmd9_c_bit9.setChecked(false);
                                        cmd9_c_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_c_bit11.setChecked(true);
                                        cmd9_c_bit10.setChecked(true);
                                        cmd9_c_bit9.setChecked(false);
                                        cmd9_c_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_c_bit11.setChecked(false);
                                        cmd9_c_bit10.setChecked(false);
                                        cmd9_c_bit9.setChecked(true);
                                        cmd9_c_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_c_bit11.setChecked(true);
                                        cmd9_c_bit10.setChecked(false);
                                        cmd9_c_bit9.setChecked(true);
                                        cmd9_c_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_c_bit11.setChecked(false);
                                        cmd9_c_bit10.setChecked(true);
                                        cmd9_c_bit9.setChecked(true);
                                        cmd9_c_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_c_bit11.setChecked(true);
                                        cmd9_c_bit10.setChecked(true);
                                        cmd9_c_bit9.setChecked(true);
                                        cmd9_c_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_c_bit11.setChecked(false);
                                        cmd9_c_bit10.setChecked(false);
                                        cmd9_c_bit9.setChecked(false);
                                        cmd9_c_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_c_bit11.setChecked(true);
                                        cmd9_c_bit10.setChecked(false);
                                        cmd9_c_bit9.setChecked(false);
                                        cmd9_c_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_c_bit11.setChecked(false);
                                        cmd9_c_bit10.setChecked(true);
                                        cmd9_c_bit9.setChecked(false);
                                        cmd9_c_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_c_bit11.setChecked(true);
                                        cmd9_c_bit10.setChecked(true);
                                        cmd9_c_bit9.setChecked(false);
                                        cmd9_c_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_c_bit11.setChecked(false);
                                        cmd9_c_bit10.setChecked(false);
                                        cmd9_c_bit9.setChecked(true);
                                        cmd9_c_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_c_bit11.setChecked(true);
                                        cmd9_c_bit10.setChecked(false);
                                        cmd9_c_bit9.setChecked(true);
                                        cmd9_c_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_c_bit11.setChecked(false);
                                        cmd9_c_bit10.setChecked(true);
                                        cmd9_c_bit9.setChecked(true);
                                        cmd9_c_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_c_bit11.setChecked(true);
                                        cmd9_c_bit10.setChecked(true);
                                        cmd9_c_bit9.setChecked(true);
                                        cmd9_c_bit8.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_c_bit4.setChecked(true);
                                        cmd9_c_bit5.setChecked(false);
                                        cmd9_c_bit6.setChecked(false);
                                        cmd9_c_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_c_bit4.setChecked(false);
                                        cmd9_c_bit5.setChecked(true);
                                        cmd9_c_bit6.setChecked(false);
                                        cmd9_c_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_c_bit4.setChecked(true);
                                        cmd9_c_bit5.setChecked(true);
                                        cmd9_c_bit6.setChecked(false);
                                        cmd9_c_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_c_bit4.setChecked(false);
                                        cmd9_c_bit5.setChecked(false);
                                        cmd9_c_bit6.setChecked(true);
                                        cmd9_c_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_c_bit4.setChecked(true);
                                        cmd9_c_bit5.setChecked(false);
                                        cmd9_c_bit6.setChecked(true);
                                        cmd9_c_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_c_bit4.setChecked(false);
                                        cmd9_c_bit5.setChecked(true);
                                        cmd9_c_bit6.setChecked(true);
                                        cmd9_c_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_c_bit4.setChecked(true);
                                        cmd9_c_bit5.setChecked(true);
                                        cmd9_c_bit6.setChecked(true);
                                        cmd9_c_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_c_bit4.setChecked(false);
                                        cmd9_c_bit5.setChecked(false);
                                        cmd9_c_bit6.setChecked(false);
                                        cmd9_c_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_c_bit4.setChecked(true);
                                        cmd9_c_bit5.setChecked(false);
                                        cmd9_c_bit6.setChecked(false);
                                        cmd9_c_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_c_bit4.setChecked(false);
                                        cmd9_c_bit5.setChecked(true);
                                        cmd9_c_bit6.setChecked(false);
                                        cmd9_c_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_c_bit4.setChecked(true);
                                        cmd9_c_bit5.setChecked(true);
                                        cmd9_c_bit6.setChecked(false);
                                        cmd9_c_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_c_bit4.setChecked(false);
                                        cmd9_c_bit5.setChecked(false);
                                        cmd9_c_bit6.setChecked(true);
                                        cmd9_c_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_c_bit4.setChecked(true);
                                        cmd9_c_bit5.setChecked(false);
                                        cmd9_c_bit6.setChecked(true);
                                        cmd9_c_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_c_bit4.setChecked(false);
                                        cmd9_c_bit5.setChecked(true);
                                        cmd9_c_bit6.setChecked(true);
                                        cmd9_c_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_c_bit4.setChecked(true);
                                        cmd9_c_bit5.setChecked(true);
                                        cmd9_c_bit6.setChecked(true);
                                        cmd9_c_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_c_bit0.setChecked(false);
                                        cmd9_c_bit1.setChecked(false);
                                        cmd9_c_bit2.setChecked(false);
                                        cmd9_c_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_c_bit0.setChecked(true);
                                        cmd9_c_bit1.setChecked(false);
                                        cmd9_c_bit2.setChecked(false);
                                        cmd9_c_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_c_bit0.setChecked(false);
                                        cmd9_c_bit1.setChecked(true);
                                        cmd9_c_bit2.setChecked(false);
                                        cmd9_c_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_c_bit0.setChecked(true);
                                        cmd9_c_bit1.setChecked(true);
                                        cmd9_c_bit2.setChecked(false);
                                        cmd9_c_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_c_bit0.setChecked(false);
                                        cmd9_c_bit1.setChecked(false);
                                        cmd9_c_bit2.setChecked(true);
                                        cmd9_c_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_c_bit0.setChecked(true);
                                        cmd9_c_bit1.setChecked(false);
                                        cmd9_c_bit2.setChecked(true);
                                        cmd9_c_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_c_bit0.setChecked(false);
                                        cmd9_c_bit1.setChecked(true);
                                        cmd9_c_bit2.setChecked(true);
                                        cmd9_c_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_c_bit0.setChecked(true);
                                        cmd9_c_bit1.setChecked(true);
                                        cmd9_c_bit2.setChecked(true);
                                        cmd9_c_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_c_bit0.setChecked(false);
                                        cmd9_c_bit1.setChecked(false);
                                        cmd9_c_bit2.setChecked(false);
                                        cmd9_c_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_c_bit0.setChecked(true);
                                        cmd9_c_bit1.setChecked(false);
                                        cmd9_c_bit2.setChecked(false);
                                        cmd9_c_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_c_bit0.setChecked(false);
                                        cmd9_c_bit1.setChecked(true);
                                        cmd9_c_bit2.setChecked(false);
                                        cmd9_c_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_c_bit0.setChecked(true);
                                        cmd9_c_bit1.setChecked(true);
                                        cmd9_c_bit2.setChecked(false);
                                        cmd9_c_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_c_bit0.setChecked(false);
                                        cmd9_c_bit1.setChecked(false);
                                        cmd9_c_bit2.setChecked(true);
                                        cmd9_c_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_c_bit0.setChecked(true);
                                        cmd9_c_bit1.setChecked(false);
                                        cmd9_c_bit2.setChecked(true);
                                        cmd9_c_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_c_bit0.setChecked(false);
                                        cmd9_c_bit1.setChecked(true);
                                        cmd9_c_bit2.setChecked(true);
                                        cmd9_c_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_c_bit0.setChecked(true);
                                        cmd9_c_bit1.setChecked(true);
                                        cmd9_c_bit2.setChecked(true);
                                        cmd9_c_bit3.setChecked(true);
                                    }
                                    /*END OF CAR CALLS*/
                                }
                                /*END OF FLOOR DOWN CALLS AND CAR CALLS*/
                            }
                        }//end of if()
                    }//end of if{}
                }//end of for()
            }//end of if{}
        }
        catch (Exception e)
        {
            //toast(e.toString());
            adminText.setText(e.toString());
        }
    }//end of floorDownAndCarCalls

    public void indications(String receivedMessage)
    {
        String[] array = receivedMessage.split(":",0);
        String theNumber=array[0];
        cellNumber          = theNumber.replace("+91","");
        extractedMessage    = array[1];
        //toast("NUMBER: "+cellNumber+" \nMESSAGE:"+extractedMessage);
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        List<String> THENUMBER = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ADMIN_CLIENT_DATA",null);
        THENUMBER.clear();
        if (cursor.moveToFirst())
        {
            do
            {
                THENUMBER.add(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NUMBER")));
                //toast(THENUMBER.toString());
            }while (cursor.moveToNext());
        }
        try
        {
            if (!THENUMBER.isEmpty())
            {
                for (int i = 0; i < THENUMBER.size(); i++) {

                    if (THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        AdminSMSReceiverControllerDB = new AdminSMSReceiverControllerDB(this);
                        theSqLiteDatabase = AdminSMSReceiverControllerDB.getReadableDatabase();
                        theQuery = "CREATE TABLE IF NOT EXISTS ADMIN_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
                        theSqLiteDatabase.execSQL(theQuery);
                        theSqLiteDatabase.execSQL("INSERT INTO ADMIN_SMS(DATE, ADDRESS, CONTENT) VALUES('" + date + "','" + cellNumber + "','" + extractedMessage + "')");
                        //Toast.makeText(getApplicationContext(), "DATA IS STORED",Toast.LENGTH_LONG).show();
                    }//end of if{}
                }//end of for()

                //INSERTING INTO DB AND THEN EXTRACTING IT TO DISPLAY ON UI
                for (int i = 0; i < THENUMBER.size(); i++)
                {
                    if (THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE DATE='"+date+"'",null);
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE ADDRESS='"+cellNumber+"'",null);
                        clientCursor = theSqLiteDatabase.rawQuery("SELECT * FROM ADMIN_SMS ORDER BY Id DESC LIMIT 1", null);
                        theContent = new ArrayList<String>();
                        theContent.clear();
                        if (clientCursor.moveToFirst())
                        {
                            do
                            {
                                theContent.add(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                                //toast(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                            } while (clientCursor.moveToNext());
                            int count = clientCursor.getCount();
                            String contentArray[] = new String[count];
                            for (int j = 0; j < contentArray.length; j++)
                            {
                                contentArray[j] = theContent.get(j);
                            }
                            StringBuffer sb0 = new StringBuffer();
                            for (int k = 0; k < contentArray.length; k++)
                            {
                                sb0.append(contentArray[k]);
                            }
                            countDown.setVisibility(View.VISIBLE);
                            String str1 = sb0.toString();
                            //adminText.setText(str1);
                            String decodeChoice = str1;
                            /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
                            String theData[] = new String[decodeChoice.length()];
                            theData = decodeChoice.split("#");
                            StringBuffer showData = new StringBuffer();
                            for (int s = 1; s < theData.length; s++)//length of array
                            {
                                if( (String.valueOf(theData[s].charAt(0)).equalsIgnoreCase("1")) && (String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("0")) )
                                {
                                    displayTimer(displayTime);
                                    indicaTargetView.getParent().requestChildFocus(indicaTargetView,indicaTargetView);
                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ui_bit31.setChecked(false);
                                        cmd9_ui_bit30.setChecked(false);
                                        cmd9_ui_bit29.setChecked(false);
                                        cmd9_ui_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ui_bit31.setChecked(true);
                                        cmd9_ui_bit30.setChecked(false);
                                        cmd9_ui_bit29.setChecked(false);
                                        cmd9_ui_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ui_bit31.setChecked(false);
                                        cmd9_ui_bit30.setChecked(true);
                                        cmd9_ui_bit29.setChecked(false);
                                        cmd9_ui_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ui_bit31.setChecked(true);
                                        cmd9_ui_bit30.setChecked(true);
                                        cmd9_ui_bit29.setChecked(false);
                                        cmd9_ui_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ui_bit31.setChecked(false);
                                        cmd9_ui_bit30.setChecked(false);
                                        cmd9_ui_bit29.setChecked(true);
                                        cmd9_ui_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ui_bit31.setChecked(true);
                                        cmd9_ui_bit30.setChecked(false);
                                        cmd9_ui_bit29.setChecked(true);
                                        cmd9_ui_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ui_bit31.setChecked(false);
                                        cmd9_ui_bit30.setChecked(true);
                                        cmd9_ui_bit29.setChecked(true);
                                        cmd9_ui_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ui_bit31.setChecked(true);
                                        cmd9_ui_bit30.setChecked(true);
                                        cmd9_ui_bit29.setChecked(true);
                                        cmd9_ui_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ui_bit31.setChecked(false);
                                        cmd9_ui_bit30.setChecked(false);
                                        cmd9_ui_bit29.setChecked(false);
                                        cmd9_ui_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ui_bit31.setChecked(true);
                                        cmd9_ui_bit30.setChecked(false);
                                        cmd9_ui_bit29.setChecked(false);
                                        cmd9_ui_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ui_bit31.setChecked(false);
                                        cmd9_ui_bit30.setChecked(true);
                                        cmd9_ui_bit29.setChecked(false);
                                        cmd9_ui_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ui_bit31.setChecked(true);
                                        cmd9_ui_bit30.setChecked(true);
                                        cmd9_ui_bit29.setChecked(false);
                                        cmd9_ui_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ui_bit31.setChecked(false);
                                        cmd9_ui_bit30.setChecked(false);
                                        cmd9_ui_bit29.setChecked(true);
                                        cmd9_ui_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ui_bit31.setChecked(true);
                                        cmd9_ui_bit30.setChecked(false);
                                        cmd9_ui_bit29.setChecked(true);
                                        cmd9_ui_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ui_bit31.setChecked(false);
                                        cmd9_ui_bit30.setChecked(true);
                                        cmd9_ui_bit29.setChecked(true);
                                        cmd9_ui_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(3)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ui_bit31.setChecked(true);
                                        cmd9_ui_bit30.setChecked(true);
                                        cmd9_ui_bit29.setChecked(true);
                                        cmd9_ui_bit28.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ui_bit27.setChecked(false);
                                        cmd9_ui_bit26.setChecked(false);
                                        cmd9_ui_bit25.setChecked(false);
                                        cmd9_ui_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ui_bit27.setChecked(true);
                                        cmd9_ui_bit26.setChecked(false);
                                        cmd9_ui_bit25.setChecked(false);
                                        cmd9_ui_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ui_bit27.setChecked(false);
                                        cmd9_ui_bit26.setChecked(true);
                                        cmd9_ui_bit25.setChecked(false);
                                        cmd9_ui_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ui_bit27.setChecked(true);
                                        cmd9_ui_bit26.setChecked(true);
                                        cmd9_ui_bit25.setChecked(false);
                                        cmd9_ui_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ui_bit27.setChecked(false);
                                        cmd9_ui_bit26.setChecked(false);
                                        cmd9_ui_bit25.setChecked(true);
                                        cmd9_ui_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ui_bit27.setChecked(true);
                                        cmd9_ui_bit26.setChecked(false);
                                        cmd9_ui_bit25.setChecked(true);
                                        cmd9_ui_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ui_bit27.setChecked(false);
                                        cmd9_ui_bit26.setChecked(true);
                                        cmd9_ui_bit25.setChecked(true);
                                        cmd9_ui_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ui_bit27.setChecked(true);
                                        cmd9_ui_bit26.setChecked(true);
                                        cmd9_ui_bit25.setChecked(true);
                                        cmd9_ui_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ui_bit27.setChecked(false);
                                        cmd9_ui_bit26.setChecked(false);
                                        cmd9_ui_bit25.setChecked(false);
                                        cmd9_ui_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ui_bit27.setChecked(true);
                                        cmd9_ui_bit26.setChecked(false);
                                        cmd9_ui_bit25.setChecked(false);
                                        cmd9_ui_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ui_bit27.setChecked(false);
                                        cmd9_ui_bit26.setChecked(true);
                                        cmd9_ui_bit25.setChecked(false);
                                        cmd9_ui_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ui_bit27.setChecked(true);
                                        cmd9_ui_bit26.setChecked(true);
                                        cmd9_ui_bit25.setChecked(false);
                                        cmd9_ui_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ui_bit27.setChecked(false);
                                        cmd9_ui_bit26.setChecked(false);
                                        cmd9_ui_bit25.setChecked(true);
                                        cmd9_ui_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ui_bit27.setChecked(true);
                                        cmd9_ui_bit26.setChecked(false);
                                        cmd9_ui_bit25.setChecked(true);
                                        cmd9_ui_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ui_bit27.setChecked(false);
                                        cmd9_ui_bit26.setChecked(true);
                                        cmd9_ui_bit25.setChecked(true);
                                        cmd9_ui_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(4)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ui_bit27.setChecked(true);
                                        cmd9_ui_bit26.setChecked(true);
                                        cmd9_ui_bit25.setChecked(true);
                                        cmd9_ui_bit24.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ui_bit23.setChecked(false);
                                        cmd9_ui_bit22.setChecked(false);
                                        cmd9_ui_bit21.setChecked(false);
                                        cmd9_ui_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ui_bit23.setChecked(true);
                                        cmd9_ui_bit22.setChecked(false);
                                        cmd9_ui_bit21.setChecked(false);
                                        cmd9_ui_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ui_bit23.setChecked(false);
                                        cmd9_ui_bit22.setChecked(true);
                                        cmd9_ui_bit21.setChecked(false);
                                        cmd9_ui_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ui_bit23.setChecked(true);
                                        cmd9_ui_bit22.setChecked(true);
                                        cmd9_ui_bit21.setChecked(false);
                                        cmd9_ui_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ui_bit23.setChecked(false);
                                        cmd9_ui_bit22.setChecked(false);
                                        cmd9_ui_bit21.setChecked(true);
                                        cmd9_ui_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ui_bit23.setChecked(true);
                                        cmd9_ui_bit22.setChecked(false);
                                        cmd9_ui_bit21.setChecked(true);
                                        cmd9_ui_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ui_bit23.setChecked(false);
                                        cmd9_ui_bit22.setChecked(true);
                                        cmd9_ui_bit21.setChecked(true);
                                        cmd9_ui_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ui_bit23.setChecked(true);
                                        cmd9_ui_bit22.setChecked(true);
                                        cmd9_ui_bit21.setChecked(true);
                                        cmd9_ui_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ui_bit23.setChecked(false);
                                        cmd9_ui_bit22.setChecked(false);
                                        cmd9_ui_bit21.setChecked(false);
                                        cmd9_ui_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ui_bit23.setChecked(true);
                                        cmd9_ui_bit22.setChecked(false);
                                        cmd9_ui_bit21.setChecked(false);
                                        cmd9_ui_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ui_bit23.setChecked(false);
                                        cmd9_ui_bit22.setChecked(true);
                                        cmd9_ui_bit21.setChecked(false);
                                        cmd9_ui_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ui_bit23.setChecked(true);
                                        cmd9_ui_bit22.setChecked(true);
                                        cmd9_ui_bit21.setChecked(false);
                                        cmd9_ui_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ui_bit23.setChecked(false);
                                        cmd9_ui_bit22.setChecked(false);
                                        cmd9_ui_bit21.setChecked(true);
                                        cmd9_ui_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ui_bit23.setChecked(true);
                                        cmd9_ui_bit22.setChecked(false);
                                        cmd9_ui_bit21.setChecked(true);
                                        cmd9_ui_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ui_bit23.setChecked(false);
                                        cmd9_ui_bit22.setChecked(true);
                                        cmd9_ui_bit21.setChecked(true);
                                        cmd9_ui_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(5)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ui_bit23.setChecked(true);
                                        cmd9_ui_bit22.setChecked(true);
                                        cmd9_ui_bit21.setChecked(true);
                                        cmd9_ui_bit20.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ui_bit19.setChecked(false);
                                        cmd9_ui_bit18.setChecked(false);
                                        cmd9_ui_bit17.setChecked(false);
                                        cmd9_ui_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ui_bit19.setChecked(true);
                                        cmd9_ui_bit18.setChecked(false);
                                        cmd9_ui_bit17.setChecked(false);
                                        cmd9_ui_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ui_bit19.setChecked(false);
                                        cmd9_ui_bit18.setChecked(true);
                                        cmd9_ui_bit17.setChecked(false);
                                        cmd9_ui_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ui_bit19.setChecked(true);
                                        cmd9_ui_bit18.setChecked(true);
                                        cmd9_ui_bit17.setChecked(false);
                                        cmd9_ui_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ui_bit19.setChecked(false);
                                        cmd9_ui_bit18.setChecked(false);
                                        cmd9_ui_bit17.setChecked(true);
                                        cmd9_ui_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ui_bit19.setChecked(true);
                                        cmd9_ui_bit18.setChecked(false);
                                        cmd9_ui_bit17.setChecked(true);
                                        cmd9_ui_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ui_bit19.setChecked(false);
                                        cmd9_ui_bit18.setChecked(true);
                                        cmd9_ui_bit17.setChecked(true);
                                        cmd9_ui_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ui_bit19.setChecked(true);
                                        cmd9_ui_bit18.setChecked(true);
                                        cmd9_ui_bit17.setChecked(true);
                                        cmd9_ui_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ui_bit19.setChecked(false);
                                        cmd9_ui_bit18.setChecked(false);
                                        cmd9_ui_bit17.setChecked(false);
                                        cmd9_ui_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ui_bit19.setChecked(true);
                                        cmd9_ui_bit18.setChecked(false);
                                        cmd9_ui_bit17.setChecked(false);
                                        cmd9_ui_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ui_bit19.setChecked(false);
                                        cmd9_ui_bit18.setChecked(true);
                                        cmd9_ui_bit17.setChecked(false);
                                        cmd9_ui_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ui_bit19.setChecked(true);
                                        cmd9_ui_bit18.setChecked(true);
                                        cmd9_ui_bit17.setChecked(false);
                                        cmd9_ui_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ui_bit19.setChecked(false);
                                        cmd9_ui_bit18.setChecked(false);
                                        cmd9_ui_bit17.setChecked(true);
                                        cmd9_ui_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ui_bit19.setChecked(true);
                                        cmd9_ui_bit18.setChecked(false);
                                        cmd9_ui_bit17.setChecked(true);
                                        cmd9_ui_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ui_bit19.setChecked(false);
                                        cmd9_ui_bit18.setChecked(true);
                                        cmd9_ui_bit17.setChecked(true);
                                        cmd9_ui_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(6)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ui_bit19.setChecked(true);
                                        cmd9_ui_bit18.setChecked(true);
                                        cmd9_ui_bit17.setChecked(true);
                                        cmd9_ui_bit16.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ui_bit15.setChecked(false);
                                        cmd9_ui_bit14.setChecked(false);
                                        cmd9_ui_bit13.setChecked(false);
                                        cmd9_ui_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ui_bit15.setChecked(true);
                                        cmd9_ui_bit14.setChecked(false);
                                        cmd9_ui_bit13.setChecked(false);
                                        cmd9_ui_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ui_bit15.setChecked(false);
                                        cmd9_ui_bit14.setChecked(true);
                                        cmd9_ui_bit13.setChecked(false);
                                        cmd9_ui_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ui_bit15.setChecked(true);
                                        cmd9_ui_bit14.setChecked(true);
                                        cmd9_ui_bit13.setChecked(false);
                                        cmd9_ui_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ui_bit15.setChecked(false);
                                        cmd9_ui_bit14.setChecked(false);
                                        cmd9_ui_bit13.setChecked(true);
                                        cmd9_ui_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ui_bit15.setChecked(true);
                                        cmd9_ui_bit14.setChecked(false);
                                        cmd9_ui_bit13.setChecked(true);
                                        cmd9_ui_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ui_bit15.setChecked(false);
                                        cmd9_ui_bit14.setChecked(true);
                                        cmd9_ui_bit13.setChecked(true);
                                        cmd9_ui_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ui_bit15.setChecked(true);
                                        cmd9_ui_bit14.setChecked(true);
                                        cmd9_ui_bit13.setChecked(true);
                                        cmd9_ui_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ui_bit15.setChecked(false);
                                        cmd9_ui_bit14.setChecked(false);
                                        cmd9_ui_bit13.setChecked(false);
                                        cmd9_ui_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ui_bit15.setChecked(true);
                                        cmd9_ui_bit14.setChecked(false);
                                        cmd9_ui_bit13.setChecked(false);
                                        cmd9_ui_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ui_bit15.setChecked(false);
                                        cmd9_ui_bit14.setChecked(true);
                                        cmd9_ui_bit13.setChecked(false);
                                        cmd9_ui_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ui_bit15.setChecked(true);
                                        cmd9_ui_bit14.setChecked(true);
                                        cmd9_ui_bit13.setChecked(false);
                                        cmd9_ui_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ui_bit15.setChecked(false);
                                        cmd9_ui_bit14.setChecked(false);
                                        cmd9_ui_bit13.setChecked(true);
                                        cmd9_ui_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ui_bit15.setChecked(true);
                                        cmd9_ui_bit14.setChecked(false);
                                        cmd9_ui_bit13.setChecked(true);
                                        cmd9_ui_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ui_bit15.setChecked(false);
                                        cmd9_ui_bit14.setChecked(true);
                                        cmd9_ui_bit13.setChecked(true);
                                        cmd9_ui_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(7)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ui_bit15.setChecked(true);
                                        cmd9_ui_bit14.setChecked(true);
                                        cmd9_ui_bit13.setChecked(true);
                                        cmd9_ui_bit12.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ui_bit11.setChecked(false);
                                        cmd9_ui_bit10.setChecked(false);
                                        cmd9_ui_bit9.setChecked(false);
                                        cmd9_ui_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ui_bit11.setChecked(true);
                                        cmd9_ui_bit10.setChecked(false);
                                        cmd9_ui_bit9.setChecked(false);
                                        cmd9_ui_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ui_bit11.setChecked(false);
                                        cmd9_ui_bit10.setChecked(true);
                                        cmd9_ui_bit9.setChecked(false);
                                        cmd9_ui_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ui_bit11.setChecked(true);
                                        cmd9_ui_bit10.setChecked(true);
                                        cmd9_ui_bit9.setChecked(false);
                                        cmd9_ui_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ui_bit11.setChecked(false);
                                        cmd9_ui_bit10.setChecked(false);
                                        cmd9_ui_bit9.setChecked(true);
                                        cmd9_ui_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ui_bit11.setChecked(true);
                                        cmd9_ui_bit10.setChecked(false);
                                        cmd9_ui_bit9.setChecked(true);
                                        cmd9_ui_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ui_bit11.setChecked(false);
                                        cmd9_ui_bit10.setChecked(true);
                                        cmd9_ui_bit9.setChecked(true);
                                        cmd9_ui_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ui_bit11.setChecked(true);
                                        cmd9_ui_bit10.setChecked(true);
                                        cmd9_ui_bit9.setChecked(true);
                                        cmd9_ui_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ui_bit11.setChecked(false);
                                        cmd9_ui_bit10.setChecked(false);
                                        cmd9_ui_bit9.setChecked(false);
                                        cmd9_ui_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ui_bit11.setChecked(true);
                                        cmd9_ui_bit10.setChecked(false);
                                        cmd9_ui_bit9.setChecked(false);
                                        cmd9_ui_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ui_bit11.setChecked(false);
                                        cmd9_ui_bit10.setChecked(true);
                                        cmd9_ui_bit9.setChecked(false);
                                        cmd9_ui_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ui_bit11.setChecked(true);
                                        cmd9_ui_bit10.setChecked(true);
                                        cmd9_ui_bit9.setChecked(false);
                                        cmd9_ui_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ui_bit11.setChecked(false);
                                        cmd9_ui_bit10.setChecked(false);
                                        cmd9_ui_bit9.setChecked(true);
                                        cmd9_ui_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ui_bit11.setChecked(true);
                                        cmd9_ui_bit10.setChecked(false);
                                        cmd9_ui_bit9.setChecked(true);
                                        cmd9_ui_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ui_bit11.setChecked(false);
                                        cmd9_ui_bit10.setChecked(true);
                                        cmd9_ui_bit9.setChecked(true);
                                        cmd9_ui_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(8)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ui_bit11.setChecked(true);
                                        cmd9_ui_bit10.setChecked(true);
                                        cmd9_ui_bit9.setChecked(true);
                                        cmd9_ui_bit8.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ui_bit4.setChecked(true);
                                        cmd9_ui_bit5.setChecked(false);
                                        cmd9_ui_bit6.setChecked(false);
                                        cmd9_ui_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ui_bit4.setChecked(false);
                                        cmd9_ui_bit5.setChecked(true);
                                        cmd9_ui_bit6.setChecked(false);
                                        cmd9_ui_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ui_bit4.setChecked(true);
                                        cmd9_ui_bit5.setChecked(true);
                                        cmd9_ui_bit6.setChecked(false);
                                        cmd9_ui_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ui_bit4.setChecked(false);
                                        cmd9_ui_bit5.setChecked(false);
                                        cmd9_ui_bit6.setChecked(true);
                                        cmd9_ui_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ui_bit4.setChecked(true);
                                        cmd9_ui_bit5.setChecked(false);
                                        cmd9_ui_bit6.setChecked(true);
                                        cmd9_ui_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ui_bit4.setChecked(false);
                                        cmd9_ui_bit5.setChecked(true);
                                        cmd9_ui_bit6.setChecked(true);
                                        cmd9_ui_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ui_bit4.setChecked(true);
                                        cmd9_ui_bit5.setChecked(true);
                                        cmd9_ui_bit6.setChecked(true);
                                        cmd9_ui_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ui_bit4.setChecked(false);
                                        cmd9_ui_bit5.setChecked(false);
                                        cmd9_ui_bit6.setChecked(false);
                                        cmd9_ui_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ui_bit4.setChecked(true);
                                        cmd9_ui_bit5.setChecked(false);
                                        cmd9_ui_bit6.setChecked(false);
                                        cmd9_ui_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ui_bit4.setChecked(false);
                                        cmd9_ui_bit5.setChecked(true);
                                        cmd9_ui_bit6.setChecked(false);
                                        cmd9_ui_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ui_bit4.setChecked(true);
                                        cmd9_ui_bit5.setChecked(true);
                                        cmd9_ui_bit6.setChecked(false);
                                        cmd9_ui_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ui_bit4.setChecked(false);
                                        cmd9_ui_bit5.setChecked(false);
                                        cmd9_ui_bit6.setChecked(true);
                                        cmd9_ui_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ui_bit4.setChecked(true);
                                        cmd9_ui_bit5.setChecked(false);
                                        cmd9_ui_bit6.setChecked(true);
                                        cmd9_ui_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ui_bit4.setChecked(false);
                                        cmd9_ui_bit5.setChecked(true);
                                        cmd9_ui_bit6.setChecked(true);
                                        cmd9_ui_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ui_bit4.setChecked(true);
                                        cmd9_ui_bit5.setChecked(true);
                                        cmd9_ui_bit6.setChecked(true);
                                        cmd9_ui_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ui_bit0.setChecked(false);
                                        cmd9_ui_bit1.setChecked(false);
                                        cmd9_ui_bit2.setChecked(false);
                                        cmd9_ui_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ui_bit0.setChecked(true);
                                        cmd9_ui_bit1.setChecked(false);
                                        cmd9_ui_bit2.setChecked(false);
                                        cmd9_ui_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ui_bit0.setChecked(false);
                                        cmd9_ui_bit1.setChecked(true);
                                        cmd9_ui_bit2.setChecked(false);
                                        cmd9_ui_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ui_bit0.setChecked(true);
                                        cmd9_ui_bit1.setChecked(true);
                                        cmd9_ui_bit2.setChecked(false);
                                        cmd9_ui_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ui_bit0.setChecked(false);
                                        cmd9_ui_bit1.setChecked(false);
                                        cmd9_ui_bit2.setChecked(true);
                                        cmd9_ui_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ui_bit0.setChecked(true);
                                        cmd9_ui_bit1.setChecked(false);
                                        cmd9_ui_bit2.setChecked(true);
                                        cmd9_ui_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ui_bit0.setChecked(false);
                                        cmd9_ui_bit1.setChecked(true);
                                        cmd9_ui_bit2.setChecked(true);
                                        cmd9_ui_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ui_bit0.setChecked(true);
                                        cmd9_ui_bit1.setChecked(true);
                                        cmd9_ui_bit2.setChecked(true);
                                        cmd9_ui_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ui_bit0.setChecked(false);
                                        cmd9_ui_bit1.setChecked(false);
                                        cmd9_ui_bit2.setChecked(false);
                                        cmd9_ui_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ui_bit0.setChecked(true);
                                        cmd9_ui_bit1.setChecked(false);
                                        cmd9_ui_bit2.setChecked(false);
                                        cmd9_ui_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ui_bit0.setChecked(false);
                                        cmd9_ui_bit1.setChecked(true);
                                        cmd9_ui_bit2.setChecked(false);
                                        cmd9_ui_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ui_bit0.setChecked(true);
                                        cmd9_ui_bit1.setChecked(true);
                                        cmd9_ui_bit2.setChecked(false);
                                        cmd9_ui_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ui_bit0.setChecked(false);
                                        cmd9_ui_bit1.setChecked(false);
                                        cmd9_ui_bit2.setChecked(true);
                                        cmd9_ui_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ui_bit0.setChecked(true);
                                        cmd9_ui_bit1.setChecked(false);
                                        cmd9_ui_bit2.setChecked(true);
                                        cmd9_ui_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ui_bit0.setChecked(false);
                                        cmd9_ui_bit1.setChecked(true);
                                        cmd9_ui_bit2.setChecked(true);
                                        cmd9_ui_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(10)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ui_bit0.setChecked(true);
                                        cmd9_ui_bit1.setChecked(true);
                                        cmd9_ui_bit2.setChecked(true);
                                        cmd9_ui_bit3.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_di_bit31.setChecked(false);
                                        cmd9_di_bit30.setChecked(false);
                                        cmd9_di_bit29.setChecked(false);
                                        cmd9_di_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_di_bit31.setChecked(true);
                                        cmd9_di_bit30.setChecked(false);
                                        cmd9_di_bit29.setChecked(false);
                                        cmd9_di_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_di_bit31.setChecked(false);
                                        cmd9_di_bit30.setChecked(true);
                                        cmd9_di_bit29.setChecked(false);
                                        cmd9_di_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_di_bit31.setChecked(true);
                                        cmd9_di_bit30.setChecked(true);
                                        cmd9_di_bit29.setChecked(false);
                                        cmd9_di_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_di_bit31.setChecked(false);
                                        cmd9_di_bit30.setChecked(false);
                                        cmd9_di_bit29.setChecked(true);
                                        cmd9_di_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_di_bit31.setChecked(true);
                                        cmd9_di_bit30.setChecked(false);
                                        cmd9_di_bit29.setChecked(true);
                                        cmd9_di_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_di_bit31.setChecked(false);
                                        cmd9_di_bit30.setChecked(true);
                                        cmd9_di_bit29.setChecked(true);
                                        cmd9_di_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_di_bit31.setChecked(true);
                                        cmd9_di_bit30.setChecked(true);
                                        cmd9_di_bit29.setChecked(true);
                                        cmd9_di_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_di_bit31.setChecked(false);
                                        cmd9_di_bit30.setChecked(false);
                                        cmd9_di_bit29.setChecked(false);
                                        cmd9_di_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_di_bit31.setChecked(true);
                                        cmd9_di_bit30.setChecked(false);
                                        cmd9_di_bit29.setChecked(false);
                                        cmd9_di_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_di_bit31.setChecked(false);
                                        cmd9_di_bit30.setChecked(true);
                                        cmd9_di_bit29.setChecked(false);
                                        cmd9_di_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_di_bit31.setChecked(true);
                                        cmd9_di_bit30.setChecked(true);
                                        cmd9_di_bit29.setChecked(false);
                                        cmd9_di_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_di_bit31.setChecked(false);
                                        cmd9_di_bit30.setChecked(false);
                                        cmd9_di_bit29.setChecked(true);
                                        cmd9_di_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_di_bit31.setChecked(true);
                                        cmd9_di_bit30.setChecked(false);
                                        cmd9_di_bit29.setChecked(true);
                                        cmd9_di_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_di_bit31.setChecked(false);
                                        cmd9_di_bit30.setChecked(true);
                                        cmd9_di_bit29.setChecked(true);
                                        cmd9_di_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(12)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_di_bit31.setChecked(true);
                                        cmd9_di_bit30.setChecked(true);
                                        cmd9_di_bit29.setChecked(true);
                                        cmd9_di_bit28.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_di_bit27.setChecked(false);
                                        cmd9_di_bit26.setChecked(false);
                                        cmd9_di_bit25.setChecked(false);
                                        cmd9_di_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_di_bit27.setChecked(true);
                                        cmd9_di_bit26.setChecked(false);
                                        cmd9_di_bit25.setChecked(false);
                                        cmd9_di_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_di_bit27.setChecked(false);
                                        cmd9_di_bit26.setChecked(true);
                                        cmd9_di_bit25.setChecked(false);
                                        cmd9_di_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_di_bit27.setChecked(true);
                                        cmd9_di_bit26.setChecked(true);
                                        cmd9_di_bit25.setChecked(false);
                                        cmd9_di_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_di_bit27.setChecked(false);
                                        cmd9_di_bit26.setChecked(false);
                                        cmd9_di_bit25.setChecked(true);
                                        cmd9_di_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_di_bit27.setChecked(true);
                                        cmd9_di_bit26.setChecked(false);
                                        cmd9_di_bit25.setChecked(true);
                                        cmd9_di_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_di_bit27.setChecked(false);
                                        cmd9_di_bit26.setChecked(true);
                                        cmd9_di_bit25.setChecked(true);
                                        cmd9_di_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_di_bit27.setChecked(true);
                                        cmd9_di_bit26.setChecked(true);
                                        cmd9_di_bit25.setChecked(true);
                                        cmd9_di_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_di_bit27.setChecked(false);
                                        cmd9_di_bit26.setChecked(false);
                                        cmd9_di_bit25.setChecked(false);
                                        cmd9_di_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_di_bit27.setChecked(true);
                                        cmd9_di_bit26.setChecked(false);
                                        cmd9_di_bit25.setChecked(false);
                                        cmd9_di_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_di_bit27.setChecked(false);
                                        cmd9_di_bit26.setChecked(true);
                                        cmd9_di_bit25.setChecked(false);
                                        cmd9_di_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_di_bit27.setChecked(true);
                                        cmd9_di_bit26.setChecked(true);
                                        cmd9_di_bit25.setChecked(false);
                                        cmd9_di_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_di_bit27.setChecked(false);
                                        cmd9_di_bit26.setChecked(false);
                                        cmd9_di_bit25.setChecked(true);
                                        cmd9_di_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_di_bit27.setChecked(true);
                                        cmd9_di_bit26.setChecked(false);
                                        cmd9_di_bit25.setChecked(true);
                                        cmd9_di_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_di_bit27.setChecked(false);
                                        cmd9_di_bit26.setChecked(true);
                                        cmd9_di_bit25.setChecked(true);
                                        cmd9_di_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_di_bit27.setChecked(true);
                                        cmd9_di_bit26.setChecked(true);
                                        cmd9_di_bit25.setChecked(true);
                                        cmd9_di_bit24.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_di_bit23.setChecked(false);
                                        cmd9_di_bit22.setChecked(false);
                                        cmd9_di_bit21.setChecked(false);
                                        cmd9_di_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_di_bit23.setChecked(true);
                                        cmd9_di_bit22.setChecked(false);
                                        cmd9_di_bit21.setChecked(false);
                                        cmd9_di_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_di_bit23.setChecked(false);
                                        cmd9_di_bit22.setChecked(true);
                                        cmd9_di_bit21.setChecked(false);
                                        cmd9_di_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_di_bit23.setChecked(true);
                                        cmd9_di_bit22.setChecked(true);
                                        cmd9_di_bit21.setChecked(false);
                                        cmd9_di_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_di_bit23.setChecked(false);
                                        cmd9_di_bit22.setChecked(false);
                                        cmd9_di_bit21.setChecked(true);
                                        cmd9_di_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_di_bit23.setChecked(true);
                                        cmd9_di_bit22.setChecked(false);
                                        cmd9_di_bit21.setChecked(true);
                                        cmd9_di_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_di_bit23.setChecked(false);
                                        cmd9_di_bit22.setChecked(true);
                                        cmd9_di_bit21.setChecked(true);
                                        cmd9_di_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_di_bit23.setChecked(true);
                                        cmd9_di_bit22.setChecked(true);
                                        cmd9_di_bit21.setChecked(true);
                                        cmd9_di_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_di_bit23.setChecked(false);
                                        cmd9_di_bit22.setChecked(false);
                                        cmd9_di_bit21.setChecked(false);
                                        cmd9_di_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_di_bit23.setChecked(true);
                                        cmd9_di_bit22.setChecked(false);
                                        cmd9_di_bit21.setChecked(false);
                                        cmd9_di_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_di_bit23.setChecked(false);
                                        cmd9_di_bit22.setChecked(true);
                                        cmd9_di_bit21.setChecked(false);
                                        cmd9_di_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_di_bit23.setChecked(true);
                                        cmd9_di_bit22.setChecked(true);
                                        cmd9_di_bit21.setChecked(false);
                                        cmd9_di_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_di_bit23.setChecked(false);
                                        cmd9_di_bit22.setChecked(false);
                                        cmd9_di_bit21.setChecked(true);
                                        cmd9_di_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_di_bit23.setChecked(true);
                                        cmd9_di_bit22.setChecked(false);
                                        cmd9_di_bit21.setChecked(true);
                                        cmd9_di_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_di_bit23.setChecked(false);
                                        cmd9_di_bit22.setChecked(true);
                                        cmd9_di_bit21.setChecked(true);
                                        cmd9_di_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_di_bit23.setChecked(true);
                                        cmd9_di_bit22.setChecked(true);
                                        cmd9_di_bit21.setChecked(true);
                                        cmd9_di_bit20.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_di_bit19.setChecked(false);
                                        cmd9_di_bit18.setChecked(false);
                                        cmd9_di_bit17.setChecked(false);
                                        cmd9_di_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_di_bit19.setChecked(true);
                                        cmd9_di_bit18.setChecked(false);
                                        cmd9_di_bit17.setChecked(false);
                                        cmd9_di_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_di_bit19.setChecked(false);
                                        cmd9_di_bit18.setChecked(true);
                                        cmd9_di_bit17.setChecked(false);
                                        cmd9_di_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_di_bit19.setChecked(true);
                                        cmd9_di_bit18.setChecked(true);
                                        cmd9_di_bit17.setChecked(false);
                                        cmd9_di_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_di_bit19.setChecked(false);
                                        cmd9_di_bit18.setChecked(false);
                                        cmd9_di_bit17.setChecked(true);
                                        cmd9_di_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_di_bit19.setChecked(true);
                                        cmd9_di_bit18.setChecked(false);
                                        cmd9_di_bit17.setChecked(true);
                                        cmd9_di_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_di_bit19.setChecked(false);
                                        cmd9_di_bit18.setChecked(true);
                                        cmd9_di_bit17.setChecked(true);
                                        cmd9_di_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_di_bit19.setChecked(true);
                                        cmd9_di_bit18.setChecked(true);
                                        cmd9_di_bit17.setChecked(true);
                                        cmd9_di_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_di_bit19.setChecked(false);
                                        cmd9_di_bit18.setChecked(false);
                                        cmd9_di_bit17.setChecked(false);
                                        cmd9_di_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_di_bit19.setChecked(true);
                                        cmd9_di_bit18.setChecked(false);
                                        cmd9_di_bit17.setChecked(false);
                                        cmd9_di_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_di_bit19.setChecked(false);
                                        cmd9_di_bit18.setChecked(true);
                                        cmd9_di_bit17.setChecked(false);
                                        cmd9_di_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_di_bit19.setChecked(true);
                                        cmd9_di_bit18.setChecked(true);
                                        cmd9_di_bit17.setChecked(false);
                                        cmd9_di_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_di_bit19.setChecked(false);
                                        cmd9_di_bit18.setChecked(false);
                                        cmd9_di_bit17.setChecked(true);
                                        cmd9_di_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_di_bit19.setChecked(true);
                                        cmd9_di_bit18.setChecked(false);
                                        cmd9_di_bit17.setChecked(true);
                                        cmd9_di_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_di_bit19.setChecked(false);
                                        cmd9_di_bit18.setChecked(true);
                                        cmd9_di_bit17.setChecked(true);
                                        cmd9_di_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_di_bit19.setChecked(true);
                                        cmd9_di_bit18.setChecked(true);
                                        cmd9_di_bit17.setChecked(true);
                                        cmd9_di_bit16.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_di_bit15.setChecked(false);
                                        cmd9_di_bit14.setChecked(false);
                                        cmd9_di_bit13.setChecked(false);
                                        cmd9_di_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_di_bit15.setChecked(true);
                                        cmd9_di_bit14.setChecked(false);
                                        cmd9_di_bit13.setChecked(false);
                                        cmd9_di_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_di_bit15.setChecked(false);
                                        cmd9_di_bit14.setChecked(true);
                                        cmd9_di_bit13.setChecked(false);
                                        cmd9_di_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_di_bit15.setChecked(true);
                                        cmd9_di_bit14.setChecked(true);
                                        cmd9_di_bit13.setChecked(false);
                                        cmd9_di_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_di_bit15.setChecked(false);
                                        cmd9_di_bit14.setChecked(false);
                                        cmd9_di_bit13.setChecked(true);
                                        cmd9_di_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_di_bit15.setChecked(true);
                                        cmd9_di_bit14.setChecked(false);
                                        cmd9_di_bit13.setChecked(true);
                                        cmd9_di_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_di_bit15.setChecked(false);
                                        cmd9_di_bit14.setChecked(true);
                                        cmd9_di_bit13.setChecked(true);
                                        cmd9_di_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_di_bit15.setChecked(true);
                                        cmd9_di_bit14.setChecked(true);
                                        cmd9_di_bit13.setChecked(true);
                                        cmd9_di_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_di_bit15.setChecked(false);
                                        cmd9_di_bit14.setChecked(false);
                                        cmd9_di_bit13.setChecked(false);
                                        cmd9_di_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_di_bit15.setChecked(true);
                                        cmd9_di_bit14.setChecked(false);
                                        cmd9_di_bit13.setChecked(false);
                                        cmd9_di_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_di_bit15.setChecked(false);
                                        cmd9_di_bit14.setChecked(true);
                                        cmd9_di_bit13.setChecked(false);
                                        cmd9_di_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_di_bit15.setChecked(true);
                                        cmd9_di_bit14.setChecked(true);
                                        cmd9_di_bit13.setChecked(false);
                                        cmd9_di_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_di_bit15.setChecked(false);
                                        cmd9_di_bit14.setChecked(false);
                                        cmd9_di_bit13.setChecked(true);
                                        cmd9_di_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_di_bit15.setChecked(true);
                                        cmd9_di_bit14.setChecked(false);
                                        cmd9_di_bit13.setChecked(true);
                                        cmd9_di_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_di_bit15.setChecked(false);
                                        cmd9_di_bit14.setChecked(true);
                                        cmd9_di_bit13.setChecked(true);
                                        cmd9_di_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_di_bit15.setChecked(true);
                                        cmd9_di_bit14.setChecked(true);
                                        cmd9_di_bit13.setChecked(true);
                                        cmd9_di_bit12.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_di_bit11.setChecked(false);
                                        cmd9_di_bit10.setChecked(false);
                                        cmd9_di_bit9.setChecked(false);
                                        cmd9_di_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_di_bit11.setChecked(true);
                                        cmd9_di_bit10.setChecked(false);
                                        cmd9_di_bit9.setChecked(false);
                                        cmd9_di_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_di_bit11.setChecked(false);
                                        cmd9_di_bit10.setChecked(true);
                                        cmd9_di_bit9.setChecked(false);
                                        cmd9_di_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_di_bit11.setChecked(true);
                                        cmd9_di_bit10.setChecked(true);
                                        cmd9_di_bit9.setChecked(false);
                                        cmd9_di_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_di_bit11.setChecked(false);
                                        cmd9_di_bit10.setChecked(false);
                                        cmd9_di_bit9.setChecked(true);
                                        cmd9_di_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_di_bit11.setChecked(true);
                                        cmd9_di_bit10.setChecked(false);
                                        cmd9_di_bit9.setChecked(true);
                                        cmd9_di_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_di_bit11.setChecked(false);
                                        cmd9_di_bit10.setChecked(true);
                                        cmd9_di_bit9.setChecked(true);
                                        cmd9_di_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_di_bit11.setChecked(true);
                                        cmd9_di_bit10.setChecked(true);
                                        cmd9_di_bit9.setChecked(true);
                                        cmd9_di_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_di_bit11.setChecked(false);
                                        cmd9_di_bit10.setChecked(false);
                                        cmd9_di_bit9.setChecked(false);
                                        cmd9_di_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_di_bit11.setChecked(true);
                                        cmd9_di_bit10.setChecked(false);
                                        cmd9_di_bit9.setChecked(false);
                                        cmd9_di_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_di_bit11.setChecked(false);
                                        cmd9_di_bit10.setChecked(true);
                                        cmd9_di_bit9.setChecked(false);
                                        cmd9_di_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_di_bit11.setChecked(true);
                                        cmd9_di_bit10.setChecked(true);
                                        cmd9_di_bit9.setChecked(false);
                                        cmd9_di_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_di_bit11.setChecked(false);
                                        cmd9_di_bit10.setChecked(false);
                                        cmd9_di_bit9.setChecked(true);
                                        cmd9_di_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_di_bit11.setChecked(true);
                                        cmd9_di_bit10.setChecked(false);
                                        cmd9_di_bit9.setChecked(true);
                                        cmd9_di_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_di_bit11.setChecked(false);
                                        cmd9_di_bit10.setChecked(true);
                                        cmd9_di_bit9.setChecked(true);
                                        cmd9_di_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_di_bit11.setChecked(true);
                                        cmd9_di_bit10.setChecked(true);
                                        cmd9_di_bit9.setChecked(true);
                                        cmd9_di_bit8.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_di_bit4.setChecked(true);
                                        cmd9_di_bit5.setChecked(false);
                                        cmd9_di_bit6.setChecked(false);
                                        cmd9_di_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_di_bit4.setChecked(false);
                                        cmd9_di_bit5.setChecked(true);
                                        cmd9_di_bit6.setChecked(false);
                                        cmd9_di_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_di_bit4.setChecked(true);
                                        cmd9_di_bit5.setChecked(true);
                                        cmd9_di_bit6.setChecked(false);
                                        cmd9_di_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_di_bit4.setChecked(false);
                                        cmd9_di_bit5.setChecked(false);
                                        cmd9_di_bit6.setChecked(true);
                                        cmd9_di_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_di_bit4.setChecked(true);
                                        cmd9_di_bit5.setChecked(false);
                                        cmd9_di_bit6.setChecked(true);
                                        cmd9_di_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_di_bit4.setChecked(false);
                                        cmd9_di_bit5.setChecked(true);
                                        cmd9_di_bit6.setChecked(true);
                                        cmd9_di_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_di_bit4.setChecked(true);
                                        cmd9_di_bit5.setChecked(true);
                                        cmd9_di_bit6.setChecked(true);
                                        cmd9_di_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_di_bit4.setChecked(false);
                                        cmd9_di_bit5.setChecked(false);
                                        cmd9_di_bit6.setChecked(false);
                                        cmd9_di_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_di_bit4.setChecked(true);
                                        cmd9_di_bit5.setChecked(false);
                                        cmd9_di_bit6.setChecked(false);
                                        cmd9_di_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_di_bit4.setChecked(false);
                                        cmd9_di_bit5.setChecked(true);
                                        cmd9_di_bit6.setChecked(false);
                                        cmd9_di_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_di_bit4.setChecked(true);
                                        cmd9_di_bit5.setChecked(true);
                                        cmd9_di_bit6.setChecked(false);
                                        cmd9_di_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_di_bit4.setChecked(false);
                                        cmd9_di_bit5.setChecked(false);
                                        cmd9_di_bit6.setChecked(true);
                                        cmd9_di_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_di_bit4.setChecked(true);
                                        cmd9_di_bit5.setChecked(false);
                                        cmd9_di_bit6.setChecked(true);
                                        cmd9_di_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_di_bit4.setChecked(false);
                                        cmd9_di_bit5.setChecked(true);
                                        cmd9_di_bit6.setChecked(true);
                                        cmd9_di_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_di_bit4.setChecked(true);
                                        cmd9_di_bit5.setChecked(true);
                                        cmd9_di_bit6.setChecked(true);
                                        cmd9_di_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_di_bit0.setChecked(false);
                                        cmd9_di_bit1.setChecked(false);
                                        cmd9_di_bit2.setChecked(false);
                                        cmd9_di_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_di_bit0.setChecked(true);
                                        cmd9_di_bit1.setChecked(false);
                                        cmd9_di_bit2.setChecked(false);
                                        cmd9_di_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_di_bit0.setChecked(false);
                                        cmd9_di_bit1.setChecked(true);
                                        cmd9_di_bit2.setChecked(false);
                                        cmd9_di_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_di_bit0.setChecked(true);
                                        cmd9_di_bit1.setChecked(true);
                                        cmd9_di_bit2.setChecked(false);
                                        cmd9_di_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_di_bit0.setChecked(false);
                                        cmd9_di_bit1.setChecked(false);
                                        cmd9_di_bit2.setChecked(true);
                                        cmd9_di_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_di_bit0.setChecked(true);
                                        cmd9_di_bit1.setChecked(false);
                                        cmd9_di_bit2.setChecked(true);
                                        cmd9_di_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_di_bit0.setChecked(false);
                                        cmd9_di_bit1.setChecked(true);
                                        cmd9_di_bit2.setChecked(true);
                                        cmd9_di_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_di_bit0.setChecked(true);
                                        cmd9_di_bit1.setChecked(true);
                                        cmd9_di_bit2.setChecked(true);
                                        cmd9_di_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_di_bit0.setChecked(false);
                                        cmd9_di_bit1.setChecked(false);
                                        cmd9_di_bit2.setChecked(false);
                                        cmd9_di_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_di_bit0.setChecked(true);
                                        cmd9_di_bit1.setChecked(false);
                                        cmd9_di_bit2.setChecked(false);
                                        cmd9_di_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_di_bit0.setChecked(false);
                                        cmd9_di_bit1.setChecked(true);
                                        cmd9_di_bit2.setChecked(false);
                                        cmd9_di_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_di_bit0.setChecked(true);
                                        cmd9_di_bit1.setChecked(true);
                                        cmd9_di_bit2.setChecked(false);
                                        cmd9_di_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_di_bit0.setChecked(false);
                                        cmd9_di_bit1.setChecked(false);
                                        cmd9_di_bit2.setChecked(true);
                                        cmd9_di_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_di_bit0.setChecked(true);
                                        cmd9_di_bit1.setChecked(false);
                                        cmd9_di_bit2.setChecked(true);
                                        cmd9_di_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_di_bit0.setChecked(false);
                                        cmd9_di_bit1.setChecked(true);
                                        cmd9_di_bit2.setChecked(true);
                                        cmd9_di_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_di_bit0.setChecked(true);
                                        cmd9_di_bit1.setChecked(true);
                                        cmd9_di_bit2.setChecked(true);
                                        cmd9_di_bit3.setChecked(true);
                                    }
                                    /*END OF FLOOR CALLS*/
                                    /*CAR CALLS*/
                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ci_bit31.setChecked(false);
                                        cmd9_ci_bit30.setChecked(false);
                                        cmd9_ci_bit29.setChecked(false);
                                        cmd9_ci_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ci_bit31.setChecked(true);
                                        cmd9_ci_bit30.setChecked(false);
                                        cmd9_ci_bit29.setChecked(false);
                                        cmd9_ci_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ci_bit31.setChecked(false);
                                        cmd9_ci_bit30.setChecked(true);
                                        cmd9_ci_bit29.setChecked(false);
                                        cmd9_ci_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ci_bit31.setChecked(true);
                                        cmd9_ci_bit30.setChecked(true);
                                        cmd9_ci_bit29.setChecked(false);
                                        cmd9_ci_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ci_bit31.setChecked(false);
                                        cmd9_ci_bit30.setChecked(false);
                                        cmd9_ci_bit29.setChecked(true);
                                        cmd9_ci_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ci_bit31.setChecked(true);
                                        cmd9_ci_bit30.setChecked(false);
                                        cmd9_ci_bit29.setChecked(true);
                                        cmd9_ci_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ci_bit31.setChecked(false);
                                        cmd9_ci_bit30.setChecked(true);
                                        cmd9_ci_bit29.setChecked(true);
                                        cmd9_ci_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ci_bit31.setChecked(true);
                                        cmd9_ci_bit30.setChecked(true);
                                        cmd9_ci_bit29.setChecked(true);
                                        cmd9_ci_bit28.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ci_bit31.setChecked(false);
                                        cmd9_ci_bit30.setChecked(false);
                                        cmd9_ci_bit29.setChecked(false);
                                        cmd9_ci_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ci_bit31.setChecked(true);
                                        cmd9_ci_bit30.setChecked(false);
                                        cmd9_ci_bit29.setChecked(false);
                                        cmd9_ci_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ci_bit31.setChecked(false);
                                        cmd9_ci_bit30.setChecked(true);
                                        cmd9_ci_bit29.setChecked(false);
                                        cmd9_ci_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ci_bit31.setChecked(true);
                                        cmd9_ci_bit30.setChecked(true);
                                        cmd9_ci_bit29.setChecked(false);
                                        cmd9_ci_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ci_bit31.setChecked(false);
                                        cmd9_ci_bit30.setChecked(false);
                                        cmd9_ci_bit29.setChecked(true);
                                        cmd9_ci_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ci_bit31.setChecked(true);
                                        cmd9_ci_bit30.setChecked(false);
                                        cmd9_ci_bit29.setChecked(true);
                                        cmd9_ci_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ci_bit31.setChecked(false);
                                        cmd9_ci_bit30.setChecked(true);
                                        cmd9_ci_bit29.setChecked(true);
                                        cmd9_ci_bit28.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(21)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ci_bit31.setChecked(true);
                                        cmd9_ci_bit30.setChecked(true);
                                        cmd9_ci_bit29.setChecked(true);
                                        cmd9_ci_bit28.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ci_bit27.setChecked(false);
                                        cmd9_ci_bit26.setChecked(false);
                                        cmd9_ci_bit25.setChecked(false);
                                        cmd9_ci_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ci_bit27.setChecked(true);
                                        cmd9_ci_bit26.setChecked(false);
                                        cmd9_ci_bit25.setChecked(false);
                                        cmd9_ci_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ci_bit27.setChecked(false);
                                        cmd9_ci_bit26.setChecked(true);
                                        cmd9_ci_bit25.setChecked(false);
                                        cmd9_ci_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ci_bit27.setChecked(true);
                                        cmd9_ci_bit26.setChecked(true);
                                        cmd9_ci_bit25.setChecked(false);
                                        cmd9_ci_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ci_bit27.setChecked(false);
                                        cmd9_ci_bit26.setChecked(false);
                                        cmd9_ci_bit25.setChecked(true);
                                        cmd9_ci_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ci_bit27.setChecked(true);
                                        cmd9_ci_bit26.setChecked(false);
                                        cmd9_ci_bit25.setChecked(true);
                                        cmd9_ci_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ci_bit27.setChecked(false);
                                        cmd9_ci_bit26.setChecked(true);
                                        cmd9_ci_bit25.setChecked(true);
                                        cmd9_ci_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ci_bit27.setChecked(true);
                                        cmd9_ci_bit26.setChecked(true);
                                        cmd9_ci_bit25.setChecked(true);
                                        cmd9_ci_bit24.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ci_bit27.setChecked(false);
                                        cmd9_ci_bit26.setChecked(false);
                                        cmd9_ci_bit25.setChecked(false);
                                        cmd9_ci_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ci_bit27.setChecked(true);
                                        cmd9_ci_bit26.setChecked(false);
                                        cmd9_ci_bit25.setChecked(false);
                                        cmd9_ci_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ci_bit27.setChecked(false);
                                        cmd9_ci_bit26.setChecked(true);
                                        cmd9_ci_bit25.setChecked(false);
                                        cmd9_ci_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ci_bit27.setChecked(true);
                                        cmd9_ci_bit26.setChecked(true);
                                        cmd9_ci_bit25.setChecked(false);
                                        cmd9_ci_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ci_bit27.setChecked(false);
                                        cmd9_ci_bit26.setChecked(false);
                                        cmd9_ci_bit25.setChecked(true);
                                        cmd9_ci_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ci_bit27.setChecked(true);
                                        cmd9_ci_bit26.setChecked(false);
                                        cmd9_ci_bit25.setChecked(true);
                                        cmd9_ci_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ci_bit27.setChecked(false);
                                        cmd9_ci_bit26.setChecked(true);
                                        cmd9_ci_bit25.setChecked(true);
                                        cmd9_ci_bit24.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(22)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ci_bit27.setChecked(true);
                                        cmd9_ci_bit26.setChecked(true);
                                        cmd9_ci_bit25.setChecked(true);
                                        cmd9_ci_bit24.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ci_bit23.setChecked(false);
                                        cmd9_ci_bit22.setChecked(false);
                                        cmd9_ci_bit21.setChecked(false);
                                        cmd9_ci_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ci_bit23.setChecked(true);
                                        cmd9_ci_bit22.setChecked(false);
                                        cmd9_ci_bit21.setChecked(false);
                                        cmd9_ci_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ci_bit23.setChecked(false);
                                        cmd9_ci_bit22.setChecked(true);
                                        cmd9_ci_bit21.setChecked(false);
                                        cmd9_ci_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ci_bit23.setChecked(true);
                                        cmd9_ci_bit22.setChecked(true);
                                        cmd9_ci_bit21.setChecked(false);
                                        cmd9_ci_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ci_bit23.setChecked(false);
                                        cmd9_ci_bit22.setChecked(false);
                                        cmd9_ci_bit21.setChecked(true);
                                        cmd9_ci_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ci_bit23.setChecked(true);
                                        cmd9_ci_bit22.setChecked(false);
                                        cmd9_ci_bit21.setChecked(true);
                                        cmd9_ci_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ci_bit23.setChecked(false);
                                        cmd9_ci_bit22.setChecked(true);
                                        cmd9_ci_bit21.setChecked(true);
                                        cmd9_ci_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ci_bit23.setChecked(true);
                                        cmd9_ci_bit22.setChecked(true);
                                        cmd9_ci_bit21.setChecked(true);
                                        cmd9_ci_bit20.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ci_bit23.setChecked(false);
                                        cmd9_ci_bit22.setChecked(false);
                                        cmd9_ci_bit21.setChecked(false);
                                        cmd9_ci_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ci_bit23.setChecked(true);
                                        cmd9_ci_bit22.setChecked(false);
                                        cmd9_ci_bit21.setChecked(false);
                                        cmd9_ci_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ci_bit23.setChecked(false);
                                        cmd9_ci_bit22.setChecked(true);
                                        cmd9_ci_bit21.setChecked(false);
                                        cmd9_ci_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ci_bit23.setChecked(true);
                                        cmd9_ci_bit22.setChecked(true);
                                        cmd9_ci_bit21.setChecked(false);
                                        cmd9_ci_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ci_bit23.setChecked(false);
                                        cmd9_ci_bit22.setChecked(false);
                                        cmd9_ci_bit21.setChecked(true);
                                        cmd9_ci_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ci_bit23.setChecked(true);
                                        cmd9_ci_bit22.setChecked(false);
                                        cmd9_ci_bit21.setChecked(true);
                                        cmd9_ci_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ci_bit23.setChecked(false);
                                        cmd9_ci_bit22.setChecked(true);
                                        cmd9_ci_bit21.setChecked(true);
                                        cmd9_ci_bit20.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(23)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ci_bit23.setChecked(true);
                                        cmd9_ci_bit22.setChecked(true);
                                        cmd9_ci_bit21.setChecked(true);
                                        cmd9_ci_bit20.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ci_bit19.setChecked(false);
                                        cmd9_ci_bit18.setChecked(false);
                                        cmd9_ci_bit17.setChecked(false);
                                        cmd9_ci_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ci_bit19.setChecked(true);
                                        cmd9_ci_bit18.setChecked(false);
                                        cmd9_ci_bit17.setChecked(false);
                                        cmd9_ci_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ci_bit19.setChecked(false);
                                        cmd9_ci_bit18.setChecked(true);
                                        cmd9_ci_bit17.setChecked(false);
                                        cmd9_ci_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ci_bit19.setChecked(true);
                                        cmd9_ci_bit18.setChecked(true);
                                        cmd9_ci_bit17.setChecked(false);
                                        cmd9_ci_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ci_bit19.setChecked(false);
                                        cmd9_ci_bit18.setChecked(false);
                                        cmd9_ci_bit17.setChecked(true);
                                        cmd9_ci_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ci_bit19.setChecked(true);
                                        cmd9_ci_bit18.setChecked(false);
                                        cmd9_ci_bit17.setChecked(true);
                                        cmd9_ci_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ci_bit19.setChecked(false);
                                        cmd9_ci_bit18.setChecked(true);
                                        cmd9_ci_bit17.setChecked(true);
                                        cmd9_ci_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ci_bit19.setChecked(true);
                                        cmd9_ci_bit18.setChecked(true);
                                        cmd9_ci_bit17.setChecked(true);
                                        cmd9_ci_bit16.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ci_bit19.setChecked(false);
                                        cmd9_ci_bit18.setChecked(false);
                                        cmd9_ci_bit17.setChecked(false);
                                        cmd9_ci_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ci_bit19.setChecked(true);
                                        cmd9_ci_bit18.setChecked(false);
                                        cmd9_ci_bit17.setChecked(false);
                                        cmd9_ci_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ci_bit19.setChecked(false);
                                        cmd9_ci_bit18.setChecked(true);
                                        cmd9_ci_bit17.setChecked(false);
                                        cmd9_ci_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ci_bit19.setChecked(true);
                                        cmd9_ci_bit18.setChecked(true);
                                        cmd9_ci_bit17.setChecked(false);
                                        cmd9_ci_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ci_bit19.setChecked(false);
                                        cmd9_ci_bit18.setChecked(false);
                                        cmd9_ci_bit17.setChecked(true);
                                        cmd9_ci_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ci_bit19.setChecked(true);
                                        cmd9_ci_bit18.setChecked(false);
                                        cmd9_ci_bit17.setChecked(true);
                                        cmd9_ci_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ci_bit19.setChecked(false);
                                        cmd9_ci_bit18.setChecked(true);
                                        cmd9_ci_bit17.setChecked(true);
                                        cmd9_ci_bit16.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ci_bit19.setChecked(true);
                                        cmd9_ci_bit18.setChecked(true);
                                        cmd9_ci_bit17.setChecked(true);
                                        cmd9_ci_bit16.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ci_bit15.setChecked(false);
                                        cmd9_ci_bit14.setChecked(false);
                                        cmd9_ci_bit13.setChecked(false);
                                        cmd9_ci_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ci_bit15.setChecked(true);
                                        cmd9_ci_bit14.setChecked(false);
                                        cmd9_ci_bit13.setChecked(false);
                                        cmd9_ci_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ci_bit15.setChecked(false);
                                        cmd9_ci_bit14.setChecked(true);
                                        cmd9_ci_bit13.setChecked(false);
                                        cmd9_ci_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ci_bit15.setChecked(true);
                                        cmd9_ci_bit14.setChecked(true);
                                        cmd9_ci_bit13.setChecked(false);
                                        cmd9_ci_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ci_bit15.setChecked(false);
                                        cmd9_ci_bit14.setChecked(false);
                                        cmd9_ci_bit13.setChecked(true);
                                        cmd9_ci_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ci_bit15.setChecked(true);
                                        cmd9_ci_bit14.setChecked(false);
                                        cmd9_ci_bit13.setChecked(true);
                                        cmd9_ci_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ci_bit15.setChecked(false);
                                        cmd9_ci_bit14.setChecked(true);
                                        cmd9_ci_bit13.setChecked(true);
                                        cmd9_ci_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ci_bit15.setChecked(true);
                                        cmd9_ci_bit14.setChecked(true);
                                        cmd9_ci_bit13.setChecked(true);
                                        cmd9_ci_bit12.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ci_bit15.setChecked(false);
                                        cmd9_ci_bit14.setChecked(false);
                                        cmd9_ci_bit13.setChecked(false);
                                        cmd9_ci_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ci_bit15.setChecked(true);
                                        cmd9_ci_bit14.setChecked(false);
                                        cmd9_ci_bit13.setChecked(false);
                                        cmd9_ci_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ci_bit15.setChecked(false);
                                        cmd9_ci_bit14.setChecked(true);
                                        cmd9_ci_bit13.setChecked(false);
                                        cmd9_ci_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ci_bit15.setChecked(true);
                                        cmd9_ci_bit14.setChecked(true);
                                        cmd9_ci_bit13.setChecked(false);
                                        cmd9_ci_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ci_bit15.setChecked(false);
                                        cmd9_ci_bit14.setChecked(false);
                                        cmd9_ci_bit13.setChecked(true);
                                        cmd9_ci_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ci_bit15.setChecked(true);
                                        cmd9_ci_bit14.setChecked(false);
                                        cmd9_ci_bit13.setChecked(true);
                                        cmd9_ci_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ci_bit15.setChecked(false);
                                        cmd9_ci_bit14.setChecked(true);
                                        cmd9_ci_bit13.setChecked(true);
                                        cmd9_ci_bit12.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(25)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ci_bit15.setChecked(true);
                                        cmd9_ci_bit14.setChecked(true);
                                        cmd9_ci_bit13.setChecked(true);
                                        cmd9_ci_bit12.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ci_bit11.setChecked(false);
                                        cmd9_ci_bit10.setChecked(false);
                                        cmd9_ci_bit9.setChecked(false);
                                        cmd9_ci_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ci_bit11.setChecked(true);
                                        cmd9_ci_bit10.setChecked(false);
                                        cmd9_ci_bit9.setChecked(false);
                                        cmd9_ci_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ci_bit11.setChecked(false);
                                        cmd9_ci_bit10.setChecked(true);
                                        cmd9_ci_bit9.setChecked(false);
                                        cmd9_ci_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ci_bit11.setChecked(true);
                                        cmd9_ci_bit10.setChecked(true);
                                        cmd9_ci_bit9.setChecked(false);
                                        cmd9_ci_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ci_bit11.setChecked(false);
                                        cmd9_ci_bit10.setChecked(false);
                                        cmd9_ci_bit9.setChecked(true);
                                        cmd9_ci_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ci_bit11.setChecked(true);
                                        cmd9_ci_bit10.setChecked(false);
                                        cmd9_ci_bit9.setChecked(true);
                                        cmd9_ci_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ci_bit11.setChecked(false);
                                        cmd9_ci_bit10.setChecked(true);
                                        cmd9_ci_bit9.setChecked(true);
                                        cmd9_ci_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ci_bit11.setChecked(true);
                                        cmd9_ci_bit10.setChecked(true);
                                        cmd9_ci_bit9.setChecked(true);
                                        cmd9_ci_bit8.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ci_bit11.setChecked(false);
                                        cmd9_ci_bit10.setChecked(false);
                                        cmd9_ci_bit9.setChecked(false);
                                        cmd9_ci_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ci_bit11.setChecked(true);
                                        cmd9_ci_bit10.setChecked(false);
                                        cmd9_ci_bit9.setChecked(false);
                                        cmd9_ci_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ci_bit11.setChecked(false);
                                        cmd9_ci_bit10.setChecked(true);
                                        cmd9_ci_bit9.setChecked(false);
                                        cmd9_ci_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ci_bit11.setChecked(true);
                                        cmd9_ci_bit10.setChecked(true);
                                        cmd9_ci_bit9.setChecked(false);
                                        cmd9_ci_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ci_bit11.setChecked(false);
                                        cmd9_ci_bit10.setChecked(false);
                                        cmd9_ci_bit9.setChecked(true);
                                        cmd9_ci_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ci_bit11.setChecked(true);
                                        cmd9_ci_bit10.setChecked(false);
                                        cmd9_ci_bit9.setChecked(true);
                                        cmd9_ci_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ci_bit11.setChecked(false);
                                        cmd9_ci_bit10.setChecked(true);
                                        cmd9_ci_bit9.setChecked(true);
                                        cmd9_ci_bit8.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(26)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ci_bit11.setChecked(true);
                                        cmd9_ci_bit10.setChecked(true);
                                        cmd9_ci_bit9.setChecked(true);
                                        cmd9_ci_bit8.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ci_bit4.setChecked(true);
                                        cmd9_ci_bit5.setChecked(false);
                                        cmd9_ci_bit6.setChecked(false);
                                        cmd9_ci_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ci_bit4.setChecked(false);
                                        cmd9_ci_bit5.setChecked(true);
                                        cmd9_ci_bit6.setChecked(false);
                                        cmd9_ci_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ci_bit4.setChecked(true);
                                        cmd9_ci_bit5.setChecked(true);
                                        cmd9_ci_bit6.setChecked(false);
                                        cmd9_ci_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ci_bit4.setChecked(false);
                                        cmd9_ci_bit5.setChecked(false);
                                        cmd9_ci_bit6.setChecked(true);
                                        cmd9_ci_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ci_bit4.setChecked(true);
                                        cmd9_ci_bit5.setChecked(false);
                                        cmd9_ci_bit6.setChecked(true);
                                        cmd9_ci_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ci_bit4.setChecked(false);
                                        cmd9_ci_bit5.setChecked(true);
                                        cmd9_ci_bit6.setChecked(true);
                                        cmd9_ci_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ci_bit4.setChecked(true);
                                        cmd9_ci_bit5.setChecked(true);
                                        cmd9_ci_bit6.setChecked(true);
                                        cmd9_ci_bit7.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ci_bit4.setChecked(false);
                                        cmd9_ci_bit5.setChecked(false);
                                        cmd9_ci_bit6.setChecked(false);
                                        cmd9_ci_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ci_bit4.setChecked(true);
                                        cmd9_ci_bit5.setChecked(false);
                                        cmd9_ci_bit6.setChecked(false);
                                        cmd9_ci_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ci_bit4.setChecked(false);
                                        cmd9_ci_bit5.setChecked(true);
                                        cmd9_ci_bit6.setChecked(false);
                                        cmd9_ci_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ci_bit4.setChecked(true);
                                        cmd9_ci_bit5.setChecked(true);
                                        cmd9_ci_bit6.setChecked(false);
                                        cmd9_ci_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ci_bit4.setChecked(false);
                                        cmd9_ci_bit5.setChecked(false);
                                        cmd9_ci_bit6.setChecked(true);
                                        cmd9_ci_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ci_bit4.setChecked(true);
                                        cmd9_ci_bit5.setChecked(false);
                                        cmd9_ci_bit6.setChecked(true);
                                        cmd9_ci_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ci_bit4.setChecked(false);
                                        cmd9_ci_bit5.setChecked(true);
                                        cmd9_ci_bit6.setChecked(true);
                                        cmd9_ci_bit7.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ci_bit4.setChecked(true);
                                        cmd9_ci_bit5.setChecked(true);
                                        cmd9_ci_bit6.setChecked(true);
                                        cmd9_ci_bit7.setChecked(true);
                                    }
                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexZero))
                                    {
                                        cmd9_ci_bit0.setChecked(false);
                                        cmd9_ci_bit1.setChecked(false);
                                        cmd9_ci_bit2.setChecked(false);
                                        cmd9_ci_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexOne))
                                    {
                                        cmd9_ci_bit0.setChecked(true);
                                        cmd9_ci_bit1.setChecked(false);
                                        cmd9_ci_bit2.setChecked(false);
                                        cmd9_ci_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexTwo))
                                    {
                                        cmd9_ci_bit0.setChecked(false);
                                        cmd9_ci_bit1.setChecked(true);
                                        cmd9_ci_bit2.setChecked(false);
                                        cmd9_ci_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexThree))
                                    {
                                        cmd9_ci_bit0.setChecked(true);
                                        cmd9_ci_bit1.setChecked(true);
                                        cmd9_ci_bit2.setChecked(false);
                                        cmd9_ci_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexFour))
                                    {
                                        cmd9_ci_bit0.setChecked(false);
                                        cmd9_ci_bit1.setChecked(false);
                                        cmd9_ci_bit2.setChecked(true);
                                        cmd9_ci_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexFive))
                                    {
                                        cmd9_ci_bit0.setChecked(true);
                                        cmd9_ci_bit1.setChecked(false);
                                        cmd9_ci_bit2.setChecked(true);
                                        cmd9_ci_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexSix))
                                    {
                                        cmd9_ci_bit0.setChecked(false);
                                        cmd9_ci_bit1.setChecked(true);
                                        cmd9_ci_bit2.setChecked(true);
                                        cmd9_ci_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexSeven))
                                    {
                                        cmd9_ci_bit0.setChecked(true);
                                        cmd9_ci_bit1.setChecked(true);
                                        cmd9_ci_bit2.setChecked(true);
                                        cmd9_ci_bit3.setChecked(false);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexEight))
                                    {
                                        cmd9_ci_bit0.setChecked(false);
                                        cmd9_ci_bit1.setChecked(false);
                                        cmd9_ci_bit2.setChecked(false);
                                        cmd9_ci_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexNine))
                                    {
                                        cmd9_ci_bit0.setChecked(true);
                                        cmd9_ci_bit1.setChecked(false);
                                        cmd9_ci_bit2.setChecked(false);
                                        cmd9_ci_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexA))
                                    {
                                        cmd9_ci_bit0.setChecked(false);
                                        cmd9_ci_bit1.setChecked(true);
                                        cmd9_ci_bit2.setChecked(false);
                                        cmd9_ci_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexB))
                                    {
                                        cmd9_ci_bit0.setChecked(true);
                                        cmd9_ci_bit1.setChecked(true);
                                        cmd9_ci_bit2.setChecked(false);
                                        cmd9_ci_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexC))
                                    {
                                        cmd9_ci_bit0.setChecked(false);
                                        cmd9_ci_bit1.setChecked(false);
                                        cmd9_ci_bit2.setChecked(true);
                                        cmd9_ci_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexD))
                                    {
                                        cmd9_ci_bit0.setChecked(true);
                                        cmd9_ci_bit1.setChecked(false);
                                        cmd9_ci_bit2.setChecked(true);
                                        cmd9_ci_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexE))
                                    {
                                        cmd9_ci_bit0.setChecked(false);
                                        cmd9_ci_bit1.setChecked(true);
                                        cmd9_ci_bit2.setChecked(true);
                                        cmd9_ci_bit3.setChecked(true);
                                    }

                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexF))
                                    {
                                        cmd9_ci_bit0.setChecked(true);
                                        cmd9_ci_bit1.setChecked(true);
                                        cmd9_ci_bit2.setChecked(true);
                                        cmd9_ci_bit3.setChecked(true);
                                    }
                                }
                            }
                        }//end of if()
                    }//end of if{}
                }//end of for()
            }//end of if{}
        }
        catch (Exception e)
        {
            //toast(e.toString());
            adminText.setText(e.toString());
        }
    }//end of indications()

    public void liftStatus(String receivedMessage)
    {
        String[] array = receivedMessage.split(":",0);
        String theNumber=array[0];
        cellNumber          = theNumber.replace("+91","");
        extractedMessage    = array[1];
        //toast("NUMBER: "+cellNumber+" \nMESSAGE:"+extractedMessage);
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        List<String> THENUMBER = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ADMIN_CLIENT_DATA",null);
        THENUMBER.clear();
        if (cursor.moveToFirst())
        {
            do
            {
                THENUMBER.add(cursor.getString(cursor.getColumnIndex("ADMIN_CLIENT_NUMBER")));
                //toast(THENUMBER.toString());
            }while (cursor.moveToNext());
        }
        try
        {
            if (!THENUMBER.isEmpty())
            {
                for (int i = 0; i < THENUMBER.size(); i++) {

                    if (THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        AdminSMSReceiverControllerDB = new AdminSMSReceiverControllerDB(this);
                        theSqLiteDatabase = AdminSMSReceiverControllerDB.getReadableDatabase();
                        theQuery = "CREATE TABLE IF NOT EXISTS ADMIN_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
                        theSqLiteDatabase.execSQL(theQuery);
                        theSqLiteDatabase.execSQL("INSERT INTO ADMIN_SMS(DATE, ADDRESS, CONTENT) VALUES('" + date + "','" + cellNumber + "','" + extractedMessage + "')");
                        //Toast.makeText(getApplicationContext(), "DATA IS STORED",Toast.LENGTH_LONG).show();
                    }//end of if{}
                }//end of for()

                //INSERTING INTO DB AND THEN EXTRACTING IT TO DISPLAY ON UI
                for (int i = 0; i < THENUMBER.size(); i++)
                {
                    if (THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE DATE='"+date+"'",null);
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM ADMIN_SMS WHERE ADDRESS='"+cellNumber+"'",null);
                        clientCursor = theSqLiteDatabase.rawQuery("SELECT * FROM ADMIN_SMS ORDER BY Id DESC LIMIT 1", null);
                        theContent = new ArrayList<String>();
                        theContent.clear();
                        if (clientCursor.moveToFirst())
                        {
                            do
                            {
                                theContent.add(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                                //toast(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                            } while (clientCursor.moveToNext());
                            int count = clientCursor.getCount();
                            String contentArray[] = new String[count];
                            for (int j = 0; j < contentArray.length; j++)
                            {
                                contentArray[j] = theContent.get(j);
                            }
                            StringBuffer sb0 = new StringBuffer();
                            for (int k = 0; k < contentArray.length; k++)
                            {
                                sb0.append(contentArray[k]);
                            }
                            countDown.setVisibility(View.VISIBLE);
                            String str1 = sb0.toString();
                            //adminText.setText(str1);
                            String decodeChoice = str1;
                            /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
                            String theData[] = new String[decodeChoice.length()];
                            theData = decodeChoice.split("#");
                            StringBuffer showData = new StringBuffer();
                            for (int s = 1; s < theData.length; s++)//length of array
                            {
                                if( (String.valueOf(theData[s].charAt(0)).equalsIgnoreCase("1")) && (String.valueOf(theData[s].charAt(1)).equalsIgnoreCase("1")) )
                                {
                                    showErrorBox();
                                    displayTimer(displayTime);
                                    dirTxt.setVisibility(View.VISIBLE);
                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexZero))
                                    {
                                        dirTxt.setText(liftMessage);
                                    }
                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexOne))
                                    {
                                        arrowImages.setVisibility(View.VISIBLE);
                                        arrowImages.setImageResource(R.drawable.arrow_dn_eg);
                                    }
                                    if (String.valueOf(theData[s].charAt(9)).equalsIgnoreCase(hexThree))
                                    {
                                        arrowImages.setVisibility(View.VISIBLE);
                                        arrowImages.setImageResource(R.drawable.arrow_up_eg);
                                    }
                                    /*Checking Dongle*/
                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[0];
                                    }
                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[1];
                                    }
                                    if (String.valueOf(theData[s].charAt(13)).equalsIgnoreCase(hexThree))
                                    {
                                        errorMessage = errorStats[1]+","+errorStats[0];
                                    }
                                    /*End of Checking Dongle*/
                                    /*Checking ID mismatches*/
                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexEight))
                                    {
                                        errorMessage = errorStats[2];
                                    }
                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexFour))
                                    {
                                        errorMessage = errorStats[3];
                                    }
                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[4];
                                    }
                                    if (String.valueOf(theData[s].charAt(14)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[5];
                                    }
                                    /*End of Checking ID mismatches*/
                                    /*Relays & Motor related*/
                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexEight))
                                    {
                                        errorMessage = errorStats[6];
                                    }
                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexFour))
                                    {
                                        errorMessage = errorStats[7];
                                    }
                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[8];
                                    }
                                    if (String.valueOf(theData[s].charAt(15)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[9];
                                    }
                                    /*End of Relays & Motor related*/
                                    /****************************************************************************/
                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexEight))
                                    {
                                        errorMessage = errorStats[10];
                                    }
                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexFour))
                                    {
                                        errorMessage = errorStats[11];
                                    }
                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[12];
                                    }
                                    if (String.valueOf(theData[s].charAt(16)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[13];
                                    }
                                    /****************************************************************************/
                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexEight))
                                    {
                                        errorMessage = errorStats[14];
                                    }
                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexFour))
                                    {
                                        errorMessage = errorStats[15];
                                    }
                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[16];
                                    }
                                    if (String.valueOf(theData[s].charAt(17)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[17];
                                    }
                                    /****************************************************************************/
                                    /****************************************************************************/
                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexEight))
                                    {
                                        errorMessage = errorStats[18];
                                    }
                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexFour))
                                    {
                                        errorMessage = errorStats[19];
                                    }
                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[20];
                                    }
                                    if (String.valueOf(theData[s].charAt(18)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[21];
                                    }
                                    /****************************************************************************/
                                    /****************************************************************************/
                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexEight))
                                    {
                                        errorMessage = errorStats[22];
                                    }
                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexFour))
                                    {
                                        errorMessage = errorStats[23];
                                    }
                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[24];
                                    }
                                    if (String.valueOf(theData[s].charAt(19)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[25];
                                    }
                                    /****************************************************************************/
                                    /************************COMM STATUS****************************************/
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexFour))
                                    {
                                        errorMessage = errorStats[26];
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[27];
                                    }
                                    if (String.valueOf(theData[s].charAt(24)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[28];
                                    }
                                    /********************END OF COMM STATUS****************************************/
                                    /************************CURR MODE****************************************/
                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[29];
                                    }
                                    if (String.valueOf(theData[s].charAt(27)).equalsIgnoreCase(hexZero))
                                    {
                                        errorMessage = errorStats[30];
                                    }
                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexEight))
                                    {
                                        errorMessage = errorStats[31];
                                    }
                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexFour))
                                    {
                                        errorMessage = errorStats[32];
                                    }
                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexTwo))
                                    {
                                        errorMessage = errorStats[33];
                                    }
                                    if (String.valueOf(theData[s].charAt(28)).equalsIgnoreCase(hexOne))
                                    {
                                        errorMessage = errorStats[34];
                                    }
                                    /********************END OF CURR MODE****************************************/
                                }//end of command 11 - Lift Status
                            }
                        }//end of if()
                    }//end of if{}
                }//end of for()
            }//end of if{}
        }
        catch (Exception e)
        {
            //toast(e.toString());
            adminText.setText(e.toString());
        }
    }//end of lift status

    public void sendSMS(View view)
    {
        switch(view.getId())
        {
            case R.id.CMD7:
                sms.sendTextMessage(choice, null, stringCMD7, null, null);
                CMD7.setBackgroundColor(Color.GREEN);
                CMD7_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD8:
                sms.sendTextMessage(choice, null, stringCMD8, null, null);
                CMD8.setBackgroundColor(Color.GREEN);
                CMD8_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD9:
                sms.sendTextMessage(choice, null, stringCMD9, null, null);
                CMD9.setBackgroundColor(Color.GREEN);
                CMD9_text.setBackgroundColor(Color.GREEN);
                //disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD10:
                sms.sendTextMessage(choice, null, stringCMD10, null, null);
                CMD10.setBackgroundColor(Color.GREEN);
                CMD10_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD11:
                sms.sendTextMessage(choice, null, stringCMD11, null, null);
                CMD11.setBackgroundColor(Color.GREEN);
                CMD11_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD12:
                sms.sendTextMessage(choice, null, stringCMD12, null, null);
                CMD12.setBackgroundColor(Color.GREEN);
                CMD12_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD13:
                sms.sendTextMessage(choice, null, stringCMD13, null, null);
                CMD13.setBackgroundColor(Color.GREEN);
                CMD13_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD14:
                sms.sendTextMessage(choice, null, stringCMD14, null, null);
                CMD14.setBackgroundColor(Color.GREEN);
                CMD14_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD7_text:
                sms.sendTextMessage(choice, null, stringCMD7, null, null);
                CMD7.setBackgroundColor(Color.GREEN);
                CMD7_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD8_text:
                sms.sendTextMessage(choice, null, stringCMD8, null, null);
                CMD8.setBackgroundColor(Color.GREEN);
                CMD8_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD9_text:
                sms.sendTextMessage(choice, null, stringCMD9, null, null);
                CMD9.setBackgroundColor(Color.GREEN);
                CMD9_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                toast("SENT TO " +choice);
                break;
            case R.id.CMD10_text:
                sms.sendTextMessage(choice, null, stringCMD10, null, null);
                CMD10.setBackgroundColor(Color.GREEN);
                CMD10_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD11_text:
                sms.sendTextMessage(choice, null, stringCMD11, null, null);
                CMD11.setBackgroundColor(Color.GREEN);
                CMD11_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD12_text:
                sms.sendTextMessage(choice, null, stringCMD12, null, null);
                CMD12.setBackgroundColor(Color.GREEN);
                CMD12_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD13_text:
                sms.sendTextMessage(choice, null, stringCMD13, null, null);
                CMD13.setBackgroundColor(Color.GREEN);
                CMD13_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD14_text:
                sms.sendTextMessage(choice, null, stringCMD14, null, null);
                CMD14.setBackgroundColor(Color.GREEN);
                CMD14_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //disableUI();
                //toast("SENT TO " +choice);
                break;
        }//end of switch()
    }//end of sendSMS()

    public void disableUI()
    {
        CMD7.setEnabled(false);
        CMD8.setEnabled(false);
        CMD9.setEnabled(false);
        CMD10.setEnabled(false);
        CMD11.setEnabled(false);
        CMD12.setEnabled(false);
        CMD13.setEnabled(false);
        CMD14.setEnabled(false);

        CMD7_text.setEnabled(false);
        CMD8_text.setEnabled(false);
        CMD9_text.setEnabled(false);
        CMD10_text.setEnabled(false);
        CMD11_text.setEnabled(false);
        CMD12_text.setEnabled(false);
        CMD13_text.setEnabled(false);
        CMD14_text.setEnabled(false);
        //startTimer(timerValue);
    }//end of disableUI()

    public void enableUI()
    {
        CMD7.setEnabled(true);
        CMD8.setEnabled(true);
        CMD9.setEnabled(true);
        CMD10.setEnabled(true);
        CMD11.setEnabled(true);
        CMD12.setEnabled(true);
        CMD13.setEnabled(true);
        CMD14.setEnabled(true);

        CMD7_text.setEnabled(true);
        CMD8_text.setEnabled(true);
        CMD9_text.setEnabled(true);
        CMD10_text.setEnabled(true);
        CMD11_text.setEnabled(true);
        CMD12_text.setEnabled(true);
        CMD13_text.setEnabled(true);
        CMD14_text.setEnabled(true);

        CMD7.setBackgroundColor(Color.WHITE);
        CMD8.setBackgroundColor(Color.WHITE);
        CMD9.setBackgroundColor(Color.WHITE);
        CMD10.setBackgroundColor(Color.WHITE);
        CMD11.setBackgroundColor(Color.WHITE);
        CMD12.setBackgroundColor(Color.WHITE);
        CMD13.setBackgroundColor(Color.WHITE);
        CMD14.setBackgroundColor(Color.WHITE);

        CMD7_text.setBackgroundColor(Color.WHITE);
        CMD8_text.setBackgroundColor(Color.WHITE);
        CMD9_text.setBackgroundColor(Color.WHITE);
        CMD10_text.setBackgroundColor(Color.WHITE);
        CMD11_text.setBackgroundColor(Color.WHITE);
        CMD12_text.setBackgroundColor(Color.WHITE);
        CMD13_text.setBackgroundColor(Color.WHITE);
        CMD14_text.setBackgroundColor(Color.WHITE);

    }//end of enableUI()

    public void progressDisplay()
    {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Sending SMS");
        progress.setMessage("Please wait...) ");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setCancelable(true);
        progressBar.show();
        /*progressStatus = 0;
        final Handler handler = new Handler();
        //startTimer(timerValue);
        // Start the lengthy operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    // Update the progress status
                    progressStatus +=1;

                    // Try to sleep the thread for 20 milliseconds
                    try{
                        Thread.sleep(20);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            // Update the progress status
                            progressBar.setProgress(progressStatus);
                            // If task execution completed
                            if(progressStatus == 100){
                                // Dismiss/hide the progress dialog
                                progressBar.dismiss();
                                enableUI();
                            }
                        }
                    });
                }
            }
        }).start(); // Start the operation*/
    }

    public void setProgressDialog()
    {
        disableUI();
        progressDialog = ProgressDialog.show(this, "Sending SMS", "Please wait...!", true);
        CountDownTimer timer = new CountDownTimer(3000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

            }
            @Override
            public void onFinish()
            {
                progressDialog.dismiss();
            }
        }.start();
    }

    public void startTimer(int timeInMilliseconds)
    {
        cTimer = new CountDownTimer(timeInMilliseconds, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                //toast("Please wait: " + millisUntilFinished / 1000);
                if(millisUntilFinished/1000 == 1)
                {
                    cTimer.cancel();
                    //progressBar.cancel();
                    enableUI();
                }
            }
            public void onFinish()
            {
            }
        };
        cTimer.start();
    }//end of startTimer()

    public void displayTimer(int timeInMilliseconds)
    {
        cTimer = new CountDownTimer(timeInMilliseconds, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                //toast(String.valueOf(millisUntilFinished / 1000));
                //countDown.setText(String.valueOf(millisUntilFinished/1000));
                if(millisUntilFinished/1000 < 1)
                {
                    cTimer.cancel();
                    enableUI();
                    setAllInactive();
                }
            }
            public void onFinish()
            {
            }
        };
        cTimer.start();
    }//end of displayTimer()

    public void setAllInactive()
    {
        countDown.setVisibility(View.INVISIBLE);
        displayNothing();
        sms_cmd7_bit0.setChecked(false);
        sms_cmd7_bit1.setChecked(false);
        sms_cmd7_bit2.setChecked(false);
        sms_cmd7_bit3.setChecked(false);
        sms_cmd7_bit4.setChecked(false);
        sms_cmd7_bit5.setChecked(false);
        sms_cmd7_bit6.setChecked(false);
        sms_cmd7_bit7.setChecked(false);
        sms_cmd8_bit_fr.setChecked(false);
        sms_cmd8_bit_lft_lck.setChecked(false);
        sms_cmd7_cbc_bit0.setChecked(false);
        sms_cmd7_cbc_bit1.setChecked(false);
        sms_cmd7_cbc_bit2.setChecked(false);
        sms_cmd7_cbc_bit3.setChecked(false);
        sms_cmd7_cbc_bit4.setChecked(false);
        sms_cmd7_cbc_bit5.setChecked(false);
        sms_cmd7_cbc_bit6.setChecked(false);
        sms_cmd7_cbc_bit7.setChecked(false);
        sms_cmd7_cbc_bit8.setChecked(false);
        sms_cmd7_cbc_bit9.setChecked(false);
        sms_cmd7_cbc_bit10.setChecked(false);
        sms_cmd7_cbc_bit11.setChecked(false);
        sms_cmd7_cbc_bit12.setChecked(false);

        sms_cmd8_bit0.setChecked(false);
        sms_cmd8_bit1.setChecked(false);
        sms_cmd8_bit2.setChecked(false);
        sms_cmd8_bit3.setChecked(false);
        sms_cmd8_bit4.setChecked(false);
        sms_cmd8_bit5.setChecked(false);
        sms_cmd8_bit6.setChecked(false);
        sms_cmd8_bit7.setChecked(false);
        sms_cmd8_bit_fr.setChecked(false);
        sms_cmd8_bit_lft_lck.setChecked(false);


        sms_cmd8_H_bit0.setChecked(false);
        sms_cmd8_H_bit1.setChecked(false);
        sms_cmd8_H_bit2.setChecked(false);
        sms_cmd8_H_bit3.setChecked(false);
        sms_cmd8_H_bit4.setChecked(false);
        sms_cmd8_H_bit5.setChecked(false);
        sms_cmd8_H_bit6.setChecked(false);
        sms_cmd8_H_bit7.setChecked(false);

        cmd8_bit0.setChecked(false);
        cmd8_bit1.setChecked(false);
        cmd8_bit2.setChecked(false);
        cmd8_bit3.setChecked(false);
        cmd8_bit4.setChecked(false);
        cmd8_bit5.setChecked(false);
        cmd8_bit6.setChecked(false);
        cmd8_bit7.setChecked(false);
        cmd8_bit9.setChecked(false);
        cmd8_bit10.setChecked(false);
        cmd8_bit11.setChecked(false);
        cmd8_bit12.setChecked(false);
        cmd8_bit13.setChecked(false);

        cmd8_cbc_bit0.setChecked(false);
        cmd8_cbc_bit1.setChecked(false);
        cmd8_cbc_bit2.setChecked(false);
        cmd8_cbc_bit3.setChecked(false);
        cmd8_cbc_bit4.setChecked(false);
        cmd8_cbc_bit5.setChecked(false);
        cmd8_cbc_bit8.setChecked(false);
        cmd8_cbc_bit9.setChecked(false);
        cmd8_cbc_bit10.setChecked(false);
        cmd8_cbc_bit11.setChecked(false);
        cmd8_cbc_bit12.setChecked(false);
        cmd8_cbc_bit13.setChecked(false);

        cmd9_u_bit0.setChecked(false);
        cmd9_u_bit1.setChecked(false);
        cmd9_u_bit2.setChecked(false);
        cmd9_u_bit3.setChecked(false);
        cmd9_u_bit4.setChecked(false);
        cmd9_u_bit5.setChecked(false);
        cmd9_u_bit6.setChecked(false);
        cmd9_u_bit7.setChecked(false);
        cmd9_u_bit8.setChecked(false);
        cmd9_u_bit9.setChecked(false);
        cmd9_u_bit10.setChecked(false);
        cmd9_u_bit11.setChecked(false);
        cmd9_u_bit12.setChecked(false);
        cmd9_u_bit13.setChecked(false);
        cmd9_u_bit14.setChecked(false);
        cmd9_u_bit15.setChecked(false);
        cmd9_u_bit16.setChecked(false);
        cmd9_u_bit17.setChecked(false);
        cmd9_u_bit18.setChecked(false);
        cmd9_u_bit19.setChecked(false);
        cmd9_u_bit20.setChecked(false);
        cmd9_u_bit21.setChecked(false);
        cmd9_u_bit22.setChecked(false);
        cmd9_u_bit23.setChecked(false);
        cmd9_u_bit24.setChecked(false);
        cmd9_u_bit25.setChecked(false);
        cmd9_u_bit26.setChecked(false);
        cmd9_u_bit27.setChecked(false);
        cmd9_u_bit28.setChecked(false);
        cmd9_u_bit29.setChecked(false);
        cmd9_u_bit30.setChecked(false);
        cmd9_u_bit31.setChecked(false);

        cmd9_d_bit0.setChecked(false);
        cmd9_d_bit1.setChecked(false);
        cmd9_d_bit2.setChecked(false);
        cmd9_d_bit3.setChecked(false);
        cmd9_d_bit4.setChecked(false);
        cmd9_d_bit5.setChecked(false);
        cmd9_d_bit6.setChecked(false);
        cmd9_d_bit7.setChecked(false);
        cmd9_d_bit8.setChecked(false);
        cmd9_d_bit9.setChecked(false);
        cmd9_d_bit10.setChecked(false);
        cmd9_d_bit11.setChecked(false);
        cmd9_d_bit12.setChecked(false);
        cmd9_d_bit13.setChecked(false);
        cmd9_d_bit14.setChecked(false);
        cmd9_d_bit15.setChecked(false);
        cmd9_d_bit16.setChecked(false);
        cmd9_d_bit17.setChecked(false);
        cmd9_d_bit18.setChecked(false);
        cmd9_d_bit19.setChecked(false);
        cmd9_d_bit20.setChecked(false);
        cmd9_d_bit21.setChecked(false);
        cmd9_d_bit22.setChecked(false);
        cmd9_d_bit23.setChecked(false);
        cmd9_d_bit24.setChecked(false);
        cmd9_d_bit25.setChecked(false);
        cmd9_d_bit26.setChecked(false);
        cmd9_d_bit27.setChecked(false);
        cmd9_d_bit28.setChecked(false);
        cmd9_d_bit29.setChecked(false);
        cmd9_d_bit30.setChecked(false);
        cmd9_d_bit31.setChecked(false);

        cmd9_c_bit0.setChecked(false);
        cmd9_c_bit1.setChecked(false);
        cmd9_c_bit2.setChecked(false);
        cmd9_c_bit3.setChecked(false);
        cmd9_c_bit4.setChecked(false);
        cmd9_c_bit5.setChecked(false);
        cmd9_c_bit6.setChecked(false);
        cmd9_c_bit7.setChecked(false);
        cmd9_c_bit8.setChecked(false);
        cmd9_c_bit9.setChecked(false);
        cmd9_c_bit10.setChecked(false);
        cmd9_c_bit11.setChecked(false);
        cmd9_c_bit12.setChecked(false);
        cmd9_c_bit13.setChecked(false);
        cmd9_c_bit14.setChecked(false);
        cmd9_c_bit15.setChecked(false);
        cmd9_c_bit16.setChecked(false);
        cmd9_c_bit17.setChecked(false);
        cmd9_c_bit18.setChecked(false);
        cmd9_c_bit19.setChecked(false);
        cmd9_c_bit20.setChecked(false);
        cmd9_c_bit21.setChecked(false);
        cmd9_c_bit22.setChecked(false);
        cmd9_c_bit23.setChecked(false);
        cmd9_c_bit24.setChecked(false);
        cmd9_c_bit25.setChecked(false);
        cmd9_c_bit26.setChecked(false);
        cmd9_c_bit27.setChecked(false);
        cmd9_c_bit28.setChecked(false);
        cmd9_c_bit29.setChecked(false);
        cmd9_c_bit30.setChecked(false);
        cmd9_c_bit31.setChecked(false);

        cmd9_ui_bit0.setChecked(false);
        cmd9_ui_bit1.setChecked(false);
        cmd9_ui_bit2.setChecked(false);
        cmd9_ui_bit3.setChecked(false);
        cmd9_ui_bit4.setChecked(false);
        cmd9_ui_bit5.setChecked(false);
        cmd9_ui_bit6.setChecked(false);
        cmd9_ui_bit7.setChecked(false);
        cmd9_ui_bit8.setChecked(false);
        cmd9_ui_bit9.setChecked(false);
        cmd9_ui_bit10.setChecked(false);
        cmd9_ui_bit11.setChecked(false);
        cmd9_ui_bit12.setChecked(false);
        cmd9_ui_bit13.setChecked(false);
        cmd9_ui_bit14.setChecked(false);
        cmd9_ui_bit15.setChecked(false);
        cmd9_ui_bit16.setChecked(false);
        cmd9_ui_bit17.setChecked(false);
        cmd9_ui_bit18.setChecked(false);
        cmd9_ui_bit19.setChecked(false);
        cmd9_ui_bit20.setChecked(false);
        cmd9_ui_bit21.setChecked(false);
        cmd9_ui_bit22.setChecked(false);
        cmd9_ui_bit23.setChecked(false);
        cmd9_ui_bit24.setChecked(false);
        cmd9_ui_bit25.setChecked(false);
        cmd9_ui_bit26.setChecked(false);
        cmd9_ui_bit27.setChecked(false);
        cmd9_ui_bit28.setChecked(false);
        cmd9_ui_bit29.setChecked(false);
        cmd9_ui_bit30.setChecked(false);
        cmd9_ui_bit31.setChecked(false);

        cmd9_di_bit0.setChecked(false);
        cmd9_di_bit1.setChecked(false);
        cmd9_di_bit2.setChecked(false);
        cmd9_di_bit3.setChecked(false);
        cmd9_di_bit4.setChecked(false);
        cmd9_di_bit5.setChecked(false);
        cmd9_di_bit6.setChecked(false);
        cmd9_di_bit7.setChecked(false);
        cmd9_di_bit8.setChecked(false);
        cmd9_di_bit9.setChecked(false);
        cmd9_di_bit10.setChecked(false);
        cmd9_di_bit11.setChecked(false);
        cmd9_di_bit12.setChecked(false);
        cmd9_di_bit13.setChecked(false);
        cmd9_di_bit14.setChecked(false);
        cmd9_di_bit15.setChecked(false);
        cmd9_di_bit16.setChecked(false);
        cmd9_di_bit17.setChecked(false);
        cmd9_di_bit18.setChecked(false);
        cmd9_di_bit19.setChecked(false);
        cmd9_di_bit20.setChecked(false);
        cmd9_di_bit21.setChecked(false);
        cmd9_di_bit22.setChecked(false);
        cmd9_di_bit23.setChecked(false);
        cmd9_di_bit24.setChecked(false);
        cmd9_di_bit25.setChecked(false);
        cmd9_di_bit26.setChecked(false);
        cmd9_di_bit27.setChecked(false);
        cmd9_di_bit28.setChecked(false);
        cmd9_di_bit29.setChecked(false);
        cmd9_di_bit30.setChecked(false);
        cmd9_di_bit31.setChecked(false);

        cmd9_ci_bit0.setChecked(false);
        cmd9_ci_bit1.setChecked(false);
        cmd9_ci_bit2.setChecked(false);
        cmd9_ci_bit3.setChecked(false);
        cmd9_ci_bit4.setChecked(false);
        cmd9_ci_bit5.setChecked(false);
        cmd9_ci_bit6.setChecked(false);
        cmd9_ci_bit7.setChecked(false);
        cmd9_ci_bit8.setChecked(false);
        cmd9_ci_bit9.setChecked(false);
        cmd9_ci_bit10.setChecked(false);
        cmd9_ci_bit11.setChecked(false);
        cmd9_ci_bit12.setChecked(false);
        cmd9_ci_bit13.setChecked(false);
        cmd9_ci_bit14.setChecked(false);
        cmd9_ci_bit15.setChecked(false);
        cmd9_ci_bit16.setChecked(false);
        cmd9_ci_bit17.setChecked(false);
        cmd9_ci_bit18.setChecked(false);
        cmd9_ci_bit19.setChecked(false);
        cmd9_ci_bit20.setChecked(false);
        cmd9_ci_bit21.setChecked(false);
        cmd9_ci_bit22.setChecked(false);
        cmd9_ci_bit23.setChecked(false);
        cmd9_ci_bit24.setChecked(false);
        cmd9_ci_bit25.setChecked(false);
        cmd9_ci_bit26.setChecked(false);
        cmd9_ci_bit27.setChecked(false);
        cmd9_ci_bit28.setChecked(false);
        cmd9_ci_bit29.setChecked(false);
        cmd9_ci_bit30.setChecked(false);
        cmd9_ci_bit31.setChecked(false);

        dirTxt.setVisibility(View.INVISIBLE);
        arrowImages.setVisibility(View.INVISIBLE);
        msgTxt.setText(message);
    }

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }//end of toast

    private void checkForSmsPermission()
    {
        /*CHECKING AND REQUESTING FOR SENDIN SMS*/
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            //toast("REQUESTING PERMISSION TO SEND SMS");
        }
        else
        {
            //toast("SMS SENDING PERMISSION GRANTED");
        }

        /*CHECKING AND REQUESTING FOR RECEIVING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_RECEIVE_SMS);
            //toast("REQUESTING PERMISSION TO RECEIVE SMS");
        }
        else
        {
            //toast("SMS RECEIVING PERMISSION GRANTED");
        }

        /*CHECKING AND REQUESTING FOR READING SMS*/
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},MY_PERMISSIONS_REQUEST_READ_SMS);
            //toast("REQUESTING PERMISSION TO READ SMS");
        }
        else
        {
            //toast("SMS READING PERMISSION GRANTED");
        }

    }//end of checkForSmsPermission()

    public void deleteTable()
    {
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        theSqLiteDatabase.execSQL("DROP TABLE ADMIN_SMS");
        toast("THE TABLE ADMIN_SMS IS DELETED");
    }

    @Override
    public void onBackPressed()
    {
        System.exit(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.delete_records, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                deleteTable();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()

    public void displayNothing()
    {
        ss_h_a.setBackgroundColor(Color.WHITE);
        ss_h_b.setBackgroundColor(Color.WHITE);
        ss_h_c.setBackgroundColor(Color.WHITE);
        ss_h_d.setBackgroundColor(Color.WHITE);
        ss_h_e.setBackgroundColor(Color.WHITE);
        ss_h_f.setBackgroundColor(Color.WHITE);
        ss_h_g.setBackgroundColor(Color.WHITE);

        ss_a.setBackgroundColor(Color.WHITE);
        ss_b.setBackgroundColor(Color.WHITE);
        ss_c.setBackgroundColor(Color.WHITE);
        ss_d.setBackgroundColor(Color.WHITE);
        ss_e.setBackgroundColor(Color.WHITE);
        ss_f.setBackgroundColor(Color.WHITE);
        ss_g.setBackgroundColor(Color.WHITE);
    }
    public void displayZero_H()
    {
        ss_h_a.setBackgroundColor(Color.RED);
        ss_h_b.setBackgroundColor(Color.RED);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.RED);
        ss_h_e.setBackgroundColor(Color.RED);
        ss_h_f.setBackgroundColor(Color.RED);
        ss_h_g.setBackgroundColor(Color.WHITE);
    }
    public void displayOne_H()
    {
        ss_h_a.setBackgroundColor(Color.WHITE);
        ss_h_b.setBackgroundColor(Color.RED);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.WHITE);
        ss_h_e.setBackgroundColor(Color.WHITE);
        ss_h_f.setBackgroundColor(Color.WHITE);
        ss_h_g.setBackgroundColor(Color.WHITE);
    }
    public void displayTwo_H()
    {
        ss_h_a.setBackgroundColor(Color.RED);
        ss_h_b.setBackgroundColor(Color.RED);
        ss_h_c.setBackgroundColor(Color.WHITE);
        ss_h_d.setBackgroundColor(Color.RED);
        ss_h_e.setBackgroundColor(Color.RED);
        ss_h_f.setBackgroundColor(Color.WHITE);
        ss_h_g.setBackgroundColor(Color.RED);
    }
    public void displayThree_H()
    {
        ss_h_a.setBackgroundColor(Color.RED);
        ss_h_b.setBackgroundColor(Color.RED);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.RED);
        ss_h_e.setBackgroundColor(Color.WHITE);
        ss_h_f.setBackgroundColor(Color.WHITE);
        ss_h_g.setBackgroundColor(Color.RED);
    }
    public void displayFour_H()
    {
        ss_h_a.setBackgroundColor(Color.WHITE);
        ss_h_b.setBackgroundColor(Color.RED);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.WHITE);
        ss_h_e.setBackgroundColor(Color.WHITE);
        ss_h_f.setBackgroundColor(Color.RED);
        ss_h_g.setBackgroundColor(Color.RED);
    }
    public void displayFive_H()
    {
        ss_h_a.setBackgroundColor(Color.RED);
        ss_h_b.setBackgroundColor(Color.WHITE);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.WHITE);
        ss_h_e.setBackgroundColor(Color.RED);
        ss_h_f.setBackgroundColor(Color.RED);
        ss_h_g.setBackgroundColor(Color.RED);
    }
    public void displaySix_H()
    {
        ss_h_a.setBackgroundColor(Color.RED);
        ss_h_b.setBackgroundColor(Color.WHITE);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.RED);
        ss_h_e.setBackgroundColor(Color.RED);
        ss_h_f.setBackgroundColor(Color.RED);
        ss_h_g.setBackgroundColor(Color.RED);
    }
    public void displaySeven_H()
    {
        ss_h_a.setBackgroundColor(Color.RED);
        ss_h_b.setBackgroundColor(Color.RED);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.WHITE);
        ss_h_e.setBackgroundColor(Color.WHITE);
        ss_h_f.setBackgroundColor(Color.WHITE);
        ss_h_g.setBackgroundColor(Color.WHITE);
    }
    public void displayEight_H()
    {
        ss_h_a.setBackgroundColor(Color.RED);
        ss_h_b.setBackgroundColor(Color.RED);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.RED);
        ss_h_e.setBackgroundColor(Color.RED);
        ss_h_f.setBackgroundColor(Color.RED);
        ss_h_g.setBackgroundColor(Color.RED);
    }
    public void displayNine_H()
    {
        ss_h_a.setBackgroundColor(Color.RED);
        ss_h_b.setBackgroundColor(Color.RED);
        ss_h_c.setBackgroundColor(Color.RED);
        ss_h_d.setBackgroundColor(Color.RED);
        ss_h_e.setBackgroundColor(Color.WHITE);
        ss_h_f.setBackgroundColor(Color.RED);
        ss_h_g.setBackgroundColor(Color.RED);
    }

    public void displayZero()
    {
        ss_a.setBackgroundColor(Color.RED);
        ss_b.setBackgroundColor(Color.RED);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.RED);
        ss_e.setBackgroundColor(Color.RED);
        ss_f.setBackgroundColor(Color.RED);
        ss_g.setBackgroundColor(Color.WHITE);
    }
    public void displayOne()
    {
        ss_a.setBackgroundColor(Color.WHITE);
        ss_b.setBackgroundColor(Color.RED);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.WHITE);
        ss_e.setBackgroundColor(Color.WHITE);
        ss_f.setBackgroundColor(Color.WHITE);
        ss_g.setBackgroundColor(Color.WHITE);
    }
    public void displayTwo()
    {
        ss_a.setBackgroundColor(Color.RED);
        ss_b.setBackgroundColor(Color.RED);
        ss_c.setBackgroundColor(Color.WHITE);
        ss_d.setBackgroundColor(Color.RED);
        ss_e.setBackgroundColor(Color.RED);
        ss_f.setBackgroundColor(Color.WHITE);
        ss_g.setBackgroundColor(Color.RED);
    }
    public void displayThree()
    {
        ss_a.setBackgroundColor(Color.RED);
        ss_b.setBackgroundColor(Color.RED);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.RED);
        ss_e.setBackgroundColor(Color.WHITE);
        ss_f.setBackgroundColor(Color.WHITE);
        ss_g.setBackgroundColor(Color.RED);
    }
    public void displayFour()
    {
        ss_a.setBackgroundColor(Color.WHITE);
        ss_b.setBackgroundColor(Color.RED);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.WHITE);
        ss_e.setBackgroundColor(Color.WHITE);
        ss_f.setBackgroundColor(Color.RED);
        ss_g.setBackgroundColor(Color.RED);
    }
    public void displayFive()
    {
        ss_a.setBackgroundColor(Color.RED);
        ss_b.setBackgroundColor(Color.WHITE);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.WHITE);
        ss_e.setBackgroundColor(Color.RED);
        ss_f.setBackgroundColor(Color.RED);
        ss_g.setBackgroundColor(Color.RED);
    }
    public void displaySix()
    {
        ss_a.setBackgroundColor(Color.RED);
        ss_b.setBackgroundColor(Color.WHITE);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.RED);
        ss_e.setBackgroundColor(Color.RED);
        ss_f.setBackgroundColor(Color.RED);
        ss_g.setBackgroundColor(Color.RED);
    }
    public void displaySeven()
    {
        ss_a.setBackgroundColor(Color.RED);
        ss_b.setBackgroundColor(Color.RED);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.WHITE);
        ss_e.setBackgroundColor(Color.WHITE);
        ss_f.setBackgroundColor(Color.WHITE);
        ss_g.setBackgroundColor(Color.WHITE);
    }
    public void displayEight()
    {
        ss_a.setBackgroundColor(Color.RED);
        ss_b.setBackgroundColor(Color.RED);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.RED);
        ss_e.setBackgroundColor(Color.RED);
        ss_f.setBackgroundColor(Color.RED);
        ss_g.setBackgroundColor(Color.RED);
    }
    public void displayNine()
    {
        ss_a.setBackgroundColor(Color.RED);
        ss_b.setBackgroundColor(Color.RED);
        ss_c.setBackgroundColor(Color.RED);
        ss_d.setBackgroundColor(Color.RED);
        ss_e.setBackgroundColor(Color.WHITE);
        ss_f.setBackgroundColor(Color.RED);
        ss_g.setBackgroundColor(Color.RED);
    }
    public void showErrorBox()
    {
        AlertDialog dialog;
        ArrayList<String> ErrorStat = new ArrayList<String>();
        ErrorStat.clear();
        ErrorStat.add(errorMessage);
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lift Error Status");
        ListView listErrors     = new ListView(this);
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ErrorStat);
        listErrors.setAdapter(modeAdapter);
        builder.setView(listErrors);
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {

            }
        });
        dialog = builder.create();
        dialog.show();
    }
}
