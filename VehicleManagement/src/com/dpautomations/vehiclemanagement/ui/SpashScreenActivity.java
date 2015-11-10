package com.dpautomations.vehiclemanagement.ui;

import com.dpautomations.vehiclemanagement.R;
import com.dpautomations.vehiclemanagement.R.anim;
import com.dpautomations.vehiclemanagement.R.id;
import com.dpautomations.vehiclemanagement.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SpashScreenActivity extends Activity {
	private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		startAnimation();
	}

	private void startAnimation() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
		anim.reset();
		RelativeLayout l = (RelativeLayout) findViewById(R.id.splashScreen);
		l.clearAnimation();
		l.startAnimation(anim);
		anim = AnimationUtils.loadAnimation(this, R.anim.tranlate);
		anim.reset();
		launchLoginScreen();
	}

	private void launchLoginScreen() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(SpashScreenActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
				// TODO Auto-generated method stub

			}
		}, SPLASH_TIME_OUT);
	}

}
