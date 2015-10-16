package com.dpautomations.vehiclemanagement.database;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.dpautomations.vehiclemanagement.config.StoreData_Info;
import com.dpautomations.vehiclemanagement.config.VehicleData_Info;
import com.dpautomations.vehiclemanagement.dto.Store;
import com.dpautomations.vehiclemanagement.dto.Vehicle;
import com.dpautomations.vehiclemanagement.util.GettingDateTime;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelOperation {

	private static ExcelOperation excelOperationSingleton;
	private String path = Environment.getExternalStorageDirectory().getAbsolutePath();
	private int countofrow = 0,countofrow1=0;
	private File exportDir = new File(path+"//VehicleManagement", "");
	
	
	private List<Vehicle> finalVehicle_ExcelData=new ArrayList<Vehicle>();
	private List<Store> finalStore_ExcelData=new ArrayList<Store>();

	private ExcelOperation() {
		if (!exportDir.exists()) {
			exportDir.mkdirs();
		}
	}

	public static ExcelOperation getInstance() {
		if (excelOperationSingleton == null) {
			excelOperationSingleton = new ExcelOperation();
		}
		return excelOperationSingleton;
	}
	
	public void writeToExcel_VehicleData(Context context) {
		// Call the select API from DatabaseOperation class to get all the Vehicle DTO
		DatabaseOperation_Vehicle databaseOperation_Vehicle = DatabaseOperation_Vehicle.getInstance(context);
		finalVehicle_ExcelData = databaseOperation_Vehicle.getAllVehicleData();
		
		if(finalVehicle_ExcelData.isEmpty()){
			Toast.makeText(context, "There is no data of Vehicles", Toast.LENGTH_SHORT).show();
		}
		else{
			
			try {
				WritableWorkbook wb;
				WorkbookSettings wbSettings = new WorkbookSettings();
				
	
				WritableFont boldFont = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
				wbSettings.setLocale(new Locale("en", "EN"));
				File file = new File(exportDir, "Vehicle_details " + ".xls");
				wb = Workbook.createWorkbook(file, wbSettings);
	
				WritableCellFormat cellFormat = new WritableCellFormat(boldFont);
	
				HashMap<String, Integer> ColumnMap = new HashMap<String, Integer>();
				List<String> colList = new ArrayList<String>();
	
				int maxColCount = 0;
				int i = 0;
				WritableSheet wsheet = null;
				//Excel sheet name. i=0 represents first sheet
				wsheet = wb.createSheet("VehicleDetails", i);
				i++;
				ColumnMap.put(VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID, 0);
				jxl.write.Label l1 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID, cellFormat);
				wsheet.addCell(l1);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l2 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_VEHICLE_NO, cellFormat);
				wsheet.addCell(l2);
				ColumnMap.put(VehicleData_Info.KEY_VEHICLE_NO, 1);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l3 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_PLANT, cellFormat);
				wsheet.addCell(l3);
				ColumnMap.put(VehicleData_Info.KEY_PLANT, 2);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l4 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_TRANSPORTERNAME, cellFormat);
				wsheet.addCell(l4);
				ColumnMap.put(VehicleData_Info.KEY_TRANSPORTERNAME, 3);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l5 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_DATE, cellFormat);
				wsheet.addCell(l5);
				ColumnMap.put(VehicleData_Info.KEY_DATE, 4);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l6 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_DC_NO, cellFormat);
				wsheet.addCell(l6);
				ColumnMap.put(VehicleData_Info.KEY_DC_NO, 5);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l7 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_DIESEL_ISSUED, cellFormat);
				wsheet.addCell(l7);
				ColumnMap.put(VehicleData_Info.KEY_DIESEL_ISSUED, 6);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l8 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_OPEN_KMS, cellFormat);
				wsheet.addCell(l8);
				ColumnMap.put(VehicleData_Info.KEY_OPEN_KMS, 7);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l9 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_OUT_DATE_TIME, cellFormat);
				wsheet.addCell(l9);
				ColumnMap.put(VehicleData_Info.KEY_OUT_DATE_TIME, 8);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l10 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_CLOSING_KMS, cellFormat);
				wsheet.addCell(l10);
				ColumnMap.put(VehicleData_Info.KEY_CLOSING_KMS, 9);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l11 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_IN_DATE_TIME, cellFormat);
				wsheet.addCell(l11);
				ColumnMap.put(VehicleData_Info.KEY_IN_DATE_TIME, 10);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l12 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_START_ENGINE_HRS, cellFormat);
				wsheet.addCell(l12);
				ColumnMap.put(VehicleData_Info.KEY_START_ENGINE_HRS, 11);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l13 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_CLOSING_ENGINE_HRS, cellFormat);
				wsheet.addCell(l13);
				ColumnMap.put(VehicleData_Info.KEY_CLOSING_ENGINE_HRS, 12);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l14 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_FOW_HRS, cellFormat);
				wsheet.addCell(l14);
				ColumnMap.put(VehicleData_Info.KEY_FOW_HRS, 13);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l15 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_REV_HRS, cellFormat);
				wsheet.addCell(l15);
				ColumnMap.put(VehicleData_Info.KEY_REV_HRS, 14);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l16 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_QTY, cellFormat);
				wsheet.addCell(l16);
				ColumnMap.put(VehicleData_Info.KEY_QTY, 15);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l17 = new jxl.write.Label(maxColCount, countofrow, VehicleData_Info.KEY_MAN_PUMP, cellFormat);
				wsheet.addCell(l17);
				ColumnMap.put(VehicleData_Info.KEY_MAN_PUMP, 16);
	
				for (Vehicle vehicleDto : finalVehicle_ExcelData) {
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_VEHICLE_REGISTRATION_ID), countofrow, vehicleDto.getVehicleRegistration_rowId(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_VEHICLE_NO), countofrow, vehicleDto.getVehicle_no(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_PLANT), countofrow, vehicleDto.getPlant(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_TRANSPORTERNAME), countofrow, vehicleDto.getTransporter_name(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_DATE), countofrow, vehicleDto.getDate_for_entered_data(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_DC_NO), countofrow, vehicleDto.getDc_no(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_DIESEL_ISSUED), countofrow, vehicleDto.getDiesel_issued(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_OPEN_KMS), countofrow, vehicleDto.getOpen_kms(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_OUT_DATE_TIME), countofrow, vehicleDto.getOut_date_time(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_CLOSING_KMS), countofrow, vehicleDto.getClosing_kms(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_IN_DATE_TIME), countofrow, vehicleDto.getIn_date_time(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_START_ENGINE_HRS), countofrow, vehicleDto.getStart_engine_hrs(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_CLOSING_ENGINE_HRS), countofrow, vehicleDto.getClosing_engine_hrs(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_FOW_HRS), countofrow, vehicleDto.getFow_hrs(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_REV_HRS), countofrow, vehicleDto.getRev_hrs(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_QTY), countofrow, vehicleDto.getQuantity(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(VehicleData_Info.KEY_MAN_PUMP), countofrow, vehicleDto.getMan_pump(), cellFormat));
					countofrow++;
				}
				wb.write();
				wb.close();
				Toast.makeText(context, "Excel sheet for Vehicle Data is created..",Toast.LENGTH_SHORT).show();
			} 	
			catch (Exception e) {
				System.out.println("EXCEPTION OCCURRED!!!");
			}
		}
				
	}
	
	public void writeToExcel_StoreData(Context context) {
		// Call the select API from DatabaseOperation class to get all the Store DTO
		DatabaseOperation_Store databaseOperation_Store = DatabaseOperation_Store.getInstance(context);	
		finalStore_ExcelData = databaseOperation_Store.getAllStoreData();
		
		if(finalStore_ExcelData.isEmpty()){
			Toast.makeText(context, "There is no data of Store", Toast.LENGTH_SHORT).show();
		}
		else{
			
			try {			
				WritableWorkbook wb;
				WorkbookSettings wbSettings = new WorkbookSettings();
				path = Environment.getExternalStorageDirectory().getAbsolutePath();
	
				WritableFont boldFont = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
				wbSettings.setLocale(new Locale("en", "EN"));
				File file = new File(exportDir, "Store_details " + ".xls");
				wb = Workbook.createWorkbook(file, wbSettings);
	
				WritableCellFormat cellFormat = new WritableCellFormat(boldFont);
	
				HashMap<String, Integer> ColumnMap = new HashMap<String, Integer>();
				List<String> colList = new ArrayList<String>();
				
				int maxColCount = 0;
				int i = 0;
				WritableSheet wsheet = null;
				wsheet = wb.createSheet("StoreDetails", i);
				i++;
				ColumnMap.put(StoreData_Info.KEY_STORE_MANAGEMENT_ID, 0);
				jxl.write.Label l1 = new jxl.write.Label(maxColCount, countofrow1, ""+StoreData_Info.KEY_STORE_MANAGEMENT_ID, cellFormat);
				wsheet.addCell(l1);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l2 = new jxl.write.Label(maxColCount, countofrow1, ""+StoreData_Info.KEY_DATE, cellFormat);
				wsheet.addCell(l2);
				ColumnMap.put(StoreData_Info.KEY_DATE, 1);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l3 = new jxl.write.Label(maxColCount, countofrow1, ""+StoreData_Info.KEY_MATERIAL_TYPE, cellFormat);
				wsheet.addCell(l3);
				ColumnMap.put(StoreData_Info.KEY_MATERIAL_TYPE, 2);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l4 = new jxl.write.Label(maxColCount, countofrow1, ""+StoreData_Info.KEY_SUPPLIERNAME, cellFormat);
				wsheet.addCell(l4);
				ColumnMap.put(StoreData_Info.KEY_SUPPLIERNAME, 3);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l5 = new jxl.write.Label(maxColCount, countofrow1, ""+StoreData_Info.KEY_MATERIAL_VEHICLE_NO, cellFormat);
				wsheet.addCell(l5);
				ColumnMap.put(StoreData_Info.KEY_MATERIAL_VEHICLE_NO, 4);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l6 = new jxl.write.Label(maxColCount, countofrow1, ""+StoreData_Info.KEY_LOAD_WEIGHT, cellFormat);
				wsheet.addCell(l6);
				ColumnMap.put(StoreData_Info.KEY_LOAD_WEIGHT, 5);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l7 = new jxl.write.Label(maxColCount, countofrow1, ""+StoreData_Info.KEY_EMPTY_WEIGHT, cellFormat);
				wsheet.addCell(l7);
				ColumnMap.put(StoreData_Info.KEY_EMPTY_WEIGHT, 6);
				
				maxColCount = maxColCount + 1;
				jxl.write.Label l8 = new jxl.write.Label(maxColCount, countofrow1, ""+StoreData_Info.KEY_NET_WEIGHT, cellFormat);
				wsheet.addCell(l8);
				ColumnMap.put(StoreData_Info.KEY_NET_WEIGHT, 7);
	
				for (Store storeDto : finalStore_ExcelData) {
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(StoreData_Info.KEY_STORE_MANAGEMENT_ID), countofrow1, storeDto.getStoreRegistration_rowid(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(StoreData_Info.KEY_DATE), countofrow1, storeDto.getDate_for_entered_material_detail(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(StoreData_Info.KEY_MATERIAL_TYPE), countofrow1, storeDto.getType_of_material(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(StoreData_Info.KEY_MATERIAL_VEHICLE_NO), countofrow1, storeDto.getMaterial_vehicle_no(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(StoreData_Info.KEY_LOAD_WEIGHT), countofrow1, storeDto.getLoad_Weight(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(StoreData_Info.KEY_EMPTY_WEIGHT), countofrow1, storeDto.getEmpty_Weight(), cellFormat));
					wsheet.addCell(new jxl.write.Label(ColumnMap.get(StoreData_Info.KEY_NET_WEIGHT), countofrow1, storeDto.getNet_Weight(), cellFormat));
					countofrow1++;
				}
				wb.write();
				wb.close();
				Toast.makeText(context, "Excel sheet for Store Data is created..",Toast.LENGTH_SHORT).show();
			} 
			catch (Exception e) {
				System.out.println("EXCEPTION OCCURRED!!!");
			}
		}
		
	}

}
