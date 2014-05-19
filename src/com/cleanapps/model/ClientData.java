package com.cleanapps.model;

public class ClientData {
	private String ClientName;

	public String getClientName() {
		return ClientName;
	}

	public void setClientName(String clientName) {
		ClientName = clientName;
	}

	@Override
	public String toString() {
		return "ClientData [ClientName=" + ClientName + "]";
	}

	public ClientData() {
		super();
	}


}
