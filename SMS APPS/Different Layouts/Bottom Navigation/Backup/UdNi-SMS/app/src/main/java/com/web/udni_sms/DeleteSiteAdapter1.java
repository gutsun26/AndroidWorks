package com.web.udni_sms;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.drm.DrmStore;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.web.udni_sms.admin_db.AdminControllerDB;

import java.util.ArrayList;

public class DeleteSiteAdapter1 extends BaseAdapter
{
    AdminControllerDB adminControllerDB;
    SQLiteDatabase sqLiteDatabase;
    private Context theContext;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NAME = new ArrayList<String>();
    Dialog dialog;
    public DeleteSiteAdapter1(Context context, ArrayList<String> Id, ArrayList<String> NAME)
    {
        this.theContext = context;
        this.Id = Id;
        this.NAME = NAME;
    }
    @Override
    public int getCount() {
        return Id.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;
        adminControllerDB = new AdminControllerDB(theContext);
        sqLiteDatabase = adminControllerDB.getReadableDatabase();
        LayoutInflater layoutInflater;
        if(convertView == null)
        {
            layoutInflater = (LayoutInflater) theContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.delete_adapter, null);
            viewHolder = new ViewHolder();;
            //viewHolder.checkBox =(CheckBox)convertView.findViewById(R.id.checkToDel);
            viewHolder.NAME = (TextView)convertView.findViewById(R.id.theName);
            //viewHolder.showButton = (ImageButton)convertView.findViewById(R.id.showButton);

            viewHolder.NAME.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String nameOfTheSite = viewHolder.NAME.getText().toString();
                    sqLiteDatabase.execSQL("DELETE FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+nameOfTheSite+"'");
                    toast("DELETED "+nameOfTheSite);
                    viewHolder.NAME.setVisibility(View.INVISIBLE);
                    viewHolder.checkBox.setVisibility(View.INVISIBLE);
                    return false;
                }
            });
            /*
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton button, boolean isChecked)
                {
                    if(button.isChecked())
                    {
                        String nameOfTheSite = viewHolder.NAME.getText().toString();
                        sqLiteDatabase.execSQL("DELETE FROM ADMIN_CLIENT_DATA WHERE ADMIN_CLIENT_NAME='"+nameOfTheSite+"'");
                        toast("DELETED "+nameOfTheSite);
                        viewHolder.NAME.setVisibility(View.INVISIBLE);
                        viewHolder.checkBox.setVisibility(View.INVISIBLE);
                    }
                }
            });*/
            /*
            viewHolder.showButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(theContext);
                    builder.setTitle("DELETE?");
                    String nameOfTheSite = viewHolder.NAME.getText().toString();
                    NAME.add(nameOfTheSite);
                    ListView listClients     = new ListView(theContext);
                    ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(theContext, android.R.layout.simple_list_item_1, android.R.id.text1, NAME);
                    listClients.setAdapter(modeAdapter);
                    builder.setView(listClients);
                    builder.setPositiveButton("OK",new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1)
                        {
                            System.exit(0);
                        }
                    });
                    dialog = builder.create();
                    dialog.show();
                }
            });
            */
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder =(ViewHolder)convertView.getTag();
        }
        viewHolder.NAME.setText(NAME.get(position));
        //viewHolder.checkBox.setTag(NAME.get(position));
        return convertView;
    }
    public class ViewHolder
    {
        TextView NAME;
        CheckBox checkBox;
        ImageButton showButton;
    }
    public void toast(String s)
    {
        Toast.makeText(theContext,s,Toast.LENGTH_LONG).show();
    }
}
