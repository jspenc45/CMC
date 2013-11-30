package com.example.cubicmetercommunity.app;

import java.util.List;
import com.example.cubicmetercommunity.classes.Session;
import com.example.cubicmetercommunity.dbutil.SQLWork;
import com.example.cubicmetercommunityapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PreviousSessionFragment extends Fragment implements
		OnClickListener {
	OnButtonClick buttonClick;
	List<Session> sessions;

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

		sessions = SQLWork.getSessions(((RoleActivity)getActivity()).getGroup());

		Spinner spinner = (Spinner) getActivity().findViewById(
				R.id.sessionSpinner);
		ArrayAdapter<Session> adapter = new ArrayAdapter<Session>(getActivity()
				.getBaseContext(), android.R.layout.simple_spinner_item,
				sessions);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_existing_session, container, false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sg_back:
			buttonClick.popStack(this);
			break;
		case R.id.sg_next:
			Spinner spinner = (Spinner) getActivity().findViewById(
					R.id.sessionSpinner);
			Session session = (Session) spinner.getSelectedItem();
			((RoleActivity) getActivity()).setSession(session);
			buttonClick.LoadNextFragment(new SelectRoleFragment());
			break;
		}
	}
}
