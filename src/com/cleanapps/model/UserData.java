package com.cleanapps.model;

public class UserData {


	private String UserName;
	private int WorkingHours;
	private int WeekendType;



	public UserData() {
		super();
	}

	@Override
	public String toString() {
		return "UserData [UserName=" + UserName + ", WorkingHours="
		+ WorkingHours + ", WeekendType=" + WeekendType + "]";
	}


	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public int getWorkingHours() {
		return WorkingHours;
	}
	public void setWorkingHours(int workingHours) {
		WorkingHours = workingHours;
	}
	public int getWeekendType() {
		return WeekendType;
	}
	public void setWeekendType(int weekendType) {
		WeekendType = weekendType;
	}


}
