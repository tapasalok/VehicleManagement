package com.dpautomations.vehiclemanagement.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.database.DatabaseOperation_Vehicle;
import com.dpautomations.vehiclemanagement.dto.Vehicle;

public class VehicleRegistationFragment extends BaseFragment implements OnItemSelectedListener{
	private EditText vehicleNumb, plant, trasportername;
	private Button button;
	private Spinner spinner;
	private boolean flag_vehicle_register = false;
	private String selected_vehicle_state;
	List<String> vehicle_state_list;
	List<Vehicle> state_vehicle = new ArrayList<Vehicle>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_vehicle_registation,
				container, false);
		plant = (EditText) view.findViewById(R.id.Plant);
		vehicleNumb = (EditText) view.findViewById(R.id.vehicle_no);
		trasportername = (EditText) view.findViewById(R.id.trasporterName);
		button = (Button) view.findViewById(R.id.button);
		spinner = (Spinner) view.findViewById(R.id.spinner_vehicle_state);
		button.setOnClickListener(registerListener);
		
		// Spinner Drop down elements
        vehicle_state_list = new ArrayList<String>();
        vehicle_state_list.add("In");
        vehicle_state_list.add("Out");
		// Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, vehicle_state_list);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
		
		return view;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {		
			selected_vehicle_state = parent.getItemAtPosition(position).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		Toast.makeText(parent.getContext(), "Please select Vehicle State", Toast.LENGTH_SHORT).show();
		return;
	}

	OnClickListener registerListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			if (TextUtils.isEmpty(vehicleNumb.getText())) {
				Toast.makeText(getActivity(), "Please enter Vehicle No.", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(plant.getText())) {
				Toast.makeText(getActivity(), "Please enter Plant Name", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(trasportername.getText())) {
				Toast.makeText(getActivity(), "Please enter Transporter Name", Toast.LENGTH_SHORT).show();
				return;
			}
			
			//Take All the strings coming from EditText
			String vehicleNumberString = vehicleNumb.getText().toString();
			String plantName = plant.getText().toString();
			String transporterNam = trasportername.getText().toString();
			
			
			Vehicle vehicleDTO = new Vehicle();
			// Set all the strings in DTO
			vehicleDTO.setVehicle_no(vehicleNumberString);
			vehicleDTO.setPlant(plantName);
			vehicleDTO.setTransporter_name(transporterNam);
			vehicleDTO.setVehicleState(selected_vehicle_state);
			
			//Call the insert API from DatabaseOperation_Vehicle class
			DatabaseOperation_Vehicle databaseOperation = DatabaseOperation_Vehicle.getInstance(getActivity());
			flag_vehicle_register = databaseOperation.createVehicleRegistration(vehicleDTO);
			
			if(flag_vehicle_register){
				Fragment detail = new VehicleListingFragment();
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
			}
		
		}
	};

}
