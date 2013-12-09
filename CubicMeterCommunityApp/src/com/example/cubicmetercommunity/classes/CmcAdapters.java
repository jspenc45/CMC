package com.example.cubicmetercommunity.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.cubicmetercommunity.app.Review_2Activity;
import com.example.cubicmetercommunity.dbutil.DatabaseManager;
import com.example.cubicmetercommunityapp.R;

public class CmcAdapters {

	public static class MeteoAdapter extends ArrayAdapter<Meteorologist> {

		Context context;
		List<Meteorologist> list;
		String notEntered;
		public MeteoAdapter(Context context, List<Meteorologist> list) {
			super(context, R.layout.reviewdata_meteorologist_layout, list);
			this.context = context;
			this.list = list;
			notEntered = "Not Entered";
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater
						.inflate(R.layout.reviewdata_meteorologist_layout,
								parent, false);
			}
	
			((TextView) convertView.findViewById(R.id.met1)).setText((((Meteorologist) list.get(position)).celsius.equals("null"))? notEntered :((Meteorologist) list.get(position)).celsius);
			((TextView) convertView.findViewById(R.id.met2)).setText((((Meteorologist) list.get(position)).canopy_cover.equals("null"))? notEntered :((Meteorologist) list.get(position)).canopy_cover);
			((TextView) convertView.findViewById(R.id.met3)).setText((((Meteorologist) list.get(position)).cloud.equals("null"))? "Not Entered" : ((Meteorologist) list.get(position)).cloud );
			((TextView) convertView.findViewById(R.id.met4)).setText((((Meteorologist) list.get(position)).fahrenheit.equals("null"))? notEntered : ((Meteorologist) list.get(position)).fahrenheit );
			((TextView) convertView.findViewById(R.id.met5)).setText((((Meteorologist) list.get(position)).humidity.equals("null"))? notEntered : ((Meteorologist) list.get(position)).humidity);
			((TextView) convertView.findViewById(R.id.met6)).setText((((Meteorologist) list.get(position)).pressure.equals("null"))?  notEntered : ((Meteorologist) list.get(position)).pressure );
			((TextView) convertView.findViewById(R.id.met7)).setText((((Meteorologist) list.get(position)).rainfall.equals("null"))?  notEntered : ((Meteorologist) list.get(position)).rainfall);
			((TextView) convertView.findViewById(R.id.met8)).setText((((Meteorologist) list.get(position)).wind.equals("null"))?  notEntered : ((Meteorologist) list.get(position)).wind);
			((TextView) convertView.findViewById(R.id.met9)).setText((((Meteorologist) list.get(position)).comments.equals("null"))? notEntered : ((Meteorologist) list.get(position)).comments);
			
			return convertView;
		}

		public List<Meteorologist> getList() {
			return list;
		}
	}

	public static class SScientistAdapter extends ArrayAdapter<SoilScientist> {

		Context context;
		List<SoilScientist> list;
		String notEntered;

		public SScientistAdapter(Context context, List<SoilScientist> list) {
			super(context, R.layout.reviewdata_meteorologist_layout, list);
			this.context = context;
			this.list = list;
			notEntered = "Not Entered";
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater
						.inflate(R.layout.reviewdata_soilscientist_sayout,
								parent, false);
			}

			((TextView) convertView.findViewById(R.id.ss1)).setText((((SoilScientist) list.get(position)).soil_color.equals("null"))? notEntered :((SoilScientist) list.get(position)).soil_color);
			((TextView) convertView.findViewById(R.id.ss2)).setText((((SoilScientist) list.get(position)).soil_consistency.equals("null"))? notEntered :((SoilScientist) list.get(position)).soil_consistency);
			((TextView) convertView.findViewById(R.id.ss3)).setText((((SoilScientist) list.get(position)).soil_moisture.equals("null"))? "Not Entered" : ((SoilScientist) list.get(position)).soil_moisture );
			((TextView) convertView.findViewById(R.id.ss4)).setText((((SoilScientist) list.get(position)).soil_odor.equals("null"))? notEntered : ((SoilScientist) list.get(position)).soil_odor );
			((TextView) convertView.findViewById(R.id.ss5)).setText((((SoilScientist) list.get(position)).soil_ph.equals("null"))? notEntered : ((SoilScientist) list.get(position)).soil_ph);
			((TextView) convertView.findViewById(R.id.ss6)).setText((((SoilScientist) list.get(position)).soil_texture.equals("null"))?  notEntered : ((SoilScientist) list.get(position)).soil_texture );
			((TextView) convertView.findViewById(R.id.ss7)).setText((((SoilScientist) list.get(position)).soil_type.equals("null"))?  notEntered : ((SoilScientist) list.get(position)).soil_type);
			((TextView) convertView.findViewById(R.id.ss9)).setText((((SoilScientist) list.get(position)).comments.equals("null"))? notEntered : ((SoilScientist) list.get(position)).comments);
			

			return convertView;
		}

		public List<SoilScientist> getList() {
			return list;
		}
	}

	public static class NaturalistAdapter extends ArrayAdapter<Naturalist> {

		Context context;
		List<Naturalist> list;
		String notEntered;

		public NaturalistAdapter(Context context, List<Naturalist> list) {
			super(context, R.layout.reviewdata_naturalist_layout, list);
			this.context = context;
			this.list = list;
			notEntered = "Not Entered";
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(
						R.layout.reviewdata_naturalist_layout, parent, false);
			}

	
			((TextView) convertView.findViewById(R.id.nat1)).setText((((Naturalist) list.get(position)).snail.equals("null"))? notEntered :((Naturalist) list.get(position)).snail);
			((TextView) convertView.findViewById(R.id.nat2)).setText((((Naturalist) list.get(position)).bristletail.equals("null"))? notEntered :((Naturalist) list.get(position)).bristletail);
			((TextView) convertView.findViewById(R.id.nat3)).setText((((Naturalist) list.get(position)).lacewing.equals("null"))? "Not Entered" : ((Naturalist) list.get(position)).lacewing );
			((TextView) convertView.findViewById(R.id.nat4)).setText((((Naturalist) list.get(position)).mayfly.equals("null"))? notEntered : ((Naturalist) list.get(position)).mayfly );
			((TextView) convertView.findViewById(R.id.nat5)).setText((((Naturalist) list.get(position)).thrip.equals("null"))? notEntered : ((Naturalist) list.get(position)).thrip);
			((TextView) convertView.findViewById(R.id.nat6)).setText((((Naturalist) list.get(position)).pillbug.equals("null"))?  notEntered : ((Naturalist) list.get(position)).pillbug );
			((TextView) convertView.findViewById(R.id.nat7)).setText((((Naturalist) list.get(position)).beetle.equals("null"))?  notEntered : ((Naturalist) list.get(position)).beetle);
			((TextView) convertView.findViewById(R.id.nat8)).setText((((Naturalist) list.get(position)).spider.equals("null"))?  notEntered : ((Naturalist) list.get(position)).spider);
			((TextView) convertView.findViewById(R.id.nat9)).setText((((Naturalist) list.get(position)).butterfly.equals("null"))? notEntered : ((Naturalist) list.get(position)).butterfly);
			((TextView) convertView.findViewById(R.id.nat10)).setText((((Naturalist) list.get(position)).grasshopper.equals("null"))? notEntered :((Naturalist) list.get(position)).grasshopper);
			((TextView) convertView.findViewById(R.id.nat11)).setText((((Naturalist) list.get(position)).worm.equals("null"))? notEntered :((Naturalist) list.get(position)).worm);
			((TextView) convertView.findViewById(R.id.nat12)).setText((((Naturalist) list.get(position)).springtail.equals("null"))? "Not Entered" : ((Naturalist) list.get(position)).springtail );
			((TextView) convertView.findViewById(R.id.nat13)).setText((((Naturalist) list.get(position)).larvae.equals("null"))? notEntered : ((Naturalist) list.get(position)).larvae );
			((TextView) convertView.findViewById(R.id.nat14)).setText((((Naturalist) list.get(position)).woodroach.equals("null"))? notEntered : ((Naturalist) list.get(position)).woodroach);
			((TextView) convertView.findViewById(R.id.nat15)).setText((((Naturalist) list.get(position)).cadisfly.equals("null"))?  notEntered : ((Naturalist) list.get(position)).cadisfly );
			((TextView) convertView.findViewById(R.id.nat16)).setText((((Naturalist) list.get(position)).scorpion.equals("null"))?  notEntered : ((Naturalist) list.get(position)).scorpion);
			((TextView) convertView.findViewById(R.id.nat17)).setText((((Naturalist) list.get(position)).tick.equals("null"))?  notEntered : ((Naturalist) list.get(position)).tick);
			((TextView) convertView.findViewById(R.id.nat18)).setText((((Naturalist) list.get(position)).earwig.equals("null"))? notEntered : ((Naturalist) list.get(position)).earwig);
			((TextView) convertView.findViewById(R.id.nat19)).setText((((Naturalist) list.get(position)).stonefly.equals("null"))? notEntered :((Naturalist) list.get(position)).stonefly);
			((TextView) convertView.findViewById(R.id.nat20)).setText((((Naturalist) list.get(position)).caterpillar.equals("null"))? notEntered :((Naturalist) list.get(position)).caterpillar);
			((TextView) convertView.findViewById(R.id.nat21)).setText((((Naturalist) list.get(position)).centipede.equals("null"))? "Not Entered" : ((Naturalist) list.get(position)).centipede );
			((TextView) convertView.findViewById(R.id.nat22)).setText((((Naturalist) list.get(position)).aphid.equals("null"))? notEntered : ((Naturalist) list.get(position)).aphid );
			((TextView) convertView.findViewById(R.id.nat23)).setText((((Naturalist) list.get(position)).booklice.equals("null"))? notEntered : ((Naturalist) list.get(position)).booklice);
			((TextView) convertView.findViewById(R.id.nat24)).setText((((Naturalist) list.get(position)).fly.equals("null"))?  notEntered : ((Naturalist) list.get(position)).fly );
			((TextView) convertView.findViewById(R.id.nat25)).setText((((Naturalist) list.get(position)).bee.equals("null"))?  notEntered : ((Naturalist) list.get(position)).bee);
			((TextView) convertView.findViewById(R.id.nat26)).setText((((Naturalist) list.get(position)).ant.equals("null"))?  notEntered : ((Naturalist) list.get(position)).ant);
			((TextView) convertView.findViewById(R.id.nat27)).setText((((Naturalist) list.get(position)).comments.equals("null"))? notEntered : ((Naturalist) list.get(position)).comments);
			
			return convertView;
		}

		public List<Naturalist> getList() {
			return list;
		}
	}

	public static class ExpandableViewAdapter extends BaseExpandableListAdapter {

		Context context;
		Review_2Activity ra;
		List<String> list;
		LayoutInflater inflater;
		List<Meteorologist> mlist;
		List<Naturalist> nlist;
		List<SoilScientist> sslist;
		List<Session> sessions;
		int size = 0;
		Map<String, String> sessionTimes;

		public ExpandableViewAdapter(Context context, List<String> list,
				String gid) {
			this.context = context;
			this.list = list;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			nlist = DatabaseManager.getCollectedDataByRole(
					DatabaseManager.NATURALIST_TABLE, gid);
			sslist = DatabaseManager.getCollectedDataByRole(
					DatabaseManager.SOIL_SCIENTIST_TABLE, gid);
			mlist = DatabaseManager.getCollectedDataByRole(
					DatabaseManager.METEOROLOGIST_TABLE, gid);
			sessions = DatabaseManager.getAllSessions();
			sessionTimes = new HashMap<String, String>();
			for (Session session : sessions) {
				sessionTimes.put(session.getId(), session.getSessionTime());
			}
		}

		@Override
		public Object getChild(int groupPos, int childPos) {
			if (list.get(groupPos).equals(Role.METEOROLOGIST)) {
				return mlist;
			}
			if (list.get(groupPos).equals(Role.NATURALIST)) {
				return nlist;
			}
			if (list.get(groupPos).equals(Role.SOIL_SCIENTIST)) {
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
			if (list.get(groupPos).equals(Role.METEOROLOGIST)) {
				return mlist.size();
			}
			if (list.get(groupPos).equals(Role.NATURALIST)) {
				return nlist.size();
			}
			if (list.get(groupPos).equals(Role.SOIL_SCIENTIST)) {
				return sslist.size();
			}
			return 0;
		}

		@SuppressWarnings("unchecked")
		@Override
		public View getChildView(int groupPos, int childPos,
				boolean isLastChild, View convertView, ViewGroup parent) {
			List<Meteorologist> _mlist = null;
			List<Naturalist> _nlist = null;
			List<SoilScientist> _sslist = null;

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.review_row_details,
						null);
			}
			if (list.get(groupPos).equals(Role.METEOROLOGIST)) {
				_mlist = (List<Meteorologist>) getChild(groupPos, childPos);

				String val = sessionTimes.get(_mlist.get(childPos).session_id);

				((TextView) convertView.findViewById(R.id.rd_n1)).setText(val);
			}

			if (list.get(groupPos).equals(Role.NATURALIST)) {
				_nlist = (List<Naturalist>) getChild(groupPos, childPos);
				String val = sessionTimes.get(_nlist.get(childPos).session_id);
				((TextView) convertView.findViewById(R.id.rd_n1)).setText(val);				
			}

			if (list.get(groupPos).equals(Role.SOIL_SCIENTIST)) {
				_sslist = (List<SoilScientist>) getChild(groupPos, childPos);
				
				String val = sessionTimes.get(_sslist.get(childPos).session_id);
				
				((TextView) convertView.findViewById(R.id.rd_n1)).setText(val);
			
			}
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
		public View getGroupView(int groupPos, boolean isExpanded,
				View convertView, ViewGroup parent) {

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

	public static class AsyncData extends AsyncTask<String, Void, List<String>> {
		Activity context;
		List<String> list;

		public AsyncData(Activity context, List<String> list) {
			this.context = context;
			this.list = list;
		}

		@Override
		protected List<String> doInBackground(String... arg0) {
			return null;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			super.onPostExecute(result);
		}

	}

}
