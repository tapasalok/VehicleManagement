package com.dpautomations.vehiclemanagement.ui;

import java.io.File;
import java.util.ArrayList;

 
import com.dpautomations.vehiclemanagement.database.ExcelOperation;
import com.dpautomations.vehiclemanagement.util.CustomTypefaceSpan;
import com.dpautomations.vehiclemanagement.util.GettingDateTime;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class BaseActivity extends android.support.v7.app.ActionBar {
	
	private String path = Environment.getExternalStorageDirectory().getAbsolutePath();
	private File exportDir = new File(path+"//VehicleManagement", "");
	private File file_vehicle = new File(exportDir, "Vehicle_details "+ ".xls");
	private File file_store = new File(exportDir, "Store_details "+ ".xls");
	private String file_path1 = file_vehicle.getAbsolutePath(), file_path2 = file_store.getAbsolutePath();
	String[] filePaths = new String[] {file_path1, file_path2};
	
	
	@Override
	public void addOnMenuVisibilityListener(OnMenuVisibilityListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTab(Tab arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTab(Tab arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTab(Tab arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTab(Tab arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public View getCustomView() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getDisplayOptions() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getNavigationItemCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getNavigationMode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getSelectedNavigationIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Tab getSelectedTab() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CharSequence getSubtitle() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Tab getTabAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getTabCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public CharSequence getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isShowing() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Tab newTab() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void removeAllTabs() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeOnMenuVisibilityListener(OnMenuVisibilityListener arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeTab(Tab arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeTabAt(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void selectTab(Tab arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setBackgroundDrawable(Drawable arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCustomView(View arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCustomView(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCustomView(View arg0, LayoutParams arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDisplayHomeAsUpEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDisplayOptions(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDisplayOptions(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDisplayShowCustomEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDisplayShowHomeEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDisplayShowTitleEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDisplayUseLogoEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setIcon(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setIcon(Drawable arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setListNavigationCallbacks(SpinnerAdapter arg0, OnNavigationListener arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLogo(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLogo(Drawable arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setNavigationMode(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSelectedNavigationItem(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSubtitle(CharSequence arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSubtitle(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setTitle(CharSequence arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setTitle(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		String[] menu_item_string={"Create Excel Sheet","Share Excel Sheet"};
		
		for(int positionOfMenuItem = 0; positionOfMenuItem<=1; positionOfMenuItem++){
			TextChanger(menu_item_string[positionOfMenuItem],positionOfMenuItem,menu);
		}

		return true;
	}
	
	private void TextChanger(String string, int position, Menu menu){
		MenuItem item = menu.getItem(position);
		Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
		SpannableStringBuilder ss = new SpannableStringBuilder(string);
		float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
		ss.setSpan(new CustomTypefaceSpan("", (int)pixels, font2), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		item.setTitle(ss);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		ExcelOperation excelOperation = ExcelOperation.getInstance();
		switch (id)
        {
        case R.id.action_excel_sheet_create:
           excelOperation.writeToExcel_VehicleData(this);
           excelOperation.writeToExcel_StoreData(this);
            return true;
 
        case R.id.action_excel_sheet_share:
			File file1 = new File(file_path1);
			File file2 = new File(file_path2);

			if (file1.exists() && file2.exists()) {
				Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
				intent.setType("application/vnd.ms-excel");

				//has to be an ArrayList
			    ArrayList<Uri> uris = new ArrayList<Uri>();
			    //convert from paths to Android friendly Parcelable Uri's
			    for (String file : filePaths)
			    {
			        File fileIn = new File(file);
			        Uri u = Uri.fromFile(fileIn);
			        uris.add(u);
			    }
			    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(Intent.createChooser(intent, "Send Attachment"));

			} else if(file1.exists()){
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("application/vnd.ms-excel");

				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file1));
				System.out.println("RVD TESTING MISSCALL.XLS PATH : "
						+ Uri.fromFile(file1));
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(Intent.createChooser(intent, "Send Attachment"));
				}
			else if(file2.exists()){
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("application/vnd.ms-excel");

				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file2));
				System.out.println("RVD TESTING MISSCALL.XLS PATH : "
						+ Uri.fromFile(file2));
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(Intent.createChooser(intent, "Send Attachment"));
			}
			else{
				Toast.makeText(this, "Oops!!Excel Sheet is not created.", Toast.LENGTH_SHORT).show();
			}
			
            return true;     
 
        default:
            return super.onOptionsItemSelected(item);
        }*/
		
}
