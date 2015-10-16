package com.dpautomations.vehiclemanagement.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.R.id;
import com.dpautomations.vehiclemanagement.R.layout;

public class DetailFragment extends BaseFragment {
	private TextView text;
	private Button createExcel, shareExcel;
	private String path, path1;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private Calendar cal;
	private int countofrow = 0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle args) {
		View view = inflater.inflate(R.layout.menu_detail_fragment, container,
				false);
		createExcel = (Button) view.findViewById(R.id.button1);
		shareExcel = (Button) view.findViewById(R.id.button2);
		cal = Calendar.getInstance();
		
		
		String menu = getArguments().getString("Menu");
		text = (TextView) view.findViewById(R.id.detail);
		text.setText(menu);

		
		
		createExcel.setOnClickListener(createExcelListener);
		shareExcel.setOnClickListener(shareExcelListener);
		return view;
	}

	OnClickListener createExcelListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Toast.makeText(getActivity(),
					"Wait, Excel Sheet is being created!!", Toast.LENGTH_SHORT)
					.show();
			// DatabaseOperation databaseOperation =
			// DatabaseOperation.getInstance();
			// databaseOperation.insert(contentValues);

			writeToExcel("ABC", "XYZ", sdf.format(cal.getTime()));
		
		}
	};

	public void writeToExcel(String incomingNumber, String msg, String callDate2) {
		try {

			/*
			 * ExcelDto dto=new ExcelDto(); dto.setMobileNo(incomingNumber);
			 * dto.setMsg(msg); dto.setDate(callDate2); finalExcelData.add(dto);
			 */

			WritableWorkbook wb;
			WorkbookSettings wbSettings = new WorkbookSettings();
			path1 = Environment.getExternalStorageDirectory().getAbsolutePath();

			WritableFont boldFont = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD);
			wbSettings.setLocale(new Locale("en", "EN"));
			File file = new File(path1 + "/Truck_detail.xls");
			wb = Workbook.createWorkbook(new File(path1 + "/Truck_detail.xls"),
					wbSettings);

			WritableCellFormat cellFormat = new WritableCellFormat(boldFont);

			HashMap<String, Integer> ColumnMap = new HashMap<String, Integer>();
			List<String> colList = new ArrayList<String>();

			int maxColCount = 0;
			int i = 0;
			WritableSheet wsheet = null;
			wsheet = wb.createSheet("TruckDetails", i);
			i++;
			ColumnMap.put("PLANT", 0);

			jxl.write.Label l1 = new jxl.write.Label(maxColCount, countofrow,
					"PLANT", cellFormat);
			wsheet.addCell(l1);
			maxColCount = maxColCount + 1;
			jxl.write.Label l2 = new jxl.write.Label(maxColCount, countofrow,
					"TRUCK", cellFormat);
			wsheet.addCell(l2);

			ColumnMap.put("TRUCK", 1);
			maxColCount = maxColCount + 1;
			jxl.write.Label l3 = new jxl.write.Label(maxColCount, countofrow,
					"DATE", cellFormat);
			wsheet.addCell(l3);
			ColumnMap.put("DATE", 2);
			maxColCount = maxColCount + 1;

			// for (ExcelDto excelDto : finalExcelData) {
			wsheet.addCell(new jxl.write.Label(ColumnMap.get("PLANT"),
					countofrow, incomingNumber, cellFormat));

			wsheet.addCell(new jxl.write.Label(ColumnMap.get("TRUCK"),
					countofrow, msg, cellFormat));
			wsheet.addCell(new jxl.write.Label(ColumnMap.get("DATE"),
					countofrow, callDate2, cellFormat));

			countofrow++;

			// }

			wb.write();

			wb.close();
			Toast.makeText(getActivity(), "EXCEL SHEET CREATED..",
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EXCEPTION OCCURRED!!!");
		}
	}
	
	OnClickListener shareExcelListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			path = Environment.getExternalStorageDirectory().getAbsolutePath();
			File file = new File(path + "/Truck_detail.xls");

			if (file.exists()) {
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("application/vnd.ms-excel");

				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
				System.out.println("RVD TESTING MISSCALL.XLS PATH : "
						+ Uri.fromFile(file));
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(Intent.createChooser(intent, "Send Attachment"));

			} else {
				Toast.makeText(getActivity(),
						"Oops!!Excel Sheet is not created.", Toast.LENGTH_SHORT)
						.show();
				return;
			}
		
		}
	};

}