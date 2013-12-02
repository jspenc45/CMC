package com.example.cubicmetercommunity.classes;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cubicmetercommunityapp.R;

public class CmcAdapters {
	
	public static class MeteoAdapter extends ArrayAdapter<Meteorologist> {

		Context context;
		List<Meteorologist> list;		

		public MeteoAdapter(Context context, List<Meteorologist> list) {
			super(context, R.layout.reviewdata_meteorologist_layout, list);
			this.context = context;
			this.list = list;				
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.reviewdata_meteorologist_layout,
						parent, false);
			}		

			TextView txt1 = (TextView) convertView.findViewById(R.id.rd_meteo1);  
			TextView txt2 = (TextView) convertView.findViewById(R.id.rd_meteo2);
			TextView txt3 = (TextView) convertView.findViewById(R.id.rd_meteo3);
			TextView txt4 = (TextView) convertView.findViewById(R.id.rd_meteo4);
			TextView txt5 = (TextView) convertView.findViewById(R.id.rd_meteo5);
			
				txt1.setText(((Meteorologist)list.get(position)).canopy_cover);
				txt2.setText(((Meteorologist)list.get(position)).celsius);
				txt3.setText(((Meteorologist)list.get(position)).fahrenheit);
				txt4.setText(((Meteorologist)list.get(position)).humidity);
				txt5.setText(((Meteorologist)list.get(position)).cloud);						
			
			return convertView;
		}

		public List<Meteorologist> getList() {
			return list;
		}
	}

	public static class SScientistAdapter extends ArrayAdapter<Meteorologist> {

		Context context;
		List<Meteorologist> list;		

		public SScientistAdapter(Context context, List<Meteorologist> list) {
			super(context, R.layout.reviewdata_meteorologist_layout, list);
			this.context = context;
			this.list = list;				
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.reviewdata_meteorologist_layout,
						parent, false);
			}		

			TextView txt1 = (TextView) convertView.findViewById(R.id.rd_ss1);  
			TextView txt2 = (TextView) convertView.findViewById(R.id.rd_ss2);
			TextView txt3 = (TextView) convertView.findViewById(R.id.rd_ss3);
			TextView txt4 = (TextView) convertView.findViewById(R.id.rd_ss4);
			TextView txt5 = (TextView) convertView.findViewById(R.id.rd_ss5);
			
				txt1.setText(((Meteorologist)list.get(position)).canopy_cover);
				txt2.setText(((Meteorologist)list.get(position)).celsius);
				txt3.setText(((Meteorologist)list.get(position)).fahrenheit);
				txt4.setText(((Meteorologist)list.get(position)).humidity);
				txt5.setText(((Meteorologist)list.get(position)).cloud);						
			
			return convertView;
		}

		public List<Meteorologist> getList() {
			return list;
		}
	}
	
	public static class NaturalistAdapter extends ArrayAdapter<Meteorologist> {

		Context context;
		List<Meteorologist> list;		

		public NaturalistAdapter(Context context, List<Meteorologist> list) {
			super(context, R.layout.reviewdata_meteorologist_layout, list);
			this.context = context;
			this.list = list;				
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.reviewdata_meteorologist_layout,
						parent, false);
			}		

			TextView txt1 = (TextView) convertView.findViewById(R.id.rd_n1);  
			TextView txt2 = (TextView) convertView.findViewById(R.id.rd_n2);
			TextView txt3 = (TextView) convertView.findViewById(R.id.rd_n3);
			TextView txt4 = (TextView) convertView.findViewById(R.id.rd_n4);
			TextView txt5 = (TextView) convertView.findViewById(R.id.rd_n5);
			
				txt1.setText(((Meteorologist)list.get(position)).canopy_cover);
				txt2.setText(((Meteorologist)list.get(position)).celsius);
				txt3.setText(((Meteorologist)list.get(position)).fahrenheit);
				txt4.setText(((Meteorologist)list.get(position)).humidity);
				txt5.setText(((Meteorologist)list.get(position)).cloud);						
			
			return convertView;
		}

		public List<Meteorologist> getList() {
			return list;
		}
	}

}
