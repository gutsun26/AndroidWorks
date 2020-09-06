package com.web.udni_sms;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.web.udni_sms.customer_db.CustomerControllerDB;
import com.web.udni_sms.customer_db.CustomerSMSReceiverControllerDB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SendSMSActivity3 extends AppCompatActivity //implements AdapterView.OnItemSelectedListener //ExpandableListActivity //FragmentActivity
{
    /******************************MODEL****************************************/
    /*SMS SENDING RELATED*/
            /*
    String  stringCMD7      = "#07%000000AA";//MAIN CARD INPUT
    String  stringCMD8      = "#08%0000042F";//MAIN CARD TERMINAL INPUT
    String  stringCMD9      = "#09%0000BCDE";//MAIN CARD OUTPUT
    String  stringCMD10     = "#10%0000BCDE";//CBC INPUTS
    String  stringCMD11     = "#11%0000EBCD";//CBC OUTPUTS
    String  stringCMD12     = "#12%CE550423";//FLOOR UP KEYS
    String  stringCMD13     = "#13%AACB6813";//FLOOR DOWN KEYS
    String  stringCMD14     = "#14%AACB6813";//CAR CALLS
    String  stringCMD15     = "#15%00000004";//LIFT STATUS
    String  stringCMD16     = "#16%000ABCDE";
    String  stringCMD17     = "#17%";
    String  stringCMD18     = "#18%";
    String  stringCMD19     = "#19%00000001";
    String  stringCMD20     = "#20%00000001";
    */
    /*THE ACTUAL COMMANDS*/

    String  stringCMD7      = "#07%";//MAIN CARD INPUT
    String  stringCMD8      = "#08%";//MAIN CARD TERMINAL INPUT
    String  stringCMD9      = "#09%";//MAIN CARD OUTPUT
    String  stringCMD10     = "#10%";//CBC INPUTS
    String  stringCMD11     = "#11%";//CBC OUTPUTS
    String  stringCMD12     = "#12%";//FLOOR UP KEYS
    String  stringCMD13     = "#13%";//FLOOR DOWN KEYS
    String  stringCMD14     = "#14%";//CAR CALLS
    String  stringCMD15     = "#15%";//LIFT STATUS
    String  stringCMD16     = "#16%";
    String  stringCMD17     = "#17%";
    String  stringCMD18     = "#18%";
    String  stringCMD19     = "#19%";
    String  stringCMD20     = "#20%";

    String modbusError = "#99%00000001";
    String replyError = "Reply error! Try again!";

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
    ProgressDialog progressBar, progressDialog;
    ImageView imageButton1, imageButton2, imageButton3;
    ImageView CMD7,CMD8,CMD9,CMD10,CMD11,CMD12,CMD13,CMD14,CMD15,CMD16,CMD17,CMD18,CMD19,CMD20,CMD21;
    TextView CMD7_text,CMD8_text,CMD9_text,CMD10_text,CMD11_text,CMD12_text,CMD13_text,CMD14_text,CMD15_text,CMD16_text,CMD17_text,CMD18_text,CMD19_text,CMD20_text,CMD21_text;
    Button insertTheDestNumberBtn,phoneBookButton;
    EditText theReceivingNumber,theClient;
    Spinner destNumber;
    TextView today, dateTimeDisplay,timerText,adminText,numberText;
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    int timerValue = 6000;
    int displayTime = 11000;
    String adminString="ADMIN";
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
    LinearLayout sms_cmd7;
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
    /*COMMAND 9 - MAIN CARD OUTPUT - LOWER BYTE */
    TextView sms_cmd9_L_bit0_txt, sms_cmd9_L_bit1_txt, sms_cmd9_L_bit2_txt, sms_cmd9_L_bit3_txt, sms_cmd9_L_bit4_txt, sms_cmd9_L_bit5_txt, sms_cmd9_L_bit6_txt, sms_cmd9_L_bit7_txt;
    RadioButton sms_cmd9_L_bit0, sms_cmd9_L_bit1, sms_cmd9_L_bit2, sms_cmd9_L_bit3, sms_cmd9_L_bit4, sms_cmd9_L_bit5, sms_cmd9_L_bit6, sms_cmd9_L_bit7;
    /*COMMAND 9- MAIN CARD OUTPUT - HIGHER BYTE*/
    TextView sms_cmd9_H_bit0_txt, sms_cmd9_H_bit1_txt, sms_cmd9_H_bit2_txt, sms_cmd9_H_bit3_txt, sms_cmd9_H_bit4_txt, sms_cmd9_H_bit5_txt, sms_cmd9_H_bit6_txt, sms_cmd9_H_bit7_txt;
    RadioButton sms_cmd9_H_bit0, sms_cmd9_H_bit1, sms_cmd9_H_bit2, sms_cmd9_H_bit3, sms_cmd9_H_bit4, sms_cmd9_H_bit5, sms_cmd9_H_bit6, sms_cmd9_H_bit7;
    String sms_cmd9_txts_L[] = {
            "RKM",
            "RCAM",
            "ARD",
            "DIR_RD",
            "PRE",
            "EX1",
            "DBK",
            "BRK"
    };
    String sms_cmd9_txts_H[] = {
            "---",
            "DC",
            "S1",
            "S2",
            "UP",
            "DN",
            "---",
            "---",
    };

    /*COMMAND 10 - CBC INPUTS - LOWER BYTE */
    TextView sms_cmd10_L_bit0_txt, sms_cmd10_L_bit1_txt, sms_cmd10_L_bit2_txt, sms_cmd10_L_bit3_txt, sms_cmd10_L_bit4_txt, sms_cmd10_L_bit5_txt, sms_cmd10_L_bit6_txt, sms_cmd10_L_bit7_txt;
    RadioButton sms_cmd10_L_bit0, sms_cmd10_L_bit1, sms_cmd10_L_bit2, sms_cmd10_L_bit3, sms_cmd10_L_bit4, sms_cmd10_L_bit5, sms_cmd10_L_bit6, sms_cmd10_L_bit7;
    /*COMMAND 10 - CBC INPUTS - HIGHER BYTE*/
    TextView sms_cmd10_H_bit0_txt, sms_cmd10_H_bit1_txt, sms_cmd10_H_bit2_txt, sms_cmd10_H_bit3_txt, sms_cmd10_H_bit4_txt, sms_cmd10_H_bit5_txt, sms_cmd10_H_bit6_txt, sms_cmd10_H_bit7_txt;
    RadioButton sms_cmd10_H_bit0, sms_cmd10_H_bit1, sms_cmd10_H_bit2, sms_cmd10_H_bit3, sms_cmd10_H_bit4, sms_cmd10_H_bit5, sms_cmd10_H_bit6, sms_cmd10_H_bit7;
    String sms_cmd10_txts_L[] = {
            "DOPB",
            "LC",
            "ULD",
            "FLD",
            "OVLD",
            "NS",
            "ATT",
            "EMST" //EMR STOP
    };
    String sms_cmd10_txts_H[] = {
            "---",
            "---",
            "---",
            "ACR",
            "IFST",
            "DOLT",
            "DCLT",
            "DCPB",
    };
    /*COMMAND 11 - CBC OUTPUTS - LOWER BYTE */
    TextView sms_cmd11_L_bit0_txt, sms_cmd11_L_bit1_txt, sms_cmd11_L_bit2_txt, sms_cmd11_L_bit3_txt, sms_cmd11_L_bit4_txt, sms_cmd11_L_bit5_txt, sms_cmd11_L_bit6_txt, sms_cmd11_L_bit7_txt;
    RadioButton sms_cmd11_L_bit0, sms_cmd11_L_bit1, sms_cmd11_L_bit2, sms_cmd11_L_bit3, sms_cmd11_L_bit4, sms_cmd11_L_bit5, sms_cmd11_L_bit6, sms_cmd11_L_bit7;
    /*COMMAND 11 - CBC OUTPUT - HIGHER BYTE*/
    TextView sms_cmd11_H_bit0_txt, sms_cmd11_H_bit1_txt, sms_cmd11_H_bit2_txt, sms_cmd11_H_bit3_txt, sms_cmd11_H_bit4_txt, sms_cmd11_H_bit5_txt, sms_cmd11_H_bit6_txt, sms_cmd11_H_bit7_txt;
    RadioButton sms_cmd11_H_bit0, sms_cmd11_H_bit1, sms_cmd11_H_bit2, sms_cmd11_H_bit3, sms_cmd11_H_bit4, sms_cmd11_H_bit5, sms_cmd11_H_bit6, sms_cmd11_H_bit7;
    String sms_cmd11_txts_L[] = {
            "---",
            "---",
            "LIGHT",
            "FAN",
            "DOF",
            "DCF",
            "DOBF",
            "GONG"
    };
    String sms_cmd11_txts_H[] = {
            "---",
            "---",
            "EX2",
            "WLCM",
            "THY",
            "DDR",
            "DCR",
            "DOBR",
    };

    /*COMMAND 12 - FLOOR UP KEYS - 0 to 7 */
    TextView sms_cmd12_L_bit0_txt, sms_cmd12_L_bit1_txt, sms_cmd12_L_bit2_txt, sms_cmd12_L_bit3_txt, sms_cmd12_L_bit4_txt, sms_cmd12_L_bit5_txt, sms_cmd12_L_bit6_txt, sms_cmd12_L_bit7_txt;
    RadioButton sms_cmd12_L_bit0, sms_cmd12_L_bit1, sms_cmd12_L_bit2, sms_cmd12_L_bit3, sms_cmd12_L_bit4, sms_cmd12_L_bit5, sms_cmd12_L_bit6, sms_cmd12_L_bit7;
    /*COMMAND 12 - FLOOR UP KEYS - 8 to 15*/
    TextView sms_cmd12_H_bit0_txt, sms_cmd12_H_bit1_txt, sms_cmd12_H_bit2_txt, sms_cmd12_H_bit3_txt, sms_cmd12_H_bit4_txt, sms_cmd12_H_bit5_txt, sms_cmd12_H_bit6_txt, sms_cmd12_H_bit7_txt;
    RadioButton sms_cmd12_H_bit0, sms_cmd12_H_bit1, sms_cmd12_H_bit2, sms_cmd12_H_bit3, sms_cmd12_H_bit4, sms_cmd12_H_bit5, sms_cmd12_H_bit6, sms_cmd12_H_bit7;
    String sms_cmd12_txts_L[] = {
            "U7",
            "U6",
            "U5",
            "U4",
            "U3",
            "U2",
            "U1",
            "U0"
    };
    String sms_cmd12_txts_H[] = {
            "U15",
            "U14",
            "U13",
            "U12",
            "U11",
            "U10",
            "U9",
            "U8",
    };

    /*COMMAND 12 - FLOOR UP KEYS - 16 to 23 */
    TextView sms_cmd12_L_M_bit0_txt, sms_cmd12_L_M_bit1_txt, sms_cmd12_L_M_bit2_txt, sms_cmd12_L_M_bit3_txt, sms_cmd12_L_M_bit4_txt, sms_cmd12_L_M_bit5_txt, sms_cmd12_L_M_bit6_txt, sms_cmd12_L_M_bit7_txt;
    RadioButton sms_cmd12_L_M_bit0, sms_cmd12_L_M_bit1, sms_cmd12_L_M_bit2, sms_cmd12_L_M_bit3, sms_cmd12_L_M_bit4, sms_cmd12_L_M_bit5, sms_cmd12_L_M_bit6, sms_cmd12_L_M_bit7;
    /*COMMAND 12 - FLOOR UP KEYS - 24 to 31*/
    TextView sms_cmd12_H_M_bit0_txt, sms_cmd12_H_M_bit1_txt, sms_cmd12_H_M_bit2_txt, sms_cmd12_H_M_bit3_txt, sms_cmd12_H_M_bit4_txt, sms_cmd12_H_M_bit5_txt, sms_cmd12_H_M_bit6_txt, sms_cmd12_H_M_bit7_txt;
    RadioButton sms_cmd12_H_M_bit0, sms_cmd12_H_M_bit1, sms_cmd12_H_M_bit2, sms_cmd12_H_M_bit3, sms_cmd12_H_M_bit4, sms_cmd12_H_M_bit5, sms_cmd12_H_M_bit6, sms_cmd12_H_M_bit7;
    String sms_cmd12_txts_L_M[] = {
            "U23",
            "U22",
            "U21",
            "U20",
            "U19",
            "U18",
            "U17",
            "U16"
    };
    String sms_cmd12_txts_H_M[] = {
            "U31",
            "U30",
            "U29",
            "U28",
            "U27",
            "U26",
            "U25",
            "U24",
    };

    /*COMMAND 13 - FLOOR DN KEYS - 0 to 7 */
    TextView sms_cmd13_L_bit0_txt, sms_cmd13_L_bit1_txt, sms_cmd13_L_bit2_txt, sms_cmd13_L_bit3_txt, sms_cmd13_L_bit4_txt, sms_cmd13_L_bit5_txt, sms_cmd13_L_bit6_txt, sms_cmd13_L_bit7_txt;
    RadioButton sms_cmd13_L_bit0, sms_cmd13_L_bit1, sms_cmd13_L_bit2, sms_cmd13_L_bit3, sms_cmd13_L_bit4, sms_cmd13_L_bit5, sms_cmd13_L_bit6, sms_cmd13_L_bit7;
    /*COMMAND 13 - FLOOR DN KEYS - 8 to 15*/
    TextView sms_cmd13_H_bit0_txt, sms_cmd13_H_bit1_txt, sms_cmd13_H_bit2_txt, sms_cmd13_H_bit3_txt, sms_cmd13_H_bit4_txt, sms_cmd13_H_bit5_txt, sms_cmd13_H_bit6_txt, sms_cmd13_H_bit7_txt;
    RadioButton sms_cmd13_H_bit0, sms_cmd13_H_bit1, sms_cmd13_H_bit2, sms_cmd13_H_bit3, sms_cmd13_H_bit4, sms_cmd13_H_bit5, sms_cmd13_H_bit6, sms_cmd13_H_bit7;
    String sms_cmd13_txts_L[] = {
            "D7",
            "D6",
            "D5",
            "D4",
            "D3",
            "D2",
            "D1",
            "D0"
    };
    String sms_cmd13_txts_H[] = {
            "D15",
            "D14",
            "D13",
            "D13",
            "D11",
            "D10",
            "D9",
            "D8",
    };

    /*COMMAND 13 - FLOOR DN KEYS - 16 to 23 */
    TextView sms_cmd13_L_M_bit0_txt, sms_cmd13_L_M_bit1_txt, sms_cmd13_L_M_bit2_txt, sms_cmd13_L_M_bit3_txt, sms_cmd13_L_M_bit4_txt, sms_cmd13_L_M_bit5_txt, sms_cmd13_L_M_bit6_txt, sms_cmd13_L_M_bit7_txt;
    RadioButton sms_cmd13_L_M_bit0, sms_cmd13_L_M_bit1, sms_cmd13_L_M_bit2, sms_cmd13_L_M_bit3, sms_cmd13_L_M_bit4, sms_cmd13_L_M_bit5, sms_cmd13_L_M_bit6, sms_cmd13_L_M_bit7;
    /*COMMAND 13 - FLOOR DN KEYS - 24 to 31*/
    TextView sms_cmd13_H_M_bit0_txt, sms_cmd13_H_M_bit1_txt, sms_cmd13_H_M_bit2_txt, sms_cmd13_H_M_bit3_txt, sms_cmd13_H_M_bit4_txt, sms_cmd13_H_M_bit5_txt, sms_cmd13_H_M_bit6_txt, sms_cmd13_H_M_bit7_txt;
    RadioButton sms_cmd13_H_M_bit0, sms_cmd13_H_M_bit1, sms_cmd13_H_M_bit2, sms_cmd13_H_M_bit3, sms_cmd13_H_M_bit4, sms_cmd13_H_M_bit5, sms_cmd13_H_M_bit6, sms_cmd13_H_M_bit7;
    String sms_cmd13_txts_L_M[] = {
            "D23",
            "D22",
            "D21",
            "D20",
            "D19",
            "D18",
            "D17",
            "D16"
    };
    String sms_cmd13_txts_H_M[] = {
            "D31",
            "D30",
            "D29",
            "D28",
            "D27",
            "D26",
            "D25",
            "D24",
    };

    /*COMMAND 14 - CAR CALLS - 0 to 7 */
    TextView sms_cmd14_L_bit0_txt, sms_cmd14_L_bit1_txt, sms_cmd14_L_bit2_txt, sms_cmd14_L_bit3_txt, sms_cmd14_L_bit4_txt, sms_cmd14_L_bit5_txt, sms_cmd14_L_bit6_txt, sms_cmd14_L_bit7_txt;
    RadioButton sms_cmd14_L_bit0, sms_cmd14_L_bit1, sms_cmd14_L_bit2, sms_cmd14_L_bit3, sms_cmd14_L_bit4, sms_cmd14_L_bit5, sms_cmd14_L_bit6, sms_cmd14_L_bit7;
    /*COMMAND 14 - CAR CALLS - 8 to 15*/
    TextView sms_cmd14_H_bit0_txt, sms_cmd14_H_bit1_txt, sms_cmd14_H_bit2_txt, sms_cmd14_H_bit3_txt, sms_cmd14_H_bit4_txt, sms_cmd14_H_bit5_txt, sms_cmd14_H_bit6_txt, sms_cmd14_H_bit7_txt;
    RadioButton sms_cmd14_H_bit0, sms_cmd14_H_bit1, sms_cmd14_H_bit2, sms_cmd14_H_bit3, sms_cmd14_H_bit4, sms_cmd14_H_bit5, sms_cmd14_H_bit6, sms_cmd14_H_bit7;
    String sms_cmd14_txts_L[] = {
            "C7",
            "C6",
            "C5",
            "C4",
            "C3",
            "C2",
            "C1",
            "C0"
    };
    String sms_cmd14_txts_H[] = {
            "C15",
            "C14",
            "C14",
            "C14",
            "C11",
            "C10",
            "C9",
            "C8",
    };

    /*COMMAND 14 - CAR CALLS - 16 to 23 */
    TextView sms_cmd14_L_M_bit0_txt, sms_cmd14_L_M_bit1_txt, sms_cmd14_L_M_bit2_txt, sms_cmd14_L_M_bit3_txt, sms_cmd14_L_M_bit4_txt, sms_cmd14_L_M_bit5_txt, sms_cmd14_L_M_bit6_txt, sms_cmd14_L_M_bit7_txt;
    RadioButton sms_cmd14_L_M_bit0, sms_cmd14_L_M_bit1, sms_cmd14_L_M_bit2, sms_cmd14_L_M_bit3, sms_cmd14_L_M_bit4, sms_cmd14_L_M_bit5, sms_cmd14_L_M_bit6, sms_cmd14_L_M_bit7;
    /*COMMAND 14 - CAR CALLS - 24 to 31*/
    TextView sms_cmd14_H_M_bit0_txt, sms_cmd14_H_M_bit1_txt, sms_cmd14_H_M_bit2_txt, sms_cmd14_H_M_bit3_txt, sms_cmd14_H_M_bit4_txt, sms_cmd14_H_M_bit5_txt, sms_cmd14_H_M_bit6_txt, sms_cmd14_H_M_bit7_txt;
    RadioButton sms_cmd14_H_M_bit0, sms_cmd14_H_M_bit1, sms_cmd14_H_M_bit2, sms_cmd14_H_M_bit3, sms_cmd14_H_M_bit4, sms_cmd14_H_M_bit5, sms_cmd14_H_M_bit6, sms_cmd14_H_M_bit7;
    String sms_cmd14_txts_L_M[] = {
            "C23",
            "C22",
            "C21",
            "C20",
            "C19",
            "C18",
            "C17",
            "C16"
    };
    String sms_cmd14_txts_H_M[] = {
            "C31",
            "C30",
            "C29",
            "C28",
            "C27",
            "C26",
            "C25",
            "C24"
    };

    /*COMMAND 16 - CBC INPUTS - LOWER BYTE */
    TextView sms_cmd16_L_bit0_txt, sms_cmd16_L_bit1_txt, sms_cmd16_L_bit2_txt, sms_cmd16_L_bit3_txt, sms_cmd16_L_bit4_txt, sms_cmd16_L_bit5_txt, sms_cmd16_L_bit6_txt, sms_cmd16_L_bit7_txt;
    RadioButton sms_cmd16_L_bit0, sms_cmd16_L_bit1, sms_cmd16_L_bit2, sms_cmd16_L_bit3, sms_cmd16_L_bit4, sms_cmd16_L_bit5, sms_cmd16_L_bit6, sms_cmd16_L_bit7;
    /*COMMAND 16 - CBC INPUTS - HIGHER BYTE*/
    TextView sms_cmd16_H_bit0_txt, sms_cmd16_H_bit1_txt, sms_cmd16_H_bit2_txt, sms_cmd16_H_bit3_txt, sms_cmd16_H_bit4_txt, sms_cmd16_H_bit5_txt, sms_cmd16_H_bit6_txt, sms_cmd16_H_bit7_txt;
    RadioButton sms_cmd16_H_bit0, sms_cmd16_H_bit1, sms_cmd16_H_bit2, sms_cmd16_H_bit3, sms_cmd16_H_bit4, sms_cmd16_H_bit5, sms_cmd16_H_bit6, sms_cmd16_H_bit7;
    String sms_cmd16_txts_L[] = {
            "BCFB STOP",
            "OCFB STOP",
            "REED TIMEOUT",
            "RCAM TIMEOUT",
            "BTS",
            "BCFB",
            "OFCB",
            "INV ENABLE"
    };
    String sms_cmd16_txts_H[] = {
            "MO1OP OFF",
            "STPR STUCK",
            "SLWR STUCK",
            "DOLT SHORT",
            "DCLT SHORT",
            "DCLT",
            "DOLT",
            "SAFETY SHORT",
    };
    /*COMMAND 16*/
    TextView sms_cmd16_L_M_bit0_txt, sms_cmd16_L_M_bit1_txt, sms_cmd16_L_M_bit2_txt, sms_cmd16_L_M_bit3_txt, sms_cmd16_L_M_bit4_txt, sms_cmd16_L_M_bit5_txt, sms_cmd16_L_M_bit6_txt, sms_cmd16_L_M_bit7_txt;
    RadioButton sms_cmd16_L_M_bit0, sms_cmd16_L_M_bit1, sms_cmd16_L_M_bit2, sms_cmd16_L_M_bit3, sms_cmd16_L_M_bit4, sms_cmd16_L_M_bit5, sms_cmd16_L_M_bit6, sms_cmd16_L_M_bit7;
    String sms_cmd16_txts_L_M[] = {
            "---",
            "---",
            "---",
            "---",
            "SMS EMR STOP",
            "RELAY OP IN",
            "MO1OP",
            "RELAY OP OFF"
    };
    /*COMMAND 15 - LIFT STATUS*/
    TextView sms_cmd15_txt;
    RadioButton sms_cmd15;
    String sms_cmd15_string="LIFT STATUS";

    /*COMMAND 19 - LIFT STATUS*/
    TextView sms_cmd19_txt;
    RadioButton sms_cmd19;
    String sms_cmd19_string="START MONITORING";

    /*COMMAND 20 - LIFT EMERGENCY STOP STATUS READ*/
    TextView sms_cmd20_bit0_txt, sms_cmd20_bit1_txt;
    RadioButton sms_cmd20_bit0, sms_cmd20_bit1;
    String sms_cmd20_txts[] =   {
            "LIFT IS STOPPED",
            "LIFT STOP REQUEST"
    };

    HorizontalScrollView horiScroll;
    AdView adView;
    AdRequest adRequest;
    TableLayout mciView, mctiView, mcoView, cbciView, cbcoView,
            flrupView, flrdnView, carkeysView, lifterrView,threeView;//threeView for CMD15, 19, 20
    View mciTargetView, mctiTargetView, mcoTargetView, cbciTargetView, cbcoTargetView,
            flrupTargetView, flrdnTargetView, carkeysTargetView, lifterrTargetView, threeTargetView;
    /*******************************CONTROLLER**********************************/
    CountDownTimer cTimer = null;
    Boolean flag=false;
    /*SMS API*/
    SmsManager sms;
    /******************************************************************************/
    CustomerControllerDB customerControllerDB;
    CustomerSMSReceiverControllerDB customerSMSReceiverControllerDB;
    SQLiteDatabase sqLiteDatabase, theSqLiteDatabase;
    String query = null, contactNumberQuery = null, theQuery;
    Cursor clientCursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);
        /*MAPPING UI ELEMENTS*/
        adminText               =   (TextView)        findViewById(R.id.adminText);
        //numberText              =   (TextView)        findViewById(R.id.numberText);

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

        mciTargetView       =findViewById(R.id.mciView);//7
        mctiTargetView      =findViewById(R.id.mctiView);//8
        mcoTargetView       =findViewById(R.id.mcoView);//9
        cbciTargetView      =findViewById(R.id.cbciView);//10
        cbcoTargetView      =findViewById(R.id.cbcoView);//11
        flrupTargetView     =findViewById(R.id.flrupView);//12
        flrdnTargetView     =findViewById(R.id.flrdnView);//13
        carkeysTargetView   =findViewById(R.id.carkeysView);//14
        lifterrTargetView   =findViewById(R.id.lifterrView);//16
        threeTargetView     =findViewById(R.id.threeView);//15,19,20

        CMD7                    =   (ImageView)        findViewById(R.id.CMD7);
        CMD8                    =   (ImageView)        findViewById(R.id.CMD8);
        CMD9                    =   (ImageView)        findViewById(R.id.CMD9);
        CMD10                   =   (ImageView)        findViewById(R.id.CMD10);
        CMD11                   =   (ImageView)        findViewById(R.id.CMD11);
        CMD12                   =   (ImageView)        findViewById(R.id.CMD12);
        CMD13                   =   (ImageView)        findViewById(R.id.CMD13);
        CMD14                   =   (ImageView)        findViewById(R.id.CMD14);
        CMD15                   =   (ImageView)        findViewById(R.id.CMD15);
        CMD16                   =   (ImageView)        findViewById(R.id.CMD16);
        CMD17                   =   (ImageView)        findViewById(R.id.CMD17);
        CMD18                   =   (ImageView)        findViewById(R.id.CMD18);
        CMD19                   =   (ImageView)        findViewById(R.id.CMD19);
        CMD20                   =   (ImageView)        findViewById(R.id.CMD20);
        today                   =   (TextView)           findViewById(R.id.today);

        CMD7_text 				=   (TextView)        findViewById(R.id.CMD7_text);
        CMD8_text               =   (TextView)        findViewById(R.id.CMD8_text);
        CMD9_text               =   (TextView)        findViewById(R.id.CMD9_text);
        CMD10_text              =   (TextView)        findViewById(R.id.CMD10_text);
        CMD11_text              =   (TextView)        findViewById(R.id.CMD11_text);
        CMD12_text              =   (TextView)        findViewById(R.id.CMD12_text);
        CMD13_text              =   (TextView)        findViewById(R.id.CMD13_text);
        CMD14_text              =   (TextView)        findViewById(R.id.CMD14_text);
        CMD15_text              =   (TextView)        findViewById(R.id.CMD15_text);
        CMD16_text              =   (TextView)        findViewById(R.id.CMD16_text);
        CMD17_text              =   (TextView)        findViewById(R.id.CMD17_text);
        CMD18_text              =   (TextView)        findViewById(R.id.CMD18_text);
        CMD19_text              =   (TextView)        findViewById(R.id.CMD19_text);
        CMD20_text              =   (TextView)        findViewById(R.id.CMD20_text);

        sms_cmd15_txt           =   (TextView)           findViewById(R.id.sms_cmd15_txt);
        sms_cmd15_txt.setText(sms_cmd15_string);

        sms_cmd19_txt           =   (TextView)           findViewById(R.id.sms_cmd19_txt);
        sms_cmd19_txt.setText(sms_cmd19_string);

        sms_cmd20_bit0_txt      =   (TextView)          findViewById(R.id.sms_cmd20_bit0_txt);
        sms_cmd20_bit1_txt      =   (TextView)          findViewById(R.id.sms_cmd20_bit1_txt);

        sms_cmd20_bit0_txt.setText(sms_cmd20_txts[0]);
        sms_cmd20_bit1_txt.setText(sms_cmd20_txts[1]);

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

        sms_cmd9_H_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd9_H_bit0_txt);
        sms_cmd9_H_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd9_H_bit1_txt);
        sms_cmd9_H_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd9_H_bit2_txt);
        sms_cmd9_H_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd9_H_bit3_txt);
        sms_cmd9_H_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd9_H_bit4_txt);
        sms_cmd9_H_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd9_H_bit5_txt);
        sms_cmd9_H_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd9_H_bit6_txt);
        sms_cmd9_H_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd9_H_bit7_txt);

        sms_cmd9_H_bit0_txt.setText(sms_cmd9_txts_H[7]);
        sms_cmd9_H_bit1_txt.setText(sms_cmd9_txts_H[6]);
        sms_cmd9_H_bit2_txt.setText(sms_cmd9_txts_H[5]);
        sms_cmd9_H_bit3_txt.setText(sms_cmd9_txts_H[4]);
        sms_cmd9_H_bit4_txt.setText(sms_cmd9_txts_H[3]);
        sms_cmd9_H_bit5_txt.setText(sms_cmd9_txts_H[2]);
        sms_cmd9_H_bit6_txt.setText(sms_cmd9_txts_H[1]);
        sms_cmd9_H_bit7_txt.setText(sms_cmd9_txts_H[0]);

        sms_cmd9_L_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd9_L_bit0_txt);
        sms_cmd9_L_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd9_L_bit1_txt);
        sms_cmd9_L_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd9_L_bit2_txt);
        sms_cmd9_L_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd9_L_bit3_txt);
        sms_cmd9_L_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd9_L_bit4_txt);
        sms_cmd9_L_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd9_L_bit5_txt);
        sms_cmd9_L_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd9_L_bit6_txt);
        sms_cmd9_L_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd9_L_bit7_txt);

        sms_cmd9_L_bit0_txt.setText(sms_cmd9_txts_L[7]);
        sms_cmd9_L_bit1_txt.setText(sms_cmd9_txts_L[6]);
        sms_cmd9_L_bit2_txt.setText(sms_cmd9_txts_L[5]);
        sms_cmd9_L_bit3_txt.setText(sms_cmd9_txts_L[4]);
        sms_cmd9_L_bit4_txt.setText(sms_cmd9_txts_L[3]);
        sms_cmd9_L_bit5_txt.setText(sms_cmd9_txts_L[2]);
        sms_cmd9_L_bit6_txt.setText(sms_cmd9_txts_L[1]);
        sms_cmd9_L_bit7_txt.setText(sms_cmd9_txts_L[0]);

        sms_cmd10_H_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd10_H_bit0_txt);
        sms_cmd10_H_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd10_H_bit1_txt);
        sms_cmd10_H_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd10_H_bit2_txt);
        sms_cmd10_H_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd10_H_bit3_txt);
        sms_cmd10_H_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd10_H_bit4_txt);
        sms_cmd10_H_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd10_H_bit5_txt);
        sms_cmd10_H_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd10_H_bit6_txt);
        sms_cmd10_H_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd10_H_bit7_txt);

        sms_cmd10_H_bit0_txt.setText(sms_cmd10_txts_H[7]);
        sms_cmd10_H_bit1_txt.setText(sms_cmd10_txts_H[6]);
        sms_cmd10_H_bit2_txt.setText(sms_cmd10_txts_H[5]);
        sms_cmd10_H_bit3_txt.setText(sms_cmd10_txts_H[4]);
        sms_cmd10_H_bit4_txt.setText(sms_cmd10_txts_H[3]);
        sms_cmd10_H_bit5_txt.setText(sms_cmd10_txts_H[2]);
        sms_cmd10_H_bit6_txt.setText(sms_cmd10_txts_H[1]);
        sms_cmd10_H_bit7_txt.setText(sms_cmd10_txts_H[0]);

        sms_cmd10_L_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd10_L_bit0_txt);
        sms_cmd10_L_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd10_L_bit1_txt);
        sms_cmd10_L_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd10_L_bit2_txt);
        sms_cmd10_L_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd10_L_bit3_txt);
        sms_cmd10_L_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd10_L_bit4_txt);
        sms_cmd10_L_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd10_L_bit5_txt);
        sms_cmd10_L_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd10_L_bit6_txt);
        sms_cmd10_L_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd10_L_bit7_txt);

        sms_cmd10_L_bit0_txt.setText(sms_cmd10_txts_L[7]);
        sms_cmd10_L_bit1_txt.setText(sms_cmd10_txts_L[6]);
        sms_cmd10_L_bit2_txt.setText(sms_cmd10_txts_L[5]);
        sms_cmd10_L_bit3_txt.setText(sms_cmd10_txts_L[4]);
        sms_cmd10_L_bit4_txt.setText(sms_cmd10_txts_L[3]);
        sms_cmd10_L_bit5_txt.setText(sms_cmd10_txts_L[2]);
        sms_cmd10_L_bit6_txt.setText(sms_cmd10_txts_L[1]);
        sms_cmd10_L_bit7_txt.setText(sms_cmd10_txts_L[0]);

        sms_cmd11_H_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd11_H_bit0_txt);
        sms_cmd11_H_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd11_H_bit1_txt);
        sms_cmd11_H_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd11_H_bit2_txt);
        sms_cmd11_H_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd11_H_bit3_txt);
        sms_cmd11_H_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd11_H_bit4_txt);
        sms_cmd11_H_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd11_H_bit5_txt);
        sms_cmd11_H_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd11_H_bit6_txt);
        sms_cmd11_H_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd11_H_bit7_txt);

        sms_cmd11_H_bit0_txt.setText(sms_cmd11_txts_H[7]);
        sms_cmd11_H_bit1_txt.setText(sms_cmd11_txts_H[6]);
        sms_cmd11_H_bit2_txt.setText(sms_cmd11_txts_H[5]);
        sms_cmd11_H_bit3_txt.setText(sms_cmd11_txts_H[4]);
        sms_cmd11_H_bit4_txt.setText(sms_cmd11_txts_H[3]);
        sms_cmd11_H_bit5_txt.setText(sms_cmd11_txts_H[2]);
        sms_cmd11_H_bit6_txt.setText(sms_cmd11_txts_H[1]);
        sms_cmd11_H_bit7_txt.setText(sms_cmd11_txts_H[0]);

        sms_cmd11_L_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd11_L_bit0_txt);
        sms_cmd11_L_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd11_L_bit1_txt);
        sms_cmd11_L_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd11_L_bit2_txt);
        sms_cmd11_L_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd11_L_bit3_txt);
        sms_cmd11_L_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd11_L_bit4_txt);
        sms_cmd11_L_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd11_L_bit5_txt);
        sms_cmd11_L_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd11_L_bit6_txt);
        sms_cmd11_L_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd11_L_bit7_txt);

        sms_cmd11_L_bit0_txt.setText(sms_cmd11_txts_L[7]);
        sms_cmd11_L_bit1_txt.setText(sms_cmd11_txts_L[6]);
        sms_cmd11_L_bit2_txt.setText(sms_cmd11_txts_L[5]);
        sms_cmd11_L_bit3_txt.setText(sms_cmd11_txts_L[4]);
        sms_cmd11_L_bit4_txt.setText(sms_cmd11_txts_L[3]);
        sms_cmd11_L_bit5_txt.setText(sms_cmd11_txts_L[2]);
        sms_cmd11_L_bit6_txt.setText(sms_cmd11_txts_L[1]);
        sms_cmd11_L_bit7_txt.setText(sms_cmd11_txts_L[0]);

        sms_cmd12_H_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_bit0_txt);
        sms_cmd12_H_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_bit1_txt);
        sms_cmd12_H_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_bit2_txt);
        sms_cmd12_H_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_bit3_txt);
        sms_cmd12_H_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_bit4_txt);
        sms_cmd12_H_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_bit5_txt);
        sms_cmd12_H_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_bit6_txt);
        sms_cmd12_H_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_bit7_txt);

        sms_cmd12_H_bit0_txt.setText(sms_cmd12_txts_H[7]);
        sms_cmd12_H_bit1_txt.setText(sms_cmd12_txts_H[6]);
        sms_cmd12_H_bit2_txt.setText(sms_cmd12_txts_H[5]);
        sms_cmd12_H_bit3_txt.setText(sms_cmd12_txts_H[4]);
        sms_cmd12_H_bit4_txt.setText(sms_cmd12_txts_H[3]);
        sms_cmd12_H_bit5_txt.setText(sms_cmd12_txts_H[2]);
        sms_cmd12_H_bit6_txt.setText(sms_cmd12_txts_H[1]);
        sms_cmd12_H_bit7_txt.setText(sms_cmd12_txts_H[0]);

        sms_cmd12_L_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_bit0_txt);
        sms_cmd12_L_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_bit1_txt);
        sms_cmd12_L_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_bit2_txt);
        sms_cmd12_L_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_bit3_txt);
        sms_cmd12_L_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_bit4_txt);
        sms_cmd12_L_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_bit5_txt);
        sms_cmd12_L_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_bit6_txt);
        sms_cmd12_L_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_bit7_txt);

        sms_cmd12_L_bit0_txt.setText(sms_cmd12_txts_L[7]);
        sms_cmd12_L_bit1_txt.setText(sms_cmd12_txts_L[6]);
        sms_cmd12_L_bit2_txt.setText(sms_cmd12_txts_L[5]);
        sms_cmd12_L_bit3_txt.setText(sms_cmd12_txts_L[4]);
        sms_cmd12_L_bit4_txt.setText(sms_cmd12_txts_L[3]);
        sms_cmd12_L_bit5_txt.setText(sms_cmd12_txts_L[2]);
        sms_cmd12_L_bit6_txt.setText(sms_cmd12_txts_L[1]);
        sms_cmd12_L_bit7_txt.setText(sms_cmd12_txts_L[0]);

        sms_cmd12_H_M_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_M_bit0_txt);
        sms_cmd12_H_M_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_M_bit1_txt);
        sms_cmd12_H_M_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_M_bit2_txt);
        sms_cmd12_H_M_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_M_bit3_txt);
        sms_cmd12_H_M_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_M_bit4_txt);
        sms_cmd12_H_M_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_M_bit5_txt);
        sms_cmd12_H_M_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_M_bit6_txt);
        sms_cmd12_H_M_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd12_H_M_bit7_txt);

        sms_cmd12_H_M_bit0_txt.setText(sms_cmd12_txts_H_M[7]);
        sms_cmd12_H_M_bit1_txt.setText(sms_cmd12_txts_H_M[6]);
        sms_cmd12_H_M_bit2_txt.setText(sms_cmd12_txts_H_M[5]);
        sms_cmd12_H_M_bit3_txt.setText(sms_cmd12_txts_H_M[4]);
        sms_cmd12_H_M_bit4_txt.setText(sms_cmd12_txts_H_M[3]);
        sms_cmd12_H_M_bit5_txt.setText(sms_cmd12_txts_H_M[2]);
        sms_cmd12_H_M_bit6_txt.setText(sms_cmd12_txts_H_M[1]);
        sms_cmd12_H_M_bit7_txt.setText(sms_cmd12_txts_H_M[0]);

        sms_cmd12_L_M_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_M_bit0_txt);
        sms_cmd12_L_M_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_M_bit1_txt);
        sms_cmd12_L_M_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_M_bit2_txt);
        sms_cmd12_L_M_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_M_bit3_txt);
        sms_cmd12_L_M_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_M_bit4_txt);
        sms_cmd12_L_M_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_M_bit5_txt);
        sms_cmd12_L_M_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_M_bit6_txt);
        sms_cmd12_L_M_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd12_L_M_bit7_txt);

        sms_cmd12_L_M_bit0_txt.setText(sms_cmd12_txts_L_M[7]);
        sms_cmd12_L_M_bit1_txt.setText(sms_cmd12_txts_L_M[6]);
        sms_cmd12_L_M_bit2_txt.setText(sms_cmd12_txts_L_M[5]);
        sms_cmd12_L_M_bit3_txt.setText(sms_cmd12_txts_L_M[4]);
        sms_cmd12_L_M_bit4_txt.setText(sms_cmd12_txts_L_M[3]);
        sms_cmd12_L_M_bit5_txt.setText(sms_cmd12_txts_L_M[2]);
        sms_cmd12_L_M_bit6_txt.setText(sms_cmd12_txts_L_M[1]);
        sms_cmd12_L_M_bit7_txt.setText(sms_cmd12_txts_L_M[0]);

        sms_cmd13_H_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_bit0_txt);
        sms_cmd13_H_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_bit1_txt);
        sms_cmd13_H_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_bit2_txt);
        sms_cmd13_H_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_bit3_txt);
        sms_cmd13_H_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_bit4_txt);
        sms_cmd13_H_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_bit5_txt);
        sms_cmd13_H_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_bit6_txt);
        sms_cmd13_H_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_bit7_txt);

        sms_cmd13_H_bit0_txt.setText(sms_cmd13_txts_H[7]);
        sms_cmd13_H_bit1_txt.setText(sms_cmd13_txts_H[6]);
        sms_cmd13_H_bit2_txt.setText(sms_cmd13_txts_H[5]);
        sms_cmd13_H_bit3_txt.setText(sms_cmd13_txts_H[4]);
        sms_cmd13_H_bit4_txt.setText(sms_cmd13_txts_H[3]);
        sms_cmd13_H_bit5_txt.setText(sms_cmd13_txts_H[2]);
        sms_cmd13_H_bit6_txt.setText(sms_cmd13_txts_H[1]);
        sms_cmd13_H_bit7_txt.setText(sms_cmd13_txts_H[0]);

        sms_cmd13_L_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_bit0_txt);
        sms_cmd13_L_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_bit1_txt);
        sms_cmd13_L_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_bit2_txt);
        sms_cmd13_L_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_bit3_txt);
        sms_cmd13_L_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_bit4_txt);
        sms_cmd13_L_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_bit5_txt);
        sms_cmd13_L_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_bit6_txt);
        sms_cmd13_L_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_bit7_txt);

        sms_cmd13_L_bit0_txt.setText(sms_cmd13_txts_L[7]);
        sms_cmd13_L_bit1_txt.setText(sms_cmd13_txts_L[6]);
        sms_cmd13_L_bit2_txt.setText(sms_cmd13_txts_L[5]);
        sms_cmd13_L_bit3_txt.setText(sms_cmd13_txts_L[4]);
        sms_cmd13_L_bit4_txt.setText(sms_cmd13_txts_L[3]);
        sms_cmd13_L_bit5_txt.setText(sms_cmd13_txts_L[2]);
        sms_cmd13_L_bit6_txt.setText(sms_cmd13_txts_L[1]);
        sms_cmd13_L_bit7_txt.setText(sms_cmd13_txts_L[0]);

        sms_cmd13_H_M_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_M_bit0_txt);
        sms_cmd13_H_M_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_M_bit1_txt);
        sms_cmd13_H_M_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_M_bit2_txt);
        sms_cmd13_H_M_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_M_bit3_txt);
        sms_cmd13_H_M_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_M_bit4_txt);
        sms_cmd13_H_M_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_M_bit5_txt);
        sms_cmd13_H_M_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_M_bit6_txt);
        sms_cmd13_H_M_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd13_H_M_bit7_txt);

        sms_cmd13_H_M_bit0_txt.setText(sms_cmd13_txts_H_M[7]);
        sms_cmd13_H_M_bit1_txt.setText(sms_cmd13_txts_H_M[6]);
        sms_cmd13_H_M_bit2_txt.setText(sms_cmd13_txts_H_M[5]);
        sms_cmd13_H_M_bit3_txt.setText(sms_cmd13_txts_H_M[4]);
        sms_cmd13_H_M_bit4_txt.setText(sms_cmd13_txts_H_M[3]);
        sms_cmd13_H_M_bit5_txt.setText(sms_cmd13_txts_H_M[2]);
        sms_cmd13_H_M_bit6_txt.setText(sms_cmd13_txts_H_M[1]);
        sms_cmd13_H_M_bit7_txt.setText(sms_cmd13_txts_H_M[0]);

        sms_cmd13_L_M_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_M_bit0_txt);
        sms_cmd13_L_M_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_M_bit1_txt);
        sms_cmd13_L_M_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_M_bit2_txt);
        sms_cmd13_L_M_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_M_bit3_txt);
        sms_cmd13_L_M_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_M_bit4_txt);
        sms_cmd13_L_M_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_M_bit5_txt);
        sms_cmd13_L_M_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_M_bit6_txt);
        sms_cmd13_L_M_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd13_L_M_bit7_txt);

        sms_cmd13_L_M_bit0_txt.setText(sms_cmd13_txts_L_M[7]);
        sms_cmd13_L_M_bit1_txt.setText(sms_cmd13_txts_L_M[6]);
        sms_cmd13_L_M_bit2_txt.setText(sms_cmd13_txts_L_M[5]);
        sms_cmd13_L_M_bit3_txt.setText(sms_cmd13_txts_L_M[4]);
        sms_cmd13_L_M_bit4_txt.setText(sms_cmd13_txts_L_M[3]);
        sms_cmd13_L_M_bit5_txt.setText(sms_cmd13_txts_L_M[2]);
        sms_cmd13_L_M_bit6_txt.setText(sms_cmd13_txts_L_M[1]);
        sms_cmd13_L_M_bit7_txt.setText(sms_cmd13_txts_L_M[0]);

        sms_cmd14_H_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_bit0_txt);
        sms_cmd14_H_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_bit1_txt);
        sms_cmd14_H_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_bit2_txt);
        sms_cmd14_H_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_bit3_txt);
        sms_cmd14_H_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_bit4_txt);
        sms_cmd14_H_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_bit5_txt);
        sms_cmd14_H_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_bit6_txt);
        sms_cmd14_H_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_bit7_txt);

        sms_cmd14_H_bit0_txt.setText(sms_cmd14_txts_H[7]);
        sms_cmd14_H_bit1_txt.setText(sms_cmd14_txts_H[6]);
        sms_cmd14_H_bit2_txt.setText(sms_cmd14_txts_H[5]);
        sms_cmd14_H_bit3_txt.setText(sms_cmd14_txts_H[4]);
        sms_cmd14_H_bit4_txt.setText(sms_cmd14_txts_H[3]);
        sms_cmd14_H_bit5_txt.setText(sms_cmd14_txts_H[2]);
        sms_cmd14_H_bit6_txt.setText(sms_cmd14_txts_H[1]);
        sms_cmd14_H_bit7_txt.setText(sms_cmd14_txts_H[0]);

        sms_cmd14_L_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_bit0_txt);
        sms_cmd14_L_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_bit1_txt);
        sms_cmd14_L_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_bit2_txt);
        sms_cmd14_L_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_bit3_txt);
        sms_cmd14_L_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_bit4_txt);
        sms_cmd14_L_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_bit5_txt);
        sms_cmd14_L_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_bit6_txt);
        sms_cmd14_L_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_bit7_txt);

        sms_cmd14_L_bit0_txt.setText(sms_cmd14_txts_L[7]);
        sms_cmd14_L_bit1_txt.setText(sms_cmd14_txts_L[6]);
        sms_cmd14_L_bit2_txt.setText(sms_cmd14_txts_L[5]);
        sms_cmd14_L_bit3_txt.setText(sms_cmd14_txts_L[4]);
        sms_cmd14_L_bit4_txt.setText(sms_cmd14_txts_L[3]);
        sms_cmd14_L_bit5_txt.setText(sms_cmd14_txts_L[2]);
        sms_cmd14_L_bit6_txt.setText(sms_cmd14_txts_L[1]);
        sms_cmd14_L_bit7_txt.setText(sms_cmd14_txts_L[0]);

        sms_cmd14_H_M_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_M_bit0_txt);
        sms_cmd14_H_M_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_M_bit1_txt);
        sms_cmd14_H_M_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_M_bit2_txt);
        sms_cmd14_H_M_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_M_bit3_txt);
        sms_cmd14_H_M_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_M_bit4_txt);
        sms_cmd14_H_M_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_M_bit5_txt);
        sms_cmd14_H_M_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_M_bit6_txt);
        sms_cmd14_H_M_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd14_H_M_bit7_txt);

        sms_cmd14_H_M_bit0_txt.setText(sms_cmd14_txts_H_M[7]);
        sms_cmd14_H_M_bit1_txt.setText(sms_cmd14_txts_H_M[6]);
        sms_cmd14_H_M_bit2_txt.setText(sms_cmd14_txts_H_M[5]);
        sms_cmd14_H_M_bit3_txt.setText(sms_cmd14_txts_H_M[4]);
        sms_cmd14_H_M_bit4_txt.setText(sms_cmd14_txts_H_M[3]);
        sms_cmd14_H_M_bit5_txt.setText(sms_cmd14_txts_H_M[2]);
        sms_cmd14_H_M_bit6_txt.setText(sms_cmd14_txts_H_M[1]);
        sms_cmd14_H_M_bit7_txt.setText(sms_cmd14_txts_H_M[0]);

        sms_cmd14_L_M_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_M_bit0_txt);
        sms_cmd14_L_M_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_M_bit1_txt);
        sms_cmd14_L_M_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_M_bit2_txt);
        sms_cmd14_L_M_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_M_bit3_txt);
        sms_cmd14_L_M_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_M_bit4_txt);
        sms_cmd14_L_M_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_M_bit5_txt);
        sms_cmd14_L_M_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_M_bit6_txt);
        sms_cmd14_L_M_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd14_L_M_bit7_txt);

        sms_cmd14_L_M_bit0_txt.setText(sms_cmd14_txts_L_M[7]);
        sms_cmd14_L_M_bit1_txt.setText(sms_cmd14_txts_L_M[6]);
        sms_cmd14_L_M_bit2_txt.setText(sms_cmd14_txts_L_M[5]);
        sms_cmd14_L_M_bit3_txt.setText(sms_cmd14_txts_L_M[4]);
        sms_cmd14_L_M_bit4_txt.setText(sms_cmd14_txts_L_M[3]);
        sms_cmd14_L_M_bit5_txt.setText(sms_cmd14_txts_L_M[2]);
        sms_cmd14_L_M_bit6_txt.setText(sms_cmd14_txts_L_M[1]);
        sms_cmd14_L_M_bit7_txt.setText(sms_cmd14_txts_L_M[0]);

        sms_cmd16_H_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd16_H_bit0_txt);
        sms_cmd16_H_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd16_H_bit1_txt);
        sms_cmd16_H_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd16_H_bit2_txt);
        sms_cmd16_H_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd16_H_bit3_txt);
        sms_cmd16_H_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd16_H_bit4_txt);
        sms_cmd16_H_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd16_H_bit5_txt);
        sms_cmd16_H_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd16_H_bit6_txt);
        sms_cmd16_H_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd16_H_bit7_txt);

        sms_cmd16_H_bit0_txt.setText(sms_cmd16_txts_H[7]);
        sms_cmd16_H_bit1_txt.setText(sms_cmd16_txts_H[6]);
        sms_cmd16_H_bit2_txt.setText(sms_cmd16_txts_H[5]);
        sms_cmd16_H_bit3_txt.setText(sms_cmd16_txts_H[4]);
        sms_cmd16_H_bit4_txt.setText(sms_cmd16_txts_H[3]);
        sms_cmd16_H_bit5_txt.setText(sms_cmd16_txts_H[2]);
        sms_cmd16_H_bit6_txt.setText(sms_cmd16_txts_H[1]);
        sms_cmd16_H_bit7_txt.setText(sms_cmd16_txts_H[0]);

        sms_cmd16_L_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_bit0_txt);
        sms_cmd16_L_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_bit1_txt);
        sms_cmd16_L_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_bit2_txt);
        sms_cmd16_L_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_bit3_txt);
        sms_cmd16_L_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_bit4_txt);
        sms_cmd16_L_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_bit5_txt);
        sms_cmd16_L_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_bit6_txt);
        sms_cmd16_L_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_bit7_txt);

        sms_cmd16_L_bit0_txt.setText(sms_cmd16_txts_L[7]);
        sms_cmd16_L_bit1_txt.setText(sms_cmd16_txts_L[6]);
        sms_cmd16_L_bit2_txt.setText(sms_cmd16_txts_L[5]);
        sms_cmd16_L_bit3_txt.setText(sms_cmd16_txts_L[4]);
        sms_cmd16_L_bit4_txt.setText(sms_cmd16_txts_L[3]);
        sms_cmd16_L_bit5_txt.setText(sms_cmd16_txts_L[2]);
        sms_cmd16_L_bit6_txt.setText(sms_cmd16_txts_L[1]);
        sms_cmd16_L_bit7_txt.setText(sms_cmd16_txts_L[0]);

        sms_cmd16_L_M_bit0_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_M_bit0_txt);
        sms_cmd16_L_M_bit1_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_M_bit1_txt);
        sms_cmd16_L_M_bit2_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_M_bit2_txt);
        sms_cmd16_L_M_bit3_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_M_bit3_txt);
        sms_cmd16_L_M_bit4_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_M_bit4_txt);
        sms_cmd16_L_M_bit5_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_M_bit5_txt);
        sms_cmd16_L_M_bit6_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_M_bit6_txt);
        sms_cmd16_L_M_bit7_txt       =   (TextView)          findViewById(R.id.sms_cmd16_L_M_bit7_txt);

        sms_cmd16_L_M_bit0_txt.setText(sms_cmd16_txts_L_M[7]);
        sms_cmd16_L_M_bit1_txt.setText(sms_cmd16_txts_L_M[6]);
        sms_cmd16_L_M_bit2_txt.setText(sms_cmd16_txts_L_M[5]);
        sms_cmd16_L_M_bit3_txt.setText(sms_cmd16_txts_L_M[4]);
        sms_cmd16_L_M_bit4_txt.setText(sms_cmd16_txts_L_M[3]);
        sms_cmd16_L_M_bit5_txt.setText(sms_cmd16_txts_L_M[2]);
        sms_cmd16_L_M_bit6_txt.setText(sms_cmd16_txts_L_M[1]);
        sms_cmd16_L_M_bit7_txt.setText(sms_cmd16_txts_L_M[0]);

        /*MAPPING RADIO BUTTONS*/

        sms_cmd15              =   (RadioButton)       findViewById(R.id.sms_cmd15);
        sms_cmd19              =   (RadioButton)       findViewById(R.id.sms_cmd19);
        sms_cmd20_bit0         =    (RadioButton)       findViewById(R.id.sms_cmd20_bit0);
        sms_cmd20_bit1         =    (RadioButton)       findViewById(R.id.sms_cmd20_bit1);

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

        sms_cmd8_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit0);
        sms_cmd8_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit1);
        sms_cmd8_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit2);
        sms_cmd8_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit3);
        sms_cmd8_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit4);
        sms_cmd8_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit5);
        sms_cmd8_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit6);
        sms_cmd8_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd8_H_bit7);

        sms_cmd9_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd9_H_bit0);
        sms_cmd9_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd9_H_bit1);
        sms_cmd9_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd9_H_bit2);
        sms_cmd9_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd9_H_bit3);
        sms_cmd9_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd9_H_bit4);
        sms_cmd9_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd9_H_bit5);
        sms_cmd9_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd9_H_bit6);
        sms_cmd9_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd9_H_bit7);

        sms_cmd9_L_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd9_L_bit0);
        sms_cmd9_L_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd9_L_bit1);
        sms_cmd9_L_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd9_L_bit2);
        sms_cmd9_L_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd9_L_bit3);
        sms_cmd9_L_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd9_L_bit4);
        sms_cmd9_L_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd9_L_bit5);
        sms_cmd9_L_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd9_L_bit6);
        sms_cmd9_L_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd9_L_bit7);

        sms_cmd10_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd10_H_bit0);
        sms_cmd10_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd10_H_bit1);
        sms_cmd10_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd10_H_bit2);
        sms_cmd10_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd10_H_bit3);
        sms_cmd10_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd10_H_bit4);
        sms_cmd10_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd10_H_bit5);
        sms_cmd10_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd10_H_bit6);
        sms_cmd10_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd10_H_bit7);

        sms_cmd10_L_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd10_L_bit0);
        sms_cmd10_L_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd10_L_bit1);
        sms_cmd10_L_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd10_L_bit2);
        sms_cmd10_L_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd10_L_bit3);
        sms_cmd10_L_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd10_L_bit4);
        sms_cmd10_L_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd10_L_bit5);
        sms_cmd10_L_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd10_L_bit6);
        sms_cmd10_L_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd10_L_bit7);

        sms_cmd11_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd11_H_bit0);
        sms_cmd11_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd11_H_bit1);
        sms_cmd11_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd11_H_bit2);
        sms_cmd11_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd11_H_bit3);
        sms_cmd11_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd11_H_bit4);
        sms_cmd11_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd11_H_bit5);
        sms_cmd11_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd11_H_bit6);
        sms_cmd11_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd11_H_bit7);

        sms_cmd11_L_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd11_L_bit0);
        sms_cmd11_L_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd11_L_bit1);
        sms_cmd11_L_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd11_L_bit2);
        sms_cmd11_L_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd11_L_bit3);
        sms_cmd11_L_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd11_L_bit4);
        sms_cmd11_L_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd11_L_bit5);
        sms_cmd11_L_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd11_L_bit6);
        sms_cmd11_L_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd11_L_bit7);

        sms_cmd12_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_bit0);
        sms_cmd12_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_bit1);
        sms_cmd12_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_bit2);
        sms_cmd12_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_bit3);
        sms_cmd12_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_bit4);
        sms_cmd12_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_bit5);
        sms_cmd12_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_bit6);
        sms_cmd12_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_bit7);

        sms_cmd12_L_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_bit0);
        sms_cmd12_L_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_bit1);
        sms_cmd12_L_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_bit2);
        sms_cmd12_L_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_bit3);
        sms_cmd12_L_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_bit4);
        sms_cmd12_L_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_bit5);
        sms_cmd12_L_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_bit6);
        sms_cmd12_L_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_bit7);

        sms_cmd12_H_M_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_M_bit0);
        sms_cmd12_H_M_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_M_bit1);
        sms_cmd12_H_M_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_M_bit2);
        sms_cmd12_H_M_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_M_bit3);
        sms_cmd12_H_M_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_M_bit4);
        sms_cmd12_H_M_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_M_bit5);
        sms_cmd12_H_M_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_M_bit6);
        sms_cmd12_H_M_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd12_H_M_bit7);

        sms_cmd12_L_M_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_M_bit0);
        sms_cmd12_L_M_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_M_bit1);
        sms_cmd12_L_M_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_M_bit2);
        sms_cmd12_L_M_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_M_bit3);
        sms_cmd12_L_M_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_M_bit4);
        sms_cmd12_L_M_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_M_bit5);
        sms_cmd12_L_M_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_M_bit6);
        sms_cmd12_L_M_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd12_L_M_bit7);

        sms_cmd13_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_bit0);
        sms_cmd13_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_bit1);
        sms_cmd13_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_bit2);
        sms_cmd13_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_bit3);
        sms_cmd13_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_bit4);
        sms_cmd13_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_bit5);
        sms_cmd13_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_bit6);
        sms_cmd13_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_bit7);

        sms_cmd13_L_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_bit0);
        sms_cmd13_L_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_bit1);
        sms_cmd13_L_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_bit2);
        sms_cmd13_L_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_bit3);
        sms_cmd13_L_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_bit4);
        sms_cmd13_L_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_bit5);
        sms_cmd13_L_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_bit6);
        sms_cmd13_L_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_bit7);

        sms_cmd13_H_M_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_M_bit0);
        sms_cmd13_H_M_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_M_bit1);
        sms_cmd13_H_M_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_M_bit2);
        sms_cmd13_H_M_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_M_bit3);
        sms_cmd13_H_M_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_M_bit4);
        sms_cmd13_H_M_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_M_bit5);
        sms_cmd13_H_M_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_M_bit6);
        sms_cmd13_H_M_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd13_H_M_bit7);

        sms_cmd13_L_M_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_M_bit0);
        sms_cmd13_L_M_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_M_bit1);
        sms_cmd13_L_M_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_M_bit2);
        sms_cmd13_L_M_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_M_bit3);
        sms_cmd13_L_M_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_M_bit4);
        sms_cmd13_L_M_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_M_bit5);
        sms_cmd13_L_M_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_M_bit6);
        sms_cmd13_L_M_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd13_L_M_bit7);

        sms_cmd14_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_bit0);
        sms_cmd14_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_bit1);
        sms_cmd14_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_bit2);
        sms_cmd14_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_bit3);
        sms_cmd14_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_bit4);
        sms_cmd14_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_bit5);
        sms_cmd14_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_bit6);
        sms_cmd14_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_bit7);

        sms_cmd14_L_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_bit0);
        sms_cmd14_L_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_bit1);
        sms_cmd14_L_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_bit2);
        sms_cmd14_L_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_bit3);
        sms_cmd14_L_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_bit4);
        sms_cmd14_L_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_bit5);
        sms_cmd14_L_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_bit6);
        sms_cmd14_L_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_bit7);

        sms_cmd14_H_M_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_M_bit0);
        sms_cmd14_H_M_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_M_bit1);
        sms_cmd14_H_M_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_M_bit2);
        sms_cmd14_H_M_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_M_bit3);
        sms_cmd14_H_M_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_M_bit4);
        sms_cmd14_H_M_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_M_bit5);
        sms_cmd14_H_M_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_M_bit6);
        sms_cmd14_H_M_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd14_H_M_bit7);

        sms_cmd14_L_M_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_M_bit0);
        sms_cmd14_L_M_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_M_bit1);
        sms_cmd14_L_M_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_M_bit2);
        sms_cmd14_L_M_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_M_bit3);
        sms_cmd14_L_M_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_M_bit4);
        sms_cmd14_L_M_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_M_bit5);
        sms_cmd14_L_M_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_M_bit6);
        sms_cmd14_L_M_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd14_L_M_bit7);

        sms_cmd16_H_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd16_H_bit0);
        sms_cmd16_H_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd16_H_bit1);
        sms_cmd16_H_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd16_H_bit2);
        sms_cmd16_H_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd16_H_bit3);
        sms_cmd16_H_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd16_H_bit4);
        sms_cmd16_H_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd16_H_bit5);
        sms_cmd16_H_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd16_H_bit6);
        sms_cmd16_H_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd16_H_bit7);

        sms_cmd16_L_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_bit0);
        sms_cmd16_L_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_bit1);
        sms_cmd16_L_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_bit2);
        sms_cmd16_L_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_bit3);
        sms_cmd16_L_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_bit4);
        sms_cmd16_L_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_bit5);
        sms_cmd16_L_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_bit6);
        sms_cmd16_L_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_bit7);

        sms_cmd16_L_M_bit0           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_M_bit0);
        sms_cmd16_L_M_bit1           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_M_bit1);
        sms_cmd16_L_M_bit2           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_M_bit2);
        sms_cmd16_L_M_bit3           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_M_bit3);
        sms_cmd16_L_M_bit4           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_M_bit4);
        sms_cmd16_L_M_bit5           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_M_bit5);
        sms_cmd16_L_M_bit6           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_M_bit6);
        sms_cmd16_L_M_bit7           =   (RadioButton)       findViewById(R.id.sms_cmd16_L_M_bit7);
        countDown.setVisibility(View.INVISIBLE);
        sms_cmd15.setClickable(false);
        sms_cmd19.setClickable(false);
        sms_cmd20_bit0.setClickable(false);
        sms_cmd20_bit1.setClickable(false);

        sms_cmd7_bit0.setClickable(false);
        sms_cmd7_bit1.setClickable(false);
        sms_cmd7_bit2.setClickable(false);
        sms_cmd7_bit3.setClickable(false);
        sms_cmd7_bit4.setClickable(false);
        sms_cmd7_bit5.setClickable(false);
        sms_cmd7_bit6.setClickable(false);
        sms_cmd7_bit7.setClickable(false);

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

        sms_cmd9_L_bit0.setClickable(false);
        sms_cmd9_L_bit1.setClickable(false);
        sms_cmd9_L_bit2.setClickable(false);
        sms_cmd9_L_bit3.setClickable(false);
        sms_cmd9_L_bit4.setClickable(false);
        sms_cmd9_L_bit5.setClickable(false);
        sms_cmd9_L_bit6.setClickable(false);
        sms_cmd9_L_bit7.setClickable(false);

        sms_cmd9_H_bit0.setClickable(false);
        sms_cmd9_H_bit1.setClickable(false);
        sms_cmd9_H_bit2.setClickable(false);
        sms_cmd9_H_bit3.setClickable(false);
        sms_cmd9_H_bit4.setClickable(false);
        sms_cmd9_H_bit5.setClickable(false);
        sms_cmd9_H_bit6.setClickable(false);
        sms_cmd9_H_bit7.setClickable(false);

        sms_cmd10_H_bit0.setClickable(false);
        sms_cmd10_H_bit1.setClickable(false);
        sms_cmd10_H_bit2.setClickable(false);
        sms_cmd10_H_bit3.setClickable(false);
        sms_cmd10_H_bit4.setClickable(false);
        sms_cmd10_H_bit5.setClickable(false);
        sms_cmd10_H_bit6.setClickable(false);
        sms_cmd10_H_bit7.setClickable(false);

        sms_cmd10_L_bit0.setClickable(false);
        sms_cmd10_L_bit1.setClickable(false);
        sms_cmd10_L_bit2.setClickable(false);
        sms_cmd10_L_bit3.setClickable(false);
        sms_cmd10_L_bit4.setClickable(false);
        sms_cmd10_L_bit5.setClickable(false);
        sms_cmd10_L_bit6.setClickable(false);
        sms_cmd10_L_bit7.setClickable(false);

        sms_cmd11_H_bit0.setClickable(false);
        sms_cmd11_H_bit1.setClickable(false);
        sms_cmd11_H_bit2.setClickable(false);
        sms_cmd11_H_bit3.setClickable(false);
        sms_cmd11_H_bit4.setClickable(false);
        sms_cmd11_H_bit5.setClickable(false);
        sms_cmd11_H_bit6.setClickable(false);
        sms_cmd11_H_bit7.setClickable(false);

        sms_cmd11_L_bit0.setClickable(false);
        sms_cmd11_L_bit1.setClickable(false);
        sms_cmd11_L_bit2.setClickable(false);
        sms_cmd11_L_bit3.setClickable(false);
        sms_cmd11_L_bit4.setClickable(false);
        sms_cmd11_L_bit5.setClickable(false);
        sms_cmd11_L_bit6.setClickable(false);
        sms_cmd11_L_bit7.setClickable(false);

        sms_cmd12_H_bit0.setClickable(false);
        sms_cmd12_H_bit1.setClickable(false);
        sms_cmd12_H_bit2.setClickable(false);
        sms_cmd12_H_bit3.setClickable(false);
        sms_cmd12_H_bit4.setClickable(false);
        sms_cmd12_H_bit5.setClickable(false);
        sms_cmd12_H_bit6.setClickable(false);
        sms_cmd12_H_bit7.setClickable(false);

        sms_cmd12_L_bit0.setClickable(false);
        sms_cmd12_L_bit1.setClickable(false);
        sms_cmd12_L_bit2.setClickable(false);
        sms_cmd12_L_bit3.setClickable(false);
        sms_cmd12_L_bit4.setClickable(false);
        sms_cmd12_L_bit5.setClickable(false);
        sms_cmd12_L_bit6.setClickable(false);
        sms_cmd12_L_bit7.setClickable(false);

        sms_cmd12_H_M_bit0.setClickable(false);
        sms_cmd12_H_M_bit1.setClickable(false);
        sms_cmd12_H_M_bit2.setClickable(false);
        sms_cmd12_H_M_bit3.setClickable(false);
        sms_cmd12_H_M_bit4.setClickable(false);
        sms_cmd12_H_M_bit5.setClickable(false);
        sms_cmd12_H_M_bit6.setClickable(false);
        sms_cmd12_H_M_bit7.setClickable(false);

        sms_cmd12_L_M_bit0.setClickable(false);
        sms_cmd12_L_M_bit1.setClickable(false);
        sms_cmd12_L_M_bit2.setClickable(false);
        sms_cmd12_L_M_bit3.setClickable(false);
        sms_cmd12_L_M_bit4.setClickable(false);
        sms_cmd12_L_M_bit5.setClickable(false);
        sms_cmd12_L_M_bit6.setClickable(false);
        sms_cmd12_L_M_bit7.setClickable(false);

        sms_cmd13_H_bit0.setClickable(false);
        sms_cmd13_H_bit1.setClickable(false);
        sms_cmd13_H_bit2.setClickable(false);
        sms_cmd13_H_bit3.setClickable(false);
        sms_cmd13_H_bit4.setClickable(false);
        sms_cmd13_H_bit5.setClickable(false);
        sms_cmd13_H_bit6.setClickable(false);
        sms_cmd13_H_bit7.setClickable(false);

        sms_cmd13_L_bit0.setClickable(false);
        sms_cmd13_L_bit1.setClickable(false);
        sms_cmd13_L_bit2.setClickable(false);
        sms_cmd13_L_bit3.setClickable(false);
        sms_cmd13_L_bit4.setClickable(false);
        sms_cmd13_L_bit5.setClickable(false);
        sms_cmd13_L_bit6.setClickable(false);
        sms_cmd13_L_bit7.setClickable(false);

        sms_cmd13_H_M_bit0.setClickable(false);
        sms_cmd13_H_M_bit1.setClickable(false);
        sms_cmd13_H_M_bit2.setClickable(false);
        sms_cmd13_H_M_bit3.setClickable(false);
        sms_cmd13_H_M_bit4.setClickable(false);
        sms_cmd13_H_M_bit5.setClickable(false);
        sms_cmd13_H_M_bit6.setClickable(false);
        sms_cmd13_H_M_bit7.setClickable(false);

        sms_cmd13_L_M_bit0.setClickable(false);
        sms_cmd13_L_M_bit1.setClickable(false);
        sms_cmd13_L_M_bit2.setClickable(false);
        sms_cmd13_L_M_bit3.setClickable(false);
        sms_cmd13_L_M_bit4.setClickable(false);
        sms_cmd13_L_M_bit5.setClickable(false);
        sms_cmd13_L_M_bit6.setClickable(false);
        sms_cmd13_L_M_bit7.setClickable(false);

        sms_cmd14_H_bit0.setClickable(false);
        sms_cmd14_H_bit1.setClickable(false);
        sms_cmd14_H_bit2.setClickable(false);
        sms_cmd14_H_bit3.setClickable(false);
        sms_cmd14_H_bit4.setClickable(false);
        sms_cmd14_H_bit5.setClickable(false);
        sms_cmd14_H_bit6.setClickable(false);
        sms_cmd14_H_bit7.setClickable(false);

        sms_cmd14_L_bit0.setClickable(false);
        sms_cmd14_L_bit1.setClickable(false);
        sms_cmd14_L_bit2.setClickable(false);
        sms_cmd14_L_bit3.setClickable(false);
        sms_cmd14_L_bit4.setClickable(false);
        sms_cmd14_L_bit5.setClickable(false);
        sms_cmd14_L_bit6.setClickable(false);
        sms_cmd14_L_bit7.setClickable(false);

        sms_cmd14_H_M_bit0.setClickable(false);
        sms_cmd14_H_M_bit1.setClickable(false);
        sms_cmd14_H_M_bit2.setClickable(false);
        sms_cmd14_H_M_bit3.setClickable(false);
        sms_cmd14_H_M_bit4.setClickable(false);
        sms_cmd14_H_M_bit5.setClickable(false);
        sms_cmd14_H_M_bit6.setClickable(false);
        sms_cmd14_H_M_bit7.setClickable(false);

        sms_cmd14_L_M_bit0.setClickable(false);
        sms_cmd14_L_M_bit1.setClickable(false);
        sms_cmd14_L_M_bit2.setClickable(false);
        sms_cmd14_L_M_bit3.setClickable(false);
        sms_cmd14_L_M_bit4.setClickable(false);
        sms_cmd14_L_M_bit5.setClickable(false);
        sms_cmd14_L_M_bit6.setClickable(false);
        sms_cmd14_L_M_bit7.setClickable(false);

        sms_cmd16_H_bit0.setClickable(false);
        sms_cmd16_H_bit1.setClickable(false);
        sms_cmd16_H_bit2.setClickable(false);
        sms_cmd16_H_bit3.setClickable(false);
        sms_cmd16_H_bit4.setClickable(false);
        sms_cmd16_H_bit5.setClickable(false);
        sms_cmd16_H_bit6.setClickable(false);
        sms_cmd16_H_bit7.setClickable(false);

        sms_cmd16_L_bit0.setClickable(false);
        sms_cmd16_L_bit1.setClickable(false);
        sms_cmd16_L_bit2.setClickable(false);
        sms_cmd16_L_bit3.setClickable(false);
        sms_cmd16_L_bit4.setClickable(false);
        sms_cmd16_L_bit5.setClickable(false);
        sms_cmd16_L_bit6.setClickable(false);
        sms_cmd16_L_bit7.setClickable(false);

        sms_cmd16_L_M_bit0.setClickable(false);
        sms_cmd16_L_M_bit1.setClickable(false);
        sms_cmd16_L_M_bit2.setClickable(false);
        sms_cmd16_L_M_bit3.setClickable(false);
        sms_cmd16_L_M_bit4.setClickable(false);
        sms_cmd16_L_M_bit5.setClickable(false);
        sms_cmd16_L_M_bit6.setClickable(false);
        sms_cmd16_L_M_bit7.setClickable(false);

        /*END OF MAPPING RADIO BUTTONS*/

        sms = SmsManager.getDefault();
        checkForSmsPermission();
        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theKey                      = theChoice.getString("ID");

        customerSMSReceiverControllerDB = new CustomerSMSReceiverControllerDB(this);
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
        theQuery = "CREATE TABLE IF NOT EXISTS CUSTOMER_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
        theSqLiteDatabase.execSQL(theQuery);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd, MMM, yyyy");
        date = dateFormat.format(calendar.getTime());
        today.setText("Today is:"+date);
        customerControllerDB = new CustomerControllerDB(this);
        sqLiteDatabase = customerControllerDB.getReadableDatabase();
        query = "SELECT CUSTOMER_CLIENT_NAME FROM CUSTOMER_CLIENT_DATA WHERE CUSTOMER_CLIENT_NAME='"+theKey+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    adminText.setText(cursor.getString(cursor.getColumnIndex("CUSTOMER_CLIENT_NAME")));
                    //adminText.setBackgroundColor(Color.WHITE);
                    //adminText.setTextColor(Color.BLACK);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        customerControllerDB = new CustomerControllerDB(this);
        sqLiteDatabase = customerControllerDB.getReadableDatabase();
        contactNumberQuery = "SELECT CUSTOMER_CLIENT_NUMBER FROM CUSTOMER_CLIENT_DATA WHERE CUSTOMER_CLIENT_NAME='"+theKey+"'";
        Cursor cursor2 = sqLiteDatabase.rawQuery(contactNumberQuery,null);
        rowCount2 = cursor2.getCount();
        if(rowCount2!=0)
        {
            if (cursor2.moveToFirst())
            {
                do
                {
                    choice = cursor2.getString(cursor2.getColumnIndex("CUSTOMER_CLIENT_NUMBER"));
                }while (cursor2.moveToNext());
            }
        }
        cursor2.close();
        // Register a broadcast receiver for receiving SMS
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
        //toast("REGISTERED THE SMS RECEIVER");
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM CUSTOMER_SMS WHERE DATE='"+date+"'",null);
        clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM CUSTOMER_SMS WHERE ADDRESS='"+choice+"'",null);
        rowCount3 = clientCursor.getCount();
        //toast(String.valueOf(rowCount3));
        theContent = new ArrayList<String>();
        theContent.clear();
        /*if(rowCount3 != emptyTable)
        {
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
                String str1 = sb0.toString();
                adminText.setText(str1);
                String decodeChoice = str1;
                //DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING
                String stringSplit[] = decodeChoice.split("\n");
                for(int s=0; s<stringSplit.length; s++)
                {
                    if(!String.valueOf(stringSplit[s]).isEmpty())
                    {
                        //DISPLAY FOR MAIN CARD INPUT//
                        //toast(String.valueOf(stringSplit[s].charAt(2)));
                        if(String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("7"))
                        {
                            //CMD7_I.setBackgroundColor(Color.RED);
                            *//******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd7_bit0.setChecked(true);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(true);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd7_bit0.setChecked(true);
                                sms_cmd7_bit1.setChecked(true);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(true);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd7_bit0.setChecked(true);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(true);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(true);
                                sms_cmd7_bit2.setChecked(true);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd7_bit0.setChecked(true);
                                sms_cmd7_bit1.setChecked(true);
                                sms_cmd7_bit2.setChecked(true);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd7_bit0.setChecked(true);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(true);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd7_bit0.setChecked(false);
                                sms_cmd7_bit1.setChecked(false);
                                sms_cmd7_bit2.setChecked(false);
                                sms_cmd7_bit3.setChecked(false);
                            }
                            *//***************************** END OF FIRST CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd7_bit4.setChecked(true);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(true);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd7_bit4.setChecked(true);
                                sms_cmd7_bit5.setChecked(true);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(true);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd7_bit4.setChecked(true);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(true);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(true);
                                sms_cmd7_bit6.setChecked(true);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd7_bit4.setChecked(true);
                                sms_cmd7_bit5.setChecked(true);
                                sms_cmd7_bit6.setChecked(true);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd7_bit4.setChecked(true);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(true);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd7_bit4.setChecked(false);
                                sms_cmd7_bit5.setChecked(false);
                                sms_cmd7_bit6.setChecked(false);
                                sms_cmd7_bit7.setChecked(false);
                            }
                            *//***************************** END OF SECOND CHARACTER FROM RIGHT **************************//*
                        }

                        //DISPLAY FOR MAIN CARD TERMINAL INPUT//
                        if(String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("8"))
                        {
                            *//******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd8_bit0.setChecked(false);
                                sms_cmd8_bit1.setChecked(false);
                                sms_cmd8_bit2.setChecked(false);
                                sms_cmd8_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd8_bit0.setChecked(true);
                                sms_cmd8_bit1.setChecked(false);
                                sms_cmd8_bit2.setChecked(false);
                                sms_cmd8_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd8_bit0.setChecked(false);
                                sms_cmd8_bit1.setChecked(true);
                                sms_cmd8_bit2.setChecked(false);
                                sms_cmd8_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd8_bit0.setChecked(true);
                                sms_cmd8_bit1.setChecked(true);
                                sms_cmd8_bit2.setChecked(false);
                                sms_cmd8_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd8_bit0.setChecked(false);
                                sms_cmd8_bit1.setChecked(false);
                                sms_cmd8_bit2.setChecked(true);
                                sms_cmd8_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd8_bit0.setChecked(true);
                                sms_cmd8_bit1.setChecked(false);
                                sms_cmd8_bit2.setChecked(true);
                                sms_cmd8_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd8_bit0.setChecked(false);
                                sms_cmd8_bit1.setChecked(true);
                                sms_cmd8_bit2.setChecked(true);
                                sms_cmd8_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd8_bit0.setChecked(true);
                                sms_cmd8_bit1.setChecked(true);
                                sms_cmd8_bit2.setChecked(true);
                                sms_cmd8_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd8_bit0.setChecked(false);
                                sms_cmd8_bit1.setChecked(false);
                                sms_cmd8_bit2.setChecked(false);
                                sms_cmd8_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd8_bit0.setChecked(true);
                                sms_cmd8_bit1.setChecked(false);
                                sms_cmd8_bit2.setChecked(false);
                                sms_cmd8_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd8_bit0.setChecked(false);
                                sms_cmd8_bit1.setChecked(true);
                                sms_cmd8_bit2.setChecked(false);
                                sms_cmd8_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd8_bit0.setChecked(true);
                                sms_cmd8_bit1.setChecked(true);
                                sms_cmd8_bit2.setChecked(false);
                                sms_cmd8_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd8_bit0.setChecked(false);
                                sms_cmd8_bit1.setChecked(false);
                                sms_cmd8_bit2.setChecked(true);
                                sms_cmd8_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd8_bit0.setChecked(true);
                                sms_cmd8_bit1.setChecked(false);
                                sms_cmd8_bit2.setChecked(true);
                                sms_cmd8_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd8_bit0.setChecked(false);
                                sms_cmd8_bit1.setChecked(true);
                                sms_cmd8_bit2.setChecked(true);
                                sms_cmd8_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd8_bit0.setChecked(true);
                                sms_cmd8_bit1.setChecked(true);
                                sms_cmd8_bit2.setChecked(true);
                                sms_cmd8_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIRST CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd8_bit4.setChecked(false);
                                sms_cmd8_bit5.setChecked(false);
                                sms_cmd8_bit6.setChecked(false);
                                sms_cmd8_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd8_bit4.setChecked(true);
                                sms_cmd8_bit5.setChecked(false);
                                sms_cmd8_bit6.setChecked(false);
                                sms_cmd8_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd8_bit4.setChecked(false);
                                sms_cmd8_bit5.setChecked(true);
                                sms_cmd8_bit6.setChecked(false);
                                sms_cmd8_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd8_bit4.setChecked(true);
                                sms_cmd8_bit5.setChecked(true);
                                sms_cmd8_bit6.setChecked(false);
                                sms_cmd8_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd8_bit4.setChecked(false);
                                sms_cmd8_bit5.setChecked(false);
                                sms_cmd8_bit6.setChecked(true);
                                sms_cmd8_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd8_bit4.setChecked(true);
                                sms_cmd8_bit5.setChecked(false);
                                sms_cmd8_bit6.setChecked(true);
                                sms_cmd8_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd8_bit4.setChecked(false);
                                sms_cmd8_bit5.setChecked(true);
                                sms_cmd8_bit6.setChecked(true);
                                sms_cmd8_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd8_bit4.setChecked(true);
                                sms_cmd8_bit5.setChecked(true);
                                sms_cmd8_bit6.setChecked(true);
                                sms_cmd8_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd8_bit4.setChecked(false);
                                sms_cmd8_bit5.setChecked(false);
                                sms_cmd8_bit6.setChecked(false);
                                sms_cmd8_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd8_bit4.setChecked(true);
                                sms_cmd8_bit5.setChecked(false);
                                sms_cmd8_bit6.setChecked(false);
                                sms_cmd8_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd8_bit4.setChecked(false);
                                sms_cmd8_bit5.setChecked(true);
                                sms_cmd8_bit6.setChecked(false);
                                sms_cmd8_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd8_bit4.setChecked(true);
                                sms_cmd8_bit5.setChecked(true);
                                sms_cmd8_bit6.setChecked(false);
                                sms_cmd8_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd8_bit4.setChecked(false);
                                sms_cmd8_bit5.setChecked(false);
                                sms_cmd8_bit6.setChecked(true);
                                sms_cmd8_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd8_bit4.setChecked(true);
                                sms_cmd8_bit5.setChecked(false);
                                sms_cmd8_bit6.setChecked(true);
                                sms_cmd8_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd8_bit4.setChecked(false);
                                sms_cmd8_bit5.setChecked(true);
                                sms_cmd8_bit6.setChecked(true);
                                sms_cmd8_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd8_bit4.setChecked(true);
                                sms_cmd8_bit5.setChecked(true);
                                sms_cmd8_bit6.setChecked(true);
                                sms_cmd8_bit7.setChecked(true);
                            }
                            *//***************************** END OF SECOND CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd8_H_bit0.setChecked(false);
                                sms_cmd8_H_bit1.setChecked(false);
                                sms_cmd8_H_bit2.setChecked(false);
                                sms_cmd8_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd8_H_bit0.setChecked(true);
                                sms_cmd8_H_bit1.setChecked(false);
                                sms_cmd8_H_bit2.setChecked(false);
                                sms_cmd8_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd8_H_bit0.setChecked(false);
                                sms_cmd8_H_bit1.setChecked(true);
                                sms_cmd8_H_bit2.setChecked(false);
                                sms_cmd8_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd8_H_bit0.setChecked(true);
                                sms_cmd8_H_bit1.setChecked(true);
                                sms_cmd8_H_bit2.setChecked(false);
                                sms_cmd8_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd8_H_bit0.setChecked(false);
                                sms_cmd8_H_bit1.setChecked(false);
                                sms_cmd8_H_bit2.setChecked(true);
                                sms_cmd8_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd8_H_bit0.setChecked(true);
                                sms_cmd8_H_bit1.setChecked(false);
                                sms_cmd8_H_bit2.setChecked(true);
                                sms_cmd8_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd8_H_bit0.setChecked(false);
                                sms_cmd8_H_bit1.setChecked(true);
                                sms_cmd8_H_bit2.setChecked(true);
                                sms_cmd8_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd8_H_bit0.setChecked(true);
                                sms_cmd8_H_bit1.setChecked(true);
                                sms_cmd8_H_bit2.setChecked(true);
                                sms_cmd8_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd8_H_bit0.setChecked(false);
                                sms_cmd8_H_bit1.setChecked(false);
                                sms_cmd8_H_bit2.setChecked(false);
                                sms_cmd8_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd8_H_bit0.setChecked(true);
                                sms_cmd8_H_bit1.setChecked(false);
                                sms_cmd8_H_bit2.setChecked(false);
                                sms_cmd8_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd8_H_bit0.setChecked(false);
                                sms_cmd8_H_bit1.setChecked(true);
                                sms_cmd8_H_bit2.setChecked(false);
                                sms_cmd8_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd8_H_bit0.setChecked(true);
                                sms_cmd8_H_bit1.setChecked(true);
                                sms_cmd8_H_bit2.setChecked(false);
                                sms_cmd8_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd8_H_bit0.setChecked(false);
                                sms_cmd8_H_bit1.setChecked(false);
                                sms_cmd8_H_bit2.setChecked(true);
                                sms_cmd8_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd8_H_bit0.setChecked(true);
                                sms_cmd8_H_bit1.setChecked(false);
                                sms_cmd8_H_bit2.setChecked(true);
                                sms_cmd8_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd8_H_bit0.setChecked(false);
                                sms_cmd8_H_bit1.setChecked(true);
                                sms_cmd8_H_bit2.setChecked(true);
                                sms_cmd8_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd8_H_bit0.setChecked(true);
                                sms_cmd8_H_bit1.setChecked(true);
                                sms_cmd8_H_bit2.setChecked(true);
                                sms_cmd8_H_bit3.setChecked(true);
                            }
                            *//***************************** END OF THIRD CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd8_H_bit4.setChecked(false);
                                sms_cmd8_H_bit5.setChecked(false);
                                sms_cmd8_H_bit6.setChecked(false);
                                sms_cmd8_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd8_H_bit4.setChecked(true);
                                sms_cmd8_H_bit5.setChecked(false);
                                sms_cmd8_H_bit6.setChecked(false);
                                sms_cmd8_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd8_H_bit4.setChecked(false);
                                sms_cmd8_H_bit5.setChecked(true);
                                sms_cmd8_H_bit6.setChecked(false);
                                sms_cmd8_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd8_H_bit4.setChecked(true);
                                sms_cmd8_H_bit5.setChecked(true);
                                sms_cmd8_H_bit6.setChecked(false);
                                sms_cmd8_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd8_H_bit4.setChecked(false);
                                sms_cmd8_H_bit5.setChecked(false);
                                sms_cmd8_H_bit6.setChecked(true);
                                sms_cmd8_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd8_H_bit4.setChecked(true);
                                sms_cmd8_H_bit5.setChecked(false);
                                sms_cmd8_H_bit6.setChecked(true);
                                sms_cmd8_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd8_H_bit4.setChecked(false);
                                sms_cmd8_H_bit5.setChecked(true);
                                sms_cmd8_H_bit6.setChecked(true);
                                sms_cmd8_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd8_H_bit4.setChecked(true);
                                sms_cmd8_H_bit5.setChecked(true);
                                sms_cmd8_H_bit6.setChecked(true);
                                sms_cmd8_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd8_H_bit4.setChecked(false);
                                sms_cmd8_H_bit5.setChecked(false);
                                sms_cmd8_H_bit6.setChecked(false);
                                sms_cmd8_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd8_H_bit4.setChecked(true);
                                sms_cmd8_H_bit5.setChecked(false);
                                sms_cmd8_H_bit6.setChecked(false);
                                sms_cmd8_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd8_H_bit4.setChecked(false);
                                sms_cmd8_H_bit5.setChecked(true);
                                sms_cmd8_H_bit6.setChecked(false);
                                sms_cmd8_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd8_H_bit4.setChecked(true);
                                sms_cmd8_H_bit5.setChecked(true);
                                sms_cmd8_H_bit6.setChecked(false);
                                sms_cmd8_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd8_H_bit4.setChecked(false);
                                sms_cmd8_H_bit5.setChecked(false);
                                sms_cmd8_H_bit6.setChecked(true);
                                sms_cmd8_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd8_H_bit4.setChecked(true);
                                sms_cmd8_H_bit5.setChecked(false);
                                sms_cmd8_H_bit6.setChecked(true);
                                sms_cmd8_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd8_H_bit4.setChecked(false);
                                sms_cmd8_H_bit5.setChecked(true);
                                sms_cmd8_H_bit6.setChecked(true);
                                sms_cmd8_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd8_H_bit4.setChecked(true);
                                sms_cmd8_H_bit5.setChecked(true);
                                sms_cmd8_H_bit6.setChecked(true);
                                sms_cmd8_H_bit7.setChecked(true);
                            }
                            *//***************************** END OF FOURTH CHARACTER FROM RIGHT **************************//*
                        }

                        //DISPLAY FOR MAIN CARD OUTPUT//
                        if(String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("9"))
                        {
                            *//******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd9_L_bit0.setChecked(false);
                                sms_cmd9_L_bit1.setChecked(false);
                                sms_cmd9_L_bit2.setChecked(false);
                                sms_cmd9_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd9_L_bit0.setChecked(true);
                                sms_cmd9_L_bit1.setChecked(false);
                                sms_cmd9_L_bit2.setChecked(false);
                                sms_cmd9_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd9_L_bit0.setChecked(false);
                                sms_cmd9_L_bit1.setChecked(true);
                                sms_cmd9_L_bit2.setChecked(false);
                                sms_cmd9_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd9_L_bit0.setChecked(true);
                                sms_cmd9_L_bit1.setChecked(true);
                                sms_cmd9_L_bit2.setChecked(false);
                                sms_cmd9_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd9_L_bit0.setChecked(false);
                                sms_cmd9_L_bit1.setChecked(false);
                                sms_cmd9_L_bit2.setChecked(true);
                                sms_cmd9_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd9_L_bit0.setChecked(true);
                                sms_cmd9_L_bit1.setChecked(false);
                                sms_cmd9_L_bit2.setChecked(true);
                                sms_cmd9_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd9_L_bit0.setChecked(false);
                                sms_cmd9_L_bit1.setChecked(true);
                                sms_cmd9_L_bit2.setChecked(true);
                                sms_cmd9_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd9_L_bit0.setChecked(true);
                                sms_cmd9_L_bit1.setChecked(true);
                                sms_cmd9_L_bit2.setChecked(true);
                                sms_cmd9_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd9_L_bit0.setChecked(false);
                                sms_cmd9_L_bit1.setChecked(false);
                                sms_cmd9_L_bit2.setChecked(false);
                                sms_cmd9_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd9_L_bit0.setChecked(true);
                                sms_cmd9_L_bit1.setChecked(false);
                                sms_cmd9_L_bit2.setChecked(false);
                                sms_cmd9_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd9_L_bit0.setChecked(false);
                                sms_cmd9_L_bit1.setChecked(true);
                                sms_cmd9_L_bit2.setChecked(false);
                                sms_cmd9_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd9_L_bit0.setChecked(true);
                                sms_cmd9_L_bit1.setChecked(true);
                                sms_cmd9_L_bit2.setChecked(false);
                                sms_cmd9_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd9_L_bit0.setChecked(false);
                                sms_cmd9_L_bit1.setChecked(false);
                                sms_cmd9_L_bit2.setChecked(true);
                                sms_cmd9_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd9_L_bit0.setChecked(true);
                                sms_cmd9_L_bit1.setChecked(false);
                                sms_cmd9_L_bit2.setChecked(true);
                                sms_cmd9_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd9_L_bit0.setChecked(false);
                                sms_cmd9_L_bit1.setChecked(true);
                                sms_cmd9_L_bit2.setChecked(true);
                                sms_cmd9_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd9_L_bit0.setChecked(true);
                                sms_cmd9_L_bit1.setChecked(true);
                                sms_cmd9_L_bit2.setChecked(true);
                                sms_cmd9_L_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIRST CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd9_L_bit4.setChecked(false);
                                sms_cmd9_L_bit5.setChecked(false);
                                sms_cmd9_L_bit6.setChecked(false);
                                sms_cmd9_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd9_L_bit4.setChecked(true);
                                sms_cmd9_L_bit5.setChecked(false);
                                sms_cmd9_L_bit6.setChecked(false);
                                sms_cmd9_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd9_L_bit4.setChecked(false);
                                sms_cmd9_L_bit5.setChecked(true);
                                sms_cmd9_L_bit6.setChecked(false);
                                sms_cmd9_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd9_L_bit4.setChecked(true);
                                sms_cmd9_L_bit5.setChecked(true);
                                sms_cmd9_L_bit6.setChecked(false);
                                sms_cmd9_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd9_L_bit4.setChecked(false);
                                sms_cmd9_L_bit5.setChecked(false);
                                sms_cmd9_L_bit6.setChecked(true);
                                sms_cmd9_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd9_L_bit4.setChecked(true);
                                sms_cmd9_L_bit5.setChecked(false);
                                sms_cmd9_L_bit6.setChecked(true);
                                sms_cmd9_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd9_L_bit4.setChecked(false);
                                sms_cmd9_L_bit5.setChecked(true);
                                sms_cmd9_L_bit6.setChecked(true);
                                sms_cmd9_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd9_L_bit4.setChecked(true);
                                sms_cmd9_L_bit5.setChecked(true);
                                sms_cmd9_L_bit6.setChecked(true);
                                sms_cmd9_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd9_L_bit4.setChecked(false);
                                sms_cmd9_L_bit5.setChecked(false);
                                sms_cmd9_L_bit6.setChecked(false);
                                sms_cmd9_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd9_L_bit4.setChecked(true);
                                sms_cmd9_L_bit5.setChecked(false);
                                sms_cmd9_L_bit6.setChecked(false);
                                sms_cmd9_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd9_L_bit4.setChecked(false);
                                sms_cmd9_L_bit5.setChecked(true);
                                sms_cmd9_L_bit6.setChecked(false);
                                sms_cmd9_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd9_L_bit4.setChecked(true);
                                sms_cmd9_L_bit5.setChecked(true);
                                sms_cmd9_L_bit6.setChecked(false);
                                sms_cmd9_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd9_L_bit4.setChecked(false);
                                sms_cmd9_L_bit5.setChecked(false);
                                sms_cmd9_L_bit6.setChecked(true);
                                sms_cmd9_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd9_L_bit4.setChecked(true);
                                sms_cmd9_L_bit5.setChecked(false);
                                sms_cmd9_L_bit6.setChecked(true);
                                sms_cmd9_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd9_L_bit4.setChecked(false);
                                sms_cmd9_L_bit5.setChecked(true);
                                sms_cmd9_L_bit6.setChecked(true);
                                sms_cmd9_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd9_L_bit4.setChecked(true);
                                sms_cmd9_L_bit5.setChecked(true);
                                sms_cmd9_L_bit6.setChecked(true);
                                sms_cmd9_L_bit7.setChecked(true);
                            }
                            *//***************************** END OF SECOND CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd9_H_bit0.setChecked(false);
                                sms_cmd9_H_bit1.setChecked(false);
                                sms_cmd9_H_bit2.setChecked(false);
                                sms_cmd9_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd9_H_bit0.setChecked(true);
                                sms_cmd9_H_bit1.setChecked(false);
                                sms_cmd9_H_bit2.setChecked(false);
                                sms_cmd9_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd9_H_bit0.setChecked(false);
                                sms_cmd9_H_bit1.setChecked(true);
                                sms_cmd9_H_bit2.setChecked(false);
                                sms_cmd9_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd9_H_bit0.setChecked(true);
                                sms_cmd9_H_bit1.setChecked(true);
                                sms_cmd9_H_bit2.setChecked(false);
                                sms_cmd9_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd9_H_bit0.setChecked(false);
                                sms_cmd9_H_bit1.setChecked(false);
                                sms_cmd9_H_bit2.setChecked(true);
                                sms_cmd9_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd9_H_bit0.setChecked(true);
                                sms_cmd9_H_bit1.setChecked(false);
                                sms_cmd9_H_bit2.setChecked(true);
                                sms_cmd9_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd9_H_bit0.setChecked(false);
                                sms_cmd9_H_bit1.setChecked(true);
                                sms_cmd9_H_bit2.setChecked(true);
                                sms_cmd9_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd9_H_bit0.setChecked(true);
                                sms_cmd9_H_bit1.setChecked(true);
                                sms_cmd9_H_bit2.setChecked(true);
                                sms_cmd9_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd9_H_bit0.setChecked(false);
                                sms_cmd9_H_bit1.setChecked(false);
                                sms_cmd9_H_bit2.setChecked(false);
                                sms_cmd9_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd9_H_bit0.setChecked(true);
                                sms_cmd9_H_bit1.setChecked(false);
                                sms_cmd9_H_bit2.setChecked(false);
                                sms_cmd9_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd9_H_bit0.setChecked(false);
                                sms_cmd9_H_bit1.setChecked(true);
                                sms_cmd9_H_bit2.setChecked(false);
                                sms_cmd9_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd9_H_bit0.setChecked(true);
                                sms_cmd9_H_bit1.setChecked(true);
                                sms_cmd9_H_bit2.setChecked(false);
                                sms_cmd9_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd9_H_bit0.setChecked(false);
                                sms_cmd9_H_bit1.setChecked(false);
                                sms_cmd9_H_bit2.setChecked(true);
                                sms_cmd9_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd9_H_bit0.setChecked(true);
                                sms_cmd9_H_bit1.setChecked(false);
                                sms_cmd9_H_bit2.setChecked(true);
                                sms_cmd9_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd9_H_bit0.setChecked(false);
                                sms_cmd9_H_bit1.setChecked(true);
                                sms_cmd9_H_bit2.setChecked(true);
                                sms_cmd9_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd9_H_bit0.setChecked(true);
                                sms_cmd9_H_bit1.setChecked(true);
                                sms_cmd9_H_bit2.setChecked(true);
                                sms_cmd9_H_bit3.setChecked(true);
                            }
                            *//***************************** END OF THIRD CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd9_H_bit4.setChecked(false);
                                sms_cmd9_H_bit5.setChecked(false);
                                sms_cmd9_H_bit6.setChecked(false);
                                sms_cmd9_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd9_H_bit4.setChecked(true);
                                sms_cmd9_H_bit5.setChecked(false);
                                sms_cmd9_H_bit6.setChecked(false);
                                sms_cmd9_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd9_H_bit4.setChecked(false);
                                sms_cmd9_H_bit5.setChecked(true);
                                sms_cmd9_H_bit6.setChecked(false);
                                sms_cmd9_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd9_H_bit4.setChecked(true);
                                sms_cmd9_H_bit5.setChecked(true);
                                sms_cmd9_H_bit6.setChecked(false);
                                sms_cmd9_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd9_H_bit4.setChecked(false);
                                sms_cmd9_H_bit5.setChecked(false);
                                sms_cmd9_H_bit6.setChecked(true);
                                sms_cmd9_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd9_H_bit4.setChecked(true);
                                sms_cmd9_H_bit5.setChecked(false);
                                sms_cmd9_H_bit6.setChecked(true);
                                sms_cmd9_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd9_H_bit4.setChecked(false);
                                sms_cmd9_H_bit5.setChecked(true);
                                sms_cmd9_H_bit6.setChecked(true);
                                sms_cmd9_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd9_H_bit4.setChecked(true);
                                sms_cmd9_H_bit5.setChecked(true);
                                sms_cmd9_H_bit6.setChecked(true);
                                sms_cmd9_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd9_H_bit4.setChecked(false);
                                sms_cmd9_H_bit5.setChecked(false);
                                sms_cmd9_H_bit6.setChecked(false);
                                sms_cmd9_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd9_H_bit4.setChecked(true);
                                sms_cmd9_H_bit5.setChecked(false);
                                sms_cmd9_H_bit6.setChecked(false);
                                sms_cmd9_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd9_H_bit4.setChecked(false);
                                sms_cmd9_H_bit5.setChecked(true);
                                sms_cmd9_H_bit6.setChecked(false);
                                sms_cmd9_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd9_H_bit4.setChecked(true);
                                sms_cmd9_H_bit5.setChecked(true);
                                sms_cmd9_H_bit6.setChecked(false);
                                sms_cmd9_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd9_H_bit4.setChecked(false);
                                sms_cmd9_H_bit5.setChecked(false);
                                sms_cmd9_H_bit6.setChecked(true);
                                sms_cmd9_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd9_H_bit4.setChecked(true);
                                sms_cmd9_H_bit5.setChecked(false);
                                sms_cmd9_H_bit6.setChecked(true);
                                sms_cmd9_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd9_H_bit4.setChecked(false);
                                sms_cmd9_H_bit5.setChecked(true);
                                sms_cmd9_H_bit6.setChecked(true);
                                sms_cmd9_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd9_H_bit4.setChecked(true);
                                sms_cmd9_H_bit5.setChecked(true);
                                sms_cmd9_H_bit6.setChecked(true);
                                sms_cmd9_H_bit7.setChecked(true);
                            }
                            *//***************************** END OF FOURTH CHARACTER FROM RIGHT **************************//*
                        }

                        //DISPLAY FOR CBC INPUTS//
                        if(String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("0"))
                        {
                            *//******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd10_L_bit0.setChecked(false);
                                sms_cmd10_L_bit1.setChecked(false);
                                sms_cmd10_L_bit2.setChecked(false);
                                sms_cmd10_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd10_L_bit0.setChecked(true);
                                sms_cmd10_L_bit1.setChecked(false);
                                sms_cmd10_L_bit2.setChecked(false);
                                sms_cmd10_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd10_L_bit0.setChecked(false);
                                sms_cmd10_L_bit1.setChecked(true);
                                sms_cmd10_L_bit2.setChecked(false);
                                sms_cmd10_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd10_L_bit0.setChecked(true);
                                sms_cmd10_L_bit1.setChecked(true);
                                sms_cmd10_L_bit2.setChecked(false);
                                sms_cmd10_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd10_L_bit0.setChecked(false);
                                sms_cmd10_L_bit1.setChecked(false);
                                sms_cmd10_L_bit2.setChecked(true);
                                sms_cmd10_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd10_L_bit0.setChecked(true);
                                sms_cmd10_L_bit1.setChecked(false);
                                sms_cmd10_L_bit2.setChecked(true);
                                sms_cmd10_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd10_L_bit0.setChecked(false);
                                sms_cmd10_L_bit1.setChecked(true);
                                sms_cmd10_L_bit2.setChecked(true);
                                sms_cmd10_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd10_L_bit0.setChecked(true);
                                sms_cmd10_L_bit1.setChecked(true);
                                sms_cmd10_L_bit2.setChecked(true);
                                sms_cmd10_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd10_L_bit0.setChecked(false);
                                sms_cmd10_L_bit1.setChecked(false);
                                sms_cmd10_L_bit2.setChecked(false);
                                sms_cmd10_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd10_L_bit0.setChecked(true);
                                sms_cmd10_L_bit1.setChecked(false);
                                sms_cmd10_L_bit2.setChecked(false);
                                sms_cmd10_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd10_L_bit0.setChecked(false);
                                sms_cmd10_L_bit1.setChecked(true);
                                sms_cmd10_L_bit2.setChecked(false);
                                sms_cmd10_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd10_L_bit0.setChecked(true);
                                sms_cmd10_L_bit1.setChecked(true);
                                sms_cmd10_L_bit2.setChecked(false);
                                sms_cmd10_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd10_L_bit0.setChecked(false);
                                sms_cmd10_L_bit1.setChecked(false);
                                sms_cmd10_L_bit2.setChecked(true);
                                sms_cmd10_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd10_L_bit0.setChecked(true);
                                sms_cmd10_L_bit1.setChecked(false);
                                sms_cmd10_L_bit2.setChecked(true);
                                sms_cmd10_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd10_L_bit0.setChecked(false);
                                sms_cmd10_L_bit1.setChecked(true);
                                sms_cmd10_L_bit2.setChecked(true);
                                sms_cmd10_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd10_L_bit0.setChecked(true);
                                sms_cmd10_L_bit1.setChecked(true);
                                sms_cmd10_L_bit2.setChecked(true);
                                sms_cmd10_L_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIRST CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd10_L_bit4.setChecked(false);
                                sms_cmd10_L_bit5.setChecked(false);
                                sms_cmd10_L_bit6.setChecked(false);
                                sms_cmd10_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd10_L_bit4.setChecked(true);
                                sms_cmd10_L_bit5.setChecked(false);
                                sms_cmd10_L_bit6.setChecked(false);
                                sms_cmd10_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd10_L_bit4.setChecked(false);
                                sms_cmd10_L_bit5.setChecked(true);
                                sms_cmd10_L_bit6.setChecked(false);
                                sms_cmd10_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd10_L_bit4.setChecked(true);
                                sms_cmd10_L_bit5.setChecked(true);
                                sms_cmd10_L_bit6.setChecked(false);
                                sms_cmd10_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd10_L_bit4.setChecked(false);
                                sms_cmd10_L_bit5.setChecked(false);
                                sms_cmd10_L_bit6.setChecked(true);
                                sms_cmd10_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd10_L_bit4.setChecked(true);
                                sms_cmd10_L_bit5.setChecked(false);
                                sms_cmd10_L_bit6.setChecked(true);
                                sms_cmd10_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd10_L_bit4.setChecked(false);
                                sms_cmd10_L_bit5.setChecked(true);
                                sms_cmd10_L_bit6.setChecked(true);
                                sms_cmd10_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd10_L_bit4.setChecked(true);
                                sms_cmd10_L_bit5.setChecked(true);
                                sms_cmd10_L_bit6.setChecked(true);
                                sms_cmd10_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd10_L_bit4.setChecked(false);
                                sms_cmd10_L_bit5.setChecked(false);
                                sms_cmd10_L_bit6.setChecked(false);
                                sms_cmd10_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd10_L_bit4.setChecked(true);
                                sms_cmd10_L_bit5.setChecked(false);
                                sms_cmd10_L_bit6.setChecked(false);
                                sms_cmd10_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd10_L_bit4.setChecked(false);
                                sms_cmd10_L_bit5.setChecked(true);
                                sms_cmd10_L_bit6.setChecked(false);
                                sms_cmd10_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd10_L_bit4.setChecked(true);
                                sms_cmd10_L_bit5.setChecked(true);
                                sms_cmd10_L_bit6.setChecked(false);
                                sms_cmd10_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd10_L_bit4.setChecked(false);
                                sms_cmd10_L_bit5.setChecked(false);
                                sms_cmd10_L_bit6.setChecked(true);
                                sms_cmd10_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd10_L_bit4.setChecked(true);
                                sms_cmd10_L_bit5.setChecked(false);
                                sms_cmd10_L_bit6.setChecked(true);
                                sms_cmd10_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd10_L_bit4.setChecked(false);
                                sms_cmd10_L_bit5.setChecked(true);
                                sms_cmd10_L_bit6.setChecked(true);
                                sms_cmd10_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd10_L_bit4.setChecked(true);
                                sms_cmd10_L_bit5.setChecked(true);
                                sms_cmd10_L_bit6.setChecked(true);
                                sms_cmd10_L_bit7.setChecked(true);
                            }
                            *//***************************** END OF SECOND CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd10_H_bit0.setChecked(false);
                                sms_cmd10_H_bit1.setChecked(false);
                                sms_cmd10_H_bit2.setChecked(false);
                                sms_cmd10_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd10_H_bit0.setChecked(true);
                                sms_cmd10_H_bit1.setChecked(false);
                                sms_cmd10_H_bit2.setChecked(false);
                                sms_cmd10_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd10_H_bit0.setChecked(false);
                                sms_cmd10_H_bit1.setChecked(true);
                                sms_cmd10_H_bit2.setChecked(false);
                                sms_cmd10_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd10_H_bit0.setChecked(true);
                                sms_cmd10_H_bit1.setChecked(true);
                                sms_cmd10_H_bit2.setChecked(false);
                                sms_cmd10_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd10_H_bit0.setChecked(false);
                                sms_cmd10_H_bit1.setChecked(false);
                                sms_cmd10_H_bit2.setChecked(true);
                                sms_cmd10_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd10_H_bit0.setChecked(true);
                                sms_cmd10_H_bit1.setChecked(false);
                                sms_cmd10_H_bit2.setChecked(true);
                                sms_cmd10_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd10_H_bit0.setChecked(false);
                                sms_cmd10_H_bit1.setChecked(true);
                                sms_cmd10_H_bit2.setChecked(true);
                                sms_cmd10_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd10_H_bit0.setChecked(true);
                                sms_cmd10_H_bit1.setChecked(true);
                                sms_cmd10_H_bit2.setChecked(true);
                                sms_cmd10_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd10_H_bit0.setChecked(false);
                                sms_cmd10_H_bit1.setChecked(false);
                                sms_cmd10_H_bit2.setChecked(false);
                                sms_cmd10_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd10_H_bit0.setChecked(true);
                                sms_cmd10_H_bit1.setChecked(false);
                                sms_cmd10_H_bit2.setChecked(false);
                                sms_cmd10_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd10_H_bit0.setChecked(false);
                                sms_cmd10_H_bit1.setChecked(true);
                                sms_cmd10_H_bit2.setChecked(false);
                                sms_cmd10_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd10_H_bit0.setChecked(true);
                                sms_cmd10_H_bit1.setChecked(true);
                                sms_cmd10_H_bit2.setChecked(false);
                                sms_cmd10_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd10_H_bit0.setChecked(false);
                                sms_cmd10_H_bit1.setChecked(false);
                                sms_cmd10_H_bit2.setChecked(true);
                                sms_cmd10_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd10_H_bit0.setChecked(true);
                                sms_cmd10_H_bit1.setChecked(false);
                                sms_cmd10_H_bit2.setChecked(true);
                                sms_cmd10_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd10_H_bit0.setChecked(false);
                                sms_cmd10_H_bit1.setChecked(true);
                                sms_cmd10_H_bit2.setChecked(true);
                                sms_cmd10_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd10_H_bit0.setChecked(true);
                                sms_cmd10_H_bit1.setChecked(true);
                                sms_cmd10_H_bit2.setChecked(true);
                                sms_cmd10_H_bit3.setChecked(true);
                            }
                            *//***************************** END OF THIRD CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd10_H_bit4.setChecked(false);
                                sms_cmd10_H_bit5.setChecked(false);
                                sms_cmd10_H_bit6.setChecked(false);
                                sms_cmd10_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd10_H_bit4.setChecked(true);
                                sms_cmd10_H_bit5.setChecked(false);
                                sms_cmd10_H_bit6.setChecked(false);
                                sms_cmd10_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd10_H_bit4.setChecked(false);
                                sms_cmd10_H_bit5.setChecked(true);
                                sms_cmd10_H_bit6.setChecked(false);
                                sms_cmd10_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd10_H_bit4.setChecked(true);
                                sms_cmd10_H_bit5.setChecked(true);
                                sms_cmd10_H_bit6.setChecked(false);
                                sms_cmd10_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd10_H_bit4.setChecked(false);
                                sms_cmd10_H_bit5.setChecked(false);
                                sms_cmd10_H_bit6.setChecked(true);
                                sms_cmd10_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd10_H_bit4.setChecked(true);
                                sms_cmd10_H_bit5.setChecked(false);
                                sms_cmd10_H_bit6.setChecked(true);
                                sms_cmd10_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd10_H_bit4.setChecked(false);
                                sms_cmd10_H_bit5.setChecked(true);
                                sms_cmd10_H_bit6.setChecked(true);
                                sms_cmd10_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd10_H_bit4.setChecked(true);
                                sms_cmd10_H_bit5.setChecked(true);
                                sms_cmd10_H_bit6.setChecked(true);
                                sms_cmd10_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd10_H_bit4.setChecked(false);
                                sms_cmd10_H_bit5.setChecked(false);
                                sms_cmd10_H_bit6.setChecked(false);
                                sms_cmd10_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd10_H_bit4.setChecked(true);
                                sms_cmd10_H_bit5.setChecked(false);
                                sms_cmd10_H_bit6.setChecked(false);
                                sms_cmd10_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd10_H_bit4.setChecked(false);
                                sms_cmd10_H_bit5.setChecked(true);
                                sms_cmd10_H_bit6.setChecked(false);
                                sms_cmd10_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd10_H_bit4.setChecked(true);
                                sms_cmd10_H_bit5.setChecked(true);
                                sms_cmd10_H_bit6.setChecked(false);
                                sms_cmd10_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd10_H_bit4.setChecked(false);
                                sms_cmd10_H_bit5.setChecked(false);
                                sms_cmd10_H_bit6.setChecked(true);
                                sms_cmd10_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd10_H_bit4.setChecked(true);
                                sms_cmd10_H_bit5.setChecked(false);
                                sms_cmd10_H_bit6.setChecked(true);
                                sms_cmd10_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd10_H_bit4.setChecked(false);
                                sms_cmd10_H_bit5.setChecked(true);
                                sms_cmd10_H_bit6.setChecked(true);
                                sms_cmd10_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd10_H_bit4.setChecked(true);
                                sms_cmd10_H_bit5.setChecked(true);
                                sms_cmd10_H_bit6.setChecked(true);
                                sms_cmd10_H_bit7.setChecked(true);
                            }
                            *//***************************** END OF FOURTH CHARACTER FROM RIGHT **************************//*
                        }

                        //DISPLAY FOR CBC OUTPUTS//
                        if(String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("1"))
                        {
                            *//******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd11_L_bit0.setChecked(false);
                                sms_cmd11_L_bit1.setChecked(false);
                                sms_cmd11_L_bit2.setChecked(false);
                                sms_cmd11_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd11_L_bit0.setChecked(true);
                                sms_cmd11_L_bit1.setChecked(false);
                                sms_cmd11_L_bit2.setChecked(false);
                                sms_cmd11_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd11_L_bit0.setChecked(false);
                                sms_cmd11_L_bit1.setChecked(true);
                                sms_cmd11_L_bit2.setChecked(false);
                                sms_cmd11_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd11_L_bit0.setChecked(true);
                                sms_cmd11_L_bit1.setChecked(true);
                                sms_cmd11_L_bit2.setChecked(false);
                                sms_cmd11_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd11_L_bit0.setChecked(false);
                                sms_cmd11_L_bit1.setChecked(false);
                                sms_cmd11_L_bit2.setChecked(true);
                                sms_cmd11_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd11_L_bit0.setChecked(true);
                                sms_cmd11_L_bit1.setChecked(false);
                                sms_cmd11_L_bit2.setChecked(true);
                                sms_cmd11_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd11_L_bit0.setChecked(false);
                                sms_cmd11_L_bit1.setChecked(true);
                                sms_cmd11_L_bit2.setChecked(true);
                                sms_cmd11_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd11_L_bit0.setChecked(true);
                                sms_cmd11_L_bit1.setChecked(true);
                                sms_cmd11_L_bit2.setChecked(true);
                                sms_cmd11_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd11_L_bit0.setChecked(false);
                                sms_cmd11_L_bit1.setChecked(false);
                                sms_cmd11_L_bit2.setChecked(false);
                                sms_cmd11_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd11_L_bit0.setChecked(true);
                                sms_cmd11_L_bit1.setChecked(false);
                                sms_cmd11_L_bit2.setChecked(false);
                                sms_cmd11_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd11_L_bit0.setChecked(false);
                                sms_cmd11_L_bit1.setChecked(true);
                                sms_cmd11_L_bit2.setChecked(false);
                                sms_cmd11_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd11_L_bit0.setChecked(true);
                                sms_cmd11_L_bit1.setChecked(true);
                                sms_cmd11_L_bit2.setChecked(false);
                                sms_cmd11_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd11_L_bit0.setChecked(false);
                                sms_cmd11_L_bit1.setChecked(false);
                                sms_cmd11_L_bit2.setChecked(true);
                                sms_cmd11_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd11_L_bit0.setChecked(true);
                                sms_cmd11_L_bit1.setChecked(false);
                                sms_cmd11_L_bit2.setChecked(true);
                                sms_cmd11_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd11_L_bit0.setChecked(false);
                                sms_cmd11_L_bit1.setChecked(true);
                                sms_cmd11_L_bit2.setChecked(true);
                                sms_cmd11_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd11_L_bit0.setChecked(true);
                                sms_cmd11_L_bit1.setChecked(true);
                                sms_cmd11_L_bit2.setChecked(true);
                                sms_cmd11_L_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIRST CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd11_L_bit4.setChecked(false);
                                sms_cmd11_L_bit5.setChecked(false);
                                sms_cmd11_L_bit6.setChecked(false);
                                sms_cmd11_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd11_L_bit4.setChecked(true);
                                sms_cmd11_L_bit5.setChecked(false);
                                sms_cmd11_L_bit6.setChecked(false);
                                sms_cmd11_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd11_L_bit4.setChecked(false);
                                sms_cmd11_L_bit5.setChecked(true);
                                sms_cmd11_L_bit6.setChecked(false);
                                sms_cmd11_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd11_L_bit4.setChecked(true);
                                sms_cmd11_L_bit5.setChecked(true);
                                sms_cmd11_L_bit6.setChecked(false);
                                sms_cmd11_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd11_L_bit4.setChecked(false);
                                sms_cmd11_L_bit5.setChecked(false);
                                sms_cmd11_L_bit6.setChecked(true);
                                sms_cmd11_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd11_L_bit4.setChecked(true);
                                sms_cmd11_L_bit5.setChecked(false);
                                sms_cmd11_L_bit6.setChecked(true);
                                sms_cmd11_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd11_L_bit4.setChecked(false);
                                sms_cmd11_L_bit5.setChecked(true);
                                sms_cmd11_L_bit6.setChecked(true);
                                sms_cmd11_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd11_L_bit4.setChecked(true);
                                sms_cmd11_L_bit5.setChecked(true);
                                sms_cmd11_L_bit6.setChecked(true);
                                sms_cmd11_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd11_L_bit4.setChecked(false);
                                sms_cmd11_L_bit5.setChecked(false);
                                sms_cmd11_L_bit6.setChecked(false);
                                sms_cmd11_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd11_L_bit4.setChecked(true);
                                sms_cmd11_L_bit5.setChecked(false);
                                sms_cmd11_L_bit6.setChecked(false);
                                sms_cmd11_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd11_L_bit4.setChecked(false);
                                sms_cmd11_L_bit5.setChecked(true);
                                sms_cmd11_L_bit6.setChecked(false);
                                sms_cmd11_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd11_L_bit4.setChecked(true);
                                sms_cmd11_L_bit5.setChecked(true);
                                sms_cmd11_L_bit6.setChecked(false);
                                sms_cmd11_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd11_L_bit4.setChecked(false);
                                sms_cmd11_L_bit5.setChecked(false);
                                sms_cmd11_L_bit6.setChecked(true);
                                sms_cmd11_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd11_L_bit4.setChecked(true);
                                sms_cmd11_L_bit5.setChecked(false);
                                sms_cmd11_L_bit6.setChecked(true);
                                sms_cmd11_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd11_L_bit4.setChecked(false);
                                sms_cmd11_L_bit5.setChecked(true);
                                sms_cmd11_L_bit6.setChecked(true);
                                sms_cmd11_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd11_L_bit4.setChecked(true);
                                sms_cmd11_L_bit5.setChecked(true);
                                sms_cmd11_L_bit6.setChecked(true);
                                sms_cmd11_L_bit7.setChecked(true);
                            }
                            *//***************************** END OF SECOND CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd11_H_bit0.setChecked(false);
                                sms_cmd11_H_bit1.setChecked(false);
                                sms_cmd11_H_bit2.setChecked(false);
                                sms_cmd11_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd11_H_bit0.setChecked(true);
                                sms_cmd11_H_bit1.setChecked(false);
                                sms_cmd11_H_bit2.setChecked(false);
                                sms_cmd11_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd11_H_bit0.setChecked(false);
                                sms_cmd11_H_bit1.setChecked(true);
                                sms_cmd11_H_bit2.setChecked(false);
                                sms_cmd11_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd11_H_bit0.setChecked(true);
                                sms_cmd11_H_bit1.setChecked(true);
                                sms_cmd11_H_bit2.setChecked(false);
                                sms_cmd11_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd11_H_bit0.setChecked(false);
                                sms_cmd11_H_bit1.setChecked(false);
                                sms_cmd11_H_bit2.setChecked(true);
                                sms_cmd11_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd11_H_bit0.setChecked(true);
                                sms_cmd11_H_bit1.setChecked(false);
                                sms_cmd11_H_bit2.setChecked(true);
                                sms_cmd11_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd11_H_bit0.setChecked(false);
                                sms_cmd11_H_bit1.setChecked(true);
                                sms_cmd11_H_bit2.setChecked(true);
                                sms_cmd11_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd11_H_bit0.setChecked(true);
                                sms_cmd11_H_bit1.setChecked(true);
                                sms_cmd11_H_bit2.setChecked(true);
                                sms_cmd11_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd11_H_bit0.setChecked(false);
                                sms_cmd11_H_bit1.setChecked(false);
                                sms_cmd11_H_bit2.setChecked(false);
                                sms_cmd11_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd11_H_bit0.setChecked(true);
                                sms_cmd11_H_bit1.setChecked(false);
                                sms_cmd11_H_bit2.setChecked(false);
                                sms_cmd11_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd11_H_bit0.setChecked(false);
                                sms_cmd11_H_bit1.setChecked(true);
                                sms_cmd11_H_bit2.setChecked(false);
                                sms_cmd11_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd11_H_bit0.setChecked(true);
                                sms_cmd11_H_bit1.setChecked(true);
                                sms_cmd11_H_bit2.setChecked(false);
                                sms_cmd11_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd11_H_bit0.setChecked(false);
                                sms_cmd11_H_bit1.setChecked(false);
                                sms_cmd11_H_bit2.setChecked(true);
                                sms_cmd11_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd11_H_bit0.setChecked(true);
                                sms_cmd11_H_bit1.setChecked(false);
                                sms_cmd11_H_bit2.setChecked(true);
                                sms_cmd11_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd11_H_bit0.setChecked(false);
                                sms_cmd11_H_bit1.setChecked(true);
                                sms_cmd11_H_bit2.setChecked(true);
                                sms_cmd11_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd11_H_bit0.setChecked(true);
                                sms_cmd11_H_bit1.setChecked(true);
                                sms_cmd11_H_bit2.setChecked(true);
                                sms_cmd11_H_bit3.setChecked(true);
                            }
                            *//***************************** END OF THIRD CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd11_H_bit4.setChecked(false);
                                sms_cmd11_H_bit5.setChecked(false);
                                sms_cmd11_H_bit6.setChecked(false);
                                sms_cmd11_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd11_H_bit4.setChecked(true);
                                sms_cmd11_H_bit5.setChecked(false);
                                sms_cmd11_H_bit6.setChecked(false);
                                sms_cmd11_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd11_H_bit4.setChecked(false);
                                sms_cmd11_H_bit5.setChecked(true);
                                sms_cmd11_H_bit6.setChecked(false);
                                sms_cmd11_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd11_H_bit4.setChecked(true);
                                sms_cmd11_H_bit5.setChecked(true);
                                sms_cmd11_H_bit6.setChecked(false);
                                sms_cmd11_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd11_H_bit4.setChecked(false);
                                sms_cmd11_H_bit5.setChecked(false);
                                sms_cmd11_H_bit6.setChecked(true);
                                sms_cmd11_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd11_H_bit4.setChecked(true);
                                sms_cmd11_H_bit5.setChecked(false);
                                sms_cmd11_H_bit6.setChecked(true);
                                sms_cmd11_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd11_H_bit4.setChecked(false);
                                sms_cmd11_H_bit5.setChecked(true);
                                sms_cmd11_H_bit6.setChecked(true);
                                sms_cmd11_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd11_H_bit4.setChecked(true);
                                sms_cmd11_H_bit5.setChecked(true);
                                sms_cmd11_H_bit6.setChecked(true);
                                sms_cmd11_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd11_H_bit4.setChecked(false);
                                sms_cmd11_H_bit5.setChecked(false);
                                sms_cmd11_H_bit6.setChecked(false);
                                sms_cmd11_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd11_H_bit4.setChecked(true);
                                sms_cmd11_H_bit5.setChecked(false);
                                sms_cmd11_H_bit6.setChecked(false);
                                sms_cmd11_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd11_H_bit4.setChecked(false);
                                sms_cmd11_H_bit5.setChecked(true);
                                sms_cmd11_H_bit6.setChecked(false);
                                sms_cmd11_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd11_H_bit4.setChecked(true);
                                sms_cmd11_H_bit5.setChecked(true);
                                sms_cmd11_H_bit6.setChecked(false);
                                sms_cmd11_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd11_H_bit4.setChecked(false);
                                sms_cmd11_H_bit5.setChecked(false);
                                sms_cmd11_H_bit6.setChecked(true);
                                sms_cmd11_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd11_H_bit4.setChecked(true);
                                sms_cmd11_H_bit5.setChecked(false);
                                sms_cmd11_H_bit6.setChecked(true);
                                sms_cmd11_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd11_H_bit4.setChecked(false);
                                sms_cmd11_H_bit5.setChecked(true);
                                sms_cmd11_H_bit6.setChecked(true);
                                sms_cmd11_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd11_H_bit4.setChecked(true);
                                sms_cmd11_H_bit5.setChecked(true);
                                sms_cmd11_H_bit6.setChecked(true);
                                sms_cmd11_H_bit7.setChecked(true);
                            }
                            *//***************************** END OF FOURTH CHARACTER FROM RIGHT **************************//*
                        }

                        //DISPLAY FOR FLOOR UP KEYS//
                        if(String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("2"))
                        {
                            *//******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd12_L_bit0.setChecked(false);
                                sms_cmd12_L_bit1.setChecked(false);
                                sms_cmd12_L_bit2.setChecked(false);
                                sms_cmd12_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd12_L_bit0.setChecked(true);
                                sms_cmd12_L_bit1.setChecked(false);
                                sms_cmd12_L_bit2.setChecked(false);
                                sms_cmd12_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd12_L_bit0.setChecked(false);
                                sms_cmd12_L_bit1.setChecked(true);
                                sms_cmd12_L_bit2.setChecked(false);
                                sms_cmd12_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd12_L_bit0.setChecked(true);
                                sms_cmd12_L_bit1.setChecked(true);
                                sms_cmd12_L_bit2.setChecked(false);
                                sms_cmd12_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd12_L_bit0.setChecked(false);
                                sms_cmd12_L_bit1.setChecked(false);
                                sms_cmd12_L_bit2.setChecked(true);
                                sms_cmd12_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd12_L_bit0.setChecked(true);
                                sms_cmd12_L_bit1.setChecked(false);
                                sms_cmd12_L_bit2.setChecked(true);
                                sms_cmd12_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd12_L_bit0.setChecked(false);
                                sms_cmd12_L_bit1.setChecked(true);
                                sms_cmd12_L_bit2.setChecked(true);
                                sms_cmd12_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd12_L_bit0.setChecked(true);
                                sms_cmd12_L_bit1.setChecked(true);
                                sms_cmd12_L_bit2.setChecked(true);
                                sms_cmd12_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd12_L_bit0.setChecked(false);
                                sms_cmd12_L_bit1.setChecked(false);
                                sms_cmd12_L_bit2.setChecked(false);
                                sms_cmd12_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd12_L_bit0.setChecked(true);
                                sms_cmd12_L_bit1.setChecked(false);
                                sms_cmd12_L_bit2.setChecked(false);
                                sms_cmd12_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd12_L_bit0.setChecked(false);
                                sms_cmd12_L_bit1.setChecked(true);
                                sms_cmd12_L_bit2.setChecked(false);
                                sms_cmd12_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd12_L_bit0.setChecked(true);
                                sms_cmd12_L_bit1.setChecked(true);
                                sms_cmd12_L_bit2.setChecked(false);
                                sms_cmd12_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd12_L_bit0.setChecked(false);
                                sms_cmd12_L_bit1.setChecked(false);
                                sms_cmd12_L_bit2.setChecked(true);
                                sms_cmd12_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd12_L_bit0.setChecked(true);
                                sms_cmd12_L_bit1.setChecked(false);
                                sms_cmd12_L_bit2.setChecked(true);
                                sms_cmd12_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd12_L_bit0.setChecked(false);
                                sms_cmd12_L_bit1.setChecked(true);
                                sms_cmd12_L_bit2.setChecked(true);
                                sms_cmd12_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd12_L_bit0.setChecked(true);
                                sms_cmd12_L_bit1.setChecked(true);
                                sms_cmd12_L_bit2.setChecked(true);
                                sms_cmd12_L_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIRST CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd12_L_bit4.setChecked(false);
                                sms_cmd12_L_bit5.setChecked(false);
                                sms_cmd12_L_bit6.setChecked(false);
                                sms_cmd12_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd12_L_bit4.setChecked(true);
                                sms_cmd12_L_bit5.setChecked(false);
                                sms_cmd12_L_bit6.setChecked(false);
                                sms_cmd12_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd12_L_bit4.setChecked(false);
                                sms_cmd12_L_bit5.setChecked(true);
                                sms_cmd12_L_bit6.setChecked(false);
                                sms_cmd12_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd12_L_bit4.setChecked(true);
                                sms_cmd12_L_bit5.setChecked(true);
                                sms_cmd12_L_bit6.setChecked(false);
                                sms_cmd12_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd12_L_bit4.setChecked(false);
                                sms_cmd12_L_bit5.setChecked(false);
                                sms_cmd12_L_bit6.setChecked(true);
                                sms_cmd12_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd12_L_bit4.setChecked(true);
                                sms_cmd12_L_bit5.setChecked(false);
                                sms_cmd12_L_bit6.setChecked(true);
                                sms_cmd12_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd12_L_bit4.setChecked(false);
                                sms_cmd12_L_bit5.setChecked(true);
                                sms_cmd12_L_bit6.setChecked(true);
                                sms_cmd12_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd12_L_bit4.setChecked(true);
                                sms_cmd12_L_bit5.setChecked(true);
                                sms_cmd12_L_bit6.setChecked(true);
                                sms_cmd12_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd12_L_bit4.setChecked(false);
                                sms_cmd12_L_bit5.setChecked(false);
                                sms_cmd12_L_bit6.setChecked(false);
                                sms_cmd12_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd12_L_bit4.setChecked(true);
                                sms_cmd12_L_bit5.setChecked(false);
                                sms_cmd12_L_bit6.setChecked(false);
                                sms_cmd12_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd12_L_bit4.setChecked(false);
                                sms_cmd12_L_bit5.setChecked(true);
                                sms_cmd12_L_bit6.setChecked(false);
                                sms_cmd12_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd12_L_bit4.setChecked(true);
                                sms_cmd12_L_bit5.setChecked(true);
                                sms_cmd12_L_bit6.setChecked(false);
                                sms_cmd12_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd12_L_bit4.setChecked(false);
                                sms_cmd12_L_bit5.setChecked(false);
                                sms_cmd12_L_bit6.setChecked(true);
                                sms_cmd12_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd12_L_bit4.setChecked(true);
                                sms_cmd12_L_bit5.setChecked(false);
                                sms_cmd12_L_bit6.setChecked(true);
                                sms_cmd12_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd12_L_bit4.setChecked(false);
                                sms_cmd12_L_bit5.setChecked(true);
                                sms_cmd12_L_bit6.setChecked(true);
                                sms_cmd12_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd12_L_bit4.setChecked(true);
                                sms_cmd12_L_bit5.setChecked(true);
                                sms_cmd12_L_bit6.setChecked(true);
                                sms_cmd12_L_bit7.setChecked(true);
                            }
                            *//***************************** END OF SECOND CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd12_H_bit0.setChecked(false);
                                sms_cmd12_H_bit1.setChecked(false);
                                sms_cmd12_H_bit2.setChecked(false);
                                sms_cmd12_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd12_H_bit0.setChecked(true);
                                sms_cmd12_H_bit1.setChecked(false);
                                sms_cmd12_H_bit2.setChecked(false);
                                sms_cmd12_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd12_H_bit0.setChecked(false);
                                sms_cmd12_H_bit1.setChecked(true);
                                sms_cmd12_H_bit2.setChecked(false);
                                sms_cmd12_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd12_H_bit0.setChecked(true);
                                sms_cmd12_H_bit1.setChecked(true);
                                sms_cmd12_H_bit2.setChecked(false);
                                sms_cmd12_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd12_H_bit0.setChecked(false);
                                sms_cmd12_H_bit1.setChecked(false);
                                sms_cmd12_H_bit2.setChecked(true);
                                sms_cmd12_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd12_H_bit0.setChecked(true);
                                sms_cmd12_H_bit1.setChecked(false);
                                sms_cmd12_H_bit2.setChecked(true);
                                sms_cmd12_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd12_H_bit0.setChecked(false);
                                sms_cmd12_H_bit1.setChecked(true);
                                sms_cmd12_H_bit2.setChecked(true);
                                sms_cmd12_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd12_H_bit0.setChecked(true);
                                sms_cmd12_H_bit1.setChecked(true);
                                sms_cmd12_H_bit2.setChecked(true);
                                sms_cmd12_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd12_H_bit0.setChecked(false);
                                sms_cmd12_H_bit1.setChecked(false);
                                sms_cmd12_H_bit2.setChecked(false);
                                sms_cmd12_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd12_H_bit0.setChecked(true);
                                sms_cmd12_H_bit1.setChecked(false);
                                sms_cmd12_H_bit2.setChecked(false);
                                sms_cmd12_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd12_H_bit0.setChecked(false);
                                sms_cmd12_H_bit1.setChecked(true);
                                sms_cmd12_H_bit2.setChecked(false);
                                sms_cmd12_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd12_H_bit0.setChecked(true);
                                sms_cmd12_H_bit1.setChecked(true);
                                sms_cmd12_H_bit2.setChecked(false);
                                sms_cmd12_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd12_H_bit0.setChecked(false);
                                sms_cmd12_H_bit1.setChecked(false);
                                sms_cmd12_H_bit2.setChecked(true);
                                sms_cmd12_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd12_H_bit0.setChecked(true);
                                sms_cmd12_H_bit1.setChecked(false);
                                sms_cmd12_H_bit2.setChecked(true);
                                sms_cmd12_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd12_H_bit0.setChecked(false);
                                sms_cmd12_H_bit1.setChecked(true);
                                sms_cmd12_H_bit2.setChecked(true);
                                sms_cmd12_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd12_H_bit0.setChecked(true);
                                sms_cmd12_H_bit1.setChecked(true);
                                sms_cmd12_H_bit2.setChecked(true);
                                sms_cmd12_H_bit3.setChecked(true);
                            }
                            *//***************************** END OF THIRD CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd12_H_bit4.setChecked(false);
                                sms_cmd12_H_bit5.setChecked(false);
                                sms_cmd12_H_bit6.setChecked(false);
                                sms_cmd12_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd12_H_bit4.setChecked(true);
                                sms_cmd12_H_bit5.setChecked(false);
                                sms_cmd12_H_bit6.setChecked(false);
                                sms_cmd12_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd12_H_bit4.setChecked(false);
                                sms_cmd12_H_bit5.setChecked(true);
                                sms_cmd12_H_bit6.setChecked(false);
                                sms_cmd12_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd12_H_bit4.setChecked(true);
                                sms_cmd12_H_bit5.setChecked(true);
                                sms_cmd12_H_bit6.setChecked(false);
                                sms_cmd12_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd12_H_bit4.setChecked(false);
                                sms_cmd12_H_bit5.setChecked(false);
                                sms_cmd12_H_bit6.setChecked(true);
                                sms_cmd12_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd12_H_bit4.setChecked(true);
                                sms_cmd12_H_bit5.setChecked(false);
                                sms_cmd12_H_bit6.setChecked(true);
                                sms_cmd12_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd12_H_bit4.setChecked(false);
                                sms_cmd12_H_bit5.setChecked(true);
                                sms_cmd12_H_bit6.setChecked(true);
                                sms_cmd12_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd12_H_bit4.setChecked(true);
                                sms_cmd12_H_bit5.setChecked(true);
                                sms_cmd12_H_bit6.setChecked(true);
                                sms_cmd12_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd12_H_bit4.setChecked(false);
                                sms_cmd12_H_bit5.setChecked(false);
                                sms_cmd12_H_bit6.setChecked(false);
                                sms_cmd12_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd12_H_bit4.setChecked(true);
                                sms_cmd12_H_bit5.setChecked(false);
                                sms_cmd12_H_bit6.setChecked(false);
                                sms_cmd12_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd12_H_bit4.setChecked(false);
                                sms_cmd12_H_bit5.setChecked(true);
                                sms_cmd12_H_bit6.setChecked(false);
                                sms_cmd12_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd12_H_bit4.setChecked(true);
                                sms_cmd12_H_bit5.setChecked(true);
                                sms_cmd12_H_bit6.setChecked(false);
                                sms_cmd12_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd12_H_bit4.setChecked(false);
                                sms_cmd12_H_bit5.setChecked(false);
                                sms_cmd12_H_bit6.setChecked(true);
                                sms_cmd12_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd12_H_bit4.setChecked(true);
                                sms_cmd12_H_bit5.setChecked(false);
                                sms_cmd12_H_bit6.setChecked(true);
                                sms_cmd12_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd12_H_bit4.setChecked(false);
                                sms_cmd12_H_bit5.setChecked(true);
                                sms_cmd12_H_bit6.setChecked(true);
                                sms_cmd12_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd12_H_bit4.setChecked(true);
                                sms_cmd12_H_bit5.setChecked(true);
                                sms_cmd12_H_bit6.setChecked(true);
                                sms_cmd12_H_bit7.setChecked(true);
                            }
                            *//***************************** END OF FOURTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FIFTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd12_L_M_bit0.setChecked(false);
                                sms_cmd12_L_M_bit1.setChecked(false);
                                sms_cmd12_L_M_bit2.setChecked(false);
                                sms_cmd12_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd12_L_M_bit0.setChecked(true);
                                sms_cmd12_L_M_bit1.setChecked(false);
                                sms_cmd12_L_M_bit2.setChecked(false);
                                sms_cmd12_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd12_L_M_bit0.setChecked(false);
                                sms_cmd12_L_M_bit1.setChecked(true);
                                sms_cmd12_L_M_bit2.setChecked(false);
                                sms_cmd12_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd12_L_M_bit0.setChecked(true);
                                sms_cmd12_L_M_bit1.setChecked(true);
                                sms_cmd12_L_M_bit2.setChecked(false);
                                sms_cmd12_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd12_L_M_bit0.setChecked(false);
                                sms_cmd12_L_M_bit1.setChecked(false);
                                sms_cmd12_L_M_bit2.setChecked(true);
                                sms_cmd12_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd12_L_M_bit0.setChecked(true);
                                sms_cmd12_L_M_bit1.setChecked(false);
                                sms_cmd12_L_M_bit2.setChecked(true);
                                sms_cmd12_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd12_L_M_bit0.setChecked(false);
                                sms_cmd12_L_M_bit1.setChecked(true);
                                sms_cmd12_L_M_bit2.setChecked(true);
                                sms_cmd12_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd12_L_M_bit0.setChecked(true);
                                sms_cmd12_L_M_bit1.setChecked(true);
                                sms_cmd12_L_M_bit2.setChecked(true);
                                sms_cmd12_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd12_L_M_bit0.setChecked(false);
                                sms_cmd12_L_M_bit1.setChecked(false);
                                sms_cmd12_L_M_bit2.setChecked(false);
                                sms_cmd12_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd12_L_M_bit0.setChecked(true);
                                sms_cmd12_L_M_bit1.setChecked(false);
                                sms_cmd12_L_M_bit2.setChecked(false);
                                sms_cmd12_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd12_L_M_bit0.setChecked(false);
                                sms_cmd12_L_M_bit1.setChecked(true);
                                sms_cmd12_L_M_bit2.setChecked(false);
                                sms_cmd12_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd12_L_M_bit0.setChecked(true);
                                sms_cmd12_L_M_bit1.setChecked(true);
                                sms_cmd12_L_M_bit2.setChecked(false);
                                sms_cmd12_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd12_L_M_bit0.setChecked(false);
                                sms_cmd12_L_M_bit1.setChecked(false);
                                sms_cmd12_L_M_bit2.setChecked(true);
                                sms_cmd12_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd12_L_M_bit0.setChecked(true);
                                sms_cmd12_L_M_bit1.setChecked(false);
                                sms_cmd12_L_M_bit2.setChecked(true);
                                sms_cmd12_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd12_L_M_bit0.setChecked(false);
                                sms_cmd12_L_M_bit1.setChecked(true);
                                sms_cmd12_L_M_bit2.setChecked(true);
                                sms_cmd12_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd12_L_M_bit0.setChecked(true);
                                sms_cmd12_L_M_bit1.setChecked(true);
                                sms_cmd12_L_M_bit2.setChecked(true);
                                sms_cmd12_L_M_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIFTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SIXTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd12_L_M_bit4.setChecked(false);
                                sms_cmd12_L_M_bit5.setChecked(false);
                                sms_cmd12_L_M_bit6.setChecked(false);
                                sms_cmd12_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd12_L_M_bit4.setChecked(true);
                                sms_cmd12_L_M_bit5.setChecked(false);
                                sms_cmd12_L_M_bit6.setChecked(false);
                                sms_cmd12_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd12_L_M_bit4.setChecked(false);
                                sms_cmd12_L_M_bit5.setChecked(true);
                                sms_cmd12_L_M_bit6.setChecked(false);
                                sms_cmd12_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd12_L_M_bit4.setChecked(true);
                                sms_cmd12_L_M_bit5.setChecked(true);
                                sms_cmd12_L_M_bit6.setChecked(false);
                                sms_cmd12_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd12_L_M_bit4.setChecked(false);
                                sms_cmd12_L_M_bit5.setChecked(false);
                                sms_cmd12_L_M_bit6.setChecked(true);
                                sms_cmd12_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd12_L_M_bit4.setChecked(true);
                                sms_cmd12_L_M_bit5.setChecked(false);
                                sms_cmd12_L_M_bit6.setChecked(true);
                                sms_cmd12_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd12_L_M_bit4.setChecked(false);
                                sms_cmd12_L_M_bit5.setChecked(true);
                                sms_cmd12_L_M_bit6.setChecked(true);
                                sms_cmd12_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd12_L_M_bit4.setChecked(true);
                                sms_cmd12_L_M_bit5.setChecked(true);
                                sms_cmd12_L_M_bit6.setChecked(true);
                                sms_cmd12_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd12_L_M_bit4.setChecked(false);
                                sms_cmd12_L_M_bit5.setChecked(false);
                                sms_cmd12_L_M_bit6.setChecked(false);
                                sms_cmd12_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd12_L_M_bit4.setChecked(true);
                                sms_cmd12_L_M_bit5.setChecked(false);
                                sms_cmd12_L_M_bit6.setChecked(false);
                                sms_cmd12_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd12_L_M_bit4.setChecked(false);
                                sms_cmd12_L_M_bit5.setChecked(true);
                                sms_cmd12_L_M_bit6.setChecked(false);
                                sms_cmd12_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd12_L_M_bit4.setChecked(true);
                                sms_cmd12_L_M_bit5.setChecked(true);
                                sms_cmd12_L_M_bit6.setChecked(false);
                                sms_cmd12_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd12_L_M_bit4.setChecked(false);
                                sms_cmd12_L_M_bit5.setChecked(false);
                                sms_cmd12_L_M_bit6.setChecked(true);
                                sms_cmd12_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd12_L_M_bit4.setChecked(true);
                                sms_cmd12_L_M_bit5.setChecked(false);
                                sms_cmd12_L_M_bit6.setChecked(true);
                                sms_cmd12_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd12_L_M_bit4.setChecked(false);
                                sms_cmd12_L_M_bit5.setChecked(true);
                                sms_cmd12_L_M_bit6.setChecked(true);
                                sms_cmd12_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd12_L_M_bit4.setChecked(true);
                                sms_cmd12_L_M_bit5.setChecked(true);
                                sms_cmd12_L_M_bit6.setChecked(true);
                                sms_cmd12_L_M_bit7.setChecked(true);
                            }
                            *//***************************** END OF SIXTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SEVENTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd12_H_M_bit0.setChecked(false);
                                sms_cmd12_H_M_bit1.setChecked(false);
                                sms_cmd12_H_M_bit2.setChecked(false);
                                sms_cmd12_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd12_H_M_bit0.setChecked(true);
                                sms_cmd12_H_M_bit1.setChecked(false);
                                sms_cmd12_H_M_bit2.setChecked(false);
                                sms_cmd12_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd12_H_M_bit0.setChecked(false);
                                sms_cmd12_H_M_bit1.setChecked(true);
                                sms_cmd12_H_M_bit2.setChecked(false);
                                sms_cmd12_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd12_H_M_bit0.setChecked(true);
                                sms_cmd12_H_M_bit1.setChecked(true);
                                sms_cmd12_H_M_bit2.setChecked(false);
                                sms_cmd12_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd12_H_M_bit0.setChecked(false);
                                sms_cmd12_H_M_bit1.setChecked(false);
                                sms_cmd12_H_M_bit2.setChecked(true);
                                sms_cmd12_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd12_H_M_bit0.setChecked(true);
                                sms_cmd12_H_M_bit1.setChecked(false);
                                sms_cmd12_H_M_bit2.setChecked(true);
                                sms_cmd12_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd12_H_M_bit0.setChecked(false);
                                sms_cmd12_H_M_bit1.setChecked(true);
                                sms_cmd12_H_M_bit2.setChecked(true);
                                sms_cmd12_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd12_H_M_bit0.setChecked(true);
                                sms_cmd12_H_M_bit1.setChecked(true);
                                sms_cmd12_H_M_bit2.setChecked(true);
                                sms_cmd12_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd12_H_M_bit0.setChecked(false);
                                sms_cmd12_H_M_bit1.setChecked(false);
                                sms_cmd12_H_M_bit2.setChecked(false);
                                sms_cmd12_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd12_H_M_bit0.setChecked(true);
                                sms_cmd12_H_M_bit1.setChecked(false);
                                sms_cmd12_H_M_bit2.setChecked(false);
                                sms_cmd12_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd12_H_M_bit0.setChecked(false);
                                sms_cmd12_H_M_bit1.setChecked(true);
                                sms_cmd12_H_M_bit2.setChecked(false);
                                sms_cmd12_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd12_H_M_bit0.setChecked(true);
                                sms_cmd12_H_M_bit1.setChecked(true);
                                sms_cmd12_H_M_bit2.setChecked(false);
                                sms_cmd12_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd12_H_M_bit0.setChecked(false);
                                sms_cmd12_H_M_bit1.setChecked(false);
                                sms_cmd12_H_M_bit2.setChecked(true);
                                sms_cmd12_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd12_H_M_bit0.setChecked(true);
                                sms_cmd12_H_M_bit1.setChecked(false);
                                sms_cmd12_H_M_bit2.setChecked(true);
                                sms_cmd12_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd12_H_M_bit0.setChecked(false);
                                sms_cmd12_H_M_bit1.setChecked(true);
                                sms_cmd12_H_M_bit2.setChecked(true);
                                sms_cmd12_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd12_H_M_bit0.setChecked(true);
                                sms_cmd12_H_M_bit1.setChecked(true);
                                sms_cmd12_H_M_bit2.setChecked(true);
                                sms_cmd12_H_M_bit3.setChecked(true);
                            }
                            *//***************************** END OF SEVENTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON EIGHTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd12_H_M_bit4.setChecked(false);
                                sms_cmd12_H_M_bit5.setChecked(false);
                                sms_cmd12_H_M_bit6.setChecked(false);
                                sms_cmd12_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd12_H_M_bit4.setChecked(true);
                                sms_cmd12_H_M_bit5.setChecked(false);
                                sms_cmd12_H_M_bit6.setChecked(false);
                                sms_cmd12_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd12_H_M_bit4.setChecked(false);
                                sms_cmd12_H_M_bit5.setChecked(true);
                                sms_cmd12_H_M_bit6.setChecked(false);
                                sms_cmd12_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd12_H_M_bit4.setChecked(true);
                                sms_cmd12_H_M_bit5.setChecked(true);
                                sms_cmd12_H_M_bit6.setChecked(false);
                                sms_cmd12_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd12_H_M_bit4.setChecked(false);
                                sms_cmd12_H_M_bit5.setChecked(false);
                                sms_cmd12_H_M_bit6.setChecked(true);
                                sms_cmd12_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd12_H_M_bit4.setChecked(true);
                                sms_cmd12_H_M_bit5.setChecked(false);
                                sms_cmd12_H_M_bit6.setChecked(true);
                                sms_cmd12_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd12_H_M_bit4.setChecked(false);
                                sms_cmd12_H_M_bit5.setChecked(true);
                                sms_cmd12_H_M_bit6.setChecked(true);
                                sms_cmd12_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd12_H_M_bit4.setChecked(true);
                                sms_cmd12_H_M_bit5.setChecked(true);
                                sms_cmd12_H_M_bit6.setChecked(true);
                                sms_cmd12_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd12_H_M_bit4.setChecked(false);
                                sms_cmd12_H_M_bit5.setChecked(false);
                                sms_cmd12_H_M_bit6.setChecked(false);
                                sms_cmd12_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd12_H_M_bit4.setChecked(true);
                                sms_cmd12_H_M_bit5.setChecked(false);
                                sms_cmd12_H_M_bit6.setChecked(false);
                                sms_cmd12_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd12_H_M_bit4.setChecked(false);
                                sms_cmd12_H_M_bit5.setChecked(true);
                                sms_cmd12_H_M_bit6.setChecked(false);
                                sms_cmd12_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd12_H_M_bit4.setChecked(true);
                                sms_cmd12_H_M_bit5.setChecked(true);
                                sms_cmd12_H_M_bit6.setChecked(false);
                                sms_cmd12_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd12_H_M_bit4.setChecked(false);
                                sms_cmd12_H_M_bit5.setChecked(false);
                                sms_cmd12_H_M_bit6.setChecked(true);
                                sms_cmd12_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd12_H_M_bit4.setChecked(true);
                                sms_cmd12_H_M_bit5.setChecked(false);
                                sms_cmd12_H_M_bit6.setChecked(true);
                                sms_cmd12_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd12_H_M_bit4.setChecked(false);
                                sms_cmd12_H_M_bit5.setChecked(true);
                                sms_cmd12_H_M_bit6.setChecked(true);
                                sms_cmd12_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd12_H_M_bit4.setChecked(true);
                                sms_cmd12_H_M_bit5.setChecked(true);
                                sms_cmd12_H_M_bit6.setChecked(true);
                                sms_cmd12_H_M_bit7.setChecked(true);
                            }
                            *//***************************** END OF EIGHTH CHARACTER FROM RIGHT **************************//*
                        }

                        //DISPLAY FOR FLOOR DOWN KEYS//
                        if(String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("3"))
                        {
                            *//******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd13_L_bit0.setChecked(false);
                                sms_cmd13_L_bit1.setChecked(false);
                                sms_cmd13_L_bit2.setChecked(false);
                                sms_cmd13_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd13_L_bit0.setChecked(true);
                                sms_cmd13_L_bit1.setChecked(false);
                                sms_cmd13_L_bit2.setChecked(false);
                                sms_cmd13_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd13_L_bit0.setChecked(false);
                                sms_cmd13_L_bit1.setChecked(true);
                                sms_cmd13_L_bit2.setChecked(false);
                                sms_cmd13_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd13_L_bit0.setChecked(true);
                                sms_cmd13_L_bit1.setChecked(true);
                                sms_cmd13_L_bit2.setChecked(false);
                                sms_cmd13_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd13_L_bit0.setChecked(false);
                                sms_cmd13_L_bit1.setChecked(false);
                                sms_cmd13_L_bit2.setChecked(true);
                                sms_cmd13_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd13_L_bit0.setChecked(true);
                                sms_cmd13_L_bit1.setChecked(false);
                                sms_cmd13_L_bit2.setChecked(true);
                                sms_cmd13_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd13_L_bit0.setChecked(false);
                                sms_cmd13_L_bit1.setChecked(true);
                                sms_cmd13_L_bit2.setChecked(true);
                                sms_cmd13_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd13_L_bit0.setChecked(true);
                                sms_cmd13_L_bit1.setChecked(true);
                                sms_cmd13_L_bit2.setChecked(true);
                                sms_cmd13_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd13_L_bit0.setChecked(false);
                                sms_cmd13_L_bit1.setChecked(false);
                                sms_cmd13_L_bit2.setChecked(false);
                                sms_cmd13_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd13_L_bit0.setChecked(true);
                                sms_cmd13_L_bit1.setChecked(false);
                                sms_cmd13_L_bit2.setChecked(false);
                                sms_cmd13_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd13_L_bit0.setChecked(false);
                                sms_cmd13_L_bit1.setChecked(true);
                                sms_cmd13_L_bit2.setChecked(false);
                                sms_cmd13_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd13_L_bit0.setChecked(true);
                                sms_cmd13_L_bit1.setChecked(true);
                                sms_cmd13_L_bit2.setChecked(false);
                                sms_cmd13_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd13_L_bit0.setChecked(false);
                                sms_cmd13_L_bit1.setChecked(false);
                                sms_cmd13_L_bit2.setChecked(true);
                                sms_cmd13_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd13_L_bit0.setChecked(true);
                                sms_cmd13_L_bit1.setChecked(false);
                                sms_cmd13_L_bit2.setChecked(true);
                                sms_cmd13_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd13_L_bit0.setChecked(false);
                                sms_cmd13_L_bit1.setChecked(true);
                                sms_cmd13_L_bit2.setChecked(true);
                                sms_cmd13_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd13_L_bit0.setChecked(true);
                                sms_cmd13_L_bit1.setChecked(true);
                                sms_cmd13_L_bit2.setChecked(true);
                                sms_cmd13_L_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIRST CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd13_L_bit4.setChecked(false);
                                sms_cmd13_L_bit5.setChecked(false);
                                sms_cmd13_L_bit6.setChecked(false);
                                sms_cmd13_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd13_L_bit4.setChecked(true);
                                sms_cmd13_L_bit5.setChecked(false);
                                sms_cmd13_L_bit6.setChecked(false);
                                sms_cmd13_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd13_L_bit4.setChecked(false);
                                sms_cmd13_L_bit5.setChecked(true);
                                sms_cmd13_L_bit6.setChecked(false);
                                sms_cmd13_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd13_L_bit4.setChecked(true);
                                sms_cmd13_L_bit5.setChecked(true);
                                sms_cmd13_L_bit6.setChecked(false);
                                sms_cmd13_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd13_L_bit4.setChecked(false);
                                sms_cmd13_L_bit5.setChecked(false);
                                sms_cmd13_L_bit6.setChecked(true);
                                sms_cmd13_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd13_L_bit4.setChecked(true);
                                sms_cmd13_L_bit5.setChecked(false);
                                sms_cmd13_L_bit6.setChecked(true);
                                sms_cmd13_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd13_L_bit4.setChecked(false);
                                sms_cmd13_L_bit5.setChecked(true);
                                sms_cmd13_L_bit6.setChecked(true);
                                sms_cmd13_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd13_L_bit4.setChecked(true);
                                sms_cmd13_L_bit5.setChecked(true);
                                sms_cmd13_L_bit6.setChecked(true);
                                sms_cmd13_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd13_L_bit4.setChecked(false);
                                sms_cmd13_L_bit5.setChecked(false);
                                sms_cmd13_L_bit6.setChecked(false);
                                sms_cmd13_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd13_L_bit4.setChecked(true);
                                sms_cmd13_L_bit5.setChecked(false);
                                sms_cmd13_L_bit6.setChecked(false);
                                sms_cmd13_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd13_L_bit4.setChecked(false);
                                sms_cmd13_L_bit5.setChecked(true);
                                sms_cmd13_L_bit6.setChecked(false);
                                sms_cmd13_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd13_L_bit4.setChecked(true);
                                sms_cmd13_L_bit5.setChecked(true);
                                sms_cmd13_L_bit6.setChecked(false);
                                sms_cmd13_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd13_L_bit4.setChecked(false);
                                sms_cmd13_L_bit5.setChecked(false);
                                sms_cmd13_L_bit6.setChecked(true);
                                sms_cmd13_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd13_L_bit4.setChecked(true);
                                sms_cmd13_L_bit5.setChecked(false);
                                sms_cmd13_L_bit6.setChecked(true);
                                sms_cmd13_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd13_L_bit4.setChecked(false);
                                sms_cmd13_L_bit5.setChecked(true);
                                sms_cmd13_L_bit6.setChecked(true);
                                sms_cmd13_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd13_L_bit4.setChecked(true);
                                sms_cmd13_L_bit5.setChecked(true);
                                sms_cmd13_L_bit6.setChecked(true);
                                sms_cmd13_L_bit7.setChecked(true);
                            }
                            *//***************************** END OF SECOND CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd13_H_bit0.setChecked(false);
                                sms_cmd13_H_bit1.setChecked(false);
                                sms_cmd13_H_bit2.setChecked(false);
                                sms_cmd13_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd13_H_bit0.setChecked(true);
                                sms_cmd13_H_bit1.setChecked(false);
                                sms_cmd13_H_bit2.setChecked(false);
                                sms_cmd13_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd13_H_bit0.setChecked(false);
                                sms_cmd13_H_bit1.setChecked(true);
                                sms_cmd13_H_bit2.setChecked(false);
                                sms_cmd13_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd13_H_bit0.setChecked(true);
                                sms_cmd13_H_bit1.setChecked(true);
                                sms_cmd13_H_bit2.setChecked(false);
                                sms_cmd13_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd13_H_bit0.setChecked(false);
                                sms_cmd13_H_bit1.setChecked(false);
                                sms_cmd13_H_bit2.setChecked(true);
                                sms_cmd13_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd13_H_bit0.setChecked(true);
                                sms_cmd13_H_bit1.setChecked(false);
                                sms_cmd13_H_bit2.setChecked(true);
                                sms_cmd13_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd13_H_bit0.setChecked(false);
                                sms_cmd13_H_bit1.setChecked(true);
                                sms_cmd13_H_bit2.setChecked(true);
                                sms_cmd13_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd13_H_bit0.setChecked(true);
                                sms_cmd13_H_bit1.setChecked(true);
                                sms_cmd13_H_bit2.setChecked(true);
                                sms_cmd13_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd13_H_bit0.setChecked(false);
                                sms_cmd13_H_bit1.setChecked(false);
                                sms_cmd13_H_bit2.setChecked(false);
                                sms_cmd13_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd13_H_bit0.setChecked(true);
                                sms_cmd13_H_bit1.setChecked(false);
                                sms_cmd13_H_bit2.setChecked(false);
                                sms_cmd13_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd13_H_bit0.setChecked(false);
                                sms_cmd13_H_bit1.setChecked(true);
                                sms_cmd13_H_bit2.setChecked(false);
                                sms_cmd13_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd13_H_bit0.setChecked(true);
                                sms_cmd13_H_bit1.setChecked(true);
                                sms_cmd13_H_bit2.setChecked(false);
                                sms_cmd13_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd13_H_bit0.setChecked(false);
                                sms_cmd13_H_bit1.setChecked(false);
                                sms_cmd13_H_bit2.setChecked(true);
                                sms_cmd13_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd13_H_bit0.setChecked(true);
                                sms_cmd13_H_bit1.setChecked(false);
                                sms_cmd13_H_bit2.setChecked(true);
                                sms_cmd13_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd13_H_bit0.setChecked(false);
                                sms_cmd13_H_bit1.setChecked(true);
                                sms_cmd13_H_bit2.setChecked(true);
                                sms_cmd13_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd13_H_bit0.setChecked(true);
                                sms_cmd13_H_bit1.setChecked(true);
                                sms_cmd13_H_bit2.setChecked(true);
                                sms_cmd13_H_bit3.setChecked(true);
                            }
                            *//***************************** END OF THIRD CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd13_H_bit4.setChecked(false);
                                sms_cmd13_H_bit5.setChecked(false);
                                sms_cmd13_H_bit6.setChecked(false);
                                sms_cmd13_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd13_H_bit4.setChecked(true);
                                sms_cmd13_H_bit5.setChecked(false);
                                sms_cmd13_H_bit6.setChecked(false);
                                sms_cmd13_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd13_H_bit4.setChecked(false);
                                sms_cmd13_H_bit5.setChecked(true);
                                sms_cmd13_H_bit6.setChecked(false);
                                sms_cmd13_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd13_H_bit4.setChecked(true);
                                sms_cmd13_H_bit5.setChecked(true);
                                sms_cmd13_H_bit6.setChecked(false);
                                sms_cmd13_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd13_H_bit4.setChecked(false);
                                sms_cmd13_H_bit5.setChecked(false);
                                sms_cmd13_H_bit6.setChecked(true);
                                sms_cmd13_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd13_H_bit4.setChecked(true);
                                sms_cmd13_H_bit5.setChecked(false);
                                sms_cmd13_H_bit6.setChecked(true);
                                sms_cmd13_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd13_H_bit4.setChecked(false);
                                sms_cmd13_H_bit5.setChecked(true);
                                sms_cmd13_H_bit6.setChecked(true);
                                sms_cmd13_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd13_H_bit4.setChecked(true);
                                sms_cmd13_H_bit5.setChecked(true);
                                sms_cmd13_H_bit6.setChecked(true);
                                sms_cmd13_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd13_H_bit4.setChecked(false);
                                sms_cmd13_H_bit5.setChecked(false);
                                sms_cmd13_H_bit6.setChecked(false);
                                sms_cmd13_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd13_H_bit4.setChecked(true);
                                sms_cmd13_H_bit5.setChecked(false);
                                sms_cmd13_H_bit6.setChecked(false);
                                sms_cmd13_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd13_H_bit4.setChecked(false);
                                sms_cmd13_H_bit5.setChecked(true);
                                sms_cmd13_H_bit6.setChecked(false);
                                sms_cmd13_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd13_H_bit4.setChecked(true);
                                sms_cmd13_H_bit5.setChecked(true);
                                sms_cmd13_H_bit6.setChecked(false);
                                sms_cmd13_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd13_H_bit4.setChecked(false);
                                sms_cmd13_H_bit5.setChecked(false);
                                sms_cmd13_H_bit6.setChecked(true);
                                sms_cmd13_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd13_H_bit4.setChecked(true);
                                sms_cmd13_H_bit5.setChecked(false);
                                sms_cmd13_H_bit6.setChecked(true);
                                sms_cmd13_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd13_H_bit4.setChecked(false);
                                sms_cmd13_H_bit5.setChecked(true);
                                sms_cmd13_H_bit6.setChecked(true);
                                sms_cmd13_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd13_H_bit4.setChecked(true);
                                sms_cmd13_H_bit5.setChecked(true);
                                sms_cmd13_H_bit6.setChecked(true);
                                sms_cmd13_H_bit7.setChecked(true);
                            }
                            *//***************************** END OF FOURTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FIFTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd13_L_M_bit0.setChecked(false);
                                sms_cmd13_L_M_bit1.setChecked(false);
                                sms_cmd13_L_M_bit2.setChecked(false);
                                sms_cmd13_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd13_L_M_bit0.setChecked(true);
                                sms_cmd13_L_M_bit1.setChecked(false);
                                sms_cmd13_L_M_bit2.setChecked(false);
                                sms_cmd13_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd13_L_M_bit0.setChecked(false);
                                sms_cmd13_L_M_bit1.setChecked(true);
                                sms_cmd13_L_M_bit2.setChecked(false);
                                sms_cmd13_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd13_L_M_bit0.setChecked(true);
                                sms_cmd13_L_M_bit1.setChecked(true);
                                sms_cmd13_L_M_bit2.setChecked(false);
                                sms_cmd13_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd13_L_M_bit0.setChecked(false);
                                sms_cmd13_L_M_bit1.setChecked(false);
                                sms_cmd13_L_M_bit2.setChecked(true);
                                sms_cmd13_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd13_L_M_bit0.setChecked(true);
                                sms_cmd13_L_M_bit1.setChecked(false);
                                sms_cmd13_L_M_bit2.setChecked(true);
                                sms_cmd13_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd13_L_M_bit0.setChecked(false);
                                sms_cmd13_L_M_bit1.setChecked(true);
                                sms_cmd13_L_M_bit2.setChecked(true);
                                sms_cmd13_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd13_L_M_bit0.setChecked(true);
                                sms_cmd13_L_M_bit1.setChecked(true);
                                sms_cmd13_L_M_bit2.setChecked(true);
                                sms_cmd13_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd13_L_M_bit0.setChecked(false);
                                sms_cmd13_L_M_bit1.setChecked(false);
                                sms_cmd13_L_M_bit2.setChecked(false);
                                sms_cmd13_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd13_L_M_bit0.setChecked(true);
                                sms_cmd13_L_M_bit1.setChecked(false);
                                sms_cmd13_L_M_bit2.setChecked(false);
                                sms_cmd13_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd13_L_M_bit0.setChecked(false);
                                sms_cmd13_L_M_bit1.setChecked(true);
                                sms_cmd13_L_M_bit2.setChecked(false);
                                sms_cmd13_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd13_L_M_bit0.setChecked(true);
                                sms_cmd13_L_M_bit1.setChecked(true);
                                sms_cmd13_L_M_bit2.setChecked(false);
                                sms_cmd13_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd13_L_M_bit0.setChecked(false);
                                sms_cmd13_L_M_bit1.setChecked(false);
                                sms_cmd13_L_M_bit2.setChecked(true);
                                sms_cmd13_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd13_L_M_bit0.setChecked(true);
                                sms_cmd13_L_M_bit1.setChecked(false);
                                sms_cmd13_L_M_bit2.setChecked(true);
                                sms_cmd13_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd13_L_M_bit0.setChecked(false);
                                sms_cmd13_L_M_bit1.setChecked(true);
                                sms_cmd13_L_M_bit2.setChecked(true);
                                sms_cmd13_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd13_L_M_bit0.setChecked(true);
                                sms_cmd13_L_M_bit1.setChecked(true);
                                sms_cmd13_L_M_bit2.setChecked(true);
                                sms_cmd13_L_M_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIFTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SIXTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd13_L_M_bit4.setChecked(false);
                                sms_cmd13_L_M_bit5.setChecked(false);
                                sms_cmd13_L_M_bit6.setChecked(false);
                                sms_cmd13_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd13_L_M_bit4.setChecked(true);
                                sms_cmd13_L_M_bit5.setChecked(false);
                                sms_cmd13_L_M_bit6.setChecked(false);
                                sms_cmd13_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd13_L_M_bit4.setChecked(false);
                                sms_cmd13_L_M_bit5.setChecked(true);
                                sms_cmd13_L_M_bit6.setChecked(false);
                                sms_cmd13_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd13_L_M_bit4.setChecked(true);
                                sms_cmd13_L_M_bit5.setChecked(true);
                                sms_cmd13_L_M_bit6.setChecked(false);
                                sms_cmd13_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd13_L_M_bit4.setChecked(false);
                                sms_cmd13_L_M_bit5.setChecked(false);
                                sms_cmd13_L_M_bit6.setChecked(true);
                                sms_cmd13_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd13_L_M_bit4.setChecked(true);
                                sms_cmd13_L_M_bit5.setChecked(false);
                                sms_cmd13_L_M_bit6.setChecked(true);
                                sms_cmd13_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd13_L_M_bit4.setChecked(false);
                                sms_cmd13_L_M_bit5.setChecked(true);
                                sms_cmd13_L_M_bit6.setChecked(true);
                                sms_cmd13_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd13_L_M_bit4.setChecked(true);
                                sms_cmd13_L_M_bit5.setChecked(true);
                                sms_cmd13_L_M_bit6.setChecked(true);
                                sms_cmd13_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd13_L_M_bit4.setChecked(false);
                                sms_cmd13_L_M_bit5.setChecked(false);
                                sms_cmd13_L_M_bit6.setChecked(false);
                                sms_cmd13_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd13_L_M_bit4.setChecked(true);
                                sms_cmd13_L_M_bit5.setChecked(false);
                                sms_cmd13_L_M_bit6.setChecked(false);
                                sms_cmd13_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd13_L_M_bit4.setChecked(false);
                                sms_cmd13_L_M_bit5.setChecked(true);
                                sms_cmd13_L_M_bit6.setChecked(false);
                                sms_cmd13_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd13_L_M_bit4.setChecked(true);
                                sms_cmd13_L_M_bit5.setChecked(true);
                                sms_cmd13_L_M_bit6.setChecked(false);
                                sms_cmd13_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd13_L_M_bit4.setChecked(false);
                                sms_cmd13_L_M_bit5.setChecked(false);
                                sms_cmd13_L_M_bit6.setChecked(true);
                                sms_cmd13_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd13_L_M_bit4.setChecked(true);
                                sms_cmd13_L_M_bit5.setChecked(false);
                                sms_cmd13_L_M_bit6.setChecked(true);
                                sms_cmd13_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd13_L_M_bit4.setChecked(false);
                                sms_cmd13_L_M_bit5.setChecked(true);
                                sms_cmd13_L_M_bit6.setChecked(true);
                                sms_cmd13_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd13_L_M_bit4.setChecked(true);
                                sms_cmd13_L_M_bit5.setChecked(true);
                                sms_cmd13_L_M_bit6.setChecked(true);
                                sms_cmd13_L_M_bit7.setChecked(true);
                            }
                            *//***************************** END OF SIXTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SEVENTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd13_H_M_bit0.setChecked(false);
                                sms_cmd13_H_M_bit1.setChecked(false);
                                sms_cmd13_H_M_bit2.setChecked(false);
                                sms_cmd13_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd13_H_M_bit0.setChecked(true);
                                sms_cmd13_H_M_bit1.setChecked(false);
                                sms_cmd13_H_M_bit2.setChecked(false);
                                sms_cmd13_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd13_H_M_bit0.setChecked(false);
                                sms_cmd13_H_M_bit1.setChecked(true);
                                sms_cmd13_H_M_bit2.setChecked(false);
                                sms_cmd13_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd13_H_M_bit0.setChecked(true);
                                sms_cmd13_H_M_bit1.setChecked(true);
                                sms_cmd13_H_M_bit2.setChecked(false);
                                sms_cmd13_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd13_H_M_bit0.setChecked(false);
                                sms_cmd13_H_M_bit1.setChecked(false);
                                sms_cmd13_H_M_bit2.setChecked(true);
                                sms_cmd13_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd13_H_M_bit0.setChecked(true);
                                sms_cmd13_H_M_bit1.setChecked(false);
                                sms_cmd13_H_M_bit2.setChecked(true);
                                sms_cmd13_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd13_H_M_bit0.setChecked(false);
                                sms_cmd13_H_M_bit1.setChecked(true);
                                sms_cmd13_H_M_bit2.setChecked(true);
                                sms_cmd13_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd13_H_M_bit0.setChecked(true);
                                sms_cmd13_H_M_bit1.setChecked(true);
                                sms_cmd13_H_M_bit2.setChecked(true);
                                sms_cmd13_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd13_H_M_bit0.setChecked(false);
                                sms_cmd13_H_M_bit1.setChecked(false);
                                sms_cmd13_H_M_bit2.setChecked(false);
                                sms_cmd13_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd13_H_M_bit0.setChecked(true);
                                sms_cmd13_H_M_bit1.setChecked(false);
                                sms_cmd13_H_M_bit2.setChecked(false);
                                sms_cmd13_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd13_H_M_bit0.setChecked(false);
                                sms_cmd13_H_M_bit1.setChecked(true);
                                sms_cmd13_H_M_bit2.setChecked(false);
                                sms_cmd13_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd13_H_M_bit0.setChecked(true);
                                sms_cmd13_H_M_bit1.setChecked(true);
                                sms_cmd13_H_M_bit2.setChecked(false);
                                sms_cmd13_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd13_H_M_bit0.setChecked(false);
                                sms_cmd13_H_M_bit1.setChecked(false);
                                sms_cmd13_H_M_bit2.setChecked(true);
                                sms_cmd13_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd13_H_M_bit0.setChecked(true);
                                sms_cmd13_H_M_bit1.setChecked(false);
                                sms_cmd13_H_M_bit2.setChecked(true);
                                sms_cmd13_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd13_H_M_bit0.setChecked(false);
                                sms_cmd13_H_M_bit1.setChecked(true);
                                sms_cmd13_H_M_bit2.setChecked(true);
                                sms_cmd13_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd13_H_M_bit0.setChecked(true);
                                sms_cmd13_H_M_bit1.setChecked(true);
                                sms_cmd13_H_M_bit2.setChecked(true);
                                sms_cmd13_H_M_bit3.setChecked(true);
                            }
                            *//***************************** END OF SEVENTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON EIGHTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd13_H_M_bit4.setChecked(false);
                                sms_cmd13_H_M_bit5.setChecked(false);
                                sms_cmd13_H_M_bit6.setChecked(false);
                                sms_cmd13_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd13_H_M_bit4.setChecked(true);
                                sms_cmd13_H_M_bit5.setChecked(false);
                                sms_cmd13_H_M_bit6.setChecked(false);
                                sms_cmd13_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd13_H_M_bit4.setChecked(false);
                                sms_cmd13_H_M_bit5.setChecked(true);
                                sms_cmd13_H_M_bit6.setChecked(false);
                                sms_cmd13_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd13_H_M_bit4.setChecked(true);
                                sms_cmd13_H_M_bit5.setChecked(true);
                                sms_cmd13_H_M_bit6.setChecked(false);
                                sms_cmd13_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd13_H_M_bit4.setChecked(false);
                                sms_cmd13_H_M_bit5.setChecked(false);
                                sms_cmd13_H_M_bit6.setChecked(true);
                                sms_cmd13_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd13_H_M_bit4.setChecked(true);
                                sms_cmd13_H_M_bit5.setChecked(false);
                                sms_cmd13_H_M_bit6.setChecked(true);
                                sms_cmd13_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd13_H_M_bit4.setChecked(false);
                                sms_cmd13_H_M_bit5.setChecked(true);
                                sms_cmd13_H_M_bit6.setChecked(true);
                                sms_cmd13_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd13_H_M_bit4.setChecked(true);
                                sms_cmd13_H_M_bit5.setChecked(true);
                                sms_cmd13_H_M_bit6.setChecked(true);
                                sms_cmd13_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd13_H_M_bit4.setChecked(false);
                                sms_cmd13_H_M_bit5.setChecked(false);
                                sms_cmd13_H_M_bit6.setChecked(false);
                                sms_cmd13_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd13_H_M_bit4.setChecked(true);
                                sms_cmd13_H_M_bit5.setChecked(false);
                                sms_cmd13_H_M_bit6.setChecked(false);
                                sms_cmd13_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd13_H_M_bit4.setChecked(false);
                                sms_cmd13_H_M_bit5.setChecked(true);
                                sms_cmd13_H_M_bit6.setChecked(false);
                                sms_cmd13_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd13_H_M_bit4.setChecked(true);
                                sms_cmd13_H_M_bit5.setChecked(true);
                                sms_cmd13_H_M_bit6.setChecked(false);
                                sms_cmd13_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd13_H_M_bit4.setChecked(false);
                                sms_cmd13_H_M_bit5.setChecked(false);
                                sms_cmd13_H_M_bit6.setChecked(true);
                                sms_cmd13_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd13_H_M_bit4.setChecked(true);
                                sms_cmd13_H_M_bit5.setChecked(false);
                                sms_cmd13_H_M_bit6.setChecked(true);
                                sms_cmd13_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd13_H_M_bit4.setChecked(false);
                                sms_cmd13_H_M_bit5.setChecked(true);
                                sms_cmd13_H_M_bit6.setChecked(true);
                                sms_cmd13_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd13_H_M_bit4.setChecked(true);
                                sms_cmd13_H_M_bit5.setChecked(true);
                                sms_cmd13_H_M_bit6.setChecked(true);
                                sms_cmd13_H_M_bit7.setChecked(true);
                            }
                            *//***************************** END OF EIGHTH CHARACTER FROM RIGHT **************************//*
                        }

                        //DISPLAY FOR CAR KEYS//
                        if(String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("4"))
                        {
                            *//******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd14_L_bit0.setChecked(false);
                                sms_cmd14_L_bit1.setChecked(false);
                                sms_cmd14_L_bit2.setChecked(false);
                                sms_cmd14_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd14_L_bit0.setChecked(true);
                                sms_cmd14_L_bit1.setChecked(false);
                                sms_cmd14_L_bit2.setChecked(false);
                                sms_cmd14_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd14_L_bit0.setChecked(false);
                                sms_cmd14_L_bit1.setChecked(true);
                                sms_cmd14_L_bit2.setChecked(false);
                                sms_cmd14_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd14_L_bit0.setChecked(true);
                                sms_cmd14_L_bit1.setChecked(true);
                                sms_cmd14_L_bit2.setChecked(false);
                                sms_cmd14_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd14_L_bit0.setChecked(false);
                                sms_cmd14_L_bit1.setChecked(false);
                                sms_cmd14_L_bit2.setChecked(true);
                                sms_cmd14_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd14_L_bit0.setChecked(true);
                                sms_cmd14_L_bit1.setChecked(false);
                                sms_cmd14_L_bit2.setChecked(true);
                                sms_cmd14_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd14_L_bit0.setChecked(false);
                                sms_cmd14_L_bit1.setChecked(true);
                                sms_cmd14_L_bit2.setChecked(true);
                                sms_cmd14_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd14_L_bit0.setChecked(true);
                                sms_cmd14_L_bit1.setChecked(true);
                                sms_cmd14_L_bit2.setChecked(true);
                                sms_cmd14_L_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd14_L_bit0.setChecked(false);
                                sms_cmd14_L_bit1.setChecked(false);
                                sms_cmd14_L_bit2.setChecked(false);
                                sms_cmd14_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd14_L_bit0.setChecked(true);
                                sms_cmd14_L_bit1.setChecked(false);
                                sms_cmd14_L_bit2.setChecked(false);
                                sms_cmd14_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd14_L_bit0.setChecked(false);
                                sms_cmd14_L_bit1.setChecked(true);
                                sms_cmd14_L_bit2.setChecked(false);
                                sms_cmd14_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd14_L_bit0.setChecked(true);
                                sms_cmd14_L_bit1.setChecked(true);
                                sms_cmd14_L_bit2.setChecked(false);
                                sms_cmd14_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd14_L_bit0.setChecked(false);
                                sms_cmd14_L_bit1.setChecked(false);
                                sms_cmd14_L_bit2.setChecked(true);
                                sms_cmd14_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd14_L_bit0.setChecked(true);
                                sms_cmd14_L_bit1.setChecked(false);
                                sms_cmd14_L_bit2.setChecked(true);
                                sms_cmd14_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd14_L_bit0.setChecked(false);
                                sms_cmd14_L_bit1.setChecked(true);
                                sms_cmd14_L_bit2.setChecked(true);
                                sms_cmd14_L_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd14_L_bit0.setChecked(true);
                                sms_cmd14_L_bit1.setChecked(true);
                                sms_cmd14_L_bit2.setChecked(true);
                                sms_cmd14_L_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIRST CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd14_L_bit4.setChecked(false);
                                sms_cmd14_L_bit5.setChecked(false);
                                sms_cmd14_L_bit6.setChecked(false);
                                sms_cmd14_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd14_L_bit4.setChecked(true);
                                sms_cmd14_L_bit5.setChecked(false);
                                sms_cmd14_L_bit6.setChecked(false);
                                sms_cmd14_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd14_L_bit4.setChecked(false);
                                sms_cmd14_L_bit5.setChecked(true);
                                sms_cmd14_L_bit6.setChecked(false);
                                sms_cmd14_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd14_L_bit4.setChecked(true);
                                sms_cmd14_L_bit5.setChecked(true);
                                sms_cmd14_L_bit6.setChecked(false);
                                sms_cmd14_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd14_L_bit4.setChecked(false);
                                sms_cmd14_L_bit5.setChecked(false);
                                sms_cmd14_L_bit6.setChecked(true);
                                sms_cmd14_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd14_L_bit4.setChecked(true);
                                sms_cmd14_L_bit5.setChecked(false);
                                sms_cmd14_L_bit6.setChecked(true);
                                sms_cmd14_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd14_L_bit4.setChecked(false);
                                sms_cmd14_L_bit5.setChecked(true);
                                sms_cmd14_L_bit6.setChecked(true);
                                sms_cmd14_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd14_L_bit4.setChecked(true);
                                sms_cmd14_L_bit5.setChecked(true);
                                sms_cmd14_L_bit6.setChecked(true);
                                sms_cmd14_L_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd14_L_bit4.setChecked(false);
                                sms_cmd14_L_bit5.setChecked(false);
                                sms_cmd14_L_bit6.setChecked(false);
                                sms_cmd14_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd14_L_bit4.setChecked(true);
                                sms_cmd14_L_bit5.setChecked(false);
                                sms_cmd14_L_bit6.setChecked(false);
                                sms_cmd14_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd14_L_bit4.setChecked(false);
                                sms_cmd14_L_bit5.setChecked(true);
                                sms_cmd14_L_bit6.setChecked(false);
                                sms_cmd14_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd14_L_bit4.setChecked(true);
                                sms_cmd14_L_bit5.setChecked(true);
                                sms_cmd14_L_bit6.setChecked(false);
                                sms_cmd14_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd14_L_bit4.setChecked(false);
                                sms_cmd14_L_bit5.setChecked(false);
                                sms_cmd14_L_bit6.setChecked(true);
                                sms_cmd14_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd14_L_bit4.setChecked(true);
                                sms_cmd14_L_bit5.setChecked(false);
                                sms_cmd14_L_bit6.setChecked(true);
                                sms_cmd14_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd14_L_bit4.setChecked(false);
                                sms_cmd14_L_bit5.setChecked(true);
                                sms_cmd14_L_bit6.setChecked(true);
                                sms_cmd14_L_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd14_L_bit4.setChecked(true);
                                sms_cmd14_L_bit5.setChecked(true);
                                sms_cmd14_L_bit6.setChecked(true);
                                sms_cmd14_L_bit7.setChecked(true);
                            }
                            *//***************************** END OF SECOND CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd14_H_bit0.setChecked(false);
                                sms_cmd14_H_bit1.setChecked(false);
                                sms_cmd14_H_bit2.setChecked(false);
                                sms_cmd14_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd14_H_bit0.setChecked(true);
                                sms_cmd14_H_bit1.setChecked(false);
                                sms_cmd14_H_bit2.setChecked(false);
                                sms_cmd14_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd14_H_bit0.setChecked(false);
                                sms_cmd14_H_bit1.setChecked(true);
                                sms_cmd14_H_bit2.setChecked(false);
                                sms_cmd14_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd14_H_bit0.setChecked(true);
                                sms_cmd14_H_bit1.setChecked(true);
                                sms_cmd14_H_bit2.setChecked(false);
                                sms_cmd14_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd14_H_bit0.setChecked(false);
                                sms_cmd14_H_bit1.setChecked(false);
                                sms_cmd14_H_bit2.setChecked(true);
                                sms_cmd14_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd14_H_bit0.setChecked(true);
                                sms_cmd14_H_bit1.setChecked(false);
                                sms_cmd14_H_bit2.setChecked(true);
                                sms_cmd14_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd14_H_bit0.setChecked(false);
                                sms_cmd14_H_bit1.setChecked(true);
                                sms_cmd14_H_bit2.setChecked(true);
                                sms_cmd14_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd14_H_bit0.setChecked(true);
                                sms_cmd14_H_bit1.setChecked(true);
                                sms_cmd14_H_bit2.setChecked(true);
                                sms_cmd14_H_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd14_H_bit0.setChecked(false);
                                sms_cmd14_H_bit1.setChecked(false);
                                sms_cmd14_H_bit2.setChecked(false);
                                sms_cmd14_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd14_H_bit0.setChecked(true);
                                sms_cmd14_H_bit1.setChecked(false);
                                sms_cmd14_H_bit2.setChecked(false);
                                sms_cmd14_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd14_H_bit0.setChecked(false);
                                sms_cmd14_H_bit1.setChecked(true);
                                sms_cmd14_H_bit2.setChecked(false);
                                sms_cmd14_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd14_H_bit0.setChecked(true);
                                sms_cmd14_H_bit1.setChecked(true);
                                sms_cmd14_H_bit2.setChecked(false);
                                sms_cmd14_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd14_H_bit0.setChecked(false);
                                sms_cmd14_H_bit1.setChecked(false);
                                sms_cmd14_H_bit2.setChecked(true);
                                sms_cmd14_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd14_H_bit0.setChecked(true);
                                sms_cmd14_H_bit1.setChecked(false);
                                sms_cmd14_H_bit2.setChecked(true);
                                sms_cmd14_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd14_H_bit0.setChecked(false);
                                sms_cmd14_H_bit1.setChecked(true);
                                sms_cmd14_H_bit2.setChecked(true);
                                sms_cmd14_H_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd14_H_bit0.setChecked(true);
                                sms_cmd14_H_bit1.setChecked(true);
                                sms_cmd14_H_bit2.setChecked(true);
                                sms_cmd14_H_bit3.setChecked(true);
                            }
                            *//***************************** END OF THIRD CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd14_H_bit4.setChecked(false);
                                sms_cmd14_H_bit5.setChecked(false);
                                sms_cmd14_H_bit6.setChecked(false);
                                sms_cmd14_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd14_H_bit4.setChecked(true);
                                sms_cmd14_H_bit5.setChecked(false);
                                sms_cmd14_H_bit6.setChecked(false);
                                sms_cmd14_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd14_H_bit4.setChecked(false);
                                sms_cmd14_H_bit5.setChecked(true);
                                sms_cmd14_H_bit6.setChecked(false);
                                sms_cmd14_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd14_H_bit4.setChecked(true);
                                sms_cmd14_H_bit5.setChecked(true);
                                sms_cmd14_H_bit6.setChecked(false);
                                sms_cmd14_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd14_H_bit4.setChecked(false);
                                sms_cmd14_H_bit5.setChecked(false);
                                sms_cmd14_H_bit6.setChecked(true);
                                sms_cmd14_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd14_H_bit4.setChecked(true);
                                sms_cmd14_H_bit5.setChecked(false);
                                sms_cmd14_H_bit6.setChecked(true);
                                sms_cmd14_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd14_H_bit4.setChecked(false);
                                sms_cmd14_H_bit5.setChecked(true);
                                sms_cmd14_H_bit6.setChecked(true);
                                sms_cmd14_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd14_H_bit4.setChecked(true);
                                sms_cmd14_H_bit5.setChecked(true);
                                sms_cmd14_H_bit6.setChecked(true);
                                sms_cmd14_H_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd14_H_bit4.setChecked(false);
                                sms_cmd14_H_bit5.setChecked(false);
                                sms_cmd14_H_bit6.setChecked(false);
                                sms_cmd14_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd14_H_bit4.setChecked(true);
                                sms_cmd14_H_bit5.setChecked(false);
                                sms_cmd14_H_bit6.setChecked(false);
                                sms_cmd14_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd14_H_bit4.setChecked(false);
                                sms_cmd14_H_bit5.setChecked(true);
                                sms_cmd14_H_bit6.setChecked(false);
                                sms_cmd14_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd14_H_bit4.setChecked(true);
                                sms_cmd14_H_bit5.setChecked(true);
                                sms_cmd14_H_bit6.setChecked(false);
                                sms_cmd14_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd14_H_bit4.setChecked(false);
                                sms_cmd14_H_bit5.setChecked(false);
                                sms_cmd14_H_bit6.setChecked(true);
                                sms_cmd14_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd14_H_bit4.setChecked(true);
                                sms_cmd14_H_bit5.setChecked(false);
                                sms_cmd14_H_bit6.setChecked(true);
                                sms_cmd14_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd14_H_bit4.setChecked(false);
                                sms_cmd14_H_bit5.setChecked(true);
                                sms_cmd14_H_bit6.setChecked(true);
                                sms_cmd14_H_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd14_H_bit4.setChecked(true);
                                sms_cmd14_H_bit5.setChecked(true);
                                sms_cmd14_H_bit6.setChecked(true);
                                sms_cmd14_H_bit7.setChecked(true);
                            }
                            *//***************************** END OF FOURTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON FIFTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd14_L_M_bit0.setChecked(false);
                                sms_cmd14_L_M_bit1.setChecked(false);
                                sms_cmd14_L_M_bit2.setChecked(false);
                                sms_cmd14_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd14_L_M_bit0.setChecked(true);
                                sms_cmd14_L_M_bit1.setChecked(false);
                                sms_cmd14_L_M_bit2.setChecked(false);
                                sms_cmd14_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd14_L_M_bit0.setChecked(false);
                                sms_cmd14_L_M_bit1.setChecked(true);
                                sms_cmd14_L_M_bit2.setChecked(false);
                                sms_cmd14_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd14_L_M_bit0.setChecked(true);
                                sms_cmd14_L_M_bit1.setChecked(true);
                                sms_cmd14_L_M_bit2.setChecked(false);
                                sms_cmd14_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd14_L_M_bit0.setChecked(false);
                                sms_cmd14_L_M_bit1.setChecked(false);
                                sms_cmd14_L_M_bit2.setChecked(true);
                                sms_cmd14_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd14_L_M_bit0.setChecked(true);
                                sms_cmd14_L_M_bit1.setChecked(false);
                                sms_cmd14_L_M_bit2.setChecked(true);
                                sms_cmd14_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd14_L_M_bit0.setChecked(false);
                                sms_cmd14_L_M_bit1.setChecked(true);
                                sms_cmd14_L_M_bit2.setChecked(true);
                                sms_cmd14_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd14_L_M_bit0.setChecked(true);
                                sms_cmd14_L_M_bit1.setChecked(true);
                                sms_cmd14_L_M_bit2.setChecked(true);
                                sms_cmd14_L_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd14_L_M_bit0.setChecked(false);
                                sms_cmd14_L_M_bit1.setChecked(false);
                                sms_cmd14_L_M_bit2.setChecked(false);
                                sms_cmd14_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd14_L_M_bit0.setChecked(true);
                                sms_cmd14_L_M_bit1.setChecked(false);
                                sms_cmd14_L_M_bit2.setChecked(false);
                                sms_cmd14_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd14_L_M_bit0.setChecked(false);
                                sms_cmd14_L_M_bit1.setChecked(true);
                                sms_cmd14_L_M_bit2.setChecked(false);
                                sms_cmd14_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd14_L_M_bit0.setChecked(true);
                                sms_cmd14_L_M_bit1.setChecked(true);
                                sms_cmd14_L_M_bit2.setChecked(false);
                                sms_cmd14_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd14_L_M_bit0.setChecked(false);
                                sms_cmd14_L_M_bit1.setChecked(false);
                                sms_cmd14_L_M_bit2.setChecked(true);
                                sms_cmd14_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd14_L_M_bit0.setChecked(true);
                                sms_cmd14_L_M_bit1.setChecked(false);
                                sms_cmd14_L_M_bit2.setChecked(true);
                                sms_cmd14_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd14_L_M_bit0.setChecked(false);
                                sms_cmd14_L_M_bit1.setChecked(true);
                                sms_cmd14_L_M_bit2.setChecked(true);
                                sms_cmd14_L_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-5))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd14_L_M_bit0.setChecked(true);
                                sms_cmd14_L_M_bit1.setChecked(true);
                                sms_cmd14_L_M_bit2.setChecked(true);
                                sms_cmd14_L_M_bit3.setChecked(true);
                            }
                            *//***************************** END OF FIFTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SIXTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd14_L_M_bit4.setChecked(false);
                                sms_cmd14_L_M_bit5.setChecked(false);
                                sms_cmd14_L_M_bit6.setChecked(false);
                                sms_cmd14_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd14_L_M_bit4.setChecked(true);
                                sms_cmd14_L_M_bit5.setChecked(false);
                                sms_cmd14_L_M_bit6.setChecked(false);
                                sms_cmd14_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd14_L_M_bit4.setChecked(false);
                                sms_cmd14_L_M_bit5.setChecked(true);
                                sms_cmd14_L_M_bit6.setChecked(false);
                                sms_cmd14_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd14_L_M_bit4.setChecked(true);
                                sms_cmd14_L_M_bit5.setChecked(true);
                                sms_cmd14_L_M_bit6.setChecked(false);
                                sms_cmd14_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd14_L_M_bit4.setChecked(false);
                                sms_cmd14_L_M_bit5.setChecked(false);
                                sms_cmd14_L_M_bit6.setChecked(true);
                                sms_cmd14_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd14_L_M_bit4.setChecked(true);
                                sms_cmd14_L_M_bit5.setChecked(false);
                                sms_cmd14_L_M_bit6.setChecked(true);
                                sms_cmd14_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd14_L_M_bit4.setChecked(false);
                                sms_cmd14_L_M_bit5.setChecked(true);
                                sms_cmd14_L_M_bit6.setChecked(true);
                                sms_cmd14_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd14_L_M_bit4.setChecked(true);
                                sms_cmd14_L_M_bit5.setChecked(true);
                                sms_cmd14_L_M_bit6.setChecked(true);
                                sms_cmd14_L_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd14_L_M_bit4.setChecked(false);
                                sms_cmd14_L_M_bit5.setChecked(false);
                                sms_cmd14_L_M_bit6.setChecked(false);
                                sms_cmd14_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd14_L_M_bit4.setChecked(true);
                                sms_cmd14_L_M_bit5.setChecked(false);
                                sms_cmd14_L_M_bit6.setChecked(false);
                                sms_cmd14_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd14_L_M_bit4.setChecked(false);
                                sms_cmd14_L_M_bit5.setChecked(true);
                                sms_cmd14_L_M_bit6.setChecked(false);
                                sms_cmd14_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd14_L_M_bit4.setChecked(true);
                                sms_cmd14_L_M_bit5.setChecked(true);
                                sms_cmd14_L_M_bit6.setChecked(false);
                                sms_cmd14_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd14_L_M_bit4.setChecked(false);
                                sms_cmd14_L_M_bit5.setChecked(false);
                                sms_cmd14_L_M_bit6.setChecked(true);
                                sms_cmd14_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd14_L_M_bit4.setChecked(true);
                                sms_cmd14_L_M_bit5.setChecked(false);
                                sms_cmd14_L_M_bit6.setChecked(true);
                                sms_cmd14_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd14_L_M_bit4.setChecked(false);
                                sms_cmd14_L_M_bit5.setChecked(true);
                                sms_cmd14_L_M_bit6.setChecked(true);
                                sms_cmd14_L_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-6))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd14_L_M_bit4.setChecked(true);
                                sms_cmd14_L_M_bit5.setChecked(true);
                                sms_cmd14_L_M_bit6.setChecked(true);
                                sms_cmd14_L_M_bit7.setChecked(true);
                            }
                            *//***************************** END OF SIXTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON SEVENTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd14_H_M_bit0.setChecked(false);
                                sms_cmd14_H_M_bit1.setChecked(false);
                                sms_cmd14_H_M_bit2.setChecked(false);
                                sms_cmd14_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd14_H_M_bit0.setChecked(true);
                                sms_cmd14_H_M_bit1.setChecked(false);
                                sms_cmd14_H_M_bit2.setChecked(false);
                                sms_cmd14_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd14_H_M_bit0.setChecked(false);
                                sms_cmd14_H_M_bit1.setChecked(true);
                                sms_cmd14_H_M_bit2.setChecked(false);
                                sms_cmd14_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd14_H_M_bit0.setChecked(true);
                                sms_cmd14_H_M_bit1.setChecked(true);
                                sms_cmd14_H_M_bit2.setChecked(false);
                                sms_cmd14_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd14_H_M_bit0.setChecked(false);
                                sms_cmd14_H_M_bit1.setChecked(false);
                                sms_cmd14_H_M_bit2.setChecked(true);
                                sms_cmd14_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd14_H_M_bit0.setChecked(true);
                                sms_cmd14_H_M_bit1.setChecked(false);
                                sms_cmd14_H_M_bit2.setChecked(true);
                                sms_cmd14_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd14_H_M_bit0.setChecked(false);
                                sms_cmd14_H_M_bit1.setChecked(true);
                                sms_cmd14_H_M_bit2.setChecked(true);
                                sms_cmd14_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd14_H_M_bit0.setChecked(true);
                                sms_cmd14_H_M_bit1.setChecked(true);
                                sms_cmd14_H_M_bit2.setChecked(true);
                                sms_cmd14_H_M_bit3.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd14_H_M_bit0.setChecked(false);
                                sms_cmd14_H_M_bit1.setChecked(false);
                                sms_cmd14_H_M_bit2.setChecked(false);
                                sms_cmd14_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd14_H_M_bit0.setChecked(true);
                                sms_cmd14_H_M_bit1.setChecked(false);
                                sms_cmd14_H_M_bit2.setChecked(false);
                                sms_cmd14_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd14_H_M_bit0.setChecked(false);
                                sms_cmd14_H_M_bit1.setChecked(true);
                                sms_cmd14_H_M_bit2.setChecked(false);
                                sms_cmd14_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd14_H_M_bit0.setChecked(true);
                                sms_cmd14_H_M_bit1.setChecked(true);
                                sms_cmd14_H_M_bit2.setChecked(false);
                                sms_cmd14_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd14_H_M_bit0.setChecked(false);
                                sms_cmd14_H_M_bit1.setChecked(false);
                                sms_cmd14_H_M_bit2.setChecked(true);
                                sms_cmd14_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd14_H_M_bit0.setChecked(true);
                                sms_cmd14_H_M_bit1.setChecked(false);
                                sms_cmd14_H_M_bit2.setChecked(true);
                                sms_cmd14_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd14_H_M_bit0.setChecked(false);
                                sms_cmd14_H_M_bit1.setChecked(true);
                                sms_cmd14_H_M_bit2.setChecked(true);
                                sms_cmd14_H_M_bit3.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-7))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd14_H_M_bit0.setChecked(true);
                                sms_cmd14_H_M_bit1.setChecked(true);
                                sms_cmd14_H_M_bit2.setChecked(true);
                                sms_cmd14_H_M_bit3.setChecked(true);
                            }
                            *//***************************** END OF SEVENTH CHARACTER FROM RIGHT **************************//*
                            *//******************************** BASED ON EIGHTH CHARACTER FROM RIGHT ***********************//*
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexZero))
                            {
                                sms_cmd14_H_M_bit4.setChecked(false);
                                sms_cmd14_H_M_bit5.setChecked(false);
                                sms_cmd14_H_M_bit6.setChecked(false);
                                sms_cmd14_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd14_H_M_bit4.setChecked(true);
                                sms_cmd14_H_M_bit5.setChecked(false);
                                sms_cmd14_H_M_bit6.setChecked(false);
                                sms_cmd14_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexTwo))
                            {
                                sms_cmd14_H_M_bit4.setChecked(false);
                                sms_cmd14_H_M_bit5.setChecked(true);
                                sms_cmd14_H_M_bit6.setChecked(false);
                                sms_cmd14_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexThree))
                            {
                                sms_cmd14_H_M_bit4.setChecked(true);
                                sms_cmd14_H_M_bit5.setChecked(true);
                                sms_cmd14_H_M_bit6.setChecked(false);
                                sms_cmd14_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd14_H_M_bit4.setChecked(false);
                                sms_cmd14_H_M_bit5.setChecked(false);
                                sms_cmd14_H_M_bit6.setChecked(true);
                                sms_cmd14_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexFive))
                            {
                                sms_cmd14_H_M_bit4.setChecked(true);
                                sms_cmd14_H_M_bit5.setChecked(false);
                                sms_cmd14_H_M_bit6.setChecked(true);
                                sms_cmd14_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexSix))
                            {
                                sms_cmd14_H_M_bit4.setChecked(false);
                                sms_cmd14_H_M_bit5.setChecked(true);
                                sms_cmd14_H_M_bit6.setChecked(true);
                                sms_cmd14_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexSeven))
                            {
                                sms_cmd14_H_M_bit4.setChecked(true);
                                sms_cmd14_H_M_bit5.setChecked(true);
                                sms_cmd14_H_M_bit6.setChecked(true);
                                sms_cmd14_H_M_bit7.setChecked(false);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexEight))
                            {
                                sms_cmd14_H_M_bit4.setChecked(false);
                                sms_cmd14_H_M_bit5.setChecked(false);
                                sms_cmd14_H_M_bit6.setChecked(false);
                                sms_cmd14_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexNine))
                            {
                                sms_cmd14_H_M_bit4.setChecked(true);
                                sms_cmd14_H_M_bit5.setChecked(false);
                                sms_cmd14_H_M_bit6.setChecked(false);
                                sms_cmd14_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexA))
                            {
                                sms_cmd14_H_M_bit4.setChecked(false);
                                sms_cmd14_H_M_bit5.setChecked(true);
                                sms_cmd14_H_M_bit6.setChecked(false);
                                sms_cmd14_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexB))
                            {
                                sms_cmd14_H_M_bit4.setChecked(true);
                                sms_cmd14_H_M_bit5.setChecked(true);
                                sms_cmd14_H_M_bit6.setChecked(false);
                                sms_cmd14_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexC))
                            {
                                sms_cmd14_H_M_bit4.setChecked(false);
                                sms_cmd14_H_M_bit5.setChecked(false);
                                sms_cmd14_H_M_bit6.setChecked(true);
                                sms_cmd14_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexD))
                            {
                                sms_cmd14_H_M_bit4.setChecked(true);
                                sms_cmd14_H_M_bit5.setChecked(false);
                                sms_cmd14_H_M_bit6.setChecked(true);
                                sms_cmd14_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexE))
                            {
                                sms_cmd14_H_M_bit4.setChecked(false);
                                sms_cmd14_H_M_bit5.setChecked(true);
                                sms_cmd14_H_M_bit6.setChecked(true);
                                sms_cmd14_H_M_bit7.setChecked(true);
                            }

                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-8))).equalsIgnoreCase(hexF))
                            {
                                sms_cmd14_H_M_bit4.setChecked(true);
                                sms_cmd14_H_M_bit5.setChecked(true);
                                sms_cmd14_H_M_bit6.setChecked(true);
                                sms_cmd14_H_M_bit7.setChecked(true);
                            }
                            *//***************************** END OF EIGHTH CHARACTER FROM RIGHT **************************//*
                        }

                        //DISPLAY FOR LIFT STATUS//
                        if(String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("5"))
                        {
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                            {
                                sms_cmd15.setChecked(true);
                            }
                            else
                            {
                                sms_cmd15.setChecked(false);
                            }
                        }

                        //DISPLAY FOR START MONITORING//
                        if(String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("9"))
                        {
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd19.setChecked(true);
                            }
                            else
                            {
                                sms_cmd19.setChecked(false);
                            }
                        }

                        //LIFT EMERGENCY STOP STATUS READ
                        if(String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("2") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("0"))
                        {
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd20_bit0.setChecked(true);
                            }
                            if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                            {
                                sms_cmd20_bit1.setChecked(true);
                            }
                        }

                        displayFromCommand16Onwards();
                    }
                }
            }
        }
        else
        {
            toast("THERE ARE NO MESSAGES.");
        }*/
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

    public void displayFromCommand16Onwards()
    {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd, MMM, yyyy");
        date = dateFormat.format(calendar.getTime());
        today.setText("Today is:"+date);
        customerControllerDB = new CustomerControllerDB(this);
        sqLiteDatabase = customerControllerDB.getReadableDatabase();
        query = "SELECT CUSTOMER_CLIENT_NAME FROM CUSTOMER_CLIENT_DATA WHERE CUSTOMER_CLIENT_NAME='"+theKey+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    adminText.setText(cursor.getString(cursor.getColumnIndex("CUSTOMER_CLIENT_NAME")));
                    //adminText.setBackgroundColor(Color.WHITE);
                    //adminText.setTextColor(Color.BLACK);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        customerControllerDB = new CustomerControllerDB(this);
        sqLiteDatabase = customerControllerDB.getReadableDatabase();
        contactNumberQuery = "SELECT CUSTOMER_CLIENT_NUMBER FROM CUSTOMER_CLIENT_DATA WHERE CUSTOMER_CLIENT_NAME='"+theKey+"'";
        Cursor cursor2 = sqLiteDatabase.rawQuery(contactNumberQuery,null);
        rowCount2 = cursor2.getCount();
        if(rowCount2!=0)
        {
            if (cursor2.moveToFirst())
            {
                do
                {
                    choice = cursor2.getString(cursor2.getColumnIndex("CUSTOMER_CLIENT_NUMBER"));
                }while (cursor2.moveToNext());
            }
        }
        cursor2.close();
        // Register a broadcast receiver for receiving SMS
        IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);
        //toast("REGISTERED THE SMS RECEIVER");
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM CUSTOMER_SMS WHERE DATE='"+date+"'",null);
        clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM CUSTOMER_SMS WHERE ADDRESS='"+choice+"'",null);
        rowCount3 = clientCursor.getCount();
        //toast(String.valueOf(rowCount3));
        theContent = new ArrayList<String>();
        theContent.clear();
        if(rowCount3 != emptyTable) {
            if (clientCursor.moveToFirst()) {
                do {
                    theContent.add(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                    //toast(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
                } while (clientCursor.moveToNext());
                int count = clientCursor.getCount();
                String contentArray[] = new String[count];
                for (int j = 0; j < contentArray.length; j++) {
                    contentArray[j] = theContent.get(j);
                }
                StringBuffer sb0 = new StringBuffer();
                for (int k = 0; k < contentArray.length; k++) {
                    sb0.append(contentArray[k]);
                }
                String str1 = sb0.toString();
                //adminText.setText(str1);
                String decodeChoice = str1;
                //DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING
                String theData[] = decodeChoice.split("\n");
                String stringSplit = theData[(theData.length-1)];
                for (int s = 0; s < stringSplit.length(); s++)
                {
                    //if (!String.valueOf(stringSplit[s]).isEmpty())
                    if(!(stringSplit.isEmpty()))
                    {
                        //DISPLAY FOR LIFT ERROR STATUS//
                        if (String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("6"))
                        {
                            displayTimer(displayTime);
                            /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexZero)) {
                                sms_cmd16_L_bit0.setChecked(false);
                                sms_cmd16_L_bit1.setChecked(false);
                                sms_cmd16_L_bit2.setChecked(false);
                                sms_cmd16_L_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexOne)) {
                                sms_cmd16_L_bit0.setChecked(true);
                                sms_cmd16_L_bit1.setChecked(false);
                                sms_cmd16_L_bit2.setChecked(false);
                                sms_cmd16_L_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexTwo)) {
                                sms_cmd16_L_bit0.setChecked(false);
                                sms_cmd16_L_bit1.setChecked(true);
                                sms_cmd16_L_bit2.setChecked(false);
                                sms_cmd16_L_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexThree)) {
                                sms_cmd16_L_bit0.setChecked(true);
                                sms_cmd16_L_bit1.setChecked(true);
                                sms_cmd16_L_bit2.setChecked(false);
                                sms_cmd16_L_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexFour)) {
                                sms_cmd16_L_bit0.setChecked(false);
                                sms_cmd16_L_bit1.setChecked(false);
                                sms_cmd16_L_bit2.setChecked(true);
                                sms_cmd16_L_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexFive)) {
                                sms_cmd16_L_bit0.setChecked(true);
                                sms_cmd16_L_bit1.setChecked(false);
                                sms_cmd16_L_bit2.setChecked(true);
                                sms_cmd16_L_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexSix)) {
                                sms_cmd16_L_bit0.setChecked(false);
                                sms_cmd16_L_bit1.setChecked(true);
                                sms_cmd16_L_bit2.setChecked(true);
                                sms_cmd16_L_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexSeven)) {
                                sms_cmd16_L_bit0.setChecked(true);
                                sms_cmd16_L_bit1.setChecked(true);
                                sms_cmd16_L_bit2.setChecked(true);
                                sms_cmd16_L_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexEight)) {
                                sms_cmd16_L_bit0.setChecked(false);
                                sms_cmd16_L_bit1.setChecked(false);
                                sms_cmd16_L_bit2.setChecked(false);
                                sms_cmd16_L_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexNine)) {
                                sms_cmd16_L_bit0.setChecked(true);
                                sms_cmd16_L_bit1.setChecked(false);
                                sms_cmd16_L_bit2.setChecked(false);
                                sms_cmd16_L_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexA)) {
                                sms_cmd16_L_bit0.setChecked(false);
                                sms_cmd16_L_bit1.setChecked(true);
                                sms_cmd16_L_bit2.setChecked(false);
                                sms_cmd16_L_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexB)) {
                                sms_cmd16_L_bit0.setChecked(true);
                                sms_cmd16_L_bit1.setChecked(true);
                                sms_cmd16_L_bit2.setChecked(false);
                                sms_cmd16_L_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexC)) {
                                sms_cmd16_L_bit0.setChecked(false);
                                sms_cmd16_L_bit1.setChecked(false);
                                sms_cmd16_L_bit2.setChecked(true);
                                sms_cmd16_L_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexD)) {
                                sms_cmd16_L_bit0.setChecked(true);
                                sms_cmd16_L_bit1.setChecked(false);
                                sms_cmd16_L_bit2.setChecked(true);
                                sms_cmd16_L_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexE)) {
                                sms_cmd16_L_bit0.setChecked(false);
                                sms_cmd16_L_bit1.setChecked(true);
                                sms_cmd16_L_bit2.setChecked(true);
                                sms_cmd16_L_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 1))).equalsIgnoreCase(hexF)) {
                                sms_cmd16_L_bit0.setChecked(true);
                                sms_cmd16_L_bit1.setChecked(true);
                                sms_cmd16_L_bit2.setChecked(true);
                                sms_cmd16_L_bit3.setChecked(true);
                            }
                            /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                            /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexZero)) {
                                sms_cmd16_L_bit4.setChecked(false);
                                sms_cmd16_L_bit5.setChecked(false);
                                sms_cmd16_L_bit6.setChecked(false);
                                sms_cmd16_L_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexOne)) {
                                sms_cmd16_L_bit4.setChecked(true);
                                sms_cmd16_L_bit5.setChecked(false);
                                sms_cmd16_L_bit6.setChecked(false);
                                sms_cmd16_L_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexTwo)) {
                                sms_cmd16_L_bit4.setChecked(false);
                                sms_cmd16_L_bit5.setChecked(true);
                                sms_cmd16_L_bit6.setChecked(false);
                                sms_cmd16_L_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexThree)) {
                                sms_cmd16_L_bit4.setChecked(true);
                                sms_cmd16_L_bit5.setChecked(true);
                                sms_cmd16_L_bit6.setChecked(false);
                                sms_cmd16_L_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexFour)) {
                                sms_cmd16_L_bit4.setChecked(false);
                                sms_cmd16_L_bit5.setChecked(false);
                                sms_cmd16_L_bit6.setChecked(true);
                                sms_cmd16_L_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexFive)) {
                                sms_cmd16_L_bit4.setChecked(true);
                                sms_cmd16_L_bit5.setChecked(false);
                                sms_cmd16_L_bit6.setChecked(true);
                                sms_cmd16_L_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexSix)) {
                                sms_cmd16_L_bit4.setChecked(false);
                                sms_cmd16_L_bit5.setChecked(true);
                                sms_cmd16_L_bit6.setChecked(true);
                                sms_cmd16_L_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexSeven)) {
                                sms_cmd16_L_bit4.setChecked(true);
                                sms_cmd16_L_bit5.setChecked(true);
                                sms_cmd16_L_bit6.setChecked(true);
                                sms_cmd16_L_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexEight)) {
                                sms_cmd16_L_bit4.setChecked(false);
                                sms_cmd16_L_bit5.setChecked(false);
                                sms_cmd16_L_bit6.setChecked(false);
                                sms_cmd16_L_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexNine)) {
                                sms_cmd16_L_bit4.setChecked(true);
                                sms_cmd16_L_bit5.setChecked(false);
                                sms_cmd16_L_bit6.setChecked(false);
                                sms_cmd16_L_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexA)) {
                                sms_cmd16_L_bit4.setChecked(false);
                                sms_cmd16_L_bit5.setChecked(true);
                                sms_cmd16_L_bit6.setChecked(false);
                                sms_cmd16_L_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexB)) {
                                sms_cmd16_L_bit4.setChecked(true);
                                sms_cmd16_L_bit5.setChecked(true);
                                sms_cmd16_L_bit6.setChecked(false);
                                sms_cmd16_L_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexC)) {
                                sms_cmd16_L_bit4.setChecked(false);
                                sms_cmd16_L_bit5.setChecked(false);
                                sms_cmd16_L_bit6.setChecked(true);
                                sms_cmd16_L_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexD)) {
                                sms_cmd16_L_bit4.setChecked(true);
                                sms_cmd16_L_bit5.setChecked(false);
                                sms_cmd16_L_bit6.setChecked(true);
                                sms_cmd16_L_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexE)) {
                                sms_cmd16_L_bit4.setChecked(false);
                                sms_cmd16_L_bit5.setChecked(true);
                                sms_cmd16_L_bit6.setChecked(true);
                                sms_cmd16_L_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 2))).equalsIgnoreCase(hexF)) {
                                sms_cmd16_L_bit4.setChecked(true);
                                sms_cmd16_L_bit5.setChecked(true);
                                sms_cmd16_L_bit6.setChecked(true);
                                sms_cmd16_L_bit7.setChecked(true);
                            }
                            /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                            /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexZero)) {
                                sms_cmd16_H_bit0.setChecked(false);
                                sms_cmd16_H_bit1.setChecked(false);
                                sms_cmd16_H_bit2.setChecked(false);
                                sms_cmd16_H_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexOne)) {
                                sms_cmd16_H_bit0.setChecked(true);
                                sms_cmd16_H_bit1.setChecked(false);
                                sms_cmd16_H_bit2.setChecked(false);
                                sms_cmd16_H_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexTwo)) {
                                sms_cmd16_H_bit0.setChecked(false);
                                sms_cmd16_H_bit1.setChecked(true);
                                sms_cmd16_H_bit2.setChecked(false);
                                sms_cmd16_H_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexThree)) {
                                sms_cmd16_H_bit0.setChecked(true);
                                sms_cmd16_H_bit1.setChecked(true);
                                sms_cmd16_H_bit2.setChecked(false);
                                sms_cmd16_H_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexFour)) {
                                sms_cmd16_H_bit0.setChecked(false);
                                sms_cmd16_H_bit1.setChecked(false);
                                sms_cmd16_H_bit2.setChecked(true);
                                sms_cmd16_H_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexFive)) {
                                sms_cmd16_H_bit0.setChecked(true);
                                sms_cmd16_H_bit1.setChecked(false);
                                sms_cmd16_H_bit2.setChecked(true);
                                sms_cmd16_H_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexSix)) {
                                sms_cmd16_H_bit0.setChecked(false);
                                sms_cmd16_H_bit1.setChecked(true);
                                sms_cmd16_H_bit2.setChecked(true);
                                sms_cmd16_H_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexSeven)) {
                                sms_cmd16_H_bit0.setChecked(true);
                                sms_cmd16_H_bit1.setChecked(true);
                                sms_cmd16_H_bit2.setChecked(true);
                                sms_cmd16_H_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexEight)) {
                                sms_cmd16_H_bit0.setChecked(false);
                                sms_cmd16_H_bit1.setChecked(false);
                                sms_cmd16_H_bit2.setChecked(false);
                                sms_cmd16_H_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexNine)) {
                                sms_cmd16_H_bit0.setChecked(true);
                                sms_cmd16_H_bit1.setChecked(false);
                                sms_cmd16_H_bit2.setChecked(false);
                                sms_cmd16_H_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexA)) {
                                sms_cmd16_H_bit0.setChecked(false);
                                sms_cmd16_H_bit1.setChecked(true);
                                sms_cmd16_H_bit2.setChecked(false);
                                sms_cmd16_H_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexB)) {
                                sms_cmd16_H_bit0.setChecked(true);
                                sms_cmd16_H_bit1.setChecked(true);
                                sms_cmd16_H_bit2.setChecked(false);
                                sms_cmd16_H_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexC)) {
                                sms_cmd16_H_bit0.setChecked(false);
                                sms_cmd16_H_bit1.setChecked(false);
                                sms_cmd16_H_bit2.setChecked(true);
                                sms_cmd16_H_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexD)) {
                                sms_cmd16_H_bit0.setChecked(true);
                                sms_cmd16_H_bit1.setChecked(false);
                                sms_cmd16_H_bit2.setChecked(true);
                                sms_cmd16_H_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexE)) {
                                sms_cmd16_H_bit0.setChecked(false);
                                sms_cmd16_H_bit1.setChecked(true);
                                sms_cmd16_H_bit2.setChecked(true);
                                sms_cmd16_H_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 3))).equalsIgnoreCase(hexF)) {
                                sms_cmd16_H_bit0.setChecked(true);
                                sms_cmd16_H_bit1.setChecked(true);
                                sms_cmd16_H_bit2.setChecked(true);
                                sms_cmd16_H_bit3.setChecked(true);
                            }
                            /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                            /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexZero)) {
                                sms_cmd16_H_bit4.setChecked(false);
                                sms_cmd16_H_bit5.setChecked(false);
                                sms_cmd16_H_bit6.setChecked(false);
                                sms_cmd16_H_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexOne)) {
                                sms_cmd16_H_bit4.setChecked(true);
                                sms_cmd16_H_bit5.setChecked(false);
                                sms_cmd16_H_bit6.setChecked(false);
                                sms_cmd16_H_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexTwo)) {
                                sms_cmd16_H_bit4.setChecked(false);
                                sms_cmd16_H_bit5.setChecked(true);
                                sms_cmd16_H_bit6.setChecked(false);
                                sms_cmd16_H_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexThree)) {
                                sms_cmd16_H_bit4.setChecked(true);
                                sms_cmd16_H_bit5.setChecked(true);
                                sms_cmd16_H_bit6.setChecked(false);
                                sms_cmd16_H_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexFour)) {
                                sms_cmd16_H_bit4.setChecked(false);
                                sms_cmd16_H_bit5.setChecked(false);
                                sms_cmd16_H_bit6.setChecked(true);
                                sms_cmd16_H_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexFive)) {
                                sms_cmd16_H_bit4.setChecked(true);
                                sms_cmd16_H_bit5.setChecked(false);
                                sms_cmd16_H_bit6.setChecked(true);
                                sms_cmd16_H_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexSix)) {
                                sms_cmd16_H_bit4.setChecked(false);
                                sms_cmd16_H_bit5.setChecked(true);
                                sms_cmd16_H_bit6.setChecked(true);
                                sms_cmd16_H_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexSeven)) {
                                sms_cmd16_H_bit4.setChecked(true);
                                sms_cmd16_H_bit5.setChecked(true);
                                sms_cmd16_H_bit6.setChecked(true);
                                sms_cmd16_H_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexEight)) {
                                sms_cmd16_H_bit4.setChecked(false);
                                sms_cmd16_H_bit5.setChecked(false);
                                sms_cmd16_H_bit6.setChecked(false);
                                sms_cmd16_H_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexNine)) {
                                sms_cmd16_H_bit4.setChecked(true);
                                sms_cmd16_H_bit5.setChecked(false);
                                sms_cmd16_H_bit6.setChecked(false);
                                sms_cmd16_H_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexA)) {
                                sms_cmd16_H_bit4.setChecked(false);
                                sms_cmd16_H_bit5.setChecked(true);
                                sms_cmd16_H_bit6.setChecked(false);
                                sms_cmd16_H_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexB)) {
                                sms_cmd16_H_bit4.setChecked(true);
                                sms_cmd16_H_bit5.setChecked(true);
                                sms_cmd16_H_bit6.setChecked(false);
                                sms_cmd16_H_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexC)) {
                                sms_cmd16_H_bit4.setChecked(false);
                                sms_cmd16_H_bit5.setChecked(false);
                                sms_cmd16_H_bit6.setChecked(true);
                                sms_cmd16_H_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexD)) {
                                sms_cmd16_H_bit4.setChecked(true);
                                sms_cmd16_H_bit5.setChecked(false);
                                sms_cmd16_H_bit6.setChecked(true);
                                sms_cmd16_H_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexE)) {
                                sms_cmd16_H_bit4.setChecked(false);
                                sms_cmd16_H_bit5.setChecked(true);
                                sms_cmd16_H_bit6.setChecked(true);
                                sms_cmd16_H_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 4))).equalsIgnoreCase(hexF)) {
                                sms_cmd16_H_bit4.setChecked(true);
                                sms_cmd16_H_bit5.setChecked(true);
                                sms_cmd16_H_bit6.setChecked(true);
                                sms_cmd16_H_bit7.setChecked(true);
                            }
                            /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                            /******************************** BASED ON FIFTH CHARACTER FROM RIGHT ***********************/
                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexZero)) {
                                sms_cmd16_L_M_bit0.setChecked(false);
                                sms_cmd16_L_M_bit1.setChecked(false);
                                sms_cmd16_L_M_bit2.setChecked(false);
                                sms_cmd16_L_M_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexOne)) {
                                sms_cmd16_L_M_bit0.setChecked(true);
                                sms_cmd16_L_M_bit1.setChecked(false);
                                sms_cmd16_L_M_bit2.setChecked(false);
                                sms_cmd16_L_M_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexTwo)) {
                                sms_cmd16_L_M_bit0.setChecked(false);
                                sms_cmd16_L_M_bit1.setChecked(true);
                                sms_cmd16_L_M_bit2.setChecked(false);
                                sms_cmd16_L_M_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexThree)) {
                                sms_cmd16_L_M_bit0.setChecked(true);
                                sms_cmd16_L_M_bit1.setChecked(true);
                                sms_cmd16_L_M_bit2.setChecked(false);
                                sms_cmd16_L_M_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexFour)) {
                                sms_cmd16_L_M_bit0.setChecked(false);
                                sms_cmd16_L_M_bit1.setChecked(false);
                                sms_cmd16_L_M_bit2.setChecked(true);
                                sms_cmd16_L_M_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexFive)) {
                                sms_cmd16_L_M_bit0.setChecked(true);
                                sms_cmd16_L_M_bit1.setChecked(false);
                                sms_cmd16_L_M_bit2.setChecked(true);
                                sms_cmd16_L_M_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexSix)) {
                                sms_cmd16_L_M_bit0.setChecked(false);
                                sms_cmd16_L_M_bit1.setChecked(true);
                                sms_cmd16_L_M_bit2.setChecked(true);
                                sms_cmd16_L_M_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexSeven)) {
                                sms_cmd16_L_M_bit0.setChecked(true);
                                sms_cmd16_L_M_bit1.setChecked(true);
                                sms_cmd16_L_M_bit2.setChecked(true);
                                sms_cmd16_L_M_bit3.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexEight)) {
                                sms_cmd16_L_M_bit0.setChecked(false);
                                sms_cmd16_L_M_bit1.setChecked(false);
                                sms_cmd16_L_M_bit2.setChecked(false);
                                sms_cmd16_L_M_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexNine)) {
                                sms_cmd16_L_M_bit0.setChecked(true);
                                sms_cmd16_L_M_bit1.setChecked(false);
                                sms_cmd16_L_M_bit2.setChecked(false);
                                sms_cmd16_L_M_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexA)) {
                                sms_cmd16_L_M_bit0.setChecked(false);
                                sms_cmd16_L_M_bit1.setChecked(true);
                                sms_cmd16_L_M_bit2.setChecked(false);
                                sms_cmd16_L_M_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexB)) {
                                sms_cmd16_L_M_bit0.setChecked(true);
                                sms_cmd16_L_M_bit1.setChecked(true);
                                sms_cmd16_L_M_bit2.setChecked(false);
                                sms_cmd16_L_M_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexC)) {
                                sms_cmd16_L_M_bit0.setChecked(false);
                                sms_cmd16_L_M_bit1.setChecked(false);
                                sms_cmd16_L_M_bit2.setChecked(true);
                                sms_cmd16_L_M_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexD)) {
                                sms_cmd16_L_M_bit0.setChecked(true);
                                sms_cmd16_L_M_bit1.setChecked(false);
                                sms_cmd16_L_M_bit2.setChecked(true);
                                sms_cmd16_L_M_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexE)) {
                                sms_cmd16_L_M_bit0.setChecked(false);
                                sms_cmd16_L_M_bit1.setChecked(true);
                                sms_cmd16_L_M_bit2.setChecked(true);
                                sms_cmd16_L_M_bit3.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 5))).equalsIgnoreCase(hexF)) {
                                sms_cmd16_L_M_bit0.setChecked(true);
                                sms_cmd16_L_M_bit1.setChecked(true);
                                sms_cmd16_L_M_bit2.setChecked(true);
                                sms_cmd16_L_M_bit3.setChecked(true);
                            }
                            /***************************** END OF FIFTH CHARACTER FROM RIGHT **************************/
                            /******************************** BASED ON SIXTH CHARACTER FROM RIGHT ***********************/
                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexZero)) {
                                sms_cmd16_L_M_bit4.setChecked(false);
                                sms_cmd16_L_M_bit5.setChecked(false);
                                sms_cmd16_L_M_bit6.setChecked(false);
                                sms_cmd16_L_M_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexOne)) {
                                sms_cmd16_L_M_bit4.setChecked(true);
                                sms_cmd16_L_M_bit5.setChecked(false);
                                sms_cmd16_L_M_bit6.setChecked(false);
                                sms_cmd16_L_M_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexTwo)) {
                                sms_cmd16_L_M_bit4.setChecked(false);
                                sms_cmd16_L_M_bit5.setChecked(true);
                                sms_cmd16_L_M_bit6.setChecked(false);
                                sms_cmd16_L_M_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexThree)) {
                                sms_cmd16_L_M_bit4.setChecked(true);
                                sms_cmd16_L_M_bit5.setChecked(true);
                                sms_cmd16_L_M_bit6.setChecked(false);
                                sms_cmd16_L_M_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexFour)) {
                                sms_cmd16_L_M_bit4.setChecked(false);
                                sms_cmd16_L_M_bit5.setChecked(false);
                                sms_cmd16_L_M_bit6.setChecked(true);
                                sms_cmd16_L_M_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexFive)) {
                                sms_cmd16_L_M_bit4.setChecked(true);
                                sms_cmd16_L_M_bit5.setChecked(false);
                                sms_cmd16_L_M_bit6.setChecked(true);
                                sms_cmd16_L_M_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexSix)) {
                                sms_cmd16_L_M_bit4.setChecked(false);
                                sms_cmd16_L_M_bit5.setChecked(true);
                                sms_cmd16_L_M_bit6.setChecked(true);
                                sms_cmd16_L_M_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexSeven)) {
                                sms_cmd16_L_M_bit4.setChecked(true);
                                sms_cmd16_L_M_bit5.setChecked(true);
                                sms_cmd16_L_M_bit6.setChecked(true);
                                sms_cmd16_L_M_bit7.setChecked(false);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexEight)) {
                                sms_cmd16_L_M_bit4.setChecked(false);
                                sms_cmd16_L_M_bit5.setChecked(false);
                                sms_cmd16_L_M_bit6.setChecked(false);
                                sms_cmd16_L_M_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexNine)) {
                                sms_cmd16_L_M_bit4.setChecked(true);
                                sms_cmd16_L_M_bit5.setChecked(false);
                                sms_cmd16_L_M_bit6.setChecked(false);
                                sms_cmd16_L_M_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexA)) {
                                sms_cmd16_L_M_bit4.setChecked(false);
                                sms_cmd16_L_M_bit5.setChecked(true);
                                sms_cmd16_L_M_bit6.setChecked(false);
                                sms_cmd16_L_M_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexB)) {
                                sms_cmd16_L_M_bit4.setChecked(true);
                                sms_cmd16_L_M_bit5.setChecked(true);
                                sms_cmd16_L_M_bit6.setChecked(false);
                                sms_cmd16_L_M_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexC)) {
                                sms_cmd16_L_M_bit4.setChecked(false);
                                sms_cmd16_L_M_bit5.setChecked(false);
                                sms_cmd16_L_M_bit6.setChecked(true);
                                sms_cmd16_L_M_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexD)) {
                                sms_cmd16_L_M_bit4.setChecked(true);
                                sms_cmd16_L_M_bit5.setChecked(false);
                                sms_cmd16_L_M_bit6.setChecked(true);
                                sms_cmd16_L_M_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexE)) {
                                sms_cmd16_L_M_bit4.setChecked(false);
                                sms_cmd16_L_M_bit5.setChecked(true);
                                sms_cmd16_L_M_bit6.setChecked(true);
                                sms_cmd16_L_M_bit7.setChecked(true);
                            }

                            if (String.valueOf(stringSplit.charAt((stringSplit.length() - 6))).equalsIgnoreCase(hexF)) {
                                sms_cmd16_L_M_bit4.setChecked(true);
                                sms_cmd16_L_M_bit5.setChecked(true);
                                sms_cmd16_L_M_bit6.setChecked(true);
                                sms_cmd16_L_M_bit7.setChecked(true);
                            }
                            /***************************** END OF SIXTH CHARACTER FROM RIGHT **************************/
                        }
                    }
                }
            }
        }
    }//end of displayFromCommand16Onwards()

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sms_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                displayScreenInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()
    */
    public void displayScreenInfo()
    {
        Intent intent = new Intent(this, InfoSMSActivity.class);
        startActivity(intent);
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
                    SendSMSActivity3.this.getReceivedSMS(theString);
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
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
        List<String> THENUMBER = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM CUSTOMER_CLIENT_DATA",null);
        THENUMBER.clear();
        if (cursor.moveToFirst())
        {
            do
            {
                THENUMBER.add(cursor.getString(cursor.getColumnIndex("CUSTOMER_CLIENT_NUMBER")));
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
                        customerSMSReceiverControllerDB = new CustomerSMSReceiverControllerDB(this);
                        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
                        theQuery = "CREATE TABLE IF NOT EXISTS CUSTOMER_SMS(Id INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, ADDRESS TEXT, CONTENT TEXT);";
                        theSqLiteDatabase.execSQL(theQuery);
                        theSqLiteDatabase.execSQL("INSERT INTO CUSTOMER_SMS(DATE, ADDRESS, CONTENT) VALUES('"+date+"','"+cellNumber+"','"+extractedMessage+"')");
                        Toast.makeText(getApplicationContext(), "DATA IS STORED",Toast.LENGTH_LONG).show();
                    }//end of if{}
                }//end of for()

                //INSERTING INTO DB AND THEN EXTRACTING IT TO DISPLAY ON UI
                for(int i=0; i<THENUMBER.size(); i++)
                {
                    if(THENUMBER.get(i).equalsIgnoreCase(cellNumber))
                    {
                        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM CUSTOMER_SMS WHERE DATE='"+date+"'",null);
                        clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM CUSTOMER_SMS WHERE ADDRESS='"+cellNumber+"'",null);
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
                            String str1 = sb0.toString();
                            //adminText.setText(str1);
                            String decodeChoice = str1;
                            /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
                            String theData[] = decodeChoice.split("\n");
                            String stringSplit = theData[(theData.length-1)];
                            countDown.setVisibility(View.VISIBLE);
                            for(int s=0; s<stringSplit.length(); s++)
                            {
                                //if(!String.valueOf(stringSplit.isEmpty())
                                if(!(stringSplit.isEmpty()))
                                {
                                    //DISPLAY FOR MAIN CARD INPUT//
                                    if(String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("7"))
                                    {
                                        displayTimer(displayTime);
                                        mciTargetView.getParent().requestChildFocus(mciTargetView,mciTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd7_bit0.setChecked(true);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(true);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd7_bit0.setChecked(true);
                                            sms_cmd7_bit1.setChecked(true);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(true);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd7_bit0.setChecked(true);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(true);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(true);
                                            sms_cmd7_bit2.setChecked(true);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd7_bit0.setChecked(true);
                                            sms_cmd7_bit1.setChecked(true);
                                            sms_cmd7_bit2.setChecked(true);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd7_bit0.setChecked(true);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(true);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd7_bit0.setChecked(false);
                                            sms_cmd7_bit1.setChecked(false);
                                            sms_cmd7_bit2.setChecked(false);
                                            sms_cmd7_bit3.setChecked(false);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd7_bit4.setChecked(true);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(true);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd7_bit4.setChecked(true);
                                            sms_cmd7_bit5.setChecked(true);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(true);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd7_bit4.setChecked(true);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(true);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(true);
                                            sms_cmd7_bit6.setChecked(true);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd7_bit4.setChecked(true);
                                            sms_cmd7_bit5.setChecked(true);
                                            sms_cmd7_bit6.setChecked(true);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd7_bit4.setChecked(true);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(true);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd7_bit4.setChecked(false);
                                            sms_cmd7_bit5.setChecked(false);
                                            sms_cmd7_bit6.setChecked(false);
                                            sms_cmd7_bit7.setChecked(false);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR MAIN CARD TERMINAL INPUT//
                                    if(String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("8"))
                                    {
                                        displayTimer(displayTime);
                                        mctiTargetView.getParent().requestChildFocus(mctiTargetView,mctiTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd8_bit0.setChecked(false);
                                            sms_cmd8_bit1.setChecked(false);
                                            sms_cmd8_bit2.setChecked(false);
                                            sms_cmd8_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd8_bit0.setChecked(true);
                                            sms_cmd8_bit1.setChecked(false);
                                            sms_cmd8_bit2.setChecked(false);
                                            sms_cmd8_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd8_bit0.setChecked(false);
                                            sms_cmd8_bit1.setChecked(true);
                                            sms_cmd8_bit2.setChecked(false);
                                            sms_cmd8_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd8_bit0.setChecked(true);
                                            sms_cmd8_bit1.setChecked(true);
                                            sms_cmd8_bit2.setChecked(false);
                                            sms_cmd8_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd8_bit0.setChecked(false);
                                            sms_cmd8_bit1.setChecked(false);
                                            sms_cmd8_bit2.setChecked(true);
                                            sms_cmd8_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd8_bit0.setChecked(true);
                                            sms_cmd8_bit1.setChecked(false);
                                            sms_cmd8_bit2.setChecked(true);
                                            sms_cmd8_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd8_bit0.setChecked(false);
                                            sms_cmd8_bit1.setChecked(true);
                                            sms_cmd8_bit2.setChecked(true);
                                            sms_cmd8_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd8_bit0.setChecked(true);
                                            sms_cmd8_bit1.setChecked(true);
                                            sms_cmd8_bit2.setChecked(true);
                                            sms_cmd8_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd8_bit0.setChecked(false);
                                            sms_cmd8_bit1.setChecked(false);
                                            sms_cmd8_bit2.setChecked(false);
                                            sms_cmd8_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd8_bit0.setChecked(true);
                                            sms_cmd8_bit1.setChecked(false);
                                            sms_cmd8_bit2.setChecked(false);
                                            sms_cmd8_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd8_bit0.setChecked(false);
                                            sms_cmd8_bit1.setChecked(true);
                                            sms_cmd8_bit2.setChecked(false);
                                            sms_cmd8_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd8_bit0.setChecked(true);
                                            sms_cmd8_bit1.setChecked(true);
                                            sms_cmd8_bit2.setChecked(false);
                                            sms_cmd8_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd8_bit0.setChecked(false);
                                            sms_cmd8_bit1.setChecked(false);
                                            sms_cmd8_bit2.setChecked(true);
                                            sms_cmd8_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd8_bit0.setChecked(true);
                                            sms_cmd8_bit1.setChecked(false);
                                            sms_cmd8_bit2.setChecked(true);
                                            sms_cmd8_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd8_bit0.setChecked(false);
                                            sms_cmd8_bit1.setChecked(true);
                                            sms_cmd8_bit2.setChecked(true);
                                            sms_cmd8_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd8_bit0.setChecked(true);
                                            sms_cmd8_bit1.setChecked(true);
                                            sms_cmd8_bit2.setChecked(true);
                                            sms_cmd8_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd8_bit4.setChecked(false);
                                            sms_cmd8_bit5.setChecked(false);
                                            sms_cmd8_bit6.setChecked(false);
                                            sms_cmd8_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd8_bit4.setChecked(true);
                                            sms_cmd8_bit5.setChecked(false);
                                            sms_cmd8_bit6.setChecked(false);
                                            sms_cmd8_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd8_bit4.setChecked(false);
                                            sms_cmd8_bit5.setChecked(true);
                                            sms_cmd8_bit6.setChecked(false);
                                            sms_cmd8_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd8_bit4.setChecked(true);
                                            sms_cmd8_bit5.setChecked(true);
                                            sms_cmd8_bit6.setChecked(false);
                                            sms_cmd8_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd8_bit4.setChecked(false);
                                            sms_cmd8_bit5.setChecked(false);
                                            sms_cmd8_bit6.setChecked(true);
                                            sms_cmd8_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd8_bit4.setChecked(true);
                                            sms_cmd8_bit5.setChecked(false);
                                            sms_cmd8_bit6.setChecked(true);
                                            sms_cmd8_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd8_bit4.setChecked(false);
                                            sms_cmd8_bit5.setChecked(true);
                                            sms_cmd8_bit6.setChecked(true);
                                            sms_cmd8_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd8_bit4.setChecked(true);
                                            sms_cmd8_bit5.setChecked(true);
                                            sms_cmd8_bit6.setChecked(true);
                                            sms_cmd8_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd8_bit4.setChecked(false);
                                            sms_cmd8_bit5.setChecked(false);
                                            sms_cmd8_bit6.setChecked(false);
                                            sms_cmd8_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd8_bit4.setChecked(true);
                                            sms_cmd8_bit5.setChecked(false);
                                            sms_cmd8_bit6.setChecked(false);
                                            sms_cmd8_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd8_bit4.setChecked(false);
                                            sms_cmd8_bit5.setChecked(true);
                                            sms_cmd8_bit6.setChecked(false);
                                            sms_cmd8_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd8_bit4.setChecked(true);
                                            sms_cmd8_bit5.setChecked(true);
                                            sms_cmd8_bit6.setChecked(false);
                                            sms_cmd8_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd8_bit4.setChecked(false);
                                            sms_cmd8_bit5.setChecked(false);
                                            sms_cmd8_bit6.setChecked(true);
                                            sms_cmd8_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd8_bit4.setChecked(true);
                                            sms_cmd8_bit5.setChecked(false);
                                            sms_cmd8_bit6.setChecked(true);
                                            sms_cmd8_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd8_bit4.setChecked(false);
                                            sms_cmd8_bit5.setChecked(true);
                                            sms_cmd8_bit6.setChecked(true);
                                            sms_cmd8_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd8_bit4.setChecked(true);
                                            sms_cmd8_bit5.setChecked(true);
                                            sms_cmd8_bit6.setChecked(true);
                                            sms_cmd8_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd8_H_bit0.setChecked(false);
                                            sms_cmd8_H_bit1.setChecked(false);
                                            sms_cmd8_H_bit2.setChecked(false);
                                            sms_cmd8_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd8_H_bit0.setChecked(true);
                                            sms_cmd8_H_bit1.setChecked(false);
                                            sms_cmd8_H_bit2.setChecked(false);
                                            sms_cmd8_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd8_H_bit0.setChecked(false);
                                            sms_cmd8_H_bit1.setChecked(true);
                                            sms_cmd8_H_bit2.setChecked(false);
                                            sms_cmd8_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd8_H_bit0.setChecked(true);
                                            sms_cmd8_H_bit1.setChecked(true);
                                            sms_cmd8_H_bit2.setChecked(false);
                                            sms_cmd8_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd8_H_bit0.setChecked(false);
                                            sms_cmd8_H_bit1.setChecked(false);
                                            sms_cmd8_H_bit2.setChecked(true);
                                            sms_cmd8_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd8_H_bit0.setChecked(true);
                                            sms_cmd8_H_bit1.setChecked(false);
                                            sms_cmd8_H_bit2.setChecked(true);
                                            sms_cmd8_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd8_H_bit0.setChecked(false);
                                            sms_cmd8_H_bit1.setChecked(true);
                                            sms_cmd8_H_bit2.setChecked(true);
                                            sms_cmd8_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd8_H_bit0.setChecked(true);
                                            sms_cmd8_H_bit1.setChecked(true);
                                            sms_cmd8_H_bit2.setChecked(true);
                                            sms_cmd8_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd8_H_bit0.setChecked(false);
                                            sms_cmd8_H_bit1.setChecked(false);
                                            sms_cmd8_H_bit2.setChecked(false);
                                            sms_cmd8_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd8_H_bit0.setChecked(true);
                                            sms_cmd8_H_bit1.setChecked(false);
                                            sms_cmd8_H_bit2.setChecked(false);
                                            sms_cmd8_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd8_H_bit0.setChecked(false);
                                            sms_cmd8_H_bit1.setChecked(true);
                                            sms_cmd8_H_bit2.setChecked(false);
                                            sms_cmd8_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd8_H_bit0.setChecked(true);
                                            sms_cmd8_H_bit1.setChecked(true);
                                            sms_cmd8_H_bit2.setChecked(false);
                                            sms_cmd8_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd8_H_bit0.setChecked(false);
                                            sms_cmd8_H_bit1.setChecked(false);
                                            sms_cmd8_H_bit2.setChecked(true);
                                            sms_cmd8_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd8_H_bit0.setChecked(true);
                                            sms_cmd8_H_bit1.setChecked(false);
                                            sms_cmd8_H_bit2.setChecked(true);
                                            sms_cmd8_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd8_H_bit0.setChecked(false);
                                            sms_cmd8_H_bit1.setChecked(true);
                                            sms_cmd8_H_bit2.setChecked(true);
                                            sms_cmd8_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd8_H_bit0.setChecked(true);
                                            sms_cmd8_H_bit1.setChecked(true);
                                            sms_cmd8_H_bit2.setChecked(true);
                                            sms_cmd8_H_bit3.setChecked(true);
                                        }
                                        /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd8_H_bit4.setChecked(false);
                                            sms_cmd8_H_bit5.setChecked(false);
                                            sms_cmd8_H_bit6.setChecked(false);
                                            sms_cmd8_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd8_H_bit4.setChecked(true);
                                            sms_cmd8_H_bit5.setChecked(false);
                                            sms_cmd8_H_bit6.setChecked(false);
                                            sms_cmd8_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd8_H_bit4.setChecked(false);
                                            sms_cmd8_H_bit5.setChecked(true);
                                            sms_cmd8_H_bit6.setChecked(false);
                                            sms_cmd8_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd8_H_bit4.setChecked(true);
                                            sms_cmd8_H_bit5.setChecked(true);
                                            sms_cmd8_H_bit6.setChecked(false);
                                            sms_cmd8_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd8_H_bit4.setChecked(false);
                                            sms_cmd8_H_bit5.setChecked(false);
                                            sms_cmd8_H_bit6.setChecked(true);
                                            sms_cmd8_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd8_H_bit4.setChecked(true);
                                            sms_cmd8_H_bit5.setChecked(false);
                                            sms_cmd8_H_bit6.setChecked(true);
                                            sms_cmd8_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd8_H_bit4.setChecked(false);
                                            sms_cmd8_H_bit5.setChecked(true);
                                            sms_cmd8_H_bit6.setChecked(true);
                                            sms_cmd8_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd8_H_bit4.setChecked(true);
                                            sms_cmd8_H_bit5.setChecked(true);
                                            sms_cmd8_H_bit6.setChecked(true);
                                            sms_cmd8_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd8_H_bit4.setChecked(false);
                                            sms_cmd8_H_bit5.setChecked(false);
                                            sms_cmd8_H_bit6.setChecked(false);
                                            sms_cmd8_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd8_H_bit4.setChecked(true);
                                            sms_cmd8_H_bit5.setChecked(false);
                                            sms_cmd8_H_bit6.setChecked(false);
                                            sms_cmd8_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd8_H_bit4.setChecked(false);
                                            sms_cmd8_H_bit5.setChecked(true);
                                            sms_cmd8_H_bit6.setChecked(false);
                                            sms_cmd8_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd8_H_bit4.setChecked(true);
                                            sms_cmd8_H_bit5.setChecked(true);
                                            sms_cmd8_H_bit6.setChecked(false);
                                            sms_cmd8_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd8_H_bit4.setChecked(false);
                                            sms_cmd8_H_bit5.setChecked(false);
                                            sms_cmd8_H_bit6.setChecked(true);
                                            sms_cmd8_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd8_H_bit4.setChecked(true);
                                            sms_cmd8_H_bit5.setChecked(false);
                                            sms_cmd8_H_bit6.setChecked(true);
                                            sms_cmd8_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd8_H_bit4.setChecked(false);
                                            sms_cmd8_H_bit5.setChecked(true);
                                            sms_cmd8_H_bit6.setChecked(true);
                                            sms_cmd8_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd8_H_bit4.setChecked(true);
                                            sms_cmd8_H_bit5.setChecked(true);
                                            sms_cmd8_H_bit6.setChecked(true);
                                            sms_cmd8_H_bit7.setChecked(true);
                                        }
                                        /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR MAIN CARD OUTPUT//
                                    if(String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("9"))
                                    {
                                        displayTimer(displayTime);
                                        mcoTargetView.getParent().requestChildFocus(mcoTargetView,mcoTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd9_L_bit0.setChecked(false);
                                            sms_cmd9_L_bit1.setChecked(false);
                                            sms_cmd9_L_bit2.setChecked(false);
                                            sms_cmd9_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd9_L_bit0.setChecked(true);
                                            sms_cmd9_L_bit1.setChecked(false);
                                            sms_cmd9_L_bit2.setChecked(false);
                                            sms_cmd9_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd9_L_bit0.setChecked(false);
                                            sms_cmd9_L_bit1.setChecked(true);
                                            sms_cmd9_L_bit2.setChecked(false);
                                            sms_cmd9_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd9_L_bit0.setChecked(true);
                                            sms_cmd9_L_bit1.setChecked(true);
                                            sms_cmd9_L_bit2.setChecked(false);
                                            sms_cmd9_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd9_L_bit0.setChecked(false);
                                            sms_cmd9_L_bit1.setChecked(false);
                                            sms_cmd9_L_bit2.setChecked(true);
                                            sms_cmd9_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd9_L_bit0.setChecked(true);
                                            sms_cmd9_L_bit1.setChecked(false);
                                            sms_cmd9_L_bit2.setChecked(true);
                                            sms_cmd9_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd9_L_bit0.setChecked(false);
                                            sms_cmd9_L_bit1.setChecked(true);
                                            sms_cmd9_L_bit2.setChecked(true);
                                            sms_cmd9_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd9_L_bit0.setChecked(true);
                                            sms_cmd9_L_bit1.setChecked(true);
                                            sms_cmd9_L_bit2.setChecked(true);
                                            sms_cmd9_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd9_L_bit0.setChecked(false);
                                            sms_cmd9_L_bit1.setChecked(false);
                                            sms_cmd9_L_bit2.setChecked(false);
                                            sms_cmd9_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd9_L_bit0.setChecked(true);
                                            sms_cmd9_L_bit1.setChecked(false);
                                            sms_cmd9_L_bit2.setChecked(false);
                                            sms_cmd9_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd9_L_bit0.setChecked(false);
                                            sms_cmd9_L_bit1.setChecked(true);
                                            sms_cmd9_L_bit2.setChecked(false);
                                            sms_cmd9_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd9_L_bit0.setChecked(true);
                                            sms_cmd9_L_bit1.setChecked(true);
                                            sms_cmd9_L_bit2.setChecked(false);
                                            sms_cmd9_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd9_L_bit0.setChecked(false);
                                            sms_cmd9_L_bit1.setChecked(false);
                                            sms_cmd9_L_bit2.setChecked(true);
                                            sms_cmd9_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd9_L_bit0.setChecked(true);
                                            sms_cmd9_L_bit1.setChecked(false);
                                            sms_cmd9_L_bit2.setChecked(true);
                                            sms_cmd9_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd9_L_bit0.setChecked(false);
                                            sms_cmd9_L_bit1.setChecked(true);
                                            sms_cmd9_L_bit2.setChecked(true);
                                            sms_cmd9_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd9_L_bit0.setChecked(true);
                                            sms_cmd9_L_bit1.setChecked(true);
                                            sms_cmd9_L_bit2.setChecked(true);
                                            sms_cmd9_L_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd9_L_bit4.setChecked(false);
                                            sms_cmd9_L_bit5.setChecked(false);
                                            sms_cmd9_L_bit6.setChecked(false);
                                            sms_cmd9_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd9_L_bit4.setChecked(true);
                                            sms_cmd9_L_bit5.setChecked(false);
                                            sms_cmd9_L_bit6.setChecked(false);
                                            sms_cmd9_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd9_L_bit4.setChecked(false);
                                            sms_cmd9_L_bit5.setChecked(true);
                                            sms_cmd9_L_bit6.setChecked(false);
                                            sms_cmd9_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd9_L_bit4.setChecked(true);
                                            sms_cmd9_L_bit5.setChecked(true);
                                            sms_cmd9_L_bit6.setChecked(false);
                                            sms_cmd9_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd9_L_bit4.setChecked(false);
                                            sms_cmd9_L_bit5.setChecked(false);
                                            sms_cmd9_L_bit6.setChecked(true);
                                            sms_cmd9_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd9_L_bit4.setChecked(true);
                                            sms_cmd9_L_bit5.setChecked(false);
                                            sms_cmd9_L_bit6.setChecked(true);
                                            sms_cmd9_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd9_L_bit4.setChecked(false);
                                            sms_cmd9_L_bit5.setChecked(true);
                                            sms_cmd9_L_bit6.setChecked(true);
                                            sms_cmd9_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd9_L_bit4.setChecked(true);
                                            sms_cmd9_L_bit5.setChecked(true);
                                            sms_cmd9_L_bit6.setChecked(true);
                                            sms_cmd9_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd9_L_bit4.setChecked(false);
                                            sms_cmd9_L_bit5.setChecked(false);
                                            sms_cmd9_L_bit6.setChecked(false);
                                            sms_cmd9_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd9_L_bit4.setChecked(true);
                                            sms_cmd9_L_bit5.setChecked(false);
                                            sms_cmd9_L_bit6.setChecked(false);
                                            sms_cmd9_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd9_L_bit4.setChecked(false);
                                            sms_cmd9_L_bit5.setChecked(true);
                                            sms_cmd9_L_bit6.setChecked(false);
                                            sms_cmd9_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd9_L_bit4.setChecked(true);
                                            sms_cmd9_L_bit5.setChecked(true);
                                            sms_cmd9_L_bit6.setChecked(false);
                                            sms_cmd9_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd9_L_bit4.setChecked(false);
                                            sms_cmd9_L_bit5.setChecked(false);
                                            sms_cmd9_L_bit6.setChecked(true);
                                            sms_cmd9_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd9_L_bit4.setChecked(true);
                                            sms_cmd9_L_bit5.setChecked(false);
                                            sms_cmd9_L_bit6.setChecked(true);
                                            sms_cmd9_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd9_L_bit4.setChecked(false);
                                            sms_cmd9_L_bit5.setChecked(true);
                                            sms_cmd9_L_bit6.setChecked(true);
                                            sms_cmd9_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd9_L_bit4.setChecked(true);
                                            sms_cmd9_L_bit5.setChecked(true);
                                            sms_cmd9_L_bit6.setChecked(true);
                                            sms_cmd9_L_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd9_H_bit0.setChecked(false);
                                            sms_cmd9_H_bit1.setChecked(false);
                                            sms_cmd9_H_bit2.setChecked(false);
                                            sms_cmd9_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd9_H_bit0.setChecked(true);
                                            sms_cmd9_H_bit1.setChecked(false);
                                            sms_cmd9_H_bit2.setChecked(false);
                                            sms_cmd9_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd9_H_bit0.setChecked(false);
                                            sms_cmd9_H_bit1.setChecked(true);
                                            sms_cmd9_H_bit2.setChecked(false);
                                            sms_cmd9_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd9_H_bit0.setChecked(true);
                                            sms_cmd9_H_bit1.setChecked(true);
                                            sms_cmd9_H_bit2.setChecked(false);
                                            sms_cmd9_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd9_H_bit0.setChecked(false);
                                            sms_cmd9_H_bit1.setChecked(false);
                                            sms_cmd9_H_bit2.setChecked(true);
                                            sms_cmd9_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd9_H_bit0.setChecked(true);
                                            sms_cmd9_H_bit1.setChecked(false);
                                            sms_cmd9_H_bit2.setChecked(true);
                                            sms_cmd9_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd9_H_bit0.setChecked(false);
                                            sms_cmd9_H_bit1.setChecked(true);
                                            sms_cmd9_H_bit2.setChecked(true);
                                            sms_cmd9_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd9_H_bit0.setChecked(true);
                                            sms_cmd9_H_bit1.setChecked(true);
                                            sms_cmd9_H_bit2.setChecked(true);
                                            sms_cmd9_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd9_H_bit0.setChecked(false);
                                            sms_cmd9_H_bit1.setChecked(false);
                                            sms_cmd9_H_bit2.setChecked(false);
                                            sms_cmd9_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd9_H_bit0.setChecked(true);
                                            sms_cmd9_H_bit1.setChecked(false);
                                            sms_cmd9_H_bit2.setChecked(false);
                                            sms_cmd9_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd9_H_bit0.setChecked(false);
                                            sms_cmd9_H_bit1.setChecked(true);
                                            sms_cmd9_H_bit2.setChecked(false);
                                            sms_cmd9_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd9_H_bit0.setChecked(true);
                                            sms_cmd9_H_bit1.setChecked(true);
                                            sms_cmd9_H_bit2.setChecked(false);
                                            sms_cmd9_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd9_H_bit0.setChecked(false);
                                            sms_cmd9_H_bit1.setChecked(false);
                                            sms_cmd9_H_bit2.setChecked(true);
                                            sms_cmd9_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd9_H_bit0.setChecked(true);
                                            sms_cmd9_H_bit1.setChecked(false);
                                            sms_cmd9_H_bit2.setChecked(true);
                                            sms_cmd9_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd9_H_bit0.setChecked(false);
                                            sms_cmd9_H_bit1.setChecked(true);
                                            sms_cmd9_H_bit2.setChecked(true);
                                            sms_cmd9_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd9_H_bit0.setChecked(true);
                                            sms_cmd9_H_bit1.setChecked(true);
                                            sms_cmd9_H_bit2.setChecked(true);
                                            sms_cmd9_H_bit3.setChecked(true);
                                        }
                                        /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd9_H_bit4.setChecked(false);
                                            sms_cmd9_H_bit5.setChecked(false);
                                            sms_cmd9_H_bit6.setChecked(false);
                                            sms_cmd9_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd9_H_bit4.setChecked(true);
                                            sms_cmd9_H_bit5.setChecked(false);
                                            sms_cmd9_H_bit6.setChecked(false);
                                            sms_cmd9_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd9_H_bit4.setChecked(false);
                                            sms_cmd9_H_bit5.setChecked(true);
                                            sms_cmd9_H_bit6.setChecked(false);
                                            sms_cmd9_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd9_H_bit4.setChecked(true);
                                            sms_cmd9_H_bit5.setChecked(true);
                                            sms_cmd9_H_bit6.setChecked(false);
                                            sms_cmd9_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd9_H_bit4.setChecked(false);
                                            sms_cmd9_H_bit5.setChecked(false);
                                            sms_cmd9_H_bit6.setChecked(true);
                                            sms_cmd9_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd9_H_bit4.setChecked(true);
                                            sms_cmd9_H_bit5.setChecked(false);
                                            sms_cmd9_H_bit6.setChecked(true);
                                            sms_cmd9_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd9_H_bit4.setChecked(false);
                                            sms_cmd9_H_bit5.setChecked(true);
                                            sms_cmd9_H_bit6.setChecked(true);
                                            sms_cmd9_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd9_H_bit4.setChecked(true);
                                            sms_cmd9_H_bit5.setChecked(true);
                                            sms_cmd9_H_bit6.setChecked(true);
                                            sms_cmd9_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd9_H_bit4.setChecked(false);
                                            sms_cmd9_H_bit5.setChecked(false);
                                            sms_cmd9_H_bit6.setChecked(false);
                                            sms_cmd9_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd9_H_bit4.setChecked(true);
                                            sms_cmd9_H_bit5.setChecked(false);
                                            sms_cmd9_H_bit6.setChecked(false);
                                            sms_cmd9_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd9_H_bit4.setChecked(false);
                                            sms_cmd9_H_bit5.setChecked(true);
                                            sms_cmd9_H_bit6.setChecked(false);
                                            sms_cmd9_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd9_H_bit4.setChecked(true);
                                            sms_cmd9_H_bit5.setChecked(true);
                                            sms_cmd9_H_bit6.setChecked(false);
                                            sms_cmd9_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd9_H_bit4.setChecked(false);
                                            sms_cmd9_H_bit5.setChecked(false);
                                            sms_cmd9_H_bit6.setChecked(true);
                                            sms_cmd9_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd9_H_bit4.setChecked(true);
                                            sms_cmd9_H_bit5.setChecked(false);
                                            sms_cmd9_H_bit6.setChecked(true);
                                            sms_cmd9_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd9_H_bit4.setChecked(false);
                                            sms_cmd9_H_bit5.setChecked(true);
                                            sms_cmd9_H_bit6.setChecked(true);
                                            sms_cmd9_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd9_H_bit4.setChecked(true);
                                            sms_cmd9_H_bit5.setChecked(true);
                                            sms_cmd9_H_bit6.setChecked(true);
                                            sms_cmd9_H_bit7.setChecked(true);
                                        }
                                        /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR CBC INPUTS//
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("0"))
                                    {
                                        displayTimer(displayTime);
                                        cbciTargetView.getParent().requestChildFocus(cbciTargetView,cbciTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd10_L_bit0.setChecked(false);
                                            sms_cmd10_L_bit1.setChecked(false);
                                            sms_cmd10_L_bit2.setChecked(false);
                                            sms_cmd10_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd10_L_bit0.setChecked(true);
                                            sms_cmd10_L_bit1.setChecked(false);
                                            sms_cmd10_L_bit2.setChecked(false);
                                            sms_cmd10_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd10_L_bit0.setChecked(false);
                                            sms_cmd10_L_bit1.setChecked(true);
                                            sms_cmd10_L_bit2.setChecked(false);
                                            sms_cmd10_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd10_L_bit0.setChecked(true);
                                            sms_cmd10_L_bit1.setChecked(true);
                                            sms_cmd10_L_bit2.setChecked(false);
                                            sms_cmd10_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd10_L_bit0.setChecked(false);
                                            sms_cmd10_L_bit1.setChecked(false);
                                            sms_cmd10_L_bit2.setChecked(true);
                                            sms_cmd10_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd10_L_bit0.setChecked(true);
                                            sms_cmd10_L_bit1.setChecked(false);
                                            sms_cmd10_L_bit2.setChecked(true);
                                            sms_cmd10_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd10_L_bit0.setChecked(false);
                                            sms_cmd10_L_bit1.setChecked(true);
                                            sms_cmd10_L_bit2.setChecked(true);
                                            sms_cmd10_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd10_L_bit0.setChecked(true);
                                            sms_cmd10_L_bit1.setChecked(true);
                                            sms_cmd10_L_bit2.setChecked(true);
                                            sms_cmd10_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd10_L_bit0.setChecked(false);
                                            sms_cmd10_L_bit1.setChecked(false);
                                            sms_cmd10_L_bit2.setChecked(false);
                                            sms_cmd10_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd10_L_bit0.setChecked(true);
                                            sms_cmd10_L_bit1.setChecked(false);
                                            sms_cmd10_L_bit2.setChecked(false);
                                            sms_cmd10_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd10_L_bit0.setChecked(false);
                                            sms_cmd10_L_bit1.setChecked(true);
                                            sms_cmd10_L_bit2.setChecked(false);
                                            sms_cmd10_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd10_L_bit0.setChecked(true);
                                            sms_cmd10_L_bit1.setChecked(true);
                                            sms_cmd10_L_bit2.setChecked(false);
                                            sms_cmd10_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd10_L_bit0.setChecked(false);
                                            sms_cmd10_L_bit1.setChecked(false);
                                            sms_cmd10_L_bit2.setChecked(true);
                                            sms_cmd10_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd10_L_bit0.setChecked(true);
                                            sms_cmd10_L_bit1.setChecked(false);
                                            sms_cmd10_L_bit2.setChecked(true);
                                            sms_cmd10_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd10_L_bit0.setChecked(false);
                                            sms_cmd10_L_bit1.setChecked(true);
                                            sms_cmd10_L_bit2.setChecked(true);
                                            sms_cmd10_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd10_L_bit0.setChecked(true);
                                            sms_cmd10_L_bit1.setChecked(true);
                                            sms_cmd10_L_bit2.setChecked(true);
                                            sms_cmd10_L_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd10_L_bit4.setChecked(false);
                                            sms_cmd10_L_bit5.setChecked(false);
                                            sms_cmd10_L_bit6.setChecked(false);
                                            sms_cmd10_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd10_L_bit4.setChecked(true);
                                            sms_cmd10_L_bit5.setChecked(false);
                                            sms_cmd10_L_bit6.setChecked(false);
                                            sms_cmd10_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd10_L_bit4.setChecked(false);
                                            sms_cmd10_L_bit5.setChecked(true);
                                            sms_cmd10_L_bit6.setChecked(false);
                                            sms_cmd10_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd10_L_bit4.setChecked(true);
                                            sms_cmd10_L_bit5.setChecked(true);
                                            sms_cmd10_L_bit6.setChecked(false);
                                            sms_cmd10_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd10_L_bit4.setChecked(false);
                                            sms_cmd10_L_bit5.setChecked(false);
                                            sms_cmd10_L_bit6.setChecked(true);
                                            sms_cmd10_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd10_L_bit4.setChecked(true);
                                            sms_cmd10_L_bit5.setChecked(false);
                                            sms_cmd10_L_bit6.setChecked(true);
                                            sms_cmd10_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd10_L_bit4.setChecked(false);
                                            sms_cmd10_L_bit5.setChecked(true);
                                            sms_cmd10_L_bit6.setChecked(true);
                                            sms_cmd10_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd10_L_bit4.setChecked(true);
                                            sms_cmd10_L_bit5.setChecked(true);
                                            sms_cmd10_L_bit6.setChecked(true);
                                            sms_cmd10_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd10_L_bit4.setChecked(false);
                                            sms_cmd10_L_bit5.setChecked(false);
                                            sms_cmd10_L_bit6.setChecked(false);
                                            sms_cmd10_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd10_L_bit4.setChecked(true);
                                            sms_cmd10_L_bit5.setChecked(false);
                                            sms_cmd10_L_bit6.setChecked(false);
                                            sms_cmd10_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd10_L_bit4.setChecked(false);
                                            sms_cmd10_L_bit5.setChecked(true);
                                            sms_cmd10_L_bit6.setChecked(false);
                                            sms_cmd10_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd10_L_bit4.setChecked(true);
                                            sms_cmd10_L_bit5.setChecked(true);
                                            sms_cmd10_L_bit6.setChecked(false);
                                            sms_cmd10_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd10_L_bit4.setChecked(false);
                                            sms_cmd10_L_bit5.setChecked(false);
                                            sms_cmd10_L_bit6.setChecked(true);
                                            sms_cmd10_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd10_L_bit4.setChecked(true);
                                            sms_cmd10_L_bit5.setChecked(false);
                                            sms_cmd10_L_bit6.setChecked(true);
                                            sms_cmd10_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd10_L_bit4.setChecked(false);
                                            sms_cmd10_L_bit5.setChecked(true);
                                            sms_cmd10_L_bit6.setChecked(true);
                                            sms_cmd10_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd10_L_bit4.setChecked(true);
                                            sms_cmd10_L_bit5.setChecked(true);
                                            sms_cmd10_L_bit6.setChecked(true);
                                            sms_cmd10_L_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd10_H_bit0.setChecked(false);
                                            sms_cmd10_H_bit1.setChecked(false);
                                            sms_cmd10_H_bit2.setChecked(false);
                                            sms_cmd10_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd10_H_bit0.setChecked(true);
                                            sms_cmd10_H_bit1.setChecked(false);
                                            sms_cmd10_H_bit2.setChecked(false);
                                            sms_cmd10_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd10_H_bit0.setChecked(false);
                                            sms_cmd10_H_bit1.setChecked(true);
                                            sms_cmd10_H_bit2.setChecked(false);
                                            sms_cmd10_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd10_H_bit0.setChecked(true);
                                            sms_cmd10_H_bit1.setChecked(true);
                                            sms_cmd10_H_bit2.setChecked(false);
                                            sms_cmd10_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd10_H_bit0.setChecked(false);
                                            sms_cmd10_H_bit1.setChecked(false);
                                            sms_cmd10_H_bit2.setChecked(true);
                                            sms_cmd10_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd10_H_bit0.setChecked(true);
                                            sms_cmd10_H_bit1.setChecked(false);
                                            sms_cmd10_H_bit2.setChecked(true);
                                            sms_cmd10_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd10_H_bit0.setChecked(false);
                                            sms_cmd10_H_bit1.setChecked(true);
                                            sms_cmd10_H_bit2.setChecked(true);
                                            sms_cmd10_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd10_H_bit0.setChecked(true);
                                            sms_cmd10_H_bit1.setChecked(true);
                                            sms_cmd10_H_bit2.setChecked(true);
                                            sms_cmd10_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd10_H_bit0.setChecked(false);
                                            sms_cmd10_H_bit1.setChecked(false);
                                            sms_cmd10_H_bit2.setChecked(false);
                                            sms_cmd10_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd10_H_bit0.setChecked(true);
                                            sms_cmd10_H_bit1.setChecked(false);
                                            sms_cmd10_H_bit2.setChecked(false);
                                            sms_cmd10_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd10_H_bit0.setChecked(false);
                                            sms_cmd10_H_bit1.setChecked(true);
                                            sms_cmd10_H_bit2.setChecked(false);
                                            sms_cmd10_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd10_H_bit0.setChecked(true);
                                            sms_cmd10_H_bit1.setChecked(true);
                                            sms_cmd10_H_bit2.setChecked(false);
                                            sms_cmd10_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd10_H_bit0.setChecked(false);
                                            sms_cmd10_H_bit1.setChecked(false);
                                            sms_cmd10_H_bit2.setChecked(true);
                                            sms_cmd10_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd10_H_bit0.setChecked(true);
                                            sms_cmd10_H_bit1.setChecked(false);
                                            sms_cmd10_H_bit2.setChecked(true);
                                            sms_cmd10_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd10_H_bit0.setChecked(false);
                                            sms_cmd10_H_bit1.setChecked(true);
                                            sms_cmd10_H_bit2.setChecked(true);
                                            sms_cmd10_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd10_H_bit0.setChecked(true);
                                            sms_cmd10_H_bit1.setChecked(true);
                                            sms_cmd10_H_bit2.setChecked(true);
                                            sms_cmd10_H_bit3.setChecked(true);
                                        }
                                        /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd10_H_bit4.setChecked(false);
                                            sms_cmd10_H_bit5.setChecked(false);
                                            sms_cmd10_H_bit6.setChecked(false);
                                            sms_cmd10_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd10_H_bit4.setChecked(true);
                                            sms_cmd10_H_bit5.setChecked(false);
                                            sms_cmd10_H_bit6.setChecked(false);
                                            sms_cmd10_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd10_H_bit4.setChecked(false);
                                            sms_cmd10_H_bit5.setChecked(true);
                                            sms_cmd10_H_bit6.setChecked(false);
                                            sms_cmd10_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd10_H_bit4.setChecked(true);
                                            sms_cmd10_H_bit5.setChecked(true);
                                            sms_cmd10_H_bit6.setChecked(false);
                                            sms_cmd10_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd10_H_bit4.setChecked(false);
                                            sms_cmd10_H_bit5.setChecked(false);
                                            sms_cmd10_H_bit6.setChecked(true);
                                            sms_cmd10_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd10_H_bit4.setChecked(true);
                                            sms_cmd10_H_bit5.setChecked(false);
                                            sms_cmd10_H_bit6.setChecked(true);
                                            sms_cmd10_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd10_H_bit4.setChecked(false);
                                            sms_cmd10_H_bit5.setChecked(true);
                                            sms_cmd10_H_bit6.setChecked(true);
                                            sms_cmd10_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd10_H_bit4.setChecked(true);
                                            sms_cmd10_H_bit5.setChecked(true);
                                            sms_cmd10_H_bit6.setChecked(true);
                                            sms_cmd10_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd10_H_bit4.setChecked(false);
                                            sms_cmd10_H_bit5.setChecked(false);
                                            sms_cmd10_H_bit6.setChecked(false);
                                            sms_cmd10_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd10_H_bit4.setChecked(true);
                                            sms_cmd10_H_bit5.setChecked(false);
                                            sms_cmd10_H_bit6.setChecked(false);
                                            sms_cmd10_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd10_H_bit4.setChecked(false);
                                            sms_cmd10_H_bit5.setChecked(true);
                                            sms_cmd10_H_bit6.setChecked(false);
                                            sms_cmd10_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd10_H_bit4.setChecked(true);
                                            sms_cmd10_H_bit5.setChecked(true);
                                            sms_cmd10_H_bit6.setChecked(false);
                                            sms_cmd10_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd10_H_bit4.setChecked(false);
                                            sms_cmd10_H_bit5.setChecked(false);
                                            sms_cmd10_H_bit6.setChecked(true);
                                            sms_cmd10_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd10_H_bit4.setChecked(true);
                                            sms_cmd10_H_bit5.setChecked(false);
                                            sms_cmd10_H_bit6.setChecked(true);
                                            sms_cmd10_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd10_H_bit4.setChecked(false);
                                            sms_cmd10_H_bit5.setChecked(true);
                                            sms_cmd10_H_bit6.setChecked(true);
                                            sms_cmd10_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd10_H_bit4.setChecked(true);
                                            sms_cmd10_H_bit5.setChecked(true);
                                            sms_cmd10_H_bit6.setChecked(true);
                                            sms_cmd10_H_bit7.setChecked(true);
                                        }
                                        /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR CBC OUTPUTS//
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("1"))
                                    {
                                        displayTimer(displayTime);
                                        cbcoTargetView.getParent().requestChildFocus(cbcoTargetView,cbcoTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd11_L_bit0.setChecked(false);
                                            sms_cmd11_L_bit1.setChecked(false);
                                            sms_cmd11_L_bit2.setChecked(false);
                                            sms_cmd11_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd11_L_bit0.setChecked(true);
                                            sms_cmd11_L_bit1.setChecked(false);
                                            sms_cmd11_L_bit2.setChecked(false);
                                            sms_cmd11_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd11_L_bit0.setChecked(false);
                                            sms_cmd11_L_bit1.setChecked(true);
                                            sms_cmd11_L_bit2.setChecked(false);
                                            sms_cmd11_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd11_L_bit0.setChecked(true);
                                            sms_cmd11_L_bit1.setChecked(true);
                                            sms_cmd11_L_bit2.setChecked(false);
                                            sms_cmd11_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd11_L_bit0.setChecked(false);
                                            sms_cmd11_L_bit1.setChecked(false);
                                            sms_cmd11_L_bit2.setChecked(true);
                                            sms_cmd11_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd11_L_bit0.setChecked(true);
                                            sms_cmd11_L_bit1.setChecked(false);
                                            sms_cmd11_L_bit2.setChecked(true);
                                            sms_cmd11_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd11_L_bit0.setChecked(false);
                                            sms_cmd11_L_bit1.setChecked(true);
                                            sms_cmd11_L_bit2.setChecked(true);
                                            sms_cmd11_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd11_L_bit0.setChecked(true);
                                            sms_cmd11_L_bit1.setChecked(true);
                                            sms_cmd11_L_bit2.setChecked(true);
                                            sms_cmd11_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd11_L_bit0.setChecked(false);
                                            sms_cmd11_L_bit1.setChecked(false);
                                            sms_cmd11_L_bit2.setChecked(false);
                                            sms_cmd11_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd11_L_bit0.setChecked(true);
                                            sms_cmd11_L_bit1.setChecked(false);
                                            sms_cmd11_L_bit2.setChecked(false);
                                            sms_cmd11_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd11_L_bit0.setChecked(false);
                                            sms_cmd11_L_bit1.setChecked(true);
                                            sms_cmd11_L_bit2.setChecked(false);
                                            sms_cmd11_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd11_L_bit0.setChecked(true);
                                            sms_cmd11_L_bit1.setChecked(true);
                                            sms_cmd11_L_bit2.setChecked(false);
                                            sms_cmd11_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd11_L_bit0.setChecked(false);
                                            sms_cmd11_L_bit1.setChecked(false);
                                            sms_cmd11_L_bit2.setChecked(true);
                                            sms_cmd11_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd11_L_bit0.setChecked(true);
                                            sms_cmd11_L_bit1.setChecked(false);
                                            sms_cmd11_L_bit2.setChecked(true);
                                            sms_cmd11_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd11_L_bit0.setChecked(false);
                                            sms_cmd11_L_bit1.setChecked(true);
                                            sms_cmd11_L_bit2.setChecked(true);
                                            sms_cmd11_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd11_L_bit0.setChecked(true);
                                            sms_cmd11_L_bit1.setChecked(true);
                                            sms_cmd11_L_bit2.setChecked(true);
                                            sms_cmd11_L_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd11_L_bit4.setChecked(false);
                                            sms_cmd11_L_bit5.setChecked(false);
                                            sms_cmd11_L_bit6.setChecked(false);
                                            sms_cmd11_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd11_L_bit4.setChecked(true);
                                            sms_cmd11_L_bit5.setChecked(false);
                                            sms_cmd11_L_bit6.setChecked(false);
                                            sms_cmd11_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd11_L_bit4.setChecked(false);
                                            sms_cmd11_L_bit5.setChecked(true);
                                            sms_cmd11_L_bit6.setChecked(false);
                                            sms_cmd11_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd11_L_bit4.setChecked(true);
                                            sms_cmd11_L_bit5.setChecked(true);
                                            sms_cmd11_L_bit6.setChecked(false);
                                            sms_cmd11_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd11_L_bit4.setChecked(false);
                                            sms_cmd11_L_bit5.setChecked(false);
                                            sms_cmd11_L_bit6.setChecked(true);
                                            sms_cmd11_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd11_L_bit4.setChecked(true);
                                            sms_cmd11_L_bit5.setChecked(false);
                                            sms_cmd11_L_bit6.setChecked(true);
                                            sms_cmd11_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd11_L_bit4.setChecked(false);
                                            sms_cmd11_L_bit5.setChecked(true);
                                            sms_cmd11_L_bit6.setChecked(true);
                                            sms_cmd11_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd11_L_bit4.setChecked(true);
                                            sms_cmd11_L_bit5.setChecked(true);
                                            sms_cmd11_L_bit6.setChecked(true);
                                            sms_cmd11_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd11_L_bit4.setChecked(false);
                                            sms_cmd11_L_bit5.setChecked(false);
                                            sms_cmd11_L_bit6.setChecked(false);
                                            sms_cmd11_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd11_L_bit4.setChecked(true);
                                            sms_cmd11_L_bit5.setChecked(false);
                                            sms_cmd11_L_bit6.setChecked(false);
                                            sms_cmd11_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd11_L_bit4.setChecked(false);
                                            sms_cmd11_L_bit5.setChecked(true);
                                            sms_cmd11_L_bit6.setChecked(false);
                                            sms_cmd11_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd11_L_bit4.setChecked(true);
                                            sms_cmd11_L_bit5.setChecked(true);
                                            sms_cmd11_L_bit6.setChecked(false);
                                            sms_cmd11_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd11_L_bit4.setChecked(false);
                                            sms_cmd11_L_bit5.setChecked(false);
                                            sms_cmd11_L_bit6.setChecked(true);
                                            sms_cmd11_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd11_L_bit4.setChecked(true);
                                            sms_cmd11_L_bit5.setChecked(false);
                                            sms_cmd11_L_bit6.setChecked(true);
                                            sms_cmd11_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd11_L_bit4.setChecked(false);
                                            sms_cmd11_L_bit5.setChecked(true);
                                            sms_cmd11_L_bit6.setChecked(true);
                                            sms_cmd11_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd11_L_bit4.setChecked(true);
                                            sms_cmd11_L_bit5.setChecked(true);
                                            sms_cmd11_L_bit6.setChecked(true);
                                            sms_cmd11_L_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd11_H_bit0.setChecked(false);
                                            sms_cmd11_H_bit1.setChecked(false);
                                            sms_cmd11_H_bit2.setChecked(false);
                                            sms_cmd11_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd11_H_bit0.setChecked(true);
                                            sms_cmd11_H_bit1.setChecked(false);
                                            sms_cmd11_H_bit2.setChecked(false);
                                            sms_cmd11_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd11_H_bit0.setChecked(false);
                                            sms_cmd11_H_bit1.setChecked(true);
                                            sms_cmd11_H_bit2.setChecked(false);
                                            sms_cmd11_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd11_H_bit0.setChecked(true);
                                            sms_cmd11_H_bit1.setChecked(true);
                                            sms_cmd11_H_bit2.setChecked(false);
                                            sms_cmd11_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd11_H_bit0.setChecked(false);
                                            sms_cmd11_H_bit1.setChecked(false);
                                            sms_cmd11_H_bit2.setChecked(true);
                                            sms_cmd11_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd11_H_bit0.setChecked(true);
                                            sms_cmd11_H_bit1.setChecked(false);
                                            sms_cmd11_H_bit2.setChecked(true);
                                            sms_cmd11_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd11_H_bit0.setChecked(false);
                                            sms_cmd11_H_bit1.setChecked(true);
                                            sms_cmd11_H_bit2.setChecked(true);
                                            sms_cmd11_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd11_H_bit0.setChecked(true);
                                            sms_cmd11_H_bit1.setChecked(true);
                                            sms_cmd11_H_bit2.setChecked(true);
                                            sms_cmd11_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd11_H_bit0.setChecked(false);
                                            sms_cmd11_H_bit1.setChecked(false);
                                            sms_cmd11_H_bit2.setChecked(false);
                                            sms_cmd11_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd11_H_bit0.setChecked(true);
                                            sms_cmd11_H_bit1.setChecked(false);
                                            sms_cmd11_H_bit2.setChecked(false);
                                            sms_cmd11_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd11_H_bit0.setChecked(false);
                                            sms_cmd11_H_bit1.setChecked(true);
                                            sms_cmd11_H_bit2.setChecked(false);
                                            sms_cmd11_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd11_H_bit0.setChecked(true);
                                            sms_cmd11_H_bit1.setChecked(true);
                                            sms_cmd11_H_bit2.setChecked(false);
                                            sms_cmd11_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd11_H_bit0.setChecked(false);
                                            sms_cmd11_H_bit1.setChecked(false);
                                            sms_cmd11_H_bit2.setChecked(true);
                                            sms_cmd11_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd11_H_bit0.setChecked(true);
                                            sms_cmd11_H_bit1.setChecked(false);
                                            sms_cmd11_H_bit2.setChecked(true);
                                            sms_cmd11_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd11_H_bit0.setChecked(false);
                                            sms_cmd11_H_bit1.setChecked(true);
                                            sms_cmd11_H_bit2.setChecked(true);
                                            sms_cmd11_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd11_H_bit0.setChecked(true);
                                            sms_cmd11_H_bit1.setChecked(true);
                                            sms_cmd11_H_bit2.setChecked(true);
                                            sms_cmd11_H_bit3.setChecked(true);
                                        }
                                        /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd11_H_bit4.setChecked(false);
                                            sms_cmd11_H_bit5.setChecked(false);
                                            sms_cmd11_H_bit6.setChecked(false);
                                            sms_cmd11_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd11_H_bit4.setChecked(true);
                                            sms_cmd11_H_bit5.setChecked(false);
                                            sms_cmd11_H_bit6.setChecked(false);
                                            sms_cmd11_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd11_H_bit4.setChecked(false);
                                            sms_cmd11_H_bit5.setChecked(true);
                                            sms_cmd11_H_bit6.setChecked(false);
                                            sms_cmd11_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd11_H_bit4.setChecked(true);
                                            sms_cmd11_H_bit5.setChecked(true);
                                            sms_cmd11_H_bit6.setChecked(false);
                                            sms_cmd11_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd11_H_bit4.setChecked(false);
                                            sms_cmd11_H_bit5.setChecked(false);
                                            sms_cmd11_H_bit6.setChecked(true);
                                            sms_cmd11_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd11_H_bit4.setChecked(true);
                                            sms_cmd11_H_bit5.setChecked(false);
                                            sms_cmd11_H_bit6.setChecked(true);
                                            sms_cmd11_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd11_H_bit4.setChecked(false);
                                            sms_cmd11_H_bit5.setChecked(true);
                                            sms_cmd11_H_bit6.setChecked(true);
                                            sms_cmd11_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd11_H_bit4.setChecked(true);
                                            sms_cmd11_H_bit5.setChecked(true);
                                            sms_cmd11_H_bit6.setChecked(true);
                                            sms_cmd11_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd11_H_bit4.setChecked(false);
                                            sms_cmd11_H_bit5.setChecked(false);
                                            sms_cmd11_H_bit6.setChecked(false);
                                            sms_cmd11_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd11_H_bit4.setChecked(true);
                                            sms_cmd11_H_bit5.setChecked(false);
                                            sms_cmd11_H_bit6.setChecked(false);
                                            sms_cmd11_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd11_H_bit4.setChecked(false);
                                            sms_cmd11_H_bit5.setChecked(true);
                                            sms_cmd11_H_bit6.setChecked(false);
                                            sms_cmd11_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd11_H_bit4.setChecked(true);
                                            sms_cmd11_H_bit5.setChecked(true);
                                            sms_cmd11_H_bit6.setChecked(false);
                                            sms_cmd11_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd11_H_bit4.setChecked(false);
                                            sms_cmd11_H_bit5.setChecked(false);
                                            sms_cmd11_H_bit6.setChecked(true);
                                            sms_cmd11_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd11_H_bit4.setChecked(true);
                                            sms_cmd11_H_bit5.setChecked(false);
                                            sms_cmd11_H_bit6.setChecked(true);
                                            sms_cmd11_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd11_H_bit4.setChecked(false);
                                            sms_cmd11_H_bit5.setChecked(true);
                                            sms_cmd11_H_bit6.setChecked(true);
                                            sms_cmd11_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd11_H_bit4.setChecked(true);
                                            sms_cmd11_H_bit5.setChecked(true);
                                            sms_cmd11_H_bit6.setChecked(true);
                                            sms_cmd11_H_bit7.setChecked(true);
                                        }
                                        /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR FLOOR UP KEYS//
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("2"))
                                    {
                                        displayTimer(displayTime);
                                        flrupTargetView.getParent().requestChildFocus(flrupTargetView,flrupTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd12_L_bit0.setChecked(false);
                                            sms_cmd12_L_bit1.setChecked(false);
                                            sms_cmd12_L_bit2.setChecked(false);
                                            sms_cmd12_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd12_L_bit0.setChecked(true);
                                            sms_cmd12_L_bit1.setChecked(false);
                                            sms_cmd12_L_bit2.setChecked(false);
                                            sms_cmd12_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd12_L_bit0.setChecked(false);
                                            sms_cmd12_L_bit1.setChecked(true);
                                            sms_cmd12_L_bit2.setChecked(false);
                                            sms_cmd12_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd12_L_bit0.setChecked(true);
                                            sms_cmd12_L_bit1.setChecked(true);
                                            sms_cmd12_L_bit2.setChecked(false);
                                            sms_cmd12_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd12_L_bit0.setChecked(false);
                                            sms_cmd12_L_bit1.setChecked(false);
                                            sms_cmd12_L_bit2.setChecked(true);
                                            sms_cmd12_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd12_L_bit0.setChecked(true);
                                            sms_cmd12_L_bit1.setChecked(false);
                                            sms_cmd12_L_bit2.setChecked(true);
                                            sms_cmd12_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd12_L_bit0.setChecked(false);
                                            sms_cmd12_L_bit1.setChecked(true);
                                            sms_cmd12_L_bit2.setChecked(true);
                                            sms_cmd12_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd12_L_bit0.setChecked(true);
                                            sms_cmd12_L_bit1.setChecked(true);
                                            sms_cmd12_L_bit2.setChecked(true);
                                            sms_cmd12_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd12_L_bit0.setChecked(false);
                                            sms_cmd12_L_bit1.setChecked(false);
                                            sms_cmd12_L_bit2.setChecked(false);
                                            sms_cmd12_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd12_L_bit0.setChecked(true);
                                            sms_cmd12_L_bit1.setChecked(false);
                                            sms_cmd12_L_bit2.setChecked(false);
                                            sms_cmd12_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd12_L_bit0.setChecked(false);
                                            sms_cmd12_L_bit1.setChecked(true);
                                            sms_cmd12_L_bit2.setChecked(false);
                                            sms_cmd12_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd12_L_bit0.setChecked(true);
                                            sms_cmd12_L_bit1.setChecked(true);
                                            sms_cmd12_L_bit2.setChecked(false);
                                            sms_cmd12_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd12_L_bit0.setChecked(false);
                                            sms_cmd12_L_bit1.setChecked(false);
                                            sms_cmd12_L_bit2.setChecked(true);
                                            sms_cmd12_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd12_L_bit0.setChecked(true);
                                            sms_cmd12_L_bit1.setChecked(false);
                                            sms_cmd12_L_bit2.setChecked(true);
                                            sms_cmd12_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd12_L_bit0.setChecked(false);
                                            sms_cmd12_L_bit1.setChecked(true);
                                            sms_cmd12_L_bit2.setChecked(true);
                                            sms_cmd12_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd12_L_bit0.setChecked(true);
                                            sms_cmd12_L_bit1.setChecked(true);
                                            sms_cmd12_L_bit2.setChecked(true);
                                            sms_cmd12_L_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd12_L_bit4.setChecked(false);
                                            sms_cmd12_L_bit5.setChecked(false);
                                            sms_cmd12_L_bit6.setChecked(false);
                                            sms_cmd12_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd12_L_bit4.setChecked(true);
                                            sms_cmd12_L_bit5.setChecked(false);
                                            sms_cmd12_L_bit6.setChecked(false);
                                            sms_cmd12_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd12_L_bit4.setChecked(false);
                                            sms_cmd12_L_bit5.setChecked(true);
                                            sms_cmd12_L_bit6.setChecked(false);
                                            sms_cmd12_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd12_L_bit4.setChecked(true);
                                            sms_cmd12_L_bit5.setChecked(true);
                                            sms_cmd12_L_bit6.setChecked(false);
                                            sms_cmd12_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd12_L_bit4.setChecked(false);
                                            sms_cmd12_L_bit5.setChecked(false);
                                            sms_cmd12_L_bit6.setChecked(true);
                                            sms_cmd12_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd12_L_bit4.setChecked(true);
                                            sms_cmd12_L_bit5.setChecked(false);
                                            sms_cmd12_L_bit6.setChecked(true);
                                            sms_cmd12_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd12_L_bit4.setChecked(false);
                                            sms_cmd12_L_bit5.setChecked(true);
                                            sms_cmd12_L_bit6.setChecked(true);
                                            sms_cmd12_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd12_L_bit4.setChecked(true);
                                            sms_cmd12_L_bit5.setChecked(true);
                                            sms_cmd12_L_bit6.setChecked(true);
                                            sms_cmd12_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd12_L_bit4.setChecked(false);
                                            sms_cmd12_L_bit5.setChecked(false);
                                            sms_cmd12_L_bit6.setChecked(false);
                                            sms_cmd12_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd12_L_bit4.setChecked(true);
                                            sms_cmd12_L_bit5.setChecked(false);
                                            sms_cmd12_L_bit6.setChecked(false);
                                            sms_cmd12_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd12_L_bit4.setChecked(false);
                                            sms_cmd12_L_bit5.setChecked(true);
                                            sms_cmd12_L_bit6.setChecked(false);
                                            sms_cmd12_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd12_L_bit4.setChecked(true);
                                            sms_cmd12_L_bit5.setChecked(true);
                                            sms_cmd12_L_bit6.setChecked(false);
                                            sms_cmd12_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd12_L_bit4.setChecked(false);
                                            sms_cmd12_L_bit5.setChecked(false);
                                            sms_cmd12_L_bit6.setChecked(true);
                                            sms_cmd12_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd12_L_bit4.setChecked(true);
                                            sms_cmd12_L_bit5.setChecked(false);
                                            sms_cmd12_L_bit6.setChecked(true);
                                            sms_cmd12_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd12_L_bit4.setChecked(false);
                                            sms_cmd12_L_bit5.setChecked(true);
                                            sms_cmd12_L_bit6.setChecked(true);
                                            sms_cmd12_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd12_L_bit4.setChecked(true);
                                            sms_cmd12_L_bit5.setChecked(true);
                                            sms_cmd12_L_bit6.setChecked(true);
                                            sms_cmd12_L_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd12_H_bit0.setChecked(false);
                                            sms_cmd12_H_bit1.setChecked(false);
                                            sms_cmd12_H_bit2.setChecked(false);
                                            sms_cmd12_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd12_H_bit0.setChecked(true);
                                            sms_cmd12_H_bit1.setChecked(false);
                                            sms_cmd12_H_bit2.setChecked(false);
                                            sms_cmd12_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd12_H_bit0.setChecked(false);
                                            sms_cmd12_H_bit1.setChecked(true);
                                            sms_cmd12_H_bit2.setChecked(false);
                                            sms_cmd12_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd12_H_bit0.setChecked(true);
                                            sms_cmd12_H_bit1.setChecked(true);
                                            sms_cmd12_H_bit2.setChecked(false);
                                            sms_cmd12_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd12_H_bit0.setChecked(false);
                                            sms_cmd12_H_bit1.setChecked(false);
                                            sms_cmd12_H_bit2.setChecked(true);
                                            sms_cmd12_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd12_H_bit0.setChecked(true);
                                            sms_cmd12_H_bit1.setChecked(false);
                                            sms_cmd12_H_bit2.setChecked(true);
                                            sms_cmd12_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd12_H_bit0.setChecked(false);
                                            sms_cmd12_H_bit1.setChecked(true);
                                            sms_cmd12_H_bit2.setChecked(true);
                                            sms_cmd12_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd12_H_bit0.setChecked(true);
                                            sms_cmd12_H_bit1.setChecked(true);
                                            sms_cmd12_H_bit2.setChecked(true);
                                            sms_cmd12_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd12_H_bit0.setChecked(false);
                                            sms_cmd12_H_bit1.setChecked(false);
                                            sms_cmd12_H_bit2.setChecked(false);
                                            sms_cmd12_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd12_H_bit0.setChecked(true);
                                            sms_cmd12_H_bit1.setChecked(false);
                                            sms_cmd12_H_bit2.setChecked(false);
                                            sms_cmd12_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd12_H_bit0.setChecked(false);
                                            sms_cmd12_H_bit1.setChecked(true);
                                            sms_cmd12_H_bit2.setChecked(false);
                                            sms_cmd12_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd12_H_bit0.setChecked(true);
                                            sms_cmd12_H_bit1.setChecked(true);
                                            sms_cmd12_H_bit2.setChecked(false);
                                            sms_cmd12_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd12_H_bit0.setChecked(false);
                                            sms_cmd12_H_bit1.setChecked(false);
                                            sms_cmd12_H_bit2.setChecked(true);
                                            sms_cmd12_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd12_H_bit0.setChecked(true);
                                            sms_cmd12_H_bit1.setChecked(false);
                                            sms_cmd12_H_bit2.setChecked(true);
                                            sms_cmd12_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd12_H_bit0.setChecked(false);
                                            sms_cmd12_H_bit1.setChecked(true);
                                            sms_cmd12_H_bit2.setChecked(true);
                                            sms_cmd12_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd12_H_bit0.setChecked(true);
                                            sms_cmd12_H_bit1.setChecked(true);
                                            sms_cmd12_H_bit2.setChecked(true);
                                            sms_cmd12_H_bit3.setChecked(true);
                                        }
                                        /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd12_H_bit4.setChecked(false);
                                            sms_cmd12_H_bit5.setChecked(false);
                                            sms_cmd12_H_bit6.setChecked(false);
                                            sms_cmd12_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd12_H_bit4.setChecked(true);
                                            sms_cmd12_H_bit5.setChecked(false);
                                            sms_cmd12_H_bit6.setChecked(false);
                                            sms_cmd12_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd12_H_bit4.setChecked(false);
                                            sms_cmd12_H_bit5.setChecked(true);
                                            sms_cmd12_H_bit6.setChecked(false);
                                            sms_cmd12_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd12_H_bit4.setChecked(true);
                                            sms_cmd12_H_bit5.setChecked(true);
                                            sms_cmd12_H_bit6.setChecked(false);
                                            sms_cmd12_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd12_H_bit4.setChecked(false);
                                            sms_cmd12_H_bit5.setChecked(false);
                                            sms_cmd12_H_bit6.setChecked(true);
                                            sms_cmd12_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd12_H_bit4.setChecked(true);
                                            sms_cmd12_H_bit5.setChecked(false);
                                            sms_cmd12_H_bit6.setChecked(true);
                                            sms_cmd12_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd12_H_bit4.setChecked(false);
                                            sms_cmd12_H_bit5.setChecked(true);
                                            sms_cmd12_H_bit6.setChecked(true);
                                            sms_cmd12_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd12_H_bit4.setChecked(true);
                                            sms_cmd12_H_bit5.setChecked(true);
                                            sms_cmd12_H_bit6.setChecked(true);
                                            sms_cmd12_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd12_H_bit4.setChecked(false);
                                            sms_cmd12_H_bit5.setChecked(false);
                                            sms_cmd12_H_bit6.setChecked(false);
                                            sms_cmd12_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd12_H_bit4.setChecked(true);
                                            sms_cmd12_H_bit5.setChecked(false);
                                            sms_cmd12_H_bit6.setChecked(false);
                                            sms_cmd12_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd12_H_bit4.setChecked(false);
                                            sms_cmd12_H_bit5.setChecked(true);
                                            sms_cmd12_H_bit6.setChecked(false);
                                            sms_cmd12_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd12_H_bit4.setChecked(true);
                                            sms_cmd12_H_bit5.setChecked(true);
                                            sms_cmd12_H_bit6.setChecked(false);
                                            sms_cmd12_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd12_H_bit4.setChecked(false);
                                            sms_cmd12_H_bit5.setChecked(false);
                                            sms_cmd12_H_bit6.setChecked(true);
                                            sms_cmd12_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd12_H_bit4.setChecked(true);
                                            sms_cmd12_H_bit5.setChecked(false);
                                            sms_cmd12_H_bit6.setChecked(true);
                                            sms_cmd12_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd12_H_bit4.setChecked(false);
                                            sms_cmd12_H_bit5.setChecked(true);
                                            sms_cmd12_H_bit6.setChecked(true);
                                            sms_cmd12_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd12_H_bit4.setChecked(true);
                                            sms_cmd12_H_bit5.setChecked(true);
                                            sms_cmd12_H_bit6.setChecked(true);
                                            sms_cmd12_H_bit7.setChecked(true);
                                        }
                                        /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FIFTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(false);
                                            sms_cmd12_L_M_bit1.setChecked(false);
                                            sms_cmd12_L_M_bit2.setChecked(false);
                                            sms_cmd12_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(true);
                                            sms_cmd12_L_M_bit1.setChecked(false);
                                            sms_cmd12_L_M_bit2.setChecked(false);
                                            sms_cmd12_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(false);
                                            sms_cmd12_L_M_bit1.setChecked(true);
                                            sms_cmd12_L_M_bit2.setChecked(false);
                                            sms_cmd12_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(true);
                                            sms_cmd12_L_M_bit1.setChecked(true);
                                            sms_cmd12_L_M_bit2.setChecked(false);
                                            sms_cmd12_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(false);
                                            sms_cmd12_L_M_bit1.setChecked(false);
                                            sms_cmd12_L_M_bit2.setChecked(true);
                                            sms_cmd12_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(true);
                                            sms_cmd12_L_M_bit1.setChecked(false);
                                            sms_cmd12_L_M_bit2.setChecked(true);
                                            sms_cmd12_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(false);
                                            sms_cmd12_L_M_bit1.setChecked(true);
                                            sms_cmd12_L_M_bit2.setChecked(true);
                                            sms_cmd12_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(true);
                                            sms_cmd12_L_M_bit1.setChecked(true);
                                            sms_cmd12_L_M_bit2.setChecked(true);
                                            sms_cmd12_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(false);
                                            sms_cmd12_L_M_bit1.setChecked(false);
                                            sms_cmd12_L_M_bit2.setChecked(false);
                                            sms_cmd12_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(true);
                                            sms_cmd12_L_M_bit1.setChecked(false);
                                            sms_cmd12_L_M_bit2.setChecked(false);
                                            sms_cmd12_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(false);
                                            sms_cmd12_L_M_bit1.setChecked(true);
                                            sms_cmd12_L_M_bit2.setChecked(false);
                                            sms_cmd12_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(true);
                                            sms_cmd12_L_M_bit1.setChecked(true);
                                            sms_cmd12_L_M_bit2.setChecked(false);
                                            sms_cmd12_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(false);
                                            sms_cmd12_L_M_bit1.setChecked(false);
                                            sms_cmd12_L_M_bit2.setChecked(true);
                                            sms_cmd12_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(true);
                                            sms_cmd12_L_M_bit1.setChecked(false);
                                            sms_cmd12_L_M_bit2.setChecked(true);
                                            sms_cmd12_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(false);
                                            sms_cmd12_L_M_bit1.setChecked(true);
                                            sms_cmd12_L_M_bit2.setChecked(true);
                                            sms_cmd12_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd12_L_M_bit0.setChecked(true);
                                            sms_cmd12_L_M_bit1.setChecked(true);
                                            sms_cmd12_L_M_bit2.setChecked(true);
                                            sms_cmd12_L_M_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIFTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SIXTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(false);
                                            sms_cmd12_L_M_bit5.setChecked(false);
                                            sms_cmd12_L_M_bit6.setChecked(false);
                                            sms_cmd12_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(true);
                                            sms_cmd12_L_M_bit5.setChecked(false);
                                            sms_cmd12_L_M_bit6.setChecked(false);
                                            sms_cmd12_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(false);
                                            sms_cmd12_L_M_bit5.setChecked(true);
                                            sms_cmd12_L_M_bit6.setChecked(false);
                                            sms_cmd12_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(true);
                                            sms_cmd12_L_M_bit5.setChecked(true);
                                            sms_cmd12_L_M_bit6.setChecked(false);
                                            sms_cmd12_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(false);
                                            sms_cmd12_L_M_bit5.setChecked(false);
                                            sms_cmd12_L_M_bit6.setChecked(true);
                                            sms_cmd12_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(true);
                                            sms_cmd12_L_M_bit5.setChecked(false);
                                            sms_cmd12_L_M_bit6.setChecked(true);
                                            sms_cmd12_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(false);
                                            sms_cmd12_L_M_bit5.setChecked(true);
                                            sms_cmd12_L_M_bit6.setChecked(true);
                                            sms_cmd12_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(true);
                                            sms_cmd12_L_M_bit5.setChecked(true);
                                            sms_cmd12_L_M_bit6.setChecked(true);
                                            sms_cmd12_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(false);
                                            sms_cmd12_L_M_bit5.setChecked(false);
                                            sms_cmd12_L_M_bit6.setChecked(false);
                                            sms_cmd12_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(true);
                                            sms_cmd12_L_M_bit5.setChecked(false);
                                            sms_cmd12_L_M_bit6.setChecked(false);
                                            sms_cmd12_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(false);
                                            sms_cmd12_L_M_bit5.setChecked(true);
                                            sms_cmd12_L_M_bit6.setChecked(false);
                                            sms_cmd12_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(true);
                                            sms_cmd12_L_M_bit5.setChecked(true);
                                            sms_cmd12_L_M_bit6.setChecked(false);
                                            sms_cmd12_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(false);
                                            sms_cmd12_L_M_bit5.setChecked(false);
                                            sms_cmd12_L_M_bit6.setChecked(true);
                                            sms_cmd12_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(true);
                                            sms_cmd12_L_M_bit5.setChecked(false);
                                            sms_cmd12_L_M_bit6.setChecked(true);
                                            sms_cmd12_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(false);
                                            sms_cmd12_L_M_bit5.setChecked(true);
                                            sms_cmd12_L_M_bit6.setChecked(true);
                                            sms_cmd12_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd12_L_M_bit4.setChecked(true);
                                            sms_cmd12_L_M_bit5.setChecked(true);
                                            sms_cmd12_L_M_bit6.setChecked(true);
                                            sms_cmd12_L_M_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SIXTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SEVENTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(false);
                                            sms_cmd12_H_M_bit1.setChecked(false);
                                            sms_cmd12_H_M_bit2.setChecked(false);
                                            sms_cmd12_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(true);
                                            sms_cmd12_H_M_bit1.setChecked(false);
                                            sms_cmd12_H_M_bit2.setChecked(false);
                                            sms_cmd12_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(false);
                                            sms_cmd12_H_M_bit1.setChecked(true);
                                            sms_cmd12_H_M_bit2.setChecked(false);
                                            sms_cmd12_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(true);
                                            sms_cmd12_H_M_bit1.setChecked(true);
                                            sms_cmd12_H_M_bit2.setChecked(false);
                                            sms_cmd12_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(false);
                                            sms_cmd12_H_M_bit1.setChecked(false);
                                            sms_cmd12_H_M_bit2.setChecked(true);
                                            sms_cmd12_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(true);
                                            sms_cmd12_H_M_bit1.setChecked(false);
                                            sms_cmd12_H_M_bit2.setChecked(true);
                                            sms_cmd12_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(false);
                                            sms_cmd12_H_M_bit1.setChecked(true);
                                            sms_cmd12_H_M_bit2.setChecked(true);
                                            sms_cmd12_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(true);
                                            sms_cmd12_H_M_bit1.setChecked(true);
                                            sms_cmd12_H_M_bit2.setChecked(true);
                                            sms_cmd12_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(false);
                                            sms_cmd12_H_M_bit1.setChecked(false);
                                            sms_cmd12_H_M_bit2.setChecked(false);
                                            sms_cmd12_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(true);
                                            sms_cmd12_H_M_bit1.setChecked(false);
                                            sms_cmd12_H_M_bit2.setChecked(false);
                                            sms_cmd12_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(false);
                                            sms_cmd12_H_M_bit1.setChecked(true);
                                            sms_cmd12_H_M_bit2.setChecked(false);
                                            sms_cmd12_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(true);
                                            sms_cmd12_H_M_bit1.setChecked(true);
                                            sms_cmd12_H_M_bit2.setChecked(false);
                                            sms_cmd12_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(false);
                                            sms_cmd12_H_M_bit1.setChecked(false);
                                            sms_cmd12_H_M_bit2.setChecked(true);
                                            sms_cmd12_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(true);
                                            sms_cmd12_H_M_bit1.setChecked(false);
                                            sms_cmd12_H_M_bit2.setChecked(true);
                                            sms_cmd12_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(false);
                                            sms_cmd12_H_M_bit1.setChecked(true);
                                            sms_cmd12_H_M_bit2.setChecked(true);
                                            sms_cmd12_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd12_H_M_bit0.setChecked(true);
                                            sms_cmd12_H_M_bit1.setChecked(true);
                                            sms_cmd12_H_M_bit2.setChecked(true);
                                            sms_cmd12_H_M_bit3.setChecked(true);
                                        }
                                        /***************************** END OF SEVENTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON EIGHTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(false);
                                            sms_cmd12_H_M_bit5.setChecked(false);
                                            sms_cmd12_H_M_bit6.setChecked(false);
                                            sms_cmd12_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(true);
                                            sms_cmd12_H_M_bit5.setChecked(false);
                                            sms_cmd12_H_M_bit6.setChecked(false);
                                            sms_cmd12_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(false);
                                            sms_cmd12_H_M_bit5.setChecked(true);
                                            sms_cmd12_H_M_bit6.setChecked(false);
                                            sms_cmd12_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(true);
                                            sms_cmd12_H_M_bit5.setChecked(true);
                                            sms_cmd12_H_M_bit6.setChecked(false);
                                            sms_cmd12_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(false);
                                            sms_cmd12_H_M_bit5.setChecked(false);
                                            sms_cmd12_H_M_bit6.setChecked(true);
                                            sms_cmd12_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(true);
                                            sms_cmd12_H_M_bit5.setChecked(false);
                                            sms_cmd12_H_M_bit6.setChecked(true);
                                            sms_cmd12_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(false);
                                            sms_cmd12_H_M_bit5.setChecked(true);
                                            sms_cmd12_H_M_bit6.setChecked(true);
                                            sms_cmd12_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(true);
                                            sms_cmd12_H_M_bit5.setChecked(true);
                                            sms_cmd12_H_M_bit6.setChecked(true);
                                            sms_cmd12_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(false);
                                            sms_cmd12_H_M_bit5.setChecked(false);
                                            sms_cmd12_H_M_bit6.setChecked(false);
                                            sms_cmd12_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(true);
                                            sms_cmd12_H_M_bit5.setChecked(false);
                                            sms_cmd12_H_M_bit6.setChecked(false);
                                            sms_cmd12_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(false);
                                            sms_cmd12_H_M_bit5.setChecked(true);
                                            sms_cmd12_H_M_bit6.setChecked(false);
                                            sms_cmd12_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(true);
                                            sms_cmd12_H_M_bit5.setChecked(true);
                                            sms_cmd12_H_M_bit6.setChecked(false);
                                            sms_cmd12_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(false);
                                            sms_cmd12_H_M_bit5.setChecked(false);
                                            sms_cmd12_H_M_bit6.setChecked(true);
                                            sms_cmd12_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(true);
                                            sms_cmd12_H_M_bit5.setChecked(false);
                                            sms_cmd12_H_M_bit6.setChecked(true);
                                            sms_cmd12_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(false);
                                            sms_cmd12_H_M_bit5.setChecked(true);
                                            sms_cmd12_H_M_bit6.setChecked(true);
                                            sms_cmd12_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd12_H_M_bit4.setChecked(true);
                                            sms_cmd12_H_M_bit5.setChecked(true);
                                            sms_cmd12_H_M_bit6.setChecked(true);
                                            sms_cmd12_H_M_bit7.setChecked(true);
                                        }
                                        /***************************** END OF EIGHTH CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR FLOOR DOWN KEYS//
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("3"))
                                    {
                                        displayTimer(displayTime);
                                        flrdnTargetView.getParent().requestChildFocus(flrdnTargetView,flrdnTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd13_L_bit0.setChecked(false);
                                            sms_cmd13_L_bit1.setChecked(false);
                                            sms_cmd13_L_bit2.setChecked(false);
                                            sms_cmd13_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd13_L_bit0.setChecked(true);
                                            sms_cmd13_L_bit1.setChecked(false);
                                            sms_cmd13_L_bit2.setChecked(false);
                                            sms_cmd13_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd13_L_bit0.setChecked(false);
                                            sms_cmd13_L_bit1.setChecked(true);
                                            sms_cmd13_L_bit2.setChecked(false);
                                            sms_cmd13_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd13_L_bit0.setChecked(true);
                                            sms_cmd13_L_bit1.setChecked(true);
                                            sms_cmd13_L_bit2.setChecked(false);
                                            sms_cmd13_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd13_L_bit0.setChecked(false);
                                            sms_cmd13_L_bit1.setChecked(false);
                                            sms_cmd13_L_bit2.setChecked(true);
                                            sms_cmd13_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd13_L_bit0.setChecked(true);
                                            sms_cmd13_L_bit1.setChecked(false);
                                            sms_cmd13_L_bit2.setChecked(true);
                                            sms_cmd13_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd13_L_bit0.setChecked(false);
                                            sms_cmd13_L_bit1.setChecked(true);
                                            sms_cmd13_L_bit2.setChecked(true);
                                            sms_cmd13_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd13_L_bit0.setChecked(true);
                                            sms_cmd13_L_bit1.setChecked(true);
                                            sms_cmd13_L_bit2.setChecked(true);
                                            sms_cmd13_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd13_L_bit0.setChecked(false);
                                            sms_cmd13_L_bit1.setChecked(false);
                                            sms_cmd13_L_bit2.setChecked(false);
                                            sms_cmd13_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd13_L_bit0.setChecked(true);
                                            sms_cmd13_L_bit1.setChecked(false);
                                            sms_cmd13_L_bit2.setChecked(false);
                                            sms_cmd13_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd13_L_bit0.setChecked(false);
                                            sms_cmd13_L_bit1.setChecked(true);
                                            sms_cmd13_L_bit2.setChecked(false);
                                            sms_cmd13_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd13_L_bit0.setChecked(true);
                                            sms_cmd13_L_bit1.setChecked(true);
                                            sms_cmd13_L_bit2.setChecked(false);
                                            sms_cmd13_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd13_L_bit0.setChecked(false);
                                            sms_cmd13_L_bit1.setChecked(false);
                                            sms_cmd13_L_bit2.setChecked(true);
                                            sms_cmd13_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd13_L_bit0.setChecked(true);
                                            sms_cmd13_L_bit1.setChecked(false);
                                            sms_cmd13_L_bit2.setChecked(true);
                                            sms_cmd13_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd13_L_bit0.setChecked(false);
                                            sms_cmd13_L_bit1.setChecked(true);
                                            sms_cmd13_L_bit2.setChecked(true);
                                            sms_cmd13_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd13_L_bit0.setChecked(true);
                                            sms_cmd13_L_bit1.setChecked(true);
                                            sms_cmd13_L_bit2.setChecked(true);
                                            sms_cmd13_L_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd13_L_bit4.setChecked(false);
                                            sms_cmd13_L_bit5.setChecked(false);
                                            sms_cmd13_L_bit6.setChecked(false);
                                            sms_cmd13_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd13_L_bit4.setChecked(true);
                                            sms_cmd13_L_bit5.setChecked(false);
                                            sms_cmd13_L_bit6.setChecked(false);
                                            sms_cmd13_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd13_L_bit4.setChecked(false);
                                            sms_cmd13_L_bit5.setChecked(true);
                                            sms_cmd13_L_bit6.setChecked(false);
                                            sms_cmd13_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd13_L_bit4.setChecked(true);
                                            sms_cmd13_L_bit5.setChecked(true);
                                            sms_cmd13_L_bit6.setChecked(false);
                                            sms_cmd13_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd13_L_bit4.setChecked(false);
                                            sms_cmd13_L_bit5.setChecked(false);
                                            sms_cmd13_L_bit6.setChecked(true);
                                            sms_cmd13_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd13_L_bit4.setChecked(true);
                                            sms_cmd13_L_bit5.setChecked(false);
                                            sms_cmd13_L_bit6.setChecked(true);
                                            sms_cmd13_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd13_L_bit4.setChecked(false);
                                            sms_cmd13_L_bit5.setChecked(true);
                                            sms_cmd13_L_bit6.setChecked(true);
                                            sms_cmd13_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd13_L_bit4.setChecked(true);
                                            sms_cmd13_L_bit5.setChecked(true);
                                            sms_cmd13_L_bit6.setChecked(true);
                                            sms_cmd13_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd13_L_bit4.setChecked(false);
                                            sms_cmd13_L_bit5.setChecked(false);
                                            sms_cmd13_L_bit6.setChecked(false);
                                            sms_cmd13_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd13_L_bit4.setChecked(true);
                                            sms_cmd13_L_bit5.setChecked(false);
                                            sms_cmd13_L_bit6.setChecked(false);
                                            sms_cmd13_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd13_L_bit4.setChecked(false);
                                            sms_cmd13_L_bit5.setChecked(true);
                                            sms_cmd13_L_bit6.setChecked(false);
                                            sms_cmd13_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd13_L_bit4.setChecked(true);
                                            sms_cmd13_L_bit5.setChecked(true);
                                            sms_cmd13_L_bit6.setChecked(false);
                                            sms_cmd13_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd13_L_bit4.setChecked(false);
                                            sms_cmd13_L_bit5.setChecked(false);
                                            sms_cmd13_L_bit6.setChecked(true);
                                            sms_cmd13_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd13_L_bit4.setChecked(true);
                                            sms_cmd13_L_bit5.setChecked(false);
                                            sms_cmd13_L_bit6.setChecked(true);
                                            sms_cmd13_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd13_L_bit4.setChecked(false);
                                            sms_cmd13_L_bit5.setChecked(true);
                                            sms_cmd13_L_bit6.setChecked(true);
                                            sms_cmd13_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd13_L_bit4.setChecked(true);
                                            sms_cmd13_L_bit5.setChecked(true);
                                            sms_cmd13_L_bit6.setChecked(true);
                                            sms_cmd13_L_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd13_H_bit0.setChecked(false);
                                            sms_cmd13_H_bit1.setChecked(false);
                                            sms_cmd13_H_bit2.setChecked(false);
                                            sms_cmd13_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd13_H_bit0.setChecked(true);
                                            sms_cmd13_H_bit1.setChecked(false);
                                            sms_cmd13_H_bit2.setChecked(false);
                                            sms_cmd13_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd13_H_bit0.setChecked(false);
                                            sms_cmd13_H_bit1.setChecked(true);
                                            sms_cmd13_H_bit2.setChecked(false);
                                            sms_cmd13_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd13_H_bit0.setChecked(true);
                                            sms_cmd13_H_bit1.setChecked(true);
                                            sms_cmd13_H_bit2.setChecked(false);
                                            sms_cmd13_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd13_H_bit0.setChecked(false);
                                            sms_cmd13_H_bit1.setChecked(false);
                                            sms_cmd13_H_bit2.setChecked(true);
                                            sms_cmd13_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd13_H_bit0.setChecked(true);
                                            sms_cmd13_H_bit1.setChecked(false);
                                            sms_cmd13_H_bit2.setChecked(true);
                                            sms_cmd13_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd13_H_bit0.setChecked(false);
                                            sms_cmd13_H_bit1.setChecked(true);
                                            sms_cmd13_H_bit2.setChecked(true);
                                            sms_cmd13_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd13_H_bit0.setChecked(true);
                                            sms_cmd13_H_bit1.setChecked(true);
                                            sms_cmd13_H_bit2.setChecked(true);
                                            sms_cmd13_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd13_H_bit0.setChecked(false);
                                            sms_cmd13_H_bit1.setChecked(false);
                                            sms_cmd13_H_bit2.setChecked(false);
                                            sms_cmd13_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd13_H_bit0.setChecked(true);
                                            sms_cmd13_H_bit1.setChecked(false);
                                            sms_cmd13_H_bit2.setChecked(false);
                                            sms_cmd13_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd13_H_bit0.setChecked(false);
                                            sms_cmd13_H_bit1.setChecked(true);
                                            sms_cmd13_H_bit2.setChecked(false);
                                            sms_cmd13_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd13_H_bit0.setChecked(true);
                                            sms_cmd13_H_bit1.setChecked(true);
                                            sms_cmd13_H_bit2.setChecked(false);
                                            sms_cmd13_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd13_H_bit0.setChecked(false);
                                            sms_cmd13_H_bit1.setChecked(false);
                                            sms_cmd13_H_bit2.setChecked(true);
                                            sms_cmd13_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd13_H_bit0.setChecked(true);
                                            sms_cmd13_H_bit1.setChecked(false);
                                            sms_cmd13_H_bit2.setChecked(true);
                                            sms_cmd13_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd13_H_bit0.setChecked(false);
                                            sms_cmd13_H_bit1.setChecked(true);
                                            sms_cmd13_H_bit2.setChecked(true);
                                            sms_cmd13_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd13_H_bit0.setChecked(true);
                                            sms_cmd13_H_bit1.setChecked(true);
                                            sms_cmd13_H_bit2.setChecked(true);
                                            sms_cmd13_H_bit3.setChecked(true);
                                        }
                                        /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd13_H_bit4.setChecked(false);
                                            sms_cmd13_H_bit5.setChecked(false);
                                            sms_cmd13_H_bit6.setChecked(false);
                                            sms_cmd13_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd13_H_bit4.setChecked(true);
                                            sms_cmd13_H_bit5.setChecked(false);
                                            sms_cmd13_H_bit6.setChecked(false);
                                            sms_cmd13_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd13_H_bit4.setChecked(false);
                                            sms_cmd13_H_bit5.setChecked(true);
                                            sms_cmd13_H_bit6.setChecked(false);
                                            sms_cmd13_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd13_H_bit4.setChecked(true);
                                            sms_cmd13_H_bit5.setChecked(true);
                                            sms_cmd13_H_bit6.setChecked(false);
                                            sms_cmd13_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd13_H_bit4.setChecked(false);
                                            sms_cmd13_H_bit5.setChecked(false);
                                            sms_cmd13_H_bit6.setChecked(true);
                                            sms_cmd13_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd13_H_bit4.setChecked(true);
                                            sms_cmd13_H_bit5.setChecked(false);
                                            sms_cmd13_H_bit6.setChecked(true);
                                            sms_cmd13_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd13_H_bit4.setChecked(false);
                                            sms_cmd13_H_bit5.setChecked(true);
                                            sms_cmd13_H_bit6.setChecked(true);
                                            sms_cmd13_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd13_H_bit4.setChecked(true);
                                            sms_cmd13_H_bit5.setChecked(true);
                                            sms_cmd13_H_bit6.setChecked(true);
                                            sms_cmd13_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd13_H_bit4.setChecked(false);
                                            sms_cmd13_H_bit5.setChecked(false);
                                            sms_cmd13_H_bit6.setChecked(false);
                                            sms_cmd13_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd13_H_bit4.setChecked(true);
                                            sms_cmd13_H_bit5.setChecked(false);
                                            sms_cmd13_H_bit6.setChecked(false);
                                            sms_cmd13_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd13_H_bit4.setChecked(false);
                                            sms_cmd13_H_bit5.setChecked(true);
                                            sms_cmd13_H_bit6.setChecked(false);
                                            sms_cmd13_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd13_H_bit4.setChecked(true);
                                            sms_cmd13_H_bit5.setChecked(true);
                                            sms_cmd13_H_bit6.setChecked(false);
                                            sms_cmd13_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd13_H_bit4.setChecked(false);
                                            sms_cmd13_H_bit5.setChecked(false);
                                            sms_cmd13_H_bit6.setChecked(true);
                                            sms_cmd13_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd13_H_bit4.setChecked(true);
                                            sms_cmd13_H_bit5.setChecked(false);
                                            sms_cmd13_H_bit6.setChecked(true);
                                            sms_cmd13_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd13_H_bit4.setChecked(false);
                                            sms_cmd13_H_bit5.setChecked(true);
                                            sms_cmd13_H_bit6.setChecked(true);
                                            sms_cmd13_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd13_H_bit4.setChecked(true);
                                            sms_cmd13_H_bit5.setChecked(true);
                                            sms_cmd13_H_bit6.setChecked(true);
                                            sms_cmd13_H_bit7.setChecked(true);
                                        }
                                        /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FIFTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(false);
                                            sms_cmd13_L_M_bit1.setChecked(false);
                                            sms_cmd13_L_M_bit2.setChecked(false);
                                            sms_cmd13_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(true);
                                            sms_cmd13_L_M_bit1.setChecked(false);
                                            sms_cmd13_L_M_bit2.setChecked(false);
                                            sms_cmd13_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(false);
                                            sms_cmd13_L_M_bit1.setChecked(true);
                                            sms_cmd13_L_M_bit2.setChecked(false);
                                            sms_cmd13_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(true);
                                            sms_cmd13_L_M_bit1.setChecked(true);
                                            sms_cmd13_L_M_bit2.setChecked(false);
                                            sms_cmd13_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(false);
                                            sms_cmd13_L_M_bit1.setChecked(false);
                                            sms_cmd13_L_M_bit2.setChecked(true);
                                            sms_cmd13_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(true);
                                            sms_cmd13_L_M_bit1.setChecked(false);
                                            sms_cmd13_L_M_bit2.setChecked(true);
                                            sms_cmd13_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(false);
                                            sms_cmd13_L_M_bit1.setChecked(true);
                                            sms_cmd13_L_M_bit2.setChecked(true);
                                            sms_cmd13_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(true);
                                            sms_cmd13_L_M_bit1.setChecked(true);
                                            sms_cmd13_L_M_bit2.setChecked(true);
                                            sms_cmd13_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(false);
                                            sms_cmd13_L_M_bit1.setChecked(false);
                                            sms_cmd13_L_M_bit2.setChecked(false);
                                            sms_cmd13_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(true);
                                            sms_cmd13_L_M_bit1.setChecked(false);
                                            sms_cmd13_L_M_bit2.setChecked(false);
                                            sms_cmd13_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(false);
                                            sms_cmd13_L_M_bit1.setChecked(true);
                                            sms_cmd13_L_M_bit2.setChecked(false);
                                            sms_cmd13_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(true);
                                            sms_cmd13_L_M_bit1.setChecked(true);
                                            sms_cmd13_L_M_bit2.setChecked(false);
                                            sms_cmd13_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(false);
                                            sms_cmd13_L_M_bit1.setChecked(false);
                                            sms_cmd13_L_M_bit2.setChecked(true);
                                            sms_cmd13_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(true);
                                            sms_cmd13_L_M_bit1.setChecked(false);
                                            sms_cmd13_L_M_bit2.setChecked(true);
                                            sms_cmd13_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(false);
                                            sms_cmd13_L_M_bit1.setChecked(true);
                                            sms_cmd13_L_M_bit2.setChecked(true);
                                            sms_cmd13_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd13_L_M_bit0.setChecked(true);
                                            sms_cmd13_L_M_bit1.setChecked(true);
                                            sms_cmd13_L_M_bit2.setChecked(true);
                                            sms_cmd13_L_M_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIFTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SIXTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(false);
                                            sms_cmd13_L_M_bit5.setChecked(false);
                                            sms_cmd13_L_M_bit6.setChecked(false);
                                            sms_cmd13_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(true);
                                            sms_cmd13_L_M_bit5.setChecked(false);
                                            sms_cmd13_L_M_bit6.setChecked(false);
                                            sms_cmd13_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(false);
                                            sms_cmd13_L_M_bit5.setChecked(true);
                                            sms_cmd13_L_M_bit6.setChecked(false);
                                            sms_cmd13_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(true);
                                            sms_cmd13_L_M_bit5.setChecked(true);
                                            sms_cmd13_L_M_bit6.setChecked(false);
                                            sms_cmd13_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(false);
                                            sms_cmd13_L_M_bit5.setChecked(false);
                                            sms_cmd13_L_M_bit6.setChecked(true);
                                            sms_cmd13_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(true);
                                            sms_cmd13_L_M_bit5.setChecked(false);
                                            sms_cmd13_L_M_bit6.setChecked(true);
                                            sms_cmd13_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(false);
                                            sms_cmd13_L_M_bit5.setChecked(true);
                                            sms_cmd13_L_M_bit6.setChecked(true);
                                            sms_cmd13_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(true);
                                            sms_cmd13_L_M_bit5.setChecked(true);
                                            sms_cmd13_L_M_bit6.setChecked(true);
                                            sms_cmd13_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(false);
                                            sms_cmd13_L_M_bit5.setChecked(false);
                                            sms_cmd13_L_M_bit6.setChecked(false);
                                            sms_cmd13_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(true);
                                            sms_cmd13_L_M_bit5.setChecked(false);
                                            sms_cmd13_L_M_bit6.setChecked(false);
                                            sms_cmd13_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(false);
                                            sms_cmd13_L_M_bit5.setChecked(true);
                                            sms_cmd13_L_M_bit6.setChecked(false);
                                            sms_cmd13_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(true);
                                            sms_cmd13_L_M_bit5.setChecked(true);
                                            sms_cmd13_L_M_bit6.setChecked(false);
                                            sms_cmd13_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(false);
                                            sms_cmd13_L_M_bit5.setChecked(false);
                                            sms_cmd13_L_M_bit6.setChecked(true);
                                            sms_cmd13_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(true);
                                            sms_cmd13_L_M_bit5.setChecked(false);
                                            sms_cmd13_L_M_bit6.setChecked(true);
                                            sms_cmd13_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(false);
                                            sms_cmd13_L_M_bit5.setChecked(true);
                                            sms_cmd13_L_M_bit6.setChecked(true);
                                            sms_cmd13_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd13_L_M_bit4.setChecked(true);
                                            sms_cmd13_L_M_bit5.setChecked(true);
                                            sms_cmd13_L_M_bit6.setChecked(true);
                                            sms_cmd13_L_M_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SIXTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SEVENTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(false);
                                            sms_cmd13_H_M_bit1.setChecked(false);
                                            sms_cmd13_H_M_bit2.setChecked(false);
                                            sms_cmd13_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(true);
                                            sms_cmd13_H_M_bit1.setChecked(false);
                                            sms_cmd13_H_M_bit2.setChecked(false);
                                            sms_cmd13_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(false);
                                            sms_cmd13_H_M_bit1.setChecked(true);
                                            sms_cmd13_H_M_bit2.setChecked(false);
                                            sms_cmd13_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(true);
                                            sms_cmd13_H_M_bit1.setChecked(true);
                                            sms_cmd13_H_M_bit2.setChecked(false);
                                            sms_cmd13_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(false);
                                            sms_cmd13_H_M_bit1.setChecked(false);
                                            sms_cmd13_H_M_bit2.setChecked(true);
                                            sms_cmd13_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(true);
                                            sms_cmd13_H_M_bit1.setChecked(false);
                                            sms_cmd13_H_M_bit2.setChecked(true);
                                            sms_cmd13_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(false);
                                            sms_cmd13_H_M_bit1.setChecked(true);
                                            sms_cmd13_H_M_bit2.setChecked(true);
                                            sms_cmd13_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(true);
                                            sms_cmd13_H_M_bit1.setChecked(true);
                                            sms_cmd13_H_M_bit2.setChecked(true);
                                            sms_cmd13_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(false);
                                            sms_cmd13_H_M_bit1.setChecked(false);
                                            sms_cmd13_H_M_bit2.setChecked(false);
                                            sms_cmd13_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(true);
                                            sms_cmd13_H_M_bit1.setChecked(false);
                                            sms_cmd13_H_M_bit2.setChecked(false);
                                            sms_cmd13_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(false);
                                            sms_cmd13_H_M_bit1.setChecked(true);
                                            sms_cmd13_H_M_bit2.setChecked(false);
                                            sms_cmd13_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(true);
                                            sms_cmd13_H_M_bit1.setChecked(true);
                                            sms_cmd13_H_M_bit2.setChecked(false);
                                            sms_cmd13_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(false);
                                            sms_cmd13_H_M_bit1.setChecked(false);
                                            sms_cmd13_H_M_bit2.setChecked(true);
                                            sms_cmd13_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(true);
                                            sms_cmd13_H_M_bit1.setChecked(false);
                                            sms_cmd13_H_M_bit2.setChecked(true);
                                            sms_cmd13_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(false);
                                            sms_cmd13_H_M_bit1.setChecked(true);
                                            sms_cmd13_H_M_bit2.setChecked(true);
                                            sms_cmd13_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd13_H_M_bit0.setChecked(true);
                                            sms_cmd13_H_M_bit1.setChecked(true);
                                            sms_cmd13_H_M_bit2.setChecked(true);
                                            sms_cmd13_H_M_bit3.setChecked(true);
                                        }
                                        /***************************** END OF SEVENTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON EIGHTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(false);
                                            sms_cmd13_H_M_bit5.setChecked(false);
                                            sms_cmd13_H_M_bit6.setChecked(false);
                                            sms_cmd13_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(true);
                                            sms_cmd13_H_M_bit5.setChecked(false);
                                            sms_cmd13_H_M_bit6.setChecked(false);
                                            sms_cmd13_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(false);
                                            sms_cmd13_H_M_bit5.setChecked(true);
                                            sms_cmd13_H_M_bit6.setChecked(false);
                                            sms_cmd13_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(true);
                                            sms_cmd13_H_M_bit5.setChecked(true);
                                            sms_cmd13_H_M_bit6.setChecked(false);
                                            sms_cmd13_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(false);
                                            sms_cmd13_H_M_bit5.setChecked(false);
                                            sms_cmd13_H_M_bit6.setChecked(true);
                                            sms_cmd13_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(true);
                                            sms_cmd13_H_M_bit5.setChecked(false);
                                            sms_cmd13_H_M_bit6.setChecked(true);
                                            sms_cmd13_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(false);
                                            sms_cmd13_H_M_bit5.setChecked(true);
                                            sms_cmd13_H_M_bit6.setChecked(true);
                                            sms_cmd13_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(true);
                                            sms_cmd13_H_M_bit5.setChecked(true);
                                            sms_cmd13_H_M_bit6.setChecked(true);
                                            sms_cmd13_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(false);
                                            sms_cmd13_H_M_bit5.setChecked(false);
                                            sms_cmd13_H_M_bit6.setChecked(false);
                                            sms_cmd13_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(true);
                                            sms_cmd13_H_M_bit5.setChecked(false);
                                            sms_cmd13_H_M_bit6.setChecked(false);
                                            sms_cmd13_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(false);
                                            sms_cmd13_H_M_bit5.setChecked(true);
                                            sms_cmd13_H_M_bit6.setChecked(false);
                                            sms_cmd13_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(true);
                                            sms_cmd13_H_M_bit5.setChecked(true);
                                            sms_cmd13_H_M_bit6.setChecked(false);
                                            sms_cmd13_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(false);
                                            sms_cmd13_H_M_bit5.setChecked(false);
                                            sms_cmd13_H_M_bit6.setChecked(true);
                                            sms_cmd13_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(true);
                                            sms_cmd13_H_M_bit5.setChecked(false);
                                            sms_cmd13_H_M_bit6.setChecked(true);
                                            sms_cmd13_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(false);
                                            sms_cmd13_H_M_bit5.setChecked(true);
                                            sms_cmd13_H_M_bit6.setChecked(true);
                                            sms_cmd13_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd13_H_M_bit4.setChecked(true);
                                            sms_cmd13_H_M_bit5.setChecked(true);
                                            sms_cmd13_H_M_bit6.setChecked(true);
                                            sms_cmd13_H_M_bit7.setChecked(true);
                                        }
                                        /***************************** END OF EIGHTH CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR CAR KEYS//
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("4"))
                                    {
                                        displayTimer(displayTime);
                                        carkeysTargetView.getParent().requestChildFocus(carkeysTargetView,carkeysTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd14_L_bit0.setChecked(false);
                                            sms_cmd14_L_bit1.setChecked(false);
                                            sms_cmd14_L_bit2.setChecked(false);
                                            sms_cmd14_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd14_L_bit0.setChecked(true);
                                            sms_cmd14_L_bit1.setChecked(false);
                                            sms_cmd14_L_bit2.setChecked(false);
                                            sms_cmd14_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd14_L_bit0.setChecked(false);
                                            sms_cmd14_L_bit1.setChecked(true);
                                            sms_cmd14_L_bit2.setChecked(false);
                                            sms_cmd14_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd14_L_bit0.setChecked(true);
                                            sms_cmd14_L_bit1.setChecked(true);
                                            sms_cmd14_L_bit2.setChecked(false);
                                            sms_cmd14_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd14_L_bit0.setChecked(false);
                                            sms_cmd14_L_bit1.setChecked(false);
                                            sms_cmd14_L_bit2.setChecked(true);
                                            sms_cmd14_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd14_L_bit0.setChecked(true);
                                            sms_cmd14_L_bit1.setChecked(false);
                                            sms_cmd14_L_bit2.setChecked(true);
                                            sms_cmd14_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd14_L_bit0.setChecked(false);
                                            sms_cmd14_L_bit1.setChecked(true);
                                            sms_cmd14_L_bit2.setChecked(true);
                                            sms_cmd14_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd14_L_bit0.setChecked(true);
                                            sms_cmd14_L_bit1.setChecked(true);
                                            sms_cmd14_L_bit2.setChecked(true);
                                            sms_cmd14_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd14_L_bit0.setChecked(false);
                                            sms_cmd14_L_bit1.setChecked(false);
                                            sms_cmd14_L_bit2.setChecked(false);
                                            sms_cmd14_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd14_L_bit0.setChecked(true);
                                            sms_cmd14_L_bit1.setChecked(false);
                                            sms_cmd14_L_bit2.setChecked(false);
                                            sms_cmd14_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd14_L_bit0.setChecked(false);
                                            sms_cmd14_L_bit1.setChecked(true);
                                            sms_cmd14_L_bit2.setChecked(false);
                                            sms_cmd14_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd14_L_bit0.setChecked(true);
                                            sms_cmd14_L_bit1.setChecked(true);
                                            sms_cmd14_L_bit2.setChecked(false);
                                            sms_cmd14_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd14_L_bit0.setChecked(false);
                                            sms_cmd14_L_bit1.setChecked(false);
                                            sms_cmd14_L_bit2.setChecked(true);
                                            sms_cmd14_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd14_L_bit0.setChecked(true);
                                            sms_cmd14_L_bit1.setChecked(false);
                                            sms_cmd14_L_bit2.setChecked(true);
                                            sms_cmd14_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd14_L_bit0.setChecked(false);
                                            sms_cmd14_L_bit1.setChecked(true);
                                            sms_cmd14_L_bit2.setChecked(true);
                                            sms_cmd14_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd14_L_bit0.setChecked(true);
                                            sms_cmd14_L_bit1.setChecked(true);
                                            sms_cmd14_L_bit2.setChecked(true);
                                            sms_cmd14_L_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd14_L_bit4.setChecked(false);
                                            sms_cmd14_L_bit5.setChecked(false);
                                            sms_cmd14_L_bit6.setChecked(false);
                                            sms_cmd14_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd14_L_bit4.setChecked(true);
                                            sms_cmd14_L_bit5.setChecked(false);
                                            sms_cmd14_L_bit6.setChecked(false);
                                            sms_cmd14_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd14_L_bit4.setChecked(false);
                                            sms_cmd14_L_bit5.setChecked(true);
                                            sms_cmd14_L_bit6.setChecked(false);
                                            sms_cmd14_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd14_L_bit4.setChecked(true);
                                            sms_cmd14_L_bit5.setChecked(true);
                                            sms_cmd14_L_bit6.setChecked(false);
                                            sms_cmd14_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd14_L_bit4.setChecked(false);
                                            sms_cmd14_L_bit5.setChecked(false);
                                            sms_cmd14_L_bit6.setChecked(true);
                                            sms_cmd14_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd14_L_bit4.setChecked(true);
                                            sms_cmd14_L_bit5.setChecked(false);
                                            sms_cmd14_L_bit6.setChecked(true);
                                            sms_cmd14_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd14_L_bit4.setChecked(false);
                                            sms_cmd14_L_bit5.setChecked(true);
                                            sms_cmd14_L_bit6.setChecked(true);
                                            sms_cmd14_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd14_L_bit4.setChecked(true);
                                            sms_cmd14_L_bit5.setChecked(true);
                                            sms_cmd14_L_bit6.setChecked(true);
                                            sms_cmd14_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd14_L_bit4.setChecked(false);
                                            sms_cmd14_L_bit5.setChecked(false);
                                            sms_cmd14_L_bit6.setChecked(false);
                                            sms_cmd14_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd14_L_bit4.setChecked(true);
                                            sms_cmd14_L_bit5.setChecked(false);
                                            sms_cmd14_L_bit6.setChecked(false);
                                            sms_cmd14_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd14_L_bit4.setChecked(false);
                                            sms_cmd14_L_bit5.setChecked(true);
                                            sms_cmd14_L_bit6.setChecked(false);
                                            sms_cmd14_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd14_L_bit4.setChecked(true);
                                            sms_cmd14_L_bit5.setChecked(true);
                                            sms_cmd14_L_bit6.setChecked(false);
                                            sms_cmd14_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd14_L_bit4.setChecked(false);
                                            sms_cmd14_L_bit5.setChecked(false);
                                            sms_cmd14_L_bit6.setChecked(true);
                                            sms_cmd14_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd14_L_bit4.setChecked(true);
                                            sms_cmd14_L_bit5.setChecked(false);
                                            sms_cmd14_L_bit6.setChecked(true);
                                            sms_cmd14_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd14_L_bit4.setChecked(false);
                                            sms_cmd14_L_bit5.setChecked(true);
                                            sms_cmd14_L_bit6.setChecked(true);
                                            sms_cmd14_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd14_L_bit4.setChecked(true);
                                            sms_cmd14_L_bit5.setChecked(true);
                                            sms_cmd14_L_bit6.setChecked(true);
                                            sms_cmd14_L_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd14_H_bit0.setChecked(false);
                                            sms_cmd14_H_bit1.setChecked(false);
                                            sms_cmd14_H_bit2.setChecked(false);
                                            sms_cmd14_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd14_H_bit0.setChecked(true);
                                            sms_cmd14_H_bit1.setChecked(false);
                                            sms_cmd14_H_bit2.setChecked(false);
                                            sms_cmd14_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd14_H_bit0.setChecked(false);
                                            sms_cmd14_H_bit1.setChecked(true);
                                            sms_cmd14_H_bit2.setChecked(false);
                                            sms_cmd14_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd14_H_bit0.setChecked(true);
                                            sms_cmd14_H_bit1.setChecked(true);
                                            sms_cmd14_H_bit2.setChecked(false);
                                            sms_cmd14_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd14_H_bit0.setChecked(false);
                                            sms_cmd14_H_bit1.setChecked(false);
                                            sms_cmd14_H_bit2.setChecked(true);
                                            sms_cmd14_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd14_H_bit0.setChecked(true);
                                            sms_cmd14_H_bit1.setChecked(false);
                                            sms_cmd14_H_bit2.setChecked(true);
                                            sms_cmd14_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd14_H_bit0.setChecked(false);
                                            sms_cmd14_H_bit1.setChecked(true);
                                            sms_cmd14_H_bit2.setChecked(true);
                                            sms_cmd14_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd14_H_bit0.setChecked(true);
                                            sms_cmd14_H_bit1.setChecked(true);
                                            sms_cmd14_H_bit2.setChecked(true);
                                            sms_cmd14_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd14_H_bit0.setChecked(false);
                                            sms_cmd14_H_bit1.setChecked(false);
                                            sms_cmd14_H_bit2.setChecked(false);
                                            sms_cmd14_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd14_H_bit0.setChecked(true);
                                            sms_cmd14_H_bit1.setChecked(false);
                                            sms_cmd14_H_bit2.setChecked(false);
                                            sms_cmd14_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd14_H_bit0.setChecked(false);
                                            sms_cmd14_H_bit1.setChecked(true);
                                            sms_cmd14_H_bit2.setChecked(false);
                                            sms_cmd14_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd14_H_bit0.setChecked(true);
                                            sms_cmd14_H_bit1.setChecked(true);
                                            sms_cmd14_H_bit2.setChecked(false);
                                            sms_cmd14_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd14_H_bit0.setChecked(false);
                                            sms_cmd14_H_bit1.setChecked(false);
                                            sms_cmd14_H_bit2.setChecked(true);
                                            sms_cmd14_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd14_H_bit0.setChecked(true);
                                            sms_cmd14_H_bit1.setChecked(false);
                                            sms_cmd14_H_bit2.setChecked(true);
                                            sms_cmd14_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd14_H_bit0.setChecked(false);
                                            sms_cmd14_H_bit1.setChecked(true);
                                            sms_cmd14_H_bit2.setChecked(true);
                                            sms_cmd14_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd14_H_bit0.setChecked(true);
                                            sms_cmd14_H_bit1.setChecked(true);
                                            sms_cmd14_H_bit2.setChecked(true);
                                            sms_cmd14_H_bit3.setChecked(true);
                                        }
                                        /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd14_H_bit4.setChecked(false);
                                            sms_cmd14_H_bit5.setChecked(false);
                                            sms_cmd14_H_bit6.setChecked(false);
                                            sms_cmd14_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd14_H_bit4.setChecked(true);
                                            sms_cmd14_H_bit5.setChecked(false);
                                            sms_cmd14_H_bit6.setChecked(false);
                                            sms_cmd14_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd14_H_bit4.setChecked(false);
                                            sms_cmd14_H_bit5.setChecked(true);
                                            sms_cmd14_H_bit6.setChecked(false);
                                            sms_cmd14_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd14_H_bit4.setChecked(true);
                                            sms_cmd14_H_bit5.setChecked(true);
                                            sms_cmd14_H_bit6.setChecked(false);
                                            sms_cmd14_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd14_H_bit4.setChecked(false);
                                            sms_cmd14_H_bit5.setChecked(false);
                                            sms_cmd14_H_bit6.setChecked(true);
                                            sms_cmd14_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd14_H_bit4.setChecked(true);
                                            sms_cmd14_H_bit5.setChecked(false);
                                            sms_cmd14_H_bit6.setChecked(true);
                                            sms_cmd14_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd14_H_bit4.setChecked(false);
                                            sms_cmd14_H_bit5.setChecked(true);
                                            sms_cmd14_H_bit6.setChecked(true);
                                            sms_cmd14_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd14_H_bit4.setChecked(true);
                                            sms_cmd14_H_bit5.setChecked(true);
                                            sms_cmd14_H_bit6.setChecked(true);
                                            sms_cmd14_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd14_H_bit4.setChecked(false);
                                            sms_cmd14_H_bit5.setChecked(false);
                                            sms_cmd14_H_bit6.setChecked(false);
                                            sms_cmd14_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd14_H_bit4.setChecked(true);
                                            sms_cmd14_H_bit5.setChecked(false);
                                            sms_cmd14_H_bit6.setChecked(false);
                                            sms_cmd14_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd14_H_bit4.setChecked(false);
                                            sms_cmd14_H_bit5.setChecked(true);
                                            sms_cmd14_H_bit6.setChecked(false);
                                            sms_cmd14_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd14_H_bit4.setChecked(true);
                                            sms_cmd14_H_bit5.setChecked(true);
                                            sms_cmd14_H_bit6.setChecked(false);
                                            sms_cmd14_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd14_H_bit4.setChecked(false);
                                            sms_cmd14_H_bit5.setChecked(false);
                                            sms_cmd14_H_bit6.setChecked(true);
                                            sms_cmd14_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd14_H_bit4.setChecked(true);
                                            sms_cmd14_H_bit5.setChecked(false);
                                            sms_cmd14_H_bit6.setChecked(true);
                                            sms_cmd14_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd14_H_bit4.setChecked(false);
                                            sms_cmd14_H_bit5.setChecked(true);
                                            sms_cmd14_H_bit6.setChecked(true);
                                            sms_cmd14_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd14_H_bit4.setChecked(true);
                                            sms_cmd14_H_bit5.setChecked(true);
                                            sms_cmd14_H_bit6.setChecked(true);
                                            sms_cmd14_H_bit7.setChecked(true);
                                        }
                                        /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FIFTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(false);
                                            sms_cmd14_L_M_bit1.setChecked(false);
                                            sms_cmd14_L_M_bit2.setChecked(false);
                                            sms_cmd14_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(true);
                                            sms_cmd14_L_M_bit1.setChecked(false);
                                            sms_cmd14_L_M_bit2.setChecked(false);
                                            sms_cmd14_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(false);
                                            sms_cmd14_L_M_bit1.setChecked(true);
                                            sms_cmd14_L_M_bit2.setChecked(false);
                                            sms_cmd14_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(true);
                                            sms_cmd14_L_M_bit1.setChecked(true);
                                            sms_cmd14_L_M_bit2.setChecked(false);
                                            sms_cmd14_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(false);
                                            sms_cmd14_L_M_bit1.setChecked(false);
                                            sms_cmd14_L_M_bit2.setChecked(true);
                                            sms_cmd14_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(true);
                                            sms_cmd14_L_M_bit1.setChecked(false);
                                            sms_cmd14_L_M_bit2.setChecked(true);
                                            sms_cmd14_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(false);
                                            sms_cmd14_L_M_bit1.setChecked(true);
                                            sms_cmd14_L_M_bit2.setChecked(true);
                                            sms_cmd14_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(true);
                                            sms_cmd14_L_M_bit1.setChecked(true);
                                            sms_cmd14_L_M_bit2.setChecked(true);
                                            sms_cmd14_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(false);
                                            sms_cmd14_L_M_bit1.setChecked(false);
                                            sms_cmd14_L_M_bit2.setChecked(false);
                                            sms_cmd14_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(true);
                                            sms_cmd14_L_M_bit1.setChecked(false);
                                            sms_cmd14_L_M_bit2.setChecked(false);
                                            sms_cmd14_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(false);
                                            sms_cmd14_L_M_bit1.setChecked(true);
                                            sms_cmd14_L_M_bit2.setChecked(false);
                                            sms_cmd14_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(true);
                                            sms_cmd14_L_M_bit1.setChecked(true);
                                            sms_cmd14_L_M_bit2.setChecked(false);
                                            sms_cmd14_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(false);
                                            sms_cmd14_L_M_bit1.setChecked(false);
                                            sms_cmd14_L_M_bit2.setChecked(true);
                                            sms_cmd14_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(true);
                                            sms_cmd14_L_M_bit1.setChecked(false);
                                            sms_cmd14_L_M_bit2.setChecked(true);
                                            sms_cmd14_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(false);
                                            sms_cmd14_L_M_bit1.setChecked(true);
                                            sms_cmd14_L_M_bit2.setChecked(true);
                                            sms_cmd14_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd14_L_M_bit0.setChecked(true);
                                            sms_cmd14_L_M_bit1.setChecked(true);
                                            sms_cmd14_L_M_bit2.setChecked(true);
                                            sms_cmd14_L_M_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIFTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SIXTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(false);
                                            sms_cmd14_L_M_bit5.setChecked(false);
                                            sms_cmd14_L_M_bit6.setChecked(false);
                                            sms_cmd14_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(true);
                                            sms_cmd14_L_M_bit5.setChecked(false);
                                            sms_cmd14_L_M_bit6.setChecked(false);
                                            sms_cmd14_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(false);
                                            sms_cmd14_L_M_bit5.setChecked(true);
                                            sms_cmd14_L_M_bit6.setChecked(false);
                                            sms_cmd14_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(true);
                                            sms_cmd14_L_M_bit5.setChecked(true);
                                            sms_cmd14_L_M_bit6.setChecked(false);
                                            sms_cmd14_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(false);
                                            sms_cmd14_L_M_bit5.setChecked(false);
                                            sms_cmd14_L_M_bit6.setChecked(true);
                                            sms_cmd14_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(true);
                                            sms_cmd14_L_M_bit5.setChecked(false);
                                            sms_cmd14_L_M_bit6.setChecked(true);
                                            sms_cmd14_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(false);
                                            sms_cmd14_L_M_bit5.setChecked(true);
                                            sms_cmd14_L_M_bit6.setChecked(true);
                                            sms_cmd14_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(true);
                                            sms_cmd14_L_M_bit5.setChecked(true);
                                            sms_cmd14_L_M_bit6.setChecked(true);
                                            sms_cmd14_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(false);
                                            sms_cmd14_L_M_bit5.setChecked(false);
                                            sms_cmd14_L_M_bit6.setChecked(false);
                                            sms_cmd14_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(true);
                                            sms_cmd14_L_M_bit5.setChecked(false);
                                            sms_cmd14_L_M_bit6.setChecked(false);
                                            sms_cmd14_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(false);
                                            sms_cmd14_L_M_bit5.setChecked(true);
                                            sms_cmd14_L_M_bit6.setChecked(false);
                                            sms_cmd14_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(true);
                                            sms_cmd14_L_M_bit5.setChecked(true);
                                            sms_cmd14_L_M_bit6.setChecked(false);
                                            sms_cmd14_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(false);
                                            sms_cmd14_L_M_bit5.setChecked(false);
                                            sms_cmd14_L_M_bit6.setChecked(true);
                                            sms_cmd14_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(true);
                                            sms_cmd14_L_M_bit5.setChecked(false);
                                            sms_cmd14_L_M_bit6.setChecked(true);
                                            sms_cmd14_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(false);
                                            sms_cmd14_L_M_bit5.setChecked(true);
                                            sms_cmd14_L_M_bit6.setChecked(true);
                                            sms_cmd14_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd14_L_M_bit4.setChecked(true);
                                            sms_cmd14_L_M_bit5.setChecked(true);
                                            sms_cmd14_L_M_bit6.setChecked(true);
                                            sms_cmd14_L_M_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SIXTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SEVENTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(false);
                                            sms_cmd14_H_M_bit1.setChecked(false);
                                            sms_cmd14_H_M_bit2.setChecked(false);
                                            sms_cmd14_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(true);
                                            sms_cmd14_H_M_bit1.setChecked(false);
                                            sms_cmd14_H_M_bit2.setChecked(false);
                                            sms_cmd14_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(false);
                                            sms_cmd14_H_M_bit1.setChecked(true);
                                            sms_cmd14_H_M_bit2.setChecked(false);
                                            sms_cmd14_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(true);
                                            sms_cmd14_H_M_bit1.setChecked(true);
                                            sms_cmd14_H_M_bit2.setChecked(false);
                                            sms_cmd14_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(false);
                                            sms_cmd14_H_M_bit1.setChecked(false);
                                            sms_cmd14_H_M_bit2.setChecked(true);
                                            sms_cmd14_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(true);
                                            sms_cmd14_H_M_bit1.setChecked(false);
                                            sms_cmd14_H_M_bit2.setChecked(true);
                                            sms_cmd14_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(false);
                                            sms_cmd14_H_M_bit1.setChecked(true);
                                            sms_cmd14_H_M_bit2.setChecked(true);
                                            sms_cmd14_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(true);
                                            sms_cmd14_H_M_bit1.setChecked(true);
                                            sms_cmd14_H_M_bit2.setChecked(true);
                                            sms_cmd14_H_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(false);
                                            sms_cmd14_H_M_bit1.setChecked(false);
                                            sms_cmd14_H_M_bit2.setChecked(false);
                                            sms_cmd14_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(true);
                                            sms_cmd14_H_M_bit1.setChecked(false);
                                            sms_cmd14_H_M_bit2.setChecked(false);
                                            sms_cmd14_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(false);
                                            sms_cmd14_H_M_bit1.setChecked(true);
                                            sms_cmd14_H_M_bit2.setChecked(false);
                                            sms_cmd14_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(true);
                                            sms_cmd14_H_M_bit1.setChecked(true);
                                            sms_cmd14_H_M_bit2.setChecked(false);
                                            sms_cmd14_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(false);
                                            sms_cmd14_H_M_bit1.setChecked(false);
                                            sms_cmd14_H_M_bit2.setChecked(true);
                                            sms_cmd14_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(true);
                                            sms_cmd14_H_M_bit1.setChecked(false);
                                            sms_cmd14_H_M_bit2.setChecked(true);
                                            sms_cmd14_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(false);
                                            sms_cmd14_H_M_bit1.setChecked(true);
                                            sms_cmd14_H_M_bit2.setChecked(true);
                                            sms_cmd14_H_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-7))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd14_H_M_bit0.setChecked(true);
                                            sms_cmd14_H_M_bit1.setChecked(true);
                                            sms_cmd14_H_M_bit2.setChecked(true);
                                            sms_cmd14_H_M_bit3.setChecked(true);
                                        }
                                        /***************************** END OF SEVENTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON EIGHTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(false);
                                            sms_cmd14_H_M_bit5.setChecked(false);
                                            sms_cmd14_H_M_bit6.setChecked(false);
                                            sms_cmd14_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(true);
                                            sms_cmd14_H_M_bit5.setChecked(false);
                                            sms_cmd14_H_M_bit6.setChecked(false);
                                            sms_cmd14_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(false);
                                            sms_cmd14_H_M_bit5.setChecked(true);
                                            sms_cmd14_H_M_bit6.setChecked(false);
                                            sms_cmd14_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(true);
                                            sms_cmd14_H_M_bit5.setChecked(true);
                                            sms_cmd14_H_M_bit6.setChecked(false);
                                            sms_cmd14_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(false);
                                            sms_cmd14_H_M_bit5.setChecked(false);
                                            sms_cmd14_H_M_bit6.setChecked(true);
                                            sms_cmd14_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(true);
                                            sms_cmd14_H_M_bit5.setChecked(false);
                                            sms_cmd14_H_M_bit6.setChecked(true);
                                            sms_cmd14_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(false);
                                            sms_cmd14_H_M_bit5.setChecked(true);
                                            sms_cmd14_H_M_bit6.setChecked(true);
                                            sms_cmd14_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(true);
                                            sms_cmd14_H_M_bit5.setChecked(true);
                                            sms_cmd14_H_M_bit6.setChecked(true);
                                            sms_cmd14_H_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(false);
                                            sms_cmd14_H_M_bit5.setChecked(false);
                                            sms_cmd14_H_M_bit6.setChecked(false);
                                            sms_cmd14_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(true);
                                            sms_cmd14_H_M_bit5.setChecked(false);
                                            sms_cmd14_H_M_bit6.setChecked(false);
                                            sms_cmd14_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(false);
                                            sms_cmd14_H_M_bit5.setChecked(true);
                                            sms_cmd14_H_M_bit6.setChecked(false);
                                            sms_cmd14_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(true);
                                            sms_cmd14_H_M_bit5.setChecked(true);
                                            sms_cmd14_H_M_bit6.setChecked(false);
                                            sms_cmd14_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(false);
                                            sms_cmd14_H_M_bit5.setChecked(false);
                                            sms_cmd14_H_M_bit6.setChecked(true);
                                            sms_cmd14_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(true);
                                            sms_cmd14_H_M_bit5.setChecked(false);
                                            sms_cmd14_H_M_bit6.setChecked(true);
                                            sms_cmd14_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(false);
                                            sms_cmd14_H_M_bit5.setChecked(true);
                                            sms_cmd14_H_M_bit6.setChecked(true);
                                            sms_cmd14_H_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-8))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd14_H_M_bit4.setChecked(true);
                                            sms_cmd14_H_M_bit5.setChecked(true);
                                            sms_cmd14_H_M_bit6.setChecked(true);
                                            sms_cmd14_H_M_bit7.setChecked(true);
                                        }
                                        /***************************** END OF EIGHTH CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR LIFT ERROR STATUS//
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("6"))
                                    {
                                        displayTimer(displayTime);
                                        lifterrTargetView.getParent().requestChildFocus(lifterrTargetView,lifterrTargetView);
                                        /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd16_L_bit0.setChecked(false);
                                            sms_cmd16_L_bit1.setChecked(false);
                                            sms_cmd16_L_bit2.setChecked(false);
                                            sms_cmd16_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd16_L_bit0.setChecked(true);
                                            sms_cmd16_L_bit1.setChecked(false);
                                            sms_cmd16_L_bit2.setChecked(false);
                                            sms_cmd16_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd16_L_bit0.setChecked(false);
                                            sms_cmd16_L_bit1.setChecked(true);
                                            sms_cmd16_L_bit2.setChecked(false);
                                            sms_cmd16_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd16_L_bit0.setChecked(true);
                                            sms_cmd16_L_bit1.setChecked(true);
                                            sms_cmd16_L_bit2.setChecked(false);
                                            sms_cmd16_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd16_L_bit0.setChecked(false);
                                            sms_cmd16_L_bit1.setChecked(false);
                                            sms_cmd16_L_bit2.setChecked(true);
                                            sms_cmd16_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd16_L_bit0.setChecked(true);
                                            sms_cmd16_L_bit1.setChecked(false);
                                            sms_cmd16_L_bit2.setChecked(true);
                                            sms_cmd16_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd16_L_bit0.setChecked(false);
                                            sms_cmd16_L_bit1.setChecked(true);
                                            sms_cmd16_L_bit2.setChecked(true);
                                            sms_cmd16_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd16_L_bit0.setChecked(true);
                                            sms_cmd16_L_bit1.setChecked(true);
                                            sms_cmd16_L_bit2.setChecked(true);
                                            sms_cmd16_L_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd16_L_bit0.setChecked(false);
                                            sms_cmd16_L_bit1.setChecked(false);
                                            sms_cmd16_L_bit2.setChecked(false);
                                            sms_cmd16_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd16_L_bit0.setChecked(true);
                                            sms_cmd16_L_bit1.setChecked(false);
                                            sms_cmd16_L_bit2.setChecked(false);
                                            sms_cmd16_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd16_L_bit0.setChecked(false);
                                            sms_cmd16_L_bit1.setChecked(true);
                                            sms_cmd16_L_bit2.setChecked(false);
                                            sms_cmd16_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd16_L_bit0.setChecked(true);
                                            sms_cmd16_L_bit1.setChecked(true);
                                            sms_cmd16_L_bit2.setChecked(false);
                                            sms_cmd16_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd16_L_bit0.setChecked(false);
                                            sms_cmd16_L_bit1.setChecked(false);
                                            sms_cmd16_L_bit2.setChecked(true);
                                            sms_cmd16_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd16_L_bit0.setChecked(true);
                                            sms_cmd16_L_bit1.setChecked(false);
                                            sms_cmd16_L_bit2.setChecked(true);
                                            sms_cmd16_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd16_L_bit0.setChecked(false);
                                            sms_cmd16_L_bit1.setChecked(true);
                                            sms_cmd16_L_bit2.setChecked(true);
                                            sms_cmd16_L_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd16_L_bit0.setChecked(true);
                                            sms_cmd16_L_bit1.setChecked(true);
                                            sms_cmd16_L_bit2.setChecked(true);
                                            sms_cmd16_L_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIRST CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SECOND CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd16_L_bit4.setChecked(false);
                                            sms_cmd16_L_bit5.setChecked(false);
                                            sms_cmd16_L_bit6.setChecked(false);
                                            sms_cmd16_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd16_L_bit4.setChecked(true);
                                            sms_cmd16_L_bit5.setChecked(false);
                                            sms_cmd16_L_bit6.setChecked(false);
                                            sms_cmd16_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd16_L_bit4.setChecked(false);
                                            sms_cmd16_L_bit5.setChecked(true);
                                            sms_cmd16_L_bit6.setChecked(false);
                                            sms_cmd16_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd16_L_bit4.setChecked(true);
                                            sms_cmd16_L_bit5.setChecked(true);
                                            sms_cmd16_L_bit6.setChecked(false);
                                            sms_cmd16_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd16_L_bit4.setChecked(false);
                                            sms_cmd16_L_bit5.setChecked(false);
                                            sms_cmd16_L_bit6.setChecked(true);
                                            sms_cmd16_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd16_L_bit4.setChecked(true);
                                            sms_cmd16_L_bit5.setChecked(false);
                                            sms_cmd16_L_bit6.setChecked(true);
                                            sms_cmd16_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd16_L_bit4.setChecked(false);
                                            sms_cmd16_L_bit5.setChecked(true);
                                            sms_cmd16_L_bit6.setChecked(true);
                                            sms_cmd16_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd16_L_bit4.setChecked(true);
                                            sms_cmd16_L_bit5.setChecked(true);
                                            sms_cmd16_L_bit6.setChecked(true);
                                            sms_cmd16_L_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd16_L_bit4.setChecked(false);
                                            sms_cmd16_L_bit5.setChecked(false);
                                            sms_cmd16_L_bit6.setChecked(false);
                                            sms_cmd16_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd16_L_bit4.setChecked(true);
                                            sms_cmd16_L_bit5.setChecked(false);
                                            sms_cmd16_L_bit6.setChecked(false);
                                            sms_cmd16_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd16_L_bit4.setChecked(false);
                                            sms_cmd16_L_bit5.setChecked(true);
                                            sms_cmd16_L_bit6.setChecked(false);
                                            sms_cmd16_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd16_L_bit4.setChecked(true);
                                            sms_cmd16_L_bit5.setChecked(true);
                                            sms_cmd16_L_bit6.setChecked(false);
                                            sms_cmd16_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd16_L_bit4.setChecked(false);
                                            sms_cmd16_L_bit5.setChecked(false);
                                            sms_cmd16_L_bit6.setChecked(true);
                                            sms_cmd16_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd16_L_bit4.setChecked(true);
                                            sms_cmd16_L_bit5.setChecked(false);
                                            sms_cmd16_L_bit6.setChecked(true);
                                            sms_cmd16_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd16_L_bit4.setChecked(false);
                                            sms_cmd16_L_bit5.setChecked(true);
                                            sms_cmd16_L_bit6.setChecked(true);
                                            sms_cmd16_L_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd16_L_bit4.setChecked(true);
                                            sms_cmd16_L_bit5.setChecked(true);
                                            sms_cmd16_L_bit6.setChecked(true);
                                            sms_cmd16_L_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SECOND CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd16_H_bit0.setChecked(false);
                                            sms_cmd16_H_bit1.setChecked(false);
                                            sms_cmd16_H_bit2.setChecked(false);
                                            sms_cmd16_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd16_H_bit0.setChecked(true);
                                            sms_cmd16_H_bit1.setChecked(false);
                                            sms_cmd16_H_bit2.setChecked(false);
                                            sms_cmd16_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd16_H_bit0.setChecked(false);
                                            sms_cmd16_H_bit1.setChecked(true);
                                            sms_cmd16_H_bit2.setChecked(false);
                                            sms_cmd16_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd16_H_bit0.setChecked(true);
                                            sms_cmd16_H_bit1.setChecked(true);
                                            sms_cmd16_H_bit2.setChecked(false);
                                            sms_cmd16_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd16_H_bit0.setChecked(false);
                                            sms_cmd16_H_bit1.setChecked(false);
                                            sms_cmd16_H_bit2.setChecked(true);
                                            sms_cmd16_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd16_H_bit0.setChecked(true);
                                            sms_cmd16_H_bit1.setChecked(false);
                                            sms_cmd16_H_bit2.setChecked(true);
                                            sms_cmd16_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd16_H_bit0.setChecked(false);
                                            sms_cmd16_H_bit1.setChecked(true);
                                            sms_cmd16_H_bit2.setChecked(true);
                                            sms_cmd16_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd16_H_bit0.setChecked(true);
                                            sms_cmd16_H_bit1.setChecked(true);
                                            sms_cmd16_H_bit2.setChecked(true);
                                            sms_cmd16_H_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd16_H_bit0.setChecked(false);
                                            sms_cmd16_H_bit1.setChecked(false);
                                            sms_cmd16_H_bit2.setChecked(false);
                                            sms_cmd16_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd16_H_bit0.setChecked(true);
                                            sms_cmd16_H_bit1.setChecked(false);
                                            sms_cmd16_H_bit2.setChecked(false);
                                            sms_cmd16_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd16_H_bit0.setChecked(false);
                                            sms_cmd16_H_bit1.setChecked(true);
                                            sms_cmd16_H_bit2.setChecked(false);
                                            sms_cmd16_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd16_H_bit0.setChecked(true);
                                            sms_cmd16_H_bit1.setChecked(true);
                                            sms_cmd16_H_bit2.setChecked(false);
                                            sms_cmd16_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd16_H_bit0.setChecked(false);
                                            sms_cmd16_H_bit1.setChecked(false);
                                            sms_cmd16_H_bit2.setChecked(true);
                                            sms_cmd16_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd16_H_bit0.setChecked(true);
                                            sms_cmd16_H_bit1.setChecked(false);
                                            sms_cmd16_H_bit2.setChecked(true);
                                            sms_cmd16_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd16_H_bit0.setChecked(false);
                                            sms_cmd16_H_bit1.setChecked(true);
                                            sms_cmd16_H_bit2.setChecked(true);
                                            sms_cmd16_H_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-3))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd16_H_bit0.setChecked(true);
                                            sms_cmd16_H_bit1.setChecked(true);
                                            sms_cmd16_H_bit2.setChecked(true);
                                            sms_cmd16_H_bit3.setChecked(true);
                                        }
                                        /***************************** END OF THIRD CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd16_H_bit4.setChecked(false);
                                            sms_cmd16_H_bit5.setChecked(false);
                                            sms_cmd16_H_bit6.setChecked(false);
                                            sms_cmd16_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd16_H_bit4.setChecked(true);
                                            sms_cmd16_H_bit5.setChecked(false);
                                            sms_cmd16_H_bit6.setChecked(false);
                                            sms_cmd16_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd16_H_bit4.setChecked(false);
                                            sms_cmd16_H_bit5.setChecked(true);
                                            sms_cmd16_H_bit6.setChecked(false);
                                            sms_cmd16_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd16_H_bit4.setChecked(true);
                                            sms_cmd16_H_bit5.setChecked(true);
                                            sms_cmd16_H_bit6.setChecked(false);
                                            sms_cmd16_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd16_H_bit4.setChecked(false);
                                            sms_cmd16_H_bit5.setChecked(false);
                                            sms_cmd16_H_bit6.setChecked(true);
                                            sms_cmd16_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd16_H_bit4.setChecked(true);
                                            sms_cmd16_H_bit5.setChecked(false);
                                            sms_cmd16_H_bit6.setChecked(true);
                                            sms_cmd16_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd16_H_bit4.setChecked(false);
                                            sms_cmd16_H_bit5.setChecked(true);
                                            sms_cmd16_H_bit6.setChecked(true);
                                            sms_cmd16_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd16_H_bit4.setChecked(true);
                                            sms_cmd16_H_bit5.setChecked(true);
                                            sms_cmd16_H_bit6.setChecked(true);
                                            sms_cmd16_H_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd16_H_bit4.setChecked(false);
                                            sms_cmd16_H_bit5.setChecked(false);
                                            sms_cmd16_H_bit6.setChecked(false);
                                            sms_cmd16_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd16_H_bit4.setChecked(true);
                                            sms_cmd16_H_bit5.setChecked(false);
                                            sms_cmd16_H_bit6.setChecked(false);
                                            sms_cmd16_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd16_H_bit4.setChecked(false);
                                            sms_cmd16_H_bit5.setChecked(true);
                                            sms_cmd16_H_bit6.setChecked(false);
                                            sms_cmd16_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd16_H_bit4.setChecked(true);
                                            sms_cmd16_H_bit5.setChecked(true);
                                            sms_cmd16_H_bit6.setChecked(false);
                                            sms_cmd16_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd16_H_bit4.setChecked(false);
                                            sms_cmd16_H_bit5.setChecked(false);
                                            sms_cmd16_H_bit6.setChecked(true);
                                            sms_cmd16_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd16_H_bit4.setChecked(true);
                                            sms_cmd16_H_bit5.setChecked(false);
                                            sms_cmd16_H_bit6.setChecked(true);
                                            sms_cmd16_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd16_H_bit4.setChecked(false);
                                            sms_cmd16_H_bit5.setChecked(true);
                                            sms_cmd16_H_bit6.setChecked(true);
                                            sms_cmd16_H_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-4))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd16_H_bit4.setChecked(true);
                                            sms_cmd16_H_bit5.setChecked(true);
                                            sms_cmd16_H_bit6.setChecked(true);
                                            sms_cmd16_H_bit7.setChecked(true);
                                        }
                                        /***************************** END OF FOURTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON FIFTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(false);
                                            sms_cmd16_L_M_bit1.setChecked(false);
                                            sms_cmd16_L_M_bit2.setChecked(false);
                                            sms_cmd16_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(true);
                                            sms_cmd16_L_M_bit1.setChecked(false);
                                            sms_cmd16_L_M_bit2.setChecked(false);
                                            sms_cmd16_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(false);
                                            sms_cmd16_L_M_bit1.setChecked(true);
                                            sms_cmd16_L_M_bit2.setChecked(false);
                                            sms_cmd16_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(true);
                                            sms_cmd16_L_M_bit1.setChecked(true);
                                            sms_cmd16_L_M_bit2.setChecked(false);
                                            sms_cmd16_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(false);
                                            sms_cmd16_L_M_bit1.setChecked(false);
                                            sms_cmd16_L_M_bit2.setChecked(true);
                                            sms_cmd16_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(true);
                                            sms_cmd16_L_M_bit1.setChecked(false);
                                            sms_cmd16_L_M_bit2.setChecked(true);
                                            sms_cmd16_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(false);
                                            sms_cmd16_L_M_bit1.setChecked(true);
                                            sms_cmd16_L_M_bit2.setChecked(true);
                                            sms_cmd16_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(true);
                                            sms_cmd16_L_M_bit1.setChecked(true);
                                            sms_cmd16_L_M_bit2.setChecked(true);
                                            sms_cmd16_L_M_bit3.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(false);
                                            sms_cmd16_L_M_bit1.setChecked(false);
                                            sms_cmd16_L_M_bit2.setChecked(false);
                                            sms_cmd16_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(true);
                                            sms_cmd16_L_M_bit1.setChecked(false);
                                            sms_cmd16_L_M_bit2.setChecked(false);
                                            sms_cmd16_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(false);
                                            sms_cmd16_L_M_bit1.setChecked(true);
                                            sms_cmd16_L_M_bit2.setChecked(false);
                                            sms_cmd16_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(true);
                                            sms_cmd16_L_M_bit1.setChecked(true);
                                            sms_cmd16_L_M_bit2.setChecked(false);
                                            sms_cmd16_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(false);
                                            sms_cmd16_L_M_bit1.setChecked(false);
                                            sms_cmd16_L_M_bit2.setChecked(true);
                                            sms_cmd16_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(true);
                                            sms_cmd16_L_M_bit1.setChecked(false);
                                            sms_cmd16_L_M_bit2.setChecked(true);
                                            sms_cmd16_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(false);
                                            sms_cmd16_L_M_bit1.setChecked(true);
                                            sms_cmd16_L_M_bit2.setChecked(true);
                                            sms_cmd16_L_M_bit3.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-5))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd16_L_M_bit0.setChecked(true);
                                            sms_cmd16_L_M_bit1.setChecked(true);
                                            sms_cmd16_L_M_bit2.setChecked(true);
                                            sms_cmd16_L_M_bit3.setChecked(true);
                                        }
                                        /***************************** END OF FIFTH CHARACTER FROM RIGHT **************************/
                                        /******************************** BASED ON SIXTH CHARACTER FROM RIGHT ***********************/
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexZero))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(false);
                                            sms_cmd16_L_M_bit5.setChecked(false);
                                            sms_cmd16_L_M_bit6.setChecked(false);
                                            sms_cmd16_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(true);
                                            sms_cmd16_L_M_bit5.setChecked(false);
                                            sms_cmd16_L_M_bit6.setChecked(false);
                                            sms_cmd16_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexTwo))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(false);
                                            sms_cmd16_L_M_bit5.setChecked(true);
                                            sms_cmd16_L_M_bit6.setChecked(false);
                                            sms_cmd16_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexThree))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(true);
                                            sms_cmd16_L_M_bit5.setChecked(true);
                                            sms_cmd16_L_M_bit6.setChecked(false);
                                            sms_cmd16_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(false);
                                            sms_cmd16_L_M_bit5.setChecked(false);
                                            sms_cmd16_L_M_bit6.setChecked(true);
                                            sms_cmd16_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexFive))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(true);
                                            sms_cmd16_L_M_bit5.setChecked(false);
                                            sms_cmd16_L_M_bit6.setChecked(true);
                                            sms_cmd16_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexSix))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(false);
                                            sms_cmd16_L_M_bit5.setChecked(true);
                                            sms_cmd16_L_M_bit6.setChecked(true);
                                            sms_cmd16_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexSeven))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(true);
                                            sms_cmd16_L_M_bit5.setChecked(true);
                                            sms_cmd16_L_M_bit6.setChecked(true);
                                            sms_cmd16_L_M_bit7.setChecked(false);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexEight))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(false);
                                            sms_cmd16_L_M_bit5.setChecked(false);
                                            sms_cmd16_L_M_bit6.setChecked(false);
                                            sms_cmd16_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexNine))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(true);
                                            sms_cmd16_L_M_bit5.setChecked(false);
                                            sms_cmd16_L_M_bit6.setChecked(false);
                                            sms_cmd16_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexA))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(false);
                                            sms_cmd16_L_M_bit5.setChecked(true);
                                            sms_cmd16_L_M_bit6.setChecked(false);
                                            sms_cmd16_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexB))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(true);
                                            sms_cmd16_L_M_bit5.setChecked(true);
                                            sms_cmd16_L_M_bit6.setChecked(false);
                                            sms_cmd16_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexC))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(false);
                                            sms_cmd16_L_M_bit5.setChecked(false);
                                            sms_cmd16_L_M_bit6.setChecked(true);
                                            sms_cmd16_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexD))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(true);
                                            sms_cmd16_L_M_bit5.setChecked(false);
                                            sms_cmd16_L_M_bit6.setChecked(true);
                                            sms_cmd16_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexE))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(false);
                                            sms_cmd16_L_M_bit5.setChecked(true);
                                            sms_cmd16_L_M_bit6.setChecked(true);
                                            sms_cmd16_L_M_bit7.setChecked(true);
                                        }

                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-6))).equalsIgnoreCase(hexF))
                                        {
                                            sms_cmd16_L_M_bit4.setChecked(true);
                                            sms_cmd16_L_M_bit5.setChecked(true);
                                            sms_cmd16_L_M_bit6.setChecked(true);
                                            sms_cmd16_L_M_bit7.setChecked(true);
                                        }
                                        /***************************** END OF SIXTH CHARACTER FROM RIGHT **************************/
                                    }

                                    //DISPLAY FOR LIFT STATUS
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("5"))
                                    {
                                        displayTimer(displayTime);
                                        threeTargetView.getParent().requestChildFocus(threeTargetView,threeTargetView);
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexFour))
                                        {
                                            sms_cmd15.setChecked(true);
                                        }
                                        else
                                        {
                                            sms_cmd15.setChecked(false);
                                        }
                                    }

                                    //DISPLAY FOR START MONITORING//
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("9"))
                                    {
                                        displayTimer(displayTime);
                                        threeTargetView.getParent().requestChildFocus(threeTargetView,threeTargetView);
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd19.setChecked(true);
                                        }
                                        else
                                        {
                                            sms_cmd19.setChecked(false);
                                        }
                                    }

                                    //LIFT EMERGENCY STOP STATUS READ
                                    if(String.valueOf(stringSplit.charAt(1)).equalsIgnoreCase("2") && String.valueOf(stringSplit.charAt(2)).equalsIgnoreCase("0"))
                                    {
                                        displayTimer(displayTime);
                                        threeTargetView.getParent().requestChildFocus(threeTargetView,threeTargetView);
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-1))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd20_bit0.setChecked(true);
                                        }
                                        if(String.valueOf(stringSplit.charAt((stringSplit.length()-2))).equalsIgnoreCase(hexOne))
                                        {
                                            sms_cmd20_bit1.setChecked(true);
                                        }
                                    }
                                    displayFromCommand16Onwards();
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
    }//end of getReceivedSMS()

    public void sendSMS(View view)
    {
        //toast("Clicked " + String.valueOf(view.getId()));

        switch(view.getId())
        {
            case R.id.CMD7:
                sms.sendTextMessage(choice, null, stringCMD7, null, null);
                CMD7.setBackgroundColor(Color.GREEN);
                CMD7_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD8:
                sms.sendTextMessage(choice, null, stringCMD8, null, null);
                CMD8.setBackgroundColor(Color.GREEN);
                CMD8_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD9:
                sms.sendTextMessage(choice, null, stringCMD9, null, null);
                CMD9.setBackgroundColor(Color.GREEN);
                CMD9_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD10:
                sms.sendTextMessage(choice, null, stringCMD10, null, null);
                CMD10.setBackgroundColor(Color.GREEN);
                CMD10_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD11:
                sms.sendTextMessage(choice, null, stringCMD11, null, null);
                CMD11.setBackgroundColor(Color.GREEN);
                CMD11_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD12:
                sms.sendTextMessage(choice, null, stringCMD12, null, null);
                CMD12.setBackgroundColor(Color.GREEN);
                CMD12_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD13:
                sms.sendTextMessage(choice, null, stringCMD13, null, null);
                CMD13.setBackgroundColor(Color.GREEN);
                CMD13_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD14:
                sms.sendTextMessage(choice, null, stringCMD14, null, null);
                CMD14.setBackgroundColor(Color.GREEN);
                CMD14_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD15:
                sms.sendTextMessage(choice, null, stringCMD15, null, null);
                CMD15.setBackgroundColor(Color.GREEN);
                CMD15_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD16:
                sms.sendTextMessage(choice, null, stringCMD16, null, null);
                CMD16.setBackgroundColor(Color.GREEN);
                CMD16_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD17:
                sms.sendTextMessage(choice, null, stringCMD17, null, null);
                CMD17.setBackgroundColor(Color.GREEN);
                CMD17_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD18:
                sms.sendTextMessage(choice, null, stringCMD18, null, null);
                CMD18.setBackgroundColor(Color.GREEN);
                CMD18_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD19:
                sms.sendTextMessage(choice, null, stringCMD19, null, null);
                CMD19.setBackgroundColor(Color.GREEN);
                CMD19_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD20:
                sms.sendTextMessage(choice, null, stringCMD20, null, null);
                CMD20.setBackgroundColor(Color.GREEN);
                CMD20_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD7_text:
                sms.sendTextMessage(choice, null, stringCMD7, null, null);
                CMD7.setBackgroundColor(Color.GREEN);
                CMD7_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD8_text:
                sms.sendTextMessage(choice, null, stringCMD8, null, null);
                CMD8.setBackgroundColor(Color.GREEN);
                CMD8_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD9_text:
                sms.sendTextMessage(choice, null, stringCMD9, null, null);
                CMD9.setBackgroundColor(Color.GREEN);
                CMD9_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD10_text:
                sms.sendTextMessage(choice, null, stringCMD10, null, null);
                CMD10.setBackgroundColor(Color.GREEN);
                CMD10_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD11_text:
                sms.sendTextMessage(choice, null, stringCMD11, null, null);
                CMD11.setBackgroundColor(Color.GREEN);
                CMD11_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD12_text:
                sms.sendTextMessage(choice, null, stringCMD12, null, null);
                CMD12.setBackgroundColor(Color.GREEN);
                CMD12_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD13_text:
                sms.sendTextMessage(choice, null, stringCMD13, null, null);
                CMD13.setBackgroundColor(Color.GREEN);
                CMD13_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD14_text:
                sms.sendTextMessage(choice, null, stringCMD14, null, null);
                CMD14.setBackgroundColor(Color.GREEN);
                CMD14_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD15_text:
                sms.sendTextMessage(choice, null, stringCMD15, null, null);
                CMD15.setBackgroundColor(Color.GREEN);
                CMD15_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD16_text:
                sms.sendTextMessage(choice, null, stringCMD16, null, null);
                CMD16.setBackgroundColor(Color.GREEN);
                CMD16_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD17_text:
                sms.sendTextMessage(choice, null, stringCMD17, null, null);
                CMD17.setBackgroundColor(Color.GREEN);
                CMD17_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD18_text:
                sms.sendTextMessage(choice, null, stringCMD18, null, null);
                CMD18.setBackgroundColor(Color.GREEN);
                CMD18_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;
            case R.id.CMD19_text:
                sms.sendTextMessage(choice, null, stringCMD19, null, null);
                CMD19.setBackgroundColor(Color.GREEN);
                CMD19_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
                //toast("SENT TO " +choice);
                break;

            case R.id.CMD20_text:
                sms.sendTextMessage(choice, null, stringCMD20, null, null);
                CMD20.setBackgroundColor(Color.GREEN);
                CMD20_text.setBackgroundColor(Color.GREEN);
                setProgressDialog();
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
        CMD15.setEnabled(false);
        CMD16.setEnabled(false);
        CMD17.setEnabled(false);
        CMD18.setEnabled(false);
        CMD19.setEnabled(false);
        CMD20.setEnabled(false);

        CMD7_text.setEnabled(false);
        CMD8_text.setEnabled(false);
        CMD9_text.setEnabled(false);
        CMD10_text.setEnabled(false);
        CMD11_text.setEnabled(false);
        CMD12_text.setEnabled(false);
        CMD13_text.setEnabled(false);
        CMD14_text.setEnabled(false);
        CMD15_text.setEnabled(false);
        CMD16_text.setEnabled(false);
        CMD17_text.setEnabled(false);
        CMD18_text.setEnabled(false);
        CMD19_text.setEnabled(false);
        CMD20_text.setEnabled(false);

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
        CMD15.setEnabled(true);
        CMD16.setEnabled(true);
        CMD17.setEnabled(true);
        CMD18.setEnabled(true);
        CMD19.setEnabled(true);
        CMD20.setEnabled(true);

        CMD7_text.setEnabled(true);
        CMD8_text.setEnabled(true);
        CMD9_text.setEnabled(true);
        CMD10_text.setEnabled(true);
        CMD11_text.setEnabled(true);
        CMD12_text.setEnabled(true);
        CMD13_text.setEnabled(true);
        CMD14_text.setEnabled(true);
        CMD15_text.setEnabled(true);
        CMD16_text.setEnabled(true);
        CMD17_text.setEnabled(true);
        CMD18_text.setEnabled(true);
        CMD19_text.setEnabled(true);
        CMD20_text.setEnabled(true);

        CMD7.setBackgroundColor(Color.WHITE);
        CMD8.setBackgroundColor(Color.WHITE);
        CMD9.setBackgroundColor(Color.WHITE);
        CMD10.setBackgroundColor(Color.WHITE);
        CMD11.setBackgroundColor(Color.WHITE);
        CMD12.setBackgroundColor(Color.WHITE);
        CMD13.setBackgroundColor(Color.WHITE);
        CMD14.setBackgroundColor(Color.WHITE);
        CMD15.setBackgroundColor(Color.WHITE);
        CMD16.setBackgroundColor(Color.WHITE);
        CMD17.setBackgroundColor(Color.WHITE);
        CMD18.setBackgroundColor(Color.WHITE);
        CMD19.setBackgroundColor(Color.WHITE);
        CMD20.setBackgroundColor(Color.WHITE);

        CMD7_text.setBackgroundColor(Color.WHITE);
        CMD8_text.setBackgroundColor(Color.WHITE);
        CMD9_text.setBackgroundColor(Color.WHITE);
        CMD10_text.setBackgroundColor(Color.WHITE);
        CMD11_text.setBackgroundColor(Color.WHITE);
        CMD12_text.setBackgroundColor(Color.WHITE);
        CMD13_text.setBackgroundColor(Color.WHITE);
        CMD14_text.setBackgroundColor(Color.WHITE);
        CMD15_text.setBackgroundColor(Color.WHITE);
        CMD16_text.setBackgroundColor(Color.WHITE);
        CMD17_text.setBackgroundColor(Color.WHITE);
        CMD18_text.setBackgroundColor(Color.WHITE);
        CMD19_text.setBackgroundColor(Color.WHITE);
        CMD20_text.setBackgroundColor(Color.WHITE);

    }//end of enableUI()

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
                enableUI();
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
                toast("Please wait: " + millisUntilFinished / 1000);
                if(millisUntilFinished/1000 == 1)
                {
                    cTimer.cancel();
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
                countDown.setText(String.valueOf(millisUntilFinished/1000));
                if(millisUntilFinished/1000 < 1)
                {
                    cTimer.cancel();
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
        sms_cmd15.setChecked(false);
        sms_cmd19.setChecked(false);
        sms_cmd20_bit0.setChecked(false);
        sms_cmd20_bit1.setChecked(false);

        sms_cmd7_bit0.setChecked(false);
        sms_cmd7_bit1.setChecked(false);
        sms_cmd7_bit2.setChecked(false);
        sms_cmd7_bit3.setChecked(false);
        sms_cmd7_bit4.setChecked(false);
        sms_cmd7_bit5.setChecked(false);
        sms_cmd7_bit6.setChecked(false);
        sms_cmd7_bit7.setChecked(false);

        sms_cmd8_bit0.setChecked(false);
        sms_cmd8_bit1.setChecked(false);
        sms_cmd8_bit2.setChecked(false);
        sms_cmd8_bit3.setChecked(false);
        sms_cmd8_bit4.setChecked(false);
        sms_cmd8_bit5.setChecked(false);
        sms_cmd8_bit6.setChecked(false);
        sms_cmd8_bit7.setChecked(false);


        sms_cmd8_H_bit0.setChecked(false);
        sms_cmd8_H_bit1.setChecked(false);
        sms_cmd8_H_bit2.setChecked(false);
        sms_cmd8_H_bit3.setChecked(false);
        sms_cmd8_H_bit4.setChecked(false);
        sms_cmd8_H_bit5.setChecked(false);
        sms_cmd8_H_bit6.setChecked(false);
        sms_cmd8_H_bit7.setChecked(false);

        sms_cmd9_L_bit0.setChecked(false);
        sms_cmd9_L_bit1.setChecked(false);
        sms_cmd9_L_bit2.setChecked(false);
        sms_cmd9_L_bit3.setChecked(false);
        sms_cmd9_L_bit4.setChecked(false);
        sms_cmd9_L_bit5.setChecked(false);
        sms_cmd9_L_bit6.setChecked(false);
        sms_cmd9_L_bit7.setChecked(false);

        sms_cmd9_H_bit0.setChecked(false);
        sms_cmd9_H_bit1.setChecked(false);
        sms_cmd9_H_bit2.setChecked(false);
        sms_cmd9_H_bit3.setChecked(false);
        sms_cmd9_H_bit4.setChecked(false);
        sms_cmd9_H_bit5.setChecked(false);
        sms_cmd9_H_bit6.setChecked(false);
        sms_cmd9_H_bit7.setChecked(false);

        sms_cmd10_H_bit0.setChecked(false);
        sms_cmd10_H_bit1.setChecked(false);
        sms_cmd10_H_bit2.setChecked(false);
        sms_cmd10_H_bit3.setChecked(false);
        sms_cmd10_H_bit4.setChecked(false);
        sms_cmd10_H_bit5.setChecked(false);
        sms_cmd10_H_bit6.setChecked(false);
        sms_cmd10_H_bit7.setChecked(false);

        sms_cmd10_L_bit0.setChecked(false);
        sms_cmd10_L_bit1.setChecked(false);
        sms_cmd10_L_bit2.setChecked(false);
        sms_cmd10_L_bit3.setChecked(false);
        sms_cmd10_L_bit4.setChecked(false);
        sms_cmd10_L_bit5.setChecked(false);
        sms_cmd10_L_bit6.setChecked(false);
        sms_cmd10_L_bit7.setChecked(false);

        sms_cmd11_H_bit0.setChecked(false);
        sms_cmd11_H_bit1.setChecked(false);
        sms_cmd11_H_bit2.setChecked(false);
        sms_cmd11_H_bit3.setChecked(false);
        sms_cmd11_H_bit4.setChecked(false);
        sms_cmd11_H_bit5.setChecked(false);
        sms_cmd11_H_bit6.setChecked(false);
        sms_cmd11_H_bit7.setChecked(false);

        sms_cmd11_L_bit0.setChecked(false);
        sms_cmd11_L_bit1.setChecked(false);
        sms_cmd11_L_bit2.setChecked(false);
        sms_cmd11_L_bit3.setChecked(false);
        sms_cmd11_L_bit4.setChecked(false);
        sms_cmd11_L_bit5.setChecked(false);
        sms_cmd11_L_bit6.setChecked(false);
        sms_cmd11_L_bit7.setChecked(false);

        sms_cmd12_H_bit0.setChecked(false);
        sms_cmd12_H_bit1.setChecked(false);
        sms_cmd12_H_bit2.setChecked(false);
        sms_cmd12_H_bit3.setChecked(false);
        sms_cmd12_H_bit4.setChecked(false);
        sms_cmd12_H_bit5.setChecked(false);
        sms_cmd12_H_bit6.setChecked(false);
        sms_cmd12_H_bit7.setChecked(false);

        sms_cmd12_L_bit0.setChecked(false);
        sms_cmd12_L_bit1.setChecked(false);
        sms_cmd12_L_bit2.setChecked(false);
        sms_cmd12_L_bit3.setChecked(false);
        sms_cmd12_L_bit4.setChecked(false);
        sms_cmd12_L_bit5.setChecked(false);
        sms_cmd12_L_bit6.setChecked(false);
        sms_cmd12_L_bit7.setChecked(false);

        sms_cmd12_H_M_bit0.setChecked(false);
        sms_cmd12_H_M_bit1.setChecked(false);
        sms_cmd12_H_M_bit2.setChecked(false);
        sms_cmd12_H_M_bit3.setChecked(false);
        sms_cmd12_H_M_bit4.setChecked(false);
        sms_cmd12_H_M_bit5.setChecked(false);
        sms_cmd12_H_M_bit6.setChecked(false);
        sms_cmd12_H_M_bit7.setChecked(false);

        sms_cmd12_L_M_bit0.setChecked(false);
        sms_cmd12_L_M_bit1.setChecked(false);
        sms_cmd12_L_M_bit2.setChecked(false);
        sms_cmd12_L_M_bit3.setChecked(false);
        sms_cmd12_L_M_bit4.setChecked(false);
        sms_cmd12_L_M_bit5.setChecked(false);
        sms_cmd12_L_M_bit6.setChecked(false);
        sms_cmd12_L_M_bit7.setChecked(false);

        sms_cmd13_H_bit0.setChecked(false);
        sms_cmd13_H_bit1.setChecked(false);
        sms_cmd13_H_bit2.setChecked(false);
        sms_cmd13_H_bit3.setChecked(false);
        sms_cmd13_H_bit4.setChecked(false);
        sms_cmd13_H_bit5.setChecked(false);
        sms_cmd13_H_bit6.setChecked(false);
        sms_cmd13_H_bit7.setChecked(false);

        sms_cmd13_L_bit0.setChecked(false);
        sms_cmd13_L_bit1.setChecked(false);
        sms_cmd13_L_bit2.setChecked(false);
        sms_cmd13_L_bit3.setChecked(false);
        sms_cmd13_L_bit4.setChecked(false);
        sms_cmd13_L_bit5.setChecked(false);
        sms_cmd13_L_bit6.setChecked(false);
        sms_cmd13_L_bit7.setChecked(false);

        sms_cmd13_H_M_bit0.setChecked(false);
        sms_cmd13_H_M_bit1.setChecked(false);
        sms_cmd13_H_M_bit2.setChecked(false);
        sms_cmd13_H_M_bit3.setChecked(false);
        sms_cmd13_H_M_bit4.setChecked(false);
        sms_cmd13_H_M_bit5.setChecked(false);
        sms_cmd13_H_M_bit6.setChecked(false);
        sms_cmd13_H_M_bit7.setChecked(false);

        sms_cmd13_L_M_bit0.setChecked(false);
        sms_cmd13_L_M_bit1.setChecked(false);
        sms_cmd13_L_M_bit2.setChecked(false);
        sms_cmd13_L_M_bit3.setChecked(false);
        sms_cmd13_L_M_bit4.setChecked(false);
        sms_cmd13_L_M_bit5.setChecked(false);
        sms_cmd13_L_M_bit6.setChecked(false);
        sms_cmd13_L_M_bit7.setChecked(false);

        sms_cmd14_H_bit0.setChecked(false);
        sms_cmd14_H_bit1.setChecked(false);
        sms_cmd14_H_bit2.setChecked(false);
        sms_cmd14_H_bit3.setChecked(false);
        sms_cmd14_H_bit4.setChecked(false);
        sms_cmd14_H_bit5.setChecked(false);
        sms_cmd14_H_bit6.setChecked(false);
        sms_cmd14_H_bit7.setChecked(false);

        sms_cmd14_L_bit0.setChecked(false);
        sms_cmd14_L_bit1.setChecked(false);
        sms_cmd14_L_bit2.setChecked(false);
        sms_cmd14_L_bit3.setChecked(false);
        sms_cmd14_L_bit4.setChecked(false);
        sms_cmd14_L_bit5.setChecked(false);
        sms_cmd14_L_bit6.setChecked(false);
        sms_cmd14_L_bit7.setChecked(false);

        sms_cmd14_H_M_bit0.setChecked(false);
        sms_cmd14_H_M_bit1.setChecked(false);
        sms_cmd14_H_M_bit2.setChecked(false);
        sms_cmd14_H_M_bit3.setChecked(false);
        sms_cmd14_H_M_bit4.setChecked(false);
        sms_cmd14_H_M_bit5.setChecked(false);
        sms_cmd14_H_M_bit6.setChecked(false);
        sms_cmd14_H_M_bit7.setChecked(false);

        sms_cmd14_L_M_bit0.setChecked(false);
        sms_cmd14_L_M_bit1.setChecked(false);
        sms_cmd14_L_M_bit2.setChecked(false);
        sms_cmd14_L_M_bit3.setChecked(false);
        sms_cmd14_L_M_bit4.setChecked(false);
        sms_cmd14_L_M_bit5.setChecked(false);
        sms_cmd14_L_M_bit6.setChecked(false);
        sms_cmd14_L_M_bit7.setChecked(false);

        sms_cmd16_H_bit0.setChecked(false);
        sms_cmd16_H_bit1.setChecked(false);
        sms_cmd16_H_bit2.setChecked(false);
        sms_cmd16_H_bit3.setChecked(false);
        sms_cmd16_H_bit4.setChecked(false);
        sms_cmd16_H_bit5.setChecked(false);
        sms_cmd16_H_bit6.setChecked(false);
        sms_cmd16_H_bit7.setChecked(false);

        sms_cmd16_L_bit0.setChecked(false);
        sms_cmd16_L_bit1.setChecked(false);
        sms_cmd16_L_bit2.setChecked(false);
        sms_cmd16_L_bit3.setChecked(false);
        sms_cmd16_L_bit4.setChecked(false);
        sms_cmd16_L_bit5.setChecked(false);
        sms_cmd16_L_bit6.setChecked(false);
        sms_cmd16_L_bit7.setChecked(false);

        sms_cmd16_L_M_bit0.setChecked(false);
        sms_cmd16_L_M_bit1.setChecked(false);
        sms_cmd16_L_M_bit2.setChecked(false);
        sms_cmd16_L_M_bit3.setChecked(false);
        sms_cmd16_L_M_bit4.setChecked(false);
        sms_cmd16_L_M_bit5.setChecked(false);
        sms_cmd16_L_M_bit6.setChecked(false);
        sms_cmd16_L_M_bit7.setChecked(false);
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
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
        theSqLiteDatabase.execSQL("DROP TABLE CUSTOMER_SMS");
        toast("THE TABLE CUSTOMER_SMS IS DELETED");
    }
    @Override
    public void onBackPressed()
    {
        System.exit(0);
    }
}
