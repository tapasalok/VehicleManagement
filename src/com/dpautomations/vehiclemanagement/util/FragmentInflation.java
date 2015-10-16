package com.dpautomations.vehiclemanagement.util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.ui.BaseFragment;

public class FragmentInflation {
	private static FragmentInflation fragmentInflation;

	private FragmentInflation() {
		// TODO Auto-generated constructor stub
	}

	public static FragmentInflation getInstance() {
		// TODO Auto-generated method stub
		if (fragmentInflation == null) {
			fragmentInflation = new FragmentInflation();
		}
		return fragmentInflation;
	}

	/**
	 * @param position
	 * @param bundle
	 * @param baseFragment
	 * @param addToBackStack
	 * @param activity
	 */
	public void gotoFragment(int position, Bundle bundle,
			BaseFragment baseFragment, boolean addToBackStack, Activity activity) {
		bundle.putInt("position", position);
		baseFragment.setArguments(bundle);
		FragmentManager fragmentManager = activity.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		if (addToBackStack) {
			fragmentTransaction.addToBackStack(null);
		}

		fragmentTransaction.replace(R.id.content_frame, baseFragment).commit();
	}
}
