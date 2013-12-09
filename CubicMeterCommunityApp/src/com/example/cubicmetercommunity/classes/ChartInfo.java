package com.example.cubicmetercommunity.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.SortedMap;

public class ChartInfo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	SortedMap<String, Double> Data;
	String Title;
	public ChartInfo(String title, SortedMap<String, Double> data){
		
		this.Title = title;
		this.Data = data;
	}
	public SortedMap<String, Double> getData() {
		return Data;
	}
	public void setData(SortedMap<String, Double> data) {
		Data = data;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}

}
