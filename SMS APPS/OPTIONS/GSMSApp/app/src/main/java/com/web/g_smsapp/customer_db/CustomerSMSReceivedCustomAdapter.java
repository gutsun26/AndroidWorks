package com.web.g_smsapp.customer_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.web.g_smsapp.R;
import com.web.g_smsapp.admin_db.AdminSMSReceivedCustomAdapter;
import com.web.g_smsapp.admin_db.AdminSMSReceiverControllerDB;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomerSMSReceivedCustomAdapter extends BaseAdapter
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
    public CustomerSMSReceivedCustomAdapter(Context  context, ArrayList<String> Id, ArrayList<String> theNumber, ArrayList<String> theContent)
    {
        this.mContext       = context;
        this.Id             = Id;
        //this.DATE           = theDate;
        this.NUMBER        = theNumber;
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
        final viewHolder holder;
        controldb =new CustomerSMSReceiverControllerDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.controller_adaptor_layout,null);
            holder = new viewHolder();
            //holder.date     =(TextView)convertView.findViewById(R.id.idDate);
            //numberChoice = (TextView)convertView.findViewById(R.id.idNumberChoice);
            holder.number  = (TextView)convertView.findViewById(R.id.idNumber);
            holder.content  = (TextView)convertView.findViewById(R.id.idContent);
            holder.number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberText = holder.number.getText().toString();
                    numberChoice.setText(numberText);

                }
            });
            convertView.setTag(holder);
        }
        else
        {
            holder = (viewHolder) convertView.getTag();
        }
        //holder.date.setText(DATE.get(position));
        holder.number.setText(NUMBER.get(position));
        holder.content.setText(CONTENT.get(position));
        return convertView;
    }
    public class viewHolder
    {
        //TextView date;
        TextView number;
        TextView content;
    }
}
