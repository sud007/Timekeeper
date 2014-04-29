package com.cleanapps.timekeeper.model;

public class MainListData {
	public MainListData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MainListData(String mDate, String mDay, String mTimeIn,
			String mTimeOut, String mHours, String mClientInfo,
			String mLocation, String mWorkDone, String mRemarks) {
		super();
		this.mDate = mDate;
		this.mDay = mDay;
		this.mTimeIn = mTimeIn;
		this.mTimeOut = mTimeOut;
		this.mHours = mHours;
		this.mClientInfo = mClientInfo;
		this.mLocation = mLocation;
		this.mWorkDone = mWorkDone;
		this.mRemarks = mRemarks;
	}
	@Override
	public String toString() {
		return "MainListData [mDate=" + mDate + ", mDay=" + mDay + ", mTimeIn="
				+ mTimeIn + ", mTimeOut=" + mTimeOut + ", mHours=" + mHours
				+ ", mClientInfo=" + mClientInfo + ", mLocation=" + mLocation
				+ ", mWorkDone=" + mWorkDone + ", mRemarks=" + mRemarks + "]";
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getmDay() {
		return mDay;
	}
	public void setmDay(String mDay) {
		this.mDay = mDay;
	}
	public String getmTimeIn() {
		return mTimeIn;
	}
	public void setmTimeIn(String mTimeIn) {
		this.mTimeIn = mTimeIn;
	}
	public String getmTimeOut() {
		return mTimeOut;
	}
	public void setmTimeOut(String mTimeOut) {
		this.mTimeOut = mTimeOut;
	}
	public String getmHours() {
		return mHours;
	}
	public void setmHours(String mHours) {
		this.mHours = mHours;
	}
	public String getmClientInfo() {
		return mClientInfo;
	}
	public void setmClientInfo(String mClientInfo) {
		this.mClientInfo = mClientInfo;
	}
	public String getmLocation() {
		return mLocation;
	}
	public void setmLocation(String mLocation) {
		this.mLocation = mLocation;
	}
	public String getmWorkDone() {
		return mWorkDone;
	}
	public void setmWorkDone(String mWorkDone) {
		this.mWorkDone = mWorkDone;
	}
	public String getmRemarks() {
		return mRemarks;
	}
	public void setmRemarks(String mRemarks) {
		this.mRemarks = mRemarks;
	}
	private String mDate;
	private String mDay;
	private String mTimeIn;
	private String mTimeOut;
	private String mHours;
	private String mClientInfo;
	private String mLocation;
	private String mWorkDone;
	private String mRemarks;

		}
