package com.example.cubicmetercommunity.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String sqlNAME = "group_name__c";
	public static final String sqlID = "Id";

	String name, id;

	public Group(String name, String id) {
		this.name = name;
		this.id = id;
	}
	

	public Group(JSONObject obj) {
		try {
			name = obj.getString(Group.sqlNAME);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			id = obj.getString(Group.sqlID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static List<Group> getGroups(JSONObject obj) {
		JSONArray records = null;
		try {
			records = obj.getJSONArray("records");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<Group> groups = new ArrayList<Group>();
		for (int i = 0; i < records.length(); i++) {
			Group group = null;
			try {
				group = new Group(records.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			groups.add(group);
		}
		return groups;

	}
}
