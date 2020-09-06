package com.web.g_smsapp.customer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.web.g_smsapp.R;
import com.web.g_smsapp.customer_db.CustomerControllerDB;
import com.web.g_smsapp.customer_db.CustomerSMSReceiverControllerDB;

import java.util.ArrayList;
import java.util.List;

public class CustomerInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    /******************************MODEL****************************************/
    final String  stringCMD7      = "#07%";
    final String  stringCMD8      = "#08%";
    final String  stringCMD9      = "#09%";
    final String  stringCMD10     = "#10%";
    final String  stringCMD11     = "#11%";
    final String  stringCMD12     = "#12%";
    final String  stringCMD13     = "#13%";
    final String  stringCMD14     = "#14%";
    final String  stringCMD15     = "#15%";
    final String  stringCMD16     = "#16%";
    final String  stringCMD17     = "#17%";
    final String  stringCMD18     = "#18%";
    final String  stringCMD19     = "#19%";
    final String  stringCMD20     = "#20%";
    final String command7         = "Main Card Input";
    final String command8         = "Main Card Terminal Input";
    final String command9         = "Main Card Output";
    final String command10        = "CBC Inputs";
    final String command11        = "CBC Outputs";
    final String command12_0      = "Floor UP KEY 00 to 15";
    final String command12_1      = "Floor UP KEY 16 to 31";
    final String command13_0      = "Floor DN KEY 00 to 15";
    final String command13_1      = "Floor DN KEY 16 to 31";
    final String command14_0      = "Car Keys 00 to 15";
    final String command14_1      = "Car Keys 16 to 31";
    final String command15        = "Lift Status";
    final String command16_0      = "Lift Error Status 00 to 15";
    final String command16_1      = "Lift Error Status 16 to 31";
    final String command17        = "Lift Emergency Stop";
    //final String command19        = "Lift Max Floor";
    //final String command20        = "Start Monitoring";

    String choice;
    String decodeChoice;


    /******************************VIEW*****************************************/
    String clickHere = "CLICK HERE";
    String inactive = "-";
    String active = "A";
    String call="C";
    Spinner dateChoice,clientChoice;
    String dateChosen, clientChosen;
    String theNumber;
    List<String> theClient, theDate,theContent;
    ArrayAdapter<String> clientDataAdapter, dateAdapter;
    Button showButton,showButton2,showButton3;
    TextView content,floorUpKeysTimeStamp,floorDownKeysTimeStamp;

    TableLayout tMainCardTerminalInput,         tMainCardInput;
    TableLayout tMainCardTerminalInputHBCols,   tMainCardTerminalInputHBData,
                tMainCardTerminalInputLBCols,   tMainCardTerminalInputLBData,
                tMainCardInputHBCols,           tMainCardInputHBData,
                tMainCardInputLBCols,           tMainCardInputLBData;
    TextView    tableRow1Column1,   tableRow1Column2,
                tableRow1Column3,   tableRow1Column4,
                tableRow1Column5,   tableRow1Column6,
                tableRow1Column7,   tableRow1Column0;
    TextView    tableRow2Column1,   tableRow2Column2,
                tableRow2Column3,   tableRow2Column4,
                tableRow2Column5,   tableRow2Column6,
                tableRow2Column7,   tableRow2Column0;
    TextView    tableRow3Column1,   tableRow3Column2,
                tableRow3Column3,   tableRow3Column4,
                tableRow3Column5,   tableRow3Column6,
                tableRow3Column7,   tableRow3Column0;
    TextView    tableRow4Column1,   tableRow4Column2,
                tableRow4Column3,   tableRow4Column4,
                tableRow4Column5,   tableRow4Column6,
                tableRow4Column7,   tableRow4Column0;
    TextView    tableRow5Column1,   tableRow5Column2,
                tableRow5Column3,   tableRow5Column4,
                tableRow5Column5,   tableRow5Column6,
                tableRow5Column7,   tableRow5Column0;
    TextView    tableRow6Column1,   tableRow6Column2,
                tableRow6Column3,   tableRow6Column4,
                tableRow6Column5,   tableRow6Column6,
                tableRow6Column7,   tableRow6Column0;
    TextView    tableRow7Column1,   tableRow7Column2,
                tableRow7Column3,   tableRow7Column4,
                tableRow7Column5,   tableRow7Column6,
                tableRow7Column7,   tableRow7Column0;
    TextView    tableRow8Column1,   tableRow8Column2,
                tableRow8Column3,   tableRow8Column4,
                tableRow8Column5,   tableRow8Column6,
                tableRow8Column7,   tableRow8Column0;
    TextView    tableRow9Column1,   tableRow9Column2,
                tableRow9Column3,   tableRow9Column4,
                tableRow9Column5,   tableRow9Column6,
                tableRow9Column7,   tableRow9Column0;
    TextView    tableRow10Column1,   tableRow10Column2,
                tableRow10Column3,   tableRow10Column4,
                tableRow10Column5,   tableRow10Column6,
                tableRow10Column7,   tableRow10Column0;
    TextView    tableRow11Column1,   tableRow11Column2,
                tableRow11Column3,   tableRow11Column4,
                tableRow11Column5,   tableRow11Column6,
                tableRow11Column7,   tableRow11Column0;
    TextView    tableRow12Column1,   tableRow12Column2,
                tableRow12Column3,   tableRow12Column4,
                tableRow12Column5,   tableRow12Column6,
                tableRow12Column7,   tableRow12Column0;
    TextView    tableRow13Column1,   tableRow13Column2,
                tableRow13Column3,   tableRow13Column4,
                tableRow13Column5,   tableRow13Column6,
                tableRow13Column7,   tableRow13Column0;
    TextView    tableRow14Column1,   tableRow14Column2,
                tableRow14Column3,   tableRow14Column4,
                tableRow14Column5,   tableRow14Column6,
                tableRow14Column7,   tableRow14Column0;
    TextView    tableRow15Column1,   tableRow15Column2,
                tableRow15Column3,   tableRow15Column4,
                tableRow15Column5,   tableRow15Column6,
                tableRow15Column7,   tableRow15Column0;
    TextView    tableRow16Column1,   tableRow16Column2,
                tableRow16Column3,   tableRow16Column4,
                tableRow16Column5,   tableRow16Column6,
                tableRow16Column7,   tableRow16Column0;
    TextView    tableRow17Column1,   tableRow17Column2,
                tableRow17Column3,   tableRow17Column4,
                tableRow17Column5,   tableRow17Column6,
                tableRow17Column7,   tableRow17Column0;
    TextView    tableRow18Column1,   tableRow18Column2,
                tableRow18Column3,   tableRow18Column4,
                tableRow18Column5,   tableRow18Column6,
                tableRow18Column7,   tableRow18Column0;
    TextView    tableRow19Column1,   tableRow19Column2,
                tableRow19Column3,   tableRow19Column4,
                tableRow19Column5,   tableRow19Column6,
                tableRow19Column7,   tableRow19Column0;
    TextView    tableRow20Column1,   tableRow20Column2,
                tableRow20Column3,   tableRow20Column4,
                tableRow20Column5,   tableRow20Column6,
                tableRow20Column7,   tableRow20Column0;
    TextView    tableRow21Column1,   tableRow21Column2,
                tableRow21Column3,   tableRow21Column4,
                tableRow21Column5,   tableRow21Column6,
                tableRow21Column7,   tableRow21Column0;
    TextView    tableRow22Column1,   tableRow22Column2,
                tableRow22Column3,   tableRow22Column4,
                tableRow22Column5,   tableRow22Column6,
                tableRow22Column7,   tableRow22Column0;
    TextView    tableRow23Column1,   tableRow23Column2,
                tableRow23Column3,   tableRow23Column4,
                tableRow23Column5,   tableRow23Column6,
                tableRow23Column7,   tableRow23Column0;
    TextView    tableRow24Column1,   tableRow24Column2,
                tableRow24Column3,   tableRow24Column4,
                tableRow24Column5,   tableRow24Column6,
                tableRow24Column7,   tableRow24Column0;
    TextView    tableRow25Column1,   tableRow25Column2,
                tableRow25Column3,   tableRow25Column4,
                tableRow25Column5,   tableRow25Column6,
                tableRow25Column7,   tableRow25Column0;
    TextView    tableRow26Column1,   tableRow26Column2,
                tableRow26Column3,   tableRow26Column4,
                tableRow26Column5,   tableRow26Column6,
                tableRow26Column7,   tableRow26Column0;
    TextView    textCharacter_I,    textCharacter_A, textCharacter_C, textDash, textCharacter_Cx, textDassh;
    TextView    err7,err6,err5,err4,err3,err2,err1,err0,updateStatus,
                err7_d,err6_d,err5_d,err4_d,err3_d,err2_d,err1_d,err0_d,
                err15,err14,err13,err12,err11,err10,err9,err8,
                err15_d,err14_d,err13_d,err12_d,err11_d,err10_d,err9_d,err8_d,
                err16,err17,err18,err19,err16_d,err17_d,err18_d,err19_d;
    TextView    liftStopStatus,liftStatus;
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

    /*******************************CONTROLLER**********************************/
    /*DATABASE API*/
    com.web.g_smsapp.customer_db.CustomerSMSReceiverControllerDB customerSMSReceiverControllerDB;
    SQLiteDatabase theSqLiteDatabase;
    Cursor dateCursor, clientCursor;
    CustomerControllerDB customerControllerDB;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);
        content                 =(TextView)     findViewById(R.id.content);
        floorUpKeysTimeStamp    =(TextView)     findViewById(R.id.floorUpKeysTimeStamp);
        floorDownKeysTimeStamp  =(TextView)     findViewById(R.id.floorDownKeysTimeStamp);
        liftStopStatus          =(TextView)     findViewById(R.id.liftStopStatus);
        liftStatus              =(TextView)     findViewById(R.id.liftStatus);
        dateChoice              =(Spinner)      findViewById(R.id.dateChoice);
        clientChoice            =(Spinner)      findViewById(R.id.clientChoice);
        showButton              =(Button)       findViewById(R.id.showButton);
        //showButton2             =(Button)       findViewById(R.id.showButton2);
        //showButton3             =(Button)       findViewById(R.id.showButton3);
        content                 =(TextView)     findViewById(R.id.content);
        textCharacter_I         =(TextView)     findViewById(R.id.textCharacter_I);
        textCharacter_A         =(TextView)     findViewById(R.id.textCharacter_A);
        textCharacter_C         =(TextView)     findViewById(R.id.textCharacter_C);
        textCharacter_Cx        =(TextView)     findViewById(R.id.textCharacter_Cx);
        textDash                =(TextView)     findViewById(R.id.textDash);
        textDassh               =(TextView)     findViewById(R.id.textDassh);
        updateStatus            =(TextView)     findViewById(R.id.updateStatus);

        tMainCardTerminalInput          =       (TableLayout)      findViewById(R.id.tMainCardTerminalInput);
        tMainCardInput                  =       (TableLayout)      findViewById(R.id.tMainCardInput);
        tMainCardTerminalInputHBCols    =       (TableLayout)      findViewById(R.id.tMainCardTerminalInputHBCols);
        tMainCardTerminalInputHBData    =       (TableLayout)      findViewById(R.id.tMainCardTerminalInputHBData);
        tMainCardTerminalInputLBCols    =       (TableLayout)      findViewById(R.id.tMainCardTerminalInputLBCols);
        tMainCardTerminalInputLBData    =       (TableLayout)      findViewById(R.id.tMainCardTerminalInputLBData);
        tMainCardInputHBCols            =       (TableLayout)      findViewById(R.id.tMainCardInputHBCols);
        tMainCardInputHBData            =       (TableLayout)      findViewById(R.id.tMainCardInputHBData);
        tMainCardInputLBCols            =       (TableLayout)      findViewById(R.id.tMainCardInputLBCols);
        tMainCardInputLBData            =       (TableLayout)      findViewById(R.id.tMainCardInputLBData);

        tableRow1Column0=(TextView)	findViewById(R.id.	tableRow1Column0);
        tableRow1Column1=(TextView)	findViewById(R.id.	tableRow1Column1);
        tableRow1Column2=(TextView)	findViewById(R.id.	tableRow1Column2);
        tableRow1Column3=(TextView)	findViewById(R.id.	tableRow1Column3);
        tableRow1Column4=(TextView)	findViewById(R.id.	tableRow1Column4);
        tableRow1Column5=(TextView)	findViewById(R.id.	tableRow1Column5);
        tableRow1Column6=(TextView)	findViewById(R.id.	tableRow1Column6);
        tableRow1Column7=(TextView)	findViewById(R.id.	tableRow1Column7);

        tableRow2Column0=(TextView)	findViewById(R.id.	tableRow2Column0);
        tableRow2Column1=(TextView)	findViewById(R.id.	tableRow2Column1);
        tableRow2Column2=(TextView)	findViewById(R.id.	tableRow2Column2);
        tableRow2Column3=(TextView)	findViewById(R.id.	tableRow2Column3);
        tableRow2Column4=(TextView)	findViewById(R.id.	tableRow2Column4);
        tableRow2Column5=(TextView)	findViewById(R.id.	tableRow2Column5);
        tableRow2Column6=(TextView)	findViewById(R.id.	tableRow2Column6);
        tableRow2Column7=(TextView)	findViewById(R.id.	tableRow2Column7);

        tableRow3Column0=(TextView)  findViewById(R.id.  tableRow3Column0);
        tableRow3Column1=(TextView)  findViewById(R.id.  tableRow3Column1);
        tableRow3Column2=(TextView)  findViewById(R.id.  tableRow3Column2);
        tableRow3Column3=(TextView)  findViewById(R.id.  tableRow3Column3);
        tableRow3Column4=(TextView)  findViewById(R.id.  tableRow3Column4);
        tableRow3Column5=(TextView)  findViewById(R.id.  tableRow3Column5);
        tableRow3Column6=(TextView)  findViewById(R.id.  tableRow3Column6);
        tableRow3Column7=(TextView)  findViewById(R.id.  tableRow3Column7);

        tableRow4Column0=(TextView)  findViewById(R.id.  tableRow4Column0);
        tableRow4Column1=(TextView)  findViewById(R.id.  tableRow4Column1);
        tableRow4Column2=(TextView)  findViewById(R.id.  tableRow4Column2);
        tableRow4Column3=(TextView)  findViewById(R.id.  tableRow4Column3);
        tableRow4Column4=(TextView)  findViewById(R.id.  tableRow4Column4);
        tableRow4Column5=(TextView)  findViewById(R.id.  tableRow4Column5);
        tableRow4Column6=(TextView)  findViewById(R.id.  tableRow4Column6);
        tableRow4Column7=(TextView)  findViewById(R.id.  tableRow4Column7);

        tableRow5Column0=(TextView)  findViewById(R.id.  tableRow5Column0);
        tableRow5Column1=(TextView)  findViewById(R.id.  tableRow5Column1);
        tableRow5Column2=(TextView)  findViewById(R.id.  tableRow5Column2);
        tableRow5Column3=(TextView)  findViewById(R.id.  tableRow5Column3);
        tableRow5Column4=(TextView)  findViewById(R.id.  tableRow5Column4);
        tableRow5Column5=(TextView)  findViewById(R.id.  tableRow5Column5);
        tableRow5Column6=(TextView)  findViewById(R.id.  tableRow5Column6);
        tableRow5Column7=(TextView)  findViewById(R.id.  tableRow5Column7);

        tableRow6Column0=(TextView)  findViewById(R.id.  tableRow6Column0);
        tableRow6Column1=(TextView)  findViewById(R.id.  tableRow6Column1);
        tableRow6Column2=(TextView)  findViewById(R.id.  tableRow6Column2);
        tableRow6Column3=(TextView)  findViewById(R.id.  tableRow6Column3);
        tableRow6Column4=(TextView)  findViewById(R.id.  tableRow6Column4);
        tableRow6Column5=(TextView)  findViewById(R.id.  tableRow6Column5);
        tableRow6Column6=(TextView)  findViewById(R.id.  tableRow6Column6);
        tableRow6Column7=(TextView)  findViewById(R.id.  tableRow6Column7);

        /*FOR CBC INPUTS*/
        tableRow7Column0=(TextView)findViewById(R.id.tableRow7Column0);
        tableRow7Column1=(TextView)findViewById(R.id.tableRow7Column1);
        tableRow7Column2=(TextView)findViewById(R.id.tableRow7Column2);
        tableRow7Column3=(TextView)findViewById(R.id.tableRow7Column3);
        tableRow7Column4=(TextView)findViewById(R.id.tableRow7Column4);
        tableRow7Column5=(TextView)findViewById(R.id.tableRow7Column5);
        tableRow7Column6=(TextView)findViewById(R.id.tableRow7Column6);
        tableRow7Column7=(TextView)findViewById(R.id.tableRow7Column7);

        tableRow8Column0=(TextView)findViewById(R.id.tableRow8Column0);
        tableRow8Column1=(TextView)findViewById(R.id.tableRow8Column1);
        tableRow8Column2=(TextView)findViewById(R.id.tableRow8Column2);
        tableRow8Column3=(TextView)findViewById(R.id.tableRow8Column3);
        tableRow8Column4=(TextView)findViewById(R.id.tableRow8Column4);
        tableRow8Column5=(TextView)findViewById(R.id.tableRow8Column5);
        tableRow8Column6=(TextView)findViewById(R.id.tableRow8Column6);
        tableRow8Column7=(TextView)findViewById(R.id.tableRow8Column7);

        /*FOR CBC OUTPUTS*/
        tableRow9Column0=(TextView)  findViewById(R.id.tableRow9Column0);
        tableRow9Column1=(TextView)  findViewById(R.id.tableRow9Column1);
        tableRow9Column2=(TextView)  findViewById(R.id.tableRow9Column2);
        tableRow9Column3=(TextView)  findViewById(R.id.tableRow9Column3);
        tableRow9Column4=(TextView)  findViewById(R.id.tableRow9Column4);
        tableRow9Column5=(TextView)  findViewById(R.id.tableRow9Column5);
        tableRow9Column6=(TextView)  findViewById(R.id.tableRow9Column6);
        tableRow9Column7=(TextView)  findViewById(R.id.tableRow9Column7);

        tableRow10Column0=(TextView)  findViewById(R.id.tableRow10Column0);
        tableRow10Column1=(TextView)  findViewById(R.id.tableRow10Column1);
        tableRow10Column2=(TextView)  findViewById(R.id.tableRow10Column2);
        tableRow10Column3=(TextView)  findViewById(R.id.tableRow10Column3);
        tableRow10Column4=(TextView)  findViewById(R.id.tableRow10Column4);
        tableRow10Column5=(TextView)  findViewById(R.id.tableRow10Column5);
        tableRow10Column6=(TextView)  findViewById(R.id.tableRow10Column6);
        tableRow10Column7=(TextView)  findViewById(R.id.tableRow10Column7);

        /*FOR UP KEYS*/
        tableRow11Column0=(TextView)  findViewById(R.id.tableRow11Column0);
        tableRow11Column1=(TextView)  findViewById(R.id.tableRow11Column1);
        tableRow11Column2=(TextView)  findViewById(R.id.tableRow11Column2);
        tableRow11Column3=(TextView)  findViewById(R.id.tableRow11Column3);
        tableRow11Column4=(TextView)  findViewById(R.id.tableRow11Column4);
        tableRow11Column5=(TextView)  findViewById(R.id.tableRow11Column5);
        tableRow11Column6=(TextView)  findViewById(R.id.tableRow11Column6);
        tableRow11Column7=(TextView)  findViewById(R.id.tableRow11Column7);

        tableRow12Column0=(TextView)  findViewById(R.id.tableRow12Column0);
        tableRow12Column1=(TextView)  findViewById(R.id.tableRow12Column1);
        tableRow12Column2=(TextView)  findViewById(R.id.tableRow12Column2);
        tableRow12Column3=(TextView)  findViewById(R.id.tableRow12Column3);
        tableRow12Column4=(TextView)  findViewById(R.id.tableRow12Column4);
        tableRow12Column5=(TextView)  findViewById(R.id.tableRow12Column5);
        tableRow12Column6=(TextView)  findViewById(R.id.tableRow12Column6);
        tableRow12Column7=(TextView)  findViewById(R.id.tableRow12Column7);

        tableRow13Column0=(TextView)findViewById(R.id.tableRow13Column0);
        tableRow13Column1=(TextView)findViewById(R.id.tableRow13Column1);
        tableRow13Column2=(TextView)findViewById(R.id.tableRow13Column2);
        tableRow13Column3=(TextView)findViewById(R.id.tableRow13Column3);
        tableRow13Column4=(TextView)findViewById(R.id.tableRow13Column4);
        tableRow13Column5=(TextView)findViewById(R.id.tableRow13Column5);
        tableRow13Column6=(TextView)findViewById(R.id.tableRow13Column6);
        tableRow13Column7=(TextView)findViewById(R.id.tableRow13Column7);

        tableRow14Column0=(TextView)findViewById(R.id.tableRow14Column0);
        tableRow14Column1=(TextView)findViewById(R.id.tableRow14Column1);
        tableRow14Column2=(TextView)findViewById(R.id.tableRow14Column2);
        tableRow14Column3=(TextView)findViewById(R.id.tableRow14Column3);
        tableRow14Column4=(TextView)findViewById(R.id.tableRow14Column4);
        tableRow14Column5=(TextView)findViewById(R.id.tableRow14Column5);
        tableRow14Column6=(TextView)findViewById(R.id.tableRow14Column6);
        tableRow14Column7=(TextView)findViewById(R.id.tableRow14Column7);

        /*FOR DOWN KEYS*/
        tableRow15Column0=(TextView)  findViewById(R.id.tableRow15Column0);
        tableRow15Column1=(TextView)  findViewById(R.id.tableRow15Column1);
        tableRow15Column2=(TextView)  findViewById(R.id.tableRow15Column2);
        tableRow15Column3=(TextView)  findViewById(R.id.tableRow15Column3);
        tableRow15Column4=(TextView)  findViewById(R.id.tableRow15Column4);
        tableRow15Column5=(TextView)  findViewById(R.id.tableRow15Column5);
        tableRow15Column6=(TextView)  findViewById(R.id.tableRow15Column6);
        tableRow15Column7=(TextView)  findViewById(R.id.tableRow15Column7);

        tableRow16Column0=(TextView)  findViewById(R.id.tableRow16Column0);
        tableRow16Column1=(TextView)  findViewById(R.id.tableRow16Column1);
        tableRow16Column2=(TextView)  findViewById(R.id.tableRow16Column2);
        tableRow16Column3=(TextView)  findViewById(R.id.tableRow16Column3);
        tableRow16Column4=(TextView)  findViewById(R.id.tableRow16Column4);
        tableRow16Column5=(TextView)  findViewById(R.id.tableRow16Column5);
        tableRow16Column6=(TextView)  findViewById(R.id.tableRow16Column6);
        tableRow16Column7=(TextView)  findViewById(R.id.tableRow16Column7);

        tableRow17Column0=(TextView)  findViewById(R.id.tableRow17Column0);
        tableRow17Column1=(TextView)  findViewById(R.id.tableRow17Column1);
        tableRow17Column2=(TextView)  findViewById(R.id.tableRow17Column2);
        tableRow17Column3=(TextView)  findViewById(R.id.tableRow17Column3);
        tableRow17Column4=(TextView)  findViewById(R.id.tableRow17Column4);
        tableRow17Column5=(TextView)  findViewById(R.id.tableRow17Column5);
        tableRow17Column6=(TextView)  findViewById(R.id.tableRow17Column6);
        tableRow17Column7=(TextView)  findViewById(R.id.tableRow17Column7);

        tableRow18Column0=(TextView)  findViewById(R.id.tableRow18Column0);
        tableRow18Column1=(TextView)  findViewById(R.id.tableRow18Column1);
        tableRow18Column2=(TextView)  findViewById(R.id.tableRow18Column2);
        tableRow18Column3=(TextView)  findViewById(R.id.tableRow18Column3);
        tableRow18Column4=(TextView)  findViewById(R.id.tableRow18Column4);
        tableRow18Column5=(TextView)  findViewById(R.id.tableRow18Column5);
        tableRow18Column6=(TextView)  findViewById(R.id.tableRow18Column6);
        tableRow18Column7=(TextView)  findViewById(R.id.tableRow18Column7);

        /*CAR CALLS*/
        tableRow19Column0=(TextView)  findViewById(R.id.tableRow19Column0);
        tableRow19Column1=(TextView)  findViewById(R.id.tableRow19Column1);
        tableRow19Column2=(TextView)  findViewById(R.id.tableRow19Column2);
        tableRow19Column3=(TextView)  findViewById(R.id.tableRow19Column3);
        tableRow19Column4=(TextView)  findViewById(R.id.tableRow19Column4);
        tableRow19Column5=(TextView)  findViewById(R.id.tableRow19Column5);
        tableRow19Column6=(TextView)  findViewById(R.id.tableRow19Column6);
        tableRow19Column7=(TextView)  findViewById(R.id.tableRow19Column7);

        tableRow20Column0=(TextView)  findViewById(R.id.tableRow20Column0);
        tableRow20Column1=(TextView)  findViewById(R.id.tableRow20Column1);
        tableRow20Column2=(TextView)  findViewById(R.id.tableRow20Column2);
        tableRow20Column3=(TextView)  findViewById(R.id.tableRow20Column3);
        tableRow20Column4=(TextView)  findViewById(R.id.tableRow20Column4);
        tableRow20Column5=(TextView)  findViewById(R.id.tableRow20Column5);
        tableRow20Column6=(TextView)  findViewById(R.id.tableRow20Column6);
        tableRow20Column7=(TextView)  findViewById(R.id.tableRow20Column7);

        tableRow21Column0=(TextView)  findViewById(R.id.tableRow21Column0);
        tableRow21Column1=(TextView)  findViewById(R.id.tableRow21Column1);
        tableRow21Column2=(TextView)  findViewById(R.id.tableRow21Column2);
        tableRow21Column3=(TextView)  findViewById(R.id.tableRow21Column3);
        tableRow21Column4=(TextView)  findViewById(R.id.tableRow21Column4);
        tableRow21Column5=(TextView)  findViewById(R.id.tableRow21Column5);
        tableRow21Column6=(TextView)  findViewById(R.id.tableRow21Column6);
        tableRow21Column7=(TextView)  findViewById(R.id.tableRow21Column7);

        tableRow22Column0=(TextView)  findViewById(R.id.tableRow22Column0);
        tableRow22Column1=(TextView)  findViewById(R.id.tableRow22Column1);
        tableRow22Column2=(TextView)  findViewById(R.id.tableRow22Column2);
        tableRow22Column3=(TextView)  findViewById(R.id.tableRow22Column3);
        tableRow22Column4=(TextView)  findViewById(R.id.tableRow22Column4);
        tableRow22Column5=(TextView)  findViewById(R.id.tableRow22Column5);
        tableRow22Column6=(TextView)  findViewById(R.id.tableRow22Column6);
        tableRow22Column7=(TextView)  findViewById(R.id.tableRow22Column7);
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

        textCharacter_I.setBackgroundColor(Color.GRAY);
        textCharacter_A.setBackgroundColor(Color.RED);
        textCharacter_C.setBackgroundColor(Color.RED);
        textDash.setBackgroundColor(Color.GRAY);
        textCharacter_Cx.setBackgroundColor(Color.RED);
        textDassh.setBackgroundColor(Color.GRAY);
        dateChoice.setBackgroundColor(Color.WHITE);
        clientChoice.setBackgroundColor(Color.WHITE);
        content.setVisibility(View.VISIBLE);
        //showButton2.setVisibility(View.INVISIBLE);
        //showButton3.setVisibility(View.INVISIBLE);
        customerSMSReceiverControllerDB = new CustomerSMSReceiverControllerDB(this);
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();

        customerControllerDB = new CustomerControllerDB(this);
        sqLiteDatabase= customerControllerDB.getReadableDatabase();
        /*EXTRACTING DATE AND SHOWING IT IN DROPDOWN*/
        theDate = new ArrayList<String>();
        dateCursor = theSqLiteDatabase.rawQuery("SELECT DISTINCT DATE FROM G_CUSTOMER_SMS",null);
        theDate.clear();
        theDate.add(clickHere);
        if (dateCursor.moveToFirst())
        {
            do
            {
                String timeStamp = dateCursor.getString(dateCursor.getColumnIndex("DATE"));
                //String[] splitDate = timeStamp.split("/ ");
                //toast(splitDate[0]);
                theDate.add(timeStamp);
            }while (dateCursor.moveToNext());
        }
        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theDate);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateChoice.setAdapter(dateAdapter);
        dateChoice.setOnItemSelectedListener(this);


        /*EXTRACTING CLIENT AND SHOWING IT IN THE DROPDOWN*/
        theClient = new ArrayList<String>();
        //Cursor cursor = theSqLiteDatabase.rawQuery("SELECT * FROM G_SMS",null);
        clientCursor = sqLiteDatabase.rawQuery("SELECT LOCATION FROM CUSTOMER_DEST_NUMBERS",null);
        theClient.clear();
        theClient.add(clickHere);
        if (clientCursor.moveToFirst())
        {
            do
            {
                theClient.add(clientCursor.getString(clientCursor.getColumnIndex("LOCATION")));
            }while (clientCursor.moveToNext());
        }
        clientDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theClient);
        // Drop down layout style - list view with radio button
        clientDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        clientChoice.setAdapter(clientDataAdapter);
        clientChoice.setOnItemSelectedListener(this);

        /*PROCESSING QUERY CHOSEN ABOVE*/
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllInformation();
            }
        });

        /*PROCESSING QUERY CHOSEN ABOVE*/
        /*
        showButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAllInformation2();
            }
        });

        showButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLiftErrorStatus();
            }
        });
         */

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
        }//end of for()

        for (int i = 0; i < liftErrorStatus_II.length; i++)
        {
            err19.setText(liftErrorStatus_II[0]);
            err18.setText(liftErrorStatus_II[1]);
            err17.setText(liftErrorStatus_II[2]);
            err16.setText(liftErrorStatus_II[3]);
        }//end of for()

    }//end of onCreate()

    @Override
    public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
    {
        clientChosen  = String.valueOf(clientChoice.getSelectedItem());
        dateChosen = String.valueOf(dateChoice.getSelectedItem());
        if(dateChosen.isEmpty() || clientChosen.isEmpty())
        {
            toast("PLEASE CHOOSE DATE AND CLIENT");
        }
    }//end of onItemSelected()
    public void onNothingSelected(AdapterView<?> parent)
    {

    }//end of onNothingSelected()

    public void showAllInformation()
    {
        sqLiteDatabase= customerControllerDB.getReadableDatabase();
        clientCursor = sqLiteDatabase.rawQuery("SELECT NUMBER FROM CUSTOMER_DEST_NUMBERS WHERE LOCATION='"+clientChosen+"'",null);
        if (clientCursor.moveToFirst())
        {
            do
            {
                theNumber=clientCursor.getString(clientCursor.getColumnIndex("NUMBER"));
            }while (clientCursor.moveToNext());
        }
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM G_CUSTOMER_SMS WHERE DATE='"+dateChosen+"' AND NUMBER='"+theNumber+"'",null);
        clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM G_CUSTOMER_SMS WHERE DATE='"+dateChosen+"'",null);
        theContent = new ArrayList<String>();
        theContent.clear();
        if (clientCursor.moveToFirst())
        {
            do
            {
                theContent.add(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
            }while (clientCursor.moveToNext());
        }
        int count = clientCursor.getCount();
        String contentArray[]=new String[count];
        for(int i=0; i<contentArray.length; i++)
        {
            contentArray[i] = theContent.get(i);
        }
        StringBuffer sb0 = new StringBuffer();
        for(int i = 0; i < contentArray.length; i++)
        {
            sb0.append(contentArray[i]);
        }
        String str1 = sb0.toString();
        //content.setText(str1);
        decodeChoice = str1;

        /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
        String stringSplit[] = decodeChoice.split("\n");
        String stringLiftErrorStat = stringSplit[0];
        for(int s=0; s<stringSplit.length; s++)
        {
            if(!String.valueOf(stringSplit[s]).isEmpty())
            {
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("4"))
                {
                    //showButton2.setVisibility(View.VISIBLE);
                    showAllInformation2();
                }
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("6"))
                {
                    //showButton3.setVisibility(View.VISIBLE);
                    showLiftErrorStatus();
                }

                /*FOR MAIN CARD INPUT*/
                if(String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("7"))
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
                }
                /*END OF MAIN CARD INPUT*/

                /*FOR MAIN CARD TERMINAL INPUT*/
                if(String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("8"))
                {
                    /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                    {
                        tableRow3Column3.setText(String.valueOf(binZero.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binZero.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binZero.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                    {
                        tableRow3Column3.setText(String.valueOf(binOne.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binOne.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binOne.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                    {
                        tableRow3Column3.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                    {
                        tableRow3Column3.setText(String.valueOf(binThree.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binThree.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binThree.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                    {
                        tableRow3Column3.setText(String.valueOf(binFour.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binFour.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binFour.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                    {
                        tableRow3Column4.setText(String.valueOf(binFive.charAt(0)));
                        tableRow3Column5.setText(String.valueOf(binFive.charAt(1)));
                        tableRow3Column6.setText(String.valueOf(binFive.charAt(2)));
                        tableRow3Column7.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                    {
                        tableRow3Column3.setText(String.valueOf(binSix.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binSix.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binSix.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                    {
                        tableRow3Column3.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                    {
                        tableRow3Column3.setText(String.valueOf(binEight.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binEight.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binEight.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                    {
                        tableRow3Column3.setText(String.valueOf(binNine.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binNine.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binNine.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                    {
                        tableRow3Column3.setText(String.valueOf(binA.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binA.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binA.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                    {
                        tableRow3Column3.setText(String.valueOf(binB.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binB.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binB.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                    {
                        tableRow3Column3.setText(String.valueOf(binC.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binC.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binC.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                    {
                        tableRow3Column3.setText(String.valueOf(binD.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binD.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binD.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                    {
                        tableRow3Column3.setText(String.valueOf(binE.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binE.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binE.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                    {
                        tableRow3Column3.setText(String.valueOf(binF.charAt(0)));
                        tableRow3Column2.setText(String.valueOf(binF.charAt(1)));
                        tableRow3Column1.setText(String.valueOf(binF.charAt(2)));
                        tableRow3Column0.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow3LowerNibble();
                    }
                    /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                    {
                        tableRow3Column7.setText(String.valueOf(binZero.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binZero.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binZero.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                    {
                        tableRow3Column7.setText(String.valueOf(binOne.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binOne.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binOne.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                    {
                        tableRow3Column7.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                    {
                        tableRow3Column7.setText(String.valueOf(binThree.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binThree.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binThree.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                    {
                        tableRow3Column7.setText(String.valueOf(binFour.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binFour.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binFour.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                    {
                        tableRow3Column7.setText(String.valueOf(binFive.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binFive.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binFive.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                    {
                        tableRow3Column7.setText(String.valueOf(binSix.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binSix.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binSix.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                    {
                        tableRow3Column7.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                    {
                        tableRow3Column7.setText(String.valueOf(binEight.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binEight.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binEight.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                    {
                        tableRow3Column7.setText(String.valueOf(binNine.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binNine.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binNine.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                    {
                        tableRow3Column7.setText(String.valueOf(binA.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binA.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binA.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                    {
                        tableRow3Column7.setText(String.valueOf(binB.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binB.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binB.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                    {
                        tableRow3Column7.setText(String.valueOf(binC.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binC.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binC.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                    {
                        tableRow3Column7.setText(String.valueOf(binD.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binD.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binD.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                    {
                        tableRow3Column7.setText(String.valueOf(binE.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binE.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binE.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                    {
                        tableRow3Column7.setText(String.valueOf(binF.charAt(0)));
                        tableRow3Column6.setText(String.valueOf(binF.charAt(1)));
                        tableRow3Column5.setText(String.valueOf(binF.charAt(2)));
                        tableRow3Column4.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow3HigherNibble();
                    }
                    /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/
                    /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexZero))
                    {
                        tableRow4Column3.setText(String.valueOf(binZero.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binZero.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binZero.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexOne))
                    {
                        tableRow4Column3.setText(String.valueOf(binOne.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binOne.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binOne.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexTwo))
                    {
                        tableRow4Column3.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexThree))
                    {
                        tableRow4Column3.setText(String.valueOf(binThree.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binThree.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binThree.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFour))
                    {
                        tableRow4Column3.setText(String.valueOf(binFour.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binFour.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binFour.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexFive))
                    {
                        tableRow4Column4.setText(String.valueOf(binFive.charAt(0)));
                        tableRow4Column5.setText(String.valueOf(binFive.charAt(1)));
                        tableRow4Column6.setText(String.valueOf(binFive.charAt(2)));
                        tableRow4Column7.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSix))
                    {
                        tableRow4Column3.setText(String.valueOf(binSix.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binSix.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binSix.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexSeven))
                    {
                        tableRow4Column3.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexEight))
                    {
                        tableRow4Column3.setText(String.valueOf(binEight.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binEight.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binEight.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexNine))
                    {
                        tableRow4Column3.setText(String.valueOf(binNine.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binNine.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binNine.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexA))
                    {
                        tableRow4Column3.setText(String.valueOf(binA.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binA.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binA.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexB))
                    {
                        tableRow4Column3.setText(String.valueOf(binB.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binB.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binB.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexC))
                    {
                        tableRow4Column3.setText(String.valueOf(binC.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binC.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binC.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexD))
                    {
                        tableRow4Column3.setText(String.valueOf(binD.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binD.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binD.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexE))
                    {
                        tableRow4Column3.setText(String.valueOf(binE.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binE.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binE.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-3))).equalsIgnoreCase(hexF))
                    {
                        tableRow4Column3.setText(String.valueOf(binF.charAt(0)));
                        tableRow4Column2.setText(String.valueOf(binF.charAt(1)));
                        tableRow4Column1.setText(String.valueOf(binF.charAt(2)));
                        tableRow4Column0.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow4LowerNibble();
                    }
                    /***************************** END OF BASED ON THIRD CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ****************************/

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexZero))
                    {
                        tableRow4Column7.setText(String.valueOf(binZero.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binZero.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binZero.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexOne))
                    {
                        tableRow4Column7.setText(String.valueOf(binOne.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binOne.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binOne.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexTwo))
                    {
                        tableRow4Column7.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexThree))
                    {
                        tableRow4Column7.setText(String.valueOf(binThree.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binThree.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binThree.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFour))
                    {
                        tableRow4Column7.setText(String.valueOf(binFour.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binFour.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binFour.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexFive))
                    {
                        tableRow4Column7.setText(String.valueOf(binFive.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binFive.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binFive.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSix))
                    {
                        tableRow4Column7.setText(String.valueOf(binSix.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binSix.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binSix.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexSeven))
                    {
                        tableRow4Column7.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexEight))
                    {
                        tableRow4Column7.setText(String.valueOf(binEight.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binEight.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binEight.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexNine))
                    {
                        tableRow4Column7.setText(String.valueOf(binNine.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binNine.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binNine.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexA))
                    {
                        tableRow4Column7.setText(String.valueOf(binA.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binA.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binA.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexB))
                    {
                        tableRow4Column7.setText(String.valueOf(binB.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binB.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binB.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexC))
                    {
                        tableRow4Column7.setText(String.valueOf(binC.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binC.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binC.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexD))
                    {
                        tableRow4Column7.setText(String.valueOf(binD.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binD.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binD.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexE))
                    {
                        tableRow4Column7.setText(String.valueOf(binE.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binE.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binE.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-4))).equalsIgnoreCase(hexF))
                    {
                        tableRow4Column7.setText(String.valueOf(binF.charAt(0)));
                        tableRow4Column6.setText(String.valueOf(binF.charAt(1)));
                        tableRow4Column5.setText(String.valueOf(binF.charAt(2)));
                        tableRow4Column4.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow4HigherNibble();
                    }
                }
                /*END OF MAIN CARD TERMINAL INPUT*/

                /*MAIN CARD OUTPUT*/
                if(String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("9"))
                {
                    /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                        tableRow5Column3.setText(String.valueOf(binZero.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binZero.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binZero.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                        tableRow5Column3.setText(String.valueOf(binOne.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binOne.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binOne.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo)) {
                        tableRow5Column3.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree)) {
                        tableRow5Column3.setText(String.valueOf(binThree.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binThree.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binThree.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                        tableRow5Column3.setText(String.valueOf(binFour.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binFour.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binFour.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive)) {
                        tableRow5Column4.setText(String.valueOf(binFive.charAt(0)));
                        tableRow5Column5.setText(String.valueOf(binFive.charAt(1)));
                        tableRow5Column6.setText(String.valueOf(binFive.charAt(2)));
                        tableRow5Column7.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix)) {
                        tableRow5Column3.setText(String.valueOf(binSix.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binSix.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binSix.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven)) {
                        tableRow5Column3.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight)) {
                        tableRow5Column3.setText(String.valueOf(binEight.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binEight.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binEight.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine)) {
                        tableRow5Column3.setText(String.valueOf(binNine.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binNine.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binNine.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA)) {
                        tableRow5Column3.setText(String.valueOf(binA.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binA.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binA.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB)) {
                        tableRow5Column3.setText(String.valueOf(binB.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binB.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binB.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC)) {
                        tableRow5Column3.setText(String.valueOf(binC.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binC.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binC.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD)) {
                        tableRow5Column3.setText(String.valueOf(binD.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binD.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binD.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE)) {
                        tableRow5Column3.setText(String.valueOf(binE.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binE.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binE.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF)) {
                        tableRow5Column3.setText(String.valueOf(binF.charAt(0)));
                        tableRow5Column2.setText(String.valueOf(binF.charAt(1)));
                        tableRow5Column1.setText(String.valueOf(binF.charAt(2)));
                        tableRow5Column0.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow5LowerNibble();
                    }
                    /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                        tableRow5Column7.setText(String.valueOf(binZero.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binZero.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binZero.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                        tableRow5Column7.setText(String.valueOf(binOne.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binOne.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binOne.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                        tableRow5Column7.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                        tableRow5Column7.setText(String.valueOf(binThree.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binThree.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binThree.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                        tableRow5Column7.setText(String.valueOf(binFour.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binFour.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binFour.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                        tableRow5Column7.setText(String.valueOf(binFive.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binFive.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binFive.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                        tableRow5Column7.setText(String.valueOf(binSix.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binSix.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binSix.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                        tableRow5Column7.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                        tableRow5Column7.setText(String.valueOf(binEight.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binEight.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binEight.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                        tableRow5Column7.setText(String.valueOf(binNine.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binNine.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binNine.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA)) {
                        tableRow5Column7.setText(String.valueOf(binA.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binA.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binA.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB)) {
                        tableRow5Column7.setText(String.valueOf(binB.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binB.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binB.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC)) {
                        tableRow5Column7.setText(String.valueOf(binC.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binC.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binC.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD)) {
                        tableRow5Column7.setText(String.valueOf(binD.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binD.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binD.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE)) {
                        tableRow5Column7.setText(String.valueOf(binE.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binE.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binE.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF)) {
                        tableRow5Column7.setText(String.valueOf(binF.charAt(0)));
                        tableRow5Column6.setText(String.valueOf(binF.charAt(1)));
                        tableRow5Column5.setText(String.valueOf(binF.charAt(2)));
                        tableRow5Column4.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow5HigherNibble();
                    }
                    /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/
                    /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                        tableRow6Column3.setText(String.valueOf(binZero.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binZero.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binZero.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                        tableRow6Column3.setText(String.valueOf(binOne.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binOne.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binOne.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                        tableRow6Column3.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                        tableRow6Column3.setText(String.valueOf(binThree.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binThree.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binThree.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                        tableRow6Column3.setText(String.valueOf(binFour.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binFour.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binFour.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                        tableRow6Column4.setText(String.valueOf(binFive.charAt(0)));
                        tableRow6Column5.setText(String.valueOf(binFive.charAt(1)));
                        tableRow6Column6.setText(String.valueOf(binFive.charAt(2)));
                        tableRow6Column7.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                        tableRow6Column3.setText(String.valueOf(binSix.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binSix.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binSix.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                        tableRow6Column3.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                        tableRow6Column3.setText(String.valueOf(binEight.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binEight.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binEight.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                        tableRow6Column3.setText(String.valueOf(binNine.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binNine.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binNine.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexA)) {
                        tableRow6Column3.setText(String.valueOf(binA.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binA.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binA.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexB)) {
                        tableRow6Column3.setText(String.valueOf(binB.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binB.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binB.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexC)) {
                        tableRow6Column3.setText(String.valueOf(binC.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binC.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binC.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexD)) {
                        tableRow6Column3.setText(String.valueOf(binD.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binD.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binD.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexE)) {
                        tableRow6Column3.setText(String.valueOf(binE.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binE.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binE.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexF)) {
                        tableRow6Column3.setText(String.valueOf(binF.charAt(0)));
                        tableRow6Column2.setText(String.valueOf(binF.charAt(1)));
                        tableRow6Column1.setText(String.valueOf(binF.charAt(2)));
                        tableRow6Column0.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow6LowerNibble();
                    }
                    /***************************** END OF BASED ON THIRD CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ****************************/

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                        tableRow6Column7.setText(String.valueOf(binZero.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binZero.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binZero.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                        tableRow6Column7.setText(String.valueOf(binOne.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binOne.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binOne.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                        tableRow6Column7.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                        tableRow6Column7.setText(String.valueOf(binThree.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binThree.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binThree.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                        tableRow6Column7.setText(String.valueOf(binFour.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binFour.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binFour.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                        tableRow6Column7.setText(String.valueOf(binFive.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binFive.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binFive.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                        tableRow6Column7.setText(String.valueOf(binSix.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binSix.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binSix.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                        tableRow6Column7.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                        tableRow6Column7.setText(String.valueOf(binEight.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binEight.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binEight.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                        tableRow6Column7.setText(String.valueOf(binNine.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binNine.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binNine.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexA)) {
                        tableRow6Column7.setText(String.valueOf(binA.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binA.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binA.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexB)) {
                        tableRow6Column7.setText(String.valueOf(binB.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binB.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binB.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexC)) {
                        tableRow6Column7.setText(String.valueOf(binC.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binC.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binC.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexD)) {
                        tableRow6Column7.setText(String.valueOf(binD.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binD.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binD.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexE)) {
                        tableRow6Column7.setText(String.valueOf(binE.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binE.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binE.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow6HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexF)) {
                        tableRow6Column7.setText(String.valueOf(binF.charAt(0)));
                        tableRow6Column6.setText(String.valueOf(binF.charAt(1)));
                        tableRow6Column5.setText(String.valueOf(binF.charAt(2)));
                        tableRow6Column4.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow6HigherNibble();

                    }
                    /*END OF MAIN CARD OUTPUT*/
                }

                /*CBC INPUTS*/
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("0"))
                {
                    /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                        tableRow7Column3.setText(String.valueOf(binZero.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binZero.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binZero.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                        tableRow7Column3.setText(String.valueOf(binOne.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binOne.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binOne.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo)) {
                        tableRow7Column3.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree)) {
                        tableRow7Column3.setText(String.valueOf(binThree.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binThree.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binThree.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                        tableRow7Column3.setText(String.valueOf(binFour.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binFour.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binFour.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive)) {
                        tableRow7Column4.setText(String.valueOf(binFive.charAt(0)));
                        tableRow7Column5.setText(String.valueOf(binFive.charAt(1)));
                        tableRow7Column6.setText(String.valueOf(binFive.charAt(2)));
                        tableRow7Column7.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix)) {
                        tableRow7Column3.setText(String.valueOf(binSix.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binSix.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binSix.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven)) {
                        tableRow7Column3.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight)) {
                        tableRow7Column3.setText(String.valueOf(binEight.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binEight.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binEight.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine)) {
                        tableRow7Column3.setText(String.valueOf(binNine.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binNine.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binNine.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA)) {
                        tableRow7Column3.setText(String.valueOf(binA.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binA.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binA.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB)) {
                        tableRow7Column3.setText(String.valueOf(binB.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binB.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binB.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC)) {
                        tableRow7Column3.setText(String.valueOf(binC.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binC.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binC.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD)) {
                        tableRow7Column3.setText(String.valueOf(binD.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binD.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binD.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE)) {
                        tableRow7Column3.setText(String.valueOf(binE.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binE.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binE.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF)) {
                        tableRow7Column3.setText(String.valueOf(binF.charAt(0)));
                        tableRow7Column2.setText(String.valueOf(binF.charAt(1)));
                        tableRow7Column1.setText(String.valueOf(binF.charAt(2)));
                        tableRow7Column0.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow7LowerNibble();
                    }
                    /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                        tableRow7Column7.setText(String.valueOf(binZero.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binZero.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binZero.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                        tableRow7Column7.setText(String.valueOf(binOne.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binOne.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binOne.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                        tableRow7Column7.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                        tableRow7Column7.setText(String.valueOf(binThree.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binThree.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binThree.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                        tableRow7Column7.setText(String.valueOf(binFour.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binFour.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binFour.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                        tableRow7Column7.setText(String.valueOf(binFive.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binFive.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binFive.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                        tableRow7Column7.setText(String.valueOf(binSix.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binSix.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binSix.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                        tableRow7Column7.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                        tableRow7Column7.setText(String.valueOf(binEight.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binEight.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binEight.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                        tableRow7Column7.setText(String.valueOf(binNine.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binNine.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binNine.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA)) {
                        tableRow7Column7.setText(String.valueOf(binA.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binA.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binA.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB)) {
                        tableRow7Column7.setText(String.valueOf(binB.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binB.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binB.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC)) {
                        tableRow7Column7.setText(String.valueOf(binC.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binC.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binC.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD)) {
                        tableRow7Column7.setText(String.valueOf(binD.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binD.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binD.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE)) {
                        tableRow7Column7.setText(String.valueOf(binE.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binE.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binE.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF)) {
                        tableRow7Column7.setText(String.valueOf(binF.charAt(0)));
                        tableRow7Column6.setText(String.valueOf(binF.charAt(1)));
                        tableRow7Column5.setText(String.valueOf(binF.charAt(2)));
                        tableRow7Column4.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow7HigherNibble();
                    }
                    /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/
                    /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                        tableRow8Column3.setText(String.valueOf(binZero.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binZero.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binZero.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                        tableRow8Column3.setText(String.valueOf(binOne.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binOne.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binOne.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                        tableRow8Column3.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                        tableRow8Column3.setText(String.valueOf(binThree.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binThree.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binThree.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                        tableRow8Column3.setText(String.valueOf(binFour.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binFour.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binFour.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                        tableRow8Column4.setText(String.valueOf(binFive.charAt(0)));
                        tableRow8Column5.setText(String.valueOf(binFive.charAt(1)));
                        tableRow8Column6.setText(String.valueOf(binFive.charAt(2)));
                        tableRow8Column7.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                        tableRow8Column3.setText(String.valueOf(binSix.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binSix.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binSix.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                        tableRow8Column3.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                        tableRow8Column3.setText(String.valueOf(binEight.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binEight.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binEight.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                        tableRow8Column3.setText(String.valueOf(binNine.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binNine.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binNine.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexA)) {
                        tableRow8Column3.setText(String.valueOf(binA.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binA.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binA.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexB)) {
                        tableRow8Column3.setText(String.valueOf(binB.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binB.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binB.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexC)) {
                        tableRow8Column3.setText(String.valueOf(binC.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binC.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binC.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexD)) {
                        tableRow8Column3.setText(String.valueOf(binD.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binD.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binD.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexE)) {
                        tableRow8Column3.setText(String.valueOf(binE.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binE.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binE.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexF)) {
                        tableRow8Column3.setText(String.valueOf(binF.charAt(0)));
                        tableRow8Column2.setText(String.valueOf(binF.charAt(1)));
                        tableRow8Column1.setText(String.valueOf(binF.charAt(2)));
                        tableRow8Column0.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow8LowerNibble();
                    }
                    /***************************** END OF BASED ON THIRD CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ****************************/

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                        tableRow8Column7.setText(String.valueOf(binZero.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binZero.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binZero.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                        tableRow8Column7.setText(String.valueOf(binOne.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binOne.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binOne.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                        tableRow8Column7.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                        tableRow8Column7.setText(String.valueOf(binThree.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binThree.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binThree.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                        tableRow8Column7.setText(String.valueOf(binFour.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binFour.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binFour.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                        tableRow8Column7.setText(String.valueOf(binFive.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binFive.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binFive.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                        tableRow8Column7.setText(String.valueOf(binSix.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binSix.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binSix.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                        tableRow8Column7.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                        tableRow8Column7.setText(String.valueOf(binEight.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binEight.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binEight.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                        tableRow8Column7.setText(String.valueOf(binNine.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binNine.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binNine.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexA)) {
                        tableRow8Column7.setText(String.valueOf(binA.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binA.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binA.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexB)) {
                        tableRow8Column7.setText(String.valueOf(binB.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binB.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binB.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexC)) {
                        tableRow8Column7.setText(String.valueOf(binC.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binC.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binC.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexD)) {
                        tableRow8Column7.setText(String.valueOf(binD.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binD.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binD.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexE)) {
                        tableRow8Column7.setText(String.valueOf(binE.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binE.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binE.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexF)) {
                        tableRow8Column7.setText(String.valueOf(binF.charAt(0)));
                        tableRow8Column6.setText(String.valueOf(binF.charAt(1)));
                        tableRow8Column5.setText(String.valueOf(binF.charAt(2)));
                        tableRow8Column4.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow8HigherNibble();
                    }
                }
                /*END OF CBC INPUTS*/

                /*CBC OUTPUTS*/
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("1"))
                {
                    /******************************** BASED ON FIRST CHARACTER FROM RIGHT ***********************/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                        tableRow9Column3.setText(String.valueOf(binZero.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binZero.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binZero.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                        tableRow9Column3.setText(String.valueOf(binOne.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binOne.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binOne.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo)) {
                        tableRow9Column3.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree)) {
                        tableRow9Column3.setText(String.valueOf(binThree.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binThree.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binThree.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                        tableRow9Column3.setText(String.valueOf(binFour.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binFour.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binFour.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive)) {
                        tableRow9Column4.setText(String.valueOf(binFive.charAt(0)));
                        tableRow9Column5.setText(String.valueOf(binFive.charAt(1)));
                        tableRow9Column6.setText(String.valueOf(binFive.charAt(2)));
                        tableRow9Column7.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix)) {
                        tableRow9Column3.setText(String.valueOf(binSix.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binSix.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binSix.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven)) {
                        tableRow9Column3.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight)) {
                        tableRow9Column3.setText(String.valueOf(binEight.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binEight.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binEight.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine)) {
                        tableRow9Column3.setText(String.valueOf(binNine.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binNine.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binNine.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA)) {
                        tableRow9Column3.setText(String.valueOf(binA.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binA.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binA.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB)) {
                        tableRow9Column3.setText(String.valueOf(binB.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binB.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binB.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC)) {
                        tableRow9Column3.setText(String.valueOf(binC.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binC.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binC.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD)) {
                        tableRow9Column3.setText(String.valueOf(binD.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binD.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binD.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE)) {
                        tableRow9Column3.setText(String.valueOf(binE.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binE.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binE.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF)) {
                        tableRow9Column3.setText(String.valueOf(binF.charAt(0)));
                        tableRow9Column2.setText(String.valueOf(binF.charAt(1)));
                        tableRow9Column1.setText(String.valueOf(binF.charAt(2)));
                        tableRow9Column0.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow9LowerNibble();
                    }
                    /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                        tableRow9Column7.setText(String.valueOf(binZero.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binZero.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binZero.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                        tableRow9Column7.setText(String.valueOf(binOne.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binOne.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binOne.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                        tableRow9Column7.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                        tableRow9Column7.setText(String.valueOf(binThree.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binThree.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binThree.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                        tableRow9Column7.setText(String.valueOf(binFour.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binFour.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binFour.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                        tableRow9Column7.setText(String.valueOf(binFive.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binFive.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binFive.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                        tableRow9Column7.setText(String.valueOf(binSix.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binSix.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binSix.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                        tableRow9Column7.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                        tableRow9Column7.setText(String.valueOf(binEight.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binEight.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binEight.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                        tableRow9Column7.setText(String.valueOf(binNine.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binNine.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binNine.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA)) {
                        tableRow9Column7.setText(String.valueOf(binA.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binA.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binA.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB)) {
                        tableRow9Column7.setText(String.valueOf(binB.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binB.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binB.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC)) {
                        tableRow9Column7.setText(String.valueOf(binC.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binC.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binC.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD)) {
                        tableRow9Column7.setText(String.valueOf(binD.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binD.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binD.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE)) {
                        tableRow9Column7.setText(String.valueOf(binE.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binE.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binE.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF)) {
                        tableRow9Column7.setText(String.valueOf(binF.charAt(0)));
                        tableRow9Column6.setText(String.valueOf(binF.charAt(1)));
                        tableRow9Column5.setText(String.valueOf(binF.charAt(2)));
                        tableRow9Column4.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow9HigherNibble();
                    }
                    /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/
                    /******************************** BASED ON THIRD CHARACTER FROM RIGHT ***********************/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                        tableRow10Column3.setText(String.valueOf(binZero.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binZero.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binZero.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                        tableRow10Column3.setText(String.valueOf(binOne.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binOne.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binOne.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                        tableRow10Column3.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                        tableRow10Column3.setText(String.valueOf(binThree.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binThree.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binThree.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                        tableRow10Column3.setText(String.valueOf(binFour.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binFour.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binFour.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                        tableRow10Column4.setText(String.valueOf(binFive.charAt(0)));
                        tableRow10Column5.setText(String.valueOf(binFive.charAt(1)));
                        tableRow10Column6.setText(String.valueOf(binFive.charAt(2)));
                        tableRow10Column7.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                        tableRow10Column3.setText(String.valueOf(binSix.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binSix.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binSix.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                        tableRow10Column3.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                        tableRow10Column3.setText(String.valueOf(binEight.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binEight.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binEight.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                        tableRow10Column3.setText(String.valueOf(binNine.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binNine.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binNine.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexA)) {
                        tableRow10Column3.setText(String.valueOf(binA.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binA.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binA.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexB)) {
                        tableRow10Column3.setText(String.valueOf(binB.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binB.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binB.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexC)) {
                        tableRow10Column3.setText(String.valueOf(binC.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binC.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binC.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexD)) {
                        tableRow10Column3.setText(String.valueOf(binD.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binD.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binD.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexE)) {
                        tableRow10Column3.setText(String.valueOf(binE.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binE.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binE.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexF)) {
                        tableRow10Column3.setText(String.valueOf(binF.charAt(0)));
                        tableRow10Column2.setText(String.valueOf(binF.charAt(1)));
                        tableRow10Column1.setText(String.valueOf(binF.charAt(2)));
                        tableRow10Column0.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow10LowerNibble();
                    }
                    /***************************** END OF BASED ON THIRD CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON FOURTH CHARACTER FROM RIGHT ****************************/

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                        tableRow10Column7.setText(String.valueOf(binZero.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binZero.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binZero.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                        tableRow10Column7.setText(String.valueOf(binOne.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binOne.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binOne.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                        tableRow10Column7.setText(String.valueOf(binTwo.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binTwo.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binTwo.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                        tableRow10Column7.setText(String.valueOf(binThree.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binThree.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binThree.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                        tableRow10Column7.setText(String.valueOf(binFour.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binFour.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binFour.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                        tableRow10Column7.setText(String.valueOf(binFive.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binFive.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binFive.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                        tableRow10Column7.setText(String.valueOf(binSix.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binSix.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binSix.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                        tableRow10Column7.setText(String.valueOf(binSeven.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binSeven.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binSeven.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                        tableRow10Column7.setText(String.valueOf(binEight.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binEight.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binEight.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                        tableRow10Column7.setText(String.valueOf(binNine.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binNine.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binNine.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexA)) {
                        tableRow10Column7.setText(String.valueOf(binA.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binA.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binA.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexB)) {
                        tableRow10Column7.setText(String.valueOf(binB.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binB.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binB.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexC)) {
                        tableRow10Column7.setText(String.valueOf(binC.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binC.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binC.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexD)) {
                        tableRow10Column7.setText(String.valueOf(binD.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binD.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binD.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexE)) {
                        tableRow10Column7.setText(String.valueOf(binE.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binE.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binE.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexF)) {
                        tableRow10Column7.setText(String.valueOf(binF.charAt(0)));
                        tableRow10Column6.setText(String.valueOf(binF.charAt(1)));
                        tableRow10Column5.setText(String.valueOf(binF.charAt(2)));
                        tableRow10Column4.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableRow10HigherNibble();
                    }
                }
                /*END OF CBC OUTPUTS*/

                /*FLOOR UP KEYS O TO 15 & 16 TO 31*/
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("2"))
                {
                    floorUpKeysTimeStamp.setText(dateChosen);
                    floorUpKeysTimeStamp.setBackgroundColor(Color.RED);
                    /*CHECKING FIRST CHARACTER FROM LEFT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                        tableRow11Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                        tableRow11Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo)) {
                        tableRow11Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree)) {
                        tableRow11Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                        tableRow11Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive)) {
                        tableRow11Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix)) {
                        tableRow11Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven)) {
                        tableRow11Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight)) {
                        tableRow11Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine)) {
                        tableRow11Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA)) {
                        tableRow11Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB)) {
                        tableRow11Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC)) {
                        tableRow11Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD)) {
                        tableRow11Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE)) {
                        tableRow11Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF)) {
                        tableRow11Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow11Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow11Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow11Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow11LowerNibble();
                    }
                    /*END OF CHECKING FIRST CHARACTER FROM LEFT*/
                    /*SECOND CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                        tableRow11Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                        tableRow11Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                        tableRow11Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                        tableRow11Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                        tableRow11Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                        tableRow11Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                        tableRow11Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                        tableRow11Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                        tableRow11Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                        tableRow11Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA)) {
                        tableRow11Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB)) {
                        tableRow11Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC)) {
                        tableRow11Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD)) {
                        tableRow11Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE)) {
                        tableRow11Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF)) {
                        tableRow11Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow11Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow11Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow11Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow11HigherNibble();
                    }
                    /*END OF SECOND CHARACTER FROM RIGHT*/
                    /*THIRD CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                        tableRow12Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                        tableRow12Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                        tableRow12Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                        tableRow12Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                        tableRow12Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                        tableRow12Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                        tableRow12Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                        tableRow12Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                        tableRow12Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                        tableRow12Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexA)) {
                        tableRow12Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexB)) {
                        tableRow12Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexC)) {
                        tableRow12Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexD)) {
                        tableRow12Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexE)) {
                        tableRow12Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexF)) {
                        tableRow12Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow12Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow12Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow12Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow12LowerNibble();
                    }
                    /*END OF THIRD CHARACTER FROM RIGHT*/
                    /*FOURTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                        tableRow12Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                        tableRow12Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                        tableRow12Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                        tableRow12Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                        tableRow12Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                        tableRow12Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                        tableRow12Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                        tableRow12Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                        tableRow12Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                        tableRow12Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexA)) {
                        tableRow12Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexB)) {
                        tableRow12Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexC)) {
                        tableRow12Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexD)) {
                        tableRow12Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexE)) {
                        tableRow12Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexF)) {
                        tableRow12Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow12Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow12Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow12Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow12HigherNibble();
                    }
                    /*END OF FOURTH CHARACTER FROM RIGHT*/
                    /*CHECKING FIFTH CHARACTER FROM LEFT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexZero)) {
                        tableRow13Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexOne)) {
                        tableRow13Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexTwo)) {
                        tableRow13Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexThree)) {
                        tableRow13Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFour)) {
                        tableRow13Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFive)) {
                        tableRow13Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSix)) {
                        tableRow13Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSeven)) {
                        tableRow13Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexEight)) {
                        tableRow13Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexNine)) {
                        tableRow13Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexA)) {
                        tableRow13Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexB)) {
                        tableRow13Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexC)) {
                        tableRow13Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexD)) {
                        tableRow13Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexE)) {
                        tableRow13Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexF)) {
                        tableRow13Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow13Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow13Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow13Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow13LowerNibble();
                    }
                    /*END OF CHECKING FIFTH CHARACTER FROM LEFT*/
                    /*SIXTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexZero)) {
                        tableRow13Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexOne)) {
                        tableRow13Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexTwo)) {
                        tableRow13Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexThree)) {
                        tableRow13Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFour)) {
                        tableRow13Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFive)) {
                        tableRow13Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSix)) {
                        tableRow13Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSeven)) {
                        tableRow13Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexEight)) {
                        tableRow13Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexNine)) {
                        tableRow13Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexA)) {
                        tableRow13Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexB)) {
                        tableRow13Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexC)) {
                        tableRow13Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexD)) {
                        tableRow13Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexE)) {
                        tableRow13Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexF)) {
                        tableRow13Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow13Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow13Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow13Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow13HigherNibble();
                    }
                    /*END OF SIXTH CHARACTER FROM RIGHT*/
                    /*SEVENTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexZero)) {
                        tableRow14Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexOne)) {
                        tableRow14Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexTwo)) {
                        tableRow14Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexThree)) {
                        tableRow14Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFour)) {
                        tableRow14Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFive)) {
                        tableRow14Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSix)) {
                        tableRow14Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSeven)) {
                        tableRow14Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexEight)) {
                        tableRow14Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexNine)) {
                        tableRow14Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexA)) {
                        tableRow14Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexB)) {
                        tableRow14Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexC)) {
                        tableRow14Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexD)) {
                        tableRow14Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexE)) {
                        tableRow14Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexF)) {
                        tableRow14Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow14Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow14Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow14Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow14LowerNibble();
                    }
                    /*END OF SEVENTH CHARACTER FROM RIGHT*/
                    /*EIGHTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexZero)) {
                        tableRow14Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexOne)) {
                        tableRow14Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexTwo)) {
                        tableRow14Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexThree)) {
                        tableRow14Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFour)) {
                        tableRow14Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFive)) {
                        tableRow14Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSix)) {
                        tableRow14Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSeven)) {
                        tableRow14Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexEight)) {
                        tableRow14Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexNine)) {
                        tableRow14Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexA)) {
                        tableRow14Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexB)) {
                        tableRow14Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexC)) {
                        tableRow14Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexD)) {
                        tableRow14Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexE)) {
                        tableRow14Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexF)) {
                        tableRow14Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow14Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow14Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow14Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow14HigherNibble();
                    }
                    /*END OF EIGHTH CHARACTER FROM RIGHT*/
                }
                /*END OF UP FLOOR KEYS*/

                /*FLOOR DOWN KEYS 0 TO 15 & 16 TO 31*/
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("3"))
                {
                    floorDownKeysTimeStamp.setText(dateChosen);
                    floorDownKeysTimeStamp.setBackgroundColor(Color.RED);
                    /*CHECKING FIRST CHARACTER FROM LEFT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                        tableRow15Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                        tableRow15Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo)) {
                        tableRow15Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree)) {
                        tableRow15Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                        tableRow15Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive)) {
                        tableRow15Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix)) {
                        tableRow15Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven)) {
                        tableRow15Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight)) {
                        tableRow15Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine)) {
                        tableRow15Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA)) {
                        tableRow15Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB)) {
                        tableRow15Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC)) {
                        tableRow15Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD)) {
                        tableRow15Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE)) {
                        tableRow15Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF)) {
                        tableRow15Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow15Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow15Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow15Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow15LowerNibble();
                    }
                    /*END OF CHECKING FIRST CHARACTER FROM LEFT*/
                    /*SECOND CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                        tableRow15Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                        tableRow15Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                        tableRow15Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                        tableRow15Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                        tableRow15Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                        tableRow15Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                        tableRow15Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                        tableRow15Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                        tableRow15Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                        tableRow15Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA)) {
                        tableRow15Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB)) {
                        tableRow15Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC)) {
                        tableRow15Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD)) {
                        tableRow15Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE)) {
                        tableRow15Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF)) {
                        tableRow15Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow15Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow15Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow15Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow15HigherNibble();
                    }
                    /*END OF SECOND CHARACTER FROM RIGHT*/
                    /*THIRD CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                        tableRow16Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                        tableRow16Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                        tableRow16Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                        tableRow16Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                        tableRow16Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                        tableRow16Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                        tableRow16Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                        tableRow16Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                        tableRow16Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                        tableRow16Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexA)) {
                        tableRow16Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexB)) {
                        tableRow16Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexC)) {
                        tableRow16Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexD)) {
                        tableRow16Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexE)) {
                        tableRow16Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexF)) {
                        tableRow16Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow16Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow16Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow16Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow16LowerNibble();
                    }
                    /*END OF THIRD CHARACTER FROM RIGHT*/
                    /*FOURTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                        tableRow16Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                        tableRow16Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                        tableRow16Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                        tableRow16Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                        tableRow16Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                        tableRow16Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                        tableRow16Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                        tableRow16Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                        tableRow16Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                        tableRow16Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexA)) {
                        tableRow16Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexB)) {
                        tableRow16Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexC)) {
                        tableRow16Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexD)) {
                        tableRow16Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexE)) {
                        tableRow16Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexF)) {
                        tableRow16Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow16Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow16Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow16Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow16HigherNibble();
                    }
                    /*END OF FOURTH CHARACTER FROM RIGHT*/
                    /*CHECKING FIFTH CHARACTER FROM LEFT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexZero)) {
                        tableRow17Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexOne)) {
                        tableRow17Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexTwo)) {
                        tableRow17Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexThree)) {
                        tableRow17Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFour)) {
                        tableRow17Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFive)) {
                        tableRow17Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSix)) {
                        tableRow17Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSeven)) {
                        tableRow17Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexEight)) {
                        tableRow17Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexNine)) {
                        tableRow17Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexA)) {
                        tableRow17Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexB)) {
                        tableRow17Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexC)) {
                        tableRow17Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexD)) {
                        tableRow17Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexE)) {
                        tableRow17Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexF)) {
                        tableRow17Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow17Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow17Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow17Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow17LowerNibble();
                    }
                    /*END OF CHECKING FIFTH CHARACTER FROM LEFT*/
                    /*SIXTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexZero)) {
                        tableRow17Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexOne)) {
                        tableRow17Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexTwo)) {
                        tableRow17Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexThree)) {
                        tableRow17Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFour)) {
                        tableRow17Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFive)) {
                        tableRow17Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSix)) {
                        tableRow17Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSeven)) {
                        tableRow17Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexEight)) {
                        tableRow17Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexNine)) {
                        tableRow17Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexA)) {
                        tableRow17Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexB)) {
                        tableRow17Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexC)) {
                        toast("YES");
                        tableRow17Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexD)) {
                        tableRow17Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexE)) {
                        tableRow17Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexF)) {
                        tableRow17Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow17Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow17Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow17Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow17HigherNibble();
                    }
                    /*END OF SIXTH CHARACTER FROM RIGHT*/
                    /*SEVENTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexZero)) {
                        tableRow18Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexOne)) {
                        tableRow18Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexTwo)) {
                        tableRow18Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexThree)) {
                        tableRow18Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFour)) {
                        tableRow18Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFive)) {
                        tableRow18Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSix)) {
                        tableRow18Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSeven)) {
                        tableRow18Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexEight)) {
                        tableRow18Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexNine)) {
                        tableRow18Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexA)) {
                        tableRow18Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexB)) {
                        tableRow18Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexC)) {
                        tableRow18Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexD)) {
                        tableRow18Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexE)) {
                        tableRow18Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexF)) {
                        tableRow18Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow18Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow18Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow18Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow18LowerNibble();
                    }
                    /*END OF SEVENTH CHARACTER FROM RIGHT*/
                    /*EIGHTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexZero)) {
                        tableRow18Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexOne)) {
                        tableRow18Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexTwo)) {
                        tableRow18Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexThree)) {
                        tableRow18Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFour)) {
                        tableRow18Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFive)) {
                        tableRow18Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSix)) {
                        tableRow18Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSeven)) {
                        tableRow18Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexEight)) {
                        tableRow18Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexNine)) {
                        tableRow18Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexA)) {
                        tableRow18Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexB)) {
                        tableRow18Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexC)) {
                        tableRow18Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexD)) {
                        tableRow18Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexE)) {
                        tableRow18Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexF)) {
                        tableRow18Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow18Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow18Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow18Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow18HigherNibble();
                    }
                    /*END OF EIGHTH CHARACTER FROM RIGHT*/
                }
                /*END OF DOWN KEYS*/

                /*FOR LIFT EMERGENCY STOP*/
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("2") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("0"))
                {
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                        liftStopStatus.setText("EMERGENCY STOP RELEASED");
                        liftStopStatus.setBackgroundColor(Color.GRAY);
                        liftStopStatus.setTextColor(Color.WHITE);
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                        liftStopStatus.setText("LIFT IS STOPPED");
                        liftStopStatus.setBackgroundColor(Color.RED);
                        liftStopStatus.setTextColor(Color.WHITE);
                    }
                }

                /*END OF EMERGENCY STOP*/

                /*LIFT STATUS*/
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("5"))
                {
                    //toast(String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))));
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                        liftStatus.setText("LIFT IS NOT RUNNING");
                        liftStatus.setBackgroundColor(Color.GRAY);
                        liftStatus.setTextColor(Color.WHITE);
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                        liftStatus.setText("LIFT IS RUNNING");
                        liftStatus.setBackgroundColor(Color.RED);
                        liftStatus.setTextColor(Color.WHITE);
                    }
                }
                /*END OF LIFT STATUS*/
            }
        }
        /*END OF UI SHOWCASING AS PER THE CONTENT OF THE STRING*/
    }//end of showAllInformation()

    public void showAllInformation2()
    {
        sqLiteDatabase= customerControllerDB.getReadableDatabase();
        clientCursor = sqLiteDatabase.rawQuery("SELECT NUMBER FROM CUSTOMER_DEST_NUMBERS WHERE LOCATION='"+clientChosen+"'",null);
        if (clientCursor.moveToFirst())
        {
            do
            {
                theNumber=clientCursor.getString(clientCursor.getColumnIndex("NUMBER"));
            }while (clientCursor.moveToNext());
        }
        theSqLiteDatabase= customerSMSReceiverControllerDB.getReadableDatabase();
        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM G_CUSTOMER_SMS WHERE DATE='"+dateChosen+"' AND NUMBER='"+theNumber+"'",null);
        clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM G_CUSTOMER_SMS WHERE DATE='"+dateChosen+"'",null);
        theContent = new ArrayList<String>();
        theContent.clear();
        if (clientCursor.moveToFirst())
        {
            do
            {
                theContent.add(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
            }while (clientCursor.moveToNext());
        }
        int count = clientCursor.getCount();
        String contentArray[]=new String[count];
        for(int i=0; i<contentArray.length; i++)
        {
            contentArray[i] = theContent.get(i);
        }
        StringBuffer sb0 = new StringBuffer();
        for(int i = 0; i < contentArray.length; i++)
        {
            sb0.append(contentArray[i]);
        }
        String str1 = sb0.toString();
        //content.setText(str1);
        decodeChoice = str1;

        /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
        String stringSplit[] = decodeChoice.split("\n");
        for(int s=0; s<stringSplit.length; s++)
        {
            if (!String.valueOf(stringSplit[s]).isEmpty())
            {
                /*CAR CALLS*/
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("4"))
                {
                    /*CHECKING FIRST CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero)) {
                        tableRow19Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne)) {
                        tableRow19Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo)) {
                        tableRow19Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree)) {
                        tableRow19Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour)) {
                        tableRow19Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive)) {
                        tableRow19Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix)) {
                        tableRow19Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven)) {
                        tableRow19Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight)) {
                        tableRow19Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine)) {
                        tableRow19Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA)) {
                        tableRow19Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB)) {
                        tableRow19Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC)) {
                        tableRow19Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD)) {
                        tableRow19Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE)) {
                        tableRow19Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF)) {
                        tableRow19Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow19Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow19Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow19Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow19LowerNibble();
                    }
                    /*END OF CHECKING FIRST CHARACTER FROM RIGHT*/
                    /*SECOND CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero)) {
                        tableRow19Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne)) {
                        tableRow19Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo)) {
                        tableRow19Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree)) {
                        tableRow19Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour)) {
                        tableRow19Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive)) {
                        tableRow19Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix)) {
                        tableRow19Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven)) {
                        tableRow19Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight)) {
                        tableRow19Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine)) {
                        tableRow19Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA)) {
                        tableRow19Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB)) {
                        tableRow19Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC)) {
                        tableRow19Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD)) {
                        tableRow19Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE)) {
                        tableRow19Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF)) {
                        tableRow19Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow19Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow19Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow19Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow19HigherNibble();
                    }
                    /*END OF SECOND CHARACTER FROM RIGHT*/
                    /*THIRD CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexZero)) {
                        tableRow20Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexOne)) {
                        tableRow20Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexTwo)) {
                        tableRow20Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexThree)) {
                        tableRow20Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFour)) {
                        tableRow20Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexFive)) {
                        tableRow20Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSix)) {
                        tableRow20Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexSeven)) {
                        tableRow20Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexEight)) {
                        tableRow20Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexNine)) {
                        tableRow20Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexA)) {
                        tableRow20Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexB)) {
                        tableRow20Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexC)) {
                        tableRow20Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexD)) {
                        tableRow20Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexE)) {
                        tableRow20Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 3))).equalsIgnoreCase(hexF)) {
                        tableRow20Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow20Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow20Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow20Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow20LowerNibble();
                    }
                    /*END OF THIRD CHARACTER FROM RIGHT*/
                    /*FOURTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexZero)) {
                        tableRow20Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexOne)) {
                        tableRow20Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexTwo)) {
                        tableRow20Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexThree)) {
                        tableRow20Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFour)) {
                        tableRow20Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexFive)) {
                        tableRow20Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSix)) {
                        tableRow20Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexSeven)) {
                        tableRow20Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexEight)) {
                        tableRow20Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexNine)) {
                        tableRow20Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexA)) {
                        tableRow20Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexB)) {
                        tableRow20Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexC)) {
                        tableRow20Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexD)) {
                        tableRow20Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexE)) {
                        tableRow20Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 4))).equalsIgnoreCase(hexF)) {
                        tableRow20Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow20Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow20Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow20Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow20HigherNibble();
                    }
                    /*END OF FOURTH CHARACTER FROM RIGHT*/
                    /*CHECKING FIFTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexZero)) {
                        tableRow21Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexOne)) {
                        tableRow21Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexTwo)) {
                        tableRow21Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexThree)) {
                        tableRow21Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFour)) {
                        tableRow21Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexFive)) {
                        tableRow21Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSix)) {
                        tableRow21Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexSeven)) {
                        tableRow21Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexEight)) {
                        tableRow21Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexNine)) {
                        tableRow21Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexA)) {
                        tableRow21Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexB)) {
                        tableRow21Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexC)) {
                        tableRow21Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexD)) {
                        tableRow21Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexE)) {
                        tableRow21Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 5))).equalsIgnoreCase(hexF)) {
                        tableRow21Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow21Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow21Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow21Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow21LowerNibble();
                    }
                    /*END OF CHECKING FIFTH CHARACTER FROM RIGHT*/
                    /*SIXTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexZero)) {
                        tableRow21Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexOne)) {
                        tableRow21Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexTwo)) {
                        tableRow21Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexThree)) {
                        tableRow21Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFour)) {
                        tableRow21Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexFive)) {
                        tableRow21Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSix)) {
                        tableRow21Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexSeven)) {
                        tableRow21Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexEight)) {
                        tableRow21Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexNine)) {
                        tableRow21Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexA)) {
                        tableRow21Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexB)) {
                        tableRow21Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexC)) {
                        tableRow21Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexD)) {
                        tableRow21Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexE)) {
                        tableRow21Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 6))).equalsIgnoreCase(hexF)) {
                        tableRow21Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow21Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow21Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow21Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow21HigherNibble();
                    }
                    /*END OF SIXTH CHARACTER FROM RIGHT*/
                    /*SEVENTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexZero)) {
                        tableRow22Column3.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexOne)) {
                        tableRow22Column3.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexTwo)) {
                        tableRow22Column3.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexThree)) {
                        tableRow22Column3.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFour)) {
                        tableRow22Column3.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexFive)) {
                        tableRow22Column3.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSix)) {
                        tableRow22Column3.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexSeven)) {
                        tableRow22Column3.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexEight)) {
                        tableRow22Column3.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexNine)) {
                        tableRow22Column3.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexA)) {
                        tableRow22Column3.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexB)) {
                        tableRow22Column3.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexC)) {
                        tableRow22Column3.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexD)) {
                        tableRow22Column3.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexE)) {
                        tableRow22Column3.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 7))).equalsIgnoreCase(hexF)) {
                        tableRow22Column3.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow22Column2.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow22Column1.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow22Column0.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow22LowerNibble();
                    }
                    /*END OF SEVENTH CHARACTER FROM RIGHT*/
                    /*EIGHTH CHARACTER FROM RIGHT*/
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexZero)) {
                        tableRow22Column7.setText(String.valueOf(floorBinZero.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinZero.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinZero.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinZero.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexOne)) {
                        tableRow22Column7.setText(String.valueOf(floorBinOne.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinOne.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinOne.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinOne.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexTwo)) {
                        tableRow22Column7.setText(String.valueOf(floorBinTwo.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinTwo.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinTwo.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinTwo.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexThree)) {
                        tableRow22Column7.setText(String.valueOf(floorBinThree.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinThree.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinThree.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinThree.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFour)) {
                        tableRow22Column7.setText(String.valueOf(floorBinFour.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinFour.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinFour.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinFour.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexFive)) {
                        tableRow22Column7.setText(String.valueOf(floorBinFive.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinFive.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinFive.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinFive.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSix)) {
                        tableRow22Column7.setText(String.valueOf(floorBinSix.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinSix.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinSix.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinSix.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexSeven)) {
                        tableRow22Column7.setText(String.valueOf(floorBinSeven.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinSeven.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinSeven.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinSeven.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexEight)) {
                        tableRow22Column7.setText(String.valueOf(floorBinEight.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinEight.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinEight.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinEight.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexNine)) {
                        tableRow22Column7.setText(String.valueOf(floorBinNine.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinNine.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinNine.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinNine.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexA)) {
                        tableRow22Column7.setText(String.valueOf(floorBinA.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinA.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinA.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinA.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexB)) {
                        tableRow22Column7.setText(String.valueOf(floorBinB.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinB.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinB.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinB.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexC)) {
                        tableRow22Column7.setText(String.valueOf(floorBinC.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinC.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinC.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinC.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexD)) {
                        tableRow22Column7.setText(String.valueOf(floorBinD.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinD.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinD.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinD.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexE)) {
                        tableRow22Column7.setText(String.valueOf(floorBinE.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinE.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinE.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinE.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 8))).equalsIgnoreCase(hexF)) {
                        tableRow22Column7.setText(String.valueOf(floorBinF.charAt(0)));
                        tableRow22Column6.setText(String.valueOf(floorBinF.charAt(1)));
                        tableRow22Column5.setText(String.valueOf(floorBinF.charAt(2)));
                        tableRow22Column4.setText(String.valueOf(floorBinF.charAt(3)));
                        setColorsForTableRow22HigherNibble();
                    }
                    /*END OF EIGHTH CHARACTER FROM RIGHT*/
                }
                /*END OF CAR CALLS*/
            }
        }//end of for loop()
    }//end of showAllInformation2()

    public void showLiftErrorStatus()
    {
        sqLiteDatabase = customerControllerDB.getReadableDatabase();
        clientCursor = sqLiteDatabase.rawQuery("SELECT NUMBER FROM CUSTOMER_DEST_NUMBERS WHERE LOCATION='" + clientChosen + "'", null);
        if (clientCursor.moveToFirst())
        {
            do
            {
                theNumber = clientCursor.getString(clientCursor.getColumnIndex("NUMBER"));
            } while (clientCursor.moveToNext());
        }
        theSqLiteDatabase = customerSMSReceiverControllerDB.getReadableDatabase();
        //clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM G_CUSTOMER_SMS WHERE DATE='"+dateChosen+"' AND NUMBER='"+theNumber+"'",null);
        clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM G_CUSTOMER_SMS WHERE DATE='" + dateChosen + "'", null);
        theContent = new ArrayList<String>();
        theContent.clear();
        if (clientCursor.moveToFirst())
        {
            do
            {
                theContent.add(clientCursor.getString(clientCursor.getColumnIndex("CONTENT")));
            } while (clientCursor.moveToNext());
        }
        int count = clientCursor.getCount();
        String contentArray[] = new String[count];
        for (int i = 0; i < contentArray.length; i++)
        {
            contentArray[i] = theContent.get(i);
        }
        StringBuffer sb0 = new StringBuffer();
        for (int i = 0; i < contentArray.length; i++)
        {
            sb0.append(contentArray[i]);
        }
        String str1 = sb0.toString();
        //content.setText(str1);
        decodeChoice = str1;

        /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
        String stringSplit[] = decodeChoice.split("\n");
        String stringLiftErrorStat = stringSplit[0];
        updateStatus.setText("STATUS OF "+dateChosen);
        for (int s = 0; s < stringSplit.length; s++)
        {
            if (!String.valueOf(stringSplit[s]).isEmpty())
                /*LIFT ERROR STATUS*/
                if (String.valueOf(stringSplit[s].charAt(1)).equalsIgnoreCase("1") && String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("6"))
                {
                    String liftErrorStat[] = new String[1];
                    liftErrorStat[0] = stringLiftErrorStat;
                    try
                    {
                        for (int k = 0; k < liftErrorStat.length; k++)
                        {
                            /*CHECKING FIRST CHARACTER FROM RIGHT*/
                            switch (String.valueOf(liftErrorStat[k].charAt((liftErrorStat[k].length() - 1)))) {
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
                            switch (String.valueOf(liftErrorStat[k].charAt((liftErrorStat[k].length() - 2)))) {
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
                            switch (String.valueOf(liftErrorStat[k].charAt((liftErrorStat[k].length() - 3)))) {
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
                            switch (String.valueOf(liftErrorStat[k].charAt((liftErrorStat[k].length() - 4)))) {
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
                            switch (String.valueOf(liftErrorStat[k].charAt((liftErrorStat[k].length() - 5)))) {
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
                    } catch (NullPointerException npe) {
                        toast(npe.toString());
                    }
                }
        }
    }//end of showLiftErrorStatus()

    public void liftErrorStatusData(String code)
    {
        String liftErrorStat[] = new String[1];
        liftErrorStat[0]=code;
        try
        {
            for(int s=0; s<liftErrorStat.length; s++)
            {
                /*CHECKING FIRST CHARACTER FROM RIGHT*/
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 1))))
                {
                    case hexZero:
                        err3_d.setText(String.valueOf(binZero.charAt(0)));
                        err2_d.setText(String.valueOf(binZero.charAt(1)));
                        err1_d.setText(String.valueOf(binZero.charAt(2)));
                        err0_d.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForLiftErrorStatus();
                        break;
                    case hexOne:
                        toast("I GOT ONE");
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
                        toast("I GOT THREE");
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
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 2))))
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
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 3))))
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
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 4))))
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
                switch( String.valueOf(liftErrorStat[s].charAt((liftErrorStat[s].length() - 5))))
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
                deleteRecords();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }//end of onOptionsItemSelected()

    public void deleteRecords()
    {
        Intent intent = new Intent(this, CustomerRecordsDeleteActivity.class);
        startActivity(intent);
    }//end of deleteRecords()

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
        if (tableRow3Column0.getText().equals(inactive)) {
            tableRow3Column0.setBackgroundColor(Color.GRAY);
        } else {
            tableRow3Column0.setBackgroundColor(Color.RED);
        }
        if (tableRow3Column1.getText().equals(inactive)) {
            tableRow3Column1.setBackgroundColor(Color.GRAY);
        } else {
            tableRow3Column1.setBackgroundColor(Color.RED);
        }
        if (tableRow3Column2.getText().equals(inactive)) {
            tableRow3Column2.setBackgroundColor(Color.GRAY);
        } else {
            tableRow3Column2.setBackgroundColor(Color.RED);
        }
        if (tableRow3Column3.getText().equals(inactive)) {
            tableRow3Column3.setBackgroundColor(Color.GRAY);
        } else {
            tableRow3Column3.setBackgroundColor(Color.RED);
        }

    }//end of setColorsForTableRow3LowerNibble()

    public void setColorsForTableRow3HigherNibble()
    {
        if (tableRow3Column4.getText().equals(inactive)) {
            tableRow3Column4.setBackgroundColor(Color.GRAY);
        } else {
            tableRow3Column4.setBackgroundColor(Color.RED);
        }
        if (tableRow3Column5.getText().equals(inactive)) {
            tableRow3Column5.setBackgroundColor(Color.GRAY);
        } else {
            tableRow3Column5.setBackgroundColor(Color.RED);
        }
        if (tableRow3Column6.getText().equals(inactive)) {
            tableRow3Column6.setBackgroundColor(Color.GRAY);
        } else {
            tableRow3Column6.setBackgroundColor(Color.RED);
        }
        if (tableRow3Column7.getText().equals(inactive)) {
            tableRow3Column7.setBackgroundColor(Color.GRAY);
        } else {
            tableRow3Column7.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow3HigherNibble()

    public void setColorsForTableRow4LowerNibble()
    {
        if (tableRow4Column0.getText().equals(inactive)) {
            tableRow4Column0.setBackgroundColor(Color.GRAY);
        } else {
            tableRow4Column0.setBackgroundColor(Color.RED);
        }
        if (tableRow4Column1.getText().equals(inactive)) {
            tableRow4Column1.setBackgroundColor(Color.GRAY);
        } else {
            tableRow4Column1.setBackgroundColor(Color.RED);
        }
        if (tableRow4Column2.getText().equals(inactive)) {
            tableRow4Column2.setBackgroundColor(Color.GRAY);
        } else {
            tableRow4Column2.setBackgroundColor(Color.RED);
        }
        if (tableRow4Column3.getText().equals(inactive)) {
            tableRow4Column3.setBackgroundColor(Color.GRAY);
        } else {
            tableRow4Column3.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableRow4LowerNibble()

    public void setColorsForTableRow4HigherNibble()
    {
        if (tableRow4Column4.getText().equals(inactive)) {
            tableRow4Column4.setBackgroundColor(Color.GRAY);
        } else {
            tableRow4Column4.setBackgroundColor(Color.RED);
        }
        if (tableRow4Column5.getText().equals(inactive)) {
            tableRow4Column5.setBackgroundColor(Color.GRAY);
        } else {
            tableRow4Column5.setBackgroundColor(Color.RED);
        }
        if (tableRow4Column6.getText().equals(inactive)) {
            tableRow4Column6.setBackgroundColor(Color.GRAY);
        } else {
            tableRow4Column6.setBackgroundColor(Color.RED);
        }
        if (tableRow4Column7.getText().equals(inactive)) {
            tableRow4Column7.setBackgroundColor(Color.GRAY);
        } else {
            tableRow4Column7.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow5LowerNibble()
    {
        if (tableRow5Column0.getText().equals(inactive)) {
            tableRow5Column0.setBackgroundColor(Color.GRAY);
        } else {
            tableRow5Column0.setBackgroundColor(Color.RED);
        }
        if (tableRow5Column1.getText().equals(inactive)) {
            tableRow5Column1.setBackgroundColor(Color.GRAY);
        } else {
            tableRow5Column1.setBackgroundColor(Color.RED);
        }
        if (tableRow5Column2.getText().equals(inactive)) {
            tableRow5Column2.setBackgroundColor(Color.GRAY);
        } else {
            tableRow5Column2.setBackgroundColor(Color.RED);
        }
        if (tableRow5Column3.getText().equals(inactive)) {
            tableRow5Column3.setBackgroundColor(Color.GRAY);
        } else {
            tableRow5Column3.setBackgroundColor(Color.RED);
        }

    }//end

    public void setColorsForTableRow5HigherNibble()
    {
        if (tableRow5Column4.getText().equals(inactive)) {
            tableRow5Column4.setBackgroundColor(Color.GRAY);
        } else {
            tableRow5Column4.setBackgroundColor(Color.RED);
        }
        if (tableRow5Column5.getText().equals(inactive)) {
            tableRow5Column5.setBackgroundColor(Color.GRAY);
        } else {
            tableRow5Column5.setBackgroundColor(Color.RED);
        }
        if (tableRow5Column6.getText().equals(inactive)) {
            tableRow5Column6.setBackgroundColor(Color.GRAY);
        } else {
            tableRow5Column6.setBackgroundColor(Color.RED);
        }
        if (tableRow5Column7.getText().equals(inactive)) {
            tableRow5Column7.setBackgroundColor(Color.GRAY);
        } else {
            tableRow5Column7.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow6LowerNibble()
    {
        if (tableRow6Column0.getText().equals(inactive)) {
            tableRow6Column0.setBackgroundColor(Color.GRAY);
        } else {
            tableRow6Column0.setBackgroundColor(Color.RED);
        }
        if (tableRow6Column1.getText().equals(inactive)) {
            tableRow6Column1.setBackgroundColor(Color.GRAY);
        } else {
            tableRow6Column1.setBackgroundColor(Color.RED);
        }
        if (tableRow6Column2.getText().equals(inactive)) {
            tableRow6Column2.setBackgroundColor(Color.GRAY);
        } else {
            tableRow6Column2.setBackgroundColor(Color.RED);
        }
        if (tableRow6Column3.getText().equals(inactive)) {
            tableRow6Column3.setBackgroundColor(Color.GRAY);
        } else {
            tableRow6Column3.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow6HigherNibble()
    {
        if (tableRow6Column4.getText().equals(inactive)) {
            tableRow6Column4.setBackgroundColor(Color.GRAY);
        } else {
            tableRow6Column4.setBackgroundColor(Color.RED);
        }
        if (tableRow6Column5.getText().equals(inactive)) {
            tableRow6Column5.setBackgroundColor(Color.GRAY);
        } else {
            tableRow6Column5.setBackgroundColor(Color.RED);
        }
        if (tableRow6Column6.getText().equals(inactive)) {
            tableRow6Column6.setBackgroundColor(Color.GRAY);
        } else {
            tableRow6Column6.setBackgroundColor(Color.RED);
        }
        if (tableRow6Column7.getText().equals(inactive)) {
            tableRow6Column7.setBackgroundColor(Color.GRAY);
        } else {
            tableRow6Column7.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow7LowerNibble()
    {
        if (tableRow7Column0.getText().equals(inactive)) {
            tableRow7Column0.setBackgroundColor(Color.GRAY);
        } else {
            tableRow7Column0.setBackgroundColor(Color.RED);
        }
        if (tableRow7Column1.getText().equals(inactive)) {
            tableRow7Column1.setBackgroundColor(Color.GRAY);
        } else {
            tableRow7Column1.setBackgroundColor(Color.RED);
        }
        if (tableRow7Column2.getText().equals(inactive)) {
            tableRow7Column2.setBackgroundColor(Color.GRAY);
        } else {
            tableRow7Column2.setBackgroundColor(Color.RED);
        }
        if (tableRow7Column3.getText().equals(inactive)) {
            tableRow7Column3.setBackgroundColor(Color.GRAY);
        } else {
            tableRow7Column3.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow7HigherNibble()
    {
        if (tableRow7Column4.getText().equals(inactive)) {
            tableRow7Column4.setBackgroundColor(Color.GRAY);
        } else {
            tableRow7Column4.setBackgroundColor(Color.RED);
        }
        if (tableRow7Column5.getText().equals(inactive)) {
            tableRow7Column5.setBackgroundColor(Color.GRAY);
        } else {
            tableRow7Column5.setBackgroundColor(Color.RED);
        }
        if (tableRow7Column6.getText().equals(inactive)) {
            tableRow7Column6.setBackgroundColor(Color.GRAY);
        } else {
            tableRow7Column6.setBackgroundColor(Color.RED);
        }
        if (tableRow7Column7.getText().equals(inactive)) {
            tableRow7Column7.setBackgroundColor(Color.GRAY);
        } else {
            tableRow7Column7.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow8LowerNibble()
    {
        if (tableRow8Column0.getText().equals(inactive)) {
            tableRow8Column0.setBackgroundColor(Color.GRAY);
        } else {
            tableRow8Column0.setBackgroundColor(Color.RED);
        }
        if (tableRow8Column1.getText().equals(inactive)) {
            tableRow8Column1.setBackgroundColor(Color.GRAY);
        } else {
            tableRow8Column1.setBackgroundColor(Color.RED);
        }
        if (tableRow8Column2.getText().equals(inactive)) {
            tableRow8Column2.setBackgroundColor(Color.GRAY);
        } else {
            tableRow8Column2.setBackgroundColor(Color.RED);
        }
        if (tableRow8Column3.getText().equals(inactive)) {
            tableRow8Column3.setBackgroundColor(Color.GRAY);
        } else {
            tableRow8Column3.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow8HigherNibble()
    {
        if (tableRow8Column4.getText().equals(inactive)) {
            tableRow8Column4.setBackgroundColor(Color.GRAY);
        } else {
            tableRow8Column4.setBackgroundColor(Color.RED);
        }
        if (tableRow8Column5.getText().equals(inactive)) {
            tableRow8Column5.setBackgroundColor(Color.GRAY);
        } else {
            tableRow8Column5.setBackgroundColor(Color.RED);
        }
        if (tableRow8Column6.getText().equals(inactive)) {
            tableRow8Column6.setBackgroundColor(Color.GRAY);
        } else {
            tableRow8Column6.setBackgroundColor(Color.RED);
        }
        if (tableRow8Column7.getText().equals(inactive)) {
            tableRow8Column7.setBackgroundColor(Color.GRAY);
        } else {
            tableRow8Column7.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow9LowerNibble()
    {
        if (tableRow9Column0.getText().equals(inactive)) {
            tableRow9Column0.setBackgroundColor(Color.GRAY);
        } else {
            tableRow9Column0.setBackgroundColor(Color.RED);
        }
        if (tableRow9Column1.getText().equals(inactive)) {
            tableRow9Column1.setBackgroundColor(Color.GRAY);
        } else {
            tableRow9Column1.setBackgroundColor(Color.RED);
        }
        if (tableRow9Column2.getText().equals(inactive)) {
            tableRow9Column2.setBackgroundColor(Color.GRAY);
        } else {
            tableRow9Column2.setBackgroundColor(Color.RED);
        }
        if (tableRow9Column3.getText().equals(inactive)) {
            tableRow9Column3.setBackgroundColor(Color.GRAY);
        } else {
            tableRow9Column3.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow9HigherNibble()
    {
        if (tableRow9Column4.getText().equals(inactive)) {
            tableRow9Column4.setBackgroundColor(Color.GRAY);
        } else {
            tableRow9Column4.setBackgroundColor(Color.RED);
        }
        if (tableRow9Column5.getText().equals(inactive)) {
            tableRow9Column5.setBackgroundColor(Color.GRAY);
        } else {
            tableRow9Column5.setBackgroundColor(Color.RED);
        }
        if (tableRow9Column6.getText().equals(inactive)) {
            tableRow9Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow9Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow9Column7.getText().equals(inactive)) {
            tableRow9Column7.setBackgroundColor(Color.GRAY);
        } else {
            tableRow9Column7.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow10LowerNibble()
    {
        if (tableRow10Column0.getText().equals(inactive)) {
            tableRow10Column0.setBackgroundColor(Color.GRAY);
        } else {
            tableRow10Column0.setBackgroundColor(Color.RED);
        }
        if (tableRow10Column1.getText().equals(inactive)) {
            tableRow10Column1.setBackgroundColor(Color.GRAY);
        } else {
            tableRow10Column1.setBackgroundColor(Color.RED);
        }
        if (tableRow10Column2.getText().equals(inactive)) {
            tableRow10Column2.setBackgroundColor(Color.GRAY);
        } else {
            tableRow10Column2.setBackgroundColor(Color.RED);
        }
        if (tableRow10Column3.getText().equals(inactive)) {
            tableRow10Column3.setBackgroundColor(Color.GRAY);
        } else {
            tableRow10Column3.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow10HigherNibble()
    {
        if (tableRow10Column4.getText().equals(inactive)) {
            tableRow10Column4.setBackgroundColor(Color.GRAY);
        } else {
            tableRow10Column4.setBackgroundColor(Color.RED);
        }
        if (tableRow10Column5.getText().equals(inactive)) {
            tableRow10Column5.setBackgroundColor(Color.GRAY);
        } else {
            tableRow10Column5.setBackgroundColor(Color.RED);
        }
        if (tableRow10Column6.getText().equals(inactive)) {
            tableRow10Column6.setBackgroundColor(Color.GRAY);
        } else {
            tableRow10Column6.setBackgroundColor(Color.RED);
        }
        if (tableRow10Column7.getText().equals(inactive)) {
            tableRow10Column7.setBackgroundColor(Color.GRAY);
        } else {
            tableRow10Column7.setBackgroundColor(Color.RED);
        }
    }//end

    public void setColorsForTableRow11LowerNibble()
    {
        if (tableRow11Column0.getText().equals(call)) {
            tableRow11Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow11Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow11Column1.getText().equals(call)) {
            tableRow11Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow11Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow11Column2.getText().equals(call)) {
            tableRow11Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow11Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow11Column3.getText().equals(call)) {
            tableRow11Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow11Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow11HigherNibble()
    {
        if (tableRow11Column4.getText().equals(call)) {
            tableRow11Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow11Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow11Column5.getText().equals(call)) {
            tableRow11Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow11Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow11Column6.getText().equals(call)) {
            tableRow11Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow11Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow11Column7.getText().equals(call)) {
            tableRow11Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow11Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow12LowerNibble()
    {
        if (tableRow12Column0.getText().equals(call)) {
            tableRow12Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow12Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow12Column1.getText().equals(call)) {
            tableRow12Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow12Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow12Column2.getText().equals(call)) {
            tableRow12Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow12Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow12Column3.getText().equals(call)) {
            tableRow12Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow12Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow12HigherNibble()
    {
        if (tableRow12Column4.getText().equals(call)) {
            tableRow12Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow12Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow12Column5.getText().equals(call)) {
            tableRow12Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow12Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow12Column6.getText().equals(call)) {
            tableRow12Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow12Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow12Column7.getText().equals(call)) {
            tableRow12Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow12Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow13LowerNibble()
    {
        if (tableRow13Column0.getText().equals(call)) {
            tableRow13Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow13Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow13Column1.getText().equals(call)) {
            tableRow13Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow13Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow13Column2.getText().equals(call)) {
            tableRow13Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow13Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow13Column3.getText().equals(call)) {
            tableRow13Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow13Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow13HigherNibble()
    {
        if (tableRow13Column4.getText().equals(call)) {
            tableRow13Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow13Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow13Column5.getText().equals(call)) {
            tableRow13Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow13Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow13Column6.getText().equals(call)) {
            tableRow13Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow13Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow13Column7.getText().equals(call)) {
            tableRow13Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow13Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow14LowerNibble()
    {
        if (tableRow14Column0.getText().equals(call)) {
            tableRow14Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow14Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow14Column1.getText().equals(call)) {
            tableRow14Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow14Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow14Column2.getText().equals(call)) {
            tableRow14Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow14Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow14Column3.getText().equals(call)) {
            tableRow14Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow14Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow14HigherNibble()
    {
        if (tableRow14Column4.getText().equals(call)) {
            tableRow14Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow14Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow14Column5.getText().equals(call)) {
            tableRow14Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow14Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow14Column6.getText().equals(call)) {
            tableRow14Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow14Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow14Column7.getText().equals(call)) {
            tableRow14Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow14Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow15LowerNibble()
    {
        if (tableRow15Column0.getText().equals(call)) {
            tableRow15Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow15Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow15Column1.getText().equals(call)) {
            tableRow15Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow15Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow15Column2.getText().equals(call)) {
            tableRow15Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow15Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow15Column3.getText().equals(call)) {
            tableRow15Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow15Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow15HigherNibble()
    {
        if (tableRow15Column4.getText().equals(call)) {
            tableRow15Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow15Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow15Column5.getText().equals(call)) {
            tableRow15Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow15Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow15Column6.getText().equals(call)) {
            tableRow15Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow15Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow15Column7.getText().equals(call)) {
            tableRow15Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow15Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow16LowerNibble()
    {
        if (tableRow16Column0.getText().equals(call)) {
            tableRow16Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow16Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow16Column1.getText().equals(call)) {
            tableRow16Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow16Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow16Column2.getText().equals(call)) {
            tableRow16Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow16Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow16Column3.getText().equals(call)) {
            tableRow16Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow16Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow16HigherNibble()
    {
        if (tableRow16Column4.getText().equals(call)) {
            tableRow16Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow16Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow16Column5.getText().equals(call)) {
            tableRow16Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow16Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow16Column6.getText().equals(call)) {
            tableRow16Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow16Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow16Column7.getText().equals(call)) {
            tableRow16Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow16Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow17LowerNibble()
    {
        if (tableRow17Column0.getText().equals(call)) {
            tableRow17Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow17Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow17Column1.getText().equals(call)) {
            tableRow17Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow17Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow17Column2.getText().equals(call)) {
            tableRow17Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow17Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow17Column3.getText().equals(call)) {
            tableRow17Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow17Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow17HigherNibble()
    {
        if (tableRow17Column4.getText().equals(call)) {
            tableRow17Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow17Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow17Column5.getText().equals(call)) {
            tableRow17Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow17Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow17Column6.getText().equals(call)) {
            tableRow17Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow17Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow17Column7.getText().equals(call)) {
            tableRow17Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow17Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow18LowerNibble()
    {
        if (tableRow18Column0.getText().equals(call)) {
            tableRow18Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow18Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow18Column1.getText().equals(call)) {
            tableRow18Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow18Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow18Column2.getText().equals(call)) {
            tableRow18Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow18Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow18Column3.getText().equals(call)) {
            tableRow18Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow18Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow18HigherNibble()
    {
        if (tableRow18Column4.getText().equals(call)) {
            tableRow18Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow18Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow18Column5.getText().equals(call)) {
            tableRow18Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow18Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow18Column6.getText().equals(call)) {
            tableRow18Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow18Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow18Column7.getText().equals(call)) {
            tableRow18Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow18Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow19LowerNibble()
    {
        if (tableRow19Column0.getText().equals(call)) {
            tableRow19Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow19Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow19Column1.getText().equals(call)) {
            tableRow19Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow19Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow19Column2.getText().equals(call)) {
            tableRow19Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow19Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow19Column3.getText().equals(call)) {
            tableRow19Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow19Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow19HigherNibble()
    {
        if (tableRow19Column4.getText().equals(call)) {
            tableRow19Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow19Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow19Column5.getText().equals(call)) {
            tableRow19Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow19Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow19Column6.getText().equals(call)) {
            tableRow19Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow19Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow19Column7.getText().equals(call)) {
            tableRow19Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow19Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow20LowerNibble()
    {
        if (tableRow20Column0.getText().equals(call)) {
            tableRow20Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow20Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow20Column1.getText().equals(call)) {
            tableRow20Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow20Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow20Column2.getText().equals(call)) {
            tableRow20Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow20Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow20Column3.getText().equals(call)) {
            tableRow20Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow20Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow20HigherNibble()
    {
        if (tableRow20Column4.getText().equals(call)) {
            tableRow20Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow20Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow20Column5.getText().equals(call)) {
            tableRow20Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow20Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow20Column6.getText().equals(call)) {
            tableRow20Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow20Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow20Column7.getText().equals(call)) {
            tableRow20Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow20Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow21LowerNibble()
    {
        if (tableRow21Column0.getText().equals(call)) {
            tableRow21Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow21Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow21Column1.getText().equals(call)) {
            tableRow21Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow21Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow21Column2.getText().equals(call)) {
            tableRow21Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow21Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow21Column3.getText().equals(call)) {
            tableRow21Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow21Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow21HigherNibble()
    {
        if (tableRow21Column4.getText().equals(call)) {
            tableRow21Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow21Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow21Column5.getText().equals(call)) {
            tableRow21Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow21Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow21Column6.getText().equals(call)) {
            tableRow21Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow21Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow21Column7.getText().equals(call)) {
            tableRow21Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow21Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow22LowerNibble()
    {
        if (tableRow22Column0.getText().equals(call)) {
            tableRow22Column0.setBackgroundColor(Color.RED);
        } else {
            tableRow22Column0.setBackgroundColor(Color.GRAY);
        }
        if (tableRow22Column1.getText().equals(call)) {
            tableRow22Column1.setBackgroundColor(Color.RED);
        } else {
            tableRow22Column1.setBackgroundColor(Color.GRAY);
        }
        if (tableRow22Column2.getText().equals(call)) {
            tableRow22Column2.setBackgroundColor(Color.RED);
        } else {
            tableRow22Column2.setBackgroundColor(Color.GRAY);
        }
        if (tableRow22Column3.getText().equals(call)) {
            tableRow22Column3.setBackgroundColor(Color.RED);
        } else {
            tableRow22Column3.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void setColorsForTableRow22HigherNibble()
    {
        if (tableRow22Column4.getText().equals(call)) {
            tableRow22Column4.setBackgroundColor(Color.RED);
        } else {
            tableRow22Column4.setBackgroundColor(Color.GRAY);
        }
        if (tableRow22Column5.getText().equals(call)) {
            tableRow22Column5.setBackgroundColor(Color.RED);
        } else {
            tableRow22Column5.setBackgroundColor(Color.GRAY);
        }
        if (tableRow22Column6.getText().equals(call)) {
            tableRow22Column6.setBackgroundColor(Color.RED);
        } else {
            tableRow22Column6.setBackgroundColor(Color.GRAY);
        }
        if (tableRow22Column7.getText().equals(call)) {
            tableRow22Column7.setBackgroundColor(Color.RED);
        } else {
            tableRow22Column7.setBackgroundColor(Color.GRAY);
        }
    }//end

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast

}//end of the Class