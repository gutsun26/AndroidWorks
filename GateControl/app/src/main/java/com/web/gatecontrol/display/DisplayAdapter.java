package com.web.gatecontrol.display;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.web.gatecontrol.R;
import com.web.gatecontrol.db.UserDB;

import java.util.ArrayList;

public class DisplayAdapter extends BaseAdapter
{
    private Context mContext;
    UserDB userDB;
    private ArrayList<String> ID = new ArrayList<String>();
    private ArrayList<String> NAME = new ArrayList<String>();
    int count=0;
    public DisplayAdapter(Context context, ArrayList<String> Id, ArrayList<String> GATE_NAME)
    {
        this.mContext   = context;
        this.ID         = Id;
        this.NAME       = GATE_NAME;
    }
    @Override
    public int getCount()
    {
        return ID.size();
    }
    @Override
    public Object getItem(int position)
    {
        return null;
    }
    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final    viewHolder holder;
        userDB = new UserDB(mContext);
        LayoutInflater layoutInflater;
        if (convertView == null)
        {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new viewHolder();
            holder.img = (ImageView)convertView.findViewById(R.id.imageSite);
            holder.img.setId(++count);
            holder.name = (TextView)convertView.findViewById(R.id.idName);
            holder.edit  = (ImageView)convertView.findViewById(R.id.editSite);
            convertView.setTag(holder);
        }
        else
        {
            holder = (viewHolder) convertView.getTag();
        }
        holder.name.setText(NAME.get(position));
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