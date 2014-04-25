package com.cleanapps.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

public class TimeKeeperUtilMethods {

	private final static String tag = TimeKeeperUtilMethods.class
			.getSimpleName();
	private static SharedPreferences loginPreferences, loginTimeInfo;
	private static Editor editor;

	/**
	 * This methods saves the Login status.
	 * 
	 * @param ctx
	 * @param value
	 */
	public static void setLoginStatus(Context ctx, Boolean value) {

		loginPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		editor = loginPreferences.edit();
		editor.putBoolean(TimeKeeperConstants.IS_LOGGED_IN, value);
		editor.commit();
	}

	/**
	 * This method returns Login status.
	 * 
	 * @param ctx
	 * @return
	 */
	public static Boolean getLoginStatus(Context ctx) {

		loginPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		Boolean status = loginPreferences.getBoolean(
				TimeKeeperConstants.IS_LOGGED_IN, false);
		Log.v(tag, status + "");

		return status;

	}

	/**
	 * This method is used to save the date and time after user logs in temporarily
	 * to calculate Hours Logged in.
	 * 
	 * @param ctx
	 * @param date
	 * @param timeInMillis
	 */
	public static void setLoginInfo(Context ctx, String date,
			String timeInMillis) {

		loginTimeInfo = PreferenceManager.getDefaultSharedPreferences(ctx);
		editor = loginTimeInfo.edit();
		editor.putString(TimeKeeperConstants.LOGIN_DATE, date);
		editor.putString(TimeKeeperConstants.LOGIN_TIME, timeInMillis);
		editor.commit();
	}

	/**
	 * This method returns the time of Login.
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getLoginTime(Context ctx) {

		loginTimeInfo = PreferenceManager.getDefaultSharedPreferences(ctx);
		String time = loginPreferences.getString(
				TimeKeeperConstants.LOGIN_TIME, "");
		Log.v(tag, time + "");

		return time;

	}

	/**
	 * this method returns the date of login.
	 * 
	 * @param ctx
	 * @return
	 */
	public static String getLoginDate(Context ctx) {

		loginTimeInfo = PreferenceManager.getDefaultSharedPreferences(ctx);
		String date = loginPreferences.getString(
				TimeKeeperConstants.LOGIN_DATE, "");
		Log.v(tag, date + "");

		return date;

	}

	/**
	 * This method converts millis to time String.
	 * can be used for timer and reference fo calculations.
	 * 
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static String getTimeString(long timeInMillis) {
		//ignore this methods for awhile i am refining logic
		
		Log.e("day", "miliday" + timeInMillis);
		long seconds = (long) (timeInMillis / 1000) % 60;
		long minutes = (long) ((timeInMillis / (1000 * 60)) % 60);
		long hours = (long) ((timeInMillis / (1000 * 60 * 60)) % 24);
		long days = (int) ((timeInMillis / (1000 * 60 * 60 * 24)) % 365);

		String timeString = "" + days + hours + minutes + seconds;
		Log.e("day", "time" + timeString);
		return timeString;

	}

}
