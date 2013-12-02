package com.example.cubicmetercommunity.classes;

import java.io.Serializable;
import java.util.HashMap;

public class ChartInfo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HashMap<String, Double> Data;
	String Title;
	public ChartInfo(String title, HashMap<String, Double> data){
		
		this.Title = title;
		this.Data = data;
	}
	public HashMap<String, Double> getData() {
		return Data;
	}
	public void setData(HashMap<String, Double> data) {
		Data = data;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

}
