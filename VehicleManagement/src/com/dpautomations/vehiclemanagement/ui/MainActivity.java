package com.dpautomations.vehiclemanagement.ui;

import java.io.File;
import java.util.ArrayList;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.adapters.NavigationDrawerAdapter;
import com.dpautomations.vehiclemanagement.database.ExcelOperation;
import com.dpautomations.vehiclemanagement.util.CustomTypefaceSpan;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.TypedValue;
import android.support.v4.widget.DrawerLayout;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
	
	
	private String path = Environment.getExternalStorageDirectory().getAbsolutePath();
	private File exportDir = new File(path+"//VehicleManagement", "");
	private File file_vehicle = new File(exportDir, "Vehicle_details "+ ".xls");
	private File file_store = new File(exportDir, "Store_details "+ ".xls");
	private String file_path1 = file_vehicle.getAbsolutePath(), file_path2 = file_store.getAbsolutePath();
	String[] filePaths = new String[] {file_path1, file_path2};

    private DrawerLayout drawerLayout;
    private ListView listView_drawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationDrawerAdapter myAdapter;
    private String[] menu;
    private ActionBar actionBar;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        actionBar=getSupportActionBar();
        //actionBar.setIcon(R.drawable.vehicle_management_actionbar_icon);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        
        menu = new String[] { "Vehicles", "Store", "Vehicle Registration", "Store Registration" };

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        listView_drawer=(ListView) findViewById(R.id.left_drawer);

        myAdapter=new NavigationDrawerAdapter(this);
        listView_drawer.setAdapter(myAdapter);
        listView_drawer.setOnItemClickListener(this);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.drawable.ic_drawer,R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     // Defer code dependent on restoration of previous instance state.
        drawerLayout.post(new Runnable() {
             @Override
             public void run() {
                 actionBarDrawerToggle.syncState();
             }
         });
        
        selectItem(0);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);

		String[] menu_item_string={"Create Excel Sheet","Share Excel Sheet"};
		
		/*for(int positionOfMenuItem = 0; positionOfMenuItem<=1; positionOfMenuItem++){
			TextChanger(menu_item_string[positionOfMenuItem],positionOfMenuItem,menu);
		}*/

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
    	if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
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
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
        
    }

    private void selectItem(int position){
    	Bundle args=new Bundle();
    	BaseFragment detail = new DetailFragment();
    	args.putString("Menu", menu[position]);
    	switch (position) {

		case 0:
			detail = new VehicleListingFragment();
			break;

		case 1:
			detail = new StoreListingFragment();
			break;

		case 2:
			detail = new VehicleRegistationFragment();
			break;
			
		case 3:
			detail = new StoreInWardFragment();
			break;
			
		default:
			detail = new DetailFragment();
			break;
		}

        detail.setArguments(args);

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,detail);
        fragmentTransaction.commit();

        listView_drawer.setItemChecked(position,true);
        setTitle(NavigationDrawerAdapter.myData[position]);
        drawerLayout.closeDrawer(listView_drawer);
    }

    private void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

}