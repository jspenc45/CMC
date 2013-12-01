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

		Button finish = (Button) getActivity().findViewById(R.id.act_finish);
		if (finish != null) {
			finish.setOnClickListener(this);
		}

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
}
