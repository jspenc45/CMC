package com.example.cubicmetercommunity.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Meteorologist {
	private final static String CELSIUS_FIELD = "celsius__c";
	private final static String CLOUD_FIELD = "cloud__c";
	private final static String COMMENTS_FIELD = "comments__c";
	private final static String FAHRENHEIT_FIELD = "fahrenheit__c";
	private final static String GROUP_ID_FIELD = "group_id__c";
	private final static String HUMIDITY_FIELD = "humidity__c";
	private final static String PRESSURE_FIELD = "pressure__c";
	private final static String RAINFALL_FIELD = "rainfall__c";
	private final static String CANOPY_COVER_FIELD = "canopy_cover__c";
	private final static String SESSION_ID_FIELD = "session_id__c";
	private final static String WIND_FIELD = "wind__c";
	
	String celsius, canopy_cover, cloud, comments, fahrenheit, group_id,humidity, pressure,rainfall,session_id,wind;
	
	public static Map<String,Object> generateFieldsAll() {
		Map<String,Object> fields = new HashMap<String,Object>();
		fields.put(CELSIUS_FIELD, null);
		fields.put(CLOUD_FIELD, null);
		fields.put(COMMENTS_FIELD, null);
		fields.put(FAHRENHEIT_FIELD, null);
		fields.put(GROUP_ID_FIELD, null);
		fields.put(HUMIDITY_FIELD, null);
		fields.put(PRESSURE_FIELD, null);
		fields.put(RAINFALL_FIELD, null);
		fields.put(SESSION_ID_FIELD, null);
		fields.put(WIND_FIELD, null);
		fields.put(CANOPY_COVER_FIELD, null);
		
		return fields;
	}
	public Meteorologist(JSONObject json){
		try {
			this.celsius = json.getString(CELSIUS_FIELD);
		} catch (JSONException e) {
			this.celsius = "";
		}
		try {
			this.cloud = json.getString(CLOUD_FIELD);
		} catch (JSONException e) {
			this.cloud = "";
		}
		try {
			this.comments = json.getString(COMMENTS_FIELD);
		} catch (JSONException e) {
			this.comments = "";
		}
		try {
			this.fahrenheit = json.getString(FAHRENHEIT_FIELD);
		} catch (JSONException e) {
			this.fahrenheit = "";
		}
		try {
			this.group_id = json.getString(GROUP_ID_FIELD);
		} catch (JSONException e) {
			this.group_id = "";
		}
		try {
			this.humidity = json.getString(HUMIDITY_FIELD);
		} catch (JSONException e) {
			this.humidity = "";
		}
		try {
			this.pressure = json.getString(PRESSURE_FIELD);
		} catch (JSONException e) {
			this.pressure = "";
		}
		try {
			this.rainfall = json.getString(RAINFALL_FIELD);
		} catch (JSONException e) {
			this.rainfall = "";
		}
		try {
			this.session_id = json.getString(SESSION_ID_FIELD);
		} catch (JSONException e) {
			this.session_id = "";
		}
		try {
			this.wind = json.getString(WIND_FIELD);
		} catch (JSONException e) {
			this.wind = "";
		}
		try {
			this.canopy_cover = json.getString(CANOPY_COVER_FIELD);
		} catch (JSONException e) {
			this.canopy_cover = "";
		}
	}
	public static List<Meteorologist> makeList(JSONObject obj){
		JSONArray records = null;
		try {
			records = obj.getJSONArray("records");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<Meteorologist> list = new ArrayList<Meteorologist>();
		for (int i = 0; i < records.length(); i++) {
			Meteorologist met = null;
			try {
				met = new Meteorologist(records.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			list.add(met);
		}
		return list;
		
	}
	public String getCelsius() {
		return celsius;
	}
	public void setCelsius(String celsius) {
		this.celsius = celsius;
	}
	public String getCanopy_cover() {
		return canopy_cover;
	}
	public void setCanopy_cover(String canopy_cover) {
		this.canopy_cover = canopy_cover;
	}
	public String getCloud() {
		return cloud;
	}
	public void setCloud(String cloud) {
		this.cloud = cloud;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFahrenheit() {
		return fahrenheit;
	}
	public void setFahrenheit(String fahrenheit) {
		this.fahrenheit = fahrenheit;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getRainfall() {
		return rainfall;
	}
	public void setRainfall(String rainfall) {
		this.rainfall = rainfall;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	@Override
	public String toString() {
		return "Meteorologist [celsius=" + celsius + ", canopy_cover="
				+ canopy_cover + ", cloud=" + cloud + ", comments=" + comments
				+ ", fahrenheit=" + fahrenheit + ", group_id=" + group_id
				+ ", humidity=" + humidity + ", pressure=" + pressure
				+ ", rainfall=" + rainfall + ", session_id=" + session_id
				+ ", wind=" + wind + "]";
	}
	
	
}
