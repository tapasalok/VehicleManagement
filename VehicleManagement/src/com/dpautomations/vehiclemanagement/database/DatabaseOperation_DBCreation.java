package com.dpautomations.vehiclemanagement.database;

import com.dpautomations.vehiclemanagement.config.StoreData_Info;
import com.dpautomations.vehiclemanagement.config.VehicleData_Info;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOperation_DBCreation extends SQLiteOpenHelper{

	private static DatabaseOperation_DBCreation excelOperationSingleton;
	
	private DatabaseOperation_DBCreation(Context context) {
		super(context, VehicleData_Info.DATABASE_NAME, null, VehicleData_Info.DATABASE_VERSION);
	}
	
	public static DatabaseOperation_DBCreation getInstance(Context context) {
		if (excelOperationSingleton == null) {
			excelOperationSingleton = new DatabaseOperation_DBCreation(context);
		}
		return excelOperationSingleton;
	}
	
	@Override
	public void onConfigure(SQLiteDatabase db){
	    db.setForeignKeyConstraintsEnabled(true);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(VehicleData_Info.CREATE_TABLE_VEHICLE_REGISTRATION);
        db.execSQL(VehicleData_Info.CREATE_TABLE_VEHICLE_MANAGEMENT);	
        db.execSQL(StoreData_Info.CREATE_TABLE_STORE_MANAGEMENT);
	}
	
	/*@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);
	    if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	        db.execSQL("PRAGMA foreign_keys=ON;");
	    }
	}*/
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + VehicleData_Info.TABLE_VEHICLE_REGISTER);
        db.execSQL("DROP TABLE IF EXISTS " + VehicleData_Info.TABLE_VEHICLE_MANAGEMENT);
        db.execSQL("DROP TABLE IF EXISTS " + StoreData_Info.TABLE_STORE_MANAGEMENT);
 
        // create new tables
        onCreate(db);
	}

}
