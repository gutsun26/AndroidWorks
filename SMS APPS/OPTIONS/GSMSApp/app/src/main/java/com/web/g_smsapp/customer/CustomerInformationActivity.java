package com.web.g_smsapp.customer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
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
import com.web.g_smsapp.admin_db.AdminControllerDB;
import com.web.g_smsapp.admin_db.AdminSMSReceiverControllerDB;
import com.web.g_smsapp.customer_db.CustomerControllerDB;
import com.web.g_smsapp.customer_db.CustomerSMSReceiverControllerDB;

import org.w3c.dom.Text;

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
    Button showButton;
    TextView content,floorUpKeysTimeStamp,floorDownKeysTimeStamp;

    TableLayout tMainCardTerminalInput,         tMainCardInput;
    TableLayout tMainCardTerminalInputHBCols,   tMainCardTerminalInputHBData,
                tMainCardTerminalInputLBCols,   tMainCardTerminalInputLBData,
                tMainCardInputHBCols,           tMainCardInputHBData,
                tMainCardInputLBCols,           tMainCardInputLBData;
    TextView    mup,    mt,
                brk,    opc,
                ard,    stpr,
                swlr,   mdn;
    TextView    utl,    dtl,
                fup,    fdn,
                fup1,   fdn1;

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
    TextView    liftStopStatus;
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
        //floorUpKeysTimeStamp    =(TextView)     findViewById(R.id.floorUpKeysTimeStamp);
        //floorDownKeysTimeStamp  =(TextView)     findViewById(R.id.floorDownKeysTimeStamp);
        liftStopStatus          =(TextView)     findViewById(R.id.liftStopStatus);
        dateChoice              =(Spinner)      findViewById(R.id.dateChoice);
        clientChoice            =(Spinner)      findViewById(R.id.clientChoice);
        showButton              =(Button)       findViewById(R.id.showButton);
        content                 =(TextView)     findViewById(R.id.content);
        textCharacter_I         =(TextView)     findViewById(R.id.textCharacter_I);
        textCharacter_A         =(TextView)     findViewById(R.id.textCharacter_A);
        //textCharacter_C         =(TextView)     findViewById(R.id.textCharacter_C);
        //textCharacter_Cx        =(TextView)     findViewById(R.id.textCharacter_Cx);
        //textDash                =(TextView)     findViewById(R.id.textDash);
        //textDassh               =(TextView)     findViewById(R.id.textDassh);
        /*
        tMainCardInput                  =       (TableLayout)      findViewById(R.id.tMainCardInput);
        tMainCardInputHBCols            =       (TableLayout)      findViewById(R.id.tMainCardInputHBCols);
        tMainCardInputHBData            =       (TableLayout)      findViewById(R.id.tMainCardInputHBData);
        tMainCardInputLBData            =       (TableLayout)      findViewById(R.id.tMainCardInputLBData);
        */
        swlr=findViewById(R.id.swlr);
        stpr=findViewById(R.id.stpr);
        ard=findViewById(R.id.ard);
        opc=findViewById(R.id.opc);
        brk=findViewById(R.id.brk);
        mt=findViewById(R.id.mt);
        mup=findViewById(R.id.mup);
        mdn=findViewById(R.id.mdn);

        utl	=	findViewById(R.id.	utl	);	utl=findViewById(R.id.utl);
        dtl	=	findViewById(R.id.	dtl	);	dtl=findViewById(R.id.dtl);
        fup	=	findViewById(R.id.	fup	);	fup=findViewById(R.id.fup);
        fdn	=	findViewById(R.id.	fdn	);	fdn=findViewById(R.id.fdn);
        fup1	=	findViewById(R.id.	fup1	);	fup1=findViewById(R.id.fup1);
        fdn1	=	findViewById(R.id.	fdn1	);	fdn1=findViewById(R.id.fdn1);

        /*
        mdn=(TextView)	findViewById(R.id.	mdn);
        mup=(TextView)	findViewById(R.id.	mup);
        mt=(TextView)	findViewById(R.id.	mt);
        brk=(TextView)	findViewById(R.id.	brk);
        opc=(TextView)	findViewById(R.id.	opc);
        ard=(TextView)	findViewById(R.id.	ard);
        stpr=(TextView)	findViewById(R.id.	stpr);
        slwr=(TextView)	findViewById(R.id.	slwr);

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
        */
        /*FOR CBC INPUTS*//*
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
        tableRow8Column7=(TextView)findViewById(R.id.tableRow8Column7);*/

        /*FOR CBC OUTPUTS*//*
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
        tableRow10Column7=(TextView)  findViewById(R.id.tableRow10Column7);*/

        /*FOR UP KEYS*//*
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
        tableRow14Column7=(TextView)findViewById(R.id.tableRow14Column7);*/

        /*FOR DOWN KEYS*//*
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
        */

        textCharacter_I.setBackgroundColor(Color.GRAY);
        textCharacter_A.setBackgroundColor(Color.RED);
        //textCharacter_C.setBackgroundColor(Color.RED);
        //textDash.setBackgroundColor(Color.GRAY);
        //textCharacter_Cx.setBackgroundColor(Color.GRAY);
        //textDassh.setBackgroundColor(Color.GRAY);

        content.setVisibility(View.VISIBLE);
        //content.setText(choice);
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
                //showRelevantInformation();
                showAllInformation();
            }
        });

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
        content.setText(str1);
        decodeChoice = str1;

        /*DECODING THE STRING FROM THE DATABASE TO SHOWCASE THE UI AS PER THE CONTENT OF THE STRING*/
        String stringSplit[] = decodeChoice.split("\n");
        for(int s=0; s<stringSplit.length; s++)
        {
            if(!String.valueOf(stringSplit[s]).isEmpty())
            {
                /*FOR MAIN CARD INPUT*/
                if (String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("7"))
                {
                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexZero))
                    {
                        brk.setText(String.valueOf(binZero.charAt(0)));
                        mt.setText(String.valueOf(binZero.charAt(1)));
                        mup.setText(String.valueOf(binZero.charAt(2)));
                        mdn.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexOne))
                    {
                        brk.setText(String.valueOf(binOne.charAt(0)));
                        mt.setText(String.valueOf(binOne.charAt(1)));
                        mup.setText(String.valueOf(binOne.charAt(2)));
                        mdn.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexTwo))
                    {
                        brk.setText(String.valueOf(binTwo.charAt(0)));
                        mt.setText(String.valueOf(binTwo.charAt(1)));
                        mup.setText(String.valueOf(binTwo.charAt(2)));
                        mdn.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexThree))
                    {
                        brk.setText(String.valueOf(binThree.charAt(0)));
                        mt.setText(String.valueOf(binThree.charAt(1)));
                        mup.setText(String.valueOf(binThree.charAt(2)));
                        mdn.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFour))
                    {
                        brk.setText(String.valueOf(binFour.charAt(0)));
                        mt.setText(String.valueOf(binFour.charAt(1)));
                        mup.setText(String.valueOf(binFour.charAt(2)));
                        mdn.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexFive))
                    {
                        opc.setText(String.valueOf(binFive.charAt(0)));
                        ard.setText(String.valueOf(binFive.charAt(1)));
                        stpr.setText(String.valueOf(binFive.charAt(2)));
                        swlr.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSix))
                    {
                        brk.setText(String.valueOf(binSix.charAt(0)));
                        mt.setText(String.valueOf(binSix.charAt(1)));
                        mup.setText(String.valueOf(binSix.charAt(2)));
                        mdn.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexSeven))
                    {
                        brk.setText(String.valueOf(binSeven.charAt(0)));
                        mt.setText(String.valueOf(binSeven.charAt(1)));
                        mup.setText(String.valueOf(binSeven.charAt(2)));
                        mdn.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexEight))
                    {
                        brk.setText(String.valueOf(binEight.charAt(0)));
                        mt.setText(String.valueOf(binEight.charAt(1)));
                        mup.setText(String.valueOf(binEight.charAt(2)));
                        mdn.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexNine))
                    {
                        brk.setText(String.valueOf(binNine.charAt(0)));
                        mt.setText(String.valueOf(binNine.charAt(1)));
                        mup.setText(String.valueOf(binNine.charAt(2)));
                        mdn.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexA))
                    {
                        brk.setText(String.valueOf(binA.charAt(0)));
                        mt.setText(String.valueOf(binA.charAt(1)));
                        mup.setText(String.valueOf(binA.charAt(2)));
                        mdn.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexB))
                    {
                        brk.setText(String.valueOf(binB.charAt(0)));
                        mt.setText(String.valueOf(binB.charAt(1)));
                        mup.setText(String.valueOf(binB.charAt(2)));
                        mdn.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexC))
                    {
                        brk.setText(String.valueOf(binC.charAt(0)));
                        mt.setText(String.valueOf(binC.charAt(1)));
                        mup.setText(String.valueOf(binC.charAt(2)));
                        mdn.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexD))
                    {
                        brk.setText(String.valueOf(binD.charAt(0)));
                        mt.setText(String.valueOf(binD.charAt(1)));
                        mup.setText(String.valueOf(binD.charAt(2)));
                        mdn.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexE))
                    {
                        brk.setText(String.valueOf(binE.charAt(0)));
                        mt.setText(String.valueOf(binE.charAt(1)));
                        mup.setText(String.valueOf(binE.charAt(2)));
                        mdn.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 1))).equalsIgnoreCase(hexF))
                    {
                        brk.setText(String.valueOf(binF.charAt(0)));
                        mt.setText(String.valueOf(binF.charAt(1)));
                        mup.setText(String.valueOf(binF.charAt(2)));
                        mdn.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableMainCardInputLowerNibble();
                    }
                    /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexZero))
                    {
                        swlr.setText(String.valueOf(binZero.charAt(0)));
                        stpr.setText(String.valueOf(binZero.charAt(1)));
                        ard.setText(String.valueOf(binZero.charAt(2)));
                        opc.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexOne))
                    {
                        swlr.setText(String.valueOf(binOne.charAt(0)));
                        stpr.setText(String.valueOf(binOne.charAt(1)));
                        ard.setText(String.valueOf(binOne.charAt(2)));
                        opc.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexTwo))
                    {
                        swlr.setText(String.valueOf(binTwo.charAt(0)));
                        stpr.setText(String.valueOf(binTwo.charAt(1)));
                        ard.setText(String.valueOf(binTwo.charAt(2)));
                        opc.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexThree))
                    {
                        swlr.setText(String.valueOf(binThree.charAt(0)));
                        stpr.setText(String.valueOf(binThree.charAt(1)));
                        ard.setText(String.valueOf(binThree.charAt(2)));
                        opc.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFour))
                    {
                        swlr.setText(String.valueOf(binFour.charAt(0)));
                        stpr.setText(String.valueOf(binFour.charAt(1)));
                        ard.setText(String.valueOf(binFour.charAt(2)));
                        opc.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexFive))
                    {
                        swlr.setText(String.valueOf(binFive.charAt(0)));
                        stpr.setText(String.valueOf(binFive.charAt(1)));
                        ard.setText(String.valueOf(binFive.charAt(2)));
                        opc.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSix))
                    {
                        swlr.setText(String.valueOf(binSix.charAt(0)));
                        stpr.setText(String.valueOf(binSix.charAt(1)));
                        ard.setText(String.valueOf(binSix.charAt(2)));
                        opc.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexSeven))
                    {
                        swlr.setText(String.valueOf(binSeven.charAt(0)));
                        stpr.setText(String.valueOf(binSeven.charAt(1)));
                        ard.setText(String.valueOf(binSeven.charAt(2)));
                        opc.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexEight))
                    {
                        swlr.setText(String.valueOf(binEight.charAt(0)));
                        stpr.setText(String.valueOf(binEight.charAt(1)));
                        ard.setText(String.valueOf(binEight.charAt(2)));
                        opc.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexNine))
                    {
                        swlr.setText(String.valueOf(binNine.charAt(0)));
                        stpr.setText(String.valueOf(binNine.charAt(1)));
                        ard.setText(String.valueOf(binNine.charAt(2)));
                        opc.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexA))
                    {
                        swlr.setText(String.valueOf(binA.charAt(0)));
                        stpr.setText(String.valueOf(binA.charAt(1)));
                        ard.setText(String.valueOf(binA.charAt(2)));
                        opc.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexB))
                    {
                        swlr.setText(String.valueOf(binB.charAt(0)));
                        stpr.setText(String.valueOf(binB.charAt(1)));
                        ard.setText(String.valueOf(binB.charAt(2)));
                        opc.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexC))
                    {
                        swlr.setText(String.valueOf(binC.charAt(0)));
                        stpr.setText(String.valueOf(binC.charAt(1)));
                        ard.setText(String.valueOf(binC.charAt(2)));
                        opc.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexD))
                    {
                        swlr.setText(String.valueOf(binD.charAt(0)));
                        stpr.setText(String.valueOf(binD.charAt(1)));
                        ard.setText(String.valueOf(binD.charAt(2)));
                        opc.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexE))
                    {
                        swlr.setText(String.valueOf(binE.charAt(0)));
                        stpr.setText(String.valueOf(binE.charAt(1)));
                        ard.setText(String.valueOf(binE.charAt(2)));
                        opc.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }

                    if (String.valueOf(stringSplit[s].charAt((stringSplit[s].length() - 2))).equalsIgnoreCase(hexF))
                    {
                        swlr.setText(String.valueOf(binF.charAt(0)));
                        stpr.setText(String.valueOf(binF.charAt(1)));
                        ard.setText(String.valueOf(binF.charAt(2)));
                        opc.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableMainCardInputHigherNibble();
                    }
                }
                /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/
                /*END OF MAIN CARD INPUT*/

                /*FOR MAIN CARD TERMINAL INPUT*/
                if (String.valueOf(stringSplit[s].charAt(2)).equalsIgnoreCase("8"))
                {
                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexZero))
                    {
                        fup.setText(String.valueOf(binZero.charAt(0)));
                        fdn.setText(String.valueOf(binZero.charAt(1)));
                        fup1.setText(String.valueOf(binZero.charAt(2)));
                        fdn1.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexOne))
                    {
                        fup.setText(String.valueOf(binOne.charAt(0)));
                        fdn.setText(String.valueOf(binOne.charAt(1)));
                        fup1.setText(String.valueOf(binOne.charAt(2)));
                        fdn1.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexTwo))
                    {
                        fup.setText(String.valueOf(binTwo.charAt(0)));
                        fdn.setText(String.valueOf(binTwo.charAt(1)));
                        fup1.setText(String.valueOf(binTwo.charAt(2)));
                        fdn1.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexThree))
                    {
                        fup.setText(String.valueOf(binThree.charAt(0)));
                        fdn.setText(String.valueOf(binThree.charAt(1)));
                        fup1.setText(String.valueOf(binThree.charAt(2)));
                        fdn1.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFour))
                    {
                        fup.setText(String.valueOf(binFour.charAt(0)));
                        fdn.setText(String.valueOf(binFour.charAt(1)));
                        fup1.setText(String.valueOf(binFour.charAt(2)));
                        fdn1.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexFive))
                    {
                        dtl.setText(String.valueOf(binFive.charAt(0)));
                        utl.setText(String.valueOf(binFive.charAt(1)));
                        stpr.setText(String.valueOf(binFive.charAt(2)));
                        swlr.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSix))
                    {
                        fup.setText(String.valueOf(binSix.charAt(0)));
                        fdn.setText(String.valueOf(binSix.charAt(1)));
                        fup1.setText(String.valueOf(binSix.charAt(2)));
                        fdn1.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexSeven))
                    {
                        fup.setText(String.valueOf(binSeven.charAt(0)));
                        fdn.setText(String.valueOf(binSeven.charAt(1)));
                        fup1.setText(String.valueOf(binSeven.charAt(2)));
                        fdn1.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexEight))
                    {
                        fup.setText(String.valueOf(binEight.charAt(0)));
                        fdn.setText(String.valueOf(binEight.charAt(1)));
                        fup1.setText(String.valueOf(binEight.charAt(2)));
                        fdn1.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexNine))
                    {
                        fup.setText(String.valueOf(binNine.charAt(0)));
                        fdn.setText(String.valueOf(binNine.charAt(1)));
                        fup1.setText(String.valueOf(binNine.charAt(2)));
                        fdn1.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexA))
                    {
                        fup.setText(String.valueOf(binA.charAt(0)));
                        fdn.setText(String.valueOf(binA.charAt(1)));
                        fup1.setText(String.valueOf(binA.charAt(2)));
                        fdn1.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexB))
                    {
                        fup.setText(String.valueOf(binB.charAt(0)));
                        fdn.setText(String.valueOf(binB.charAt(1)));
                        fup1.setText(String.valueOf(binB.charAt(2)));
                        fdn1.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexC))
                    {
                        fup.setText(String.valueOf(binC.charAt(0)));
                        fdn.setText(String.valueOf(binC.charAt(1)));
                        fup1.setText(String.valueOf(binC.charAt(2)));
                        fdn1.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexD))
                    {
                        fup.setText(String.valueOf(binD.charAt(0)));
                        fdn.setText(String.valueOf(binD.charAt(1)));
                        fup1.setText(String.valueOf(binD.charAt(2)));
                        fdn1.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexE))
                    {
                        fup.setText(String.valueOf(binE.charAt(0)));
                        fdn.setText(String.valueOf(binE.charAt(1)));
                        fup1.setText(String.valueOf(binE.charAt(2)));
                        fdn1.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-1))).equalsIgnoreCase(hexF))
                    {
                        fup.setText(String.valueOf(binF.charAt(0)));
                        fdn.setText(String.valueOf(binF.charAt(1)));
                        fup1.setText(String.valueOf(binF.charAt(2)));
                        fdn1.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableMainCardTerminalInputLowerNibble();
                    }
                    /***************************** END OF BASED ON FIRST CHARACTER FROM RIGHT **************************/

                    /******************************** BASED ON SECOND CHARACTER FROM RIGHT ****************************/

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexZero))
                    {
                        utl.setText(String.valueOf(binZero.charAt(2)));
                        dtl.setText(String.valueOf(binZero.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexOne))
                    {
                        utl.setText(String.valueOf(binOne.charAt(2)));
                        dtl.setText(String.valueOf(binOne.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexTwo))
                    {
                        utl.setText(String.valueOf(binTwo.charAt(2)));
                        dtl.setText(String.valueOf(binTwo.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexThree))
                    {
                        utl.setText(String.valueOf(binThree.charAt(2)));
                        dtl.setText(String.valueOf(binThree.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFour))
                    {
                        utl.setText(String.valueOf(binFour.charAt(2)));
                        dtl.setText(String.valueOf(binFour.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexFive))
                    {
                        utl.setText(String.valueOf(binFive.charAt(2)));
                        dtl.setText(String.valueOf(binFive.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSix))
                    {
                        utl.setText(String.valueOf(binSix.charAt(2)));
                        dtl.setText(String.valueOf(binSix.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexSeven))
                    {
                        utl.setText(String.valueOf(binSeven.charAt(2)));
                        dtl.setText(String.valueOf(binSeven.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexEight))
                    {
                        utl.setText(String.valueOf(binEight.charAt(2)));
                        dtl.setText(String.valueOf(binEight.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexNine))
                    {
                        utl.setText(String.valueOf(binNine.charAt(2)));
                        dtl.setText(String.valueOf(binNine.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexA))
                    {
                        utl.setText(String.valueOf(binA.charAt(2)));
                        dtl.setText(String.valueOf(binA.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexB))
                    {
                        utl.setText(String.valueOf(binB.charAt(2)));
                        dtl.setText(String.valueOf(binB.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexC))
                    {
                        utl.setText(String.valueOf(binC.charAt(2)));
                        dtl.setText(String.valueOf(binC.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexD))
                    {
                        utl.setText(String.valueOf(binD.charAt(2)));
                        dtl.setText(String.valueOf(binD.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexE))
                    {
                        utl.setText(String.valueOf(binE.charAt(2)));
                        dtl.setText(String.valueOf(binE.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    if(String.valueOf(stringSplit[s].charAt((stringSplit[s].length()-2))).equalsIgnoreCase(hexF))
                    {
                        utl.setText(String.valueOf(binF.charAt(2)));
                        dtl.setText(String.valueOf(binF.charAt(3)));
                        setColorsForTableMainCardTerminalInputHigherNibble();
                    }

                    /********************************** END OF SECOND CHARACTER FROM RIGHT ****************************/
                }

            }
        }
        /*END OF UI SHOWCASING AS PER THE CONTENT OF THE STRING*/
    }//end of showAllInformation()
    public void setColorsForTableMainCardInputLowerNibble()
    {
        if(mdn.getText().equals(inactive))
        {
            mdn.setBackgroundColor(Color.GRAY);
        }
        else
        {
            mdn.setBackgroundColor(Color.RED);
        }
        if(mup.getText().equals(inactive))
        {
            mup.setBackgroundColor(Color.GRAY);
        }
        else
        {
            mup.setBackgroundColor(Color.RED);
        }
        if(mt.getText().equals(inactive))
        {
            mt.setBackgroundColor(Color.GRAY);
        }
        else
        {
            mt.setBackgroundColor(Color.RED);
        }
        if(brk.getText().equals(inactive))
        {
            brk.setBackgroundColor(Color.GRAY);
        }
        else
        {
            brk.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableMainCardInputLowerNibble()

    public void setColorsForTableMainCardInputHigherNibble()
    {
        if(opc.getText().equals(inactive))
        {
            opc.setBackgroundColor(Color.GRAY);
        }
        else
        {
            opc.setBackgroundColor(Color.RED);
        }
        if(ard.getText().equals(inactive))
        {
            ard.setBackgroundColor(Color.GRAY);
        }
        else
        {
            ard.setBackgroundColor(Color.RED);
        }
        if(stpr.getText().equals(inactive))
        {
            stpr.setBackgroundColor(Color.GRAY);
        }
        else
        {
            stpr.setBackgroundColor(Color.RED);
        }
        if(swlr.getText().equals(inactive))
        {
            swlr.setBackgroundColor(Color.GRAY);
        }
        else
        {
            swlr.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableMainCardInputHigherNibble()
    public void setColorsForTableMainCardTerminalInputLowerNibble()
    {
        if(fdn1.getText().equals(inactive))
        {
            fdn1.setBackgroundColor(Color.GRAY);
        }
        else
        {
            fdn1.setBackgroundColor(Color.RED);
        }
        if(fup1.getText().equals(inactive))
        {
            fup1.setBackgroundColor(Color.GRAY);
        }
        else
        {
            fup1.setBackgroundColor(Color.RED);
        }
        if(fdn.getText().equals(inactive))
        {
            fdn.setBackgroundColor(Color.GRAY);
        }
        else
        {
            fdn.setBackgroundColor(Color.RED);
        }
        if(fup.getText().equals(inactive))
        {
            fup.setBackgroundColor(Color.GRAY);
        }
        else
        {
            fup.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableMainCardInputLowerNibble()

    public void setColorsForTableMainCardTerminalInputHigherNibble()
    {
        if(dtl.getText().equals(inactive))
        {
            dtl.setBackgroundColor(Color.GRAY);
        }
        else
        {
            dtl.setBackgroundColor(Color.RED);
        }
        if(utl.getText().equals(inactive))
        {
            utl.setBackgroundColor(Color.GRAY);
        }
        else
        {
            utl.setBackgroundColor(Color.RED);
        }
    }//end of setColorsForTableMainCardInputHigherNibble()

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast

}//end of the Class