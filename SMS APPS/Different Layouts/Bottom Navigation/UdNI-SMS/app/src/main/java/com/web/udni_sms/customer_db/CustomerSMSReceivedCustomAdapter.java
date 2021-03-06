package com.web.udni_sms.customer_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.web.udni_sms.R;
import com.web.udni_sms.builder_db.BuilderSMSReceivedCustomAdapter;
import com.web.udni_sms.builder_db.BuilderSMSReceiverControllerDB;

import java.util.ArrayList;

public class CustomerSMSReceivedCustomAdapter extends BaseAdapter
{
    private Context mContext;
    BuilderSMSReceiverControllerDB controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> DATE = new ArrayList<String>();
    private ArrayList<String> ADDRESS = new ArrayList<String>();
    private ArrayList<String> CONTENT = new ArrayList<String>();


    //public AdminSMSReceivedCustomAdapter(Context  context, ArrayList<String> Id, ArrayList<String> theDate, ArrayList<String> theNumber, ArrayList<String> theContent)
    public CustomerSMSReceivedCustomAdapter(Context  context, ArrayList<String> Id, ArrayList<String> theNumber, ArrayList<String> theContent)
    {
        this.mContext       = context;
        this.Id             = Id;
        //this.DATE           = theDate;
        this.ADDRESS        = theNumber;
        this.CONTENT        = theContent;
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
        final CustomerSMSReceivedCustomAdapter.viewHolder holder;
        controldb =new BuilderSMSReceiverControllerDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.controller_adaptor_layout,null);
            holder = new CustomerSMSReceivedCustomAdapter.viewHolder();
            //holder.date     =(TextView)convertView.findViewById(R.id.idDate);
            holder.address  = (TextView)convertView.findViewById(R.id.idNumber);
            holder.content  = (TextView)convertView.findViewById(R.id.idContent);
            convertView.setTag(holder);
        }
        else
        {
            holder = (CustomerSMSReceivedCustomAdapter.viewHolder) convertView.getTag();
        }
        //holder.date.setText(DATE.get(position));
        holder.address.setText(ADDRESS.get(position));
        holder.content.setText(CONTENT.get(position));
        return convertView;
    }
    public class viewHolder
    {
        //TextView date;
        TextView address;
        TextView content;
    }
}

