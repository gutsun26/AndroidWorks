package com.web.g_smsapp.admin;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.web.g_smsapp.R;
import com.web.g_smsapp.admin_db.AdminControllerDB;
import com.web.g_smsapp.customer_db.CustomerControllerDB;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class AdminInformationImmediateActivity extends AppCompatActivity
{
    /******************************VIEW*****************************************/
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    String inactive     = "-";
    String active       = "A";
    Spinner dateChoice,clientChoice,binaryInfo;
    String dateChosen, clientChosen, contentToBeSplit, content1, content0;
    String theNumber;
    List<String> theClient, theDate,theContent;
    ArrayAdapter<String> clientDataAdapter, dateAdapter, contentAdapter;
    Button showButton;
    TextView content;
    TableLayout table0,table1,table2,table3,table4,table5,table6,table7,table8,table9,table10;
    TableLayout table;
    TextView    title, stopTitles;
    TextView    tableRow0Column0,
            tableRow0Column1,
            tableRow0Column2,
            tableRow0Column3,
            tableRow0Column4,
            tableRow0Column5,
            tableRow0Column6,
            tableRow0Column7;
    TextView    tableRow1Column0,
            tableRow1Column1,
            tableRow1Column2,
            tableRow1Column3,
            tableRow1Column4,
            tableRow1Column5,
            tableRow1Column6,
            tableRow1Column7;
    TextView    tableRow2Column0,
            tableRow2Column1,
            tableRow2Column2,
            tableRow2Column3,
            tableRow2Column4,
            tableRow2Column5,
            tableRow2Column6,
            tableRow2Column7;
    TextView    tableRow3Column0,
            tableRow3Column1,
            tableRow3Column2,
            tableRow3Column3,
            tableRow3Column4,
            tableRow3Column5,
            tableRow3Column6,
            tableRow3Column7;
    TextView    tableRow4Column0,
            tableRow4Column1,
            tableRow4Column2,
            tableRow4Column3,
            tableRow4Column4,
            tableRow4Column5,
            tableRow4Column6,
            tableRow4Column7;
    TextView    tableRow5Column0,
            tableRow5Column1,
            tableRow5Column2,
            tableRow5Column3,
            tableRow5Column4,
            tableRow5Column5,
            tableRow5Column6,
            tableRow5Column7;
    TextView    tableRow6Column0,
            tableRow6Column1,
            tableRow6Column2,
            tableRow6Column3,
            tableRow6Column4,
            tableRow6Column5,
            tableRow6Column6,
            tableRow6Column7;
    TextView    tableRow7Column0,
            tableRow7Column1,
            tableRow7Column2,
            tableRow7Column3,
            tableRow7Column4,
            tableRow7Column5,
            tableRow7Column6,
            tableRow7Column7;
    TextView    textCharacter_I,    textCharacter_A, textCharacter_C, timeStamp, textDash, liftStopStatus;
    TextView    err7,err6,err5,err4,err3,err2,err1,err0,errorTitle,
                err7_d,err6_d,err5_d,err4_d,err3_d,err2_d,err1_d,err0_d,
                err15,err14,err13,err12,err11,err10,err9,err8,
                err15_d,err14_d,err13_d,err12_d,err11_d,err10_d,err9_d,err8_d,
                err16,err17,err18,err19,err16_d,err17_d,err18_d,err19_d;
    String mainCardInput[]              = {"---","---","---","---","---","---","---","---","SLWR","STPR","ARD","OPC","BRK","MT","MUP","MDN"};
    String mainCardTerminalInput[]      = {"---","---","---","---","---","LGS","CGS","PS", "---", "---", "UTL", "DTL", "FUP", "FDN", "FUP1", "FDN1"};
    String mainCardOutput[]             = {"---","---", "DN","UP","S2","S1","DC","---","RKM","RCAM","ARD", "DIR_RD","PRE","EX1","DBK","BRK"};
    String cbcInputs[]                  = {"---","---","---","ACR","IFST","DOLT","DCLT","DCPB","DOPB","LC","ULD","FLD","OVLD","NS","ATT","EMR STOP"};
    String cbcOutputs[]                 = {"---","---","EX2","WELCOME","THY","DOR","DCR","DOBR","---","---","LIGHT","FAN","DOF","DCF","DOBF","GONG"};
    String floorKeysZeroToFifteen[]     = {"15","14","13","12","11","10","09","08","07","06","05","04","03","02","01","GR"};
    String floorKeysSixteenAbove[]      = {"31","30","29","28","27","26","25","24","23","22","21","20","19","18","17","16"};
    String liftErrorStatus_I[]          = {
                                                "MO1 OP OFF",
                                                "STPR STUCK",
                                                "SLWR STUCK",
                                                "DOLT SHORT",
                                                "DCLT SHORT",
                                                "DCLT",
                                                "DOLT",
                                                "SAFETY SHORT",
                                                "BCFB STOP",
                                                "OCFB STOP",
                                                "REED TIMEOUT",
                                                "RCAM TIMEOUT",
                                                "BTS",
                                                "BCFB",
                                                "OCFB",
                                                "INV ENABEL"
                                        };
    String liftErrorStatus_II[]         =   {
                                                "SMS EMR STOP",
                                                "RELAY OP IN",
                                                "MO1 OP ON",
                                                "RELAY OP OFF"
                                            };
    String carKeys[]                    = {};
    String liftStatus[]                 = {};
    String liftErrorStauts[]            = {};
    String liftEmerStop[]               = {};
    String liftEmerStopStatusRead[]     = {};
    String liftMaxFloor[]               = {};
    String startMonitoring[]            = {};
    String listOfTitles[]               = {                                  //COMMAND         ARRAY INDEX
                                            "MAIN CARD INPUT",              //07                0
                                            "MAIN CARD TERMINAL INPUT",     //08                1
                                            "MAIN CARD OUTPUT",             //09                2
                                            "CBC INPUTS",                   //10                3
                                            "CBC OUTPUTS",                  //11                4
                                            "FLOOR UP KEYS",                //12                5
                                            "FLOOR DOWN KEYS",              //13                6
                                            "CAR KEYS",                     //14                7
                                            "LIFT STATUS",                  //20                8
                                            "LIFT ERROR STATUS",            //16                9
                                            "LIFT EMR STOP STATUS READ",    //                  10
                                            "LIFT MAX FLOOR",
                                            "START MONITORING",
                                            "LIFT EMR STOP"
                                        };
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

    String	binZero		=	"----"	;
    String	binOne		=	"---A"	;
    String	binTwo		=	"--A-"	;
    String	binThree	=	"--AA"	;
    String	binFour		=	"-A--"	;
    String	binFive		=	"-A-A"	;
    String	binSix		=	"-AA-"	;
    String	binSeven	=	"-AAA"	;
    String	binEight	=	"A---"	;
    String	binNine		=	"A--A"	;
    String	binA		=	"A-A-"	;
    String	binB		=	"A-AA"	;
    String	binC		=	"AA--"	;
    String	binD		=	"AA-A"	;
    String	binE		=	"AAA-"	;
    String	binF		=	"AAAA"	;
    String theMessage=null;

    /*FOR FLOOR CALLS*/
    String	floorBinZero		=	"----"	;
    String	floorBinOne			=	"---C"	;
    String	floorBinTwo			=	"--C-"	;
    String	floorBinThree		=	"--CC"	;
    String	floorBinFour		=	"-C--"	;
    String	floorBinFive		=	"-C-C"	;
    String	floorBinSix			=	"-CC-"	;
    String	floorBinSeven		=	"-CCC"	;
    String	floorBinEight		=	"C---"	;
    String	floorBinNine		=	"C--C"	;
    String	floorBinA			=	"C-C-"	;
    String	floorBinB			=	"C-CC"	;
    String	floorBinC			=	"CC--"	;
    String	floorBinD			=	"CC-C"	;
    String	floorBinE			=	"CCC-"	;
    String	floorBinF			=	"CCCC"	;

    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_admin_information_immediate);
        title       =       (TextView)findViewById(R.id.title);
        stopTitles  =       (TextView)findViewById(R.id.stopTitles);
        table0=(TableLayout)findViewById(R.id.table0);
        table1=(TableLayout)findViewById(R.id.table1);
        table2=(TableLayout)findViewById(R.id.table2);
        table3=(TableLayout)findViewById(R.id.table3);
        table4=(TableLayout)findViewById(R.id.table4);
        table5=(TableLayout)findViewById(R.id.table5);
        table6=(TableLayout)findViewById(R.id.table6);
        table7=(TableLayout)findViewById(R.id.table7);
        table8=(TableLayout)findViewById(R.id.table8);
        table9=(TableLayout)findViewById(R.id.table9);
        table10=(TableLayout)findViewById(R.id.table10);

        err15=(TextView)findViewById(R.id.err15);
        err14=(TextView)findViewById(R.id.err14);
        err13=(TextView)findViewById(R.id.err13);
        err12=(TextView)findViewById(R.id.err12);
        err11=(TextView)findViewById(R.id.err11);
        err10=(TextView)findViewById(R.id.err10);
        err9=(TextView)findViewById(R.id.err9);
        err8=(TextView)findViewById(R.id.err8);
        err7=(TextView)findViewById(R.id.err7);
        err6=(TextView)findViewById(R.id.err6);
        err5=(TextView)findViewById(R.id.err5);
        err4=(TextView)findViewById(R.id.err4);
        err3=(TextView)findViewById(R.id.err3);
        err2=(TextView)findViewById(R.id.err2);
        err1=(TextView)findViewById(R.id.err1);
        err0=(TextView)findViewById(R.id.err0);

        err15_d=(TextView)findViewById(R.id.err15_d);
        err14_d=(TextView)findViewById(R.id.err14_d);
        err13_d=(TextView)findViewById(R.id.err13_d);
        err12_d=(TextView)findViewById(R.id.err12_d);
        err11_d=(TextView)findViewById(R.id.err11_d);
        err10_d=(TextView)findViewById(R.id.err10_d);
        err9_d=(TextView)findViewById(R.id.err9_d);
        err8_d=(TextView)findViewById(R.id.err8_d);
        err7_d=(TextView)findViewById(R.id.err7_d);
        err6_d=(TextView)findViewById(R.id.err6_d);
        err5_d=(TextView)findViewById(R.id.err5_d);
        err4_d=(TextView)findViewById(R.id.err4_d);
        err3_d=(TextView)findViewById(R.id.err3_d);
        err2_d=(TextView)findViewById(R.id.err2_d);
        err1_d=(TextView)findViewById(R.id.err1_d);
        err0_d=(TextView)findViewById(R.id.err0_d);

        err16=(TextView)findViewById(R.id.err16);
        err17=(TextView)findViewById(R.id.err17);
        err18=(TextView)findViewById(R.id.err18);
        err19=(TextView)findViewById(R.id.err19);

        err16_d=(TextView)findViewById(R.id.err16_d);
        err17_d=(TextView)findViewById(R.id.err17_d);
        err18_d=(TextView)findViewById(R.id.err18_d);
        err19_d=(TextView)findViewById(R.id.err19_d);


        errorTitle=(TextView)findViewById(R.id.errorTitle);
        content     =       (TextView)findViewById(R.id.content);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/ HH:mm:ss a");
        date = dateFormat.format(calendar.getTime());
        textCharacter_I         =       (TextView)      findViewById(R.id.textCharacter_I);
        textCharacter_A         =       (TextView)      findViewById(R.id.textCharacter_A);
        textCharacter_C         =       (TextView)      findViewById(R.id.textCharacter_C);
        timeStamp               =       (TextView)      findViewById(R.id.timeStamp);
        textDash                =       (TextView)      findViewById(R.id.textDash);
        liftStopStatus          =       (TextView)      findViewById(R.id.liftStopStatus);


        tableRow0Column0	=(TextView)findViewById(R.id.	tableRow0Column0	);
        tableRow0Column1	=(TextView)findViewById(R.id.	tableRow0Column1	);
        tableRow0Column2	=(TextView)findViewById(R.id.	tableRow0Column2	);
        tableRow0Column3	=(TextView)findViewById(R.id.	tableRow0Column3	);
        tableRow0Column4	=(TextView)findViewById(R.id.	tableRow0Column4	);
        tableRow0Column5	=(TextView)findViewById(R.id.	tableRow0Column5	);
        tableRow0Column6	=(TextView)findViewById(R.id.	tableRow0Column6	);
        tableRow0Column7	=(TextView)findViewById(R.id.	tableRow0Column7	);


        tableRow1Column0	=(TextView)findViewById(R.id.	tableRow1Column0	);
        tableRow1Column1	=(TextView)findViewById(R.id.	tableRow1Column1	);
        tableRow1Column2	=(TextView)findViewById(R.id.	tableRow1Column2	);
        tableRow1Column3	=(TextView)findViewById(R.id.	tableRow1Column3	);
        tableRow1Column4	=(TextView)findViewById(R.id.	tableRow1Column4	);
        tableRow1Column5	=(TextView)findViewById(R.id.	tableRow1Column5	);
        tableRow1Column6	=(TextView)findViewById(R.id.	tableRow1Column6	);
        tableRow1Column7	=(TextView)findViewById(R.id.	tableRow1Column7	);

        tableRow2Column0	=(TextView)findViewById(R.id.	tableRow2Column0	);
        tableRow2Column1	=(TextView)findViewById(R.id.	tableRow2Column1	);
        tableRow2Column2	=(TextView)findViewById(R.id.	tableRow2Column2	);
        tableRow2Column3	=(TextView)findViewById(R.id.	tableRow2Column3	);
        tableRow2Column4	=(TextView)findViewById(R.id.	tableRow2Column4	);
        tableRow2Column5	=(TextView)findViewById(R.id.	tableRow2Column5	);
        tableRow2Column6	=(TextView)findViewById(R.id.	tableRow2Column6	);
        tableRow2Column7	=(TextView)findViewById(R.id.	tableRow2Column7	);

        tableRow3Column0	=(TextView)findViewById(R.id.	tableRow3Column0	);
        tableRow3Column1	=(TextView)findViewById(R.id.	tableRow3Column1	);
        tableRow3Column2	=(TextView)findViewById(R.id.	tableRow3Column2	);
        tableRow3Column3	=(TextView)findViewById(R.id.	tableRow3Column3	);
        tableRow3Column4	=(TextView)findViewById(R.id.	tableRow3Column4	);
        tableRow3Column5	=(TextView)findViewById(R.id.	tableRow3Column5	);
        tableRow3Column6	=(TextView)findViewById(R.id.	tableRow3Column6	);
        tableRow3Column7	=(TextView)findViewById(R.id.	tableRow3Column7	);

        tableRow4Column0	=(TextView)findViewById(R.id.	tableRow4Column0	);
        tableRow4Column1	=(TextView)findViewById(R.id.	tableRow4Column1	);
        tableRow4Column2	=(TextView)findViewById(R.id.	tableRow4Column2	);
        tableRow4Column3	=(TextView)findViewById(R.id.	tableRow4Column3	);
        tableRow4Column4	=(TextView)findViewById(R.id.	tableRow4Column4	);
        tableRow4Column5	=(TextView)findViewById(R.id.	tableRow4Column5	);
        tableRow4Column6	=(TextView)findViewById(R.id.	tableRow4Column6	);
        tableRow4Column7	=(TextView)findViewById(R.id.	tableRow4Column7	);


        tableRow5Column0	=(TextView)findViewById(R.id.	tableRow5Column0	);
        tableRow5Column1	=(TextView)findViewById(R.id.	tableRow5Column1	);
        tableRow5Column2	=(TextView)findViewById(R.id.	tableRow5Column2	);
        tableRow5Column3	=(TextView)findViewById(R.id.	tableRow5Column3	);
        tableRow5Column4	=(TextView)findViewById(R.id.	tableRow5Column4	);
        tableRow5Column5	=(TextView)findViewById(R.id.	tableRow5Column5	);
        tableRow5Column6	=(TextView)findViewById(R.id.	tableRow5Column6	);
        tableRow5Column7	=(TextView)findViewById(R.id.	tableRow5Column7	);

        tableRow6Column0	=(TextView)findViewById(R.id.	tableRow6Column0	);
        tableRow6Column1	=(TextView)findViewById(R.id.	tableRow6Column1	);
        tableRow6Column2	=(TextView)findViewById(R.id.	tableRow6Column2	);
        tableRow6Column3	=(TextView)findViewById(R.id.	tableRow6Column3	);
        tableRow6Column4	=(TextView)findViewById(R.id.	tableRow6Column4	);
        tableRow6Column5	=(TextView)findViewById(R.id.	tableRow6Column5	);
        tableRow6Column6	=(TextView)findViewById(R.id.	tableRow6Column6	);
        tableRow6Column7	=(TextView)findViewById(R.id.	tableRow6Column7	);

        tableRow7Column0	=(TextView)findViewById(R.id.	tableRow7Column0	);
        tableRow7Column1	=(TextView)findViewById(R.id.	tableRow7Column1	);
        tableRow7Column2	=(TextView)findViewById(R.id.	tableRow7Column2	);
        tableRow7Column3	=(TextView)findViewById(R.id.	tableRow7Column3	);
        tableRow7Column4	=(TextView)findViewById(R.id.	tableRow7Column4	);
        tableRow7Column5	=(TextView)findViewById(R.id.	tableRow7Column5	);
        tableRow7Column6	=(TextView)findViewById(R.id.	tableRow7Column6	);
        tableRow7Column7	=(TextView)findViewById(R.id.	tableRow7Column7	);

        Intent theIntent            = getIntent();
        Bundle theChoice            = theIntent.getExtras();
        theMessage                  = theChoice.getString("KEY");
        content.setText(theMessage);
        textCharacter_I.setBackgroundColor(Color.GRAY);
        textCharacter_A.setBackgroundColor(Color.RED);
        textCharacter_C.setBackgroundColor(Color.RED);
        timeStamp.setBackgroundColor(Color.RED);
        textDash.setBackgroundColor(Color.GRAY);
        timeStamp.setText(date);
        makeThemInvisible();

        String stringSplitCode[] = theMessage.split("%");
        String stringSplit[] = theMessage.split("\n");
        /*MAIN CARD INPUT*/
        if(stringSplitCode[0].equalsIgnoreCase("#07"))
        {
            title.setText(listOfTitles[0]);
            for(int i=0; i<mainCardInput.length; i++)
            {
                tableRow2Column7.setText(mainCardInput[0]);
                tableRow2Column6.setText(mainCardInput[1]);
                tableRow2Column5.setText(mainCardInput[2]);
                tableRow2Column4.setText(mainCardInput[3]);
                tableRow2Column3.setText(mainCardInput[4]);
                tableRow2Column2.setText(mainCardInput[5]);
                tableRow2Column1.setText(mainCardInput[6]);
                tableRow2Column0.setText(mainCardInput[7]);

                tableRow0Column7.setText(mainCardInput[8]);
                tableRow0Column6.setText(mainCardInput[9]);
                tableRow0Column5.setText(mainCardInput[10]);
                tableRow0Column4.setText(mainCardInput[11]);
                tableRow0Column3.setText(mainCardInput[12]);
                tableRow0Column2.setText(mainCardInput[13]);
                tableRow0Column1.setText(mainCardInput[14]);
                tableRow0Column0.setText(mainCardInput[15]);
            }
            for(int s=0; s<stringSplit.length; s++)
            {
                //content.setText("-1:"+String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1)))+"\n"+"-2:"+String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))));

                /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/
            }
        }

        /*MAIN CARD TERMINAL INPUT*/
        if(stringSplitCode[0].equalsIgnoreCase("#08"))
        {
            title.setText(listOfTitles[1]);
            for(int i=0; i<mainCardTerminalInput.length; i++)
            {
                tableRow2Column7.setText(mainCardTerminalInput[0]);
                tableRow2Column6.setText(mainCardTerminalInput[1]);
                tableRow2Column5.setText(mainCardTerminalInput[2]);
                tableRow2Column4.setText(mainCardTerminalInput[3]);
                tableRow2Column3.setText(mainCardTerminalInput[4]);
                tableRow2Column2.setText(mainCardTerminalInput[5]);
                tableRow2Column1.setText(mainCardTerminalInput[6]);
                tableRow2Column0.setText(mainCardTerminalInput[7]);

                tableRow0Column7.setText(mainCardTerminalInput[8]);
                tableRow0Column6.setText(mainCardTerminalInput[9]);
                tableRow0Column5.setText(mainCardTerminalInput[10]);
                tableRow0Column4.setText(mainCardTerminalInput[11]);
                tableRow0Column3.setText(mainCardTerminalInput[12]);
                tableRow0Column2.setText(mainCardTerminalInput[13]);
                tableRow0Column1.setText(mainCardTerminalInput[14]);
                tableRow0Column0.setText(mainCardTerminalInput[15]);
            }
            for(int s=0; s<stringSplit.length; s++)
            {
                /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/

                /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                {
                    tableRow3Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                {
                    tableRow3Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                {
                    tableRow3Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                {
                    tableRow3Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                {
                    tableRow3Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                {
                    tableRow3Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow3Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow3Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow3Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                {
                    tableRow3Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                {
                    tableRow3Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                {
                    tableRow3Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                {
                    tableRow3Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                {
                    tableRow3Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                {
                    tableRow3Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                {
                    tableRow3Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                {
                    tableRow3Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                {
                    tableRow3Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                {
                    tableRow3Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                /***************************** END OF BASED ON THIRD CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ****************************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                {
                    tableRow3Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                {
                    tableRow3Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                {
                    tableRow3Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                {
                    tableRow3Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                {
                    tableRow3Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                {
                    tableRow3Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                {
                    tableRow3Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                {
                    tableRow3Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                {
                    tableRow3Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                {
                    tableRow3Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                {
                    tableRow3Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                {
                    tableRow3Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                {
                    tableRow3Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                {
                    tableRow3Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                {
                    tableRow3Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                {
                    tableRow3Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                /********************************** END OF FOURTH CHARACTER FROM RIGHT ****************************/
            }
        }

        /*MAIN CARD OUTPUT*/
        if(stringSplitCode[0].equalsIgnoreCase("#09"))
        {
            title.setText(listOfTitles[2]);
            for(int i=0; i<mainCardOutput.length; i++)
            {
                tableRow2Column7.setText(mainCardOutput[0]);
                tableRow2Column6.setText(mainCardOutput[1]);
                tableRow2Column5.setText(mainCardOutput[2]);
                tableRow2Column4.setText(mainCardOutput[3]);
                tableRow2Column3.setText(mainCardOutput[4]);
                tableRow2Column2.setText(mainCardOutput[5]);
                tableRow2Column1.setText(mainCardOutput[6]);
                tableRow2Column0.setText(mainCardOutput[7]);

                tableRow0Column7.setText(mainCardOutput[8]);
                tableRow0Column6.setText(mainCardOutput[9]);
                tableRow0Column5.setText(mainCardOutput[10]);
                tableRow0Column4.setText(mainCardOutput[11]);
                tableRow0Column3.setText(mainCardOutput[12]);
                tableRow0Column2.setText(mainCardOutput[13]);
                tableRow0Column1.setText(mainCardOutput[14]);
                tableRow0Column0.setText(mainCardOutput[15]);
            }
            for(int s=0; s<stringSplit.length; s++)
            {
                /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/

                /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                {
                    tableRow3Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                {
                    tableRow3Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                {
                    tableRow3Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                {
                    tableRow3Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                {
                    tableRow3Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                {
                    tableRow3Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow3Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow3Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow3Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                {
                    tableRow3Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                {
                    tableRow3Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                {
                    tableRow3Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                {
                    tableRow3Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                {
                    tableRow3Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                {
                    tableRow3Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                {
                    tableRow3Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                {
                    tableRow3Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                {
                    tableRow3Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                {
                    tableRow3Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                /***************************** END OF BASED ON THIRD CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ****************************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                {
                    tableRow3Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                {
                    tableRow3Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                {
                    tableRow3Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                {
                    tableRow3Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                {
                    tableRow3Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                {
                    tableRow3Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                {
                    tableRow3Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                {
                    tableRow3Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                {
                    tableRow3Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                {
                    tableRow3Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                {
                    tableRow3Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                {
                    tableRow3Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                {
                    tableRow3Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                {
                    tableRow3Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                {
                    tableRow3Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                {
                    tableRow3Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                /********************************** END OF FOURTH CHARACTER FROM RIGHT ****************************/
            }
        }

        /*CBC INPUT*/
        if(stringSplitCode[0].equalsIgnoreCase("#10"))
        {
            title.setText(listOfTitles[3]);
            for(int i=0; i<cbcInputs.length; i++)
            {
                tableRow2Column7.setText(cbcInputs[0]);
                tableRow2Column6.setText(cbcInputs[1]);
                tableRow2Column5.setText(cbcInputs[2]);
                tableRow2Column4.setText(cbcInputs[3]);
                tableRow2Column3.setText(cbcInputs[4]);
                tableRow2Column2.setText(cbcInputs[5]);
                tableRow2Column1.setText(cbcInputs[6]);
                tableRow2Column0.setText(cbcInputs[7]);

                tableRow0Column7.setText(cbcInputs[8]);
                tableRow0Column6.setText(cbcInputs[9]);
                tableRow0Column5.setText(cbcInputs[10]);
                tableRow0Column4.setText(cbcInputs[11]);
                tableRow0Column3.setText(cbcInputs[12]);
                tableRow0Column2.setText(cbcInputs[13]);
                tableRow0Column1.setText(cbcInputs[14]);
                tableRow0Column0.setText(cbcInputs[15]);
            }
            for(int s=0; s<stringSplit.length; s++)
            {
                /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/

                /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                {
                    tableRow3Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                {
                    tableRow3Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                {
                    tableRow3Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                {
                    tableRow3Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                {
                    tableRow3Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                {
                    tableRow3Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow3Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow3Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow3Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                {
                    tableRow3Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                {
                    tableRow3Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                {
                    tableRow3Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                {
                    tableRow3Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                {
                    tableRow3Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                {
                    tableRow3Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                {
                    tableRow3Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                {
                    tableRow3Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                {
                    tableRow3Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                {
                    tableRow3Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                /***************************** END OF BASED ON THIRD CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ****************************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                {
                    tableRow3Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                {
                    tableRow3Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                {
                    tableRow3Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                {
                    tableRow3Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                {
                    tableRow3Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                {
                    tableRow3Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                {
                    tableRow3Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                {
                    tableRow3Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                {
                    tableRow3Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                {
                    tableRow3Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                {
                    tableRow3Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                {
                    tableRow3Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                {
                    tableRow3Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                {
                    tableRow3Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                {
                    tableRow3Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                {
                    tableRow3Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                /********************************** END OF FOURTH CHARACTER FROM RIGHT ****************************/
            }
        }

        /*CBC OUTPUT*/
        if(stringSplitCode[0].equalsIgnoreCase("#11"))
        {
            title.setText(listOfTitles[4]);
            for(int i=0; i<cbcOutputs.length; i++)
            {
                tableRow2Column7.setText(cbcOutputs[0]);
                tableRow2Column6.setText(cbcOutputs[1]);
                tableRow2Column5.setText(cbcOutputs[2]);
                tableRow2Column4.setText(cbcOutputs[3]);
                tableRow2Column3.setText(cbcOutputs[4]);
                tableRow2Column2.setText(cbcOutputs[5]);
                tableRow2Column1.setText(cbcOutputs[6]);
                tableRow2Column0.setText(cbcOutputs[7]);

                tableRow0Column7.setText(cbcOutputs[8]);
                tableRow0Column6.setText(cbcOutputs[9]);
                tableRow0Column5.setText(cbcOutputs[10]);
                tableRow0Column4.setText(cbcOutputs[11]);
                tableRow0Column3.setText(cbcOutputs[12]);
                tableRow0Column2.setText(cbcOutputs[13]);
                tableRow0Column1.setText(cbcOutputs[14]);
                tableRow0Column0.setText(cbcOutputs[15]);
            }
            for(int s=0; s<stringSplit.length; s++)
            {
                /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                {
                    tableRow1Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                {
                    tableRow1Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                {
                    tableRow1Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                {
                    tableRow1Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                {
                    tableRow1Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                {
                    tableRow1Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                {
                    tableRow1Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                {
                    tableRow1Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                {
                    tableRow1Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                {
                    tableRow1Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                {
                    tableRow1Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                {
                    tableRow1Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                {
                    tableRow1Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                {
                    tableRow1Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                {
                    tableRow1Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                {
                    tableRow1Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/

                /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                {
                    tableRow3Column3.setText(String.valueOf(binZero.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binZero.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binZero.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                {
                    tableRow3Column3.setText(String.valueOf(binOne.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binOne.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binOne.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                {
                    tableRow3Column3.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                {
                    tableRow3Column3.setText(String.valueOf(binThree.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binThree.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binThree.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                {
                    tableRow3Column3.setText(String.valueOf(binFour.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binFour.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binFour.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                {
                    tableRow3Column4.setText(String.valueOf(binFive.charAt(0)));
                    tableRow3Column5.setText(String.valueOf(binFive.charAt(1)));
                    tableRow3Column6.setText(String.valueOf(binFive.charAt(2)));
                    tableRow3Column7.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                {
                    tableRow3Column3.setText(String.valueOf(binSix.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binSix.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binSix.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                {
                    tableRow3Column3.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                {
                    tableRow3Column3.setText(String.valueOf(binEight.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binEight.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binEight.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                {
                    tableRow3Column3.setText(String.valueOf(binNine.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binNine.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binNine.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                {
                    tableRow3Column3.setText(String.valueOf(binA.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binA.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binA.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                {
                    tableRow3Column3.setText(String.valueOf(binB.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binB.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binB.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                {
                    tableRow3Column3.setText(String.valueOf(binC.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binC.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binC.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                {
                    tableRow3Column3.setText(String.valueOf(binD.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binD.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binD.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                {
                    tableRow3Column3.setText(String.valueOf(binE.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binE.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binE.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                {
                    tableRow3Column3.setText(String.valueOf(binF.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(binF.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(binF.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                /***************************** END OF BASED ON THIRD CHARACTER FROM RIGHT **************************/

                /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ****************************/
                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                {
                    tableRow3Column7.setText(String.valueOf(binZero.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binZero.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binZero.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binZero.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                {
                    tableRow3Column7.setText(String.valueOf(binOne.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binOne.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binOne.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binOne.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                {
                    tableRow3Column7.setText(String.valueOf(binTwo.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binTwo.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binTwo.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binTwo.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                {
                    tableRow3Column7.setText(String.valueOf(binThree.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binThree.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binThree.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binThree.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                {
                    tableRow3Column7.setText(String.valueOf(binFour.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binFour.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binFour.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binFour.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                {
                    tableRow3Column7.setText(String.valueOf(binFive.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binFive.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binFive.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binFive.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                {
                    tableRow3Column7.setText(String.valueOf(binSix.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binSix.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binSix.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binSix.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                {
                    tableRow3Column7.setText(String.valueOf(binSeven.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binSeven.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binSeven.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binSeven.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                {
                    tableRow3Column7.setText(String.valueOf(binEight.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binEight.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binEight.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binEight.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                {
                    tableRow3Column7.setText(String.valueOf(binNine.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binNine.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binNine.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binNine.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                {
                    tableRow3Column7.setText(String.valueOf(binA.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binA.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binA.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binA.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                {
                    tableRow3Column7.setText(String.valueOf(binB.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binB.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binB.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binB.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                {
                    tableRow3Column7.setText(String.valueOf(binC.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binC.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binC.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binC.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                {
                    tableRow3Column7.setText(String.valueOf(binD.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binD.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binD.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binD.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                {
                    tableRow3Column7.setText(String.valueOf(binE.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binE.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binE.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binE.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                {
                    tableRow3Column7.setText(String.valueOf(binF.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(binF.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(binF.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(binF.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }

                /********************************** END OF FOURTH CHARACTER FROM RIGHT ****************************/
            }
        }

        /*FLOOR UP*/
        if(stringSplitCode[0].equalsIgnoreCase("#12"))
        {
            title.setText(listOfTitles[5]);
            makeThemVisible();
            for(int i=0; i<floorKeysZeroToFifteen.length; i++)
            {
                setTitleForTheFirstTwoRows();
            }
            for(int i=0; i<floorKeysSixteenAbove.length; i++)
            {
                setTitleForTheLastTwoRows();
            }
            for(int s=0; s<stringSplit.length; s++)
            {
                /*CHECKING FIRST CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                    tableRow1Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                    tableRow1Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo)) {
                    tableRow1Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree)) {
                    tableRow1Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                    tableRow1Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive)) {
                    tableRow1Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix)) {
                    tableRow1Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven)) {
                    tableRow1Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight)) {
                    tableRow1Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine)) {
                    tableRow1Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA)) {
                    tableRow1Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB)) {
                    tableRow1Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC)) {
                    tableRow1Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD)) {
                    tableRow1Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE)) {
                    tableRow1Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF)) {
                    tableRow1Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                /*END OF CHECKING FIRST CHARACTER FROM RIGHT*/
                /*SECOND CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                    tableRow1Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                    tableRow1Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                    tableRow1Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                    tableRow1Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                    tableRow1Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                    tableRow1Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                    tableRow1Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                    tableRow1Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                    tableRow1Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                    tableRow1Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA)) {
                    tableRow1Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB)) {
                    tableRow1Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC)) {
                    tableRow1Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD)) {
                    tableRow1Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE)) {
                    tableRow1Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF)) {
                    tableRow1Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                /*END OF SECOND CHARACTER FROM RIGHT*/
                /*THIRD CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                    tableRow3Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                    tableRow3Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                    tableRow3Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                    tableRow3Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                    tableRow3Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                    tableRow3Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                    tableRow3Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                    tableRow3Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                    tableRow3Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                    tableRow3Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexA)) {
                    tableRow3Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexB)) {
                    tableRow3Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexC)) {
                    tableRow3Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexD)) {
                    tableRow3Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexE)) {
                    tableRow3Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexF)) {
                    tableRow3Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                /*END OF THIRD CHARACTER FROM RIGHT*/
                /*FOURTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                    tableRow3Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                    tableRow3Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                    tableRow3Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                    tableRow3Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                    tableRow3Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                    tableRow3Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                    tableRow3Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                    tableRow3Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                    tableRow3Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                    tableRow3Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexA)) {
                    tableRow3Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexB)) {
                    tableRow3Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexC)) {
                    tableRow3Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexD)) {
                    tableRow3Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexE)) {
                    tableRow3Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexF)) {
                    tableRow3Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                /*END OF FOURTH CHARACTER FROM RIGHT*/

                /*CHECKING FIFTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexZero)) {
                    tableRow5Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexOne)) {
                    tableRow5Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexTwo)) {
                    tableRow5Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexThree)) {
                    tableRow5Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFour)) {
                    tableRow5Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFive)) {
                    tableRow5Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSix)) {
                    tableRow5Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSeven)) {
                    tableRow5Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexEight)) {
                    tableRow5Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexNine)) {
                    tableRow5Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexA)) {
                    tableRow5Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexB)) {
                    tableRow5Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexC)) {
                    tableRow5Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexD)) {
                    tableRow5Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexE)) {
                    tableRow5Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexF)) {
                    tableRow5Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                /*END OF CHECKING FIFTH CHARACTER FROM RIGHT*/
                /*SIXTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexZero)) {
                    tableRow5Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexOne)) {
                    tableRow5Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexTwo)) {
                    tableRow5Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexThree)) {
                    tableRow5Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFour)) {
                    tableRow5Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFive)) {
                    tableRow5Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSix)) {
                    tableRow5Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSeven)) {
                    tableRow5Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexEight)) {
                    tableRow5Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexNine)) {
                    tableRow5Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexA)) {
                    tableRow5Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexB)) {
                    tableRow5Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexC)) {
                    tableRow5Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexD)) {
                    tableRow5Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexE)) {
                    tableRow5Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexF)) {
                    tableRow5Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                /*END OF SIXTH CHARACTER FROM RIGHT*/

                /*SEVENTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexZero)) {
                    tableRow7Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexOne)) {
                    tableRow7Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexTwo)) {
                    tableRow7Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexThree)) {
                    tableRow7Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFour)) {
                    tableRow7Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFive)) {
                    tableRow7Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSix)) {
                    tableRow7Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSeven)) {
                    tableRow7Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexEight)) {
                    tableRow7Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexNine)) {
                    tableRow7Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexA)) {
                    tableRow7Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexB)) {
                    tableRow7Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexC)) {
                    tableRow7Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexD)) {
                    tableRow7Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexE)) {
                    tableRow7Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexF)) {
                    tableRow7Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                /*END OF SEVENTH CHARACTER FROM RIGHT*/
                /*EIGHTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexZero)) {
                    tableRow7Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexOne)) {
                    tableRow7Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexTwo)) {
                    tableRow7Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexThree)) {
                    tableRow7Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFour)) {
                    tableRow7Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFive)) {
                    tableRow7Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSix)) {
                    tableRow7Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSeven)) {
                    tableRow7Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexEight)) {
                    tableRow7Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexNine)) {
                    tableRow7Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexA)) {
                    tableRow7Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexB)) {
                    tableRow7Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexC)) {
                    tableRow7Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexD)) {
                    tableRow7Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexE)) {
                    tableRow7Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexF)) {
                    tableRow7Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                /*END OF EIGHTH CHARACTER FROM RIGHT*/
            }
        }

        /*FLOOR DOWN*/
        if(stringSplitCode[0].equalsIgnoreCase("#13"))
        {
            title.setText(listOfTitles[6]);
            makeThemVisible();
            for(int i=0; i<floorKeysZeroToFifteen.length; i++)
            {
                tableRow2Column7.setText(floorKeysZeroToFifteen[0]);
                tableRow2Column6.setText(floorKeysZeroToFifteen[1]);
                tableRow2Column5.setText(floorKeysZeroToFifteen[2]);
                tableRow2Column4.setText(floorKeysZeroToFifteen[3]);
                tableRow2Column3.setText(floorKeysZeroToFifteen[4]);
                tableRow2Column2.setText(floorKeysZeroToFifteen[5]);
                tableRow2Column1.setText(floorKeysZeroToFifteen[6]);
                tableRow2Column0.setText(floorKeysZeroToFifteen[7]);

                tableRow0Column7.setText(floorKeysZeroToFifteen[8]);
                tableRow0Column6.setText(floorKeysZeroToFifteen[9]);
                tableRow0Column5.setText(floorKeysZeroToFifteen[10]);
                tableRow0Column4.setText(floorKeysZeroToFifteen[11]);
                tableRow0Column3.setText(floorKeysZeroToFifteen[12]);
                tableRow0Column2.setText(floorKeysZeroToFifteen[13]);
                tableRow0Column1.setText(floorKeysZeroToFifteen[14]);
                tableRow0Column0.setText(floorKeysZeroToFifteen[15]);
            }
            for(int i=0; i<floorKeysSixteenAbove.length; i++)
            {
                tableRow6Column7.setText(floorKeysSixteenAbove[0]);
                tableRow6Column6.setText(floorKeysSixteenAbove[1]);
                tableRow6Column5.setText(floorKeysSixteenAbove[2]);
                tableRow6Column4.setText(floorKeysSixteenAbove[3]);
                tableRow6Column3.setText(floorKeysSixteenAbove[4]);
                tableRow6Column2.setText(floorKeysSixteenAbove[5]);
                tableRow6Column1.setText(floorKeysSixteenAbove[6]);
                tableRow6Column0.setText(floorKeysSixteenAbove[7]);

                tableRow4Column7.setText(floorKeysSixteenAbove[8]);
                tableRow4Column6.setText(floorKeysSixteenAbove[9]);
                tableRow4Column5.setText(floorKeysSixteenAbove[10]);
                tableRow4Column4.setText(floorKeysSixteenAbove[11]);
                tableRow4Column3.setText(floorKeysSixteenAbove[12]);
                tableRow4Column2.setText(floorKeysSixteenAbove[13]);
                tableRow4Column1.setText(floorKeysSixteenAbove[14]);
                tableRow4Column0.setText(floorKeysSixteenAbove[15]);
            }
            for(int s=0; s<stringSplit.length; s++)
            {
                /*CHECKING FIRST CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                    tableRow1Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                    tableRow1Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo)) {
                    tableRow1Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree)) {
                    tableRow1Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                    tableRow1Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive)) {
                    tableRow1Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix)) {
                    tableRow1Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven)) {
                    tableRow1Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight)) {
                    tableRow1Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine)) {
                    tableRow1Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA)) {
                    tableRow1Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB)) {
                    tableRow1Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC)) {
                    tableRow1Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD)) {
                    tableRow1Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE)) {
                    tableRow1Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF)) {
                    tableRow1Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                /*END OF CHECKING FIRST CHARACTER FROM RIGHT*/
                /*SECOND CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                    tableRow1Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                    tableRow1Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                    tableRow1Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                    tableRow1Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                    tableRow1Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                    tableRow1Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                    tableRow1Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                    tableRow1Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                    tableRow1Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                    tableRow1Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA)) {
                    tableRow1Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB)) {
                    tableRow1Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC)) {
                    tableRow1Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD)) {
                    tableRow1Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE)) {
                    tableRow1Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF)) {
                    tableRow1Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                /*END OF SECOND CHARACTER FROM RIGHT*/
                /*THIRD CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                    tableRow3Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                    tableRow3Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                    tableRow3Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                    tableRow3Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                    tableRow3Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                    tableRow3Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                    tableRow3Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                    tableRow3Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                    tableRow3Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                    tableRow3Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexA)) {
                    tableRow3Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexB)) {
                    tableRow3Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexC)) {
                    tableRow3Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexD)) {
                    tableRow3Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexE)) {
                    tableRow3Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexF)) {
                    tableRow3Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                /*END OF THIRD CHARACTER FROM RIGHT*/
                /*FOURTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                    tableRow3Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                    tableRow3Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                    tableRow3Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                    tableRow3Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                    tableRow3Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                    tableRow3Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                    tableRow3Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                    tableRow3Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                    tableRow3Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                    tableRow3Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexA)) {
                    tableRow3Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexB)) {
                    tableRow3Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexC)) {
                    tableRow3Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexD)) {
                    tableRow3Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexE)) {
                    tableRow3Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexF)) {
                    tableRow3Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                /*END OF FOURTH CHARACTER FROM RIGHT*/

                /*CHECKING FIFTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexZero)) {
                    tableRow5Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexOne)) {
                    tableRow5Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexTwo)) {
                    tableRow5Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexThree)) {
                    tableRow5Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFour)) {
                    tableRow5Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFive)) {
                    tableRow5Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSix)) {
                    tableRow5Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSeven)) {
                    tableRow5Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexEight)) {
                    tableRow5Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexNine)) {
                    tableRow5Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexA)) {
                    tableRow5Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexB)) {
                    tableRow5Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexC)) {
                    tableRow5Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexD)) {
                    tableRow5Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexE)) {
                    tableRow5Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexF)) {
                    tableRow5Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                /*END OF CHECKING FIFTH CHARACTER FROM RIGHT*/
                /*SIXTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexZero)) {
                    tableRow5Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexOne)) {
                    tableRow5Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexTwo)) {
                    tableRow5Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexThree)) {
                    tableRow5Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFour)) {
                    tableRow5Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFive)) {
                    tableRow5Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSix)) {
                    tableRow5Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSeven)) {
                    tableRow5Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexEight)) {
                    tableRow5Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexNine)) {
                    tableRow5Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexA)) {
                    tableRow5Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexB)) {
                    tableRow5Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexC)) {
                    tableRow5Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexD)) {
                    tableRow5Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexE)) {
                    tableRow5Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexF)) {
                    tableRow5Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                /*END OF SIXTH CHARACTER FROM RIGHT*/

                /*SEVENTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexZero)) {
                    tableRow7Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexOne)) {
                    tableRow7Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexTwo)) {
                    tableRow7Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexThree)) {
                    tableRow7Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFour)) {
                    tableRow7Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFive)) {
                    tableRow7Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSix)) {
                    tableRow7Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSeven)) {
                    tableRow7Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexEight)) {
                    tableRow7Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexNine)) {
                    tableRow7Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexA)) {
                    tableRow7Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexB)) {
                    tableRow7Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexC)) {
                    tableRow7Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexD)) {
                    tableRow7Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexE)) {
                    tableRow7Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexF)) {
                    tableRow7Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                /*END OF SEVENTH CHARACTER FROM RIGHT*/
                /*EIGHTH CHARACTER FROM RIGHT*/
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexZero)) {
                    tableRow7Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexOne)) {
                    tableRow7Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexTwo)) {
                    tableRow7Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexThree)) {
                    tableRow7Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFour)) {
                    tableRow7Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFive)) {
                    tableRow7Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSix)) {
                    tableRow7Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSeven)) {
                    tableRow7Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexEight)) {
                    tableRow7Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexNine)) {
                    tableRow7Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexA)) {
                    tableRow7Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexB)) {
                    tableRow7Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexC)) {
                    tableRow7Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexD)) {
                    tableRow7Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexE)) {
                    tableRow7Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexF)) {
                    tableRow7Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                /*END OF EIGHTH CHARACTER FROM RIGHT*/
            }
        }

        /*CAR KEYS*/
        if(stringSplitCode[0].equalsIgnoreCase("#14"))
        {
            //toast("#14");
            toast(stringSplitCode[1]);
            toast("RAW LENGTH:"+stringSplitCode[1].length());
            carCallStatus();
            setStatus(stringSplitCode[1]);
        }

        /*LIFT STATUS*/
        if (stringSplitCode[0].equalsIgnoreCase("#15"))
        {
            stopTitles.setText(listOfTitles[8]);
            makeAllInvisible();
            for(int s=0; s<stringSplit.length; s++)
            {
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero))
                {
                    liftStopStatus.setText("LIFT IS STOPPED");
                    liftStopStatus.setBackgroundColor(Color.GRAY);
                    liftStopStatus.setTextColor(Color.WHITE);
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour))
                {
                    liftStopStatus.setText("LIFT IS RUNNING");
                    liftStopStatus.setBackgroundColor(Color.RED);
                    liftStopStatus.setTextColor(Color.WHITE);
                }
            }
        }

        /*FOR LIFT EMERGENCY STOP*/
        if (stringSplitCode[0].equalsIgnoreCase("#20"))
        {
            title.setText(listOfTitles[10]);
            makeAllInvisible();
            for(int s=0; s<stringSplit.length; s++)
            {
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero))
                {
                    liftStopStatus.setText("EMERGENCY STOP RELEASED");
                    liftStopStatus.setBackgroundColor(Color.GRAY);
                    liftStopStatus.setTextColor(Color.WHITE);
                }
                if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne))
                {
                    liftStopStatus.setText("LIFT IS STOPPED");
                    liftStopStatus.setBackgroundColor(Color.RED);
                    liftStopStatus.setTextColor(Color.WHITE);
                }
            }
        }

        /*LIFT ERROR STATUS*/
        if (stringSplitCode[0].equalsIgnoreCase("#16"))
        {
            table1.setVisibility(View.INVISIBLE);
            table2.setVisibility(View.INVISIBLE);
            table3.setVisibility(View.INVISIBLE);
            table4.setVisibility(View.INVISIBLE);
            table5.setVisibility(View.INVISIBLE);
            table6.setVisibility(View.INVISIBLE);
            table7.setVisibility(View.INVISIBLE);
            table8.setVisibility(View.INVISIBLE);
            table9.setVisibility(View.INVISIBLE);
            table0.setVisibility(View.VISIBLE);

            errorTitle.setVisibility(View.VISIBLE);
            errorTitle.setText("LIFT ERROR STATUS");
            err15.setVisibility(View.VISIBLE);
            err14.setVisibility(View.VISIBLE);
            err13.setVisibility(View.VISIBLE);
            err12.setVisibility(View.VISIBLE);
            err11.setVisibility(View.VISIBLE);
            err10.setVisibility(View.VISIBLE);
            err9.setVisibility(View.VISIBLE);
            err8.setVisibility(View.VISIBLE);
            err7.setVisibility(View.VISIBLE);
            err6.setVisibility(View.VISIBLE);
            err5.setVisibility(View.VISIBLE);
            err4.setVisibility(View.VISIBLE);
            err3.setVisibility(View.VISIBLE);
            err2.setVisibility(View.VISIBLE);
            err1.setVisibility(View.VISIBLE);
            err0.setVisibility(View.VISIBLE);

            err15_d.setVisibility(View.VISIBLE);
            err14_d.setVisibility(View.VISIBLE);
            err13_d.setVisibility(View.VISIBLE);
            err12_d.setVisibility(View.VISIBLE);
            err11_d.setVisibility(View.VISIBLE);
            err10_d.setVisibility(View.VISIBLE);
            err9_d.setVisibility(View.VISIBLE);
            err8_d.setVisibility(View.VISIBLE);
            err7_d.setVisibility(View.VISIBLE);
            err6_d.setVisibility(View.VISIBLE);
            err5_d.setVisibility(View.VISIBLE);
            err4_d.setVisibility(View.VISIBLE);
            err3_d.setVisibility(View.VISIBLE);
            err2_d.setVisibility(View.VISIBLE);
            err1_d.setVisibility(View.VISIBLE);
            err0_d.setVisibility(View.VISIBLE);

            err19.setVisibility(View.VISIBLE);
            err18.setVisibility(View.VISIBLE);
            err17.setVisibility(View.VISIBLE);
            err16.setVisibility(View.VISIBLE);
            err19_d.setVisibility(View.VISIBLE);
            err18_d.setVisibility(View.VISIBLE);
            err17_d.setVisibility(View.VISIBLE);
            err16_d.setVisibility(View.VISIBLE);

            for (int i = 0; i < liftErrorStatus_I.length; i++)
            {
                err15.setText(liftErrorStatus_I[0]);
                err14.setText(liftErrorStatus_I[1]);
                err13.setText(liftErrorStatus_I[2]);
                err12.setText(liftErrorStatus_I[3]);
                err11.setText(liftErrorStatus_I[4]);
                err10.setText(liftErrorStatus_I[5]);
                err9.setText(liftErrorStatus_I[6]);
                err8.setText(liftErrorStatus_I[7]);
                err7.setText(liftErrorStatus_I[8]);
                err6.setText(liftErrorStatus_I[9]);
                err5.setText(liftErrorStatus_I[10]);
                err4.setText(liftErrorStatus_I[11]);
                err3.setText(liftErrorStatus_I[12]);
                err2.setText(liftErrorStatus_I[13]);
                err1.setText(liftErrorStatus_I[14]);
                err0.setText(liftErrorStatus_I[15]);
                liftErrorStatusData(stringSplitCode[1]);
            }//end of for()
            for (int i = 0; i < liftErrorStatus_II.length; i++)
            {
                err19.setText(liftErrorStatus_II[0]);
                err18.setText(liftErrorStatus_II[1]);
                err17.setText(liftErrorStatus_II[2]);
                err16.setText(liftErrorStatus_II[3]);
            }//end of for()
        }

    }//end of onCreate()

    public void liftErrorStatusData(String code)
    {
        String liftErrorStat[] = new String[1];
        liftErrorStat[0]=code;
        try
        {
            for(int s=0; s<liftErrorStat.length; s++)
            {
                /*CHECKING FIRST CHARACTER FROM RIGHT*/
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 2))))
                {
                    case hexZero:
                        err3_d.setText(String.valueOf(binZero.charAt(0)));
                        err2_d.setText(String.valueOf(binZero.charAt(1)));
                        err1_d.setText(String.valueOf(binZero.charAt(2)));
                        err0_d.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexOne:
                        err3_d.setText(String.valueOf(binOne.charAt(0)));
                        err2_d.setText(String.valueOf(binOne.charAt(1)));
                        err1_d.setText(String.valueOf(binOne.charAt(2)));
                        err0_d.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexTwo:
                        err3_d.setText(String.valueOf(binTwo.charAt(0)));
                        err2_d.setText(String.valueOf(binTwo.charAt(1)));
                        err1_d.setText(String.valueOf(binTwo.charAt(2)));
                        err0_d.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexThree:
                        err3_d.setText(String.valueOf(binThree.charAt(0)));
                        err2_d.setText(String.valueOf(binThree.charAt(1)));
                        err1_d.setText(String.valueOf(binThree.charAt(2)));
                        err0_d.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFour:
                        err3_d.setText(String.valueOf(binFour.charAt(0)));
                        err2_d.setText(String.valueOf(binFour.charAt(1)));
                        err1_d.setText(String.valueOf(binFour.charAt(2)));
                        err0_d.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFive:
                        err3_d.setText(String.valueOf(binFive.charAt(0)));
                        err2_d.setText(String.valueOf(binFive.charAt(1)));
                        err1_d.setText(String.valueOf(binFive.charAt(2)));
                        err0_d.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSix:
                        err3_d.setText(String.valueOf(binSix.charAt(0)));
                        err2_d.setText(String.valueOf(binSix.charAt(1)));
                        err1_d.setText(String.valueOf(binSix.charAt(2)));
                        err0_d.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSeven:
                        err3_d.setText(String.valueOf(binSeven.charAt(0)));
                        err2_d.setText(String.valueOf(binSeven.charAt(1)));
                        err1_d.setText(String.valueOf(binSeven.charAt(2)));
                        err0_d.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexEight:
                        err3_d.setText(String.valueOf(binEight.charAt(0)));
                        err2_d.setText(String.valueOf(binEight.charAt(1)));
                        err1_d.setText(String.valueOf(binEight.charAt(2)));
                        err0_d.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexNine:
                        err3_d.setText(String.valueOf(binNine.charAt(0)));
                        err2_d.setText(String.valueOf(binNine.charAt(1)));
                        err1_d.setText(String.valueOf(binNine.charAt(2)));
                        err0_d.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexA:
                        err3_d.setText(String.valueOf(binA.charAt(0)));
                        err2_d.setText(String.valueOf(binA.charAt(1)));
                        err1_d.setText(String.valueOf(binA.charAt(2)));
                        err0_d.setText(String.valueOf(binA.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexB:
                        err3_d.setText(String.valueOf(binB.charAt(0)));
                        err2_d.setText(String.valueOf(binB.charAt(1)));
                        err1_d.setText(String.valueOf(binB.charAt(2)));
                        err0_d.setText(String.valueOf(binB.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexC:
                        err3_d.setText(String.valueOf(binC.charAt(0)));
                        err2_d.setText(String.valueOf(binC.charAt(1)));
                        err1_d.setText(String.valueOf(binC.charAt(2)));
                        err0_d.setText(String.valueOf(binC.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexD:
                        err3_d.setText(String.valueOf(binD.charAt(0)));
                        err2_d.setText(String.valueOf(binD.charAt(1)));
                        err1_d.setText(String.valueOf(binD.charAt(2)));
                        err0_d.setText(String.valueOf(binD.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexE:
                        err3_d.setText(String.valueOf(binE.charAt(0)));
                        err2_d.setText(String.valueOf(binE.charAt(1)));
                        err1_d.setText(String.valueOf(binE.charAt(2)));
                        err0_d.setText(String.valueOf(binE.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexF:
                        err3_d.setText(String.valueOf(binF.charAt(0)));
                        err2_d.setText(String.valueOf(binF.charAt(1)));
                        err1_d.setText(String.valueOf(binF.charAt(2)));
                        err0_d.setText(String.valueOf(binF.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    default:
                }

                /*CHECKING SECOND CHARACTER FROM RIGHT*/
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 3))))
                {
                    case hexZero:
                        err7_d.setText(String.valueOf(binZero.charAt(0)));
                        err6_d.setText(String.valueOf(binZero.charAt(1)));
                        err5_d.setText(String.valueOf(binZero.charAt(2)));
                        err4_d.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexOne:
                        err7_d.setText(String.valueOf(binOne.charAt(0)));
                        err6_d.setText(String.valueOf(binOne.charAt(1)));
                        err5_d.setText(String.valueOf(binOne.charAt(2)));
                        err4_d.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexTwo:
                        err7_d.setText(String.valueOf(binTwo.charAt(0)));
                        err6_d.setText(String.valueOf(binTwo.charAt(1)));
                        err5_d.setText(String.valueOf(binTwo.charAt(2)));
                        err4_d.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexThree:
                        err7_d.setText(String.valueOf(binThree.charAt(0)));
                        err6_d.setText(String.valueOf(binThree.charAt(1)));
                        err5_d.setText(String.valueOf(binThree.charAt(2)));
                        err4_d.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFour:
                        err7_d.setText(String.valueOf(binFour.charAt(0)));
                        err6_d.setText(String.valueOf(binFour.charAt(1)));
                        err5_d.setText(String.valueOf(binFour.charAt(2)));
                        err4_d.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFive:
                        err7_d.setText(String.valueOf(binFive.charAt(0)));
                        err6_d.setText(String.valueOf(binFive.charAt(1)));
                        err5_d.setText(String.valueOf(binFive.charAt(2)));
                        err4_d.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSix:
                        err7_d.setText(String.valueOf(binSix.charAt(0)));
                        err6_d.setText(String.valueOf(binSix.charAt(1)));
                        err5_d.setText(String.valueOf(binSix.charAt(2)));
                        err4_d.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSeven:
                        err7_d.setText(String.valueOf(binSeven.charAt(0)));
                        err6_d.setText(String.valueOf(binSeven.charAt(1)));
                        err5_d.setText(String.valueOf(binSeven.charAt(2)));
                        err4_d.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexEight:
                        err7_d.setText(String.valueOf(binEight.charAt(0)));
                        err6_d.setText(String.valueOf(binEight.charAt(1)));
                        err5_d.setText(String.valueOf(binEight.charAt(2)));
                        err4_d.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexNine:
                        err7_d.setText(String.valueOf(binNine.charAt(0)));
                        err6_d.setText(String.valueOf(binNine.charAt(1)));
                        err5_d.setText(String.valueOf(binNine.charAt(2)));
                        err4_d.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexA:
                        err7_d.setText(String.valueOf(binA.charAt(0)));
                        err6_d.setText(String.valueOf(binA.charAt(1)));
                        err5_d.setText(String.valueOf(binA.charAt(2)));
                        err4_d.setText(String.valueOf(binA.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexB:
                        err7_d.setText(String.valueOf(binB.charAt(0)));
                        err6_d.setText(String.valueOf(binB.charAt(1)));
                        err5_d.setText(String.valueOf(binB.charAt(2)));
                        err4_d.setText(String.valueOf(binB.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexC:
                        err7_d.setText(String.valueOf(binC.charAt(0)));
                        err6_d.setText(String.valueOf(binC.charAt(1)));
                        err5_d.setText(String.valueOf(binC.charAt(2)));
                        err4_d.setText(String.valueOf(binC.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexD:
                        err7_d.setText(String.valueOf(binD.charAt(0)));
                        err6_d.setText(String.valueOf(binD.charAt(1)));
                        err5_d.setText(String.valueOf(binD.charAt(2)));
                        err4_d.setText(String.valueOf(binD.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexE:
                        err7_d.setText(String.valueOf(binE.charAt(0)));
                        err6_d.setText(String.valueOf(binE.charAt(1)));
                        err5_d.setText(String.valueOf(binE.charAt(2)));
                        err4_d.setText(String.valueOf(binE.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexF:
                        err7_d.setText(String.valueOf(binF.charAt(0)));
                        err6_d.setText(String.valueOf(binF.charAt(1)));
                        err5_d.setText(String.valueOf(binF.charAt(2)));
                        err4_d.setText(String.valueOf(binF.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    default:

                }

                /*CHECKING THIRD CHARACTER FROM RIGHT*/
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 4))))
                {
                    case hexZero:
                        err11_d.setText(String.valueOf(binZero.charAt(0)));
                        err10_d.setText(String.valueOf(binZero.charAt(1)));
                        err9_d.setText(String.valueOf(binZero.charAt(2)));
                        err8_d.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexOne:
                        err11_d.setText(String.valueOf(binOne.charAt(0)));
                        err10_d.setText(String.valueOf(binOne.charAt(1)));
                        err9_d.setText(String.valueOf(binOne.charAt(2)));
                        err8_d.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexTwo:
                        err11_d.setText(String.valueOf(binTwo.charAt(0)));
                        err10_d.setText(String.valueOf(binTwo.charAt(1)));
                        err9_d.setText(String.valueOf(binTwo.charAt(2)));
                        err8_d.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexThree:
                        err11_d.setText(String.valueOf(binThree.charAt(0)));
                        err10_d.setText(String.valueOf(binThree.charAt(1)));
                        err9_d.setText(String.valueOf(binThree.charAt(2)));
                        err8_d.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFour:
                        err11_d.setText(String.valueOf(binFour.charAt(0)));
                        err10_d.setText(String.valueOf(binFour.charAt(1)));
                        err9_d.setText(String.valueOf(binFour.charAt(2)));
                        err8_d.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFive:
                        err11_d.setText(String.valueOf(binFive.charAt(0)));
                        err10_d.setText(String.valueOf(binFive.charAt(1)));
                        err9_d.setText(String.valueOf(binFive.charAt(2)));
                        err8_d.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSix:
                        err11_d.setText(String.valueOf(binSix.charAt(0)));
                        err10_d.setText(String.valueOf(binSix.charAt(1)));
                        err9_d.setText(String.valueOf(binSix.charAt(2)));
                        err8_d.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSeven:
                        err11_d.setText(String.valueOf(binSeven.charAt(0)));
                        err10_d.setText(String.valueOf(binSeven.charAt(1)));
                        err9_d.setText(String.valueOf(binSeven.charAt(2)));
                        err8_d.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexEight:
                        err11_d.setText(String.valueOf(binEight.charAt(0)));
                        err10_d.setText(String.valueOf(binEight.charAt(1)));
                        err9_d.setText(String.valueOf(binEight.charAt(2)));
                        err8_d.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexNine:
                        err11_d.setText(String.valueOf(binNine.charAt(0)));
                        err10_d.setText(String.valueOf(binNine.charAt(1)));
                        err9_d.setText(String.valueOf(binNine.charAt(2)));
                        err8_d.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexA:
                        err11_d.setText(String.valueOf(binA.charAt(0)));
                        err10_d.setText(String.valueOf(binA.charAt(1)));
                        err9_d.setText(String.valueOf(binA.charAt(2)));
                        err8_d.setText(String.valueOf(binA.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexB:
                        err11_d.setText(String.valueOf(binB.charAt(0)));
                        err10_d.setText(String.valueOf(binB.charAt(1)));
                        err9_d.setText(String.valueOf(binB.charAt(2)));
                        err8_d.setText(String.valueOf(binB.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexC:
                        err11_d.setText(String.valueOf(binC.charAt(0)));
                        err10_d.setText(String.valueOf(binC.charAt(1)));
                        err9_d.setText(String.valueOf(binC.charAt(2)));
                        err8_d.setText(String.valueOf(binC.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexD:
                        err11_d.setText(String.valueOf(binD.charAt(0)));
                        err10_d.setText(String.valueOf(binD.charAt(1)));
                        err9_d.setText(String.valueOf(binD.charAt(2)));
                        err8_d.setText(String.valueOf(binD.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexE:
                        err11_d.setText(String.valueOf(binE.charAt(0)));
                        err10_d.setText(String.valueOf(binE.charAt(1)));
                        err9_d.setText(String.valueOf(binE.charAt(2)));
                        err8_d.setText(String.valueOf(binE.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexF:
                        err11_d.setText(String.valueOf(binF.charAt(0)));
                        err10_d.setText(String.valueOf(binF.charAt(1)));
                        err9_d.setText(String.valueOf(binF.charAt(2)));
                        err8_d.setText(String.valueOf(binF.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    default:

                }

                /*CHECKING FOURTH CHARACTER FROM RIGHT*/
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 5))))
                {
                    case hexZero:
                        err15_d.setText(String.valueOf(binZero.charAt(0)));
                        err14_d.setText(String.valueOf(binZero.charAt(1)));
                        err13_d.setText(String.valueOf(binZero.charAt(2)));
                        err12_d.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexOne:
                        err15_d.setText(String.valueOf(binOne.charAt(0)));
                        err14_d.setText(String.valueOf(binOne.charAt(1)));
                        err13_d.setText(String.valueOf(binOne.charAt(2)));
                        err12_d.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexTwo:
                        err15_d.setText(String.valueOf(binTwo.charAt(0)));
                        err14_d.setText(String.valueOf(binTwo.charAt(1)));
                        err13_d.setText(String.valueOf(binTwo.charAt(2)));
                        err12_d.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexThree:
                        err15_d.setText(String.valueOf(binThree.charAt(0)));
                        err14_d.setText(String.valueOf(binThree.charAt(1)));
                        err13_d.setText(String.valueOf(binThree.charAt(2)));
                        err12_d.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFour:
                        err15_d.setText(String.valueOf(binFour.charAt(0)));
                        err14_d.setText(String.valueOf(binFour.charAt(1)));
                        err13_d.setText(String.valueOf(binFour.charAt(2)));
                        err12_d.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFive:
                        err15_d.setText(String.valueOf(binFive.charAt(0)));
                        err14_d.setText(String.valueOf(binFive.charAt(1)));
                        err13_d.setText(String.valueOf(binFive.charAt(2)));
                        err12_d.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSix:
                        err15_d.setText(String.valueOf(binSix.charAt(0)));
                        err14_d.setText(String.valueOf(binSix.charAt(1)));
                        err13_d.setText(String.valueOf(binSix.charAt(2)));
                        err12_d.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSeven:
                        err15_d.setText(String.valueOf(binSeven.charAt(0)));
                        err14_d.setText(String.valueOf(binSeven.charAt(1)));
                        err13_d.setText(String.valueOf(binSeven.charAt(2)));
                        err12_d.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexEight:
                        err15_d.setText(String.valueOf(binEight.charAt(0)));
                        err14_d.setText(String.valueOf(binEight.charAt(1)));
                        err13_d.setText(String.valueOf(binEight.charAt(2)));
                        err12_d.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexNine:
                        err15_d.setText(String.valueOf(binNine.charAt(0)));
                        err14_d.setText(String.valueOf(binNine.charAt(1)));
                        err13_d.setText(String.valueOf(binNine.charAt(2)));
                        err12_d.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexA:
                        err15_d.setText(String.valueOf(binA.charAt(0)));
                        err14_d.setText(String.valueOf(binA.charAt(1)));
                        err13_d.setText(String.valueOf(binA.charAt(2)));
                        err12_d.setText(String.valueOf(binA.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexB:
                        err15_d.setText(String.valueOf(binB.charAt(0)));
                        err14_d.setText(String.valueOf(binB.charAt(1)));
                        err13_d.setText(String.valueOf(binB.charAt(2)));
                        err12_d.setText(String.valueOf(binB.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexC:
                        err15_d.setText(String.valueOf(binC.charAt(0)));
                        err14_d.setText(String.valueOf(binC.charAt(1)));
                        err13_d.setText(String.valueOf(binC.charAt(2)));
                        err12_d.setText(String.valueOf(binC.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexD:
                        err15_d.setText(String.valueOf(binD.charAt(0)));
                        err14_d.setText(String.valueOf(binD.charAt(1)));
                        err13_d.setText(String.valueOf(binD.charAt(2)));
                        err12_d.setText(String.valueOf(binD.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexE:
                        err15_d.setText(String.valueOf(binE.charAt(0)));
                        err14_d.setText(String.valueOf(binE.charAt(1)));
                        err13_d.setText(String.valueOf(binE.charAt(2)));
                        err12_d.setText(String.valueOf(binE.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexF:
                        err15_d.setText(String.valueOf(binF.charAt(0)));
                        err14_d.setText(String.valueOf(binF.charAt(1)));
                        err13_d.setText(String.valueOf(binF.charAt(2)));
                        err12_d.setText(String.valueOf(binF.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    default:

                }

                /*CHECKING FIFTH CHARACTER FROM RIGHT*/
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 6))))
                {
                    case hexZero:
                        err19_d.setText(String.valueOf(binZero.charAt(0)));
                        err18_d.setText(String.valueOf(binZero.charAt(1)));
                        err17_d.setText(String.valueOf(binZero.charAt(2)));
                        err16_d.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexOne:
                        err19_d.setText(String.valueOf(binOne.charAt(0)));
                        err18_d.setText(String.valueOf(binOne.charAt(1)));
                        err17_d.setText(String.valueOf(binOne.charAt(2)));
                        err16_d.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexTwo:
                        err19_d.setText(String.valueOf(binTwo.charAt(0)));
                        err18_d.setText(String.valueOf(binTwo.charAt(1)));
                        err17_d.setText(String.valueOf(binTwo.charAt(2)));
                        err16_d.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexThree:
                        err19_d.setText(String.valueOf(binThree.charAt(0)));
                        err18_d.setText(String.valueOf(binThree.charAt(1)));
                        err17_d.setText(String.valueOf(binThree.charAt(2)));
                        err16_d.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFour:
                        err19_d.setText(String.valueOf(binFour.charAt(0)));
                        err18_d.setText(String.valueOf(binFour.charAt(1)));
                        err17_d.setText(String.valueOf(binFour.charAt(2)));
                        err16_d.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexFive:
                        err19_d.setText(String.valueOf(binFive.charAt(0)));
                        err18_d.setText(String.valueOf(binFive.charAt(1)));
                        err17_d.setText(String.valueOf(binFive.charAt(2)));
                        err16_d.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSix:
                        err19_d.setText(String.valueOf(binSix.charAt(0)));
                        err18_d.setText(String.valueOf(binSix.charAt(1)));
                        err17_d.setText(String.valueOf(binSix.charAt(2)));
                        err16_d.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexSeven:
                        err19_d.setText(String.valueOf(binSeven.charAt(0)));
                        err18_d.setText(String.valueOf(binSeven.charAt(1)));
                        err17_d.setText(String.valueOf(binSeven.charAt(2)));
                        err16_d.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexEight:
                        err19_d.setText(String.valueOf(binEight.charAt(0)));
                        err18_d.setText(String.valueOf(binEight.charAt(1)));
                        err17_d.setText(String.valueOf(binEight.charAt(2)));
                        err16_d.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexNine:
                        err19_d.setText(String.valueOf(binNine.charAt(0)));
                        err18_d.setText(String.valueOf(binNine.charAt(1)));
                        err17_d.setText(String.valueOf(binNine.charAt(2)));
                        err16_d.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexA:
                        err19_d.setText(String.valueOf(binA.charAt(0)));
                        err18_d.setText(String.valueOf(binA.charAt(1)));
                        err17_d.setText(String.valueOf(binA.charAt(2)));
                        err16_d.setText(String.valueOf(binA.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexB:
                        err19_d.setText(String.valueOf(binB.charAt(0)));
                        err18_d.setText(String.valueOf(binB.charAt(1)));
                        err17_d.setText(String.valueOf(binB.charAt(2)));
                        err16_d.setText(String.valueOf(binB.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexC:
                        err19_d.setText(String.valueOf(binC.charAt(0)));
                        err18_d.setText(String.valueOf(binC.charAt(1)));
                        err17_d.setText(String.valueOf(binC.charAt(2)));
                        err16_d.setText(String.valueOf(binC.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexD:
                        err19_d.setText(String.valueOf(binD.charAt(0)));
                        err18_d.setText(String.valueOf(binD.charAt(1)));
                        err17_d.setText(String.valueOf(binD.charAt(2)));
                        err16_d.setText(String.valueOf(binD.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexE:
                        err19_d.setText(String.valueOf(binE.charAt(0)));
                        err18_d.setText(String.valueOf(binE.charAt(1)));
                        err17_d.setText(String.valueOf(binE.charAt(2)));
                        err16_d.setText(String.valueOf(binE.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexF:
                        err19_d.setText(String.valueOf(binF.charAt(0)));
                        err18_d.setText(String.valueOf(binF.charAt(1)));
                        err17_d.setText(String.valueOf(binF.charAt(2)));
                        err16_d.setText(String.valueOf(binF.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    default:

                }
            }
        }
        catch(NullPointerException npe)
        {
            toast(npe.toString());
        }
    }//end of liftErrorStatusData()

    public void carCallStatus()
    {
        title.setText(listOfTitles[7]);
        makeThemVisible();
        for(int i=0; i<floorKeysZeroToFifteen.length; i++)
        {
            tableRow2Column7.setText(floorKeysZeroToFifteen[0]);
            tableRow2Column6.setText(floorKeysZeroToFifteen[1]);
            tableRow2Column5.setText(floorKeysZeroToFifteen[2]);
            tableRow2Column4.setText(floorKeysZeroToFifteen[3]);
            tableRow2Column3.setText(floorKeysZeroToFifteen[4]);
            tableRow2Column2.setText(floorKeysZeroToFifteen[5]);
            tableRow2Column1.setText(floorKeysZeroToFifteen[6]);
            tableRow2Column0.setText(floorKeysZeroToFifteen[7]);

            tableRow0Column7.setText(floorKeysZeroToFifteen[8]);
            tableRow0Column6.setText(floorKeysZeroToFifteen[9]);
            tableRow0Column5.setText(floorKeysZeroToFifteen[10]);
            tableRow0Column4.setText(floorKeysZeroToFifteen[11]);
            tableRow0Column3.setText(floorKeysZeroToFifteen[12]);
            tableRow0Column2.setText(floorKeysZeroToFifteen[13]);
            tableRow0Column1.setText(floorKeysZeroToFifteen[14]);
            tableRow0Column0.setText(floorKeysZeroToFifteen[15]);
        }
        for(int i=0; i<floorKeysSixteenAbove.length; i++)
        {
            tableRow6Column7.setText(floorKeysSixteenAbove[0]);
            tableRow6Column6.setText(floorKeysSixteenAbove[1]);
            tableRow6Column5.setText(floorKeysSixteenAbove[2]);
            tableRow6Column4.setText(floorKeysSixteenAbove[3]);
            tableRow6Column3.setText(floorKeysSixteenAbove[4]);
            tableRow6Column2.setText(floorKeysSixteenAbove[5]);
            tableRow6Column1.setText(floorKeysSixteenAbove[6]);
            tableRow6Column0.setText(floorKeysSixteenAbove[7]);

            tableRow4Column7.setText(floorKeysSixteenAbove[8]);
            tableRow4Column6.setText(floorKeysSixteenAbove[9]);
            tableRow4Column5.setText(floorKeysSixteenAbove[10]);
            tableRow4Column4.setText(floorKeysSixteenAbove[11]);
            tableRow4Column3.setText(floorKeysSixteenAbove[12]);
            tableRow4Column2.setText(floorKeysSixteenAbove[13]);
            tableRow4Column1.setText(floorKeysSixteenAbove[14]);
            tableRow4Column0.setText(floorKeysSixteenAbove[15]);
        }
    }//end of carCallStatus()

    public void setColorsForLiftErrorStatus()
    {
        err0_d.setTextColor(Color.WHITE);
        err1_d.setTextColor(Color.WHITE);
        err2_d.setTextColor(Color.WHITE);
        err3_d.setTextColor(Color.WHITE);
        err4_d.setTextColor(Color.WHITE);
        err5_d.setTextColor(Color.WHITE);
        err6_d.setTextColor(Color.WHITE);
        err7_d.setTextColor(Color.WHITE);
        err8_d.setTextColor(Color.WHITE);
        err9_d.setTextColor(Color.WHITE);
        err10_d.setTextColor(Color.WHITE);
        err11_d.setTextColor(Color.WHITE);
        err12_d.setTextColor(Color.WHITE);
        err13_d.setTextColor(Color.WHITE);
        err14_d.setTextColor(Color.WHITE);
        err15_d.setTextColor(Color.WHITE);
        err16_d.setTextColor(Color.WHITE);
        err17_d.setTextColor(Color.WHITE);
        err18_d.setTextColor(Color.WHITE);
        err19_d.setTextColor(Color.WHITE);

        if(err19_d.getText().equals(inactive))
        {
            err19_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err19_d.setBackgroundColor(Color.RED);
        }

        if(err18_d.getText().equals(inactive))
        {
            err18_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err18_d.setBackgroundColor(Color.RED);
        }

        if(err17_d.getText().equals(inactive))
        {
            err17_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err17_d.setBackgroundColor(Color.RED);
        }

        if(err16_d.getText().equals(inactive))
        {
            err16_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err16_d.setBackgroundColor(Color.RED);
        }

        if(err15_d.getText().equals(inactive))
        {
            err15_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err15_d.setBackgroundColor(Color.RED);
        }

        if(err14_d.getText().equals(inactive))
        {
            err14_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err14_d.setBackgroundColor(Color.RED);
        }

        if(err13_d.getText().equals(inactive))
        {
            err13_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err13_d.setBackgroundColor(Color.RED);
        }

        if(err12_d.getText().equals(inactive))
        {
            err12_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err12_d.setBackgroundColor(Color.RED);
        }


        if(err11_d.getText().equals(inactive))
        {
            err11_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err11_d.setBackgroundColor(Color.RED);
        }

        if(err10_d.getText().equals(inactive))
        {
            err10_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err10_d.setBackgroundColor(Color.RED);
        }

        if(err9_d.getText().equals(inactive))
        {
            err9_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err9_d.setBackgroundColor(Color.RED);
        }

        if(err8_d.getText().equals(inactive))
        {
            err8_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err8_d.setBackgroundColor(Color.RED);
        }


        if(err7_d.getText().equals(inactive))
        {
            err7_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err7_d.setBackgroundColor(Color.RED);
        }

        if(err6_d.getText().equals(inactive))
        {
            err6_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err6_d.setBackgroundColor(Color.RED);
        }

        if(err5_d.getText().equals(inactive))
        {
            err5_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err5_d.setBackgroundColor(Color.RED);
        }

        if(err4_d.getText().equals(inactive))
        {
            err4_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err4_d.setBackgroundColor(Color.RED);
        }

        if(err3_d.getText().equals(inactive))
        {
            err3_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err3_d.setBackgroundColor(Color.RED);
        }
        if(err2_d.getText().equals(inactive))
        {
            err2_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err2_d.setBackgroundColor(Color.RED);
        }
        if(err1_d.getText().equals(inactive))
        {
            err1_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err1_d.setBackgroundColor(Color.RED);
        }
        if(err0_d.getText().equals(inactive))
        {
            err0_d.setBackgroundColor(Color.GRAY);
        }
        else
        {
            err0_d.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForLiftErrorStatus()

    public void setStatus(String code)
    {
        String carCallCode[] = new String[1];
        carCallCode[0]=code;
        try
        {
            for(int s=0; s<carCallCode.length; s++)
            {
                /*CHECKING FIRST CHARACTER FROM RIGHT*/
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                    tableRow1Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                    tableRow1Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                    tableRow1Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                    tableRow1Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                    tableRow1Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                    tableRow1Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                    tableRow1Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                    tableRow1Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                    tableRow1Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                    tableRow1Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexA)) {
                    tableRow1Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexB)) {
                    tableRow1Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexC)) {
                    tableRow1Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexD)) {
                    tableRow1Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexE)) {
                    tableRow1Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 2))).equalsIgnoreCase(hexF)) {
                    tableRow1Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow1Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow1Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow1Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow1LowerNibble();
                }
                /*END OF CHECKING FIRST CHARACTER FROM RIGHT*/
                /*SECOND CHARACTER FROM RIGHT*/
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                    tableRow1Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                    tableRow1Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                    tableRow1Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                    tableRow1Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                    tableRow1Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                    tableRow1Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                    tableRow1Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                    tableRow1Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                    tableRow1Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                    tableRow1Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexA)) {
                    tableRow1Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexB)) {
                    tableRow1Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexC)) {
                    tableRow1Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexD)) {
                    tableRow1Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexE)) {
                    tableRow1Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 3))).equalsIgnoreCase(hexF)) {
                    tableRow1Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow1Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow1Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow1Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow1HigherNibble();
                }
                /*END OF SECOND CHARACTER FROM RIGHT*/
                /*THIRD CHARACTER FROM RIGHT*/
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                    tableRow3Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                    tableRow3Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                    tableRow3Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                    tableRow3Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                    tableRow3Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                    tableRow3Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                    tableRow3Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                    tableRow3Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                    tableRow3Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                    tableRow3Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexA)) {
                    tableRow3Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexB)) {
                    tableRow3Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexC)) {
                    tableRow3Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexD)) {
                    tableRow3Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexE)) {
                    tableRow3Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 4))).equalsIgnoreCase(hexF)) {
                    tableRow3Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow3Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow3Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow3Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow3LowerNibble();
                }
                /*END OF THIRD CHARACTER FROM RIGHT*/

                /*FOURTH CHARACTER FROM RIGHT*/
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexZero)) {
                    tableRow3Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexOne)) {
                    tableRow3Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexTwo)) {
                    tableRow3Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexThree)) {
                    tableRow3Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexFour)) {
                    tableRow3Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexFive)) {
                    tableRow3Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexSix)) {
                    tableRow3Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexSeven)) {
                    tableRow3Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexEight)) {
                    tableRow3Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexNine)) {
                    tableRow3Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexA)) {
                    tableRow3Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexB)) {
                    tableRow3Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexC)) {
                    tableRow3Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexD)) {
                    tableRow3Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexE)) {
                    tableRow3Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 5))).equalsIgnoreCase(hexF)) {
                    tableRow3Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow3Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow3Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow3Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow3HigherNibble();
                }
                /*END OF FOURTH CHARACTER FROM RIGHT*/
                /*CHECKING FIFTH CHARACTER FROM RIGHT*/
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexZero)) {
                    tableRow5Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexOne)) {
                    tableRow5Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexTwo)) {
                    tableRow5Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexThree)) {
                    tableRow5Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexFour)) {
                    tableRow5Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexFive)) {
                    tableRow5Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexSix)) {
                    tableRow5Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexSeven)) {
                    tableRow5Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexEight)) {
                    tableRow5Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexNine)) {
                    tableRow5Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexA)) {
                    tableRow5Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexB)) {
                    tableRow5Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexC)) {
                    tableRow5Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexD)) {
                    tableRow5Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexE)) {
                    tableRow5Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 6))).equalsIgnoreCase(hexF)) {
                    tableRow5Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow5Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow5Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow5Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow5LowerNibble();
                }
                /*END OF CHECKING FIFTH CHARACTER FROM RIGHT*/
                /*SIXTH CHARACTER FROM RIGHT*/
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexZero)) {
                    tableRow5Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexOne)) {
                    tableRow5Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexTwo)) {
                    tableRow5Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexThree)) {
                    tableRow5Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexFour)) {
                    tableRow5Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexFive)) {
                    tableRow5Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexSix)) {
                    tableRow5Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexSeven)) {
                    tableRow5Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexEight)) {
                    tableRow5Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexNine)) {
                    tableRow5Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexA)) {
                    tableRow5Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexB)) {
                    tableRow5Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexC)) {
                    tableRow5Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexD)) {
                    tableRow5Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexE)) {
                    tableRow5Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 7))).equalsIgnoreCase(hexF)) {
                    tableRow5Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow5Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow5Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow5Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow5HigherNibble();
                }
                /*END OF SIXTH CHARACTER FROM RIGHT*/

                /*SEVENTH CHARACTER FROM RIGHT*/
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexZero)) {
                    tableRow7Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexOne)) {
                    tableRow7Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexTwo)) {
                    tableRow7Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexThree)) {
                    tableRow7Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexFour)) {
                    tableRow7Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexFive)) {
                    tableRow7Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexSix)) {
                    tableRow7Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexSeven)) {
                    tableRow7Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexEight)) {
                    tableRow7Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexNine)) {
                    tableRow7Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexA)) {
                    tableRow7Column3.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexB)) {
                    tableRow7Column3.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexC)) {
                    tableRow7Column3.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexD)) {
                    tableRow7Column3.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexE)) {
                    tableRow7Column3.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 8))).equalsIgnoreCase(hexF)) {
                    tableRow7Column3.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow7Column2.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow7Column1.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow7Column0.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow7LowerNibble();
                }
                /*END OF SEVENTH CHARACTER FROM RIGHT*/

                /*EIGHTH CHARACTER FROM RIGHT*/
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexZero)) {
                    tableRow7Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexOne)) {
                    tableRow7Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexTwo)) {
                    tableRow7Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexThree)) {
                    tableRow7Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexFour)) {
                    tableRow7Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexFive)) {
                    tableRow7Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexSix)) {
                    tableRow7Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexSeven)) {
                    tableRow7Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexEight)) {
                    tableRow7Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexNine)) {
                    tableRow7Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexA)) {
                    tableRow7Column7.setText(String.valueOf(floorBinA.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinA.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinA.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinA.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexB)) {
                    tableRow7Column7.setText(String.valueOf(floorBinB.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinB.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinB.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinB.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexC)) {
                    tableRow7Column7.setText(String.valueOf(floorBinC.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinC.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinC.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinC.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexD)) {
                    tableRow7Column7.setText(String.valueOf(floorBinD.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinD.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinD.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinD.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexE)) {
                    tableRow7Column7.setText(String.valueOf(floorBinE.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinE.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinE.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinE.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                if (String.valueOf(carCallCode[s].charAt((carCallCode[s].length() - 9))).equalsIgnoreCase(hexF)) {
                    tableRow7Column7.setText(String.valueOf(floorBinF.charAt(0)));
                    tableRow7Column6.setText(String.valueOf(floorBinF.charAt(1)));
                    tableRow7Column5.setText(String.valueOf(floorBinF.charAt(2)));
                    tableRow7Column4.setText(String.valueOf(floorBinF.charAt(3)));
                    setColorsForTableRow7HigherNibble();
                }
                /*END OF EIGHTH CHARACTER FROM RIGHT*/
            }
        }
        catch(NullPointerException npe)
        {
            //toast(npe.toString());
        }
    }//end of setStatus()

    public void setTitleForTheFirstTwoRows()
    {
        tableRow2Column7.setText(floorKeysZeroToFifteen[0]);
        tableRow2Column6.setText(floorKeysZeroToFifteen[1]);
        tableRow2Column5.setText(floorKeysZeroToFifteen[2]);
        tableRow2Column4.setText(floorKeysZeroToFifteen[3]);
        tableRow2Column3.setText(floorKeysZeroToFifteen[4]);
        tableRow2Column2.setText(floorKeysZeroToFifteen[5]);
        tableRow2Column1.setText(floorKeysZeroToFifteen[6]);
        tableRow2Column0.setText(floorKeysZeroToFifteen[7]);

        tableRow0Column7.setText(floorKeysZeroToFifteen[8]);
        tableRow0Column6.setText(floorKeysZeroToFifteen[9]);
        tableRow0Column5.setText(floorKeysZeroToFifteen[10]);
        tableRow0Column4.setText(floorKeysZeroToFifteen[11]);
        tableRow0Column3.setText(floorKeysZeroToFifteen[12]);
        tableRow0Column2.setText(floorKeysZeroToFifteen[13]);
        tableRow0Column1.setText(floorKeysZeroToFifteen[14]);
        tableRow0Column0.setText(floorKeysZeroToFifteen[15]);
    }//end of setTitleForTheFirstTwoRows()

    public void setTitleForTheLastTwoRows()
    {
        tableRow6Column7.setText(floorKeysSixteenAbove[0]);
        tableRow6Column6.setText(floorKeysSixteenAbove[1]);
        tableRow6Column5.setText(floorKeysSixteenAbove[2]);
        tableRow6Column4.setText(floorKeysSixteenAbove[3]);
        tableRow6Column3.setText(floorKeysSixteenAbove[4]);
        tableRow6Column2.setText(floorKeysSixteenAbove[5]);
        tableRow6Column1.setText(floorKeysSixteenAbove[6]);
        tableRow6Column0.setText(floorKeysSixteenAbove[7]);

        tableRow4Column7.setText(floorKeysSixteenAbove[8]);
        tableRow4Column6.setText(floorKeysSixteenAbove[9]);
        tableRow4Column5.setText(floorKeysSixteenAbove[10]);
        tableRow4Column4.setText(floorKeysSixteenAbove[11]);
        tableRow4Column3.setText(floorKeysSixteenAbove[12]);
        tableRow4Column2.setText(floorKeysSixteenAbove[13]);
        tableRow4Column1.setText(floorKeysSixteenAbove[14]);
        tableRow4Column0.setText(floorKeysSixteenAbove[15]);
    }//setTitleForTheLastTwoRows()

    public void setColorsForTableRow1LowerNibble()
    {
        if(tableRow1Column0.getText().equals(inactive))
        {
            tableRow1Column0.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow1Column0.setBackgroundColor(Color.RED);
        }
        if(tableRow1Column1.getText().equals(inactive))
        {
            tableRow1Column1.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow1Column1.setBackgroundColor(Color.RED);
        }
        if(tableRow1Column2.getText().equals(inactive))
        {
            tableRow1Column2.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow1Column2.setBackgroundColor(Color.RED);
        }
        if(tableRow1Column3.getText().equals(inactive))
        {
            tableRow1Column3.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow1Column3.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow1LowerNibble()

    public void setColorsForTableRow1HigherNibble()
    {
        if(tableRow1Column4.getText().equals(inactive))
        {
            tableRow1Column4.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow1Column4.setBackgroundColor(Color.RED);
        }
        if(tableRow1Column5.getText().equals(inactive))
        {
            tableRow1Column5.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow1Column5.setBackgroundColor(Color.RED);
        }
        if(tableRow1Column6.getText().equals(inactive))
        {
            tableRow1Column6.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow1Column6.setBackgroundColor(Color.RED);
        }
        if(tableRow1Column7.getText().equals(inactive))
        {
            tableRow1Column7.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow1Column7.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow1HigherNibble()

    public void setColorsForTableRow3LowerNibble()
    {
        if(tableRow3Column0.getText().equals(inactive))
        {
            tableRow3Column0.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow3Column0.setBackgroundColor(Color.RED);
        }
        if(tableRow3Column1.getText().equals(inactive))
        {
            tableRow3Column1.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow3Column1.setBackgroundColor(Color.RED);
        }
        if(tableRow3Column2.getText().equals(inactive))
        {
            tableRow3Column2.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow3Column2.setBackgroundColor(Color.RED);
        }
        if(tableRow3Column3.getText().equals(inactive))
        {
            tableRow3Column3.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow3Column3.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow3LowerNibble()

    public void setColorsForTableRow3HigherNibble()
    {
        if(tableRow3Column4.getText().equals(inactive))
        {
            tableRow3Column4.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow3Column4.setBackgroundColor(Color.RED);
        }
        if(tableRow3Column5.getText().equals(inactive))
        {
            tableRow3Column5.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow3Column5.setBackgroundColor(Color.RED);
        }
        if(tableRow3Column6.getText().equals(inactive))
        {
            tableRow3Column6.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow3Column6.setBackgroundColor(Color.RED);
        }
        if(tableRow3Column7.getText().equals(inactive))
        {
            tableRow3Column7.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow3Column7.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow3HigherNibble()

    public void setColorsForTableRow5LowerNibble()
    {
        if(tableRow5Column0.getText().equals(inactive))
        {
            tableRow5Column0.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow5Column0.setBackgroundColor(Color.RED);
        }
        if(tableRow5Column1.getText().equals(inactive))
        {
            tableRow5Column1.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow5Column1.setBackgroundColor(Color.RED);
        }
        if(tableRow5Column2.getText().equals(inactive))
        {
            tableRow5Column2.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow5Column2.setBackgroundColor(Color.RED);
        }
        if(tableRow5Column3.getText().equals(inactive))
        {
            tableRow5Column3.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow5Column3.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow5LowerNibble()

    public void setColorsForTableRow5HigherNibble()
    {
        if(tableRow5Column4.getText().equals(inactive))
        {
            tableRow5Column4.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow5Column4.setBackgroundColor(Color.RED);
        }
        if(tableRow5Column5.getText().equals(inactive))
        {
            tableRow5Column5.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow5Column5.setBackgroundColor(Color.RED);
        }
        if(tableRow5Column6.getText().equals(inactive))
        {
            tableRow5Column6.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow5Column6.setBackgroundColor(Color.RED);
        }
        if(tableRow5Column7.getText().equals(inactive))
        {
            tableRow5Column7.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow5Column7.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow5HigherNibble()

    public void setColorsForTableRow7LowerNibble()
    {
        if(tableRow7Column0.getText().equals(inactive))
        {
            tableRow7Column0.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow7Column0.setBackgroundColor(Color.RED);
        }
        if(tableRow7Column1.getText().equals(inactive))
        {
            tableRow7Column1.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow7Column1.setBackgroundColor(Color.RED);
        }
        if(tableRow7Column2.getText().equals(inactive))
        {
            tableRow7Column2.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow7Column2.setBackgroundColor(Color.RED);
        }
        if(tableRow7Column3.getText().equals(inactive))
        {
            tableRow7Column3.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow7Column3.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow7LowerNibble()

    public void setColorsForTableRow7HigherNibble()
    {
        if(tableRow7Column4.getText().equals(inactive))
        {
            tableRow7Column4.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow7Column4.setBackgroundColor(Color.RED);
        }
        if(tableRow7Column5.getText().equals(inactive))
        {
            tableRow7Column5.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow7Column5.setBackgroundColor(Color.RED);
        }
        if(tableRow7Column6.getText().equals(inactive))
        {
            tableRow7Column6.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow7Column6.setBackgroundColor(Color.RED);
        }
        if(tableRow7Column7.getText().equals(inactive))
        {
            tableRow7Column7.setBackgroundColor(Color.GRAY);
        }
        else
        {
            tableRow7Column7.setBackgroundColor(Color.RED);
        }
    }

    public void makeThemInvisible()
    {
        tableRow4Column0.setVisibility(View.INVISIBLE);
        tableRow4Column1.setVisibility(View.INVISIBLE);
        tableRow4Column2.setVisibility(View.INVISIBLE);
        tableRow4Column3.setVisibility(View.INVISIBLE);
        tableRow4Column4.setVisibility(View.INVISIBLE);
        tableRow4Column5.setVisibility(View.INVISIBLE);
        tableRow4Column6.setVisibility(View.INVISIBLE);
        tableRow4Column7.setVisibility(View.INVISIBLE);

        tableRow5Column0.setVisibility(View.INVISIBLE);
        tableRow5Column1.setVisibility(View.INVISIBLE);
        tableRow5Column2.setVisibility(View.INVISIBLE);
        tableRow5Column3.setVisibility(View.INVISIBLE);
        tableRow5Column4.setVisibility(View.INVISIBLE);
        tableRow5Column5.setVisibility(View.INVISIBLE);
        tableRow5Column6.setVisibility(View.INVISIBLE);
        tableRow5Column7.setVisibility(View.INVISIBLE);

        tableRow6Column0.setVisibility(View.INVISIBLE);
        tableRow6Column1.setVisibility(View.INVISIBLE);
        tableRow6Column2.setVisibility(View.INVISIBLE);
        tableRow6Column3.setVisibility(View.INVISIBLE);
        tableRow6Column4.setVisibility(View.INVISIBLE);
        tableRow6Column5.setVisibility(View.INVISIBLE);
        tableRow6Column6.setVisibility(View.INVISIBLE);
        tableRow6Column7.setVisibility(View.INVISIBLE);

        tableRow7Column0.setVisibility(View.INVISIBLE);
        tableRow7Column1.setVisibility(View.INVISIBLE);
        tableRow7Column2.setVisibility(View.INVISIBLE);
        tableRow7Column3.setVisibility(View.INVISIBLE);
        tableRow7Column4.setVisibility(View.INVISIBLE);
        tableRow7Column5.setVisibility(View.INVISIBLE);
        tableRow7Column6.setVisibility(View.INVISIBLE);
        tableRow7Column7.setVisibility(View.INVISIBLE);
        err0.setVisibility(View.INVISIBLE);
        err1.setVisibility(View.INVISIBLE);
        err2.setVisibility(View.INVISIBLE);
        err3.setVisibility(View.INVISIBLE);
        err4.setVisibility(View.INVISIBLE);
        err5.setVisibility(View.INVISIBLE);
        err6.setVisibility(View.INVISIBLE);
        err7.setVisibility(View.INVISIBLE);
        err0_d.setVisibility(View.INVISIBLE);
        err1_d.setVisibility(View.INVISIBLE);
        err2_d.setVisibility(View.INVISIBLE);
        err3_d.setVisibility(View.INVISIBLE);
        err4_d.setVisibility(View.INVISIBLE);
        err5_d.setVisibility(View.INVISIBLE);
        err6_d.setVisibility(View.INVISIBLE);
        err7_d.setVisibility(View.INVISIBLE);

        err15.setVisibility(View.INVISIBLE);
        err14.setVisibility(View.INVISIBLE);
        err13.setVisibility(View.INVISIBLE);
        err12.setVisibility(View.INVISIBLE);
        err11.setVisibility(View.INVISIBLE);
        err10.setVisibility(View.INVISIBLE);
        err9.setVisibility(View.INVISIBLE);
        err8.setVisibility(View.INVISIBLE);
        err15_d.setVisibility(View.INVISIBLE);
        err14_d.setVisibility(View.INVISIBLE);
        err13_d.setVisibility(View.INVISIBLE);
        err12_d.setVisibility(View.INVISIBLE);
        err11_d.setVisibility(View.INVISIBLE);
        err10_d.setVisibility(View.INVISIBLE);
        err9_d.setVisibility(View.INVISIBLE);
        err8_d.setVisibility(View.INVISIBLE);

        err16_d.setVisibility(View.INVISIBLE);
        err17_d.setVisibility(View.INVISIBLE);
        err18_d.setVisibility(View.INVISIBLE);
        err19_d.setVisibility(View.INVISIBLE);

        err16.setVisibility(View.INVISIBLE);
        err17.setVisibility(View.INVISIBLE);
        err18.setVisibility(View.INVISIBLE);
        err19.setVisibility(View.INVISIBLE);
    }

    public void makeThemVisible()
    {
        tableRow4Column0.setVisibility(View.VISIBLE);
        tableRow4Column1.setVisibility(View.VISIBLE);
        tableRow4Column2.setVisibility(View.VISIBLE);
        tableRow4Column3.setVisibility(View.VISIBLE);
        tableRow4Column4.setVisibility(View.VISIBLE);
        tableRow4Column5.setVisibility(View.VISIBLE);
        tableRow4Column6.setVisibility(View.VISIBLE);
        tableRow4Column7.setVisibility(View.VISIBLE);

        tableRow5Column0.setVisibility(View.VISIBLE);
        tableRow5Column1.setVisibility(View.VISIBLE);
        tableRow5Column2.setVisibility(View.VISIBLE);
        tableRow5Column3.setVisibility(View.VISIBLE);
        tableRow5Column4.setVisibility(View.VISIBLE);
        tableRow5Column5.setVisibility(View.VISIBLE);
        tableRow5Column6.setVisibility(View.VISIBLE);
        tableRow5Column7.setVisibility(View.VISIBLE);

        tableRow6Column0.setVisibility(View.VISIBLE);
        tableRow6Column1.setVisibility(View.VISIBLE);
        tableRow6Column2.setVisibility(View.VISIBLE);
        tableRow6Column3.setVisibility(View.VISIBLE);
        tableRow6Column4.setVisibility(View.VISIBLE);
        tableRow6Column5.setVisibility(View.VISIBLE);
        tableRow6Column6.setVisibility(View.VISIBLE);
        tableRow6Column7.setVisibility(View.VISIBLE);

        tableRow7Column0.setVisibility(View.VISIBLE);
        tableRow7Column1.setVisibility(View.VISIBLE);
        tableRow7Column2.setVisibility(View.VISIBLE);
        tableRow7Column3.setVisibility(View.VISIBLE);
        tableRow7Column4.setVisibility(View.VISIBLE);
        tableRow7Column5.setVisibility(View.VISIBLE);
        tableRow7Column6.setVisibility(View.VISIBLE);
        tableRow7Column7.setVisibility(View.VISIBLE);
    }

    public void makeAllInvisible()
    {
        title.setVisibility(View.INVISIBLE);
        tableRow0Column0.setVisibility(View.INVISIBLE);
        tableRow0Column1.setVisibility(View.INVISIBLE);
        tableRow0Column2.setVisibility(View.INVISIBLE);
        tableRow0Column3.setVisibility(View.INVISIBLE);
        tableRow0Column4.setVisibility(View.INVISIBLE);
        tableRow0Column5.setVisibility(View.INVISIBLE);
        tableRow0Column6.setVisibility(View.INVISIBLE);
        tableRow0Column7.setVisibility(View.INVISIBLE);

        tableRow1Column0.setVisibility(View.INVISIBLE);
        tableRow1Column1.setVisibility(View.INVISIBLE);
        tableRow1Column2.setVisibility(View.INVISIBLE);
        tableRow1Column3.setVisibility(View.INVISIBLE);
        tableRow1Column4.setVisibility(View.INVISIBLE);
        tableRow1Column5.setVisibility(View.INVISIBLE);
        tableRow1Column6.setVisibility(View.INVISIBLE);
        tableRow1Column7.setVisibility(View.INVISIBLE);

        tableRow2Column0.setVisibility(View.INVISIBLE);
        tableRow2Column1.setVisibility(View.INVISIBLE);
        tableRow2Column2.setVisibility(View.INVISIBLE);
        tableRow2Column3.setVisibility(View.INVISIBLE);
        tableRow2Column4.setVisibility(View.INVISIBLE);
        tableRow2Column5.setVisibility(View.INVISIBLE);
        tableRow2Column6.setVisibility(View.INVISIBLE);
        tableRow2Column7.setVisibility(View.INVISIBLE);

        tableRow3Column0.setVisibility(View.INVISIBLE);
        tableRow3Column1.setVisibility(View.INVISIBLE);
        tableRow3Column2.setVisibility(View.INVISIBLE);
        tableRow3Column3.setVisibility(View.INVISIBLE);
        tableRow3Column4.setVisibility(View.INVISIBLE);
        tableRow3Column5.setVisibility(View.INVISIBLE);
        tableRow3Column6.setVisibility(View.INVISIBLE);
        tableRow3Column7.setVisibility(View.INVISIBLE);

        tableRow4Column0.setVisibility(View.INVISIBLE);
        tableRow4Column1.setVisibility(View.INVISIBLE);
        tableRow4Column2.setVisibility(View.INVISIBLE);
        tableRow4Column3.setVisibility(View.INVISIBLE);
        tableRow4Column4.setVisibility(View.INVISIBLE);
        tableRow4Column5.setVisibility(View.INVISIBLE);
        tableRow4Column6.setVisibility(View.INVISIBLE);
        tableRow4Column7.setVisibility(View.INVISIBLE);

        tableRow5Column0.setVisibility(View.INVISIBLE);
        tableRow5Column1.setVisibility(View.INVISIBLE);
        tableRow5Column2.setVisibility(View.INVISIBLE);
        tableRow5Column3.setVisibility(View.INVISIBLE);
        tableRow5Column4.setVisibility(View.INVISIBLE);
        tableRow5Column5.setVisibility(View.INVISIBLE);
        tableRow5Column6.setVisibility(View.INVISIBLE);
        tableRow5Column7.setVisibility(View.INVISIBLE);

        tableRow6Column0.setVisibility(View.INVISIBLE);
        tableRow6Column1.setVisibility(View.INVISIBLE);
        tableRow6Column2.setVisibility(View.INVISIBLE);
        tableRow6Column3.setVisibility(View.INVISIBLE);
        tableRow6Column4.setVisibility(View.INVISIBLE);
        tableRow6Column5.setVisibility(View.INVISIBLE);
        tableRow6Column6.setVisibility(View.INVISIBLE);
        tableRow6Column7.setVisibility(View.INVISIBLE);

        tableRow7Column0.setVisibility(View.INVISIBLE);
        tableRow7Column1.setVisibility(View.INVISIBLE);
        tableRow7Column2.setVisibility(View.INVISIBLE);
        tableRow7Column3.setVisibility(View.INVISIBLE);
        tableRow7Column4.setVisibility(View.INVISIBLE);
        tableRow7Column5.setVisibility(View.INVISIBLE);
        tableRow7Column6.setVisibility(View.INVISIBLE);
        tableRow7Column7.setVisibility(View.INVISIBLE);

        err0.setVisibility(View.INVISIBLE);
        err1.setVisibility(View.INVISIBLE);
        err2.setVisibility(View.INVISIBLE);
        err3.setVisibility(View.INVISIBLE);
        err4.setVisibility(View.INVISIBLE);
        err5.setVisibility(View.INVISIBLE);
        err6.setVisibility(View.INVISIBLE);
        err7.setVisibility(View.INVISIBLE);
        err0_d.setVisibility(View.INVISIBLE);
        err1_d.setVisibility(View.INVISIBLE);
        err2_d.setVisibility(View.INVISIBLE);
        err3_d.setVisibility(View.INVISIBLE);
        err4_d.setVisibility(View.INVISIBLE);
        err5_d.setVisibility(View.INVISIBLE);
        err6_d.setVisibility(View.INVISIBLE);
        err7_d.setVisibility(View.INVISIBLE);

        err15.setVisibility(View.INVISIBLE);
        err14.setVisibility(View.INVISIBLE);
        err13.setVisibility(View.INVISIBLE);
        err12.setVisibility(View.INVISIBLE);
        err11.setVisibility(View.INVISIBLE);
        err10.setVisibility(View.INVISIBLE);
        err9.setVisibility(View.INVISIBLE);
        err8.setVisibility(View.INVISIBLE);
        err15_d.setVisibility(View.INVISIBLE);
        err14_d.setVisibility(View.INVISIBLE);
        err13_d.setVisibility(View.INVISIBLE);
        err12_d.setVisibility(View.INVISIBLE);
        err11_d.setVisibility(View.INVISIBLE);
        err10_d.setVisibility(View.INVISIBLE);
        err9_d.setVisibility(View.INVISIBLE);
        err8_d.setVisibility(View.INVISIBLE);

        err16_d.setVisibility(View.INVISIBLE);
        err17_d.setVisibility(View.INVISIBLE);
        err18_d.setVisibility(View.INVISIBLE);
        err19_d.setVisibility(View.INVISIBLE);

        err16.setVisibility(View.INVISIBLE);
        err17.setVisibility(View.INVISIBLE);
        err18.setVisibility(View.INVISIBLE);
        err19.setVisibility(View.INVISIBLE);
    }

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast
}
