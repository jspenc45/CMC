package com.example.cubicmetercommunity.classes;

import java.io.Serializable;

public class TableIDs implements Serializable {

	
	private static final long serialVersionUID = 1L;
	public static final String sqlSESSION_ID = "session_id__c";
	public static final String sqlGROUP_ID = "group_id__c";
	public static final String sqlTABLE_ID = "Id";
	public String groupId;
	public String sessionId;
	
	public String naturalistID, meteorologistID, soilScientistID;

	public String getNaturalistID() {
		return naturalistID;
	}

	public void setNaturalistID(String naturalistID) {
		this.naturalistID = naturalistID;
	}

	public String getSoilScientistID() {
		return soilScientistID;
	}

	public void setSoilScientistID(String soilScientistID) {
		this.soilScientistID = soilScientistID;
	}

	public String getMeteorologistID() {
		return meteorologistID;
	}

	public void setMeteorologistID(String meteorologistID) {
		this.meteorologistID = meteorologistID;
	}
}
