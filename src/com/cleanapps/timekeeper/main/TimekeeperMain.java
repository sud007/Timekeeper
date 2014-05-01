package com.cleanapps.timekeeper.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.cleanapps.timekeeper.R;

public class TimekeeperMain extends FragmentActivity {

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	setContentView(R.layout.timer_screen);
	
	tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_timer_screen_tabs);
	pager = (ViewPager) findViewById(R.id.activity_timer_screen_viewpager);
//	pager.setAdapter(new TestAdapter(getSupportFragmentManager()));
	
	
	}
	
}
