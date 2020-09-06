package com.web.g_smsapp.panel_builder;

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

import com.web.g_smsapp.panel_builder_db.PanelBuilderControllerDB;
import com.web.g_smsapp.panel_builder_db.PanelBuilderSMSReceiverControllerDB;

import java.util.ArrayList;
import java.util.List;

public class PanelBuilderInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
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
    com.web.g_smsapp.panel_builder_db.PanelBuilderSMSReceiverControllerDB panelBuilderSMSReceiverControllerDB;
    SQLiteDatabase theSqLiteDatabase;
    Cursor dateCursor, clientCursor;
    PanelBuilderControllerDB panelBuilderControllerDB;
    SQLiteDatabase sqLiteDatabase;
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_panelbuilder_information);
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
        panelBuilderSMSReceiverControllerDB = new PanelBuilderSMSReceiverControllerDB(this);
        theSqLiteDatabase= panelBuilderSMSReceiverControllerDB.getReadableDatabase();

        panelBuilderControllerDB = new PanelBuilderControllerDB(this);
        sqLiteDatabase= panelBuilderControllerDB.getReadableDatabase();

        /*EXTRACTING DATE AND SHOWING IT IN DROPDOWN*/
        theDate = new ArrayList<String>();
        //dateCursor = theSqLiteDatabase.rawQuery("SELECT DISTINCT DATE FROM G_PANEL_BUILDER_SMS",null);
        theDate.clear();
        theDate.add(clickHere);
        /*
        if (dateCursor.moveToFirst())
        {
            do
            {
                String timeStamp = dateCursor.getString(dateCursor.getColumnIndex("DATE"));
                //String[] splitDate = timeStamp.split("/ ");
                //toast(splitDate[0]);
                theDate.add(timeStamp);
            }while (dateCursor.moveToNext());
        }*/
        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theDate);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateChoice.setAdapter(dateAdapter);
        dateChoice.setOnItemSelectedListener(this);


        /*EXTRACTING CLIENT AND SHOWING IT IN THE DROPDOWN*/
        theClient = new ArrayList<String>();
        //clientCursor = sqLiteDatabase.rawQuery("SELECT LOCATION FROM PANEL_BUILDER_DEST_NUMBERS",null);
        theClient.clear();
        theClient.add(clickHere);
        /*
        if (clientCursor.moveToFirst())
        {
            do
            {
                theClient.add(clientCursor.getString(clientCursor.getColumnIndex("LOCATION")));
            }while (clientCursor.moveToNext());
        }*/
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
        else
        {
            toast(clientChosen+":"+dateChosen);
        }
    }//end of onItemSelected()
    public void onNothingSelected(AdapterView<?> parent)
    {

    }//end of onNothingSelected()

    public void showAllInformation()
    {

    }//end of showAllInformation()

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast
}