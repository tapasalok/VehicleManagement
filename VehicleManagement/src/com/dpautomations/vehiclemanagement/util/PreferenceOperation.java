package com.dpautomations.vehiclemanagement.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class PreferenceOperation {
	private static PreferenceOperation fragmentOpration;
	private static Activity activity;

	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	private String SHARED_PREFERENCE_NAME = "smart_report";

	private PreferenceOperation(final Activity context) {
		// TODO Auto-generated constructor stub

	}

	public static PreferenceOperation getInstance(final Activity context) {
		activity = context;
		if (fragmentOpration == null) {
			fragmentOpration = new PreferenceOperation(context);
		}
		return fragmentOpration;
	}

	public SharedPreferences getSharedPreferences() {
		sharedPreferences = activity.getSharedPreferences(
				SHARED_PREFERENCE_NAME, activity.MODE_PRIVATE);
		return sharedPreferences;
	}
	
	public void putTotalQuantity(String totalQuantity) {
		// TODO Auto-generated method stub
		editor = getSharedPreferences().edit();
		editor.putString("totalQuantity", totalQuantity);
		editor.commit();
	}

	public String getTotalQuantity() {
		return getSharedPreferences().getString("totalQuantity", "");
	}

	public void putLastUpdatedQuantityDate(String Mobile_No) {
		// TODO Auto-generated method stub
		editor = getSharedPreferences().edit();
		editor.putString("updatedquantitydate", Mobile_No);
		editor.commit();
	}

	public String getLastUpdatedQuantityDate() {
		return getSharedPreferences().getString("updatedquantitydate", "");
	}

	public void putNetWeight(String totalQuantity) {
		// TODO Auto-generated method stub
		editor = getSharedPreferences().edit();
		editor.putString("netweight", totalQuantity);
		editor.commit();
	}

	public String getNetWeight() {
		return getSharedPreferences().getString("netweight", "");
	}

	public void putLastUpdatedWeightDate(String Mobile_No) {
		// TODO Auto-generated method stub
		editor = getSharedPreferences().edit();
		editor.putString("updatedweightdate", Mobile_No);
		editor.commit();
	}

	public String getLastUpdatedWeightDate() {
		return getSharedPreferences().getString("updatedweightdate", "");
	}

}
