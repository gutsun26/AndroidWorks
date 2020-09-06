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

import com.web.udni_sms.builder_db.BuilderControllerDB;
import com.web.udni_sms.builder_db.BuilderControllerDB;

import java.util.ArrayList;

public class DeleteSiteAdapter2 extends BaseAdapter
{
    BuilderControllerDB builderControllerDB;
    SQLiteDatabase sqLiteDatabase;
    private Context theContext;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NAME = new ArrayList<String>();
    Dialog dialog;
    public DeleteSiteAdapter2(Context context, ArrayList<String> Id, ArrayList<String> NAME)
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
        builderControllerDB = new BuilderControllerDB(theContext);
        sqLiteDatabase = builderControllerDB.getReadableDatabase();
        LayoutInflater layoutInflater;
        if(convertView == null)
        {
            layoutInflater = (LayoutInflater) theContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.delete_adapter, null);
            viewHolder = new ViewHolder();;
            //viewHolder.checkBox =(CheckBox)convertView.findViewById(R.id.checkToDel);
            viewHolder.NAME = (TextView)convertView.findViewById(R.id.theName);
            //viewHolder.showButton = (ImageButton)convertView.findViewById(R.id.showButton);

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                public void onCheckedChanged(CompoundButton button, boolean isChecked)
                {
                    if(button.isChecked())
                    {
                        String nameOfTheSite = viewHolder.NAME.getText().toString();
                        sqLiteDatabase.execSQL("DELETE FROM BUILDER_CLIENT_DATA WHERE BUILDER_CLIENT_NAME='"+nameOfTheSite+"'");
                        toast("DELETED "+nameOfTheSite);
                        viewHolder.NAME.setVisibility(View.INVISIBLE);
                        viewHolder.checkBox.setVisibility(View.INVISIBLE);
                    }
                }
            });
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
