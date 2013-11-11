package com.example.cubicmetercommunity.app;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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

public class SelectActivityFragment extends Fragment implements OnClickListener {
	OnButtonClick buttonClick;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String[] str = {};
		String label = "";
		String meteorologist = getResources().getString(
				R.string.label_meteorologist);
		String naturalist = getResources().getString(R.string.label_naturalist);
		String soilScientist = getResources().getString(
				R.string.label_soil__scientist);
		
		List<String> list = null;

		if (getActivity().getIntent().getStringArrayExtra(meteorologist) != null) {
			str = getActivity().getIntent().getStringArrayExtra(meteorologist);
			list = Arrays.asList(getResources().getStringArray(R.array.meteorologistFragments));
			label = meteorologist;
		} else if (getActivity().getIntent().getStringArrayExtra(naturalist) != null) {
			str = getActivity().getIntent().getStringArrayExtra(naturalist);
			list = Arrays.asList(getResources().getStringArray(R.array.naturalistFragments));
			label = naturalist;
		} else if (getActivity().getIntent().getStringArrayExtra(soilScientist) != null) {
			str = getActivity().getIntent().getStringArrayExtra(soilScientist);
			list = Arrays.asList(getResources().getStringArray(R.array.soilScientistFragments));
			label = soilScientist;
		}

		((TextView) getActivity().findViewById(R.id.role)).setText(label);
		LinearLayout activitiesList = (LinearLayout) getActivity()
				.findViewById(R.id.activityList);
		Iterator<String> iter = list.iterator();
		for (String s : str) {
			Button b = new Button(getActivity());
			b.setText(s);
			final String layout = iter.next();
			b.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent i = new Intent(getActivity(),
							IndividualActivity.class);
					i.putExtra("fragmentList", layout);
					startActivity(i);

				}
			});
			activitiesList.addView(b);
		}
		((Button) getActivity().findViewById(R.id.sa_back))
				.setOnClickListener(this);
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

}
