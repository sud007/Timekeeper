package com.cleanapps.timekeeper.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cleanapps.timekeeper.R;

public class ActivityRegistration extends Activity {

	private ParallaxImageView mBackground;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_registration);

		initView();

	}

	private void initView() {
		mBackground = (ParallaxImageView) findViewById(R.id.background);
		mBackground.setForwardTiltOffset(.35f);
		mBackground.setParallaxIntensity(1.1f);
		mBackground.setImageDrawable(getResources().getDrawable(
				R.drawable.splash_bg));
		mBackground.registerSensorManager();

		// this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}

	 @Override
	 public void onResume() {
	 super.onResume();
	
	 mBackground.registerSensorManager();
	 }
	
	 @Override
	 public void onPause() {
	 mBackground.unregisterSensorManager();
	 super.onPause();
	 }

}
