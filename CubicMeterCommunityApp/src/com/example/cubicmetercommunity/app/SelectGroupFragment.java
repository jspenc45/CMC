package com.example.cubicmetercommunity.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.Roles;
import com.example.cubicmetercommunity.classes.Session;
import com.example.cubicmetercommunity.dbutil.DBUtil;
import com.example.cubicmetercommunityapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class SelectGroupFragment extends Fragment implements OnClickListener {
	OnButtonClick buttonClick;
	List<Group> groups;
	DBUtil db;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		db = new DBUtil();
		((Button) getActivity().findViewById(R.id.sg_back))
				.setOnClickListener(this);
		((Button) getActivity().findViewById(R.id.sg_next))
				.setOnClickListener(this);

		groups = getGroups();
		Group newGroup = new Group("*New Group*","null");
		groups.add(newGroup);
		
		Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner1);
		ArrayAdapter<Group> adapter = new ArrayAdapter<Group>(getActivity()
				.getBaseContext(), android.R.layout.simple_spinner_item, groups);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			RadioButton newRb = (RadioButton) getActivity().findViewById(
					R.id.radioButton1);
			RadioButton existing = (RadioButton) getActivity().findViewById(
					R.id.radioButton2);

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String selection = parent.getItemAtPosition(pos).toString();

				if (selection.equals("*New Group*")) {
					existing.setEnabled(false);
					newRb.toggle();
				} else
					existing.setEnabled(true);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	private List<Group> getGroups() {
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(Group.sqlNAME, null);
		fields.put(Group.sqlID, null);
		JSONObject response = db.select("Group__c", fields, null);

		return Group.getGroups(response);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_select_group, container,
				false);
	}
	public void createNewSession(Group group){
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(Session.sqlGROUP_ID, group.getId());
		String date = new Date().toString();
		fields.put(Session.sqlTIME, date);
		JSONObject response = db.create("Session__c", fields);
		Session session = null;
		try {
			session = new Session(response.getString("id"), group.getId(), date);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		((RoleActivity) getActivity()).setSession(session);
		createNewRoles(group,session);
	}
	private void createNewRoles(Group group,Session session) {
		Roles role = new Roles();
		Map<String, Object> fields = new HashMap<String, Object>();
		
		fields.put(Roles.sqlSESSION_ID, session.getId());
		fields.put(Roles.sqlGROUP_ID, group.getId());
		
		//Set up Meteorologist Table
		JSONObject response = db.create("Meteorologist__c", fields);
		try {
			role.setMeteorologistID(response.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Set up Naturalist Table
		response = db.create("Naturalist__c", fields);
		try {
			role.setNaturalistID(response.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		//Set up Soil Scientist Table
		response = db.create("SoilScientist__c", fields);
		try {
			role.setSoilScientistID(response.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/*
		((RoleActivity) getActivity()).setSession(session);*/
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sg_back:
			getActivity().finish();
			break;
		case R.id.sg_next:
			Spinner s = (Spinner) getActivity().findViewById(R.id.spinner1);
			Group group = (Group) s.getSelectedItem();
			
			//New Group
			if (group.getName().equals("*New Group*"))
				buttonClick.LoadNextFragmentWithBackstack(new NewGroupFragment());
			//Existing session
			else if (((RadioButton)getActivity().findViewById(R.id.radioButton2)).isChecked()) {
				buttonClick.LoadNextFragmentWithBackstack(new PreviousSessionFragment());
				((RoleActivity) getActivity()).setGroup(group);
			}
			//New Session
			else {
				createNewSession(group);
				((RoleActivity) getActivity()).setGroup(group);
				buttonClick.LoadNextFragment(new SelectRoleFragment());
			}
			break;
		}
	}

}
