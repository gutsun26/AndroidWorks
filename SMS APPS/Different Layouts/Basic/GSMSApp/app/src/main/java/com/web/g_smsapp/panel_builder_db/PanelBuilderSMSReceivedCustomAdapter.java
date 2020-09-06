package com.web.g_smsapp.panel_builder_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.web.g_smsapp.R;

import java.util.ArrayList;

public class PanelBuilderSMSReceivedCustomAdapter extends BaseAdapter
{
    private Context mContext;
    PanelBuilderSMSReceiverControllerDB panelBuilderSMSReceiverControllerDB;
    SQLiteDatabase db;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> DATE = new ArrayList<String>();
    private ArrayList<String> NUMBER = new ArrayList<String>();
    private ArrayList<String> CONTENT = new ArrayList<String>();
    String numberText;
    TextView numberChoice;
    public PanelBuilderSMSReceivedCustomAdapter(Context  context, ArrayList<String> Id, ArrayList<String> theNumber, ArrayList<String> theContent)
    {
        this.mContext       = context;
        this.Id             = Id;
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
        panelBuilderSMSReceiverControllerDB =new PanelBuilderSMSReceiverControllerDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.controller_adaptor_layout,null);
            holder = new viewHolder();
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
        holder.number.setText(NUMBER.get(position));
        holder.content.setText(CONTENT.get(position));
        return convertView;
    }
    public class viewHolder
    {
        TextView number;
        TextView content;
    }
}
