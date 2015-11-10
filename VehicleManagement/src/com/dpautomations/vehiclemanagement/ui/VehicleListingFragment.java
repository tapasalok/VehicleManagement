package com.dpautomations.vehiclemanagement.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import jxl.write.DateTime;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.adapters.VehicleColumnsAdapter;
import com.dpautomations.vehiclemanagement.adapters.VehicleList_CustomAdapter;
import com.dpautomations.vehiclemanagement.config.StoreListing_Constants;
import com.dpautomations.vehiclemanagement.config.VehicleData_Info;
import com.dpautomations.vehiclemanagement.config.VehicleListing_Constants;
import com.dpautomations.vehiclemanagement.database.DatabaseOperation_DBCreation;
import com.dpautomations.vehiclemanagement.database.DatabaseOperation_Vehicle;
import com.dpautomations.vehiclemanagement.dto.Vehicle;
import com.dpautomations.vehiclemanagement.util.PreferenceOperation;

public class VehicleListingFragment extends BaseFragment {
	private ListView listView, listView_columnNames;
	private TextView textView_noVehicleData , totalQuantity;
	private String state_of_vehicle, Out_Time_for_Vehicle, In_Time_for_Vehicle;
	// private AutoCompleteTextView autoCompleteTextView;

	private List<Vehicle> allvehicles = new ArrayList<Vehicle>();
	private List<Vehicle> allvehicles_InOut = new ArrayList<Vehicle>();
	private HashMap<String, String> temp;
	private ArrayList<HashMap<String, String>> list, list_columnNames;
	 
	  
 

	private DatabaseOperation_Vehicle databaseOperation_Vehicle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DatabaseOperation_DBCreation.getInstance(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.custom_vehicle_list_view, container, false);
		textView_noVehicleData = (TextView) view.findViewById(R.id.noRegisteredVehicle);
		listView_columnNames = (ListView) view.findViewById(R.id.vehicle_columnName_listing);
		listView = (ListView) view.findViewById(R.id.vehicle_listing);
		totalQuantity = (TextView) view.findViewById(R.id.totalQuantity);
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Calendar calobj = Calendar.getInstance();
		String currentDate = df.format(calobj.getTime());
		
		PreferenceOperation preferenceOperation = PreferenceOperation.getInstance(getActivity());
		if (!currentDate.equals(preferenceOperation.getLastUpdatedQuantityDate())) {
			preferenceOperation.putTotalQuantity("0");
		}
		
		if (!TextUtils.isEmpty(preferenceOperation.getTotalQuantity())) {
			totalQuantity.setText("Total Quantity: "+preferenceOperation.getTotalQuantity());
		}
		
		list = new ArrayList<HashMap<String, String>>();
		list_columnNames = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> temp_Columns = new HashMap<String, String>();
		temp_Columns.put(VehicleListing_Constants.FIRST_COLUMN, "Vehicle No.");
		temp_Columns.put(VehicleListing_Constants.SECOND_COLUMN, "In/Out Timings");
		temp_Columns.put(VehicleListing_Constants.THIRD_COLUMN, "Transporter");
		list_columnNames.add(temp_Columns);

		getVehiclesFromDatabase();

		VehicleColumnsAdapter vehicleColumnsAdapter = new VehicleColumnsAdapter(getActivity(), list_columnNames);
		listView_columnNames.setAdapter(vehicleColumnsAdapter);
		VehicleList_CustomAdapter adapter = new VehicleList_CustomAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		// Register the ListView for Context menu
		registerForContextMenu(listView);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				String individual_vehicle_plant = allvehicles.get(position).getPlant();

				AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
				// Setting Dialog Title
				alertDialog.setTitle("Vehicle Information");
				// Setting Dialog Message
				alertDialog.setMessage("Plant : " + individual_vehicle_plant);
				alertDialog.setCancelable(true);

				// Showing Alert Message
				alertDialog.show();
			}

		});

		return view;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select The Action");
		menu.add(0, 0, 0, "Make It Out");// groupId, itemId, order, title
		menu.add(0, 1, 0, "Make It In");
		menu.add(0, 2, 0, "Manage the Vehicle");
		menu.add(0, 3, 0, "Delete this Vehicle");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		int index = adapterContextMenuInfo.position;
		HashMap<String, String> map = list.get(index);
		final Vehicle vehicleDTO = new Vehicle();
		// Set all the strings in DTO
		vehicleDTO.setVehicleRegistration_rowId(map.get(VehicleListing_Constants.FIRST_COLUMN));
		vehicleDTO.setVehicle_no(map.get(VehicleListing_Constants.SECOND_COLUMN));
		vehicleDTO.setPlant(map.get(VehicleListing_Constants.THIRD_COLUMN));
		vehicleDTO.setTransporter_name(map.get(VehicleListing_Constants.FOURTH_COLUMN));
		vehicleDTO.setVehicleState(map.get(VehicleListing_Constants.FIFTH_COLUMN));

		state_of_vehicle = map.get(VehicleListing_Constants.FIFTH_COLUMN);
		int menuItemId = item.getItemId();

		switch (menuItemId) {
		case 0:
			if (state_of_vehicle.equals("Out")) {
				Toast.makeText(getActivity().getApplicationContext(), "Vehicle is already Out.", Toast.LENGTH_SHORT)
						.show();
				
			}
			if (state_of_vehicle.equals("In")) {
				listView.getChildAt(index).setBackgroundColor(Color.parseColor("#f75d59"));
				vehicleDTO.setVehicleState("Out");
				
				vehicleDTO.setOut_date_time(Calendar.getInstance().getTimeInMillis()+"");
				

				// Call the insert API from DatabaseOperation_Vehicle class
				DatabaseOperation_Vehicle databaseOperation = DatabaseOperation_Vehicle.getInstance(getActivity());
				databaseOperation.updateVehicleRegistration(vehicleDTO);

				Fragment detail = new VehicleListingFragment();
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
			}
			
			break;
		case 1:
			if (state_of_vehicle.equals("In")) {
				Toast.makeText(getActivity().getApplicationContext(), "Vehicle is already In.", Toast.LENGTH_SHORT)
						.show();
			}
			if (state_of_vehicle.equals("Out")) {
				listView.getChildAt(index).setBackgroundColor(Color.parseColor("#728c00"));
				vehicleDTO.setVehicleState("In");
				
				
				
				// Call the insert API from DatabaseOperation_Vehicle class
				DatabaseOperation_Vehicle databaseOperation = DatabaseOperation_Vehicle.getInstance(getActivity());
				databaseOperation.updateVehicleRegistration(vehicleDTO);

				Fragment detail = new VehicleListingFragment();
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
			}
			 
			break;
		case 2:
			Fragment detail = new VehicleInOutFragment(vehicleDTO);
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
			break;
		case 3:
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
			// Setting Dialog Title
			alertDialog.setTitle(R.string.vehicle_delete_confirm);
			// Setting Dialog Message
			alertDialog.setMessage(R.string.vehicle_delete);
			// Setting Positive "Yes" Button
			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					databaseOperation_Vehicle.deleteVehicleRegistration(vehicleDTO);
					Toast.makeText(getActivity(), "Vehicle is deleted..", Toast.LENGTH_SHORT).show();
					Fragment detaill = new VehicleListingFragment();
					FragmentManager fragmentManagerr = getFragmentManager();
					fragmentManagerr.beginTransaction().replace(R.id.content_frame, detaill).commit();
				}
			});
			// Setting Negative "NO" Button
			alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			// Showing Alert Message
			alertDialog.show();
			break;
		default:
			break;
		}

		return super.onContextItemSelected(item);
	}

	private void getVehiclesFromDatabase() {

		// Call the select API from DatabaseOperation class to get all the
		// Vehicle DTO
		databaseOperation_Vehicle = DatabaseOperation_Vehicle.getInstance(getActivity());

		allvehicles = databaseOperation_Vehicle.getAllVehicleRegistrationData();
		if (allvehicles.isEmpty()) {
			textView_noVehicleData.setVisibility(View.VISIBLE);
		} else {
			textView_noVehicleData.setVisibility(View.GONE);
		}
		for (Vehicle vehcleNumbr : allvehicles) {
			temp = new HashMap<String, String>();
			temp.put(VehicleListing_Constants.FIRST_COLUMN, vehcleNumbr.getVehicleRegistration_rowId());
			temp.put(VehicleListing_Constants.SECOND_COLUMN, vehcleNumbr.getVehicle_no());
			temp.put(VehicleListing_Constants.THIRD_COLUMN, vehcleNumbr.getPlant());
			temp.put(VehicleListing_Constants.FOURTH_COLUMN, vehcleNumbr.getTransporter_name());
			temp.put(VehicleListing_Constants.FIFTH_COLUMN, vehcleNumbr.getVehicleState());
			getInOut_Timings_FromDatabase(vehcleNumbr);
			temp.put(VehicleListing_Constants.OUT_TIMING_ROW, vehcleNumbr.getOut_date_time());
			temp.put(VehicleListing_Constants.IN_TIMING_ROW, In_Time_for_Vehicle);
			list.add(temp);
		}
	}

	private void getInOut_Timings_FromDatabase(Vehicle vehicle) {

		// Call the select API from DatabaseOperation class to get all the
		// Vehicle DTO
		databaseOperation_Vehicle = DatabaseOperation_Vehicle.getInstance(getActivity());

		allvehicles_InOut = databaseOperation_Vehicle.getAllVehicleManagementData(vehicle);

		if (allvehicles_InOut.isEmpty()) {
			Out_Time_for_Vehicle = "No data is found";
			In_Time_for_Vehicle = "No data is found";
		} else {
			for (Vehicle vehcleNumbr : allvehicles_InOut) {
				if (vehcleNumbr.getOut_date_time().isEmpty()) {
					Out_Time_for_Vehicle = "Not yet decided";
				} else {
					Out_Time_for_Vehicle = vehcleNumbr.getOut_date_time();
				}

				if (vehcleNumbr.getIn_date_time().isEmpty()) {
					In_Time_for_Vehicle = "Not yet decided";
				} else {
					In_Time_for_Vehicle = vehcleNumbr.getIn_date_time();
				}

			}
		}
	}

}
