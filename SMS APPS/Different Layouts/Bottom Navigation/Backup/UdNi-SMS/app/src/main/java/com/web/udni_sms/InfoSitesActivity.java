package com.web.udni_sms;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InfoSitesActivity extends AppCompatActivity {
    TextView sitesInfo;
    String info =   "1) Click on Add Site button to add a new site\n\n" +
                    "2) A form appears and fill in the relevant details\n\n"+
                    "3) After the form is filled, click on Save button and you will be brought back to this same page\n\n"+
                    "4) The site which was added recently will be displayed along with a Message icon for sending SMS and an Edit Icon for updating\nthe site related info\n\n"+
                    "5) To delete the site, hold the text displaying Site name for long and click on OK of the dialog box that appears"
                    ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        sitesInfo = (TextView)findViewById(R.id.info);
        sitesInfo.setText(info);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        System.exit(0);
    }
}
