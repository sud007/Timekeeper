package com.cleanapps.timekeeper.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.cleanapps.timekeeper.R;
import com.cleanapps.timekeeper.components.FloatLabelEditText;
import com.cleanapps.timekeeper.components.ParallaxImageView;

public class ActivityRegistration extends Activity implements OnClickListener {

	private Button gSignIn;
	private final String tag = ActivityRegistration.class.getSimpleName();

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
		gSignIn = (Button) findViewById(R.id.activity_registration_sign_in_button);
		gSignIn.setOnClickListener(this);
		mBackground = (ParallaxImageView) findViewById(R.id.background);
		mBackground.setForwardTiltOffset(.35f);
		mBackground.setParallaxIntensity(1.1f);
		mBackground.setImageDrawable(getResources().getDrawable(
				R.drawable.splash_bg));
		mBackground.registerSensorManager();

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.activity_registration_sign_in_button:
			Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
			
			 Intent intent = new Intent(ActivityRegistration.this, TimekeeperMain.class);
			 ActivityRegistration.this.startActivity(intent);
			 ActivityRegistration.this.finish();

			break;

		default:
			break;
		}

	}

}
