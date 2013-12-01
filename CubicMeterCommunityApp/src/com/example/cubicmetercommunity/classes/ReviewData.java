package com.example.cubicmetercommunity.classes;

import java.io.Serializable;

import org.json.JSONObject;

public class ReviewData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4792774731561162284L;
	String ID, groupID, sessionID;
	
	public ReviewData(String ID, String groupID, String sessionID){
		this.ID = ID;
		this.groupID = groupID;
		this.sessionID = sessionID;		
	}
	public ReviewData(JSONObject obj){
		
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	@Override
	public String toString() {
		return ID;
	}	
}
