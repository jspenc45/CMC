package com.example.cubicmetercommunity.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Naturalist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String GROUP_ID_FIELD = "group_id__c";
	private final static String COMMENTS_FIELD = "comments__c";
	private final static String SESSION_ID_FIELD = "session_id__c"; 
	private final static String ANTS_FIELD = "ant__c"; 
	
	String comments ,group_id,session_id, ants;
	
	public static Map<String,Object> generateFieldsAll() {
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put(COMMENTS_FIELD, null);
		fields.put(GROUP_ID_FIELD, null);
		fields.put(ANTS_FIELD, null);
		fields.put(SESSION_ID_FIELD, null);
		
		return fields;
	}
	public Naturalist(String comment){
		this.comments = comment;		
	}
	
	public Naturalist(JSONObject json){		
		try {
			this.ants = json.getString(ANTS_FIELD);
		} catch (JSONException e) {
			this.ants = "";
		}
		try {
			this.comments = json.getString(COMMENTS_FIELD);
		} catch (JSONException e) {
			this.comments = "";
		}
	}	
	
	public String getAnts() {
		return ants;
	}
	public void setAnts(String ants) {
		this.ants = ants;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public static List<Naturalist> makeList(JSONObject obj){
		JSONArray records = null;
		try {
			records = obj.getJSONArray("records");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<Naturalist> list = new ArrayList<Naturalist>();
		for (int i = 0; i < records.length(); i++) {
			Naturalist met = null;
			try {
				met = new Naturalist(records.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			list.add(met);
		}
		return list;
		
	}
}
