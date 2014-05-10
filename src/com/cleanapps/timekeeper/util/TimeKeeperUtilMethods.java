package com.cleanapps.timekeeper.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class TimeKeeperUtilMethods {

	private final static String tag = TimeKeeperUtilMethods.class
			.getSimpleName();
	private static SharedPreferences globalSharedPreferences;
	private static Editor editor;

	/**
	 * This methods saves the Login status.
	 * 
	 * @param ctx
	 * @param value
	 */
	public static void setLoginStatus(Context ctx, Boolean value) {

		globalSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		editor = globalSharedPreferences.edit();
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

		globalSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		Boolean status = globalSharedPreferences.getBoolean(
				TimeKeeperConstants.IS_LOGGED_IN, false);
		Log.v(tag, status + "");

		return status;

	}

	/**
	 * this method saves the status if timer is runnning
	 * 
	 * @param ctx
	 * @param value
	 */
	public static void setTimerRunning(Context ctx, Boolean value) {
		globalSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		editor = globalSharedPreferences.edit();
		editor.putBoolean(TimeKeeperConstants.IS_RUNNING, value);
		editor.commit();

	}

	/**
	 * This method returns Login status.
	 * 
	 * @param ctx
	 * @return
	 */
	public static Boolean getTimerRunning(Context ctx) {

		globalSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		Boolean status = globalSharedPreferences.getBoolean(
				TimeKeeperConstants.IS_RUNNING, false);

		return status;

	}

	/**
	 * This method is used to save the date and time after user logs in
	 * temporarily to calculate Hours Logged in.
	 * 
	 * @param ctx
	 * @param date
	 * @param timeInMillis
	 */
	public static void setLoginInfo(Context ctx, String date, long timeInMillis) {

		globalSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		editor = globalSharedPreferences.edit();
		editor.putString(TimeKeeperConstants.LOGIN_DATE, date);
		editor.putLong(TimeKeeperConstants.LOGIN_TIME, timeInMillis);
		editor.commit();
	}

	/**
	 * This method returns the time of Login.
	 * 
	 * @param ctx
	 * @return
	 */
	public static long getLoginTime(Context ctx) {

		globalSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		long time = globalSharedPreferences.getLong(
				TimeKeeperConstants.LOGIN_TIME, 0);
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

		globalSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(ctx);
		String date = globalSharedPreferences.getString(
				TimeKeeperConstants.LOGIN_DATE, "");
		Log.v(tag, date + "");

		return date;

	}

	/**
	 * This method converts millis to time String. can be used for timer and
	 * reference fo calculations.
	 * 
	 * 
	 * @param timeInMillis
	 * @return
	 */
	public static String getTimeString(long timeInMillis) {
		// ignore this methods for awhile i am refining logic

		Log.e("day", "miliday" + timeInMillis);
		long seconds = (long) (timeInMillis / 1000) % 60;
		long minutes = (long) ((timeInMillis / (1000 * 60)) % 60);
		long hours = (long) ((timeInMillis / (1000 * 60 * 60)) % 24);
		long days = (int) ((timeInMillis / (1000 * 60 * 60 * 24)) % 365);

		String timeString = "" + days + hours + minutes + seconds;
		Log.e("day", "time" + timeString);
		return timeString;

	}

	/**
	 * method to get day name of the week
	 * 
	 * @return
	 */
	public String getDayOfWeek() {

		String dayOfWeek = "";
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);

		switch (day) {
		case 1:
			dayOfWeek = TimeKeeperConstants.SUNDAY;
			break;

		case 2:
			dayOfWeek = TimeKeeperConstants.MONDAY;
			break;

		case 3:
			dayOfWeek = TimeKeeperConstants.TUESDAY;
			break;

		case 4:
			dayOfWeek = TimeKeeperConstants.WEDNESDAY;
			break;

		case 5:
			dayOfWeek = TimeKeeperConstants.THURSDAY;
			break;

		case 6:
			dayOfWeek = TimeKeeperConstants.FRIDAY;
			break;

		case 7:
			dayOfWeek = TimeKeeperConstants.SATURDAY;
			break;

		default:
			break;
		}
		return dayOfWeek;

	}

	public static String getDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String mDay = dateFormat.format(date);

		return mDay;
	}

	public static void animateTextColor(TextView timerView) {
		final Property<TextView, Integer> property = new Property<TextView, Integer>(
				int.class, "textColor") {
			@Override
			public Integer get(TextView object) {
				return object.getCurrentTextColor();
			}

			@Override
			public void set(TextView object, Integer value) {
				object.setTextColor(value);
			}
		};

		final ObjectAnimator animator = ObjectAnimator.ofInt(timerView,
				property, Color.GREEN);
		animator.setDuration(700L);
		animator.setEvaluator(new ArgbEvaluator());
		animator.setInterpolator(new DecelerateInterpolator(0.1f));
		animator.start();
	}
}
