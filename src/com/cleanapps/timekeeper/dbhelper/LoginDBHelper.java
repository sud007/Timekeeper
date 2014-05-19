package com.cleanapps.timekeeper.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cleanapps.model.ClientData;
import com.cleanapps.model.CompanyData;
import com.cleanapps.model.LocationData;
import com.cleanapps.model.TimeSheetData;
import com.cleanapps.model.UserData;

public class LoginDBHelper extends SQLiteOpenHelper {

	public static final int DATABSE_VERSION = 1;
	public static final String DATABASE_NAME = "timeKeeper";

	/*
	 * Table  USER 
	 * Contains logged in User specific info.
	 */
	public static final String TABLE_USER = "userTable";
	/*
	 * Table  COMPANY 
	 * Contains Multiple Companies Name and info that users visit
	 */
	public static final String TABLE_COMPANY = "companyTable";
	/*
	 * Table  Location 
	 * Contains list of location user usually visits
	 */
	public static final String TABLE_LOCATION = "locationTable";
	/*
	 * Table  CLIENT 
	 * Contains list of clients user works for
	 */
	public static final String TABLE_CLIENT = "clientTable";
	/*
	 * Table  TIMESHEET 
	 * Contains user time-sheet details
	 */
	public static final String TABLE_TIMESHEET = "timesheetTable";



	/**
	 * Column Names **TABLE USER** 
	 * @param context
	 */
	public static final String COLUMN_USER_NAME = "User_Name";
	public static final String COLUMN_WORKING_HOURS = "Working_Hours";
	public static final String COLUMN_WEEKEND= "Weekend";  //(1 = Sun,2 = Sat n Sun)

	/**
	 * Column Names **TABLE COMPANY** 
	 * @param context
	 */
	public static final String COLUMN_COMPANY_NAME = "Company_Name";

	/**
	 * Column Names **TABLE LOCATION** 
	 * @param context
	 */
	public static final String COLUMN_LOCATION_NAME = "Location_Name";

	/**
	 * Column Names **TABLE CLIENT** 
	 * @param context
	 */
	public static final String COLUMN_CLIENT_NAME= "CLIENT_NAME";  //(1 = Sun,2 = Sat n Sun)

	/**
	 * Column Names **TABLE TIMESHEET** 
	 * @param context
	 */
	public static final String COLUMN_DATE = "Date";
	public static final String COLUMN_DAY = "Day";
	public static final String COLUMN_TIME_IN = "Time_In";
	public static final String COLUMN_TIME_OUT = "Time_Out";
	public static final String COLUMN_TOTAL_HOURS = "Hours";
	public static final String COLUMN_CLIENT = "Client";
	public static final String COLUMN_LOCATION = "Map_Location";
	public static final String COLUMN_WORK_DONE = "Work_Done";
	public static final String COLUMN_REMARKS = "Remarks";

	private static LoginDBHelper helper = null;
	private SQLiteDatabase db = null;
	public static LoginDBHelper getInstance() {

		if (helper == null) {
			synchronized (LoginDBHelper.class) {
				if (helper == null) {
					// helper=new LoginDBHelper();
				}
			}
		}
		return helper;
	}

	public LoginDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABSE_VERSION);
		db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		//USER TABLE
		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
		+ COLUMN_USER_NAME + " TEXT PRIMARY KEY,"+ COLUMN_WORKING_HOURS +" INT ,"
		+ COLUMN_WEEKEND + " INT"+ ")";

		//COMPANY TABLE
		String CREATE_COMPANY_TABLE = "CREATE TABLE " + TABLE_COMPANY + "("
		+ COLUMN_COMPANY_NAME + " TEXT "+")";

		//LOCATION TABLE
		String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATION + "("
		+ COLUMN_LOCATION_NAME + " TEXT"+")";

		//LOCATION TABLE
		String CREATE_CLIENT_TABLE = "CREATE TABLE " + TABLE_CLIENT + "("
		+ COLUMN_CLIENT_NAME + " TEXT"+")";

		String CREATE_TIMESHEET_TABLE = "CREATE TABLE " + TABLE_TIMESHEET + "("
		+ COLUMN_DATE + " TEXT PRIMARY KEY," + COLUMN_DAY + " TEXT,"
		+ COLUMN_TIME_IN + " TEXT," + COLUMN_TIME_OUT + " TEXT,"
		+ COLUMN_TOTAL_HOURS + " TEXT," + COLUMN_CLIENT + " TEXT,"
		+ COLUMN_LOCATION + " TEXT," + COLUMN_WORK_DONE + " TEXT,"
		+ COLUMN_REMARKS + " TEXT" + ")";

		db.execSQL(CREATE_USER_TABLE);
		db.execSQL(CREATE_COMPANY_TABLE);
		db.execSQL(CREATE_LOCATION_TABLE);
		db.execSQL(CREATE_CLIENT_TABLE);
		db.execSQL(CREATE_TIMESHEET_TABLE);


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	@Override
	public synchronized void close() {
		if (helper != null)
			db.close();
	}

	//----Start----------------------------USER SPECIFIC DATA TRANSACTIONS------------------------------------//

	public boolean addUserData (UserData userData)   //ADD
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_USER_NAME, userData.getUserName());
		contentValues.put(COLUMN_WORKING_HOURS, userData.getWorkingHours());
		contentValues.put(COLUMN_WEEKEND, userData.getWeekendType());

		long result = db.insert(TABLE_USER, null, contentValues);
		db.close();

		//Notifying if transaction was successful 
		if(result == -1)return false;
		else return true;

	}

	public boolean updateUserData (UserData userData) //UPDATE
	{

		String args = COLUMN_USER_NAME+"="+ userData.getUserName();

		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_USER_NAME, userData.getUserName());
		contentValues.put(COLUMN_WORKING_HOURS, userData.getWorkingHours());
		contentValues.put(COLUMN_WEEKEND, userData.getWeekendType());

		long result = db.update(TABLE_USER, contentValues,args,null);
		db.close();

		//Notifying if transaction was successful 
		if(result > 0)return true;
		else return false;

	}


	public boolean deleteUserData ()             //DELETE
	{	
		long result = db.delete(TABLE_USER, "1", null);
		db.close();

		//Notifying if transaction was successful 
		if(result > 0)return true;
		else return false;

	}


	//----End------------------------------USER SPECIFIC DATA TRANSACTIONS--------------------------------------//



	//----Start------------------------------COMPANY DATA TRANSACTIONS-----------------------------------------//


	public boolean addCompanyData (CompanyData companyData)   //ADD
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_COMPANY_NAME, companyData.getCompanyName());


		long result = db.insert(TABLE_COMPANY, null, contentValues);
		db.close();

		//Notifying if transaction was successful 
		if(result == -1)return false;
		else return true;

	}


	public boolean deleteCompanyData ()             //DELETE
	{	
		long result = db.delete(TABLE_COMPANY, "1", null);
		db.close();

		//Notifying if transaction was successful 
		if(result > 0)return true;
		else return false;

	}

	//----End--------------------------------COMPANY DATA TRANSACTIONS-----------------------------------------//

	//----Start------------------------------LOCATION DATA TRANSACTIONS-----------------------------------------//


	public boolean addLocationData (LocationData locationData)   //ADD
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_LOCATION_NAME, locationData.getLocationName());


		long result = db.insert(TABLE_LOCATION, null, contentValues);
		db.close();

		//Notifying if transaction was successful 
		if(result == -1)return false;
		else return true;

	}

	public boolean deleteLocationData ()             //DELETE
	{	
		long result = db.delete(TABLE_LOCATION, "1", null);
		db.close();

		//Notifying if transaction was successful 
		if(result > 0)return true;
		else return false;

	}

	//----End--------------------------------LOCATION DATA TRANSACTIONS-----------------------------------------//

	//----Start------------------------------CLIENT DATA TRANSACTIONS-----------------------------------------//


	public boolean addClientData (ClientData clientData)   //ADD
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_CLIENT_NAME, clientData.getClientName());


		long result = db.insert(TABLE_CLIENT, null, contentValues);
		db.close();

		//Notifying if transaction was successful 
		if(result == -1)return false;
		else return true;

	}

	public boolean deleteClientData ()             //DELETE
	{	
		long result = db.delete(TABLE_CLIENT, "1", null);
		db.close();

		//Notifying if transaction was successful 
		if(result > 0)return true;
		else return false;

	}

	//----End--------------------------------CLIENT DATA TRANSACTIONS-----------------------------------------//


	//----Start-----------------------------TIMESHEET DATA TRANSACTIONS-----------------------------------------//


	public void addData(TimeSheetData dataList) {
		SQLiteDatabase db = this.getWritableDatabase();
		Log.v("databse calll", dataList + "");

		ContentValues values = new ContentValues();
		// values.put(COLUMN_SERIAL_NUMBER, dataList.getmSerialNumber());
		values.put(COLUMN_DATE, dataList.getmDate());
		values.put(COLUMN_DAY, dataList.getmDay());
		values.put(COLUMN_TIME_IN, dataList.getmTimeIn());

		// Inserting Row
		db.insert(TABLE_TIMESHEET, null, values);
		db.close(); // Closing database connection
	}

	public void updateData(TimeSheetData dataList,String date){
		SQLiteDatabase db = this.getWritableDatabase();
		Log.v("databse calll", dataList + "");

		ContentValues values = new ContentValues();
		values.put(COLUMN_CLIENT, dataList.getmClientInfo());
		values.put(COLUMN_LOCATION, dataList.getmLocation());
		values.put(COLUMN_WORK_DONE, dataList.getmWorkDone());
		values.put(COLUMN_REMARKS, dataList.getmRemarks());

		db.update(TABLE_TIMESHEET, values, COLUMN_DATE+"=?",new String[]{date});
		db.close(); // Closing database connection	
	}

	public Cursor getAllData() { // getting all data

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_TIMESHEET, new String[] { "DISTINCT "
				+ COLUMN_DATE }, null, null, null, null, null);

		return cursor;
	}
	//----End-------------------------------TIMESHEET DATA TRANSACTIONS-----------------------------------------//


}
