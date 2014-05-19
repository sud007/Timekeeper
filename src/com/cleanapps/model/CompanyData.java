package com.cleanapps.model;

public class CompanyData {
	private String companyName;

	public CompanyData()
	{
		super();
	}
	@Override
	public String toString() {
		return "CompanyData [companyName=" + companyName + "]";
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



}
