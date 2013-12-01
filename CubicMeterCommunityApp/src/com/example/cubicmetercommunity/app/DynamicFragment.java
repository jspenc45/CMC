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
