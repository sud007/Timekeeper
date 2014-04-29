package com.cleanapps.timekeeper.dbhelper;

import com.cleanapps.timekeeper.main.MainActivity;
import com.cleanapps.timekeeper.model.MainListData;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LoginDBHelper extends SQLiteOpenHelper {

	public static final int DATABSE_VERSION = 1;
	public static final String DATABASE_NAME = "loginDatabase";

	/*
	 * Table
	 */

	public static final String TABLE_DATA = "dataTable";

	/**
	 * Column Names
	 * 
	 * @param context
	 */
	private static final String COLUMN_USER_NAME = "Employee_Name";
	public static final String COLUMN_SERIAL_NUMBER = "SNo";
	public static final String COLUMN_DATE = "Date";
	public static final String COLUMN_DAY = "Day";
	public static final String COLUMN_TIME_IN = "Time_In";
	public static final String COLUMN_TIME_OUT = "Time_Out";
	public static final String COLUMN_TOTAL_HOURS = "Hours";
	public static final String COLUMN_CLIENT = "Client";
	public static final String COLUMN_lOCATION = "Map_Location";
	public static final String COLUMN_WORK_DONE = "Work_Done";
	public static final String COLUMN_REMARKS = "Remarks";

	private static LoginDBHelper helper;

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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		String CREATE_SCHEDULE_TABLE = "CREATE TABLE " + TABLE_DATA + "("
				+ COLUMN_DATE + " TEXT PRIMARY KEY," + COLUMN_DAY + " TEXT,"
				+ COLUMN_TIME_IN + " TEXT," + COLUMN_TIME_OUT + " TEXT,"
				+ COLUMN_TOTAL_HOURS + " TEXT," + COLUMN_CLIENT + " TEXT,"
				+ COLUMN_lOCATION + " TEXT," + COLUMN_WORK_DONE + " TEXT,"
				+ COLUMN_REMARKS + " TEXT" + ")";

		db.execSQL(CREATE_SCHEDULE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void addData(MainListData dataList) {
		SQLiteDatabase db = this.getWritableDatabase();
		Log.v("databse calll", dataList + "");

		ContentValues values = new ContentValues();
		// values.put(COLUMN_SERIAL_NUMBER, dataList.getmSerialNumber());
		values.put(COLUMN_DATE, dataList.getmDate());
		values.put(COLUMN_DAY, dataList.getmDay());
		values.put(COLUMN_TIME_IN, dataList.getmTimeIn());

		// Inserting Row
		db.insert(TABLE_DATA, null, values);
		db.close(); // Closing database connection
	}

	public void updateData(MainListData dataList,String date){
		SQLiteDatabase db = this.getWritableDatabase();
		Log.v("databse calll", dataList + "");

		ContentValues values = new ContentValues();
		values.put(COLUMN_CLIENT, dataList.getmClientInfo());
		values.put(COLUMN_lOCATION, dataList.getmLocation());
		values.put(COLUMN_WORK_DONE, dataList.getmWorkDone());
		values.put(COLUMN_REMARKS, dataList.getmRemarks());

//		db.update(TABLE_DATA, values, COLUMN_CLIENT+"=? and"+COLUMN_lOCATION+"=? and"+COLUMN_WORK_DONE+"=? and"+COLUMN_REMARKS+"=?",
//				new String [] { dataList.getmClientInfo().toString(),dataList.getmLocation().toString(),dataList.getmWorkDone().toString(),dataList.getmRemarks().toString() });
		
		db.update(TABLE_DATA, values, COLUMN_DATE+"=?",new String[]{date});
		db.close(); // Closing database connection	
	}

	public Cursor getAllData() { // getting all data

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_DATA, new String[] { "DISTINCT "
				+ COLUMN_DATE }, null, null, null, null, null);

		return cursor;
	}
	
	

}
