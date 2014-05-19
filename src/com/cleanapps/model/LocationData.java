package com.cleanapps.model;

public class LocationData {

	private String locationName;

	@Override
	public String toString() {
		return "LocationData [locationName=" + locationName + "]";
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public LocationData() {
		super();

	}


}
