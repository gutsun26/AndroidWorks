package com.web.gatecontrol.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.web.gatecontrol.GateControlActivity;
import com.web.gatecontrol.R;
import com.web.gatecontrol.ScanQR;
import com.web.gatecontrol.db.UserDB;

import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;

public class HomeFragment extends Fragment
{
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter adapter;
    ListView listView;
    UserDB userDB;
    SQLiteDatabase database;
    int rowCount;
    private static final int RESULT_OK = -1;
    private HomeViewModel homeViewModel;
    FloatingActionButton btnScanBarcode;
    String theAppID, gateName, serverURI, userName, passWord;
    TextView theAppIDText, gateNameText, serverURIText, userNameText, passWordText;
    String query = "SELECT * FROM GATES";
    Button click;
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        userDB = new UserDB(getContext());
        database = userDB.getReadableDatabase();
        listView = root.findViewById(R.id.listView);
        //theAppIDText = root.findViewById(R.id.theAppIDText);

        //gateNameText = root.findViewById(R.id.gateNameText);

        /*serverURIText = root.findViewById(R.id.serverURIText);
        userNameText = root.findViewById(R.id.userNameText);
        passWordText = root.findViewById(R.id.passWordText);*/

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                view.setBackgroundColor(Color.RED);
                TextView gateName = (TextView)view;
                gateName.setTextColor(Color.WHITE);
                String gate = gateName.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("gateName",gate);
                Intent intent = new Intent(getContext(), GateControlActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        click = root.findViewById(R.id.click);
        userDB = new UserDB(getContext());
        database = userDB.getReadableDatabase();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                //deleteTable();
            }
        });
        return root;
    }
    public void getData()
    {
        Cursor cursor = database.rawQuery(query,null);
        rowCount = cursor.getCount();
        if(rowCount!=0)
        {
            if (cursor.moveToFirst())
            {
                do
                {
                    /*
                    theAppIDText.setText(cursor.getString(cursor.getColumnIndex("APP_ID")));
                    gateNameText.setText(cursor.getString(cursor.getColumnIndex("GATE_NAME")));
                    serverURIText.setText(cursor.getString(cursor.getColumnIndex("SERVER")));
                    userNameText.setText(cursor.getString(cursor.getColumnIndex("USERNAME")));
                    passWordText.setText(cursor.getString(cursor.getColumnIndex("PASSWORD")));
                    */
                    arrayList.add(cursor.getString(cursor.getColumnIndex("GATE_NAME")));
                    adapter.notifyDataSetChanged();
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.item1:
                deleteTable();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deleteTable()
    {
        database.execSQL("DROP TABLE GATES");
        toast("Deleted GATES table");
    }
    public void toast(String s)
    {
        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
    }
}