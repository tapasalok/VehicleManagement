package com.dpautomations.vehiclemanagement.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.adapters.StoreList_CustomAdapter;
import com.dpautomations.vehiclemanagement.adapters.StoreColumnsAdapter;
import com.dpautomations.vehiclemanagement.config.StoreListing_Constants;
import com.dpautomations.vehiclemanagement.database.DatabaseOperation_Store;
import com.dpautomations.vehiclemanagement.dto.Store;
import com.dpautomations.vehiclemanagement.util.PreferenceOperation;

public class StoreListingFragment extends BaseFragment {
	private ListView listView, listView_columnNames;
	private TextView textView_noStoreData, netWeight;

	private List<Store> allStoreData = new ArrayList<Store>();
	private HashMap<String, String> temp;
	private ArrayList<HashMap<String, String>> list, list_columnNames;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.custom_store_list_view,
				container, false);

		textView_noStoreData = (TextView) view.findViewById(R.id.noStoreData);
		listView_columnNames = (ListView) view
				.findViewById(R.id.store_columnName_listing);
		netWeight = (TextView) view.findViewById(R.id.netWeight);

		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Calendar calobj = Calendar.getInstance();
		String currentDate = df.format(calobj.getTime());
		
		PreferenceOperation preferenceOperation = PreferenceOperation.getInstance(getActivity());
		if (!currentDate.equals(preferenceOperation.getLastUpdatedWeightDate())) {
			preferenceOperation.putNetWeight("0");
		}
		
		if (!TextUtils.isEmpty(preferenceOperation.getNetWeight())) {
			netWeight.setText("Net Weight: "
					+ preferenceOperation.getNetWeight());
		}

		listView = (ListView) view.findViewById(R.id.store_listing);
		list = new ArrayList<HashMap<String, String>>();
		list_columnNames = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> temp_Columns = new HashMap<String, String>();
		temp_Columns.put(StoreListing_Constants.FIRST_COLUMN, "Vehicle Number");
		temp_Columns.put(StoreListing_Constants.SECOND_COLUMN, "Supplier Name");
		list_columnNames.add(temp_Columns);

		getStoreFromDatabase();

		StoreColumnsAdapter storeColumnsAdapter = new StoreColumnsAdapter(
				getActivity(), list_columnNames);
		listView_columnNames.setAdapter(storeColumnsAdapter);
		StoreList_CustomAdapter adapter = new StoreList_CustomAdapter(
				getActivity(), list);
		listView.setAdapter(adapter);
		// Register the ListView for Context menu
		registerForContextMenu(listView);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
			}

		});
		return view;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select The Action");
		menu.add(0, 0, 0, "Manage the Store");// groupId, itemId, order, title
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		int index = adapterContextMenuInfo.position;
		HashMap<String, String> map = list.get(index);
		Store storeDTO = new Store();
		// Set all the strings in DTO
		storeDTO.setStoreManagement_rowid(map
				.get(StoreListing_Constants.FIRST_COLUMN));
		storeDTO.setMaterial_vehicle_no(map
				.get(StoreListing_Constants.SECOND_COLUMN));
		storeDTO.setSupplierName(map.get(StoreListing_Constants.THIRD_COLUMN));

		int menuItemId = item.getItemId();

		switch (menuItemId) {
		case 0:
			Fragment detaill = new StoreInWardFragment(storeDTO);
			FragmentManager fragmentManagerr = getFragmentManager();
			fragmentManagerr.beginTransaction()
					.replace(R.id.content_frame, detaill).commit();
			break;
		default:
			break;
		}

		return super.onContextItemSelected(item);
	}

	private void getStoreFromDatabase() {

		// Call the select API from DatabaseOperation_Store class to get all the
		// Vehicle DTO
		DatabaseOperation_Store databaseOperation_Store = DatabaseOperation_Store
				.getInstance(getActivity());

		allStoreData = databaseOperation_Store.getAllStoreManagementData();
		if (allStoreData.isEmpty()) {
			textView_noStoreData.setVisibility(View.VISIBLE);
		} else {
			textView_noStoreData.setVisibility(View.GONE);
		}
		for (Store store_data : allStoreData) {
			temp = new HashMap<String, String>();
			temp.put(StoreListing_Constants.FIRST_COLUMN,
					store_data.getStoreManagement_rowid());
			temp.put(StoreListing_Constants.SECOND_COLUMN,
					store_data.getMaterial_vehicle_no());
			temp.put(StoreListing_Constants.THIRD_COLUMN,
					store_data.getSupplierName());
			temp.put(StoreListing_Constants.HOLD_COLUMN, store_data.getHold());
			temp.put(StoreListing_Constants.EIGHT_COLUMN, store_data.getHold());
			temp.put(StoreListing_Constants.NINE_COLUMN, store_data.getHold());

			list.add(temp);
		}
	}
}
