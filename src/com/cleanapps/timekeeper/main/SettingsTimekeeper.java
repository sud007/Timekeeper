package com.cleanapps.timekeeper.main;

import com.cleanapps.timekeeper.R;

import android.app.Activity;
import android.os.Bundle;

public class SettingsTimekeeper extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_timekeeper);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		this.finish();
	}

}
