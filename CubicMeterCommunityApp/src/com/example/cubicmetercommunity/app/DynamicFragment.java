package com.example.cubicmetercommunity.app;

import java.util.HashMap;
import java.util.Map;

import com.example.cubicmetercommunity.classes.TableIDs;
import com.example.cubicmetercommunity.dbutil.DBUtil;
import com.example.cubicmetercommunityapp.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class DynamicFragment extends Fragment implements OnClickListener {
	OnButtonClick buttonClick;
	int resource;

	@SuppressLint("ValidFragment")
	public DynamicFragment(int resource) {
		this.resource = resource;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// BACK BUTTON
		Button back = (Button) getActivity().findViewById(R.id.act_back);
		if (back != null)
			back.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					getActivity().finish();
				}
			});

		// FINISH BUTTON
		Button finish = (Button) getActivity().findViewById(R.id.act_finish);
		if (finish != null) {
			finish.setOnClickListener(this);
		}

		setSoilColorOnClicks();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(resource, container, false);
	}

	@Override
	public void onClick(View v) {
		// Initialize tables
		TableIDs ids = ((IndividualActivity) getActivity()).getTables();
		boolean success = false;
		DBUtil db = new DBUtil();
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.clear();

		// Get inputs
		EditText temp;
		RadioGroup rg;

		// TEMPERATURE
		if ((temp = (EditText) getActivity().findViewById(R.id.text_fahr)) != null) {

			EditText temp2 = (EditText) getActivity().findViewById(
					R.id.text_cels);
			String fahrenheit = temp.getText().toString();
			String celsius = temp2.getText().toString();
			if (fahrenheit.equals("") || celsius.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("fahrenheit__c", fahrenheit);
				fields.put("celsius__c", celsius);

				db.update("Meteorologist__c", ids.getMeteorologistID(), fields);
				success = true;
			}
		}
		// WIND
		else if ((temp = (EditText) getActivity().findViewById(R.id.text_wind)) != null) {
			String wind = temp.getText().toString();
			if (wind.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("wind__c", wind);

				db.update("Meteorologist__c", ids.getMeteorologistID(), fields);
				success = true;
			}
		}
		// PRESSURE
		else if ((temp = (EditText) getActivity().findViewById(
				R.id.text_pressure)) != null) {
			String pressure = temp.getText().toString();
			if (pressure.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("pressure__c", pressure);

				db.update("Meteorologist__c", ids.getMeteorologistID(), fields);
				success = true;
			}
		}
		// RAINFALL
		else if ((temp = (EditText) getActivity().findViewById(
				R.id.text_rainfall)) != null) {
			String rainfall = temp.getText().toString();
			if (rainfall.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("rainfall__c", rainfall);

				db.update("Meteorologist__c", ids.getMeteorologistID(), fields);
				success = true;
			}
		}
		// HUMIDITY
		else if ((temp = (EditText) getActivity().findViewById(
				R.id.text_humidity)) != null) {
			String humidity = temp.getText().toString();
			if (humidity.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("humidity__c", humidity);

				db.update("Meteorologist__c", ids.getMeteorologistID(), fields);
				success = true;
			}
		}

		// CLOUDS
		else if ((rg = (RadioGroup) getActivity().findViewById(
				R.id.cloudSelector)) != null) {
			RadioButton selected = (RadioButton) getActivity().findViewById(
					rg.getCheckedRadioButtonId());

			if (selected == null)
				showToast();
			else {
				String cloud = selected.getText().toString();
				// Do update on DB
				fields.put("cloud__c", cloud);

				db.update("Meteorologist__c", ids.getMeteorologistID(), fields);
				success = true;
			}
		}
		
		// SOIL CONSISTENCY
		else if ((rg = (RadioGroup) getActivity().findViewById(
				R.id.selectSoilConsistency)) != null) {
			RadioButton selected = (RadioButton) getActivity().findViewById(
					rg.getCheckedRadioButtonId());

			if (selected == null)
				showToast();
			else {
				String soilC = selected.getText().toString();
				// Do update on DB
				fields.put("soil_consistency__c", soilC);

				db.update("SoilScientist__c", ids.getSoilScientistID(), fields);
				success = true;
			}
		}
		
		// SOIL TEXTURE
		else if ((temp = (EditText) getActivity().findViewById(
				R.id.text_soil_texture)) != null) {
			String soilText = temp.getText().toString();
			if (soilText.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("soil_texture__c", soilText);

				db.update("SoilScientist__c", ids.getSoilScientistID(), fields);
				success = true;
			}
		}
		
		// SOIL MOISTURE
		else if ((temp = (EditText) getActivity().findViewById(
				R.id.text_soil_moisture)) != null) {
			String soilMoist = temp.getText().toString();
			if (soilMoist.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("soil_moisture__c", soilMoist);

				db.update("SoilScientist__c", ids.getSoilScientistID(), fields);
				success = true;
			}
		}
		// SOIL PH/TYPE
		else if ((rg = (RadioGroup) getActivity().findViewById(
				R.id.selectSoilType)) != null) {
			temp = (EditText) getActivity().findViewById(R.id.text_soilPH);
			RadioButton selected = (RadioButton) getActivity().findViewById(
					rg.getCheckedRadioButtonId());
			String soilPh = temp.getText().toString();

			if (selected == null || soilPh.equals(""))
				showToast();
			else {
				String soilType = selected.getText().toString();
				// Do update on DB
				fields.put("soil_type__c", soilType);
				fields.put("soil_ph__c", soilPh);

				db.update("SoilScientist__c", ids.getSoilScientistID(), fields);
				success = true;
			}
		}
		// Additional - Meteorologist
		else if ((temp = (EditText) getActivity().findViewById(
				R.id.text_m_additional)) != null) {
			String additional = temp.getText().toString();
			if (additional.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("comments__c", additional);

				db.update("Meteorologist__c", ids.getMeteorologistID(), fields);
				success = true;
			}
		}
		// Additional - Naturalist
		else if ((temp = (EditText) getActivity().findViewById(
				R.id.text_n_additional)) != null) {
			String additional = temp.getText().toString();
			if (additional.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("comments__c", additional);

				db.update("Naturalist__c", ids.getNaturalistID(), fields);
				success = true;
			}
		}
		// Additional - Soil Scientist
		else if ((temp = (EditText) getActivity().findViewById(
				R.id.text_s_additional)) != null) {
			String additional = temp.getText().toString();
			if (additional.equals(""))
				showToast();
			else {
				// Do update on DB
				fields.put("comments__c", additional);

				db.update("SoilScientist__c", ids.getSoilScientistID(), fields);
				success = true;
			}
		}

		// End
		if (success) {
			getActivity().finish();
		}
	}

	public void showToast() {
		Toast.makeText(getActivity(), "One of your boxes is blank!",
				Toast.LENGTH_LONG).show();
	}

	public void setSoilColorOnClicks() {
		ImageButton button = (ImageButton) getActivity().findViewById(
				R.id.black);
		if (button != null) {
			OnClickListener sColor = new soilColor();
			button.setOnClickListener(sColor);
			getActivity().findViewById(R.id.darkred).setOnClickListener(sColor);
			getActivity().findViewById(R.id.brown).setOnClickListener(sColor);
			getActivity().findViewById(R.id.brownorange).setOnClickListener(
					sColor);
			getActivity().findViewById(R.id.sienna).setOnClickListener(sColor);
			getActivity().findViewById(R.id.orange).setOnClickListener(sColor);
			getActivity().findViewById(R.id.brightorange).setOnClickListener(
					sColor);
			getActivity().findViewById(R.id.paleorange).setOnClickListener(
					sColor);
			getActivity().findViewById(R.id.pinkorange).setOnClickListener(
					sColor);
			getActivity().findViewById(R.id.red).setOnClickListener(sColor);
			getActivity().findViewById(R.id.darkyellow).setOnClickListener(
					sColor);
			getActivity().findViewById(R.id.gray).setOnClickListener(sColor);
		}
	}

	public class soilColor implements OnClickListener {

		// SOIL COLOR
		@Override
		public void onClick(View v) {
			String color = ((ImageButton) v).getTag().toString();

			TableIDs ids = ((IndividualActivity) getActivity()).getTables();
			DBUtil db = new DBUtil();
			Map<String, Object> fields = new HashMap<String, Object>();
			fields.clear();
			fields.put("soil_color__c", color);

			db.update("SoilScientist__c", ids.getSoilScientistID(), fields);

			getActivity().finish();

		}

	}
}
