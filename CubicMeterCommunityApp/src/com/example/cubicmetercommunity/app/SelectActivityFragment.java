package com.example.cubicmetercommunity.app;

import com.example.cubicmetercommunityapp.R;

import android.app.Activity;
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
		String meteorologist = getResources().getString(R.string.label_meteorologist);
		String naturalist = getResources().getString(R.string.label_naturalist);
		String soilScientist = getResources().getString(R.string.label_soil__scientist);
		
		if(getActivity().getIntent().getStringArrayExtra(meteorologist)!=null) {
			str = getActivity().getIntent().getStringArrayExtra(meteorologist);
			label = meteorologist;
		}
		else if(getActivity().getIntent().getStringArrayExtra(naturalist)!=null) {
			str = getActivity().getIntent().getStringArrayExtra(naturalist);
			label = naturalist;
		}
		else if(getActivity().getIntent().getStringArrayExtra(soilScientist)!=null) {
			str = getActivity().getIntent().getStringArrayExtra(soilScientist);
			label = soilScientist;
		}
		((TextView)getActivity().findViewById(R.id.role)).setText(label);
		LinearLayout activitiesList = (LinearLayout) getActivity().findViewById(R.id.activityList);
		for (String s : str){
			Button b = new Button(getActivity());
			b.setText(s);
			activitiesList.addView(b);
		}
		((Button) getActivity().findViewById(R.id.sa_back))
				.setOnClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater
				.inflate(R.layout.fragment_select_activity, container, false);
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
