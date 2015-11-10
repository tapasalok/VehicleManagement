package com.dpautomations.vehiclemanagement.adapters;

import com.dpautomations.vehiclemanagement.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class NavigationDrawerAdapter extends BaseAdapter{

	public static String[] myData;
    private Context context;

    public NavigationDrawerAdapter(Context context){
        this.context=context;
        myData=context.getResources().getStringArray(R.array.custom__drawer_item);
    }

    @Override
    public int getCount() {
        return myData.length;
    }

    @Override
    public Object getItem(int position) {
        return myData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=null;
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.custom_drawer_row,parent,false);

        }
        else{
            row=convertView;
        }
        TextView titleText=(TextView)row.findViewById(R.id.home_text);

        titleText.setText(myData[position]);
        return row;
    }

}
