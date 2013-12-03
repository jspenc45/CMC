package com.example.cubicmetercommunity.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SoilScientist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static String GROUP_ID_FIELD = "group_id_c";
	private final static String COMMENTS_FIELD = "comments_c";
	private final static String SESSION_ID_FIELD = "session_id__c"; 
	private final static String SOIL_COLOR_FIELD = "soil_color_c";
	private final static String SOIL_CONSISTENCY_FIELD = "soil_consistency_c";
	private final static String SOIL_MOISTURE_FIELD = "soil_moisture_c";
	private final static String SOIL_ODOR_FIELD = "soil_odor_c";
	private final static String SOIL_PH_FIELD = "soil_ph_c";
	private final static String SOIL_TEXTURE_FIELD = "soil_texture_c";
	private final static String SOIL_TYPE_FIELD = "soil_type_c";
	
	String comments ,group_id,session_id,soil_color, soil_consistency,soil_moisture,soil_odor,soil_ph,
	soil_texture,soil_type;	
	
	public static Map<String,Object> generateFieldsAll() {
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put(SOIL_COLOR_FIELD, null);
		fields.put(COMMENTS_FIELD, null);
		fields.put(SOIL_CONSISTENCY_FIELD, null);
		fields.put(GROUP_ID_FIELD, null);
		fields.put(SOIL_MOISTURE_FIELD, null);
		fields.put(SOIL_ODOR_FIELD, null);
		fields.put(SOIL_PH_FIELD, null);
		fields.put(SESSION_ID_FIELD, null);
		fields.put(SOIL_TEXTURE_FIELD, null);
		fields.put(SOIL_TYPE_FIELD, null);
		
		return fields;
	}
	public SoilScientist(String soil_color, String soil_consistency, String soil_moisture, String soil_odor , String soil_ph){
		this.soil_color = soil_color;
		this.soil_consistency = soil_consistency;
		this.soil_moisture = soil_moisture;
		this.soil_odor = soil_odor;
		this.soil_ph = soil_ph;
	}
	
	public SoilScientist(JSONObject json){
		try {
			this.soil_color = json.getString(SOIL_COLOR_FIELD);
		} catch (JSONException e) {
			this.soil_color = "";
		}
		try {
			this.soil_consistency = json.getString(SOIL_CONSISTENCY_FIELD);
		} catch (JSONException e) {
			this.soil_consistency = "";
		}
		try {
			this.comments = json.getString(COMMENTS_FIELD);
		} catch (JSONException e) {
			this.comments = "";
		}
		try {
			this.soil_moisture = json.getString(SOIL_MOISTURE_FIELD);
		} catch (JSONException e) {
			this.soil_moisture = "";
		}
		try {
			this.group_id = json.getString(GROUP_ID_FIELD);
		} catch (JSONException e) {
			this.group_id = "";
		}
		try {
			this.soil_odor = json.getString(SOIL_ODOR_FIELD);
		} catch (JSONException e) {
			this.soil_odor = "";
		}
		try {
			this.soil_ph = json.getString(SOIL_PH_FIELD);
		} catch (JSONException e) {
			this.soil_ph = "";
		}
		try {
			this.soil_texture = json.getString(SOIL_TEXTURE_FIELD);
		} catch (JSONException e) {
			this.soil_texture = "";
		}
		try {
			this.session_id = json.getString(SESSION_ID_FIELD);
		} catch (JSONException e) {
			this.session_id = "";
		}
		try {
			this.soil_type = json.getString(SOIL_TYPE_FIELD);
		} catch (JSONException e) {
			this.soil_type = "";
		}		
	}
	
	public static List<SoilScientist> makeList(JSONObject obj){
		JSONArray records = null;
		try {
			records = obj.getJSONArray("records");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<SoilScientist> list = new ArrayList<SoilScientist>();
		for (int i = 0; i < records.length(); i++) {
			SoilScientist met = null;
			try {
				met = new SoilScientist(records.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			list.add(met);
		}
		return list;		
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getSoil_color() {
		return soil_color;
	}
	public void setSoil_color(String soil_color) {
		this.soil_color = soil_color;
	}
	public String getSoil_consistency() {
		return soil_consistency;
	}
	public void setSoil_consistency(String soil_consistency) {
		this.soil_consistency = soil_consistency;
	}
	public String getSoil_moisture() {
		return soil_moisture;
	}
	public void setSoil_moisture(String soil_moisture) {
		this.soil_moisture = soil_moisture;
	}
	public String getSoil_odor() {
		return soil_odor;
	}
	public void setSoil_odor(String soil_odor) {
		this.soil_odor = soil_odor;
	}
	public String getSoil_ph() {
		return soil_ph;
	}
	public void setSoil_ph(String soil_ph) {
		this.soil_ph = soil_ph;
	}
	public String getSoil_texture() {
		return soil_texture;
	}
	public void setSoil_texture(String soil_texture) {
		this.soil_texture = soil_texture;
	}
	public String getSoil_type() {
		return soil_type;
	}
	public void setSoil_type(String soil_type) {
		this.soil_type = soil_type;
	}
	@Override
	public String toString() {
		return "SoilScientist [comments=" + comments + ", group_id=" + group_id
				+ ", session_id=" + session_id + ", soil_color=" + soil_color
				+ ", soil_consistency=" + soil_consistency + ", soil_moisture="
				+ soil_moisture + ", soil_odor=" + soil_odor + ", soil_ph="
				+ soil_ph + ", soil_texture=" + soil_texture + ", soil_type="
				+ soil_type + "]";
	}
	
}
