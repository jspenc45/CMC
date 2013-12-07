package com.example.cubicmetercommunity.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Session implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String sqlID = "Id";
	public static final String sqlGROUP_ID = "group_id__c";
	public static final String sqlTIME = "session_time__c";
	String id, groupId, sessionTime;
	
	public Session(String id, String groupId, String sessionTime) {
		this.id = id;
		this.groupId = groupId;
		this.sessionTime = sessionTime;
	}
	public String getSessionTime() {
		return sessionTime;
	}
	public void setSessionTime(String sessionTime) {
		this.sessionTime = sessionTime;
	}
	public Session(JSONObject obj) {
		try {
			id = obj.getString(Session.sqlID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			groupId = obj.getString(Session.sqlGROUP_ID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			sessionTime = obj.getString(Session.sqlTIME);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public static List<Session> getSessions(JSONObject obj) {
		JSONArray records = null;
		try {
			records = obj.getJSONArray("records");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<Session> sessions = new ArrayList<Session>();
		for (int i = 0; i < records.length(); i++) {
			Session session = null;
			try {
				session = new Session(records.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			sessions.add(session);
		}
		return sessions;

	}
	@Override
	public String toString() {
		return sessionTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
