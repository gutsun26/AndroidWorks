package com.web.g_smsapp.customer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.web.g_smsapp.R;
import com.web.g_smsapp.customer_db.CustomerSMSReceivedCustomAdapter;
import com.web.g_smsapp.customer_db.CustomerSMSReceiverControllerDB;

import java.util.ArrayList;

public class CustomerSMSToBeDeletedAdapter extends BaseAdapter
{
    private Context mContext;
    CustomerSMSReceiverControllerDB controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> DATE = new ArrayList<String>();
    private ArrayList<String> NUMBER = new ArrayList<String>();
    private ArrayList<String> CONTENT = new ArrayList<String>();
    String numberText;
    TextView numberChoice;
    public CustomerSMSToBeDeletedAdapter(Context  context, ArrayList<String> Id, ArrayList<String> theNumber)
    {
        this.mContext       = context;
        this.Id             = Id;
        this.NUMBER        = theNumber;
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
        controldb =new CustomerSMSReceiverControllerDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.delete_adaptor,null);
            holder = new viewHolder();
            holder.number  = (TextView)convertView.findViewById(R.id.idNumber);
            convertView.setTag(holder);
        }
        else
        {
            holder = (viewHolder) convertView.getTag();
        }
        holder.number.setText(NUMBER.get(position));
        return convertView;
    }
    public class viewHolder
    {
        //TextView date;
        TextView number;
        TextView content;
    }
}
