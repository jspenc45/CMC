package com.example.cubicmetercommunity.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.cubicmetercommunity.classes.Meteorologist;
import com.example.cubicmetercommunity.classes.Naturalist;
import com.example.cubicmetercommunity.classes.SoilScientist;
import com.example.cubicmetercommunity.classes.TableIDs;
import com.example.cubicmetercommunity.dbutil.DBUtil;
import com.example.cubicmetercommunity.dbutil.DatabaseManager;
import com.example.cubicmetercommunityapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//This fragment allows users to select an activity from a list populated in Strings.xml

public class SelectActivityFragment extends Fragment implements OnClickListener {
	OnButtonClick buttonClick;
	Bundle savedInstanceState;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
		((Button) getActivity().findViewById(R.id.sa_back))
				.setOnClickListener(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		String[] str = {};
		String label = "";
		String meteorologist = getResources().getString(
				R.string.label_meteorologist);
		String naturalist = getResources().getString(R.string.label_naturalist);
		String soilScientist = getResources().getString(
				R.string.label_soil__scientist);
		boolean[] done = {};
		List<String> list = null;

		if (getActivity().getIntent().getStringArrayExtra(meteorologist) != null) {
			str = getActivity().getIntent().getStringArrayExtra(meteorologist);
			list = Arrays.asList(getResources().getStringArray(
					R.array.meteorologistFragments));
			label = meteorologist;
			done = getMeteorologistDone();
		} else if (getActivity().getIntent().getStringArrayExtra(naturalist) != null) {
			str = getActivity().getIntent().getStringArrayExtra(naturalist);
			list = Arrays.asList(getResources().getStringArray(
					R.array.naturalistFragments));
			label = naturalist;
			done = getNaturalistDone();
		} else if (getActivity().getIntent().getStringArrayExtra(soilScientist) != null) {
			str = getActivity().getIntent().getStringArrayExtra(soilScientist);
			list = Arrays.asList(getResources().getStringArray(
					R.array.soilScientistFragments));
			label = soilScientist;
			done = getSoilScientistDone();
		}
		
		

		((TextView) getActivity().findViewById(R.id.role)).setText(label);
		LinearLayout activitiesList = (LinearLayout) getActivity()
				.findViewById(R.id.activityList);
		activitiesList.removeAllViews();
		Iterator<String> iter = list.iterator();
		int x = 0;
		for (String s : str) {
			Button b = (Button) getLayoutInflater(savedInstanceState).inflate(
					R.layout.custom_button_ylw, null);
			if(done.length>x)
				if (done[x])
					b = (Button) getLayoutInflater(savedInstanceState).inflate(
						R.layout.custom_button_grn, null);

			b.setText(s);
			final String layout = iter.next();
			b.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(getActivity(),
							IndividualActivity.class);
					i.putExtra("fragmentList", layout);
					buttonClick.sendIntentWithExtra(i);

				}
			});
			activitiesList.addView(b);
			x++;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_select_activity, container,
				false);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.sa_back:
			getActivity().finish();
			break;
		}
	}
	public boolean[] getMeteorologistDone(){
		boolean[] done = {false,false,false,false,false,false,false,false};
		DBUtil db = new DBUtil();
		Map<String, Object> fields = new HashMap<String, Object>();

		fields = Meteorologist.generateFieldsAll();
		TableIDs ids = ((ActivitiesActivity) getActivity()).ids;
		String where = "Id" + "=" + "\'" + ids.getMeteorologistID()
				+ "\'";
		JSONObject resp = db.select(DatabaseManager.METEOROLOGIST_TABLE,
				fields, where);
		Meteorologist met = null;
		try {
			met = new Meteorologist(resp.getJSONArray("records")
					.getJSONObject(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if(!met.getCanopy_cover().equals("null")) done[0] = true;
		if(!met.getFahrenheit().equals("null")) done[1] = true;
		if(!met.getPressure().equals("null")) done[2] = true;
		if(!met.getHumidity().equals("null")) done[3] = true;
		if(!met.getWind().equals("null")) done[4] = true;
		if(!met.getRainfall().equals("null")) done[5] = true;
		if(!met.getCloud().equals("null")) done[6] = true;
		if(!met.getComments().equals("null")) done[7] = true;
		
		return done;
	}
	public boolean[] getNaturalistDone(){
		boolean[] done = {false,false};
		DBUtil db = new DBUtil();
		Map<String, Object> fields = new HashMap<String, Object>();

		fields = Naturalist.generateFieldsAll();
		TableIDs ids = ((ActivitiesActivity) getActivity()).ids;
		String where = "Id" + "=" + "\'" + ids.getNaturalistID()
				+ "\'";
		JSONObject resp = db.select(DatabaseManager.NATURALIST_TABLE,
				fields, where);
		Naturalist nat = null;
		try {
			nat = new Naturalist(resp.getJSONArray("records")
					.getJSONObject(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if(!nat.getAnts().equals("null")) done[0] = true;
		if(!nat.getComments().equals("null")) done[1] = true;
		
		return done;
	}
	public boolean[] getSoilScientistDone(){
		boolean[] done = {false,false,false,false,false,false,false};
		DBUtil db = new DBUtil();
		Map<String, Object> fields = new HashMap<String, Object>();

		fields = SoilScientist.generateFieldsAll();
		TableIDs ids = ((ActivitiesActivity) getActivity()).ids;
		String where = "Id" + "=" + "\'" + ids.getSoilScientistID()
				+ "\'";
		JSONObject resp = db.select(DatabaseManager.SOIL_SCIENTIST_TABLE,
				fields, where);
		SoilScientist sSci = null;
		try {
			sSci = new SoilScientist(resp.getJSONArray("records")
					.getJSONObject(0));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if(!sSci.getSoil_color().equals("null")) done[0] = true;
		if(!sSci.getSoil_ph().equals("null")) done[1] = true;
		if(!sSci.getSoil_texture().equals("null")) done[2] = true;
		if(!sSci.getSoil_consistency().equals("null")) done[3] = true;
		if(!sSci.getSoil_moisture().equals("null")) done[4] = true;
		if(!sSci.getSoil_odor().equals("null")) done[5] = true;
		if(!sSci.getComments().equals("null")) done[6] = true;
		
		return done;
	}

}
