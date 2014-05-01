//package com.cleanapps.timekeeper.main;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//
//import com.cleanapps.timekeeper.util.TimeKeeperConstants;
//import com.viewpagerindicator.IconPagerAdapter;
//
//public class FragmentAdapter extends FragmentStatePagerAdapter implements
//		IconPagerAdapter {
//
//	private final String[] TITLES = { TimeKeeperConstants.ACTIVITY,
//			TimeKeeperConstants.MONITOR, TimeKeeperConstants.LOG };
//
//	public FragmentAdapter(FragmentManager fm) {
//		super(fm);
//		// TODO Auto-generated constructor stub
//	}
//
//	public int getIconResId(int index) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	public Fragment getItem(int position) {
//		// TODO Auto-generated method stub
//		Fragment fragment = new FragmentTimerScreen();
//		switch (position) {
//		case 0:
//			fragment = new FragmentTimerScreen();
//			break;
//		case 1:
//			fragment = new FragmentMonitorScreen();
//			break;
//		case 2:
//			fragment = new FragmentGraphsScreen();
//			break;
//		}
//		return fragment;
//	}
//
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return TITLES.length;
//	}
//
//	public CharSequence getPageTitle(int position) {
//
//		return TITLES[position];
//	}
//
//}