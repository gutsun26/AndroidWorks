package com.web.g_smsapp.admin;

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

import java.util.ArrayList;
import java.util.List;

public class AdminInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    /******************************MODEL****************************************/
    final String  stringCMD7      = "#07";
    final String  stringCMD8      = "#08";
    final String  stringCMD9      = "#09";
    final String  stringCMD10     = "#10";
    final String  stringCMD11     = "#11";
    final String  stringCMD12     = "#12";
    final String  stringCMD13     = "#13";
    final String  stringCMD14     = "#14";
    final String  stringCMD15     = "#15";
    final String  stringCMD16     = "#16";
    final String  stringCMD17     = "#17";
    final String  stringCMD18     = "#18";
    final String  stringCMD19     = "#19";
    final String  stringCMD20     = "#20";
    final String command1         = "Main Card Input CMD";
    final String command2         = "Main Card Output CMD";
    final String command3         = "Car Input CMD";
    final String command4         = "Car Output CMD";
    final String command5         = "UP Key Input CMD";
    final String command6         = "UP Key Output CMD";
    final String command7         = "Down Key Input CMD";
    final String command8         = "Down Key Output CMD";
    final String command9         = "Car Key Call";
    final String command10        = "Lift Current Status";
    final String command11        = "Lift Error Status";
    final String command12        = "Lift Emergency Stop";
    final String command13        = "Lift Out From Emergency Stop";
    final String command14        = "Start Monitor CMD";


    /******************************VIEW*****************************************/
    Spinner dateChoice,clientChoice,binaryInfo;
    String dateChosen, clientChosen, contentToBeSplit, content1, content0;
    String theNumber;
    List<String> theClient, theDate,theContent;
    ArrayAdapter<String> clientDataAdapter, dateAdapter, contentAdapter;
    Button showButton;
    TextView content, tableIDCommand, tableIDContent;
    TextView    tableRow1Column1,   tableRow1Column2,
                tableRow1Column3,   tableRow1Column4,
                tableRow1Column5,   tableRow1Column6,
                tableRow1Column7,   tableRow1Column8;
    TextView    tableRow2Column1,   tableRow2Column2,
                tableRow2Column3,   tableRow2Column4,
                tableRow2Column5,   tableRow2Column6,
                tableRow2Column7,   tableRow2Column8;
    TextView    tableIDContent1,    tableIDContent2,    tableContent3,
                tableIDContent4,    tableIDContent5,    tableContent6,
                tableIDContent7,    tableIDContent8,    tableContent9,
                tableIDContent10,   tableIDContent11,   tableContent12,
                tableIDContent13,   tableIDContent14;
    TableLayout tMainCardInput, t0,t1,t2, t3;
    final String	zero	=	"0"	;
    final String	one		=	"1"	;
    final String	two		=	"2"	;
    final String	three	=	"3"	;
    final String	four	=	"4"	;
    final String	five	=	"5"	;
    final String	six		=	"6"	;
    final String	seven	=	"7"	;
    final String	eight	=	"8"	;
    final String	nine	=	"9"	;
    final String	hexA	=	"A"	;
    final String	hexB	=	"B"	;
    final String	hexC	=	"C"	;
    final String	hexD	=	"D"	;
    final String	hexE	=	"E"	;
    final String	hexF	=	"F"	;
    /*
    String	binZero		=	"00000000"	;
    String	binOne		=	"00000001"	;
    String	binTwo		=	"00000010"	;
    String	binThree	=	"00000011"	;
    String	binFour		=	"00000100"	;
    String	binFive		=	"00000101"	;
    String	binSix		=	"00000110"	;
    String	binSeven	=	"00000111"	;
    String	binEight	=	"00001000"	;
    String	binNine		=	"00001001"	;
    String	binA		=	"00001010"	;
    String	binB		=	"00001011"	;
    String	binC		=	"00001100"	;
    String	binD		=	"00001101"	;
    String	binE		=	"00001110"	;
    String	binF		=	"00001111"	;

     */
    /*******************************CONTROLLER**********************************/
    /*DATABASE API*/
    AdminSMSReceiverControllerDB AdminSMSReceiverControllerDB;
    SQLiteDatabase theSqLiteDatabase;
    Cursor dateCursor, clientCursor;
    AdminControllerDB adminControllerDB;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_information);
        dateChoice      =       (Spinner)       findViewById(R.id.dateChoice);
        clientChoice    =       (Spinner)       findViewById(R.id.clientChoice);
        showButton      =       (Button)        findViewById(R.id.showButton);
        //binaryInfo      =       (Spinner)   findViewById(R.id.binaryInfo);
        content         =       (TextView)      findViewById(R.id.content);
        tMainCardInput  =       (TableLayout)   findViewById(R.id.tMainCardInput);
        t0              =       (TableLayout)   findViewById(R.id.t0);
        t1              =       (TableLayout)   findViewById(R.id.t1);
        t2              =       (TableLayout)   findViewById(R.id.t2);
        t3              =       (TableLayout)   findViewById(R.id.t3);

        tMainCardInput.setVisibility(View.INVISIBLE);
        t0.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);

        tableRow1Column1	=	(TextView)	findViewById(R.id.	tableRow1Column1	);
        tableRow1Column2	=	(TextView)	findViewById(R.id.	tableRow1Column2	);
        tableRow1Column3	=	(TextView)	findViewById(R.id.	tableRow1Column3	);
        tableRow1Column4	=	(TextView)	findViewById(R.id.	tableRow1Column4	);
        tableRow1Column5	=	(TextView)	findViewById(R.id.	tableRow1Column5	);
        tableRow1Column6	=	(TextView)	findViewById(R.id.	tableRow1Column6	);
        tableRow1Column7	=	(TextView)	findViewById(R.id.	tableRow1Column7	);
        tableRow1Column8	=	(TextView)	findViewById(R.id.	tableRow1Column8	);

        tableRow2Column1	=	(TextView)	findViewById(R.id.	tableRow2Column1	);
        tableRow2Column2	=	(TextView)	findViewById(R.id.	tableRow2Column2	);
        tableRow2Column3	=	(TextView)	findViewById(R.id.	tableRow2Column3	);
        tableRow2Column4	=	(TextView)	findViewById(R.id.	tableRow2Column4	);
        tableRow2Column5	=	(TextView)	findViewById(R.id.	tableRow2Column5	);
        tableRow2Column6	=	(TextView)	findViewById(R.id.	tableRow2Column6	);
        tableRow2Column7	=	(TextView)	findViewById(R.id.	tableRow2Column7	);
        tableRow2Column8	=	(TextView)	findViewById(R.id.	tableRow2Column8	);


        AdminSMSReceiverControllerDB = new AdminSMSReceiverControllerDB(this);
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();

        adminControllerDB = new AdminControllerDB(this);
        sqLiteDatabase= adminControllerDB.getReadableDatabase();

        /*EXTRACTING DATE AND SHOWING IT IN DROPDOWN*/
        theDate = new ArrayList<String>();
        dateCursor = theSqLiteDatabase.rawQuery("SELECT DISTINCT DATE FROM G_SMS",null);
        theDate.clear();
        theDate.add("     ");
        if (dateCursor.moveToFirst())
        {
            do
            {
                theDate.add(dateCursor.getString(dateCursor.getColumnIndex("DATE")));
            }while (dateCursor.moveToNext());
        }
        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, theDate);
        // Drop down layout style - list view with radio button
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        dateChoice.setAdapter(dateAdapter);
        dateChoice.setOnItemSelectedListener(this);


        /*EXTRACTING CLIENT AND SHOWING IT IN THE DROPDOWN*/
        theClient = new ArrayList<String>();
        //Cursor cursor = theSqLiteDatabase.rawQuery("SELECT * FROM G_SMS",null);
        clientCursor = sqLiteDatabase.rawQuery("SELECT LOCATION FROM DEST_NUMBERS",null);
        theClient.clear();
        theClient.add("     ");
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

    public void toast(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }//end of toast

    public void showAllInformation()
    {
        toast(dateChosen+":"+clientChosen);
        sqLiteDatabase= adminControllerDB.getReadableDatabase();
        clientCursor = sqLiteDatabase.rawQuery("SELECT NUMBER FROM DEST_NUMBERS WHERE LOCATION='"+clientChosen+"'",null);
        if (clientCursor.moveToFirst())
        {
            do
            {
                theNumber=clientCursor.getString(clientCursor.getColumnIndex("NUMBER"));
            }while (clientCursor.moveToNext());
        }
        theSqLiteDatabase= AdminSMSReceiverControllerDB.getReadableDatabase();
        clientCursor = theSqLiteDatabase.rawQuery("SELECT CONTENT FROM G_SMS WHERE DATE='"+dateChosen+"' AND ADDRESS='"+theNumber+"'",null);
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
        int n=0;
        //char[] dataArray = null;
        //dataArray = str1.toCharArray();
        toast(String.valueOf(str1.length()));

        if(str1.length() == 7)
        {
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("7"))
            {
                toast("Main Card Input CMD");
                tMainCardInput.setVisibility(View.VISIBLE);
                t0.setVisibility(View.VISIBLE);
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                /****************************CHECKING FOR HIGHER BYTE**********************/
                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(zero))
                {
                    showTheBinZeroHB();
                }//end of if()
                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(one))
                {
                    showTheBinOneHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(two))
                {
                    showTheBinTwoHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(three))
                {
                    showTheBinThreeHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(four))
                {
                    showTheBinFourHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(five))
                {
                    showTheBinFiveHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(six))
                {
                    showTheBinSixHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(seven))
                {
                    showTheBinSevenHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(eight))
                {
                    showTheBinEightHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(nine))
                {
                    showTheBinNineHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(hexA))
                {
                    showTheBinAHB();
                }//end of if()
                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(hexB))
                {
                    showTheBinBHB();
                }//end of if()
                /******************************CHECKING FOR LOWER BYTE**********************/
                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(zero))
                {
                    showTheBinZeroLB();
                }//end of if()
                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(zero))
                {
                    showTheBinZeroLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(one))
                {
                    showTheBinOneLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(two))
                {
                    showTheBinTwoLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(three))
                {
                    showTheBinThreeLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(four))
                {
                    showTheBinFourLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(five))
                {
                    showTheBinFiveLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(six))
                {
                    showTheBinSixLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(seven))
                {
                    showTheBinSevenLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(eight))
                {
                    showTheBinEightLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(nine))
                {
                    showTheBinNineLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(hexA))
                {
                    showTheBinALB();
                }//end of if()
                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(hexB))
                {
                    showTheBinBLB();
                }//end of if()
            }//end if checking Main Card Input CMD
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("8"))
            {
                toast("Main Card Output CMD");
                tMainCardInput.setVisibility(View.VISIBLE);
                t0.setVisibility(View.VISIBLE);
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
                /****************************CHECKING FOR HIGHER BYTE**********************/
                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(zero))
                {
                    showTheBinZeroHB();
                }//end of if()
                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(zero))
                {
                    showTheBinZeroHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(one))
                {
                    showTheBinOneHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(two))
                {
                    showTheBinTwoHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(three))
                {
                    showTheBinThreeHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(four))
                {
                    showTheBinFourHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(five))
                {
                    showTheBinFiveHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(six))
                {
                    showTheBinSixHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(seven))
                {
                    showTheBinSevenHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(eight))
                {
                    showTheBinEightHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(nine))
                {
                    showTheBinNineHB();
                }//end of if()

                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(hexA))
                {
                    showTheBinAHB();
                }//end of if()
                if (String.valueOf(str1.charAt(4)).equalsIgnoreCase(hexB))
                {
                    showTheBinBHB();
                }//end of if()
                /******************************CHECKING FOR LOWER BYTE**********************/
                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(zero))
                {
                    showTheBinZeroLB();
                }//end of if()
                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(zero))
                {
                    showTheBinZeroLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(one))
                {
                    showTheBinOneLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(two))
                {
                    showTheBinTwoLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(three))
                {
                    showTheBinThreeLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(four))
                {
                    showTheBinFourLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(five))
                {
                    showTheBinFiveLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(six))
                {
                    showTheBinSixLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(seven))
                {
                    showTheBinSevenLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(eight))
                {
                    showTheBinEightLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(nine))
                {
                    showTheBinNineLB();
                }//end of if()

                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(hexA))
                {
                    showTheBinALB();
                }//end of if()
                if (String.valueOf(str1.charAt(5)).equalsIgnoreCase(hexB))
                {
                    showTheBinBLB();
                }//end of if()
            }//end if checking Main Card Output CMD
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("9"))
            {
                toast("Car Input CMD");
            }
        }
        else if(str1.length() == 8)
        {
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1") && String.valueOf(str1.charAt(3)).equalsIgnoreCase("0"))
            {
                toast("Car Output CMD");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("1"))
            {
                toast("UP Key Input CMD");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("2"))
            {
                toast("UP Key Output CMD");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("3"))
            {
                toast("Down Key Input CMD");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("4"))
            {
                toast("Down Key Output CMD");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("5"))
            {
                toast("Car Key Call");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("6"))
            {
                toast("Lift Current Status");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("7"))
            {
                toast("Lift Error Status");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("8"))
            {
                toast("Lift Emergency Stop");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("1")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("9"))
            {
                toast("Lift Out From Emergency Stop");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("2")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("0"))
            {
                toast("Start Monitor CMD");
            }
            if(String.valueOf(str1.charAt(2)).equalsIgnoreCase("2")&&String.valueOf(str1.charAt(3)).equalsIgnoreCase("1"))
            {
                toast("Kuch bhi...");
            }
        }
        else if(str1.length() == 21)
        {
            n=7;
            if(String.valueOf(str1.charAt(((str1.length()/n)+1))).equalsIgnoreCase(zero))
            {
                showTheBinZeroLB();
            }//end of if()
            if(String.valueOf(str1.charAt(((str1.length()/n)+1))).equalsIgnoreCase(hexA))
            {
                showTheBinALB();
            }//end of if()
            if(String.valueOf(str1.charAt(((str1.length()/n)+2))).equalsIgnoreCase(hexB))
            {
                showTheBinBLB();
            }//end of if()
        }
    }//end of showAllInformation()

    /******************************************************DEFINING BINARY FOR ALL THE LOWER BYTES****************************/
    public void showTheBinZeroLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of the showTheBinZeroLB()

    public void showTheBinOneLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of the showTheBinOneLB()

    public void showTheBinTwoLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of the showTheBinTwoLB()

    public void showTheBinThreeLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of the showTheBinThreeLB()

    public void showTheBinFourLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of the showTheBinFourLB()

    public void showTheBinFiveLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of the showTheBinFiveLB()

    public void showTheBinSixLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GRAY);
        tableRow1Column6.setBackgroundColor(Color.GRAY);
        tableRow1Column7.setBackgroundColor(Color.GRAY);
        tableRow1Column8.setBackgroundColor(Color.GRAY);
    }//end of the showTheBinSixLB()

    public void showTheBinSevenLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of the showTheBinSevenLB()

    public void showTheBinEightLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GRAY);
        tableRow1Column6.setBackgroundColor(Color.GRAY);
        tableRow1Column7.setBackgroundColor(Color.GRAY);
        tableRow1Column8.setBackgroundColor(Color.GRAY);
    }//end of the showTheBinEightLB()

    public void showTheBinNineLB()
    {
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of the showTheBinNineLB()

    public void showTheBinALB()
    {
        //String	binA		=	"00001010"	;
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of showTheHexALB()

    public void showTheBinBLB()
    {
        //String	binB		=	"00001011"	;
        tableRow1Column1.setBackgroundColor(Color.GRAY);
        tableRow1Column2.setBackgroundColor(Color.GRAY);
        tableRow1Column3.setBackgroundColor(Color.GRAY);
        tableRow1Column4.setBackgroundColor(Color.GRAY);
        tableRow1Column5.setBackgroundColor(Color.GREEN);
        tableRow1Column5.setTextColor(Color.GREEN);
        tableRow1Column6.setBackgroundColor(Color.RED);
        tableRow1Column6.setTextColor(Color.RED);
        tableRow1Column7.setBackgroundColor(Color.GREEN);
        tableRow1Column7.setTextColor(Color.GREEN);
        tableRow1Column8.setBackgroundColor(Color.GREEN);
        tableRow1Column8.setTextColor(Color.GREEN);
    }//end of showTheHexBHB()

    public void showTheBinCLB()
    {

    }//end of showTheHexCLB()

    public void showTheBinDLB()
    {

    }//end of showTheHexDLB()

    public void showTheBinELB()
    {

    }//end of the showTheHexELB()

    public void showTheBinFLB()
    {

    }//end of the showTheHexFLB()

    /********************************************************DEFINING BINARY FOR ALL THE HIGHER BYTES****************************/
    public void showTheBinZeroHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinZeroHB()

    public void showTheBinOneHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinOneHB()

    public void showTheBinTwoHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinTwoHB()

    public void showTheBinThreeHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinThreeHB()

    public void showTheBinFourHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinFourHB()

    public void showTheBinFiveHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinFiveHB()

    public void showTheBinSixHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinSixHB()

    public void showTheBinSevenHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinSevenHB()

    public void showTheBinEightHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinEightHB()

    public void showTheBinNineHB()
    {
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of the showTheBinNineHB()

    public void showTheBinAHB()
    {
        //String	binA		=	"00001010"	;
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.RED);
        tableRow2Column8.setTextColor(Color.RED);
    }//end of showTheHexAHB()

    public void showTheBinBHB()
    {
        //String	binB		=	"00001011"	;
        tableRow2Column1.setBackgroundColor(Color.GRAY);
        tableRow2Column2.setBackgroundColor(Color.GRAY);
        tableRow2Column3.setBackgroundColor(Color.GRAY);
        tableRow2Column4.setBackgroundColor(Color.GRAY);
        tableRow2Column5.setBackgroundColor(Color.GREEN);
        tableRow2Column5.setTextColor(Color.GREEN);
        tableRow2Column6.setBackgroundColor(Color.RED);
        tableRow2Column6.setTextColor(Color.RED);
        tableRow2Column7.setBackgroundColor(Color.GREEN);
        tableRow2Column7.setTextColor(Color.GREEN);
        tableRow2Column8.setBackgroundColor(Color.GREEN);
        tableRow2Column8.setTextColor(Color.GREEN);
    }//end of showTheHexBHB()

    public void showTheBinCHB()
    {

    }//end of showTheHexCHB()

    public void showTheBinDHB()
    {

    }//end of showTheHexDHB()

    public void showTheBinEHB()
    {

    }//end of the showTheHexEHB()

    public void showTheBinFHB()
    {

    }//end of the showTheHexFHB()
}//end of class
