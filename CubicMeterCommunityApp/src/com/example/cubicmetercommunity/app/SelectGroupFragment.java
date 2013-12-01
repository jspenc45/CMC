package com.example.cubicmetercommunity.app;

import java.util.List;
import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.classes.TableIDs;
import com.example.cubicmetercommunity.classes.Session;
import com.example.cubicmetercommunity.dbutil.DatabaseManager;
import com.example.cubicmetercommunityapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		((Button) getActivity().findViewById(R.id.sg_back))
				.setOnClickListener(this);
		((Button) getActivity().findViewById(R.id.sg_next))
				.setOnClickListener(this);

		groups = DatabaseManager.getGroups();
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_select_group, container,
				false);
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
				buttonClick.LoadNextFragmentWithBackstack(new PreviousSessionFragment(group));
			}
			//New Session
			else {
				Session session = DatabaseManager.createNewSession(group);
				TableIDs ids = DatabaseManager.createNewRoles(group,session);
				
				((RoleActivity) getActivity()).setTableIDs(ids);
				
				buttonClick.LoadNextFragment(new SelectRoleFragment());
			}
			break;
		}
	}

}
