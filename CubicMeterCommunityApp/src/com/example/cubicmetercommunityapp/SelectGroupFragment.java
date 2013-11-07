package com.example.cubicmetercommunityapp;

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

public class SelectGroupFragment extends Fragment implements OnClickListener{
	OnButtonClick buttonClick;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		((Button)getActivity().findViewById(R.id.sg_back)).setOnClickListener(this);
		((Button)getActivity().findViewById(R.id.sg_next)).setOnClickListener(this);
		
		Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.groups, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			RadioButton newRb = (RadioButton) getActivity().findViewById(R.id.radioButton1);
			RadioButton existing = (RadioButton) getActivity().findViewById(R.id.radioButton2);

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				String selection = parent.getItemAtPosition(pos).toString();
				Log.d("debug", selection);
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
		return inflater.inflate(R.layout.fragment_select_group, container, false);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.sg_back:
			getActivity().finish();
			break;
		case R.id.sg_next:
			buttonClick.LoadNextFragment(new SelectRoleFragment());
			break;
		}
	}

}
