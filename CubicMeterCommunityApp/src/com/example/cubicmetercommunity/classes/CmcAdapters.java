package com.example.cubicmetercommunity.classes;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cubicmetercommunity.dbutil.DatabaseManager;
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

	public static class SScientistAdapter extends ArrayAdapter<SoilScientist> {

		Context context;
		List<SoilScientist> list;		

		public SScientistAdapter(Context context, List<SoilScientist> list) {
			super(context, R.layout.reviewdata_meteorologist_layout, list);
			this.context = context;
			this.list = list;				
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.reviewdata_soilscientist_sayout,
						parent, false);
			}		

			TextView txt1 = (TextView) convertView.findViewById(R.id.rd_ss1);  
			TextView txt2 = (TextView) convertView.findViewById(R.id.rd_ss2);
			TextView txt3 = (TextView) convertView.findViewById(R.id.rd_ss3);
			TextView txt4 = (TextView) convertView.findViewById(R.id.rd_ss4);
			TextView txt5 = (TextView) convertView.findViewById(R.id.rd_ss5);
			
				txt1.setText((list.get(position)).soil_color);
				txt2.setText((list.get(position)).soil_moisture);
				txt3.setText((list.get(position)).soil_odor);
				txt4.setText((list.get(position)).soil_ph);
				txt5.setText((list.get(position)).soil_type);						
			
			return convertView;
		}

		public List<SoilScientist> getList() {
			return list;
		}
	}
	
	public static class NaturalistAdapter extends ArrayAdapter<Naturalist> {

		Context context;
		List<Naturalist> list;		

		public NaturalistAdapter(Context context, List<Naturalist> list) {
			super(context, R.layout.reviewdata_naturalist_layout, list);
			this.context = context;
			this.list = list;				
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.reviewdata_naturalist_layout,
						parent, false);
			}		

			TextView txt1 = (TextView) convertView.findViewById(R.id.rd_n1);  
			TextView txt2 = (TextView) convertView.findViewById(R.id.rd_n2);
			TextView txt3 = (TextView) convertView.findViewById(R.id.rd_n3);
			TextView txt4 = (TextView) convertView.findViewById(R.id.rd_n4);
			TextView txt5 = (TextView) convertView.findViewById(R.id.rd_n5);
			
				txt1.setText((list.get(position)).comments);
				txt2.setText((list.get(position)).comments);
				txt3.setText((list.get(position)).comments);
				//txt4.setText((list.get(position)).humidity);
				//txt5.setText((list.get(position)).cloud);						
			
			return convertView;
		}

		public List<Naturalist> getList() {
			return list;
		}
	}

	public static class ExpandableViewAdapter extends BaseExpandableListAdapter{
		
		Context context;
		List<String> list;	
		LayoutInflater inflater;
		List<Meteorologist> mlist;
		List<Naturalist> nlist;
		List<SoilScientist> sslist;
		int size = 0;
		
		public ExpandableViewAdapter(Context context, List<String> list) {		
			this.context = context;
			this.list = list;			
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			mlist = DatabaseManager.getCollectedDataByRole(DatabaseManager.METEOROLOGIST_TABLE);
			nlist = DatabaseManager.getCollectedDataByRole(DatabaseManager.NATURALIST_TABLE);
			sslist = DatabaseManager.getCollectedDataByRole(DatabaseManager.SOIL_SCIENTIST_TABLE);
		}

		@Override
		public Object getChild(int groupPos, int childPos) {
			//String [] child = new String[]{"sub 1", "sub 2", "sub 3", "sub 4"};
			
			if(list.get(groupPos).equals(Role.METEOROLOGIST)){
				return mlist;
			}
			if(list.get(groupPos).equals(Role.NATURALIST)){				
				return nlist;
			}
			if(list.get(groupPos).equals(Role.SOIL_SCIENTIST)){
				return sslist;
			}
			
			
			return null; 
		}

		@Override
		public long getChildId(int arg0, int arg1) {
			return 0;
		}
		
		@Override
		public int getChildrenCount(int groupPos) {			
			if(list.get(groupPos).equals(Role.METEOROLOGIST)){
				return mlist.size();
			}
			if(list.get(groupPos).equals(Role.NATURALIST)){				
				return nlist.size();
			}
			if(list.get(groupPos).equals(Role.SOIL_SCIENTIST)){
				return sslist.size();
			}
			return 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public View getChildView(int groupPos, int childPos, boolean isLastChild, View convertView,
				ViewGroup parent) {
			
		final String value = "vaaa"; // (String) getChild(groupPos, childPos);
		List<Meteorologist> _mlist = null;
		List<Naturalist> _nlist = null;
		List<SoilScientist> _sslist = null;
		
			if (convertView == null) {
			      convertView = inflater.inflate(R.layout.review_row_details, null);
			    }
			
			if(list.get(groupPos).equals(Role.METEOROLOGIST))
				 _mlist = (List<Meteorologist>)getChild(groupPos, childPos);
			((TextView)convertView.findViewById(R.id.rd_n1)).setText(_mlist.get(childPos).canopy_cover);
			((TextView)convertView.findViewById(R.id.rd_n2)).setText(_mlist.get(childPos).celsius);
			((TextView)convertView.findViewById(R.id.rd_n3)).setText(_mlist.get(childPos).comments);
			
			if(list.get(groupPos).equals(Role.NATURALIST)){
				_nlist = (List<Naturalist>)getChild(groupPos, childPos);
				((TextView)convertView.findViewById(R.id.rd_n1)).setText(_nlist.get(childPos).ant);
				((TextView)convertView.findViewById(R.id.rd_n2)).setText(_nlist.get(childPos).bee);
				((TextView)convertView.findViewById(R.id.rd_n3)).setText(_nlist.get(childPos).comments);
			}
			if(list.get(groupPos).equals(Role.SOIL_SCIENTIST)){
				_sslist = (List<SoilScientist>)getChild(groupPos, childPos);
				((TextView)convertView.findViewById(R.id.rd_n1)).setText(_sslist.get(childPos).soil_color);
				((TextView)convertView.findViewById(R.id.rd_n2)).setText(_sslist.get(childPos).soil_consistency);
				((TextView)convertView.findViewById(R.id.rd_n3)).setText(_sslist.get(childPos).comments);
			}
			
			
			
			convertView.setOnClickListener(new OnClickListener() {
			      @Override
			      public void onClick(View v) {
			        Toast.makeText(context, value,
			            Toast.LENGTH_SHORT).show();
			      }
			    });
			return convertView;
		}

		
		@Override
		public Object getGroup(int groupPos) {
			return list.get(groupPos);
		}

		@Override
		public int getGroupCount() {
			return list.size();
		}

		@Override
		public long getGroupId(int arg0) {
			return 0;
		}

		@Override
		public View getGroupView(int groupPos, boolean isExpanded, View convertView,
				ViewGroup parent) {
			
			if (convertView == null) {
			      convertView = inflater.inflate(R.layout.review_row_group, null);
			    }

			String group = (String) getGroup(groupPos);
		    ((CheckedTextView) convertView).setText(group.toString());
		    ((CheckedTextView) convertView).setChecked(isExpanded);
			
			return convertView;
		}

		  @Override
		  public void onGroupCollapsed(int groupPos) {
		    super.onGroupCollapsed(groupPos);
		  }
		
		@Override
		public void onGroupExpanded(int groupPos) {
			super.onGroupExpanded(groupPos);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			return true;
		}
		
		
	}


}
