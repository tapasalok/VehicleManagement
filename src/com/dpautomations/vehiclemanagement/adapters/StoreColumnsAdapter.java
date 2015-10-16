package com.dpautomations.vehiclemanagement.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.config.StoreListing_Constants;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class StoreColumnsAdapter extends BaseAdapter implements OnClickListener{

	public ArrayList<HashMap<String, String>> list_ColumnName;
	Activity activity;
	private TextView textView_StoreVehicleNo, textView_SupplierName;
	public StoreColumnsAdapter(Activity activity,ArrayList<HashMap<String, String>> list_ColumnNames){
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
			
			convertView=inflater.inflate(R.layout.store_list_view_column_names, null);
			
			textView_StoreVehicleNo = (TextView) convertView.findViewById(R.id.vehicle_typee_column);
			textView_SupplierName = (TextView) convertView.findViewById(R.id.supplier_namee_column);
		}
		
		HashMap<String, String> map=list_ColumnName.get(position);
		textView_StoreVehicleNo.setText(map.get(StoreListing_Constants.FIRST_COLUMN));		
		textView_SupplierName.setText(map.get(StoreListing_Constants.SECOND_COLUMN));
		
		
		
		return convertView;
	}

	@Override
	public void onClick(View v) {
	}   

}
