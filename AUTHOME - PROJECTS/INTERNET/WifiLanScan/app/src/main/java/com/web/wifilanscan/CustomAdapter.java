package com.web.wifilanscan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shree on 10/22/2016.
 */
public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    Controllerdb controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> SSID = new ArrayList<String>();
    private ArrayList<String> ROOM_NAME = new ArrayList<String>();
    private ArrayList<String> PASSWORD = new ArrayList<String>();
    public CustomAdapter(Context  context, ArrayList<String> Id, ArrayList<String> SSID, ArrayList<String> ROOM_NAME, ArrayList<String> PASSWORD)
    {
        this.mContext = context;
        this.Id = Id;
        this.SSID=SSID;
        this.ROOM_NAME=ROOM_NAME;
        this.PASSWORD=PASSWORD;
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
        final    viewHolder holder;
        controldb =new Controllerdb(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout, null);
            holder = new viewHolder();
            //holder.id = (TextView) convertView.findViewById(R.id.tvid);
            holder.ssid = (TextView)convertView.findViewById(R.id.ssid);
            holder.room_name = (TextView)convertView.findViewById(R.id.room_name);
            holder.password  = (TextView)convertView.findViewById(R.id.password);
            convertView.setTag(holder);
        }
        else {holder = (viewHolder) convertView.getTag();}
        //holder.id.setText(Id.get(position));
        holder.ssid.setText(SSID.get(position));
        holder.room_name.setText(ROOM_NAME.get(position));
        holder.password.setText(PASSWORD.get(position));
        return convertView;
    }
    public class viewHolder
    {
        TextView id;
        TextView ssid;
        TextView room_name;
        TextView password;
    }
}
