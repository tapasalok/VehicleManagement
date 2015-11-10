package com.dpautomations.vehiclemanagement.database;

import java.util.ArrayList;
import java.util.List;

import com.dpautomations.vehiclemanagement.config.StoreData_Info;
import com.dpautomations.vehiclemanagement.config.VehicleData_Info;
import com.dpautomations.vehiclemanagement.dto.Store;
import com.dpautomations.vehiclemanagement.dto.Vehicle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOperation_Store {

	private static DatabaseOperation_Store excelOperationSingleton;
	private static SQLiteDatabase db_readable, db_writable;

	private DatabaseOperation_Store(Context context) {
		DatabaseOperation_DBCreation databaseOperation_DBCreation = DatabaseOperation_DBCreation.getInstance(context);
		db_readable = databaseOperation_DBCreation.getReadableDatabase();
		db_writable = databaseOperation_DBCreation.getWritableDatabase();
	}

	public static DatabaseOperation_Store getInstance(Context context) {
		if (excelOperationSingleton == null) {
			excelOperationSingleton = new DatabaseOperation_Store(context);
		}
		return excelOperationSingleton;
	}

	// ------------------------ "store_registration" table methods
	// ----------------//

	/**
	 * Creating a store_registration
	 */
	public boolean createStoreRegistration(Store store_registration_data) {
		String nullColumnHack = null;
		ContentValues values = new ContentValues();
		values.put(StoreData_Info.KEY_MATERIAL_TYPE, store_registration_data.getType_of_material());
		values.put(StoreData_Info.KEY_SUPPLIERNAME, store_registration_data.getSupplierName());
		db_writable.insert(StoreData_Info.TABLE_STORE_REGISTER, nullColumnHack, values);
		return true;
	}

	/**
	 * getting all store_registration data
	 */
	public List<Store> getAllStoreRegistrationData() {
		List<Store> store_data = new ArrayList<Store>();

		String selectQuery = "SELECT  * FROM " + StoreData_Info.TABLE_STORE_REGISTER;
		Cursor c = db_readable.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Store store = new Store();
				store.setStoreRegistration_rowid(
						Integer.toString(c.getInt((c.getColumnIndex(StoreData_Info.KEY_STORE_REGISTRATION_ID)))));
				store.setType_of_material(c.getString((c.getColumnIndex(StoreData_Info.KEY_MATERIAL_TYPE))));
				store.setSupplierName(c.getString(c.getColumnIndex(StoreData_Info.KEY_SUPPLIERNAME)));

				// adding to store_data list
				store_data.add(store);
			} while (c.moveToNext());
		}

		return store_data;
	}

	/**
	 * Updating store_registration data
	 */
	public int updateStoreRegistration(Store store_update_data) {
		ContentValues values = new ContentValues();
		values.put(StoreData_Info.KEY_MATERIAL_TYPE, store_update_data.getType_of_material());
		values.put(StoreData_Info.KEY_MATERIAL_QUNATITY, store_update_data.getQuantity_of_material());
		values.put(StoreData_Info.KEY_SUPPLIERNAME, store_update_data.getSupplierName());

		// updating row
		return db_writable.update(StoreData_Info.TABLE_STORE_REGISTER, values,
				StoreData_Info.KEY_STORE_REGISTRATION_ID + " = ?",
				new String[] { String.valueOf(store_update_data.getStoreRegistration_rowid()) });
	}

	/**
	 * Deleting vehicle_registration table
	 */
	public void deleteStoreRegistration(Store store_delete_data) {
		db_writable.delete(StoreData_Info.TABLE_STORE_REGISTER, StoreData_Info.KEY_STORE_REGISTRATION_ID + " = ?",
				new String[] { String.valueOf(store_delete_data.getStoreRegistration_rowid()) });
	}

	// ------------------------ "store_management" table methods
	// ----------------//

	/**
	 * Creating a store_management
	 */
	public boolean createStoreManagement(Store store_registration_data) {
		String nullColumnHack = null;
		ContentValues values = new ContentValues();
		values.put(StoreData_Info.KEY_DATE, store_registration_data.getDate_for_entered_material_detail());
		values.put(StoreData_Info.KEY_MATERIAL_TYPE, store_registration_data.getType_of_material());
		values.put(StoreData_Info.KEY_SUPPLIERNAME, store_registration_data.getSupplierName());
		values.put(StoreData_Info.KEY_MATERIAL_VEHICLE_NO, store_registration_data.getMaterial_vehicle_no());
		values.put(StoreData_Info.KEY_LOAD_WEIGHT, store_registration_data.getLoad_Weight());
		values.put(StoreData_Info.KEY_EMPTY_WEIGHT, store_registration_data.getEmpty_Weight());
		values.put(StoreData_Info.KEY_NET_WEIGHT, store_registration_data.getNet_Weight());
		values.put(StoreData_Info.KEY_CHALLANNUMBER, store_registration_data.getChallan_Number());
		
		values.put(StoreData_Info.KEY_DATE_TIME , store_registration_data.getDateTime());
		values.put(StoreData_Info.KEY_DATE_TIME_1, store_registration_data.getDateTime_1());
		
		values.put(StoreData_Info.KEY_HOLD, store_registration_data.getHold());
		db_writable.insert(StoreData_Info.TABLE_STORE_MANAGEMENT, nullColumnHack, values);
		return true;
	}

	/**
	 * getting all store_management data
	 */
	public List<Store> getAllStoreManagementData() {
		List<Store> store_data = new ArrayList<Store>();
		String selectQuery = "SELECT * FROM " + StoreData_Info.TABLE_STORE_MANAGEMENT + " WHERE "
				+ StoreData_Info.KEY_HOLD + "='Yes'";

		Cursor c = db_readable.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Store store = new Store();
				store.setStoreManagement_rowid(
						Integer.toString(c.getInt((c.getColumnIndex(StoreData_Info.KEY_STORE_MANAGEMENT_ID)))));
				store.setDate_for_entered_material_detail(c.getString((c.getColumnIndex(StoreData_Info.KEY_DATE))));
				store.setType_of_material(c.getString(c.getColumnIndex(StoreData_Info.KEY_MATERIAL_TYPE)));
				store.setSupplierName(c.getString(c.getColumnIndex(StoreData_Info.KEY_SUPPLIERNAME)));
				store.setMaterial_vehicle_no(c.getString(c.getColumnIndex(StoreData_Info.KEY_MATERIAL_VEHICLE_NO)));
				store.setLoad_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_LOAD_WEIGHT)));
				store.setEmpty_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_EMPTY_WEIGHT)));
				store.setNet_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_NET_WEIGHT)));
				
				store.setChallan_Number(c.getString(c.getColumnIndex(StoreData_Info.KEY_CHALLANNUMBER)));
				store.setDateTime(c.getString(c.getColumnIndex(StoreData_Info.KEY_DATE_TIME)));
				store.setDateTime_1(c.getString(c.getColumnIndex(StoreData_Info.KEY_DATE_TIME_1)));
				
				store.setHold(c.getString(c.getColumnIndex(StoreData_Info.KEY_HOLD)));

				// adding to vehicle_data list
				store_data.add(store);
			} while (c.moveToNext());
		}

		return store_data;
	}

	/**
	 * getting all holded data of store_management
	 */
	public List<Store> getAllHoldedStoreManagementData(Store store_holded_management_data) {
		List<Store> store_data = new ArrayList<Store>();
		String selectQuery = "SELECT * FROM " + StoreData_Info.TABLE_STORE_MANAGEMENT + " WHERE "
				+ StoreData_Info.KEY_STORE_MANAGEMENT_ID + "='"
				+ store_holded_management_data.getStoreManagement_rowid() + "'";

		Cursor c = db_readable.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Store store = new Store();
				store.setDate_for_entered_material_detail(c.getString((c.getColumnIndex(StoreData_Info.KEY_DATE))));
				store.setType_of_material(c.getString(c.getColumnIndex(StoreData_Info.KEY_MATERIAL_TYPE)));
				store.setSupplierName(c.getString(c.getColumnIndex(StoreData_Info.KEY_SUPPLIERNAME)));
				store.setMaterial_vehicle_no(c.getString(c.getColumnIndex(StoreData_Info.KEY_MATERIAL_VEHICLE_NO)));
				store.setLoad_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_LOAD_WEIGHT)));
				store.setEmpty_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_EMPTY_WEIGHT)));
				store.setNet_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_NET_WEIGHT)));
				
				store.setChallan_Number(c.getString(c.getColumnIndex(StoreData_Info.KEY_CHALLANNUMBER)));
				store.setDateTime(c.getString(c.getColumnIndex(StoreData_Info.KEY_DATE_TIME)));
				store.setDateTime_1(c.getString(c.getColumnIndex(StoreData_Info.KEY_DATE_TIME_1)));
				
				store.setHold(c.getString(c.getColumnIndex(StoreData_Info.KEY_HOLD)));

				// adding to vehicle_data list
				store_data.add(store);
			} while (c.moveToNext());
		}

		return store_data;
	}

	/**
	 * Updating store_management data
	 */
	public int updateStoreManagement(Store store_update_data) {
		ContentValues values = new ContentValues();
		values.put(StoreData_Info.KEY_EMPTY_WEIGHT, store_update_data.getEmpty_Weight());
		values.put(StoreData_Info.KEY_NET_WEIGHT, store_update_data.getNet_Weight());
		values.put(StoreData_Info.KEY_DATE_TIME_1, store_update_data.getDateTime_1());
		values.put(StoreData_Info.KEY_HOLD, store_update_data.getHold());

		// updating row
		return db_writable.update(StoreData_Info.TABLE_STORE_MANAGEMENT, values,
				StoreData_Info.KEY_STORE_MANAGEMENT_ID + " = ?",
				new String[] { String.valueOf(store_update_data.getStoreManagement_rowid()) });
	}

	// ------------------------ methods for excel sheet operation
	// ----------------//

	/**
	 * getting all store data
	 */
	public List<Store> getAllStoreData() {
		List<Store> store_data = new ArrayList<Store>();
		String selectQuery = "SELECT * FROM " + StoreData_Info.TABLE_STORE_MANAGEMENT;

		Cursor c = db_readable.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				Store store = new Store();
				store.setStoreManagement_rowid(c.getString(c.getColumnIndex(StoreData_Info.KEY_STORE_MANAGEMENT_ID)));
				store.setDate_for_entered_material_detail(c.getString((c.getColumnIndex(StoreData_Info.KEY_DATE))));
				store.setType_of_material(c.getString(c.getColumnIndex(StoreData_Info.KEY_MATERIAL_TYPE)));
				store.setSupplierName(c.getString(c.getColumnIndex(StoreData_Info.KEY_SUPPLIERNAME)));
				store.setMaterial_vehicle_no(c.getString(c.getColumnIndex(StoreData_Info.KEY_MATERIAL_VEHICLE_NO)));
				store.setLoad_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_LOAD_WEIGHT)));
				store.setEmpty_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_EMPTY_WEIGHT)));
				store.setNet_Weight(c.getString(c.getColumnIndex(StoreData_Info.KEY_NET_WEIGHT)));
				
				store.setChallan_Number(c.getString(c.getColumnIndex(StoreData_Info.KEY_CHALLANNUMBER)));
				store.setDateTime(c.getString(c.getColumnIndex(StoreData_Info.KEY_DATE_TIME)));
				store.setDateTime_1(c.getString(c.getColumnIndex(StoreData_Info.KEY_DATE_TIME_1)));
				
				store.setHold(c.getString(c.getColumnIndex(StoreData_Info.KEY_HOLD)));

				// adding to vehicle_data list
				store_data.add(store);
			} while (c.moveToNext());
		}

		return store_data;
	}

}
