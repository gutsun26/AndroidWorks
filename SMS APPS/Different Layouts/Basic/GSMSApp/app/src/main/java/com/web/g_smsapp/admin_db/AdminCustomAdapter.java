package com.web.g_smsapp.admin_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.web.g_smsapp.R;

import java.util.ArrayList;

public class AdminCustomAdapter extends BaseAdapter
{
    private Context mContext;
    AdminControllerDB controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> NUMBER = new ArrayList<String>();
    private ArrayList<String> LOCATION = new ArrayList<String>();


    public AdminCustomAdapter(Context  context, ArrayList<String> Id, ArrayList<String> NUMBER, ArrayList<String> LOCATION)
    {
        this.mContext = context;
        this.Id = Id;
        this.NUMBER=NUMBER;
        this.LOCATION=LOCATION;
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
        controldb =new AdminControllerDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.adapter_layout, null);
            holder = new viewHolder();
            holder.number = (TextView)convertView.findViewById(R.id.idNumber);
            holder.location  = (TextView)convertView.findViewById(R.id.idLocation);
            convertView.setTag(holder);
        }
        else {
                holder = (viewHolder) convertView.getTag();
        }
        holder.number.setText(NUMBER.get(position));
        holder.location.setText(LOCATION.get(position));
        return convertView;
    }
    public class viewHolder
    {
        TextView number;
        TextView location;
    }
}
