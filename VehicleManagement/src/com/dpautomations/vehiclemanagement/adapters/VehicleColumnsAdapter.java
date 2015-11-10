package com.dpautomations.vehiclemanagement.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.config.StoreListing_Constants;
import com.dpautomations.vehiclemanagement.config.VehicleListing_Constants;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class VehicleColumnsAdapter extends BaseAdapter implements OnClickListener{

	public ArrayList<HashMap<String, String>> list_ColumnName;
	Activity activity;
	private TextView textView_Vehicle_no, textView_InOut, textView_Transporter;
	public VehicleColumnsAdapter(Activity activity,ArrayList<HashMap<String, String>> list_ColumnNames){
		super();
		this.activity=activity;
		this.list_ColumnName=list_ColumnNames;
	}
	
	@Override
	public int getCount() {
		return list_ColumnName.size();
	}
 
	@Override
	public Object getItem(int position) {
		return list_ColumnName.get(position);
	}
 
	@Override
	public long getItemId(int position) {
		return position;
	}
 	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater=activity.getLayoutInflater();
		
		if(convertView == null){
			
			convertView=inflater.inflate(R.layout.vehicle_list_view_column_names, null);
			
			textView_Vehicle_no = (TextView) convertView.findViewById(R.id.vehicle_no_column);
			textView_InOut = (TextView) convertView.findViewById(R.id.in_out_column);
			textView_Transporter = (TextView) convertView.findViewById(R.id.transporter_column);
		}
		
		HashMap<String, String> map=list_ColumnName.get(position);
		textView_Vehicle_no.setText(map.get(VehicleListing_Constants.FIRST_COLUMN));
		textView_InOut.setText(map.get(VehicleListing_Constants.SECOND_COLUMN));
		textView_Transporter.setText(map.get(VehicleListing_Constants.THIRD_COLUMN));
		
		return convertView;
	}

	@Override
	public void onClick(View v) {
	}   

}
