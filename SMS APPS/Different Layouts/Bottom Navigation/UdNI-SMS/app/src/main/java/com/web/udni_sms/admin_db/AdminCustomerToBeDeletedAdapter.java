package com.web.udni_sms.admin_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.web.udni_sms.R;

import java.util.ArrayList;

public class AdminCustomerToBeDeletedAdapter extends BaseAdapter
{
    private Context mContext;
    AdminControllerDB controldb;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> ADMIN_CLIENT_NAME = new ArrayList<String>();
    int count=0;
    String theCount;
    String theID="ID";
    public AdminCustomerToBeDeletedAdapter(Context context, ArrayList<String> Id, ArrayList<String> NAME)
    {
        this.Id = Id;
        this.ADMIN_CLIENT_NAME = NAME;
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
        final AdminCustomerToBeDeletedAdapter.viewHolder holder;
        controldb =new AdminControllerDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {

            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.adapter_layout,null);
            holder = new AdminCustomerToBeDeletedAdapter.viewHolder();
            /*convertView = layoutInflater.inflate(R.layout.delete_adapter_layout,null);
            holder = new AdminCustomerToBeDeletedAdapter.viewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.nameOfTheSite);
            holder.chk  = (CheckBox) convertView.findViewById(R.id.deleteCheck);*/
            //holder.chk.setId(++count);
            convertView.setTag(holder);
        }
        else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.name.setText(ADMIN_CLIENT_NAME.get(position));
        return convertView;
    }
    public class viewHolder
    {
        CheckBox chk;
        TextView name;
    }
    public void toast(String s)
    {
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }

    public void onDeleteSelect(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.checkBox:
                toast(String.valueOf(count));
                break;
        }
    }
}
