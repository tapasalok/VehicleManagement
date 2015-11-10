package com.dpautomations.vehiclemanagement.adapters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.config.StoreListing_Constants;
import com.dpautomations.vehiclemanagement.config.VehicleListing_Constants;
import com.dpautomations.vehiclemanagement.dto.Vehicle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class VehicleList_CustomAdapter extends BaseAdapter implements OnClickListener{

	public ArrayList<HashMap<String, String>> list;
	Activity activity;
	private String state_of_vehicle;
	
	public VehicleList_CustomAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
		super();
		this.activity=activity;
		this.list=list;
	}
	
	static class ViewHolder {
		  TextView text_VehicleNo, text_Out, text_Out_time, text_In, text_In_time, text_Transporter;
		  int position;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}
 
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}
 
	@Override
	public long getItemId(int position) {
		return position;
	}
 	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		LayoutInflater inflater=activity.getLayoutInflater();
		
		if(convertView == null){
			
			convertView=inflater.inflate(R.layout.vehicle_list_view_item, null);
			
			holder = new ViewHolder();
			
			holder.text_VehicleNo = (TextView) convertView.findViewById(R.id.vehicle_nummbr);
			holder.text_Out = (TextView) convertView.findViewById(R.id.text_Out);
			holder.text_Out_time = (TextView) convertView.findViewById(R.id.text_Out_date_time);
			holder.text_In = (TextView) convertView.findViewById(R.id.text_In);
			holder.text_In_time = (TextView) convertView.findViewById(R.id.text_In_date_time);
			holder.text_Transporter = (TextView) convertView.findViewById(R.id.text_transporter);
			holder.position = position;
			convertView.setTag(holder);
		}
		else {
	        holder = (ViewHolder) convertView.getTag();
	    }
			HashMap<String, String> map=list.get(position);
			holder.text_VehicleNo.setText(map.get(VehicleListing_Constants.SECOND_COLUMN));
			holder.text_Out.setText("Out Timing");
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm"); 
			String dateString = map.get(VehicleListing_Constants.OUT_TIMING_ROW);
			try {
				dateString = formatter.format(new Date(Long.parseLong
						(map.get(VehicleListing_Constants.OUT_TIMING_ROW))));
			} catch (NumberFormatException e) {
				 dateString = map.get(VehicleListing_Constants.OUT_TIMING_ROW);
			}
			holder.text_Out_time.setText(dateString);
			holder.text_In.setText("In Timing");
			holder.text_In_time.setText(map.get(VehicleListing_Constants.IN_TIMING_ROW));
			holder.text_Transporter.setText(map.get(VehicleListing_Constants.FOURTH_COLUMN));
			state_of_vehicle = map.get(VehicleListing_Constants.FIFTH_COLUMN);
			
			if(state_of_vehicle.equals("Out")){
				
				
				convertView.setBackgroundColor(Color.parseColor("#f75d59"));
				
				String outTimeInMillisec = map.get(VehicleListing_Constants.OUT_TIMING_ROW);
				
				try {
				double outTime = Double.parseDouble(outTimeInMillisec);
				
				double currentTime = (double) Calendar.getInstance().getTimeInMillis();
				
				double timeDiff = currentTime - outTime;
				
				double timeDiffInHours = timeDiff/216000;
				
				if(timeDiffInHours > 3){
					convertView.setBackgroundColor(Color.parseColor("#FFFF00"));
				}
				else {
				convertView.setBackgroundColor(Color.parseColor("#f75d59"));
				}
				}
				catch (NumberFormatException e){
					
				}
			}
			if(state_of_vehicle.equals("In")){
				convertView.setBackgroundColor(Color.parseColor("#728c00"));
			}
			
		holder.position = position;
		return convertView;
	}

	@Override
	public void onClick(View v) {
	}   

}
