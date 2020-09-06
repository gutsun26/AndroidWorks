package com.web.wifilanscan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
public class DisplayAdapter  extends BaseAdapter
{
    private Context mContext;
    Controllerdb controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> ROOM_NAME = new ArrayList<String>();
    public DisplayAdapter(Context  context, ArrayList<String> Id, ArrayList<String> ROOM_NAME)
    {
        this.mContext = context;
        this.Id = Id;
        this.ROOM_NAME=ROOM_NAME;
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
        final viewHolder holder;
        controldb =new Controllerdb(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.room_list_layout, null);
            holder = new DisplayAdapter.viewHolder();
            holder.room_name = (TextView)convertView.findViewById(R.id.room_name);
            convertView.setTag(holder);
        }
        else {holder = (DisplayAdapter.viewHolder) convertView.getTag();}
        holder.room_name.setText(ROOM_NAME.get(position));
        return convertView;
    }
    public class viewHolder
    {
        TextView room_name;
    }
}