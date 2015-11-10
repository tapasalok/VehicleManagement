package com.dpautomations.vehiclemanagement.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.adapters.VehicleList_CustomAdapter.ViewHolder;
import com.dpautomations.vehiclemanagement.config.StoreListing_Constants;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StoreList_CustomAdapter extends BaseAdapter implements OnClickListener {

	public ArrayList<HashMap<String, String>> list;
	Activity activity;

	public StoreList_CustomAdapter(Activity activity, ArrayList<HashMap<String, String>> list) {
		super();
		this.activity = activity;
		this.list = list;
	}

	static class ViewHolder {
		TextView text_MaterialType, text_SupplierName, text_vehicle_Number;
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
		LayoutInflater inflater = activity.getLayoutInflater();

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.store_list_view_item, null);

			holder = new ViewHolder();

			holder.text_SupplierName = (TextView) convertView.findViewById(R.id.supplier_namee);
			holder.text_vehicle_Number = (TextView) convertView.findViewById(R.id.vehicle_number);

			holder.position = position;
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		HashMap<String, String> map = list.get(position);
		holder.text_vehicle_Number.setText(map.get(StoreListing_Constants.SECOND_COLUMN));
		holder.text_SupplierName.setText(map.get(StoreListing_Constants.THIRD_COLUMN));
		
		if(TextUtils.isEmpty(map.get(StoreListing_Constants.HOLD_COLUMN))){
			
		}
		else if(map.get(StoreListing_Constants.HOLD_COLUMN).equals("Yes")){
			convertView.setBackgroundColor(Color.parseColor("#f75d59"));
		}

		holder.position = position;
		return convertView;
	}

	@Override
	public void onClick(View v) {
	}

}
