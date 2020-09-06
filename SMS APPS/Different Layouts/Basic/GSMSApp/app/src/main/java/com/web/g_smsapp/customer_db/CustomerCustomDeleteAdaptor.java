package com.web.g_smsapp.customer_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.web.g_smsapp.R;

import java.util.ArrayList;

public class CustomerCustomDeleteAdaptor extends BaseAdapter
{
    private Context mContext;
    private CustomerControllerDB customerControllerDB;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> LOCATION = new ArrayList<String>();

    public CustomerCustomDeleteAdaptor(Context  context, ArrayList<String> LOCATION)
    {
        this.mContext = context;
        //this.Id = Id;
        this.LOCATION=LOCATION;
    }

    @Override
    public int getCount() {
        return 0;
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
        customerControllerDB =new CustomerControllerDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.customer_delete_adaptor_layout, null);
            holder = new viewHolder();
            holder.location  = (TextView)convertView.findViewById(R.id.idLocation);
            convertView.setTag(holder);
        }
        else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.location.setText(LOCATION.get(position));
        return convertView;
    }
    public class viewHolder
    {
        TextView location;
    }
}
