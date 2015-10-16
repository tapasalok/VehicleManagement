package com.dpautomations.vehiclemanagement.database;

import java.util.ArrayList;
import java.util.List;

import com.dpautomations.vehiclemanagement.config.VehicleData_Info;
import com.dpautomations.vehiclemanagement.dto.Vehicle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DatabaseOperation_Vehicle{

	private static DatabaseOperation_Vehicle excelOperationSingleton;
	private static SQLiteDatabase db_readable, db_writable;
	
	private DatabaseOperation_Vehicle(Context context) {
		DatabaseOperation_DBCreation databaseOperation_DBCreation = DatabaseOperation_DBCreation.getInstance(context);
	    db_readable = databaseOperation_DBCreation.getReadableDatabase();
	    db_writable = databaseOperation_DBCreation.getWritableDatabase();
	}
	
	public static DatabaseOperation_Vehicle getInstance(Context context) {
		if (excelOperationSingleton == null) {
			excelOperationSingleton = new DatabaseOperation_Vehicle(context.getApplicationContext());
		}
		return excelOperationSingleton;
	}
	
	// ------------------------ "vehicle_registration" table methods ----------------//
	 
    /**
    * Creating a vehicle_registration
    */
   public boolean createVehicleRegistration(Vehicle vehicle_registration_data) {
       String nullColumnHack = null;
       ContentValues values = new ContentValues();
       values.put(VehicleData_Info.KEY_VEHICLE_NO, vehicle_registration_data.getVehicle_no());
       values.put(VehicleData_Info.KEY_PLANT, vehicle_registration_data.getPlant());
       values.put(VehicleData_Info.KEY_TRANSPORTERNAME, vehicle_registration_data.getTransporter_name());
       values.put(VehicleData_Info.KEY_VEHICLE_STATE, vehicle_registration_data.getVehicleState());
       db_writable.insert(VehicleData_Info.TABLE_VEHICLE_REGISTER, nullColumnHack, values);
       return true;
   }
   
   /**
    * getting all vehicle_registration data
    * */
   public List<Vehicle> getAllVehicleRegistrationData() {
       List<Vehicle> vehicle_data = new ArrayList<Vehicle>();
       String selectQuery = "SELECT * FROM " + VehicleData_Info.TABLE_VEHICLE_REGISTER;

       Cursor c = db_readable.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (c.moveToFirst()) {
           do {
               Vehicle vehicle = new Vehicle();
               vehicle.setVehicleRegistration_rowId(c.getString((c.getColumnIndex(VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID))));
               vehicle.setVehicle_no(c.getString((c.getColumnIndex(VehicleData_Info.KEY_VEHICLE_NO))));
               vehicle.setPlant(c.getString((c.getColumnIndex(VehicleData_Info.KEY_PLANT))));
               vehicle.setTransporter_name(c.getString(c.getColumnIndex(VehicleData_Info.KEY_TRANSPORTERNAME)));
               vehicle.setVehicleState((c.getString((c.getColumnIndex(VehicleData_Info.KEY_VEHICLE_STATE)))));

               // adding to vehicle_data list
               vehicle_data.add(vehicle);
           } while (c.moveToNext());
       }

       return vehicle_data;
   }
   
   /**
    * Updating vehicle_registration data
    */
   public int updateVehicleRegistration(Vehicle vehicle_update_data) {
       ContentValues values = new ContentValues();
       values.put(VehicleData_Info.KEY_VEHICLE_NO, vehicle_update_data.getVehicle_no());
       values.put(VehicleData_Info.KEY_PLANT, vehicle_update_data.getPlant());
       values.put(VehicleData_Info.KEY_TRANSPORTERNAME, vehicle_update_data.getTransporter_name());
       values.put(VehicleData_Info.KEY_VEHICLE_STATE, vehicle_update_data.getVehicleState());

       
       // updating row
       return db_writable.update(VehicleData_Info.TABLE_VEHICLE_REGISTER, values, VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID + " = ?",
               new String[] { String.valueOf(vehicle_update_data.getVehicleRegistration_rowId()) });
   }

   /**
    * Deleting vehicle_registration table
    */
   public int deleteVehicleRegistration(Vehicle vehicle_delete_data) {
      return db_writable.delete(VehicleData_Info.TABLE_VEHICLE_REGISTER, VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID + " = ?",
               new String[] { String.valueOf(vehicle_delete_data.getVehicleRegistration_rowId()) });
   }

// ------------------------ "vehicle_management" table methods ----------------//
   
   /**
    * Creating a vehicle_management
    */
   public boolean createVehicleManagement(Vehicle vehicle_registration_data) {
       String nullColumnHack = null;
       ContentValues values = new ContentValues();
       values.put(VehicleData_Info.KEY_VEHICLE_MANAGEMENT_ID, vehicle_registration_data.getVehicleRegistration_rowId());
       values.put(VehicleData_Info.KEY_DATE, vehicle_registration_data.getDate_for_entered_data());
       values.put(VehicleData_Info.KEY_DC_NO, vehicle_registration_data.getDc_no());
       values.put(VehicleData_Info.KEY_DIESEL_ISSUED, vehicle_registration_data.getDiesel_issued());
       values.put(VehicleData_Info.KEY_OPEN_KMS, vehicle_registration_data.getOpen_kms());
       values.put(VehicleData_Info.KEY_OUT_DATE_TIME, vehicle_registration_data.getOut_date_time());
       values.put(VehicleData_Info.KEY_CLOSING_KMS, vehicle_registration_data.getClosing_kms());
       values.put(VehicleData_Info.KEY_IN_DATE_TIME, vehicle_registration_data.getIn_date_time());
       values.put(VehicleData_Info.KEY_START_ENGINE_HRS, vehicle_registration_data.getStart_engine_hrs());
       values.put(VehicleData_Info.KEY_CLOSING_ENGINE_HRS, vehicle_registration_data.getClosing_engine_hrs());
       values.put(VehicleData_Info.KEY_FOW_HRS, vehicle_registration_data.getFow_hrs());
       values.put(VehicleData_Info.KEY_REV_HRS, vehicle_registration_data.getRev_hrs());
       values.put(VehicleData_Info.KEY_QTY, vehicle_registration_data.getQuantity());
       values.put(VehicleData_Info.KEY_MAN_PUMP, vehicle_registration_data.getMan_pump());
       db_writable.insert(VehicleData_Info.TABLE_VEHICLE_MANAGEMENT, nullColumnHack, values);
       return true;
   }
   
   /**
    * getting all vehicle_management data
    * */
   public List<Vehicle> getAllVehicleManagementData(Vehicle vehicle_registration_data) {
       List<Vehicle> vehicle_data = new ArrayList<Vehicle>();
       String selectQuery = "SELECT * FROM "+ VehicleData_Info.TABLE_VEHICLE_MANAGEMENT + " WHERE "+VehicleData_Info.KEY_VEHICLE_MANAGEMENT_ID+"='"+vehicle_registration_data.getVehicleRegistration_rowId()+"'";

       Cursor c = db_readable.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (c.moveToLast()) {
           //do {
               Vehicle vehicle = new Vehicle();
               vehicle.setDate_for_entered_data(c.getString((c.getColumnIndex(VehicleData_Info.KEY_DATE))));
               vehicle.setDc_no(c.getString((c.getColumnIndex(VehicleData_Info.KEY_DC_NO))));
               vehicle.setDiesel_issued(c.getString(c.getColumnIndex(VehicleData_Info.KEY_DIESEL_ISSUED)));
               vehicle.setOpen_kms(c.getString(c.getColumnIndex(VehicleData_Info.KEY_OPEN_KMS)));
               vehicle.setOut_date_time(c.getString(c.getColumnIndex(VehicleData_Info.KEY_OUT_DATE_TIME)));
               vehicle.setClosing_kms(c.getString(c.getColumnIndex(VehicleData_Info.KEY_CLOSING_KMS)));
               vehicle.setIn_date_time(c.getString(c.getColumnIndex(VehicleData_Info.KEY_IN_DATE_TIME)));
               vehicle.setStart_engine_hrs(c.getString(c.getColumnIndex(VehicleData_Info.KEY_START_ENGINE_HRS)));
               vehicle.setClosing_engine_hrs(c.getString(c.getColumnIndex(VehicleData_Info.KEY_CLOSING_ENGINE_HRS)));
               vehicle.setFow_hrs(c.getString(c.getColumnIndex(VehicleData_Info.KEY_FOW_HRS)));
               vehicle.setRev_hrs(c.getString(c.getColumnIndex(VehicleData_Info.KEY_REV_HRS)));
               vehicle.setQuantity(c.getString(c.getColumnIndex(VehicleData_Info.KEY_QTY)));
               vehicle.setMan_pump(c.getString(c.getColumnIndex(VehicleData_Info.KEY_MAN_PUMP)));

               // adding to vehicle_data list
               vehicle_data.add(vehicle);
           //} while (c.moveToNext());
       }

       return vehicle_data;
   }
   
   /**
    * Updating vehicle_management data
    */
   public int updateVehicleManagement(Vehicle vehicle_update_data) {
	   
	   String selectQuery = "SELECT MAX("+VehicleData_Info.KEY_ROW_VEHICLE_ID+") FROM "+ VehicleData_Info.TABLE_VEHICLE_MANAGEMENT + " WHERE "+VehicleData_Info.KEY_VEHICLE_MANAGEMENT_ID+"='"+vehicle_update_data.getVehicleRegistration_rowId()+"'";
 
       Cursor c = db_readable.rawQuery(selectQuery, null);
       c.moveToFirst();
       int id= c.getInt(0);
	   
        	   ContentValues values = new ContentValues();
               values.put(VehicleData_Info.KEY_DATE, vehicle_update_data.getDate_for_entered_data());
               values.put(VehicleData_Info.KEY_DC_NO, vehicle_update_data.getDc_no());
               values.put(VehicleData_Info.KEY_DIESEL_ISSUED, vehicle_update_data.getDiesel_issued());
               values.put(VehicleData_Info.KEY_OPEN_KMS, vehicle_update_data.getOpen_kms());
               values.put(VehicleData_Info.KEY_OUT_DATE_TIME, vehicle_update_data.getOut_date_time());
               values.put(VehicleData_Info.KEY_CLOSING_KMS, vehicle_update_data.getClosing_kms());
               values.put(VehicleData_Info.KEY_IN_DATE_TIME, vehicle_update_data.getIn_date_time());
               values.put(VehicleData_Info.KEY_START_ENGINE_HRS, vehicle_update_data.getStart_engine_hrs());
               values.put(VehicleData_Info.KEY_CLOSING_ENGINE_HRS, vehicle_update_data.getClosing_engine_hrs());
               values.put(VehicleData_Info.KEY_FOW_HRS, vehicle_update_data.getFow_hrs());
               values.put(VehicleData_Info.KEY_REV_HRS, vehicle_update_data.getRev_hrs());
               values.put(VehicleData_Info.KEY_QTY, vehicle_update_data.getQuantity());
               values.put(VehicleData_Info.KEY_MAN_PUMP, vehicle_update_data.getMan_pump());

               // updating row
               return db_writable.update(VehicleData_Info.TABLE_VEHICLE_MANAGEMENT, values, VehicleData_Info.KEY_ROW_VEHICLE_ID + " = ?",
                       new String[] { Integer.toString(id) });
   }
   
// ------------------------ methods for excel sheet operation ----------------//
   
   /**
    * getting all vehicle data
    * */
   public List<Vehicle> getAllVehicleData() {
       List<Vehicle> vehicle_data = new ArrayList<Vehicle>();
     
       //String selectQuery = "SELECT * FROM "+ VehicleData_Info.CREATE_TABLE_VEHICLE_REGISTRATION +" INNER JOIN "+ VehicleData_Info.CREATE_TABLE_VEHICLE_MANAGEMENT +" ON "+ VehicleData_Info.TABLE_VEHICLE_REGISTER +"."+ VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID +"="+ VehicleData_Info.TABLE_VEHICLE_MANAGEMENT +"."+ VehicleData_Info.KEY_VEHICLE_MANAGEMENT_ID;
       
       String selectQuery = "SELECT "+VehicleData_Info.TABLE_VEHICLE_REGISTER+"."+VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID+","+VehicleData_Info.TABLE_VEHICLE_REGISTER+"."+VehicleData_Info.KEY_VEHICLE_NO+","+VehicleData_Info.TABLE_VEHICLE_REGISTER+"."+VehicleData_Info.KEY_PLANT+","+VehicleData_Info.TABLE_VEHICLE_REGISTER+"."+VehicleData_Info.KEY_TRANSPORTERNAME+","
    		   +VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_DATE+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_DC_NO+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_DIESEL_ISSUED+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_OPEN_KMS+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_OUT_DATE_TIME+","
    		   +VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_CLOSING_KMS+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_IN_DATE_TIME+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_START_ENGINE_HRS+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_CLOSING_ENGINE_HRS+","
    		   +VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_FOW_HRS+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_REV_HRS+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_QTY+","+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT+"."+VehicleData_Info.KEY_MAN_PUMP+" FROM "+VehicleData_Info.TABLE_VEHICLE_REGISTER+" ,"+VehicleData_Info.TABLE_VEHICLE_MANAGEMENT
    		   +" WHERE "+VehicleData_Info.TABLE_VEHICLE_REGISTER +"."+ VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID +"="+ VehicleData_Info.TABLE_VEHICLE_MANAGEMENT +"."+ VehicleData_Info.KEY_VEHICLE_MANAGEMENT_ID;
       
       Cursor c = db_readable.rawQuery(selectQuery, null);
       
       // looping through all rows and adding to list
       if (c.moveToFirst()) {
           do {
               Vehicle vehicle = new Vehicle();
               vehicle.setVehicleRegistration_rowId(c.getString((c.getColumnIndex(VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID))));
               vehicle.setVehicle_no(c.getString((c.getColumnIndex(VehicleData_Info.KEY_VEHICLE_NO))));
               vehicle.setPlant(c.getString((c.getColumnIndex(VehicleData_Info.KEY_PLANT))));
               vehicle.setTransporter_name(c.getString(c.getColumnIndex(VehicleData_Info.KEY_TRANSPORTERNAME)));
               vehicle.setDate_for_entered_data(c.getString((c.getColumnIndex(VehicleData_Info.KEY_DATE))));
               vehicle.setDc_no(c.getString((c.getColumnIndex(VehicleData_Info.KEY_DC_NO))));
               vehicle.setDiesel_issued(c.getString(c.getColumnIndex(VehicleData_Info.KEY_DIESEL_ISSUED)));
               vehicle.setOpen_kms(c.getString(c.getColumnIndex(VehicleData_Info.KEY_OPEN_KMS)));
               vehicle.setOut_date_time(c.getString(c.getColumnIndex(VehicleData_Info.KEY_OUT_DATE_TIME)));
               vehicle.setClosing_kms(c.getString(c.getColumnIndex(VehicleData_Info.KEY_CLOSING_KMS)));
               vehicle.setIn_date_time(c.getString(c.getColumnIndex(VehicleData_Info.KEY_IN_DATE_TIME)));
               vehicle.setStart_engine_hrs(c.getString(c.getColumnIndex(VehicleData_Info.KEY_START_ENGINE_HRS)));
               vehicle.setClosing_engine_hrs(c.getString(c.getColumnIndex(VehicleData_Info.KEY_CLOSING_ENGINE_HRS)));
               vehicle.setFow_hrs(c.getString(c.getColumnIndex(VehicleData_Info.KEY_FOW_HRS)));
               vehicle.setRev_hrs(c.getString(c.getColumnIndex(VehicleData_Info.KEY_REV_HRS)));
               vehicle.setQuantity(c.getString(c.getColumnIndex(VehicleData_Info.KEY_QTY)));
               vehicle.setMan_pump(c.getString(c.getColumnIndex(VehicleData_Info.KEY_MAN_PUMP)));

               // adding to vehicle_data list
               vehicle_data.add(vehicle);
           } while (c.moveToNext());
       }

       return vehicle_data;
   }
}
