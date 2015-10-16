package com.dpautomations.vehiclemanagement.config;

public interface VehicleData_Info {

		//DATABASE NAME
		public static final String DATABASE_NAME = "vehicle_management_db";
		
		//DATABASE VERSION
		public static final int DATABASE_VERSION = 2;
		
		//TABLE NAMES
		public static final String TABLE_VEHICLE_REGISTER = "vehicle_registration";
		public static final String TABLE_VEHICLE_MANAGEMENT = "vehicle_management";
		
		// "vehicle_registration" Table - column names
		public static final String KEY_VEHICLE_REGISTRATION_ID = "VEHICLE_ID";
		public static final String KEY_VEHICLE_NO = "VEHICLE_NO";
	    public static final String KEY_PLANT = "PLANT";
	    public static final String KEY_TRANSPORTERNAME = "TRANSPORTER_NAME";
	    public static final String KEY_VEHICLE_STATE = "VEHICLE_STATE";
	    
	    // "vehicle_management" Table - column names
	    public static final String KEY_ROW_VEHICLE_ID = "ID";
	    public static final String KEY_VEHICLE_MANAGEMENT_ID = "VEHICLE_MANAGEMENT_ID";
	    public static final String KEY_DATE = "RECORD_CREATION";
	    public static final String KEY_DC_NO = "DC_NO";
	    public static final String KEY_DIESEL_ISSUED = "DIESEL_ISSUED";
	    public static final String KEY_OPEN_KMS = "OPEN_KMS";
	    public static final String KEY_OUT_DATE_TIME = "OUT_DATE_TIME";
	    public static final String KEY_CLOSING_KMS = "CLOSING_KMS";
	    public static final String KEY_IN_DATE_TIME = "IN_DATE_TIME";
	    public static final String KEY_START_ENGINE_HRS = "START_ENGINE_HRS";
	    public static final String KEY_CLOSING_ENGINE_HRS = "CLOSING_ENGINE_HRS";
	    public static final String KEY_FOW_HRS = "FOW_HRS";
	    public static final String KEY_REV_HRS = "REV_HRS";
	    public static final String KEY_QTY = "QTY";
	    public static final String KEY_MAN_PUMP = "MAN_PUMP";
	    
	    // Table Create Statements
	    // "vehicle_registration" table create statement
	    public static final String CREATE_TABLE_VEHICLE_REGISTRATION = "CREATE TABLE "+ TABLE_VEHICLE_REGISTER + "(" + KEY_VEHICLE_REGISTRATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_VEHICLE_NO + " TEXT," + KEY_PLANT + " TEXT," + KEY_TRANSPORTERNAME + " TEXT," + KEY_VEHICLE_STATE + " TEXT" +")";
	 
	    // "vehicle_management" table create statement
	    public static final String CREATE_TABLE_VEHICLE_MANAGEMENT = "CREATE TABLE "
	    															+ TABLE_VEHICLE_MANAGEMENT + "(" + KEY_ROW_VEHICLE_ID + " INTEGER PRIMARY KEY,"+ KEY_VEHICLE_MANAGEMENT_ID + " INTEGER," + KEY_DATE + " TEXT," + KEY_DC_NO + " TEXT," 
	    															+ KEY_DIESEL_ISSUED + " TEXT," + KEY_OPEN_KMS + " TEXT," + KEY_OUT_DATE_TIME + " TEXT," 
	    															+ KEY_CLOSING_KMS + " TEXT," + KEY_IN_DATE_TIME + " TEXT," + KEY_START_ENGINE_HRS + " TEXT," 
	    															+ KEY_CLOSING_ENGINE_HRS + " TEXT," + KEY_FOW_HRS + " TEXT," 
	    															+ KEY_REV_HRS + " TEXT," + KEY_QTY + " TEXT," + KEY_MAN_PUMP + " TEXT," 
	    															+"FOREIGN KEY(" + KEY_VEHICLE_MANAGEMENT_ID + ") REFERENCES " + TABLE_VEHICLE_REGISTER +"(" + KEY_VEHICLE_REGISTRATION_ID + ") ON DELETE CASCADE)";

}
