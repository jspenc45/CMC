package com.example.cubicmetercommunity.app;

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

public class SelectRoleFragment extends Fragment implements OnClickListener {
	OnButtonClick buttonClick;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		((Button) getActivity().findViewById(R.id.sr_main_menu))
				.setOnClickListener(this);
		((Button) getActivity().findViewById(R.id.sr_meteorologist))
				.setOnClickListener(this);
		((Button) getActivity().findViewById(R.id.sr_naturalist))
				.setOnClickListener(this);
		((Button) getActivity().findViewById(R.id.sr_soil_scientist))
				.setOnClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_select_role, container, false);
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(getActivity().getBaseContext(), ActivitiesActivity.class);
		
		switch (v.getId()) {
		case R.id.sr_meteorologist:
			String meteorologist = getResources().getString(R.string.label_meteorologist);
			i.putExtra(meteorologist,getResources().getStringArray(R.array.meteorologistActivities));
			buttonClick.sendIntentWithExtra(i);
			break;
		case R.id.sr_naturalist:
			String naturalist = getResources().getString(R.string.label_naturalist);
			i.putExtra(naturalist,getResources().getStringArray(R.array.naturalistActivities));
			buttonClick.sendIntentWithExtra(i);
			break;
		case R.id.sr_soil_scientist:
			String soilScientist = getResources().getString(R.string.label_soil__scientist);
			i.putExtra(soilScientist,getResources().getStringArray(R.array.soilScientistActivities));
			buttonClick.sendIntentWithExtra(i);
			break;
		case R.id.sr_main_menu:
			getActivity().finish();
			break;
		}
	}

}
