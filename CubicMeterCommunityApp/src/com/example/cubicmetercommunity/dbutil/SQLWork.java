package com.example.cubicmetercommunity.dbutil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.Session;
import com.example.cubicmetercommunity.classes.TableIDs;

public class SQLWork {

	public static Group createNewGroup(String groupName){
		DBUtil db = new DBUtil();
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put(Group.sqlNAME, groupName);
		JSONObject resp = db.create("Group__c", fields);
		Group group = null;
		try {
			group = new Group(groupName,resp.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return group;
	}
	public static List<Session> getSessions(Group group) {
		DBUtil db = new DBUtil();
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(Session.sqlID, null);
		fields.put(Session.sqlGROUP_ID, null);
		fields.put(Session.sqlTIME, null);
		String where = Session.sqlGROUP_ID + "=" + "\'" + group.getId() + "\'";
		JSONObject resp = db.select("Session__c", fields, where);
		
		return Session.getSessions(resp);
	}
	public static List<Group> getGroups(){
		DBUtil db = new DBUtil();
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(Group.sqlNAME, null);
		fields.put(Group.sqlID, null);
		JSONObject response = db.select("Group__c", fields, null);

		return Group.getGroups(response);
	}
	public static Session createNewSession(Group group){
		DBUtil db = new DBUtil();
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(Session.sqlGROUP_ID, group.getId());
		String date = new Date().toString();
		fields.put(Session.sqlTIME, date);
		JSONObject response = db.create("Session__c", fields);
		Session session = null;
		try {
			session = new Session(response.getString("id"), group.getId(), date);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return session;
	}
	
	public static TableIDs createNewRoles(Group group,Session session) {
		DBUtil db = new DBUtil();
		TableIDs ids = new TableIDs();
		Map<String, Object> fields = new HashMap<String, Object>();
		
		fields.put(TableIDs.sqlSESSION_ID, session.getId());
		fields.put(TableIDs.sqlGROUP_ID, group.getId());
		
		//Set up Meteorologist Table
		JSONObject response = db.create("Meteorologist__c", fields);
		try {
			ids.setMeteorologistID(response.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Set up Naturalist Table
		response = db.create("Naturalist__c", fields);
		try {
			ids.setNaturalistID(response.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Set up Soil Scientist Table
		response = db.create("SoilScientist__c", fields);
		try {
			ids.setSoilScientistID(response.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ids;
	}
}
