package com.web.udni_sms.builder_db;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.web.udni_sms.DeleteSiteActivityFrag1;
import com.web.udni_sms.DeleteSiteActivityFrag2;
import com.web.udni_sms.EditSiteInformationActivity2;
import com.web.udni_sms.R;
import com.web.udni_sms.SendSMSActivity2;

import java.util.ArrayList;

public class BuilderCustomAdapter extends BaseAdapter
{
    private Context mContext;
    BuilderControllerDB controldb,builderControllerDB;
    SQLiteDatabase db,sqLiteDatabase;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> BUILDER_CLIENT_NAME = new ArrayList<String>();
    private ArrayList<String> BUILDER_CLIENT_NUMBER = new ArrayList<String>();
    private ArrayList<String> BUILDER_CLIENT_ADDRESS = new ArrayList<String>();
    private ArrayList<ImageView> BUILDER_IMAGE = new ArrayList<ImageView>();
    int count=0;
    String theCount;
    String theID="ID";
    public BuilderCustomAdapter(Context  context, ArrayList<String> Id, ArrayList<String> NAME)
    {
        this.mContext = context;
        this.Id = Id;
        this.BUILDER_CLIENT_NAME=NAME;
        //this.BUILDER_CLIENT_NUMBER=NUMBER;
        //this.BUILDER_CLIENT_ADDRESS=ADDRESS;
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
        final BuilderCustomAdapter.viewHolder holder;
        controldb =new BuilderControllerDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.adapter_layout, null);
            holder = new BuilderCustomAdapter.viewHolder();
            holder.img = (ImageView)convertView.findViewById(R.id.imageSite);
            holder.img.setId(++count);
            holder.name = (TextView)convertView.findViewById(R.id.idName);
            holder.edit  = (ImageView)convertView.findViewById(R.id.editSite);
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //toast(String.valueOf(holder.img.getId()));
                    //theCount = String.valueOf(holder.img.getId());
                    toast("Please wait...");
                    holder.img.setBackgroundColor(Color.GREEN);
                    String nameOfTheSite = holder.name.getText().toString();
                    //toast(nameOfTheSite);
                    Bundle countBundle = new Bundle();
                    countBundle.putString(theID,nameOfTheSite);
                    Intent intent = new Intent(mContext, SendSMSActivity2.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(countBundle);
                    mContext.startActivity(intent);
                }
            });

            holder.name.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String nameOfTheSite = holder.name.getText().toString();/*
                    builderControllerDB = new BuilderControllerDB(mContext);
                    sqLiteDatabase = builderControllerDB.getReadableDatabase();
                    sqLiteDatabase.execSQL("DELETE FROM BUILDER_CLIENT_DATA WHERE BUILDER_CLIENT_NAME='"+nameOfTheSite+"'");*/
                    Bundle countBundle = new Bundle();
                    countBundle.putString(theID,nameOfTheSite);
                    Intent intent = new Intent(mContext, DeleteSiteActivityFrag2.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(countBundle);
                    mContext.startActivity(intent);
                    return true;
                }
            });

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    theCount = String.valueOf(holder.img.getId());
                    holder.edit.setBackgroundColor(Color.GREEN);
                    //toast(theCount);
                    String nameOfTheSite = holder.name.getText().toString();
                    Bundle countBundle = new Bundle();
                    countBundle.putString(theID,nameOfTheSite);
                    Intent intent = new Intent(mContext, EditSiteInformationActivity2.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(countBundle);
                    mContext.startActivity(intent);
                }
            });

            convertView.setTag(holder);
        }
        else {
            holder = (BuilderCustomAdapter.viewHolder) convertView.getTag();
        }
        //holder.img.setImageBitmap(BUILDER_IMAGE.add());
        holder.name.setText(BUILDER_CLIENT_NAME.get(position));
        //holder.number.setText(BUILDER_CLIENT_NUMBER.get(position));
        return convertView;
    }
    public class viewHolder
    {
        ImageView img;
        TextView name;
        ImageView edit;
    }
    public void toast(String s)
    {
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }
}
