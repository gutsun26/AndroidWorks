package com.web.g_smsapp.customer;

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
import com.web.g_smsapp.customer_db.CustomerControllerDB;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomerInformationImmediateActivity extends AppCompatActivity
{
    /******************************VIEW*****************************************/
    Calendar calendar;
    SimpleDateFormat dateFormat;
    String date;
    String inactive     = "I";
    String active       = "A";
    Spinner dateChoice,clientChoice,binaryInfo;
    String dateChosen, clientChosen, contentToBeSplit, content1, content0;
    String theNumber;
    List<String> theClient, theDate,theContent;
    ArrayAdapter<String> clientDataAdapter, dateAdapter, contentAdapter;
    Button showButton;
    TextView content;
    TableLayout table;
    TextView    title;
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
    TextView    textCharacter_I,    textCharacter_A, textCharacter_C, timeStamp, textDash;
    String mainCardInput[]              = {"---","---","---","---","---","---","---","---","SLWR","STPR","ARD","OPC","BRK","MT","MUP","MDN"};
    String mainCardTerminalInput[]      = {"---","---","---","---","---","LGS","CGS","PS", "---", "---", "UTL", "DTL", "FUP", "FDN", "FUP1", "FDN1"};
    String mainCardOutput[]             = {"---","---", "DN","UP","S2","S1","DC","---","RKM","RCAM","ARD", "DIR_RD","PRE","EX1","DBK","BRK"};
    String cbcInputs[]                  = {"---","---","---","ACR","IFST","DOLT","DCLT","DCPB","DOPB","LC","ULD","FLD","OVLD","NS","ATT","EMR STOP"};
    String cbcOutputs[]                 = {"---","---","EX2","WELCOME","THY","DOR","DCR","DOBR","---","---","LIGHT","FAN","DOF","DCF","DOBF","GONG"};
    String floorKeysZeroToFifteen[]     = {"15","14","13","12","11","10","9","8","7","6","5","4","3","2","1","0"};
    String floorKeysSixteenAbove[]      = {"31","30","29","28","27","26","25","24","23","22","21","20","19","18","17","16"};
    String carKeys[]                    = {};
    String liftStatus[]                 = {};
    String liftErrorStauts[]            = {};
    String liftEmerStop[]               = {};
    String liftEmerStopStatusRead[]     = {};
    String liftMaxFloor[]               = {};
    String startMonitoring[]            = {};
    String listOfTitles[]               = { "MAIN CARD INPUT", "MAIN CARD TERMINAL INPUT", "MAIN CARD OUTPUT","CBC INPUTS",
                                            "CBC OUTPUTS", "FLOOR UP KEYS","FLOOR DOWN KEYS","CAR KEYS","LIFT STATUS","LIFT ERROR STATUS",
                                            "LIFT EMR STOP STATUS READ","LIFT MAX FLOOR","START MONITORING", "LIFT EMR STOP"
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

    String	binZero		=	"IIII"	;
    String	binOne		=	"IIIA"	;
    String	binTwo		=	"IIAI"	;
    String	binThree	=	"IIAA"	;
    String	binFour		=	"IAII"	;
    String	binFive		=	"IAIA"	;
    String	binSix		=	"IAAI"	;
    String	binSeven	=	"IAAA"	;
    String	binEight	=	"AIII"	;
    String	binNine		=	"AIIA"	;
    String	binA		=	"AIAI"	;
    String	binB		=	"AIAA"	;
    String	binC		=	"AAII"	;
    String	binD		=	"AAIA"	;
    String	binE		=	"AAAI"	;
    String	binF		=	"AAAA"	;
    String theMessage=null;

    /*******************************CONTROLLER**********************************/
    /*DATABASE API*/
    com.web.g_smsapp.customer_db.CustomerSMSReceiverControllerDB customerSMSReceiverControllerDB;
    SQLiteDatabase theSqLiteDatabase;
    Cursor dateCursor, clientCursor;
    CustomerControllerDB customerControllerDB;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_customer_information_immediate);

        title       =       (TextView)findViewById(R.id.title);
        table       =       (TableLayout)findViewById(R.id.table);
        content     =       (TextView)findViewById(R.id.content);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/ HH:mm:ss a");
        date = dateFormat.format(calendar.getTime());
        toast(date);
        textCharacter_I         =       (TextView)      findViewById(R.id.textCharacter_I);
        textCharacter_A         =       (TextView)      findViewById(R.id.textCharacter_A);
        textCharacter_C         =       (TextView)      findViewById(R.id.textCharacter_C);
        timeStamp               =       (TextView)      findViewById(R.id.timeStamp);
        textDash                =       (TextView)      findViewById(R.id.textDash);

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
        textCharacter_I.setBackgroundColor(Color.RED);
        textCharacter_A.setBackgroundColor(Color.GREEN);
        textCharacter_C.setBackgroundColor(Color.RED);
        timeStamp.setBackgroundColor(Color.RED);
        textDash.setBackgroundColor(Color.GRAY);
        timeStamp.setText(date);
        makeThemInvisible();

        String stringSplitCode[] = theMessage.split("%");
        String stringSplit[] = theMessage.split("\n");
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
        }
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

        }

    }//end of onCreate()
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
    }
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
    }
    public void setColorsForTableRow1LowerNibble()
    {
        if(tableRow1Column0.getText().equals(inactive))
        {
            tableRow1Column0.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow1Column0.setBackgroundColor(Color.GREEN);
        }
        if(tableRow1Column1.getText().equals(inactive))
        {
            tableRow1Column1.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow1Column1.setBackgroundColor(Color.GREEN);
        }
        if(tableRow1Column2.getText().equals(inactive))
        {
            tableRow1Column2.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow1Column2.setBackgroundColor(Color.GREEN);
        }
        if(tableRow1Column3.getText().equals(inactive))
        {
            tableRow1Column3.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow1Column3.setBackgroundColor(Color.GREEN);
        }
    }//end of setColorsForTableRow1LowerNibble()

    public void setColorsForTableRow1HigherNibble()
    {
        if(tableRow1Column4.getText().equals(inactive))
        {
            tableRow1Column4.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow1Column4.setBackgroundColor(Color.GREEN);
        }
        if(tableRow1Column5.getText().equals(inactive))
        {
            tableRow1Column5.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow1Column5.setBackgroundColor(Color.GREEN);
        }
        if(tableRow1Column6.getText().equals(inactive))
        {
            tableRow1Column6.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow1Column6.setBackgroundColor(Color.GREEN);
        }
        if(tableRow1Column7.getText().equals(inactive))
        {
            tableRow1Column7.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow1Column7.setBackgroundColor(Color.GREEN);
        }
    }//end of setColorsForTableRow1HigherNibble()

    public void setColorsForTableRow3LowerNibble()
    {
        if(tableRow3Column0.getText().equals(inactive))
        {
            tableRow3Column0.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow3Column0.setBackgroundColor(Color.GREEN);
        }
        if(tableRow3Column1.getText().equals(inactive))
        {
            tableRow3Column1.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow3Column1.setBackgroundColor(Color.GREEN);
        }
        if(tableRow3Column2.getText().equals(inactive))
        {
            tableRow3Column2.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow3Column2.setBackgroundColor(Color.GREEN);
        }
        if(tableRow3Column3.getText().equals(inactive))
        {
            tableRow3Column3.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow3Column3.setBackgroundColor(Color.GREEN);
        }
    }//end of setColorsForTableRow3LowerNibble()

    public void setColorsForTableRow3HigherNibble()
    {
        if(tableRow3Column4.getText().equals(inactive))
        {
            tableRow3Column4.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow3Column4.setBackgroundColor(Color.GREEN);
        }
        if(tableRow3Column5.getText().equals(inactive))
        {
            tableRow3Column5.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow3Column5.setBackgroundColor(Color.GREEN);
        }
        if(tableRow3Column6.getText().equals(inactive))
        {
            tableRow3Column6.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow3Column6.setBackgroundColor(Color.GREEN);
        }
        if(tableRow3Column7.getText().equals(inactive))
        {
            tableRow3Column7.setBackgroundColor(Color.RED);
        }
        else
        {
            tableRow3Column7.setBackgroundColor(Color.GREEN);
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
    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast

}
