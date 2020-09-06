package com.web.udni_sms.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.web.udni_sms.ChangeNumbersActivity3;
import com.web.udni_sms.R;

public class HomeFragment3 extends Fragment {
    View root;
    ImageButton imageButton3_0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home3, container, false);
        imageButton3_0 = root.findViewById(R.id.imageButton3_0);

        imageButton3_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNumberFunction();
            }
        });

        return root;
    }

    public void changeNumberFunction()
    {
        Bundle choiceConfig = new Bundle();
        choiceConfig.putString("KEY", "3");
        Intent intent = new Intent(getContext(), ChangeNumbersActivity3.class);
        intent.putExtras(choiceConfig);
        startActivity(intent);
    }//end of changeNumberFunction()

    public void toast(String message)
    {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }//end of toast
}
