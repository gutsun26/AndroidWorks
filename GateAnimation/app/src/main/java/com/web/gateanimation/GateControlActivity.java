package com.web.gateanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class GateControlActivity extends AppCompatActivity
{
    private Button btnSLeft;
    private Button btnSRight;
    private ImageView img;
    TextView gate;
    String gateName = "FRONT GATE";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);
        btnSLeft = (Button)findViewById(R.id.btnSlideLeft);
        btnSRight = (Button)findViewById(R.id.btnSlideRight);
        img = (ImageView)findViewById(R.id.imgvw);
        gate = (TextView)findViewById(R.id.gateName);
        gate.setText(gateName);
        btnSLeft.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Animation animSlideLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_left);
                animSlideLeft.setDuration(10000);
                img.startAnimation(animSlideLeft);
            }
        });
        btnSRight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Animation animSlideRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_right);
                animSlideRight.setDuration(10000);
                img.startAnimation(animSlideRight);
            }
        });

    }
}
