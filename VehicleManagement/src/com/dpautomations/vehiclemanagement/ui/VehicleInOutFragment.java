package com.dpautomations.vehiclemanagement.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
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

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.database.DatabaseOperation_Vehicle;
import com.dpautomations.vehiclemanagement.dto.Vehicle;
import com.dpautomations.vehiclemanagement.util.DatePicker_Dialog_Fragment;
import com.dpautomations.vehiclemanagement.util.GettingDateTime;
import com.dpautomations.vehiclemanagement.util.PreferenceOperation;
import com.dpautomations.vehiclemanagement.util.TimePicker_Dialog_Fragment;

public class VehicleInOutFragment extends BaseFragment {
	private TextView textView_show_creationDate;
	private EditText editText_dc_no, editText_diesel_issued, editText_openkms,
			editText_out_time_date, editText_closing_kms,
			editText_in_time_date, editText_start_engine_hrs,
			editText_closing_engine_hrs, editText_fow_hrs, editText_rev_hrs,
			editText_man_pump;
	EditText editText_qty;
	private Button vehicle_manageCheckOutButton, vehicle_manageCheckInButton;

	public SharedPreferences sh1;

	private boolean flag_vehicle_management = false,
			flag_checkOut_AlreadyDone = false,
			flag_checkIn_AlreadyDone = false;

	private Vehicle vehicle_data_from_context_menu;
	private List<Vehicle> allvehicles = new ArrayList<Vehicle>();

	public VehicleInOutFragment(Vehicle vehicle_data_from_context_menu) {
		this.vehicle_data_from_context_menu = vehicle_data_from_context_menu;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_vehicle_in_out,
				container, false);

		textView_show_creationDate = (TextView) view
				.findViewById(R.id.datee_created);

		editText_dc_no = (EditText) view.findViewById(R.id.dc_no);
		editText_diesel_issued = (EditText) view
				.findViewById(R.id.diesel_issued);
		editText_openkms = (EditText) view.findViewById(R.id.openkms);
		editText_out_time_date = (EditText) view
				.findViewById(R.id.out_time_Date);
		editText_closing_kms = (EditText) view.findViewById(R.id.closingkms);
		editText_in_time_date = (EditText) view.findViewById(R.id.in_time_Date);
		editText_start_engine_hrs = (EditText) view
				.findViewById(R.id.start_engine_hrs);
		editText_closing_engine_hrs = (EditText) view
				.findViewById(R.id.closing_engine_hrs);
		editText_fow_hrs = (EditText) view.findViewById(R.id.fow_hrs);
		editText_rev_hrs = (EditText) view.findViewById(R.id.rev_hrs);
		editText_qty = (EditText) view.findViewById(R.id.quantity);
		editText_man_pump = (EditText) view.findViewById(R.id.man_pump);
		vehicle_manageCheckOutButton = (Button) view
				.findViewById(R.id.manage_vehicle_checkOut);
		vehicle_manageCheckInButton = (Button) view
				.findViewById(R.id.manage_vehicle_checkIn);

		manageVehiclefromDatabase();

		GettingDateTime gettingDateTime = GettingDateTime.getInstance();
		textView_show_creationDate.setText(gettingDateTime.getDate());

		// textView_show_totalQuantity.setText(qty_editText);

		editText_out_time_date.setOnClickListener(outTimeListener);
		editText_in_time_date.setOnClickListener(inTimeListener);

		Bundle bundle = getArguments();
		if (bundle != null) {
			boolean checkedOut = bundle.getBoolean("checkedOut");
			if (checkedOut) {
				// registerButton.setText("Check In");
				// registerButton.setBackgroundColor(Color.GREEN);
			} else {
				// registerButton.setText("Check Out");
				// registerButton.setBackgroundColor(Color.RED);
			}

			String vehicleNumberString = bundle.getString("vehicleNumber");
			if (TextUtils.isEmpty(vehicleNumberString)) {

			} else {
				// vehicleNumber.setText(vehicleNumberString);
			}
		}

		vehicle_manageCheckOutButton.setOnClickListener(checkOutEntryListener);
		vehicle_manageCheckInButton.setOnClickListener(checkInEntryListener);
		return view;
	}

	android.view.View.OnClickListener outTimeListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			DialogFragment datePickerFragment = new DatePicker_Dialog_Fragment() {
				int yy, mm, dd;
				boolean timerOpened = false;

				@Override
				public void onDateSet(DatePicker view, int year, int month,
						int day) {
					Calendar c = Calendar.getInstance();
					c.set(year, month, day);
					yy = year;
					mm = month + 1;
					dd = day;

					DialogFragment timePickerFragment = new TimePicker_Dialog_Fragment() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							super.onTimeSet(view, hourOfDay, minute);
							timerOpened = false;
							editText_out_time_date.setText(dd + "/" + mm + "/"
									+ yy + " " + hourOfDay + ":" + minute);
						}
					};
					if (!timerOpened) {
						timerOpened = true;
						timePickerFragment.show(getActivity()
								.getFragmentManager(), "timePicker");
					}
				}
			};
			datePickerFragment.show(getActivity().getFragmentManager(),
					"datePicker");
		}
	};

	android.view.View.OnClickListener inTimeListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			DialogFragment datePickerFragment = new DatePicker_Dialog_Fragment() {
				int yy, mm, dd;
				boolean timerOpened = false;

				@Override
				public void onDateSet(DatePicker view, int year, int month,
						int day) {
					Calendar c = Calendar.getInstance();
					c.set(year, month, day);
					yy = year;
					mm = month + 1;
					dd = day;

					DialogFragment timePickerFragment = new TimePicker_Dialog_Fragment() {

						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							super.onTimeSet(view, hourOfDay, minute);
							timerOpened = false;

							editText_in_time_date.setText(dd + "/" + mm + "/"
									+ yy + " " + hourOfDay + ":" + minute);
						}
					};
					if (!timerOpened) {
						timerOpened = true;
						timePickerFragment.show(getActivity()
								.getFragmentManager(), "timePicker");
					}
				}
			};
			datePickerFragment.show(getActivity().getFragmentManager(),
					"datePicker");
		}
	};

	OnClickListener checkOutEntryListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			Vehicle vehicleDTO = new Vehicle();

			if (TextUtils.isEmpty(editText_dc_no.getText())) {
				Toast.makeText(getActivity(), "Please enter DC No.",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(editText_qty.getText())) {
				Toast.makeText(getActivity(), "Please enter Quantity",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(editText_man_pump.getText())) {
				Toast.makeText(getActivity(), "Please enter Man/Pump",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(editText_diesel_issued.getText())) {
				Toast.makeText(getActivity(), "Please enter issued Diesel",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(editText_openkms.getText())) {
				Toast.makeText(getActivity(), "Please enter opening Kms.",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(editText_start_engine_hrs.getText())) {
				Toast.makeText(getActivity(),
						"Please enter starting engine Hrs.", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			if (TextUtils.isEmpty(editText_out_time_date.getText())) {
				Toast.makeText(getActivity(),
						"Please choose Vehicle Out date and time",
						Toast.LENGTH_SHORT).show();
				return;
			}

			// Take All the strings coming from EditText
			String show_creationDate_text = textView_show_creationDate
					.getText().toString();
			// String show_totalQuantity_text =
			// textView_show_totalQuantity.getText().toString();

			String dc_no_editText = editText_dc_no.getText().toString();
			String qty_editText = editText_qty.getText().toString();
			String man_pump_editText = editText_man_pump.getText().toString();
			String issued_diesel_editText = editText_diesel_issued.getText()
					.toString();
			String openKms_editText = editText_openkms.getText().toString();
			String startEngineHrs_editText = editText_start_engine_hrs
					.getText().toString();
			String out_date_time_editText = editText_out_time_date.getText()
					.toString();
			String closingKms_editText = editText_closing_kms.getText()
					.toString();
			String in_date_time_editText = editText_in_time_date.getText()
					.toString();
			String closingEngineHrs_editText = editText_closing_engine_hrs
					.getText().toString();
			String fow_hrs_editText = editText_fow_hrs.getText().toString();
			String rev_hrs_editText = editText_rev_hrs.getText().toString();

			// Set all the strings in DTO
			vehicleDTO
					.setVehicleRegistration_rowId(vehicle_data_from_context_menu
							.getVehicleRegistration_rowId());
			vehicleDTO.setDate_for_entered_data(show_creationDate_text);
			// vehicleDTO.setQuantity_for_entered_data(qty_editText);

			vehicleDTO.setDc_no(dc_no_editText);
			vehicleDTO.setQuantity(qty_editText);
			vehicleDTO.setMan_pump(man_pump_editText);
			vehicleDTO.setDiesel_issued(issued_diesel_editText);
			vehicleDTO.setOpen_kms(openKms_editText);
			vehicleDTO.setStart_engine_hrs(startEngineHrs_editText);
			vehicleDTO.setOut_date_time(out_date_time_editText);
			vehicleDTO.setClosing_kms(closingKms_editText);
			vehicleDTO.setClosing_engine_hrs(closingEngineHrs_editText);
			vehicleDTO.setFow_hrs(fow_hrs_editText);
			vehicleDTO.setRev_hrs(rev_hrs_editText);
			vehicleDTO.setIn_date_time(in_date_time_editText);

			// saveM1(editText_qty.getText().toString());

			PreferenceOperation preferenceOperation = PreferenceOperation
					.getInstance(getActivity());
			if (!TextUtils.isEmpty(qty_editText)) {
				String lastTotalQuantity = preferenceOperation
						.getTotalQuantity();
				DateFormat df = new SimpleDateFormat("dd/MM/yy");
				Calendar calobj = Calendar.getInstance();
				String currentDate = df.format(calobj.getTime());
				if (TextUtils.isEmpty(lastTotalQuantity)) {
					preferenceOperation.putTotalQuantity(qty_editText);
					preferenceOperation.putLastUpdatedQuantityDate(currentDate);
				} else {
					Double double1 = Double.parseDouble(lastTotalQuantity);
					Double double2 = Double.parseDouble(qty_editText);
					if (currentDate.equals(preferenceOperation.getLastUpdatedQuantityDate())) {
						Double resultantQuantity = double1 + double2;
						preferenceOperation.putTotalQuantity(resultantQuantity
								.toString());
					} else {
						preferenceOperation.putTotalQuantity(double2
								.toString());
					}
					preferenceOperation.putLastUpdatedQuantityDate(currentDate);
				}
			}

			DatabaseOperation_Vehicle databaseOperation = DatabaseOperation_Vehicle
					.getInstance(getActivity());
			if (!flag_checkOut_AlreadyDone && !flag_checkIn_AlreadyDone) {
				// Call the insert API from DatabaseOperation_Vehicle class
				flag_vehicle_management = databaseOperation
						.createVehicleManagement(vehicleDTO);

				if (flag_vehicle_management) {
					vehicleDTO.setVehicle_no(vehicle_data_from_context_menu
							.getVehicle_no());
					vehicleDTO.setPlant(vehicle_data_from_context_menu
							.getPlant());
					vehicleDTO
							.setTransporter_name(vehicle_data_from_context_menu
									.getTransporter_name());
					vehicleDTO.setVehicleState("Out");
					databaseOperation.updateVehicleRegistration(vehicleDTO);

					Toast.makeText(getActivity(), "CheckOut is processed..",
							Toast.LENGTH_SHORT).show();
					Fragment detail = new VehicleListingFragment();
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content_frame, detail).commit();
				}
			} else {
				// Call the insert API from DatabaseOperation_Vehicle class
				int update_result = databaseOperation
						.updateVehicleManagement(vehicleDTO);
				if (!flag_checkOut_AlreadyDone) {
					if (update_result == 1) {
						vehicleDTO.setVehicle_no(vehicle_data_from_context_menu
								.getVehicle_no());
						vehicleDTO.setPlant(vehicle_data_from_context_menu
								.getPlant());
						vehicleDTO
								.setTransporter_name(vehicle_data_from_context_menu
										.getTransporter_name());
						vehicleDTO.setVehicleState("Out");
						databaseOperation.updateVehicleRegistration(vehicleDTO);

						Toast.makeText(getActivity(),
								"CheckOut is processed..", Toast.LENGTH_SHORT)
								.show();
						Fragment detail = new VehicleListingFragment();
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.content_frame, detail).commit();
					}

				}

				else {
					Toast.makeText(getActivity(), "CheckOut is already done..",
							Toast.LENGTH_SHORT).show();
				}
			}
		}
		//
		// @SuppressWarnings("unused")
		// private void saveM1(String string) {
		// // TODO Auto-generated method stub
		//
		// SharedPreferences.Editor preferencesEditor = sh1.edit();
		// preferencesEditor.putString("save1",editText_qty); //change this line
		// to this
		// preferencesEditor.commit();
		// }

	};

	OnClickListener checkInEntryListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			Vehicle vehicleDTO = new Vehicle();

			if (TextUtils.isEmpty(editText_closing_kms.getText())) {
				Toast.makeText(getActivity(), "Please enter closing Kms.",
						Toast.LENGTH_SHORT).show();
				return;
			}
			if (TextUtils.isEmpty(editText_closing_engine_hrs.getText())) {
				Toast.makeText(getActivity(),
						"Please enter closing engine Hrs.", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			if (TextUtils.isEmpty(editText_fow_hrs.getText())) {
				Toast.makeText(getActivity(),
						"Please enter forward engine Hrs.", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			if (TextUtils.isEmpty(editText_rev_hrs.getText())) {
				Toast.makeText(getActivity(),
						"Please enter reverse engine Hrs.", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			if (TextUtils.isEmpty(editText_in_time_date.getText())) {
				Toast.makeText(getActivity(),
						"Please choose Vehicle In date and time",
						Toast.LENGTH_SHORT).show();
				return;
			}

			// Take All the strings coming from EditText
			String show_creationDate_text = textView_show_creationDate
					.getText().toString();

			// String show_creationQuantity_text =
			// textView_show_totalQuantity.getText().toString();

			String dc_no_editText = editText_dc_no.getText().toString();
			String qty_editText = editText_qty.getText().toString();
			String man_pump_editText = editText_man_pump.getText().toString();
			String issued_diesel_editText = editText_diesel_issued.getText()
					.toString();
			String openKms_editText = editText_openkms.getText().toString();
			String startEngineHrs_editText = editText_start_engine_hrs
					.getText().toString();
			String out_date_time_editText = editText_out_time_date.getText()
					.toString();
			String closingKms_editText = editText_closing_kms.getText()
					.toString();
			String in_date_time_editText = editText_in_time_date.getText()
					.toString();
			String closingEngineHrs_editText = editText_closing_engine_hrs
					.getText().toString();
			String fow_hrs_editText = editText_fow_hrs.getText().toString();
			String rev_hrs_editText = editText_rev_hrs.getText().toString();

			// Set all the strings in DTO
			vehicleDTO
					.setVehicleRegistration_rowId(vehicle_data_from_context_menu
							.getVehicleRegistration_rowId());
			vehicleDTO.setDate_for_entered_data(show_creationDate_text);
			// vehicleDTO.setQuantity_for_entered_data(qty_editText);

			vehicleDTO.setDc_no(dc_no_editText);
			vehicleDTO.setQuantity(qty_editText);
			vehicleDTO.setMan_pump(man_pump_editText);
			vehicleDTO.setDiesel_issued(issued_diesel_editText);
			vehicleDTO.setOpen_kms(openKms_editText);
			vehicleDTO.setStart_engine_hrs(startEngineHrs_editText);
			vehicleDTO.setOut_date_time(out_date_time_editText);
			vehicleDTO.setClosing_kms(closingKms_editText);
			vehicleDTO.setClosing_engine_hrs(closingEngineHrs_editText);
			vehicleDTO.setFow_hrs(fow_hrs_editText);
			vehicleDTO.setRev_hrs(rev_hrs_editText);
			vehicleDTO.setIn_date_time(in_date_time_editText);

			// get data from Quantity edit text into text view

			DatabaseOperation_Vehicle databaseOperation = DatabaseOperation_Vehicle
					.getInstance(getActivity());
			if (!flag_checkOut_AlreadyDone && !flag_checkIn_AlreadyDone) {
				// Call the insert API from DatabaseOperation_Vehicle class
				flag_vehicle_management = databaseOperation
						.createVehicleManagement(vehicleDTO);

				if (flag_vehicle_management) {
					vehicleDTO.setVehicle_no(vehicle_data_from_context_menu
							.getVehicle_no());
					vehicleDTO.setPlant(vehicle_data_from_context_menu
							.getPlant());
					vehicleDTO
							.setTransporter_name(vehicle_data_from_context_menu
									.getTransporter_name());
					vehicleDTO.setVehicleState("In");
					databaseOperation.updateVehicleRegistration(vehicleDTO);

					Toast.makeText(getActivity(), "CheckIn is processed..",
							Toast.LENGTH_SHORT).show();
					Fragment detail = new VehicleListingFragment();
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content_frame, detail).commit();
				}
			} else {
				// Call the update API from DatabaseOperation_Vehicle class
				int update_result = databaseOperation
						.updateVehicleManagement(vehicleDTO);
				if (!flag_checkIn_AlreadyDone) {
					if (update_result == 1) {
						vehicleDTO.setVehicle_no(vehicle_data_from_context_menu
								.getVehicle_no());
						vehicleDTO.setPlant(vehicle_data_from_context_menu
								.getPlant());
						vehicleDTO
								.setTransporter_name(vehicle_data_from_context_menu
										.getTransporter_name());
						vehicleDTO.setVehicleState("In");
						databaseOperation.updateVehicleRegistration(vehicleDTO);

						Toast.makeText(getActivity(), "CheckIn is processed..",
								Toast.LENGTH_SHORT).show();
						Fragment detail = new VehicleListingFragment();
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.content_frame, detail).commit();
					}
				} else {
					Toast.makeText(getActivity(), "CheckIn is already done..",
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	};

	private void manageVehiclefromDatabase() {
		// Call the select API from DatabaseOperation class to get all the
		// Vehicle DTO
		DatabaseOperation_Vehicle databaseOperation_Vehicle = DatabaseOperation_Vehicle
				.getInstance(getActivity());

		allvehicles = databaseOperation_Vehicle
				.getAllVehicleManagementData(vehicle_data_from_context_menu);

		for (Vehicle vehcleManageData : allvehicles) {
			if (!vehcleManageData.getDc_no().isEmpty()
					&& !vehcleManageData.getQuantity().isEmpty()
					&& !vehcleManageData.getMan_pump().isEmpty()
					&& !vehcleManageData.getDiesel_issued().isEmpty()
					&& !vehcleManageData.getOpen_kms().isEmpty()
					&& !vehcleManageData.getStart_engine_hrs().isEmpty()
					&& !vehcleManageData.getOut_date_time().isEmpty()) {
				flag_checkOut_AlreadyDone = true;

				editText_dc_no.setText(vehcleManageData.getDc_no());
				editText_dc_no.setEnabled(false);
				editText_qty.setText(vehcleManageData.getQuantity());
				editText_qty.setEnabled(false);
				editText_man_pump.setText(vehcleManageData.getMan_pump());
				editText_man_pump.setEnabled(false);
				editText_diesel_issued.setText(vehcleManageData
						.getDiesel_issued());
				editText_diesel_issued.setEnabled(false);
				editText_openkms.setText(vehcleManageData.getOpen_kms());
				editText_openkms.setEnabled(false);
				editText_start_engine_hrs.setText(vehcleManageData
						.getStart_engine_hrs());
				editText_start_engine_hrs.setEnabled(false);
				editText_out_time_date.setText(vehcleManageData
						.getOut_date_time());
				editText_out_time_date.setEnabled(false);
			}
			if (!vehcleManageData.getClosing_kms().isEmpty()
					&& !vehcleManageData.getClosing_engine_hrs().isEmpty()
					&& !vehcleManageData.getFow_hrs().isEmpty()
					&& !vehcleManageData.getRev_hrs().isEmpty()
					&& !vehcleManageData.getIn_date_time().isEmpty()) {
				flag_checkIn_AlreadyDone = true;

				editText_closing_kms.setText(vehcleManageData.getClosing_kms());
				editText_closing_kms.setEnabled(false);
				editText_closing_engine_hrs.setText(vehcleManageData
						.getClosing_engine_hrs());
				editText_closing_engine_hrs.setEnabled(false);
				editText_fow_hrs.setText(vehcleManageData.getFow_hrs());
				editText_fow_hrs.setEnabled(false);
				editText_rev_hrs.setText(vehcleManageData.getRev_hrs());
				editText_rev_hrs.setEnabled(false);
				editText_in_time_date.setText(vehcleManageData
						.getIn_date_time());
				editText_in_time_date.setEnabled(false);
			}
		}
		if (flag_checkOut_AlreadyDone && flag_checkIn_AlreadyDone) {
			// Enabling CheckOut editText fields and setting HintText.
			flag_checkOut_AlreadyDone = false;
			flag_checkIn_AlreadyDone = false;

			editText_dc_no.setEnabled(true);
			editText_dc_no.setText("");
			editText_dc_no.setHint(R.string.enterDcNo);

			editText_qty.setEnabled(true);
			editText_qty.setText("");
			editText_qty.setHint(R.string.qty);

			editText_man_pump.setEnabled(true);
			editText_man_pump.setText("");
			editText_man_pump.setHint(R.string.man_pump);

			editText_diesel_issued.setEnabled(true);
			editText_diesel_issued.setHint(R.string.dieselissued);
			editText_diesel_issued.setText("");

			editText_openkms.setEnabled(true);
			editText_openkms.setHint(R.string.openingkms);
			editText_openkms.setText("");

			editText_start_engine_hrs.setEnabled(true);
			editText_start_engine_hrs.setHint(R.string.start_engine);
			editText_start_engine_hrs.setText("");

			editText_out_time_date.setEnabled(true);
			editText_out_time_date.setHint(R.string.enterOuttime);
			editText_out_time_date.setText("");

			// Enabling CheckIn editText fields and setting HintText.
			editText_closing_kms.setEnabled(true);
			editText_closing_kms.setHint(R.string.closingkms);
			editText_closing_kms.setText("");

			editText_closing_engine_hrs.setEnabled(true);
			editText_closing_engine_hrs.setHint(R.string.close_engine);
			editText_closing_engine_hrs.setText("");

			editText_fow_hrs.setEnabled(true);
			editText_fow_hrs.setHint(R.string.forwardengine);
			editText_fow_hrs.setText("");

			editText_rev_hrs.setEnabled(true);
			editText_rev_hrs.setHint(R.string.reverseengine);
			editText_rev_hrs.setText("");

			editText_in_time_date.setEnabled(true);
			editText_in_time_date.setHint(R.string.enterIntime);
			editText_in_time_date.setText("");
		}
	}
}
