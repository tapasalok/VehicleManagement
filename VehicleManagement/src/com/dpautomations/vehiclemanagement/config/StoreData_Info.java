package com.dpautomations.vehiclemanagement.config;

public interface StoreData_Info {

	// DATABASE NAME
	public static final String DATABASE_NAME = "vehicle_management_db";

	// DATABASE VERSION
	public static final int DATABASE_VERSION = 1;

	// TABLE NAMES
	public static final String TABLE_STORE_REGISTER = "store_registration";
	public static final String TABLE_STORE_MANAGEMENT = "store_management";

	// "store_registration" Table - column names
	public static final String KEY_STORE_REGISTRATION_ID = "MATERIAL_ID";
	public static final String KEY_MATERIAL_TYPE = "MATERIAL_TYPE";
	public static final String KEY_MATERIAL_QUNATITY = "MATERIAL_QUNATITY";
	public static final String KEY_SUPPLIERNAME = "SUPPLIER_NAME";

	// "store_management" Table - column names
	public static final String KEY_ROW_STORE_ID = "ID";
	public static final String KEY_STORE_MANAGEMENT_ID = "STORE_MANAGEMENT_ID";
	public static final String KEY_DATE = "RECORD_CREATION";
	public static final String KEY_DC_NO = "DC_NO";
	public static final String KEY_MATERIAL_VEHICLE_NO = "VEHICLE_NO";
	public static final String KEY_LOAD_WEIGHT = "LOAD_WEIGHT";
	public static final String KEY_EMPTY_WEIGHT = "EMPTY_WEIGHT";
	public static final String KEY_NET_WEIGHT = "NET_WEIGHT";
	public static final String KEY_CHALLANNUMBER = "CHALLAN_NUMBER";
	public static final String KEY_DATE_TIME = "DATE_TIME";
	public static final String KEY_DATE_TIME_1 = "DATE_TIME_1";
	public static final String KEY_HOLD = "HOLD";

	// Table Create Statements
	// "store_registration" table create statement
	public static final String CREATE_TABLE_STORE_REGISTRATION = "CREATE TABLE " + TABLE_STORE_REGISTER + "("
			+ KEY_STORE_REGISTRATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MATERIAL_TYPE + " TEXT,"
			+ KEY_SUPPLIERNAME + " TEXT)";

	// "store_management" table create statement
	public static final String CREATE_TABLE_STORE_MANAGEMENT = "CREATE TABLE " + TABLE_STORE_MANAGEMENT + "("
			+ KEY_STORE_MANAGEMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT," + KEY_MATERIAL_TYPE
			+ " TEXT," + KEY_SUPPLIERNAME + " TEXT," + KEY_MATERIAL_VEHICLE_NO + " TEXT," + KEY_LOAD_WEIGHT + " TEXT,"
			+ KEY_EMPTY_WEIGHT + " TEXT," + KEY_NET_WEIGHT + " TEXT," + KEY_HOLD + " TEXT , " + KEY_DATE_TIME + " TEXT ,"
			+ KEY_CHALLANNUMBER + " TEXT, " + KEY_DATE_TIME_1 + " TEXT)";

}
