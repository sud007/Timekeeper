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

public class TimekeeperMain extends FragmentActivity{
	private final String tag = TimekeeperMain.class.getSimpleName();

	private PagerSlidingTabStrip tabs;
	private ViewPager mPager;
	private MyPagerAdapter adapter;
//	private TitlePageIndicator mpageIndicator;
//	private FragmentAdapter mFragmentAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timer_screen);


		tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_timer_screen_tabs);
		mPager = (ViewPager) findViewById(R.id.activity_timer_screen_viewpager);
//		mFragmentAdapter=new FragmentAdapter(getSupportFragmentManager());
//		mpageIndicator=(TitlePageIndicator)findViewById(R.id.indicator);
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		
		
		mPager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		mPager.setPageMargin(pageMargin);

		tabs.setViewPager(mPager);
		
//		mpageIndicator.setViewPager(mPager);
		
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

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new FragmentMonitorScreen();
			switch (position) {
			case 0:
				fragment = new FragmentTimerScreen();
				break;
			case 1:
				fragment = new FragmentMonitorScreen();
				break;
			case 2:
				fragment = new FragmentGraphsScreen();
				break;
			}
			return fragment;
		}
//		
//		@Override
//		public Fragment getItem(int position) {
//			return SuperAwesomeCardFragment.newInstance(position);
//		}

	}

}
