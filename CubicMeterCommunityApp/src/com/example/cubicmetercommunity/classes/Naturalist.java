package com.example.cubicmetercommunity.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Naturalist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String GROUP_ID_FIELD = "group_id_c";
	private final static String COMMENTS_FIELD = "comments_c";
	private final static String SESSION_ID_FIELD = "session_id__c"; 
	
	String comments ,group_id,session_id;
	
	public static Map<String,Object> generateFieldsAll() {
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put(COMMENTS_FIELD, null);
		fields.put(GROUP_ID_FIELD, null);
		fields.put(SESSION_ID_FIELD, null);
		
		return fields;
	}
	public Naturalist(String comment){
		this.comments = comment;		
	}
	
	public Naturalist(JSONObject json){		
		try {
			this.comments = json.getString(COMMENTS_FIELD);
		} catch (JSONException e) {
			this.comments = "";
		}
	}	
	
}
