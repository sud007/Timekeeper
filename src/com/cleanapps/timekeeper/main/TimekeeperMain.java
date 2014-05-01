package com.cleanapps.timekeeper.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.cleanapps.timekeeper.R;
import com.cleanapps.timekeeper.util.TimeKeeperConstants;

public class TimekeeperMain extends FragmentActivity {

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer_screen);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_timer_screen_tabs);
		pager = (ViewPager) findViewById(R.id.activity_timer_screen_viewpager);
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { TimeKeeperConstants.ACTIVITY,
				TimeKeeperConstants.MONITOR, TimeKeeperConstants.LOG };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}
//
//		@Override
//		public Fragment getItem(int position) {
//			Fragment fragment = new FragmentTimerScreen();
//			switch (position) {
//			case 0:
//				fragment = new FragmentTimerScreen();
//				break;
//			case 1:
//				fragment = new FragmentMonitorScreen();
//				Toast.makeText(getBaseContext(), "2", Toast.LENGTH_SHORT).show();
//				break;
//			case 2:
//				fragment = new FragmentGraphsScreen();
//				break;
//			}
//			return fragment;
//		}
		
		@Override
		public Fragment getItem(int position) {
			return SuperAwesomeCardFragment.newInstance(position);
		}

	}
}
