package com.example.vehiclemanagement;

import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	String[] menu;
	DrawerLayout dLayout;
	ListView dList;
	Button shareButton, createExcel;
	ArrayAdapter<String> adapter;
	String path,path1;
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private Calendar cal;
	int countofrow = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		menu = new String[] { "User Login", "Vehicles", "Vehicle Registration",
				"Supplier Registration" };
		dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		dList = (ListView) findViewById(R.id.left_drawer);
		shareButton=(Button) findViewById(R.id.button1);
		createExcel=(Button) findViewById(R.id.createExcelButton);
		cal=Calendar.getInstance();
		
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menu);

		dList.setAdapter(adapter);
		dList.setSelector(android.R.color.holo_blue_dark);

		dList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				dLayout.closeDrawers();
				Bundle args = new Bundle();
				args.putString("Menu", menu[position]);
				Fragment detail = new DetailFragment();
				detail.setArguments(args);
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, detail).commit();

			}

		});
	}

	
	public void shareExcel(View view) {
		path=Environment.getExternalStorageDirectory().getAbsolutePath();
		File file=new File(path + "/Truck_detail.xls");

		if(file.exists()){ 
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("application/vnd.ms-excel");
				
				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
				System.out.println("RVD TESTING MISSCALL.XLS PATH : "+Uri.fromFile(file));
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(Intent.createChooser(intent, "Send Attachment"));
			
		}
		else{
			Toast.makeText(MainActivity.this, "Oops!!Excel Sheet is not created.", Toast.LENGTH_SHORT).show();
			return;
		}
	}
	
	public void createExcelsheet(View view){
		Toast.makeText(MainActivity.this, "Wait, Excel Sheet is being created!!", Toast.LENGTH_SHORT).show();
		writeToExcel("ABC","XYZ",sdf.format(cal.getTime()));
	}
	
	public void writeToExcel(String incomingNumber, String msg, String callDate2) {
		try {
			
			/*ExcelDto dto=new ExcelDto();
			dto.setMobileNo(incomingNumber);
			dto.setMsg(msg);
			dto.setDate(callDate2);
			finalExcelData.add(dto);*/
			
			WritableWorkbook wb;
			WorkbookSettings wbSettings = new WorkbookSettings();
			path1=Environment.getExternalStorageDirectory().getAbsolutePath();
			
			WritableFont boldFont = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
			wbSettings.setLocale(new Locale("en", "EN"));
			File  file=new File(path1+ "/Truck_detail.xls");
			wb = Workbook.createWorkbook(new File(path1+"/Truck_detail.xls"), wbSettings);
			
			WritableCellFormat cellFormat = new WritableCellFormat(boldFont);
			
			HashMap<String, Integer> ColumnMap = new HashMap<String, Integer>();
			List<String> colList = new ArrayList<String>();
			
			int maxColCount = 0;
			int i = 0;
			WritableSheet wsheet = null;
			wsheet = wb.createSheet("TruckDetails", i);
			i++;
			ColumnMap.put("PLANT", 0);

			jxl.write.Label l1 = new jxl.write.Label(maxColCount, countofrow, "PLANT",
					cellFormat);
			wsheet.addCell(l1);
			maxColCount = maxColCount + 1;
			jxl.write.Label l2 = new jxl.write.Label(maxColCount, countofrow, "TRUCK",
					cellFormat);
			wsheet.addCell(l2);

			ColumnMap.put("TRUCK", 1);
			maxColCount = maxColCount + 1;
			jxl.write.Label l3 = new jxl.write.Label(maxColCount, countofrow, "DATE",
					cellFormat);
			wsheet.addCell(l3);
			ColumnMap.put("DATE", 2);
			maxColCount = maxColCount + 1;

			
			//for (ExcelDto excelDto : finalExcelData) {
				wsheet.addCell(new jxl.write.Label(ColumnMap.get("PLANT"), countofrow, incomingNumber,
						cellFormat));

				wsheet.addCell(new jxl.write.Label(ColumnMap.get("TRUCK"), countofrow, msg,
						cellFormat));
				wsheet.addCell(new jxl.write.Label(ColumnMap.get("DATE"), countofrow, callDate2,
						cellFormat));

				countofrow++;
				
			//}

			wb.write();

			wb.close();
			Toast.makeText(MainActivity.this, "EXCEL SHEET CREATED..", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EXCEPTION OCCURRED!!!");
		}
	}
	
}