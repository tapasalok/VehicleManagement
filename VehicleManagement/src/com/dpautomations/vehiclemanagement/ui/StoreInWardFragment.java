package com.dpautomations.vehiclemanagement.ui;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.database.DatabaseOperation_Store;
import com.dpautomations.vehiclemanagement.dto.Store;
import com.dpautomations.vehiclemanagement.util.DatePicker_Dialog_Fragment;
import com.dpautomations.vehiclemanagement.util.GettingDateTime;
import com.dpautomations.vehiclemanagement.util.PreferenceOperation;
import com.dpautomations.vehiclemanagement.util.TimePicker_Dialog_Fragment;

public class StoreInWardFragment extends BaseFragment {
	private EditText typesofmaterial, suppliername, vehicleNumber, loadWeight, emptyWeight, netWeight;
	private EditText challan_Number, date_Time, date_Time_1;
	private TextView textView_show_StorecreationDate;;
	private Button button, button1;

	private boolean flag_holded_data = false, flag_store_register = false, flag_call_from_storeListing = false;
	protected int index;

	private Store store_data_from_context_menu;
	private List<Store> allHoldedStore = new ArrayList<Store>();

	public StoreInWardFragment(Store store_data_from_context_menu) {
		this.store_data_from_context_menu = store_data_from_context_menu;
		flag_call_from_storeListing = true;
	}

	public StoreInWardFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_store_in_ward, container, false);

		textView_show_StorecreationDate = (TextView) view.findViewById(R.id.store_datee_created);

		typesofmaterial = (EditText) view.findViewById(R.id.typeofmateials);
		suppliername = (EditText) view.findViewById(R.id.supplierName);
		vehicleNumber = (EditText) view.findViewById(R.id.vehicleNumber);
		loadWeight = (EditText) view.findViewById(R.id.loadWeight);
		emptyWeight = (EditText) view.findViewById(R.id.emptyWeight);
		netWeight = (EditText) view.findViewById(R.id.netWeight);
		challan_Number = (EditText) view.findViewById(R.id.challanNumber);
		date_Time = (EditText) view.findViewById(R.id.dateTimeStore);
		date_Time_1 = (EditText) view.findViewById(R.id.dateTimeStore_1);

		button1 = (Button) view.findViewById(R.id.button_store_register_hold);
		button = (Button) view.findViewById(R.id.button_store_register);
		
		
	 
		date_Time.setOnClickListener(outTimeListener);
		date_Time_1.setOnClickListener(inTimeListener);
		
		if (flag_call_from_storeListing) {
			manageStoreFromDatabase();
		}

		GettingDateTime gettingDateTime = GettingDateTime.getInstance();
		textView_show_StorecreationDate.setText(gettingDateTime.getDate());

		button.setOnClickListener(registerListener);
		button1.setOnClickListener(registerListener1);

		return view;
	}
	
	android.view.View.OnClickListener outTimeListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			DialogFragment datePickerFragment = new DatePicker_Dialog_Fragment() {
				int yy, mm, dd;
				//boolean timerOpened = false;
				@Override
				public void onDateSet(DatePicker view, int year, int month, int day) {
					Calendar c = Calendar.getInstance();
					c.set(year, month, day);
					yy = year;
					mm = month + 1;
					dd = day;

					DialogFragment timePickerFragment = new TimePicker_Dialog_Fragment() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							super.onTimeSet(view, hourOfDay, minute);
							//timerOpened = false;
							date_Time.setText(dd + "/" + mm + "/" + yy + " " + hourOfDay + ":" + minute);
						}
					};
//					if(!timerOpened){
//						timerOpened = true;
//					timePickerFragment.show(getActivity().getFragmentManager(), "timePicker");
//					}
					timePickerFragment.show(getActivity().getFragmentManager(), "timePicker");
				}
			};
			 
			 
			
			datePickerFragment.show(getActivity().getFragmentManager(), "datePicker");
		}
	};
	
	android.view.View.OnClickListener inTimeListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			DialogFragment datePickerFragment = new DatePicker_Dialog_Fragment() {
				int yy, mm, dd;
				//boolean timerOpened = false;
				@Override
				public void onDateSet(DatePicker view, int year, int month, int day) {
					Calendar c = Calendar.getInstance();
					c.set(year, month, day);
					yy = year;
					mm = month + 1;
					dd = day;

					DialogFragment timePickerFragment = new TimePicker_Dialog_Fragment() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							super.onTimeSet(view, hourOfDay, minute);
							//timerOpened = false;
							date_Time_1.setText(dd + "/" + mm + "/" + yy + " " + hourOfDay + ":" + minute);
						}
					};
//					if(!timerOpened){
//						timerOpened = true;
//					timePickerFragment.show(getActivity().getFragmentManager(), "timePicker");
//					}
					timePickerFragment.show(getActivity().getFragmentManager(), "timePicker");
				}
			};
			datePickerFragment.show(getActivity().getFragmentManager(), "datePicker");
		}
	};

	OnClickListener registerListener1 = new OnClickListener() {

		@Override
		public void onClick(View v) {

			if (TextUtils.isEmpty(typesofmaterial.getText())) {
				Toast.makeText(getActivity(), "Please enter Material type", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(suppliername.getText())) {
				Toast.makeText(getActivity(), "Please enter Supplier Name", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(vehicleNumber.getText())) {
				Toast.makeText(getActivity(), "Please enter Vehicle Number", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(loadWeight.getText())) {
				Toast.makeText(getActivity(), "Please enter Load weight", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(challan_Number.getText())) {
				Toast.makeText(getActivity(), "Please enter  Challan Number", Toast.LENGTH_SHORT).show();
				return;
			}

			// Take All the strings coming from EditText
			String show_StorecreationDate_text = textView_show_StorecreationDate.getText().toString();
			String typesofMaterialString = typesofmaterial.getText().toString();
			String suppliernameString = suppliername.getText().toString();
			String vehicleNumberSting = vehicleNumber.getText().toString();
			String loadweightString = loadWeight.getText().toString();
			String emptyWeightString = emptyWeight.getText().toString();
			String netWeightString = netWeight.getText().toString();
			String challanNumber = challan_Number.getText().toString();
			String dateTime = date_Time.getText().toString();
			String dateTIme1 = date_Time_1.getText().toString();
			
			Store storeDTO = new Store();
			// Set all the strings in DTO
			storeDTO.setDate_for_entered_material_detail(show_StorecreationDate_text);
			storeDTO.setType_of_material(typesofMaterialString);
			storeDTO.setSupplierName(suppliernameString);
			storeDTO.setMaterial_vehicle_no(vehicleNumberSting);
			storeDTO.setLoad_Weight(loadweightString);
			storeDTO.setEmpty_Weight(emptyWeightString);
			storeDTO.setNet_Weight(netWeightString);
			storeDTO.setChallan_Number(challanNumber);
			storeDTO.setDateTime(dateTime);
			storeDTO.setDateTime_1(dateTIme1);
			  
			storeDTO.setHold("Yes");
			
			DatabaseOperation_Store databaseOperation = DatabaseOperation_Store.getInstance(getActivity());

			if (!flag_holded_data) {
				// Call the createStoreManagement API from
				// DatabaseOperation_Store class
				flag_store_register = databaseOperation.createStoreManagement(storeDTO);
				if (flag_store_register) {
					Toast.makeText(getActivity(), "Now Data is in hold..", Toast.LENGTH_SHORT).show();
					Fragment detail = new StoreListingFragment();
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
				}
			} else {
				Toast.makeText(getActivity(), "Data is already in hold..", Toast.LENGTH_SHORT).show();
			}

		}
	};

	OnClickListener registerListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			if (TextUtils.isEmpty(typesofmaterial.getText())) {
				Toast.makeText(getActivity(), "Please enter Material type", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(suppliername.getText())) {
				Toast.makeText(getActivity(), "Please enter Supplier Name", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(vehicleNumber.getText())) {
				Toast.makeText(getActivity(), "Please enter Vehicle Number", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(loadWeight.getText())) {
				Toast.makeText(getActivity(), "Please enter Load weight", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(emptyWeight.getText())) {
				Toast.makeText(getActivity(), "Please enter Empty Weight", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(netWeight.getText())) {
				Toast.makeText(getActivity(), "Please enter Net Weight", Toast.LENGTH_SHORT).show();
				return;
			}

			if (TextUtils.isEmpty(challan_Number.getText())) {
				Toast.makeText(getActivity(), "Please enter Challan Number", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(date_Time.getText())) {
				Toast.makeText(getActivity(), "Please enter Date Time", Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(date_Time_1.getText())) {
				Toast.makeText(getActivity(), "Please enter  Date Time", Toast.LENGTH_SHORT).show();
				return;
			}

			
			
			// Take All the strings coming from EditText
			String show_StorecreationDate_text = textView_show_StorecreationDate.getText().toString();
			String typesofMaterialString = typesofmaterial.getText().toString();
			String suppliernameString = suppliername.getText().toString();
			String vehicleNumberSting = vehicleNumber.getText().toString();
			String loadweightString = loadWeight.getText().toString();
			String emptyWeightString = emptyWeight.getText().toString();
			String netWeightString = netWeight.getText().toString();
			
			String challanNumber = challan_Number.getText().toString();
			String dateTime = date_Time.getText().toString();
			String dateTIme1 = date_Time_1.getText().toString();
			

			Store storeDTO = new Store();
			// Set all the strings in DTO
			if (flag_call_from_storeListing) {
				storeDTO.setStoreManagement_rowid(store_data_from_context_menu.getStoreManagement_rowid());
			}
			storeDTO.setDate_for_entered_material_detail(show_StorecreationDate_text);
			storeDTO.setType_of_material(typesofMaterialString);
			storeDTO.setSupplierName(suppliernameString);
			storeDTO.setMaterial_vehicle_no(vehicleNumberSting);
			storeDTO.setLoad_Weight(loadweightString);
			storeDTO.setEmpty_Weight(emptyWeightString);
			storeDTO.setNet_Weight(netWeightString);
			
			storeDTO.setChallan_Number(challanNumber);
			storeDTO.setDateTime(dateTime);
			storeDTO.setDateTime_1(dateTIme1);
			
			storeDTO.setHold("Yes");

			PreferenceOperation preferenceOperation = PreferenceOperation
					.getInstance(getActivity());
			if (!TextUtils.isEmpty(netWeightString)) {
				String lastNetWeight = preferenceOperation
						.getNetWeight();
				DateFormat df = new SimpleDateFormat("dd/MM/yy");
				Calendar calobj = Calendar.getInstance();
				String currentDate = df.format(calobj.getTime());
				if (TextUtils.isEmpty(lastNetWeight)) {
					preferenceOperation.putNetWeight(netWeightString);
					preferenceOperation.putLastUpdatedWeightDate(currentDate);
				} else {
					Double double1 = Double.parseDouble(lastNetWeight);
					Double double2 = Double.parseDouble(netWeightString);
					if (currentDate.equals(preferenceOperation.getLastUpdatedWeightDate())) {
						Double resultantWeight = double1 + double2;
						preferenceOperation.putNetWeight(resultantWeight
								.toString());
					} else {
						preferenceOperation.putNetWeight(double2
								.toString());
					}
					preferenceOperation.putLastUpdatedWeightDate(currentDate);
				}
			}
			
			DatabaseOperation_Store databaseOperation = DatabaseOperation_Store.getInstance(getActivity());

			if (!flag_holded_data) {
				// Call the createStoreManagement API from
				// DatabaseOperation_Store class
				flag_store_register = databaseOperation.createStoreManagement(storeDTO);
				if (flag_store_register) {
					Toast.makeText(getActivity(), "Updation is processed..", Toast.LENGTH_SHORT).show();
					Fragment detail = new StoreListingFragment();
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
				}
			}
			if (flag_holded_data) {
				// Call the updateStoreManagement API from
				// DatabaseOperation_Store class
				int update_result = databaseOperation.updateStoreManagement(storeDTO);
				if (update_result == 1) {
					Toast.makeText(getActivity(), "Updation is processed..", Toast.LENGTH_SHORT).show();
					Fragment detail = new StoreListingFragment();
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
				}

			}

		}
	};

	private void manageStoreFromDatabase() {
		// Call the select getAllHoldedStoreManagementData from
		// DatabaseOperation_Store class to get all the
		// Store DTO

		DatabaseOperation_Store databaseOperation_Store = DatabaseOperation_Store.getInstance(getActivity());
		allHoldedStore = databaseOperation_Store.getAllHoldedStoreManagementData(store_data_from_context_menu);

		for (Store storeHoldedData : allHoldedStore) {
			if (!storeHoldedData.getType_of_material().isEmpty() && !storeHoldedData.getSupplierName().isEmpty()
					&& !storeHoldedData.getMaterial_vehicle_no().isEmpty()
					&& !storeHoldedData.getLoad_Weight().isEmpty() 
					&&!storeHoldedData.getChallan_Number().isEmpty() 
					&&!storeHoldedData.getDateTime().isEmpty() 
					) {
				flag_holded_data = true;

				typesofmaterial.setText(storeHoldedData.getType_of_material());
				typesofmaterial.setEnabled(false);
				suppliername.setText(storeHoldedData.getSupplierName());
				suppliername.setEnabled(false);
				vehicleNumber.setText(storeHoldedData.getMaterial_vehicle_no());
				vehicleNumber.setEnabled(false);
				loadWeight.setText(storeHoldedData.getLoad_Weight());
				loadWeight.setEnabled(false);
				challan_Number.setText(storeHoldedData.getChallan_Number() );
				challan_Number.setEnabled(false);
				date_Time.setText(storeHoldedData.getDateTime());
				date_Time.setEnabled(false);
			}
		}
	}

}
