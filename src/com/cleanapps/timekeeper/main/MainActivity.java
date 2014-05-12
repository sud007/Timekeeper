package com.cleanapps.timekeeper.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.cleanapps.timekeeper.dbhelper.LoginDBHelper;
import com.cleanapps.timekeeper.model.MainListData;
import com.cleanapps.timekeeper.util.TimeKeeperConstants;
import com.cleanapps.timekeeper.util.TimeKeeperUtilMethods;
import com.cleanapps.timekeeper.R;

public class MainActivity extends Activity implements OnClickListener {
	private final String tag = MainActivity.class.getSimpleName();
	private EditText mClientInfo, mWorkDone, mRemarks;
	private Button mLoginButton, mSaveButton, mLogoutButton;
	private String clientInfoString = "", workDoneString = "",
			remarksString = "";
	private SharedPreferences loginPreferences;
	private Editor editor;
	private Boolean isLoggedIn = false;

	public LoginDBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_input);

		initview();

		// if (savedInstanceState == null) {
		// getFragmentManager().beginTransaction()
		// .add(R.id.container, new PlaceholderFragment())
		// .commit();
		// }
	}

	private void initview() {
		// TODO Auto-generated method stub

		isLoggedIn = TimeKeeperUtilMethods.getLoginStatus(this);
		mClientInfo = (EditText) findViewById(R.id.fragment_data_input_client_info);
		mWorkDone = (EditText) findViewById(R.id.fragment_data_input_work_done);
		mRemarks = (EditText) findViewById(R.id.fragment_data_input_remarks);
		mLoginButton = (Button) findViewById(R.id.fragment_data_input_login_button);
		mLoginButton.setOnClickListener(this);
		mLogoutButton = (Button) findViewById(R.id.fragment_data_input_logout_button);
		mLogoutButton.setOnClickListener(this);
		mSaveButton = (Button) findViewById(R.id.fragment_data_input_save_button);
		mSaveButton.setOnClickListener(this);

		if (!isLoggedIn) {

			mLogoutButton.setEnabled(false);
		}else{
			mLoginButton.setEnabled(false);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.fragment_data_input_login_button:
			Toast.makeText(MainActivity.this, "Login Clicked",
					Toast.LENGTH_SHORT).show();
			mLogoutButton.setEnabled(true);
			mLoginButton.setEnabled(false);

			TimeKeeperUtilMethods.setLoginStatus(this, true);
			isLoggedIn = TimeKeeperUtilMethods.getLoginStatus(this);
			
			if(isLoggedIn){
				Toast.makeText(MainActivity.this, "Logged in",
						Toast.LENGTH_SHORT).show();
				/*
				 * here we save only the time and date as soon as user Logs-in.
				 * things left to save now in DB are: Extra details from EditTexts,
				 * Logout time, and Difference(hours logged in).
				 */
				addToDB();
				
			}
			
			
			Log.v(tag, "" + isLoggedIn);

			break;
		case R.id.fragment_data_input_save_button:
			
			/*
			 *After log-in We are allowing user to save or edit extra details
			 *about the JOb or today's Work.
			 *saving data from the EditTexts to specific row with today's date.
			 */
			
			if(isLoggedIn){
				Toast.makeText(MainActivity.this, "Updated Successfully",
						Toast.LENGTH_SHORT).show();
				//updateDB();
				
			}
			else{
				Toast.makeText(MainActivity.this, "Please Log in First",
						Toast.LENGTH_SHORT).show();
			}
			

			
			
			isLoggedIn = TimeKeeperUtilMethods.getLoginStatus(this);
			Log.v(tag, "" + isLoggedIn);
			break;
		case R.id.fragment_data_input_logout_button:
			/*
			 * finally saving curernt time as Log-out time.
			 * comparing this time with Login time saved in Shared preference for calculating Hours
			 * worked today and 
			 * emptying shared preference.
			 */
			Toast.makeText(MainActivity.this, "Logout Clicked",
					Toast.LENGTH_SHORT).show();
			mLoginButton.setEnabled(true);
			mLogoutButton.setEnabled(false);
			TimeKeeperUtilMethods.setLoginStatus(this, false);
			isLoggedIn = TimeKeeperUtilMethods.getLoginStatus(this);
			Log.v(tag, "" + isLoggedIn);

			/*
			 * here we call the method to get the saved data base
			 * There should be two methods probably
			 * getAllData-get complete data to populate the list or graph.
			 * getData(String date)- returns the selected date.
			 */
			//getTableData();
			
			break;

		default:
			break;
		}
	}

	private void getTableData() {
		// TODO Auto-generated method stub
		
		ArrayList <MainListData> list= new ArrayList<MainListData>();
		 Cursor c1 = db.getAllData();
		 //calling
		
		  if (c1 != null && c1.getCount() != 0) {
		   if (c1.moveToFirst()) {
		    do {
		     MainListData listdata = new MainListData();
		     listdata.setmDate(c1.getString(c1.getColumnIndex("Date")));
		     listdata.setmDay(c1.getString(c1.getColumnIndex("Day")));
		     listdata.setmTimeIn(c1.getString(c1.getColumnIndex(LoginDBHelper.COLUMN_TIME_IN)));
		     listdata.setmTimeOut(c1.getString(c1.getColumnIndex(db.COLUMN_TIME_OUT)));
		     listdata.setmHours(c1.getString(c1.getColumnIndex(db.COLUMN_TOTAL_HOURS)));
		     listdata.setmClientInfo(c1.getString(c1.getColumnIndex(db.COLUMN_CLIENT)));
		     listdata.setmLocation(c1.getString(c1.getColumnIndex(db.COLUMN_lOCATION)));
		     listdata.setmWorkDone(c1.getString(c1.getColumnIndex(db.COLUMN_WORK_DONE)));
		     listdata.setmRemarks(c1.getString(c1.getColumnIndex(db.COLUMN_REMARKS)));
		     
		     ///populating the array list
		     list.add(listdata);
		 
		    } while (c1.moveToNext());
		   }
		  }
		  c1.close();
		  
		  Log.v(tag, list.toString());
	}

	private String getDayOfWeek() {

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

	private void addToDB(){
		

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		String mDay = dateFormat.format(date);
		String mTime = timeFormat.format(date);

		String dayOfWeek = getDayOfWeek();

		long timeInMillis=System.currentTimeMillis();
		String timeString=TimeKeeperUtilMethods.getTimeString(timeInMillis);
		
		db = new LoginDBHelper(this);

		try {

			Log.v(tag, "" + mDay + " " + timeString + dayOfWeek+mTime);
			
//			 db.addData(new MainListData(mDay, dayOfWeek, timeString, "", "",
//			  clientInfoString, "", workDoneString, remarksString));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}
	
	private void saveDateTime(String date,String timeInMillis){
		
		//share into shared preferences
		//methods are in UTILmethods setLoginInfo();
		
		
	}
	private void updateDB(){
		clientInfoString = mClientInfo.getText().toString();
		workDoneString = mWorkDone.getText().toString();
		remarksString = mRemarks.getText().toString();
		
		
		try {

			Log.v(tag, "" + clientInfoString + " " + workDoneString + remarksString);
			 db.updateData(new MainListData("", "", "", "", "",
			  clientInfoString, "", workDoneString, remarksString),TimeKeeperUtilMethods.getLoginDate(this));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_main, container,
//					false);
//			return rootView;
//		}
//	}

}
